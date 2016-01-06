package pilotage.admin.actions.sousmodule;

import java.util.List;

import pilotage.admin.metier.Module;
import pilotage.admin.metier.Sous_Module;
import pilotage.database.admin.ModuleDatabaseService;
import pilotage.database.admin.SousModuleDatabaseService;
import pilotage.framework.AbstractAdminAction;

public class RedirectModifySousModuleAdminAction extends AbstractAdminAction{

	private static final long serialVersionUID = 3115699343176444088L;
	private Integer sousModuleID;
	private List<Module> listModule;
	private Sous_Module sm;

	public Integer getSousModuleID() {
		return sousModuleID;
	}
	
	public void setSousModuleID(Integer sousModuleID) {
		this.sousModuleID = sousModuleID;
	}

	public List<Module> getListModule() {
		return listModule;
	}

	public void setListModule(List<Module> listModule) {
		this.listModule = listModule;
	}

	public Sous_Module getSm() {
		return sm;
	}

	public void setSm(Sous_Module sm) {
		this.sm = sm;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		try{
			sm = SousModuleDatabaseService.get(sousModuleID);
			listModule = ModuleDatabaseService.getAll();
			return OK;
		} catch(Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Redirection vers la modification de sous-module ", e);
			return ERROR;
		}
	}

}
