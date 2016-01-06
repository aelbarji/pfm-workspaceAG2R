package pilotage.astreintes.actions.planning;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.joda.time.DateTime;

import pilotage.database.admin.ParametreDatabaseService;
import pilotage.database.astreintes.AstreinteDestinatairesDatabaseService;
import pilotage.database.astreintes.AstreintePlanningDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Astreinte_Destinataires;
import pilotage.metier.Astreinte_Domaine;
import pilotage.metier.Astreinte_Planning;
import pilotage.service.constants.PilotageConstants;
import pilotage.service.date.DateService;
import pilotage.service.mail.MailService;

public class AjaxAstreintePlanningAction extends AbstractAction{

	private static final long serialVersionUID = -3028091544153552980L;
	private String titre;
	private InputStream excelStream;

	public InputStream getExcelStream() {
		return excelStream;
	}

	public void setExcelStream(InputStream excelStream) {
		this.excelStream = excelStream;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();

		try {
			//Récupération de la liste de destinataires
			List<Astreinte_Destinataires> listAstreinteDestinataire =  AstreinteDestinatairesDatabaseService.getAll();
			
			if(listAstreinteDestinataire == null || listAstreinteDestinataire.isEmpty()){
				error = getText("bilan.envoi.aucun.destinataire");
				return error;
			}
			String[] listDestinataireArray = new String[listAstreinteDestinataire.size()];
			for(int i = 0; i < listAstreinteDestinataire.size(); ++i){
				listDestinataireArray[i] = listAstreinteDestinataire.get(i).getMail();
			}
			
			String heureAstrPlan = ParametreDatabaseService.get(PilotageConstants.ASTR_PLANNING_HEURE).getValeur();
			String stringDebAstrPlan = request.getParameter("dateDebAstrPlan");
			DateTime firstday = DateService.strToJodaDateTime(stringDebAstrPlan, heureAstrPlan);
			
			Date datefin = DateService.strToDate(stringDebAstrPlan);
			Date dateFinAstrPlan = DateService.addDays(datefin, 7);
			String datestringfin = DateService.dateToStr(dateFinAstrPlan, DateService.p1);
			DateTime lastday = DateService.strToJodaDateTime(datestringfin, heureAstrPlan);
			
			//récupération des astreintes de la 1ère semaine qui suit
			List<Astreinte_Planning> firstWeekPlanning = AstreintePlanningDatabaseService.getWeekPlanning(firstday.toDate(), lastday.toDate());
			Map<Astreinte_Domaine, List<Astreinte_Planning>> firstWeekMap = groupAPlanningByDomaine(firstWeekPlanning);
			
			Context ctx = new InitialContext();
			String path = ctx.lookup(PilotageConstants.ASTREINTE_CSS_CONFIG_PATH).toString();

			BufferedReader input =  new BufferedReader(new FileReader(path));
			String line;
			StringBuffer style = new StringBuffer();
			while (( line = input.readLine()) != null){
				style.append(line);
				style.append(System.getProperty("line.separator"));
			}
			input.close();
			String titremail = "PLANNING des ASTREINTES<br>SEMAINE "+DateService.getWeekOfYear(firstday)+" : du " + DateService.dateTimeToStr(firstday, DateService.p5) + " au " + DateService.dateTimeToStr(lastday, DateService.p5);
			
			StringBuffer htmlPage = new StringBuffer();
			htmlPage.append("<html><head>");
			htmlPage.append("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
			htmlPage.append("<style type='text/css'>");
			htmlPage.append(style.toString());
			htmlPage.append("</style>");
			htmlPage.append("</head><body>");
			htmlPage.append("<img alt='bandeau' src='cid:bandeau' /><br/>");
			htmlPage.append("<p class=title>&nbsp;&nbsp;"+titremail);
			htmlPage.append("</p>");
			htmlPage.append("<table class='innerTable' ");
			htmlPage.append("<colgroup>");
			htmlPage.append("<col width='5.5cm'/>");
			htmlPage.append("<col width='5cm'/>");
			htmlPage.append("<col width='6cm'/>");
			htmlPage.append("<col width='2.5cm'/>");
			htmlPage.append("</colgroup>");
			for(Astreinte_Domaine Domaine : firstWeekMap.keySet()){
				List<Astreinte_Planning> listAstreinte = firstWeekMap.get(Domaine);
				htmlPage.append("<tr ><td class='domaineTitle' colspan=4>&nbsp;&nbsp;" + Domaine.getDomaine() + "</td></tr>\n");
				Integer numline = 0;
				String trClass = "even";
				for(Astreinte_Planning Astreinte : listAstreinte) {
					if ((numline % 2)==0) {
						trClass = "even";
					} else {
						trClass = "odd";
					}
					htmlPage.append("<tr class='"+trClass+"'>\n");
					htmlPage.append("<td>&nbsp;&nbsp;"+Astreinte.getAstreinte().getType().getType()+"</td>\n");
					htmlPage.append("<td>"+Astreinte.getAstreinte().getNomPrenom()+"</td>\n");
					htmlPage.append("<td>du&nbsp;&nbsp;"+DateService.dateToStr(Astreinte.getDatedeb(),DateService.pdt2)+"&nbsp;&nbsp;au&nbsp;&nbsp;"+DateService.dateToStr(Astreinte.getDatefin(),DateService.pdt2)+"</td>\n");
					htmlPage.append("<td>"+Astreinte.getTel().replaceAll("[ ./]", "").replaceAll("^([0-9]{2})([0-9]{2})([0-9]{2})([0-9]{2})([0-9]{2})$", "$1 $2 $3 $4 $5")+"</td>\n");
					htmlPage.append("</tr>\n");
					if (Astreinte.getInfogene() != null && !Astreinte.getInfogene().equals("")) {
						htmlPage.append("<tr class='"+trClass+"'>\n");
						htmlPage.append("<td class='infogene' colspan=4>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;("+Astreinte.getInfogene()+")</td>\n");					
						htmlPage.append("</tr>\n");
					}
					numline ++;
				}
				
			}
			htmlPage.append("</table>\n<br>");
			htmlPage.append("<img alt='pied' src='cid:pied' /><br/>");
			
			htmlPage.append("</body>\n</html>");
			Map<String, String> images = new HashMap<String, String>();
			images.put("bandeau", ctx.lookup(PilotageConstants.BANDEAU_MAIL_CONFIG_PATH).toString());
			images.put("pied", ctx.lookup(PilotageConstants.PIED_MAIL_CONFIG_PATH).toString());
			
			titremail = titremail.replace("<br>", " de la ").toLowerCase();
			
			boolean checkSend = MailService.sendEmail(listDestinataireArray, titremail, htmlPage.toString(), null, images);
			
			JSONObject json = new JSONObject();
			if (checkSend){
				json.put("retour", "OK");
			} else {
				json.put("retour", "KO");
			}
			// chaque clé de notre map devient une clé dans l'objet JSON (utilisation de Json-lib)
			
			response.setContentType("application/json");
			PrintWriter out;
			try {
				out = response.getWriter();
		        out.println(json.toString());
		        out.flush();
		        out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Export des astreintes - " , e);
			return error;
		}
	}
	
	/**
	 * Groupement des plannings par domaine
	 * @param aPlannings
	 * @return
	 */
	private Map<Astreinte_Domaine, List<Astreinte_Planning>> groupAPlanningByDomaine(List<Astreinte_Planning> aPlannings){
		Map<Astreinte_Domaine, List<Astreinte_Planning>> map = new LinkedHashMap<Astreinte_Domaine, List<Astreinte_Planning>>();
		
		for(Astreinte_Planning ap : aPlannings){
			List<Astreinte_Planning> listParDomaine = map.get(ap.getDomaine());
			if(listParDomaine == null)
				listParDomaine = new ArrayList<Astreinte_Planning>();
			
			listParDomaine.add(ap);
			map.put(ap.getDomaine(), listParDomaine);
		}
		
		return map;
	}

}
