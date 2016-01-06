package pilotage.admin.actions.module;

import java.text.MessageFormat;
import java.util.List;

import pilotage.admin.metier.Module;
import pilotage.database.admin.ModuleDatabaseService;
import pilotage.framework.AbstractAdminAction;

public class CreateModuleAdminAction extends AbstractAdminAction {

	private static final long serialVersionUID = -5776878545074746871L;
	private String nomMod;

	public String getNomMod() {
		return nomMod;
	}

	public void setNomMod(String nomMod) {
		this.nomMod = nomMod;
	}

	@Override
	protected boolean validateMetier() {
		List<Module> listMod = ModuleDatabaseService.getAll();
		
		for(Module m : listMod){
			if(m.getNom().equals(nomMod)){
				error = MessageFormat.format(getText("ce module existe déjà"),"nom");
				return false;
			}	
		}
		return true;
	}

	@Override
	protected String executeMetier() {
		try {
			ModuleDatabaseService.create(nomMod);
			return OK;
		} catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Création d'un module - validation ", e);
			return ERROR;
		}

	}

}
