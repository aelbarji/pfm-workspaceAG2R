package pilotage.database.meteo;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import pilotage.metier.Meteo_Service;
import pilotage.session.PilotageSession;
import pilotage.utils.Pagination;

public class MeteoServiceDatabaseService {

	/**
	 * SELECT service_meteo avec son nom
	 * @param nom
	 */
	public static Meteo_Service getByName(String libelle){
		Session session = PilotageSession.getCurrentSession();
		Meteo_Service sl = (Meteo_Service)session.createCriteria(Meteo_Service.class)
									.add(Restrictions.eq("service", libelle))
									.setMaxResults(1)
									.uniqueResult();
		session.getTransaction().commit();
		return sl;
	}
	
	/**
	 * SELECT du service identifié par le paramètre
	 * @param selectRow
	 * @return
	 */
	public static Meteo_Service get(Integer selectRow){
		Session session = PilotageSession.getCurrentSession();
		Meteo_Service service = (Meteo_Service)session.createCriteria(Meteo_Service.class)
		                                .add(Restrictions.eq("id", selectRow))
		                                .setMaxResults(1)
		                                .uniqueResult();
		session.getTransaction().commit();
		return service;
	}
	
	/**
	 * SELECT de tous les services listes ordonnées par NomServices
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Meteo_Service> getAll(){
		Session session = PilotageSession.getCurrentSession();
		List<Meteo_Service> servicesList = session.createCriteria(Meteo_Service.class)
		    										.addOrder(Order.asc("service")).list();
		session.getTransaction().commit();
		return servicesList;
	}
	
	/**
	 * DELETE le service météo identifié par selectRow
	 * @param selectRow
	 * @return
	 */
	public static void delete(int selectRow) throws Exception{
		Session session = PilotageSession.getCurrentSession();
		try {
			Meteo_Service service = (Meteo_Service) session.load(Meteo_Service.class, selectRow);
			session.delete(service);
			session.getTransaction().commit();
		} catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	/**
	 * COUNT test si le service météo existe déjà
	 * @param id - id a ne pas tester
	 * @param nomService
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static Boolean exists(Integer id, String nomService){
		Session session = PilotageSession.getCurrentSession();
		
		Criteria criteria = session.createCriteria(Meteo_Service.class);
		criteria.add(Restrictions.eq("service", nomService));
		
		if(id != null)
			criteria.add(Restrictions.not(Restrictions.eq("id", id)));
		
		criteria.setProjection(Projections.rowCount());

		List<Long> results = criteria.list();
		session.getTransaction().commit();
		if (results != null && results.size() > 0 && results.get(0) > 0) {
			return true;
		}
		else {
			return false;
		}
 	}
	
	/**
	 * INSERT d'un nouveau service météo
	 * @param nomService
	 * @throws Exception 
	 */
	public static void create(String nomService) throws Exception{
		Session session = PilotageSession.getCurrentSession();
		try {
			Meteo_Service service = new Meteo_Service();
			service.setService(nomService);
			
			session.save(service);
			session.getTransaction().commit();
			
		} catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}		
	}
	
	/**
	 * SELECT de tous les services météo
	 * @param pagination
	 * @param filtreService
	 * @return
	 */
	public static List<Meteo_Service> getAll(Pagination<Meteo_Service> pagination, String filtreService){
		Session session = PilotageSession.getCurrentSession();
		Criteria criteria = session.createCriteria(Meteo_Service.class);
		if (filtreService != null && !"".equals(filtreService)) {
			criteria.add(Restrictions.like("service", "%"+ filtreService+ "%"));
		}
		criteria.addOrder(Order.asc("service"));
		List<Meteo_Service> listServices = pagination.setCriteriaPagination(criteria);
		session.getTransaction().commit();
		return listServices;
	}
	
	/**
	 * UPDATE d'un service météo
	 * @param id
	 * @param nomServices
	 * @throws Exception 
	 */
	public static void modifier(Integer id, String nomService, String consigne) throws Exception{
		Session session  = PilotageSession.getCurrentSession();
		try {
			Meteo_Service service = (Meteo_Service)session.load(Meteo_Service.class, id);
			service.setService(nomService);
			if(consigne !=null && !consigne.equals("")) service.setConsigne(consigne);
			
			session.update(service);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
		    throw e;	
		}
	}
}
