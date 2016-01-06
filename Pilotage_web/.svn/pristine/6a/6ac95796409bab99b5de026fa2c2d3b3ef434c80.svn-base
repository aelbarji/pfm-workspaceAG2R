package pilotage.machines.actions.service;

import java.text.MessageFormat;

import pilotage.database.historique.HistoriqueDatabaseService;
import pilotage.database.machine.MachineInterlocuteurDatabaseService;
import pilotage.database.machine.MachinesListesDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;

public class SupprimerMachineInterlocuteurAction extends AbstractAction{

	private static final long serialVersionUID = 5215317819756935397L;
	private int selectRow;
	
	private String sort;
	private String sens;
	private int page;
	private int nrPages;
	private int nrPerPage;

	public int getSelectRow() {
		return selectRow;
	}

	public void setSelectRow(int selectRow) {
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
		try{
			if(MachinesListesDatabaseService.hasMachineWithInterlocuteur(selectRow)){
				error = getText("machine.gerant.suppression.failed");
				return false;
			}
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Suppression d'un service gérant - ", e);
			return false;
		}

		return true;
	}

	@Override
	protected String executeMetier() {
		try{
			String libelle = MachineInterlocuteurDatabaseService.get(selectRow).getNomService();
			MachineInterlocuteurDatabaseService.delete(selectRow);
			info = MessageFormat.format(getText("machine.gerant.suppression.valide"), new Object[]{libelle});
			HistoriqueDatabaseService.create(null,MessageFormat.format(getText("historique.machine.gerant.suppression"), new Object[]{selectRow,libelle}), (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_MACHINES);

			return OK;
		}
		catch (Exception e){
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Suppression d'un service gérant - ", e);
			return ERROR;
		}
	}
}
