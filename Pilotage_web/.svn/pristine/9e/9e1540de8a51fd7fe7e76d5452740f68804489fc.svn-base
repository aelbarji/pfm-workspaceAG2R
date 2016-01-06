package pilotage.gup.admin.service;

import pilotage.database.gup.ComServiceDatabaseService;
import pilotage.framework.AbstractAction;

public class ModifyComServiceAction extends AbstractAction{

	private static final long serialVersionUID = -3927929538365484035L;
	private Integer selectedID;
	private String nom;
	
	public Integer getSelectedID() {
		return selectedID;
	}

	public void setSelectedID(Integer selectedID) {
		this.selectedID = selectedID;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		try {
			ComServiceDatabaseService.modify(selectedID, nom);
			return OK;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

}
