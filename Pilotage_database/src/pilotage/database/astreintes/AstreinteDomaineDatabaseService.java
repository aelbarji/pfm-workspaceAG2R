package pilotage.database.astreintes;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import pilotage.metier.Astreinte_Domaine;
import pilotage.session.PilotageSession;

public class AstreinteDomaineDatabaseService {
	
	/**
	 * SELECT de tous les domaines d'astreinte
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Astreinte_Domaine> getAll(){
		Session session = PilotageSession.getCurrentSession();
		List<Astreinte_Domaine> astreinteDomaineList = session.createCriteria(Astreinte_Domaine.class)
																.addOrder(Order.asc("domaine"))
																.list();
		session.getTransaction().commit();
		return astreinteDomaineList;
	}

	/**
	 * DELETE d'un domaine
	 * @param selectRow
	 * @throws Exception 
	 */
	public static void delete(int selectRow) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			Astreinte_Domaine astreinteDomaine = (Astreinte_Domaine)session.load(Astreinte_Domaine.class, selectRow);
			session.delete(astreinteDomaine);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	/**
	 * SELECT d'un domaine d'astreinte
	 * @param selectRow
	 * @return
	 */
	public static Astreinte_Domaine get(Integer selectRow) {
		Session session = PilotageSession.getCurrentSession();
		Astreinte_Domaine astreinteDomaine = (Astreinte_Domaine)session.createCriteria(Astreinte_Domaine.class)
							.add(Restrictions.eq("id", selectRow))
							.setMaxResults(1)
							.uniqueResult();
		session.getTransaction().commit();
		return astreinteDomaine;
	}
	
	/**
	 * INSERT d'un domaine
	 * @param domaine
	 * @throws Exception 
	 */
	public static void create(String domaine) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			Astreinte_Domaine astreinteDomaine = new Astreinte_Domaine();
			astreinteDomaine.setDomaine(domaine);

			session.save(astreinteDomaine);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	/**
	 * UPDATE d'un domaine d'astreinte
	 * @param id
	 * @param domaine
	 * @throws Exception 
	 */
	public static void modify(Integer id, String domaine) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			Astreinte_Domaine astreinteDomaine = (Astreinte_Domaine)session.load(Astreinte_Domaine.class, id);
			astreinteDomaine.setDomaine(domaine);
	
			session.update(astreinteDomaine);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	/**
	 * 
	 * @param domaine
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static boolean DomaineAlreadyExists(String domaine){
        Session session = PilotageSession.getCurrentSession();
		
		List<Long> results = (List<Long>)session.createCriteria(Astreinte_Domaine.class)
		                     .add(Restrictions.eq("domaine", domaine))
		                     .setProjection(Projections.rowCount())
		                     .list();
		session.getTransaction().commit();
		if (results != null && results.size() > 0 && results.get(0) > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * COUNT teste si le domaine passé en paramètre existe déjà
	 * @param id
	 * @param domaine
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public static Boolean exists(Integer id, String domaine) throws Exception{
		Session session = PilotageSession.getCurrentSession();
		
		List<Long> results = null;
		try{
			Criteria criteria = session.createCriteria(Astreinte_Domaine.class);
			criteria.add(Restrictions.eq("domaine", domaine));
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
		Astreinte_Domaine ad = (Astreinte_Domaine)session.createCriteria(Astreinte_Domaine.class)
								.add(Restrictions.eq("domaine", libelle))
								.setMaxResults(1)
								.uniqueResult();
		session.getTransaction().commit();
		return ad.getId();
	}
}
