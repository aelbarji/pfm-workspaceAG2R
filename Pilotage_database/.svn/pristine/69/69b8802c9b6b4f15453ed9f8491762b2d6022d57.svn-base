package pilotage.database.checklist;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import pilotage.metier.Checklist_Base;
import pilotage.metier.Checklist_Mensuel;
import pilotage.session.PilotageSession;


public class ChecklistMensuelDatabaseService {
	
	/**
	 * SELECT de la liste suivant la base
	 * @param baseID
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Checklist_Mensuel> getListFromBase(Integer baseID){
		Session session = PilotageSession.getCurrentSession();
		
		Checklist_Base base = (Checklist_Base)session.load(Checklist_Base.class, baseID);
		List<Checklist_Mensuel> mensuelles = session.createCriteria(Checklist_Mensuel.class)
															.add(Restrictions.eq("idChecklist", base))
															.list();
		session.getTransaction().commit();
		return mensuelles;
	}

	/**
	 * COUNT test si la base en paramètre est une tache mensuelle
	 * @param baseID
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static boolean isFrequenceMensuelle(Integer baseID) {
		Session session = PilotageSession.getCurrentSession();
		
		List<Long> results = null;
		Checklist_Base base = (Checklist_Base)session.load(Checklist_Base.class, baseID);		
		results =  session.createCriteria(Checklist_Mensuel.class)
						.add(Restrictions.eq("idChecklist", base))
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
	 * SELECT de tous les checklist_mensuel ayant la place dans la liste en paramètre
	 * @param placesID
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Checklist_Mensuel> getFromPlacesList(List<Integer> placesID) {
		Session session = PilotageSession.getCurrentSession();
		
		List<Checklist_Mensuel> listChecklistMensuel = session.createCriteria(Checklist_Mensuel.class)
															.add(Restrictions.in("mensuel", placesID))
															.list();
		session.getTransaction().commit();
		return listChecklistMensuel;
	}
	
	public static Integer getId(Checklist_Base checklist, Integer mensuel) {
		Session session = PilotageSession.getCurrentSession();
		
		Checklist_Mensuel cm = (Checklist_Mensuel)session.createCriteria(Checklist_Mensuel.class)
										.add(Restrictions.eq("idChecklist",checklist))
										.add(Restrictions.eq("mensuel",mensuel))
										.setMaxResults(1)
										.uniqueResult();
		session.getTransaction().commit();
		return cm.getId();
	}
}
