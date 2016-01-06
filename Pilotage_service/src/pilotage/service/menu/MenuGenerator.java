package pilotage.service.menu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import pilotage.admin.metier.Affectation_menu;
import pilotage.admin.metier.Menu;

public class MenuGenerator {

	/**
	 * Génère l'arbre des menus
	 * @param listAffectations
	 * @return
	 */
	public static List<PilotageMenu> getTreeMenu(List<Affectation_menu> listAffectations){
		//recuperation de tous les menus
		List<Menu> menuList = new ArrayList<Menu>();
		if(listAffectations != null){
			for(Affectation_menu affectation : listAffectations){
				menuList.add(affectation.getId_menu());
			}
		}
		
		return generateTreeMenu(menuList);
	}
	
	/**
	 * Génère l'arbre des menus
	 * @param menuList
	 * @return
	 */
	public static List<PilotageMenu> generateTreeMenu(List<Menu> menuList){
		PilotageMenuPlaceSorter comp = new PilotageMenuPlaceSorter();

		//construction de l'arbre des menus
		List<PilotageMenu> treeMenu = new LinkedList<PilotageMenu>();
		List<Menu> treatedMenu = new ArrayList<Menu>();
		//recuperation du 1er niveau de menu
		if(menuList != null){
			for(Menu menu : menuList){
				if(menu.getId_parent() == null){
					PilotageMenu pilMenu = new PilotageMenu(menu);
					treeMenu.add(pilMenu);
					
					treatedMenu.add(menu);
				}
			}
		}
		Collections.sort(treeMenu,comp);
		menuList.removeAll(treatedMenu);
		List<PilotageMenu> lastLevelMenu = treeMenu;
		List<PilotageMenu> lastChildrenLevelMenu;
		//parcours et construction des fils
		while(menuList.size() > 0 && lastLevelMenu.size() > 0){
			lastChildrenLevelMenu = new ArrayList<PilotageMenu>();
			//Pour chaque parent, on parcours la liste des menus restants 
			for(PilotageMenu parent : lastLevelMenu){
				treatedMenu = new ArrayList<Menu>();
				Integer parentId = parent.getMenu().getId();
				for(Menu menu : menuList){
					//si le menu a l'id parent = parent actuel, on l'ajoute aux menus traités et à la liste des enfants du parent 
					if(parentId.equals(menu.getId_parent())){
						parent.getChildren().add(new PilotageMenu(menu));
						treatedMenu.add(menu);
					}
				}
				Collections.sort(parent.getChildren(),comp);
				//suppression du la liste des menus restant des menus qu'on vient de traiter
				menuList.removeAll(treatedMenu);
				//ajout des enfants dans la liste du dernier niveau traité
				lastChildrenLevelMenu.addAll(parent.getChildren());
			}
			lastLevelMenu = lastChildrenLevelMenu;
		}
		
		//Niveaux et parents
		for(PilotageMenu pilotageMenu : treeMenu){
			pilotageMenu.setLevel(0);
			pilotageMenu.setParents(null);
		}
		
		return treeMenu;
	}

	/**
	 * Converti l'arbre des menus en liste dans l'ordre des places, et les fils suivant directement les parents
	 * @param treeMenu
	 * @return
	 */
	public static List<PilotageMenu> convertTreeToList(List<PilotageMenu> treeMenu) {
		List<PilotageMenu> menuList = new ArrayList<PilotageMenu>();
		if(treeMenu == null){
			return menuList; 
		}
		for(PilotageMenu pilotageMenu : treeMenu){
			menuList.add(pilotageMenu);
			menuList.addAll(MenuGenerator.convertTreeToList(pilotageMenu.getChildren()));
		}
		
		return menuList;
	}
	
	/**
	 * Recupération du menu identifié par l'id dans l'arbre des menus
	 * @param treeMenu
	 * @param id
	 * @return
	 */
	public static PilotageMenu getMenuFromTree(List<PilotageMenu> treeMenu, Integer id){
		if(id == null || treeMenu == null || treeMenu.size() == 0){
			return null;
		}
		for(PilotageMenu menu : treeMenu){
			if(menu.getMenu().getId() == id){
				return menu;
			}
			else{
				PilotageMenu childrenMenu = MenuGenerator.getMenuFromTree(menu.getChildren(), id);
				if(childrenMenu != null){
					return childrenMenu;
				}
			}
		}
		return null;
	}
	
