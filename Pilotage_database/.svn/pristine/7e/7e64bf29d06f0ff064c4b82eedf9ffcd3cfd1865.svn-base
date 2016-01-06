package pilotage.database.checklist;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import pilotage.metier.Checklist_Etat;
import pilotage.session.PilotageSession;

public class ChecklistEtatDatabaseService {

	/**
	 * SELECT de tous les etats
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Checklist_Etat> getAll() {
		Session session = PilotageSession.getCurrentSession();
		List<Checklist_Etat> listEtat = session.createCriteria(Checklist_Etat.class).addOrder(Order.asc("etat")).list();
		session.getTransaction().commit();
		return listEtat;
	}

	/**
	 * SELECT d'un etat
	 * @param etatID
	 * @return
	 */
	public static Checklist_Etat get(Integer etatID) {
		Session session = PilotageSession.getCurrentSession();
		Checklist_Etat etat = (Checklist_Etat)session.createCriteria(Checklist_Etat.class)
										.add(Restrictions.eq("id", etatID))
										.setMaxResults(1)
										.uniqueResult();
		session.getTransaction().commit();
		return etat;
	}

}
