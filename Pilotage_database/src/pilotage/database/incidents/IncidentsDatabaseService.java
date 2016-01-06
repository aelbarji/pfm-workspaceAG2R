package pilotage.database.incidents;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import pilotage.database.environnement.EnvironnementDatabaseService;
import pilotage.metier.Environnement;
import pilotage.metier.Environnement_Type;
import pilotage.metier.Heures_Oceor;
import pilotage.metier.Incidents;
import pilotage.metier.Incidents_Outils;
import pilotage.metier.Incidents_Type;
import pilotage.metier.Machines_Liste;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;
import pilotage.service.date.DateService;
import pilotage.session.PilotageSession;
import pilotage.utils.Pagination;

/**
 * @author xxu
 *
 */
public class IncidentsDatabaseService {

	/**
	 * SELECT des incidents non finis du type pass� en param�tre
	 * @param incidents_Type 
	 * @param typeSelected
	 * @return
	 * @throws ParseException 
	 * @throws HibernateException 
	 */
	public static List<Incidents> getSpecificTypeUnfinished(Pagination<Incidents> pagination, Integer filtreIncidents, Integer incidentsTypeID) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		String nowString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		Date now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(nowString);
		Incidents_Type incidents_Type = (Incidents_Type)session.load(Incidents_Type.class, incidentsTypeID);
		Criteria criteria = session.createCriteria(Incidents.class)
						.add(Restrictions.eq("type", incidents_Type))
						.addOrder(Order.desc("dateDebut"));
		
