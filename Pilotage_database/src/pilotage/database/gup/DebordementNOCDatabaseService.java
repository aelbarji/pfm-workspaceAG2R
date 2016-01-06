package pilotage.database.gup;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.joda.time.DateTime;

import pilotage.metier.Debordement_NOC;
import pilotage.session.PilotageSession;
import pilotage.utils.Pagination;

public class DebordementNOCDatabaseService {

	/**
	 * SELECT de la liste des debordements NOC
	 * @return
	 */
	public static List<Debordement_NOC> getAll(Pagination<Debordement_NOC> pagination){
		Session session = PilotageSession.getCurrentSession();
		Criteria criteria = session.createCriteria(Debordement_NOC.class);
		
		criteria.addOrder(Order.desc("datetime"));
		
		List<Debordement_NOC> debNOC = pagination.setCriteriaPagination(criteria);
		session.getTransaction().commit();
		return debNOC;
	}
	
	/**
	 * SELECT debordement NOC
	 * @param selectRow
	 * @return
	 */
	public static Debordement_NOC get(Integer selectRow){
		Session session = PilotageSession.getCurrentSession();
		Debordement_NOC debNOC = (Debordement_NOC)session.createCriteria(Debordement_NOC.class)
								.add(Restrictions.eq("id", selectRow))
								.setMaxResults(1)
								.uniqueResult();
		session.getTransaction().commit();
		return debNOC;
	}
	
	
	/**
	 * INSERT d'un nouveau debordement_noc
	 * @param datetime
	 * @param operateur
	 * @param inc_noc
	 * @param inc_op
	 * @param info_act
	 */
	public static Integer create(DateTime datetime, String operateur, String inc_noc, String inc_op, String info_act) throws Exception{
		Session session = PilotageSession.getCurrentSession();
		
		try{
			Debordement_NOC debNoc = new Debordement_NOC();
			debNoc.setDatetime(datetime);
			debNoc.setOperateur(operateur);
			debNoc.setInc_noc(inc_noc);
			debNoc.setInc_op(inc_op);
			debNoc.setInfo_act(info_act);
			
			session.save(debNoc);
			session.getTransaction().commit();
			
			return debNoc.getId();
		}catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	/**
	 * UPDATE d'un debordement NOC
	 * @param id
	 * @param datetime
	 * @param operateur
	 * @param inc_noc
	 * @param inc_op
	 * @param info_act
	 * @throws Exception 
	 */
	public static void modify(Integer id, DateTime datetime, String operateur, String inc_noc, String inc_op, String info_act) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			Debordement_NOC debNoc = (Debordement_NOC)session.load(Debordement_NOC.class, id);
			debNoc.setDatetime(datetime);
			debNoc.setOperateur(operateur);
			debNoc.setInc_noc(inc_noc);
			debNoc.setInc_op(inc_op);
			debNoc.setInfo_act(info_act);
			
			session.update(debNoc);
			session.getTransaction().commit();
			
		}catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	/**
	 * DELETE d'un debordement NOC
	 * @param selectedID
	 * @throws Exception 
	 */
	public static void delete(Integer selectedID) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			Debordement_NOC debNoc = (Debordement_NOC) session.load(Debordement_NOC.class, selectedID);
			
			session.delete(debNoc);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	
}
