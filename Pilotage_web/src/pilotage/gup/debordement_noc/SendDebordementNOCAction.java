package pilotage.gup.debordement_noc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;

import pilotage.database.admin.ParametreDatabaseService;
import pilotage.database.gup.DebordementNOCDatabaseService;
import pilotage.database.gup.DebordementNocDestinataireDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Debordement_NOC;
import pilotage.metier.Debordement_Noc_Destinataire;
import pilotage.service.constants.PilotageConstants;
import pilotage.service.mail.MailService;

public class SendDebordementNOCAction extends  AbstractAction{

	private static final long serialVersionUID = -3997292920006700640L;
	private Integer idDebNoc;
	private Debordement_NOC dn;

	public Integer getIdDebNoc() {
		return idDebNoc;
	}

	public void setIdDebNoc(Integer idDebNoc) {
		this.idDebNoc = idDebNoc;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		try{
			//Récupération de la liste de destinataires
			List<Debordement_Noc_Destinataire> listDebNocDestinataire = DebordementNocDestinataireDatabaseService.getAll();
			
			if(listDebNocDestinataire == null || listDebNocDestinataire.isEmpty()){
				error = getText("debordementNOC.envoi.aucun.destinataire");
				return ERROR;
			}
			
			dn = DebordementNOCDatabaseService.get(idDebNoc);
			//constitution du bilan
			String body = getBody();
			Integer nbDest = 0;
			Integer nbDestCC = 0;
			for(Debordement_Noc_Destinataire l : listDebNocDestinataire){
				if(l.getCc().equals(0))
					nbDest++;
			}
			for(Debordement_Noc_Destinataire l : listDebNocDestinataire){
				if(l.getCc().equals(1))
					nbDestCC++;
			}
			String[] listDestinataireArray = new String[nbDest];
			String[] listDestinataireCCArray = new String[nbDestCC];
			Integer i = 0;
			for(Debordement_Noc_Destinataire l : listDebNocDestinataire){
				if(l.getCc().equals(0)){
					listDestinataireArray[i] = l.getDestinataire().getMail();
					i++;
				}
			}
			i=0;
			for(Debordement_Noc_Destinataire l : listDebNocDestinataire){
				if(l.getCc().equals(1)){
					listDestinataireCCArray[i] = l.getDestinataire().getMail();
					i++;
				}
			}
			Map<String, String> images = new HashMap<String, String>();
			Context ctx = new InitialContext();
			images.put("head_deb", ctx.lookup(PilotageConstants.NOC_HEAD_CONFIG_PATH).toString());
			images.put("foot_deb", ctx.lookup(PilotageConstants.NOC_FOOT_CONFIG_PATH).toString());
			images.put("logo", ctx.lookup(PilotageConstants.BILAN_LOGO_CONFIG_PATH).toString());
			String objet = "Débordement NOC ";
			
			if(dn.getInc_noc() != null && !dn.getInc_noc().trim().equals(""))
				objet += "n° " + dn.getInc_noc(); 
			else if(dn.getInc_op() != null && !dn.getInc_op().trim().equals(""))
				objet += "n° " + dn.getInc_op(); 
			 
			boolean checkSend = MailService.sendEmailGup(listDestinataireArray, listDestinataireCCArray, null, objet , body , null, images);
			if(!checkSend){
				error = getText("debordement.noc.envoi.error");
				return ERROR;
			}
			info = "Le débordement NOC a été envoyé";
			return OK;
		
		} catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Envoi du debordement NOC", e);
			return ERROR;
		}
	}
	
	private String getBody() throws Exception{
		
		//Récupération du contenu de la CSS
			Context ctx = new InitialContext();
			String path = ctx.lookup(PilotageConstants.NOC_CSS_CONFIG_PATH).toString();

			BufferedReader input =  new BufferedReader(new FileReader(path));
			String line;
			StringBuffer style = new StringBuffer();
			while (( line = input.readLine()) != null){
				style.append(line);
				style.append(System.getProperty("line.separator"));
			}		
			input.close();
			
			//Constitution de la page HTML
			StringBuffer htmlPage = new StringBuffer();
			htmlPage.append("<html>");
			
			//Head
			htmlPage.append("<head>");
			htmlPage.append("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
			htmlPage.append("<style type='text/css'>");
			htmlPage.append(style.toString());
			htmlPage.append("</style>");
			htmlPage.append("<title>Bilan</title>");
			htmlPage.append("</head>");
			
			
			htmlPage.append("<body>");
			
			htmlPage.append("<div class='contentTable' >");
			
			htmlPage.append("<img alt='GUP' src='cid:head_deb' /><br/>");
			htmlPage.append("<table class='dataTable' rules='all'>");
			htmlPage.append("<colgroup>");
			htmlPage.append("<col width='7cm' />");
			htmlPage.append("<col width='12cm'/>");
			htmlPage.append("</colgroup>");
			
			htmlPage.append("<thead>");
			htmlPage.append("<tr>");
				htmlPage.append("<th class='BigTitle' colspan='2'>Débordement NOC</th>");
			htmlPage.append("</tr>");
			htmlPage.append("</thead>");
			htmlPage.append("<tbody>");
			
				htmlPage.append("<tr>");
				htmlPage.append("<td align='center' class='colonne1'> <b> Date et Heure de l'appel</b></td>");
				
				Integer month = dn.getDatetime().getMonthOfYear();
				String month2 = month.toString();
				if(month2.length() == 1)
					month2 = "0" + month2;
				Integer day = dn.getDatetime().getDayOfMonth();
				String day2 = day.toString();
				if(day2.length() == 1)
					day2 = "0" + day2;		
				Integer hour = dn.getDatetime().getHourOfDay();
				String hour2 = hour.toString();
				if(hour2.length() == 1)
					hour2 = "0" + hour2;		
				Integer min = dn.getDatetime().getMinuteOfHour();
				String min2 = min.toString();
				if(min2.length() == 1)
					min2 = "0" + min2;
				
				htmlPage.append("<td align='center' class='colonne'>" + day2 + "/" + month2 +"/"+ dn.getDatetime().getYear() + " à " + hour2 + ":" + min2 + "</td>");
				htmlPage.append("</tr>");
				
				htmlPage.append("<tr>");
				htmlPage.append("<td align='center' class='colonne1'> <b> Opérateur </b> </td>");
				htmlPage.append("<td align='center' class='colonne'>" + dn.getOperateur() + "</td>");
				htmlPage.append("</tr>");
				
				htmlPage.append("<tr>");
				htmlPage.append("<td align='center' class='colonne1'> <b> N° Incident NOC</b></td>");
				if(dn.getInc_noc() != null)
					htmlPage.append("<td align='center' class='colonne'>" + dn.getInc_noc() + "</td>");
				else
					htmlPage.append("<td align='center' class='colonne'></td>");
				htmlPage.append("</tr>");
				
				htmlPage.append("<tr>");
				htmlPage.append("<td align='center' class='colonne1'> <b>Et/ou n° incident Opérateur</b></td>");
				if(dn.getInc_op() != null)
					htmlPage.append("<td align='center' class='colonne'>" + dn.getInc_op() + "</td>");
				else
					htmlPage.append("<td align='center' class='colonne'></td>");
				htmlPage.append("</tr>");
				
				htmlPage.append("<tr>");
				htmlPage.append("<td align='center' class='colonne1'> <b>Information / Action demandée </b></td>");
				htmlPage.append("<td align='center' class='colonne'>" + dn.getInfo_act() + "</td>");
				htmlPage.append("</tr> ");
				
			htmlPage.append("</tbody>");
			
			htmlPage.append("</table>");
			htmlPage.append(" <br/> <br/><img alt='LOGO' src='cid:logo' /><br/>");
			htmlPage.append("<div class='textBottom'> <br/>  " + ParametreDatabaseService.get("SIGNATURE_GUP").getValeur() + " <br/> </div>");
		
			htmlPage.append(" <br/><img alt='GUP' src='cid:foot_deb' /><br/>");
			htmlPage.append("</div>");
			htmlPage.append("</body>");
			htmlPage.append("</html>");
			
		return htmlPage.toString();
		
	}

}
