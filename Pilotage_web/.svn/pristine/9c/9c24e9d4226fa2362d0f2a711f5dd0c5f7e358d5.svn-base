package pilotage.planning.planning;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import pilotage.database.planning.PlanningCycleEquipeDatabaseService;
import pilotage.database.planning.PlanningEquipeDatabaseService;
import pilotage.database.planning.PlanningModifPonctuelleDatabaseService;
import pilotage.database.planning.PlanningSemaineDatabaseService;
import pilotage.database.planning.PlanningVacationsDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Planning_Cycle_Equipe;
import pilotage.metier.Planning_Modif_Ponctuelle;
import pilotage.metier.Planning_Nom_Equipe;
import pilotage.metier.Planning_Semaine;
import pilotage.metier.Planning_Vacation;
import pilotage.service.color.ColorService;
import pilotage.service.constants.PilotageConstants;
import pilotage.service.date.DateService;

public class ShowPlanningMoisAction extends AbstractAction {

	private static final long serialVersionUID = 2800115204815595744L;
	
	protected List<Planning_Nom_Equipe> listEquipe;
	protected String equipe;
	protected String selectedDateStr;
	protected Date selectedDate;
	protected Map<Integer,Integer> mapJour;
	protected Map<Integer,String> mapCouleur;
	protected Map<Integer,String> mapVacation;
	protected Map<Integer,String> mapDark;
	protected Integer[] days;
	protected List<String[]> listMois;
	protected Planning_Cycle_Equipe cycleEnCour;
	protected List<Planning_Vacation> listPlanningVacation;
	protected Map<Integer,Integer> mapJourVacation;
	

	public Map<Integer, Integer> getMapJourVacation() {
		return mapJourVacation;
	}

