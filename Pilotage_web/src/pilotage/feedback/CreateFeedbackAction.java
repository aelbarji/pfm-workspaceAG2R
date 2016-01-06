package pilotage.feedback;

import java.util.Date;
import java.util.List;

import pilotage.database.feedback.FeedbackDatabaseService;
import pilotage.database.historique.HistoriqueDatabaseService;
import pilotage.database.users.management.UsersDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;
import pilotage.service.date.DateService;
import pilotage.service.mail.MailService;

public class CreateFeedbackAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	
	private String bug;
	
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

	public String getBug() {
		return bug;
	}

	public void setBug(String bug) {
		this.bug = bug;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		try{
            Users createur = (Users)session.get(PilotageConstants.USER_LOGGED);

			Date now = new Date();
			
			FeedbackDatabaseService.create(now ,bug ,createur);

			HistoriqueDatabaseService.create(null, "ajout de feedback " + FeedbackDatabaseService.getId(bug), (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_FEEDBACK);
 
			String[] content = new String[4];
			content[0] = DateService.dateToStr(now, DateService.p1)+" "+DateService.getTime(now, null);
			content[1] = createur.getNom() +" "+ createur.getPrenom();
			content[2] = bug.replaceAll("\n", "<br/>");

			List<Users> adminList = UsersDatabaseService.getAdminList();
			boolean allMailsSent = true;
			for(int i = 0; i < adminList.size(); ++i){
				Users u = adminList.get(i);
				if(!MailService.sendEmail(u.getEmail(), PilotageConstants.MAILSERVER_CREATE_FEEDBACK_SUBJECT, PilotageConstants.MAILSERVER_CREATE_FEEDBACK_TEXTE, content,null))
					allMailsSent = false;
			}
			if(!allMailsSent)
				error = getText("feedback.sendEmail.failed");

			info = getText("feedback.creation.valide");
			return OK;
		}
		catch(Exception e){
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Création d'un feedback - ", e);
			return ERROR;
		}

	}

}
