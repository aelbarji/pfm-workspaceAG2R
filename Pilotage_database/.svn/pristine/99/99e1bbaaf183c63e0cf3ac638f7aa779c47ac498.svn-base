package pilotage.database.applicatif;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import pilotage.metier.Hardware_Software;
import pilotage.session.PilotageSession;

public class HardwareSoftwareDatabaseService {
	
	/**
	 * SELECT d'un hardware_software
	 * @param parseInt
	 * @return
	 */
	public static Hardware_Software get(Integer id) {
		Session session = PilotageSession.getCurrentSession();
		
		Hardware_Software hs = (Hardware_Software)session.createCriteria(Hardware_Software.class)
									.add(Restrictions.eq("id", id))
									.setMaxResults(1)
									.uniqueResult();
		session.getTransaction().commit();
		
		return hs;
	}
	
	/**
	 * SELECT de tous les hardware_software, ordonnés par libellé
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Hardware_Software> getAll() {
		Session session = PilotageSession.getCurrentSession();
		List<Hardware_Software> hs = (List<Hardware_Software>)session.createCriteria(Hardware_Software.class)
													.addOrder(Order.asc("libelle"))
													.list();
		session.getTransaction().commit();
		return hs;
	}
	
	/**
	 * SELECT de la liste des applicatifs ayant l'id dans le tableau passé en paramètre
	 * @param appIDList
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Hardware_Software> getMultiple(List<Integer> hardIDList) {
		List<Hardware_Software> hards;
		if(hardIDList != null && !hardIDList.isEmpty()){
			Session session = PilotageSession.getCurrentSession();
			hards = (List<Hardware_Software>)session.createCriteria(Hardware_Software.class)
												.add(Restrictions.in("id", hardIDList))
												.list();
			session.getTransaction().commit();
		}
		else{
			hards = new ArrayList<Hardware_Software>();
		}
		return hards;
	}

}
