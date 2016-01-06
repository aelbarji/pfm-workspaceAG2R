package pilotage.gup.admin.domaine;

import pilotage.database.gup.DomaineDatabaseService;
import pilotage.framework.AbstractAction;

public class ModifyComDomaineAction extends AbstractAction{

	private static final long serialVersionUID = -7707301802286468130L;
	private String nom;
	private Integer selectedID;
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Integer getSelectedID() {
		return selectedID;
	}

	public void setSelectedID(Integer selectedID) {
		this.selectedID = selectedID;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		try {
			DomaineDatabaseService.modify(selectedID, nom);
			return OK;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

}
