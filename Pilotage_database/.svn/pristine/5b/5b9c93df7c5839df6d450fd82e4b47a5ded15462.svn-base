package pilotage.database.gup;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import pilotage.metier.Com_Incident_Etat;
import pilotage.session.PilotageSession;

public class ComIncidentEtatDatabaseService {

	/**
	 * SELECT etat possible selon id
	 * @param id
	 */
	public static Com_Incident_Etat get(Integer id){
		Session session = PilotageSession.getCurrentSession();
		Com_Incident_Etat etat = (Com_Incident_Etat)session.createCriteria(Com_Incident_Etat.class)
									.add(Restrictions.eq("id", id))
									.setMaxResults(1)
									.uniqueResult();
		session.getTransaction().commit();
		return etat;
	}
	
	/**
	 * SELECT de tous les etats
	 */
	@SuppressWarnings("unchecked")
	public static List<Com_Incident_Etat> getAll(){
		Session session = PilotageSession.getCurrentSession();
		List<Com_Incident_Etat> etats = (List<Com_Incident_Etat>)session.createCriteria(Com_Incident_Etat.class)
													.addOrder(Order.asc("id"))
													.list();
		session.getTransaction().commit();
		return etats;
	}
	
	/**
	 * INSERT d'un nouvel état incident GUP
	 * @param libelle
	 * @param definition
	 * @throws Exception 
	 */
	public static void create(String libelle, String definition) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			Com_Incident_Etat etat= new Com_Incident_Etat();
			etat.setLibelle(libelle);
			etat.setDefinition(definition);
			session.save(etat);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}	
	}
	
	/**
	 * MODIFY un nouvel état
	 * @param id
	 * @param libelle
	 * @param definition
	 * @throws Exception 
	 */
	public static void modify(Integer id, String libelle, String definition) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			Com_Incident_Etat etat = (Com_Incident_Etat)session.load(Com_Incident_Etat.class, id);
			etat.setLibelle(libelle);
			etat.setDefinition(definition);
			session.save(etat);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}	
	}
	
	/**
	 * DELETE un état
	 * @param id
	 * @throws Exception 
	 */
	public static void delete(Integer id) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			Com_Incident_Etat etat = (Com_Incident_Etat)session.load(Com_Incident_Etat.class, id);
			session.delete(etat);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
}
