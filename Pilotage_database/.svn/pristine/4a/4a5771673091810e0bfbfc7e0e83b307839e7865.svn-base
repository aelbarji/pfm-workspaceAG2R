package pilotage.database.admin;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import pilotage.admin.metier.Droits_Liste;
import pilotage.admin.metier.Profil;
import pilotage.admin.metier.Profil_Droits;
import pilotage.admin.session.ParametreSession;

public class ProfilDroitsDatabaseService {

	public static void create(Profil profil, String[] droitSelected) throws Exception {
		List<Droits_Liste> listDroit = new ArrayList<Droits_Liste>();
		for (int i=0; i<droitSelected.length; i++) {
			Droits_Liste dl = DroitsListeDatabaseService.get(droitSelected[i]);
			listDroit.add(dl);	
		}
		Session session = ParametreSession.getCurrentSession();
		try{
			for (Droits_Liste dl : listDroit) {
				Profil_Droits pd = new Profil_Droits();
				pd.setId_profil(profil);
				pd.setId_droits(dl);
				session.save(pd);
			}
			session.getTransaction().commit();
		}
		catch (Exception e) {
			ParametreSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void delete(int selectRow) throws Exception {
		Session session = ParametreSession.getCurrentSession();
		try{
			Profil p = (Profil)session.load(Profil.class, selectRow);
			List<Profil_Droits> listPD = session.createCriteria(Profil_Droits.class)
											.add(Restrictions.eq("id_profil", p))
											.list();
			for (Profil_Droits pd : listPD) {
				session.delete(pd);
			}
			session.getTransaction().commit();
		}
		catch (Exception e) {
			ParametreSession.rollbackTransaction(session);
			throw e;
		}	
	}

	public static void modify(Integer selectRow,  List<String> droitToAdd, List<String> droitToDelete) throws Exception {
		Profil profil = ProfilDatabaseService.get(selectRow);	
		List<Droits_Liste> listDroitAdd = new ArrayList<Droits_Liste>();
		List<Droits_Liste> listDroitDelete = new ArrayList<Droits_Liste>();
		for (String droitA : droitToAdd) {
			Droits_Liste dl = DroitsListeDatabaseService.get(droitA);
			listDroitAdd.add(dl);	
		}
		for (String droitD : droitToDelete) {
			Droits_Liste dl = DroitsListeDatabaseService.get(droitD);
			listDroitDelete.add(dl);	
		}
		Session session = ParametreSession.getCurrentSession();
		try{
			for (Droits_Liste dl : listDroitAdd) {
				Profil_Droits pd = new Profil_Droits();
				pd.setId_profil(profil);
				pd.setId_droits(dl);
				session.save(pd);
			}
			for (Droits_Liste dl : listDroitDelete) {
				Profil_Droits pd = (Profil_Droits)session.createCriteria(Profil_Droits.class)
									.add(Restrictions.eq("id_profil", profil))
									.add(Restrictions.eq("id_droits", dl))
									.setMaxResults(1)
									.uniqueResult();
				session.delete(pd);
			}
			session.getTransaction().commit();
		}
		catch (Exception e) {
			ParametreSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	@SuppressWarnings("unchecked")
	public static List<Profil_Droits> getAll(){
		Session session = ParametreSession.getCurrentSession();
		List<Profil_Droits> profilDroitList = session.createCriteria(Profil_Droits.class).list();
		session.getTransaction().commit();
		return profilDroitList;
	}
}
