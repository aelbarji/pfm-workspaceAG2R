package pilotage.database.checklist;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import pilotage.metier.Checklist_Status;
import pilotage.service.constants.PilotageConstants;
import pilotage.session.PilotageSession;

public class ChecklistStatutDatabaseService {

	/**
	 * SELECT de la liste des statuts de la checklist ordonnés par ordre alphabétique
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Checklist_Status> getAll() {
		Session session = PilotageSession.getCurrentSession();
		List<Checklist_Status> statutList = session.createCriteria(Checklist_Status.class).addOrder(Order.asc("status")).list();
		session.getTransaction().commit();
		return statutList;
	}

	/**
	 * SELECT du statut identifié par le paramètre
	 * @param statutID
	 * @return
	 */
	public static Checklist_Status get(Integer statutID) {
		Session session = PilotageSession.getCurrentSession();
		Checklist_Status statut = (Checklist_Status)session.createCriteria(Checklist_Status.class)
									.add(Restrictions.eq("id", statutID))
									.setMaxResults(1)
									.uniqueResult();
		session.getTransaction().commit();
		return  statut;
	}

	/**
	 * SELECT de la liste des statuts de la checklist ordonnés par ordre alphabétique pour les taches des jours futures
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Checklist_Status> getAllFuturStatut() {
		List<Integer> listStatutID = new ArrayList<Integer>();
		listStatutID.add(PilotageConstants.STATUT_A_VENIR);
		listStatutID.add(PilotageConstants.STATUT_ANNULE);
		
		Session session = PilotageSession.getCurrentSession();
		List<Checklist_Status> statutList = session.createCriteria(Checklist_Status.class)
												.add(Restrictions.in("id", listStatutID))
												.addOrder(Order.asc("status"))
												.list();
		session.getTransaction().commit();
		return statutList;
	}
}
