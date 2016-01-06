package pilotage.meteo.environnement;

import pilotage.database.meteo.MeteoEnvironnementDatabaseService;
import pilotage.framework.AbstractAction;

public class DeleteEnvironnementMeteoAction extends AbstractAction{

	private static final long serialVersionUID = 437193951169879737L;
	private Integer envirID;

	public Integer getEnvirID() {
		return envirID;
	}

	public void setEnvirID(Integer envirID) {
		this.envirID = envirID;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		try{
			MeteoEnvironnementDatabaseService.delete(envirID);
			info =  getText("envirMeteo.suppression.valide");
			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Suppression d'un environnement Météo - ", e);
			return ERROR;
		}
	}

}
