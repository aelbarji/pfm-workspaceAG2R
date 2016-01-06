package pilotage.database.bilan;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import pilotage.metier.Bilan;
import pilotage.metier.Bilan_Envoie;
import pilotage.metier.Bilan_Stat;
import pilotage.metier.Users;
import pilotage.service.date.DateService;
import pilotage.session.PilotageSession;

public class BilanDatabaseService {

	/**
	 * SELECT du bilan à une date donnée
	 * @param selectedDate
	 * @return
	 */
	public static Bilan get(Date selectedDate) {
		Session session = PilotageSession.getCurrentSession();
		Bilan result = (Bilan) session.createCriteria(Bilan.class)
									.add(Restrictions.eq("id", DateService.getDayWithoutHour(selectedDate)))
									.setMaxResults(1)
									.uniqueResult();
		session.getTransaction().commit();
		return result;
	}

	/**
	 * INSERT/UPDATE d'un bilan avec ses vacations
	 * @param selectedDate
	 * @param users
	 * @param users2
	 * @param users3
	 * @throws Exception
	 */

	public static void save(Date selectedDate, Integer matin, Integer journee, Integer soir) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			Bilan bilan = (Bilan) session.createCriteria(Bilan.class)
										.add(Restrictions.eq("id", DateService.getDayWithoutHour(selectedDate)))
										.setMaxResults(1)
										.uniqueResult();
			if(bilan == null){
				bilan = new Bilan();
				bilan.setId(DateService.getDayWithoutHour(selectedDate));
				bilan.setVacationmatin(matin == null ? null : (Users)session.load(Users.class, matin));
				bilan.setVacationaprem(journee == null ? null : (Users)session.load(Users.class, journee));
				bilan.setVacationnuit(soir == null ? null : (Users)session.load(Users.class, soir));
				
				session.save(bilan);
			}
			else{
				bilan.setVacationmatin(matin == null ? null : (Users)session.load(Users.class, matin));
				bilan.setVacationaprem(journee == null ? null : (Users)session.load(Users.class, journee));
				bilan.setVacationnuit(soir == null ? null : (Users)session.load(Users.class, soir));
				
				session.update(bilan);
			}
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	/**
	 * INSERT/UPDATE d'une ligne de stat bilan pour le type passé en paramètre
	 * @param typeBilan
	 * @param dateEnvoi
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public static void updateBilanStat(Bilan_Envoie typeBilan, Date dateEnvoi) throws Exception{
		Session session = PilotageSession.getCurrentSession();
		try{
			Bilan_Stat stat;
			
			Date debut = DateService.getMonthEarlier(dateEnvoi);
			Date fin = DateService.getMonthLater(dateEnvoi);
			
			Date debutWithoutHour = DateService.getDayWithoutHour(debut);
			Date finWithoutHour = DateService.getDayWithoutHour(fin);
			
			Date debutPlusUn = DateService.addDays(DateService.getDayWithoutHour(debut), 1);
			Date finMoinsUn = DateService.getDayWithoutHour(fin);
			
			Date time730 = DateService.getTimeFromString("07:30:00");
			
			List<Bilan_Stat> listStat = session.createCriteria(Bilan_Stat.class)
										.add(Restrictions.eq("nomBilan", typeBilan.getNom()))
										.add(Restrictions.or(
												//date entre le 02/X et 01/(X+1)
												Restrictions.and(
														Restrictions.gt("date", debutPlusUn), 
														Restrictions.lt("date", finMoinsUn)
												), 
												Restrictions.or(
														//date le 01/X et heure >= 7:30
														Restrictions.and(
																Restrictions.eq("date", debutWithoutHour),
																Restrictions.ge("heureEnvoi", time730)
														),
														//date le 01/(X+1) et heure < 7h30
														Restrictions.and(
																Restrictions.eq("date", finWithoutHour),
																Restrictions.lt("heureEnvoi", time730)
														)
												)
											)
										)
										.list();
			
			if(listStat == null || listStat.isEmpty()){
				stat = new Bilan_Stat();
				stat.setDate(DateService.getDayWithoutHour(dateEnvoi));
				stat.setHeureEnvoi(DateService.getTimeFromString(DateService.getTime(dateEnvoi, DateService.pt1)));
				stat.setIdBilan(typeBilan.getId());
				stat.setNomBilan(typeBilan.getNom());
				stat.setNbBilansActuels(1);
				stat.setNbBilansAttendus(DateService.getNbDaysInMonth(debut) * typeBilan.getNbDailySent());
			}
			else{
				stat = listStat.get(0);
				
				//ne pas mettre à jour si le dernier envoi est trop rapproché
				Date lastEnvoi = DateService.addTime(stat.getDate(), stat.getHeureEnvoi());
				Date lastEnvoiPlusDecalage = DateService.addTime(lastEnvoi, DateService.getTimeFromString("00:03"));
				if(dateEnvoi.after(lastEnvoiPlusDecalage)){
					stat.setDate(DateService.getDayWithoutHour(dateEnvoi));
					stat.setHeureEnvoi(DateService.getTimeFromString(DateService.getTime(dateEnvoi, DateService.pt1)));
					stat.setNbBilansActuels(stat.getNbBilansActuels() + 1);
				}
			}
			
			if(! DateService.getDayWithoutHour(dateEnvoi).equals(finWithoutHour))
				stat.setNbBilansPresents(DateService.getDayOfMonth(dateEnvoi) * typeBilan.getNbDailySent());
			else
				stat.setNbBilansPresents(DateService.getNbDaysInMonth(debut) * typeBilan.getNbDailySent());
			
			session.saveOrUpdate(stat);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
}
