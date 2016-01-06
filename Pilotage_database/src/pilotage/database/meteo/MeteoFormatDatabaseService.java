package pilotage.database.meteo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import pilotage.metier.Meteo_Format;
import pilotage.session.PilotageSession;

public class MeteoFormatDatabaseService {

	/**
	 * INSERT d'un nouveau format lié au groupe météo
	 * @param format
	 * @throws Exception 
	 */
	public static void create(String format) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			Meteo_Format meteoFormat = new Meteo_Format();
			meteoFormat.setFormat(format);
			session.save(meteoFormat);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}	
	}
	
	/**
	 * SELECT d'un format avec son id
	 * @param selectRow
	 * @return
	 */
	public static Meteo_Format get(Integer id) {
		Session session = PilotageSession.getCurrentSession();
		Meteo_Format meteoFormat = (Meteo_Format)session.createCriteria(Meteo_Format.class)
																	.add(Restrictions.eq("id", id))
																	.setMaxResults(1)
																	.uniqueResult();
		session.getTransaction().commit();
		return meteoFormat;
	}
	
	/**
	 * SELECT de tous les formats, ordonnés par id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Meteo_Format> getAll() {
		Session session = PilotageSession.getCurrentSession();
		List<Meteo_Format> formatList = (List<Meteo_Format>)session.createCriteria(Meteo_Format.class)
													.addOrder(Order.asc("id"))
													.list();
		session.getTransaction().commit();
		return formatList;
	}
}
