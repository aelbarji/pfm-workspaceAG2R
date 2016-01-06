package pilotage.meteo.groupeMeteo;

import java.text.MessageFormat;
import pilotage.database.meteo.GroupeMeteoDatabaseService;
import pilotage.framework.AbstractAction;

public class DeleteGroupeMeteoAction extends AbstractAction{

	private static final long serialVersionUID = 3661024662778579691L;
	private  Integer groupeMeteoID;
	
	public Integer getGroupeMeteoID() {
		return groupeMeteoID;
	}

	public void setGroupeMeteoID(Integer groupeMeteoID) {
		this.groupeMeteoID = groupeMeteoID;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		try{
			String nom = GroupeMeteoDatabaseService.get(groupeMeteoID).getNom_groupeMeteo();
			GroupeMeteoDatabaseService.delete(groupeMeteoID);
			info = MessageFormat.format(getText("groupeMeteo.suppression.valide"), new Object[]{nom}); 
			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Suppression d'un groupe Météo - ", e);
			return ERROR;
		}
	}

	
}
