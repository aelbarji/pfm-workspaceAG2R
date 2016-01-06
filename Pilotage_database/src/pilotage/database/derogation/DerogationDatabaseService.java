package pilotage.database.derogation;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;


import pilotage.metier.Applicatifs_Liste;
import pilotage.metier.Derogation;
import pilotage.metier.Derogation_Etat;
import pilotage.metier.Derogation_Type;
import pilotage.metier.Derogation_Type_Changement;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;
import pilotage.session.PilotageSession;
import pilotage.utils.Pagination;

public class DerogationDatabaseService {

	/**
	 * SELECT des dérogations
	 * @param pagination
	 * @param sort
	 * @param sens
	 * @param filtreNumero
	 * @param filtreDate
	 * @param filtreAppli
	 * @param filtreTp
	 * @param filtreDtc
	 * @param filtreEtat
	 * @return
	 */
	public static List<Derogation> getAllDerogation(Pagination<Derogation> pagination, String sort, String sens, Integer filtreNumero, Date filtreDate, Integer filtreAppli, Integer filtreTp, Integer filtreDtc, Integer filtreEtat) {
		Session session = PilotageSession.getCurrentSession();
		Criteria criteria = session.createCriteria(Derogation.class);
		
		if(filtreNumero != null){
			criteria.add(Restrictions.eq("id", filtreNumero));
		}

		if(filtreDate != null){
			criteria.add(Restrictions.eq("timeDemande",filtreDate));
		}
						
		if(filtreTp != null && filtreTp != -1){
			criteria.add(Restrictions.eq("tp", filtreTp));
		}
		
		if(filtreAppli != null && filtreAppli != -1){
			Applicatifs_Liste appli = (Applicatifs_Liste)session.load(Applicatifs_Liste.class, filtreAppli);
			criteria.add(Restrictions.eq("idAppli", appli));
		}
		
		if(filtreDtc != null && filtreDtc != -1){
			Derogation_Type_Changement typeChangement = (Derogation_Type_Changement)session.load(Derogation_Type_Changement.class, filtreDtc);
			criteria.add(Restrictions.eq("typeChangement", typeChangement));
		}
		
		if(filtreEtat != null && filtreEtat != -1){
			Derogation_Etat etat = (Derogation_Etat)session.load(Derogation_Etat.class, filtreEtat);
			criteria.add(Restrictions.eq("etat", etat));
		}
		
		if("idAppli".equals(sort)){
			criteria.createAlias("idAppli", "appli", Criteria.LEFT_JOIN);
			criteria.addOrder("desc".equalsIgnoreCase(sens) ? Order.desc("appli.applicatif") : Order.asc("appli.applicatif"));
		}
		else if("typeChangement".equals(sort)){
			criteria.createAlias("typeChangement", "tc", Criteria.LEFT_JOIN);
			criteria.addOrder("desc".equalsIgnoreCase(sens) ? Order.desc("tc.typeChangement") : Order.asc("tc.typeChangement"));
		}
		else if("etat".equals(sort)){
			criteria.createAlias("etat", "etat", Criteria.LEFT_JOIN);
			criteria.addOrder("desc".equalsIgnoreCase(sens) ? Order.desc("etat.etat") : Order.asc("etat.etat"));
		}
		else
			criteria.addOrder("desc".equalsIgnoreCase(sens) ? Order.desc(sort) : Order.asc(sort));
		
		List<Derogation> listDerogation = pagination.setCriteriaPagination(criteria);
		session.getTransaction().commit();
		return listDerogation;
	}
	
	/**
	 * DELETE d'une dérogation
	 * @param selectRow
	 * @throws Exception 
	 */
	public static void delete(int selectRow) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			Derogation derogation = (Derogation)session.load(Derogation.class, selectRow);
			
