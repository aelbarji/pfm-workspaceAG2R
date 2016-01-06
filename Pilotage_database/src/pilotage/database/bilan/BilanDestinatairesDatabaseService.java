package pilotage.database.bilan;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import pilotage.metier.Bilan_Destinataires;
import pilotage.metier.Bilan_Envoie;
import pilotage.session.PilotageSession;

public class BilanDestinatairesDatabaseService {

	/**
	 * SELECT de tous les destinataires correspondants au type de bilan sélectionné
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Bilan_Destinataires> getAll(Integer bilanTypeID) {
		Session session = PilotageSession.getCurrentSession();
		
		Bilan_Envoie typeBilan = bilanTypeID == null ? null : (Bilan_Envoie)session.load(Bilan_Envoie.class, bilanTypeID);
		List<Bilan_Destinataires> listDestina = session.createCriteria(Bilan_Destinataires.class)
													.add(Restrictions.eq("type_bilan", typeBilan))
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
	public static void create(String nom, String prenom, String email, Integer type_bilanID) throws Exception{
		Session session = PilotageSession.getCurrentSession();
		try{
			Bilan_Envoie type_bilan = (Bilan_Envoie)session.load(Bilan_Envoie.class, type_bilanID);
			
			Bilan_Destinataires bd = new Bilan_Destinataires();
			bd.setMail(email);
			bd.setNom(nom);
			bd.setPrenom(prenom);
			bd.setType_bilan(type_bilan);
			
			session.save(bd);
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
	public static Bilan_Destinataires get(int selectedID) {
		Session session = PilotageSession.getCurrentSession();
		Bilan_Destinataires result = (Bilan_Destinataires) session.createCriteria(Bilan_Destinataires.class)
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
	public static void update(int selectedID, String nom, String prenom, String mail, Integer type_bilanID) throws Exception{
		Session session = PilotageSession.getCurrentSession();
		try {
			Bilan_Envoie type_bilan = (Bilan_Envoie)session.load(Bilan_Envoie.class, type_bilanID);
			
			Bilan_Destinataires bd = (Bilan_Destinataires) session.load(Bilan_Destinataires.class, selectedID);
			bd.setMail(mail);
			bd.setNom(nom);
			bd.setPrenom(prenom);
			bd.setType_bilan(type_bilan);
			
			session.update(bd);
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
			Bilan_Destinataires bd = (Bilan_Destinataires)session.load(Bilan_Destinataires.class, selectedID);
			
			session.delete(bd);
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
	public static boolean exists(Integer id, String mail, Integer typeBilanID) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		
		Bilan_Envoie typeBilan = (Bilan_Envoie)session.load(Bilan_Envoie.class, typeBilanID);
		Criteria criteria = session.createCriteria(Bilan_Destinataires.class)
									.add(Restrictions.eq("mail", mail))
									.add(Restrictions.eq("type_bilan", typeBilan))
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
	
	public static Integer getId(String libelle, Integer selectedType){
		Session session = PilotageSession.getCurrentSession();
		Bilan_Envoie typeBilan = (Bilan_Envoie)session.load(Bilan_Envoie.class, selectedType);

		Bilan_Destinataires bd = (Bilan_Destinataires)session.createCriteria(Bilan_Destinataires.class)
									.add(Restrictions.eq("mail", libelle))
									.add(Restrictions.eq("type_bilan", typeBilan))
									.setMaxResults(1)
									.uniqueResult();
		session.getTransaction().commit();
		return bd.getId();
	}
}
