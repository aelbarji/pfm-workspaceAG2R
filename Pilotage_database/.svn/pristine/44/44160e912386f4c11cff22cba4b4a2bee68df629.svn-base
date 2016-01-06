package pilotage.database.incidents;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import pilotage.metier.Incidents_Qualite_Statut;
import pilotage.session.PilotageSession;

public class IncidentsQualiteStatutDatabaseService {

	
	/**
	 * SELECT de tous les statuts
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Incidents_Qualite_Statut> getAll(){
		Session session = PilotageSession.getCurrentSession();
		List<Incidents_Qualite_Statut> IncidentsQualiteStatutList = session.createCriteria(Incidents_Qualite_Statut.class).addOrder(Order.asc("statut")).list();
		session.getTransaction().commit();
		return IncidentsQualiteStatutList;
	}
	
	/**
	 * SELECT d'un statut
	 * @param selectRow
	 * @return
	 */
	public static Incidents_Qualite_Statut get(Integer selectRow) {
		Session session = PilotageSession.getCurrentSession();
		Incidents_Qualite_Statut incidentsQualiteStatut = (Incidents_Qualite_Statut)session.createCriteria(Incidents_Qualite_Statut.class)
							.add(Restrictions.eq("id", selectRow))
							.setMaxResults(1)
							.uniqueResult();
		session.getTransaction().commit();
		return incidentsQualiteStatut;
	}
	
}
