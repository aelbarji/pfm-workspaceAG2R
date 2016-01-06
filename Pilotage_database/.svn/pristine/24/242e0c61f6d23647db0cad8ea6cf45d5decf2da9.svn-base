package pilotage.database.admin;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import pilotage.admin.metier.Affectation_menu;
import pilotage.admin.metier.Menu;
import pilotage.admin.metier.Profil;
import pilotage.admin.session.ParametreSession;
import pilotage.service.menu.PilotageMenu;


public class MenuDatabaseService {
	/**
	 * SELECT le menu identifié par l'id
	 * @param id
	 * @return
	 */
	public static Menu get(Integer id){
		Session session = ParametreSession.getCurrentSession();
		Menu menu = (Menu)session.createCriteria(Menu.class)
							.add(Restrictions.eq("id", id))
							.setMaxResults(1)
							.uniqueResult();
		session.getTransaction().commit();
		return  menu;
	}
	
	/**
	 * SELECt de tous les menus cliquables
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Menu> getAllFinalMenus(){
		Session session = ParametreSession.getCurrentSession();
		List<Menu> menuList = session.createCriteria(Menu.class)
								.add(Restrictions.isNotNull("lien"))
								.add(Restrictions.ne("lien", ""))
								.addOrder(Order.asc("libelle"))
								.list();
		session.getTransaction().commit();
		return menuList;
	}
	
	/**
	 * SELECT de tous les menus
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Menu> getAllMenus(){
		Session session = ParametreSession.getCurrentSession();
		List<Menu> menuList = session.createCriteria(Menu.class).addOrder(Order.asc("libelle")).list();
		session.getTransaction().commit();
		return menuList;
	}

	/**
	 * DELETE du menu, de tous les enfant et des affectations
	 * @param menuToDelete
	 * @throws Exception
	 */
	public static void delete(PilotageMenu menuToDelete) throws Exception {
		Session session = ParametreSession.getCurrentSession();
		try{
			//modifie les places des freres du menu
			MenuDatabaseService.modifySiblingsPlaces(menuToDelete.getMenu(), session, -1, false);
			//suppression des enfants avant de supprimer le menu
			MenuDatabaseService.deleteRecursive(menuToDelete, session);
			
			session.getTransaction().commit();
		}
		catch (Exception e) {
			session.getTransaction().rollback();
			throw e;
		}
	}

	/**
	 * Suppression recursive de tous les enfants, du menu, et des affectations
	 * @param menuToDelete
	 * @param session
	 */
	private static void deleteRecursive(PilotageMenu menuToDelete, Session session) {
		if(menuToDelete.getChildren().size() > 0){
			for(PilotageMenu child : menuToDelete.getChildren()){
				deleteRecursive(child, session);
			}
		}
		//suppression des affectations
		MenuDatabaseService.deleteAllAffectationByMenu(menuToDelete, session);
		
		//suppression des pages d'accueil
		MenuDatabaseService.deleteAllAccueilPageByMenu(menuToDelete, session);
		
		//suppression du menu
		Menu menu = (Menu)session.load(Menu.class, menuToDelete.getMenu().getId());
		session.delete(menu);
	}
	
	/**
	 * DELETE toutes les pages d'accueil correspondant au menu
	 * @param menuToDelete
	 * @param session
	 */
	@SuppressWarnings("unchecked")
	private static void deleteAllAccueilPageByMenu(PilotageMenu menuToDelete, Session session) {
		List<Profil> profilsList = session.createCriteria(Profil.class)
										.add(Restrictions.eq("accueil", menuToDelete.getMenu()))
										.list();
		for(Profil profil : profilsList){
			session.evict(profil.getAccueil()); // pour eviter les objets menu en double dans la session, on le supprime de la session
			profil.setAccueil(null);
			session.update(profil);
		}
	}

	/**
	 * Decalage des places des frères d'un menu à supprimer
	 * @param currentMenu
	 * @param session
	 * @param decalage
	 * @param getSamePlace - indique s'il faut aussi modifier le menu dont la place est égale au menu qu'on manipule
	 */
	private static void modifySiblingsPlaces(Menu currentMenu, Session session, Integer decalage, Boolean getSamePlace) {
		List<Menu> olderSiblings = MenuDatabaseService.getAllOlderSiblings(currentMenu, session, getSamePlace);
		for(Menu menu : olderSiblings){
			menu.setPlace(menu.getPlace() + decalage);
			session.update(menu);
		}
	}
	
	/**
	 * Suppression de tous les affectaions à un menu
	 * @param menuToDelete
	 * @param session
	 */
	@SuppressWarnings("unchecked")
	private static void deleteAllAffectationByMenu(PilotageMenu menuToDelete, Session session){
		List<Affectation_menu> affectationsList = session.createCriteria(Affectation_menu.class)
												.add(Restrictions.eq("id_menu", menuToDelete.getMenu()))
												.list();
		for(Affectation_menu affectation : affectationsList){
			session.delete(affectation);
		}
	}
	
