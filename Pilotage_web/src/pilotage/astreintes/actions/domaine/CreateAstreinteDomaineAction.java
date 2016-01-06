package pilotage.astreintes.actions.domaine;

import java.text.MessageFormat;

import pilotage.database.astreintes.AstreinteDomaineDatabaseService;
import pilotage.database.historique.HistoriqueDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;


public class CreateAstreinteDomaineAction extends AbstractAction{

	private static final long serialVersionUID = -7632679294144293519L;
	
	private String astreinteDomaine;

	public String getAstreinteDomaine() {
		return astreinteDomaine;
	}

	public void setAstreinteDomaine(String astreinteDomaine) {
		this.astreinteDomaine = astreinteDomaine;
	}


	@Override
	protected boolean validateMetier() {
		try{
			if(AstreinteDomaineDatabaseService.exists(null, astreinteDomaine)){
				error = MessageFormat.format(getText("astreinte.domaine.creation.existe.deja"), astreinteDomaine);
				return false;
			}
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Création d'un domaine d'astreinte - ", e);
			return false;
		}
		return true;
	}

	@Override
	protected String executeMetier() {
		try{
			AstreinteDomaineDatabaseService.create(astreinteDomaine);
			HistoriqueDatabaseService.create(null, MessageFormat.format(getText("historique.astreinte.domaine.creation"), new Object[]{astreinteDomaine,AstreinteDomaineDatabaseService.getId(astreinteDomaine)}), (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_ASTREINTES);

			info = MessageFormat.format(getText("astreinte.domaine.creation.valide"), new Object[]{astreinteDomaine});
			astreinteDomaine = null;
			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Création d'un domaine d'astreinte - ", e);
			return ERROR;
		}

	}

}
