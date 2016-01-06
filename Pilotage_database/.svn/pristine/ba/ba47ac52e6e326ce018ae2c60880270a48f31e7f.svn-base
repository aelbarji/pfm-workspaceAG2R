package pilotage.database.machine;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import pilotage.metier.Domaine_Wind;
import pilotage.metier.Domaine_Wind_Login;
import pilotage.session.PilotageSession;

public class MachineDomaineDatabaseService {	
	/**
	 * INSERT d'un nouveau domaine
	 * @param libelle
	 * @param listLoginDomaine
	 * @throws Exception 
	 */
	public static void create(String libelle, List<Map<String, String>> listLoginDomaine) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			Domaine_Wind dw = new Domaine_Wind();
			dw.setNomDomaine(libelle);
			
			List<Domaine_Wind_Login> listLogin = new ArrayList<Domaine_Wind_Login>();
			for(Map<String, String> ligneLogin : listLoginDomaine){	
				Domaine_Wind_Login dwl = new Domaine_Wind_Login();
				dwl.setLogin(ligneLogin.get("login"));
				dwl.setPassword(ligneLogin.get("password"));
				dwl.setDomaine(dw);
				
				listLogin.add(dwl);
			}
			
			session.save(dw);
			
			for(Domaine_Wind_Login login : listLogin){
				session.save(login);
			}
			
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	/**
	 * SELECT de la liste des domaines
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Domaine_Wind> getAll(){
		Session session = PilotageSession.getCurrentSession();
		List<Domaine_Wind> machineDomaineList = session.createCriteria(Domaine_Wind.class).addOrder(Order.asc("nomDomaine")).list();
		session.getTransaction().commit();
		return machineDomaineList;
	}

	/**
	 * DELETE d'un domaine à l'aide de son id
	 * @param selectRow
	 * @throws Exception 
	 */
	public static void delete(Integer selectRow) throws Exception {

		List<Domaine_Wind_Login> listLogin = DomaineLoginDatabaseService.getLoginsFromDomaine(selectRow);

		Session session = PilotageSession.getCurrentSession();
		try{
			Domaine_Wind dw = (Domaine_Wind)session.load(Domaine_Wind.class, selectRow);
			
			for (Domaine_Wind_Login dwl : listLogin) {
				session.delete(dwl);
			}
			session.delete(dw);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}	
	}
	
	/**
	 * COUNT test si le domaine existe déjà
	 * @param id - id à ne pas controler
	 * @param libelle
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static boolean exists(Integer id, String libelle){
		Session session = PilotageSession.getCurrentSession();
		
		Criteria criteria = session.createCriteria(Domaine_Wind.class);
		criteria.add(Restrictions.eq("nomDomaine", libelle));
		if(id != null)
			criteria.add(Restrictions.not(Restrictions.eq("id", id)));
		criteria.setProjection(Projections.rowCount());

		List<Long> results = criteria.list();
		session.getTransaction().commit();
		
		if (results != null && results.size() > 0 && results.get(0) > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * SELECT du domaine avec son id
	 * @param selectRow
	 * @return
	 */
	public static Domaine_Wind get(Integer selectRow) {
		Session session = PilotageSession.getCurrentSession();
		Domaine_Wind machineOS = (Domaine_Wind)session.createCriteria(Domaine_Wind.class)
										.add(Restrictions.eq("id", selectRow))
										.setMaxResults(1)
										.uniqueResult();
		session.getTransaction().commit();
		return machineOS;
	}

	/**
	 * UPDATE d'un domaine
	 * @param selectRow
	 * @param libelle
	 * @param loginToAdd
	 * @param loginToUpdate
	 * @param loginToDelete
	 * @throws Exception 
	 */
	public static void modify(Integer selectRow, String libelle, List<Map<String, String>> loginToAdd, List<Map<String, String>> loginToUpdate, List<Integer> loginToDelete) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			Domaine_Wind dw = (Domaine_Wind)session.load(Domaine_Wind.class, selectRow);
			dw.setNomDomaine(libelle);
			
			List<Domaine_Wind_Login> listLoginToAdd = new ArrayList<Domaine_Wind_Login>();
			List<Domaine_Wind_Login> listLoginToUpdate = new ArrayList<Domaine_Wind_Login>();
			List<Domaine_Wind_Login> listLoginToDelete = new ArrayList<Domaine_Wind_Login>();
			for(Map<String, String> loginMap : loginToAdd){
				Domaine_Wind_Login dwl = new Domaine_Wind_Login();
				dwl.setLogin(loginMap.get("login"));
				dwl.setPassword(loginMap.get("password"));
				dwl.setDomaine(dw);
				
				listLoginToAdd.add(dwl);
			}
			for(Map<String, String> loginMap : loginToUpdate){
				Domaine_Wind_Login dwl = (Domaine_Wind_Login)session.load(Domaine_Wind_Login.class, Integer.parseInt(loginMap.get("id")));
				dwl.setLogin(loginMap.get("login"));
				dwl.setPassword(loginMap.get("password"));
				dwl.setDomaine(dw);
				
				listLoginToUpdate.add(dwl);
			}
			for(Integer loginID : loginToDelete){
				Domaine_Wind_Login dwl = (Domaine_Wind_Login)session.load(Domaine_Wind_Login.class, loginID);
				
				listLoginToDelete.add(dwl);
			}
			
			for(Domaine_Wind_Login dwl : listLoginToAdd) {
				session.save(dwl);
			}
			for(Domaine_Wind_Login dwl : listLoginToUpdate) {
				session.update(dwl);
			}
			for(Domaine_Wind_Login dwl : listLoginToDelete) {
				session.delete(dwl);
			}
			session.update(dw);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	public static Integer getId(String libelle){
		Session session = PilotageSession.getCurrentSession();
		Domaine_Wind domaine = (Domaine_Wind)session.createCriteria(Domaine_Wind.class)
									.add(Restrictions.eq("nomDomaine", libelle))
									.setMaxResults(1)
									.uniqueResult();
		session.getTransaction().commit();
		return domaine.getId();
	}
}

