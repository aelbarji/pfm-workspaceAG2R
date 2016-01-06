package pilotage.planning.equipes;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import pilotage.database.historique.HistoriqueDatabaseService;
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
import pilotage.service.constants.PilotageConstants;
import pilotage.service.date.DateService;

public class ModifyPlanningEquipesAction extends AbstractAction{

	private static final long serialVersionUID = 7013818341148152623L;
	
	private int selectRow;
	private String nomEquipe;
	private String deletePilote;
	private List<Users> listPilote;
	private List<Planning_Cycle> listCycle;
	private List<Map<String, String>> mapCycle;
	private List<Planning_Nom_Equipe> listPlanningEquipe;
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

	public String getDeletePilote() {
		return deletePilote;
	}

	public void setDeletePilote(String deletePilote) {
		this.deletePilote = deletePilote;
	}

	public void setNomEquipe(String nomEquipe) {
		this.nomEquipe = nomEquipe;
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

	public List<Map<String, String>> getMapCycle() {
		return mapCycle;
	}

	public void setMapCycle(List<Map<String, String>> mapCycle) {
		this.mapCycle = mapCycle;
	}

	public List<Planning_Nom_Equipe> getListPlanningEquipe() {
		return listPlanningEquipe;
	}

	public void setListPlanningEquipe(List<Planning_Nom_Equipe> listPlanningEquipe) {
		this.listPlanningEquipe = listPlanningEquipe;
	}

	public List<Map<String, String>> getMapPilote() {
		return mapPilote;
	}

	public void setMapPilote(List<Map<String, String>> mapPilote) {
		this.mapPilote = mapPilote;
	}

	@Override
	protected boolean validateMetier() {
		try{
			if(PlanningEquipeDatabaseService.exists(selectRow, nomEquipe)){
				error = MessageFormat.format(getText("planning.equipe.creation.existe.deja"), nomEquipe);
				prepareRedirectToModificationPage();
				return false;
			} else {
				HttpServletRequest request = ServletActionContext.getRequest();

				mapPilote = new ArrayList<Map<String,String>>();
				CreatePlanningEquipesAction.getListPilote(request, mapPilote);
				for (Map<String, String> pilote : mapPilote) {
					Users u = UsersDatabaseService.get(Integer.parseInt(pilote.get("pilote")));
					List<Planning_Equipe_Pilote> listPEP = PlanningEquipePiloteDatabaseService.getListEquipeByPilote(u);
					for (Planning_Equipe_Pilote pep : listPEP) {
						if (!pep.getIdNomEquipe().getId().equals(selectRow)) {
							Date dateDebut = null;
							Date dateFin = null;
							if (pilote.get("dateDebut") != null){
								dateDebut = DateService.strToDate(pilote.get("dateDebut"));
							} 
							if (pilote.get("dateFin") != null){
								dateFin = DateService.strToDate(pilote.get("dateFin"));
							} 
							boolean checkDate = false;
							if (pep.getDateFin() != null && dateFin != null) {
								checkDate = (dateDebut.after(DateService.addDays(pep.getDateDebut(),-1)) && dateDebut.before(DateService.addDays(pep.getDateFin(),1))) 
											|| (dateFin.after(DateService.addDays(pep.getDateDebut(),-1)) && dateFin.before(DateService.addDays(pep.getDateFin(),1)))
											|| (dateDebut.before(DateService.addDays(pep.getDateDebut(),1)) && dateFin.after(DateService.addDays(pep.getDateFin(),-1)));
							} else if (pep.getDateFin() != null && dateFin == null) {
								checkDate = dateDebut.before(DateService.addDays(pep.getDateFin(),1));
							} else if (dateFin != null && pep.getDateFin() == null){
								checkDate = dateFin.after(DateService.addDays(pep.getDateDebut(),-1));
							} else if (pep.getDateFin() == null && dateFin == null) {
								checkDate = true;
							}
							if (checkDate) {
								error = MessageFormat.format(getText("planning.equipe.date.failed"), new Object[]{u.getPrenom() + " " + u.getNom(),"du " + pilote.get("dateDebut") + " au " + pilote.get("dateFin")});
								prepareRedirectToModificationPage();
								return false;
							}
						}
					}
				}
			}
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Modification d'une équipe de planning - ", e);
			return false;
		}
		return true;
	}

	@Override
	protected String executeMetier() {
		try{
			HttpServletRequest request = ServletActionContext.getRequest();
			
			List<Planning_Equipe_Pilote> listPiloteEnBase = PlanningEquipePiloteDatabaseService.getPilotesByEquipe(selectRow);
			mapPilote = new ArrayList<Map<String,String>>();
			CreatePlanningEquipesAction.getListPilote(request, mapPilote);
			
			List<Integer> piloteToDelete = new ArrayList<Integer>();
			List<Map<String, String>> piloteToUpdate = new ArrayList<Map<String,String>>();
			List<Map<String, String>> piloteToAdd = new ArrayList<Map<String,String>>();
			for(Map<String,String> pilote : mapPilote){
				boolean alreadyInBase = false;
				boolean inBaseButUpdated = false;
				if(pilote.get(CreatePlanningEquipesAction.ID)!=null){
				Integer piloteId = Integer.parseInt(pilote.get(CreatePlanningEquipesAction.ID));
				Integer Pilote = Integer.parseInt(pilote.get(CreatePlanningEquipesAction.PILOTE));
				Date dateDebut = DateService.strToDate(pilote.get(CreatePlanningEquipesAction.DATE_DEBUT));
				Date dateFin = DateService.strToDate(pilote.get(CreatePlanningEquipesAction.DATE_FIN));
				for(Planning_Equipe_Pilote pep : listPiloteEnBase){
					if(pep.getId().equals(piloteId) && pep.getIdUser().getId().equals(Pilote) && pep.getDateDebut().equals(dateDebut) && (pep.getDateFin() == null ? dateFin == null: pep.getDateFin().equals(dateFin))) {
						alreadyInBase = true;
						break;
					}
					else if(pep.getId().equals(piloteId)) {
						inBaseButUpdated = true;
						pilote.put("id", pep.getId().toString());
						break;
					}
				}
				if(inBaseButUpdated){
					piloteToUpdate.add(pilote);
				}
				else if(!alreadyInBase){
					piloteToAdd.add(pilote);
				}
				}else{
					piloteToAdd.add(pilote);
				}
			}
			for(Planning_Equipe_Pilote pep : listPiloteEnBase){
				boolean stillInList = false;
				Integer piloteId = pep.getId();
				for(Map<String,String> pilote : mapPilote){
					if(piloteId.equals(Integer.parseInt(pilote.get(CreatePlanningEquipesAction.ID)))){
						stillInList = true;
						break;
					}
				}
				if(!stillInList){
					piloteToDelete.add(pep.getId());
				}
			}
			
			
			List<Planning_Cycle_Equipe> listCycleEnBase = PlanningCycleEquipeDatabaseService.getCycleByEquipe(selectRow);
			mapCycle = new ArrayList<Map<String,String>>();
			CreatePlanningEquipesAction.getListCycle(request, mapCycle);
			
			List<Integer> cycleToDelete = new ArrayList<Integer>();
			List<Map<String, String>> cycleToUpdate = new ArrayList<Map<String,String>>();
			List<Map<String, String>> cycleToAdd = new ArrayList<Map<String,String>>();
			for(Map<String,String> cycle : mapCycle){
				boolean alreadyInBase = false;
				boolean inBaseButUpdated = false;
				Integer cycleId = Integer.parseInt(cycle.get(CreatePlanningEquipesAction.ID));
				Integer Cycle = Integer.parseInt(cycle.get(CreatePlanningEquipesAction.CYCLE));
				Date dateDebut = DateService.strToDate(cycle.get(CreatePlanningEquipesAction.DATE_DEBUT));
				Date dateFin = DateService.strToDate(cycle.get(CreatePlanningEquipesAction.DATE_FIN));
				for(Planning_Cycle_Equipe pce : listCycleEnBase){
					if(pce.getId().equals(cycleId) && pce.getIdNomCycle().getId().equals(Cycle) && pce.getDateDebut().equals(dateDebut) && (pce.getDateFin() == null ? dateFin == null : pce.getDateFin().equals(dateFin))) {
						alreadyInBase = true;
						break;
					}
					else if(pce.getId().equals(cycleId)) {
						inBaseButUpdated = true;
						cycle.put("id", pce.getId().toString());
						break;
					}
				}
				if(inBaseButUpdated){
					cycleToUpdate.add(cycle);
				}
				else if(!alreadyInBase){
					cycleToAdd.add(cycle);
				}
			}
			for(Planning_Cycle_Equipe pce : listCycleEnBase){
				boolean stillInList = false;
				Integer cycleId = pce.getId();
				for(Map<String,String> cycle : mapCycle){
					if(cycleId.equals(Integer.parseInt(cycle.get(CreatePlanningEquipesAction.ID)))){
						stillInList = true;
						break;
					}
				}
				if(!stillInList){
					cycleToDelete.add(pce.getId());
				}
			}
			
			String historique = " ";
			if (!nomEquipe.equals(PlanningEquipeDatabaseService.get(selectRow).getNomEquipe())) {
				historique += "nomEquipe, ";
			}
			
			for (Integer i : piloteToDelete) {
				historique += "suppression du pilote " + i + ", ";
			}	
			for (Integer i : cycleToDelete) {
				historique += "suppression du cyle " + i + ", ";
			}
			for (Map<String, String> i : piloteToUpdate) {
				historique += "modification du pilote " + i.get("id") + ", ";
			}
			for (Map<String, String> i : cycleToUpdate) {
				historique += "modification du cycle " + i.get("id") + ", ";
			}

			PlanningEquipeDatabaseService.modify(selectRow, nomEquipe, piloteToAdd, piloteToUpdate, piloteToDelete, cycleToAdd, cycleToUpdate, cycleToDelete);
			for (Map<String, String> i : piloteToAdd) {
				historique += "ajout du pilote " + PlanningEquipePiloteDatabaseService.getId(selectRow, Integer.parseInt(i.get("pilote")), i.get("dateDebut")) + ", ";
			}
			for (Map<String, String> i : cycleToAdd) {
				historique += "ajout du cycle " + PlanningCycleEquipeDatabaseService.getId(selectRow, Integer.parseInt(i.get("cycle"))) + ", ";
			}
			
			listPlanningEquipe = PlanningEquipeDatabaseService.getAll();
			info = MessageFormat.format(getText("planning.equipe.modification.valide"), new Object[]{nomEquipe});
			HistoriqueDatabaseService.create(null, MessageFormat.format(getText("historique.planning.equipe.modification"), new Object[]{historique, selectRow}), (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_PLANNING);
			
			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Modification d'une équipe de planning - ", e);
			prepareRedirectToModificationPage();
			return ERROR;
		}
	}
	
	private void prepareRedirectToModificationPage(){
		HttpServletRequest request = ServletActionContext.getRequest();

		nomEquipe = PlanningEquipeDatabaseService.get(selectRow).getNomEquipe();
		
		mapPilote = new ArrayList<Map<String,String>>();
		CreatePlanningEquipesAction.getListPilote(request, mapPilote);
		
		mapCycle = new ArrayList<Map<String,String>>();
		CreatePlanningEquipesAction.getListCycle(request, mapCycle);
		
		List<Planning_Equipe_Pilote> listEquipePilote = PlanningEquipePiloteDatabaseService.getPilotesByEquipe(selectRow);
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
	}
}