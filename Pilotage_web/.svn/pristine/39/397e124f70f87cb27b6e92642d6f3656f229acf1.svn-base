package pilotage.bilan.colonnes;

import pilotage.database.bilan.BilanColonnesDatabaseService;
import pilotage.framework.AbstractAction;

public class DeleteBilanColonnesAction extends AbstractAction {

	private static final long serialVersionUID = 8653300060051851015L;
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
			BilanColonnesDatabaseService.delete(selectedID);
			return OK;
		} catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Suppression bilan colonnes - ", e);
			return ERROR;
		}
		
	}

}
