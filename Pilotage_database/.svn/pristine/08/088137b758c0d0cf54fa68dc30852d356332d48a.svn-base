package pilotage.database.bilan;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import pilotage.metier.Flux_CFT;
import pilotage.metier.Flux_Erreur;
import pilotage.session.PilotageSession;

public class FluxCFTDatabaseService {

	/**
	 * SELECT de tous les flux non supprimés
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Flux_CFT> getAll() {
		Session session = PilotageSession.getCurrentSession();
		List<Flux_CFT> listCFT = session.createCriteria(Flux_CFT.class)
										.add(Restrictions.eq("deleted", false))
										.addOrder(Order.asc("libelle"))
										.list();
		session.getTransaction().commit();
		return listCFT;
	}

	/**
	 * COUNT teste si le libelle existe déjà dans la table des flux
	 * @param id - id à ne pas tester
	 * @param libelle
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static boolean exists(Integer id, String libelle) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		
		Criteria criteria = session.createCriteria(Flux_CFT.class)
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
	 * SELECT d'un flux
	 * @param selectedID
	 * @return
	 */
	public static Flux_CFT get(Integer selectedID) {
		Session session = PilotageSession.getCurrentSession();
		Flux_CFT result = (Flux_CFT) session.createCriteria(Flux_CFT.class)
								.add(Restrictions.eq("id", selectedID))
								.setMaxResults(1)
								.uniqueResult();
		session.getTransaction().commit();
		return result;
	}

	/**
	 * INSERT d'un nouveau flux
	 * @param libelle
	 * @throws Exception 
	 */
	public static void create(String libelle) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			Flux_CFT fc = new Flux_CFT();
			fc.setDeleted(false);
			fc.setLibelle(libelle);
			session.save(fc);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}

	/**
	 * UPDATE d'un flux
	 * @param selectedID
	 * @param libelle
	 * @throws Exception 
	 */
	public static void update(Integer selectedID, String libelle) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			Flux_CFT alertCF = (Flux_CFT) session.load(Flux_CFT.class, selectedID);
			alertCF.setLibelle(libelle);
			
			session.update(alertCF);
			session.getTransaction().commit();
		}
		catch (Exception e){
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	/**
	 * DELETE/UPDATE d'un flux
	 * @param selectedID
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public static void delete(Integer selectedID) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		
		try {
			Flux_CFT flux_cft = (Flux_CFT)session.load(Flux_CFT.class, selectedID);
			List<Long> results = session.createCriteria(Flux_Erreur.class)
										.setProjection(Projections.rowCount())
										.add(Restrictions.eq("flux", flux_cft))
										.list();
			
			
			if(results != null && results.size() > 0 && results.get(0) > 0){				
				flux_cft.setDeleted(true);
				session.update(flux_cft);
			}
			else{
				session.delete(flux_cft);
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
		Flux_CFT f = (Flux_CFT)session.createCriteria(Flux_CFT.class)
									.add(Restrictions.eq("libelle", libelle))
									.setMaxResults(1)
									.uniqueResult();
		session.getTransaction().commit();
		return f.getId();
	}
}
