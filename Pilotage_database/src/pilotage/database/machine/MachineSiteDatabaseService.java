package pilotage.database.machine;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import pilotage.metier.Machines_Site;
import pilotage.session.PilotageSession;

public class MachineSiteDatabaseService {	
	/**
	 * INSERT d'un nouveau site
	 * @param libelle
	 * @param adresse1
	 * @param adresse2
	 * @param cp
	 * @throws Exception 
	 */
	public static void create(String libelle, String adresse1, String adresse2, Integer cp) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			Machines_Site ms = new Machines_Site();
			ms.setSite(libelle);
			ms.setAdresse1(adresse1);
			ms.setAdresse2(adresse2);
			ms.setCp(cp);
			
			session.save(ms);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	/**
	 * SELECT de la liste des sites
	 * @param sort
	 * @param sens
	 * @param filtreSite
	 * @param filtreAdresse
	 * @param filtreCP
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Machines_Site> getAll(String sort, String sens, String filtreSite, String filtreAdresse, Integer filtreCP){
		Session session = PilotageSession.getCurrentSession();
		
		Criteria criteria = session.createCriteria(Machines_Site.class);
		if(filtreSite != null && !"".equals(filtreSite))
			criteria.add(Restrictions.like("site", "%" + filtreSite + "%"));
		if(filtreAdresse != null && !"".equals(filtreAdresse))
			criteria.add(Restrictions.or(
						Restrictions.like("adresse1", "%" + filtreAdresse + "%"),
						Restrictions.like("adresse2", "%" + filtreAdresse + "%")));
		if(filtreCP != null && !"".equals(filtreCP))
			criteria.add(Restrictions.eq("cp", filtreCP));
		
		if(sort != null && !"".equals(sort))
			criteria.addOrder("desc".equals(sens) ? Order.desc(sort) : Order.asc(sort));
		
		List<Machines_Site> machineSiteList = criteria.list();
		session.getTransaction().commit();
		return machineSiteList;
	}

	/**
	 * DELETE d'un site à l'aide de son id
	 * @param selectRow
	 * @throws Exception 
	 */
	public static void delete(Integer selectRow) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			Machines_Site ms = (Machines_Site)session.load(Machines_Site.class, selectRow);
			session.delete(ms);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}	
	}

	/**
	 * COUNT test si le site existe déjà
	 * @param id - id à ne pas controler
	 * @param libelle
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static boolean exists(Integer id, String libelle){

		Session session = PilotageSession.getCurrentSession();
		
		Criteria criteria = session.createCriteria(Machines_Site.class);
		criteria.add(Restrictions.eq("site", libelle));
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
	 * SELECT du site avec son id
	 * @param selectRow
	 * @return
	 */
	public static Machines_Site get(Integer selectRow) {
		Session session = PilotageSession.getCurrentSession();
		Machines_Site machineSite = (Machines_Site)session.createCriteria(Machines_Site.class)
											.add(Restrictions.eq("id", selectRow))
											.setMaxResults(1)
											.uniqueResult();
		session.getTransaction().commit();
		return machineSite;
	}

	/**
	 * UPDATE d'un site
	 * @param selectRow
	 * @param libelle
	 * @param adresse1
	 * @param adresse2
	 * @param cp
	 * @throws Exception 
	 */
	public static void modify(Integer selectRow, String libelle, String adresse1, String adresse2, Integer cp) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			Machines_Site ms = (Machines_Site)session.load(Machines_Site.class, selectRow);
			ms.setSite(libelle);
			ms.setAdresse1(adresse1);
			ms.setAdresse2(adresse2);
			ms.setCp(cp);
			
			session.update(ms);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	public static Integer getId(String libelle){
		Session session = PilotageSession.getCurrentSession();
		Machines_Site ms = (Machines_Site)session.createCriteria(Machines_Site.class)
									.add(Restrictions.eq("site", libelle))
									.setMaxResults(1)
									.uniqueResult();
		session.getTransaction().commit();
		return ms.getId();
	}
}