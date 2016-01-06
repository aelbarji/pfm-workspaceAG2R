package pilotage.planning.cycles;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import pilotage.database.historique.HistoriqueDatabaseService;
import pilotage.database.planning.PlanningCyclesDatabaseService;
import pilotage.database.planning.PlanningSemaineDatabaseService;
import pilotage.database.planning.PlanningVacationsDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Planning_Semaine;
import pilotage.metier.Planning_Vacation;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;

public class ModifyPlanningCyclesAction extends AbstractAction{

	private static final long serialVersionUID = 7013818341148152623L;
	
	private Integer selectRow;
	private String nomCycle;
	private List<Planning_Vacation> listVacation;
	private List<Planning_Semaine> listSemaine;
	private List<Map<String, String>> mapSemaine;
	
	public Integer getSelectRow() {
		return selectRow;
	}

	public void setSelectRow(Integer selectRow) {
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
		try{
			if(PlanningCyclesDatabaseService.exists(selectRow, nomCycle)){
				error = MessageFormat.format(getText("planning.cycle.creation.existe.deja"), nomCycle);
				prepareRedirectToModificationPage(ServletActionContext.getRequest());
				return false;
			}
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Modification d'un cycle de planning - ", e);
			return false;
		}
		return true;
	}

	@Override
	protected String executeMetier() {
		try{
			HttpServletRequest request = ServletActionContext.getRequest();
			
			mapSemaine = new ArrayList<Map<String,String>>();
			CreatePlanningCyclesAction.getListSemaine(request, mapSemaine);
			
			List<Planning_Semaine> listSemaineEnBase = PlanningSemaineDatabaseService.getSemaineByCycle(selectRow);
			
			List<Integer> semaineToDelete = new ArrayList<Integer>();
			//List<Map<String, String>> semaineToUpdate = new ArrayList<Map<String,String>>();
			List<Map<String, String>> semaineToAdd = new ArrayList<Map<String,String>>();
			
			for(Map<String,String> semaine : mapSemaine){
				boolean alreadyInBase = false;
				boolean inBaseButUpdated = false;
				Integer numSemaine = Integer.parseInt(semaine.get(CreatePlanningCyclesAction.NumSemaine));
				Integer lundi = Integer.parseInt(semaine.get(CreatePlanningCyclesAction.LUNDI));
				Integer mardi = Integer.parseInt(semaine.get(CreatePlanningCyclesAction.MARDI));
				Integer mercredi = Integer.parseInt(semaine.get(CreatePlanningCyclesAction.MERCREDI));
				Integer jeudi = Integer.parseInt(semaine.get(CreatePlanningCyclesAction.JEUDI));
				Integer vendredi = Integer.parseInt(semaine.get(CreatePlanningCyclesAction.VENDREDI));
				Integer samedi = Integer.parseInt(semaine.get(CreatePlanningCyclesAction.SAMEDI));
				Integer dimanche = Integer.parseInt(semaine.get(CreatePlanningCyclesAction.DIMANCHE));
				
				for(Planning_Semaine ps : listSemaineEnBase){
					
					if(ps.getNumeroSemaine().equals(numSemaine) && ps.getLundi().getId().equals(lundi) && ps.getMardi().getId().equals(mardi) && ps.getMercredi().getId().equals(mercredi) 
							&& ps.getJeudi().getId().equals(jeudi) && ps.getVendredi().getId().equals(vendredi) && ps.getSamedi().getId().equals(samedi) && ps.getDimanche().getId().equals(dimanche)){
						alreadyInBase = true;
						break;
					}
					else if(ps.getNumeroSemaine().equals(numSemaine)) {
						inBaseButUpdated = true;
						semaine.put("id", ps.getId().toString());
						break;
					}
				}
				/*if(inBaseButUpdated){
					semaineToUpdate.add(semaine);
				}*/
				if(!alreadyInBase || inBaseButUpdated){
					semaineToAdd.add(semaine);
				}
			}
			for(Planning_Semaine ps : listSemaineEnBase){
				boolean stillInList = false;
				Integer numSemaine = ps.getNumeroSemaine();
				for(Map<String,String> semaine : mapSemaine){
					if(numSemaine.equals(Integer.parseInt(semaine.get(CreatePlanningCyclesAction.NumSemaine)))) {
						stillInList = true;
						break;
					}
				}
				if(!stillInList){
					semaineToDelete.add(ps.getId());
				}
			}
			
			String historique = " ";
			if (!nomCycle.equals(PlanningCyclesDatabaseService.get(selectRow).getNomCycle())) {
				historique += "nomCycle, ";
			}
			for (Integer i : semaineToDelete) {
				historique += "suppression de la semaine " + i + ", ";
			}
			/*for (Map<String, String> i : semaineToUpdate) {
				historique += "modification de la semaine " + i.get("id") + ", ";
			}*/
			
			PlanningCyclesDatabaseService.modify(selectRow, nomCycle, semaineToAdd, semaineToDelete);
			for (Map<String, String> i : semaineToAdd) {
				historique += "ajout de la semaine " + PlanningSemaineDatabaseService.getId(selectRow, Integer.parseInt(i.get("numSemaine"))) + ", ";
			}
			
			info = MessageFormat.format(getText("planning.cycle.modification.valide"), new Object[]{nomCycle});
			HistoriqueDatabaseService.create(null, MessageFormat.format(getText("historique.planning.cycle.modification"), new Object[]{historique, selectRow}), (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_PLANNING);
			
			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Modification d'un cycle de planning - ", e);
			prepareRedirectToModificationPage(ServletActionContext.getRequest());
			return ERROR;
		}
	}
	
	private void prepareRedirectToModificationPage(HttpServletRequest request){
		nomCycle = PlanningCyclesDatabaseService.get(selectRow).getNomCycle();
		listVacation = PlanningVacationsDatabaseService.getAll();
		//listSemaine = PlanningSemaineDatabaseService.getSemaineByCycle(selectRow);
		mapSemaine = new ArrayList<Map<String,String>>();
		CreatePlanningCyclesAction.getListSemaine(request, mapSemaine);
		/*for(Planning_Semaine ps : listSemaine){
			Map<String, String> semaine = new HashMap<String, String>();
			semaine.put(CreatePlanningCyclesAction.LUNDI, ps.getLundi().getId().toString());
			semaine.put(CreatePlanningCyclesAction.MARDI, ps.getMardi().getId().toString());
			semaine.put(CreatePlanningCyclesAction.MERCREDI, ps.getMercredi().getId().toString());
			semaine.put(CreatePlanningCyclesAction.JEUDI, ps.getJeudi().getId().toString());
			semaine.put(CreatePlanningCyclesAction.VENDREDI, ps.getVendredi().getId().toString());
			semaine.put(CreatePlanningCyclesAction.SAMEDI, ps.getSamedi().getId().toString());
			semaine.put(CreatePlanningCyclesAction.DIMANCHE, ps.getDimanche().getId().toString());
			mapSemaine.add(semaine);
		}*/
	}
}