package pilotage.database.incidents;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import pilotage.metier.Heures_Oceor;
import pilotage.session.PilotageSession;

/**
 * @author xxu
 *
 */
public class HeuresOceorDatabaseService {

	/**
	 * SELECT de tous les heures océor ordonnées par nom
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Heures_Oceor> getAll() {
		Session session = PilotageSession.getCurrentSession();
		List<Heures_Oceor> list = session.createCriteria(Heures_Oceor.class)
										.addOrder(Order.asc("nom"))
										.list();
		session.getTransaction().commit();
		return list;
	}
	
	public static String getTimezone(Integer idZone){
		Session session = PilotageSession.getCurrentSession();
		Heures_Oceor zone =(Heures_Oceor)session.load(Heures_Oceor.class, idZone);
		String timezone = zone.getTimezone();
		return timezone;
	}
	
	/**
	 * SELECT de tous les heures océor ordonnées par heure
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Heures_Oceor> getAllByHourAsc() {
		Session session = PilotageSession.getCurrentSession();
		try {
			Query query = session.createQuery("from Heures_Oceor h order by heure+0");
			List<Heures_Oceor> list = query.list();
			session.getTransaction().commit();
			return list;
			
		} catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			e.printStackTrace();
			return null;
		}
	}
}

