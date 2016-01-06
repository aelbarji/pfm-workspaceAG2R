package pilotage.bilan.destinataires;

import java.text.MessageFormat;

import pilotage.database.bilan.BilanDestinatairesDatabaseService;
import pilotage.database.historique.HistoriqueDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Bilan_Destinataires;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;

public class DeleteDestinataireAction extends AbstractAction {

	private static final long serialVersionUID = -5430474094126546400L;
	private Integer selectedType;
	private Integer selectedID;
	
	/**
	 * @return the selectedType
	 */
	public Integer getSelectedType() {
		return selectedType;
	}

	/**
	 * @param selectedType the selectedType to set
	 */
	public void setSelectedType(Integer selectedType) {
		this.selectedType = selectedType;
	}

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
			Bilan_Destinataires destinataire = BilanDestinatairesDatabaseService.get(selectedID);
			String nomPrenom = destinataire.getNom() + " " + destinataire.getPrenom();
			
			BilanDestinatairesDatabaseService.delete(selectedID);
			HistoriqueDatabaseService.create(null, MessageFormat.format(getText("historique.bilan.destinataire.suppression"), new Object[]{nomPrenom, selectedID, selectedType}), (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_BILAN);
			
			info = MessageFormat.format(getText("bilan.destinataires.suppression.valide"), nomPrenom);
			
			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Suppression d'un destinataire pour un type de bilan - ", e);
			return ERROR;
		}
		
	}

}
