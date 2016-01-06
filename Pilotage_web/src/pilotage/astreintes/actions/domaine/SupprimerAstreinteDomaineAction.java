package pilotage.astreintes.actions.domaine;

import java.text.MessageFormat;

import pilotage.database.astreintes.AstreinteDomaineDatabaseService;
import pilotage.database.astreintes.AstreintePlanningDatabaseService;
import pilotage.database.historique.HistoriqueDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Astreinte_Domaine;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;

public class SupprimerAstreinteDomaineAction extends AbstractAction{

	private static final long serialVersionUID = 2009212513602335792L;

	private int domaineID;
	
	public int getDomaineID() {
		return domaineID;
	}

	public void setDomaineID(int domaineID) {
		this.domaineID = domaineID;
	}

	@Override
	protected boolean validateMetier() {
		try{
			if(AstreintePlanningDatabaseService.hasAstreinteDomaine(domaineID)){
				error = getText("astreinte.domaine.suppression.failed");
				return false;
			}
			return true;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Suppression d'un domaine d'astreinte - ", e);
			return false;	
		}
	}

	@Override
	protected String executeMetier() {
		try{
			Astreinte_Domaine aDomaine = AstreinteDomaineDatabaseService.get(domaineID);
			String labelle = aDomaine.getDomaine();
			AstreinteDomaineDatabaseService.delete(domaineID);
			HistoriqueDatabaseService.create(null, MessageFormat.format(getText("historique.astreinte.domaine.suppression"), new Object[]{labelle, domaineID}), (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_ASTREINTES);

			info = MessageFormat.format(getText("astreinte.domaine.suppression.valide"), new Object[]{aDomaine.getDomaine()});
		
			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Suppression d'un domaine d'astreinte - ", e);
			return ERROR;	
		}
	}

}
