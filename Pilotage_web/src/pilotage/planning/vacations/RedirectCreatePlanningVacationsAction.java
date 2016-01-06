package pilotage.planning.vacations;

import java.util.List;

import pilotage.database.planning.PlanningPatieJourDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Planning_Cra_Partie_Jour;

public class RedirectCreatePlanningVacationsAction extends AbstractAction {

	private static final long serialVersionUID = 2800115204815595744L;
	List<Planning_Cra_Partie_Jour> lpcpj;
	
	public List<Planning_Cra_Partie_Jour> getLpcpj() {
		return lpcpj;
	}

	public void setLpcpj(List<Planning_Cra_Partie_Jour> lpcpj) {
		this.lpcpj = lpcpj;
	}
	
	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		lpcpj = PlanningPatieJourDatabaseService.getAll();
		return OK;
	}
}
