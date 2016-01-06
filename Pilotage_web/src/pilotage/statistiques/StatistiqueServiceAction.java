package pilotage.statistiques;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import pilotage.database.services.ServicesDatabaseService;
import pilotage.database.statistiques.StatistiqueIncidentDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Incidents;
import pilotage.metier.Services_Liste;
import pilotage.service.constants.PilotageConstants;
import pilotage.service.date.DateService;

public class StatistiqueServiceAction extends AbstractAction {

	private static final long serialVersionUID = -8239562739498737977L;

	public static final int SERVICE_RAS = 0;
	public static final int SERVICE_INCIDENT_SC = 1;
	public static final int SERVICE_INCIDENT_CRITICAL_SC = 2;
	public static final int SERVICE_INCIDENT_CRITICAL_AC = 3;

	protected String selectedDateStr;
	protected Date selectedDate;
	protected List<Services_Liste> listServices;
	protected List<Services_Liste> listServicesTemp;
	protected Map<Services_Liste, List<Integer>> servicesMap;
	protected List<Integer> dates;

	protected Integer year;
	protected Integer month;
	
	
	/**
	 * @return the year
	 */
	public Integer getYear() {
		return year;
	}

	/**
	 * @return the month
	 */
	public Integer getMonth() {
		return month;
	}

	/**
	 * @return the selectedDate
	 */
	public Date getSelectedDate() {
		return selectedDate;
	}

	/**
	 * @param selectedDate the selectedDate to set
	 */
	public void setSelectedDate(Date selectedDate) {
		this.selectedDate = selectedDate;
	}

	/**
	 * @return the selectedDateStr
	 */
	public String getSelectedDateStr() {
		return selectedDateStr;
	}

	/**
	 * @param selectedDateStr
	 *            the selectedDateStr to set
	 */
	public void setSelectedDateStr(String selectedDateStr) {
		this.selectedDateStr = selectedDateStr;
	}

	/**
	 * @return the listServices
	 */
	public List<Services_Liste> getListServices() {
		return listServices;
	}

	/**
	 * @param listServices
	 *            the listServices to set
	 */
	public void setListServices(List<Services_Liste> listServices) {
		this.listServices = listServices;
	}

	/**
	 * @return the servicesMap
	 */
	public Map<Services_Liste, List<Integer>> getServicesMap() {
		return servicesMap;
	}

	/**
	 * @param servicesMap
	 *            the servicesMap to set
	 */
	public void setServicesMap(Map<Services_Liste, List<Integer>> servicesMap) {
		this.servicesMap = servicesMap;
	}

	/**
	 * @return the dates
	 */
	public List<Integer> getDates() {
		return dates;
	}

	/**
	 * @param dates
	 *            the dates to set
	 */
	public void setDates(List<Integer> dates) {
		this.dates = dates;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		try{
			initialDate();
			selectedDateStr = DateService.dateToStr(selectedDate, DateService.p1 + " " + DateService.pt2);
			dates = DateService.getWeekDate(selectedDate);
			Date temp = DateService.getDateEarlier(DateService.getWeekStart(selectedDate));
			
			Calendar cd = Calendar.getInstance();
			cd.setTime(temp);
			year = cd.get(Calendar.YEAR);
			month = cd.get(Calendar.MONTH)+1;
	
			listServicesTemp = ServicesDatabaseService.getAll();
			listServices = reorgListService(listServicesTemp);
			initialMap();
	
			return SUCCESS;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Statistiques service - ", e);
			return ERROR;
		}
	}

	/**
	 * Change the date Can be overridden
	 */
	protected void initialDate() throws Exception {
		if (selectedDateStr == null) {
			selectedDate = DateService.getTodayWithoutHour();
		} else {
			selectedDate = new SimpleDateFormat("dd/MM/yyyy").parse(selectedDateStr);
		}
		
		selectedDate = DateService.strToDate(DateService.dateToStr(selectedDate, DateService.p1), "12:00:00");
	}

