package pilotage.gup.bilan;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;

import pilotage.database.gup.ComIncidentAppliDatabaseService;
import pilotage.database.gup.ComIncidentDetectionDatabaseService;
import pilotage.database.gup.ComIncidentDomaineDatabaseService;
import pilotage.database.gup.ComIncidentInterlocuteurDatabaseService;
import pilotage.database.gup.ComIncidentServiceDatabaseService;
import pilotage.database.gup.IncidentsGupDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Com_Incident_Appli;
import pilotage.metier.Com_Incident_Detection;
import pilotage.metier.Com_Incident_Domaine;
import pilotage.metier.Com_Incident_Interlocuteur;
import pilotage.metier.Com_Incident_Service;
import pilotage.metier.Incidents_Gup;

public class ShowDetailBilanGupAction extends AbstractAction {

	private static final long serialVersionUID = 4063463615929323914L;
	
	private List<Incidents_Gup> listIncidentsEC;
	private List<String> listAppliEC;
	private List<String> listServiceEC;
	private List<String> listDomaineEC;
	private List<String> listDetectionEC;
	private List<String> listInterlocEC;
	private List<String> listDateDebEC;
	private Date selectedDate;
	
	private List<Incidents_Gup> listIncidentsCL;
	private List<String> listAppliCL;
	private List<String> listServiceCL;
	private List<String> listDomaineCL;
	private List<String> listDetectionCL;
	private List<String> listInterlocCL;
	private List<String> listDateDebCL;
	private List<String> listDateFinCL;
	
	private Integer incidentsAuJour;
	private Integer incidentsResolu;
	
	public List<Incidents_Gup> getListIncidentsEC() {
		return listIncidentsEC;
	}

	public void setListIncidentsEC(List<Incidents_Gup> listIncidentsEC) {
		this.listIncidentsEC = listIncidentsEC;
	}

	public List<String> getListAppliEC() {
		return listAppliEC;
	}

	public void setListAppliEC(List<String> listAppliEC) {
		this.listAppliEC = listAppliEC;
	}

	public List<String> getListServiceEC() {
		return listServiceEC;
	}

	public void setListServiceEC(List<String> listServiceEC) {
		this.listServiceEC = listServiceEC;
	}

	public List<String> getListDomaineEC() {
		return listDomaineEC;
	}

	public void setListDomaineEC(List<String> listDomaineEC) {
		this.listDomaineEC = listDomaineEC;
	}

	public List<String> getListDetectionEC() {
		return listDetectionEC;
	}

	public void setListDetectionEC(List<String> listDetectionEC) {
		this.listDetectionEC = listDetectionEC;
	}

	public List<String> getListInterlocEC() {
		return listInterlocEC;
	}

	public void setListInterlocEC(List<String> listInterlocEC) {
		this.listInterlocEC = listInterlocEC;
	}

	public List<Incidents_Gup> getListIncidentsCL() {
		return listIncidentsCL;
	}

	public void setListIncidentsCL(List<Incidents_Gup> listIncidentsCL) {
		this.listIncidentsCL = listIncidentsCL;
	}

	public List<String> getListAppliCL() {
		return listAppliCL;
	}

	public void setListAppliCL(List<String> listAppliCL) {
		this.listAppliCL = listAppliCL;
	}

	public List<String> getListServiceCL() {
		return listServiceCL;
	}

	public void setListServiceCL(List<String> listServiceCL) {
		this.listServiceCL = listServiceCL;
	}

	public List<String> getListDomaineCL() {
		return listDomaineCL;
	}

	public void setListDomaineCL(List<String> listDomaineCL) {
		this.listDomaineCL = listDomaineCL;
	}

	public List<String> getListDetectionCL() {
		return listDetectionCL;
	}

	public void setListDetectionCL(List<String> listDetectionCL) {
		this.listDetectionCL = listDetectionCL;
	}

	public List<String> getListInterlocCL() {
		return listInterlocCL;
	}

	public void setListInterlocCL(List<String> listInterlocCL) {
		this.listInterlocCL = listInterlocCL;
	}

	public List<String> getListDateDebEC() {
		return listDateDebEC;
	}

	public void setListDateDebEC(List<String> listDateDebEC) {
		this.listDateDebEC = listDateDebEC;
	}

	/**
	 * @return the listDateDebCL
	 */
	public List<String> getListDateDebCL() {
		return listDateDebCL;
	}

	/**
	 * @param listDateDebCL the listDateDebCL to set
	 */
	public void setListDateDebCL(List<String> listDateDebCL) {
		this.listDateDebCL = listDateDebCL;
	}

	/**
	 * @return the listDateFinCL
	 */
	public List<String> getListDateFinCL() {
		return listDateFinCL;
	}

	/**
	 * @param listDateFinCL the listDateFinCL to set
	 */
	public void setListDateFinCL(List<String> listDateFinCL) {
		this.listDateFinCL = listDateFinCL;
	}

	/**
	 * @return the incidentsAuJour
	 */
	public Integer getIncidentsAuJour() {
		return incidentsAuJour;
	}

