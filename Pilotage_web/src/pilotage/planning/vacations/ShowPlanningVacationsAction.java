package pilotage.planning.vacations;

import java.util.List;

import pilotage.database.planning.PlanningVacationsDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Planning_Vacation;

public class ShowPlanningVacationsAction extends AbstractAction {
	private static final long serialVersionUID = -4037713125016637085L;
	private List<Planning_Vacation> listPlanningVacation;

	public List<Planning_Vacation> getListPlanningVacation() {
		return listPlanningVacation;
	}

	public void setListPlanningVacation(List<Planning_Vacation> listPlanningVacation) {
		this.listPlanningVacation = listPlanningVacation;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		listPlanningVacation = PlanningVacationsDatabaseService.getAll();
		return OK;
	}
}