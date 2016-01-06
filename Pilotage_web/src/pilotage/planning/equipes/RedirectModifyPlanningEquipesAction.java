package pilotage.planning.equipes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pilotage.database.planning.PlanningCycleEquipeDatabaseService;
import pilotage.database.planning.PlanningCyclesDatabaseService;
import pilotage.database.planning.PlanningEquipeDatabaseService;
import pilotage.database.planning.PlanningEquipePiloteDatabaseService;
import pilotage.database.users.management.UsersDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Planning_Cycle;
import pilotage.metier.Planning_Cycle_Equipe;
import pilotage.metier.Planning_Equipe_Pilote;
import pilotage.metier.Planning_Nom_Equipe;
import pilotage.metier.Users;
import pilotage.service.date.DateService;

public class RedirectModifyPlanningEquipesAction extends AbstractAction {
	
	private static final long serialVersionUID = -2136820498896120263L;
	
	private int selectRow;
	private String nomEquipe;
	private List<Planning_Nom_Equipe> listPlanningEquipe;
	private List<Users> listPilote;
	private List<Planning_Cycle> listCycle;
	private List<Planning_Cycle_Equipe> listCycleEquipe;
	private List<Planning_Equipe_Pilote> listEquipePilote;
	private List<Map<String, String>> mapCycle;
	private List<Map<String, String>> mapPilote;

	public int getSelectRow() {
		return selectRow;
	}

	public void setSelectRow(int selectRow) {
		this.selectRow = selectRow;
	}

	public String getNomEquipe() {
		return nomEquipe;
	}

	public void setNomEquipe(String nomEquipe) {
		this.nomEquipe = nomEquipe;
	}

	public List<Planning_Nom_Equipe> getListPlanningEquipe() {
		return listPlanningEquipe;
	}

	public void setListPlanningEquipe(List<Planning_Nom_Equipe> listPlanningEquipe) {
		this.listPlanningEquipe = listPlanningEquipe;
	}

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

	public List<Planning_Cycle_Equipe> getListCycleEquipe() {
		return listCycleEquipe;
	}

	public void setListCycleEquipe(List<Planning_Cycle_Equipe> listCycleEquipe) {
		this.listCycleEquipe = listCycleEquipe;
	}

	public List<Planning_Equipe_Pilote> getListEquipePilote() {
		return listEquipePilote;
	}

	public void setListEquipePilote(List<Planning_Equipe_Pilote> listEquipePilote) {
		this.listEquipePilote = listEquipePilote;
	}

	public List<Map<String, String>> getMapCycle() {
		return mapCycle;
	}

	public void setMapCycle(List<Map<String, String>> mapCycle) {
		this.mapCycle = mapCycle;
	}

	public List<Map<String, String>> getMapPilote() {
		return mapPilote;
	}

	public void setMapPilote(List<Map<String, String>> mapPilote) {
		this.mapPilote = mapPilote;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		try {
			nomEquipe = PlanningEquipeDatabaseService.get(selectRow).getNomEquipe();
			
			listCycleEquipe = PlanningCycleEquipeDatabaseService.getCycleByEquipe(selectRow);
			mapCycle = new ArrayList<Map<String,String>>();
			for(Planning_Cycle_Equipe pce : listCycleEquipe){
				Map<String, String> cycle = new HashMap<String, String>();
				cycle.put(CreatePlanningEquipesAction.CYCLE, pce.getIdNomCycle().getId().toString());
				cycle.put(CreatePlanningEquipesAction.ID, pce.getId().toString());
				cycle.put(CreatePlanningEquipesAction.DATE_DEBUT, DateService.dateToStr(pce.getDateDebut(),DateService.p1));
				cycle.put(CreatePlanningEquipesAction.DATE_FIN, pce.getDateFin() == null ? "":DateService.dateToStr(pce.getDateFin(),DateService.p1));
				mapCycle.add(cycle);
			}
			
			listEquipePilote = PlanningEquipePiloteDatabaseService.getPilotesByEquipe(selectRow);
			mapPilote = new ArrayList<Map<String,String>>();
			for(Planning_Equipe_Pilote pep : listEquipePilote){
				Map<String, String> pilote = new HashMap<String, String>();
				pilote.put(CreatePlanningEquipesAction.PILOTE, pep.getIdUser().getId().toString());
				pilote.put(CreatePlanningEquipesAction.ID, pep.getId().toString());
				pilote.put(CreatePlanningEquipesAction.DATE_DEBUT, DateService.dateToStr(pep.getDateDebut(),DateService.p1));
				pilote.put(CreatePlanningEquipesAction.DATE_FIN, pep.getDateFin() == null ? "":DateService.dateToStr(pep.getDateFin(),DateService.p1));
				mapPilote.add(pilote);
			}
			
			List<Planning_Equipe_Pilote> listPiloteEquipeWithoutEquipePiloteSelect = PlanningEquipePiloteDatabaseService.getAllWithoutEquipePiloteSelect(selectRow);
			List<Integer> listIdPilote = new ArrayList<Integer>();
			for (Planning_Equipe_Pilote pep : listPiloteEquipeWithoutEquipePiloteSelect) {
				listIdPilote.add(pep.getIdUser().getId());
			}
			for(Planning_Equipe_Pilote pep : listEquipePilote) {
				if (listIdPilote.contains(pep.getIdUser().getId())) {
					listIdPilote.remove(pep.getIdUser().getId());
				}
			}
			
			listPilote = UsersDatabaseService.getAllExcept(listIdPilote, null, true);
			listCycle = PlanningCyclesDatabaseService.getAll();
			return OK;
		}
		catch (Exception e) {
			erreurLogger.error("Redirection vers la modification d'une équipe de planning : ", e);
			error = getText("error.message.generique") + " : " + e.getMessage();
			listPlanningEquipe = PlanningEquipeDatabaseService.getAll();
			return ERROR;
		}
	}
}
