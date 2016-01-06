package pilotage.database.meteo;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import pilotage.metier.Meteo_GroupeMeteo;
import pilotage.metier.Meteo_GroupeMeteo_Commentaire;
import pilotage.session.PilotageSession;

public class GroupeMeteoCommentaireDatabaseService {

	/**
	 * INSERT d'un nouveau commentaire affilié au groupe météo
	 * @param groupe
	 * @param commentaire
	 * @param date
	 * @param horaire
	 * @throws Exception 
	 */
	public static void create(Meteo_GroupeMeteo groupe, String commentaire, Date date, Date horaire) throws Exception{
		Session session = PilotageSession.getCurrentSession();
		try {
			Meteo_GroupeMeteo_Commentaire comment = new Meteo_GroupeMeteo_Commentaire();
			comment.setGroupe(groupe);
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
	 * SELECT du commentaire du groupe météo
	 * @param groupe
	 * @param date
	 * @param horaire
	 * @return
	 */
	public static Meteo_GroupeMeteo_Commentaire get(Meteo_GroupeMeteo groupe, Date date, Date horaire){
		Session session = PilotageSession.getCurrentSession();
		Meteo_GroupeMeteo_Commentaire comment = (Meteo_GroupeMeteo_Commentaire)session.createCriteria(Meteo_GroupeMeteo_Commentaire.class)
		                                .add(Restrictions.eq("groupe", groupe))
		                                .add(Restrictions.eq("date", date))
		                                .add(Restrictions.eq("horaire", horaire))
		                                .setMaxResults(1)
		                                .uniqueResult();
		session.getTransaction().commit();
		return comment;
	}
	
	/**
	 * UPDATE d'un commentaire groupe météo
	 * @param id
	 * @param commentaire
	 * @throws Exception 
	 */
	public static void update(Integer id, String commentaire) throws Exception{
		Session session  = PilotageSession.getCurrentSession();
		try {
			Meteo_GroupeMeteo_Commentaire comment = (Meteo_GroupeMeteo_Commentaire)session.load(Meteo_GroupeMeteo_Commentaire.class, id);
			comment.setCommentaire(commentaire);
			
			session.update(comment);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
		    throw e;	
		}
	}
	
	/**
	 * DELETE d'un commentaire groupe météo
	 * @param id
	 * @throws Exception 
	 */
	public static void delete(Integer id) throws Exception{
		Session session = PilotageSession.getCurrentSession();
		try{
			Meteo_GroupeMeteo_Commentaire comment = (Meteo_GroupeMeteo_Commentaire)session.load(Meteo_GroupeMeteo_Commentaire.class, id);
			session.delete(comment);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
}
