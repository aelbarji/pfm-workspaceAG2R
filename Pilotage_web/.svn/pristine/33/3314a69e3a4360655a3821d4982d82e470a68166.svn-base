package pilotage.planning.cycles;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;

import pilotage.database.planning.PlanningCyclesDatabaseService;
import pilotage.database.planning.PlanningSemaineDatabaseService;
import pilotage.database.planning.PlanningVacationsDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Planning_Semaine;
import pilotage.metier.Planning_Vacation;

public class RedirectModifyPlanningCyclesAction extends AbstractAction {
	
	private static final long serialVersionUID = -2136820498896120263L;
	
	private int selectRow;
	private String nomCycle;
	private List<Planning_Vacation> listVacation;
	private List<Planning_Semaine> listSemaine;
	private List<Map<String, String>> mapSemaine;
	
	public int getSelectRow() {
		return selectRow;
	}

	public void setSelectRow(int selectRow) {
		this.selectRow = selectRow;
	}

	public String getNomCycle() {
		return nomCycle;
	}

	public void setNomCycle(String nomCycle) {
		this.nomCycle = nomCycle;
	}

	public List<Planning_Vacation> getListVacation() {
		return listVacation;
	}

	public void setListVacation(List<Planning_Vacation> listVacation) {
		this.listVacation = listVacation;
	}

	public List<Planning_Semaine> getListSemaine() {
		return listSemaine;
	}

	public void setListSemaine(List<Planning_Semaine> listSemaine) {
		this.listSemaine = listSemaine;
	}

	public List<Map<String, String>> getMapSemaine() {
		return mapSemaine;
	}

	public void setMapSemaine(List<Map<String, String>> mapSemaine) {
		this.mapSemaine = mapSemaine;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		try {
			nomCycle = PlanningCyclesDatabaseService.get(selectRow).getNomCycle();
			listVacation = PlanningVacationsDatabaseService.getAll();
			DateTime d = new DateTime();
		
			listSemaine = PlanningSemaineDatabaseService.getSemaineByCycleAndDate(selectRow, d, d);
			mapSemaine = new ArrayList<Map<String,String>>();
			for(Planning_Semaine ps : listSemaine){
				Map<String, String> semaine = new HashMap<String, String>();
				semaine.put(CreatePlanningCyclesAction.LUNDI, ps.getLundi().getId().toString());
				semaine.put(CreatePlanningCyclesAction.MARDI, ps.getMardi().getId().toString());
				semaine.put(CreatePlanningCyclesAction.MERCREDI, ps.getMercredi().getId().toString());
				semaine.put(CreatePlanningCyclesAction.JEUDI, ps.getJeudi().getId().toString());
				semaine.put(CreatePlanningCyclesAction.VENDREDI, ps.getVendredi().getId().toString());
				semaine.put(CreatePlanningCyclesAction.SAMEDI, ps.getSamedi().getId().toString());
				semaine.put(CreatePlanningCyclesAction.DIMANCHE, ps.getDimanche().getId().toString());
				mapSemaine.add(semaine);
			}
			return OK;
		}
		catch (Exception e) {
			erreurLogger.error("Redirection vers la modification d'un cycle de planning : ", e);
			error = getText("error.message.generique") + " : " + e.getMessage();
			return ERROR;
		}
	}
}
