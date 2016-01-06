package pilotage.database.feedback;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import pilotage.metier.Bug;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;
import pilotage.session.PilotageSession;
import pilotage.utils.Pagination;

public class FeedbackDatabaseService {
	
	/**
	 * SELECT de tous les feedbacks
	 * @param pagination
	 * @return
	 */
	public static List<Bug> getAll(Pagination<Bug> pagination){
		Session session = PilotageSession.getCurrentSession();
		Criteria criteria = session.createCriteria(Bug.class).addOrder(Order.desc("date"));
		List<Bug> listBug = pagination.setCriteriaPagination(criteria);
		session.getTransaction().commit();
		return listBug;
	}
	
	/**
	 * SELECT des feedbacks dans l'etat indiqué 
	 * @param pagination
	 * @param etat
	 * @return
	 */
	public static List<Bug> getAllByEtat(Pagination<Bug> pagination, String etat){
		Session session = PilotageSession.getCurrentSession();
		Criteria criteria = session.createCriteria(Bug.class)
									.add(Restrictions.eq("etat", etat))
									.addOrder(Order.desc("date"));
		List<Bug> listBug = pagination.setCriteriaPagination(criteria);
		session.getTransaction().commit();
		return listBug;
	}
	
	/**
	 * DELETE d'un feedback
	 * @param selectRow
	 * @throws Exception 
	 */
	public static void delete(int selectRow) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			Bug bug = (Bug)session.load(Bug.class, selectRow);
			session.delete(bug);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}

	/**
	 * INSERT d'un nouveau feedback
	 * @param date
	 * @param bug
	 * @param idUser
	 * @throws Exception
	 */
	public static void create(Date date, String bug, Users idUser) throws Exception{
		Session session = PilotageSession.getCurrentSession();
		try{
			Bug bug1 = new Bug();
			bug1.setDate(date);
			bug1.setBug(bug);
			bug1.setEtat(PilotageConstants.FEEDBACK_ETAT_EN_ATTENTE);
			bug1.setIdUser(idUser);
			
			session.save(bug1);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
		
	}
	
	/**
	 * SELECT d'un feedback
	 * @param selectRow
	 * @return
	 */
	public static Bug get(Integer selectRow) {
		Session session = PilotageSession.getCurrentSession();
		Bug bug = (Bug)session.createCriteria(Bug.class)
							.add(Restrictions.eq("id", selectRow))
							.setMaxResults(1)
							.uniqueResult();
		session.getTransaction().commit();
		return bug;
	}
	
	/**
	 * UPDATE de l'état d'un feedback
	 * @param id
	 * @param etat
	 * @throws Exception 
	 */
    public static void setEtat(Integer id, String etat) throws Exception{
		Session session = PilotageSession.getCurrentSession();
		try{
			Bug bug = (Bug)session.load(Bug.class, id);
			bug.setEtat(etat);
			
			session.update(bug);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
    /**
     * UPDATE du contenu d'un feedback
     * @param id
     * @param bugMessage
     * @throws Exception 
     */
    public static void modifyBug(Integer id, String bugMessage) throws Exception{
		Session session = PilotageSession.getCurrentSession();
		try{
			Bug bug = (Bug)session.load(Bug.class, id);
			bug.setBug(bugMessage);
			
			session.update(bug);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}		
	}

    public static Integer getId(String libelle){
		Session session = PilotageSession.getCurrentSession();
		Bug b = (Bug)session.createCriteria(Bug.class)
						.add(Restrictions.eq("bug", libelle))
						.setMaxResults(1)
						.uniqueResult();
		session.getTransaction().commit();
		return b.getId();
	}
}
