package pilotage.database.planning;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.joda.time.DateTime;

import pilotage.metier.Planning_Cycle;
import pilotage.metier.Planning_Semaine;
import pilotage.metier.Planning_Vacation;
import pilotage.session.PilotageSession;

public class PlanningSemaineDatabaseService {	
	@SuppressWarnings("unchecked")
	public static boolean hasSemaineWithVacation(int id){
		Session session = PilotageSession.getCurrentSession();
		
		Planning_Vacation pv = (Planning_Vacation)session.load(Planning_Vacation.class, id);
		List<Long> results= (List<Long>)session.createCriteria(Planning_Semaine.class)
									.add(Restrictions.or(
										Restrictions.eq("lundi", pv),
										Restrictions.or(
											Restrictions.eq("mardi", pv),
											Restrictions.or(
												Restrictions.eq("mercredi", pv),
												Restrictions.or(
													Restrictions.eq("jeudi", pv),
													Restrictions.or(
														Restrictions.eq("vendredi", pv),
														Restrictions.or(
															Restrictions.eq("samedi", pv),
															Restrictions.eq("dimanche", pv))))))))
									.setProjection(Projections.rowCount())
									.list();
		session.getTransaction().commit();
		
		if (results != null && results.size() > 0 && results.get(0) > 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * SELECT des semaines du cycle
	 * @param idCycle
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Planning_Semaine> getSemaineByCycle(Integer idCycle) {
		Session session = PilotageSession.getCurrentSession();
		Planning_Cycle pc = (Planning_Cycle)session.load(Planning_Cycle.class, idCycle);
		List<Planning_Semaine> semaineList = session.createCriteria(Planning_Semaine.class)
												.add(Restrictions.eq("idNomCycle",pc))
												.addOrder(Order.asc("numeroSemaine"))
												.list();
		session.getTransaction().commit();
		return semaineList;
	}
	
	@SuppressWarnings("unchecked")
	public static List<Planning_Semaine> getSemaineByCycleAndDate(Integer idCycle, DateTime deb, DateTime fin) {
		Session session = PilotageSession.getCurrentSession();
		Planning_Cycle pc = (Planning_Cycle)session.load(Planning_Cycle.class, idCycle);
		List<Planning_Semaine> semaineList = session.createCriteria(Planning_Semaine.class)
												.add(Restrictions.eq("idNomCycle",pc))
												.add(Restrictions.or(
														Restrictions.isNull("dateFin"),
														Restrictions.ge("dateFin", deb)))
												.add(Restrictions.le("dateDeb", fin))
												.addOrder(Order.asc("numeroSemaine"))
												.addOrder(Order.asc("dateDeb"))
												.list();
		session.getTransaction().commit();
		return semaineList;
	}
	
	public static List<Planning_Semaine> getBySemAndCycle(Integer idCycle, Integer numSemaine, DateTime deb, DateTime fin){
		Session session = PilotageSession.getCurrentSession();
		
		Planning_Cycle pc = (Planning_Cycle)session.load(Planning_Cycle.class, idCycle);
		
		@SuppressWarnings("unchecked")
		List<Planning_Semaine> semaineList = (List<Planning_Semaine>)session.createCriteria(Planning_Semaine.class)
									.add(Restrictions.eq("idNomCycle",pc))
									.add(Restrictions.eq("numeroSemaine", numSemaine))
									.add(Restrictions.or(
														Restrictions.isNull("dateFin"),
														Restrictions.ge("dateFin", deb)))
									.add(Restrictions.le("dateDeb", fin))
									.list();
		session.getTransaction().commit();
		return semaineList;
	}
	

	public static List<Planning_Semaine> getBySemAndCycle(Integer idCycle, Integer numSemaine){
		Session session = PilotageSession.getCurrentSession();
		
		Planning_Cycle pc = (Planning_Cycle)session.load(Planning_Cycle.class, idCycle);
		
		@SuppressWarnings("unchecked")
		List<Planning_Semaine> semaineList = (List<Planning_Semaine>)session.createCriteria(Planning_Semaine.class)
									.add(Restrictions.eq("idNomCycle",pc))
									.add(Restrictions.eq("numeroSemaine", numSemaine))
									.addOrder(Order.asc("dateDeb"))
									.list();
		// fait exprès car fonction utilisé dans PlanningCyclesDatabaseService
		//session.getTransaction().commit();
		return semaineList;
	}
	
	public static Integer getId(Integer idCycle, Integer numSemaine){
		Session session = PilotageSession.getCurrentSession();
		Planning_Cycle pc = (Planning_Cycle)session.load(Planning_Cycle.class, idCycle);
		Planning_Semaine ps = (Planning_Semaine)session.createCriteria(Planning_Semaine.class)
									.add(Restrictions.eq("idNomCycle",pc))
									.add(Restrictions.eq("numeroSemaine", numSemaine))
									.setMaxResults(1)
									.uniqueResult();
		session.getTransaction().commit();
		return ps.getId();
	}
	
	/**
	 * SELECT d'un planning semaine
	 * @return
	 */
	public static Planning_Semaine get(Integer selectRow){
		Session session = PilotageSession.getCurrentSession();
		Planning_Semaine ps = (Planning_Semaine)session.createCriteria(Planning_Semaine.class)
								.add(Restrictions.eq("id", selectRow))
								.setMaxResults(1)
								.uniqueResult();
		session.getTransaction().commit();
		return ps;
	}
	
	/**
	 * SELECT de la liste des planning semaine
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Planning_Semaine> getAll(){
		Session session = PilotageSession.getCurrentSession();
		List<Planning_Semaine> lps = session.createCriteria(Planning_Semaine.class).list();
		session.getTransaction().commit();
		return lps;
	}
}
