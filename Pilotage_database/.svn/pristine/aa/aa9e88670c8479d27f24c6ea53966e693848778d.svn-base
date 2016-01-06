package pilotage.database.bilan;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import pilotage.metier.Disques;
import pilotage.metier.Espaces_Disques;
import pilotage.session.PilotageSession;

public class DisqueDatabaseService {

	/**
	 * COUNT test si le libellé existe déjà dans les disques
	 * @param id - id à ne pas tester
	 * @param libelle
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static boolean exists(Integer id, String libelle) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		
		Criteria criteria = session.createCriteria(Disques.class)
								.add(Restrictions.eq("libelle", libelle))
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
	 * INSERT d'un disque
	 * @param libelle
	 * @param filiale
	 * @param seuil 
	 * @throws Exception 
	 */
	public static void create(String libelle, boolean filiale, Float seuil) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			Disques disque = new Disques();
			disque.setDeleted(false);
			disque.setFiliale(filiale);
			disque.setLibelle(libelle);
			disque.setSeuil(seuil);
			session.save(disque);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}

	/**
	 * SELECT de tous les disques non supprimés
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Disques> getAll() {
		Session session = PilotageSession.getCurrentSession();
		List<Disques> listDisques = session.createCriteria(Disques.class)
										.add(Restrictions.eq("deleted", false))
										.addOrder(Order.asc("libelle"))
										.list();
		session.getTransaction().commit();
		return listDisques;

	}

	/**
	 * SELECT d'un disque
	 * @param selectedID
	 * @return
	 */
	public static Disques get(Integer selectedID) {
		Session session = PilotageSession.getCurrentSession();
		
		Disques result = (Disques) session.createCriteria(Disques.class)
										.add(Restrictions.eq("id", selectedID))
										.uniqueResult();
		session.getTransaction().commit();
		return result;
	}

	/**
	 * UPDATE d'un disque
	 * @param selectedID
	 * @param libelle
	 * @param filiale
	 * @param seuil 
	 * @param deleted
	 * @throws Exception
	 */
	public static void update(Integer selectedID, String libelle, boolean filiale, Float seuil) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			Disques disque = (Disques) session.load(Disques.class, selectedID);
			disque.setFiliale(filiale);
			disque.setLibelle(libelle);
			disque.setSeuil(seuil);
			
			session.update(disque);
			session.getTransaction().commit();
		}
		catch (Exception e){
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	/**
	 * DELETE/UPDATE d'un disque
	 * @param selectedID
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public static void delete(Integer selectedID) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try {
			Disques disque = (Disques)session.load(Disques.class, selectedID);
			
			List<Long> results = session.createCriteria(Espaces_Disques.class)
									.setProjection(Projections.count("disque"))
									.add(Restrictions.eq("disque", disque))
									.list();
			
			if(results != null && results.size() > 0 && results.get(0) > 0){				
				disque.setDeleted(true);
				session.update(disque);
			}
			else{
				session.delete(disque);
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
		Disques d = (Disques)session.createCriteria(Disques.class)
									.add(Restrictions.eq("libelle", libelle))
									.setMaxResults(1)
									.uniqueResult();
		session.getTransaction().commit();
		return d.getId();
	}
}
