package pilotage.bilan.alertes.disques;

import java.util.Date;
import java.util.List;

import pilotage.database.bilan.AlertesDisquesDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Alertes_Disques;
import pilotage.service.date.DateService;

public class ShowAlertesDisquesAction extends AbstractAction {

	private static final long serialVersionUID = 6128388564103339178L;
	
	private Date selectedDate;
	private List<Alertes_Disques> listAlertes;
	
	private String currentDate;
	
	/**
	 * @return the currentDate
	 */
	public String getCurrentDate() {
		return currentDate;
	}

	/**
	 * @param currentDate the currentDate to set
	 */
	public void setCurrentDate(String currentDate) {
		this.currentDate = currentDate;
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
	 * @return the listAlertes
	 */
	public List<Alertes_Disques> getListAlertes() {
		return listAlertes;
	}

	/**
	 * @param listAlertes the listAlertes to set
	 */
	public void setListAlertes(List<Alertes_Disques> listAlertes) {
		this.listAlertes = listAlertes;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		try{
			currentDate = DateService.dateToStr(new Date(), DateService.p4);
			if(selectedDate == null){
				selectedDate = new Date();
			}
			listAlertes = AlertesDisquesDatabaseService.getByDate(selectedDate);
			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Création d'une alerte disque - ", e);
			return ERROR;
		}
	}

}
