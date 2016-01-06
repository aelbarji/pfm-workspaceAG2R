package pilotage.planning.equipes;

import java.util.List;

import pilotage.database.planning.PlanningEquipeDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Planning_Nom_Equipe;

public class ShowPlanningEquipesAction extends AbstractAction {
	private static final long serialVersionUID = -4037713125016637085L;
	
	private List<Planning_Nom_Equipe> listPlanningEquipe;

	public List<Planning_Nom_Equipe> getListPlanningEquipe() {
		return listPlanningEquipe;
	}

	public void setListPlanningEquipe(List<Planning_Nom_Equipe> listPlanningEquipe) {
		this.listPlanningEquipe = listPlanningEquipe;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		listPlanningEquipe = PlanningEquipeDatabaseService.getAll();
		return OK;
	}
}