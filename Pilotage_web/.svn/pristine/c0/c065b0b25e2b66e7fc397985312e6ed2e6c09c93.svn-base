package pilotage.statistiques;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import pilotage.service.date.DateService;

public class StatistiqueServiceNextWeekAction extends StatistiqueServiceAction {

	private static final long serialVersionUID = -7243442076796909093L;

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
		selectedDate = DateService.addWeeks(selectedDate, 1);
	}
}
