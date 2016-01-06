package pilotage.gup.incidents;

import java.util.List;

import org.joda.time.DateTime;

import pilotage.database.applicatif.ApplicatifDatabaseService;
import pilotage.database.destinataires.DestinatairesDatabaseService;
import pilotage.database.gup.ComIncidentAppliDatabaseService;
import pilotage.database.gup.ComIncidentDetectionDatabaseService;
import pilotage.database.gup.ComIncidentDomaineDatabaseService;
import pilotage.database.gup.ComIncidentEtatDatabaseService;
import pilotage.database.gup.ComIncidentInterlocuteurDatabaseService;
import pilotage.database.gup.ComIncidentServiceDatabaseService;
import pilotage.database.gup.ComServiceDatabaseService;
import pilotage.database.gup.DomaineDatabaseService;
import pilotage.database.gup.IncidentsGupDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Applicatifs_Liste;
import pilotage.metier.Com_Incident_Appli;
import pilotage.metier.Com_Incident_Detection;
import pilotage.metier.Com_Incident_Domaine;
import pilotage.metier.Com_Incident_Etat;
import pilotage.metier.Com_Incident_Interlocuteur;
import pilotage.metier.Com_Incident_Service;
import pilotage.metier.Com_Service;
import pilotage.metier.Com_domaine;
import pilotage.metier.Destinataires;
import pilotage.metier.Incidents_Gup;

public class RedirectModifyIncidentsGupAction extends AbstractAction{

	private static final long serialVersionUID = -3679574382251463125L;
	private Integer selectedID;
	private Incidents_Gup incident;
	private String[] serviceSelected;
	private String[] domaineSelected;
	private String[] appliSelected;
	private String[] detectionSelected;
	private String[] interlocuteurSelected;
	private Integer etatSelected;
	private Integer impactSelected;
	private String dateDebut;
	private String heureDebut;
	private String dateFin;
	private String heureFin;
	
	private List<Com_Incident_Etat> listEtat;
	private List<Com_domaine> listDomaine;
	private List<Applicatifs_Liste> listAppli;
	private List<Com_Service> listService;
	private List<Destinataires> listInterloc;
	
	private String prob;
	private String ars;
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
	private Integer filtrePilote;
	
	
	public Integer getSelectedID() {
		return selectedID;
	}

