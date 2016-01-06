package pilotage.database.bilan;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import pilotage.metier.Alertes_Disques_Destinataires;
import pilotage.session.PilotageSession;

public class AlertesDisquesDestinatairesDatabaseService {

	/**
	 * SELECT de tous les destinataires des alertes disques ordonnées par ordre alphabetique
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Alertes_Disques_Destinataires> getAll() {
		Session session = PilotageSession.getCurrentSession();
		List<Alertes_Disques_Destinataires> listDestina = session.createCriteria(Alertes_Disques_Destinataires.class)
																.addOrder(Order.asc("nom"))
																.addOrder(Order.asc("prenom"))
																.list();
		session.getTransaction().commit();
		return listDestina;
	}

	/**
	 * COUNT test si l'adresse mail existe déjà dans les destinataires d'alertes disques
	 * @param id - id à ne pas tester
	 * @param mail
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static boolean exists(Integer id, String mail) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		
		Criteria criteria = session.createCriteria(Alertes_Disques_Destinataires.class)
									.add(Restrictions.eq("mail", mail))
									.setProjection(Projections.rowCount());
		if(id != null)
			criteria.add(Restrictions.ne("id", id));
		
		List<Long>results = criteria.list();
		
		session.getTransaction().commit();
		
		if (results != null && results.size() > 0 && results.get(0) > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * INSERT d'un nouveau destinataire pour les alertes disques
	 * @param mail
	 * @param nom
	 * @param prenom
	 * @throws HibernateException
	 * @throws Exception
	 */
	public static void create(String mail, String nom, String prenom) throws HibernateException, Exception {
		Session session = PilotageSession.getCurrentSession();
		Alertes_Disques_Destinataires alertDestina = new Alertes_Disques_Destinataires();
		alertDestina.setMail(mail);
		alertDestina.setNom(nom);
		alertDestina.setPrenom(prenom);
		session.save(alertDestina);
		session.getTransaction().commit();
	}

	/**
	 * SELECT d'un destinataire pour les alertes disques
	 * @param selectedID
	 * @return
	 */
	public static Alertes_Disques_Destinataires get(Integer selectedID) {
		Session session = PilotageSession.getCurrentSession();
		Alertes_Disques_Destinataires result = (Alertes_Disques_Destinataires) session.createCriteria(Alertes_Disques_Destinataires.class)
														.add(Restrictions.eq("id", selectedID))
														.setMaxResults(1)
														.uniqueResult();
		session.getTransaction().commit();
		return result;
	}

	/**
	 * DELETE d'un destinataire pour les alertes disques
	 * @param selectedID
	 * @throws Exception
	 */
	public static void delete(Integer selectedID) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try {
			Alertes_Disques_Destinataires alertes_destina = (Alertes_Disques_Destinataires)session.load(Alertes_Disques_Destinataires.class, selectedID);
		
			session.delete(alertes_destina);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}

	/**
	 * UPDATE d'un destinataires pour les alertes disques
	 * @param selectedID
	 * @param mail
	 * @param nom
	 * @param prenom
	 * @throws Exception 
	 */
	public static void update(Integer selectedID, String mail, String nom, String prenom) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			Alertes_Disques_Destinataires alertDestina = (Alertes_Disques_Destinataires) session.load(Alertes_Disques_Destinataires.class, selectedID);
			alertDestina.setMail(mail);
			alertDestina.setNom(nom);
			alertDestina.setPrenom(prenom);
			
			session.update(alertDestina);
			session.getTransaction().commit();
		}
		catch (Exception e){
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	public static Integer getId(String libelle){
		Session session = PilotageSession.getCurrentSession();
		Alertes_Disques_Destinataires add = (Alertes_Disques_Destinataires)session.createCriteria(Alertes_Disques_Destinataires.class)
											.add(Restrictions.eq("mail", libelle))
											.setMaxResults(1)
											.uniqueResult();
		session.getTransaction().commit();
		return add.getId();
	}
}
