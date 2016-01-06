package pilotage.database.applicatif;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import pilotage.metier.Appli_Entite;
import pilotage.metier.Appli_Envir;
import pilotage.metier.Appli_Service;
import pilotage.metier.Applicatifs_Liste;
import pilotage.metier.Derogation;
import pilotage.metier.Environnement;
import pilotage.metier.Incidents;
import pilotage.metier.Interlocuteur;
import pilotage.metier.Machines_Applicatifs;
import pilotage.metier.Machines_Liste;
import pilotage.metier.Services_Liste;
import pilotage.session.PilotageSession;
import pilotage.utils.Pagination;

public class ApplicatifDatabaseService {

	/**
	 * SELECT de tous les applicatifs, ordonnés par libellé
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Applicatifs_Liste> getAll() {
		Session session = PilotageSession.getCurrentSession();
		List<Applicatifs_Liste> appList = (List<Applicatifs_Liste>)session.createCriteria(Applicatifs_Liste.class)
													.add(Restrictions.eq("actif", Boolean.TRUE))
													.addOrder(Order.asc("applicatif"))
													.list();
		session.getTransaction().commit();
		return appList;
	}
	
	/**
	 * SELECT de tous les applicatifs suivant les paramètres de filtre/pagination
	 * @param pagination
	 * @param filtreNom
	 * @param filtreDescription
	 * @param filtreDocument
	 * @param sens 
	 * @param sort 
	 * @return
	 */
	public static List<Applicatifs_Liste> getAll(Pagination<Applicatifs_Liste> pagination, String filtreNom, String filtreDescription, String filtreDocument, String sort, String sens) {
		Session session = PilotageSession.getCurrentSession();
		Criteria criteria = session.createCriteria(Applicatifs_Liste.class);
		
		if(filtreNom != null && !filtreNom.equals("")){
			criteria.add(Restrictions.like("applicatif", "%" + filtreNom + "%"));
		}
		
		if(filtreDescription != null && !filtreDescription.equals("")){
			criteria.add(Restrictions.like("description", "%" + filtreDescription + "%"));
		}
		
		if(filtreDocument != null && !filtreDocument.equals("")){
			criteria.add(Restrictions.like("documentation", "%" + filtreDocument + "%"));
		}
		
		criteria.add(Restrictions.eq("actif", Boolean.TRUE));
		
		if("idEnvironnement".equals(sort)){
			criteria.createAlias("idEnvironnement", "env", Criteria.LEFT_JOIN);
			criteria.addOrder("desc".equalsIgnoreCase(sens) ? Order.desc("env.environnement") : Order.asc("env.environnement"));
		}
		else{
			criteria.addOrder("desc".equalsIgnoreCase(sens) ? Order.desc(sort) : Order.asc(sort));
		}
		
		List<Applicatifs_Liste> appList = pagination.setCriteriaPagination(criteria);
		session.getTransaction().commit();
		return appList;
	}

	/**
	 * SELECT d'un applicatif
	 * @param parseInt
	 * @return
	 */
	public static Applicatifs_Liste get(Integer id) {
		Session session = PilotageSession.getCurrentSession();
		
		Applicatifs_Liste app = (Applicatifs_Liste)session.createCriteria(Applicatifs_Liste.class)
									.add(Restrictions.eq("id", id))
									.setMaxResults(1)
									.uniqueResult();
		session.getTransaction().commit();
		
		return app;
	}
	
