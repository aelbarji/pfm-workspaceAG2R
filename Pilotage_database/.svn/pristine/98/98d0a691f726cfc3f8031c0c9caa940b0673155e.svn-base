package pilotage.database.machine;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import pilotage.metier.Machines_Type;
import pilotage.session.PilotageSession;

public class MachineTypeDatabaseService {

	/**
	 * INSERT d'un nouveau type
	 * @param libelle
	 * @throws Exception 
	 */
	public static void create(String libelle) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			Machines_Type mt = new Machines_Type();
			mt.setType(libelle);
			
			session.save(mt);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	/**
	 * SELECT de la liste des types
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Machines_Type> getAll(){
		Session session = PilotageSession.getCurrentSession();
		List<Machines_Type> machineTypeList = session.createCriteria(Machines_Type.class).addOrder(Order.asc("type")).list();
		session.getTransaction().commit();
		return machineTypeList;
	}

	/**
	 * DELETE d'un type à l'aide de son id
	 * @param selectRow
	 * @throws Exception 
	 */
	public static void delete(Integer selectRow) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			Machines_Type mt = (Machines_Type)session.load(Machines_Type.class, selectRow);
			session.delete(mt);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}	
	}

	/**
	 * COUNT test si le type existe déjà
	 * @param id - id à ne pas controler
	 * @param libelle
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static boolean exists(Integer id, String libelle){
		Session session = PilotageSession.getCurrentSession();
		
		Criteria criteria = session.createCriteria(Machines_Type.class);
		criteria.add(Restrictions.eq("type", libelle));
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
	 * SELECT du type avec son id
	 * @param selectRow
	 * @return
	 */
	public static Machines_Type get(Integer selectRow) {
		Session session = PilotageSession.getCurrentSession();
		Machines_Type machineType = (Machines_Type)session.createCriteria(Machines_Type.class)
							.add(Restrictions.eq("id", selectRow))
							.setMaxResults(1)
							.uniqueResult();
		session.getTransaction().commit();
		return machineType;
	}

	/**
	 * UPDATE d'un type
	 * @param selectRow
	 * @param libelle
	 * @throws Exception 
	 */
	public static void modify(Integer selectRow, String libelle) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			Machines_Type mt = (Machines_Type)session.load(Machines_Type.class, selectRow);
			mt.setType(libelle);
			session.update(mt);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	public static Integer getId(String libelle){
		Session session = PilotageSession.getCurrentSession();
		Machines_Type mt = (Machines_Type)session.createCriteria(Machines_Type.class)
									.add(Restrictions.eq("type", libelle))
									.setMaxResults(1)
									.uniqueResult();
		session.getTransaction().commit();
		return mt.getId();
	}
}