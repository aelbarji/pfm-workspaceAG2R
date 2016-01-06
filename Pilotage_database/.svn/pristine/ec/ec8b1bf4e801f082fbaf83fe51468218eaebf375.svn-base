package pilotage.database.admin;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import pilotage.admin.metier.Module;
import pilotage.admin.session.ParametreSession;

public class ModuleDatabaseService {
	
	@SuppressWarnings("unchecked")
	public static List<Module> getAll() {
		Session session = ParametreSession.getCurrentSession();
		List<Module> moduleList = session.createCriteria(Module.class).addOrder(Order.asc("nom")).list();
				
		session.getTransaction().commit();
		return moduleList;
	}
	
	public static Module get(Integer selectRow){
			Session session = ParametreSession.getCurrentSession();
			Module module = (Module)session.createCriteria(Module.class)
								.add(Restrictions.eq("id", selectRow))
								.setMaxResults(1)
								.uniqueResult();
			session.getTransaction().commit();
			return module;
		}
	
	public static void create(String nom) throws Exception {
		Session session = ParametreSession.getCurrentSession();
		try{
			Module module = new Module();
			module.setNom(nom);
		
			session.save(module);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			ParametreSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	public static void modify(Integer id, String nom) throws Exception {
		Session session = ParametreSession.getCurrentSession();
		try{
			Module module = (Module)session.load(Module.class, id);
			module.setNom(nom);
	
			session.update(module);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			ParametreSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	public static void delete(int selectRow) throws Exception{
		Session session = ParametreSession.getCurrentSession();
		try{
			Module module = (Module)session.load(Module.class, selectRow);
			session.delete(module);
			session.getTransaction().commit();
		}catch (Exception e) {
			ParametreSession.rollbackTransaction(session);
			throw e;
		}
	}
}
