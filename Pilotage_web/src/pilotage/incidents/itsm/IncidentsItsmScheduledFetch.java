package pilotage.incidents.itsm;

import java.util.TimerTask;

import pilotage.service.constants.PilotageConstants;

public class IncidentsItsmScheduledFetch extends TimerTask{

	public void run() {
		//Call WSDL Incidents ITSM 
		if ( PilotageConstants.isIncidentsItsmTimerStarted == false){
			PilotageConstants.isIncidentsItsmTimerStarted = true;
		}
		IncidentItsmFetcher.FetchData();
	}
}
