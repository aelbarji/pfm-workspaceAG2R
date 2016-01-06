package pilotage.astreintes.actions.planning;

import java.text.MessageFormat;

import pilotage.database.astreintes.AstreintePlanningDatabaseService;
import pilotage.database.historique.HistoriqueDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;

public class SupprimerAstreintePlanningAction extends AbstractAction{

	private static final long serialVersionUID = -2590524858236151166L;

	private Integer selectRow;
	
	private String sort;
	private String sens;
	private int page;
	private int nrPages;
	private int nrPerPage;
	
	private Integer dateNB;

	public Integer getSelectRow() {
		return selectRow;
	}

	public void setSelectRow(Integer selectRow) {
		this.selectRow = selectRow;
	}

	public Integer getDateNB() {
		return dateNB;
	}

	public void setDateNB(Integer dateNB) {
		this.dateNB = dateNB;
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
		
		try {
			AstreintePlanningDatabaseService.delete(selectRow);
			HistoriqueDatabaseService.create(null, MessageFormat.format(getText("historique.astreinte.planning.suppression"),new Object[]{selectRow}), (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_ASTREINTES);

			info = getText("astreinte.planning.suppression.valide");
			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Suppression d'un planning d'astreinte - ", e);
			return ERROR;
		}
	}
}
