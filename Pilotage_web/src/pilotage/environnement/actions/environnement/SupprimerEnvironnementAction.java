package pilotage.environnement.actions.environnement;

import java.text.MessageFormat;

import pilotage.database.applicatif.ApplicatifDatabaseService;
import pilotage.database.checklist.ChecklistBaseDatabaseService;
import pilotage.database.environnement.EnvironnementDatabaseService;
import pilotage.database.historique.HistoriqueDatabaseService;
import pilotage.database.incidents.IncidentsDatabaseService;
import pilotage.database.machine.MachinesListesDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;

public class SupprimerEnvironnementAction extends AbstractAction{

	private static final long serialVersionUID = 4625298924249700015L;
	
	private Integer selectRow;
	private String  sort;
	private String  sens;
	private int page;
	private int nrPages;
	private int nrPerPage;
	
	public Integer getSelectRow() {
		return selectRow;
	}

	public void setSelectRow(Integer selectRow) {
		this.selectRow = selectRow;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getSens() {
		return sens;
	}

	public void setSens(String sens) {
		this.sens = sens;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getNrPages() {
		return nrPages;
	}

	public void setNrPages(int nrPages) {
		this.nrPages = nrPages;
	}

	public int getNrPerPage() {
		return nrPerPage;
	}

	public void setNrPerPage(int nrPerPage) {
		this.nrPerPage = nrPerPage;
	}
	
	@Override
	protected boolean validateMetier() {
		try {
			//vérifie si une appli_envir, une checklist_base, un incident, ou une machine référence cet environnement
			if (MachinesListesDatabaseService.hasEnvironnement(selectRow)
					|| ChecklistBaseDatabaseService.hasEnvironnement(selectRow)
					|| IncidentsDatabaseService.hasIncidentWithEnvironnement(selectRow)
					|| ApplicatifDatabaseService.hasEnvironnement(selectRow)) {
				error = getText("environnement.suppression.failed");
				return false;
			}
			return true;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + ":" + e.getMessage();
			erreurLogger.error("Suppression d'environnement -",e);
			return false;
		}
	}

	@Override
	protected String executeMetier() {
		try {
			//récupération du libelle
			String libelle = EnvironnementDatabaseService.get(selectRow).getEnvironnement();
			
			//suppression du environnement
			EnvironnementDatabaseService.delete(selectRow);
			
			HistoriqueDatabaseService.create(null, MessageFormat.format(getText("historique.environnement.suppression"),new Object[]{selectRow,libelle}), (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_ENVIRONNEMENT);

			//message de validation
			info = MessageFormat.format(getText("environnement.suppression.valide"), new Object[]{libelle});
			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + ":" + e.getMessage();
			erreurLogger.error("Suppression d'un environnement - ",e);
		    return ERROR;
		}
	}
}
