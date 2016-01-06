package pilotage.admin.actions.module;

import pilotage.database.admin.ModuleDatabaseService;
import pilotage.framework.AbstractAdminAction;

public class RedirectModifyModuleAdminAction extends AbstractAdminAction{

	private Integer moduleID;
	private String nom;
	private static final long serialVersionUID = 3115699343176444088L;

	public Integer getModuleID() {
		return moduleID;
	}

	public void setModuleID(Integer moduleID) {
		this.moduleID = moduleID;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		try{
			nom = ModuleDatabaseService.get(moduleID).getNom();
			moduleID = ModuleDatabaseService.get(moduleID).getId();
			return OK;
		} catch(Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Redirection vers la modification de module ", e);
			return ERROR;
		}
	}

}
