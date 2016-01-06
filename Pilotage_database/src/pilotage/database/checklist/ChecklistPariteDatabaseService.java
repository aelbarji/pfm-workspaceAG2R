package pilotage.database.checklist;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import pilotage.metier.Checklist_Base;
import pilotage.metier.Checklist_Parite;
import pilotage.session.PilotageSession;


public class ChecklistPariteDatabaseService {
	
	/**
	 * SELECT de la liste des jours exceptionnelles suivant la base
	 * @param baseID
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Checklist_Parite> getListFromBase(Integer baseID){
		Session session = PilotageSession.getCurrentSession();
		
		Checklist_Base base = (Checklist_Base)session.load(Checklist_Base.class, baseID);
		List<Checklist_Parite> parites = session.createCriteria(Checklist_Parite.class)
															.add(Restrictions.eq("idChecklist", base))
															.list();
		session.getTransaction().commit();
		return parites;
	}

	/**
	 * COUNT test si la base en paramètre est une tache paire/impaire
	 * @param baseID
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static boolean isFrequenceParite(Integer baseID) {
		Session session = PilotageSession.getCurrentSession();
		
		List<Long> results = null;
		Checklist_Base base = (Checklist_Base)session.load(Checklist_Base.class, baseID);		
		results =  session.createCriteria(Checklist_Parite.class)
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
	 * SELECT des checklist_parite suivant la parité
	 * @param parite
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Checklist_Parite> getFromParite(Integer parite) {
		Session session = PilotageSession.getCurrentSession();
		
		List<Checklist_Parite> listChecklistParite = session.createCriteria(Checklist_Parite.class)
															.add(Restrictions.eq("parite", parite))
															.list();
		session.getTransaction().commit();
		return listChecklistParite;
	}
	
	public static Integer getId(Checklist_Base checklist, Integer parite) {
		Session session = PilotageSession.getCurrentSession();
		
		Checklist_Parite cp = (Checklist_Parite)session.createCriteria(Checklist_Parite.class)
										.add(Restrictions.eq("idChecklist",checklist))
										.add(Restrictions.eq("parite",parite))
										.setMaxResults(1)
										.uniqueResult();
		session.getTransaction().commit();
		return cp.getId();
	}
}
