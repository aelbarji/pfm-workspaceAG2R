package pilotage.database.admin;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import pilotage.admin.metier.Menu;
import pilotage.admin.metier.Profil;
import pilotage.admin.session.ParametreSession;
import pilotage.metier.Users;
import pilotage.session.PilotageSession;


public class ProfilDatabaseService {

	/**
	 * INSERT d'un nouveau profil
	 * @param idProfil
	 * @param libelle
	 * @param check
	 * @param accueil
	 * @param admin
	 * @param pilote
	 * @throws Exception 
	 */
	public static void create(String libelle, Boolean check, Integer accueil, Boolean admin, Boolean pilote) throws Exception {
		Menu menuAccueil = MenuDatabaseService.get(accueil);
		Session session = ParametreSession.getCurrentSession();
		try{
			Profil p = new Profil();
			p.setLibelle(libelle);
			p.setClign_consigne(check ? Profil.CONSIGNE_T : Profil.CONSIGNE_F);
			p.setAccueil(menuAccueil);
			p.setAdmin(admin);
			p.setPilote(pilote);
			
			session.save(p);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			ParametreSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	/**
	 * SELECT de la liste des profils
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Profil> getAll(){
		Session session = ParametreSession.getCurrentSession();
		List<Profil> profilList = session.createCriteria(Profil.class).list();
		session.getTransaction().commit();
		return profilList;
	}

	/**
	 * DELETE d'un profil à l'aide de son id
	 * @param selectRow
	 * @throws Exception 
	 */
	public static void delete(int selectRow) throws Exception {
		Session session = ParametreSession.getCurrentSession();
		try{
			Profil p = (Profil)session.load(Profil.class, selectRow);
			session.delete(p);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			ParametreSession.rollbackTransaction(session);
			throw e;
		}	
	}
	
	/**
	 * COUNT teste si le profil est affecté à des utilisateurs
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static boolean hasUserWithProfil(int id){
		Session session = PilotageSession.getCurrentSession();
		List<Long> results= (List<Long>)session.createCriteria(Users.class)
					.add(Restrictions.eq("statut", id))
					.setProjection(Projections.rowCount())
					.list();
		session.getTransaction().commit();
		if (results != null && results.size() > 0 && results.get(0) > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * COUNT test si l'id est déjà utilisé
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static boolean idAlreadyExists(int id){
		Session session = ParametreSession.getCurrentSession();
		List<Long> results= (List<Long>)session.createCriteria(Profil.class)
					.add(Restrictions.eq("id", id))
					.setProjection(Projections.rowCount())
					.list();
		session.getTransaction().commit();
		if (results != null && results.size() > 0 && results.get(0) > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * SELECT du profil avec son id
	 * @param selectRow
	 * @return
	 */
	public static Profil get(Integer selectRow) {
		Session session = ParametreSession.getCurrentSession();
		Profil profil = (Profil)session.createCriteria(Profil.class)
							.add(Restrictions.eq("id", selectRow))
							.setMaxResults(1)
							.uniqueResult();
		session.getTransaction().commit();
		return profil;
	}

	/**
	 * UPDATE d'un profil
	 * @param selectRow
	 * @param libelle
	 * @param clignConsigne
	 * @param accueil
	 * @param admin
	 * @param pilote
	 * @throws Exception 
	 */
	public static void modify(Integer selectRow, String libelle, Boolean clignConsigne, Integer accueil, Boolean admin, Boolean pilote) throws Exception {
		Menu menuAccueil = MenuDatabaseService.get(accueil);
		Session session = ParametreSession.getCurrentSession();
		try{
			Profil p = (Profil)session.load(Profil.class, selectRow);
			p.setLibelle(libelle);
			p.setClign_consigne(clignConsigne ? Profil.CONSIGNE_T : Profil.CONSIGNE_F);
			p.setAccueil(menuAccueil);
			p.setAdmin(admin);
			p.setPilote(pilote);
			session.update(p);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			ParametreSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	
	/**
	 * SELECT de la liste des profil admins
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Profil> getAdmins(){
		Session session = ParametreSession.getCurrentSession();
		List<Profil> admins = session.createCriteria(Profil.class)
								.add(Restrictions.eq("admin", true))
								.list();
		session.getTransaction().commit();
		return admins;
	}
	
	/**
	 * SELECT de la liste des profils pilotes
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Profil> getPilotes(){
		Session session = ParametreSession.getCurrentSession();
		List<Profil> pilotes = session.createCriteria(Profil.class)
		                       .add(Restrictions.eq("pilote", true))
		                       .list();
		session.getTransaction().commit();
		return pilotes;
	}
	
	/**
	 * SELECT du profil avec son libelle
	 * @param libelle
	 * @return
	 */
	public static Profil getByLibelle(String libelle) {
		Session session = ParametreSession.getCurrentSession();
		Profil profil = (Profil)session.createCriteria(Profil.class)
							.add(Restrictions.eq("libelle", libelle))
							.setMaxResults(1)
							.uniqueResult();
		session.getTransaction().commit();
		return profil;
	}
}
