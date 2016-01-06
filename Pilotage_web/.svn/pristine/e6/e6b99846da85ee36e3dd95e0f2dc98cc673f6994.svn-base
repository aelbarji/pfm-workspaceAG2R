package pilotage.admin.actions.menu;

import java.util.List;

import pilotage.admin.metier.Images;
import pilotage.admin.metier.Menu;
import pilotage.database.admin.MenuDatabaseService;
import pilotage.database.images.ImagesDatabaseService;
import pilotage.framework.AbstractAdminAction;
import pilotage.service.menu.MenuGenerator;
import pilotage.service.menu.PilotageMenu;


public class RedirectModifyMenuAdminAction extends AbstractAdminAction {

	private static final long serialVersionUID = 8605819712554298068L;
	private int selectRow;
	private Menu menuToEdit;
	private String parentName;
	private List<PilotageMenu> menuList;
	private List<Images> imagesList;

	public int getSelectRow() {
		return selectRow;
	}

	public void setSelectRow(int selectRow) {
		this.selectRow = selectRow;
	}

	public Menu getMenuToEdit() {
		return menuToEdit;
	}

	public void setMenuToEdit(Menu menuToEdit) {
		this.menuToEdit = menuToEdit;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

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
			menuToEdit =  MenuDatabaseService.get(selectRow);
			menuList = MenuGenerator.getListMenuForParentDisplay(selectRow, MenuDatabaseService.getAllMenus());
			imagesList = ImagesDatabaseService.getAll();
			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Redirection vers la modification de menu ", e);
			menuList = MenuGenerator.getListMenuForDisplay(MenuDatabaseService.getAllMenus());
			return ERROR;
		}
	}

}
