package pilotage.machines.actions.os;

import pilotage.database.machine.MachineOSDatabaseService;
import pilotage.framework.AbstractAction;

public class RedirectModifyMachineOSAction extends AbstractAction {
	private static final long serialVersionUID = -2136820498896120263L;
	
	private int selectRow;
	private String libelle;
	
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

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		try {
			libelle = MachineOSDatabaseService.get(selectRow).getNom_OS();
			return OK;
		}
		catch (Exception e) {
			erreurLogger.error("Redirection vers la page de création d'OS : ", e);
			error = getText("error.message.generique") + " : " + e.getMessage();
			return ERROR;
		}
	}
}
