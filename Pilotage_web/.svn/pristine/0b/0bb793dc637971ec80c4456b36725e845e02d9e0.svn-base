package pilotage.admin.actions.menu;

import java.util.List;

import pilotage.database.admin.MenuDatabaseService;
import pilotage.framework.AbstractAdminAction;
import pilotage.service.menu.MenuGenerator;
import pilotage.service.menu.PilotageMenu;

/**
 * This class is used to get the data for the listMenu page
 * Success : listMenu.jsp
 * Failed :
 * Error :
 * 
 * @author Xxu
 *
 */

public class ShowMenuAdminAction extends AbstractAdminAction {
	private static final long serialVersionUID = -3142056450427016696L;
	private List<PilotageMenu> menuList;
	
	
	/**
	 * Getter liste des menus
	 * @return
	 */
	public List<PilotageMenu> getMenuList() {
		return menuList;
	}

	/**
	 * Setter liste des menus
	 * @param menuList
	 */
	public void setMenuList(List<PilotageMenu> menuList) {
		this.menuList = menuList;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		menuList = MenuGenerator.getListMenuForDisplay(MenuDatabaseService.getAllMenus());
		return OK;
	}

}
