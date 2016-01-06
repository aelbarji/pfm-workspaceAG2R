package pilotage.database.bilan;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import pilotage.metier.Disques;
import pilotage.metier.Espaces_Disques;
import pilotage.service.date.DateService;
import pilotage.session.PilotageSession;

public class EspacesDisquesDatabaseService {

	/**
	 * SELECT des espaces disques pour la date précisée
	 * @param selectedDate
	 * @return
	 * @throws  
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public static List<Espaces_Disques> getAllByDate(Date date) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		List<Espaces_Disques> listEspaces = session.createCriteria(Espaces_Disques.class)
										.add(Restrictions.between("date", DateService.getDateEarlier(date), DateService.getDateLater(date)))
										.addOrder(Order.asc("date"))
										.list();
		session.getTransaction().commit();
		return listEspaces;
	}
	
	/**
	 * SELECT des espaces disques pour la date periode
	 * @param dateDebut
	 * @param dateFin
	 * @return
	 * @throws  
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public static List<Espaces_Disques> getAllByDatePeriode(Date dateDebut, Date dateFin) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		List<Espaces_Disques> listEspaces = session.createCriteria(Espaces_Disques.class)
										.add(Restrictions.between("date", DateService.getDateEarlier(dateDebut), DateService.getDateLater(DateService.addDays(dateFin, 1))))
										.addOrder(Order.asc("date"))
										.list();
		session.getTransaction().commit();
		return listEspaces;
	}
	
	/**
	 * SAVE/UPDATE d'un espace disque
	 * @param disqueId
	 * @param date
	 * @param espace
	 * @param seuil 
	 * @throws Exception
	 */
	public static void save(int disqueId, Date date, float espace, Float seuil) throws Exception {
		if(exists(date, disqueId)){
			Espaces_Disques ed = get(date, disqueId);
			Session session = PilotageSession.getCurrentSession();
			
			try {
				ed.setEspace(espace);
				ed.setSeuil(seuil == null ? ed.getDisque().getSeuil() : seuil);
				
				session.update(ed);
				session.getTransaction().commit();
			}
			catch (Exception e) {
				PilotageSession.rollbackTransaction(session);
				throw e;
			}
		}
		else{
			Session session = PilotageSession.getCurrentSession();
			
			try {
				Disques d = (Disques)session.load(Disques.class, disqueId);
				
				Espaces_Disques ed = new Espaces_Disques();
				ed.setDate(date);
				ed.setEspace(espace);
				ed.setDisque(d);
				ed.setSeuil(seuil == null ? d.getSeuil() : seuil);
				
				session.save(ed);
				session.getTransaction().commit();
			}
			catch (Exception e) {
				PilotageSession.rollbackTransaction(session);
				throw e;
			}
		}
	}

	/**
	 * SELECT d'un espace disque
	 * @param date 
	 * @param id
	 * @return
	 * @throws  
	 * @throws Exception 
	 */
	public static Espaces_Disques get(Date date, int idDisque) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		
		Disques disque = (Disques)session.load(Disques.class, idDisque);
		Espaces_Disques ed = (Espaces_Disques) session.createCriteria(Espaces_Disques.class)
									.add(Restrictions.eq("disque", disque))
									.add(Restrictions.between("date", DateService.getDateEarlier(date), DateService.getDateLater(date)))
									.setMaxResults(1)
									.uniqueResult();
		session.getTransaction().commit();
		return ed;
	}

	/**
	 * COUNT test si l'espace disque correspondant à la date et au disque existe
	 * @param date
	 * @param idDisque
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static boolean exists(Date date, Integer idDisque) throws Exception{
		Session session = PilotageSession.getCurrentSession();
		
		Disques d = (Disques)session.load(Disques.class, idDisque);
		List<Long> results = session.createCriteria(Espaces_Disques.class)
									.add(Restrictions.between("date", DateService.getDateEarlier(date), DateService.getDateLater(date)))
									.add(Restrictions.eq("disque", d))
									.setProjection(Projections.rowCount())
									.list();
		session.getTransaction().commit();
		
		if (results != null && results.size() > 0 && results.get(0) > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * DELETE d'un espace disque
	 * @param idDisque
	 * @param selectedDate
	 * @throws Exception 
	 */
	public static void delete(int idDisque, Date date) throws Exception {
		Espaces_Disques ed = get(date, idDisque);
		
		Session session = PilotageSession.getCurrentSession();
		try{
			session.delete(ed);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
		
	}

	/**
	 * @param yesterday
	 * @param d
	 * @return
	 * @throws  
	 * @throws Exception 
	 */
	public static Float getByDateAndDisque(Date date, Disques d) throws Exception {
		// TODO Auto-generated method stub
		Session session = PilotageSession.getCurrentSession();
		Espaces_Disques ed = (Espaces_Disques) session.createCriteria(Espaces_Disques.class)
											.add(Restrictions.between("date", DateService.getDateEarlier(date), DateService.getDateLater(date)))
											.add(Restrictions.eq("disque", d))
											.uniqueResult();
		session.getTransaction().commit();
											
		return ed == null? 0 : ed.getEspace();
	}
}
