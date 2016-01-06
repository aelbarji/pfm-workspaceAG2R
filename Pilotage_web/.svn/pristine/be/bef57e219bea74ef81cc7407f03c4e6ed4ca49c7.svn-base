package pilotage.gup.bilan;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.naming.Context;
import javax.naming.InitialContext;

import org.joda.time.DateTime;

import pilotage.database.admin.ParametreDatabaseService;
import pilotage.database.gup.ComBilanDestinataireDatabaseService;
import pilotage.database.gup.ComIncidentAppliDatabaseService;
import pilotage.database.gup.ComIncidentDomaineDatabaseService;
import pilotage.database.gup.IncidentsGupDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Com_Bilan_Destinataire;
import pilotage.metier.Com_Incident_Appli;
import pilotage.metier.Com_Incident_Domaine;
import pilotage.metier.Incidents_Gup;
import pilotage.service.constants.PilotageConstants;
import pilotage.service.date.DateService;
import pilotage.service.mail.MailService;

public class SendBilanIncidentsGupAction extends AbstractAction {

	private static final long serialVersionUID = -8490517230828689376L;
	
	private Date selectedDate;
	private List<Incidents_Gup> listIncidentsEC;
	private List<Incidents_Gup> listIncidentsCL;
	
	private List<String> listAppliEC;
	private List<String> listDomaineEC;
	private List<String> listDateDebEC;

	private List<String> listAppliCL;
	private List<String> listDomaineCL;
	private List<String> listDateDebCL;
	private List<String> listDateFinCL;

	public List<String> getListAppliEC() {
		return listAppliEC;
	}

	public void setListAppliEC(List<String> listAppliEC) {
		this.listAppliEC = listAppliEC;
	}

	public List<String> getListDomaineEC() {
		return listDomaineEC;
	}

	public void setListDomaineEC(List<String> listDomaineEC) {
		this.listDomaineEC = listDomaineEC;
	}

	public List<String> getListDateDebEC() {
		return listDateDebEC;
	}

	public void setListDateDebEC(List<String> listDateDebEC) {
		this.listDateDebEC = listDateDebEC;
	}

	public List<String> getListAppliCL() {
		return listAppliCL;
	}

	public void setListAppliCL(List<String> listAppliCL) {
		this.listAppliCL = listAppliCL;
	}

	public List<String> getListDomaineCL() {
		return listDomaineCL;
	}

	public void setListDomaineCL(List<String> listDomaineCL) {
		this.listDomaineCL = listDomaineCL;
	}
	
	public List<String> getListDateDebCL() {
		return listDateDebCL;
	}

	public void setListDateDebCL(List<String> listDateDebCL) {
		this.listDateDebCL = listDateDebCL;
	}

	public List<String> getListDateFinCL() {
		return listDateFinCL;
	}

	public void setListDateFinCL(List<String> listDateFinCL) {
		this.listDateFinCL = listDateFinCL;
	}

	public Date getSelectedDate() {
		return selectedDate;
	}

	public void setSelectedDate(Date selectedDate) {
		this.selectedDate = selectedDate;
	}
	
	public List<Incidents_Gup> getListIncidentsEC() {
		return listIncidentsEC;
	}

	public void setListIncidentsEC(List<Incidents_Gup> listIncidentsEC) {
		this.listIncidentsEC = listIncidentsEC;
	}

	public List<Incidents_Gup> getListIncidentsCL() {
		return listIncidentsCL;
	}

