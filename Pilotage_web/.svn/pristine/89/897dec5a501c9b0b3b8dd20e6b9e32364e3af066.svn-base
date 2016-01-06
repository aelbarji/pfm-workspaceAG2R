package pilotage.environnement.actions.type;


import java.text.MessageFormat;

import pilotage.database.environnement.EnvironnementDatabaseService;
import pilotage.database.environnement.EnvironnementTypeDatabaseService;
import pilotage.database.historique.HistoriqueDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;

public class ChangePrincipalTypeAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer selectRow;

	public Integer getSelectRow() {
		return selectRow;
	}

	public void setSelectRow(Integer selectRow) {
		this.selectRow = selectRow;
	}


	@Override
	protected boolean validateMetier() {
	try{
			
		if (!EnvironnementDatabaseService.hasEnvironnementType(selectRow)){
			String environnement_Type = EnvironnementTypeDatabaseService.get(selectRow).getType();
			error = MessageFormat.format(getText("environnement.type.principal.change.failed"),new Object[]{environnement_Type});
			return false;
		}
	} catch (Exception e) {
		error = getText("error.message.generique")+ ":"+ e.getMessage();
		erreurLogger.error("Modification d'un type d'environnement",e);
		return false;
	}	
		return true;            
	}

	@Override
	protected String executeMetier() {
		try {
			String environnement_Type = EnvironnementTypeDatabaseService.get(selectRow).getType();
			EnvironnementTypeDatabaseService.modifierPrincipal(selectRow);

			info = MessageFormat.format(getText("environnemnt.type.creation.principal"), new Object[]{environnement_Type});

			HistoriqueDatabaseService.create(null, MessageFormat.format(getText("historique.environnement.type.modification.principal"),new Object[]{selectRow}), (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_ENVIRONNEMENT);

			
			return OK;

		} catch (Exception e) {
			error = getText("error.message.generique") + ":"+ e.getMessage();
			erreurLogger.error("Modification de l'environnement principal",e);
			return ERROR;
		}
	}

}
