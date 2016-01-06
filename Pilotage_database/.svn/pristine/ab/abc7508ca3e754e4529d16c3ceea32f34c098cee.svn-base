package pilotage.database.planning;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import pilotage.metier.Planning_Cycle;
import pilotage.metier.Planning_Cycle_Equipe;
import pilotage.metier.Planning_Nom_Equipe;
import pilotage.session.PilotageSession;

public class PlanningCycleEquipeDatabaseService {
	
	/**
	 * COUNT test si le cycle est utilisé
	 * @param idCycle
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static boolean hasEquipeWithCycle(int idCycle){
		Session session = PilotageSession.getCurrentSession();
		
		Planning_Cycle pc = (Planning_Cycle)session.load(Planning_Cycle.class, idCycle);
		List<Long> results= (List<Long>)session.createCriteria(Planning_Cycle_Equipe.class)
									.add(Restrictions.eq("idNomCycle", pc))
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
	
	@SuppressWarnings("unchecked")
	public static List<Planning_Cycle_Equipe> getCycleByEquipe(int idEquipe){
		Session session = PilotageSession.getCurrentSession();
		
		Planning_Nom_Equipe pne = (Planning_Nom_Equipe)session.load(Planning_Nom_Equipe.class, idEquipe);
		List<Planning_Cycle_Equipe> listPCE = (List<Planning_Cycle_Equipe>)session.createCriteria(Planning_Cycle_Equipe.class)
												.add(Restrictions.eq("idNomEquipe", pne))
												.addOrder(Order.asc("dateDebut"))
												.list();
		session.getTransaction().commit();
		return listPCE;
	}
	
	public static Integer getId(Integer idEquipe, Integer idCycle){
		Session session = PilotageSession.getCurrentSession();
		Planning_Cycle pc = (Planning_Cycle)session.load(Planning_Cycle.class, idCycle);
		Planning_Nom_Equipe pne = (Planning_Nom_Equipe)session.load(Planning_Nom_Equipe.class, idEquipe);
		Planning_Cycle_Equipe pce = (Planning_Cycle_Equipe)session.createCriteria(Planning_Cycle_Equipe.class)
									.add(Restrictions.eq("idNomCycle", pc))
									.add(Restrictions.eq("idNomEquipe", pne))
									.setMaxResults(1)
									.uniqueResult();
		session.getTransaction().commit();
		return pce.getId();
	}

	/**
	 * SELECT de la date de début la plus ancienne
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Date getFirstDate() {
		Session session = PilotageSession.getCurrentSession();
		List<Planning_Cycle_Equipe> pce = session.createCriteria(Planning_Cycle_Equipe.class)
												.addOrder(Order.asc("dateDebut"))
												.setMaxResults(1)
												.list();
		session.getTransaction().commit();
		return pce.size() == 0 ? null : pce.get(0).getDateDebut();
	}

	/**
	 * SELECT des cycles d'une équipe où les dates de cycle dans l'équipe correspond aux dates demandées
	 * @param idEquipe - id de l'équipe
	 * @param dateDebutSemaine - debut de la semaine demandée
	 * @param dateFinSemaine - fin de la semaine demandée
	 * @param pepDateDebut - debut du pilote dans cette equipe
	 * @param pepDateFin - fin du pilotage dans cette equipe
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Planning_Cycle_Equipe> getCycleByEquipe(Integer idEquipe, Date dateDebutSemaine, Date dateFinSemaine, Date pepDateDebut, Date pepDateFin) {
		
		Session session = PilotageSession.getCurrentSession();
		Planning_Nom_Equipe pne = (Planning_Nom_Equipe)session.load(Planning_Nom_Equipe.class, idEquipe);
		
		Criteria criteria = session.createCriteria(Planning_Cycle_Equipe.class)
								.add(Restrictions.eq("idNomEquipe", pne))
								.add(Restrictions.le("dateDebut", dateFinSemaine))
								.add(Restrictions.or(
										Restrictions.isNull("dateFin"),
										Restrictions.and(
												Restrictions.ge("dateFin", pepDateDebut), 
												Restrictions.ge("dateFin", dateDebutSemaine))))
								.addOrder(Order.asc("dateDebut"));
		if(pepDateFin != null)
			criteria = criteria.add(Restrictions.le("dateDebut", pepDateFin));
		
		List<Planning_Cycle_Equipe>  pceList = criteria.list();
		
		session.getTransaction().commit();
		return pceList;
	}

	/**
	 * SELECT des cycles suivant l'équipe et la période concernée
	 * @param parseInt
	 * @param dateDebut
	 * @param dateFin
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Planning_Cycle_Equipe> getCycleByEquipeAndDate(int idEquipe, Date dateDebut, Date dateFin) {
		Session session = PilotageSession.getCurrentSession();
		
		Planning_Nom_Equipe pne = (Planning_Nom_Equipe)session.load(Planning_Nom_Equipe.class, idEquipe);
		List<Planning_Cycle_Equipe> pceList = session.createCriteria(Planning_Cycle_Equipe.class)
								.add(Restrictions.eq("idNomEquipe", pne))
								.add(Restrictions.or(
										Restrictions.isNull("dateFin"), 
										Restrictions.ge("dateFin", dateDebut)))
								.add(Restrictions.le("dateDebut", dateFin))
								.addOrder(Order.asc("dateDebut"))
								.list();
		
		session.getTransaction().commit();
		return pceList;
	}
	
	/**
	 * SELECT des cycles suivant l'équipe et la période concernée
	 * @param parseInt
	 * @param dateDebut
	 * @param dateFin
	 * @return
	 */
	public static Planning_Cycle_Equipe getCycleByEquipeAndDate(int idEquipe, Date date) {
			
		Session session = PilotageSession.getCurrentSession();
		
		Planning_Nom_Equipe pne = (Planning_Nom_Equipe)session.load(Planning_Nom_Equipe.class, idEquipe);
		Planning_Cycle_Equipe pce = (Planning_Cycle_Equipe) session.createCriteria(Planning_Cycle_Equipe.class)
								.add(Restrictions.eq("idNomEquipe", pne))
								.add(Restrictions.or(
										Restrictions.isNull("dateFin"), 
										Restrictions.ge("dateFin", date)))
								.add(Restrictions.le("dateDebut", date))
								.setMaxResults(1).uniqueResult();
		
		session.getTransaction().commit();
		return pce;
	}
	
	/**
	 * SELECT d'un planning cycle equipe
	 * @return
	 */
	public static Planning_Cycle_Equipe get(Integer selectRow){
		Session session = PilotageSession.getCurrentSession();
		Planning_Cycle_Equipe pce = (Planning_Cycle_Equipe)session.createCriteria(Planning_Cycle_Equipe.class)
								.add(Restrictions.eq("id", selectRow))
								.setMaxResults(1)
								.uniqueResult();
		session.getTransaction().commit();
		return pce;
	}
	
	@SuppressWarnings("unchecked")
	public static List<Planning_Cycle_Equipe> getEquipesByCycle(int idCycle){
		
		Session session = PilotageSession.getCurrentSession();
		Planning_Cycle pc = (Planning_Cycle)session.load(Planning_Cycle.class, idCycle);
		List<Planning_Cycle_Equipe> results= (List<Planning_Cycle_Equipe>)session.createCriteria(Planning_Cycle_Equipe.class)
									.add(Restrictions.eq("idNomCycle", pc))
									.list();
		return results;
	}
}
