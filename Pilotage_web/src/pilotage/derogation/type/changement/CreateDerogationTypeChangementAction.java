package pilotage.derogation.type.changement;

import java.text.MessageFormat;

import pilotage.database.derogation.DerogationTypeChangementDatabaseService;
import pilotage.database.historique.HistoriqueDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;

public class CreateDerogationTypeChangementAction extends AbstractAction{

	
	private static final long serialVersionUID = -4675428619413807979L;
	
	private String libelle;

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	@Override
	protected boolean validateMetier() {
		try{
			if(DerogationTypeChangementDatabaseService.exists(null, libelle)){
				error = MessageFormat.format(getText("derogation.type.changement.creation.existe.deja"), libelle);
				return false;
			}
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Création d'un type de changement - ", e);
			return false;
		}
		return true;
	}

	@Override
	protected String executeMetier() {
		try{
			DerogationTypeChangementDatabaseService.create(libelle);
			
			HistoriqueDatabaseService.create(null, MessageFormat.format(getText("historique.derogation.type.changement.creation"),new Object[]{libelle,DerogationTypeChangementDatabaseService.getId(libelle)}), (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_DEROGATION);

			info = MessageFormat.format(getText("derogation.type.changement.creation.valide"), new Object[]{libelle});
			libelle = null;
			return OK;
		}
		catch(Exception e){
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Création d'un type de changement - ", e);
			return ERROR;
		}
	}
}
