package pilotage.database.meteo;

import java.util.List;


import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import pilotage.metier.Meteo_Caisse;
import pilotage.session.PilotageSession;
import pilotage.utils.Pagination;

public class MeteoCaisseDatabaseService {

	/**
	 * SELECT de toutes les caisses, ordonnés par nom
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Meteo_Caisse> getAll() {
		Session session = PilotageSession.getCurrentSession();
		List<Meteo_Caisse> caisseList = (List<Meteo_Caisse>)session.createCriteria(Meteo_Caisse.class)
													.addOrder(Order.asc("nomCaisse"))
													.list();
		session.getTransaction().commit();
		return caisseList;
	}
	
	/**
	 * SELECT de la liste des caisses environnement météo
	 * @param pagination
	 * @return
	 */
	
	public static List<Meteo_Caisse> getAll(Pagination<Meteo_Caisse> pagination){
		Session session = PilotageSession.getCurrentSession();
		Criteria criteria = session.createCriteria(Meteo_Caisse.class)
											.addOrder(Order.asc("nomCaisse"));
		List<Meteo_Caisse> caisseList = pagination.setCriteriaPagination(criteria);
		session.getTransaction().commit();
		return caisseList;
	}
	
	/**
	 * INSERT d'une nouvelle caisse
	 * @param nomCaisse
	 * @throws Exception 
	 */
	public static void create(String nomCaisse,String nomCaisseComplet) throws Exception{
		Session session = PilotageSession.getCurrentSession();
		try {
			Meteo_Caisse caisse = new Meteo_Caisse();
			caisse.setNomCaisse(nomCaisse);
			caisse.setNomCaisseComplet(nomCaisseComplet);
			
			session.save(caisse);
			session.getTransaction().commit();
			
		} catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}		
	}
	
	/**
	 * DELETE la caisse identifiée par caisseID
	 * @param caisseID
	 * @return
	 */
	public static void delete(int caisseID) throws Exception{
		Session session = PilotageSession.getCurrentSession();
		try {
			Meteo_Caisse caisse = (Meteo_Caisse) session.load(Meteo_Caisse.class, caisseID);
			session.delete(caisse);
			session.getTransaction().commit();
		} catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	/**
	 * SELECT de la caisse identifié par le paramètre
	 * @param selectRow
	 * @return
	 */
	public static Meteo_Caisse get(Integer selectRow){
		Session session = PilotageSession.getCurrentSession();
		Meteo_Caisse caisse = (Meteo_Caisse)session.createCriteria(Meteo_Caisse.class)
		                                .add(Restrictions.eq("id", selectRow))
		                                .setMaxResults(1)
		                                .uniqueResult();
		session.getTransaction().commit();
		return caisse;
	}
	
	/**
	 * UPDATE d'une caisse
	 * @param id
	 * @param nomCaisse
	 * @throws Exception 
	 */
	public static void modifier(Integer id, String nomCaisse, String nomCaisseComplet) throws Exception{
		Session session  = PilotageSession.getCurrentSession();
		try {
			Meteo_Caisse caisse = (Meteo_Caisse)session.load(Meteo_Caisse.class, id);
			caisse.setNomCaisse(nomCaisse);
			caisse.setNomCaisseComplet(nomCaisseComplet);
			
			session.update(caisse);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
		    throw e;	
		}
	}
	
	/**
	 * COUNT test si la caisse existe déjà
	 * @param id - id a ne pas tester
	 * @param nomCaisse
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static Boolean exists(Integer id, String nomCaisse){
		Session session = PilotageSession.getCurrentSession();
		
		Criteria criteria = session.createCriteria(Meteo_Caisse.class);
		criteria.add(Restrictions.eq("nomCaisse", nomCaisse));
		
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
}
