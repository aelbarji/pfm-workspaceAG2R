package pilotage.database.statistiques;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import pilotage.database.applicatif.ApplicatifDatabaseService;
import pilotage.database.environnement.EnvironnementDatabaseService;
import pilotage.database.environnement.EnvironnementTypeDatabaseService;
import pilotage.database.services.ServicesDatabaseService;
import pilotage.metier.Applicatifs_Liste;
import pilotage.metier.Environnement;
import pilotage.metier.Environnement_Type;
import pilotage.metier.Incidents;
import pilotage.metier.Incidents_Type;
import pilotage.metier.Machines_Liste;
import pilotage.metier.Services_Liste;
import pilotage.service.constants.PilotageConstants;
import pilotage.service.date.DateService;
import pilotage.session.PilotageSession;

public class StatistiqueIncidentDatabaseService {

	/**
	 * COUNT le nombre d'incident du jour entre 7:30 et 7:30
	 * @param selectedDate
	 * @return
	 */
	public static Long getIncidentDeJour(Date selectedDate) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		Long results = (Long) session.createCriteria(Incidents.class)
							.add(Restrictions.between("dateDebut",
									DateService.getDateEarlier(selectedDate),
									DateService.getDateLater(selectedDate)))
							.setProjection(Projections.rowCount())
							.uniqueResult();
		