			session.delete(derogation);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}

	/**
	 * UPDATE d'une dérogation
	 * @param id
	 * @param dtID
	 * @param demandeurID
	 * @param telephone
	 * @param appID
	 * @param service
	 * @param realisateur
	 * @param description
	 * @param clientTouche
	 * @param serviceImpact
	 * @param numars
	 * @param tp
	 * @param etude
	 * @param retourArriere
	 * @param dtcID
	 * @param externe
	 * @param recette
	 * @param justificatif
	 * @param time 
	 * @param date 
	 * @throws Exception 
	 */
	public static void modify(Integer id, Integer dtID, Integer demandeurID, String telephone, Integer appID, 
			String  service , String  realisateur, String  description, String  clientTouche, String  serviceImpact, 
			String  numars, Integer tp, Integer etude, Integer retourArriere, 
			Integer  dtcID, Integer externe, Integer recette, String  justificatif, Date date, String time) throws Exception {
		
		Session session = PilotageSession.getCurrentSession();
		try{
			Derogation_Type dt = (Derogation_Type)session.load(Derogation_Type.class, dtID);
			Users demandeur = (Users)session.load(Users.class, demandeurID);
			Applicatifs_Liste appli = (Applicatifs_Liste)session.load(Applicatifs_Liste.class, appID);
			Derogation_Type_Changement dtc = (Derogation_Type_Changement)session.load(Derogation_Type_Changement.class, dtcID);
			
			Derogation derogation = (Derogation)session.load(Derogation.class, id);
			derogation.setIdType(dt);
			derogation.setIdNomDemandeur(demandeur);	
			derogation.setTelephone(telephone);
			derogation.setIdAppli(appli);
			derogation.setService(service);
			derogation.setRealisateur(realisateur);
			derogation.setDescription(description);
			derogation.setClientTouche(clientTouche);
			derogation.setServiceImpact(serviceImpact);
			derogation.setNumars(numars);
			derogation.setTp(tp);		
			derogation.setEtude(etude);
			derogation.setRetourArriere(retourArriere);
			derogation.setTypeChangement(dtc);
			derogation.setExterne(externe);
			derogation.setRecette(recette);
			derogation.setJustificatif(justificatif);
			derogation.setTimeDemande(date);
			derogation.setHeureDemande(time);
	
			session.update(derogation);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	/**
	 * INSERT d'une nouvelle dérogation
	 * @param dtID
	 * @param demandeurID
	 * @param idNomCreateur
	 * @param telephone
	 * @param appID
	 * @param service
	 * @param realisateur
	 * @param description
	 * @param clientTouche
	 * @param serviceImpact
	 * @param numars
	 * @param tp
	 * @param timeDemande
	 * @param heureDemande
	 * @param etude
	 * @param retourArriere
	 * @param dtcID
	 * @param externe
	 * @param recette
	 * @param justificatif
	 * @param message
	 * @throws Exception 
	 */
	public static void create(Integer dtID, Integer demandeurID, Users idNomCreateur, String telephone, Integer appID, 
			String  service , String  realisateur, String  description, String  clientTouche, String  serviceImpact, 
			String  numars, Integer tp, Date timeDemande, String  heureDemande, Integer etude, Integer retourArriere, 
			Integer  dtcID, Integer externe, Integer recette, String  justificatif, String  message) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			Derogation_Type dt = (Derogation_Type)session.load(Derogation_Type.class, dtID);
			Users demandeur = (Users)session.load(Users.class, demandeurID);
			Applicatifs_Liste appli = (Applicatifs_Liste)session.load(Applicatifs_Liste.class, appID);
			Derogation_Type_Changement dtc = (Derogation_Type_Changement)session.load(Derogation_Type_Changement.class, dtcID);
			Derogation_Etat etat = (Derogation_Etat)session.load(Derogation_Etat.class, PilotageConstants.DEROGATION_ETAT_ENREGISTREE);
			
			Derogation derogation = new Derogation();
			derogation.setIdType(dt);
			derogation.setIdNomDemandeur(demandeur);
			derogation.setIdNomCreateur(idNomCreateur);
			derogation.setTelephone(telephone);
			derogation.setIdAppli(appli);
			derogation.setService(service);
			derogation.setRealisateur(realisateur);
			derogation.setDescription(description);
			derogation.setClientTouche(clientTouche);
			derogation.setServiceImpact(serviceImpact);
			derogation.setNumars(numars);
			derogation.setTp(tp);
			derogation.setTimeDemande(timeDemande);
			derogation.setHeureDemande(heureDemande);
			derogation.setEtude(etude);
			derogation.setRetourArriere(retourArriere);
			derogation.setTypeChangement(dtc);
			derogation.setExterne(externe);
			derogation.setRecette(recette);
			derogation.setJustificatif(justificatif);
			derogation.setEtat(etat);
			derogation.setValider(PilotageConstants.DEROGATION_NON_VALIDEE);
			derogation.setMessage(message);
			derogation.setDate_creation(new Date());
			
			session.save(derogation);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	/**
	 * SELECT d'une dérogation
	 * @param selectRow
	 * @return
	 */
	public static Derogation get(Integer selectRow) {
		Session session = PilotageSession.getCurrentSession();
		Derogation derogation = (Derogation)session.createCriteria(Derogation.class)
							.add(Restrictions.eq("id", selectRow))
							.setMaxResults(1)
							.uniqueResult();
		session.getTransaction().commit();
		return derogation;
	}

	/**
	 * COUNT teste si des dérogations ont le type passé en paramètre
	 * @param typeID
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public static boolean hasDerogationType(Integer typeID) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		
		List<Long> results = null;
		try{
			Derogation_Type type = (Derogation_Type)session.load(Derogation_Type.class, typeID);
			results = session.createCriteria(Derogation.class)
				.add(Restrictions.eq("idType", type))
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
	 * COUNT teste si des dérogations ont le type de changement passé en paramètre
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public static boolean hasDerogationTypeChangement(Integer id) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		
		List<Long> results = null;
		try{
			Derogation_Type_Changement type = (Derogation_Type_Changement)session.load(Derogation_Type_Changement.class, id);
			results = session.createCriteria(Derogation.class)
				.add(Restrictions.eq("typeChangement", type))
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
	 * COUNT test si une dérogation référence un service
	 * teste si le champ service est serviceID ou "%;" + serviceID ou "%;" + serviceID  + ";%" ou serviceID  + ";%"
	 * @param serviceID
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static boolean hasService(Integer serviceID) {
		Session session = PilotageSession.getCurrentSession();
		
		List<Long> results = session.createCriteria(Derogation.class)
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
	 * UPDATE de l'état de la dérogation
	 * @param selectRow
	 * @param etatID
	 * @throws Exception 
	 */
	public static void changeStatut(Integer selectRow, Integer etatID) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			Derogation_Etat etat = (Derogation_Etat)session.load(Derogation_Etat.class, etatID);
			Derogation derogation = (Derogation)session.load(Derogation.class, selectRow);
			
			derogation.setEtat(etat);
			
			session.update(derogation);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}


	/**
	 * SELECT des dérogations à valider
	 * @param pagination
	 * @param sort
	 * @param sens
	 * @param numero
	 * @param date
	 * @param filtreAppli
	 * @param filtreTp
	 * @param filtreDtc
	 * @param filtreEtat
	 * @return
	 */
	public static List<Derogation> getAllDerogationToValidate(
			Pagination<Derogation> pagination, String sort, String sens,
			Integer filtreNumero, Date filtreDate, Integer filtreAppli, Integer filtreTp,
			Integer filtreDtc, Integer filtreEtat) {
		
		Session session = PilotageSession.getCurrentSession();
		
		Derogation_Etat envoyee = (Derogation_Etat)session.load(Derogation_Etat.class, PilotageConstants.DEROGATION_ETAT_ENVOYEE);
		Derogation_Etat suspendue = (Derogation_Etat)session.load(Derogation_Etat.class, PilotageConstants.DEROGATION_ETAT_SUSPENDUE);
		
		Criteria criteria = session.createCriteria(Derogation.class);
		criteria.createAlias("etat", "etat", Criteria.LEFT_JOIN);
		criteria.add(Restrictions.or(Restrictions.eq("etat", envoyee), Restrictions.eq("etat", suspendue)));
		
		if(filtreNumero != null){
			criteria.add(Restrictions.eq("id", filtreNumero));
		}

		if(filtreDate != null){
			criteria.add(Restrictions.eq("timeDemande",filtreDate));
		}
						
		if(filtreTp != null && filtreTp != -1){
			criteria.add(Restrictions.eq("tp", filtreTp));
		}
		
		if(filtreAppli != null && filtreAppli != -1){
			Applicatifs_Liste appli = (Applicatifs_Liste)session.load(Applicatifs_Liste.class, filtreAppli);
			criteria.add(Restrictions.eq("idAppli", appli));
		}
		
		if(filtreDtc != null && filtreDtc != -1){
			Derogation_Type_Changement typeChangement = (Derogation_Type_Changement)session.load(Derogation_Type_Changement.class, filtreDtc);
			criteria.add(Restrictions.eq("typeChangement", typeChangement));
		}
		
		if(filtreEtat != null && filtreEtat != -1){
			Derogation_Etat etat = (Derogation_Etat)session.load(Derogation_Etat.class, filtreEtat);
			criteria.add(Restrictions.eq("etat", etat));
		}
		
		if("idAppli".equals(sort)){
			criteria.createAlias("idAppli", "appli", Criteria.LEFT_JOIN);
			criteria.addOrder("desc".equalsIgnoreCase(sens) ? Order.desc("appli.applicatif") : Order.asc("appli.applicatif"));
		}
		else if("typeChangement".equals(sort)){
			criteria.createAlias("typeChangement", "tc", Criteria.LEFT_JOIN);
			criteria.addOrder("desc".equalsIgnoreCase(sens) ? Order.desc("tc.typeChangement") : Order.asc("tc.typeChangement"));
		}
		else if("etat".equals(sort)){
			criteria.addOrder("desc".equalsIgnoreCase(sens) ? Order.desc("etat.etat") : Order.asc("etat.etat"));
		}
		else
			criteria.addOrder("desc".equalsIgnoreCase(sens) ? Order.desc(sort) : Order.asc(sort));
		
		List<Derogation> listDerogation = pagination.setCriteriaPagination(criteria);
		session.getTransaction().commit();
		return listDerogation;
	}
	
	public static Integer getId(Integer appID, Integer tp, Date timeDemande, Integer  dtcID){
		Session session = PilotageSession.getCurrentSession();
		
		Applicatifs_Liste appli = (Applicatifs_Liste)session.load(Applicatifs_Liste.class, appID);
		Derogation_Type_Changement dtc = (Derogation_Type_Changement)session.load(Derogation_Type_Changement.class, dtcID);
		
		Derogation d = (Derogation)session.createCriteria(Derogation.class)
						.add(Restrictions.eq("idAppli", appli))
						.add(Restrictions.eq("tp", tp))
						.add(Restrictions.eq("timeDemande", timeDemande))
						.add(Restrictions.eq("typeChangement", dtc))
						.setMaxResults(1)
						.uniqueResult();
		session.getTransaction().commit();
		return d.getId();
	}
}
