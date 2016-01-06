package pilotage.planning.planning;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import pilotage.service.date.DateService;

public class ShowPlanningMoisApresAction extends ShowPlanningMoisAction {

	private static final long serialVersionUID = 2800115204815595744L;
	
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
