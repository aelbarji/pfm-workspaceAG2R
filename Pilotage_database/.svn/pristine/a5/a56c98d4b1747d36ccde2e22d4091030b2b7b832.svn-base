package pilotage.database.meteo;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import pilotage.metier.Meteo_EtatCourant;
import pilotage.metier.Meteo_Indicateur;
import pilotage.session.PilotageSession;

public class EtatCourantDatabaseService {

	/**
	 * INSERT de l'etat courant d'un service
	 * @param indicateur
	 * @param etat_courant
	 * @param commentaire
	 * @param timestamp
	 * @throws Exception 
	 */
	public static void create(Meteo_Indicateur indicateur, String etat, String commentaire, Date date, Date heureSaisie, Date horaireGroupe, Date heure_debut, Date heure_fin) throws Exception{
		Session session = PilotageSession.getCurrentSession();
		try{
			Meteo_EtatCourant etatCourant = new Meteo_EtatCourant();
			etatCourant.setIndicateur(indicateur);
			etatCourant.setEtat(etat);
			etatCourant.setCommentaire(commentaire);
			etatCourant.setDate(date);
			etatCourant.setHoraire(horaireGroupe);
			etatCourant.setHeure_saisie(heureSaisie);
			etatCourant.setHeure_debut(heure_debut);
			etatCourant.setHeure_fin(heure_fin);
			session.save(etatCourant);
	
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}	
	}
	
	public static void create2(Integer id, String etat, String commentaire, Date date, Date heureSaisie, Date horaireGroupe, Date heure_debut, Date heure_fin) throws Exception{
		Session session = PilotageSession.getCurrentSession();
		Meteo_Indicateur indicateur = (Meteo_Indicateur)session.load(Meteo_Indicateur.class, id);
		try{
			Meteo_EtatCourant etatCourant = new Meteo_EtatCourant();
			etatCourant.setIndicateur(indicateur);
			etatCourant.setEtat(etat);
			etatCourant.setCommentaire(commentaire);
			etatCourant.setDate(date);
			etatCourant.setHoraire(horaireGroupe);
			etatCourant.setHeure_saisie(heureSaisie);
			etatCourant.setHeure_debut(heure_debut);
			etatCourant.setHeure_fin(heure_fin);
			session.save(etatCourant);
	
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}	
	}
	
	public static void create3(List<Meteo_EtatCourant> etats) throws Exception{
		Session session = PilotageSession.getCurrentSession();
		try{
			for(Meteo_EtatCourant etatCourant : etats)
				session.save(etatCourant);
	
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}	
	}
	
	/**
	 * SELECT de l'etat courant selon id, date et horaire
	 * @param id
	 * @param date
	 * @param horaire
	 */
	public static Meteo_EtatCourant getByIndicDate(Meteo_Indicateur idIndicateur, Date date, Date horaire){
		Session session = PilotageSession.getCurrentSession();
		Meteo_EtatCourant etat = (Meteo_EtatCourant)session.createCriteria(Meteo_EtatCourant.class)
																	.add(Restrictions.eq("indicateur", idIndicateur))
																	.add(Restrictions.eq("date", date))
																	.add(Restrictions.eq("horaire", horaire))
																	.setMaxResults(1)
																	.uniqueResult();
		session.getTransaction().commit();
		return etat;
	}
	
	public static Meteo_EtatCourant getByIndicDate2(Integer id, Date date, Date horaire){
		Session session = PilotageSession.getCurrentSession();
		Meteo_Indicateur indicateur =  (Meteo_Indicateur)session.load(Meteo_Indicateur.class, id);
		Meteo_EtatCourant etat = (Meteo_EtatCourant)session.createCriteria(Meteo_EtatCourant.class)
																	.add(Restrictions.eq("indicateur", indicateur))
																	.add(Restrictions.eq("date", date))
																	.add(Restrictions.eq("horaire", horaire))
																	.setMaxResults(1)
																	.uniqueResult();
		session.getTransaction().commit();
		return etat;
	}
	
	/**
	 * UPDATE etat courant
	 * @param indicateur
	 * @param etat
	 * @param commentaire
	 * @param dateMeteo
	 * @param heure_saisie
	 * @param horaire 
	 * @param heure_debut
	 * @param heure_fin
	 * @throws Exception 
	 */
	public static void modify(Meteo_Indicateur indicateur, String etat, String commentaire, Date dateMeteo, Date heure_saisie, Date horaire, Date heure_debut, Date heure_fin) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		
		try{
			Meteo_EtatCourant etatCourant = (Meteo_EtatCourant)session.createCriteria(Meteo_EtatCourant.class)
								.add(Restrictions.eq("indicateur", indicateur))
								.add(Restrictions.eq("date", dateMeteo))
								.add(Restrictions.eq("horaire", horaire))
								.setMaxResults(1)
								.uniqueResult();
			
			if(etatCourant != null){
				etatCourant.setCommentaire(commentaire);
				etatCourant.setEtat(etat);
				etatCourant.setHeure_saisie(heure_saisie);
				etatCourant.setHeure_debut(heure_debut);
				etatCourant.setHeure_fin(heure_fin);
			}
	
			session.update(etatCourant);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	public static void modify2(Integer id, String etat, String commentaire, Date dateMeteo, Date heure_saisie, Date horaire, Date heure_debut, Date heure_fin) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		Meteo_Indicateur indicateur = (Meteo_Indicateur)session.load(Meteo_Indicateur.class, id);
		try{
			Meteo_EtatCourant etatCourant = (Meteo_EtatCourant)session.createCriteria(Meteo_EtatCourant.class)
								.add(Restrictions.eq("indicateur", indicateur))
								.add(Restrictions.eq("date", dateMeteo))
								.add(Restrictions.eq("horaire", horaire))
								.setMaxResults(1)
								.uniqueResult();
			
			if(etatCourant != null){
				etatCourant.setCommentaire(commentaire);
				etatCourant.setEtat(etat);
				etatCourant.setHeure_saisie(heure_saisie);
				etatCourant.setHeure_debut(heure_debut);
				etatCourant.setHeure_fin(heure_fin);
			}
	
			session.update(etatCourant);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
}
