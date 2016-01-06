package pilotage.database.bilan;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import pilotage.database.machine.MachinesListesDatabaseService;
import pilotage.metier.Alertes_Disques;
import pilotage.metier.Alertes_Type;
import pilotage.metier.Machines_Liste;
import pilotage.metier.Users;
import pilotage.service.date.DateService;
import pilotage.session.PilotageSession;

public class AlertesDisquesDatabaseService {

	/**
	 * SELECT des alertes disques à la date en paramètre
	 * @param selectedDate
	 * @return
	 * @throws Exception  
	 */
	@SuppressWarnings("unchecked")
	public static List<Alertes_Disques> getByDate(Date selectedDate) throws Exception{
		Session session = PilotageSession.getCurrentSession();
		List<Alertes_Disques> listDisques = session.createCriteria(Alertes_Disques.class)
											.add(Restrictions.between("date", DateService.getDateEarlier(selectedDate), DateService.getDateLater(selectedDate)))
											.add(Restrictions.ne("date", DateService.getDateLater(selectedDate)))
											.addOrder(Order.asc("date"))
											.list();
		session.getTransaction().commit();
		return listDisques;
	}
	
	/**
	 * SELECT des alertes disques à une date periode en paramètre
	 * @param dateDebut
	 * @param dateFin
	 * @return
	 * @throws Exception  
	 */
	@SuppressWarnings("unchecked")
	public static List<Alertes_Disques> getByDatePeriode(Date dateDebut, Date dateFin) throws Exception{
		Session session = PilotageSession.getCurrentSession();
		List<Alertes_Disques> listDisques = session.createCriteria(Alertes_Disques.class)
											.add(Restrictions.between("date", DateService.getDateEarlier(dateDebut), DateService.getDateLater(DateService.addDays(dateFin, 1))))
											.add(Restrictions.ne("date", DateService.getDateLater(dateFin)))
											.addOrder(Order.asc("date"))
											.list();
		session.getTransaction().commit();
		return listDisques;
	}

	/**
	 * INSERT d'une nouvelle alerte disque
	 * @param selectedDate
	 * @param systeme
	 * @param fs
	 * @param type
	 * @param alerte
	 * @param seuil
	 * @param users
	 * @throws Exception 
	 */
	public static void create(Date selectedDate, Integer systeme, String fs, Integer type, Integer alerte, Integer seuil,Users users) throws Exception {
		
		Session session = PilotageSession.getCurrentSession();
		try{
			Machines_Liste machine = (Machines_Liste)session.load(Machines_Liste.class, systeme);
			Alertes_Type alerte_type = (Alertes_Type)session.load(Alertes_Type.class, type);
			
			Alertes_Disques ad = new Alertes_Disques();
			ad.setDate(selectedDate);
			ad.setSysteme(machine);
			ad.setAlerte(alerte);
			ad.setFs(fs);
			ad.setSeuil(seuil);
			ad.setType(alerte_type);
			ad.setUser(users);
			
			session.save(ad);
			session.getTransaction().commit();
		}
		catch(Exception e){
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}

	/**
	 * UPDATE d'une alerte disque
	 * @param selectedID
	 * @param selectedDate
	 * @param systeme
	 * @param fs
	 * @param type
	 * @param alerte
	 * @param seuil
	 * @param users
	 * @throws Exception 
	 */
	public static void update(Integer selectedID, Date selectedDate,
			Integer systeme, String fs, Integer type, Integer alerte,
			Integer seuil, Users users) throws Exception {
		Machines_Liste ml = MachinesListesDatabaseService.get(systeme);
		Alertes_Type at = AlertesTypeDatabaseService.get(type);
		
		Session session = PilotageSession.getCurrentSession();
		try {
			Alertes_Disques ad = (Alertes_Disques) session.load(Alertes_Disques.class, selectedID);
			ad.setDate(selectedDate);
			ad.setSysteme(ml);
			ad.setAlerte(alerte);
			ad.setFs(fs);
			ad.setSeuil(seuil);
			ad.setType(at);
			ad.setUser(users);
			
			session.update(ad);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}

	/**
	 * SELECT d'un alerte disque
	 * @param selectedID
	 * @return
	 */
	public static Alertes_Disques get(Integer selectedID) {
		Session session = PilotageSession.getCurrentSession();
		Alertes_Disques result = (Alertes_Disques) session.createCriteria(Alertes_Disques.class)
								.add(Restrictions.eq("id", selectedID))
								.uniqueResult();
		session.getTransaction().commit();
		return result;
	}

	/**
	 * DELETE d'une alerte disque
	 * @param selectedID
	 * @throws Exception 
	 */
	public static void delete(Integer selectedID) throws Exception {
		
		Session session = PilotageSession.getCurrentSession();
		try{
			Alertes_Disques ad = (Alertes_Disques)session.load(Alertes_Disques.class, selectedID);
			session.delete(ad);
			session.getTransaction().commit();
		}
		catch(Exception e){
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	public static Integer getId(Date selectedDate, Integer systeme, String fs, Integer type, Integer alerte, Integer seuil,Users users){
		Session session = PilotageSession.getCurrentSession();
		Machines_Liste machine = (Machines_Liste)session.load(Machines_Liste.class, systeme);
		Alertes_Type alerte_type = (Alertes_Type)session.load(Alertes_Type.class, type);
		
		Alertes_Disques ad = (Alertes_Disques)session.createCriteria(Alertes_Disques.class)
									.add(Restrictions.eq("date", selectedDate))
									.add(Restrictions.eq("systeme", machine))
									.add(Restrictions.eq("fs", fs))
									.add(Restrictions.eq("seuil", seuil))
									.add(Restrictions.eq("type", alerte_type))
									.add(Restrictions.eq("alerte", alerte))
									.add(Restrictions.eq("user", users))
									.setMaxResults(1)
									.uniqueResult();
		session.getTransaction().commit();
		return ad.getId();
	}
}
