package pilotage.derogation.type;

import java.text.MessageFormat;

import pilotage.database.derogation.DerogationDatabaseService;
import pilotage.database.derogation.DerogationTypeDatabaseService;
import pilotage.database.historique.HistoriqueDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;

public class SupprimerDerogationTypeAction extends AbstractAction{

	private static final long serialVersionUID = -3262339411784004524L;
	
	private Integer id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	protected boolean validateMetier() {
		try{
			if(DerogationDatabaseService.hasDerogationType(id)){
				error = getText("derogation.type.suppression.failed");
				return false;
			}
			return true;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Suppression d'un type de dérogation - ", e);
			return false;	
		}
	}

	@Override
	protected String executeMetier() {
		try{
			String libelle = DerogationTypeDatabaseService.get(id).getType();
			DerogationTypeDatabaseService.delete(id);
			info = getText("derogation.type.suppression.valide");
			HistoriqueDatabaseService.create(null, MessageFormat.format(getText("historique.derogation.type.suppression"), new Object[]{libelle,id}), (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_DEROGATION);

			return OK;
		}
		catch(Exception e){
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Suppression d'un type de dérogation - ", e);
			return ERROR;
		}
	}

}
