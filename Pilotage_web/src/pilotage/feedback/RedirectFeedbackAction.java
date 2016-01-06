package pilotage.feedback;

import pilotage.database.feedback.FeedbackDatabaseService;
import pilotage.framework.AbstractAction;

public class RedirectFeedbackAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	
	private Integer filtreFeedbacks;	

	private Integer selectRow;
	
	private String bug;
	
	private int page;
	private int nrPages;
	private int nrPerPage;
	
	public Integer getFiltreFeedbacks() {
		return filtreFeedbacks;
	}

	public void setFiltreFeedbacks(Integer filtreFeedbacks) {
		this.filtreFeedbacks = filtreFeedbacks;
	}
	
	public Integer getSelectRow() {
		return selectRow;
	}

	public void setSelectRow(Integer selectRow) {
		this.selectRow = selectRow;
	}

	public String getBug() {
		return bug;
	}

	public void setBug(String bug) {
		this.bug = bug;
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
		if(selectRow != null){
			bug = FeedbackDatabaseService.get(selectRow).getBug();
		}
		return OK;
	}

}
