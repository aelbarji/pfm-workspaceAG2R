package pilotage.astreintes.actions.appel;

import java.text.MessageFormat;

import java.util.Date;

import pilotage.database.astreintes.AstreinteAppelDatabaseService;
import pilotage.database.incidents.IncidentsDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Astreinte_Nombre_Appel_View;
import pilotage.metier.Incidents;
import pilotage.service.date.DateService;
import pilotage.utils.Pagination;

public class CreateAppelAstreinteAction extends AbstractAction{

	private static final long serialVersionUID = 4371084435199449562L;
	private String date;
	private String heure;
	private Integer astreinte;
	private String incident;
	private Integer statut;
	private String commentaire;
	private Integer validAst;
	private Integer idInc;
	private String provenance;
	private String last_provenance;
	
	protected Integer typeSelected;
	
	protected int page;
	protected int nrPages;
	protected int nrPerPage;
	
	protected Pagination<Astreinte_Nombre_Appel_View> pagination;
	
	protected int pageIncident;
	protected int nrPagesIncident;
	protected int nrPerPageIncident;
	
	protected Pagination<Incidents> paginationIncident;
	
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

	public Integer getAstreinte() {
		return astreinte;
	}

	public void setAstreinte(Integer astreinte) {
		this.astreinte = astreinte;
	}

	public String getIncident() {
		return incident;
	}

	public void setIncident(String incident) {
		this.incident = incident;
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
			Date dateD = DateService.strToDate(date);
			Date dateH = DateService.getTimeFromString(heure);
			Date dateAppel = DateService.addTime(dateD, dateH);
			AstreinteAppelDatabaseService.create(dateAppel, astreinte, Integer.parseInt(incident), statut, commentaire);
			info = MessageFormat.format(getText("astreinte.appel.creation.valide"),date, heure);
			idInc = Integer.parseInt(incident);
			IncidentsDatabaseService.updateAstreinte(idInc, 1);
			
			if(last_provenance != null && !last_provenance.equals("")) return last_provenance;
			if(provenance != null && !provenance.equals("")) return provenance;
			else return OK;
		}catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Sauvegarde d'un appel astreinte - ", e);
			return ERROR;
		}
	}

}
