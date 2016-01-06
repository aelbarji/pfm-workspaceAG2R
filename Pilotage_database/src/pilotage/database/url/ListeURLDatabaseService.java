package pilotage.database.url;

import java.io.File;


import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import pilotage.metier.Liste_URL;
import pilotage.session.PilotageSession;
import pilotage.utils.Pagination;

public class ListeURLDatabaseService {

	/**
	 * INSERT d'une nouvelle URL
	 * @param application
	 * @param adresse
	 * @param image
	 * @throws Exception 
	 */
	public static void create(String application, String adresse, File image) throws Exception{
		Session session = PilotageSession.getCurrentSession();
		try {
			InputStream stream = new FileInputStream(image);
			int length = (int)image.length();
			byte[] imageByteArray = new byte[length];
			stream.read(imageByteArray, 0, length);
			stream.close();
			
			Liste_URL url = new Liste_URL();
			url.setApplication(application);
			url.setAdresse(adresse);
			url.setImage(imageByteArray);
			
			session.save(url);
			session.getTransaction().commit();
			
		} catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}		
	}
	
	/**
	 * UPDATE d'une url
	 * @param id
	 * @param application
	 * @param adresse
	 * @param image
	 * @throws Exception 
	 */
	public static void update(Integer id, String application, String adresse, File image) throws Exception{
		Session session  = PilotageSession.getCurrentSession();
		try {
			Liste_URL url = (Liste_URL)session.load(Liste_URL.class, id);
			url.setApplication(application);
			url.setAdresse(adresse);
			
			if(image != null){
				InputStream stream = new FileInputStream(image);
				int length = (int)image.length();
				byte[] imageByteArray = new byte[length];
				stream.read(imageByteArray, 0, length);
				stream.close();
				url.setImage(imageByteArray);
			}
			
			session.update(url);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
		    throw e;	
		}
	}
	
	/**
	 * DELETE d'une url
	 * @param id
	 * @throws Exception 
	 */
	public static void delete(Integer id) throws Exception{
		Session session = PilotageSession.getCurrentSession();
		try{
			Liste_URL url = (Liste_URL)session.load(Liste_URL.class, id);
			session.delete(url);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	/**
	 * SELECT d'une url
	 * @param id
	 * @return
	 */
	public static Liste_URL get(Integer id){
		Session session = PilotageSession.getCurrentSession();
		Liste_URL url = (Liste_URL) session.createCriteria(Liste_URL.class)
						.add(Restrictions.eq("id", id))
						.uniqueResult();
		session.getTransaction().commit();
		return url;
	}
	
	/**
	 * SELECT de la liste des url
	 * @param pagination
	 * @return
	 */
	
	public static List<Liste_URL> getAll(Pagination<Liste_URL> pagination){
		Session session = PilotageSession.getCurrentSession();
		Criteria criteria = session.createCriteria(Liste_URL.class)
											.addOrder(Order.asc("application"));
		return pagination.setCriteriaPagination(criteria);
}
	
	/**
	 * SELECT de la liste des url
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public static List<Liste_URL> getAll(){
		Session session = PilotageSession.getCurrentSession();
		List<Liste_URL> urls = (List<Liste_URL>)session.createCriteria(Liste_URL.class)
													.addOrder(Order.asc("application"))
													.list();
		session.getTransaction().commit();
		return urls;
	}
			
	
	/**
	 * SELECT de la liste des url selon filtre
	 * @param pagination
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public static List<Liste_URL> getByFilter(Pagination<Liste_URL> pagination, String filtreAppli, String filtreAdresse){
		Session session = PilotageSession.getCurrentSession();
		
		Criteria criteria = session.createCriteria(Liste_URL.class);
		
		if(filtreAppli != null && !filtreAppli.equals(""))
		criteria.add(Restrictions.ilike("application", "%"+filtreAppli+"%"));
	
		if(filtreAdresse != null && !filtreAdresse.equals(""))
			criteria.add(Restrictions.ilike("adresse", "%"+filtreAdresse+"%"));
		
		criteria.addOrder(Order.asc("application"));
		List<Liste_URL> listUrl;
		if(pagination != null)
			listUrl = pagination.setCriteriaPagination(criteria);
		else
			listUrl = criteria.list();
		
		session.getTransaction().commit();
		return listUrl;
	}
	
	/**
	 * Verifie l'existance d'une url
	 * @param application
	 * @return
	 */
	public static Liste_URL existAppli(String appli) {
		Session session = PilotageSession.getCurrentSession();
		Liste_URL url = (Liste_URL)session.createCriteria(Liste_URL.class)
				.add(Restrictions.eq("application", appli))
				.setMaxResults(1)
				.uniqueResult();
		session.getTransaction().commit();
		return url;
	}
	
	/**
	 * Verifie l'existance d'une url
	 * @param adresse
	 * @return
	 */
	public static Liste_URL existAdresse(String adresse) {
		Session session = PilotageSession.getCurrentSession();
		Liste_URL url = (Liste_URL)session.createCriteria(Liste_URL.class)
				.add(Restrictions.eq("adresse", adresse))
				.setMaxResults(1)
				.uniqueResult();
		session.getTransaction().commit();
		return url;
	}
}
