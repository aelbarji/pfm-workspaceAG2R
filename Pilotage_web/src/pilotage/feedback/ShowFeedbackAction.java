package pilotage.feedback;

import java.util.List;

import pilotage.database.feedback.FeedbackDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Bug;
import pilotage.service.constants.PilotageConstants;
import pilotage.utils.Pagination;

public class ShowFeedbackAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	
	private List<Bug> bugList;
	
	private Integer filtreFeedbacks;	

	private int page;
	private int nrPages;
	private int nrPerPage;
	private Pagination<Bug> pagination;
	
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

	public Pagination<Bug> getPagination() {
		return pagination;
	}

	public void setPagination(Pagination<Bug> pagination) {
		this.pagination = pagination;
	}
	
	public List<Bug> getBugList() {
		return bugList;
	}

	public void setBugList(List<Bug> bugList) {
		this.bugList = bugList;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		if(page < 1)
			page = 1;
		else if(nrPages != 0 && page > nrPages)
			page = nrPages;		
		if(nrPerPage == 0)
			nrPerPage = PilotageConstants.NB_FEEDBACKS_PER_PAGE;
		pagination = new Pagination<Bug>(page, nrPerPage);
		
		if (filtreFeedbacks == null) {
			filtreFeedbacks = 0;
		}
		
		switch (filtreFeedbacks) {
		case 1:
			bugList = FeedbackDatabaseService.getAllByEtat(pagination, PilotageConstants.FEEDBACK_ETAT_EN_ATTENTE);
			break;
		case 2:
			bugList = FeedbackDatabaseService.getAllByEtat(pagination, PilotageConstants.FEEDBACK_ETAT_VALIDE);
			break;
		case 3:
			bugList = FeedbackDatabaseService.getAllByEtat(pagination, PilotageConstants.FEEDBACK_ETAT_ANNULE);
			break;
		default:
			bugList = FeedbackDatabaseService.getAll(pagination);
			break;
		}
		
		return OK;
	}

}
