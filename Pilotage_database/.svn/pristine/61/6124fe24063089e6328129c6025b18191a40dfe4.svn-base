package pilotage.database.users.management;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import pilotage.admin.metier.Profil;
import pilotage.database.admin.ProfilDatabaseService;
import pilotage.metier.Alertes_Disques;
import pilotage.metier.Bilan;
import pilotage.metier.Bug;
import pilotage.metier.Checklist_Annule;
import pilotage.metier.Checklist_Current;
import pilotage.metier.Consignes;
import pilotage.metier.Consignes_Pilotes_Validation;
import pilotage.metier.Derogation;
import pilotage.metier.Derogation_Valideur;
import pilotage.metier.Filtre;
import pilotage.metier.Historique;
import pilotage.metier.Incidents;
import pilotage.metier.Interlocuteur;
import pilotage.metier.Planning_Equipe_Pilote;
import pilotage.metier.Planning_Modif_Ponctuelle;
import pilotage.metier.Users;
import pilotage.service.date.DateService;
import pilotage.session.PilotageSession;
import pilotage.utils.Pagination;

public class UsersDatabaseService {
	
	/**
	 * SELECT de tous les utilisateurs ordonnées par nom prénom
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Users> getAll(){
		Session session = PilotageSession.getCurrentSession();
		List<Users> user = session.createCriteria(Users.class)
								.add(Restrictions.eq("actif", true))
								.addOrder(Order.asc("nom"))
								.addOrder(Order.asc("prenom"))
								.list();
		session.getTransaction().commit();
		return user;
	}

	/**
	 * SELECT le user identifié par selectRow
	 * @param selectRow
	 * @return
	 */
	public static Users get(Integer selectRow) {
		Session session = PilotageSession.getCurrentSession();
		Users user = (Users)session.createCriteria(Users.class)
						.add(Restrictions.eq("id", selectRow))
						.setMaxResults(1)
						.uniqueResult();
		session.getTransaction().commit();
		return user;
	}

	/**
	 * UPDATE d'un user
	 * @param selectRow
	 * @param nom
	 * @param prenom
	 * @param login
	 * @param email
	 * @param profil
	 * @throws Exception 
	 */
	public static void modify(Integer selectRow, String nom, String prenom, String login, String email, Integer profil) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		
		try{
			Users user = (Users)session.load(Users.class, selectRow);
			user.setNom(nom);
			user.setPrenom(prenom);
			user.setLogin(login);
			user.setEmail(email);
			user.setStatut(profil);
			session.update(user);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}

