package pilotage.database.incidents;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import pilotage.metier.Incidents_Outils;
import pilotage.session.PilotageSession;

/**
 * @author xxu
 * 
 */
public class IncidentsOutilsDatabaseService {

	/**
	 * SELECT de tous les outils
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Incidents_Outils> getAll() {
		Session session = PilotageSession.getCurrentSession();
		List<Incidents_Outils> list = session.createCriteria(Incidents_Outils.class)
												.addOrder(Order.asc("nomOutils"))
												.list();
		session.getTransaction().commit();
		return list;
	}

	/**
	 * SELECT de l'outil
	 * @param outilSelected
	 * @return
	 */
	public static Incidents_Outils get(int id) {
		Session session = PilotageSession.getCurrentSession();
		Incidents_Outils io = (Incidents_Outils) session
								.createCriteria(Incidents_Outils.class)
								.add(Restrictions.eq("id", id))
								.setMaxResults(1)
								.uniqueResult();
		session.getTransaction().commit();
		return io;
	}

	/**
	 * DELETE d'un outil
	 * @param id
	 * @throws Exception 
	 */
	public static void delete(Integer id) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			Incidents_Outils io = (Incidents_Outils) session.load(Incidents_Outils.class, id);
			session.delete(io);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		} 
		
	}

	/**
	 * INSERT d'un nouvel outil
	 * @param nomOutils
	 * @throws Exception
	 */
	public static void create(String nomOutils) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			Incidents_Outils io = new Incidents_Outils();
			io.setNomOutils(nomOutils);
			session.save(io);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		} 
	}

	/**
	 * UPDATE d'un outil
	 * @param outilID
	 * @param nomOutils
	 * @throws Exception
	 */
	public static void update(Integer outilID, String nomOutils) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			Incidents_Outils io = (Incidents_Outils)session.load(Incidents_Outils.class, outilID);
			io.setNomOutils(nomOutils);
			session.update(io);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		} 
	}

	/**
	 * COUNT teste si le libellé existe déjà
	 * @param id
	 * @param nomOutils
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static boolean exists(Integer id, String nomOutils) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		
		List<Long> results = null;
		try{
			Criteria criteria = session.createCriteria(Incidents_Outils.class);
			criteria.add(Restrictions.eq("nomOutils", nomOutils));
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
		Incidents_Outils io = (Incidents_Outils)session.createCriteria(Incidents_Outils.class)
									.add(Restrictions.eq("nomOutils", libelle))
									.setMaxResults(1)
									.uniqueResult();
		session.getTransaction().commit();
		return io.getId();
	}
}
