package pilotage.gup.admin.service;

import pilotage.database.gup.ComServiceDatabaseService;
import pilotage.framework.AbstractAction;

public class DeleteComServiceAction extends AbstractAction{

	private static final long serialVersionUID = -9211671960436450871L;
	private Integer selectedID;
	
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
			ComServiceDatabaseService.delete(selectedID);
			return OK;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

}
