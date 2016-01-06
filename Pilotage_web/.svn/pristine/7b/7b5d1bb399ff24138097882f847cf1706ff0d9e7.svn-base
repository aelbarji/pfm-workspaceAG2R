package pilotage.statistiques;

import java.text.SimpleDateFormat;
import java.util.Date;

import pilotage.database.checklist.ChecklistDatabaseService;
import pilotage.service.date.DateService;

public class StatistiqueChecklistMoisAction extends StatistiqueChecklistAction {
	
	private static final long serialVersionUID = -1492762476828824665L;

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		try{
			if(ERROR.equals(super.executeMetier()))
				return ERROR;
			
			String selectedMoisAnneeStr = "01/" + selectedMois + "/" + selectedAnneeM + " 07:30:00";
			Date selectedMoisAnnee = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(selectedMoisAnneeStr);

			Date dernierJour;
			Integer nombreJourDuMois;
			
			if (selectedMois.equals(new SimpleDateFormat("MM").format(new Date())) && selectedAnneeM.equals(new SimpleDateFormat("yyyy").format(new Date()))) {
				nombreJourDuMois = DateService.getDayOfMonth(new Date());
				dernierJour = new Date();
			} else {
				nombreJourDuMois = DateService.getNbDaysInMonth(selectedMoisAnnee);
				dernierJour = DateService.getLastDayOfMonth(selectedMoisAnnee);
			}
			
			Integer nombreTacheDuMois = ChecklistDatabaseService.getNombreTache(selectedMoisAnnee, dernierJour);
			Double moyenneMois = (Math.round(1000*nombreTacheDuMois/nombreJourDuMois/24))/1000d;
			String selectedMoisNom = numMoisToNomMois(selectedMois);
			resultatM = "Nombre moyen de tâche par heure pour le mois " + selectedMoisNom + " de l'année " + selectedAnneeM + " est " + moyenneMois;
			return SUCCESS;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Statistiques checklist - ", e);
			return ERROR;
		}
	}
	
	public String numMoisToNomMois(String selectedMois) {
		String mois = "";
		if ("01".equals(selectedMois)) {
			mois = "Janvier";
		}
		if ("02".equals(selectedMois)) {
			mois = "Février";
		}
		if ("03".equals(selectedMois)) {
			mois = "Mars";
		}
		if ("04".equals(selectedMois)) {
			mois = "Avril";
		}
		if ("05".equals(selectedMois)) {
			mois = "Mai";
		}
		if ("06".equals(selectedMois)) {
			mois = "Juin";
		}
		if ("07".equals(selectedMois)) {
			mois = "Juillet";
		}
		if ("08".equals(selectedMois)) {
			mois = "Août";
		}
		if ("09".equals(selectedMois)) {
			mois = "Septembre";
		}
		if ("10".equals(selectedMois)) {
			mois = "Octobre";
		}
		if ("11".equals(selectedMois)) {
			mois = "Novembre";
		}
		if ("12".equals(selectedMois)) {
			mois = "Décembre";
		}
		return mois;
	}
}