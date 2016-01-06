package pilotage.machines.actions.domaine;

import java.text.MessageFormat;

import pilotage.database.historique.HistoriqueDatabaseService;
import pilotage.database.machine.MachineDomaineDatabaseService;
import pilotage.database.machine.MachinesListesDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;


public class SupprimerMachineDomaineAction extends AbstractAction{

	private static final long serialVersionUID = 5215317819756935397L;
	
	private int selectRow;

	/**
	 * @return the selectRow
	 */
	public int getSelectRow() {
		return selectRow;
	}

	/**
	 * @param selectRow the selectRow to set
	 */
	public void setSelectRow(int selectRow) {
		this.selectRow = selectRow;
	}

	@Override
	protected boolean validateMetier() {
		if(MachinesListesDatabaseService.hasMachineWithDomaine(selectRow)){
			error = getText("machine.domaine.suppression.failed");
			return false;
		}
		return true;
	}

	@Override
	protected String executeMetier() {
		try{
			String libelle = MachineDomaineDatabaseService.get(selectRow).getNomDomaine();
			MachineDomaineDatabaseService.delete(selectRow);
			info = MessageFormat.format(getText("machine.domaine.suppression.valide"), new Object[]{libelle});
			HistoriqueDatabaseService.create(null, MessageFormat.format(getText("historique.machine.domaine.suppression"), new Object[]{selectRow,libelle}), (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_MACHINES);
			return OK;
		}
		catch (Exception e){
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Suppression d'un domaine de machine - ", e);
			return ERROR;
		}
	}
}
