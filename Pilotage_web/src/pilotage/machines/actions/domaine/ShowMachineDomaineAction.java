package pilotage.machines.actions.domaine;

import java.util.List;

import pilotage.database.machine.MachineDomaineDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Domaine_Wind;

public class ShowMachineDomaineAction extends AbstractAction {
	private static final long serialVersionUID = -4037713125016637085L;
	private List<Domaine_Wind> listMachineDomaine;
	private String libelle;

	public List<Domaine_Wind> getListMachineDomaine() {
		return listMachineDomaine;
	}

	public void setListMachineDomaine(List<Domaine_Wind> listMachineDomaine) {
		this.listMachineDomaine = listMachineDomaine;
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
		
		listMachineDomaine = MachineDomaineDatabaseService.getAll();
		return OK;
	}
}