package pilotage.astreintes.actions.domaine;

import java.util.List;

import pilotage.database.astreintes.AstreinteDomaineDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Astreinte_Domaine;

public class ShowAstreinteDomaineAction extends AbstractAction{

	private static final long serialVersionUID = 2198371780235465657L;

	private List<Astreinte_Domaine> aDomaines;
	
	private String astreinteDomaine;
	
	public List<Astreinte_Domaine> getADomaines() {
		return aDomaines;
	}

	public void setADomaines(List<Astreinte_Domaine> aDomaines) {
		this.aDomaines = aDomaines;
	}

	public String getAstreinteDomaine() {
		return astreinteDomaine;
	}

	public void setAstreinteDomaine(String astreinteDomaine) {
		this.astreinteDomaine = astreinteDomaine;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		if(error == null || error.equals(""))
			astreinteDomaine = null;
		
		aDomaines = AstreinteDomaineDatabaseService.getAll();
		return OK;
	}

}
