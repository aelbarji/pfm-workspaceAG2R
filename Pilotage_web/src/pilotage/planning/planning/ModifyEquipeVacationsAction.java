package pilotage.planning.planning;

import java.util.Date;

import pilotage.database.historique.HistoriqueDatabaseService;
import pilotage.database.planning.PlanningEquipeDatabaseService;
import pilotage.database.planning.PlanningModifPonctuelleDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Planning_Nom_Equipe;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;

public class ModifyEquipeVacationsAction extends AbstractAction {
	
	private static final long serialVersionUID = -754080998198548904L;
	
	private Integer status;
	private Integer equipeID;
	private Date dateDebut;
	private Date dateFin;
	private String selectedVacation;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getEquipeID() {
		return equipeID;
	}

	public void setEquipeID(Integer equipeID) {
		this.equipeID = equipeID;
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

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		status = 0;
		try{
			Planning_Nom_Equipe pne = PlanningEquipeDatabaseService.get(equipeID);
			PlanningModifPonctuelleDatabaseService.create(null, pne, dateDebut, dateFin, selectedVacation, (Users)session.get(PilotageConstants.USER_LOGGED), "");
			
			info = "La vacation de l'équipe " + pne.getNomEquipe() + " a été mise à jour.";
			String historique = "Modification de vacation d'équipe " +  pne.getNomEquipe();
			HistoriqueDatabaseService.create(null, historique, (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_PLANNING);
			status = 1;
			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Modification d'une vacation d'équipe - ", e);
			return ERROR;
		}
	}

}
