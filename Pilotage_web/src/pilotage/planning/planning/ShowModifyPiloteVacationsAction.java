package pilotage.planning.planning;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import pilotage.database.planning.PlanningModifPonctuelleDatabaseService;
import pilotage.database.planning.PlanningVacationsDatabaseService;
import pilotage.database.users.management.UsersDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Planning_Modif_Ponctuelle;
import pilotage.metier.Planning_Vacation;
import pilotage.metier.Users;
import pilotage.service.date.DateService;

public class ShowModifyPiloteVacationsAction extends AbstractAction {
	
	private static final long serialVersionUID = -2136820498896120263L;
	
	private Integer piloteID;
	private String pilote;
	private String date;
	private Integer vacationID;
	private String vacation;
	private Integer selectedVacation;
	private Date dateDebut;
	private Date dateFin;
	private List<Planning_Vacation> listVacation;
	private Users userModif;
	private String dateModif;
	private String textCell;

	public Integer getPiloteID() {
		return piloteID;
	}

	public void setPiloteID(Integer piloteID) {
		this.piloteID = piloteID;
	}

	public String getPilote() {
		return pilote;
	}

	public void setPilote(String pilote) {
		this.pilote = pilote;
	}

	public Integer getSelectedVacation() {
		return selectedVacation;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	public Integer getVacationID() {
		return vacationID;
	}

	public void setVacationID(Integer vacationID) {
		this.vacationID = vacationID;
	}

	public String getVacation() {
		return vacation;
	}

	public void setVacation(String vacation) {
		this.vacation = vacation;
	}

	public Integer getSelectedVacationn() {
		return selectedVacation;
	}

	public void setSelectedVacation(Integer selectedVacation) {
		this.selectedVacation = selectedVacation;
	}

	public String getTextCell() {
		return textCell;
	}

	public void setTextCell(String textCell) {
		this.textCell = textCell;
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

	public List<Planning_Vacation> getListVacation() {
		return listVacation;
	}

	public void setListVacation(List<Planning_Vacation> listVacation) {
		this.listVacation = listVacation;
	}

	public Users getUserModif() {
		return userModif;
	}

	public void setUserModif(Users userModif) {
		this.userModif = userModif;
	}

	public String getDateModif() {
		return dateModif;
	}

	public void setDateModif(String dateModif) {
		this.dateModif = dateModif;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		try {
			Users user = UsersDatabaseService.get(piloteID);
			Date d = DateService.strToDate(date);
			Calendar c = Calendar.getInstance();
			c.setTime(d);
			if (PlanningModifPonctuelleDatabaseService.userExists(user)) {
				Planning_Modif_Ponctuelle pmp = PlanningModifPonctuelleDatabaseService.getByUserAndDate(user, c.getTime());
				if(pmp != null){
					userModif = pmp.getIdUserModif();
					textCell = pmp.getTextCell();
					dateModif = "Le " + getJour(pmp.getDateModif()) + " " + ShowPlanningSemaineAction.dateToStr(pmp.getDateModif());
				}
			}

			selectedVacation = vacationID;
			vacation = vacationID != null ? PlanningVacationsDatabaseService.get(vacationID).getLibelle() : "";
			pilote = user.getPrenom() + " " + user.getNom();
			dateDebut = DateService.strToDate(date);
			dateFin = DateService.strToDate(date);
			listVacation = PlanningVacationsDatabaseService.getAll();
			
			//vérification des vacations speciaux
			List<Planning_Vacation> toDelete = new ArrayList<Planning_Vacation>();
			for(Planning_Vacation vacationSpeciale : listVacation){
				if (vacationSpeciale.getLibelle().equalsIgnoreCase("Backup")){
					toDelete.add(vacationSpeciale);
				}
				if (vacationSpeciale.getLibelle().equalsIgnoreCase("Maladie")){
					toDelete.add(vacationSpeciale);
				}
				if (vacationSpeciale.getLibelle().equalsIgnoreCase("Visite Médicale")){
					toDelete.add(vacationSpeciale);
				}
			}
			listVacation.removeAll(toDelete);
			return OK;
		}
		catch (Exception e) {
			erreurLogger.error("Redirection vers la modification d'une vacation de pilote : ", e);
			error = getText("error.message.generique") + " : " + e.getMessage();
			return ERROR;
		}
	}
	
	private String getJour(Date date) {
		Integer jour = DateService.getJour(date);
		String jourStr = "";
		if (jour.equals(1)) {
			jourStr = "Lundi";
		}
		if (jour.equals(2)) {
			jourStr = "Mardi";
		}
		if (jour.equals(3)) {
			jourStr = "Mercredi";
		}
		if (jour.equals(4)) {
			jourStr = "Jeudi";
		}
		if (jour.equals(5)) {
			jourStr = "Vendredi";
		}
		if (jour.equals(6)) {
			jourStr = "Samedi";
		}
		if (jour.equals(7)) {
			jourStr = "Dimanche";
		}
		return jourStr;
	}
}
