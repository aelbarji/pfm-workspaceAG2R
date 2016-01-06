package pilotage.feedback;

import pilotage.database.feedback.FeedbackDatabaseService;
import pilotage.database.historique.HistoriqueDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;

public class SupprimerFeedbackAction extends AbstractAction {

	private static final long serialVersionUID = 1L;

	private Integer selectRow;

	private Integer filtreFeedbacks;	

	private int page;
	private int nrPages;
	private int nrPerPage;
	
	public Integer getFiltreFeedbacks() {
		return filtreFeedbacks;
	}

	public void setFiltreFeedbacks(Integer filtreFeedbacks) {
		this.filtreFeedbacks = filtreFeedbacks;
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

	public Integer getSelectRow() {
		return selectRow;
	}

	public void setSelectRow(Integer selectRow) {
		this.selectRow = selectRow;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		try {
			FeedbackDatabaseService.delete(selectRow);
			
			HistoriqueDatabaseService.create(null, "suppression du feedback " + selectRow, (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_FEEDBACK);

			info = getText("feedback.suppression.valide");

			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Suppression d'un feedback - ", e);
			return ERROR;
		}
	}

}
