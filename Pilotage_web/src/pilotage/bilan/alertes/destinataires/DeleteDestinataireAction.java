package pilotage.bilan.alertes.destinataires;

import java.text.MessageFormat;

import pilotage.database.bilan.AlertesDisquesDestinatairesDatabaseService;
import pilotage.database.historique.HistoriqueDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Alertes_Disques_Destinataires;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;

public class DeleteDestinataireAction extends AbstractAction {

	private static final long serialVersionUID = 9132002187466073883L;
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
		Alertes_Disques_Destinataires destinataire = AlertesDisquesDestinatairesDatabaseService.get(selectedID);
		
		try {
			AlertesDisquesDestinatairesDatabaseService.delete(selectedID);
			HistoriqueDatabaseService.create(null, MessageFormat.format(getText("historique.bilan.alerte.destinataire.suppression"), new Object[]{destinataire.getNom() + " " + destinataire.getPrenom(), selectedID}) , (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_BILAN);

			info = MessageFormat.format(getText("alertes.destinataires.suppression.valide"), destinataire.getNom() + " " + destinataire.getPrenom());
			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Suppression d'un destinataire d'alertes disques - ", e);
			return ERROR;
		}
	}

}
