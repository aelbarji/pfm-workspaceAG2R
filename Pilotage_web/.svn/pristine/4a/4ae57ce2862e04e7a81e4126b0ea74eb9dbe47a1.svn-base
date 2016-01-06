package pilotage.statistiques;

import java.text.SimpleDateFormat;
import java.util.Date;

import pilotage.database.checklist.ChecklistDatabaseService;
import pilotage.service.date.DateService;

public class StatistiqueChecklistSemaineAction extends StatistiqueChecklistAction {

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
			
			String debutAnneeStr = "01/01/" + selectedAnneeS;
			Date debutAnnee = new SimpleDateFormat("dd/MM/yyyy").parse(debutAnneeStr);
			Date monday = DateService.getMonday(Integer.parseInt(selectedSemaine), debutAnnee);
			Date sunday = DateService.getWeekEnd(monday);
			Integer nombreTacheSemaine = ChecklistDatabaseService.getNombreTache(monday, sunday);
			Double moyenneSemaine = (Math.round(1000*nombreTacheSemaine/7/24))/1000d;
			resultatS = "Nombre moyen de tâche par heure pour la semaine " + DateService.dateToStr(monday, DateService.p1) + " - " + DateService.dateToStr(sunday, DateService.p1) + " de l'année " + selectedAnneeS + " est " + moyenneSemaine;
			
			return SUCCESS;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Statistiques checklist - ", e);
			return ERROR;
		}
	}
}