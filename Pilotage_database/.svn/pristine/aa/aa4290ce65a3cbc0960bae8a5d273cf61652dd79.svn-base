package pilotage.database.machine;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import pilotage.metier.Machines_Applicatifs;
import pilotage.metier.Machines_Liste;
import pilotage.session.PilotageSession;

public class MachineApplicatifDatabaseService {
	/**
	 * SELECT de tous les machines_applicatifs attachés à la machine 
	 * @param selectMachine
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Machines_Applicatifs> getApplicatifsFromMachine(Integer selectMachine) {
		Session session = PilotageSession.getCurrentSession();
		
		Machines_Liste machineList = (Machines_Liste)session.load(Machines_Liste.class, selectMachine);
		List<Machines_Applicatifs> machinesAppli = session.createCriteria(Machines_Applicatifs.class)
														.add(Restrictions.eq("machine", machineList))
														.createAlias("applicatif", "appli", Criteria.LEFT_JOIN)
														.add(Restrictions.eq("appli.actif", Boolean.TRUE))
														.addOrder(Order.asc("appli.applicatif"))
														.list(); 
		
		session.getTransaction().commit();
		return machinesAppli;
	}
}
