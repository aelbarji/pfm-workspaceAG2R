package pilotage.planning.vacations;

import java.text.MessageFormat;

import pilotage.database.historique.HistoriqueDatabaseService;
import pilotage.database.planning.PlanningVacationsDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Planning_Vacation;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;
import pilotage.service.date.DateService;

public class ModifyPlanningVacationsAction extends AbstractAction{

	private static final long serialVersionUID = 7013818341148152623L;
	
	private Integer selectRow;
	private String codeCouleur;
	private String heureDebut;
	private String heureFin;
	private String libelle;
	private Integer partiJour;
	
	public Integer getSelectRow() {
		return selectRow;
	}

	public void setSelectRow(Integer selectRow) {
		this.selectRow = selectRow;
	}

	public String getCodeCouleur() {
		return codeCouleur;
	}

	public void setCodeCouleur(String codeCouleur) {
		this.codeCouleur = codeCouleur;
	}

	public String getHeureDebut() {
		return heureDebut;
	}

	public void setHeureDebut(String heureDebut) {
		this.heureDebut = heureDebut;
	}

	public String getHeureFin() {
		return heureFin;
	}

	public void setHeureFin(String heureFin) {
		this.heureFin = heureFin;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public Integer getPartiJour() {
		return partiJour;
	}

	public void setPartiJour(Integer partiJour) {
		this.partiJour = partiJour;
	}

	@Override
	protected boolean validateMetier() {
		try{
			if(PlanningVacationsDatabaseService.exists(selectRow, libelle)){
				error = MessageFormat.format(getText("planning.vacation.creation.existe.deja"), libelle);
				Planning_Vacation pv = PlanningVacationsDatabaseService.get(selectRow);
				libelle = pv.getLibelle();
				return false;
			}
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Modification d'une vacation de planning - ", e);
			return false;
		}
		return true;
	}

	@Override
	protected String executeMetier() {
		try{
			String historique = " ";
			Planning_Vacation pv = PlanningVacationsDatabaseService.get(selectRow);
			if (!codeCouleur.equals(pv.getCodeCouleur())) {
				historique += "codeCouleur, ";
			}
			if (!DateService.getTimeFromString(heureDebut).equals(pv.getHeureDebut())) {
				historique += "dateFin, ";
			}
			if (!DateService.getTimeFromString(heureFin).equals(pv.getHeureFin())) {
				historique += "dateDebut, ";
			}
			if (!libelle.equals(pv.getLibelle())) {
				historique += "libelle, ";
			}
			
			PlanningVacationsDatabaseService.modify(selectRow, codeCouleur, DateService.getTimeFromString(heureDebut), DateService.getTimeFromString(heureFin), libelle, partiJour);
			
			info = MessageFormat.format(getText("planning.vacation.modification.valide"), new Object[]{libelle});
			
			HistoriqueDatabaseService.create(null, MessageFormat.format(getText("historique.planning.vacation.modification"), new Object[]{historique, selectRow}), (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_PLANNING);
			
			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Modification d'une vacation de planning - ", e);
			return ERROR;
		}
	}
}