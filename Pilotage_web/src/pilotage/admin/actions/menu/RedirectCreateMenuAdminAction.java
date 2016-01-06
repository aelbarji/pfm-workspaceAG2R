package pilotage.admin.actions.menu;

import java.util.Collections;
import java.util.List;

import pilotage.admin.metier.Images;
import pilotage.database.admin.MenuDatabaseService;
import pilotage.database.images.ImagesDatabaseService;
import pilotage.framework.AbstractAdminAction;
import pilotage.service.menu.MenuGenerator;
import pilotage.service.menu.PilotageMenu;
import pilotage.service.menu.PilotageMenuParentSorter;


public class RedirectCreateMenuAdminAction extends AbstractAdminAction {

	private static final long serialVersionUID = 9090142691643705894L;
	private List<PilotageMenu> menuList;
	private List<Images> imagesList;
	
	public List<PilotageMenu> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<PilotageMenu> menuList) {
		this.menuList = menuList;
	}

	public List<Images> getImagesList() {
		return imagesList;
	}

	public void setImagesList(List<Images> imagesList) {
		this.imagesList = imagesList;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		try{
			imagesList = ImagesDatabaseService.getAll();
			menuList = MenuGenerator.getListMenuForParentDisplay(null, MenuDatabaseService.getAllMenus());
			Collections.sort(menuList, new PilotageMenuParentSorter());
			return OK;
		}
		catch(Exception e){
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Redirection vers la création de menu ", e);
			menuList = MenuGenerator.getListMenuForDisplay(MenuDatabaseService.getAllMenus());
			return ERROR;
		}
	}
}
