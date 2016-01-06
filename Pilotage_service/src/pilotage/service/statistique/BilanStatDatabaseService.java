package pilotage.service.statistique;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import pilotage.metier.Bilan_Stat;
import pilotage.service.date.DateService;
import pilotage.session.PilotageSession;

public class BilanStatDatabaseService {

	/**
	 * 
	 * @param date
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static List<Bilan_Stat> getByDate(Date date) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		List<Bilan_Stat> results = session.createCriteria(Bilan_Stat.class)
											.add(Restrictions.between("date", 
													DateService.getMonthEarlier(date), 
													DateService.getMonthLater(date)))
											.list();
		session.getTransaction().commit();
		return results;
	}

}
