package pilotage.database.gup;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import pilotage.metier.Com_Service;
import pilotage.session.PilotageSession;
import pilotage.utils.Pagination;

public class ComServiceDatabaseService {
	
	/**
	 * SELECT de la liste des services
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Com_Service> getAll(){
		Session session = PilotageSession.getCurrentSession();
		List<Com_Service> service = session.createCriteria(Com_Service.class).list();
		session.getTransaction().commit();
		return service;
	}
	
	/**
	 * SELECT de la liste des domaines
	 * @return
	 */
	public static List<Com_Service> getAllWithPagination(Pagination<Com_Service> pagination){
		Session session = PilotageSession.getCurrentSession();
		
		Criteria criteria = session.createCriteria(Com_Service.class);
		
		List<Com_Service> service = pagination.setCriteriaPagination(criteria);
		session.getTransaction().commit();
		return service;
	}
	
	/**
	 * SELECT d'un service
	 * @return
	 */
	public static Com_Service get(Integer selectRow){
		Session session = PilotageSession.getCurrentSession();
		Com_Service s = (Com_Service)session.createCriteria(Com_Service.class)
								.add(Restrictions.eq("id", selectRow))
								.setMaxResults(1)
								.uniqueResult();
		session.getTransaction().commit();
		return s;
	}
	
	/**
	 * INSERT d'un nouveau service
	 * @param nom
	*/
	
	public static void create(String nom) throws Exception{
		Session session = PilotageSession.getCurrentSession();
		
		try{
			Com_Service cs = new Com_Service();
			cs.setNom(nom);
			
			session.save(cs);
			session.getTransaction().commit();
		}catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	/**
	 * DELETE d'un service
	 * @param selectedID
	 * @throws Exception 
	 */
	public static void delete(Integer selectedID) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			Com_Service cs = (Com_Service) session.load(Com_Service.class, selectedID);
			
			session.delete(cs);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	/**
	 * UPDATE d'un service
	 * @param id
	 * @param nom
	 */
	public static void modify(Integer id, String nom) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			Com_Service cs = (Com_Service)session.load(Com_Service.class, id);
			cs.setNom(nom);
			
			session.update(cs);
			session.getTransaction().commit();
			
		}catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}

}
