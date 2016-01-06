package pilotage.database.incidents;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import pilotage.metier.Incidents_Qualite;
import pilotage.metier.Incidents_Qualite_Action;
import pilotage.metier.Incidents_Qualite_Statut;
import pilotage.session.PilotageSession;
import pilotage.utils.Pagination;

public class IncidentsQualiteDatabaseService {

	/**
	 * SELECT de la liste des fiches incidents qualite de utilisateur avec son id
	 * @param pagination
	 * @param sort
	 * @param sens
	 * @param dateRes 
	 * @param filtreStatut 
	 * @param filtreIncidence 
	 * @param filtreCause 
	 * @param filtreDescription 
	 * @param date 
	 * @return
	 */
	public static List<Incidents_Qualite> getAllFicheIncidentQualite(Pagination<Incidents_Qualite> pagination, String sort, String sens, Date date, String filtreDescription, String filtreCause, String filtreIncidence, String filtreEcheance, Integer filtreStatut, Date dateRes){
		Session session = PilotageSession.getCurrentSession();
		Criteria criteria = session.createCriteria(Incidents_Qualite.class);
		
		if(date != null){
			criteria.add(Restrictions.eq("dateEvenement", date));
		}
		if(filtreDescription != null && ! "".equals(filtreDescription)){
			criteria.add(Restrictions.like("description", "%" + filtreDescription + "%"));
		}
		if(filtreCause != null && ! "".equals(filtreCause)){
			criteria.add(Restrictions.like("cause_raison", "%" + filtreCause + "%"));
		}
		if(filtreIncidence != null && ! "".equals(filtreIncidence)){
			criteria.add(Restrictions.like("incidence", "%" + filtreIncidence + "%"));
		}
		if(filtreEcheance != null && ! "".equals(filtreEcheance)){
			criteria.add(Restrictions.like("echeance", "%" + filtreEcheance + "%"));
		}
		if(filtreStatut != null && filtreStatut > -1){
			Incidents_Qualite_Statut statut = (Incidents_Qualite_Statut) session.load(Incidents_Qualite_Statut.class, filtreStatut);
			criteria.add(Restrictions.eq("statut", statut));
		}
		if(dateRes != null){
			criteria.add(Restrictions.eq("dateResolution", dateRes));
		}
		
		if("statut".equals(sort)){
			criteria.createCriteria("statut", "statut", Criteria.LEFT_JOIN);
			criteria.addOrder("desc".equalsIgnoreCase(sens) ? Order.desc("statut.statut") : Order.asc("statut.statut"));
		}
		else{
			criteria.addOrder("desc".equalsIgnoreCase(sens) ? Order.desc(sort) : Order.asc(sort));
		}
		
		List<Incidents_Qualite> result = pagination.setCriteriaPagination(criteria);
		session.getTransaction().commit();
		return result;
	}
	
	/**
	 * INSERT d'une nouvelle Fiche incident qualite
	 * @param dateE
	 * @param description
	 * @param cause_raison
	 * @param incidence
	 * @param echeance
	 * @param statut
	 * @param dateR
	 * @throws Exception 
	 */
	public static void create(Date dateEvenement, String description, String cause_raison, String  incidence, String  echeance, Integer statut, Date dateResolution) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			Incidents_Qualite_Statut s = (Incidents_Qualite_Statut)session.load(Incidents_Qualite_Statut.class, statut);
			
			Incidents_Qualite incidentsQualite = new Incidents_Qualite();
			incidentsQualite.setDateEvenement(dateEvenement);
			incidentsQualite.setDescription(description);
			incidentsQualite.setCause_raison(cause_raison);
			incidentsQualite.setIncidence(incidence);
			incidentsQualite.setEcheance(echeance);
			incidentsQualite.setStatut(s);
			incidentsQualite.setDateResolution(dateResolution);
			
			session.save(incidentsQualite);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	/**
	 * SELECT d'une Fiche incident qualite
	 * @param selectRow
	 * @return
	 */
	public static Incidents_Qualite get(Integer selectRow) {
		Session session = PilotageSession.getCurrentSession();
		Incidents_Qualite incidentsQualite = (Incidents_Qualite)session.createCriteria(Incidents_Qualite.class)
							.add(Restrictions.eq("id", selectRow))
							.setMaxResults(1)
							.uniqueResult();
		session.getTransaction().commit();
		return incidentsQualite;
	}
	
	public static Integer getId(Date dateE, String description){
		Session session = PilotageSession.getCurrentSession();
		
		Incidents_Qualite iq = (Incidents_Qualite)session.createCriteria(Incidents_Qualite.class)
						.add(Restrictions.eq("dateEvenement", dateE))
						.add(Restrictions.eq("description", description))
						.setMaxResults(1)
						.uniqueResult();
		session.getTransaction().commit();
		return iq.getId();
	}
	
	/**
	 * DELETE de la fiche incident qualite
	 * @param selectRow
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public static void delete(Integer selectRow) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{		
			Incidents_Qualite iq = (Incidents_Qualite) session.load(Incidents_Qualite.class, selectRow);
			// recup des actions de la fiche incidnets qualite
			List<Incidents_Qualite_Action> iqaList = session.createCriteria(Incidents_Qualite_Action.class)
				.add(Restrictions.eq("idIncidentsQualite", iq))
				.list();
			for(Incidents_Qualite_Action action : iqaList){
				session.delete(action);
			}		
			session.delete(iq);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	/**
	 * UPDATE d'une fiche incidents qualite
	 * @param id
	 * @param dateE
	 * @param description
	 * @param cause_raison
	 * @param incidence
	 * @param echeance
	 * @param statut
	 * @param dateR
	 * @throws Exception 
	 */
	public static void modify(Integer id, Date dateEvenement, String description, String cause_raison, String  incidence, String  echeance, Integer statut, Date dateResolution) throws Exception {
		
		Session session = PilotageSession.getCurrentSession();
		try{
			Incidents_Qualite_Statut s = (Incidents_Qualite_Statut)session.load(Incidents_Qualite_Statut.class, statut);
			
			Incidents_Qualite incidentsQualite = (Incidents_Qualite)session.load(Incidents_Qualite.class, id);
			incidentsQualite.setDateEvenement(dateEvenement);
			incidentsQualite.setDescription(description);
			incidentsQualite.setCause_raison(cause_raison);
			incidentsQualite.setIncidence(incidence);
			incidentsQualite.setEcheance(echeance);
			incidentsQualite.setStatut(s);
			incidentsQualite.setDateResolution(dateResolution);
	
			session.update(incidentsQualite);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	
}
