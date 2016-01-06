package pilotage.database.checklist;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import pilotage.metier.Checklist_Base;
import pilotage.metier.Checklist_Horaire;
import pilotage.session.PilotageSession;


public class ChecklistHoraireDatabaseService {
	
	/**
	 * SELECT de la liste suivant la base
	 * @param baseID
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Checklist_Horaire> getListFromBase(Integer baseID){
		Session session = PilotageSession.getCurrentSession();
		
		Checklist_Base base = (Checklist_Base)session.load(Checklist_Base.class, baseID);
		List<Checklist_Horaire> horaires = session.createCriteria(Checklist_Horaire.class)
															.add(Restrictions.eq("idChecklist", base))
															.add(Restrictions.eq("actif", Boolean.TRUE))
															.addOrder(Order.asc("horaireStamp"))
															.list();
		session.getTransaction().commit();
		return horaires;
	}
	
	public static Integer getId(Checklist_Base checklist, Date horaire) {
		Session session = PilotageSession.getCurrentSession();
		
		Checklist_Horaire ch = (Checklist_Horaire)session.createCriteria(Checklist_Horaire.class)
										.add(Restrictions.eq("idChecklist",checklist))
										.add(Restrictions.eq("horaire",horaire))
										.setMaxResults(1)
										.uniqueResult();
		session.getTransaction().commit();
		return ch.getId();
	}
}