	public void setListIncidentsCL(List<Incidents_Gup> listIncidentsCL) {
		this.listIncidentsCL = listIncidentsCL;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		try{
			//Récupération de la liste de destinataires
			List<Com_Bilan_Destinataire> listBilanDestinataire =  ComBilanDestinataireDatabaseService.getAll();
			
			if(listBilanDestinataire == null || listBilanDestinataire.isEmpty()){
				error = getText("bilan.envoi.aucun.destinataire");
				return ERROR;
			}
			
			// récupération des données
			

			Calendar calendarD = Calendar.getInstance();
			calendarD.setTime(selectedDate);
			calendarD.set(Calendar.HOUR_OF_DAY, 0);
			calendarD.set(Calendar.MINUTE, 0);
			calendarD.set(Calendar.MINUTE, 0);
			
			DateTime debDateTime = new DateTime(calendarD.getTime());
			
			Calendar calendarF = Calendar.getInstance();
			calendarF.setTime(selectedDate);
			calendarF.set(Calendar.HOUR_OF_DAY, 23);
			calendarF.set(Calendar.MINUTE, 59);
			calendarF.set(Calendar.MINUTE, 59);
			
			DateTime finDateTime = new DateTime(calendarF.getTime());
			
			listIncidentsEC = IncidentsGupDatabaseService.getAllEnCours(debDateTime, finDateTime);
			listIncidentsCL = IncidentsGupDatabaseService.getAllClotures(debDateTime, finDateTime);		
			recupDonnées();
			
			//constitution du bilan
			String body = getBody();
			
			//envoi
			Integer nbDest = 0;
			Integer nbDestCC = 0;
			for(Com_Bilan_Destinataire l : listBilanDestinataire){
				if(l.getCc().equals(0))
					nbDest++;
			}
			for(Com_Bilan_Destinataire l : listBilanDestinataire){
				if(l.getCc().equals(1))
					nbDestCC++;
			}
			String[] listDestinataireArray = new String[nbDest];
			String[] listDestinataireCCArray = new String[nbDestCC];
			Integer i = 0;
			for(Com_Bilan_Destinataire l : listBilanDestinataire){
				if(l.getCc().equals(0)){
					listDestinataireArray[i] = l.getDestinataire().getMail();
					i++;
				}
			}
			i=0;
			for(Com_Bilan_Destinataire l : listBilanDestinataire){
				if(l.getCc().equals(1)){
					listDestinataireCCArray[i] = l.getDestinataire().getMail();
					i++;
				}
			}
			
			String objet = "Bilan GUP du " + DateService.dateToStr(selectedDate, DateService.p1);
			Map<String, String> images = new HashMap<String, String>();
			Context ctx = new InitialContext();
			images.put("head_deb", ctx.lookup(PilotageConstants.NOC_HEAD_CONFIG_PATH).toString());
			images.put("foot_deb", ctx.lookup(PilotageConstants.NOC_FOOT_CONFIG_PATH).toString());
			images.put("logo", ctx.lookup(PilotageConstants.BILAN_LOGO_CONFIG_PATH).toString());
			
			
			boolean checkSend = MailService.sendEmailGup(listDestinataireArray, listDestinataireCCArray, null, objet , body , null, images);
			if(!checkSend){
				error = getText("bilan.gup.envoi.error");
				return ERROR;
			}
			info = "Le bilan a été envoyé";
			
			
			if(!checkSend){
				error = getText("bilan.envoi.error");
				return ERROR;
			}
	
			info = MessageFormat.format(getText("bilan.envoi.valide"), DateService.dateToStr(selectedDate, DateService.p1));
			return OK;
		} catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Envoi du bilan du " + DateService.dateToStr(selectedDate, DateService.p1) + " - ", e);
			return ERROR;
		}
	}
	
	@SuppressWarnings("unused")
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
			htmlPage.append("<th class='BigTitle' colspan='3'>" + "Bilan GUP" + " du " + DateService.dateToStr(selectedDate, DateService.p1) + "</th>");
		htmlPage.append("</tr>");
		htmlPage.append("</thead>");
		htmlPage.append("<tbody>");  ////////////////////////////
	
		htmlPage.append("<tr>");
		htmlPage.append("<td colspan='3' align='center' class='colonne1'>Incidents en cours</td>");
		htmlPage.append("</tr>");

		htmlPage.append("<tr>");
		htmlPage.append("<td width='33%' align='center' 	class='colonne1'>Applicatif</td>");
		htmlPage.append("<td width='33%' align='center'		class='colonne1'>Domaine</td>");
		htmlPage.append("<td width='33%' align='center'		class='colonne1'>Date et Heure incident</td>");
		htmlPage.append("</tr>");
		
		int i=0;
		if(listIncidentsEC.isEmpty()){
			htmlPage.append("<tr>");
			htmlPage.append("<td colspan='3' align='center' class='colonne'>" + "RAS" + "</td>");
			htmlPage.append("</tr>");
		}
		else{
			for(Incidents_Gup inc : listIncidentsEC){
				htmlPage.append("<tr>");
					htmlPage.append("<td align='center' class='colonne'>" + listAppliEC.get(i) + "</td>");
					htmlPage.append("<td align='center' class='colonne'>" + listDomaineEC.get(i) + "</td>");
					htmlPage.append("<td align='center' class='colonne'>" + listDateDebEC.get(i) + "</td>");

				htmlPage.append("</tr>");
			i++;
			}
		}
		htmlPage.append("</td>");
		htmlPage.append("</tr>");
		
		htmlPage.append("<tr>");
		htmlPage.append("<td colspan='3' align='center' class='colonne1'>Incidents cloturés</td>");
		htmlPage.append("</tr>");
		//Incidents clotures --> titre des colonnes
		htmlPage.append("<tr>");
		htmlPage.append("<td width='33%' align='center'		class='colonne1'>Applicatif</td>");
		htmlPage.append("<td width='33%' align='center'		class='colonne1'>Domaine</td>");
		htmlPage.append("<td width='33%' align='center'		class='colonne1'>Date et Heure incident</td>");
		htmlPage.append("</tr>");
		//Incidents clotures --> liste des incidents
		i=0;
		if(listIncidentsCL.isEmpty()){
			htmlPage.append("<tr>");
			htmlPage.append("<td colspan='3' align='center' class='colonne'>" + "RAS" + "</td>");
			htmlPage.append("</tr>");
		}
		else{
			for(Incidents_Gup inc : listIncidentsCL){
				htmlPage.append("<tr>");
					htmlPage.append("<td align='center' class='colonne'>" + listAppliCL.get(i) + "</td>");
					htmlPage.append("<td align='center' class='colonne'>" + listDomaineCL.get(i) + "</td>");
					htmlPage.append("<td align='center' class='colonne'>" + listDateDebCL.get(i) + listDateFinCL.get(i) + "</td>");
				htmlPage.append("</tr>");
			i++;
			}
		}
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
	
	private void recupDonnées(){
		
		listAppliEC = new ArrayList<String>();
		listDomaineEC = new ArrayList<String>();
		listDateDebEC = new ArrayList<String>();
		
		listAppliCL = new ArrayList<String>();
		listDomaineCL = new ArrayList<String>();
		listDateDebCL = new ArrayList<String>();
		listDateFinCL = new ArrayList<String>();
		
		for(Incidents_Gup i : listIncidentsEC){
			
			// mise de l'heure à un format affichable
			DateTime deb = new DateTime();
			deb = i.getDate_deb();
				Integer dd1 = deb.getDayOfMonth();
				String dd = dd1.toString();
				if(dd.length() == 1)
					dd = "0" + dd;
				Integer mon = deb.getMonthOfYear();
				String mo = mon.toString();
				if(mo.length() == 1)
					mo = "0" + mo;
			String dateDebut = dd + "/" +  mo + "/" + deb.getYear();
			Integer heure = deb.getHourOfDay();
			String hh = heure.toString();
			if(hh.length() == 1)
				hh = "0" + hh;
			Integer minutes = deb.getMinuteOfHour();
			String mm = minutes.toString();
			if(mm.length() == 1)
				mm = "0" + mm;
			String heureDebut = hh + ":" + mm;
			
			listDateDebEC.add("le " + dateDebut + " à " + heureDebut);
			
			List<Com_Incident_Appli> la = ComIncidentAppliDatabaseService.getAppli(i.getId());
			List<Com_Incident_Domaine> ld = ComIncidentDomaineDatabaseService.getDomaine(i.getId());
			int j = 0;
			
			String a = "";
			for(Com_Incident_Appli cia : la){
				if(j==0)
					a = cia.getIdAppli().getApplicatif();
				else
					a += "; " + cia.getIdAppli().getApplicatif() ;
				j++;
			}
			listAppliEC.add(a);
			j=0;
			String d = "";
			for(Com_Incident_Domaine cid : ld){
				if(j==0)
					d = cid.getDomaine().getNom();
				else
					d += "; " + cid.getDomaine().getNom();
				j++;
			}
			listDomaineEC.add(d);
		}
		
		for(Incidents_Gup i : listIncidentsCL){
			
			// mise de l'heure à un format affichable
			DateTime deb = new DateTime();
			deb = i.getDate_deb();
				Integer dd1 = deb.getDayOfMonth();
				String dd = dd1.toString();
				if(dd.length() == 1)
					dd = "0" + dd;
				Integer mon = deb.getMonthOfYear();
				String mo = mon.toString();
				if(mo.length() == 1)
					mo = "0" + mo;
			String dateDebut = dd + "/" +  mo + "/" + deb.getYear();
			Integer heure = deb.getHourOfDay();
			String hh = heure.toString();
			if(hh.length() == 1)
				hh = "0" + hh;
			Integer minutes = deb.getMinuteOfHour();
			String mm = minutes.toString();
			if(mm.length() == 1)
				mm = "0" + mm;
			String heureDebut = hh + ":" + mm;
			
			DateTime fin = new DateTime();
			fin = i.getDate_fin();
			Integer dfin = fin.getDayOfMonth();
			String dfin2 = dfin.toString();
			dfin2 = (dfin2.length() > 1) ? dfin2 : "0" + dfin2;
			Integer mfin =  fin.getMonthOfYear();
			String mfin2 = mfin.toString();
			mfin2 = (mfin2.length() > 1) ? mfin2 : "0" + mfin2;
			String dateFin = dfin2 + "/" +  mfin2 + "/" + fin.getYear();
			
			Integer heure2 = fin.getHourOfDay();
			String hh2 = heure2.toString();
			hh2 = (hh2.length() > 1) ? hh2 : "0" + hh2;
			Integer minutes2 = fin.getMinuteOfHour();
			String mm2 = minutes2.toString();
			mm2 = (mm2.length() > 1) ? mm2 : "0" + mm2;
			String heureFin = hh2 + ":" + mm2;
			
			if(dateDebut.equals(dateFin)){
				listDateDebCL.add("le " + dateDebut + " de " + heureDebut);
				listDateFinCL.add(" à " + heureFin);
			}
			else{
				listDateDebCL.add("le " + dateDebut + " à " + heureDebut + " au ");
				listDateFinCL.add(dateFin + " à " + heureFin);
			}
			
			List<Com_Incident_Appli> la = ComIncidentAppliDatabaseService.getAppli(i.getId());
			List<Com_Incident_Domaine> ld = ComIncidentDomaineDatabaseService.getDomaine(i.getId());
			
			int j = 0;
			String a = "";
			for(Com_Incident_Appli cia : la){
				if(j==0)
					a = cia.getIdAppli().getApplicatif();
				else
					a += "; " + cia.getIdAppli().getApplicatif() ;
				j++;
			}
			listAppliCL.add(a);
			j=0;
			String d = "";
			for(Com_Incident_Domaine cid : ld){
				if(j==0)
					d = cid.getDomaine().getNom();
				else
					d += "; " + cid.getDomaine().getNom();
				j++;
			}
			listDomaineCL.add(d);
		}
	}
}
