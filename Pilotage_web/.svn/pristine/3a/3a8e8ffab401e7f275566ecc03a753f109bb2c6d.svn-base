package pilotage.gup.admin.destinataire.bilan;

import pilotage.database.gup.ComBilanDestinataireDatabaseService;
import pilotage.framework.AbstractAction;

public class DeleteDestinataireBilanAction extends AbstractAction {
	
	private static final long serialVersionUID = -7470648582618210602L;
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
			ComBilanDestinataireDatabaseService.delete(selectedID);
			return OK;
		} catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Suppression destinataire bilan - ", e);
			return ERROR;
		}

	}


}
