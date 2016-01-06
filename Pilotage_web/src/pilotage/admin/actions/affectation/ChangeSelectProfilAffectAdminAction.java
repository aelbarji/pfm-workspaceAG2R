/**
 * pilotage.admin.actions.affectation
 * 28 juin 2011
 */
package pilotage.admin.actions.affectation;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import pilotage.admin.metier.Affectation_menu;
import pilotage.admin.metier.Profil;
import pilotage.database.admin.MenuDatabaseService;
import pilotage.database.admin.ProfilDatabaseService;
import pilotage.framework.AbstractAdminAction;
import pilotage.service.constants.PilotageConstants;
import pilotage.service.menu.PilotageMenu;

/**
 * This class is to change the affectation to selected menu
 * It is called when user click on valider button
 * 
 * @author xxu
 *
 */
public class ChangeSelectProfilAffectAdminAction extends AbstractAdminAction {

	private static final long serialVersionUID = -5027963711851312895L;

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
			//recup des menus
			listMenu = (List<PilotageMenu>)session.get(PilotageConstants.AFFECTATION_LISTE_MENU);
			
			//recup les checkbox checkées
			affectMenu = new ArrayList<Integer>();
			HttpServletRequest request = ServletActionContext.getRequest();
			for (PilotageMenu pilotageMenu : listMenu) {
				boolean check = request.getParameter(pilotageMenu.getMenu().getId().toString()) != null;
				if(check)
					affectMenu.add(pilotageMenu.getMenu().getId());
			}
			
			//synchronisation avec la base
			List<Affectation_menu> amList = MenuDatabaseService.getALLMenusAffectedToProfil(selectedProfil);
			List<Affectation_menu> amListToDelete = new ArrayList<Affectation_menu>();
			List<Integer> amListToAdd = new ArrayList<Integer>();
			List<Affectation_menu> notDeletedAmList = new ArrayList<Affectation_menu>();
			
			for(Affectation_menu am : amList){
				if(!affectMenu.contains(am.getId_menu().getId())){
					amListToDelete.add(am);
				}
				else{
					notDeletedAmList.add(am);
				}
			}

			for(Integer idMenu : affectMenu){
				if(!menuInAffectationList(notDeletedAmList, idMenu))
					amListToAdd.add(idMenu);
			}
			MenuDatabaseService.synchroniseAffectationsForProfil(selectedProfil, amListToAdd, amListToDelete);
			
			info = getText("affectation.modification.valide");
			
			return OK;
		} catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Affectation menu - validation", e);
			return ERROR;
		}
		finally{
			listProfil = ProfilDatabaseService.getAll();
			
			//recup des menus et des ID des checkbox
			listMenu = (List<PilotageMenu>)session.get(PilotageConstants.AFFECTATION_LISTE_MENU);
			menuID = (Map<Integer, String>)session.get(PilotageConstants.AFFECTATION_LISTE_ID_CHECKBOX);
		}
	}

	private boolean menuInAffectationList(List<Affectation_menu> amList, Integer idMenu) {
		for(Affectation_menu am : amList){
			if(am.getId_menu().getId().equals(idMenu)){
				return true;
			}
		}
		return false;
	}

}
