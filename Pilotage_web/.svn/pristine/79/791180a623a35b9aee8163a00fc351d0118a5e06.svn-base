package pilotage.planning.equipes;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import pilotage.database.historique.HistoriqueDatabaseService;
import pilotage.database.planning.PlanningCyclesDatabaseService;
import pilotage.database.planning.PlanningEquipeDatabaseService;
import pilotage.database.planning.PlanningEquipePiloteDatabaseService;
import pilotage.database.users.management.UsersDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Planning_Cycle;
import pilotage.metier.Planning_Equipe_Pilote;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;
import pilotage.service.date.DateService;


public class CreatePlanningEquipesAction extends AbstractAction {
	
	private static final long serialVersionUID = -1299589526541605339L;
	
	public static final String CYCLE 	  = "cycle";
	public static final String PILOTE     = "pilote";
	public static final String ID   	  = "id";
	public static final String DATE_DEBUT = "dateDebut";
	public static final String DATE_FIN   = "dateFin";
	
	private String nomEquipe;
	private List<Map<String, String>> mapPilote;
	private List<Map<String, String>> mapCycle;
	private List<Users> listPilote;
	private List<Planning_Cycle> listCycle;

	public String getNomEquipe() {
		return nomEquipe;
	}

	public void setNomEquipe(String nomEquipe) {
		this.nomEquipe = nomEquipe;
	}

	public List<Map<String, String>> getMapPilote() {
		return mapPilote;
	}

	public void setMapPilote(List<Map<String, String>> mapPilote) {
		this.mapPilote = mapPilote;
	}

	public List<Map<String, String>> getMapCycle() {
		return mapCycle;
	}

	public void setMapCycle(List<Map<String, String>> mapCycle) {
		this.mapCycle = mapCycle;
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

	@Override
	protected boolean validateMetier() {
		try{
			if(PlanningEquipeDatabaseService.exists(null, nomEquipe)){
				error = MessageFormat.format(getText("planning.equipe.creation.existe.deja"), nomEquipe);
				prepareRedirectToCreationPage();
				return false;
			} else {
				HttpServletRequest request = ServletActionContext.getRequest();

				mapPilote = new ArrayList<Map<String,String>>();
				getListPilote(request, mapPilote);
				for (Map<String, String> pilote : mapPilote) {
					Users u = UsersDatabaseService.get(Integer.parseInt(pilote.get(PILOTE)));
					List<Planning_Equipe_Pilote> listPEP = PlanningEquipePiloteDatabaseService.getListEquipeByPilote(u);
					for (Planning_Equipe_Pilote pep : listPEP) {
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
							prepareRedirectToCreationPage();
							return false;
						}
					}
				}
			}
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Création d'une équipe de planning - ", e);
			return false;
		}
		return true;
	}

	@Override
	protected String executeMetier() {
		try{
			HttpServletRequest request = ServletActionContext.getRequest();
			
			mapCycle = new ArrayList<Map<String,String>>();
			getListCycle(request, mapCycle);
			
			mapPilote = new ArrayList<Map<String,String>>();
			getListPilote(request, mapPilote);
			
			PlanningEquipeDatabaseService.create(nomEquipe, mapPilote, mapCycle);
			info = MessageFormat.format(getText("planning.equipe.creation.valide"), new Object[]{nomEquipe});
			HistoriqueDatabaseService.create(null, MessageFormat.format(getText("historique.planning.equipe.creation"), new Object[]{nomEquipe,PlanningEquipeDatabaseService.getId(nomEquipe)}), (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_PLANNING);
			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Création d'une équipe de planning - ", e);
			prepareRedirectToCreationPage();
			return ERROR;
		}
	}
	
	public static void getListCycle(HttpServletRequest request, List<Map<String, String>> listCycle) {
		int i = 0;
		while(request.getParameter("cycle" + i) != null){
			Map<String, String> cy = new HashMap<String, String>();
			cy.put(CYCLE, request.getParameter("cycle"+i));
			cy.put(ID, request.getParameter("cycleId"+i));
			cy.put(DATE_DEBUT, request.getParameter("dateDebutC" + i));
			cy.put(DATE_FIN, request.getParameter("dateFinC" + i));
			listCycle.add(cy);
			++i;
		}
	}
	
	public static void getListPilote(HttpServletRequest request, List<Map<String, String>> listPilote) {
		int i = 0;
		while(request.getParameter("pilote" + i) != null){
			Map<String, String> pilote = new HashMap<String, String>();
			pilote.put(PILOTE, request.getParameter("pilote"+i));
			pilote.put(ID, request.getParameter("piloteId"+i));
			pilote.put(DATE_DEBUT, request.getParameter("dateDebutP" + i));
			pilote.put(DATE_FIN, request.getParameter("dateFinP" + i));
			listPilote.add(pilote);
			++i;
		}
	}
	
	private void prepareRedirectToCreationPage(){
		HttpServletRequest request = ServletActionContext.getRequest();
		
		mapCycle = new ArrayList<Map<String,String>>();
		getListCycle(request, mapCycle);
		
		mapPilote = new ArrayList<Map<String,String>>();
		getListPilote(request, mapPilote);
		
		List<Planning_Equipe_Pilote> listPiloteEquipe = PlanningEquipePiloteDatabaseService.getAll();
		List<Integer> listIdPilote = new ArrayList<Integer>();
		for (Planning_Equipe_Pilote pep : listPiloteEquipe) {
			listIdPilote.add(pep.getIdUser().getId());
		}
		listPilote = UsersDatabaseService.getAllExcept(listIdPilote, null, true);
		listCycle = PlanningCyclesDatabaseService.getAll();
	}
}