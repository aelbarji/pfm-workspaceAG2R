package pilotage.astreintes.actions.appel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import pilotage.database.astreintes.AstreinteAppelStatutDatabaseService;
import pilotage.database.astreintes.AstreinteDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Astreinte;
import pilotage.metier.Astreinte_Appel_Statut;
import pilotage.metier.Astreinte_Nombre_Appel_View;
import pilotage.metier.Incidents;
import pilotage.utils.Pagination;

public class RedirectCreateAppelAstreinteAction extends AbstractAction{

	private static final long serialVersionUID = 1741906866662651848L;

	private Integer astreinte;
	private Integer incident;
	private List<Astreinte> astreintes;
	private List<Astreinte_Appel_Statut> statuts;
	private String date;
	private String heure;
	private Integer selectedID;
	private Integer idInc;
	private Integer validAst;
	private String provenance;
	private String last_provenance;
	private Integer incFromAstreinte;
	
	private Integer incidentID;
	
	protected Integer typeSelected;
	
	protected int page;
	protected int nrPages;
	protected int nrPerPage;
	
	protected Pagination<Astreinte_Nombre_Appel_View> pagination;
	
	protected int pageIncident;
	protected int nrPagesIncident;
	protected int nrPerPageIncident;
	
	protected Pagination<Incidents> paginationIncident;
	
	public Integer getAstreinte() {
		return astreinte;
	}

	public void setAstreinte(Integer astreinte) {
		this.astreinte = astreinte;
	}

	public Integer getIncident() {
		return incident;
	}

	public void setIncident(Integer incident) {
		this.incident = incident;
	}

	public List<Astreinte> getAstreintes() {
		return astreintes;
	}

	public void setAstreintes(List<Astreinte> astreintes) {
		this.astreintes = astreintes;
	}

	public List<Astreinte_Appel_Statut> getStatuts() {
		return statuts;
	}

	public void setStatuts(List<Astreinte_Appel_Statut> statuts) {
		this.statuts = statuts;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getHeure() {
		return heure;
	}

	public void setHeure(String heure) {
		this.heure = heure;
	}

	public Integer getIdInc() {
		return idInc;
	}

	public void setIdInc(Integer idInc) {
		this.idInc = idInc;
	}

	public Integer getValidAst() {
		return validAst;
	}

	public void setValidAst(Integer validAst) {
		this.validAst = validAst;
	}

	public String getProvenance() {
		return provenance;
	}

	public void setProvenance(String provenance) {
		this.provenance = provenance;
	}

	public String getLast_provenance() {
		return last_provenance;
	}

	public void setLast_provenance(String last_provenance) {
		this.last_provenance = last_provenance;
	}

	public Integer getSelectedID() {
		return selectedID;
	}

	public void setSelectedID(Integer selectedID) {
		this.selectedID = selectedID;
	}

	public Integer getIncFromAstreinte() {
		return incFromAstreinte;
	}

	public void setIncFromAstreinte(Integer incFromAstreinte) {
		this.incFromAstreinte = incFromAstreinte;
	}

	public Integer getTypeSelected() {
		return typeSelected;
	}

	public void setTypeSelected(Integer typeSelected) {
		this.typeSelected = typeSelected;
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

	public Pagination<Astreinte_Nombre_Appel_View> getPagination() {
		return pagination;
	}

	public void setPagination(Pagination<Astreinte_Nombre_Appel_View> pagination) {
		this.pagination = pagination;
	}

	public int getPageIncident() {
		return pageIncident;
	}

	public void setPageIncident(int pageIncident) {
		this.pageIncident = pageIncident;
	}

	public int getNrPagesIncident() {
		return nrPagesIncident;
	}

	public void setNrPagesIncident(int nrPagesIncident) {
		this.nrPagesIncident = nrPagesIncident;
	}

	public int getNrPerPageIncident() {
		return nrPerPageIncident;
	}

	public void setNrPerPageIncident(int nrPerPageIncident) {
		this.nrPerPageIncident = nrPerPageIncident;
	}

	public Pagination<Incidents> getPaginationIncident() {
		return paginationIncident;
	}

	public void setPaginationIncident(Pagination<Incidents> paginationIncident) {
		this.paginationIncident = paginationIncident;
	}

	public Integer getIncidentID() {
		return incidentID;
	}

	public void setIncidentID(Integer incidentID) {
		this.incidentID = incidentID;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		try{
			DateFormat dform = new SimpleDateFormat("dd/MM/yyyy");
			DateFormat hform = new SimpleDateFormat("HH:mm");
			Date dateJour = new Date();
			date = dform.format(dateJour);
			heure = hform.format(dateJour);
			astreintes = AstreinteDatabaseService.getAll();
			statuts = AstreinteAppelStatutDatabaseService.getAll();		
			
			idInc = incidentID;
			if(incident != null && idInc == null && provenance != null && !provenance.equals(""))
				idInc = incident;
			if(selectedID != null && idInc == null)
				idInc = selectedID;
			if (provenance == null || provenance.equals("")){
				if (last_provenance == null || last_provenance.equals(""))
					idInc = null;
			}
			if(incFromAstreinte != null && incFromAstreinte == 1)
				idInc = null;
			return OK;
		}catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Affichage de la création d'un appel astreinte", e);
			return ERROR;
		}
	}

}
