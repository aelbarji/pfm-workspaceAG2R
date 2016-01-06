package pilotage.checklist.admin;

import java.text.MessageFormat;

import pilotage.database.checklist.ChecklistBaseDatabaseService;
import pilotage.database.checklist.ChecklistTachesDatabaseService;
import pilotage.database.historique.HistoriqueDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;

public class DeleteChecklistBaseAction extends AbstractAction {

	private static final long serialVersionUID = -6434176135064678269L;
	
	private Integer selectRow;
	
	private String sort;
	private String sens;
	private int page;
	private int nrPages;
	private int nrPerPage;

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
			String nom = ChecklistBaseDatabaseService.get(selectRow).getTache();
			ChecklistTachesDatabaseService.delete(selectRow);
			
			HistoriqueDatabaseService.create(null, MessageFormat.format(getText("historique.checklist.tache.suppression"), new Object[]{selectRow, nom}), (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_CHECKLIST);

			info = MessageFormat.format(getText("checklist.tache.suppression.valide"), new Object[]{nom});
			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Suppression d'une tache - ", e);
			return ERROR;
		}
	}
}
