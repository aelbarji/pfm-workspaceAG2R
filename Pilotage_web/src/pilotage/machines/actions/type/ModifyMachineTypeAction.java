package pilotage.machines.actions.type;

import java.text.MessageFormat;

import pilotage.database.historique.HistoriqueDatabaseService;
import pilotage.database.machine.MachineTypeDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;

public class ModifyMachineTypeAction extends AbstractAction{

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
			if(libelleChanged && MachineTypeDatabaseService.exists(selectRow, libelle)){
				error = MessageFormat.format(getText("machine.type.creation.existe.deja"), libelle);
				libelle = MachineTypeDatabaseService.get(selectRow).getType();
				return false;
			}
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Modification d'un type de machine - ", e);
			return false;
		}
		return true;
	}

	@Override
	protected String executeMetier() {
		try{
			if(libelleChanged)
				MachineTypeDatabaseService.modify(selectRow, libelle);
			
			info = MessageFormat.format(getText("machine.type.modification.valide"), new Object[]{libelle});
			HistoriqueDatabaseService.create(null, MessageFormat.format(getText("historique.machine.type.modification"),new Object[]{selectRow}), (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_MACHINES);
			libelle = null;
			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Modification d'un type de machine - ", e);
			return ERROR;
		}
	}
}