	public void setMapJourVacation(Map<Integer, Integer> mapJourVacation) {
		this.mapJourVacation = mapJourVacation;
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

	public List<Planning_Nom_Equipe> getListEquipe() {
		return listEquipe;
	}

	public void setListEquipe(List<Planning_Nom_Equipe> listEquipe) {
		this.listEquipe = listEquipe;
	}

	public String getEquipe() {
		return equipe;
	}

	public void setEquipe(String equipe) {
		this.equipe = equipe;
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

	public Map<Integer,Integer> getMapJour() {
		return mapJour;
	}

	public void setMapJour(Map<Integer,Integer> mapJour) {
		this.mapJour = mapJour;
	}

	public Map<Integer, String> getMapCouleur() {
		return mapCouleur;
	}

	public void setMapCouleur(Map<Integer, String> mapCouleur) {
		this.mapCouleur = mapCouleur;
	}

	public Map<Integer, String> getMapVacation() {
		return mapVacation;
	}

	public void setMapVacation(Map<Integer, String> mapVacation) {
		this.mapVacation = mapVacation;
	}

	public Map<Integer, String> getMapDark() {
		return mapDark;
	}

	public void setMapDark(Map<Integer, String> mapDark) {
		this.mapDark = mapDark;
	}

	public Integer[] getDays() {
		return days;
	}

	public void setDays(Integer[] days) {
		this.days = days;
	}

	public List<String[]> getListMois() {
		return listMois;
	}

	public void setListMois(List<String[]> listMois) {
		this.listMois = listMois;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected String executeMetier() {
		try {
			Planning_Nom_Equipe pnequip = null;
			if (equipe != null && !"".equals(equipe)){
				pnequip = PlanningEquipeDatabaseService.get(Integer.parseInt(equipe));
			}
			
			
			//récupération de la date concernée
			initialDate();
			selectedDateStr = DateService.dateToStr(selectedDate, DateService.p1 + " " + DateService.pt2);
			Date dateDebutMois = DateService.getFirstDayOfMonth(selectedDate);
			Date dateFinMois = DateService.getLastDayOfMonth(selectedDate);

			//initialisation des dates de la semaine
			listEquipe = PlanningEquipeDatabaseService.getAll();
			
			
			//initialisation des 42 cases affichées 
			days = new Integer[42];
			
			//détermination de l'index de départ du tableau
			selectedDate = dateDebutMois;
			Calendar thisMonth = Calendar.getInstance();
			thisMonth.setTime(selectedDate);
			int firstIndex = thisMonth.get(Calendar.DAY_OF_WEEK) - 2;
			if (firstIndex == -1){
				firstIndex = 6;
			}
			
			//insertion des numéro des dates dans le tableau
			thisMonth.setTime(dateFinMois);
			int maxIndex = thisMonth.getActualMaximum(Calendar.DAY_OF_MONTH);
			for(int i = 0; i < maxIndex; i++) {
				days[firstIndex+i] = i + 1;
			}
			
			//initialisation des couleurs et vacations
			mapJour = new HashMap<Integer, Integer>();
			mapCouleur = new HashMap<Integer,String>();
			mapVacation = new HashMap<Integer,String>();
			for(int i = 0; i < 42; i++) {
				mapJour.put(i,days[i]);
				mapCouleur.put(mapJour.get(i), "FFFFFF");
				mapVacation.put(mapJour.get(i), "");
			}
			
			//Insertion des valeurs
			if (equipe != null && !"".equals(equipe)) {
				//récupération des cycles et semaines pour la période concernée
				List<Planning_Cycle_Equipe> listPCE = PlanningCycleEquipeDatabaseService.getCycleByEquipeAndDate(Integer.parseInt(equipe), dateDebutMois, dateFinMois);
				Map<Planning_Cycle_Equipe, List<Planning_Semaine>> mapListSemaineByPCE = new HashMap<Planning_Cycle_Equipe, List<Planning_Semaine>>();
				for(Planning_Cycle_Equipe pce : listPCE){
					mapListSemaineByPCE.put(pce, PlanningSemaineDatabaseService.getSemaineByCycle(pce.getIdNomCycle().getId()));
				}
				
				for (int j = firstIndex; j < firstIndex + maxIndex; j++) {
					Date jourActuel = DateService.addDays(dateDebutMois, days[j] - 1); 
					//récupération du cycle pour le jour numeroJour
					for(Planning_Cycle_Equipe pce : listPCE){
						if((jourActuel.after(pce.getDateDebut()) || jourActuel.equals(pce.getDateDebut()))
								&& (pce.getDateFin() == null || jourActuel.before(pce.getDateFin()) || jourActuel.equals(pce.getDateFin()))){
							
							//récupération de(s) semaines de cycle
							List<Planning_Semaine> psList = PlanningSemaineDatabaseService.getSemaineByCycle(pce.getIdNomCycle().getId());
							Integer nbMaxSem = 0;
							for(Planning_Semaine ps : psList)
								if(ps.getNumeroSemaine() > nbMaxSem)
									nbMaxSem = ps.getNumeroSemaine();
							
							
							//détermination de la semaine du cycle à appliquer
							//int nbSemainesenCycle = mapListSemaineByPCE.get(pce).size();
							int numeroSemaine = 1;
							Date dateTmp = DateService.getWeekStart(pce.getDateDebut());
							Date dateDebutSemaine = DateService.getWeekStart(jourActuel);
							while(dateTmp.before(dateDebutSemaine)){
								dateTmp = DateService.addDays(dateTmp, 7);
								numeroSemaine++;
								if(numeroSemaine > nbMaxSem){
									numeroSemaine -= nbMaxSem;
								}
							}
							Planning_Semaine ps = null;
							for(Planning_Semaine psTmp : mapListSemaineByPCE.get(pce)){
								if(psTmp.getNumeroSemaine().equals(numeroSemaine)){
									ps = psTmp;
									break;
								}
							}
							
							//récupération du jour de la semaine
							switch(DateService.getJour(jourActuel)){
								case 1:
									mapCouleur.put(days[j], ps.getLundi().getCodeCouleur());
									mapVacation.put(days[j], ps.getLundi().getLibelle());
									break;
								case 2:
									mapCouleur.put(days[j], ps.getMardi().getCodeCouleur());
									mapVacation.put(days[j], ps.getMardi().getLibelle());
									break;
								case 3:
									mapCouleur.put(days[j], ps.getMercredi().getCodeCouleur());
									mapVacation.put(days[j], ps.getMercredi().getLibelle());
									break;
								case 4:
									mapCouleur.put(days[j], ps.getJeudi().getCodeCouleur());
									mapVacation.put(days[j], ps.getJeudi().getLibelle());
									break;
								case 5:
									mapCouleur.put(days[j], ps.getVendredi().getCodeCouleur());
									mapVacation.put(days[j], ps.getVendredi().getLibelle());
									break;
								case 6: 
									mapCouleur.put(days[j], ps.getSamedi().getCodeCouleur());
									mapVacation.put(days[j], ps.getSamedi().getLibelle());
									break;
								case 0:
									mapCouleur.put(days[j], ps.getDimanche().getCodeCouleur());
									mapVacation.put(days[j], ps.getDimanche().getLibelle());
									break;
								default:
									break;
							}
						}
					}
				}
			}
			
			//récupération de(s) modifs ponctuelles
			List<Planning_Modif_Ponctuelle> pmpList = PlanningModifPonctuelleDatabaseService.getByEquipeAndDate(pnequip, dateDebutMois, dateFinMois);
			for (Planning_Modif_Ponctuelle pmp : pmpList) {
				selectedDate = dateDebutMois;
				Integer dayOfMonth = DateService.getDayOfMonth(selectedDate);
				
				for (int i = 0; i < maxIndex; i++) {
					if ((selectedDate.after(pmp.getDateDebut()) || selectedDate.equals(pmp.getDateDebut())) && (selectedDate.before(pmp.getDateFin()) || selectedDate.equals(pmp.getDateFin()))) {
						mapVacation.put(dayOfMonth, pmp.getIdVacation().getLibelle());
						mapCouleur.put(dayOfMonth, pmp.getIdVacation().getCodeCouleur());
					}
					selectedDate = DateService.addDays(selectedDate, 1);
					dayOfMonth = DateService.getDayOfMonth(selectedDate);
				}
				selectedDate = dateDebutMois;
			}
			
			listPlanningVacation = PlanningVacationsDatabaseService.getAll();
			
			mapDark = new HashMap<Integer, String>();
			for(Entry<Integer, String> entry : mapCouleur.entrySet()){
				mapDark.put(entry.getKey(), ColorService.isDark(entry.getValue()).toString());
			}
			
			Map<Integer, String> couleurListe = new HashMap<Integer, String>();
			Map<Integer,String> vacationListe = new HashMap<Integer, String>();
			for (Planning_Vacation vac : listPlanningVacation){
				couleurListe.put(vac.getId(), vac.getCodeCouleur());
				vacationListe.put(vac.getId(), vac.getLibelle());
			}
			
			mapJourVacation = new HashMap<Integer, Integer>();
			for (int i = 0 ; i < 42 ; i++){
				Integer idJour = mapJour.get(i);
				if (idJour != null){
					Planning_Vacation vacation = PlanningVacationsDatabaseService.getByLibelle(mapVacation.get(idJour));
					if (vacation != null)
						mapJourVacation.put(i, vacation.getId());
					else
						mapJourVacation.put(i, null);
				} else {
					mapJourVacation.put(i, null);
				}
			}
			
			session.put("equipe", pnequip);
			session.put("mapJourMois", mapJour);
			session.put(PilotageConstants.PLANNING_LISTE_JOUR, mapJourVacation);
			session.put(PilotageConstants.PLANNING_SELECT_DATE, selectedDate);
			session.put(PilotageConstants.PLANNING_SELECT_COULEUR, vacationListe);
			session.put(PilotageConstants.PLANNING_COULEUR, couleurListe);
			
			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Planning mois - ", e);
			listEquipe = PlanningEquipeDatabaseService.getAll();
			mapJour = new HashMap<Integer, Integer>();
			mapCouleur = new HashMap<Integer,String>();
			mapVacation = new HashMap<Integer,String>();
			return ERROR;
		}
	}
	
	protected void initialDate() throws ParseException{
		if (selectedDateStr == null || "".equals(selectedDateStr)) {
			selectedDate = DateService.getTodayWithoutHour();
		} else {
			selectedDate = new SimpleDateFormat("dd/MM/yyyy").parse(selectedDateStr);
		}
	}
	
	public String getJour(Date selectedDate) {
		String jour = new SimpleDateFormat("dd").format(selectedDate);
		String mois = new SimpleDateFormat("MM").format(selectedDate);
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
		return jour+" "+mois;
	}
}
