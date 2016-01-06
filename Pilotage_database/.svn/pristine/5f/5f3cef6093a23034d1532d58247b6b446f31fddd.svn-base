package pilotage.database.machine;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import pilotage.metier.Machines_Ip;
import pilotage.metier.Machines_Liste;
import pilotage.session.PilotageSession;

public class MachineIpDatabaseService {


	/**
	 * SELECT de tous les machines_ip attachés à la machine
	 * @param selectMachine
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Machines_Ip> getIPFromMachine(Integer selectMachine) {
		Session session = PilotageSession.getCurrentSession();
		Machines_Liste machine = (Machines_Liste) session.load(Machines_Liste.class, selectMachine);
		List<Machines_Ip> listIps = session.createCriteria(Machines_Ip.class)
										.add(Restrictions.eq("machines_liste", machine))
										.addOrder(Order.asc("ip"))
										.list(); 
		session.getTransaction().commit();
		return listIps;
	}
	
	public static void modify(String ip,String commentaire,String id) throws Exception {
		Session session = PilotageSession.getCurrentSession();	
		try{
			Machines_Ip mi = (Machines_Ip)session.load(Machines_Ip.class, Integer.parseInt(id));
			mi.setIp(ip);
			mi.setCommentaire(commentaire);
			session.update(mi);
			session.getTransaction().commit();		
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}

	public static void create(String ip, String commentaire, String nom) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			Machines_Liste machineList = (Machines_Liste) session.createCriteria(Machines_Liste.class).add(Restrictions.eq("nom", nom)).setMaxResults(1).uniqueResult();

			Machines_Ip mi = new Machines_Ip();
			mi.setId(new Integer(0));
			mi.setIp(ip);
			mi.setCommentaire(commentaire);
			mi.setMachines_liste(machineList);
			session.save(mi);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	public static void delete(Integer selectRow) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			Machines_Ip machineIp = (Machines_Ip) session.load(Machines_Ip.class,selectRow);
			session.delete(machineIp);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}	
	}
	
	public static Integer getId(String ip){
		Session session = PilotageSession.getCurrentSession();
		Machines_Ip mi = (Machines_Ip)session.createCriteria(Machines_Ip.class)
									.add(Restrictions.eq("ip", ip))
									.setMaxResults(1)
									.uniqueResult();
		session.getTransaction().commit();
		return mi.getId();
	}
}
