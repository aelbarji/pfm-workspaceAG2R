package pilotage.gup.debordement_noc;

import pilotage.database.gup.DebordementNOCDatabaseService;
import pilotage.framework.AbstractAction;

public class DeleteDebordementNOCAction extends AbstractAction{

	private static final long serialVersionUID = 8309491975030412385L;
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
			DebordementNOCDatabaseService.delete(selectedID);
			return OK;
		} catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Suppression débordement NOC - ", e);
			return ERROR;	
		}
		
	}

}
