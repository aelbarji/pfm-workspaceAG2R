package pilotage.bilan.alertes.disques;

import java.text.MessageFormat;
import java.util.Date;

import pilotage.database.bilan.AlertesDisquesDatabaseService;
import pilotage.database.historique.HistoriqueDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;

public class DeleteAlertesDisquesAction extends AbstractAction {

	private static final long serialVersionUID = 2799533008289179777L;
	
	private Date selectedDate;
	private Integer selectedID;

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
	 * @return the selectedID
	 */
	public Integer getSelectedID() {
		return selectedID;
	}

	/**
	 * @param selectedID the selectedID to set
	 */
	public void setSelectedID(Integer selectedID) {
		this.selectedID = selectedID;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {

		try{
			AlertesDisquesDatabaseService.delete(selectedID);
			HistoriqueDatabaseService.create(null,MessageFormat.format(getText("historique.bilan.alerte.suppression"), selectedID), (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_BILAN);

			info = getText("alertes.disques.suppression.valide");
			return OK;
		}
		catch (Exception e){
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Suppression d'une alerte disque - ", e);
			return ERROR;
		}
	}

}
