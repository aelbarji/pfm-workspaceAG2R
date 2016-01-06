package pilotage.bilan.flux.cft;

import pilotage.database.bilan.FluxCFTDatabaseService;
import pilotage.framework.AbstractAction;

public class RedirectModifyFluxCFTAction extends AbstractAction {

	private static final long serialVersionUID = 5459988898477765542L;
	private Integer selectedID;
	private String libelle;
	
	/**
	 * @return the selectedID
	 */
	public Integer getSelectedID() {
		return selectedID;
	}

	/**
	 * @param selectedID the selectedID to set
	 */
	public void setSelectedID(Integer selectedID) {
		this.selectedID = selectedID;
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
		return true;
	}

	@Override
	protected String executeMetier() {
		libelle = FluxCFTDatabaseService.get(selectedID).getLibelle();
		return OK;
	}

}
