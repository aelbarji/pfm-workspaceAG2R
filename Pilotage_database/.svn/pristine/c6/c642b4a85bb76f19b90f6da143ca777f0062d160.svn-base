package pilotage.database.incidents;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import pilotage.metier.Incidents_Type;
import pilotage.session.PilotageSession;

/**
 * @author xxu
 *
 */
public class IncidentsTypesDatabaseService {

	/**
	 * SELECT de tous les types d'incident
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Incidents_Type> getAll() {
		Session session = PilotageSession.getCurrentSession();
		List<Incidents_Type> typeList = session.createCriteria(Incidents_Type.class).addOrder(Order.asc("impact")).list();
		session.getTransaction().commit();
		return typeList;
	}

	/**
	 * UPDATE d'un type d'incident
	 * @param typeID
	 * @param libelle
	 * @throws Exception 
	 */
	public static void update(int typeID, String libelle, String description, int impact, String titre_bilan) throws Exception {
		
		Session session = PilotageSession.getCurrentSession();
		try{
			Incidents_Type type = (Incidents_Type)session.load(Incidents_Type.class, typeID);
			type.setType(libelle);
			type.setDescription(description);
			type.setImpact(impact);
			type.setTitre_bilan(titre_bilan);
			session.update(type);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		} 
	}

	/**
	 * DELETE
	 * @param typeID
	 * @throws Exception 
	 */
	public static void delete(Integer typeID) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			Incidents_Type type = (Incidents_Type)session.load(Incidents_Type.class, typeID) ;
			session.delete(type);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		} 
	}

	/**
	 * SELECT du type
	 * @param typeID
	 * @return
	 */
	public static Incidents_Type get(int typeID) {
		Session session = PilotageSession.getCurrentSession();
		Incidents_Type type = (Incidents_Type)session.createCriteria(Incidents_Type.class)
									.add(Restrictions.eq("id", typeID))
									.setMaxResults(1)
									.uniqueResult();
		session.getTransaction().commit();
		return  type;
	}

	/**
	 * INSERT d'un nouveau type d'incident
	 * @param libelle
	 * @throws Exception 
	 */
	public static void create(String libelle, String description, int impact, String titre_bilan) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			Incidents_Type type = new Incidents_Type();
			type.setType(libelle);
			type.setDescription(description);
			type.setImpact(impact);
			type.setTitre_bilan(titre_bilan);
			session.save(type);
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
	 * @param libelle
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static boolean exists(Integer id, String libelle) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		
		List<Long> results = null;
		try{
			Criteria criteria = session.createCriteria(Incidents_Type.class);
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
		Incidents_Type it = (Incidents_Type)session.createCriteria(Incidents_Type.class)
									.add(Restrictions.eq("type", libelle))
									.setMaxResults(1)
									.uniqueResult();
		session.getTransaction().commit();
		return it.getId();
	}
}
