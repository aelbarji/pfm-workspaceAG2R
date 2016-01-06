package pilotage.admin.actions.profil;

import java.util.ArrayList;
import java.util.List;

import pilotage.admin.metier.Droits_Liste;
import pilotage.admin.metier.Menu;
import pilotage.admin.metier.Module;
import pilotage.admin.metier.Sous_Module;
import pilotage.database.admin.DroitsListeDatabaseService;
import pilotage.database.admin.MenuDatabaseService;
import pilotage.database.admin.ModuleDatabaseService;
import pilotage.database.admin.SousModuleDatabaseService;
import pilotage.framework.AbstractAdminAction;

public class RedirectCreateProfilAdminAction extends AbstractAdminAction {

	private static final long serialVersionUID = 2800115204815595744L;
	private List<Menu> listMenu;
	private List<Droits_Liste> listDroits;
	private List<Module> listModule;
	private List<Sous_Module> listSousMod;
	private List<List<Sous_Module>> listSousModule = new ArrayList<List<Sous_Module>>();
	
	/**
	 * @return the listMenu
	 */
	public List<Menu> getListMenu() {
		return listMenu;
	}

	/**
	 * @param listMenu the listMenu to set
	 */
	public void setListMenu(List<Menu> listMenu) {
		this.listMenu = listMenu;
	}

	public List<Droits_Liste> getListDroits() {
		return listDroits;
	}

	public void setListDroits(List<Droits_Liste> listDroits) {
		this.listDroits = listDroits;
	}

	public List<Module> getListModule() {
		return listModule;
	}

	public void setListModule(List<Module> listModule) {
		this.listModule = listModule;
	}

	public List<List<Sous_Module>> getListSousModule() {
		return listSousModule;
	}

	public void setListSousModule(List<List<Sous_Module>> listSousModule) {
		this.listSousModule = listSousModule;
	}

	public List<Sous_Module> getListSousMod() {
		return listSousMod;
	}

	public void setListSousMod(List<Sous_Module> listSousMod) {
		this.listSousMod = listSousMod;
	}
	
	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		
		listMenu = MenuDatabaseService.getAllFinalMenus();
		listDroits = DroitsListeDatabaseService.getAll();
		listModule = ModuleDatabaseService.getAll();
		
		for(Module m : listModule){
			listSousMod = SousModuleDatabaseService.getAllFromModule(m.getId());
			listSousModule.add(listSousMod);
		}
			return OK;
	}

}
