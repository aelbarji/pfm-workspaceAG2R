package pilotage.planning.planning;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
import pilotage.service.color.ColorService;
import pilotage.service.constants.PilotageConstants;
import pilotage.service.date.DateService;

public class ShowPlanningSemaineAction extends AbstractAction {

	private static final long serialVersionUID = 2800115204815595744L;
	
	protected Map<Integer, String> mapJour;
	protected Map<Integer, Date> mapJourDate;
	protected List<Map<String, String>> mapSemaine;
	protected String selectedDateStr;
	protected Date selectedDate;
	protected Map<Integer, String> mapNumSemaine;
	protected Map<String, String> mapAnnee;
	protected Planning_Cycle_Equipe cycleEnCour;
	
	protected String selectedSemaine;
	protected String selectedAnnee;
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

	public List<Planning_Vacation> getListPlanningVacation() {
		return listPlanningVacation;
	}

	public void setListPlanningVacation(List<Planning_Vacation> listPlanningVacation) {
		this.listPlanningVacation = listPlanningVacation;
	}

	public Planning_Cycle_Equipe getCycleEnCour() {
		return cycleEnCour;
	}

	public void setCycleEnCour(Planning_Cycle_Equipe cycleEnCour) {
		this.cycleEnCour = cycleEnCour;
	}

	public Map<Integer, String> getMapNumSemaine() {
		return mapNumSemaine;
	}

	public void setMapNumSemaine(Map<Integer, String> mapNumSemaine) {
		this.mapNumSemaine = mapNumSemaine;
	}

	public Map<String, String> getMapAnnee() {
		return mapAnnee;
	}

	public void setMapAnnee(Map<String, String> mapAnnee) {
		this.mapAnnee = mapAnnee;
	}

	public String getSelectedSemaine() {
		return selectedSemaine;
	}

	public void setSelectedSemaine(String selectedSemaine) {
		this.selectedSemaine = selectedSemaine;
	}

	public String getSelectedAnnee() {
		return selectedAnnee;
	}

	public void setSelectedAnnee(String selectedAnnee) {
		this.selectedAnnee = selectedAnnee;
	}

	// equipeId avec le pilote qui a une date de fin equipe 
	protected Map<Integer, Planning_Equipe_Pilote> piloteDateFinEquipe;

	public Map<Integer, String> getMapJour() {
		return mapJour;
	}

	public void setMapJour(Map<Integer, String> mapJour) {
		this.mapJour = mapJour;
	}

	public Map<Integer, Date> getMapJourDate() {
		return mapJourDate;
	}

	public void setMapJourDate(Map<Integer, Date> mapJourDate) {
		this.mapJourDate = mapJourDate;
	}

	public Map<Integer, Planning_Equipe_Pilote> getPiloteDateFinEquipe() {
		return piloteDateFinEquipe;
	}

	public void setPiloteDateFinEquipe(
			Map<Integer, Planning_Equipe_Pilote> piloteDateFinEquipe) {
		this.piloteDateFinEquipe = piloteDateFinEquipe;
	}
	
	public List<Map<String, String>> getMapSemaine() {
		return mapSemaine;
	}

	public void setMapSemaine(List<Map<String, String>> mapSemaine) {
		this.mapSemaine = mapSemaine;
	}

	public String getSelectedDateStr() {
		return selectedDateStr;
	}

	public void setSelectedDateStr(String selectedDateStr) {
		this.selectedDateStr = selectedDateStr;
	}

	public Date getSelectedDate() {
		return selectedDate;
	}

