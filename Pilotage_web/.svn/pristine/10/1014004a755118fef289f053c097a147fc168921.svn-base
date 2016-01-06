package pilotage.gup.incidents;

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

public class DetailIncidentGupAction extends AbstractAction{

	private static final long serialVersionUID = 9215728327814872966L;
	
	private Integer selectedID;
	
	private String nomDetections;
	private String nomServices;
	private String nomApplis;
	private String nomDomaines;
	private String nomInterlocuteur;
	private String prob; 
	private String dateDebut;
	private String heureDebut;
	private String dateFin;
	private String heureFin;
	private String nomEtat;
	private String impact;
	
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
	
	private String provenance;
	
	private final String SEPARATEUR = "; ";

	public Integer getSelectedID() {
		return selectedID;
	}

	public void setSelectedID(Integer selectedID) {
		this.selectedID = selectedID;
	}

	public String getNomDetections() {
		return nomDetections;
	}

	public void setNomDetections(String nomDetections) {
		this.nomDetections = nomDetections;
	}

	public String getNomServices() {
		return nomServices;
	}

	public void setNomServices(String nomServices) {
		this.nomServices = nomServices;
	}

	public String getNomApplis() {
		return nomApplis;
	}

	public void setNomApplis(String nomApplis) {
		this.nomApplis = nomApplis;
	}

	public String getNomDomaines() {
		return nomDomaines;
	}

	public void setNomDomaines(String nomDomaines) {
		this.nomDomaines = nomDomaines;
	}

	public String getProb() {
		return prob;
	}

	public void setProb(String prob) {
		this.prob = prob;
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

	public String getDateDebut() {
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

	public String getNomEtat() {
		return nomEtat;
	}

	public void setNomEtat(String nomEtat) {
		this.nomEtat = nomEtat;
	}

	public String getNomInterlocuteur() {
		return nomInterlocuteur;
	}

	public void setNomInterlocuteur(String nomInterlocuteur) {
		this.nomInterlocuteur = nomInterlocuteur;
	}

	public String getImpact() {
		return impact;
	}

	public void setImpact(String impact) {
		this.impact = impact;
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
		Incidents_Gup inc = IncidentsGupDatabaseService.get(selectedID);
		List<Com_Incident_Domaine> cd = ComIncidentDomaineDatabaseService.getDomaine(selectedID);
		nomDomaines = ""; nomDetections = ""; nomServices= ""; nomInterlocuteur = ""; nomApplis = "";
		int i=0;
		for(Com_Incident_Domaine d : cd){
			if(i == 0)
				nomDomaines = d.getDomaine().getNom();
			else nomDomaines += SEPARATEUR + d.getDomaine().getNom();
			i++;
		}
		List<Com_Incident_Interlocuteur> lcii = ComIncidentInterlocuteurDatabaseService.getInterlocuteurs(selectedID);
		i=0;
		for(Com_Incident_Interlocuteur cii : lcii){
			if(i == 0)
				nomInterlocuteur = cii.getIdInterlocuteur().getNom();
			else nomInterlocuteur += SEPARATEUR + cii.getIdInterlocuteur().getNom();
			i++;
		}	
		List<Com_Incident_Service> lcis = ComIncidentServiceDatabaseService.getServices(selectedID);
		i=0;
		for(Com_Incident_Service cis : lcis){
			if(i == 0)
				nomServices = cis.getIdService().getNom();
			else nomServices += SEPARATEUR + cis.getIdService().getNom();
			i++;
		}
		List<Com_Incident_Detection> lcid = ComIncidentDetectionDatabaseService.getDetection(selectedID);
		i=0;
		if(lcid.isEmpty())
			nomDetections = "Non renseigné";
		else{
			for(Com_Incident_Detection cide : lcid)
				if(i == 0)
					nomDetections = cide.getIdDetection().getNom();
				else nomDetections += SEPARATEUR + cide.getIdDetection().getNom();
			i++;
		}
		List<Com_Incident_Appli> lcia = ComIncidentAppliDatabaseService.getAppli(selectedID);
		i=0;
		for(Com_Incident_Appli cia : lcia){
			if(i == 0)
				nomApplis =cia.getIdAppli().getApplicatif() ;
			else nomApplis += SEPARATEUR + cia.getIdAppli().getApplicatif() ;
			i++;
		}
		prob = inc.getDescription_prob();
		if(inc.getEtat() != null)
		nomEtat = inc.getEtat().getLibelle();
		else nomEtat = "Non renseigné";
		if(inc.getImpact().equals(1))
			impact = "Oui";
		else impact = "Non";
		
		// mise de l'heure à un format affichable
				DateTime deb = new DateTime();
				deb = inc.getDate_deb();
				Integer month = deb.getMonthOfYear();
				String monthd = month.toString();
				monthd = (monthd.length() > 1) ? monthd : "0" + monthd;
				
				Integer day = deb.getDayOfMonth();
				String dayd = day.toString();
				dayd = (dayd.length() > 1) ? dayd : "0" + dayd;
				
				dateDebut = dayd + "/" +  monthd + "/" + deb.getYear();
				Integer heure = deb.getHourOfDay();
				String hh = heure.toString();
				hh = (hh.length() > 1) ? hh : "0" + hh;
				Integer minutes = deb.getMinuteOfHour();
				String mm = minutes.toString();
				mm = (mm.length() > 1) ? mm : "0" + mm;
				heureDebut = hh + ":" + mm;
				
				DateTime fin = new DateTime();
				fin = inc.getDate_fin();
				if(fin != null){
					Integer dayf2 = fin.getDayOfMonth();
					String dayf = dayf2.toString();
					dayf = (dayf.length() < 1) ? dayf : "0" + dayf; 
					Integer monthf2 = fin.getMonthOfYear();
					String monthf = monthf2.toString();
					monthf = (monthf.length() > 1) ? monthf : "0" + monthf;
					dateFin = dayf + "/" +  monthf + "/" + fin.getYear();
					Integer heure2 = fin.getHourOfDay();
					String hh2 = heure2.toString();
					hh2 = (hh2.length() > 1) ? hh : "0" + hh;
					Integer minutes2 = fin.getMinuteOfHour();
					String mm2 = minutes2.toString();
					mm2 = (mm2.length() > 1) ? mm2 : "0" + mm2;
					heureFin = hh2 + ":" + mm2;
				}
		
		return OK;
	}

}
