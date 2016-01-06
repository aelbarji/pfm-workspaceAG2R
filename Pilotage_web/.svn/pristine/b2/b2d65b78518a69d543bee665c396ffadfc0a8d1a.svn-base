package pilotage.astreintes.actions.appel;

import java.util.List;

import pilotage.database.astreintes.AstreinteAppelDatabaseService;
import pilotage.database.astreintes.AstreinteAppelStatutDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Astreinte_Appel;
import pilotage.metier.Astreinte_Appel_Statut;
import pilotage.metier.Astreinte_Nombre_Appel_View;
import pilotage.metier.Incidents;
import pilotage.service.date.DateService;
import pilotage.utils.Pagination;

public class RedirectModifyAppelAstreinteAction extends AbstractAction{

	private static final long serialVersionUID = 4261258354698622445L;

	private Integer selectRow;
	private String nomAstreinte;
	private String date;
	private Integer incident;
	private String heure;
	private Integer statut;
	private String commentaire;
	private List<Astreinte_Appel_Statut> statuts;
	private Integer astreinte;
	private Integer validAst; 
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
	
	public Integer getSelectRow() {
		return selectRow;
	}

	public void setSelectRow(Integer selectRow) {
		this.selectRow = selectRow;
	}

	public String getNomAstreinte() {
		return nomAstreinte;
	}

	public void setNomAstreinte(String nomAstreinte) {
		this.nomAstreinte = nomAstreinte;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Integer getIncident() {
		return incident;
	}

	public void setIncident(Integer incident) {
		this.incident = incident;
	}

	public String getHeure() {
		return heure;
	}

	public void setHeure(String heure) {
		this.heure = heure;
	}

	public Integer getStatut() {
		return statut;
	}

	public void setStatut(Integer statut) {
		this.statut = statut;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public List<Astreinte_Appel_Statut> getStatuts() {
		return statuts;
	}

	public void setStatuts(List<Astreinte_Appel_Statut> statuts) {
		this.statuts = statuts;
	}

	public Integer getAstreinte() {
		return astreinte;
	}

	public void setAstreinte(Integer astreinte) {
		this.astreinte = astreinte;
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
			Astreinte_Appel astr = AstreinteAppelDatabaseService.get(selectRow);
			nomAstreinte = astr.getAstreinte().getNomPrenom();
			incident = astr.getIncident().getId();
			date = DateService.dateToStr(astr.getDate(), DateService.p1);
			heure =  DateService.getTime(astr.getDate(), null);
			statut = astr.getStatut().getId();
			commentaire = astr.getCommentaire();
			statuts = AstreinteAppelStatutDatabaseService.getAll();
			return OK;
		}catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Redirection modification d'un appel astreinte", e);
			return ERROR;
		}
	}

}
