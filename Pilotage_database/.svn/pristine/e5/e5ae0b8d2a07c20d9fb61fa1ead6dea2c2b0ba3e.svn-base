package pilotage.database.machine;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import pilotage.database.admin.ParametreDatabaseService;
import pilotage.database.applicatif.ApplicatifDatabaseService;
import pilotage.metier.Applicatifs_Liste;
import pilotage.metier.Domaine_Wind;
import pilotage.metier.Environnement;
import pilotage.metier.Interlocuteur;
import pilotage.metier.Machine_Login;
import pilotage.metier.Machine_Os;
import pilotage.metier.Machines_Applicatifs;
import pilotage.metier.Machines_Ip;
import pilotage.metier.Machines_Liste;
import pilotage.metier.Machines_Site;
import pilotage.metier.Machines_Type;
import pilotage.service.constants.PilotageConstants;
import pilotage.service.convertor.EncryptDecrypt;
import pilotage.session.PilotageSession;
import pilotage.utils.Pagination;

public class MachinesListesDatabaseService {

	/**
	 * SELECT de toutes les machines ordonnées par nom
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Machines_Liste> getAll() {
		Session session = PilotageSession.getCurrentSession();
		List<Machines_Liste> list = session.createCriteria(Machines_Liste.class).addOrder(Order.asc("nom")).list();
		session.getTransaction().commit();
		return list;
	}

	/**
	 * SELECT d'une machine
	 * @param machineSelected
	 * @return
	 */
	public static Machines_Liste get(Integer id) {
		Session session = PilotageSession.getCurrentSession();
		Machines_Liste ml = (Machines_Liste) session.createCriteria(Machines_Liste.class)
									.add(Restrictions.eq("id", id))
									.setMaxResults(1)
									.uniqueResult();
		session.getTransaction().commit();
		return ml;
	}
	