	/**
	 * SELECT de tous les menus frères du menu en paramètre
	 * @param currentMenu
	 * @param session
	 * @param getSamePlace
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private static List<Menu> getAllOlderSiblings(Menu currentMenu, Session session, Boolean getSamePlace){
		Criteria criteria = session.createCriteria(Menu.class)
								.add(currentMenu.getId_parent() == null ? Restrictions.isNull("id_parent") : Restrictions.eq("id_parent", currentMenu.getId_parent()))
								.add(getSamePlace ? Restrictions.ge("place", currentMenu.getPlace()) : Restrictions.gt("place", currentMenu.getPlace()));
		if(currentMenu.getId() != null)
			criteria.add(Restrictions.ne("id", currentMenu.getId()));
								
		List<Menu> menuList = criteria.list();
		return menuList;
	}
	
	/**
	 * INSERT un nouveau menu
	 * @param parent
	 * @param place
	 * @param libelle
	 * @param lien
	 * @param icone
	 * @param interop
	 * @throws Exception
	 */
	public static void create(String parent, String place, String libelle, String lien, Integer icone, Boolean interop) throws Exception{
		Integer menuParentId = parent == null || "".equals(parent) ? null : Integer.parseInt(parent);
		Integer lastPlace = getLastPlace(menuParentId);
		Integer menuPlace = place == null || "".equals(place) ? lastPlace + 1 : Integer.parseInt(place);
		if(menuPlace > lastPlace + 1)
			menuPlace = lastPlace + 1;
		String menuLien = lien == null || "".equals(lien) ? null : lien;
		Integer menuIcone = icone == null || icone == -1 ? null : icone;

		Menu menu = new Menu();
		menu.setId_parent(menuParentId);
		menu.setPlace(menuPlace);
		menu.setLibelle(libelle);
		menu.setLien(menuLien);
		menu.setIcone(menuIcone);
		menu.setInterop(interop);
		
		Session session = ParametreSession.getCurrentSession();
		try{
			//déplacement des menus frères dont la place est >= place du menu à insérer
			MenuDatabaseService.modifySiblingsPlaces(menu, session, 1, true);
			session.save(menu);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			session.getTransaction().rollback();
			throw e;
		}
	}
	
	/**
	 * SELECT la dernière place utilisée pour le parent id_parent
	 * @param id_parent
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private static Integer getLastPlace(Integer id_parent){
		Session session = ParametreSession.getCurrentSession();
		List<Integer> results = session.createCriteria(Menu.class)
						.add(id_parent == null || "".equals(id_parent) ? Restrictions.isNull("id_parent") : Restrictions.eq("id_parent", id_parent))
						.setProjection(Projections.max("place"))
						.list();
		session.getTransaction().commit();
		if (results != null && results.size() > 0 && results.get(0) != null) {
			return results.get(0);
		}
		return -1;
	}

	/**
	 * UPDATE d'un menu
	 * @param idSelect
	 * @param parent
	 * @param place
	 * @param libelle
	 * @param lien
	 * @param icone
	 * @param interop
	 * @throws Exception
	 */
	public static void modify(Integer idSelect, String parent, String place, String libelle, String lien, Integer icone, Boolean interop) throws Exception {
		Integer menuParentId = parent == null || "".equals(parent) ? null : Integer.parseInt(parent);
		Integer lastPlace = getLastPlace(menuParentId);
		Integer menuPlace = place == null || "".equals(place) ? lastPlace + 1 : Integer.parseInt(place);
		String menuLien = lien == null || "".equals(lien) ? null : lien;
		Integer menuIcone = icone == null || icone == -1 ? null : icone;
		
		Session session = ParametreSession.getCurrentSession();
		try{
			Menu menu = (Menu)session.load(Menu.class, idSelect);
			
			//si on a changé de parent et que la place indiquée > place max des nouveaux frères + 1, alors on le met à max + 1
			if(menuParentId != menu.getId_parent()){
				if(menuPlace > lastPlace + 1)
					menuPlace = lastPlace + 1;
			}
			//si on a pas changé de parent, alors le nombre de fils ne change pas. Au pire, on se déplace à hauteur du dernier.
			else if(menuPlace != menu.getPlace() && menuPlace > lastPlace){
				menuPlace = lastPlace;
			}
			
			if(menuPlace != menu.getPlace() || menuParentId != menu.getId_parent()){
				MenuDatabaseService.modifySiblingsPlaces(menu, session, -1, false);
				menu.setPlace(menuPlace);
				menu.setId_parent(menuParentId);
				MenuDatabaseService.modifySiblingsPlaces(menu, session, 1, true);
			}
			menu.setLibelle(libelle);
			menu.setLien(menuLien);
			menu.setIcone(menuIcone);
			menu.setInterop(interop);
			
			session.getTransaction().commit();
		}
		catch (Exception e) {
			session.getTransaction().rollback();
			throw e;
		}
	}
	
	/**
	 * SELECT toutes les affectations pour un profil
	 * @param idProfil      user's profile id
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public static List<Affectation_menu> getALLMenusAffectedToProfil(int idProfil) throws Exception{
		Session session = ParametreSession.getCurrentSession();
		List<Affectation_menu> affectations = null;
		try {
			Profil profil =  (Profil)session.load(Profil.class, idProfil);
			affectations = session.createCriteria(Affectation_menu.class)
				.add(Restrictions.eq("id_profil", profil))
				.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			ParametreSession.rollbackTransaction(session);
			throw e;
		}
		return affectations;
	}

	/**
	 * UPDATE/DELETE les affectations menus
	 * @param selectedProfil
	 * @param amListToAdd
	 * @param amListToDelete
	 * @throws Exception
	 */
	public static void synchroniseAffectationsForProfil(Integer selectedProfil, List<Integer> amListToAdd, List<Affectation_menu> amListToDelete) throws Exception {

		Session session = ParametreSession.getCurrentSession();
		try {
			Profil profil =  (Profil)session.load(Profil.class, selectedProfil);
			Menu menu;
			//ajout
			for(Integer idMenu : amListToAdd){
				menu = (Menu)session.load(Menu.class, idMenu);
				Affectation_menu am = new Affectation_menu();
				am.setId_menu(menu);
				am.setId_profil(profil);
				session.save(am);
			}
			
			//suppression
			for(Affectation_menu am : amListToDelete){
				session.delete(am);
			}
			
			session.getTransaction().commit();
		} catch (Exception e) {
			ParametreSession.rollbackTransaction(session);
			throw e;
		}
	}
}
