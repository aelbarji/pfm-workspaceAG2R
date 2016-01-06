package pilotage.planning.cycles;

import java.util.List;

import pilotage.database.planning.PlanningCyclesDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Planning_Cycle;

public class ShowPlanningCyclesAction extends AbstractAction {
	private static final long serialVersionUID = -4037713125016637085L;
	private List<Planning_Cycle> listPlanningCycle;

	public List<Planning_Cycle> getListPlanningCycle() {
		return listPlanningCycle;
	}

	public void setListPlanningCycle(List<Planning_Cycle> listPlanningCycle) {
		this.listPlanningCycle = listPlanningCycle;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		listPlanningCycle = PlanningCyclesDatabaseService.getAll();
		return OK;
	}
}