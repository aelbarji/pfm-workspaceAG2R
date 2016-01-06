package pilotage.database.gup;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.joda.time.DateTime;

import pilotage.metier.Com_Incident_Etat;
import pilotage.metier.Incidents_Gup;
import pilotage.metier.Users;
import pilotage.session.PilotageSession;
import pilotage.utils.Pagination;

public class IncidentsGupDatabaseService {
	
	/**
	 * SELECT de la liste des incidents GUP avec Pagination
	 * @return
	 */
	public static List<Incidents_Gup> getAll(Pagination<Incidents_Gup> pagination){
		
		Session session = PilotageSession.getCurrentSession();
		Criteria criteria = session.createCriteria(Incidents_Gup.class);
		criteria.addOrder(Order.desc("date_deb"));
		List<Incidents_Gup> inc = pagination.setCriteriaPagination(criteria);
		session.getTransaction().commit();
		return inc;
	}
	
	/**
	 * SELECT de la liste des incidents GUP
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Incidents_Gup> getAllEnCours(DateTime selectedDateDeb, DateTime selectedDateFin){
		
		Session session = PilotageSession.getCurrentSession();
		Criteria criteria = session.createCriteria(Incidents_Gup.class);
			criteria.addOrder(Order.asc("date_deb"));
			criteria.add(Restrictions.isNull("date_fin"));
		List<Incidents_Gup> inc = criteria.list();
		session.getTransaction().commit();
		return inc;
	}
	
	/**
	 * SELECT de la liste des incidents GUP
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Incidents_Gup> getAllClotures(DateTime selectedDateDeb, DateTime selectedDateFin){
		
		Session session = PilotageSession.getCurrentSession();
		Criteria criteria = session.createCriteria(Incidents_Gup.class);
			criteria.addOrder(Order.asc("date_deb"));
			criteria.add(Restrictions.isNotNull("date_fin"));
			criteria.add(Restrictions.disjunction()
			        .add(Restrictions.between("date_deb", selectedDateDeb, selectedDateFin))
			        .add(Restrictions.between("date_fin", selectedDateDeb, selectedDateFin)));
		List<Incidents_Gup> inc = criteria.list();
		session.getTransaction().commit();
		return inc;
	}
	
	/**
	 * SELECT de la liste des incidents GUP avec Pagination
	 * @return
	 */
	public static List<Incidents_Gup> getAll(Pagination<Incidents_Gup> pagination, Integer filtreDomaine, Integer filtreService, Integer filtreDetection,
			Integer filtreApplicatif, String filtreArs, DateTime filtreDate, DateTime filtreDateFin, Integer filtreInterlocuteur, Integer filtreEtat,
			Integer filtreImpact, String filtreDescription, Integer filtrePilote){
		
		Session session = PilotageSession.getCurrentSession();
		Criteria criteria = session.createCriteria(Incidents_Gup.class).addOrder(Order.desc("date_deb"));
		

		if (filtrePilote != null && !filtrePilote.equals(-1)) {
			Users user1 = (Users)session.load(Users.class, filtrePilote);
			criteria.add(Restrictions.eq("user", user1));
		}
		
		if (filtreArs != null && !filtreArs.trim().equals("")) {
			criteria.add(Restrictions.eq("ars", filtreArs));
		}
		
		if (filtreEtat != null && !filtreEtat.equals(-1)) {
			Com_Incident_Etat mep = (Com_Incident_Etat) session.load(Com_Incident_Etat.class, filtreEtat);
			criteria.add(Restrictions.eq("etat", mep));
		}
		
		if (filtreDescription != null && !filtreDescription.trim().equals("")) {
			criteria.add(Restrictions.ilike("description_prob", "%" + filtreDescription + "%"));
			
		}
		
		if (filtreImpact != null && !filtreImpact.equals(-1)) {
			criteria.add(Restrictions.eq("impact", filtreImpact));
		}
		
		List<Incidents_Gup> inc = pagination.setCriteriaPagination(criteria);
		session.getTransaction().commit();
		return inc;
	}
	
