package pilotage.planning.vacations;

import java.util.List;

import pilotage.database.planning.PlanningPatieJourDatabaseService;
import pilotage.database.planning.PlanningVacationsDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Planning_Cra_Partie_Jour;
import pilotage.metier.Planning_Vacation;
import pilotage.service.date.DateService;

public class RedirectModifyPlanningVacationsAction extends AbstractAction {
	
	private static final long serialVersionUID = -2136820498896120263L;
	
	private Integer selectRow;
	private String codeCouleur;
	private String heureDebut;
	private String heureFin;
	private String libelle;
	List<Planning_Cra_Partie_Jour> lpcpj;

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

	public List<Planning_Cra_Partie_Jour> getLpcpj() {
		return lpcpj;
	}

	public void setLpcpj(List<Planning_Cra_Partie_Jour> lpcpj) {
		this.lpcpj = lpcpj;
	}

	public Integer getPartiJour() {
		return partiJour;
	}

	public void setPartiJour(Integer partiJour) {
		this.partiJour = partiJour;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		try {
			lpcpj = PlanningPatieJourDatabaseService.getAll();
			Planning_Vacation pv = PlanningVacationsDatabaseService.get(selectRow);
			codeCouleur = pv.getCodeCouleur();
			heureDebut = DateService.getTime(pv.getHeureDebut(), null);
			heureFin = DateService.getTime(pv.getHeureFin(), null);
			libelle = pv.getLibelle();
			setPartiJour(pv.getPartiJour().getId());
			return OK;
		}
		catch (Exception e) {
			erreurLogger.error("Redirection vers la modification d'une vacation de planning : ", e);
			error = getText("error.message.generique") + " : " + e.getMessage();
			return ERROR;
		}
	}
}
