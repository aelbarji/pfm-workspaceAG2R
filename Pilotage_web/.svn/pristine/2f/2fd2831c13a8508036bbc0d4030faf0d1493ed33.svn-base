package pilotage.planning.planning;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import pilotage.service.date.DateService;

public class ShowPlanningMoisPiloteApresAction extends ShowPlanningMoisPiloteAction {

	private static final long serialVersionUID = 1464998114516373274L;

	@Override
	protected void initialDate(){
		try {
			if (selectedDateStr == null) {
				selectedDate = new Date();
			} else {
				selectedDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
						.parse(selectedDateStr);
			}
		} catch (ParseException e) {
			selectedDate = new Date();
		}
		selectedDate = DateService.addMonths(selectedDate, 1);
	}
}
