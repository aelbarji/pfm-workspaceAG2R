package pilotage.database.checklist;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import pilotage.metier.Checklist_Criticite;
import pilotage.session.PilotageSession;

public class ChecklistCriticiteDatabaseService {

	/**
	 * SELECT de toutes les criticités
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Checklist_Criticite> getAll() {
		Session session = PilotageSession.getCurrentSession();
		List<Checklist_Criticite> listCriticite = session.createCriteria(Checklist_Criticite.class).addOrder(Order.asc("libelle")).list();
		session.getTransaction().commit();
		return listCriticite;
	}

	/**
	 * SELECT d'une criticité
	 * @param criticiteID
	 * @return
	 */
	public static Checklist_Criticite get(Integer criticiteID) {
		Session session = PilotageSession.getCurrentSession();
		Checklist_Criticite criticite = (Checklist_Criticite)session.createCriteria(Checklist_Criticite.class)
												.add(Restrictions.eq("id", criticiteID))
												.setMaxResults(1)
												.uniqueResult();
		session.getTransaction().commit();
		return criticite;
	}

}
