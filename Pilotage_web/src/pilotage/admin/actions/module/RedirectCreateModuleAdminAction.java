package pilotage.admin.actions.module;

import java.util.List;

import pilotage.database.admin.ModuleDatabaseService;
import pilotage.framework.AbstractAdminAction;
import pilotage.admin.metier.Module;

public class RedirectCreateModuleAdminAction extends AbstractAdminAction{

	private static final long serialVersionUID = -5985965529299734268L;
	
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
		} catch(Exception e){
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Redirection vers la création de module ", e);
			return ERROR;
		}
	}

}
