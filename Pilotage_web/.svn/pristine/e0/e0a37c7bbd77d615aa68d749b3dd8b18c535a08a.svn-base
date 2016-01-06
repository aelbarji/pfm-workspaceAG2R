package pilotage.bilan.disques;

import java.text.MessageFormat;

import pilotage.database.bilan.DisqueDatabaseService;
import pilotage.database.historique.HistoriqueDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;

public class DeleteDisquesAction extends AbstractAction {

	private static final long serialVersionUID = 8591920461969220699L;
	private Integer selectedID;
	
	/**
	 * @return the selectedID
	 */
	public Integer getSelectedID() {
		return selectedID;
	}

	/**
	 * @param selectedID the selectedID to set
	 */
	public void setSelectedID(Integer selectedID) {
		this.selectedID = selectedID;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		try{
			String libelle = DisqueDatabaseService.get(selectedID).getLibelle();
			DisqueDatabaseService.delete(selectedID);
			HistoriqueDatabaseService.create(null, MessageFormat.format(getText("historique.bilan.disque.suppression"), new Object[]{libelle, selectedID}), (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_BILAN);

			info = MessageFormat.format(getText("bilan.disques.suppression.valide"), libelle);
			
			return OK;
		}
		catch (Exception e){
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Suppression d'un disque - ", e);
			return ERROR;
		}
	}

}
