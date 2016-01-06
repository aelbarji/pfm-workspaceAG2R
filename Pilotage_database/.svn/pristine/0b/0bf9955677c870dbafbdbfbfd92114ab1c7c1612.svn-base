package pilotage.database.machine;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import pilotage.metier.Appli_Entite;
import pilotage.metier.Applicatifs_Liste;
import pilotage.metier.Interlocuteur;
import pilotage.metier.Users;
import pilotage.session.PilotageSession;
import pilotage.utils.Pagination;

public class MachineInterlocuteurDatabaseService {	
	/**
	 * INSERT d'un nouveau interlocuteur
	 * @param nomService
	 * @param nomComplet
	 * @param idResponsable
	 * @param balService
	 * @throws Exception 
	 */
	public static void create(String nomService, String nomComplet, int idResponsable, String balService) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			Users user = (Users)session.load(Users.class, idResponsable);
			
			Interlocuteur sg = new Interlocuteur();
			sg.setNomService(nomService);
			sg.setNomComplet(nomComplet);
			sg.setIdResponsable(user);
			sg.setBalService(balService);
			
			session.save(sg);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	/**
	 * SELECT de la liste des interlocuteurs suivant les filtres
	 * @param filtreBAI 
	 * @param filtreResponsable 
	 * @param filtreNom 
	 * @param filtreNomComplet 
	 * @param sens 
	 * @param sort 
	 * @param pagination 
	 * @return
	 */
	public static List<Interlocuteur> getAll(Pagination<Interlocuteur> pagination, String sort, String sens, String filtreNom, String filtreNomComplet, Integer filtreResponsable, String filtreBAI){
		Session session = PilotageSession.getCurrentSession();
		Criteria criteria = session.createCriteria(Interlocuteur.class);
		
		if(filtreNom != null && !"".equals(filtreNom))
			criteria.add(Restrictions.like("nomService", "%" + filtreNom + "%"));
		
		if(filtreNomComplet != null && !"".equals(filtreNomComplet))
			criteria.add(Restrictions.like("nomComplet", "%" + filtreNomComplet + "%"));
		
		if(filtreResponsable != null && filtreResponsable != -1) {
			criteria.createAlias("idResponsable", "users");
			criteria.add(Restrictions.eq("users.id", filtreResponsable));
		}
		
		if(filtreBAI != null && !"".equals(filtreBAI))
			criteria.add(Restrictions.like("balService", "%" + filtreBAI + "%"));
		
		if("idResponsable".equals(sort)){
			if(filtreResponsable == null || filtreResponsable == -1) {
				criteria.createAlias("idResponsable", "users");
			}
			criteria.addOrder("desc".equalsIgnoreCase(sens) ? Order.desc("users.nom") : Order.asc("users.nom"));
		}
		else{
			criteria.addOrder("desc".equalsIgnoreCase(sens) ? Order.desc(sort) : Order.asc(sort));
		}

		List<Interlocuteur> listInterlocuteur = pagination.setCriteriaPagination(criteria);
		session.getTransaction().commit();
		return listInterlocuteur;
	}
	
	/**
	 * SELECT de tous les interlocuteurs ordonnés par nom de service
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Interlocuteur> getAll(){
		Session session = PilotageSession.getCurrentSession();
		List<Interlocuteur> interlocuteurs = session.createCriteria(Interlocuteur.class).addOrder(Order.asc("nomService")).list();
		session.getTransaction().commit();
		return interlocuteurs;
	}

	/**
	 * DELETE d'un interlocuteur à l'aide de son id
	 * @param selectRow
	 * @throws Exception 
	 */
	public static void delete(Integer selectRow) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			Interlocuteur sg = (Interlocuteur)session.load(Interlocuteur.class, selectRow);
			session.delete(sg);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}	
	}
	
	/**
	 * COUNT test si l'interlocuteur existe déjà
	 * @param id - identificateur à ne pas controler
	 * @param libelle
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static boolean exists(Integer id, String libelle){
		Session session = PilotageSession.getCurrentSession();
		
		Criteria criteria = session.createCriteria(Interlocuteur.class);
		criteria.add(Restrictions.eq("nomService", libelle));
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
	 * SELECT de l'interlocuteur avec son id
	 * @param selectRow
	 * @return
	 */
	public static Interlocuteur get(Integer selectRow) {
		Session session = PilotageSession.getCurrentSession();
		Interlocuteur serviceGerant = (Interlocuteur)session.createCriteria(Interlocuteur.class)
											.add(Restrictions.eq("id", selectRow))
											.setMaxResults(1)
											.uniqueResult();
		session.getTransaction().commit();
		return serviceGerant;
	}

	/**
	 * UPDATE d'un interlocuteur
	 * @param id
	 * @param nomService
	 * @param nomComplet
	 * @param idResponsable
	 * @param balService
	 * @throws Exception 
	 */
	public static void modify(Integer id, String nomService, String nomComplet, Integer idResponsable, String balService) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			Users user = (Users)session.load(Users.class, idResponsable);
			
			Interlocuteur sg = (Interlocuteur)session.load(Interlocuteur.class, id);
			sg.setNomService(nomService);
			sg.setNomComplet(nomComplet);
			sg.setIdResponsable(user);
			sg.setBalService(balService);
			
			session.update(sg);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	public static Integer getId(String libelle){
		Session session = PilotageSession.getCurrentSession();
		Interlocuteur interl = (Interlocuteur)session.createCriteria(Interlocuteur.class)
									.add(Restrictions.eq("nomService", libelle))
									.setMaxResults(1)
									.uniqueResult();
		session.getTransaction().commit();
		return interl.getId();
	}

	/**
	 * SELECT de tous les interlocuteurs reliés à l'app
	 * @param appId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Interlocuteur> getAllByApp(Integer appId) {
		Session session = PilotageSession.getCurrentSession();
		List<Interlocuteur> listInterloc = null;
		
		Applicatifs_Liste app = (Applicatifs_Liste)session.load(Applicatifs_Liste.class, appId);
		List<Appli_Entite> list = session.createCriteria(Appli_Entite.class)
									.add(Restrictions.eq("idApplication", app))
									.list();
		
		if(list != null && list.size() > 0){
			listInterloc = new ArrayList<Interlocuteur>();
			for(Appli_Entite appInterloc : list){
				if(!listInterloc.contains(appInterloc.getIdEntite()))
					listInterloc.add(appInterloc.getIdEntite());
			}
		}
		
		session.getTransaction().commit();
		
		return listInterloc;
	}
}

