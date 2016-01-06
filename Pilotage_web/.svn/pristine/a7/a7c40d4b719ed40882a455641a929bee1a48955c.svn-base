package pilotage.astreinte.destinataires;

import java.text.MessageFormat;

import pilotage.database.astreintes.AstreinteDestinatairesDatabaseService;
import pilotage.database.historique.HistoriqueDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Astreinte_Destinataires;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;

public class DeleteDestinataireAction extends AbstractAction {

	private static final long serialVersionUID = -5430474094126546400L;
	private Integer selectedID;
	
	/**
	 * @return the selectedID
	 */
	public int getSelectedID() {
		return selectedID;
	}

	/**
	 * @param selectedID the selectedID to set
	 */
	public void setSelectedID(int selectedID) {
		this.selectedID = selectedID;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		
		try {
			Astreinte_Destinataires destinataire = AstreinteDestinatairesDatabaseService.get(selectedID);
			String nomPrenom = destinataire.getNom() + " " + destinataire.getPrenom();
			
			AstreinteDestinatairesDatabaseService.delete(selectedID);
			HistoriqueDatabaseService.create(null, MessageFormat.format(getText("historique.astreinte.destinataire.suppression"), new Object[]{nomPrenom, selectedID}), (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_ASTREINTES);
			
			info = MessageFormat.format(getText("astreinte.destinataires.suppression.valide"), nomPrenom);
			
			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Suppression d'un destinataire l'envoi des astreintes - ", e);
			return ERROR;
		}
		
	}

}
