package pilotage.gup.incidents;

import java.util.ArrayList;
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
import pilotage.service.constants.PilotageConstants;
import pilotage.utils.Pagination;

public class ShowIncidentsGupAction extends AbstractAction{

	private static final long serialVersionUID = -6832020304443615138L;
	private List<Incidents_Gup> listIncident;
	private Pagination<Incidents_Gup> pagination;
	private List<String> service;
	private List<String> appli;
	private List<String> domaine;
	private List<String> interloc;
	private List<String> detection;
	private List<String> date;
	private String provenance;
	
	private int page;
	private int nrPages;
	private int nrPerPage;
	
	public List<Incidents_Gup> getListIncident() {
		return listIncident;
	}

	public void setListIncident(List<Incidents_Gup> listIncident) {
		this.listIncident = listIncident;
	}

	public Pagination<Incidents_Gup> getPagination() {
		return pagination;
	}

	public void setPagination(Pagination<Incidents_Gup> pagination) {
		this.pagination = pagination;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getNrPages() {
		return nrPages;
	}

	public void setNrPages(int nrPages) {
		this.nrPages = nrPages;
	}

	public int getNrPerPage() {
		return nrPerPage;
	}

	public void setNrPerPage(int nrPerPage) {
		this.nrPerPage = nrPerPage;
	}

	public List<String> getService() {
		return service;
	}

	public void setService(List<String> service) {
		this.service = service;
	}

	public List<String> getAppli() {
		return appli;
	}

	public void setAppli(List<String> appli) {
		this.appli = appli;
	}

	public List<String> getDomaine() {
		return domaine;
	}

	public void setDomaine(List<String> domaine) {
		this.domaine = domaine;
	}

	public List<String> getInterloc() {
		return interloc;
	}

	public void setInterloc(List<String> interloc) {
		this.interloc = interloc;
	}

	public List<String> getDetection() {
		return detection;
	}

	public void setDetection(List<String> detection) {
		this.detection = detection;
	}

	public List<String> getDate() {
		return date;
	}

	public void setDate(List<String> date) {
		this.date = date;
	}

	public String getProvenance() {
		return provenance;
	}

	public void setProvenance(String provenance) {
		this.provenance = provenance;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		try{
			if(page < 1)
				page = 1;
			else if(nrPages != 0 && page > nrPages)
				page = nrPages;		
			if(nrPerPage == 0)
				nrPerPage = PilotageConstants.NB_INCIDENTS_PER_PAGE;
			pagination = new Pagination<Incidents_Gup>(page, nrPerPage);
			listIncident = IncidentsGupDatabaseService.getAll(pagination);
			
			
			date = new ArrayList<String>();
			service = new ArrayList<String>();
			appli = new ArrayList<String>();
			domaine = new ArrayList<String>();
			interloc = new ArrayList<String>();
			detection = new ArrayList<String>();
			
			for(Incidents_Gup i : listIncident){
				
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
				
				date.add(dateDebut + " " + heureDebut);
				
				/*DateTime fin = new DateTime();
				fin = i.getDate_fin();
				String dateFin = fin.getDayOfMonth() + "/" +  fin.getMonthOfYear() + "/" + fin.getYear();
				Integer heure2 = fin.getHourOfDay();
				String hh2 = heure2.toString();
				if(hh2.length() == 1)
					hh2 = "0" + hh2;
				Integer minutes2 = fin.getMinuteOfHour();
				String mm2 = minutes2.toString();
				if(mm2.length() == 1)
					mm2 = "0" + mm2;
				String heureFin = hh2 + ":" + mm2;*/ 
				
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
				service.add(s);
				
				j=0;
				String a = "";
				for(Com_Incident_Appli cia : la){
					if(j==0)
						a = cia.getIdAppli().getApplicatif();
					else
						a += "; " + cia.getIdAppli().getApplicatif() ;
					j++;
				}
				appli.add(a);
				j=0;
				String d = "";
				for(Com_Incident_Domaine cid : ld){
					if(j==0)
						d = cid.getDomaine().getNom();
					else
						d += "; " + cid.getDomaine().getNom();
					j++;
				}
				domaine.add(d);
				j=0;
				String i1 = "";
				for(Com_Incident_Interlocuteur cii : li){
					if(j==0)
						i1 = cii.getIdInterlocuteur().getNom();
					else
						i1 += "; " + cii.getIdInterlocuteur().getNom();
					j++;
				}
				interloc.add(i1);
				j=0;
				String de = "";
				for(Com_Incident_Detection cide : lde){
					if(j==0)
						de = cide.getIdDetection().getNom();
					else
						de +=  "; " + cide.getIdDetection().getNom();
					j++;
				}
				detection.add(de);
			}
			provenance = "ShowIncidentsGup";
			return OK;
			
		} catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Affichage liste incidents GUP - ", e);
			return ERROR;	
		}
	}

}
