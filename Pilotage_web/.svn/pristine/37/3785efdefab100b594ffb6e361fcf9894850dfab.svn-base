package pilotage.planning.planning;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pilotage.database.planning.PlanningEquipeDatabaseService;
import pilotage.database.planning.PlanningVacationsDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Planning_Nom_Equipe;
import pilotage.metier.Planning_Vacation;
import pilotage.metier.Users;
import pilotage.service.date.DateService;


public class showModifyEquipesVacationAction extends AbstractAction {
	
		private static final long serialVersionUID = -5031601800701618030L;
	
		private Integer equipeID;
		private String equipe;
		private String date;
		private Integer vacationID;
		private String vacation;
		private Integer selectedVacation;
		private Date dateDebut;
		private Date dateFin;
		private List<Planning_Vacation> listVacation;
		private Users userModif;
		private String dateModif;

		public Integer getEquipeID() {
			return equipeID;
		}

		public void setEquipeID(Integer equipeID) {
			this.equipeID = equipeID;
		}

		public String getEquipe() {
			return equipe;
		}

		public void setEquipe(String equipe) {
			this.equipe = equipe;
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
				Planning_Nom_Equipe nomEquip = PlanningEquipeDatabaseService.get(equipeID);
				selectedVacation = vacationID;
				vacation = vacationID != null ? PlanningVacationsDatabaseService.get(vacationID).getLibelle() : "";
				
				if (nomEquip != null){
					equipe = nomEquip.getNomEquipe();
				}
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
				erreurLogger.error("Redirection vers la modification d'une vacation d'équipe : ", e);
				error = getText("error.message.generique") + " : " + e.getMessage();
				return ERROR;
			}
		}
		
		@SuppressWarnings("unused")
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
//	}
}
