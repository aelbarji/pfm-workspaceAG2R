package pilotage.database.checklist;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import pilotage.metier.Checklist_Base;
import pilotage.metier.Checklist_Ferie;
import pilotage.metier.Jour_Ferie;
import pilotage.session.PilotageSession;


public class ChecklistFerieDatabaseService {
	
	/**
	 * SELECT de la liste suivant la base
	 * @param baseID
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Checklist_Ferie> getListFromBase(Integer baseID){
		Session session = PilotageSession.getCurrentSession();
		
		Checklist_Base base = (Checklist_Base)session.load(Checklist_Base.class, baseID);
		List<Checklist_Ferie> feries = session.createCriteria(Checklist_Ferie.class)
															.add(Restrictions.eq("idChecklist", base))
															.list();
		session.getTransaction().commit();
		return feries;
	}

	/**
	 * COUNT test si la base en paramètre est une tache Fériée
	 * @param baseID
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Boolean isFrequenceFerie(Integer baseID){
		
		Session session = PilotageSession.getCurrentSession();
		
		List<Long> results = null;
		Checklist_Base base = (Checklist_Base)session.load(Checklist_Base.class, baseID);		
		results =  session.createCriteria(Checklist_Ferie.class)
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
	 * SELECT de la liste suivant la base et l'indicateur de veille/jour/lendemain
	 * @param baseID
	 * @param lendeveille
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Checklist_Ferie> getListFromBase(Integer baseID, Integer lendeveille) {
		Session session = PilotageSession.getCurrentSession();
		
		Checklist_Base base = (Checklist_Base)session.load(Checklist_Base.class, baseID);
		List<Checklist_Ferie> feries = session.createCriteria(Checklist_Ferie.class)
												.add(Restrictions.eq("idChecklist", base))
												.add(Restrictions.eq("lendeveille", lendeveille))
												.list();
		session.getTransaction().commit();
		return feries;
	}

	/**
	 * SELECT des checklist_ferie correspondant à l'id du jour férié et au lendeveille
	 * @param ferieID
	 * @param lendeveille
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Checklist_Ferie> getFromDate(Integer ferieID, Integer lendeveille) {
		if(ferieID == null)
			return new ArrayList<Checklist_Ferie>();
			
		Session session = PilotageSession.getCurrentSession();
		Jour_Ferie jourFerie = (Jour_Ferie)session.load(Jour_Ferie.class, ferieID);
		
		List<Checklist_Ferie> listChecklistFerie = session.createCriteria(Checklist_Ferie.class)
														.add(Restrictions.eq("idJourFerie", jourFerie))
														.add(Restrictions.eq("lendeveille", lendeveille))
														.list();
		session.getTransaction().commit();
		return listChecklistFerie;
	}
	
	public static Integer getId(Checklist_Base checklist, Integer jour_ferie, Integer lendeveille) {
		Session session = PilotageSession.getCurrentSession();
		
		Jour_Ferie jour = (Jour_Ferie) session.load(Jour_Ferie.class, jour_ferie);
		Checklist_Ferie cf = (Checklist_Ferie)session.createCriteria(Checklist_Ferie.class)
										.add(Restrictions.eq("idChecklist",checklist))
										.add(Restrictions.eq("lendeveille",lendeveille))
										.add(Restrictions.eq("idJourFerie",jour))
										.setMaxResults(1)
										.uniqueResult();
		session.getTransaction().commit();
		return cf.getId();
	}
}
