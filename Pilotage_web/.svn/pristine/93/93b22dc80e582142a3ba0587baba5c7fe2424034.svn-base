package pilotage.gup.incidents;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;

import pilotage.database.admin.ParametreDatabaseService;
import pilotage.database.destinataires.DestinatairesDatabaseService;
import pilotage.database.gup.IncidentsGupDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Incidents_Gup;
import pilotage.service.constants.PilotageConstants;
import pilotage.service.mail.MailService;
import pilotage.service.string.StringConverter;

public class SendIncidentsGupAction extends AbstractAction {

	private static final long serialVersionUID = -282594209386850810L;
	private Integer selectedID;
	private Incidents_Gup inc;
	private String nomDomaines;
	private String nomApplis;
	private String nomInterlocuteur;
	private String nomServices;
	private String dateinc;
	private String datefinc;
	private boolean deb;
	private boolean suivi;
	private boolean fin;
	private String interlocCSelected;
	private final String SEPARATEUR = ", ";
	private String interlocESelected;
	private String provenance;
	
	private Integer filtreDomaine;
	private Integer filtreService;
	private Integer filtreApplicatif;
	private Integer filtreDetection;
	private Integer filtreInterlocuteur;
	private Integer filtreEtat;
	private String filtreArs;
	private String filtreDate;
	private String filtreDateFin;
	private Integer filtreImpact;
	private String filtreDescription;
	private String filtreResolution;
	private Integer filtrePilote;
	
	public Integer getSelectedID() {
		return selectedID;
	}

	public void setSelectedID(Integer selectedID) {
		this.selectedID = selectedID;
	}

	public String getNomApplis() {
		return nomApplis;
	}

	public void setNomApplis(String nomApplis) {
		this.nomApplis = nomApplis;
	}

	public Incidents_Gup getInc() {
		return inc;
	}

	public void setInc(Incidents_Gup inc) {
		this.inc = inc;
	}

	public String getDateinc() {
		return dateinc;
	}

	public void setDateinc(String dateinc) {
		this.dateinc = dateinc;
	}

	public String getNomServices() {
		return nomServices;
	}

	public void setNomServices(String nomServices) {
		this.nomServices = nomServices;
	}

	public String getNomDomaines() {
		return nomDomaines;
	}

	public void setNomDomaines(String nomDomaines) {
		this.nomDomaines = nomDomaines;
	}

	public String getNomInterlocuteur() {
		return nomInterlocuteur;
	}

	public void setNomInterlocuteur(String nomInterlocuteur) {
		this.nomInterlocuteur = nomInterlocuteur;
	}

	public boolean getDeb() {
		return deb;
	}

	public void setDeb(boolean deb) {
		this.deb = deb;
	}

	public boolean getSuivi() {
		return suivi;
	}

	public void setSuivi(boolean suivi) {
		this.suivi = suivi;
	}

	public Integer getFiltreDomaine() {
		return filtreDomaine;
	}

	public void setFiltreDomaine(Integer filtreDomaine) {
		this.filtreDomaine = filtreDomaine;
	}

	public Integer getFiltreService() {
		return filtreService;
	}

	public void setFiltreService(Integer filtreService) {
		this.filtreService = filtreService;
	}

	public Integer getFiltreApplicatif() {
		return filtreApplicatif;
	}

	public void setFiltreApplicatif(Integer filtreApplicatif) {
		this.filtreApplicatif = filtreApplicatif;
	}

	public Integer getFiltreDetection() {
		return filtreDetection;
	}

	public void setFiltreDetection(Integer filtreDetection) {
		this.filtreDetection = filtreDetection;
	}

	public Integer getFiltreInterlocuteur() {
		return filtreInterlocuteur;
	}

	public void setFiltreInterlocuteur(Integer filtreInterlocuteur) {
		this.filtreInterlocuteur = filtreInterlocuteur;
	}

	public Integer getFiltreEtat() {
		return filtreEtat;
	}

	public void setFiltreEtat(Integer filtreEtat) {
		this.filtreEtat = filtreEtat;
	}

