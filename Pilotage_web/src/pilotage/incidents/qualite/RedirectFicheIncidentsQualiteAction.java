package pilotage.incidents.qualite;

import java.util.List;

import pilotage.database.incidents.IncidentsQualiteDatabaseService;
import pilotage.database.incidents.IncidentsQualiteStatutDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Incidents_Qualite;
import pilotage.metier.Incidents_Qualite_Statut;
import pilotage.service.date.DateService;

public class RedirectFicheIncidentsQualiteAction extends AbstractAction {

	private static final long serialVersionUID = 8308641200516784377L;
	private String sort;
	private String sens;
	private int page;
	private int nrPages;
	private int nrPerPage;
	private Integer selectRow;
	private int provenance;
	
	private String sortFiche;
	private String sensFiche;
	private int pageFiche;
	private int nrPagesFiche;
	private int nrPerPageFiche;
	
	private String dateEvenement;
	private String description;
	private String cause_raison;
	private String incidence;
	private String echeance;
	private Integer statut;

	private List<Incidents_Qualite_Statut> statutList;
	private String dateResolution;
	
	private Incidents_Qualite incidentQualite;
	
	public String getSortFiche() {
		return sortFiche;
	}

	public void setSortFiche(String sortFiche) {
		this.sortFiche = sortFiche;
	}

	public String getSensFiche() {
		return sensFiche;
	}

	public void setSensFiche(String sensFiche) {
		this.sensFiche = sensFiche;
	}

	public int getPageFiche() {
		return pageFiche;
	}

	public void setPageFiche(int pageFiche) {
		this.pageFiche = pageFiche;
	}

	public int getNrPagesFiche() {
		return nrPagesFiche;
	}

	public void setNrPagesFiche(int nrPagesFiche) {
		this.nrPagesFiche = nrPagesFiche;
	}

	public int getNrPerPageFiche() {
		return nrPerPageFiche;
	}

	public void setNrPerPageFiche(int nrPerPageFiche) {
		this.nrPerPageFiche = nrPerPageFiche;
	}

	public int getProvenance() {
		return provenance;
	}

	public void setProvenance(int provenance) {
		this.provenance = provenance;
	}

	public Incidents_Qualite getIncidentQualite() {
		return incidentQualite;
	}

	public void setIncidentQualite(Incidents_Qualite incidentQualite) {
		this.incidentQualite = incidentQualite;
	}

	public Integer getSelectRow() {
		return selectRow;
	}

	public void setSelectRow(Integer selectRow) {
		this.selectRow = selectRow;
	}
	
	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getSens() {
		return sens;
	}

	public void setSens(String sens) {
		this.sens = sens;
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

	public String getDateEvenement() {
		return dateEvenement;
	}

	public void setDateEvenement(String dateEvenement) {
		this.dateEvenement = dateEvenement;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCause_raison() {
		return cause_raison;
	}

	public void setCause_raison(String cause_raison) {
		this.cause_raison = cause_raison;
	}

	public String getIncidence() {
		return incidence;
	}

	public void setIncidence(String incidence) {
		this.incidence = incidence;
	}

	public String getEcheance() {
		return echeance;
	}

	public void setEcheance(String echeance) {
		this.echeance = echeance;
	}
	
	public Integer getStatut() {
		return statut;
	}

	public void setStatut(Integer statut) {
		this.statut = statut;
	}

	public List<Incidents_Qualite_Statut> getStatutList() {
		return statutList;
	}

	public void setStatutList(List<Incidents_Qualite_Statut> statutList) {
		this.statutList = statutList;
	}

	public String getDateResolution() {
		return dateResolution;
	}

	public void setDateResolution(String dateResolution) {
		this.dateResolution = dateResolution;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		
		statutList = IncidentsQualiteStatutDatabaseService.getAll();
		
		if(selectRow != null){
			incidentQualite = IncidentsQualiteDatabaseService.get(selectRow);
			dateEvenement = DateService.dateToStr(incidentQualite.getDateEvenement(), DateService.p1);
			description = incidentQualite.getDescription();
			cause_raison = incidentQualite.getCause_raison();
			incidence = incidentQualite.getIncidence();
			echeance = incidentQualite.getEcheance();
			statut = incidentQualite.getStatut().getId();
			if (incidentQualite.getDateResolution() != null){
				dateResolution = DateService.dateToStr(incidentQualite.getDateResolution(), DateService.p1);
			}
		}
		return OK;
	}
}
