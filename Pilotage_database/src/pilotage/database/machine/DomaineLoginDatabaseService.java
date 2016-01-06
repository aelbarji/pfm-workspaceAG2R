package pilotage.database.machine;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import pilotage.metier.Domaine_Wind;
import pilotage.metier.Domaine_Wind_Login;
import pilotage.session.PilotageSession;

public class DomaineLoginDatabaseService {	
	/**
	 * SELECT de tous les logins attachés au domaine
	 * @param selectDomaine
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Domaine_Wind_Login> getLoginsFromDomaine(Integer selectDomaine) {
		Session session = PilotageSession.getCurrentSession();
		Domaine_Wind domaine = (Domaine_Wind)session.load(Domaine_Wind.class,selectDomaine);
		List<Domaine_Wind_Login> listLogin = session.createCriteria(Domaine_Wind_Login.class)
											.add(Restrictions.eq("domaine", domaine))
											.addOrder(Order.asc("login"))
											.list(); 
		session.getTransaction().commit();
		return listLogin;
	}
}

