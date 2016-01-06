package pilotage.database.machine;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import pilotage.metier.Environnement;
import pilotage.session.PilotageSession;

public class MachineEnvironnementDatabaseService {

	/**
	 * SELECT de tous les environnements
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Environnement> getAll() {
		Session session = PilotageSession.getCurrentSession();
		List<Environnement> listEnvironnement = session.createCriteria(Environnement.class).addOrder(Order.asc("environnement")).list(); 
		session.getTransaction().commit();
		return listEnvironnement;
	}
	
	public static Integer getId(String libelle){
		Session session = PilotageSession.getCurrentSession();
		Environnement env = (Environnement)session.createCriteria(Environnement.class)
									.add(Restrictions.eq("environnement", libelle))
									.setMaxResults(1)
									.uniqueResult();
		session.getTransaction().commit();
		return env.getId();
	}
}
