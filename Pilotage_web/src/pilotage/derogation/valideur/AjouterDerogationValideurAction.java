package pilotage.derogation.valideur;

import java.text.MessageFormat;

import pilotage.database.derogation.DerogationValideurDatabaseService;
import pilotage.database.historique.HistoriqueDatabaseService;
import pilotage.database.users.management.UsersDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;

public class AjouterDerogationValideurAction extends AbstractAction{

	
	private static final long serialVersionUID = -4139311278458919891L;
	
	private Integer userSelected;

	public Integer getUserSelected() {
		return userSelected;
	}

	public void setUserSelected(Integer userSelected) {
		this.userSelected = userSelected;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		try{	
			Users u = UsersDatabaseService.get(userSelected);
			DerogationValideurDatabaseService.create(userSelected);
			
			HistoriqueDatabaseService.create(null, MessageFormat.format(getText("historique.derogation.valideur.ajout"),  new Object[]{u.getNom() + " " + u.getPrenom(),DerogationValideurDatabaseService.getId(userSelected)}), (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_DEROGATION);

			info = MessageFormat.format(getText("derogation.valideur.ajout.valide"),  new Object[]{u.getNom() + " " + u.getPrenom()});
			return OK;
		}
		catch(Exception e){
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Ajout d'un validateur de dérogation", e);
			return ERROR;
		}
		
	}
	

}
