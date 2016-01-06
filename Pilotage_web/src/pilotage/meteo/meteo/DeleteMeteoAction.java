package pilotage.meteo.meteo;

import java.text.MessageFormat;

import pilotage.database.meteo.MeteoDatabaseService;
import pilotage.framework.AbstractAction;

public class DeleteMeteoAction extends AbstractAction{

	private static final long serialVersionUID = -6183669772873461891L;
	private  Integer meteoID;
	
	public Integer getMeteoID() {
		return meteoID;
	}

	public void setMeteoID(Integer meteoID) {
		this.meteoID = meteoID;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		try{
			String nom = MeteoDatabaseService.get(meteoID).getNom_meteo();
			MeteoDatabaseService.delete(meteoID);
			info = MessageFormat.format(getText("meteo.suppression.valide"), new Object[]{nom}); 
			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Suppression d'une Météo - ", e);
			return ERROR;
		}
	}

	

}
