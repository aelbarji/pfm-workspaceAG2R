package pilotage.astreintes.actions.domaine;

import java.text.MessageFormat;

import pilotage.database.astreintes.AstreinteDomaineDatabaseService;
import pilotage.database.historique.HistoriqueDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;

public class ModifyAstreinteDomaineAction extends AbstractAction{

	private static final long serialVersionUID = -5892707637867524500L;
	
	private Integer domaineID;
	private String astreinteDomaine;
	private Boolean libelleChanged;

	public Integer getDomaineID() {
		return domaineID;
	}

	public void setDomaineID(Integer domaineID) {
		this.domaineID = domaineID;
	}

	public String getAstreinteDomaine() {
		return astreinteDomaine;
	}

	public void setAstreinteDomaine(String astreinteDomaine) {
		this.astreinteDomaine = astreinteDomaine;
	}

	public Boolean getLibelleChanged() {
		return libelleChanged;
	}

	public void setLibelleChanged(Boolean libelleChanged) {
		this.libelleChanged = libelleChanged;
	}

	@Override
	protected boolean validateMetier() {
		try{
			if(libelleChanged && AstreinteDomaineDatabaseService.exists(domaineID, astreinteDomaine)){
				error = MessageFormat.format(getText("astreinte.domaine.creation.existe.deja"), astreinteDomaine);
				astreinteDomaine = AstreinteDomaineDatabaseService.get(domaineID).getDomaine();
				return false;
			}
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Modification d'un domaine d'astreinte - ", e);
			return false;
		}
		return true;
	}

	@Override
	protected String executeMetier() {
		try{
			AstreinteDomaineDatabaseService.modify(domaineID, astreinteDomaine);
			HistoriqueDatabaseService.create(null, MessageFormat.format(getText("historique.astreinte.domaine.modification"), new Object[]{domaineID}), (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_ASTREINTES);

			info = MessageFormat.format(getText("astreinte.domaine.modification.valide"), new Object[]{astreinteDomaine});
			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Modification d'un domaine d'astreinte - ", e);
			return ERROR;
		}
	}

}
