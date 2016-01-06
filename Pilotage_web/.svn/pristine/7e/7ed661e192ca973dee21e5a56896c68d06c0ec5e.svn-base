package pilotage.machines.actions.os;

import java.text.MessageFormat;

import pilotage.database.historique.HistoriqueDatabaseService;
import pilotage.database.machine.MachineOSDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;


public class ModifyMachineOSAction extends AbstractAction{

	private static final long serialVersionUID = 7013818341148152623L;
	
	private Boolean libelleChanged;
	private Integer selectRow;
	private String libelle;

	
	/**
	 * @return
	 */
	public Boolean getLibelleChanged() {
		return libelleChanged;
	}

	/**
	 * @param libelleChanged
	 */
	public void setLibelleChanged(Boolean libelleChanged) {
		this.libelleChanged = libelleChanged;
	}

	/**
	 * @return the selectRow
	 */
	public Integer getSelectRow() {
		return selectRow;
	}

	/**
	 * @param selectRow the selectRow to set
	 */
	public void setSelectRow(Integer selectRow) {
		this.selectRow = selectRow;
	}
	
	/**
	 * @return the libelle
	 */
	public String getLibelle() {
		return libelle;
	}

	/**
	 * @param libelle the libelle to set
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	@Override
	protected boolean validateMetier() {
		try{
			if(libelleChanged && MachineOSDatabaseService.exists(selectRow, libelle)){
				error = MessageFormat.format(getText("machine.OS.creation.existe.deja"), libelle);
				libelle = MachineOSDatabaseService.get(selectRow).getNom_OS();
				return false;
			}
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Modification d'un OS - ", e);
			return false;
		}
		return true;
	}

	@Override
	protected String executeMetier() {
		try{
			if(libelleChanged)
				MachineOSDatabaseService.modify(selectRow, libelle);
			
			info = MessageFormat.format(getText("machine.OS.modification.valide"), new Object[]{libelle});
			HistoriqueDatabaseService.create(null, MessageFormat.format(getText("historique.machine.OS.modification"),new Object[]{selectRow}), (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_MACHINES);

			libelle = null;
			
			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Modification d'un OS - ", e);
			return ERROR;
		}
	}
}