	/**
	 * SELECT de toutes les machines suivant les filtres
	 * @param pagination
	 * @param sort
	 * @param sens
	 * @param filtreNom
	 * @param filtreType
	 * @param filtreSite
	 * @param filtreOs
	 * @param filtreDomaine
	 * @param filtreInterlocuteur
	 * @param filtreEnvironnement
	 * @param filtreIP 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Machines_Liste> getAll(Pagination<Machines_Liste> pagination, String sort, String sens, String filtreNom, Integer filtreType, Integer filtreSite, Integer filtreOs, Integer filtreDomaine,Integer filtreInterlocuteur,Integer filtreEnvironnement, String filtreIP) {
		Session session = PilotageSession.getCurrentSession();
		Criteria criteria = session.createCriteria(Machines_Liste.class);
		
		if (filtreNom != null && !"".equals(filtreNom)) {
			criteria.add(Restrictions.like("nom", "%" + filtreNom + "%"));
		}
		if (filtreIP != null && !"".equals(filtreIP)) {
			List<Machines_Ip> listIP = session.createCriteria(Machines_Ip.class).add(Restrictions.like("ip", "%" + filtreIP + "%")).list();
			List<Integer> listID = new ArrayList<Integer>();
			if(listIP != null && listIP.size() > 0){
				for(Machines_Ip machIP: listIP)
					listID.add(machIP.getMachines_liste().getId());
				
				criteria.add(Restrictions.in("id", listID));
			}
			else{
				return new ArrayList<Machines_Liste>();
			}
		}
		if (filtreType != null && filtreType != -1) {
			Machines_Type machine_type = (Machines_Type)session.load(Machines_Type.class, filtreType);
			criteria.add(Restrictions.eq("id_type", machine_type));
		}
		if (filtreSite != null && filtreSite != -1)  {
			Machines_Site machine_site = (Machines_Site)session.load(Machines_Site.class, filtreSite);
			criteria.add(Restrictions.eq("id_site", machine_site));
		}
		if (filtreDomaine != null && filtreDomaine != -1) {
			Domaine_Wind domaine_wind = (Domaine_Wind)session.load(Domaine_Wind.class, filtreDomaine); 
			criteria.add(Restrictions.eq("id_domaine", domaine_wind));
		}
		if (filtreInterlocuteur != null && filtreInterlocuteur != -1) {
			Interlocuteur interloc = (Interlocuteur)session.load(Interlocuteur.class, filtreInterlocuteur);
			criteria.add(Restrictions.eq("id_interlocuteur", interloc));
		}
		if (filtreEnvironnement != null && filtreEnvironnement != -1) {
			Environnement envir = (Environnement)session.load(Environnement.class, filtreEnvironnement);
			criteria.add(Restrictions.eq("id_environnement", envir));
		}
		if (filtreOs != null && filtreOs != -1) {		
			Machine_Os machine_os = (Machine_Os)session.load(Machine_Os.class, filtreOs);
			criteria.add(Restrictions.eq("id_os", machine_os));	
		}
				
		if ("id_type".equals(sort)){ 
			criteria.createCriteria(sort, "ty", Criteria.LEFT_JOIN);
			criteria.addOrder("desc".equals(sens) ? Order.desc("ty.type") : Order.asc("ty.type"));
		}
		else if ("id_site".equals(sort)){ 
			criteria.createCriteria(sort, "si", Criteria.LEFT_JOIN);
			criteria.addOrder("desc".equals(sens) ? Order.desc("si.site") : Order.asc("si.site"));
		}
		else if ("id_os".equals(sort)){ 
			criteria.createCriteria(sort, "os", Criteria.LEFT_JOIN);
			criteria.addOrder("desc".equals(sens) ? Order.desc("os.Nom_OS") : Order.asc("os.Nom_OS"));
		}
		else if ("id_domaine".equals(sort)){ 
			criteria.createCriteria(sort, "dom", Criteria.LEFT_JOIN);
			criteria.addOrder("desc".equals(sens) ? Order.desc("dom.nomDomaine") : Order.asc("dom.nomDomaine"));
		}
		else if ("id_interlocuteur".equals(sort)){ 
			criteria.createCriteria(sort, "interloc", Criteria.LEFT_JOIN);
			criteria.addOrder("desc".equals(sens) ? Order.desc("interloc.nomService") : Order.asc("interloc.nomService"));
		}
		else if ("id_environnement".equals(sort)){ 
			criteria.createCriteria(sort, "envir", Criteria.LEFT_JOIN);
			criteria.addOrder("desc".equals(sens) ? Order.desc("envir.environnement") : Order.asc("envir.environnement"));
		}
		else{
			criteria.addOrder("desc".equals(sens) ? Order.desc(sort) : Order.asc(sort));
		}

		List<Machines_Liste> listMachines = pagination.setCriteriaPagination(criteria);
		session.getTransaction().commit();
		return listMachines;
	}

	/**
	 * SELECT INTER de toutes mes machines attachées à la liste des applicatifs en paramètre
	 * @param listAppID
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Machines_Liste> getIntersectionByAppList(List<Integer> listAppID) {
		List<Applicatifs_Liste> apps = ApplicatifDatabaseService.getMultiple(listAppID);
		List<Machines_Liste> listMachines = new ArrayList<Machines_Liste>();
		
		if(apps != null && !apps.isEmpty()){
			Session session = PilotageSession.getCurrentSession();
			for(Applicatifs_Liste al : apps){
				List<Machines_Applicatifs> listMachineAppli = session.createCriteria(Machines_Applicatifs.class)
													.add(Restrictions.eq("applicatif", al))
													.createAlias("machine", "machine", Criteria.LEFT_JOIN)
													.addOrder(Order.asc("machine.nom"))
													.list();
				
				//première appli
				if(listMachines.isEmpty()){
					for(Machines_Applicatifs ma : listMachineAppli){
						listMachines.add(ma.getMachine());
					}
				}
				//intersection des applis précédentes avec l'actuelle
				else{
					List<Machines_Liste> machinesTemp = new ArrayList<Machines_Liste>(); // va contenir l'inter entre les applis précédentes et l'appli actuelle
					for(Machines_Applicatifs ma : listMachineAppli){
						if(listMachines.contains(ma.getMachine()))
							machinesTemp.add(ma.getMachine());
					}
					listMachines = machinesTemp;
				}
				
				//si l'intersection des appli jusqu'à maintenant est vide, pas la peine de continuer
				if(listMachines.isEmpty()){
					break;
				}
			}
			session.getTransaction().commit();
		}
		return listMachines;
	}

	/**
	 * SELECT UNION de toutes les machines attachées à la liste des applicatifs en paramètre
	 * @param listAppID
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Machines_Liste> getUnionByAppList(List<Integer> listAppID) {
		List<Applicatifs_Liste> apps = ApplicatifDatabaseService.getMultiple(listAppID);
		List<Machines_Liste> listMachines = new ArrayList<Machines_Liste>();
		if(apps != null && !apps.isEmpty()){
			Session session = PilotageSession.getCurrentSession();
			
			List<Machines_Applicatifs> listMachineAppli = session.createCriteria(Machines_Applicatifs.class)
														.add(Restrictions.in("applicatif", apps))
														.createAlias("machine", "machine", Criteria.LEFT_JOIN)
														.addOrder(Order.asc("machine.nom"))
														.list();
			session.getTransaction().commit();
			for(Machines_Applicatifs ma : listMachineAppli){
				if(!listMachines.contains(ma.getMachine())){
					listMachines.add(ma.getMachine());
				}
			}
			
		}

		return listMachines;
	}

	/**
	 * COUNT test si une machine référence un environnement
	 * @param envID
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static boolean hasEnvironnement(Integer envID)throws Exception{
		Session session = PilotageSession.getCurrentSession();
		
		Environnement environnement = (Environnement) session.load(Environnement.class, envID);
		List<Long> results = session.createCriteria(Machines_Liste.class)
						          .add(Restrictions.eq("id_environnement", environnement))
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
	 * COUNT teste si le type est affecté à des machines
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static boolean hasMachineWithType(int id){
		Session session = PilotageSession.getCurrentSession();
		
		Machines_Type mt = (Machines_Type)session.load(Machines_Type.class, id);
		List<Long> results = (List<Long>)session.createCriteria(Machines_Liste.class)
									.add(Restrictions.eq("id_type", mt))
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
	 * COUNT teste si l'os est affecté à des machines
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static boolean hasMachineWithOs(int id){
		Session session = PilotageSession.getCurrentSession();
		
		Machine_Os mo = (Machine_Os)session.load(Machine_Os.class, id);
		List<Long> results= (List<Long>)session.createCriteria(Machines_Liste.class)
									.add(Restrictions.eq("id_os", mo))
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
	 * COUNT teste si le domaine est affecté à des machines
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static boolean hasMachineWithDomaine(int id){
		Session session = PilotageSession.getCurrentSession();
		
		Domaine_Wind dw = (Domaine_Wind)session.load(Domaine_Wind.class, id);
		List<Long> results= (List<Long>)session.createCriteria(Machines_Liste.class)
									.add(Restrictions.eq("id_domaine", dw))
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
	 * COUNT teste si le site est affecté à des machines
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static boolean hasMachineWithSite(int id){
		Session session = PilotageSession.getCurrentSession();
		
		Machines_Site ms = (Machines_Site)session.load(Machines_Site.class, id);
		List<Long> results= (List<Long>)session.createCriteria(Machines_Liste.class)
									.add(Restrictions.eq("id_site", ms))
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
	 * COUNT teste si l'interlocuteur est affecté à des machines
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static boolean hasMachineWithInterlocuteur(int id){
		Session session = PilotageSession.getCurrentSession();
		
		Interlocuteur inter = (Interlocuteur)session.load(Interlocuteur.class, id);
		List<Long> results= (List<Long>)session.createCriteria(Machines_Liste.class)
									.add(Restrictions.eq("id_interlocuteur", inter))
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
	 * COUNT test si le nom de machine existe déjà
	 * @param id - id à ne pas controler
	 * @param libelle
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static boolean exists(Integer id, String libelle){
		Session session = PilotageSession.getCurrentSession();
		
		Criteria criteria = session.createCriteria(Machines_Liste.class);
		criteria.add(Restrictions.eq("nom", libelle));
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
	 * INSERT d'une machine avec les machines_applicatifs, machines_ip et machines_login
	 * @param nom
	 * @param type
	 * @param site
	 * @param domaine
	 * @param interlocuteur
	 * @param environnement
	 * @param os
	 * @param commentaire
	 * @param listApplications
	 * @param listIPs
	 * @param listAccesLocal
	 * @throws Exception
	 */
	public static void create(String nom, Integer type, Integer site, Integer domaine, Integer interlocuteur, Integer environnement, Integer os, String commentaire,
			List<Map<String, String>> listApplications, List<Map<String, String>> listIPs, List<Map<String, String>> listAccesLocal) throws Exception {
		
		Session session = PilotageSession.getCurrentSession();
		try{		
			//machines_liste
			Machines_Type machine_type = (Machines_Type)session.load(Machines_Type.class, type);
			Machines_Site machine_site = (Machines_Site)session.load(Machines_Site.class, site);
			Domaine_Wind domaine_wind = (Domaine_Wind)session.load(Domaine_Wind.class, domaine);
			Machine_Os machine_os = (Machine_Os)session.load(Machine_Os.class, os);
			
			Interlocuteur interloc = null;
			if (interlocuteur != null && interlocuteur != -1) {
				interloc = (Interlocuteur)session.load(Interlocuteur.class, interlocuteur);	
			}
			Environnement envir = null;
			if (environnement != null && environnement != -1) {
				envir = (Environnement)session.load(Environnement.class, environnement);				
			}
		
			Machines_Liste machine = new Machines_Liste();
			machine.setNom(nom);
			machine.setId_type(machine_type);
			machine.setId_site(machine_site);
			machine.setId_domaine(domaine_wind);
			machine.setId_environnement(envir);
			machine.setId_interlocuteur(interloc);
			machine.setId_os(machine_os);
			machine.setCommentaire(commentaire);
	
			//IP
			List<Machines_Ip> listMachinesIP = new ArrayList<Machines_Ip>();
			for(Map<String, String> ligneIP : listIPs){
				Machines_Ip mi = new Machines_Ip();
				mi.setIp(ligneIP.get("IP"));
				mi.setCommentaire(ligneIP.get("Commentaire"));
				mi.setMachines_liste(machine);
				
				listMachinesIP.add(mi);
			}
			
			//Applicatifs
			List<Machines_Applicatifs> listMachinesApplicatifs = new ArrayList<Machines_Applicatifs>();
			for(Map<String, String> ligneApplicatif : listApplications){
				Applicatifs_Liste applicatifs_liste = (Applicatifs_Liste) session.load(Applicatifs_Liste.class, Integer.parseInt(ligneApplicatif.get("appID")));
				Machines_Applicatifs ma = new Machines_Applicatifs();
				ma.setApplicatif(applicatifs_liste);
				ma.setMachine(machine);
				
				listMachinesApplicatifs.add(ma);
			}
			
			//login
			List<Machine_Login> listMachinesLogin = new ArrayList<Machine_Login>();
			String key;
			String mdpCrypte;
			for(Map<String, String> ligneLogin : listAccesLocal){
				Domaine_Wind ligneDomaine = null;
				if (ligneLogin.get("domaine") != null){
					ligneDomaine = (Domaine_Wind) session.load(Domaine_Wind.class, Integer.parseInt(ligneLogin.get("domaine")));
				} 		
				Machine_Login ml = new Machine_Login();
				ml.setLogin(ligneLogin.get("login"));
				key = ParametreDatabaseService.get(PilotageConstants.CLE_ENCODAGE).getValeur();
				mdpCrypte = EncryptDecrypt.encryptBlowfish(ligneLogin.get("password"), key);
				ml.setPassword(mdpCrypte);
				ml.setDomaine_wind(ligneDomaine);
				ml.setMachines_liste(machine);
				
				listMachinesLogin.add(ml);
			}
			
			//sauvegarde en base
			session.save(machine);
			for(Machines_Ip ip : listMachinesIP){
				session.save(ip);
			}

			for(Machines_Applicatifs appli : listMachinesApplicatifs){
				session.save(appli);
			}
			
			for(Machine_Login login : listMachinesLogin){
				session.save(login);
			}
			
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}

	/**
	 * UPDATE d'une machine avec les machines_applicatifs, machines_ip et machines_login
	 * @param selectRow
	 * @param nom
	 * @param type
	 * @param site
	 * @param domaine
	 * @param interlocuteur
	 * @param environnement
	 * @param os
	 * @param commentaire
	 * @param appliToAdd
	 * @param appliToDelete
	 * @param ipToAdd
	 * @param ipToUpdate
	 * @param ipToDelete
	 * @param loginToAdd
	 * @param loginToUpdate
	 * @param loginToDelete
	 * @throws Exception 
	 */
	public static void modify(Integer selectRow, String nom, Integer type,
			Integer site, Integer domaine, Integer interlocuteur, Integer environnement, Integer os, String commentaire,
			List<Integer> appliToAdd, List<Integer> appliToDelete, 
			List<Map<String, String>> ipToAdd, List<Map<String, String>> ipToUpdate, List<Integer> ipToDelete,
			List<Map<String, String>> loginToAdd, List<Map<String, String>> loginToUpdate, List<Integer> loginToDelete) throws Exception {

		Session session = PilotageSession.getCurrentSession();
		try{
			//Machine
			Machines_Liste machine = (Machines_Liste)session.load(Machines_Liste.class, selectRow);
			Machines_Type machine_type = (Machines_Type)session.load(Machines_Type.class, type);
			Machines_Site machine_site = (Machines_Site)session.load(Machines_Site.class, site);
			Domaine_Wind domaine_wind = (Domaine_Wind)session.load(Domaine_Wind.class, domaine);
			Machine_Os machine_os = (Machine_Os)session.load(Machine_Os.class, os);
			Interlocuteur interloc = null;
			if (interlocuteur != null && interlocuteur != -1) {
				interloc = (Interlocuteur)session.load(Interlocuteur.class, interlocuteur);	
			}
			Environnement envir = null;
			if (environnement != null && environnement != -1) {
				envir = (Environnement)session.load(Environnement.class, environnement);				
			}

			machine.setNom(nom);
			machine.setId_type(machine_type);
			machine.setId_site(machine_site);
			machine.setId_domaine(domaine_wind);
			machine.setId_environnement(envir);
			machine.setId_interlocuteur(interloc);
			machine.setId_os(machine_os);
			machine.setCommentaire(commentaire);
			
			//applis
			List<Machines_Applicatifs> listMachinesAppliToAdd = new ArrayList<Machines_Applicatifs>();
			List<Machines_Applicatifs> listMachinesAppliToDelete = new ArrayList<Machines_Applicatifs>();
			for(Integer appliID : appliToAdd){
				Applicatifs_Liste appli = (Applicatifs_Liste)session.load(Applicatifs_Liste.class, appliID);
				Machines_Applicatifs machAppli = new Machines_Applicatifs();
				machAppli.setApplicatif(appli);
				machAppli.setMachine(machine);
				
				listMachinesAppliToAdd.add(machAppli);
			}
			for(Integer appliID : appliToDelete){
				Applicatifs_Liste appli = (Applicatifs_Liste)session.load(Applicatifs_Liste.class, appliID);
				Machines_Applicatifs machAppli = (Machines_Applicatifs)session.createCriteria(Machines_Applicatifs.class)
																	.add(Restrictions.eq("applicatif", appli))
																	.add(Restrictions.eq("machine", machine))
																	.setMaxResults(1)
																	.uniqueResult();
				listMachinesAppliToDelete.add(machAppli);
			}
			
			//ips
			List<Machines_Ip> listMachinesIPToAdd = new ArrayList<Machines_Ip>();
			List<Machines_Ip> listMachinesIPToUpdate = new ArrayList<Machines_Ip>();
			List<Machines_Ip> listMachinesIPToDelete = new ArrayList<Machines_Ip>();
			for(Map<String, String> ipMap : ipToAdd){
				Machines_Ip machIP = new Machines_Ip();
				machIP.setMachines_liste(machine);
				machIP.setIp(ipMap.get("IP"));
				machIP.setCommentaire(ipMap.get("Commentaire"));
				
				listMachinesIPToAdd.add(machIP);
			}
			for(Map<String, String> ipMap : ipToUpdate){
				Machines_Ip machIP = (Machines_Ip)session.load(Machines_Ip.class, Integer.parseInt(ipMap.get("id")));
				machIP.setIp(ipMap.get("IP"));
				machIP.setCommentaire(ipMap.get("Commentaire"));
				
				listMachinesIPToUpdate.add(machIP);
			}
			for(Integer ipID : ipToDelete){
				Machines_Ip machIP = (Machines_Ip)session.load(Machines_Ip.class, ipID);
				listMachinesIPToDelete.add(machIP);
			}
			
			//logins
			List<Machine_Login> listMachineLoginToAdd = new ArrayList<Machine_Login>();
			List<Machine_Login> listMachineLoginToUpdate = new ArrayList<Machine_Login>();
			List<Machine_Login> listMachineLoginToDelete = new ArrayList<Machine_Login>();
			String key;
			String mdpCrypte;
			for(Map<String, String> loginMap : loginToAdd){
				Domaine_Wind ligneDomaine = null;
				if (loginMap.get("domaine") != null){
					ligneDomaine = (Domaine_Wind) session.load(Domaine_Wind.class, Integer.parseInt(loginMap.get("domaine")));
				} 
				Machine_Login machLogin = new Machine_Login();
				machLogin.setMachines_liste(machine);
				machLogin.setLogin(loginMap.get("login"));
				key = ParametreDatabaseService.get(PilotageConstants.CLE_ENCODAGE).getValeur();
				mdpCrypte = EncryptDecrypt.encryptBlowfish(loginMap.get("password"), key);
				machLogin.setPassword(mdpCrypte);
				machLogin.setDomaine_wind(ligneDomaine);
				
				listMachineLoginToAdd.add(machLogin);
			}
			for(Map<String, String> loginMap : loginToUpdate){
				Domaine_Wind ligneDomaine = null;
				if (loginMap.get("domaine") != null){
					ligneDomaine = (Domaine_Wind) session.load(Domaine_Wind.class, Integer.parseInt(loginMap.get("domaine")));
				} 
				Machine_Login machLogin = (Machine_Login)session.load(Machine_Login.class, Integer.parseInt(loginMap.get("id")));
				machLogin.setLogin(loginMap.get("login"));
				key = ParametreDatabaseService.get(PilotageConstants.CLE_ENCODAGE).getValeur();
				mdpCrypte = EncryptDecrypt.encryptBlowfish(loginMap.get("password"), key);
				machLogin.setPassword(mdpCrypte);
				machLogin.setDomaine_wind(ligneDomaine);
				
				listMachineLoginToUpdate.add(machLogin);
			}
			for(Integer loginID : loginToDelete){
				Machine_Login machLogin = (Machine_Login)session.load(Machine_Login.class, loginID);
				
				listMachineLoginToDelete.add(machLogin);
			}
			
			//sauvegarde en base
			for(Machine_Login machLogin : listMachineLoginToAdd) {
				session.save(machLogin);
			}
			for(Machine_Login machLogin : listMachineLoginToUpdate) {
				session.update(machLogin);
			}
			for(Machine_Login machLogin : listMachineLoginToDelete) {
				session.delete(machLogin);
			}
			for(Machines_Ip machIP : listMachinesIPToAdd) {
				session.save(machIP);
			}
			for(Machines_Ip machIP : listMachinesIPToUpdate) {
				session.update(machIP);
			}
			for(Machines_Ip machIP : listMachinesIPToDelete) {
				session.delete(machIP);
			}
			for(Machines_Applicatifs machAppli : listMachinesAppliToAdd) {
				session.save(machAppli);
			}
			for(Machines_Applicatifs machAppli : listMachinesAppliToDelete) {
				session.delete(machAppli);
			}
			session.save(machine);
			
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	/**
	 * DELETE d'une machine avec ces machines_applicatif, machines_ip et machine_login
	 * @param selectRow
	 * @throws Exception 
	 */
	public static void delete(Integer selectRow) throws Exception {
		
		List<Machines_Applicatifs> listMachinesApplicatifs = MachineApplicatifDatabaseService.getApplicatifsFromMachine(selectRow);
		List<Machines_Ip> listMachinesIP = MachineIpDatabaseService.getIPFromMachine(selectRow);
		List<Machine_Login> listMachineLogin = MachineLoginDatabaseService.getLoginsFromMachine(selectRow);
		
		Session session = PilotageSession.getCurrentSession();
		try{
			Machines_Liste machine = (Machines_Liste)session.load(Machines_Liste.class, selectRow);
			
			for(Machines_Applicatifs appli : listMachinesApplicatifs){
				session.delete(appli);
			}
			for(Machines_Ip ip : listMachinesIP){
				session.delete(ip);
			}
			for(Machine_Login login : listMachineLogin){
				session.delete(login);
			}
			session.delete(machine);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}	
	}
	
	public static Integer getId(String nom){
		Session session = PilotageSession.getCurrentSession();
		Machines_Liste ml = (Machines_Liste)session.createCriteria(Machines_Liste.class)
									.add(Restrictions.eq("nom", nom))
									.setMaxResults(1)
									.uniqueResult();
		session.getTransaction().commit();
		return ml.getId();
	}
}
