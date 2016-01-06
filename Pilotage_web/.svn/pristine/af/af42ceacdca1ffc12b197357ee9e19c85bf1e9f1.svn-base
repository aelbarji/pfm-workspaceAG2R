package pilotage.planning.planning;

import java.util.Date;

import pilotage.database.historique.HistoriqueDatabaseService;
import pilotage.database.planning.PlanningModifPonctuelleDatabaseService;
import pilotage.database.users.management.UsersDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;

public class ModifyPiloteVacationsAction extends AbstractAction{

	private static final long serialVersionUID = 7013818341148152623L;
	
	private Integer piloteID;
	private Date dateDebut;
	private Date dateFin;
	private String selectedVacation;
	private Integer status;
	private String textCell;

	public Integer getPiloteID() {
		return piloteID;
	}

	public void setPiloteID(Integer piloteID) {
		this.piloteID = piloteID;
	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public String getSelectedVacation() {
		return selectedVacation;
	}

	public void setSelectedVacation(String selectedVacation) {
		this.selectedVacation = selectedVacation;
	}

	public String getTextCell() {
		return textCell;
	}

	public void setTextCell(String textCell) {
		this.textCell = textCell;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		status = 0;
		try{
			if(textCell.trim().equals(""))
				textCell = null;
			Users user = UsersDatabaseService.get(piloteID);
			PlanningModifPonctuelleDatabaseService.create(user, null, dateDebut, dateFin, selectedVacation, (Users)session.get(PilotageConstants.USER_LOGGED), textCell);
			
			info = "La vacation du pilote " + user.getNom() + " " + user.getPrenom() + " a été mis à jour.";
			String historique = "Modification de vacation de pilote " + user.getId();
			HistoriqueDatabaseService.create(null, historique, (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_PLANNING);
			status = 1;
			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Modification d'une vacation de pilote - ", e);
			return ERROR;
		}
	}
}