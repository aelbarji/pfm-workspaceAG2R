package pilotage.database.environnement;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import pilotage.metier.Environnement_Type;
import pilotage.session.PilotageSession;

public class EnvironnementTypeDatabaseService {
	/**
	 * SELECT de tous les environnements types ordonnées par Type
	 * @return
	 */
       @SuppressWarnings("unchecked")
	public static List<Environnement_Type> getAll(){
    	   Session session = PilotageSession.getCurrentSession();
    	   List<Environnement_Type> environnementTypeList = session.createCriteria(Environnement_Type.class)
    	                                                    .addOrder(Order.asc("type"))
    	                                                    .list();
    	   session.getTransaction().commit();
    	   return environnementTypeList;
       }
	/**
	 * SELECT un type d'environnement principal
	 * @return
	 */
	public static Environnement_Type getPrincipal() {
		List<Environnement_Type> etList = getAll();
		for (Environnement_Type environnement_Type : etList) {
			if (environnement_Type.getPrincipal().equals(1)) {
				return environnement_Type;
			}
		}
		throw new RuntimeException("Aucun environnement n'est principal!");
	}
	/**
	 * CHECK un type d'environnement principal
	 * @param selectRow
	 * @return
	 */
	public static Boolean isPrincipal(Integer id) throws Exception {
		Environnement_Type et = get(id);
		if (et.getPrincipal().equals(1)){
			return true;
		}
		return false;
	}
	/**
	 * MODIFY un type d'environnement principal
	 * @param selectRow
	 * @return
	 */
	public static void modifierPrincipal(Integer id) throws Exception {
		
		List<Environnement_Type> etList = getAll();
		
		for (Environnement_Type et : etList) {
			if(et.getPrincipal().equals(1)){
				Session session = PilotageSession.getCurrentSession();
				et.setPrincipal(0);
				session.update(et);
				session.getTransaction().commit();
			}
		}
		Environnement_Type et = get(id);
		Session session = PilotageSession.getCurrentSession();
		et.setPrincipal(1);
		session.update(et);
		session.getTransaction().commit();

	}
	/**
	 * DELETE un type d'environnement
	 * @param selectRow
	 * @return
	 */
	public static void delete(int selectRow) throws Exception{
		Session session = PilotageSession.getCurrentSession();
		try {
			Environnement_Type environnementType = (Environnement_Type)session.load(Environnement_Type.class, selectRow);
			
			session.delete(environnementType);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	/**
	 * SELECT de l'environnement type identifié par le paramètre
	 * @param selectRow
	 * @return
	 */
	public static Environnement_Type get(Integer selectRow){
		Session session = PilotageSession.getCurrentSession();
		Environnement_Type environnement_Type = (Environnement_Type)session.createCriteria(Environnement_Type.class)
		                                       .add(Restrictions.eq("id", selectRow))
		                                       .setMaxResults(1)
		                                       .uniqueResult();
		session.getTransaction().commit();
		return environnement_Type;
	}
	
	/**
	 * INSERT d'un type d'environnement
	 * @param type
	 * @throws Exception 
	 */
	public static void create(String type) throws Exception{
		Session session = PilotageSession.getCurrentSession();
		try {
			Environnement_Type environnement_Type = new Environnement_Type();
			environnement_Type.setType(type);
			environnement_Type.setPrincipal(0);
			session.save(environnement_Type);
			session.getTransaction().commit();
			
		} catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	/**
	 * UPDATE d'un environnement type
	 * @param id
	 * @param type
	 * @throws Exception 
	 */
	public static void modifier(Integer id, String type) throws Exception{
		Session session = PilotageSession.getCurrentSession();
		try {
			Environnement_Type environnement_Type = (Environnement_Type)session.load(Environnement_Type.class, id);
			environnement_Type.setType(type);
			
			session.update(environnement_Type);
			session.getTransaction().commit();
			
		} catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	/**
	 * COUNT test si le type d'environnement existe deja
	 * @param id - id a ne pas tester
	 * @param type
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static Boolean exists(Integer id, String type) throws Exception{
	    Session session = PilotageSession.getCurrentSession();
	    Criteria criteria = session.createCriteria(Environnement_Type.class);
		criteria.add(Restrictions.eq("type", type));
		if(id != null)
			criteria.add(Restrictions.not(Restrictions.eq("id", id)));
		criteria.setProjection(Projections.rowCount());
		
		List<Long>  results = criteria.list();
		
		session.getTransaction().commit();
		
		if (results != null && results.size() > 0 && results.get(0) > 0) {
			return true;
		}
		else{
			return false;
		}
	}
	
	public static Integer getId(String libelle){
		Session session = PilotageSession.getCurrentSession();

		Environnement_Type et = (Environnement_Type)session.createCriteria(Environnement_Type.class)
									.add(Restrictions.eq("type", libelle))
									.setMaxResults(1)
									.uniqueResult();
		session.getTransaction().commit();
		return et.getId();
	}
}
