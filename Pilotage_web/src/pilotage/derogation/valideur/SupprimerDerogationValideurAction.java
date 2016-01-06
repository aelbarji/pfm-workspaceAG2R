package pilotage.derogation.valideur;

import java.text.MessageFormat;

import pilotage.database.derogation.DerogationValideurDatabaseService;
import pilotage.database.historique.HistoriqueDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;

public class SupprimerDerogationValideurAction extends AbstractAction{


	private static final long serialVersionUID = 7858438170051632209L;
	
	private Integer valideurID;

	public Integer getValideurID() {
		return valideurID;
	}

	public void setValideurID(Integer valideurID) {
		this.valideurID = valideurID;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		try{
			Users user = DerogationValideurDatabaseService.get(valideurID).getValideur();
			DerogationValideurDatabaseService.delete(valideurID);
			info = MessageFormat.format(getText("derogation.valideur.suppression.valide"),  new Object[]{user.getNom() + " " + user.getPrenom()});
			HistoriqueDatabaseService.create(null, MessageFormat.format(getText("historique.derogation.valideur.suppression"), new Object[]{user.getNom() + " " + user.getPrenom(),valideurID}), (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_DEROGATION);

			return OK;
			
		}
		catch(Exception e){
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Suppression d'un valideur - ", e);
			return ERROR;
		}
	}
}
