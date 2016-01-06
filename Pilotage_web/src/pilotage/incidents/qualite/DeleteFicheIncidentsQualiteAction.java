package pilotage.incidents.qualite;

import java.text.MessageFormat;

import pilotage.database.historique.HistoriqueDatabaseService;
import pilotage.database.incidents.IncidentsQualiteDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;

public class DeleteFicheIncidentsQualiteAction extends AbstractAction {

	private static final long serialVersionUID = 5890689032144385865L;

	private Integer selectRow;
	private String sort;
	private String sens;
	private int page;
	private int nrPages;
	private int nrPerPage;
	
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
	
	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		
		try{
			IncidentsQualiteDatabaseService.delete(selectRow);
			HistoriqueDatabaseService.create(null,MessageFormat.format(getText("historique.incidents.qualite.suppression"),new Object[]{selectRow}), (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_INCIDENTS);

			info = getText("incidents.qualite.suppression.valide");
			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Suppression d'une fiche incident qualite - ", e);
			return ERROR;
		}
	}
	
}
