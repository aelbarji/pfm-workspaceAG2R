package pilotage.admin.actions.checklist;

import java.util.List;

import pilotage.admin.metier.Checklist_color;
import pilotage.database.admin.ChecklistColorDatabaseService;
import pilotage.framework.AbstractAdminAction;

public class ListChecklistColorAdminAction extends AbstractAdminAction {

	private static final long serialVersionUID = 2088541307503207125L;
	
	private List<Checklist_color> listChecklistColor;

	public List<Checklist_color> getListChecklistColor() {
		return listChecklistColor;
	}


	public void setListChecklistColor(List<Checklist_color> listChecklistColor) {
		this.listChecklistColor = listChecklistColor;
	}


	@Override
	protected boolean validateMetier() {
		return true;
	}

	
	@Override
	protected String executeMetier() {
		try {
			listChecklistColor = ChecklistColorDatabaseService.getAll();
			return OK;
		}
		catch (Exception e) {
			erreurLogger.error("Récupération des couleurs de checklist : ", e);
			error = getText("error.message.generique") + " : " + e.getMessage();
			return ERROR;
		}
	}

}