	/**
	 * SELECT de la liste des applicatifs ayant l'id dans le tableau passé en paramètre
	 * @param appIDList
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Applicatifs_Liste> getMultiple(List<Integer> appIDList) {
		List<Applicatifs_Liste> apps;
		if(appIDList != null && !appIDList.isEmpty()){
			Session session = PilotageSession.getCurrentSession();
			apps = (List<Applicatifs_Liste>)session.createCriteria(Applicatifs_Liste.class)
												.add(Restrictions.in("id", appIDList))
												.list();
			session.getTransaction().commit();
		}
		else{
			apps = new ArrayList<Applicatifs_Liste>();
		}
		return apps;
	}

	/**
	 * SELECT tous les applicatifs contenus dans la machine en paramètre
	 * @param machineId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Applicatifs_Liste> getByMachine(Integer machineId) {
		Session session = PilotageSession.getCurrentSession();
		
		Machines_Liste machine = (Machines_Liste)session.load(Machines_Liste.class, machineId);
		List<Machines_Applicatifs> listApp_Machine = session.createCriteria(Machines_Applicatifs.class)
														.add(Restrictions.eq("machine", machine))
														.createAlias("applicatif", "app", Criteria.LEFT_JOIN)
														.addOrder(Order.asc("app.applicatif"))
														.add(Restrictions.eq("app.actif", Boolean.TRUE))
														.list();
		session.getTransaction().commit();
		
		List<Applicatifs_Liste> listApp = new ArrayList<Applicatifs_Liste>();
		for(Machines_Applicatifs ma : listApp_Machine){
			listApp.add(ma.getApplicatif());
		}
		return listApp;
	}
	
	/**
	 * SELECT tous les applicatifs contenus dans l'environnement en paramètre
	 * @param enviroSelected
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Applicatifs_Liste> getByEnvironnement(Integer enviroSelected) {
		Session session = PilotageSession.getCurrentSession();
		
		Environnement envir = (Environnement)session.load(Environnement.class, enviroSelected);
		List<Appli_Envir> listApp_Envir = session.createCriteria(Appli_Envir.class)
													.add(Restrictions.eq("idEnvironnement", envir))
													.createAlias("idApplication", "app", Criteria.LEFT_JOIN)
													.addOrder(Order.asc("app.applicatif"))
													.add(Restrictions.eq("app.actif", Boolean.TRUE))
													.list();
		session.getTransaction().commit();
		
		List<Applicatifs_Liste> listApp = new ArrayList<Applicatifs_Liste>();
		for(Appli_Envir ae : listApp_Envir){
			listApp.add(ae.getIdApplication());
		}
		return listApp;
	}

	/**
	 * SELECT UNION de tous les applicatifs attachés à la liste des services en paramètre
	 * @param serviceSelectedID
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Applicatifs_Liste> getUnionByServiceList(List<Integer> serviceSelectedID) {
		List<Services_Liste> services = ServiceDatabaseService.getMultiple(serviceSelectedID);
		List<Applicatifs_Liste> listApplis = new ArrayList<Applicatifs_Liste>();
		if(services != null && !services.isEmpty()){
			Session session = PilotageSession.getCurrentSession();
			
			List<Appli_Service> listApp_Service = session.createCriteria(Appli_Service.class)
														.add(Restrictions.in("idServices", services))
														.createAlias("idApplication", "app", Criteria.LEFT_JOIN)
														.addOrder(Order.asc("app.applicatif"))
														.add(Restrictions.eq("app.actif", Boolean.TRUE))
														.list();
			for(Appli_Service as : listApp_Service){
				if(!listApplis.contains(as.getIdApplication())){
					listApplis.add(as.getIdApplication());
				}
			}
			
			session.getTransaction().commit();
		}

		return listApplis;
	}

	/**
	 * COUNT test si une appli référence un environnement
	 * @param envID
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static boolean hasEnvironnement(Integer envID)throws Exception{
		Session session = PilotageSession.getCurrentSession();
		
		Environnement environnement = (Environnement) session.load(Environnement.class, envID);
		List<Long> results = session.createCriteria(Appli_Envir.class)
						          .add(Restrictions.eq("idEnvironnement", environnement))
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
	 * COUNT test si une appli référence un service
	 * @param serviceID
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static boolean hasService(Integer serviceID) {
		Session session = PilotageSession.getCurrentSession();
		
		Services_Liste service = (Services_Liste) session.load(Services_Liste.class, serviceID);
		List<Long> results = session.createCriteria(Appli_Service.class)
						          .add(Restrictions.eq("idServices", service))
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
	 * DELETE/UPDATE d'une application
	 * Suppression des liaisons appli_env appli_entite machine_appli
	 * 1 - si des incidents inclus l'appli --> desactivation
	 * 2 - si des derogations inclus l'appli --> desactivation
	 * @param selectRow
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public static void delete(int selectRow) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			List<Long> results;
			boolean hasIncidents = false;
			boolean hasDerogations = false;
			Applicatifs_Liste app = (Applicatifs_Liste)session.load(Applicatifs_Liste.class, selectRow);
			
			List<Appli_Envir> listAppEnv = session.createCriteria(Appli_Envir.class)
												.add(Restrictions.eq("idApplication", app))
												.list();
			
			List<Appli_Entite> listAppEntite = session.createCriteria(Appli_Entite.class)
												.add(Restrictions.eq("idApplication", app))
												.list();

			List<Machines_Applicatifs> listAppMachine = session.createCriteria(Machines_Applicatifs.class)
												.add(Restrictions.eq("applicatif", app))
												.list();
			
			List<Appli_Service> listAppService = session.createCriteria(Appli_Service.class)
												.add(Restrictions.eq("idApplication", app))
												.list();
			
			//test du 1.
			results = (List<Long>)session.createCriteria(Incidents.class)
									.add(
						        		  Restrictions.or( 
						        				  Restrictions.eq("applicatif", selectRow + ""), 
						        				  Restrictions.or(
						        						  Restrictions.like("applicatif", selectRow + ";%"), 
						        						  Restrictions.or(
						        								  Restrictions.like("applicatif", "%;" + selectRow + ";%"), 
						        								  Restrictions.like("applicatif", "%;" + selectRow))))) 
						          .setProjection(Projections.rowCount())
						          .list();
			if (results != null && results.size() > 0 && results.get(0) > 0){
				hasIncidents = true;
			}
			
			//test du 2.
			results = (List<Long>)session.createCriteria(Derogation.class)
									.add(Restrictions.eq("idAppli", app)) 
									.setProjection(Projections.rowCount())
									.list();
			if (results != null && results.size() > 0 && results.get(0) > 0){
				hasDerogations = true;
			}
			
			if(listAppEnv != null){
				for(Appli_Envir appEnv : listAppEnv){
					session.delete(appEnv);
				}
			}
			if(listAppEntite != null){
				for(Appli_Entite appEnt : listAppEntite){
					session.delete(appEnt);
				}
			}
			if(listAppMachine != null){
				for(Machines_Applicatifs appMac : listAppMachine){
					session.delete(appMac);
				}
			}
			if(listAppService != null){
				for(Appli_Service appServ : listAppService){
					session.delete(appServ);
				}
			}
			
			if(hasIncidents || hasDerogations){
				app.setActif(Boolean.FALSE);
				session.update(app);
			}
			else{
				session.delete(app);
			}
			
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}

	/**
	 * COUNT test si le nom d'application existe déjà
	 * @param id - id à ne pas controler
	 * @param libelle
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static boolean exists(Integer id, String libelle){
		Session session = PilotageSession.getCurrentSession();
		
		Criteria criteria = session.createCriteria(Applicatifs_Liste.class);
		criteria.add(Restrictions.eq("applicatif", libelle));
		if(id != null)
			criteria.add(Restrictions.not(Restrictions.eq("id", id)));
		criteria.setProjection(Projections.rowCount());
		List<Long> results = criteria.list();
		
		session.getTransaction().commit();
		
		if (results != null && results.size() > 0 && results.get(0) > 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * COUNT test si l'application est active
	 * @param libelle
	 * @return
	 */
	public static boolean actif(String libelle){
		Session session = PilotageSession.getCurrentSession();
		Applicatifs_Liste appli = (Applicatifs_Liste)session.createCriteria(Applicatifs_Liste.class)
				.add(Restrictions.eq("applicatif", libelle))
				.setMaxResults(1)
				.uniqueResult();
		session.getTransaction().commit();
		return appli.getActif();
	}
	