	/**
	 * Génération des id des tag checkbox pour les affectations : id = (place du pere)-(place du fils)-...
	 * @param treeMenu
	 * @param debutId
	 * @return
	 */
	public static Map<Integer, String> generateCheckBoxId(List<PilotageMenu> treeMenu, String debutId){
		Map<Integer, String> mapId = new HashMap<Integer, String>();
		int i = 0;
		for(PilotageMenu pilotageMenu : treeMenu){
			String id = String.valueOf(i);
			if(!"".equals(debutId))
				id = debutId + "-" + id;
			mapId.put(pilotageMenu.getMenu().getId(), id);
			mapId.putAll(generateCheckBoxId(pilotageMenu.getChildren(), id));
			++i;
		}
		return mapId;
	}

	/**
	 * Interopérabilité
	 * @param treeMenu
	 * @param encodedUsername
	 */
	public static void setInteroperabilite(List<PilotageMenu> treeMenu, String encodedUsername) {
		if(treeMenu == null || treeMenu.isEmpty()){
			return;
		}
		for(PilotageMenu pilotageMenu : treeMenu){
			if(pilotageMenu.getMenu().getInterop() && !pilotageMenu.getLien().equals("#")){
				if(pilotageMenu.getLien().contains("?"))
					pilotageMenu.setLien(pilotageMenu.getLien() + "&p=" + encodedUsername);
				else
					pilotageMenu.setLien(pilotageMenu.getLien() + "?p=" + encodedUsername);
			}
			MenuGenerator.setInteroperabilite(pilotageMenu.getChildren(), encodedUsername);
		}
	}
	
	/**
	 * Teste si l'arbre contient le menu
	 * @param treeMenu
	 * @param menu
	 * @return
	 */
	public static boolean contains(List<PilotageMenu> treeMenu, Menu menu){
		if(treeMenu == null || treeMenu.isEmpty())
			return false;
		for(PilotageMenu pilMenu : treeMenu){
			if(pilMenu.getMenu().equals(menu) || MenuGenerator.contains(pilMenu.getChildren(), menu))
				return true;
		}
		return false;
	}

	/**
	 * Enlève le sous arbre dont le noeud est l'id passé en paramètre 
	 * @param selectRow
	 * @param menuList 
	 */
	public static boolean remove(int selectRow, List<PilotageMenu> treeMenu) {
		if(treeMenu == null || treeMenu.isEmpty())
			return false;
		for(PilotageMenu pilMenu : treeMenu){
			if(selectRow == pilMenu.getMenu().getId()){
				treeMenu.remove(pilMenu);
				return true;
			}
			else if(remove(selectRow, pilMenu.getChildren())){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Construit la liste des menus à afficher, sous forme d'arbre
	 * @return
	 */
	public static List<PilotageMenu> getListMenuForDisplay(List<Menu> menuList){
		//1 - Récupération de la liste des menus et Mise sous forme d'arbre
		//2- Transformation en liste formaté pour l'affichage
		List<PilotageMenu> treeMenu = MenuGenerator.generateTreeMenu(menuList);
		return MenuGenerator.convertTreeToList(treeMenu);
	}
	
	/**
	 * Construit la liste pour la combo box des parents dans la page de modification des menus ou pour la page de création
	 * @param selectRow
	 * @param menuList
	 * @return
	 */
	public static List<PilotageMenu> getListMenuForParentDisplay(Integer selectRow, List<Menu> menuList){
		//récupère l'arbre
		List<PilotageMenu> treeMenu = MenuGenerator.generateTreeMenu(menuList);
		//enlève l'élément
		if(selectRow != null)
			remove(selectRow, treeMenu);
		//transforme en liste
		treeMenu = MenuGenerator.convertTreeToList(treeMenu);
		//tri
		Collections.sort(treeMenu, new PilotageMenuParentSorter());
		
		return treeMenu;
	}
}
