/**
 * pilotage.admin.actions.affectation
 * 4 juil. 2011
 */
package pilotage.admin.actions.affectation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import pilotage.admin.metier.Affectation_menu;
import pilotage.admin.metier.Profil;
import pilotage.database.admin.MenuDatabaseService;
import pilotage.database.admin.ProfilDatabaseService;
import pilotage.framework.AbstractAdminAction;
import pilotage.service.constants.PilotageConstants;
import pilotage.service.menu.MenuGenerator;
import pilotage.service.menu.PilotageMenu;

/**
 * This action is called when the selected option of profil selected list is changed
 * 
 * @author xxu
 *
 */
public class SelectChangedAffectAdminAction extends AbstractAdminAction {
	
	private static final long serialVersionUID = -23227324580792931L;

	private List<Integer> affectMenu;
	private List<Profil> listProfil;
	private Integer selectedProfil;
	private List<PilotageMenu> listMenu;
	private Map<Integer, String> menuID;
	
	/**
	 * Getter de la liste des tag id
	 * @return
	 */
	public Map<Integer, String> getMenuID() {
		return menuID;
	}

	/**
	 * Setter de la liste des tag id
	 * @param menuID
	 */
	public void setMenuID(Map<Integer, String> menuID) {
		this.menuID = menuID;
	}

	/**
	 * @return the listMenu
	 */
	public List<PilotageMenu> getListMenu() {
		return listMenu;
	}

	/**
	 * @param listMenu the listMenu to set
	 */
	public void setListMenu(List<PilotageMenu> listMenu) {
		this.listMenu = listMenu;
	}

	/**
	 * @return the affectMenu
	 */
	public List<Integer> getAffectMenu() {
		return affectMenu;
	}

	/**
	 * @param affectMenu the affectMenu to set
	 */
	public void setAffectMenu(List<Integer> affectMenu) {
		this.affectMenu = affectMenu;
	}

	/**
	 * @return the selectedProfil
	 */
	public Integer getSelectedProfil() {
		return selectedProfil;
	}

	/**
	 * @param selectedProfil the selectedProfil to set
	 */
	public void setSelectedProfil(Integer selectedProfil) {
		this.selectedProfil = selectedProfil;
	}

	/**
	 * @return the listProfil
	 */
	public List<Profil> getListProfil() {
		return listProfil;
	}

	/**
	 * @param listProfil the listProfil to set
	 */
	public void setListProfil(List<Profil> listProfil) {
		this.listProfil = listProfil;
	}

	/* (non-Javadoc)
	 * @see pilotage.framework.AbstractAdminAction#validateMetier()
	 */
	@Override
	protected boolean validateMetier() {
		return true;
	}

	/* (non-Javadoc)
	 * @see pilotage.framework.AbstractAdminAction#executeMetier()
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected String executeMetier() {
		try {
			//recup des id des menus qui sont affectés au profil
			List<Affectation_menu> listAM = MenuDatabaseService.getALLMenusAffectedToProfil(selectedProfil);
			affectMenu = new ArrayList<Integer>();
			for (Affectation_menu am : listAM) {
				affectMenu.add(am.getId_menu().getId());
			}
			
			return OK;
		} catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Affectation menu - changement de profil", e);
			return ERROR;
		}
		finally{
			listProfil = ProfilDatabaseService.getAll();
			
			//recup des menus et des ID des checkbox
			listMenu = (List<PilotageMenu>)session.get(PilotageConstants.AFFECTATION_LISTE_MENU);
			menuID = (Map<Integer, String>)session.get(PilotageConstants.AFFECTATION_LISTE_ID_CHECKBOX);
			if(listMenu == null){
				List<PilotageMenu> treeMenu = MenuGenerator.generateTreeMenu(MenuDatabaseService.getAllMenus());
				listMenu = MenuGenerator.convertTreeToList(treeMenu);
				menuID = MenuGenerator.generateCheckBoxId(treeMenu, "");
				session.put(PilotageConstants.AFFECTATION_LISTE_MENU, listMenu);
				session.put(PilotageConstants.AFFECTATION_LISTE_ID_CHECKBOX, menuID);
			}
		}
	}

}
