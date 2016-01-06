package pilotage.admin.actions.module;

import java.util.List;

import pilotage.admin.metier.Module;
import pilotage.database.admin.ModuleDatabaseService;
import pilotage.framework.AbstractAdminAction;

public class ShowModuleAdminAction extends AbstractAdminAction{

	private static final long serialVersionUID = 554605792514978167L;
	
	private List<Module> listModule;
	
	public List<Module> getListModule() {
		return listModule;
	}

	public void setListModule(List<Module> listModule) {
		this.listModule = listModule;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		try{
			listModule = ModuleDatabaseService.getAll();
			return OK;
		} catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Affichage de la liste de module ", e);
			return ERROR;
		}
	}
}