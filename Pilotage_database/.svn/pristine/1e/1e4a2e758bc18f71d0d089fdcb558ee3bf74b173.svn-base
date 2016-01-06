package pilotage.database.checklist;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import pilotage.metier.Jour_Ferie;
import pilotage.session.PilotageSession;

public class JourFerieDatabaseService {

	/**
	 * SELECT de tous les jours fériés
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Jour_Ferie> getAll() {
		Session session = PilotageSession.getCurrentSession();
		List<Jour_Ferie> statutList = session.createCriteria(Jour_Ferie.class).list();
		session.getTransaction().commit();
		return statutList;
	}

	/**
	 * SELECT du jour férié
	 * @param id
	 * @return
	 */
	public static Jour_Ferie get(Integer id) {
		Session session = PilotageSession.getCurrentSession();
		Jour_Ferie jour = (Jour_Ferie) session.createCriteria(Jour_Ferie.class)
								.add(Restrictions.eq("id", id))
								.setMaxResults(1)
								.uniqueResult();
		session.getTransaction().commit();
		return jour;
	}
}
