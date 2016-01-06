package pilotage.database.machine;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import pilotage.metier.Domaine_Wind;
import pilotage.metier.Machine_Login;
import pilotage.metier.Machines_Liste;
import pilotage.session.PilotageSession;

public class MachineLoginDatabaseService {
	
	/**
	 * SELECT de tous les machines_login attachés à la machine
	 * @param selectMachine
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Machine_Login> getLoginsFromMachine(Integer selectMachine) {
		Session session = PilotageSession.getCurrentSession();
		Machines_Liste machine = (Machines_Liste)session.load(Machines_Liste.class,selectMachine);
		List<Machine_Login> listLogin = session.createCriteria(Machine_Login.class)
											.add(Restrictions.eq("machines_liste", machine))
											.addOrder(Order.asc("login"))
											.list(); 
		session.getTransaction().commit();
		return listLogin;
	}

	public static void create(String login, String password, String domaine, String nom) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			Machines_Liste machineList = (Machines_Liste) session.createCriteria(Machines_Liste.class).add(Restrictions.eq("nom", nom)).setMaxResults(1).uniqueResult();
			Domaine_Wind domaine_wind = null;
			if (!"".equals(domaine)){
				domaine_wind = (Domaine_Wind) session.createCriteria(Domaine_Wind.class).add(Restrictions.eq("id", Integer.parseInt(domaine))).setMaxResults(1).uniqueResult();
			} 		
			Machine_Login ml = new Machine_Login();
			ml.setId(new Integer(0));
			ml.setLogin(login);
			ml.setPassword(password);
			ml.setDomaine_wind(domaine_wind);
			ml.setMachines_liste(machineList);
			session.save(ml);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	public static void modify(String login,String password, String domaine, String id) throws Exception {
		Session session = PilotageSession.getCurrentSession();		
		try{
			Machine_Login ml = (Machine_Login)session.load(Machine_Login.class, Integer.parseInt(id));
			Domaine_Wind domaine_wind = null;
			if (!"".equals(domaine)) {
				domaine_wind = (Domaine_Wind) session.createCriteria(Domaine_Wind.class).add(Restrictions.eq("id", Integer.parseInt(domaine))).setMaxResults(1).uniqueResult();
			}
			ml.setLogin(login);
			ml.setPassword(password);
			ml.setDomaine_wind(domaine_wind);
			session.update(ml);
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
			Machine_Login machineLogin = (Machine_Login) session.load(Machine_Login.class,selectRow);
			session.delete(machineLogin);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}	
	}
	
	public static Integer getId(String login){
		Session session = PilotageSession.getCurrentSession();
		Machine_Login ml = (Machine_Login)session.createCriteria(Machine_Login.class)
									.add(Restrictions.eq("login", login))
									.setMaxResults(1)
									.uniqueResult();
		session.getTransaction().commit();
		return ml.getId();
	}
}
