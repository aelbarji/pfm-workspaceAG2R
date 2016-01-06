package pilotage.database.gup;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import pilotage.metier.Com_domaine;
import pilotage.session.PilotageSession;
import pilotage.utils.Pagination;

public class DomaineDatabaseService {
	
	/**
	 * SELECT de la liste des domaines
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Com_domaine> getAll(){
		Session session = PilotageSession.getCurrentSession();
		List<Com_domaine> domaine = session.createCriteria(Com_domaine.class).list();
		session.getTransaction().commit();
		return domaine;
	}
	
	/**
	 * SELECT de la liste des domaines
	 * @return
	 */
	public static List<Com_domaine> getAllWithPagination(Pagination<Com_domaine> pagination){
		Session session = PilotageSession.getCurrentSession();
		
		Criteria criteria = session.createCriteria(Com_domaine.class);
		
		List<Com_domaine> domaine = pagination.setCriteriaPagination(criteria);
		session.getTransaction().commit();
		return domaine;
	}
	
	/**
	 * SELECT d'un domaine
	 * @return
	 */
	public static Com_domaine get(Integer selectRow){
		Session session = PilotageSession.getCurrentSession();
		Com_domaine d = (Com_domaine)session.createCriteria(Com_domaine.class)
								.add(Restrictions.eq("id", selectRow))
								.setMaxResults(1)
								.uniqueResult();
		session.getTransaction().commit();
		return d;
	}
	
	/**
	 * INSERT d'un nouveau domaine
	 * @param nom
	*/
	
	public static void create(String nom) throws Exception{
		Session session = PilotageSession.getCurrentSession();
		
		try{
			Com_domaine cd = new Com_domaine();
			cd.setNom(nom);
			
			session.save(cd);
			session.getTransaction().commit();
		}catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	/**
	 * DELETE d'un domaine
	 * @param selectedID
	 * @throws Exception 
	 */
	public static void delete(Integer selectedID) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			Com_domaine cd = (Com_domaine) session.load(Com_domaine.class, selectedID);
			
			session.delete(cd);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	/**
	 * UPDATE d'un domaine
	 * @param id
	 * @param nom
	 */
	public static void modify(Integer id, String nom) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			Com_domaine cd = (Com_domaine)session.load(Com_domaine.class, id);
			cd.setNom(nom);
			
			session.update(cd);
			session.getTransaction().commit();
			
		}catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}

}
