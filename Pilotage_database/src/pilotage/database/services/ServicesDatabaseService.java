package pilotage.database.services;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import pilotage.metier.Services_Liste;
import pilotage.session.PilotageSession;
import pilotage.utils.Pagination;

public class ServicesDatabaseService {
	/**
	 * SELECT de tous les services listes ordonnées par NomServices
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Services_Liste> getAll(){
		Session session = PilotageSession.getCurrentSession();
		List<Services_Liste> servicesListesList = session.createCriteria(Services_Liste.class)
		    										.addOrder(Order.asc("nomService")).list();
		session.getTransaction().commit();
		return servicesListesList;
	}
	
	/**
	 * DELETE le service liste identifié par selectRow
	 * @param selectRow
	 * @return
	 */
	public static void delete(int selectRow) throws Exception{
		Session session = PilotageSession.getCurrentSession();
		try {
			Services_Liste services_Liste = (Services_Liste) session.load(Services_Liste.class, selectRow);
			session.delete(services_Liste);
			session.getTransaction().commit();
		} catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	/**
	 * SELECT du service identifié par le paramètre
	 * @param selectRow
	 * @return
	 */
	public static Services_Liste get(Integer selectRow){
		Session session = PilotageSession.getCurrentSession();
		Services_Liste services_Liste = (Services_Liste)session.createCriteria(Services_Liste.class)
		                                .add(Restrictions.eq("id", selectRow))
		                                .setMaxResults(1)
		                                .uniqueResult();
		session.getTransaction().commit();
		return services_Liste;
	}
	
	/**
	 * INSERT d'un nouveau service
	 * @param nomServices
	 * @throws Exception 
	 */
	public static void create(String nomServices) throws Exception{
		Session session = PilotageSession.getCurrentSession();
		try {
			Services_Liste services_Liste = new Services_Liste();
			services_Liste.setNomService(nomServices);
			
			session.save(services_Liste);
			session.getTransaction().commit();
			
		} catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}		
	}
	
	/**
	 * UPDATE d'un service
	 * @param id
	 * @param nomServices
	 * @throws Exception 
	 */
	public static void modifier(Integer id, String nomServices, String consigne_meteo) throws Exception{
		Session session  = PilotageSession.getCurrentSession();
		try {
			Services_Liste services_Liste = (Services_Liste)session.load(Services_Liste.class, id);
			services_Liste.setNomService(nomServices);
			if(consigne_meteo !=null && !consigne_meteo.equals("")) services_Liste.setConsigne_meteo(consigne_meteo);
			
			session.update(services_Liste);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
		    throw e;	
		}
	}
	
	/**
	 * COUNT test si le service existe déjà
	 * @param id - id a ne pas tester
	 * @param nomServicesString
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static Boolean exists(Integer id, String nomServicesString){
		Session session = PilotageSession.getCurrentSession();
		
		Criteria criteria = session.createCriteria(Services_Liste.class);
		criteria.add(Restrictions.eq("nomService", nomServicesString));
		
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
	 * SELECT de tous les services
	 * @param pagination
	 * @param filtreNomService
	 * @return
	 */
	public static List<Services_Liste> getAll(Pagination<Services_Liste> pagination, String filtreNomService){
		Session session = PilotageSession.getCurrentSession();
		Criteria criteria = session.createCriteria(Services_Liste.class);
		if (filtreNomService != null && !"".equals(filtreNomService)) {
			criteria.add(Restrictions.like("nomService", "%"+ filtreNomService+ "%"));
		}
		criteria.addOrder(Order.asc("nomService"));
		List<Services_Liste> listServices = pagination.setCriteriaPagination(criteria);
		session.getTransaction().commit();
		return listServices;
	}
	
	public static Integer getId(String libelle){
		Session session = PilotageSession.getCurrentSession();
		Services_Liste sl = (Services_Liste)session.createCriteria(Services_Liste.class)
									.add(Restrictions.eq("nomService", libelle))
									.setMaxResults(1)
									.uniqueResult();
		session.getTransaction().commit();
		return sl.getId();
	}
	
	/**
	 * SELECT service avec son nom
	 * @param nom
	 */
	public static Services_Liste getByName(String libelle){
		Session session = PilotageSession.getCurrentSession();
		Services_Liste sl = (Services_Liste)session.createCriteria(Services_Liste.class)
									.add(Restrictions.eq("nomService", libelle))
									.setMaxResults(1)
									.uniqueResult();
		return sl;
	}
	
}
