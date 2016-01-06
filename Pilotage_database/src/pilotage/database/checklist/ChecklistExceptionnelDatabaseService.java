package pilotage.database.checklist;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import pilotage.metier.Checklist_Base;
import pilotage.metier.Checklist_Exceptionnel;
import pilotage.session.PilotageSession;


public class ChecklistExceptionnelDatabaseService {
	
	/**
	 * SELECT de la liste suivant la base
	 * @param baseID
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Checklist_Exceptionnel> getListFromBase(Integer baseID){
		Session session = PilotageSession.getCurrentSession();
		
		Checklist_Base base = (Checklist_Base)session.load(Checklist_Base.class, baseID);
		List<Checklist_Exceptionnel> exceptionnels = session.createCriteria(Checklist_Exceptionnel.class)
															.add(Restrictions.eq("idChecklist", base))
															.addOrder(Order.asc("jour"))
															.list();
		session.getTransaction().commit();
		return exceptionnels;
	}
	
	/**
	 * COUNT test si la base en paramètre est une tache exceptionnelle
	 * @param baseID
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Boolean isFrequenceExceptionnelle(Integer baseID){
		
		Session session = PilotageSession.getCurrentSession();
		
		List<Long> results = null;
		Checklist_Base base = (Checklist_Base)session.load(Checklist_Base.class, baseID);		
		results =  session.createCriteria(Checklist_Exceptionnel.class)
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
	 * SELECT de toutes les dates exceptionnelles correspondant au jour en paramètre
	 * @param date
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Checklist_Exceptionnel> getFromDate(Date date) {
		Session session = PilotageSession.getCurrentSession();
		
		List<Checklist_Exceptionnel> listTodayExceptionnel = session.createCriteria(Checklist_Exceptionnel.class)
																	.add(Restrictions.eq("jour", date))
																	.list();
		session.getTransaction().commit();
		return listTodayExceptionnel;
	}
	
	public static Integer getId(Checklist_Base checklist, Date jour) {
		Session session = PilotageSession.getCurrentSession();
		
		Checklist_Exceptionnel ce = (Checklist_Exceptionnel)session.createCriteria(Checklist_Exceptionnel.class)
										.add(Restrictions.eq("idChecklist",checklist))
										.add(Restrictions.eq("jour",jour))
										.setMaxResults(1)
										.uniqueResult();
		session.getTransaction().commit();
		return ce.getId();
	}
}
