package pilotage.database.astreintes;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import pilotage.metier.Astreinte_Appel_Statut;
import pilotage.session.PilotageSession;

public class AstreinteAppelStatutDatabaseService {
	
	/**
	 * SELECT de la liste des statuts des appels Astreinte
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Astreinte_Appel_Statut> getAll(){
		Session session = PilotageSession.getCurrentSession();
		List<Astreinte_Appel_Statut> statuts = session.createCriteria(Astreinte_Appel_Statut.class)
											.addOrder(Order.asc("statut"))
											.list();
		session.getTransaction().commit();
		return statuts;
	}
	
	/**
	 * SELECT du statut par son id
	 * @param id
	 * @return
	 */
	public static Astreinte_Appel_Statut get(Integer id) {
		Session session = PilotageSession.getCurrentSession();
		Astreinte_Appel_Statut statut = (Astreinte_Appel_Statut)session.createCriteria(Astreinte_Appel_Statut.class)
							.add(Restrictions.eq("id", id))
							.setMaxResults(1)
							.uniqueResult();
		session.getTransaction().commit();
		return statut;
	}
}
