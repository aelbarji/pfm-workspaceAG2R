package pilotage.meteo.typeIndicateur;

import java.text.MessageFormat;

import pilotage.database.meteo.TypeIndicateurDatabaseService;
import pilotage.framework.AbstractAction;

public class DeleteTypeIndicateurAction extends AbstractAction{

	private static final long serialVersionUID = 8900722716663804715L;

	private Integer typeID;
	
	public Integer getTypeID() {
		return typeID;
	}

	public void setTypeID(Integer typeID) {
		this.typeID = typeID;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		try{
			String type = TypeIndicateurDatabaseService.get(typeID).getType();
			TypeIndicateurDatabaseService.delete(typeID);
			info = MessageFormat.format(getText("meteo.typeIndicateur.suppression.valide"), new Object[]{type}); 
			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Suppression d'un type d'indicateur Météo - ", e);
			return ERROR;
		}
	}

}
