package pilotage.gup.admin.debordementnoc;

import pilotage.database.gup.DebordementNocDestinataireDatabaseService;
import pilotage.framework.AbstractAction;

public class DeleteDestinataireNocAction extends AbstractAction {

	private static final long serialVersionUID = -7241571600530577405L;
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
			DebordementNocDestinataireDatabaseService.delete(selectedID);
			return OK;
		} catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Suppression destinataire débordement NOC - ", e);
			return ERROR;
		}
		
	}

}
