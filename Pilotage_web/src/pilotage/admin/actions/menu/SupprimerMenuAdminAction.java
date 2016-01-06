package pilotage.admin.actions.menu;

import java.text.MessageFormat;
import java.util.List;

import pilotage.database.admin.MenuDatabaseService;
import pilotage.framework.AbstractAdminAction;
import pilotage.service.menu.MenuGenerator;
import pilotage.service.menu.PilotageMenu;

/**
 * This class is used to delete the specific menu.
 * Success : ShowMenuAdminAction
 * Failed :
 * Erroe :
 * 
 * @author Xxu
 *
 */

public class SupprimerMenuAdminAction extends AbstractAdminAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8507754318594147748L;
	private int selectRow;
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

	/**
	 * @return the selectRow
	 */
	public int getSelectRow() {
		return selectRow;
	}

	/**
	 * @param selectRow the selectRow to set
	 */
	public void setSelectRow(int selectRow) {
		this.selectRow = selectRow;
	}

	@Override
	protected boolean validateMetier() {
		
		return true;
	}

	@Override
	protected String executeMetier() {
		try{
			List<PilotageMenu> treeMenu = MenuGenerator.generateTreeMenu(MenuDatabaseService.getAllMenus());
			PilotageMenu menuToDelete = MenuGenerator.getMenuFromTree(treeMenu, selectRow);
			
			String nom = menuToDelete.getMenu().getLibelle();
			MenuDatabaseService.delete(menuToDelete);
			info = MessageFormat.format(getText("menu.suppression.valide"), new Object[]{nom});
			
			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Suppression de menu ", e);
			return ERROR;
		}
		finally{
			menuList = MenuGenerator.getListMenuForDisplay(MenuDatabaseService.getAllMenus());
		}
	}
}
