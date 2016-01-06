package pilotage.incidents.qualite;

import java.util.Date;
import java.util.List;

import pilotage.database.incidents.IncidentsQualiteActionDatabaseService;
import pilotage.database.incidents.IncidentsQualiteDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Incidents_Qualite;
import pilotage.metier.Incidents_Qualite_Action;
import pilotage.metier.Incidents_Qualite_Statut;
import pilotage.service.constants.PilotageConstants;
import pilotage.utils.Pagination;

public class ShowDetailFicheIncidentsQualiteAction extends AbstractAction {

	private static final long serialVersionUID = 5669838650056528344L;
	private Integer provenance;
	
	private Date dateEvenement;
	private Date dateResolution;
	private String description;
	private String cause_raison;
	private String incidence;
	private String echeance;
	private List<Incidents_Qualite_Statut>  statutList; 
	
	private Date dateAction;
	private String action;
	private List<Incidents_Qualite_Action> listActionIncidentsQualite;
	
	private Integer selectRow;
	private Integer ficheId;

	private String sortFiche;
	private String sensFiche;
	private int pageFiche;
	private int nrPagesFiche;
	private int nrPerPageFiche;
	
	private String sort;
	private String sens;
	private int page;
	private int nrPages;
	private int nrPerPage;
	private Pagination<Incidents_Qualite_Action> pagination;
	private Incidents_Qualite ficheIncidentsQualite;

	public Integer getProvenance() {
		return provenance;
	}

	public void setProvenance(Integer provenance) {
		this.provenance = provenance;
	}

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

	public Integer getFicheId() {
		return ficheId;
	}

	public void setFicheId(Integer ficheId) {
		this.ficheId = ficheId;
	}
	
	public Date getDateAction() {
		return dateAction;
	}

	public void setDateAction(Date dateAction) {
		this.dateAction = dateAction;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public List<Incidents_Qualite_Action> getListActionIncidentsQualite() {
		return listActionIncidentsQualite;
	}

	public void setListActionIncidentsQualite(
			List<Incidents_Qualite_Action> listActionIncidentsQualite) {
		this.listActionIncidentsQualite = listActionIncidentsQualite;
	}
	
	public Incidents_Qualite getFicheIncidentsQualite() {
		return ficheIncidentsQualite;
	}

	public void setFicheIncidentsQualite(Incidents_Qualite ficheIncidentsQualite) {
		this.ficheIncidentsQualite = ficheIncidentsQualite;
	}

	public Integer getSelectRow() {
		return selectRow;
	}

	public void setSelectRow(Integer selectRow) {
		this.selectRow = selectRow;
	}
	
	public Date getDateEvenement() {
		return dateEvenement;
	}

	public void setDateEvenement(Date dateEvenement) {
		this.dateEvenement = dateEvenement;
	}

	public Date getDateResolution() {
		return dateResolution;
	}

	public void setDateResolution(Date dateResolution) {
		this.dateResolution = dateResolution;
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

	public List<Incidents_Qualite_Statut> getStatutList() {
		return statutList;
	}

	public void setStatutList(List<Incidents_Qualite_Statut> statutList) {
		this.statutList = statutList;
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

	public Pagination<Incidents_Qualite_Action> getPagination() {
		return pagination;
	}

	public void setPagination(Pagination<Incidents_Qualite_Action> pagination) {
		this.pagination = pagination;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	protected String executeMetier() {
		try{
			if(provenance != null && provenance == 0){
				pageFiche = page;
				nrPerPageFiche = nrPerPage;
				nrPagesFiche = nrPages;
				sortFiche = sort;
				sensFiche = sens;
				
				page = 1;
				nrPerPage = 0;
				nrPages = 0;
				sort = null;
				sens = null;
			}
			if(page < 1)
				page = 1;
			else if(nrPages != 0 && page > nrPages)
				page = nrPages;		
			if(nrPerPage == 0)
				nrPerPage = PilotageConstants.NB_ACTION_INCIDENTS_QUALITE_PER_PAGE;
			if(sens == null || "".equals(sens))
				sens = "desc";
			if(sort == null || "".equals(sort))
				sort = "dateAction";
			pagination = new Pagination<Incidents_Qualite_Action>(page, nrPerPage);

			if (ficheId == null && selectRow != null){
				ficheId = selectRow;
			}
			
			ficheIncidentsQualite = IncidentsQualiteDatabaseService.get(ficheId);
			listActionIncidentsQualite = IncidentsQualiteActionDatabaseService.getAllActionIncidentQualite(ficheId, pagination, sort, sens);
			return OK;
			
		} catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Affichage fiche incident qualite et ces actions - ", e);
			return ERROR;	
		}
	}
}
