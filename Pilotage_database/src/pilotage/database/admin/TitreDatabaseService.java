/**
 * pilotage.database.admin
 * 6 juil. 2011
 */
package pilotage.database.admin;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import pilotage.admin.metier.Titre_page;
import pilotage.admin.session.ParametreSession;

/**
 * @author xxu
 *
 */
public class TitreDatabaseService {
	/**
	 * SELECT de tous les titres
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Titre_page> getAll(){
		Session session = ParametreSession.getCurrentSession();
		List<Titre_page> listTitres = session.createCriteria(Titre_page.class).list();
		session.getTransaction().commit();
		return  listTitres;
	} 
	
	/**
	 * DELETE du titre identifié par id
	 * 
	 * @param id :The key value of the object
	 * @param type :The type of the key value
	 * @throws Exception 
	 */
	public static void delete(String id ) throws Exception{
		Session session = ParametreSession.getCurrentSession();
		try{
			session.delete(session.load(Titre_page.class,id));
			session.getTransaction().commit();
		}
		catch (Exception e) {
			ParametreSession.rollbackTransaction(session);
			throw e;
		}
	}

	/**
	 * Delete object
	 * @param p
	 * @throws Exception 
	 */
	public static void delete(Titre_page p) throws Exception{
		Session session = ParametreSession.getCurrentSession();
		try{
			session.delete(p);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			ParametreSession.rollbackTransaction(session);
			throw e;
		}
	}

	/**
	 * SELECT d'un titre identifié par id
	 * @param id
	 * @param objClass
	 * @return
	 */
	public static Titre_page get(String id){
		Session session = ParametreSession.getCurrentSession();
		Titre_page obj = (Titre_page) session.createCriteria(Titre_page.class)
							.add(Restrictions.eq("id", id))
							.setMaxResults(1)
							.uniqueResult();
		session.getTransaction().commit();
		return obj;
	}

	/**
	 * INSERT d'un nouveau titre
	 * @param id
	 * @param description
	 * @param titre
	 * @throws Exception 
	 */
	public static void create(String id, String description, String titre) throws Exception {
		Session session = ParametreSession.getCurrentSession();
		
		try{
			Titre_page tp = new Titre_page();
			tp.setId(id);
			tp.setDescription(description);
			tp.setLibelle(titre);
			TitreDatabaseService.checkSizes(tp);
			
			session.save(tp);
			session.getTransaction().commit();
		}
		catch(Exception e){
			ParametreSession.rollbackTransaction(session);
			throw e;
		}
	}

	/**
	 * UPDATE du titre de page identifié par id
	 * @param id
	 * @param titre
	 * @throws Exception 
	 */
	public static void modify(String id, String titre) throws Exception {
		Session session = ParametreSession.getCurrentSession();
		
		try {
			Titre_page tp = (Titre_page) session.load(Titre_page.class, id);
			tp.setLibelle(titre);
			TitreDatabaseService.checkSizes(tp);
			session.update(tp);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			ParametreSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	/**
	 * CHECK si les tailles des champs sont conformes à la base de données. Si non il y a troncature
	 * @param tp
	 */
	private static void checkSizes(Titre_page tp){
		if(tp.getId() != null && tp.getId().length() > 9){
			tp.setId(tp.getId().substring(0, 9));
		}
		if(tp.getDescription() != null && tp.getDescription().length() > 127){
			tp.setDescription(tp.getDescription().substring(0, 127));
		}
		if(tp.getLibelle() != null && tp.getLibelle().length() > 127){
			tp.setLibelle(tp.getLibelle().substring(0, 127));
		}
	}
}
