package pilotage.database.environnement;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import pilotage.metier.Appli_Envir;
import pilotage.metier.Applicatifs_Liste;
import pilotage.metier.Environnement;
import pilotage.metier.Environnement_Type;
import pilotage.session.PilotageSession;
import pilotage.utils.Pagination;

public class EnvironnementDatabaseService {
	/**
	 * SELECT de tous les environnements ordonnées par environnment et type
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Environnement> getAll(){
		Session session = PilotageSession.getCurrentSession();
		List <Environnement> environnementList = session.createCriteria(Environnement.class)
														.addOrder(Order.asc("environnement"))
														.list();
		session.getTransaction().commit();
		return environnementList;
	}
	
	/**
	 * DELETE d'un environnement
	 * @param selectRow
	 * @return
	 */
	public static void delete(int selectRow) throws Exception{
		Session session = PilotageSession.getCurrentSession();
		try {
			Environnement environnement = (Environnement) session.load(Environnement.class, selectRow);
			session.delete(environnement);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	/**
	 * SELECT de l'environnement identifié par le paramètre
	 * @param selectRow
	 * @return
	 */
	public static Environnement get(Integer selectRow){
		Session session = PilotageSession.getCurrentSession();
		Environnement environnement = (Environnement) session.createCriteria(Environnement.class)
		                            .add(Restrictions.eq("id", selectRow))
		                            .setMaxResults(1)
		                            .uniqueResult();
		session.getTransaction().commit();
		return environnement;
	}
	
	/**
	 * SELECT de l'environnement dans la liste des id passés en paramètre
	 * @param selectRow
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Environnement> getMultiple(List<Integer> listID){
		if(listID == null || listID.isEmpty())
			return new ArrayList<Environnement>();
		
		Session session = PilotageSession.getCurrentSession();
		List<Environnement> environnements = session.createCriteria(Environnement.class)
		                            .add(Restrictions.in("id", listID))
		                            .list();
		session.getTransaction().commit();
		return environnements;
	}
	
	/**
	 * INSERT d'un nouvel environnement
	 * @param environnement
	 * @param type
	 * @throws Exception 
	 */
	public static void create(String environnement,Integer type)throws Exception{
		Session session = PilotageSession.getCurrentSession();
		try {
			Environnement evrmt = new Environnement();
			Environnement_Type evrmtType = (Environnement_Type)session.load(Environnement_Type.class, type);
			evrmt.setEnvironnement(environnement);
			evrmt.setType(evrmtType);
			
			session.save(evrmt);
			session.getTransaction().commit();
		} catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	/**
	 * UPDATE d'un environnement
	 * @param id
	 * @param environnement
	 * @param type
	 * @throws Exception 
	 */
	public static void modifier(Integer id, String environnement, Integer type)throws Exception{
		Session session = PilotageSession.getCurrentSession();
		try {
			Environnement_Type environnementType = (Environnement_Type)session.load(Environnement_Type.class, type);
			Environnement evrmt = (Environnement)session.load(Environnement.class, id);
			evrmt.setEnvironnement(environnement);
			evrmt.setType(environnementType);
			
			session.update(evrmt);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
 
	/**
	 * SELECT de tous les environnements actifs
	 * @param pagination
	 * @param sort
	 * @param sens
	 * @param filtreEnvironnement
	 * @param filtreType
	 * @return
	 */
	public static List<Environnement> getAll(Pagination<Environnement> pagination, String sort, String sens, String filtreEnvironnement, Integer filtreType){
		Session session = PilotageSession.getCurrentSession();
        
		Criteria criteria = session.createCriteria(Environnement.class);
		if (filtreEnvironnement != null && !"".equals(filtreEnvironnement)) {
			criteria.add(Restrictions.like("environnement", "%" + filtreEnvironnement + "%"));
		}
		if (filtreType != null && filtreType > -1) {
			Environnement_Type environnement_Type = (Environnement_Type) session.load(Environnement_Type.class, filtreType);
			criteria.add(Restrictions.eq("type", environnement_Type));
		}
		
		if("type".equals(sort)){
			criteria.createAlias("type", "type_object", Criteria.LEFT_JOIN);
			criteria.addOrder("desc".equalsIgnoreCase(sens) ? Order.desc("type_object.type") : Order.asc("type_object.type"));
		}
		criteria.addOrder("desc".equalsIgnoreCase(sens) ? Order.desc(sort) : Order.asc(sort));
		
		List<Environnement> listEnv = pagination.setCriteriaPagination(criteria);
		session.getTransaction().commit();
		return listEnv;
	}
	
	/**
	 * COUNT test si le type est affecté à un environnement
	 * @param selectRow
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static boolean hasEnvironnementType(Integer selectRow) throws Exception{
		Session session = PilotageSession.getCurrentSession();
		
		Environnement_Type environnement_Type = (Environnement_Type)session.load(Environnement_Type.class, selectRow);
		List<Long> results = session.createCriteria(Environnement.class)
		                 .add(Restrictions.eq("type", environnement_Type))
		                 .setProjection(Projections.rowCount())
		                 .list();
		session.getTransaction().commit();
		if (results != null && results.size() > 0 && results.get(0) > 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * COUNT test si un environnement existe déjà
	 * @param id - id à ne pas tester
	 * @param environnementString
	 * @param type
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static Boolean exists(Integer id, String environnementString, Integer type) throws Exception{
		Session session  = PilotageSession.getCurrentSession();
		
		Environnement_Type environnement_Type = (Environnement_Type) session.load(Environnement_Type.class, type);
		Criteria criteria = session.createCriteria(Environnement.class);
		criteria.add(Restrictions.eq("environnement", environnementString)).add(Restrictions.eq("type", environnement_Type));
		if (id != null) 
			criteria.add(Restrictions.not(Restrictions.eq("id", id)));
		criteria.setProjection(Projections.rowCount());
		
		List<Long> results = criteria.list();
		
		session.getTransaction().commit();
		
		if (results!=null && results.size()>0 && results.get(0) > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * SELECt des environnement appartenant au type en paramètre
	 * @param typeID
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Environnement> getByType(Integer typeID) {
		Session session = PilotageSession.getCurrentSession();
		
		Environnement_Type et = (Environnement_Type)session.load(Environnement_Type.class, typeID);
		List<Environnement> list = session.createCriteria(Environnement.class)
										.add(Restrictions.eq("type", et))
										.list();
		session.getTransaction().commit();
		return list;
	}
	
	public static Integer getId(String environnement,Integer type){
		Session session = PilotageSession.getCurrentSession();
		Environnement_Type evrmtType = (Environnement_Type)session.load(Environnement_Type.class, type);

		Environnement e = (Environnement)session.createCriteria(Environnement.class)
									.add(Restrictions.eq("environnement", environnement))
									.add(Restrictions.eq("type", evrmtType))
									.setMaxResults(1)
									.uniqueResult();
		session.getTransaction().commit();
		return e.getId();
	}

	/**
	 * SELECT de tous les environnements attachés à une application
	 * @param appId
	 */
	@SuppressWarnings("unchecked")
	public static List<Environnement> getAllByApp(Integer appId) {
		Session session = PilotageSession.getCurrentSession();
		List<Environnement> listEnv = null;
		
		Applicatifs_Liste app = (Applicatifs_Liste)session.load(Applicatifs_Liste.class, appId);
		List<Appli_Envir> list = session.createCriteria(Appli_Envir.class)
									.add(Restrictions.eq("idApplication", app))
									.list();
		
		session.getTransaction().commit();
		
		if(list != null && list.size() > 0){
			listEnv = new ArrayList<Environnement>();
			for(Appli_Envir appEnv : list){
				if(!listEnv.contains(appEnv.getIdEnvironnement()))
					listEnv.add(appEnv.getIdEnvironnement());
			}
		}
				
		return listEnv;
	}
}