	public void setSelectedID(Integer selectedID) {
		this.selectedID = selectedID;
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

	public Integer getFiltrePilote() {
		return filtrePilote;
	}

	public void setFiltrePilote(Integer filtrePilote) {
		this.filtrePilote = filtrePilote;
	}

	public Incidents_Gup getIncident() {
		return incident;
	}

	public void setIncident(Incidents_Gup incident) {
		this.incident = incident;
	}

	public String[] getServiceSelected() {
		return serviceSelected;
	}

	public void setServiceSelected(String[] serviceSelected) {
		this.serviceSelected = serviceSelected;
	}

	public String[] getDomaineSelected() {
		return domaineSelected;
	}

	public void setDomaineSelected(String[] domaineSelected) {
		this.domaineSelected = domaineSelected;
	}

	public String[] getAppliSelected() {
		return appliSelected;
	}

	public void setAppliSelected(String[] appliSelected) {
		this.appliSelected = appliSelected;
	}

	public String[] getDetectionSelected() {
		return detectionSelected;
	}

	public void setDetectionSelected(String[] detectionSelected) {
		this.detectionSelected = detectionSelected;
	}

	public Integer getEtatSelected() {
		return etatSelected;
	}

	public void setEtatSelected(Integer etatSelected) {
		this.etatSelected = etatSelected;
	}

	public Integer getImpactSelected() {
		return impactSelected;
	}

	public void setImpactSelected(Integer impactSelected) {
		this.impactSelected = impactSelected;
	}

	public String[] getInterlocuteurSelected() {
		return interlocuteurSelected;
	}

	public void setInterlocuteurSelected(String[] interlocuteurSelected) {
		this.interlocuteurSelected = interlocuteurSelected;
	}

	public String getArs() {
		return ars;
	}

	public void setArs(String ars) {
		this.ars = ars;
	}

	public Object getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(String dateDebut) {
		this.dateDebut = dateDebut;
	}

	public String getHeureDebut() {
		return heureDebut;
	}

	public void setHeureDebut(String heureDebut) {
		this.heureDebut = heureDebut;
	}

	public String getDateFin() {
		return dateFin;
	}

	public void setDateFin(String dateFin) {
		this.dateFin = dateFin;
	}

	public String getHeureFin() {
		return heureFin;
	}

	public void setHeureFin(String heureFin) {
		this.heureFin = heureFin;
	}

	public List<Com_Incident_Etat> getListEtat() {
		return listEtat;
	}

	public void setListEtat(List<Com_Incident_Etat> listEtat) {
		this.listEtat = listEtat;
	}

	public List<Com_domaine> getListDomaine() {
		return listDomaine;
	}

	public void setListDomaine(List<Com_domaine> listDomaine) {
		this.listDomaine = listDomaine;
	}

	public List<Applicatifs_Liste> getListAppli() {
		return listAppli;
	}

	public void setListAppli(List<Applicatifs_Liste> listAppli) {
		this.listAppli = listAppli;
	}

	public List<Com_Service> getListService() {
		return listService;
	}

	public void setListService(List<Com_Service> listService) {
		this.listService = listService;
	}

	public List<Destinataires> getListInterloc() {
		return listInterloc;
	}

	public void setListInterloc(List<Destinataires> listInterloc) {
		this.listInterloc = listInterloc;
	}

	public String getProb() {
		return prob;
	}

	public void setProb(String prob) {
		this.prob = prob;
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
		incident = IncidentsGupDatabaseService.get(selectedID);
		
		// mise de l'heure à un format affichable
		DateTime deb = new DateTime();
		deb = incident.getDate_deb();
		dateDebut = deb.getDayOfMonth() + "/" +  deb.getMonthOfYear() + "/" + deb.getYear();
		Integer heure = deb.getHourOfDay();
		String hh = heure.toString();
		if(hh.length() == 1)
			hh = "0" + hh;
		Integer minutes = deb.getMinuteOfHour();
		String mm = minutes.toString();
		if(mm.length() == 1)
			mm = "0" + mm;
		heureDebut = hh + ":" + mm;
		
		DateTime fin = new DateTime();
		fin = incident.getDate_fin();
		if(fin != null){
			dateFin = fin.getDayOfMonth() + "/" +  fin.getMonthOfYear() + "/" + fin.getYear();
			Integer heure2 = fin.getHourOfDay();
			String hh2 = heure2.toString();
			if(hh2.length() == 1)
				hh2 = "0" + hh2;
			Integer minutes2 = fin.getMinuteOfHour();
			String mm2 = minutes2.toString();
			if(mm2.length() == 1)
				mm2 = "0" + mm2;
			heureFin = hh2 + ":" + mm2;
		}
		
		// récupération des listes 
		listEtat = ComIncidentEtatDatabaseService.getAll();
		listDomaine = DomaineDatabaseService.getAll();
		listAppli = ApplicatifDatabaseService.getAll();
		listService = ComServiceDatabaseService.getAll();
		listInterloc = DestinatairesDatabaseService.getAll();

		prob = incident.getDescription_prob();
		setArs(incident.getArs());
		if(incident.getEtat() != null)
			etatSelected = incident.getEtat().getId();
		impactSelected = incident.getImpact();
		
		// récupération des services de l'incident
		List<Com_Incident_Service> lcis = ComIncidentServiceDatabaseService.getServices(selectedID);
		int i=0;
		String[] serviceSelected2 = new String [lcis.size()];
		for(Com_Incident_Service cis : lcis){
			serviceSelected2[i] = cis.getIdService().getId().toString();
			i++;
		}
		serviceSelected = serviceSelected2;
		
		// récupération des interlocuteurs de l'incident
		List<Com_Incident_Interlocuteur> lcii = ComIncidentInterlocuteurDatabaseService.getInterlocuteurs(selectedID);
		i=0;
		String[] interlocuteurSelected2 = new String [lcii.size()];
		for(Com_Incident_Interlocuteur cii : lcii){
			interlocuteurSelected2[i] = cii.getIdInterlocuteur().getId().toString();
			i++;
		}
		setInterlocuteurSelected(interlocuteurSelected2);
		
		// récupération des détections de l'incident
		List<Com_Incident_Detection> lcid = ComIncidentDetectionDatabaseService.getDetection(selectedID);
		i=0;
		String[] detecSelected2 = new String [lcid.size()];
		for(Com_Incident_Detection cii : lcid){
			detecSelected2[i] = cii.getIdDetection().getId().toString();
			i++;
		}
		detectionSelected = detecSelected2;
		
		// récupération des domaines de l'incident
		List<Com_Incident_Domaine> lcido = ComIncidentDomaineDatabaseService.getDomaine(selectedID);
		i=0;
		
		String[] domaineSelected2 = new String [lcido.size()];
		for(Com_Incident_Domaine cido : lcido){
			domaineSelected2[i] = cido.getDomaine().getId().toString();
			i++;
		}
		domaineSelected = domaineSelected2;
		
		// récupération des applicatifs de l'incident
		List<Com_Incident_Appli> lcia = ComIncidentAppliDatabaseService.getAppli(selectedID);
		i=0;
		String[] appliSelected2 = new String [lcia.size()];
		for(Com_Incident_Appli cia : lcia){
			appliSelected2[i] = cia.getIdAppli().getId().toString();
			i++;
		}
		appliSelected = appliSelected2;
		
		return OK;
	}

}
