package pilotage.database.meteo;

import java.util.ArrayList;
import java.util.Date;


import java.util.Map;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import pilotage.metier.Meteo_Meteo;
import pilotage.metier.Meteo_Service;
import pilotage.metier.Meteo_Service_Meteo;
import pilotage.session.PilotageSession;
import pilotage.utils.Pagination;

public class MeteoDatabaseService {
	
	/**
	 * SELECT de toutes les météos, ordonnés par nom
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Meteo_Meteo> getAll() {
		Session session = PilotageSession.getCurrentSession();
		List<Meteo_Meteo> meteoList = (List<Meteo_Meteo>)session.createCriteria(Meteo_Meteo.class)
													.addOrder(Order.asc("nom_meteo"))
													.list();
		session.getTransaction().commit();
		return meteoList;
	}
	
	/**
	 * SELECT de la liste des Météos
	 * @param pagination
	 * @return
	 */
	
	public static List<Meteo_Meteo> getAll(Pagination<Meteo_Meteo> pagination){
		Session session = PilotageSession.getCurrentSession();
		Criteria criteria = session.createCriteria(Meteo_Meteo.class)
											.addOrder(Order.asc("nom_meteo"));
		return pagination.setCriteriaPagination(criteria);
	}
	
	/**
	 * INSERT d'une nouvelle Météo
	 * @param nom
	 * @param listService
	 * @throws Exception 
	 */
	public static void create(String nom, List<Map<String, String>> listService) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			Meteo_Meteo meteo = new Meteo_Meteo();
			meteo.setNom_meteo(nom);
			session.save(meteo);
			
			//Services
			List<Meteo_Service_Meteo> listMeteoService = new ArrayList<Meteo_Service_Meteo>();
			for(Map<String, String> ligneService : listService){
				Meteo_Service service = (Meteo_Service) session.load(Meteo_Service.class, Integer.parseInt(ligneService.get("serviceID")));
				Meteo_Service_Meteo ms = new Meteo_Service_Meteo();
				ms.setService(service);
				ms.setMeteo(meteo);
				ms.setDateCreation(new Date());
				
				listMeteoService.add(ms);
			}
	
			for(Meteo_Service_Meteo meteoService : listMeteoService){
				session.save(meteoService);
			}
	
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}	
	}
	
	/**
	 * SELECT d'une Météo avec son id
	 * @param selectRow
	 * @return
	 */
	public static Meteo_Meteo get(Integer selectRow) {
		Session session = PilotageSession.getCurrentSession();
		Meteo_Meteo meteo = (Meteo_Meteo)session.createCriteria(Meteo_Meteo.class)
																	.add(Restrictions.eq("id", selectRow))
																	.setMaxResults(1)
																	.uniqueResult();
		session.getTransaction().commit();
		return meteo;
	}
	
	/**
	 * SELECT d'une Météo avec son nom
	 * @param nom
	 * @return
	 */
	public static Meteo_Meteo getByName(String nom) {
		Session session = PilotageSession.getCurrentSession();
		Meteo_Meteo meteo = (Meteo_Meteo)session.createCriteria(Meteo_Meteo.class)
																	.add(Restrictions.eq("nom_meteo", nom))
																	.setMaxResults(1)
																	.uniqueResult();
		session.getTransaction().commit();
		return meteo;
	}
	
	/**
	 * UPDATE d'une Météo
	 * @param id
	 * @param nom
	 * @param serviceToAdd
	 * @param serviceToDelete
	 * @throws Exception 
	 */
	public static void modify(Integer idMeteo, String nom, List<Integer> serviceToAdd, List<Integer> serviceToDelete) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			Meteo_Meteo meteo = (Meteo_Meteo)session.load(Meteo_Meteo.class, idMeteo);
			meteo.setNom_meteo(nom);
			
			List<Meteo_Service_Meteo> listMeteoServiceToAdd = new ArrayList<Meteo_Service_Meteo>();
			List<Meteo_Service_Meteo> listMeteoServiceToDelete = new ArrayList<Meteo_Service_Meteo>();
			for(Integer serviceID : serviceToAdd){
				Meteo_Service service = (Meteo_Service)session.load(Meteo_Service.class, serviceID);
				Meteo_Service_Meteo meteoService = new Meteo_Service_Meteo();
				meteoService.setMeteo(meteo);
				meteoService.setService(service);
				meteoService.setDateCreation(new Date());
				
				listMeteoServiceToAdd.add(meteoService);
			}
			
			for(Integer serviceID : serviceToDelete){
				Meteo_Service service = (Meteo_Service)session.load(Meteo_Service.class, serviceID);
				Meteo_Service_Meteo meteoService = (Meteo_Service_Meteo)session.createCriteria(Meteo_Service_Meteo.class)
																	.add(Restrictions.eq("service", service))
																	.add(Restrictions.eq("meteo", meteo))
																	.setMaxResults(1)
																	.uniqueResult();
				listMeteoServiceToDelete.add(meteoService);
			}
			
			for(Meteo_Service_Meteo meteoService : listMeteoServiceToAdd) {
				session.save(meteoService);
			}
			for(Meteo_Service_Meteo meteoService : listMeteoServiceToDelete) {
				meteoService.setDateSuppression(new Date());
			}
			
			session.save(meteo);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	/**
	 * DELETE d'une Météo
	 * @param selectRow
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public static void delete(int selectRow) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			Meteo_Meteo meteo = (Meteo_Meteo)session.load(Meteo_Meteo.class, selectRow);
			List<Meteo_Service_Meteo> listMeteoService = session.createCriteria(Meteo_Service_Meteo.class)
					.add(Restrictions.eq("meteo", meteo))
					.createAlias("service", "service", Criteria.LEFT_JOIN)
					.addOrder(Order.asc("service.service"))
					.list(); 
			for(Meteo_Service_Meteo meteoService : listMeteoService) {
				session.delete(meteoService);
			}
			session.delete(meteo);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	/**
	 * SELECT de tous les meteo_service attachés à la météo 
	 * @param idMeteo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Meteo_Service_Meteo> getListMeteoService(Integer idMeteo) {
		Session session = PilotageSession.getCurrentSession();
	
		Meteo_Meteo meteo = (Meteo_Meteo)session.load(Meteo_Meteo.class, idMeteo);
		List<Meteo_Service_Meteo> meteoService = session.createCriteria(Meteo_Service_Meteo.class)
														.add(Restrictions.eq("meteo", meteo))
														.createAlias("service", "service", Criteria.LEFT_JOIN)
														.addOrder(Order.asc("service.service"))
														.list(); 
		session.getTransaction().commit();
		return meteoService;
	}
	
	/**
	 * SELECT de tous les meteo_service attachés à la météo 
	 * @param idMeteo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Meteo_Service_Meteo> getListMeteoServiceActive(Integer idMeteo, Date datetime) {
		Session session = PilotageSession.getCurrentSession();
	
		Meteo_Meteo meteo = (Meteo_Meteo)session.load(Meteo_Meteo.class, idMeteo);
		List<Meteo_Service_Meteo> meteoService = session.createCriteria(Meteo_Service_Meteo.class)
														.add(Restrictions.eq("meteo", meteo))
														.add(Restrictions.and(Restrictions.le("dateCreation",datetime), 
												Restrictions.or(Restrictions.isNull("dateSuppression"),Restrictions.gt("dateSuppression",datetime))))
														.createAlias("service", "service", Criteria.LEFT_JOIN)
														.addOrder(Order.asc("service.service"))
														.list(); 
		session.getTransaction().commit();
		return meteoService;
	}

}
