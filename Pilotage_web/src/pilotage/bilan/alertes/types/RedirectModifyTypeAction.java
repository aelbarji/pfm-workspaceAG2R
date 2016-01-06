package pilotage.bilan.alertes.types;

import pilotage.database.bilan.AlertesTypeDatabaseService;
import pilotage.framework.AbstractAction;

public class RedirectModifyTypeAction extends AbstractAction {

	private static final long serialVersionUID = 2094996746370918014L;
	private int selectedID;
	private String libelle;

	public int getSelectedID() {
		return selectedID;
	}

	public void setSelectedID(int selectedID) {
		this.selectedID = selectedID;
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
		libelle = AlertesTypeDatabaseService.get(selectedID).getType();
		return OK;
	}
}
