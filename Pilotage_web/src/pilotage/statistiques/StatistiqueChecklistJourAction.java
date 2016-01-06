package pilotage.statistiques;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import pilotage.database.checklist.ChecklistDatabaseService;
import pilotage.metier.Checklist_Current;
import pilotage.service.date.DateService;

public class StatistiqueChecklistJourAction extends StatistiqueChecklistAction {

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
			
			String selectedDateStr = DateService.dateToStr(selectedDate, DateService.p1);
			Integer checklistDuJour = ChecklistDatabaseService.getNombreTacheDuJour(selectedDate);
			Double moyenneJour = (Math.round(1000*checklistDuJour/24))/1000d;
			resultatJ = "Nombre de tâche par heure pour " + selectedDateStr + " est " + moyenneJour;
			
			List<Date> listTimeUsed = ChecklistDatabaseService.getListTimeUsed(selectedDate);
			Long ecart = 0l;
			for (Date d : listTimeUsed) {
				ecart += DateService.getLongTime(d);
			}
			Integer ecartJour = (int) Math.floor(ecart/1000/60/60/24);
			Date timeUsed = DateService.getDateTime(ecart);
			String timeUsedStr = String.valueOf(Integer.parseInt(new SimpleDateFormat("HH").format(timeUsed))+24*ecartJour) + new SimpleDateFormat(":mm:ss").format(timeUsed);
			if (timeUsedStr.length()<8) {
				timeUsedStr = "0" + timeUsedStr;
			}
			tempsControleStr = "Le temps total de contrôle dans la journée est " + timeUsedStr;
			
			StringBuilder sbNombreTacheParHeureURL = new StringBuilder();
			sbNombreTacheParHeureURL.append("drawBarChartAction.action?");
			sbNombreTacheParHeureURL.append("type=bar&titre=");
			sbNombreTacheParHeureURL.append("Nombre de tache par heure");
			sbNombreTacheParHeureURL
					.append("&legende=true&tips=true&colorString=55FE75&vertialLabel=Nombre&horizontalLabel=Periode&width=800&height=300&is3D=true&");

			List<Checklist_Current> listChecklistDuJour = ChecklistDatabaseService.getTacheDuJour(selectedDate);

			for (int i=0; i<24; i++) {
				Integer nombreTache = 0;
				for (Checklist_Current cc: listChecklistDuJour) {
					Date dateExecute = DateService.addTime(cc.getIdHoraire().getHoraire(), cc.getSousTache().getDecalageStamp());
					Integer heureExecute = Integer.valueOf(new SimpleDateFormat("HH").format(dateExecute))%24;
					if (i == heureExecute) {
						nombreTache++;
					}
				}
				sbNombreTacheParHeureURL.append("&n");
				sbNombreTacheParHeureURL.append(i);
				sbNombreTacheParHeureURL.append("=");
				sbNombreTacheParHeureURL.append(i + "h");
				sbNombreTacheParHeureURL.append("&c");
				sbNombreTacheParHeureURL.append(i);
				sbNombreTacheParHeureURL.append("=");
				sbNombreTacheParHeureURL.append(i + ":00 - " + i + ":59");
				sbNombreTacheParHeureURL.append("&v");
				sbNombreTacheParHeureURL.append(i);
				sbNombreTacheParHeureURL.append("=");
				sbNombreTacheParHeureURL.append(nombreTache);
			}
			nombreTacheParHeureUrl = sbNombreTacheParHeureURL.toString();
			return SUCCESS;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Statistiques checklist - ", e);
			return ERROR;
		}
	}
}