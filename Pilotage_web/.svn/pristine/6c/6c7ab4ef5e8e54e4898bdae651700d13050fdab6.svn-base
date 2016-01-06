package pilotage.machines.actions.os;

import java.text.MessageFormat;

import pilotage.database.historique.HistoriqueDatabaseService;
import pilotage.database.machine.MachineOSDatabaseService;
import pilotage.database.machine.MachinesListesDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;


public class SupprimerMachineOSAction extends AbstractAction{

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
		try{
			if(MachinesListesDatabaseService.hasMachineWithOs(selectRow)){
				error = getText("machine.OS.suppression.failed");
				return false;
			}
			return true;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Suppression d'un OS - ", e);
			return false;	
		}
	}

	@Override
	protected String executeMetier() {
		try{
			String libelle = MachineOSDatabaseService.get(selectRow).getNom_OS();
			MachineOSDatabaseService.delete(selectRow);
			info = MessageFormat.format(getText("machine.OS.suppression.valide"), new Object[]{libelle});
			HistoriqueDatabaseService.create(null, MessageFormat.format(getText("historique.machine.OS.suppression"), new Object[]{selectRow,libelle}), (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_MACHINES);

			return OK;
		}
		catch (Exception e){
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Suppression d'un OS - ", e);
			return ERROR;
		}
	}
}
