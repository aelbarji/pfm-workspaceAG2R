package pilotage.environnement.actions.type;

import java.text.MessageFormat;

import pilotage.database.environnement.EnvironnementTypeDatabaseService;
import pilotage.database.historique.HistoriqueDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;

public class CreateEnvironnementTypeAction extends AbstractAction{

	private static final long serialVersionUID = 2745773011875273816L;
	
	private String libelle;

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	@Override
	protected boolean validateMetier() {
		try {
			//vérifie que cet environnement type existe ou non
			if (EnvironnementTypeDatabaseService.exists(null, libelle)) {
				error = MessageFormat.format(getText("environnement.type.creation.existe.deja"), libelle);
				return false;
			}
		} 
		catch (Exception e) {
			error = getText("error.message.generique") + ":" + e.getMessage();
			erreurLogger.error("Creation d'un type d'environnement",e);
			return false;
		}
		return true;
	}

	@Override
	protected String executeMetier() {
		try {
			EnvironnementTypeDatabaseService.create(libelle);
			
			HistoriqueDatabaseService.create(null, MessageFormat.format(getText("historique.environnement.type.creation"),new Object[]{libelle, EnvironnementTypeDatabaseService.getId(libelle)}), (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_ENVIRONNEMENT);

			info = MessageFormat.format(getText("environnement.type.creation.valide"), new Object[]{libelle});
			libelle = "";

			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + ":" + e.getMessage();
			erreurLogger.error("Creation d'un type d'environnement",e);
			return ERROR;
		}
	}

}
