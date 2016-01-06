package pilotage.machines.actions.os;

import java.util.List;

import pilotage.database.machine.MachineOSDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Machine_Os;

public class ShowMachineOSAction extends AbstractAction {
	private static final long serialVersionUID = -4037713125016637085L;
	private List<Machine_Os> listMachineOS;
	private String libelle;

	public List<Machine_Os> getListMachineOS() {
		return listMachineOS;
	}

	public void setListMachineOS(List<Machine_Os> listMachineOS) {
		this.listMachineOS = listMachineOS;
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
		
		listMachineOS = MachineOSDatabaseService.getAll();
		return OK;
	}
}
