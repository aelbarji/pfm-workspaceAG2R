package pilotage.database.admin;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import pilotage.admin.metier.Droits_Liste;
import pilotage.admin.metier.Module;
import pilotage.admin.metier.Sous_Module;
import pilotage.admin.session.ParametreSession;

public class SousModuleDatabaseService {
	
	@SuppressWarnings("unchecked")
	public static List<Sous_Module> getAll() {
		Session session = ParametreSession.getCurrentSession();
		List<Sous_Module> sousModuleList = (List<Sous_Module>)session.createCriteria(Sous_Module.class)
				.addOrder(Order.asc("id"))
				.list();
				
		session.getTransaction().commit();
		return sousModuleList;
	}
	
	public static Sous_Module get(int selectRow){
		Session session = ParametreSession.getCurrentSession();
		Sous_Module sousModule = (Sous_Module)session.createCriteria(Sous_Module.class)
							.add(Restrictions.eq("id", selectRow))
							.setMaxResults(1)
							.uniqueResult();
		session.getTransaction().commit();
		return sousModule;
	}
	
	@SuppressWarnings("unchecked")
	public static List<Sous_Module> getAllFromModule(Integer idModule){
		Session session = ParametreSession.getCurrentSession();
		Module m = (Module)session.load(Module.class, idModule);
		List<Sous_Module> sousModuleList = (List<Sous_Module>) session.createCriteria(Sous_Module.class).add(Restrictions.eq("idParent", m)).list();
		session.getTransaction().commit();
		return sousModuleList;
	}
	
	public static void create(String nom, Module idParent, Droits_Liste idAjout , Droits_Liste idModif, Droits_Liste idSuppr, Droits_Liste idDetail) throws Exception {
		Session session = ParametreSession.getCurrentSession();
		try{
			Sous_Module sousModule = new Sous_Module();
			sousModule.setNom(nom);
			sousModule.setIdAjout(idAjout);
			sousModule.setIdModif(idModif);
			sousModule.setIdSuppr(idSuppr);
			sousModule.setIdDetail(idDetail);
			sousModule.setIdParent(idParent);
		
			session.save(sousModule);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			ParametreSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	public static void modify(Integer id, String nom, Droits_Liste idAj, Droits_Liste idMod, Droits_Liste idSup, Droits_Liste idDetail) throws Exception {
		Session session = ParametreSession.getCurrentSession();
		try{
			Sous_Module sousModule = (Sous_Module)session.load(Sous_Module.class, id);
			sousModule.setNom(nom);
			sousModule.setIdAjout(idAj);
			sousModule.setIdModif(idMod);
			sousModule.setIdSuppr(idSup);
			sousModule.setIdDetail(idDetail);
	
			session.update(sousModule);
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
			Sous_Module sousModule = (Sous_Module)session.load(Sous_Module.class, selectRow);
			session.delete(sousModule);
			session.getTransaction().commit();
		}catch (Exception e) {
			ParametreSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	public static void deleteDroitAjout(int idDroit) throws Exception{
		Session session = ParametreSession.getCurrentSession();
		try{
			Sous_Module sousModule = (Sous_Module)session.load(Sous_Module.class, idDroit);
			sousModule.setIdAjout(null);
			session.update(sousModule);
			session.getTransaction().commit();
		}catch (Exception e) {
			ParametreSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	public static void deleteDroitModif(int idDroit) throws Exception{
		Session session = ParametreSession.getCurrentSession();
		try{
			Sous_Module sousModule = (Sous_Module)session.load(Sous_Module.class, idDroit);
			sousModule.setIdModif(null);
			session.update(sousModule);
			session.getTransaction().commit();
		}catch (Exception e) {
			ParametreSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	public static void deleteDroitSuppr(int idDroit) throws Exception{
		Session session = ParametreSession.getCurrentSession();
		try{
			Sous_Module sousModule = (Sous_Module)session.load(Sous_Module.class, idDroit);
			sousModule.setIdSuppr(null);
			session.update(sousModule);
			session.getTransaction().commit();
		}catch (Exception e) {
			ParametreSession.rollbackTransaction(session);
			throw e;
		}
	}

	public static void deleteDroitDetail(int idDroit) throws Exception{
		Session session = ParametreSession.getCurrentSession();
		try{
			Sous_Module sousModule = (Sous_Module)session.load(Sous_Module.class, idDroit);
			sousModule.setIdDetail(null);
			session.update(sousModule);
			session.getTransaction().commit();
		}catch (Exception e) {
			ParametreSession.rollbackTransaction(session);
			throw e;
		}
	}

}
