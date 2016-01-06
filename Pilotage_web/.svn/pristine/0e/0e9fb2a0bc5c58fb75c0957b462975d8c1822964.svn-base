package pilotage.gup.admin.service;

import pilotage.database.gup.ComServiceDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Com_Service;

public class RedirectModifyComServiceAction extends AbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5288985829159790167L;
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
		Com_Service com = ComServiceDatabaseService.get(selectedID);
		nom = com.getNom();
		return OK;
	}

}
