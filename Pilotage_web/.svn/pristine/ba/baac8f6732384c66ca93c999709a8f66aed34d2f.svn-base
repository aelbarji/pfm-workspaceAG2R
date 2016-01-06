package pilotage.planning.planning;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.LocalDate;

import pilotage.database.planning.PlanningCycleEquipeDatabaseService;
import pilotage.database.planning.PlanningCyclesDatabaseService;
import pilotage.database.planning.PlanningEquipeDatabaseService;
import pilotage.database.planning.PlanningEquipePiloteDatabaseService;
import pilotage.database.planning.PlanningModifPonctuelleDatabaseService;
import pilotage.database.planning.PlanningSemaineDatabaseService;
import pilotage.database.planning.PlanningVacationsDatabaseService;
import pilotage.database.users.management.UsersDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Planning_Cycle;
import pilotage.metier.Planning_Cycle_Equipe;
import pilotage.metier.Planning_Equipe_Pilote;
import pilotage.metier.Planning_Modif_Ponctuelle;
import pilotage.metier.Planning_Nom_Equipe;
import pilotage.metier.Planning_Semaine;
import pilotage.metier.Planning_Vacation;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;
import pilotage.service.date.DateService;


public class ShowPlanningMoisPiloteAction extends AbstractAction {

	private static final long serialVersionUID = -3843321001859989628L;
	protected Date selectedDate;
	protected String selectedDateStr;
	
	protected Map<Integer, Date> mapJourDate;
	protected int nbDayOfTheMonth;
	protected Map<Integer, Map<Integer, Map<String, String>>> mapMoisVacation;
	protected List<Users> listUsers;
	protected List<Integer> listJour;
	protected List<Planning_Vacation> listPlanningVacation;
	protected boolean isModifPonct;
	protected boolean hasEquipe;
	protected List<Planning_Cycle> listCycle;
	protected String cycle;
	
	public List<Planning_Cycle> getListCycle() {
		return listCycle;
	}

	public void setListCycle(List<Planning_Cycle> listCycle) {
		this.listCycle = listCycle;
	}

	public String getCycle() {
		return cycle;
	}

	public void setCycle(String cycle) {
		this.cycle = cycle;
	}

	public Date getSelectedDate() {
		return selectedDate;
	}

	public void setSelectedDate(Date selectedDate) {
		this.selectedDate = selectedDate;
	}

	public String getSelectedDateStr() {
		return selectedDateStr;
	}

	public void setSelectedDateStr(String selectedDateStr) {
		this.selectedDateStr = selectedDateStr;
	}

	public Map<Integer, Date> getMapJourDate() {
		return mapJourDate;
	}

	public void setMapJourDate(Map<Integer, Date> mapJourDate) {
		this.mapJourDate = mapJourDate;
	}

	public int getNbDayOfTheMonth() {
		return nbDayOfTheMonth;
	}

	public void setNbDayOfTheMonth(int nbDayOfTheMonth) {
		this.nbDayOfTheMonth = nbDayOfTheMonth;
	}

	public Map<Integer, Map<Integer, Map<String, String>>> getMapMoisVacation() {
		return mapMoisVacation;
	}

	public void setMapMoisVacation(Map<Integer, Map<Integer, Map<String, String>>> mapMoisVacation) {
		this.mapMoisVacation = mapMoisVacation;
	}

	public List<Users> getListUsers() {
		return listUsers;
	}

	public void setListUsers(List<Users> listUsers) {
		this.listUsers = listUsers;
	}

	public List<Integer> getListJour() {
		return listJour;
	}

	public void setListJour(List<Integer> listJour) {
		this.listJour = listJour;
	}

	public List<Planning_Vacation> getListPlanningVacation() {
		return listPlanningVacation;
	}

