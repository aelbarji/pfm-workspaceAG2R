package pilotage.derogation.type.changement;

import java.text.MessageFormat;

import pilotage.database.derogation.DerogationDatabaseService;
import pilotage.database.derogation.DerogationTypeChangementDatabaseService;
import pilotage.database.historique.HistoriqueDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;


public class SupprimerDerogationTypeChangementAction extends AbstractAction{

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
			if(DerogationDatabaseService.hasDerogationTypeChangement(id)){
				error = getText("derogation.type.changement.suppression.failed");
				return false;
			}
			return true;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Suppression d'un type de changement - ", e);
			return false;	
		}
	}

	@Override
	protected String executeMetier() {
		try{
			String libelle = DerogationTypeChangementDatabaseService.get(id).getTypeChangement();
			DerogationTypeChangementDatabaseService.delete(id);
			info = getText("derogation.type.changement.suppression.valide");
			HistoriqueDatabaseService.create(null, MessageFormat.format(getText("historique.derogation.type.changement.suppression"), new Object[]{libelle,id}), (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_DEROGATION);

			return OK;
		}
		catch(Exception e){
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Suppression d'un type de changement - ", e);
			return ERROR;
		}
	}

}
