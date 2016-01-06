package pilotage.database.planning;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import pilotage.metier.Planning_Cra_Partie_Jour;
import pilotage.session.PilotageSession;

public class PlanningPatieJourDatabaseService {
	
	@SuppressWarnings("unchecked")
	public static List<Planning_Cra_Partie_Jour> getAll(){
		Session session = PilotageSession.getCurrentSession();
		List<Planning_Cra_Partie_Jour> pcpj = session.createCriteria(Planning_Cra_Partie_Jour.class).list();
		session.getTransaction().commit();
		return pcpj;
	}
	
	
	public static Planning_Cra_Partie_Jour get(Integer selectRow){
		Session session = PilotageSession.getCurrentSession();
		Planning_Cra_Partie_Jour s = (Planning_Cra_Partie_Jour)session.createCriteria(Planning_Cra_Partie_Jour.class)
								.add(Restrictions.eq("id", selectRow))
								.setMaxResults(1)
								.uniqueResult();
		session.getTransaction().commit();
		return s;
	}


}
