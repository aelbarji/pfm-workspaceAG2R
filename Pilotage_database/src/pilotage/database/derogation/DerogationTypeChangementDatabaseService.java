package pilotage.database.derogation;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import pilotage.metier.Derogation_Type_Changement;
import pilotage.session.PilotageSession;

public class DerogationTypeChangementDatabaseService {
	
	/**
	 * SELECT de tous les derogation type changement ordonnées par ordre alphabetique
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Derogation_Type_Changement> getAll(){
		Session session = PilotageSession.getCurrentSession();
		List<Derogation_Type_Changement> listDTC = session.createCriteria(Derogation_Type_Changement.class)
																.addOrder(Order.asc("typeChangement"))
																.list();
		session.getTransaction().commit();
		return listDTC;
	}
	
	
	/**
	 * DELETE d'un type de changement
	 * @param selectRow
	 * @throws Exception 
	 */
	public static void delete(Integer selectRow) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			Derogation_Type_Changement dtc = (Derogation_Type_Changement)session.load(Derogation_Type_Changement.class, selectRow);
			session.delete(dtc);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}

	/**
	 * UPDATE d'un type de changement
	 * @param id
	 * @param date
	 * @param text
	 * @param couleur
	 * @param createur
	 * @param old
	 * @throws Exception 
	 */
	public static void modify(Integer id, String typec) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			Derogation_Type_Changement dtc = (Derogation_Type_Changement)session.load(Derogation_Type_Changement.class, id);
			dtc.setTypeChangement(typec);

			session.update(dtc);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	/**
	 * INSERT d'un nouveau type de changement
	 * @param typec
	 * @throws Exception 
	 */
	public static void create(String typec) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			Derogation_Type_Changement dtc = new Derogation_Type_Changement();
			dtc.setTypeChangement(typec);
			
			session.save(dtc);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	/**
	 * SELECT d'un type de changement identifié par le paramètre
	 * @param selectRow
	 * @return
	 */
	public static Derogation_Type_Changement get(Integer selectRow) {
		Session session = PilotageSession.getCurrentSession();
		Derogation_Type_Changement derogationTypeChangement = (Derogation_Type_Changement)session.createCriteria(Derogation_Type_Changement.class)
											.add(Restrictions.eq("id", selectRow))
											.setMaxResults(1)
											.uniqueResult();
		session.getTransaction().commit();
		return derogationTypeChangement;
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
			Criteria criteria = session.createCriteria(Derogation_Type_Changement.class);
			criteria.add(Restrictions.eq("typeChangement", libelle));
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
		Derogation_Type_Changement dtc = (Derogation_Type_Changement)session.createCriteria(Derogation_Type_Changement.class)
											.add(Restrictions.eq("typeChangement", libelle))
											.setMaxResults(1)
											.uniqueResult();
		session.getTransaction().commit();
		return dtc.getId();
	}
}
