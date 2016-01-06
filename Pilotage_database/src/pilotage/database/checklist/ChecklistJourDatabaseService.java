package pilotage.database.checklist;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import pilotage.metier.Checklist_Base;
import pilotage.metier.Checklist_Jour;
import pilotage.session.PilotageSession;


public class ChecklistJourDatabaseService {
	
	/**
	 * SELECT de la liste suivant la base
	 * @param baseID
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Checklist_Jour> getListFromBase(Integer baseID){
		Session session = PilotageSession.getCurrentSession();
		
		Checklist_Base base = (Checklist_Base)session.load(Checklist_Base.class, baseID);
		List<Checklist_Jour> jours = session.createCriteria(Checklist_Jour.class)
															.add(Restrictions.eq("idChecklist", base))
															.list();
		session.getTransaction().commit();
		return jours;
	}

	/**
	 * SELECT de tous les checklist_jour suivant le jour et la liste des checklist_base
	 * @param jour
	 * @param listBase
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Checklist_Jour> getListFromDayAndBaseList(Integer jour, List<Checklist_Base> listBase) {
		if(listBase == null || listBase.isEmpty()){
			return new ArrayList<Checklist_Jour>();
		}
		
		Session session = PilotageSession.getCurrentSession();
		
		List<Checklist_Jour> listChecklistJour = session.createCriteria(Checklist_Jour.class)
														.add(Restrictions.in("idChecklist", listBase))
														.add(Restrictions.eq("jour", jour))
														.list();
		session.getTransaction().commit();
		return listChecklistJour;
	}

	/**
	 * SELECT de tous les checklist_jour ayant le jour passé en paramètre
	 * @param jour
	 * @param jourFerie
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Checklist_Jour> getListFromDay(Integer jour, Boolean jourFerie) {
		Session session = PilotageSession.getCurrentSession();
		
		Criteria criteria = session.createCriteria(Checklist_Jour.class).add(Restrictions.eq("jour", jour));
		if(Boolean.TRUE.equals(jourFerie))
			criteria.add(Restrictions.eq("ferie", Boolean.TRUE));
		
		List<Checklist_Jour> listChecklistJour = criteria.list(); 
			
		session.getTransaction().commit();
		return listChecklistJour;
	}
	
	public static Integer getId(Checklist_Base checklist, Integer jour) {
		Session session = PilotageSession.getCurrentSession();
		
		Checklist_Jour cj = (Checklist_Jour)session.createCriteria(Checklist_Jour.class)
										.add(Restrictions.eq("idChecklist",checklist))
										.add(Restrictions.eq("jour",jour))
										.setMaxResults(1)
										.uniqueResult();
		session.getTransaction().commit();
		return cj.getId();
	}
}
