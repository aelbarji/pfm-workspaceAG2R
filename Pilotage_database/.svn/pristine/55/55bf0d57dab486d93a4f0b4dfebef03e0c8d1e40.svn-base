package pilotage.database.meteo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.joda.time.DateTime;

import pilotage.metier.Meteo_GroupeMeteo;
import pilotage.metier.Meteo_GroupeMeteo_Horaire;
import pilotage.session.PilotageSession;

public class GroupeMeteoHoraireDatabaseService {

	/**
	 * INSERT d'un nouvel horaire au Groupe Météo
	 * @param groupe
	 * @param liste horaires
	 * @throws Exception 
	 */
	public static void create(Meteo_GroupeMeteo groupe, List<Date> listHoraires) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			for(Date horaire : listHoraires){
				Meteo_GroupeMeteo_Horaire groupeHoraire = new Meteo_GroupeMeteo_Horaire();
				groupeHoraire.setGroupeMeteo(groupe);
				groupeHoraire.setHoraire(horaire);
				session.save(groupeHoraire);
			}
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}	
	}
	
	/**
	 * INSERT d'un horaire exceptionnel au Groupe Météo
	 * @param groupe
	 * @param liste horaires
	 * @throws Exception 
	 */
	public static void createException(Meteo_GroupeMeteo groupe, Date date, Date horaire) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			Meteo_GroupeMeteo_Horaire groupeHoraire = new Meteo_GroupeMeteo_Horaire();
			groupeHoraire.setGroupeMeteo(groupe);
			groupeHoraire.setHoraire(horaire);
			groupeHoraire.setDateException(date);
			session.save(groupeHoraire);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}	
	}
	
	/**
	 * SELECT horaires selon id du groupe et date
	 * @param idGroupe
	 * @param date
	 */
	@SuppressWarnings("unchecked")
	public static List<Date> getHoraires(Integer idGroupe, Date date){
		List<Date> horaires = new ArrayList<Date>();
		DateTime datetime = new DateTime();
		DateTime dateDebut = datetime;
		DateTime dateFin = datetime;
		
		if(date != null){
			datetime = new DateTime(date);
			dateDebut = new DateTime(datetime.getYear(),datetime.getMonthOfYear(), datetime.getDayOfMonth(),0,0,0);
			dateFin = dateDebut.plusHours(24);
		}
		
		if(idGroupe != null){
			Session session = PilotageSession.getCurrentSession();

			Meteo_GroupeMeteo groupe = (Meteo_GroupeMeteo)session.load(Meteo_GroupeMeteo.class, idGroupe);		
			List<Meteo_GroupeMeteo_Horaire> groupeHoraire = null;
			
			if(date != null){
				groupeHoraire = session.createCriteria(Meteo_GroupeMeteo_Horaire.class)
						.add(Restrictions.eq("groupeMeteo", groupe))
						.add(Restrictions.le("dateDebut", dateFin))
						.add(Restrictions.or(Restrictions.isNull("dateFin"), Restrictions.gt("dateFin", dateDebut)))
						.addOrder(Order.asc("horaire"))
						.list();
			}else{
				groupeHoraire = session.createCriteria(Meteo_GroupeMeteo_Horaire.class)
						.add(Restrictions.eq("groupeMeteo", groupe))
						.add(Restrictions.le("dateDebut", dateDebut))
						.add(Restrictions.or(Restrictions.isNull("dateFin"), Restrictions.gt("dateFin", dateFin)))
						.addOrder(Order.asc("horaire"))
						.list();
			}
			
			for(int i=0;i<groupeHoraire.size();i++){
				if(!horaires.contains(groupeHoraire.get(i).getHoraire()) && groupeHoraire.get(i).getDateException() == null){
					horaires.add(groupeHoraire.get(i).getHoraire());
				}
			}
			
			if(date != null){
				List<Meteo_GroupeMeteo_Horaire> groupeHoraireExcept = session.createCriteria(Meteo_GroupeMeteo_Horaire.class)
						.add(Restrictions.eq("groupeMeteo", groupe))
						.add(Restrictions.eq("dateException", date))
						.addOrder(Order.asc("horaire"))
						.list();
				
				
				
				for(Meteo_GroupeMeteo_Horaire ho : groupeHoraireExcept){
					if(!horaires.contains(ho.getHoraire())){
						horaires.add(ho.getHoraire());
					}
				}
			}
			
			session.getTransaction().commit();
		}

		return horaires;
	}
	
	/**
	 * MODIFY horaires du groupe
	 * @param groupe
	 * @param horaireToAdd
	 * @param horaireToDelete
	 * @throws Exception
	 */
	public static void modify(Meteo_GroupeMeteo groupe, List<Date> horaireToAdd, List<Date> horaireToDelete) throws Exception{
		Session session = PilotageSession.getCurrentSession();
		try{
			DateTime datetime = new DateTime();
			List<Meteo_GroupeMeteo_Horaire> listHoraire = new ArrayList<Meteo_GroupeMeteo_Horaire>();
			//List<Meteo_GroupeMeteo_Horaire> listHoraireToDelete = new ArrayList<Meteo_GroupeMeteo_Horaire>();
			
			for(Date horaire : horaireToAdd){
				Meteo_GroupeMeteo_Horaire groupeHoraire = new Meteo_GroupeMeteo_Horaire();
				groupeHoraire.setGroupeMeteo(groupe);
				groupeHoraire.setHoraire(horaire);
				groupeHoraire.setDateDebut(datetime);
				listHoraire.add(groupeHoraire);
			}
			
			for(Date horaire : horaireToDelete){
				Meteo_GroupeMeteo_Horaire groupeHoraire = (Meteo_GroupeMeteo_Horaire)session.createCriteria(Meteo_GroupeMeteo_Horaire.class)
																	.add(Restrictions.eq("horaire", horaire))
																	.add(Restrictions.eq("groupeMeteo", groupe))
																	.setMaxResults(1)
																	.uniqueResult();
				groupeHoraire.setDateFin(datetime);
				listHoraire.add(groupeHoraire);
			}
			
			for(Meteo_GroupeMeteo_Horaire groupeHoraire : listHoraire) {
				session.save(groupeHoraire);
			}
			/*for(Meteo_GroupeMeteo_Horaire groupeHoraire : listHoraireToDelete) {
				session.delete(groupeHoraire);
			}*/
			
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	/**
	 * SELECT dernier horaire saisie du groupe
	 * @param date
	 * @param horaire
	 * @param idGroupe
	 * @param exception
	 * @throws ParseException 
	 */
	@SuppressWarnings("unchecked")
	public static Meteo_GroupeMeteo_Horaire getDernierHoraireSaisie(Date date, Date horaire, Integer idGroupe, int exception) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
		String datehoraire = "00:00:00";
		Date horaireDebut = simpleDateFormat.parse(datehoraire);
		
		Session session = PilotageSession.getCurrentSession();
		Meteo_GroupeMeteo groupe = (Meteo_GroupeMeteo)session.load(Meteo_GroupeMeteo.class, idGroupe);
		List<Meteo_GroupeMeteo_Horaire> groupeHoraire = session.createCriteria(Meteo_GroupeMeteo_Horaire.class)
																	.add(Restrictions.eq("groupeMeteo", groupe))
																	.add(Restrictions.between("horaire", horaireDebut, horaire))
																	.add(Restrictions.or(Restrictions.eq("dateException", date), Restrictions.isNull("dateException")))
																	.addOrder(Order.asc("horaire"))
																	.list();
		
		session.getTransaction().commit();
		Meteo_GroupeMeteo_Horaire groupeHoraireRecup = null;
		if(groupeHoraire.size()>1 && exception ==0)
			groupeHoraireRecup = groupeHoraire.get(groupeHoraire.size()-2);
		else if(groupeHoraire.size()>=1 && exception ==1)
			groupeHoraireRecup = groupeHoraire.get(groupeHoraire.size()-1);
		return groupeHoraireRecup;
	}
}
