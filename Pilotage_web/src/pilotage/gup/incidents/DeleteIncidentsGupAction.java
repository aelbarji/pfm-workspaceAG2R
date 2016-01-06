package pilotage.gup.incidents;

import java.util.List;

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

public class DeleteIncidentsGupAction extends AbstractAction{

	private static final long serialVersionUID = 4079976217916873672L;
	private Integer selectedID;
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

	public String getProvenance() {
		return provenance;
	}

	public void setProvenance(String provenance) {
		this.provenance = provenance;
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

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		try {
			List<Com_Incident_Appli> lista = ComIncidentAppliDatabaseService.getAll();
			for(Com_Incident_Appli appli : lista){
				if(appli.getIdIncident().getId().equals(selectedID)){
					ComIncidentAppliDatabaseService.delete(appli.getId());
				}
			}
			
			List<Com_Incident_Detection> listd = ComIncidentDetectionDatabaseService.getAll();
			for(Com_Incident_Detection d : listd){
				if(d.getIdIncident().getId().equals(selectedID)){
					ComIncidentDetectionDatabaseService.delete(d.getId());
				}
			}
			
			List<Com_Incident_Domaine> listdo = ComIncidentDomaineDatabaseService.getAll();
			for(Com_Incident_Domaine d : listdo){
				if(d.getIdIncident().getId().equals(selectedID)){
					ComIncidentDomaineDatabaseService.delete(d.getId());
				}
			}
			
			List<Com_Incident_Interlocuteur> listi = ComIncidentInterlocuteurDatabaseService.getAll();
			for(Com_Incident_Interlocuteur i : listi){
				if(i.getIdIncident().getId().equals(selectedID)){
					ComIncidentInterlocuteurDatabaseService.delete(i.getId());
				}
			}
			
			List<Com_Incident_Service> lists = ComIncidentServiceDatabaseService.getAll();
			for(Com_Incident_Service s : lists){
				if(s.getIdIncident().getId().equals(selectedID)){
					ComIncidentServiceDatabaseService.delete(s.getId());
				}
			}
			IncidentsGupDatabaseService.delete(selectedID);
			info = "L'incident " + selectedID + " a bien été supprimé.";
			return provenance;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

}
