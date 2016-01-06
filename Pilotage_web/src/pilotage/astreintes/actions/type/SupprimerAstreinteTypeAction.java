package pilotage.astreintes.actions.type;

import java.text.MessageFormat;

import pilotage.database.astreintes.AstreinteDatabaseService;
import pilotage.database.astreintes.AstreinteTypeDatabaseService;
import pilotage.database.historique.HistoriqueDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Astreinte_Type;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;

public class SupprimerAstreinteTypeAction extends AbstractAction{

	private static final long serialVersionUID = 3105465082843571342L;
	
	private Integer typeID;

	public Integer getTypeID() {
		return typeID;
	}

	public void setTypeID(Integer typeID) {
		this.typeID = typeID;
	}

	@Override
	protected boolean validateMetier() {
		try{
			if(AstreinteDatabaseService.hasAstreinteType(typeID)){
				error = getText("astreinte.type.suppression.failed");
				return false;
			}
			return true;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Suppression d'un type d'astreinte - ", e);
			return false;	
		}
	}

	@Override
	protected String executeMetier() {
		try{
			Astreinte_Type aType = AstreinteTypeDatabaseService.get(typeID);
			String labelle = aType.getType();
			AstreinteTypeDatabaseService.delete(typeID);
			HistoriqueDatabaseService.create(null, MessageFormat.format(getText("historique.astreinte.type.suppression"), new Object[]{labelle, typeID}), (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_ASTREINTES);

			info = MessageFormat.format(getText("astreinte.type.suppression.valide"), new Object[]{aType.getType()});
			
			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Suppression d'un type d'astreinte - ", e);
			return ERROR;	
		}
	}

}
