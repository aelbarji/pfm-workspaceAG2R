package pilotage.admin.actions.sousmodule;

import java.util.List;

import pilotage.database.admin.SousModuleDatabaseService;
import pilotage.framework.AbstractAdminAction;
import pilotage.admin.metier.Sous_Module;

public class ShowSousModuleAdminAction extends AbstractAdminAction{

	private static final long serialVersionUID = 1206931849558542245L;
	
	private List<Sous_Module> listSousModule;
	private Integer moduleID;

	public List<Sous_Module> getListSousModule() {
		return listSousModule;
	}

	public void setListSousModule(List<Sous_Module> listSousModule) {
		this.listSousModule = listSousModule;
	}

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
		listSousModule = SousModuleDatabaseService.getAllFromModule(moduleID);
		return OK;
		} catch(Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Affichage de la liste de sous-module ", e);
			return ERROR;
		}
	}

}
