package pilotage.database.meteo;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import pilotage.metier.Meteo_Commentaire;
import pilotage.metier.Meteo_Meteo;
import pilotage.session.PilotageSession;

public class MeteoCommentaireDatabaseService {

	/**
	 * INSERT d'un nouveau commentaire affilié à une météo
	 * @param meteo
	 * @param commentaire
	 * @param date
	 * @param horaire
	 * @throws Exception 
	 */
	public static void create(Meteo_Meteo meteo, String commentaire, Date date, Date horaire) throws Exception{
		Session session = PilotageSession.getCurrentSession();
		try {
			Meteo_Commentaire comment = new Meteo_Commentaire();
			comment.setMeteo(meteo);
			comment.setCommentaire(commentaire);
			comment.setDate(date);
			comment.setHoraire(horaire);
			
			session.save(comment);
			session.getTransaction().commit();
			
		} catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}		
	}
	
	/**
	 * SELECT du commentauire météo
	 * @param idMeteo
	 * @return
	 */
	public static Meteo_Commentaire get(Meteo_Meteo meteo, Date date, Date horaire){
		Session session = PilotageSession.getCurrentSession();
		Meteo_Commentaire comment = (Meteo_Commentaire)session.createCriteria(Meteo_Commentaire.class)
		                                .add(Restrictions.eq("meteo", meteo))
		                                .add(Restrictions.eq("date", date))
		                                .add(Restrictions.eq("horaire", horaire))
		                                .setMaxResults(1)
		                                .uniqueResult();
		session.getTransaction().commit();
		return comment;
	}
	
	/**
	 * UPDATE d'un commentaire météo
	 * @param id
	 * @param commentaire
	 * @throws Exception 
	 */
	public static void update(Integer id, String commentaire) throws Exception{
		Session session  = PilotageSession.getCurrentSession();
		try {
			Meteo_Commentaire comment = (Meteo_Commentaire)session.load(Meteo_Commentaire.class, id);
			comment.setCommentaire(commentaire);
			
			session.update(comment);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
		    throw e;	
		}
	}

}
