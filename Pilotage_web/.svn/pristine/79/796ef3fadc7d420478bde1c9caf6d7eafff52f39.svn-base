package pilotage.admin.actions.module;

import pilotage.database.admin.ModuleDatabaseService;
import pilotage.framework.AbstractAdminAction;

public class ModifyModuleAdminAction extends AbstractAdminAction{

	private static final long serialVersionUID = -943430239783632713L;
	private Integer moduleID;
	private String nom;
	
	
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
		try {
			ModuleDatabaseService.modify(moduleID, nom);
			return OK;
		} catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Modificaton d'un module - validation ", e);
			return ERROR;
		}
		
	}

}
