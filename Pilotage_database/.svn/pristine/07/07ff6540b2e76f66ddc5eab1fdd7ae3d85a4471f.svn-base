package pilotage.database.incidents;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import pilotage.metier.Incidents_Qualite;
import pilotage.metier.Incidents_Qualite_Action;
import pilotage.session.PilotageSession;
import pilotage.utils.Pagination;

public class IncidentsQualiteActionDatabaseService {

	
	/**
	 * SELECT de la liste des action d'une fiche incident qualite de utilisateur avec son id
	 * @param selectRow
	 * @param pagination
	 * @param sort
	 * @param sens
	 * @return
	 */
	public static List<Incidents_Qualite_Action> getAllActionIncidentQualite(Integer selectRow, Pagination<Incidents_Qualite_Action> pagination, String sort, String sens){
		Session session = PilotageSession.getCurrentSession();
		Incidents_Qualite iq = (Incidents_Qualite) session.load(Incidents_Qualite.class, selectRow);
		Criteria criteria = session.createCriteria(Incidents_Qualite_Action.class)
								.add(Restrictions.eq("idIncidentsQualite",iq))
								.addOrder("desc".equalsIgnoreCase(sens) ? Order.desc(sort) : Order.asc(sort));
		List<Incidents_Qualite_Action> result = pagination.setCriteriaPagination(criteria);
		session.getTransaction().commit();
		return result;
	}
	
	/**
	 * INSERT d'une nouvelle Action incident qualite
	 * @param dateA
	 * @param action
	 * @throws Exception 
	 */
	public static void create(Integer incidnetsQualiteId, Date dateAction, String action) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			Incidents_Qualite iq = (Incidents_Qualite) session.load(Incidents_Qualite.class, incidnetsQualiteId);
			
			Incidents_Qualite_Action incidentsQualiteAction = new Incidents_Qualite_Action();
			incidentsQualiteAction.setIdIncidentsQualite(iq);
			incidentsQualiteAction.setDateAction(dateAction);
			incidentsQualiteAction.setAction(action);
			
			session.save(incidentsQualiteAction);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	/**
	 * SELECT d'une action incident qualite
	 * @param selectRow
	 * @return
	 */
	public static Incidents_Qualite_Action get(Integer selectRow) {
		Session session = PilotageSession.getCurrentSession();
		Incidents_Qualite_Action incidentsQualiteAction = (Incidents_Qualite_Action)session.createCriteria(Incidents_Qualite_Action.class)
							.add(Restrictions.eq("id", selectRow))
							.setMaxResults(1)
							.uniqueResult();
		session.getTransaction().commit();
		return incidentsQualiteAction;
	}
	
	/**
	 * SELECT de l'id d'une action
	 * @param dateA
	 * @param action
	 * @return
	 */
	public static Integer getId(Date dateA, String action){
		Session session = PilotageSession.getCurrentSession();
		
		Incidents_Qualite_Action iqa = (Incidents_Qualite_Action)session.createCriteria(Incidents_Qualite_Action.class)
						.add(Restrictions.eq("dateAction", dateA))
						.add(Restrictions.eq("action", action))
						.setMaxResults(1)
						.uniqueResult();
		session.getTransaction().commit();
		return iqa.getId();
	}
	
	/**
	 * UPDATE d'une action incidents qualite
	 * @param id
	 * @param idIncidentQualite
	 * @param dateAction
	 * @param action
	 * @throws Exception 
	 */
	public static void modify(Integer id, Integer idIncidentsQualite, Date dateAction, String action) throws Exception {
		
		Session session = PilotageSession.getCurrentSession();
		try{
			Incidents_Qualite iq = (Incidents_Qualite)session.load(Incidents_Qualite.class, idIncidentsQualite);
			
			Incidents_Qualite_Action incidentsQualiteAction = (Incidents_Qualite_Action)session.load(Incidents_Qualite_Action.class, id);
			incidentsQualiteAction.setIdIncidentsQualite(iq);
			incidentsQualiteAction.setDateAction(dateAction);
			incidentsQualiteAction.setAction(action);
			
			session.update(incidentsQualiteAction);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	/**
	 * DELETE de l'action incident qualite
	 * @param selectRow
	 * @throws Exception 
	 */
	public static void delete(Integer selectRow) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{		
			Incidents_Qualite_Action iqa = (Incidents_Qualite_Action) session.load(Incidents_Qualite_Action.class, selectRow);
			
			session.delete(iqa);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
}