	public void setListPlanningVacation(List<Planning_Vacation> listPlanningVacation) {
		this.listPlanningVacation = listPlanningVacation;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected String executeMetier() {
		try {
			//récupération de la date concernée
			initialDate();
			selectedDateStr = DateService.dateToStr(selectedDate, DateService.p1 + " " + DateService.pt2);
			
			//initialisation du filtre cycle
			listCycle = PlanningCyclesDatabaseService.getAll();
			
			//Initialisation des dates du mois
			listJour = new ArrayList<Integer>();
			mapJourDate = new HashMap<Integer, Date>();
			Date dateDebutMois = DateService.getFirstDayOfMonth(selectedDate);
			Date dateFinMois = DateService.getLastDayOfMonth(selectedDate);
			nbDayOfTheMonth = DateService.getNbDaysInMonth(selectedDate);
			selectedDate = dateDebutMois;
			for (int i = 1; i <= nbDayOfTheMonth; i++) {
				listJour.add(i);
				mapJourDate.put(i, selectedDate);
				selectedDate = DateService.addDays(selectedDate, 1);
			}
			selectedDate = dateDebutMois;

			//initialisation des affectations des pilotes par equipe pour le classement par equipe
			List<Integer> piloteDejaDansEquipe = new ArrayList<Integer>();
			Map<Integer, List<Integer>> mapPilotesParEquipe = new LinkedHashMap<Integer, List<Integer>>();
			for(Planning_Nom_Equipe pne : PlanningEquipeDatabaseService.getAll()){
				mapPilotesParEquipe.put(pne.getId(), new ArrayList<Integer>());
			}
			
			//Récupération du planning du mois pour chaque pilotage
			mapMoisVacation = new LinkedHashMap<Integer, Map<Integer, Map<String, String>>>();
			
			//ajout du filtre Cycle
			if (cycle != null && !"".equals(cycle)){
				int idCycle = Integer.parseInt(cycle);
				List<Planning_Cycle_Equipe> listEquipe = PlanningCycleEquipeDatabaseService.getEquipesByCycle(idCycle);
				listUsers = new ArrayList<Users>();
				for (Planning_Cycle_Equipe pce : listEquipe){
					List<Planning_Equipe_Pilote> listPiloteEquipe = PlanningEquipePiloteDatabaseService.getPilotesByEquipeNomAsc(pce.getIdNomEquipe().getId());
					for (Planning_Equipe_Pilote pep : listPiloteEquipe){
						if (!listUsers.contains(pep.getIdUser()))
							listUsers.add(pep.getIdUser());
					}
				}
				
			} else {
				listUsers = UsersDatabaseService.getPiloteList();
			}
			List<Users> listUsersAvecPlanning = new ArrayList<Users>();
			for (Users u : listUsers) {
				Map<Integer, Map<String, String>> mapJoursPilote = new HashMap<Integer, Map<String,String>>();
				isModifPonct = false;
				hasEquipe = false;
				
				//initialisation de la ligne
				for(Integer i = 1; i <= nbDayOfTheMonth; ++i){
					Map<String, String> map = new HashMap<String, String>();
					map.put("vacationID", "");
					map.put("vacationText", "");
					map.put("couleur", "");
					
					mapJoursPilote.put(i, map);
				}
				mapMoisVacation.put(u.getId(), mapJoursPilote);

				//récupération de(s) équipe(s)
				List<Planning_Equipe_Pilote> pepList = PlanningEquipePiloteDatabaseService.getListEquipeByPilote(u);
				
				for(Planning_Equipe_Pilote pep : pepList){
					Date pepDateDebut = pep.getDateDebut();
					Date pepDateFin = pep.getDateFin();
					if((pepDateFin == null || pepDateFin.after(dateDebutMois) || pepDateFin.equals(dateDebutMois))
							&& (pepDateDebut.before(dateFinMois) || pepDateDebut.equals(dateFinMois))){
						//récupération de(s) cycle(s)
						List<Planning_Cycle_Equipe> pceList = PlanningCycleEquipeDatabaseService.getCycleByEquipe(pep.getIdNomEquipe().getId(), dateDebutMois, dateFinMois, pepDateDebut, pepDateFin);
						
						for(Planning_Cycle_Equipe pce : pceList){
							hasEquipe = true;
							Date dateDebutCycle = pce.getDateDebut();
							Date dateFinCycle = pce.getDateFin();
							
							//récupération de(s) semaines de cycle
							List<Planning_Semaine> psList = PlanningSemaineDatabaseService.getSemaineByCycle(pce.getIdNomCycle().getId());
							Integer nbMaxSem = 0;
							for(Planning_Semaine ps : psList)
								if(ps.getNumeroSemaine() > nbMaxSem)
									nbMaxSem = ps.getNumeroSemaine();
							
							//détermination de la semaine du cycle de commencement du mois 
							int numeroSemaine = 1;
							Date dateTmp = DateService.getWeekEnd(dateDebutCycle);
							while(dateTmp.before(dateDebutMois)){
								dateTmp = DateService.addDays(dateTmp, 7);
								numeroSemaine++;
								if(numeroSemaine > nbMaxSem){
									numeroSemaine -= nbMaxSem;
								}
							}
							
							
							//récupération de la bonne semaine et des vacations pour les jours qu'il faut
							Planning_Semaine ps = null;
							
							List<Planning_Semaine> psL = new ArrayList<Planning_Semaine>();
							for(Planning_Semaine psTmp : psList){
								if(psTmp.getNumeroSemaine().equals(numeroSemaine)){
									psL.add(psTmp);
								}
							}

							LocalDate psDateDeb;
							LocalDate psDateFin;
							boolean isEqualDate = false;
							for(int i = 0; i < nbDayOfTheMonth; ++i){
								Date jour = DateService.addDays(dateDebutMois, i);
								psDateDeb = null;

								// choix de la semaine en fonction de la date
								LocalDate jourDT = new LocalDate(jour);

								for(Planning_Semaine ps1 : psL){
									isEqualDate = false;
									psDateFin = null;
									psDateDeb = ps1.getDateDeb().toLocalDate();
									
									if(ps1.getDateFin() != null){
										psDateFin = ps1.getDateFin().toLocalDate();
										if(psDateFin.equals(psDateDeb)){
											isEqualDate = true;
										}
									}
									
									 if(!isEqualDate && (jourDT.equals(psDateDeb) || jourDT.isAfter(psDateDeb)) && (psDateFin == null || jourDT.isBefore(psDateFin))){
										 ps = ps1;
										 break;
									 }
								}		

						
								//si le jour est bien dans la tranche d'affectation du pilote à l'équipe et dans la tranche d'affectation du cycle à l'équipe
								if(!jour.before(pepDateDebut) && !jour.before(dateDebutCycle) && (pepDateFin == null || !jour.after(pepDateFin)) && (dateFinCycle == null || !jour.after(dateFinCycle))){
									Integer dayOfMonth = DateService.getDayOfMonth(jour);
									Integer dayOfWeek = DateService.getJour(jour);
									
									if(!piloteDejaDansEquipe.contains(u.getId())){
										mapPilotesParEquipe.get(pep.getIdNomEquipe().getId()).add(u.getId());
										piloteDejaDansEquipe.add(u.getId());
									}
									
									if(PilotageConstants.LUNDI.equals(dayOfWeek)){
										mapJoursPilote.get(dayOfMonth).put("vacationID", ps.getLundi().getId().toString());
										mapJoursPilote.get(dayOfMonth).put("vacationText", ps.getLundi().getLibelle());
										mapJoursPilote.get(dayOfMonth).put("couleur", ps.getLundi().getCodeCouleur());
									}
									else if(PilotageConstants.MARDI.equals(dayOfWeek)){
										mapJoursPilote.get(dayOfMonth).put("vacationID", ps.getMardi().getId().toString());
										mapJoursPilote.get(dayOfMonth).put("vacationText", ps.getMardi().getLibelle());
										mapJoursPilote.get(dayOfMonth).put("couleur", ps.getMardi().getCodeCouleur());
									}
									else if(PilotageConstants.MERCREDI.equals(dayOfWeek)){
										mapJoursPilote.get(dayOfMonth).put("vacationID", ps.getMercredi().getId().toString());
										mapJoursPilote.get(dayOfMonth).put("vacationText", ps.getMercredi().getLibelle());
										mapJoursPilote.get(dayOfMonth).put("couleur", ps.getMercredi().getCodeCouleur());
									}
									else if(PilotageConstants.JEUDI.equals(dayOfWeek)){
										mapJoursPilote.get(dayOfMonth).put("vacationID", ps.getJeudi().getId().toString());
										mapJoursPilote.get(dayOfMonth).put("vacationText", ps.getJeudi().getLibelle());
										mapJoursPilote.get(dayOfMonth).put("couleur", ps.getJeudi().getCodeCouleur());
									}
									else if(PilotageConstants.VENDREDI.equals(dayOfWeek)){
										mapJoursPilote.get(dayOfMonth).put("vacationID", ps.getVendredi().getId().toString());
										mapJoursPilote.get(dayOfMonth).put("vacationText", ps.getVendredi().getLibelle());
										mapJoursPilote.get(dayOfMonth).put("couleur", ps.getVendredi().getCodeCouleur());
									}
									else if(PilotageConstants.SAMEDI.equals(dayOfWeek)){
										mapJoursPilote.get(dayOfMonth).put("vacationID", ps.getSamedi().getId().toString());
										mapJoursPilote.get(dayOfMonth).put("vacationText", ps.getSamedi().getLibelle());
										mapJoursPilote.get(dayOfMonth).put("couleur", ps.getSamedi().getCodeCouleur());
									}
									else if(PilotageConstants.DIMANCHE.equals(dayOfWeek)){
										mapJoursPilote.get(dayOfMonth).put("vacationID", ps.getDimanche().getId().toString());
										mapJoursPilote.get(dayOfMonth).put("vacationText", ps.getDimanche().getLibelle());
										mapJoursPilote.get(dayOfMonth).put("couleur", ps.getDimanche().getCodeCouleur());
									}
								}
								
								if(PilotageConstants.DIMANCHE.equals(DateService.getJour(jour))){
									if(numeroSemaine == nbMaxSem)
										numeroSemaine = 1;
									else
										numeroSemaine++;
									
									//recup de la semaine suivante dans ps
									psL.clear();
									for(Planning_Semaine psTmp : psList){
										if(psTmp.getNumeroSemaine().equals(numeroSemaine))
											psL.add(psTmp);
									}
								}
							}
						}
					}
				}

				//récupération de(s) modifs ponctuelles
				List<Planning_Modif_Ponctuelle> pmpList = PlanningModifPonctuelleDatabaseService.getByUserAndDate(u, dateDebutMois, dateFinMois);
				for (Planning_Modif_Ponctuelle pmp : pmpList) {
					selectedDate = dateDebutMois;
					Integer dayOfMonth = DateService.getDayOfMonth(selectedDate);
					
					for (int i = 1; i <= nbDayOfTheMonth; ++i) {
						if ((selectedDate.after(pmp.getDateDebut()) || selectedDate.equals(pmp.getDateDebut())) && (selectedDate.before(pmp.getDateFin()) || selectedDate.equals(pmp.getDateFin()))) {
							isModifPonct = true;
							mapJoursPilote.get(dayOfMonth).put("vacationID", pmp.getIdVacation().getId().toString());
							mapJoursPilote.get(dayOfMonth).put("vacationText", pmp.getIdVacation().getLibelle());
							mapJoursPilote.get(dayOfMonth).put("couleur", pmp.getIdVacation().getCodeCouleur());
							if(pmp.getTextCell() != null)
								mapJoursPilote.get(dayOfMonth).put("textCell", pmp.getTextCell());
							else mapJoursPilote.get(dayOfMonth).put("textCell", "");
						}
						selectedDate = DateService.addDays(selectedDate, 1);
						dayOfMonth = DateService.getDayOfMonth(selectedDate);
					}
					selectedDate = dateDebutMois;
				}
				if (isModifPonct || hasEquipe){
					listUsersAvecPlanning.add(u);
				}
			}
			
			//regroupement des users par equipe
			Map<Integer, Users> usersTmp = new HashMap<Integer, Users>();
			for(Users u1 : listUsersAvecPlanning){
				usersTmp.put(u1.getId(), u1);
			}
			listUsersAvecPlanning.clear();
			for(Planning_Nom_Equipe pne : PlanningEquipeDatabaseService.getAll()){
				for(Integer uid : mapPilotesParEquipe.get(pne.getId())){
					listUsersAvecPlanning.add(usersTmp.get(uid));
				}
			}
			for(Users u1 : usersTmp.values()){
				if(!listUsersAvecPlanning.contains(u1)){
					listUsersAvecPlanning.add(u1);
				}
			}
			listUsers = listUsersAvecPlanning;
			
			listPlanningVacation = PlanningVacationsDatabaseService.getAll();
			
			//mise en session de mapJoursPilote afin de les recuperer lors de l'export du planning mois pilote eventuellement
			
			session.put(PilotageConstants.PLANNING_LISTE_MOIS_PILOTE, mapMoisVacation);
			session.put(PilotageConstants.PLANNING_LISTE_USER, listUsers);
			session.put(PilotageConstants.PLANNING_SELECT_DATE, selectedDate);
			session.put(PilotageConstants.PLANNING_SELECT_COULEUR, listPlanningVacation);
			
			return OK;
		}

		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Planning semaine - ", e);
			mapMoisVacation = new HashMap<Integer, Map<Integer,Map<String,String>>>();
			return ERROR;
		}
	}

	protected void initialDate() throws ParseException{
		if (selectedDateStr == null || "".equals(selectedDateStr)) {
			selectedDate = DateService.getTodayWithoutHour();
		} else {
			if (selectedDateStr.length() >= 10 ) {
			selectedDate =DateService.strToDate(selectedDateStr.substring(0, 10));
				if (selectedDate == null) {
					error = getText("error.message.generique") + " : Saisie de la date incorrecte, date du jour prise en compte";
					selectedDate = DateService.getTodayWithoutHour();
				}
			} else {
				error = getText("error.message.generique") + " : Saisie de la date incorrecte, date du jour prise en compte";
				selectedDate = DateService.getTodayWithoutHour();
			}
		}
//		selectedDate = DateService.strToDateTime(DateService.dateToStr(selectedDate, DateService.p1), "12:00:00");
	}
}