	/**
	 * initial map of services
	 */
	private void initialMap() throws Exception {
		servicesMap = new HashMap<Services_Liste, List<Integer>>();
		List<List<Incidents>> allIncident = StatistiqueIncidentDatabaseService.getAllIncidentByWeek(selectedDate);
		for (Services_Liste sl : listServices) {
			List<Integer> list = new ArrayList<Integer>();
			for (List<Incidents> l : allIncident) {
				if (l.size() == 0) {
					list.add(SERVICE_RAS);
				}else {
					Integer status = SERVICE_RAS;
					for (Incidents incident : l) {
						List<String> strList = Arrays.asList(incident.getService().split(PilotageConstants.INCIDENT_SEPARATEUR));
						if (strList.contains(Integer.toString(sl.getId()))) {
							// statement to check if the incident is critical
							// Need to be modified
							if (incident.getCoupure() == 1) {
								status = SERVICE_INCIDENT_CRITICAL_AC;
							} else {
								if (incident.getType().getId() == 3){
									status = SERVICE_INCIDENT_CRITICAL_SC;
								}else{
									status = SERVICE_INCIDENT_SC;
								}
							}	
						}
					}
					list.add(status);
				}
			}
			servicesMap.put(sl, list);
		}
	}
	
	protected List<Services_Liste> reorgListService(List<Services_Liste> listServicesTemp) throws Exception {
		List<Services_Liste> orderServicesListesList = listServicesTemp;
		List<List<Incidents>> allIncident = StatistiqueIncidentDatabaseService.getAllIncidentByWeek(selectedDate);
		
		// comptabiliser le nombre d'incident par service
		HashMap<Integer, Integer> newMap = new HashMap<Integer, Integer>();
		for (List<Incidents> l : allIncident) {
			if (l.size() != 0) {
				for (Incidents incident : l) {
					// récup du service lié à l'incident
					List<String> strList = Arrays.asList(incident.getService().split(PilotageConstants.INCIDENT_SEPARATEUR));
					int coupure = incident.getCoupure();
					int type = incident.getType().getId();
					Iterator<String> itSL = strList.iterator();
					while (itSL.hasNext()){
						String sId = itSL.next();
						if(sId == null || "".equals(sId))
							continue;
						
						Integer serviceId = Integer.parseInt(sId);
						int i=1;
						if (newMap.containsKey(serviceId)){
							i = newMap.get(serviceId);
							i++;
							if (coupure == 1){
								newMap.put(serviceId, i+2);
							}
							else{
								if(type == 3){
									newMap.put(serviceId, i+1);
								}
								else{
									newMap.put(serviceId, i);
								}
							}	
						}
						else{
							if (coupure == 1){
								newMap.put(serviceId, i+2);
							}
							else{
								if(type == 3){
									newMap.put(serviceId, i+1);
								}
								else{
									newMap.put(serviceId, i);
								}
							}
						}	
					}	
				}
			}
		}
		
		//Ordonner la map
		@SuppressWarnings("unchecked")
		Map.Entry<Integer,Integer>[] array = newMap.entrySet().toArray(new Map.Entry[newMap.size()]);	 
		Arrays.sort(array, new Comparator<Map.Entry<Integer,Integer>>() {
		        public int compare(Map.Entry<Integer,Integer> s1, Map.Entry<Integer,Integer> s2) {
		                return s2.getValue().compareTo(s1.getValue());
		        }
		});

		// reorganisation de la nouvelle liste
		for (int i=0; i< array.length; i++){
			int keyService = array[i].getKey();
			for (Services_Liste sl : listServicesTemp){
				if (keyService == sl.getId()){				
					// recup du service à remplacer
					Services_Liste slTemp = new Services_Liste();
					int index = listServicesTemp.indexOf(sl);
					int id = listServicesTemp.get(i).getId();
					String nomS = listServicesTemp.get(i).getNomService();
					slTemp.setId(id);
					slTemp.setNomService(nomS);				
					orderServicesListesList.set(i,sl);
					orderServicesListesList.set(index, slTemp);
				}
			}	
		}
		return orderServicesListesList;
	}
	
	
}
