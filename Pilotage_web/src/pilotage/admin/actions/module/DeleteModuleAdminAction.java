package pilotage.admin.actions.module;

import java.text.MessageFormat;
import java.util.List;

import pilotage.admin.actions.sousmodule.DeleteSousModuleAdminAction;
import pilotage.admin.metier.Sous_Module;
import pilotage.database.admin.ModuleDatabaseService;
import pilotage.database.admin.SousModuleDatabaseService;
import pilotage.framework.AbstractAdminAction;

public class DeleteModuleAdminAction extends AbstractAdminAction{


	private static final long serialVersionUID = -8223615754302646392L;
	private Integer moduleID;
	private List<Sous_Module> listSousModule;

	public Integer getModuleID() {
		return moduleID;
	}

	public void setModuleID(Integer moduleID) {
		this.moduleID = moduleID;
	}

	public List<Sous_Module> getListSousModule() {
		return listSousModule;
	}

	public void setListSousModule(List<Sous_Module> listSousModule) {
		this.listSousModule = listSousModule;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		try {
			listSousModule = SousModuleDatabaseService.getAllFromModule(moduleID);
			int i = 0;
			for(Sous_Module s : listSousModule){
				i = DeleteSousModuleAdminAction.deleteSousModule(s);
				if(i == 0){
					error = MessageFormat.format(getText("suppression impossible car les droits sont affectés à un profil"),"nom");
					return OK;
				}
			}
			if(i != 0){
				ModuleDatabaseService.delete(moduleID);
			}
			return OK;
		} catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Suppression d'un module - validation ", e);
			return ERROR;
		}	
	}

}
