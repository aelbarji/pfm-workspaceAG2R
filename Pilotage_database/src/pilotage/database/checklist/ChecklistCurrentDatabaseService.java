package pilotage.database.checklist;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import pilotage.metier.Checklist_Current;
import pilotage.metier.Checklist_Horaire;
import pilotage.session.PilotageSession;

public class ChecklistCurrentDatabaseService {

	/**
	 * SELECT status pour demandes
	 */
	public static String getStatus(Checklist_Horaire checklistHoraire) {
		Session session = PilotageSession.getCurrentSession();
		Criteria criteria = session.createCriteria(Checklist_Current.class);
		Checklist_Current cc = (Checklist_Current) criteria.createAlias("tache", "t")
				.add(Restrictions.eqProperty("t.dateDebut", "jour"))
				.add(Restrictions.eq("t.id", checklistHoraire.getIdChecklist().getId()))
				.add(Restrictions.eq("idHoraire.id", checklistHoraire.getId()))
				.uniqueResult();
		
		String status = "";
		if (cc != null) {
			status = cc.getStatus().getStatus();
		}
		session.close();
		return status;
	}
}
