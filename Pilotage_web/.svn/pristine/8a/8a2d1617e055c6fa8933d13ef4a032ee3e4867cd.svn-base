package pilotage.checklist.current;

import pilotage.database.users.management.UsersDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;

public class SaveNrPerPageAction extends AbstractAction{

	private static final long serialVersionUID = 3952808607867940851L;

	private int nrPerPage;

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
			Users userprofil = (Users)session.get(PilotageConstants.USER_LOGGED);
			Integer userId = userprofil.getId();
			UsersDatabaseService.modifyNrPerPage(userId, nrPerPage);
		    return OK;
		}
		catch(Exception e){
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Modify nrPerPage - ", e);
			return ERROR;
		}

	}
}
