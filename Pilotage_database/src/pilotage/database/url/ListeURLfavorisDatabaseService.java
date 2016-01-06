package pilotage.database.url;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import pilotage.metier.Liste_URL;
import pilotage.metier.Liste_URL_favoris;
import pilotage.metier.Users;
import pilotage.session.PilotageSession;

public class ListeURLfavorisDatabaseService {

	/**
	 * INSERT d'une url en favori
	 * @param application
	 * @param adresse
	 * @param image
	 * @throws Exception 
	 */
	public static void create(Users user, Liste_URL url) throws Exception{
		Session session = PilotageSession.getCurrentSession();
		try {				
			Liste_URL_favoris favori = new Liste_URL_favoris();
			favori.setUrl(url);
			favori.setUser(user);
			
			session.save(favori);
			session.getTransaction().commit();
			
		} catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}		
	}
	
	/**
	 * DELETE d'un favori
	 * @param id
	 * @throws Exception 
	 */
	public static void delete(Users user, Liste_URL url) throws Exception{
		Session session = PilotageSession.getCurrentSession();
		try{
			Liste_URL_favoris favori = (Liste_URL_favoris)session.createCriteria(Liste_URL_favoris.class)
					.add(Restrictions.eq("user", user))
					.add(Restrictions.eq("url", url))
					.setMaxResults(1)
					.uniqueResult();
			session.delete(favori);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	/**
	 * DELETE de tous les favoris correspondant à une URL
	 * @param idurl
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public static void deleteAll(Integer idurl) throws Exception{
		Session session = PilotageSession.getCurrentSession();
		try{
			Liste_URL url = (Liste_URL)session.load(Liste_URL.class, idurl);
			List<Liste_URL_favoris> favoris = session.createCriteria(Liste_URL_favoris.class)
											.add(Restrictions.eq("url", url))
											.list();
			for(Liste_URL_favoris f : favoris)
				session.delete(f);
			
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	/**
	 * SELECT de tous les favoris de l'utilisateur
	 * @param id_user
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Liste_URL_favoris> getByUser(Users user) {
		Session session = PilotageSession.getCurrentSession();
		List<Liste_URL_favoris> favoris = session.createCriteria(Liste_URL_favoris.class)
														.add(Restrictions.eq("user", user))
														.createAlias("url", "url", Criteria.LEFT_JOIN)
														.addOrder(Order.asc("url.application"))
														.list(); 
		session.getTransaction().commit();
		return favoris;
	}
	
	/**
	 * SELECT favori
	 * @param user
	 * @param url
	 * @return
	 */
	public static Liste_URL_favoris get(Users user, Liste_URL url) {
		Session session = PilotageSession.getCurrentSession();
		Liste_URL_favoris favori = (Liste_URL_favoris)session.createCriteria(Liste_URL_favoris.class)
															.add(Restrictions.eq("user", user))
															.add(Restrictions.eq("url", url))
															.setMaxResults(1)
															.uniqueResult();
		session.getTransaction().commit();
		return favori;
	}
}
