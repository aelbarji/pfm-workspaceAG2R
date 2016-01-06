package pilotage.database.tdb;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.joda.time.DateTime;

import pilotage.metier.Environnement;
import pilotage.metier.TdB_Commentaire;
import pilotage.metier.Users;
import pilotage.session.PilotageSession;

public class TdBCommentDatabaseService {

	/**
	 * INSERT d'un nouveau commentaire 
	 * @param date
	 * @param environnement
	 * @param pilote
	 * @throws Exception 
	 */
	public static void create(DateTime date, Environnement environnement, String commentaire, Users pilote) throws Exception{
		Session session = PilotageSession.getCurrentSession();
		try {
			TdB_Commentaire comment = new TdB_Commentaire();
			comment.setDate(date);
			comment.setEnvironnement(environnement);
			comment.setCommentaire(commentaire);
			comment.setPilote(pilote);
			session.save(comment);
			session.getTransaction().commit();
			
		} catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}		
	}
	
	/**
	 * UPDATE d'un commentaire
	 * @param id
	 * @param commentaire
	 * @throws Exception 
	 */
	public static void update(Integer id, DateTime date, String commentaire, Users pilote) throws Exception{
		Session session  = PilotageSession.getCurrentSession();
		try {
			TdB_Commentaire comment = (TdB_Commentaire)session.load(TdB_Commentaire.class, id);
			comment.setDate(date);
			comment.setCommentaire(commentaire);
			comment.setPilote(pilote);
			
			session.update(comment);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
		    throw e;	
		}
	}
	
	/**
	 * DELETE comment selon id
	 * @param id
	 * @return
	 */
	public static void delete(Integer id) throws Exception{
		Session session = PilotageSession.getCurrentSession();
		try {
			TdB_Commentaire comment = (TdB_Commentaire) session.load(TdB_Commentaire.class, id);
			session.delete(comment);
			session.getTransaction().commit();
		} catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	/**
	 * SELECT de  le commentaire des dernières 12h sur l'environnement
	 * @param date
	 * @param envir
	 * @return
	 */
	public static TdB_Commentaire getByDateEnvir(DateTime date, Environnement envir) {
		Session session = PilotageSession.getCurrentSession();
		TdB_Commentaire comment = (TdB_Commentaire) session.createCriteria(TdB_Commentaire.class)
										 .add(Restrictions.ge("date", date))	
										 .add(Restrictions.eq("environnement", envir))
										 .uniqueResult();
		session.getTransaction().commit();
		return comment;
	}
}
