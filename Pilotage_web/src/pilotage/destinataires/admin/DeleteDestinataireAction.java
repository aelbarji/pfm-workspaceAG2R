package pilotage.destinataires.admin;

import pilotage.database.destinataires.DestinatairesDatabaseService;
import pilotage.framework.AbstractAction;

public class DeleteDestinataireAction extends AbstractAction{

	private static final long serialVersionUID = 1315087306170070977L;
	private Integer selectedID;

	public Integer getSelectedID() {
		return selectedID;
	}

	public void setSelectedID(Integer selectedID) {
		this.selectedID = selectedID;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		try {
			DestinatairesDatabaseService.delete(selectedID);
			return OK;
		} catch(Exception e){
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Suppression destinataire - ", e);
			return ERROR;
		}
		
	}
}