	/**
	 * SELECT de la liste des incidents GUP sans Pagination
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Incidents_Gup> getAllWithoutPagination(String filtreArs, DateTime filtreDate, DateTime filtreDateFin, Integer filtreEtat,
			Integer filtreImpact, String filtreDescription, Integer filtrePilote){
		
		Session session = PilotageSession.getCurrentSession();
		Criteria criteria = session.createCriteria(Incidents_Gup.class).addOrder(Order.desc("date_deb"));
		
		if (filtreDate != null && filtreDateFin != null && !filtreDateFin.equals("") && !filtreDate.equals("")) {
			criteria.add(Restrictions.between("date_deb", filtreDate, filtreDateFin));
		}
		
		if (filtreDate != null && !filtreDate.equals("")) {
			criteria.add(Restrictions.ge("date_deb", filtreDate));
		}
		
		if (filtreDateFin != null && !filtreDateFin.equals("")) {
			criteria.add(Restrictions.le("date_deb", filtreDateFin));
		}

		if (filtrePilote != null && !filtrePilote.equals(-1)) {
			Users user1 = (Users)session.load(Users.class, filtrePilote);
			criteria.add(Restrictions.eq("user", user1));
		}
		
		if (filtreArs != null && !filtreArs.trim().equals("")) {
			criteria.add(Restrictions.eq("ars", filtreArs));
		}
		
		if (filtreEtat != null && !filtreEtat.equals(-1)) {
			Com_Incident_Etat mep = (Com_Incident_Etat) session.load(Com_Incident_Etat.class, filtreEtat);
			criteria.add(Restrictions.eq("etat", mep));
		}
		
		if (filtreDescription != null && !filtreDescription.trim().equals("")) {
			criteria.add(Restrictions.ilike("description_prob", "%" + filtreDescription + "%"));
		}
		
		if (filtreImpact != null && !filtreImpact.equals(-1)) {
			criteria.add(Restrictions.eq("impact", filtreImpact));
		}

		List<Incidents_Gup> inc = criteria.list();
		session.getTransaction().commit();
		return inc;
	}
	
	/**
	 * SELECT d'un incident GUP
	 * @return
	 */
	public static Incidents_Gup get(Integer selectRow){
		Session session = PilotageSession.getCurrentSession();
		Incidents_Gup inc = (Incidents_Gup)session.createCriteria(Incidents_Gup.class)
								.add(Restrictions.eq("id", selectRow))
								.setMaxResults(1)
								.uniqueResult();
		session.getTransaction().commit();
		return inc;
	}
	
	
	/**
	 * INSERT d'un nouvel incident Gup
	 * @param id
	 * @param users
	 * @param appliID
	 * @param domaineID
	 * @param serviceID
	 * @param date_deb
	 * @param date_fin
	 * @param detectionID
	 * @param prob
	 * @param resol
	 * @param interlocID
	 * @param impact
	 * @param etatID
	*/
	
	public static Incidents_Gup create(Users user, DateTime date_deb, DateTime date_fin, String prob, Integer impact, Integer etatID, String ars) throws Exception{
		Session session = PilotageSession.getCurrentSession();
		
		try{
			
			Incidents_Gup inc = new Incidents_Gup();
			inc.setUser(user);
			if(etatID != null && !etatID.equals(-1)){
				Com_Incident_Etat etat = (Com_Incident_Etat)session.load(Com_Incident_Etat.class, etatID);
				inc.setEtat(etat);
			}
			inc.setDate_deb(date_deb);
			inc.setDate_fin(date_fin);
			inc.setDescription_prob(prob);
			inc.setImpact(impact);
			inc.setArs(ars);
			session.save(inc);
			session.getTransaction().commit();
			return inc;
		}catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	/**
	 * UPDATE d'un incident GUP
	 * @param id
	 * @param userID
	 * @param appliID
	 * @param domaineID
	 * @param serviceID
	 * @param date_deb
	 * @param date_fin
	 * @param detectionID
	 * @param prob
	 * @param resol
	 * @param interlocID
	 * @param impact
	 * @param etatID
	 * @throws Exception 
	 */
	public static Incidents_Gup modify(Integer id, Users user, DateTime date_deb, DateTime date_fin, String prob, Integer impact, Integer etatID, String ars) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			Incidents_Gup inc = (Incidents_Gup)session.load(Incidents_Gup.class, id);
			if(etatID != null){
				Com_Incident_Etat etat = (Com_Incident_Etat)session.load(Com_Incident_Etat.class, etatID);
				inc.setEtat(etat);
			}
			inc.setUser(user);
			inc.setDate_deb(date_deb);
			inc.setDate_fin(date_fin);
			inc.setDescription_prob(prob);
			inc.setImpact(impact);
			inc.setArs(ars);
			
			session.update(inc);
			session.getTransaction().commit();
			return inc;
		}catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	/**
	 * DELETE de l'incident gup
	 * @param selectedID
	 * @throws Exception 
	 */
	public static void delete(Integer selectedID) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			Incidents_Gup inc = (Incidents_Gup) session.load(Incidents_Gup.class, selectedID);
			
			session.delete(inc);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	public static void reinitialiserIncident(Integer id) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			Incidents_Gup inc = (Incidents_Gup)session.load(Incidents_Gup.class, id);

			inc.setDate_fin(null);
			
			session.update(inc);
			session.getTransaction().commit();
		}catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	
	
}