		//si on veut les incidents du jour 
		if(filtreIncidents == 1){
			criteria.add(Restrictions.between("dateDebut", DateService.getDateEarlier(now), DateService.getDateLater(now)));
		}
		//si on ne veut pas les incidents du jour on prend les incidents non clotures
		else{
			criteria.add(
					Restrictions.or(
							Restrictions.isNull("dateFin"), 
							Restrictions.lt("dateFin", new SimpleDateFormat("dd-MM-yyyy HH:mm").parse("01-01-1970 00:00"))
					)
			);
		}
		List<Incidents> listIncidents = pagination.setCriteriaPagination(criteria);
		session.getTransaction().commit();
		return listIncidents;
	}

	/**
	 * SELECT de tous les incidents non finis
	 * @return 
	 * @throws Exception 
	 */
	public static List<Incidents> getAllUnfinished(Pagination<Incidents> pagination, Integer filtreIncidents) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		String nowString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		Date now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(nowString);
		Criteria criteria = session.createCriteria(Incidents.class).addOrder(Order.desc("dateDebut"));
		
		//si on veut les incidents du jour 
		if(filtreIncidents == 1){
			criteria.add(Restrictions.between("dateDebut", DateService.getDateEarlier(now), DateService.getDateLater(now)));
		}
		//si on ne veut pas les incidents du jour on prend les incidents non clotures
		else{
			criteria.add(
					Restrictions.or(
							Restrictions.isNull("dateFin"), 
							Restrictions.lt("dateFin", new SimpleDateFormat("dd-MM-yyyy HH:mm").parse("01-01-1970 00:00"))
					)
			);
		}
		
		List<Incidents> listIncidents = pagination.setCriteriaPagination(criteria);
		session.getTransaction().commit();
		return listIncidents;
	}
	
	/**
	 * SELECT de tous les incidents non finis
	 * @return 
	 * @throws Exception 
	 */
	public static List<Incidents> getIncidentByEnvir(Pagination<Incidents> pagination, Environnement envir, Date date) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		
		Integer[] listTypeIncidentID = {PilotageConstants.INCIDENT, PilotageConstants.CRITIQUE};
		
		Criteria criteria = session.createCriteria(Incidents.class).addOrder(Order.desc("dateDebut"));
		criteria.add(Restrictions.eq("domaine", envir));
		
		addTypeIncidentRestrictionToIncidentCriteria(session, criteria, listTypeIncidentID);
		if(date != null){
			criteria.add(Restrictions.ge("dateFin",date));
		}else{
			criteria.add(Restrictions.isNull("dateFin"));
		}
		List<Incidents> listIncidents = pagination.setCriteriaPagination(criteria);
		session.getTransaction().commit();
		return listIncidents;
	}

	/**
	 * SELECT de l'incident identifi� par le param�tre
	 * @param id
	 * @return
	 */
	public static Incidents get(Integer id) {
		Session session = PilotageSession.getCurrentSession();
		Incidents incident = (Incidents) session.createCriteria(Incidents.class)
									.add(Restrictions.eq("id", id))
									.uniqueResult();
		session.getTransaction().commit();
		return incident;
	}

	/**
	 * DELETE de l'incident
	 * @param selectedID
	 * @throws Exception 
	 */
	public static void delete(Integer selectedID) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			Incidents inc = (Incidents) session.load(Incidents.class, selectedID);
			
			session.delete(inc);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	/**
	 * COUNT teste si des incidents ont le type pass� en param�tre
	 * @param typeID
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public static boolean hasIncidentType(Integer typeID) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		
		List<Long> results = null;
		try{
			Incidents_Type type = (Incidents_Type)session.load(Incidents_Type.class, typeID);
			results = session.createCriteria(Incidents.class)
				.add(Restrictions.eq("type", type))
				.setProjection(Projections.rowCount())
				.list();
			session.getTransaction().commit();
		}
		catch (Exception e) {
			throw e;
		}
		
		if (results != null && results.size() > 0 && results.get(0) > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * COUNT teste si des incidents ont l'outil pass� en param�tre
	 * @param typeID
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public static boolean hasIncidentOutil(Integer outilID) throws Exception {
		Session session = PilotageSession.getCurrentSession();

		List<Long> results = null;
		try{
			Incidents_Outils outil = (Incidents_Outils)session.load(Incidents_Outils.class, outilID);
			results = session.createCriteria(Incidents.class)
							.add(Restrictions.eq("idOutil", outil))
							.setProjection(Projections.rowCount())
							.list();
			session.getTransaction().commit();
		}
		catch (Exception e) {
			throw e;
		}
		
		if (results != null && results.size() > 0 && results.get(0) > 0) {
			return true;
		} else {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public static List<Incidents> incidentsWithAstreinte(){
		Session session = PilotageSession.getCurrentSession();
		List<Incidents> results = null;
		results = session.createCriteria(Incidents.class).add(Restrictions.eq("HasAstreinte", 1)).list();
		session.getTransaction().commit();
		return results;
	}
	
	/**
	 * INSERT d'un nouvel incident
	 * @param action
	 * @param applicatifs
	 * @param numeroARS
	 * @param astreintes
	 * @param coupure
	 * @param environnementID
	 * @param dateDebut
	 * @param dateDebutGMT
	 * @param dateFin
	 * @param dateFinGMT
	 * @param outilID
	 * @param machineID
	 * @param observation
	 * @param oceorID
	 * @param services
	 * @param typeIncidentID
	 * @param user
	 * @throws Exception
	 */
	public static Integer create(String action, String applicatifs, 
							String numeroARS,/* String astreintes,*/ Integer coupure, Integer resoluPil,
							Integer environnementID, String hardsoft, Date dateDebut,
							Date dateDebutGMT, Date dateFin, Date dateFinGMT,
							String appli_ordo, String job, String reprise,
							Integer outilID, Integer machineID, String observation,
							Integer oceorID, String services, Integer typeIncidentID,
							Users user) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			//chargement des liens
			Environnement environnement = (Environnement)session.load(Environnement.class, environnementID);
			Incidents_Outils outil = outilID == null || outilID < 0 ? null : (Incidents_Outils)session.load(Incidents_Outils.class, outilID);
			Incidents_Type type = (Incidents_Type)session.load(Incidents_Type.class, typeIncidentID);
			Machines_Liste machine = (Machines_Liste)session.load(Machines_Liste.class, machineID);
			Heures_Oceor oceor = (Heures_Oceor)session.load(Heures_Oceor.class, oceorID == null ? PilotageConstants.DEFAULT_OCEOR : oceorID);
			//cr�ation de l'incident
			Incidents inc = new Incidents();
			
			inc.setAction(action);
			inc.setObservation(observation);
			inc.setArs(numeroARS);
			inc.setCoupure(coupure);
			inc.setResoluPil(resoluPil);
			inc.setIdOutil(outil);
			inc.setType(type);
			inc.setDateDebut(dateDebut);
			inc.setDateDebutGmt(dateDebutGMT);
			inc.setDateFin(dateFin);
			inc.setDateFinGmt(dateFinGMT);
			inc.setAppli_ordonnanceur(appli_ordo);
			inc.setJob(job);
			inc.setOceor(oceor);
			inc.setUser(user);
			inc.setAstreinte("");
			inc.setHasAstreinte(0);
			inc.setMachine(machine);
			inc.setApplicatif(applicatifs);
			inc.setDomaine(environnement);
			inc.setService(services);
			inc.setHard_software(hardsoft);
			
			//sauvegarde
			session.save(inc);
			session.getTransaction().commit();
			
			return inc.getId();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}

	/**
	 * UPDATE d'un incident
	 * @param id
	 * @param action
	 * @param applicatifs
	 * @param numeroARS
	 * @param astreintes
	 * @param coupure
	 * @param environnementID
	 * @param dateDebut
	 * @param dateDebutGMT
	 * @param dateFin
	 * @param dateFinGMT
	 * @param outilID
	 * @param machineID
	 * @param observation
	 * @param oceorID
	 * @param services
	 * @param typeIncidentID
	 * @param user
	 * @throws Exception
	 */
	public static void modify(Integer id, String action, String applicatifs,
							String numeroARS, String astreintes, Integer coupure, Integer resoluPil,
							Integer environnementID, String hardsoft, Date dateDebut,
							Date dateDebutGMT, Date dateFin, Date dateFinGMT,
							String appli_ordonnanceur, String job, String reprise,
							Integer outilID, Integer machineID, String observation,
							Integer oceorID, String services, Integer typeIncidentID,
							Users user) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			//chargement des liens
			Environnement environnement = (Environnement)session.load(Environnement.class, environnementID);
			Incidents_Outils outil = outilID == null || outilID < 0 ? null : (Incidents_Outils)session.load(Incidents_Outils.class, outilID);
			Incidents_Type type = (Incidents_Type)session.load(Incidents_Type.class, typeIncidentID);
			Machines_Liste machine = (Machines_Liste)session.load(Machines_Liste.class, machineID);
			Heures_Oceor oceor = (Heures_Oceor)session.load(Heures_Oceor.class, oceorID == null ? PilotageConstants.DEFAULT_OCEOR : oceorID);
			
			//cr�ation de l'incident
			Incidents inc = (Incidents) session.load(Incidents.class, id);
			
			inc.setAction(action);
			inc.setObservation(observation);
			inc.setArs(numeroARS);
			inc.setCoupure(coupure);
			inc.setResoluPil(resoluPil);
			inc.setIdOutil(outil);
			inc.setType(type);
			inc.setDateDebut(dateDebut);
			inc.setDateDebutGmt(dateDebutGMT);
			inc.setDateFin(dateFin);
			inc.setDateFinGmt(dateFinGMT);
			inc.setAppli_ordonnanceur(appli_ordonnanceur);
			inc.setJob(job);
			inc.setOceor(oceor);
			inc.setUser(user);
			inc.setHard_software(hardsoft);
			
			if (astreintes != null)
				inc.setAstreinte(astreintes);
			else 
				inc.setAstreinte("");
			inc.setMachine(machine);
			inc.setApplicatif(applicatifs);
			inc.setDomaine(environnement);
			inc.setService(services);
			
			//sauvegarde
			session.update(inc);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}

	public static void updateAstreinte(Integer selectedID, Integer hasAstreinte) throws Exception{
		Session session = PilotageSession.getCurrentSession();
		try{
			Incidents inc = (Incidents) session.load(Incidents.class, selectedID);
			inc.setHasAstreinte(hasAstreinte);
			session.getTransaction().commit();
		}catch(Exception e){
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	/**
	 * UPDATE met en place la date de fin --> cloture incident
	 * @param selectedID
	 * @param dateFin
	 * @param dateFinGMT
	 * @throws Exception 
	 */
	public static void updateDateFin(Integer selectedID, Date dateFin, Date dateFinGMT) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			Incidents inc = (Incidents) session.load(Incidents.class, selectedID);
			inc.setDateFin(dateFin);
			inc.setDateFinGmt(dateFinGMT);
			
			session.update(inc);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	/**
	 * COUNT teste si des incidents ont l'astreinte pass� en param�tre
	 * @param astreinteID
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public static boolean hasIncidentWithAstreinte(Integer astreinteID) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		
		List<Long> results = null;
		try{
			results = session.createCriteria(Incidents.class)
				//WHERE astreinte = 'astreinteID' 
				//OR	astreinte LIKE 'astreinteID;%'
				//OR	astreinte LIKE '%;astreinteID'
				//OR	astreinte LIKE '%;astreinteID;%'
				.add(
						Restrictions.or(
								Restrictions.eq("astreinte", astreinteID.toString()),
								Restrictions.or(
										Restrictions.like("astreinte", astreinteID + ";%"),
										Restrictions.or(
												Restrictions.like("astreinte", "%;" + astreinteID),
												Restrictions.like("astreinte", "%;" + astreinteID + ";%")
										)
								)
						)
				)
				.setProjection(Projections.rowCount())
				.list();
			session.getTransaction().commit();
		}
		catch (Exception e) {
			throw e;
		}
		
		if (results != null && results.size() > 0 && results.get(0) > 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * COUNT teste si des incidents ont la machine pass�e en param�tre
	 * @param machineID
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public static boolean hasIncidentWithMachine(Integer machineID) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		
		List<Long> results = null;
		Machines_Liste machine = (Machines_Liste)session.load(Machines_Liste.class, machineID);
		results = session.createCriteria(Incidents.class)
					.add(Restrictions.eq("machine", machine))
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
	 * COUNT test si un incident r�f�rence un environnement
	 * @param envID
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static boolean hasIncidentWithEnvironnement(Integer envID)throws Exception{
		Session session = PilotageSession.getCurrentSession();
		
		Environnement environnement = (Environnement) session.load(Environnement.class, envID);
		List<Long> results = session.createCriteria(Incidents.class)
						          .add(Restrictions.eq("domaine", environnement))
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
	
	/**
	 * COUNT test si un incident r�f�rence un environnement
	 * @param envID
	 * @return
	 * @throws Exception
	 */
	public static Integer hasIncidentEnCoursWithEnvironnement(Environnement environnement)throws Exception{
		Session session = PilotageSession.getCurrentSession();
		
		Integer incident = (Integer) session.createCriteria(Incidents.class)
						          .add(Restrictions.eq("domaine", environnement))
						          .add(Restrictions.isNull("dateFin"))
						          .createAlias("type", "typeInc")
						          .setProjection(Projections.min("typeInc.impact"))
						          .uniqueResult();
		session.getTransaction().commit();
		return incident;
	}
	
	/**
	 * COUNT test si un incident r�f�rence un environnement
	 * @param envID
	 * @return
	 * @throws Exception
	 */
	public static Integer hasIncidentResoluWithEnvironnement(Environnement environnement, Date date)throws Exception{
		Session session = PilotageSession.getCurrentSession();
		
		Integer incident = (Integer) session.createCriteria(Incidents.class)
						          .add(Restrictions.eq("domaine", environnement))
						          .add(Restrictions.ge("dateFin",date))
						          .createAlias("type", "typeInc")
						          .setProjection(Projections.min("typeInc.impact"))
						          .uniqueResult();
		session.getTransaction().commit();
		return incident;
	}
	
	/**
	 * COUNT test si un incident r�f�rence un service
	 * @param serviceID
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static boolean hasIncidentWithService(Integer serviceID) {
		Session session = PilotageSession.getCurrentSession();
		
		List<Long> results = session.createCriteria(Incidents.class)
						          .add(
						        		  Restrictions.or( 
						        				  Restrictions.eq("service", serviceID.toString()), 
						        				  Restrictions.or(
						        						  Restrictions.like("service", serviceID + ";%"), 
						        						  Restrictions.or(
						        								  Restrictions.like("service", "%;" + serviceID + ";%"), 
						        								  Restrictions.like("service", "%;" + serviceID)))))
						        		  
						        		 
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
	

	/**
	 * SELECT de tous les incidents non finis � la date s�lectionn�e ou termin� � cette date ci, pour la liste des environnements concern�s
	 * @param selectedDate
	 * @param listEnvironnementID 
	 * @return
	 * @throws  
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public static List<Incidents> getJustFinishedOrUnfinished(Date selectedDate, Integer[] listEnvironnementID) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		
		Criteria criteria = session.createCriteria(Incidents.class)
									.add(Restrictions.or(
											Restrictions.between("dateFin", DateService.getDateEarlier(selectedDate), DateService.getDateLater(selectedDate)),
											Restrictions.or(
													Restrictions.isNull("dateFin"), 
													Restrictions.le("dateFin", new SimpleDateFormat("dd-MM-yyyy HH:mm").parse("01-01-1970 00:00")))));
		addEnvRestrictionToIncidentCriteria(session, criteria, listEnvironnementID);
		
		List<Incidents> listIncidents = criteria.list();
		session.getTransaction().commit();										
		return listIncidents;
	}
	
	/**
	 * SELECT de tous les incidents de la date en param�tre, pour la liste des environnements concern�s
	 * @param selectedDate
	 * @param listEnvironnementID 
	 * @return
	 * @throws  
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public static List<Incidents> getByDate(Date selectedDate, Integer[] listEnvironnementID) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		
		Criteria criteria = session.createCriteria(Incidents.class)
									.add(Restrictions.between("dateDebut", DateService.getDateEarlier(selectedDate), DateService.getDateLater(selectedDate)));
		addEnvRestrictionToIncidentCriteria(session, criteria, listEnvironnementID);
		
		criteria.createAlias("type", "type", Criteria.LEFT_JOIN);
		criteria.addOrder(Order.asc("type.impact"));
		
		List<Incidents> listIncidents = criteria.addOrder(Order.asc("dateDebut")).list();
		session.getTransaction().commit();									
		return listIncidents;
	}
	
	/**
	 * SELECT de tous les incidents dans une periode de date en param�tre, pour la liste des environnements concern�s
	 * @param dateDebut
	 * @param dateFin
	 * @param listEnvironnementID 
	 * @return
	 * @throws  
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public static List<Incidents> getByDatePeriode(Date dateDebut, Date dateFin, Integer[] listEnvironnementID) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		
		Criteria criteria = session.createCriteria(Incidents.class)
									.add(Restrictions.between("dateDebut", DateService.getDateEarlier(dateDebut), DateService.getDateLater(DateService.addDays(dateFin, 1))));
		addEnvRestrictionToIncidentCriteria(session, criteria, listEnvironnementID);
		
		List<Incidents> listIncidents = criteria.addOrder(Order.asc("dateDebut")).list();
		session.getTransaction().commit();									
		return listIncidents;
	}

	/**
	 * COUNT le nombre d'incidents d�butant � la date pr�cis�e (de 7h30 � 7h30), suivant la liste des environnements concern�s.
	 * @param selectedDate
	 * @param listEnvironnementID 
	 * @param object 
	 * @param integers 
	 * @return
	 * @throws  
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public static Integer getNbNew(Date selectedDate, Integer[] listEnvironnementID, Integer[] listTypeIncidentID, Integer[] listTypeEnvironnementID) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		
		Criteria criteria = session.createCriteria(Incidents.class)
								.add(Restrictions.between("dateDebut", DateService.getDateEarlier(selectedDate), DateService.getDateLater(selectedDate)))
								.setProjection(Projections.rowCount());
		
		addEnvRestrictionToIncidentCriteria(session, criteria, listEnvironnementID);
		addTypeIncidentRestrictionToIncidentCriteria(session, criteria, listTypeIncidentID);
		addTypeEnvRestrictionToIncidentCriteria(session, criteria, listTypeEnvironnementID);
		
		List<Long> results = criteria.list();
		session.getTransaction().commit();
		if (results != null && results.size() > 0 && results.get(0) > 0) {
			return results.get(0).intValue();
		}
		else {
			return 0;
		}
	}

	/**
	 * COUNT le nombre d'incident depuis le d�but du mois
	 * @param selectedDate
	 * @param listEnvironnementID 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Integer getNbSinceFirstOfMonth(Date selectedDate, Integer[] listEnvironnementID, Integer[] listTypeIncidentID) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		Date premierJour = DateService.getDateEarlier(DateService.getFirstDayOfMonth(DateService.getDateEarlier(selectedDate)));
		
		Criteria criteria = session.createCriteria(Incidents.class)
									.add(Restrictions.between("dateDebut", premierJour, DateService.getDateLater(selectedDate)))
									.setProjection(Projections.rowCount());
		
		addEnvRestrictionToIncidentCriteria(session, criteria, listEnvironnementID);
		addTypeIncidentRestrictionToIncidentCriteria(session, criteria, listTypeIncidentID);
		
		List<Long> results = criteria.list();
		session.getTransaction().commit();
		if (results != null && results.size() > 0 && results.get(0) > 0) {
			return results.get(0).intValue();
		}
		else {
			return 0;
		}
	}
	
	/**
	 * COUNT le nombre d'incident r�solu � la date pr�cis�e (de 7h30 � 7h30) et suivant la liste des environnements concern�s
	 * @param selectedDate
	 * @param listEnvironnementID 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Integer getNumResoluByDate(Date selectedDate, Integer[] listEnvironnementID, Integer[] listTypeIncidentID) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		
		Criteria criteria = session.createCriteria(Incidents.class)
								.add(Restrictions.between("dateFin", DateService.getDateEarlier(selectedDate), DateService.getDateLater(selectedDate)))
								.setProjection(Projections.rowCount());
		
		addEnvRestrictionToIncidentCriteria(session, criteria, listEnvironnementID);
		addTypeIncidentRestrictionToIncidentCriteria(session, criteria, listTypeIncidentID);
		
		List<Long> results = criteria.list();
		session.getTransaction().commit();
		if (results != null && results.size() > 0 && results.get(0) > 0) {
			return results.get(0).intValue();
		}
		else {
			return 0;
		}
	}

	/**
	 * COUNT le nombre d'appel astreinte des incidents de la date pass�e en param�tre (de 7h30 � 7h30)
	 * @param selectedDate
	 * @param listEnvironnementID 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Integer getNumAstreintesAppel(Date selectedDate, Integer[] listEnvironnementID) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		
		Criteria criteria = session.createCriteria(Incidents.class)
								.setProjection(Projections.property("astreinte"))
								.add(Restrictions.between("dateDebut", DateService.getDateEarlier(selectedDate), DateService.getDateLater(selectedDate)));
		
		addEnvRestrictionToIncidentCriteria(session, criteria, listEnvironnementID);
		
		List<String> resultStr = criteria.list();
		session.getTransaction().commit();
		int result = 0;
		if(resultStr == null || resultStr.size() == 0){
			return result;
		}
		for(String str : resultStr){
			if(str != null && !str.equals("")){
				result += str.split(PilotageConstants.INCIDENT_SEPARATEUR).length;
			}
		}
		return result;
	}

	/**
	 * @param et
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Incidents> getByDateAndType(Date selectedDate, Environnement_Type type) {
		// TODO Auto-generated method stub
		List<Environnement> listEnvironnements = EnvironnementDatabaseService.getByType(type.getId());
		List<Incidents> listIncidents = null;
		try {
			if (listEnvironnements.size() > 0) {
				Session session = PilotageSession.getCurrentSession();
				listIncidents = session.createCriteria(Incidents.class)
						.add(Restrictions.in("domaine", listEnvironnements))
						.add(Restrictions.or(Restrictions.between("dateFin", DateService.getDateEarlier(selectedDate), DateService.getDateLater(selectedDate)),
						Restrictions.or(
								Restrictions.isNull("dateFin"), 
								Restrictions.lt("dateFin", new SimpleDateFormat("dd-MM-yyyy HH:mm").parse("01-01-1970 00:00")))))
								.list();
				session.getTransaction().commit();
			} else {
				listIncidents = new ArrayList<Incidents>();
			}
		}catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			listIncidents = new ArrayList<Incidents>();
		}
		return listIncidents;
	}

	/**
	 * @param selectedDate
	 * @param listEnviron
	 * @return
	 * @throws  
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public static List<Incidents> getByDateAndEnviron(Date selectedDate, List<Environnement> listEnviron) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		List<Incidents> listIncidents = session.createCriteria(Incidents.class)
											.add(Restrictions.or(
													Restrictions.between("dateFin", DateService.getDateEarlier(selectedDate), DateService.getDateLater(selectedDate)),
													Restrictions.or(
																	Restrictions.isNull("dateFin"), 
																	Restrictions.le("dateFin", new SimpleDateFormat("dd-MM-yyyy HH:mm").parse("01-01-1970 00:00")))))
											.add(Restrictions.in("domaine", listEnviron))
											.list();
		session.getTransaction().commit();
		return listIncidents;
	}

	/**
	 * SELECT des incidents par date et service
	 * @param selectedDate
	 * @param serviceID
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Incidents> getByDateAndService(Date selectedDate, int serviceID) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		List<Incidents> listIncidents = session.createCriteria(Incidents.class)
										.add(Restrictions.between("dateDebut", 
												DateService.getDateEarlier(selectedDate), 
												DateService.getDateLater(selectedDate)))
										.add(Restrictions.or(
												//serviceID seul dans la liste
												Restrictions.eq("service", Integer.toString(serviceID)),
												Restrictions.or(
														//serviceID en d�but de liste
														Restrictions.like("service", Integer.toString(serviceID) + ";%"),
														Restrictions.or(
																//serviceID en milieu de liste
																Restrictions.like("service", "%;" + Integer.toString(serviceID) + ";%"),
																//serviceID en fin de liste
																Restrictions.like("service", "%;" + Integer.toString(serviceID))
														)
												)
										))
										.list();
		session.getTransaction().commit();
		return listIncidents;
	}
	
	/**
	 * Ajoute la condition sur les environnements � un criteria d'incident
	 * @param session
	 * @param criteria
	 * @param listEnvironnementID
	 */
	private static void addEnvRestrictionToIncidentCriteria(Session session, Criteria criteria, Integer[] listEnvironnementID){
		if(listEnvironnementID != null){
			List<Environnement> listEnv = new ArrayList<Environnement>();
			for(Integer envID : listEnvironnementID){
				if(envID != null)
					listEnv.add((Environnement)session.load(Environnement.class, envID));
			}
			criteria.add(Restrictions.in("domaine", listEnv));
		}
	}
	
	/**
	 * Ajoute la condition sur les types d'environnements � un criteria d'incident
	 * @param session
	 * @param criteria
	 * @param listTypeEnvironnementID
	 */
	private static void addTypeEnvRestrictionToIncidentCriteria(Session session, Criteria criteria, Integer[] listTypeEnvironnementID) {
		if(listTypeEnvironnementID != null){
			List<Environnement_Type> listEnvType = new ArrayList<Environnement_Type>();
			for(Integer envTypeID : listTypeEnvironnementID){
				if(envTypeID != null)
					listEnvType.add((Environnement_Type)session.load(Environnement_Type.class, envTypeID));
			}
			criteria.createAlias("domaine", "dom", Criteria.LEFT_JOIN);
			criteria.add(Restrictions.in("dom.type", listEnvType));
		}
	}

	/**
	 * Ajoute la condition sur les types d'incident � un criteria d'incident
	 * @param session
	 * @param criteria
	 * @param listTypeIncidentID
	 */
	private static void addTypeIncidentRestrictionToIncidentCriteria(Session session, Criteria criteria, Integer[] listTypeIncidentID) {
		if(listTypeIncidentID != null){
			List<Incidents_Type> listIncidentType = new ArrayList<Incidents_Type>();
			for(Integer incidentTypeID : listTypeIncidentID){
				if(incidentTypeID != null)
					listIncidentType.add((Incidents_Type)session.load(Incidents_Type.class, incidentTypeID));
			}
			criteria.add(Restrictions.in("type", listIncidentType));
		}
	}

	/**
	 * SELECT de tous les incidents du mois
	 * @param selectMois
	 * @return
	 * @throws Exception 
	 */
	public static List<Incidents> getAllIncidentsMois(Date selectMois) throws Exception {
		return getAllIncidentsPeriode(DateService.getMonthEarlier(selectMois), DateService.getMonthLater(selectMois));
	}
	
	/**
	 * SELECT de tous les incidents de la p�riode
	 * @param debut
	 * @param fin
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static List<Incidents> getAllIncidentsPeriode(Date debut, Date fin) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			List<Incidents> result = session.createCriteria(Incidents.class)
										.add(Restrictions.between("dateDebut", debut, fin))
										.list();
			session.getTransaction().commit();
			return result;
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	/**
	 * SELECT des incidents suivant le filtre
	 * @param pagination
	 * @param filtrePilote
	 * @param filtreDate
	 * @param filtreServeur
	 * @param filtreApplicatif
	 * @param filtreEnvironnement
	 * @param filtreCoupureService
	 * @param filtreObservation
	 * @param filtreArs
	 * @return
	 */
	public static List<Incidents> getAllByFiltre(Pagination<Incidents> pagination, Integer filtrePilote, Date filtreDate, Date filtreDateFin, Integer filtreServeur, Integer filtreApplicatif, Integer filtreEnvironnement, Integer filtreType, Integer filtreCoupureService, Integer filtreResoluPilotage, Integer filtreHasAstreinte, String filtreObservation,String filtreArs) {
		Session session = PilotageSession.getCurrentSession();
		Criteria criteria = session.createCriteria(Incidents.class).addOrder(Order.desc("dateDebut"));
		
		if (filtrePilote != null && filtrePilote != -1) {
			Users user = (Users)session.load(Users.class, filtrePilote);
			criteria.add(Restrictions.eq("user", user));
		}
		if (filtreDate != null && !filtreDate.equals("")) {
			if(filtreDateFin != null && !filtreDateFin.equals("")){
			    criteria.add(Restrictions.between("dateDebut", filtreDate, DateService.addDays(filtreDateFin,1)));
			}
		}
		if (filtreDate != null && !"".equals(filtreDate)){
			if(filtreDateFin == null){
				criteria.add(Restrictions.between("dateDebut",filtreDate,DateService.addDays(filtreDate,1)));
			}
		}
		if (filtreServeur != null && filtreServeur != -1)  {
			Machines_Liste machine = (Machines_Liste)session.load(Machines_Liste.class, filtreServeur);
			criteria.add(Restrictions.eq("machine", machine));
		}
		if (filtreApplicatif != null && filtreApplicatif != -1) {
			criteria.add(Restrictions.or(
				Restrictions.eq("applicatif", String.valueOf(filtreApplicatif)),
				Restrictions.or(
					Restrictions.like("applicatif", "%;" + String.valueOf(filtreApplicatif) + ";%"),
					Restrictions.or(
						Restrictions.like("applicatif", "%;" + String.valueOf(filtreApplicatif)),
						Restrictions.like("applicatif", String.valueOf(filtreApplicatif) + ";%")))));
		}
		if (filtreEnvironnement != null && filtreEnvironnement != -1) {
			Environnement environnement = (Environnement)session.load(Environnement.class, filtreEnvironnement);
			criteria.add(Restrictions.eq("domaine", environnement));
		}
		if (filtreType != null && filtreType != -1) {
			Incidents_Type itype = (Incidents_Type)session.load(Incidents_Type.class, filtreType);
			criteria.add(Restrictions.eq("type", itype));
		}
		if (filtreCoupureService != null && filtreCoupureService != -1) {
			criteria.add(Restrictions.eq("coupure", filtreCoupureService));
		}
		if (filtreResoluPilotage != null && filtreResoluPilotage != -1) {
			criteria.add(Restrictions.eq("resoluPil", filtreResoluPilotage));
		}
		if(filtreHasAstreinte != null && filtreHasAstreinte != -1){
			criteria.add(Restrictions.eq("hasAstreinte", filtreHasAstreinte));
		}
		if (filtreObservation != null && !"".equals(filtreObservation)) {
			criteria.add(Restrictions.like("observation", "%" + filtreObservation + "%"));
		}
		if (filtreArs != null && !"".equals(filtreArs)) {
			criteria.add(Restrictions.like("ars", "%" + filtreArs + "%"));
		}
		List<Incidents> listIncidents = pagination.setCriteriaPagination(criteria);
		session.getTransaction().commit();
		return listIncidents;
	}
	
	/**
	 * UPDATE d'un incident pour simuler une r�ouverture
	 * @param selectedID
	 * @throws Exception
	 */
	public static void reinitialiserIncident(Integer selectedID) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			Incidents inc = (Incidents) session.load(Incidents.class, selectedID);
			inc.setDateFin(null);
			inc.setResoluPil(null);
			inc.setDateFinGmt(null);
			
			session.update(inc);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
}