	/**
	 * SELECT appli selon le libelle
	 * @param libelle
	 * @return
	 */
	public static Applicatifs_Liste getByName(String libelle){
		Session session = PilotageSession.getCurrentSession();
		Applicatifs_Liste appli = (Applicatifs_Liste)session.createCriteria(Applicatifs_Liste.class)
				.add(Restrictions.eq("applicatif", libelle))
				.setMaxResults(1)
				.uniqueResult();
		session.getTransaction().commit();
		return appli;
	}

	/**
	 * INSERT d'une nouvelle application ainsi que des liaisons avec machines, interlocuteur, service, environnement
	 * @param nom
	 * @param description
	 * @param document
	 * @param machineSelected
	 * @param environnementSelected
	 * @param interlocuteurSelected
	 * @param serviceSelected
	 * @return
	 * @throws Exception 
	 */
	public static Integer create(String nom, String description, String document, String[] machineSelected, String[] environnementSelected, String[] interlocuteurSelected, String[] serviceSelected) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		
		try{
			Applicatifs_Liste app = new Applicatifs_Liste();
			app.setApplicatif(nom);
			app.setDescription(description);
			app.setDocumentation(document);
			app.setActif(Boolean.TRUE);
			
			List<Machines_Applicatifs> listAppMac = null;
			if(machineSelected != null && machineSelected.length > 0){
				listAppMac = new ArrayList<Machines_Applicatifs>();
				for(String macId : machineSelected){
					Machines_Liste mac = (Machines_Liste)session.load(Machines_Liste.class, Integer.parseInt(macId));
					Machines_Applicatifs appMac = new Machines_Applicatifs();
					appMac.setApplicatif(app);
					appMac.setMachine(mac);
					listAppMac.add(appMac);
				}
			}
			
			List<Appli_Envir> listAppEnv = null;
			if(environnementSelected != null && environnementSelected.length > 0){
				listAppEnv = new ArrayList<Appli_Envir>();
				for(String envId : environnementSelected){
					Environnement env = (Environnement)session.load(Environnement.class, Integer.parseInt(envId));
					Appli_Envir appEnv = new Appli_Envir();
					appEnv.setIdApplication(app);
					appEnv.setIdEnvironnement(env);
					listAppEnv.add(appEnv);
				}
			}
			
			List<Appli_Entite> listAppInterloc = null;
			if(interlocuteurSelected != null && interlocuteurSelected.length > 0){
				listAppInterloc = new ArrayList<Appli_Entite>();
				for(String interlocId : interlocuteurSelected){
					Interlocuteur interloc = (Interlocuteur)session.load(Interlocuteur.class, Integer.parseInt(interlocId));
					Appli_Entite appInterloc = new Appli_Entite();
					appInterloc.setIdApplication(app);
					appInterloc.setIdEntite(interloc);
					listAppInterloc.add(appInterloc);
				}
			}
			
			List<Appli_Service> listAppService = null;
			if(serviceSelected != null && serviceSelected.length > 0){
				listAppService = new ArrayList<Appli_Service>();
				for(String serviceId : serviceSelected){
					Services_Liste service = (Services_Liste)session.load(Services_Liste.class, Integer.parseInt(serviceId));
					Appli_Service appService = new Appli_Service();
					appService.setIdApplication(app);
					appService.setIdServices(service);
					listAppService.add(appService);
				}
			}
			session.save(app);
			
			if(listAppMac != null){
				for(Machines_Applicatifs appMac : listAppMac){
					session.save(appMac);
				}
			}
			if(listAppEnv != null){
				for(Appli_Envir appEnv : listAppEnv){
					session.save(appEnv);
				}
			}
			if(listAppInterloc != null){
				for(Appli_Entite appInterloc : listAppInterloc){
					session.save(appInterloc);
				}
			}
			if(listAppService != null){
				for(Appli_Service appService : listAppService){
					session.save(appService);
				}
			}
			
			session.getTransaction().commit();
			return app.getId();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}

