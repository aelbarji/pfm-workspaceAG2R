package pilotage.derogation.type;

import java.text.MessageFormat;

import pilotage.database.derogation.DerogationTypeDatabaseService;
import pilotage.database.historique.HistoriqueDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;

public class CreateDerogationTypeAction extends AbstractAction{

	
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
			if(DerogationTypeDatabaseService.exists(null, libelle)){
				error = MessageFormat.format(getText("derogation.type.creation.existe.deja"), libelle);
				return false;
			}
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Création d'un type de dérogation - ", e);
			return false;
		}
		return true;
	}

	@Override
	protected String executeMetier() {
		try{
			DerogationTypeDatabaseService.create(libelle);
			
			info = MessageFormat.format(getText("derogation.type.creation.valide"), new Object[]{libelle});
			HistoriqueDatabaseService.create(null, MessageFormat.format(getText("historique.derogation.type.creation"), new Object[]{libelle, DerogationTypeDatabaseService.getId(libelle)}), (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_DEROGATION);

			libelle = null;
			return OK;
		}
		catch(Exception e){
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Création d'un type de dérogation - ", e);
			return ERROR;
		}
	}
}