	/**
	 * @param incidentsAuJour the incidentsAuJour to set
	 */
	public void setIncidentsAuJour(Integer incidentsAuJour) {
		this.incidentsAuJour = incidentsAuJour;
	}

	/**
	 * @return the incidentsResolu
	 */
	public Integer getIncidentsResolu() {
		return incidentsResolu;
	}

	/**
	 * @param incidentsResolu the incidentsResolu to set
	 */
	public void setIncidentsResolu(Integer incidentsResolu) {
		this.incidentsResolu = incidentsResolu;
	}

	/**
	 * @return the selectedDate
	 */
	public Date getSelectedDate() {
		return selectedDate;
	}

	/**
	 * @param selectedDate the selectedDate to set
	 */
	public void setSelectedDate(Date selectedDate) {
		this.selectedDate = selectedDate;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
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
		
		listIncidentsEC = IncidentsGupDatabaseService.getAllEnCours(debDateTime,finDateTime);
		listIncidentsCL = IncidentsGupDatabaseService.getAllClotures(debDateTime, finDateTime);
		listAppliEC = new ArrayList<String>();
		listServiceEC = new ArrayList<String>();
		listDomaineEC = new ArrayList<String>();
		listDetectionEC = new ArrayList<String>();
		listInterlocEC = new ArrayList<String>();
		listDateDebEC = new ArrayList<String>();
		
		listAppliCL = new ArrayList<String>();
		listServiceCL = new ArrayList<String>();
		listDomaineCL = new ArrayList<String>();
		listDetectionCL = new ArrayList<String>();
		listInterlocCL = new ArrayList<String>();
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
			
			listDateDebEC.add(dateDebut + " à " + heureDebut);
			
			List<Com_Incident_Service> ls = ComIncidentServiceDatabaseService.getServices(i.getId());
			List<Com_Incident_Appli> la = ComIncidentAppliDatabaseService.getAppli(i.getId());
			List<Com_Incident_Domaine> ld = ComIncidentDomaineDatabaseService.getDomaine(i.getId());
			List<Com_Incident_Interlocuteur> li = ComIncidentInterlocuteurDatabaseService.getInterlocuteurs(i.getId());
			List<Com_Incident_Detection> lde = ComIncidentDetectionDatabaseService.getDetection(i.getId());
			String s = "";
			int j = 0;
			for(Com_Incident_Service cis : ls){
					if(j==0)
						s = cis.getIdService().getNom();
					else
						s += "; " + cis.getIdService().getNom();
					j++;
			}
			listServiceEC.add(s);
			
			j=0;
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
			j=0;
			String i1 = "";
			for(Com_Incident_Interlocuteur cii : li){
				if(j==0)
					i1 = cii.getIdInterlocuteur().getNom();
				else
					i1 += "; " + cii.getIdInterlocuteur().getNom();
				j++;
			}
			listInterlocEC.add(i1);
			j=0;
			String de = "";
			for(Com_Incident_Detection cide : lde){
				if(j==0)
					de = cide.getIdDetection().getNom();
				else
					de +=  "; " + cide.getIdDetection().getNom();
				j++;
			}
			listDetectionEC.add(de);
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
				listDateDebCL.add(dateDebut + " de " + heureDebut);
				listDateFinCL.add(" à " + heureFin);
			}
			else{
				listDateDebCL.add(dateDebut + " à " + heureDebut + " au ");
				listDateFinCL.add(dateFin + " à " + heureFin);
			}
			
			List<Com_Incident_Service> ls = ComIncidentServiceDatabaseService.getServices(i.getId());
			List<Com_Incident_Appli> la = ComIncidentAppliDatabaseService.getAppli(i.getId());
			List<Com_Incident_Domaine> ld = ComIncidentDomaineDatabaseService.getDomaine(i.getId());
			List<Com_Incident_Interlocuteur> li = ComIncidentInterlocuteurDatabaseService.getInterlocuteurs(i.getId());
			List<Com_Incident_Detection> lde = ComIncidentDetectionDatabaseService.getDetection(i.getId());
			String s = "";
			int j = 0;
			for(Com_Incident_Service cis : ls){
					if(j==0)
						s = cis.getIdService().getNom();
					else
						s += "; " + cis.getIdService().getNom();
					j++;
			}
			listServiceCL.add(s);
			
			j=0;
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
			j=0;
			String i1 = "";
			for(Com_Incident_Interlocuteur cii : li){
				if(j==0)
					i1 = cii.getIdInterlocuteur().getNom();
				else
					i1 += "; " + cii.getIdInterlocuteur().getNom();
				j++;
			}
			listInterlocCL.add(i1);
			j=0;
			String de = "";
			for(Com_Incident_Detection cide : lde){
				if(j==0)
					de = cide.getIdDetection().getNom();
				else
					de +=  "; " + cide.getIdDetection().getNom();
				j++;
			}
			listDetectionCL.add(de);
		}

		incidentsAuJour = listIncidentsEC.size() + listIncidentsCL.size();
		incidentsResolu = listIncidentsCL.size();
		return OK;
	}

}
