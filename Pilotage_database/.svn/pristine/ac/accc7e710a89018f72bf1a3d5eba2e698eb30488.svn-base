package pilotage.database.historique;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import pilotage.metier.Historique;
import pilotage.metier.Users;
import pilotage.session.PilotageSession;
import pilotage.utils.Pagination;

public class HistoriqueDatabaseService {
	/**
	 * SELECT de toutes les historiques suivant les filtres
	 * @param pagination
	 * @param sort
	 * @param sens
	 * @param filtreAction
	 * @param filtreUtilisateur
	 * @param filtreModule
	 * @return
	 */
	public static List<Historique> getAll(Pagination<Historique> pagination, String sort, String sens, String filtreAction, Integer filtreUtilisateur, String filtreModule) {
		Session session = PilotageSession.getCurrentSession();
		Criteria criteria = session.createCriteria(Historique.class);
		
		if (filtreAction != null && !"".equals(filtreAction)) {
			criteria.add(Restrictions.like("action", "%" + filtreAction + "%"));
		}
		if (filtreUtilisateur != null && filtreUtilisateur != -1) {
			Users users = (Users)session.load(Users.class, filtreUtilisateur);
			criteria.add(Restrictions.eq("utilisateur", users));
		}
		if (filtreModule != null && !"".equals(filtreModule)) {
			criteria.add(Restrictions.like("module", "%" + filtreModule + "%"));
		}
		
		if ("utilisateur".equals(sort)){ 
			criteria.createCriteria(sort, "ut", Criteria.LEFT_JOIN);
			criteria.addOrder("desc".equals(sens) ? Order.desc("ut.nom") : Order.asc("ut.nom"));
		} else {
			criteria.addOrder("desc".equals(sens) ? Order.desc(sort) : Order.asc(sort));
		}
		
		List<Historique> listHistorique = pagination.setCriteriaPagination(criteria);
		session.getTransaction().commit();
		return listHistorique;
	}
	
	/**
	 * INSERT d'un nouvel historique
	 * @param dateAction
	 * @param action
	 * @param utilisateur
	 * @param module
	 * @throws Exception
	 */
	public static void create(Date dateAction, String action, Users utilisateur, String module) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			Historique his = new Historique();
			
			his.setDateAction(new Date());
			his.setAction(action);
			his.setUtilisateur(utilisateur);
			his.setModule(module);
			
			session.save(his);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
}