	/**
	 * DELETE/UPDATE du user identifié par selectRow
	 * 
	 * 1. CONSIGNE :	Suppression des consignes_pilotes_validation créées par le user
	 * 2. CONSIGNE : 		Si des consignes ont été créées par le user				--> 	désactivation
	 * 3. INCIDENT : 		Si des incidents ont été créés par le user				--> 	désactivation
	 * 4. INTERLOCUTEUR : 	Si des interlocuteurs ont été affectés au user			-->		désactivation
	 * 5. CHECKLIST : 		Si des checklist_current ont été affectés au user 		-->		désactivation
	 * 6. CHECKLIST : 		Si des checklist_annule ont été affectés au user 		-->		désactivation
	 * 7. DEROGATION :	Suppression des derogation_valideurs attachés au user
	 * 8. DEROGATION : 		Si des dérogations sont attachés au user				-->		désactivation
	 * 9. BILAN : 			Si des bilans ont le user en vacation					-->		désactivation
	 * 10.BILAN : 			Si des alertes_disques sont entrées par le user			-->		désactivation
	 * 11.FEEDBACK : 		Si des feedbacks ont été créés par le user				-->		désactivation
	 * 12.HISTORIQUE :		Si des historiques sont affectés à ce user				-->		désactivation
	 * 13.FILTRE : 		suppression des filtres sauvés de ce user
	 * 14.PLANNING : 		Si le user fait parti d'une équipe de planning 
	 * 
	 * @param selectRow
	 * @throws Exception 
	 */
	@SuppressWarnings({ "unchecked" })
	public static void delete(int selectRow) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		Boolean hasConsignes = false;
		Boolean hasIncidents = false;
		Boolean hasInterlocuteur = false;
		Boolean hasChecklistCurrent = false;
		Boolean hasChecklistAnnule = false;
		Boolean hasDerogation = false;
		Boolean hasBilans = false;
		Boolean hasAlertesDisques = false;
		Boolean hasFeedback = false;
		Boolean hasHistorique = false;
		Boolean hasPlanningEquipe = false;
		List<Long> results;
		try {
			Users u = (Users)session.load(Users.class, selectRow);
			
			//1. suppression de toutes les consignes_pilotes_validation 
			List<Consignes_Pilotes_Validation> validations = session.createCriteria(Consignes_Pilotes_Validation.class)
																		.add(Restrictions.eq("idPilote", u))
																		.list();
			for(Consignes_Pilotes_Validation conPilVal : validations){
				session.delete(conPilVal);
			}
			
			//7. suppression des derogation_valideur
			List<Derogation_Valideur> listDerogationValideurs = session.createCriteria(Derogation_Valideur.class)
																			.add(Restrictions.eq("valideur", u))
																			.list();
			for(Derogation_Valideur valideur : listDerogationValideurs){
				session.delete(valideur);
			}
			
			//13. suppression des filtres sauvés de ce user
			List<Filtre> listFiltres = session.createCriteria(Filtre.class)
													.add(Restrictions.eq("userId", u))
													.list();
			
			for(Filtre filtre : listFiltres){
				session.delete(filtre);
			}
			
			//test du 2.
			results = (List<Long>)session.createCriteria(Consignes.class)
									.add(Restrictions.eq("createur", u))
									.setProjection(Projections.rowCount())
									.list();
			if (results != null && results.size() > 0 && results.get(0) > 0){
				hasConsignes = true;
			}
			
			//Si non 2., on teste 3.
			if(! hasConsignes){
				results = (List<Long>)session.createCriteria(Incidents.class)
										.add(Restrictions.eq("user", u))
										.setProjection(Projections.rowCount())
										.list();
				if (results != null && results.size() > 0 && results.get(0) > 0){
					hasIncidents = true;
				}
			}
			
			//Si non 3., on teste 4.
			if(! hasConsignes && ! hasIncidents){
				results = (List<Long>)session.createCriteria(Interlocuteur.class)
										.add(Restrictions.eq("idResponsable", u))
										.setProjection(Projections.rowCount())
										.list();
				if (results != null && results.size() > 0 && results.get(0) > 0){
					hasInterlocuteur = true;
				}
			}
			
			//si non 4., on teste 5.
			if(! hasConsignes && ! hasIncidents && !hasInterlocuteur){
				results = (List<Long>)session.createCriteria(Checklist_Current.class)
										.add(Restrictions.eq("user", u))
										.setProjection(Projections.rowCount())
										.list();
				if (results != null && results.size() > 0 && results.get(0) > 0){
					hasChecklistCurrent = true;
				}
			}
			
			//si non 5., on teste 6.
			if(! hasConsignes && ! hasIncidents && !hasInterlocuteur && !hasChecklistCurrent){
				results = (List<Long>)session.createCriteria(Checklist_Annule.class)
										.add(Restrictions.eq("user", u))
										.setProjection(Projections.rowCount())
										.list();
				if (results != null && results.size() > 0 && results.get(0) > 0){
					hasChecklistAnnule = true;
				}
			}
			
			//si non 6., on teste 8.
			if(! hasConsignes && ! hasIncidents && !hasInterlocuteur && !hasChecklistCurrent && !hasChecklistAnnule){
				results = (List<Long>)session.createCriteria(Derogation.class)
										.add(
												Restrictions.or(
														Restrictions.eq("idNomDemandeur", u),
														Restrictions.eq("idNomCreateur", u)
												)
										)
										.setProjection(Projections.rowCount())
										.list();
				if (results != null && results.size() > 0 && results.get(0) > 0){
					hasDerogation = true;
				}
			}
			
			//si non 8., on teste 9.
			if(! hasConsignes && ! hasIncidents && !hasInterlocuteur && !hasChecklistCurrent && !hasChecklistAnnule && !hasDerogation){
				results = (List<Long>)session.createCriteria(Bilan.class)
										.add(Restrictions.or(
												Restrictions.eq("vacationmatin", u), 
												Restrictions.or(
														Restrictions.eq("vacationaprem", u),
														Restrictions.eq("vacationnuit", u))
										))
										.setProjection(Projections.rowCount())
										.list();
				if (results != null && results.size() > 0 && results.get(0) > 0){
					hasBilans = true;
				}
			}
			
			//si non 9., on teste 10.
			if(! hasConsignes && ! hasIncidents && !hasInterlocuteur && !hasChecklistCurrent && !hasChecklistAnnule && !hasDerogation){
				results = (List<Long>)session.createCriteria(Alertes_Disques.class)
										.add(Restrictions.eq("user", u))
										.setProjection(Projections.rowCount())
										.list();
				if (results != null && results.size() > 0 && results.get(0) > 0){
					hasAlertesDisques = true;
				}
			}
			
			//si non 10., on teste 11.
			if(! hasConsignes && ! hasIncidents && !hasInterlocuteur && !hasChecklistCurrent && !hasChecklistAnnule && !hasDerogation && !hasAlertesDisques){
				results = (List<Long>)session.createCriteria(Bug.class)
										.add(Restrictions.eq("idUser", u))
										.setProjection(Projections.rowCount())
										.list();
				if (results != null && results.size() > 0 && results.get(0) > 0){
					hasFeedback = true;
				}
			}
			
			//si non 11., on teste 12.
			if(! hasConsignes && ! hasIncidents && !hasInterlocuteur && !hasChecklistCurrent && !hasChecklistAnnule && !hasDerogation && !hasAlertesDisques && !hasFeedback){
				results = (List<Long>)session.createCriteria(Historique.class)
										.add(Restrictions.eq("utilisateur", u))
										.setProjection(Projections.rowCount())
										.list();
				if (results != null && results.size() > 0 && results.get(0) > 0){
					hasHistorique = true;
				}
			}
			
			// test 14 si le user est dans une équipe planning ou une modif ponctuelle
			List<Planning_Equipe_Pilote> pepListe = (List<Planning_Equipe_Pilote>)session.createCriteria(Planning_Equipe_Pilote.class)
														.add(Restrictions.eq("idUser", u))
														.list();
			List<Planning_Modif_Ponctuelle> pmpListe = session.createCriteria(Planning_Modif_Ponctuelle.class)
														.add(Restrictions.eq("idUser", u))
														.addOrder(Order.desc("dateModif"))
														.list();
			Date deleteDate = new Date();
			List<Planning_Equipe_Pilote> pepToUpdate = new ArrayList<Planning_Equipe_Pilote>();
			List<Planning_Equipe_Pilote> pepToDelete = new ArrayList<Planning_Equipe_Pilote>();
			List<Planning_Modif_Ponctuelle> pmpToDelete = new ArrayList<Planning_Modif_Ponctuelle>();
			if (!pepListe.isEmpty()){
				hasPlanningEquipe = true;
				for (Planning_Equipe_Pilote pep : pepListe){
					if (pep.getDateDebut().before(deleteDate) && (pep.getDateFin().after(deleteDate) || pep.getDateFin() == null)){
						String delDate = DateService.dateToStr(deleteDate, DateService.p4);
						Date delD = DateService.strToDate(delDate);
						pep.setDateFin(delD);
						pepToUpdate.add(pep);
					}
					if (pep.getDateDebut().after(deleteDate)){
						pepToDelete.add(pep);
					}
				}
				for (Planning_Equipe_Pilote pep : pepToUpdate){
					session.update(pep);			
				}
				for (Planning_Equipe_Pilote pep : pepToDelete){
					session.delete(pep);			
				}				
			}
			if (!pmpListe.isEmpty()){
				hasPlanningEquipe = true; 
				for (Planning_Modif_Ponctuelle pmp : pmpListe){
					if (DateService.addDays(pmp.getDateDebut(), 1).after(deleteDate)){
						pmpToDelete.add(pmp);
					}
				}
				for (Planning_Modif_Ponctuelle pmp : pmpToDelete){
					session.delete(pmp);			
				}			
			}
			
			//si 2. ou 3. ou 4. ou 5. ou 6. ou 8. ou 9. ou 10. ou 11. ou 12. ou 14. --> desactivation
			if(hasConsignes || hasIncidents || hasInterlocuteur || hasChecklistCurrent || hasChecklistAnnule || hasDerogation || hasBilans || hasAlertesDisques || hasFeedback || hasHistorique || hasPlanningEquipe){
				u.setActif(false);
				session.update(u);
			}

			//sinon suppression
			else{
				session.delete(u);
			}

			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}

