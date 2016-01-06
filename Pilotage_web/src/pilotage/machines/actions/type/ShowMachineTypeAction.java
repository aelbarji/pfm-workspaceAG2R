package pilotage.machines.actions.type;

import java.util.List;

import pilotage.database.machine.MachineTypeDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Machines_Type;

public class ShowMachineTypeAction extends AbstractAction {
	private static final long serialVersionUID = -4037713125016637085L;
	private List<Machines_Type> listMachineType;
	
	private String libelle;

	public List<Machines_Type> getListMachineType() {
		return listMachineType;
	}

	public void setListMachineType(List<Machines_Type> listMachineType) {
		this.listMachineType = listMachineType;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		if(error == null || error.equals(""))
			libelle = null;
		
		listMachineType = MachineTypeDatabaseService.getAll();
		return OK;
	}
}