		session.getTransaction().commit();
		return results;
	}

	/**
	 * COUNT le nombre d'incidents de la semaine
	 * @param selectedDate
	 * @return
	 */
	public static Long getIncidentDeSemaine(Date selectedDate) throws Exception{
		Session session = PilotageSession.getCurrentSession();
		Long results = (Long) session.createCriteria(Incidents.class)
							.add(Restrictions.between("dateDebut",
									DateService.getDateEarlier(DateService.getWeekStart(selectedDate)),
									DateService.getDateLater(DateService.getWeekEnd(selectedDate))))
							.setProjection(Projections.rowCount())
							.uniqueResult();
		
		session.getTransaction().commit();
							
		return results;
	}

	/**
	 * COUNT du nombre d'incident du mois entre 7h30 et 7h30
	 * @param selectedDate
	 * @param critique
	 * @param coupure
	 * @return
	 * @throws ParseException 
	 */
	public static Long getIncidentDeMois(Date selectedDate, Boolean critique, Boolean coupure,Integer type_incident) throws ParseException {
		return getIncidentDePeriode(DateService.getMonthEarlier(selectedDate), DateService.getMonthLater(selectedDate), critique, coupure,type_incident);
	}
	
	public static Long getIncidentDeSemaine(Date debut, Date fin, Boolean critique, Boolean coupure, Integer type_incident) throws ParseException {

		Calendar calDeb = Calendar.getInstance();
		calDeb.setTime(debut);
		calDeb.set(Calendar.HOUR_OF_DAY, 7);
		calDeb.set(Calendar.MINUTE, 30);
		calDeb.set(Calendar.SECOND, 0);
		debut = calDeb.getTime();
		
		fin = DateService.addDays(fin, 1);
		Calendar calFin = Calendar.getInstance();
		calFin.setTime(fin);
		calFin.set(Calendar.HOUR_OF_DAY, 7);
		calFin.set(Calendar.MINUTE, 29);
		calFin.set(Calendar.SECOND, 59);
		fin = calFin.getTime();
		
		return getIncidentDePeriode(debut, fin, critique, coupure,type_incident);
	}

	/**
	 * COUNT du nombre d'incident du période
	 * @param debut
	 * @param fin
	 * @param critique
	 * @param coupure
	 * @return
	 */
	public static Long getIncidentDePeriode(Date debut, Date fin, Boolean critique, Boolean coupure, Integer type_incident) {
		Session session = PilotageSession.getCurrentSession();
		Long results = (long) 0;
		try {			
			Criteria criteria = session.createCriteria(Incidents.class)
										.add(Restrictions.between("dateDebut", debut, fin))
										.setProjection(Projections.rowCount());
			if(critique != null){
				Incidents_Type type = (Incidents_Type)session.load(Incidents_Type.class, type_incident);
				if(critique){
					criteria.add(Restrictions.eq("type", type));
				}
				else{
					criteria.add(Restrictions.ne("type", type));
				}
			}
			
			if(coupure != null){
				if(coupure)
					criteria.add(Restrictions.eq("coupure", 1));
				else
					criteria.add(Restrictions.eq("coupure", 0));
			}
			
			results = (Long)criteria.uniqueResult();
		}
		catch (HibernateException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		session.getTransaction().commit();
		return results;
	}
	
	/**
	 * COUNT du nombre d'incident resolu par le pilotage du mois entre 7h30 et 7h30
	 * @param selectedDate
	 * @param critique
	 * @param coupure
	 * @return
	 * @throws ParseException 
	 */
	public static Long getIncidentDeMoisResolu(Date selectedDate, Boolean resoluPilotage) throws ParseException {
		return getIncidentDePeriodeResolu(DateService.getMonthEarlier(selectedDate), DateService.getMonthLater(selectedDate), resoluPilotage);
	}
	
	
	public static Long getIncidentDeSemaineResolu(Date debut, Date fin, Boolean resoluPilotage) throws ParseException {
		
		Calendar calDeb = Calendar.getInstance();
		calDeb.setTime(debut);
		calDeb.set(Calendar.HOUR_OF_DAY, 7);
		calDeb.set(Calendar.MINUTE, 30);
		calDeb.set(Calendar.SECOND, 0);
		debut = calDeb.getTime();
		
		fin = DateService.addDays(fin, 1);
		Calendar calFin = Calendar.getInstance();
		calFin.setTime(fin);
		calFin.set(Calendar.HOUR_OF_DAY, 7);
		calFin.set(Calendar.MINUTE, 29);
		calFin.set(Calendar.SECOND, 59);
		fin = calFin.getTime();
		
		return getIncidentDePeriodeResolu(debut, fin, resoluPilotage);
	}
	
	/**
	 * COUNT du nombre d'incident du période
	 * @param debut
	 * @param fin
	 * @param resoluPilotage
	 * @return
	 */
	public static Long getIncidentDePeriodeResolu(Date debut, Date fin, Boolean resoluPilotage) {
		Session session = PilotageSession.getCurrentSession();
		Long results = (long) 0;
		try {
			Criteria criteria = session.createCriteria(Incidents.class)
										.add(Restrictions.between("dateDebut", debut, fin))
										.setProjection(Projections.rowCount());
			
			if(resoluPilotage != null){
				if(resoluPilotage)
					criteria.add(Restrictions.eq("resoluPil", 1));
				else
					criteria.add(Restrictions.eq("resoluPil", 0));
			}
			
			results = (Long)criteria.uniqueResult();
		}
		catch (HibernateException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		session.getTransaction().commit();
		return results;
	}

	/**
	 * COUNT le nombre d'incident des 30 jours avant (ou égale) la date
	 * @param selectedDate
	 * @return
	 */
	public static Long getIncidentDeDernier30Jour(Date selectedDate) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		Long results = (Long) session.createCriteria(Incidents.class)
								.add(Restrictions.between("dateDebut",
										DateService.addDays(selectedDate, -30), 
										DateService.getDateLater(selectedDate)))
								.setProjection(Projections.rowCount()).uniqueResult();
		session.getTransaction().commit();
		return results;
	}
	
	public static List<Incidents> getIncidentDeMoisCoupure(Date selectedDate, Boolean coupure) throws ParseException {
		return getIncidentDePeriodeCoupure(DateService.getMonthEarlier(selectedDate), DateService.getMonthLater(selectedDate), coupure);
	}
	
	/**
	 * COUNT du nombre d'incident du période
	 * @param debut
	 * @param fin
	 * @param coupure
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Incidents> getIncidentDePeriodeCoupure(Date debut, Date fin, Boolean coupure) {
		Session session = PilotageSession.getCurrentSession();
		List<Incidents> results = null;
		try {
			Criteria criteria = session.createCriteria(Incidents.class)
										.add(Restrictions.between("dateDebut", debut, fin));
			
			if(coupure != null){
				if(coupure)
					criteria.add(Restrictions.eq("coupure", 1));
				else
					criteria.add(Restrictions.eq("coupure", 0));
			}
			results = (List<Incidents>)criteria.list();
		}
		catch (HibernateException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		session.getTransaction().commit();
		return results;
	}

	/**
	 * COUNT le nombre d'incidents de production du jour
	 * @param selectedDate
	 * @return
	 */
	public static Long getIncidentDeJourPro(Date selectedDate) throws Exception{
		Environnement_Type enviType = EnvironnementTypeDatabaseService.getPrincipal();
		List<Environnement> listEnvi = EnvironnementDatabaseService.getByType(enviType.getId());
//		List<Environnement> listEnvi = EnvironnementDatabaseService.getByType(PilotageConstants.BILAN_INCIDENT_DE_PRODUCTION);
		Session session = PilotageSession.getCurrentSession();

		Long results = (Long) session.createCriteria(Incidents.class)
									.add(Restrictions.between("dateDebut",
											DateService.getDateEarlier(selectedDate),
											DateService.getDateLater(selectedDate)))
									.add(Restrictions.in("domaine", listEnvi))
									.setProjection(Projections.rowCount())
									.uniqueResult();
		session.getTransaction().commit();
		return results;
	}

	/**
	 * COUNT du nombre d'incident de production de la semaine
	 * @param selectedDate
	 * @return
	 */
	public static Long getIncidentDeSemainePro(Date selectedDate) throws Exception {
		Environnement_Type enviType = EnvironnementTypeDatabaseService.getPrincipal();
		List<Environnement> listEnvi = EnvironnementDatabaseService.getByType(enviType.getId());
//		List<Environnement> listEnvi = EnvironnementDatabaseService.getByType(PilotageConstants.BILAN_INCIDENT_DE_PRODUCTION);
		Session session = PilotageSession.getCurrentSession();
		Long results = (Long) session.createCriteria(Incidents.class)
								.add(Restrictions.between("dateDebut",
										DateService.getDateEarlier(DateService.getWeekStart(selectedDate)),
										DateService.getDateLater(DateService.getWeekEnd(selectedDate))))
								.add(Restrictions.in("domaine", listEnvi))
								.setProjection(Projections.rowCount())
								.uniqueResult();
		session.getTransaction().commit();
		return results;
	}

	/**
	 * COUNT du nombre d'incident de production du mois
	 * @param selectedDate
	 * @return
	 */
	public static Long getIncidentDeMoisPro(Date selectedDate) throws Exception{
		Environnement_Type enviType = EnvironnementTypeDatabaseService.getPrincipal();
		List<Environnement> listEnvi = EnvironnementDatabaseService.getByType(enviType.getId());
//		List<Environnement> listEnvi = EnvironnementDatabaseService.getByType(PilotageConstants.BILAN_INCIDENT_DE_PRODUCTION);
		Session session = PilotageSession.getCurrentSession();
		Long results = (Long) session.createCriteria(Incidents.class)
								.add(Restrictions.between("dateDebut",
										DateService.getMonthEarlier(selectedDate),
										DateService.getMonthLater(selectedDate)))
								.add(Restrictions.in("domaine", listEnvi))
								.setProjection(Projections.rowCount()).uniqueResult();
		
		session.getTransaction().commit();
		return results;
	}

	/**
	 * COUNT du nombre d'incidents du jour des environnements hors production
	 * @param selectedDate
	 * @return
	 */
	public static Long getIncidentDeJourAutre(Date selectedDate) throws Exception {
		Environnement_Type enviType = EnvironnementTypeDatabaseService.getPrincipal();
		List<Environnement> listEnvi = EnvironnementDatabaseService.getByType(enviType.getId());
//		List<Environnement> listEnvi = EnvironnementDatabaseService.getByType(PilotageConstants.BILAN_INCIDENT_DE_PRODUCTION);
		Session session = PilotageSession.getCurrentSession();
		Long results = (Long) session.createCriteria(Incidents.class)
								.add(Restrictions.between("dateDebut",
										DateService.getDateEarlier(selectedDate),
										DateService.getDateLater(selectedDate)))
								.add(Restrictions.not(Restrictions.in("domaine", listEnvi)))
								.setProjection(Projections.rowCount()).uniqueResult();
		
		session.getTransaction().commit();
		return results;
	}

	/**
	 * COUNT du nombre d'incident de la semaine hors production
	 * @param selectedDate
	 * @return
	 */
	public static Long getIncidentDeSemaineAutre(Date selectedDate) throws Exception {
		Environnement_Type enviType = EnvironnementTypeDatabaseService.getPrincipal();
		List<Environnement> listEnvi = EnvironnementDatabaseService.getByType(enviType.getId());
//		List<Environnement> listEnvi = EnvironnementDatabaseService.getByType(PilotageConstants.BILAN_INCIDENT_DE_PRODUCTION);
		Session session = PilotageSession.getCurrentSession();
		Long results = (Long) session.createCriteria(Incidents.class)
									.add(Restrictions.between("dateDebut",
											DateService.getDateEarlier(DateService.getWeekStart(selectedDate)),
											DateService.getDateLater(DateService.getWeekEnd(selectedDate))))
									.add(Restrictions.not(Restrictions.in("domaine", listEnvi)))
									.setProjection(Projections.rowCount()).uniqueResult();
		
		session.getTransaction().commit();
		return results;
	}

	/**
	 * COUNT le nombre d'incident du mois hors production
	 * @param selectedDate
	 * @return
	 */
	public static Long getIncidentDeMoisAutre(Date selectedDate) throws Exception {
		Environnement_Type enviType = EnvironnementTypeDatabaseService.getPrincipal();
		List<Environnement> listEnvi = EnvironnementDatabaseService.getByType(enviType.getId());
//		List<Environnement> listEnvi = EnvironnementDatabaseService.getByType(PilotageConstants.BILAN_INCIDENT_DE_PRODUCTION);
		Session session = PilotageSession.getCurrentSession();
		Long results = (Long) session.createCriteria(Incidents.class)
									.add(Restrictions.between("dateDebut",
											DateService.getMonthEarlier(selectedDate),
											DateService.getMonthLater(selectedDate)))
									.add(Restrictions.not(Restrictions.in("domaine", listEnvi)))
									.setProjection(Projections.rowCount()).uniqueResult();
		
		session.getTransaction().commit();
		return results;
	}


	/**
	 * SELECt des machines des incidents des 30 derniers jours
	 * @param selectedDate
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Machines_Liste> getTopFiveServerDer30J(Date selectedDate) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		List<Machines_Liste> listMachines = session.createCriteria(Incidents.class)
								.add(Restrictions.between("dateDebut",
										DateService.addDays(selectedDate, -30), 
										DateService.getDateLater(selectedDate)))
								.setProjection(Projections.property("machine")).list();
		session.getTransaction().commit();
		return listMachines;
	}

	/**
	 * SELECT des applications des incidents des 30 derniers jours
	 * @param selectedDate
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Applicatifs_Liste> getTopFiveAppDer30J(Date selectedDate) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		List<String> listAppStr = session.createCriteria(Incidents.class)
									.add(Restrictions.between("dateDebut",
											DateService.addDays(selectedDate, -30), 
											DateService.getDateLater(selectedDate)))
									.setProjection(Projections.property("applicatif"))
									.list();
		
		session.getTransaction().commit();
		
		List<Applicatifs_Liste> listApp = new ArrayList<Applicatifs_Liste>();
		for (String appStr : listAppStr) {
			String[] apps = appStr.split(";");
			if (apps.length > 0) {
				for (int i = 0; i < apps.length; i++) {
					if (!apps[i].equals("")) {
						listApp.add(ApplicatifDatabaseService.get(Integer.parseInt(apps[i])));
					}
				}
			}
		}
		return listApp;
	}

	/**
	 * SELECT des services des incidents des 30 derniers jours
	 * @param selectedDate
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Services_Liste> getTopFiveServiceDer30J(Date selectedDate) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		List<String> listServiceStr = session.createCriteria(Incidents.class)
										.add(Restrictions.between("dateDebut",
												DateService.addDays(selectedDate, -30), 
												DateService.getDateLater(selectedDate)))
										.setProjection(Projections.property("service"))
										.list();
		
		session.getTransaction().commit();
		
		List<Services_Liste> listService = new ArrayList<Services_Liste>();
		for (String serviceStr : listServiceStr) {
			String[] sers = serviceStr
					.split(";");
			if (sers.length > 0) {
				for (int i = 0; i < sers.length; i++) {
					if (!sers[i].equals("")) {
						listService.add(ServicesDatabaseService.get(Integer.parseInt(sers[i])));
					}
				}
			}
		}
		return listService;
	}

	/**
	 * SELECT des domaines des incidents des 30 derniers jours
	 * @param selectedDate
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Environnement> getTopFiveDomaineDer30J(Date selectedDate) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		List<Environnement> listEnviro = session.createCriteria(Incidents.class)
											.add(Restrictions.between("dateDebut",
													DateService.addDays(selectedDate, -30), 
													DateService.getDateLater(selectedDate)))
											.setProjection(Projections.property("domaine")).list();
		session.getTransaction().commit();
		return listEnviro;
	}

	/**
	 * SELECT des machines des incidents du mois
	 * @param selectedDate
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static List<Machines_Liste> getTopFiveServerDeMois(Date selectedDate) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		List<Machines_Liste> listMachines = session.createCriteria(Incidents.class)
												.add(Restrictions.between("dateDebut",
														DateService.getMonthEarlier(selectedDate),
														DateService.getMonthLater(selectedDate)))
												.setProjection(Projections.property("machine"))
												.list();
		session.getTransaction().commit();
		return listMachines;
	}

	/**
	 * SELECT des applications des incidents du mois
	 * @param selectedDate
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static List<Applicatifs_Liste> getTopFiveAppDeMois(Date selectedDate) throws Exception{
		Session session = PilotageSession.getCurrentSession();
		List<String> listAppStr = session.createCriteria(Incidents.class)
										.add(Restrictions.between("dateDebut",
												DateService.getMonthEarlier(selectedDate),
												DateService.getMonthLater(selectedDate)))
										.setProjection(Projections.property("applicatif")).list();
		
		session.getTransaction().commit();
		
		List<Applicatifs_Liste> listApp = new ArrayList<Applicatifs_Liste>();
		for (String appStr : listAppStr) {
			String[] apps = appStr.split(";");
			if (apps.length > 0) {
				for (int i = 0; i < apps.length; i++) {
					if (!apps[i].equals("")) {
						listApp.add(ApplicatifDatabaseService.get(Integer.parseInt(apps[i])));
					}
				}
			}
		}
		return listApp;
	}

	/**
	 * SELECT des services des incidents du mois
	 * @param selectedDate
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static List<Services_Liste> getTopFiveServiceDeMois(Date selectedDate) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		List<String> listServiceStr = session.createCriteria(Incidents.class)
											.add(Restrictions.between("dateDebut",
													DateService.getMonthEarlier(selectedDate),
													DateService.getMonthLater(selectedDate)))
											.setProjection(Projections.property("service"))
											.list();
		session.getTransaction().commit();
		
		List<Services_Liste> listService = new ArrayList<Services_Liste>();
		for (String serviceStr : listServiceStr) {
			String[] sers = serviceStr
					.split(";");
			if (sers.length > 0) {
				for (int i = 0; i < sers.length; i++) {
					if (!sers[i].equals("")) {
						listService.add(ServicesDatabaseService.get(Integer
								.parseInt(sers[i])));
					}
				}
			}
		}
		return listService;
	}

	/**
	 * SELECT des domaines des incidents du mois
	 * @param selectedDate
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static List<Environnement> getTopFiveDomaineDeMois(Date selectedDate) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		List<Environnement> listEnviro = session.createCriteria(Incidents.class)
											.add(Restrictions.between("dateDebut",
													DateService.getMonthEarlier(selectedDate),
													DateService.getMonthLater(selectedDate)))
											.setProjection(Projections.property("domaine"))
											.list();
		session.getTransaction().commit();
		return listEnviro;
	}

	/**
	 * SELECT les environnements qui ont eu des incidents dans le mois passé en paramètre
	 * @param selectMois
	 * @return
	 */
	public static List<Environnement> getDomaineDeMois(Date selectMois) throws Exception {
		return getDomaineDePeriode(DateService.getMonthEarlier(selectMois), DateService.getMonthLater(selectMois));
	}
	
	/**
	 * SELECT les environnements qui ont eu des incidents dans la période passé en paramètre
	 * @param debut
	 * @param fin
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static List<Environnement> getDomaineDePeriode(Date debut, Date fin) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		List<Environnement> listEnviro = new ArrayList<Environnement>();
		listEnviro = session.createCriteria(Incidents.class)
						.add(Restrictions.between("dateDebut", debut, fin))
						.setProjection(Projections.property("domaine"))
						.list();
		
		session.getTransaction().commit();
		return listEnviro;
	}
	
	/**

	 * SELECT des incidents dans le mois de la date et pour un environnement en paramètre
	 * @param selectMois
	 * @param environnementId 
	 * @return
	 * @throws Exception 
	 */
	public static List<Incidents> getIncidentDomaineDeMois(Date selectMois, Integer environnementId) throws Exception {
		return getIncidentDomaineDePeriode(DateService.getMonthEarlier(selectMois), DateService.getMonthLater(selectMois), environnementId);
	}
	
	/**
	 * SELECT des incidents dans la période et pour un environnement en paramètre
	 * @param debut
	 * @param fin
	 * @param environnementId
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static List<Incidents> getIncidentDomaineDePeriode(Date debut, Date fin, Integer environnementId) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		
		Environnement env = (Environnement) session.load(Environnement.class, environnementId);
		List<Incidents> listIncidentEnviro = session.createCriteria(Incidents.class)
													.add(Restrictions.between("dateDebut", debut, fin))
													.add(Restrictions.eq("domaine", env))
													.list();
		session.getTransaction().commit();
		return listIncidentEnviro;
	}
	
	
	/**
	 * SELECT de tous les uncidents de la semaine
	 * @param selectedDate
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<List<Incidents>> getAllIncidentByWeek(Date selectedDate) throws Exception {
		List<List<Incidents>> resultList = new ArrayList<List<Incidents>>();
		Session session = PilotageSession.getCurrentSession();
		selectedDate = DateService.getWeekStart(selectedDate);
		for (int i = 0; i < 7; i++) {
			resultList.add(
				session.createCriteria(Incidents.class)
					.add(Restrictions.between("dateDebut",
							DateService.getDateEarlier(selectedDate),
							DateService.getDateLater(selectedDate)))
					.list()
			);
			selectedDate = DateService.addDays(selectedDate, 1);
		}
		session.getTransaction().commit();
		return resultList;
	}

	/**
	 * Met le mois sous format 01/{mois}/{année} 7:30:00
	 * @param moisEnCoursStr
	 * @return
	 */
	public static String convMoisEnCour(String moisEnCoursStr, String annee){
		HashMap<String,String> moisMap = new HashMap<String,String>();
		moisMap.put("janvier", "01");
		moisMap.put("fevrier", "02");
		moisMap.put("mars", "03");
		moisMap.put("avril", "04");
		moisMap.put("mai", "05");
		moisMap.put("juin", "06");
		moisMap.put("juillet", "07");
		moisMap.put("aout", "08");
		moisMap.put("septembre", "09");
		moisMap.put("octobre", "10");
		moisMap.put("novembre", "11");
		moisMap.put("decembre", "12");
		if(annee == null){
			Date selectedDate = new Date();
			annee = new SimpleDateFormat("yyyy").format(selectedDate);
		}
		String dateTemp = "01/"+moisMap.get(moisEnCoursStr)+"/"+annee+" 7:30:00";
		return dateTemp;
	}
	
}
