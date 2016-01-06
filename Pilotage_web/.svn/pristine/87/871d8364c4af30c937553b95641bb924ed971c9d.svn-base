package pilotage.bilan.flux.cft;

import java.text.MessageFormat;

import pilotage.database.bilan.FluxCFTDatabaseService;
import pilotage.database.historique.HistoriqueDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Flux_CFT;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;

public class DeleteFluxCFTAction extends AbstractAction {

	private static final long serialVersionUID = -3868625109489466522L;
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
			Flux_CFT cft = FluxCFTDatabaseService.get(selectedID);
			FluxCFTDatabaseService.delete(selectedID);
			HistoriqueDatabaseService.create(null, MessageFormat.format(getText("historique.bilan.flux.suppression"), new Object[]{cft.getLibelle(), selectedID}), (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_BILAN);

			info = MessageFormat.format(getText("flux.CFT.suppression.valide"), cft.getLibelle());
			return OK;
		}
		catch (Exception e){
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Suppression d'un flux - ", e);
			return ERROR;
		}
	}

}