	/**
	 * INSERT d'un nouveau user
	 * @param nom
	 * @param prenom
	 * @param login
	 * @param email
	 * @param password
	 * @param profil
	 * @throws Exception 
	 */
	public static void createNewUser(String nom, String prenom, String login, String email, String password, int profil) throws Exception{
		Session session =PilotageSession.getCurrentSession();

		try {
			Users user=new Users();
			user.setNom(nom);
			user.setPrenom(prenom);
			user.setLogin(login);
			user.setEmail(email);
			user.setPassword(password);
			user.setStatut(profil);
			user.setMyChecklist(100);
			user.setRefresshChecklist(60);
			user.setFiltreEnv("0");
			user.setAutoXguard(0);
			user.setNrPerpage(20);
			user.setActif(true);
			session.save(user);
			session.getTransaction().commit();

		} catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}

	/**
	 * SELECT tous les utilisateurs avec pagination
	 * @param pagination
	 * @param sort
	 * @param sens
	 * @param filtreProfil 
	 * @param filtreEmail 
	 * @param filtreLogin 
	 * @param filtrePrenom 
	 * @param filtreNom 
	 * @return
	 */
	public static List<Users> getAllUsers(Pagination<Users> pagination, String sort, String sens, String filtreNom, String filtrePrenom, String filtreLogin, String filtreEmail, String filtreProfil) {
		Session session = PilotageSession.getCurrentSession();

		Criteria criteria = session.createCriteria(Users.class);
		criteria.add(Restrictions.eq("actif", true));
		
		if(filtreNom != null && !"".equals(filtreNom)){
			criteria.add(Restrictions.like("nom", "%" + filtreNom + "%"));
		}
		if(filtrePrenom != null && !"".equals(filtrePrenom)){
			criteria.add(Restrictions.like("prenom", "%" + filtrePrenom + "%"));
		}
		if(filtreLogin != null && !"".equals(filtreLogin)){
			criteria.add(Restrictions.like("login", "%" + filtreLogin + "%"));
		}
		if(filtreEmail != null && !"".equals(filtreEmail)){
			criteria.add(Restrictions.like("email", "%" + filtreEmail + "%"));
		}
		if(filtreProfil != null && !"".equals(filtreProfil) && !"-1".equals(filtreProfil)){
			criteria.add(Restrictions.eq("statut", Integer.parseInt(filtreProfil)));
		}

		criteria.addOrder("desc".equalsIgnoreCase(sens) ? Order.desc(sort) : Order.asc(sort));
		
		List<Users> listUsers = pagination.setCriteriaPagination(criteria);
		session.getTransaction().commit();
		return listUsers;
	}

