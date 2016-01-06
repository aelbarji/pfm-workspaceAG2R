package pilotage.machines.actions.type;

import java.util.List;

import pilotage.database.machine.MachineTypeDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Machines_Type;

public class RedirectModifyMachineTypeAction extends AbstractAction {
	
	private static final long serialVersionUID = -2136820498896120263L;
	
	private int selectRow;
	private String libelle;
	private List<Machines_Type> listMachineType;
	
	public int getSelectRow() {
		return selectRow;
	}

	public void setSelectRow(int selectRow) {
		this.selectRow = selectRow;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public List<Machines_Type> getListMachineType() {
		return listMachineType;
	}

	public void setListMachineType(List<Machines_Type> listMachineType) {
		this.listMachineType = listMachineType;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		try {
			libelle = MachineTypeDatabaseService.get(selectRow).getType();
			return OK;
		}
		catch (Exception e) {
			listMachineType = MachineTypeDatabaseService.getAll();
			error = getText("error.message.generique") + " : " + e.getMessage();
			return ERROR;
		}
	}
}
