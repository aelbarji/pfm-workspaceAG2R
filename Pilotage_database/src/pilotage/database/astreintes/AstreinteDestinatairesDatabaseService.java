package pilotage.database.astreintes;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import pilotage.metier.Astreinte_Destinataires;
import pilotage.session.PilotageSession;

public class AstreinteDestinatairesDatabaseService {

	/**
	 * SELECT de tous les destinataires correspondants au type de bilan sélectionné
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Astreinte_Destinataires> getAll() {
		Session session = PilotageSession.getCurrentSession();
		
		List<Astreinte_Destinataires> listDestina = session.createCriteria(Astreinte_Destinataires.class)
													.addOrder(Order.asc("nom"))
													.addOrder(Order.asc("prenom"))
													.list();
		session.getTransaction().commit();
		return listDestina;
	}

	/**
	 * INSERT d'un nouveau destinataire pour un type de bilan
	 * @param nom
	 * @param prenom
	 * @param email
	 * @param type_bilanID
	 * @throws Exception
	 */
	public static void create(String nom, String prenom, String email) throws Exception{
		Session session = PilotageSession.getCurrentSession();
		try{
			
			Astreinte_Destinataires ad = new Astreinte_Destinataires();
			ad.setMail(email);
			ad.setNom(nom);
			ad.setPrenom(prenom);
			
			session.save(ad);
			session.getTransaction().commit();
		}
		catch (Exception e){
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}

	/**
	 * SELECT d'un destinataire de bilan
	 * @param selectedID
	 * @return
	 */
	public static Astreinte_Destinataires get(int selectedID) {
		Session session = PilotageSession.getCurrentSession();
		Astreinte_Destinataires result = (Astreinte_Destinataires) session.createCriteria(Astreinte_Destinataires.class)
											.add(Restrictions.eq("id", selectedID))
											.setMaxResults(1)
											.uniqueResult();
		session.getTransaction().commit();
		return result;
	}

	/**
	 * UPDATE d'un destinataire pour un type de bilan
	 * @param selectedID
	 * @param nom
	 * @param prenom
	 * @param mail
	 * @param type_bilanID
	 * @throws Exception
	 */
	public static void update(int selectedID, String nom, String prenom, String mail) throws Exception{
		Session session = PilotageSession.getCurrentSession();
		try {
			Astreinte_Destinataires ad = (Astreinte_Destinataires) session.get(Astreinte_Destinataires.class, selectedID);
			ad.setMail(mail);
			ad.setNom(nom);
			ad.setPrenom(prenom);
			
			session.update(ad);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}

	/**
	 * DELETE d'un destinataire pour un type de bilan
	 * @param selectedID
	 */
	public static void delete(Integer selectedID) throws Exception{
		Session session = PilotageSession.getCurrentSession();
		try{
			Astreinte_Destinataires ad = (Astreinte_Destinataires)session.get(Astreinte_Destinataires.class, selectedID);
			
			session.delete(ad);
			session.getTransaction().commit();
		}
		catch(Exception e){
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	/**
	 * COUNT test si l'adresse mail existe déjà dans la liste de diffusion du type de bilan
	 * @param id - id à ne pas tester
	 * @param mail
	 * @param typeBilanID
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static boolean exists(Integer id, String mail) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		
		Criteria criteria = session.createCriteria(Astreinte_Destinataires.class)
									.add(Restrictions.eq("mail", mail))
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
	
	public static Integer getId(String mail){
		Session session = PilotageSession.getCurrentSession();
		Astreinte_Destinataires ad = (Astreinte_Destinataires)session.createCriteria(Astreinte_Destinataires.class)
									.add(Restrictions.eq("mail", mail))
									.setMaxResults(1)
									.uniqueResult();
		session.getTransaction().commit();
		return ad.getId();
	}
}
