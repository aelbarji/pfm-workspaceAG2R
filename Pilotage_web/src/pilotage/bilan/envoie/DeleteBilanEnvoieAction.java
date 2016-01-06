package pilotage.bilan.envoie;

import java.text.MessageFormat;

import pilotage.database.bilan.BilanEnvoieDatabaseService;
import pilotage.database.historique.HistoriqueDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;

public class DeleteBilanEnvoieAction extends AbstractAction {

	private static final long serialVersionUID = -2729941328578110359L;
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
			String nom = BilanEnvoieDatabaseService.get(selectedID).getNom();
			BilanEnvoieDatabaseService.delete(selectedID);
			HistoriqueDatabaseService.create(null, MessageFormat.format(getText("historique.bilan.type.suppression"), new Object[]{nom, selectedID}) , (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_BILAN);

			info = MessageFormat.format(getText("bilan.envoie.suppression.valide"), nom);
			return OK;
		}
		catch(Exception e){
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Suppression d'un type de bilan - ", e);
			return ERROR;
		}
	}

}
