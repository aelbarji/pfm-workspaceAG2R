package pilotage.database.meteo;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import pilotage.metier.Meteo_Envoi;
import pilotage.metier.Meteo_GroupeMeteo;
import pilotage.session.PilotageSession;

public class MeteoEnvoiDatabaseService {

	/**
	 * SELECT envoi groupe
	 * @param groupe
	 * @param date
	 * @param horaire
	 * @return
	 */
	public static Meteo_Envoi get(Meteo_GroupeMeteo groupe, Date date, Date horaire) {
		Session session = PilotageSession.getCurrentSession();
		Meteo_Envoi envoi = (Meteo_Envoi)session.createCriteria(Meteo_Envoi.class)
																	.add(Restrictions.eq("groupe", groupe))
																	.add(Restrictions.eq("date", date))
																	.add(Restrictions.eq("horaire", horaire))
																	.setMaxResults(1)
																	.uniqueResult();
		session.getTransaction().commit();
		return envoi;
	}
	
	/**
	 * INSERT d'un nouvel envoi du Groupe Météo
	 * @param groupe
	 * @param date
	 * @param horaire
	 * @throws Exception 
	 */
	public static void create(Meteo_GroupeMeteo groupe, Date date, Date horaire, Date date_envoi, Date heure_envoi) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			Meteo_Envoi envoi = new Meteo_Envoi();
			envoi.setDate(date);
			envoi.setGroupe(groupe);
			envoi.setHoraire(horaire);
			envoi.setDate_envoi(date_envoi);
			envoi.setHeure_envoi(heure_envoi);
			session.save(envoi);
			
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}	
	}
	
	/**
	 * UPDATE envoi météo
	 * @param groupe
	 * @param horaire
	 * @throws Exception 
	 */
	public static void modify(Meteo_GroupeMeteo groupe, Date date, Date horaire, Date date_envoi, Date heure_envoi) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		
		try{
			Meteo_Envoi envoi = (Meteo_Envoi)session.createCriteria(Meteo_Envoi.class)
								.add(Restrictions.eq("groupe", groupe))
								.add(Restrictions.eq("date", date))
								.add(Restrictions.eq("horaire", horaire))
								.setMaxResults(1)
								.uniqueResult();
			
			if(envoi != null){
				envoi.setHeure_envoi(heure_envoi);
				envoi.setDate_envoi(date_envoi);
			}
	
			session.update(envoi);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
}
