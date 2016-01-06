package pilotage.database.bilan;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import pilotage.metier.Incident_Colonnes;
import pilotage.session.PilotageSession;

public class IncidentColonnesDatabaseService {
	
	@SuppressWarnings("unchecked")
	public static List<Incident_Colonnes> getAll() {
		Session session = PilotageSession.getCurrentSession();
		List<Incident_Colonnes> icList = session.createCriteria(Incident_Colonnes.class).list();
				
		session.getTransaction().commit();
		return icList;
	}
	
	public static Incident_Colonnes get(Integer selectRow){
		Session session = PilotageSession.getCurrentSession();
		Incident_Colonnes ic = (Incident_Colonnes)session.createCriteria(Incident_Colonnes.class)
								.add(Restrictions.eq("id", selectRow))
								.setMaxResults(1)
								.uniqueResult();
		session.getTransaction().commit();
		return ic;
	}

}
