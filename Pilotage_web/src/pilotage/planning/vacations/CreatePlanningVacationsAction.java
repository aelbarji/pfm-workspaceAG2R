package pilotage.planning.vacations;

import java.text.MessageFormat;

import pilotage.database.historique.HistoriqueDatabaseService;
import pilotage.database.planning.PlanningVacationsDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;
import pilotage.service.date.DateService;


public class CreatePlanningVacationsAction extends AbstractAction {
	
	private static final long serialVersionUID = -1299589526541605339L;
	
	private String codeCouleur;
	private String heureDebut;
	private String heureFin;
	private String libelle;
	private Integer partiJour;

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
			if(PlanningVacationsDatabaseService.exists(null, libelle)){
				error = MessageFormat.format(getText("planning.vacation.creation.existe.deja"), libelle);
				return false;
			}
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Création d'une vacation de planning - ", e);
			return false;
		}
		return true;
	}

	@Override
	protected String executeMetier() {
		try{
			Integer id = PlanningVacationsDatabaseService.create(codeCouleur, DateService.getTimeFromString(heureDebut), DateService.getTimeFromString(heureFin), libelle, partiJour);
			info = MessageFormat.format(getText("planning.vacation.creation.valide"), new Object[]{libelle});
			HistoriqueDatabaseService.create(null, MessageFormat.format(getText("historique.planning.vacation.creation"), new Object[]{libelle,id}), (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_PLANNING);
			libelle = null;
			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Création d'une vacation de planning - ", e);
			return ERROR;
		}
	}
}