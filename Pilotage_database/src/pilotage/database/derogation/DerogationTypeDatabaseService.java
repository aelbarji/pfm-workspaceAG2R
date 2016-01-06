package pilotage.database.derogation;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import pilotage.metier.Derogation_Type;
import pilotage.session.PilotageSession;

public class DerogationTypeDatabaseService {
	
	/**
	 * SELECT de la liste des types de dérogation ordonnés par libellé
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Derogation_Type> getAll(){
		Session session = PilotageSession.getCurrentSession();
		List<Derogation_Type> listDT = session.createCriteria(Derogation_Type.class)
											.addOrder(Order.asc("type"))
											.list();
		session.getTransaction().commit();
		return listDT;
	}
	
	/**
	 * DELETE d'un type de dérogation
	 * @param selectRow
	 */
	public static void delete(Integer selectRow) throws Exception{
		Session session = PilotageSession.getCurrentSession();
		try{
			Derogation_Type derogationType = (Derogation_Type)session.load(Derogation_Type.class, selectRow);
			session.delete(derogationType);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}

	/**
	 * UPDATE d'un type de dérogation
	 * @param id
	 * @param type
	 * @throws Exception 
	 */
	public static void modify(Integer id, String type) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			Derogation_Type derogationType = (Derogation_Type)session.load(Derogation_Type.class, id);
			derogationType.setType(type);
	
			session.update(derogationType);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	/**
	 * INSERT d'un nouveau type
	 * @param type
	 * @throws Exception
	 */
	public static void create(String type) throws Exception{
		Session session = PilotageSession.getCurrentSession();
		
		try{
			Derogation_Type derogationType = new Derogation_Type();
			derogationType.setType(type);
			
			session.save(derogationType);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
		
	
	/**
	 * SELECT du type de dérogation
	 * @param selectRow
	 * @return
	 */
	public static Derogation_Type get(Integer selectRow) {
		Session session = PilotageSession.getCurrentSession();
		Derogation_Type derogationType = (Derogation_Type)session.createCriteria(Derogation_Type.class)
							.add(Restrictions.eq("id", selectRow))
							.setMaxResults(1)
							.uniqueResult();
		session.getTransaction().commit();
		return derogationType;
	}

	/**
	 * COUNT teste si le libellé existe déjà
	 * @param libelle
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public static boolean exists(Integer id, String libelle) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		
		List<Long> results = null;
		try{
			Criteria criteria = session.createCriteria(Derogation_Type.class);
			criteria.add(Restrictions.eq("type", libelle));
			if(id != null)
				criteria.add(Restrictions.not(Restrictions.eq("id", id)));
			criteria.setProjection(Projections.rowCount());
							
			results = criteria.list();
			
			session.getTransaction().commit();
		}
		catch (Exception e) {
			throw e;
		}
		
		if (results != null && results.size() > 0 && results.get(0) > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public static Integer getId(String libelle){
		Session session = PilotageSession.getCurrentSession();
		Derogation_Type dt = (Derogation_Type)session.createCriteria(Derogation_Type.class)
									.add(Restrictions.eq("type", libelle))
									.setMaxResults(1)
									.uniqueResult();
		session.getTransaction().commit();
		return dt.getId();
	}
}
