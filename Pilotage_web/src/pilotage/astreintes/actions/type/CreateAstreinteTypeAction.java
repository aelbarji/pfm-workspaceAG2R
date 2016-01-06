package pilotage.astreintes.actions.type;

import java.text.MessageFormat;

import pilotage.database.astreintes.AstreinteTypeDatabaseService;
import pilotage.database.historique.HistoriqueDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;

public class CreateAstreinteTypeAction extends AbstractAction{

	private static final long serialVersionUID = -4014297635957816333L;
	
	private String astreinteType;

	public String getAstreinteType() {
		return astreinteType;
	}

	public void setAstreinteType(String astreinteType) {
		this.astreinteType = astreinteType;
	}

	@Override
	protected boolean validateMetier() {
		try{
			if(AstreinteTypeDatabaseService.exists(null, astreinteType)){
				error = MessageFormat.format(getText("astreinte.type.creation.existe.deja"), astreinteType);
				return false;
			}
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Création d'un type d'astreinte - ", e);
			return false;
		}
		return true;
	}

	@Override
	protected String executeMetier() {
		try{
			AstreinteTypeDatabaseService.create(astreinteType);
			HistoriqueDatabaseService.create(null, MessageFormat.format(getText("historique.astreinte.type.creation"), new Object[]{astreinteType,AstreinteTypeDatabaseService.getId(astreinteType)}), (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_ASTREINTES);
			info = MessageFormat.format(getText("astreinte.type.creation.valide"), new Object[]{astreinteType});
			astreinteType = null;
			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Création d'un type d'astreinte - ", e);
			return ERROR;
		}
	}

}
