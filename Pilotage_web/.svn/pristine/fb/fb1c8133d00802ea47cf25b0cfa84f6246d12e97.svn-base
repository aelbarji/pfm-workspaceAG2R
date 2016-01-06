package pilotage.astreintes.actions.appel;

import java.util.List;

import pilotage.database.astreintes.AstreinteAppelDatabaseService;
import pilotage.database.astreintes.AstreinteAppelStatutDatabaseService;
import pilotage.database.astreintes.AstreinteDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Astreinte;
import pilotage.metier.Astreinte_Appel_Statut;
import pilotage.metier.Astreinte_Nombre_Appel_View;
import pilotage.metier.Incidents;
import pilotage.service.constants.PilotageConstants;
import pilotage.utils.Pagination;

public class ShowAppelAstreinteAction extends AbstractAction{

	private static final long serialVersionUID = -5408193151607288404L;
	
	private List<Astreinte_Nombre_Appel_View> listAppelAstreinte;
	private List<Integer> listNbAppel;
	private List<Astreinte> astreintes;
	private List<Astreinte_Appel_Statut> statuts;
	
	private String sort;
	private String sens;
	private int page;
	private int nrPages;
	private int nrPerPage;
	
	private String filtreDateDay;
	private String filtreAstreinte;
	private String filtreIncident;
	private String filtreStatut;
	private String filtreString;
	private String filtreNbAppel;
	
	private Pagination<Astreinte_Nombre_Appel_View> pagination;

	private Integer selectedID;
	private Integer validAst;
	private Integer idInc;
	
	private String provenance;
	private String last_provenance;
	private Integer incFromAstreinte;
	
	protected Integer typeSelected;
	
	protected int pageIncident;
	protected int nrPagesIncident;
	protected int nrPerPageIncident;
	
	protected Pagination<Incidents> paginationIncident;

	public Integer getSelectedID() {
		return selectedID;
	}

	public void setSelectedID(Integer selectedID) {
		this.selectedID = selectedID;
	}

	public List<Astreinte_Nombre_Appel_View> getListAppelAstreinte() {
		return listAppelAstreinte;
	}

	public void setListAppelAstreinte(List<Astreinte_Nombre_Appel_View> listAppelAstreinte) {
		this.listAppelAstreinte = listAppelAstreinte;
	}

	public List<Integer> getListNbAppel() {
		return listNbAppel;
	}

	public void setListNbAppel(List<Integer> listNbAppel) {
		this.listNbAppel = listNbAppel;
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

	public String getFiltreDateDay() {
		return filtreDateDay;
	}

	public void setFiltreDateDay(String filtreDateDay) {
		this.filtreDateDay = filtreDateDay;
	}

	public String getFiltreAstreinte() {
		return filtreAstreinte;
	}

	public void setFiltreAstreinte(String filtreAstreinte) {
		this.filtreAstreinte = filtreAstreinte;
	}

	public String getFiltreIncident() {
		return filtreIncident;
	}

	public void setFiltreIncident(String filtreIncident) {
		this.filtreIncident = filtreIncident;
	}

	public String getFiltreStatut() {
		return filtreStatut;
	}

	public void setFiltreStatut(String filtreStatut) {
		this.filtreStatut = filtreStatut;
	}

	public String getFiltreString() {
		return filtreString;
	}

	public void setFiltreString(String filtreString) {
		this.filtreString = filtreString;
	}

	public String getFiltreNbAppel() {
		return filtreNbAppel;
	}

	public void setFiltreNbAppel(String filtreNbAppel) {
		this.filtreNbAppel = filtreNbAppel;
	}

	public Pagination<Astreinte_Nombre_Appel_View> getPagination() {
		return pagination;
	}

	public void setPagination(Pagination<Astreinte_Nombre_Appel_View> pagination) {
		this.pagination = pagination;
	}

	public Integer getValidAst() {
		return validAst;
	}

	public void setValidAst(Integer validAst) {
		this.validAst = validAst;
	}

	public Integer getIdInc() {
		return idInc;
	}

	public void setIdInc(Integer idInc) {
		this.idInc = idInc;
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
			if(page < 1)
				page = 1;
			else if(nrPages != 0 && page > nrPages)
				page = nrPages;
			if(nrPerPage == 0)
				nrPerPage = PilotageConstants.NB_ASTREINTES_PER_PAGE;
			pagination = new Pagination<Astreinte_Nombre_Appel_View>(page, nrPerPage);
			astreintes = AstreinteDatabaseService.getAll();
			statuts = AstreinteAppelStatutDatabaseService.getAll();
			if(validAst != null && validAst == 2 && idInc != null) // si la redirection concerne "voir astreintes"
				selectedID = idInc;
			else if(idInc == null && selectedID != null){
				idInc = selectedID;
			}
			if(validAst != null && validAst == 2 && selectedID != null){
				listAppelAstreinte = AstreinteAppelDatabaseService.getAllView(pagination, selectedID.toString(), filtreNbAppel, filtreDateDay, filtreAstreinte, filtreStatut);
			}else
				listAppelAstreinte = AstreinteAppelDatabaseService.getAllView(pagination, filtreIncident, filtreNbAppel, filtreDateDay, filtreAstreinte, filtreStatut);			

			return OK;
		}catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Affichage de la liste des appels astreinte", e);
			return ERROR;
		}
	}

}
