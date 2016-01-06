package pilotage.statistiques;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import pilotage.database.checklist.ChecklistDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.service.date.DateService;

public class StatistiqueChecklistAction extends AbstractAction {

	private static final long serialVersionUID = -1492762476828824665L;
	
	protected String currentDate;
	protected String currentSemaine;
	protected String currentMois;
	protected String currentAnnee;
	protected Date selectedDate;
	protected String selectedSemaine;
	protected String selectedAnneeS;
	protected String selectedMois;
	protected String selectedAnneeM;
	protected Map<Integer, String> mapSemaine;
	protected Map<String, String> mapAnnee;
	protected Integer statChecklistType;
	protected String resultatJ;
	protected String resultatS;
	protected String resultatM;
	protected String tempsControleStr;
	protected String nombreTacheParHeureUrl;
	
	public String getNombreTacheParHeureUrl() {
		return nombreTacheParHeureUrl;
	}

	public void setNombreTacheParHeureUrl(String nombreTacheParHeureUrl) {
		this.nombreTacheParHeureUrl = nombreTacheParHeureUrl;
	}

	public String getCurrentDate() {
		return currentDate;
	}

	public String getCurrentSemaine() {
		return currentSemaine;
	}

	public void setCurrentSemaine(String currentSemaine) {
		this.currentSemaine = currentSemaine;
	}

	public String getCurrentMois() {
		return currentMois;
	}

	public void setCurrentMois(String currentMois) {
		this.currentMois = currentMois;
	}

	public String getCurrentAnnee() {
		return currentAnnee;
	}

	public void setCurrentAnnee(String currentAnnee) {
		this.currentAnnee = currentAnnee;
	}

	public void setCurrentDate(String currentDate) {
		this.currentDate = currentDate;
	}

	public Date getSelectedDate() {
		return selectedDate;
	}

	public void setSelectedDate(Date selectedDate) {
		this.selectedDate = selectedDate;
	}

	public String getSelectedSemaine() {
		return selectedSemaine;
	}

	public void setSelectedSemaine(String selectedSemaine) {
		this.selectedSemaine = selectedSemaine;
	}

	public String getSelectedAnneeS() {
		return selectedAnneeS;
	}

	public void setSelectedAnneeS(String selectedAnneeS) {
		this.selectedAnneeS = selectedAnneeS;
	}

	public String getSelectedMois() {
		return selectedMois;
	}

	public void setSelectedMois(String selectedMois) {
		this.selectedMois = selectedMois;
	}

	public String getSelectedAnneeM() {
		return selectedAnneeM;
	}

	public void setSelectedAnneeM(String selectedAnneeM) {
		this.selectedAnneeM = selectedAnneeM;
	}

	public Map<Integer, String> getMapSemaine() {
		return mapSemaine;
	}

	public void setMapSemaine(Map<Integer, String> mapSemaine) {
		this.mapSemaine = mapSemaine;
	}

	public Map<String, String> getMapAnnee() {
		return mapAnnee;
	}

	public void setMapAnnee(Map<String, String> mapAnnee) {
		this.mapAnnee = mapAnnee;
	}

	public Integer getStatChecklistType() {
		return statChecklistType;
	}

	public void setStatChecklistType(Integer statChecklistType) {
		this.statChecklistType = statChecklistType;
	}

	public String getResultatJ() {
		return resultatJ;
	}

	public void setResultatJ(String resultatJ) {
		this.resultatJ = resultatJ;
	}

	public String getResultatS() {
		return resultatS;
	}

	public void setResultatS(String resultatS) {
		this.resultatS = resultatS;
	}

	public String getResultatM() {
		return resultatM;
	}

	public void setResultatM(String resultatM) {
		this.resultatM = resultatM;
	}

	public String getTempsControleStr() {
		return tempsControleStr;
	}

	public void setTempsControleStr(String tempsControleStr) {
		this.tempsControleStr = tempsControleStr;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		try {
			currentDate = DateService.dateToStr(new Date(), DateService.p4);
			if (selectedDate == null) {
				selectedDate = new Date();
			}
			if (selectedSemaine == null || "".equals(selectedSemaine)) {
				selectedSemaine = String.valueOf(DateService.getWeekOfYear(new Date()));
			}
			if (selectedAnneeS == null || "".equals(selectedAnneeS)) {
				selectedAnneeS = new SimpleDateFormat("yyyy").format(new Date());
			}
			if (selectedMois == null || "".equals(selectedMois)) {
				selectedMois = new SimpleDateFormat("MM").format(new Date());
			}
			if (selectedAnneeM == null || "".equals(selectedAnneeM)) {
				selectedAnneeM = new SimpleDateFormat("yyyy").format(new Date());
			}
			
			mapSemaine = new LinkedHashMap<Integer, String>();
			String debutAnneeStr = "01/01/" + selectedAnneeS;
			Date debutAnnee = new SimpleDateFormat("dd/MM/yyyy").parse(debutAnneeStr);
			for (int i=1; i<53; i++) {
				Date lundi = DateService.getMonday(i, debutAnnee);
				Date dimanche = DateService.getWeekEnd(lundi);
				mapSemaine.put(i, "Semaine " + i + " - " + DateService.dateToStr(lundi, DateService.p1) + " au " + DateService.dateToStr(dimanche, DateService.p1));
			}

			mapAnnee = new HashMap<String, String>();
			Date premierChecklist = ChecklistDatabaseService.getPremierChecklist();
			String premierAnnee = new SimpleDateFormat("yyyy").format(premierChecklist);
			String currentAnnee = new SimpleDateFormat("yyyy").format(new Date());
			for (int i=Integer.parseInt(premierAnnee); i<Integer.parseInt(currentAnnee)+1; i++) {
				mapAnnee.put(String.valueOf(i), String.valueOf(i));
			}
			
			return SUCCESS;
		} catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Statistiques checklist - ", e);
			return ERROR;
		}
	}
}
