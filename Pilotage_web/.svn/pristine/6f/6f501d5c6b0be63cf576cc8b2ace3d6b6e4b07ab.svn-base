package pilotage.statistiques;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pilotage.database.incidents.IncidentsDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.incidents.AbstractIncidentsAction;
import pilotage.metier.Applicatifs_Liste;
import pilotage.metier.Incidents;
import pilotage.service.date.DateService;

public class ShowDetailIncidentAction extends AbstractAction {

	private static final long serialVersionUID = -1000813017125625864L;
	private String selectedDateStr;
	private int serviceStatus;
	private int serviceID;

	private int dayOfWeek;
	private List<Incidents> listIncident;
	private Map<Integer, List<Applicatifs_Liste>> appMap;
	
	/**
	 * @return the selectedDateStr
	 */
	public String getSelectedDateStr() {
		return selectedDateStr;
	}

	/**
	 * @param selectedDateStr the selectedDateStr to set
	 */
	public void setSelectedDateStr(String selectedDateStr) {
		this.selectedDateStr = selectedDateStr;
	}

	/**
	 * @return the listIncident
	 */
	public List<Incidents> getListIncident() {
		return listIncident;
	}

	/**
	 * @param listIncident the listIncident to set
	 */
	public void setListIncident(List<Incidents> listIncident) {
		this.listIncident = listIncident;
	}


	/**
	 * @return the serviceStatus
	 */
	public int getServiceStatus() {
		return serviceStatus;
	}

	/**
	 * @param serviceStatus the serviceStatus to set
	 */
	public void setServiceStatus(int serviceStatus) {
		this.serviceStatus = serviceStatus;
	}

	/**
	 * @return the serviceID
	 */
	public int getServiceID() {
		return serviceID;
	}

	/**
	 * @param serviceID the serviceID to set
	 */
	public void setServiceID(int serviceID) {
		this.serviceID = serviceID;
	}

	/**
	 * @return the dayOfWeek
	 */
	public int getDayOfWeek() {
		return dayOfWeek;
	}

	/**
	 * @param dayOfWeek the dayOfWeek to set
	 */
	public void setDayOfWeek(int dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public Map<Integer, List<Applicatifs_Liste>> getAppMap() {
		return appMap;
	}

	public void setAppMap(Map<Integer, List<Applicatifs_Liste>> appMap) {
		this.appMap = appMap;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		try{
			Date selectedDate;
			if(selectedDateStr != null)
				selectedDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(selectedDateStr);
			else
				selectedDate = new Date();
			
			selectedDate = DateService.getByDayOfWeek(selectedDate, dayOfWeek);

			listIncident = IncidentsDatabaseService.getByDateAndService(selectedDate, serviceID);
			appMap = new HashMap<Integer, List<Applicatifs_Liste>>();
			AbstractIncidentsAction.initApplicationMap(appMap, listIncident);
			
			return SUCCESS;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Detail incident pour une date et un service - ", e);
			return ERROR;
		}
	}

}
