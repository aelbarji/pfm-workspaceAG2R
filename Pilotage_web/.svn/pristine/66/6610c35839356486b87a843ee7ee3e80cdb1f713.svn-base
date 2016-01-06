package pilotage.gup.admin.domaine;


import pilotage.database.gup.DomaineDatabaseService;
import pilotage.framework.AbstractAction;

public class DeleteComDomaineAction extends AbstractAction{

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
			DomaineDatabaseService.delete(selectedID);
			return OK;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

}
