package pilotage.database.bilan;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import pilotage.metier.Alertes_Disques;
import pilotage.metier.Alertes_Type;
import pilotage.session.PilotageSession;

public class AlertesTypeDatabaseService {

	/**
	 * SELECT de tous les types d'alerte non supprimés
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Alertes_Type> getAll() {
		Session session = PilotageSession.getCurrentSession();
		List<Alertes_Type> listTypes = session.createCriteria(Alertes_Type.class)
											.add(Restrictions.eq("deleted", false))
											.addOrder(Order.asc("type"))
											.list();
		session.getTransaction().commit();
		return listTypes;
	}

	/**
	 * COUNT test si le libellé existe déjà
	 * @param id - id à ne pas controler
	 * @param type
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static boolean exists(Integer id, String type) {
		Session session = PilotageSession.getCurrentSession();
		
		Criteria criteria = session.createCriteria(Alertes_Type.class)
										.add(Restrictions.eq("type", type))
										.setProjection(Projections.rowCount());
		if(id != null)
			criteria.add(Restrictions.ne("id", id));
		
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
	 * INSERT d'un nouveau type d'alerte
	 * @param type
	 * @throws Exception 
	 * @throws HibernateException 
	 */
	public static void create(String type) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		
		try {
			Alertes_Type alertType = new Alertes_Type();
			alertType.setDeleted(false);
			alertType.setType(type);
			
			session.save(alertType);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}

	/**
	 * UPDATE d'un type d'alerte
	 * @param selectedID
	 * @param type
	 * @throws Exception 
	 */
	public static void update(int selectedID, String type) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		
		try{
			Alertes_Type alertType = (Alertes_Type) session.load(Alertes_Type.class, selectedID);
			alertType.setType(type);
			
			session.update(alertType);
			session.getTransaction().commit();
		}
		catch (Exception e){
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}

	/**
	 * SELECT d'un type d'alerte
	 * @param selectedID
	 * @return
	 */
	public static Alertes_Type get(int selectedID) {
		Session session = PilotageSession.getCurrentSession();
		Alertes_Type result = (Alertes_Type) session.createCriteria(Alertes_Type.class)
									.add(Restrictions.eq("id", selectedID))
									.setMaxResults(1)
									.uniqueResult();
		session.getTransaction().commit();
		return result;
	}

	/**
	 * DELETE/UPDATE d'un type d'alerte
	 * @param selectedID
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public static void delete(Integer selectedID) throws Exception {
		Session session = PilotageSession.getCurrentSession();

		try {
			Alertes_Type alertes_type = (Alertes_Type)session.load(Alertes_Type.class, selectedID);
			
			List<Long> results = session.createCriteria(Alertes_Disques.class)
									.add(Restrictions.eq("type", alertes_type))
									.setProjection(Projections.rowCount())
									.list();
	
			if(results != null && results.size() > 0 && results.get(0) > 0){
				alertes_type.setDeleted(true);
				session.update(alertes_type);
			}
			else{
				session.delete(alertes_type);
			}
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}

	public static Integer getId(String libelle){
		Session session = PilotageSession.getCurrentSession();
		Alertes_Type at = (Alertes_Type)session.createCriteria(Alertes_Type.class)
									.add(Restrictions.eq("type", libelle))
									.setMaxResults(1)
									.uniqueResult();
		session.getTransaction().commit();
		return at.getId();
	}
}
