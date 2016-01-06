package pilotage.planning.cycles;

import java.util.List;

import pilotage.database.planning.PlanningVacationsDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Planning_Vacation;

public class RedirectCreatePlanningCyclesAction extends AbstractAction {

	private static final long serialVersionUID = 2800115204815595744L;
	
	private List<Planning_Vacation> listVacation;
	
	public List<Planning_Vacation> getListVacation() {
		return listVacation;
	}

	public void setListVacation(List<Planning_Vacation> listVacation) {
		this.listVacation = listVacation;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		listVacation = PlanningVacationsDatabaseService.getAll();
		return OK;
	}
}
