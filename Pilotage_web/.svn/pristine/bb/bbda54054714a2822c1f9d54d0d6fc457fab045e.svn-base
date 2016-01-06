package pilotage.astreintes.actions.type;

import java.text.MessageFormat;

import pilotage.database.astreintes.AstreinteTypeDatabaseService;
import pilotage.database.historique.HistoriqueDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;

public class ModifyAstreinteTypeAction extends AbstractAction{

	private static final long serialVersionUID = -3081774105393357333L;

	private Integer typeID;
	private String astreinteType;
	private Boolean libelleChanged;

	public Integer getTypeID() {
		return typeID;
	}

	public void setTypeID(Integer typeID) {
		this.typeID = typeID;
	}

	public String getAstreinteType() {
		return astreinteType;
	}

	public void setAstreinteType(String astreinteType) {
		this.astreinteType = astreinteType;
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
			if(libelleChanged && AstreinteTypeDatabaseService.exists(typeID, astreinteType)){
				error = MessageFormat.format(getText("astreinte.type.creation.existe.deja"), astreinteType);
				astreinteType = AstreinteTypeDatabaseService.get(typeID).getType();
				return false;
			}
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Modification d'un type d'astreinte - ", e);
			return false;
		}
		return true;
	}

	@Override
	protected String executeMetier() {
		try{
			if(libelleChanged)
				AstreinteTypeDatabaseService.modify(typeID, astreinteType);

			HistoriqueDatabaseService.create(null, MessageFormat.format(getText("historique.astreinte.type.modification"), new Object[]{typeID}), (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_ASTREINTES);

			info = MessageFormat.format(getText("astreinte.type.modification.valide"), new Object[]{astreinteType});
			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Modification d'un type d'astreinte - ", e);
			return ERROR;
		}
	}

}
