package pilotage.gup.admin.domaine;

import pilotage.database.gup.DomaineDatabaseService;
import pilotage.framework.AbstractAction;

public class CreateComDomaineAction extends AbstractAction{

	private static final long serialVersionUID = -1307963146585488338L;
	private String nom;
	
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
			DomaineDatabaseService.create(nom);
			return OK;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

}
