package pilotage.incidents.qualite;

import java.text.MessageFormat;
import java.util.Date;

import pilotage.database.historique.HistoriqueDatabaseService;
import pilotage.database.incidents.IncidentsQualiteActionDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;
import pilotage.service.date.DateService;

public class CreateActionIncidentsQualiteAction extends AbstractAction{

	private static final long serialVersionUID = 1099373928466720921L;
	private String sort;
	private String sens;
	private int page;
	private int nrPages;
	private int nrPerPage;
	
	private String dateAction;
	private String action;
	private Integer ficheId;
	private Integer selectRow;
	
	private String sortFiche;
	private String sensFiche;
	private int pageFiche;
	private int nrPagesFiche;
	private int nrPerPageFiche;
	
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

	public Integer getSelectRow() {
		return selectRow;
	}

	public void setSelectRow(Integer selectRow) {
		this.selectRow = selectRow;
	}
	
	public Integer getFicheId() {
		return ficheId;
	}

	public void setFicheId(Integer ficheId) {
		this.ficheId = ficheId;
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

	public String getDateAction() {
		return dateAction;
	}

	public void setDateAction(String dateAction) {
		this.dateAction = dateAction;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		try{				
			Date dateA = DateService.strToDate(dateAction);
			IncidentsQualiteActionDatabaseService.create(ficheId, dateA, action);
			
			HistoriqueDatabaseService.create(null, MessageFormat.format(getText("historique.incidents.qualite.action.creation"), new Object[]{IncidentsQualiteActionDatabaseService.getId(dateA, action),ficheId}), (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_INCIDENTS);
			info = getText("incidents.qualite.action.creation.valide");

			return OK;
		}
		catch(Exception e){
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Creation d'une action incident qualite", e);
			return ERROR;
		}
	}
}
