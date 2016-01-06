package pilotage.admin.actions.documents;


import java.util.List;

import pilotage.framework.AbstractAdminAction;


public class ShowChargementDocConsigneAdminAction extends AbstractAdminAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4687128755868497332L;
	private List<String> repertoires;

	public List<String> getRepertoires() {
		return repertoires;
	}

	public void setRepertoires(List<String> repertoires) {
		this.repertoires = repertoires;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {	
		return OK;
	}
}
