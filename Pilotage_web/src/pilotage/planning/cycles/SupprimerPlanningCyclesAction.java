package pilotage.planning.cycles;

import java.text.MessageFormat;

import pilotage.database.historique.HistoriqueDatabaseService;
import pilotage.database.planning.PlanningCycleEquipeDatabaseService;
import pilotage.database.planning.PlanningCyclesDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;

public class SupprimerPlanningCyclesAction extends AbstractAction{

	private static final long serialVersionUID = 5215317819756935397L;
	
	private int selectRow;

	/**
	 * @return the selectRow
	 */
	public int getSelectRow() {
		return selectRow;
	}

	/**
	 * @param selectRow the selectRow to set
	 */
	public void setSelectRow(int selectRow) {
		this.selectRow = selectRow;
	}

	@Override
	protected boolean validateMetier() {
		if(PlanningCycleEquipeDatabaseService.hasEquipeWithCycle(selectRow)){
			error = getText("planning.cycle.suppression.failed");
			return false;
		}
		return true;
	}

	@Override
	protected String executeMetier() {
		try{
			String libelle = PlanningCyclesDatabaseService.get(selectRow).getNomCycle();
			PlanningCyclesDatabaseService.delete(selectRow);
			info = MessageFormat.format(getText("planning.cycle.suppression.valide"), libelle);
			HistoriqueDatabaseService.create(null, MessageFormat.format(getText("historique.planning.cycle.suppression"), new Object[]{selectRow,libelle}), (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_PLANNING);
			return OK;
		}
		catch (Exception e){
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Suppression d'un cycle de planning - ", e);
			return ERROR;
		}
	}
}
