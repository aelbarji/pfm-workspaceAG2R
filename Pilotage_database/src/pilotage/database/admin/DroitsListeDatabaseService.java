package pilotage.database.admin;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import pilotage.admin.metier.Droits_Liste;
import pilotage.admin.metier.Profil;
import pilotage.admin.metier.Profil_Droits;
import pilotage.admin.session.ParametreSession;

public class DroitsListeDatabaseService {
	
	public static Droits_Liste get(String droit) {
		Session session = ParametreSession.getCurrentSession();
		Droits_Liste dl = (Droits_Liste)session.createCriteria(Droits_Liste.class)
								.add(Restrictions.eq("libelle", droit))
								.setMaxResults(1)
								.uniqueResult();
		session.getTransaction().commit();
		return dl;
	}
	
	public static Droits_Liste getById(Integer selectrow) {
		Session session = ParametreSession.getCurrentSession();
		Droits_Liste dl = (Droits_Liste)session.createCriteria(Droits_Liste.class)
								.add(Restrictions.eq("id", selectrow))
								.setMaxResults(1)
								.uniqueResult();
		session.getTransaction().commit();
		return dl;
	}
	
	@SuppressWarnings("unchecked")
	public static List<Droits_Liste> getAll() {
		Session session = ParametreSession.getCurrentSession();
		List<Droits_Liste> listDroits = session.createCriteria(Droits_Liste.class).list();
		session.getTransaction().commit();
		return listDroits;
	}
	
	@SuppressWarnings("unchecked")
	public static List<Droits_Liste> getDroitsByProfil(Profil profil) {		
		Session session = ParametreSession.getCurrentSession();
		List<Profil_Droits> listProfilDroit = session.createCriteria(Profil_Droits.class)
												.add(Restrictions.eq("id_profil", profil))
												.list();
		List<Droits_Liste> listDroits = new ArrayList<Droits_Liste>();
		for (Profil_Droits pd : listProfilDroit) {
			listDroits.add(pd.getId_droits());
		}
		session.getTransaction().commit();
		return listDroits;
	}
	
	public static Droits_Liste create(String libelle, String description) throws Exception {
		Session session = ParametreSession.getCurrentSession();
		try{
			Droits_Liste droitsListe = new Droits_Liste();
			droitsListe.setLibelle(libelle);
			droitsListe.setDescription(description);
		
			session.save(droitsListe);
			session.getTransaction().commit();
			return droitsListe;
		}
		catch (Exception e) {
			ParametreSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	public static void modify(Integer id, String libelle, String description) throws Exception {
		Session session = ParametreSession.getCurrentSession();
		try{
			Droits_Liste droitsListe = (Droits_Liste)session.load(Droits_Liste.class, id);
			droitsListe.setLibelle(libelle);
			droitsListe.setDescription(description);
	
			session.update(droitsListe);
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
			Droits_Liste droitsListe = (Droits_Liste)session.load(Droits_Liste.class, selectRow);
			session.delete(droitsListe);
			session.getTransaction().commit();
		}catch (Exception e) {
			ParametreSession.rollbackTransaction(session);
			throw e;
		}
	}
}
