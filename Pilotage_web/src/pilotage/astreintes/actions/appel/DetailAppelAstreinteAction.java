package pilotage.astreintes.actions.appel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import pilotage.database.astreintes.AstreinteAppelDatabaseService;
import pilotage.database.incidents.IncidentsDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Astreinte_Appel;
import pilotage.metier.Astreinte_Nombre_Appel_View;
import pilotage.metier.Incidents;
import pilotage.utils.Pagination;

public class DetailAppelAstreinteAction extends AbstractAction{

	private static final long serialVersionUID = -2710331233393032990L;

	private Integer astreinte;
	private Integer incident;
	private String date;
	private Integer selectRow;
	private Integer selectedID;
	private Integer validAst;
	private List<Astreinte_Appel> listAppel;
	private String last_provenance;
	private String provenance;
	
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Integer getSelectRow() {
		return selectRow;
	}

	public void setSelectRow(Integer selectRow) {
		this.selectRow = selectRow;
	}

	public List<Astreinte_Appel> getListAppel() {
		return listAppel;
	}

	public void setListAppel(List<Astreinte_Appel> listAppel) {
		this.listAppel = listAppel;
	}

	public Integer getSelectedID() {
		return selectedID;
	}

	public void setSelectedID(Integer selectedID) {
		this.selectedID = selectedID;
	}

	public Integer getValidAst() {
		return validAst;
	}

	public void setValidAst(Integer validAst) {
		this.validAst = validAst;
	}

	public String getLast_provenance() {
		return last_provenance;
	}

	public void setLast_provenance(String last_provenance) {
		this.last_provenance = last_provenance;
	}

	public String getProvenance() {
		return provenance;
	}

	public void setProvenance(String provenance) {
		this.provenance = provenance;
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

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		try{
			listAppel = new ArrayList<Astreinte_Appel>();
			SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
			listAppel = AstreinteAppelDatabaseService.getByAstreinteIncident(astreinte, incident);
			selectedID = incident;
			if(listAppel.size()>0)
				date = formatDate.format(listAppel.get(listAppel.size()-1).getDate());
			if(listAppel.isEmpty())
				IncidentsDatabaseService.updateAstreinte(incident, 0);
			return OK;
		}catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Affichage du détail d'un appel astreinte", e);
			return ERROR;
		}
	}

}