	public void setSelectedDate(Date selectedDate) {
		this.selectedDate = selectedDate;
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
			
			//Initialisation des dates de la semaine
			mapJour = new HashMap<Integer,String>();
			mapJourDate = new HashMap<Integer, Date>();
			Date dateDebutSemaine = DateService.getWeekStart(selectedDate);
			Date dateFinSemaine = DateService.getWeekEnd(selectedDate);
			selectedDate = dateDebutSemaine;
			for (int i=1; i<8;i++) {
				mapJour.put(i, dateToStr(selectedDate));
				mapJourDate.put(i, selectedDate);
				selectedDate = DateService.addDays(selectedDate, 1);
			}
			selectedDate = dateDebutSemaine;
			
			//Récupération du planning de la semaine pour chaque pilotage
			mapSemaine = new ArrayList<Map<String, String>>();
			List<Map<String, String>> mapSemaineAvecPlanning = new ArrayList<Map<String, String>>();
			List<Users> listUsers = null;
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
			
			for (Users u : listUsers) {
				isModifPonct = false;
				hasEquipe = false;
				
				//initialisation de la ligne
				Map<String, String> map = new HashMap<String, String>();
				map.put("pilote", u.getNom() + " " + u.getPrenom());
				map.put("piloteID", u.getId().toString());
				map.put("lundiVacationID", "");
				map.put("mardiVacationID", "");
				map.put("mercrediVacationID", "");
				map.put("jeudiVacationID", "");
				map.put("vendrediVacationID", "");
				map.put("samediVacationID", "");
				map.put("dimancheVacationID", "");
				map.put("lundi", "FFFFFF");
				map.put("mardi", "FFFFFF");
				map.put("mercredi", "FFFFFF");
				map.put("jeudi", "FFFFFF");
				map.put("vendredi", "FFFFFF");
				map.put("samedi", "FFFFFF");
				map.put("dimanche", "FFFFFF");
				map.put("lundiText", "");
				map.put("mardiText", "");
				map.put("mercrediText", "");
				map.put("jeudiText", "");
				map.put("vendrediText", "");
				map.put("samediText", "");
				map.put("dimancheText", "");
				
				//récupération de(s) équipe(s)
				List<Planning_Equipe_Pilote> pepList = PlanningEquipePiloteDatabaseService.getListEquipeByPilote(u);
				for(Planning_Equipe_Pilote pep : pepList){
					Date pepDateDebut = pep.getDateDebut();
					Date pepDateFin = pep.getDateFin();
					if((pepDateFin == null || pepDateFin.after(dateDebutSemaine) || pepDateFin.equals(dateDebutSemaine))
							&& (pepDateDebut.before(dateFinSemaine) || pepDateDebut.equals(dateFinSemaine))){
						//récupération de(s) cycle(s)
						List<Planning_Cycle_Equipe> pceList = PlanningCycleEquipeDatabaseService.getCycleByEquipe(pep.getIdNomEquipe().getId(), dateDebutSemaine, dateFinSemaine, pepDateDebut, pepDateFin);
						for(Planning_Cycle_Equipe pce : pceList){
							hasEquipe = true;
							Date dateDebutCycle = pce.getDateDebut();
							Date dateFinCycle = pce.getDateFin();
							
							//récupération de(s) semaines de cycle
							List<Planning_Semaine> psList = PlanningSemaineDatabaseService.getSemaineByCycle(pce.getIdNomCycle().getId());
							
							//détermination de la semaine du cycle à appliquer
							int numeroSemaine = 1;
							Date dateTmp = DateService.getWeekStart(dateDebutCycle);
							while(dateTmp.before(dateDebutSemaine)){
								dateTmp = DateService.addDays(dateTmp, 7);
								numeroSemaine++;
								if(numeroSemaine > psList.size()){
									numeroSemaine -= psList.size();
								}
							}
							
							//récupération de la bonne semaine et des vacations pour les jours qu'il faut
							Planning_Semaine ps = null;
							for(Planning_Semaine psTmp : psList){
								if(psTmp.getNumeroSemaine().equals(numeroSemaine)){
									ps = psTmp;
									break;
								}
							}
							for(int i = 0; i < 8; ++i){
								Date jour = DateService.addDays(dateDebutSemaine, i);
								//si je jour est bien dans la tranche d'affectation du pilote à l'équipe et dans la tranche d'affectation du cycle à l'équipe
								if(!jour.before(pepDateDebut) && !jour.before(dateDebutCycle) && (pepDateFin == null || !jour.after(pepDateFin)) && (dateFinCycle == null || !jour.after(dateFinCycle))){
									switch(i){
									case 0:
										map.put("equipeLundi", pep.getIdNomEquipe().getNomEquipe());
										map.put("lundi", ps.getLundi().getCodeCouleur());
										map.put("lundiVacationID", ps.getLundi().getId().toString());
										map.put("lundiText", ps.getLundi().getLibelle());
										break;
									case 1:
										map.put("equipeMardi", pep.getIdNomEquipe().getNomEquipe());
										map.put("mardi", ps.getMardi().getCodeCouleur());
										map.put("mardiVacationID", ps.getMardi().getId().toString());
										map.put("mardiText", ps.getMardi().getLibelle());
										break;
									case 2:
										map.put("equipeMercredi", pep.getIdNomEquipe().getNomEquipe());
										map.put("mercredi", ps.getMercredi().getCodeCouleur());
										map.put("mercrediVacationID", ps.getMercredi().getId().toString());
										map.put("mercrediText", ps.getMercredi().getLibelle());
										break;
									case 3:
										map.put("equipeJeudi", pep.getIdNomEquipe().getNomEquipe());
										map.put("jeudi", ps.getJeudi().getCodeCouleur());
										map.put("jeudiVacationID", ps.getJeudi().getId().toString());
										map.put("jeudiText", ps.getJeudi().getLibelle());
										break;
									case 4:
										map.put("equipeVendredi", pep.getIdNomEquipe().getNomEquipe());
										map.put("vendredi", ps.getVendredi().getCodeCouleur());
										map.put("vendrediVacationID", ps.getVendredi().getId().toString());
										map.put("vendrediText", ps.getVendredi().getLibelle());
										break;
									case 5: 
										map.put("equipeSamedi", pep.getIdNomEquipe().getNomEquipe());
										map.put("samedi", ps.getSamedi().getCodeCouleur());
										map.put("samediVacationID", ps.getSamedi().getId().toString());
										map.put("samediText", ps.getSamedi().getLibelle());
										break;
									case 6:
										map.put("equipeDimanche", pep.getIdNomEquipe().getNomEquipe());
										map.put("dimanche", ps.getDimanche().getCodeCouleur());
										map.put("dimancheVacationID", ps.getDimanche().getId().toString());
										map.put("dimancheText", ps.getDimanche().getLibelle());
										break;
									default:
										break;
									}
								}
							}
						}
					}
				}
				
				//récupération de(s) modifs ponctuelles
				List<Planning_Modif_Ponctuelle> pmpList = PlanningModifPonctuelleDatabaseService.getByUserAndDate(u, dateDebutSemaine, dateFinSemaine);
				for (Planning_Modif_Ponctuelle pmp : pmpList) {
					selectedDate = dateDebutSemaine;
					for (int i = 1; i < 8; i++) {
						if ((selectedDate.after(pmp.getDateDebut()) || selectedDate.equals(pmp.getDateDebut())) && (selectedDate.before(pmp.getDateFin()) || selectedDate.equals(pmp.getDateFin()))) {
							isModifPonct = true;
							if (DateService.getJour(selectedDate)==PilotageConstants.LUNDI) {
								map.put("lundi",pmp.getIdVacation().getCodeCouleur());
								map.put("lundiVacationID",pmp.getIdVacation().getId().toString());
								map.put("lundiText",pmp.getIdVacation().getLibelle());
							} else if (DateService.getJour(selectedDate)==PilotageConstants.MARDI) {
								map.put("mardi",pmp.getIdVacation().getCodeCouleur());
								map.put("mardiVacationID",pmp.getIdVacation().getId().toString());
								map.put("mardiText",pmp.getIdVacation().getLibelle());
							} else if (DateService.getJour(selectedDate)==PilotageConstants.MERCREDI) {
								map.put("mercredi",pmp.getIdVacation().getCodeCouleur());
								map.put("mercrediVacationID",pmp.getIdVacation().getId().toString());
								map.put("mercrediText",pmp.getIdVacation().getLibelle());
							} else if (DateService.getJour(selectedDate)==PilotageConstants.JEUDI) {
								map.put("jeudi",pmp.getIdVacation().getCodeCouleur());
								map.put("jeudiVacationID",pmp.getIdVacation().getId().toString());
								map.put("jeudiText",pmp.getIdVacation().getLibelle());
							} else if (DateService.getJour(selectedDate)==PilotageConstants.VENDREDI) {
								map.put("vendredi",pmp.getIdVacation().getCodeCouleur());
								map.put("vendrediVacationID",pmp.getIdVacation().getId().toString());
								map.put("vendrediText",pmp.getIdVacation().getLibelle());
							} else if (DateService.getJour(selectedDate)==PilotageConstants.SAMEDI) {
								map.put("samedi",pmp.getIdVacation().getCodeCouleur());
								map.put("samediVacationID",pmp.getIdVacation().getId().toString());
								map.put("samediText",pmp.getIdVacation().getLibelle());
							} else if (DateService.getJour(selectedDate)==PilotageConstants.DIMANCHE) {
								map.put("dimanche",pmp.getIdVacation().getCodeCouleur());
								map.put("dimancheVacationID", pmp.getIdVacation().getId().toString());
								map.put("dimancheText",pmp.getIdVacation().getLibelle());
							}
						}
						selectedDate = DateService.addDays(selectedDate, 1);
					}
					selectedDate = dateDebutSemaine;
				}
				
				//couleur du texte
				map.put("lundiDark", ColorService.isDark(map.get("lundi")).toString());
				map.put("mardiDark", ColorService.isDark(map.get("mardi")).toString());
				map.put("mercrediDark", ColorService.isDark(map.get("mercredi")).toString());
				map.put("jeudiDark", ColorService.isDark(map.get("jeudi")).toString());
				map.put("vendrediDark", ColorService.isDark(map.get("vendredi")).toString());
				map.put("samediDark", ColorService.isDark(map.get("samedi")).toString());
				map.put("dimancheDark", ColorService.isDark(map.get("dimanche")).toString());
				
				//détermination de la 1ere equipe dont le pilote fait parti
				if(map.get("equipeLundi") != null){
					map.put("equipe", map.get("equipeLundi"));
				}
				else if(map.get("equipeMardi") != null){
					map.put("equipe", map.get("equipeMardi"));
				}
				else if(map.get("equipeMercredi") != null){
					map.put("equipe", map.get("equipeMercredi"));
				}
				else if(map.get("equipeJeudi") != null){
					map.put("equipe", map.get("equipeJeudi"));
				}
				else if(map.get("equipeVendredi") != null){
					map.put("equipe", map.get("equipeVendredi"));
				}
				else if(map.get("equipeSamedi") != null){
					map.put("equipe", map.get("equipeSamedi"));
				}
				else if(map.get("equipeDimanche") != null){
					map.put("equipe", map.get("equipeDimanche"));
				}
				
				mapSemaine.add(map);
				if (isModifPonct || hasEquipe){
					mapSemaineAvecPlanning.add(map);
				}
			}
			mapSemaine = mapSemaineAvecPlanning;
			
			List<Map<String, String>> mapTmp = new ArrayList<Map<String,String>>();
			mapTmp.addAll(mapSemaine);
			mapSemaine.clear();
			for(Planning_Nom_Equipe pne : PlanningEquipeDatabaseService.getAll()){
				for(Map<String, String> ligneUser : mapTmp){
					if(pne.getNomEquipe().equals(ligneUser.get("equipe"))){
						mapSemaine.add(ligneUser);
					}
				}
			}
			for(Map<String, String> ligneUser : mapTmp){
				if(ligneUser.get("equipe") == null){
					mapSemaine.add(ligneUser);
				}
			}
			
			//Set value selected des combo box de date et semaine
			selectedAnnee = DateService.dateToStr(dateDebutSemaine, DateService.p4).substring(0, 4);
			selectedSemaine = DateService.getWeekOfYear(dateDebutSemaine).toString();
			
			//Initialisation de la combobox des semaines
			mapNumSemaine = new LinkedHashMap<Integer, String>();
			String debutAnneeStr = "01/01/" + selectedAnnee;
			Date debutAnnee = new SimpleDateFormat("dd/MM/yyyy").parse(debutAnneeStr);
			for (int i=1; i<53; i++) {
				Date lundi = DateService.getMonday(i, debutAnnee);
				Date dimanche = DateService.getWeekEnd(lundi);
				mapNumSemaine.put(i, "Semaine " + i + " - " + DateService.dateToStr(lundi, DateService.p1) + " au " + DateService.dateToStr(dimanche, DateService.p1));
			}
			
			//Initialisation de la combobox des années
			mapAnnee = new HashMap<String, String>();
			Date premier = PlanningCycleEquipeDatabaseService.getFirstDate();
			if(premier == null)
				premier = new Date();
			String precedentAnnee = new SimpleDateFormat("yyyy").format(DateService.addYear(premier, -1));
			String currentAnnee = new SimpleDateFormat("yyyy").format(new Date());
			for (int i=Integer.parseInt(precedentAnnee); i<=Integer.parseInt(currentAnnee)+1; i++) {
				mapAnnee.put(String.valueOf(i), String.valueOf(i));
			}
			
			//légende du planning
			listPlanningVacation = PlanningVacationsDatabaseService.getAll();
			
			//mise en session de mapJour et mapSemaine afin de les recuperer lors de l'export du planning eventuellement
			
			session.put(PilotageConstants.PLANNING_LISTE_JOUR_SEMAINE, mapJour);
			session.put(PilotageConstants.PLANNING_LISTE_SEMAINE_PILOTE, mapSemaine);
			session.put(PilotageConstants.PLANNING_NUM_SEMAINE,mapNumSemaine);
			session.put(PilotageConstants.PLANNING_ANNEE, mapAnnee);
			session.put(PilotageConstants.PLANNING_SELECTED_NUM_SEMAINE,selectedSemaine);
			session.put(PilotageConstants.PLANNING_SELECTED_ANNEE, selectedAnnee);
			session.put(PilotageConstants.PLANNING_SELECT_COULEUR, listPlanningVacation);
			
			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Planning semaine - ", e);
			mapJour = new HashMap<Integer,String>();
			mapSemaine = new ArrayList<Map<String, String>>();
			return ERROR;
		}
	}

	protected void initialDate() throws ParseException{
		if(selectedAnnee != null && selectedSemaine != null){
			selectedDate = DateService.getMonday(Integer.parseInt(selectedSemaine),  DateService.strToDate("01/01/" + selectedAnnee));
		}
		else if (selectedDateStr == null || "".equals(selectedDateStr)) {
			selectedDate = DateService.getTodayWithoutHour();
		} else {
			selectedDate = DateService.strToDate(selectedDateStr.substring(0, 10));
		}
		//selectedDate = DateService.strToDateTime(DateService.dateToStr(selectedDate, DateService.p1), "12:00:00");
	}
	
	public static String dateToStr(Date selectedDate) {
		String jour = new SimpleDateFormat("dd").format(selectedDate);
		String mois = new SimpleDateFormat("MM").format(selectedDate);
		String annee = new SimpleDateFormat("yyyy").format(selectedDate);
		if ("01".equals(mois)) {
			mois = "Janvier";
		}
		if ("02".equals(mois)) {
			mois = "Février";
		}
		if ("03".equals(mois)) {
			mois = "Mars";
		}
		if ("04".equals(mois)) {
			mois = "Avril";
		}
		if ("05".equals(mois)) {
			mois = "Mai";
		}
		if ("06".equals(mois)) {
			mois = "Juin";
		}
		if ("07".equals(mois)) {
			mois = "Juillet";
		}
		if ("08".equals(mois)) {
			mois = "Août";
		}
		if ("09".equals(mois)) {
			mois = "Septembre";
		}
		if ("10".equals(mois)) {
			mois = "Octobre";
		}
		if ("11".equals(mois)) {
			mois = "Novembre";
		}
		if ("12".equals(mois)) {
			mois = "Décembre";
		}
		return jour+" "+mois+" "+annee;
	}
}
