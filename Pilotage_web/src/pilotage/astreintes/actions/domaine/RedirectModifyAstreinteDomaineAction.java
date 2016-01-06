package pilotage.astreintes.actions.domaine;

import pilotage.database.astreintes.AstreinteDomaineDatabaseService;
import pilotage.framework.AbstractAction;

public class RedirectModifyAstreinteDomaineAction extends AbstractAction{

	private static final long serialVersionUID = 1467814879097345375L;

	private Integer domaineID;
	private String astreinteDomaine;
	
	public Integer getDomaineID() {
		return domaineID;
	}

	public void setDomaineID(Integer domaineID) {
		this.domaineID = domaineID;
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
		
		astreinteDomaine = AstreinteDomaineDatabaseService.get(domaineID).getDomaine();
		return OK;
	}

}
