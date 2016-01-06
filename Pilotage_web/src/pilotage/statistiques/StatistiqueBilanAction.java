package pilotage.statistiques;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import pilotage.framework.AbstractAction;
import pilotage.metier.Bilan_Stat;
import pilotage.service.date.DateService;
import pilotage.service.statistique.BilanStatDatabaseService;

public class StatistiqueBilanAction extends AbstractAction {

	private static final long serialVersionUID = 8152876409733556261L;
	
	private List<Bilan_Stat> bilanStatList;
	private Date selectedDate;
	private String selectedDateStr;
	
	private String month;

	/**
	 * @return the month
	 */
	public String getMonth() {
		return month;
	}

	/**
	 * @param month the month to set
	 */
	public void setMonth(String month) {
		this.month = month;
	}

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
	 * @return the bilanStatList
	 */
	public List<Bilan_Stat> getBilanStatList() {
		return bilanStatList;
	}

	/**
	 * @param bilanStatList the bilanStatList to set
	 */
	public void setBilanStatList(List<Bilan_Stat> bilanStatList) {
		this.bilanStatList = bilanStatList;
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

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		try{
			if(selectedDateStr == null){
				selectedDate = new Date();
				selectedDateStr = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(selectedDate);
			}
			else{
				try {
					selectedDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(selectedDateStr);
				} catch (ParseException e) {
					selectedDate = new Date();
					selectedDateStr = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(selectedDate);
				}
			}
			
			bilanStatList = BilanStatDatabaseService.getByDate(selectedDate);
			month = DateService.getMonthString(selectedDate);
			if(month != null){
				month = month.substring(0,1).toUpperCase()+month.substring(1);
			}
			return SUCCESS;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Statistiques bilan - ", e);
			return ERROR;
		}
	}

}