	public String getFiltreArs() {
		return filtreArs;
	}

	public void setFiltreArs(String filtreArs) {
		this.filtreArs = filtreArs;
	}

	public String getFiltreDate() {
		return filtreDate;
	}

	public void setFiltreDate(String filtreDate) {
		this.filtreDate = filtreDate;
	}

	public String getFiltreDateFin() {
		return filtreDateFin;
	}

	public void setFiltreDateFin(String filtreDateFin) {
		this.filtreDateFin = filtreDateFin;
	}

	public String getDatefinc() {
		return datefinc;
	}

	public void setDatefinc(String datefinc) {
		this.datefinc = datefinc;
	}

	public Integer getFiltreImpact() {
		return filtreImpact;
	}

	public void setFiltreImpact(Integer filtreImpact) {
		this.filtreImpact = filtreImpact;
	}

	public String getFiltreDescription() {
		return filtreDescription;
	}

	public void setFiltreDescription(String filtreDescription) {
		this.filtreDescription = filtreDescription;
	}

	public String getFiltreResolution() {
		return filtreResolution;
	}

	public void setFiltreResolution(String filtreResolution) {
		this.filtreResolution = filtreResolution;
	}

	public Integer getFiltrePilote() {
		return filtrePilote;
	}

	public void setFiltrePilote(Integer filtrePilote) {
		this.filtrePilote = filtrePilote;
	}

	public boolean getFin() {
		return fin;
	}

	public void setFin(boolean fin) {
		this.fin = fin;
	}

	public String getInterlocCSelected() {
		return interlocCSelected;
	}

	public void setInterlocCSelected(String interlocCSelected) {
		this.interlocCSelected = interlocCSelected;
	}

	public String getInterlocESelected() {
		return interlocESelected;
	}

	public void setInterlocESelected(String interlocESelected) {
		this.interlocESelected = interlocESelected;
	}

	public String getProvenance() {
		return provenance;
	}

	public void setProvenance(String provenance) {
		this.provenance = provenance;
	}

