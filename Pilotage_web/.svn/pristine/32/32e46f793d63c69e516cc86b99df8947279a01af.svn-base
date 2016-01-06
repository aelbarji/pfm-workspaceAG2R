package pilotage.gup.admin.domaine;

import pilotage.database.gup.DomaineDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Com_domaine;

public class RedirectModifyComDomaineAction extends AbstractAction{

	private static final long serialVersionUID = -5106904062019747749L;
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
		Com_domaine dom = DomaineDatabaseService.get(selectedID);
		nom = dom.getNom();
		return OK;
	}

}
