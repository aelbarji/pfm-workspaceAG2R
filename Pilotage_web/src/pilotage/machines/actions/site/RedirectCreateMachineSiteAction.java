package pilotage.machines.actions.site;

import pilotage.framework.AbstractAction;

public class RedirectCreateMachineSiteAction extends AbstractAction {

	private static final long serialVersionUID = 2800115204815595744L;

	private String sort;
	private String sens;
	
	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getSens() {
		return sens;
	}

	public void setSens(String sens) {
		this.sens = sens;
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
