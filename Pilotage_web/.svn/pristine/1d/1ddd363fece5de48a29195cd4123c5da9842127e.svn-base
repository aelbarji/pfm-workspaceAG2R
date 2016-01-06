package pilotage.planning.equipes;

import java.util.ArrayList;
import java.util.List;

import pilotage.database.planning.PlanningCyclesDatabaseService;
import pilotage.database.planning.PlanningEquipePiloteDatabaseService;
import pilotage.database.users.management.UsersDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Planning_Cycle;
import pilotage.metier.Planning_Equipe_Pilote;
import pilotage.metier.Users;

public class RedirectCreatePlanningEquipesAction extends AbstractAction {

	private static final long serialVersionUID = 2800115204815595744L;
	
	private List<Users> listPilote;
	private List<Planning_Cycle> listCycle;

	public List<Users> getListPilote() {
		return listPilote;
	}

	public void setListPilote(List<Users> listPilote) {
		this.listPilote = listPilote;
	}

	public List<Planning_Cycle> getListCycle() {
		return listCycle;
	}

	public void setListCycle(List<Planning_Cycle> listCycle) {
		this.listCycle = listCycle;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		List<Planning_Equipe_Pilote> listPiloteEquipe = PlanningEquipePiloteDatabaseService.getAll();
		List<Integer> listIdPilote = new ArrayList<Integer>();
		for (Planning_Equipe_Pilote pep : listPiloteEquipe) {
			listIdPilote.add(pep.getIdUser().getId());
		}
		listPilote = UsersDatabaseService.getAllExcept(listIdPilote, null, true);
		listCycle = PlanningCyclesDatabaseService.getAll();
		return OK;
	}
}