	/**
	 * COUNT teste si le login existe déjà
	 * @param login
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static boolean loginExists(String login) {
		Session session = PilotageSession.getCurrentSession();
		
		List<Long> results = null;
		try{
			results= (List<Long>)session.createCriteria(Users.class)
				.add(Restrictions.eq("login", login))
				.add(Restrictions.eq("actif", true))
				.setProjection(Projections.rowCount())
				.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			e.printStackTrace();
		}

		if (results != null && results.size() > 0 && results.get(0) > 0)
			return true;
		else
			return false;
	}

	/**
	 * SELECT de la liste des users pilotes
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Users> getPiloteList(){
		
		List<Profil> piloteProfil = ProfilDatabaseService.getPilotes();
		
		Session session = PilotageSession.getCurrentSession();
		
		Integer[] piloteID = new Integer[piloteProfil.size()];
		int i = 0;
		for(Profil p : piloteProfil){
			piloteID[i] = p.getId();
			i++;
		}
		
		List<Users> piloteList = (List<Users>)session.createCriteria(Users.class)
		                         	.add(Restrictions.in("statut", piloteID))
		                         	.add(Restrictions.eq("actif", true))
		                         	.addOrder(Order.asc("nom"))
		                         	.addOrder(Order.asc("prenom"))
		                         	.addOrder(Order.desc("statut"))
		                         	.list();
		session.getTransaction().commit();
		return piloteList;
	}
	
	/**
	 * SELECT de la liste des users admins
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Users> getAdminList(){
		List<Profil> adminProfil = ProfilDatabaseService.getAdmins();
		
		Session session = PilotageSession.getCurrentSession();
		
		Integer[] adminID = new Integer[adminProfil.size()];
		int i = 0;
		for(Profil p :adminProfil){
			adminID[i] = p.getId();
			i++;
		}
		
		List<Users> adminList = (List<Users>)session.createCriteria(Users.class)
									.add(Restrictions.in("statut", adminID))
									.add(Restrictions.eq("actif", true))
									.list();
		session.getTransaction().commit();
		return adminList;
	}

	/**
	 * COUNT le nombre de pilotes
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static int getNombrePilotes() {
		List<Profil> piloteProfil = ProfilDatabaseService.getPilotes();
		
		Session session = PilotageSession.getCurrentSession();
		
		Integer[] piloteID = new Integer[piloteProfil.size()];
		int i = 0;
		for(Profil p : piloteProfil){
			piloteID[i] = p.getId();
			i++;
		}
		
		List<Long> results = null;
		try{
			results= (List<Long>)session.createCriteria(Users.class)
						.add(Restrictions.in("statut", piloteID))
						.add(Restrictions.eq("actif", true))
						.setProjection(Projections.rowCount())
						.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			e.printStackTrace();
		}

		if (results != null && results.size() > 0)
			return results.get(0).intValue();
		else
			return 0;
	}

	/**
	 * SELECT de tous les users qui ne sont pas dans la liste passée en paramètre
	 * @param listUserValideurs
	 * @param admin
	 * @param pilote
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Users> getAllExcept(List<Integer> listUserValideurs, Boolean admin, Boolean pilote) {
		if(listUserValideurs == null || listUserValideurs.isEmpty())
			return UsersDatabaseService.getAll();
		
		List<Integer> listID = new ArrayList<Integer>();
		
		if(Boolean.TRUE.equals(pilote)){
			List<Profil> piloteProfil = ProfilDatabaseService.getPilotes();
			for(Profil p : piloteProfil){
				listID.add(p.getId());
			}
		}
		if(Boolean.TRUE.equals(admin)){
			List<Profil> adminProfil = ProfilDatabaseService.getAdmins();
			for(Profil p : adminProfil){
				listID.add(p.getId());
			}
		}
		
		Session session = PilotageSession.getCurrentSession();
	
		Criteria criteria = session.createCriteria(Users.class)
								.add(Restrictions.not(Restrictions.in("id", listUserValideurs)))
								.add(Restrictions.eq("actif", true))
								.addOrder(Order.asc("nom"))
								.addOrder(Order.asc("prenom"));
		
		if(Boolean.TRUE.equals(pilote) || Boolean.TRUE.equals(admin))
			criteria.add(Restrictions.in("statut", listID));
		
		List<Users> users = criteria.list();
		session.getTransaction().commit();
		return users;
	}
	
	public static Integer getId(String login){
		Session session = PilotageSession.getCurrentSession();
		Users u = (Users)session.createCriteria(Users.class)
						.add(Restrictions.eq("login", login))
						.setMaxResults(1)
						.uniqueResult();
		session.getTransaction().commit();
		return u.getId();
	}
	
	/**
	  * UPDATE d'un user
	  * @param userId
	  * @param nrPerPage
	  * @throws Exception 
	  */
	 public static void modifyNrPerPage(Integer userId, Integer nrPerpage) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		  
		try{
		   Users user = (Users)session.load(Users.class, userId);
		   user.setNrPerpage(nrPerpage);
		   session.update(user);
		   session.getTransaction().commit();
		  }
		  catch (Exception e) {
		   PilotageSession.rollbackTransaction(session);
		   throw e;
		  }
	 }
}
