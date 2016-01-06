package pilotage.bilan.alertes.types;

import java.text.MessageFormat;

import pilotage.database.bilan.AlertesTypeDatabaseService;
import pilotage.database.historique.HistoriqueDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;

public class DeleteTypeAction extends AbstractAction {

	private static final long serialVersionUID = 7962965606925000630L;
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
			String type = AlertesTypeDatabaseService.get(selectedID).getType();
			AlertesTypeDatabaseService.delete(selectedID);
			HistoriqueDatabaseService.create(null, MessageFormat.format(getText("historique.bilan.alerte.type.suppression"), new Object[]{type, selectedID}), (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_BILAN);

			info = MessageFormat.format(getText("alertes.type.suppression.valide"), type);
			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Suppression d'un type d'alerte disque - ", e);
			return ERROR;
		}
	}
}
