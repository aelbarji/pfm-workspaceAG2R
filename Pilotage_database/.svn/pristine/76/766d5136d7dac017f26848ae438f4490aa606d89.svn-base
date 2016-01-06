package pilotage.database.derogation;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import pilotage.metier.Derogation_Valideur;
import pilotage.metier.Users;
import pilotage.session.PilotageSession;

public class DerogationValideurDatabaseService {

	/**
	 * DELETE d'un valideur
	 * @param selectRow
	 */
	public static void delete(Integer selectRow) throws Exception{
		Session session = PilotageSession.getCurrentSession();
		try{
			Derogation_Valideur derogationValideur = (Derogation_Valideur)session.load(Derogation_Valideur.class, selectRow);
			session.delete(derogationValideur);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	/**
	 * INSERT d'un nouveau valideur
	 * @param valideur
	 * @throws Exception 
	 */
	public static void create(Integer valideur) throws Exception{
		Session session = PilotageSession.getCurrentSession();
		
		try{
			Users user = (Users) session.load(Users.class, valideur);

			Derogation_Valideur derogationValideur = new Derogation_Valideur();
			derogationValideur.setValideur(user);		
			
			session.save(derogationValideur);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	/**
	 * SELECT d'un valideur
	 * @param selectRow
	 * @return
	 */
	public static Derogation_Valideur get(Integer selectRow) {
		Session session = PilotageSession.getCurrentSession();
		Derogation_Valideur derogationValideur = (Derogation_Valideur)session.createCriteria(Derogation_Valideur.class)
							.add(Restrictions.eq("id", selectRow))
							.setMaxResults(1)
							.uniqueResult();
		session.getTransaction().commit();
		return derogationValideur;
	}

	/**
	 * SELECT de tous les utilisateurs valideurs
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Derogation_Valideur> getAll() {
		Session session = PilotageSession.getCurrentSession();
		
		List<Derogation_Valideur> derogValid = session.createCriteria(Derogation_Valideur.class)
														.createAlias("valideur", "user", Criteria.LEFT_JOIN)
														.addOrder(Order.asc("user.nom"))
														.addOrder(Order.asc("user.prenom"))
														.list();
		session.getTransaction().commit();
		return derogValid;
	}
	
	public static Integer getId(Integer valideur){
		Session session = PilotageSession.getCurrentSession();
		Users user = (Users) session.load(Users.class, valideur);
		
		Derogation_Valideur dv = (Derogation_Valideur)session.createCriteria(Derogation_Valideur.class)
									.add(Restrictions.eq("valideur", user))
									.setMaxResults(1)
									.uniqueResult();
		session.getTransaction().commit();
		return dv.getId();
	}
}
