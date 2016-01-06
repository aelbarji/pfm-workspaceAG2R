package pilotage.machines.actions.liste;

import java.text.MessageFormat;

import pilotage.database.historique.HistoriqueDatabaseService;
import pilotage.database.incidents.IncidentsDatabaseService;

import pilotage.database.machine.MachinesListesDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Machines_Liste;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;
import pilotage.utils.Pagination;

public class SupprimerMachineListAction extends AbstractAction{

	private static final long serialVersionUID = 5215317819756935397L;
	private int selectRow;

	private String sort;
	private String sens;
	private int page;
	private int nrPages;
	private int nrPerPage;

	private Pagination<Machines_Liste> pagination;

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

	public Pagination<Machines_Liste> getPagination() {
		return pagination;
	}

	public void setPagination(Pagination<Machines_Liste> pagination) {
		this.pagination = pagination;
	}

	@Override
	protected boolean validateMetier() {
		try{
			if(IncidentsDatabaseService.hasIncidentWithMachine(selectRow)){
				error = getText("machine.suppression.failed");
				return false;
			}
			return true;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Suppression d'une machine - ", e);
			return false;	
		}
	}

	@Override
	protected String executeMetier() {
		try{
			String nom = MachinesListesDatabaseService.get(selectRow).getNom();
			MachinesListesDatabaseService.delete(selectRow);
			info = MessageFormat.format(getText("machine.suppression.valide"), new Object[]{nom});
			HistoriqueDatabaseService.create(null, MessageFormat.format(getText("historique.machine.supprimer") ,new Object[]{selectRow}), (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_MACHINES);
			return OK;
		}
		catch (Exception e){
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Suppression d'une machine - ", e);
			return ERROR;
		}

	}
	

}