	/**
	 * UPDATE d'une application
	 * @param selectRow 
	 * @param nom
	 * @param description
	 * @param documentation
	 * @param machineSelected
	 * @param environnementSelected
	 * @param interlocuteurSelected
	 * @param serviceSelected
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public static void modify(Integer selectRow, String nom, String description, String documentation, String[] machineSelected, String[] environnementSelected, String[] interlocuteurSelected, String[] serviceSelected) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		
		try{
			Applicatifs_Liste app = (Applicatifs_Liste)session.load(Applicatifs_Liste.class, selectRow);
			app.setApplicatif(nom);
			app.setDescription(description);
			app.setDocumentation(documentation);
			app.setActif(true);
			
			List<Integer> macToAdd = new ArrayList<Integer>();
			List<Machines_Applicatifs> macToDelete = new ArrayList<Machines_Applicatifs>();
			List<Machines_Applicatifs> listAppMac = session.createCriteria(Machines_Applicatifs.class)
										.add(Restrictions.eq("applicatif", app))
										.list();
			
			List<Integer> envToAdd = new ArrayList<Integer>();
			List<Appli_Envir> envToDelete = new ArrayList<Appli_Envir>();
			List<Appli_Envir> listAppEnv = session.createCriteria(Appli_Envir.class)
										.add(Restrictions.eq("idApplication", app))
										.list();
			
			List<Integer> interlocToAdd = new ArrayList<Integer>();
			List<Appli_Entite> interlocToDelete = new ArrayList<Appli_Entite>();
			List<Appli_Entite> listAppInterloc = session.createCriteria(Appli_Entite.class)
										.add(Restrictions.eq("idApplication", app))
										.list();
			
			List<Integer> servToAdd = new ArrayList<Integer>();
			List<Appli_Service> servToDelete = new ArrayList<Appli_Service>();
			List<Appli_Service> listAppServ = session.createCriteria(Appli_Service.class)
										.add(Restrictions.eq("idApplication", app))
										.list();
			
			//machines à suppr
			for(Machines_Applicatifs appMac : listAppMac){
				boolean stillInList = false;
				if(machineSelected != null){
					for(String macId : machineSelected){
						if(macId.equals(appMac.getMachine().getId().toString())){
							stillInList = true;
							break;
						}
					}
				}
				if(!stillInList){
					macToDelete.add(appMac);
				}
			}
			
			//machines à ajouter
			if(machineSelected != null){
				for(String macId : machineSelected){
					boolean alreadyInList = false;
					for(Machines_Applicatifs appMac : listAppMac){
						if(macId.equals(appMac.getMachine().getId().toString())){
							alreadyInList = true;
							break;
						}
					}
					if(!alreadyInList){
						macToAdd.add(Integer.parseInt(macId));
					}
				}
			}
			
			//environnements à suppr
			for(Appli_Envir appEnv : listAppEnv){
				boolean stillInList = false;
				if(environnementSelected != null){
					for(String envId : environnementSelected){
						if(envId.equals(appEnv.getIdEnvironnement().getId().toString())){
							stillInList = true;
							break;
						}
					}
				}
				if(!stillInList){
					envToDelete.add(appEnv);
				}
			}
			
			//environnements à ajouter
			if(environnementSelected != null){
				for(String envId : environnementSelected){
					boolean alreadyInList = false;
					for(Appli_Envir appEnv : listAppEnv){
						if(envId.equals(appEnv.getIdEnvironnement().getId().toString())){
							alreadyInList = true;
							break;
						}
					}
					if(!alreadyInList){
						envToAdd.add(Integer.parseInt(envId));
					}
				}
			}
			
			//interlocuteurs à suppr
			for(Appli_Entite appInterloc : listAppInterloc){
				boolean stillInList = false;
				if(interlocuteurSelected != null){
					for(String interlocId : interlocuteurSelected){
						if(interlocId.equals(appInterloc.getIdEntite().getId().toString())){
							stillInList = true;
							break;
						}
					}
				}
				if(!stillInList){
					interlocToDelete.add(appInterloc);
				}
			}
			
			//interlocuteurs à ajouter
			if(interlocuteurSelected != null){
				for(String interlocId : interlocuteurSelected){
					boolean alreadyInList = false;
					for(Appli_Entite appInterloc : listAppInterloc){
						if(interlocId.equals(appInterloc.getIdEntite().getId().toString())){
							alreadyInList = true;
							break;
						}
					}
					if(!alreadyInList){
						interlocToAdd.add(Integer.parseInt(interlocId));
					}
				}
			}
			
			//services à suppr
			for(Appli_Service appServ : listAppServ){
				boolean stillInList = false;
				if(serviceSelected != null){
					for(String servId : serviceSelected){
						if(servId.equals(appServ.getIdServices().getId().toString())){
							stillInList = true;
							break;
						}
					}
				}
				if(!stillInList){
					servToDelete.add(appServ);
				}
			}
			
			//services à ajouter
			if(serviceSelected != null){
				for(String servId : serviceSelected){
					boolean alreadyInList = false;
					for(Appli_Service appServ : listAppServ){
						if(servId.equals(appServ.getIdServices().getId().toString())){
							alreadyInList = true;
							break;
						}
					}
					if(!alreadyInList){
						servToAdd.add(Integer.parseInt(servId));
					}
				}
			}
			
			//update application
			session.update(app);
			
			//update ref machines
			for(Integer macId : macToAdd){
				Machines_Liste mac = (Machines_Liste)session.load(Machines_Liste.class, macId);
				Machines_Applicatifs appMac = new Machines_Applicatifs();
				appMac.setApplicatif(app);
				appMac.setMachine(mac);
				session.save(appMac);
			}
			for(Machines_Applicatifs appMac : macToDelete){
				session.delete(appMac);
			}
			
			//update ref environnements
			for(Integer envId : envToAdd){
				Environnement env = (Environnement)session.load(Environnement.class, envId);
				Appli_Envir appEnv = new Appli_Envir();
				appEnv.setIdApplication(app);
				appEnv.setIdEnvironnement(env);
				session.save(appEnv);
			}
			for(Appli_Envir appEnv : envToDelete){
				session.delete(appEnv);
			}
			
			//update ref interlocuteurs
			for(Integer interlocId : interlocToAdd){
				Interlocuteur interloc = (Interlocuteur)session.load(Interlocuteur.class, interlocId);
				Appli_Entite appInterloc = new Appli_Entite();
				appInterloc.setIdApplication(app);
				appInterloc.setIdEntite(interloc);
				session.save(appInterloc);
			}
			for(Appli_Entite appInterloc : interlocToDelete){
				session.delete(appInterloc);
			}
			
			//update ref services
			for(Integer servId : servToAdd){
				Services_Liste serv = (Services_Liste)session.load(Services_Liste.class, servId);
				Appli_Service appServ = new Appli_Service();
				appServ.setIdApplication(app);
				appServ.setIdServices(serv);
				session.save(appServ);
			}
			for(Appli_Service appServ : servToDelete){
				session.delete(appServ);
			}
			
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
}
