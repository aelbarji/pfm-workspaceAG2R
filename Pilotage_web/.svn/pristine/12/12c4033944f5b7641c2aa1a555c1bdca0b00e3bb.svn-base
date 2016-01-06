package pilotage.planning.vacations;

import java.text.MessageFormat;

import pilotage.database.historique.HistoriqueDatabaseService;
import pilotage.database.planning.PlanningModifPonctuelleDatabaseService;
import pilotage.database.planning.PlanningSemaineDatabaseService;
import pilotage.database.planning.PlanningVacationsDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;


public class SupprimerPlanningVacationsAction extends AbstractAction{

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
		/*if(PlanningSemaineDatabaseService.hasSemaineWithVacation(selectRow) || PlanningModifPonctuelleDatabaseService.hasModifPonctuelleWithVacation(selectRow)){
			error = getText("planning.vacation.suppression.failed");
			return false;
		}*/
		return true;
	}

	@Override
	protected String executeMetier() {
		try{
			String libelle = PlanningVacationsDatabaseService.get(selectRow).getLibelle();
			if(PlanningSemaineDatabaseService.hasSemaineWithVacation(selectRow) || PlanningModifPonctuelleDatabaseService.hasModifPonctuelleWithVacation(selectRow))
				PlanningVacationsDatabaseService.desactiver(selectRow);
			else
				PlanningVacationsDatabaseService.delete(selectRow);
			info = MessageFormat.format(getText("planning.vacation.suppression.valide"), libelle);
			HistoriqueDatabaseService.create(null, MessageFormat.format(getText("historique.planning.vacation.suppression"), new Object[]{selectRow,libelle}), (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_PLANNING);
			return OK;
		}
		catch (Exception e){
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Suppression d'une vacation de planning - ", e);
			return ERROR;
		}
	}
}
