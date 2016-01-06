package pilotage.database.machine;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import pilotage.metier.Machine_Os;
import pilotage.session.PilotageSession;

public class MachineOSDatabaseService {	
	/**
	 * INSERT d'un nouveau os
	 * @param libelle
	 * @throws Exception 
	 */
	public static void create(String libelle) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			Machine_Os mo = new Machine_Os();
			mo.setNom_OS(libelle);
			
			session.save(mo);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	/**
	 * SELECT de la liste des os
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Machine_Os> getAll(){
		Session session = PilotageSession.getCurrentSession();
		List<Machine_Os> machineOSList = session.createCriteria(Machine_Os.class).addOrder(Order.asc("Nom_OS")).list();
		session.getTransaction().commit();
		return machineOSList;
	}

	/**
	 * DELETE d'un os à l'aide de son id
	 * @param selectRow
	 * @throws Exception 
	 */
	public static void delete(Integer selectRow) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			Machine_Os mo = (Machine_Os)session.load(Machine_Os.class, selectRow);
			session.delete(mo);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}	
	}
	
	/**
	 * COUNT test si l'os existe déjà
	 * @param id - id à ne pas controler
	 * @param libelle
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static boolean exists(Integer id, String libelle){
		Session session = PilotageSession.getCurrentSession();
		
		Criteria criteria = session.createCriteria(Machine_Os.class);
		criteria.add(Restrictions.eq("Nom_OS", libelle));
		if(id != null)
			criteria.add(Restrictions.not(Restrictions.eq("id", id)));
		
		criteria.setProjection(Projections.rowCount());
		List<Long> results= criteria.list();
		session.getTransaction().commit();
		
		if (results != null && results.size() > 0 && results.get(0) > 0){
			return true;
		}
		else{
			return false;
		}
	}

	/**
	 * SELECT de l'os avec son id
	 * @param selectRow
	 * @return
	 */
	public static Machine_Os get(Integer selectRow) {
		Session session = PilotageSession.getCurrentSession();
		
		Machine_Os machineOS = (Machine_Os)session.createCriteria(Machine_Os.class)
									.add(Restrictions.eq("id", selectRow))
									.setMaxResults(1)
									.uniqueResult();
		
		session.getTransaction().commit();
		return machineOS;
	}

	/**
	 * UPDATE d'un os
	 * @param selectRow
	 * @param libelle
	 * @throws Exception 
	 */
	public static void modify(Integer selectRow, String libelle) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			Machine_Os mo = (Machine_Os)session.load(Machine_Os.class, selectRow);
			mo.setNom_OS(libelle);
			
			session.update(mo);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	public static Integer getId(String libelle){
		Session session = PilotageSession.getCurrentSession();
		Machine_Os mo = (Machine_Os)session.createCriteria(Machine_Os.class)
									.add(Restrictions.eq("Nom_OS", libelle))
									.setMaxResults(1)
									.uniqueResult();
		session.getTransaction().commit();
		return mo.getId();
	}
}