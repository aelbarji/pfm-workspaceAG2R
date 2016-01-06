package pilotage.bilan.alertes.types;

import java.text.MessageFormat;

import pilotage.database.bilan.AlertesTypeDatabaseService;
import pilotage.database.historique.HistoriqueDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;

public class CreateTypeAction extends AbstractAction {

	private static final long serialVersionUID = 302011581616302950L;
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
			if(AlertesTypeDatabaseService.exists(null, libelle)){
				error = MessageFormat.format(getText("alertes.type.existe.deja"), libelle);
				return false;
			}
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Création d'un type d'alerte disque - ", e);
			return false;
		}
		return true;
	}

	@Override
	protected String executeMetier() {
		try {
			AlertesTypeDatabaseService.create(libelle);
			info = MessageFormat.format(getText("alertes.type.creation.valide"), libelle);
			HistoriqueDatabaseService.create(null, MessageFormat.format(getText("historique.bilan.alerte.type.creation"), new Object[]{libelle, AlertesTypeDatabaseService.getId(libelle)}), (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_BILAN);
			
			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Création d'un type d'alerte disque - ", e);
			return ERROR;
		}
	}

}
