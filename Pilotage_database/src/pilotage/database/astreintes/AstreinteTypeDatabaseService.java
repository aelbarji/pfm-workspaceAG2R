package pilotage.database.astreintes;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import pilotage.metier.Astreinte_Obligatoire;
import pilotage.metier.Astreinte_Type;
import pilotage.session.PilotageSession;

public class AstreinteTypeDatabaseService {
	
	/**
	 * SELECT de la liste des types d'Astreinte
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Astreinte_Type> getAll(){
		Session session = PilotageSession.getCurrentSession();
		List<Astreinte_Type> astreinteTypeList = session.createCriteria(Astreinte_Type.class)
														.addOrder(Order.asc("type"))
														.list();
		session.getTransaction().commit();
		return astreinteTypeList;
	}

	/**
	 * DELETE d'un type et des astreintes obligatoires attachées
	 * @param selectRow
	 * @throws Exception 
	 */

	@SuppressWarnings("unchecked")
	public static void delete(int selectRow) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			//récupération du type
			Astreinte_Type astreinteType = (Astreinte_Type)session.load(Astreinte_Type.class, selectRow);
			
			//Suppression des astreintes obligatoires de ce type
			List<Astreinte_Obligatoire> aos = (List<Astreinte_Obligatoire>)session.createCriteria(Astreinte_Obligatoire.class)
													.add(Restrictions.eq("type", astreinteType))
													.list();
			for(Astreinte_Obligatoire ao : aos){
				session.delete(ao);
			}
			
			//suppression du type
			session.delete(astreinteType);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	/**
	 * SELECT d'un type d'astreinte
	 * @param selectRow
	 * @return
	 */
	public static Astreinte_Type get(Integer selectRow) {
		Session session = PilotageSession.getCurrentSession();
		Astreinte_Type astreinteType = (Astreinte_Type)session.createCriteria(Astreinte_Type.class)
							.add(Restrictions.eq("id", selectRow))
							.setMaxResults(1)
							.uniqueResult();
		session.getTransaction().commit();
		return astreinteType;
	}
	
	/**
	 * INSERT d'un nouveau type d'astreinte
	 * @param type
	 * @throws Exception 
	 */
	public static void create(String type) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			Astreinte_Type astreinteType = new Astreinte_Type();
			astreinteType.setType(type);

			session.save(astreinteType);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		} 
	}
	
	/**
	 * UPDATE d'un type
	 * @param id
	 * @param type
	 * @throws Exception 
	 */
	public static void modify(Integer id, String type) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			Astreinte_Type astreinteType = (Astreinte_Type)session.load(Astreinte_Type.class, id);
			astreinteType.setType(type);
			
			session.update(astreinteType);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	/**
	 * COUNT teste si le type passé en paramètre existe déjà
	 * @param id
	 * @param type
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public static Boolean exists(Integer id, String type) throws Exception{
		Session session = PilotageSession.getCurrentSession();
		
		List<Long> results = null;
		try{
			Criteria criteria = session.createCriteria(Astreinte_Type.class);
			criteria.add(Restrictions.eq("type", type));
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
		Astreinte_Type at = (Astreinte_Type)session.createCriteria(Astreinte_Type.class)
									.add(Restrictions.eq("type", libelle))
									.setMaxResults(1)
									.uniqueResult();
		session.getTransaction().commit();
		return at.getId();
	}
}
