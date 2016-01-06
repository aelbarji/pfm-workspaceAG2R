package pilotage.admin.actions.sousmodule;

import pilotage.framework.AbstractAdminAction;

public class RedirectCreateSousModuleAdminAction extends AbstractAdminAction{

	private static final long serialVersionUID = -7627906023607376447L;
	private Integer moduleID;

	public Integer getModuleID() {
		return moduleID;
	}

	public void setModuleID(Integer moduleID) {
		this.moduleID = moduleID;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		try{
			return OK;
		}catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Redirection vers la création de sous-module ", e);
			return ERROR;
		}
	}

}
