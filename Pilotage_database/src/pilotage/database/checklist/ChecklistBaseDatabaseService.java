package pilotage.database.checklist;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import pilotage.metier.Checklist_Base;
import pilotage.metier.Checklist_Criticite;
import pilotage.metier.Checklist_Etat;
import pilotage.metier.Environnement;
import pilotage.service.date.DateService;
import pilotage.session.PilotageSession;
import pilotage.utils.Pagination;

public class ChecklistBaseDatabaseService {

	/**
	 * SELECT de toutes les taches
	 * @param filtreCriticite 
	 * @param filtreEtat 
	 * @param filtreDateDebut 
	 * @param filtreEnvironnement 
	 * @param filtreNom 
	 * @param sens 
	 * @param sort 
	 * @param pagination 
	 * @return
	 */
	public static List<Checklist_Base> getAll(Pagination<Checklist_Base> pagination, String sort, String sens, String filtreNom, Integer filtreEnvironnement, String filtreDateDebut, Integer filtreEtat, Integer filtreCriticite, Integer filtreDemandes) {
		Session session = PilotageSession.getCurrentSession();
		Criteria criteria = session.createCriteria(Checklist_Base.class);
		criteria.add(Restrictions.eq("actif", Boolean.TRUE));
		
		if(filtreNom != null && !"".equals(filtreNom)){
			criteria.add(Restrictions.like("tache", "%" + filtreNom + "%"));
		}
		
		if(filtreEnvironnement != null && filtreEnvironnement != -1){
			Environnement env = (Environnement) session.load(Environnement.class, filtreEnvironnement);
			criteria.add(Restrictions.like("environnement", env));
		}
		
		if(filtreDateDebut != null && !"".equals(filtreDateDebut)){
			Date debut = DateService.strToDate(filtreDateDebut);
			criteria.add(Restrictions.like("dateDebut", debut));
		}
		
		if(filtreEtat != null && filtreEtat != -1){
			Checklist_Etat etat = (Checklist_Etat) session.load(Checklist_Etat.class, filtreEtat);
			criteria.add(Restrictions.like("etat", etat));
		}
		
		if(filtreCriticite != null && filtreCriticite != -1){
			Checklist_Criticite criticite = (Checklist_Criticite) session.load(Checklist_Criticite.class, filtreCriticite);
			criteria.add(Restrictions.like("criticite", criticite));
		}
		
		if (filtreDemandes != null && filtreDemandes == 1) {
			criteria.add(Restrictions.or(Restrictions.eq("typeDemande", "1"), Restrictions.eq("typeDemande", "2")));
		}
		
		if("environnement".equals(sort)){
			criteria.createAlias("environnement", "env", Criteria.LEFT_JOIN);
			criteria.addOrder("desc".equals(sens) ? Order.desc("env.environnement") : Order.asc("env.environnement"));
		}
		else if("etat".equals(sort)){
			criteria.createAlias("etat", "et", Criteria.LEFT_JOIN);
			criteria.addOrder("desc".equals(sens) ? Order.desc("et.etat") : Order.asc("et.etat"));
		}
		else if("criticite".equals(sort)){
			criteria.createAlias("criticite", "cri", Criteria.LEFT_JOIN);
			criteria.addOrder("desc".equals(sens) ? Order.desc("cri.libelle") : Order.asc("cri.libelle"));
		}
		else{
			criteria.addOrder("desc".equals(sens) ? Order.desc(sort) : Order.asc(sort));
		}
		
		List<Checklist_Base> listCB = pagination.setCriteriaPagination(criteria);
		session.getTransaction().commit();
		return listCB;
	}
	
	
	/**
	 * SELECT d'une checklist_base
	 * @param selectRow
	 * @return
	 */
	public static Checklist_Base get(Integer selectRow) {
		Session session = PilotageSession.getCurrentSession();
		Checklist_Base base = (Checklist_Base)session.createCriteria(Checklist_Base.class)
								.add(Restrictions.eq("id", selectRow))
								.setMaxResults(1)
								.uniqueResult();
		session.getTransaction().commit();
		return base;
	}
	
	/**
	 * COUNT test si une tache référence un environnement
	 * @param envID
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static boolean hasEnvironnement(Integer envID)throws Exception{
		Session session = PilotageSession.getCurrentSession();
		
		Environnement environnement = (Environnement) session.load(Environnement.class, envID);
		List<Long> results = session.createCriteria(Checklist_Base.class)
						          .add(Restrictions.eq("environnement", environnement))
						          .setProjection(Projections.rowCount())
						          .list();
		session.getTransaction().commit();
		
		if (results!= null && results.size() > 0 && results.get(0) > 0) {
			return true;
		}
		else {
			return false;
		}
	}
}