	public String getSEPARATEUR() {
		return SEPARATEUR;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		try{
			//Récupération de l'incident
			
			setInc(IncidentsGupDatabaseService.get(selectedID));
			//mise de la date en format affichable
			Integer month = inc.getDate_deb().getMonthOfYear();
			String month2 = month.toString();
			if(month2.length() == 1)
				month2 = "0" + month2;
			Integer day = inc.getDate_deb().getDayOfMonth();
			String day2 = day.toString();
			if(day2.length() == 1)
				day2 = "0" + day2;		
			Integer hour = inc.getDate_deb().getHourOfDay();
			String hour2 = hour.toString();
			if(hour2.length() == 1)
				hour2 = "0" + hour2;		
			Integer min = inc.getDate_deb().getMinuteOfHour();
			String min2 = min.toString();
			if(min2.length() == 1)
				min2 = "0" + min2;
			
			dateinc = day2 + "/" + month2 + "/" + inc.getDate_deb().getYear() + " à " + hour2 + ":" + min2;
			
			/*if(inc.getDate_fin() != null && !inc.getDate_fin().equals("")){
				Integer monthf = inc.getDate_deb().getMonthOfYear();
				String monthf2 = monthf.toString();
				if(monthf2.length() == 1)
					monthf2 = "0" + monthf2;
				Integer dayf = inc.getDate_deb().getDayOfMonth();
				String dayf2 = dayf.toString();
				if(dayf2.length() == 1)
					dayf2 = "0" + dayf2;		
				Integer hourf = inc.getDate_deb().getHourOfDay();
				String hourf2 = hourf.toString();
				if(hourf2.length() == 1)
					hourf2 = "0" + hourf2;		
				Integer minf = inc.getDate_deb().getMinuteOfHour();
				String minf2 = minf.toString();
				if(minf2.length() == 1)
					minf2 = "0" + minf2;
				
				setDatefinc(dayf2 + "/" + monthf2 + "/" + inc.getDate_fin().getYear() + " à " + hourf2 + ":" + minf2);
			}*/
			
			String body = getBody();
			
			//récupération des destinataires de l'incident
			String[] interlocC = StringConverter.stringToArray(interlocCSelected, SEPARATEUR);
			String[] interlocE = StringConverter.stringToArray(interlocESelected, SEPARATEUR);
			String[] listDestinataireArrayCCI;
			
			if(!interlocESelected.equals(""))
				listDestinataireArrayCCI = new String[(interlocC.length + interlocE.length)];
			else
				listDestinataireArrayCCI = new String[interlocC.length];
			
			int i;
			for(i = 0; i < interlocC.length ; ++i){
				listDestinataireArrayCCI[i] = DestinatairesDatabaseService.get(Integer.parseInt(interlocC[i])).getMail();
			}
			int j = i;
			if(!interlocESelected.equals("")){
				for(i=0; i < interlocE.length ; ++i){
					listDestinataireArrayCCI[j] = DestinatairesDatabaseService.get(Integer.parseInt(interlocE[i])).getMail();
					j++;
				}
			}
					
			Map<String, String> images = new HashMap<String, String>();
			Context ctx = new InitialContext();
			images.put("head_deb", ctx.lookup(PilotageConstants.NOC_HEAD_CONFIG_PATH).toString());
			images.put("foot_deb", ctx.lookup(PilotageConstants.NOC_FOOT_CONFIG_PATH).toString());
			images.put("logo", ctx.lookup(PilotageConstants.BILAN_LOGO_CONFIG_PATH).toString());
			
			//constitution de l'objet
			String objet = "";
			if(deb)
				objet = "Communiqué GUP : Début d'incident le " + dateinc; 
			else if (suivi)
				objet = "Communiqué GUP : Suivi d'incident " + nomApplis + " le " + dateinc; 
			else if (fin) objet = "Communiqué GUP : Fin d'incident " + nomApplis + " le " + dateinc; 
			
			//envoi de l'email
			boolean checkSend = MailService.sendEmailGup(null, null, listDestinataireArrayCCI, objet , body , null, images);
			if(!checkSend){
				error = getText("debordement.noc.envoi.error");
				return ERROR;
			}
			info = "Le communiqué a été envoyé";
	
			return provenance;
		
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
			
			//body
			htmlPage.append("<body>");
			
			htmlPage.append("<div class='contentTable' >");
			
			htmlPage.append("<img alt='GUP' src='cid:head_deb' /><br/>");
			htmlPage.append("<table class='dataTable' rules='all'>");
			htmlPage.append("<colgroup>");
			htmlPage.append("<col width='20%' />");
			htmlPage.append("<col width='80%'/>");
			htmlPage.append("</colgroup>");
			
			htmlPage.append("<thead>");
			htmlPage.append("<tr>");
			htmlPage.append("<th class='BigTitle' colspan='2'>Communiqué</th>");
			htmlPage.append("</tr>");
			htmlPage.append("</thead>");
			htmlPage.append("<tbody>");
			
			String sd = nomDomaines;
			sd = sd.replace("-", "<br/>");

				htmlPage.append("<tr>");
				htmlPage.append("<td align='center' class='colonne1'> <b> Domaine </b> </td>");
				htmlPage.append("<td align='center' class='colonne'><b>"  + sd + "<b/></td>");
				htmlPage.append("</tr>");
				
				htmlPage.append("<tr>");
				htmlPage.append("<td align='center' class='colonne1'> <b> Service </b> </td>");
				htmlPage.append("<td align='center' class='colonne'><b>"  + nomServices + "<b/></td>");
				htmlPage.append("</tr>");
				
				htmlPage.append("<tr>");
				htmlPage.append("<td align='center' class='colonne1'> <b> Applicatif impacté</b></td>");
				htmlPage.append("<td align='center' class='colonne'><b>" + nomApplis + "</b></td>");
				htmlPage.append("</tr>");
				
			
				htmlPage.append("<tr>");
				htmlPage.append("<td align='center' class='colonne1'> <b> Date et Heure de début d'incident</b></td>");
				
				Integer month = inc.getDate_deb().getMonthOfYear();
				String month2 = month.toString();
				if(month2.length() == 1)
					month2 = "0" + month2;
				Integer day = inc.getDate_deb().getDayOfMonth();
				String day2 = day.toString();
				if(day2.length() == 1)
					day2 = "0" + day2;		
				Integer hour = inc.getDate_deb().getHourOfDay();
				String hour2 = hour.toString();
				if(hour2.length() == 1)
					hour2 = "0" + hour2;		
				Integer min = inc.getDate_deb().getMinuteOfHour();
				String min2 = min.toString();
				if(min2.length() == 1)
					min2 = "0" + min2;
				
				htmlPage.append("<td align='center' class='colonne'><b>" + dateinc + "</b></td>");
				htmlPage.append("</tr>");
				
				htmlPage.append("<tr>");
				htmlPage.append("<td align='center' class='colonne1'> <b> Date et Heure de fin d'incident</b></td>");
				if(inc.getDate_fin() != null){
					Integer monthf = inc.getDate_fin().getMonthOfYear();
					String monthf2 = monthf.toString();
					if(monthf2.length() == 1)
						monthf2 = "0" + monthf2;
					Integer dayf = inc.getDate_fin().getDayOfMonth();
					String dayf2 = dayf.toString();
					if(dayf2.length() == 1)
						dayf2 = "0" + dayf2;		
					Integer hourf = inc.getDate_fin().getHourOfDay();
					String hourf2 = hourf.toString();
					if(hourf2.length() == 1)
						hourf2 = "0" + hourf2;		
					Integer minf = inc.getDate_fin().getMinuteOfHour();
					String minf2 = minf.toString();
					if(minf2.length() == 1)
						minf2 = "0" + minf2;
					
					htmlPage.append("<td align='center' class='colonne'><b>" + dayf2 + "/" + monthf2 +"/"+ inc.getDate_fin().getYear() + " à " + hourf2 + ":" + minf2 + "</b></td>");
				}
				else
					htmlPage.append("<td align='center' class='colonne'><b>" + "En cours" + "</b></td>");
				htmlPage.append("</tr>");
				
				
				htmlPage.append("<tr>");
				htmlPage.append("<td align='center' class='colonne1'> <b>Equipe résolvante</b></td>");
				htmlPage.append("<td align='center' class='colonne'><b>" + nomInterlocuteur + "</b></td>");
				htmlPage.append("</tr>");
				
				htmlPage.append("<tr>");
				htmlPage.append("<td align='center' class='colonne1'> <b>Commentaires </b></td>");
				String s = inc.getDescription_prob();
				s = s.replace("\r\n", "<br/>µ");
				
				String[] split = s.split("µ");
				String s2 = split[0];
				int i; s ="";
				for(i = 1; i < split.length ; i++){
					s += split[i];
				}
				
				htmlPage.append("<td align='left' style='padding-left=10px' class='colonne'> <b>" + s2 + "</b>" + s + "</td>");
				htmlPage.append("</tr> ");
				
				if(inc.getArs() != null &&  !inc.getArs().equals("")){
					htmlPage.append("<tr>");
					htmlPage.append("<td align='center' class='colonne1'> <b>N° ARS </b></td>");
					htmlPage.append("<td align='center' class='colonne'><b>" + inc.getArs() + "</b></td>");
					htmlPage.append("</tr> ");
				}
				
			htmlPage.append("</tbody>");
			
			htmlPage.append("</table>");
			//foot
			htmlPage.append(" <br/> <br/><img alt='LOGO' src='cid:logo' /><br/>");
			htmlPage.append("<div class='textBottom'> <br/>  " + ParametreDatabaseService.get("SIGNATURE_GUP").getValeur() + " <br/> </div>");
		
			htmlPage.append(" <br/><img alt='GUP' src='cid:foot_deb' /><br/>");
			htmlPage.append("</div>");
			htmlPage.append("</body>");
			htmlPage.append("</html>");
			
		return htmlPage.toString();
		
	}

}
