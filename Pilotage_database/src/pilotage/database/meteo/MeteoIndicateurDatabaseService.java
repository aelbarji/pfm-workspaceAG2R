package pilotage.database.meteo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import pilotage.metier.Heures_Oceor;
import pilotage.metier.Meteo_Environnement;
import pilotage.metier.Meteo_Indicateur;
import pilotage.metier.Meteo_PlageFonct_Jour;
import pilotage.metier.Meteo_PlageFonctionnement;
import pilotage.metier.Meteo_Service;
import pilotage.metier.Meteo_SeuilPlage;
import pilotage.metier.Meteo_TypeIndicateur;
import pilotage.service.date.DateService;
import pilotage.session.PilotageSession;

public class MeteoIndicateurDatabaseService {

	/**
	 * SELECT de tous les indicateurs pour page détail
	 * @param service
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Meteo_Indicateur> getListIndicByService(Meteo_Service service){
		Session session = PilotageSession.getCurrentSession();
		
		List<Meteo_Indicateur> indics = session.createCriteria(Meteo_Indicateur.class)
										.add(Restrictions.eq("service", service))
										.list(); 
		
		session.getTransaction().commit();
		return indics;
	}
	
	/**
	 * SELECT de tous les plages de fonctionnement pour page détail
	 * @param indicateur
	 * @return
	 */	
	@SuppressWarnings("unchecked")
	public static List<Meteo_PlageFonctionnement> getListPlageFonctionnement(Meteo_Indicateur indic){
		Session session = PilotageSession.getCurrentSession();
		List<Meteo_PlageFonctionnement> plages = (List<Meteo_PlageFonctionnement>)session.createCriteria(Meteo_PlageFonctionnement.class)
										.add(Restrictions.eq("indicateur", indic))
										.list(); 
		session.getTransaction().commit();
		return plages;
	}
	
	/**
	 * SELECT de tous les plages de fonctionnement pour page détail
	 * @param indicateur
	 * @return
	 */	
	@SuppressWarnings("unchecked")
	public static List<Meteo_PlageFonctionnement> getListPlageFonctionnement2(Meteo_Indicateur indic, Date datetime){
		Session session = PilotageSession.getCurrentSession();
		List<Meteo_PlageFonctionnement> plages = (List<Meteo_PlageFonctionnement>)session.createCriteria(Meteo_PlageFonctionnement.class)
										.add(Restrictions.eq("indicateur", indic))
										.add(Restrictions.and(Restrictions.le("date_creation",datetime), 
												Restrictions.or(Restrictions.isNull("date_suppression"),Restrictions.gt("date_suppression",datetime))))
										.list(); 
		session.getTransaction().commit();
		return plages;
	}
	
	
	/**
	 * SELECT de tous les plages de fonctionnement pour page détail
	 * @param indicateur
	 * @return
	 */	
	@SuppressWarnings("unchecked")
	public static List<Meteo_PlageFonctionnement> getListPlageActive(Meteo_Indicateur indic){
		Session session = PilotageSession.getCurrentSession();
		List<Meteo_PlageFonctionnement> plages = session.createCriteria(Meteo_PlageFonctionnement.class)
										.add(Restrictions.eq("indicateur", indic))
										.add(Restrictions.isNull("date_suppression"))
										.list(); 
		session.getTransaction().commit();
		return plages;
	}
	
	/**
	 * UPDATE d'un indicateur
	 * @param idService
	 * @param listIndicateur
	 * @throws Exception 
	 */
	public static void modifyIndicateur(Integer selectRow, List<Map<String,String>> indicToChange, List<Integer> indicToDelete) throws Exception{
		Session session = PilotageSession.getCurrentSession();
		try{
			List<Meteo_Indicateur> listIndicToChange = new ArrayList<Meteo_Indicateur>();
			
			for(Map<String,String> indicID : indicToChange){
				Meteo_Indicateur indic = (Meteo_Indicateur)session.load(Meteo_Indicateur.class, Integer.parseInt(indicID.get("idIndic")));
				indic.setAutomatique(Integer.parseInt(indicID.get("auto")));
				if(indicID.get("debut")!=null) indic.setHeureDebut(Integer.parseInt(indicID.get("debut")));
				if(indicID.get("fin")!=null) indic.setHeureFin(Integer.parseInt(indicID.get("fin")));
				listIndicToChange.add(indic);
			}	
			
			Date datesupp = new Date();
			for(Integer indicID : indicToDelete){
				Meteo_Indicateur indic = (Meteo_Indicateur)session.load(Meteo_Indicateur.class, indicID);
				indic.setDateSuppression(datesupp);
			}
			
			for(Meteo_Indicateur indic : listIndicToChange) {
				session.save(indic);
			}

			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	/**
	 * CREATE d'un indicateur
	 * @param service
	 * @param environnement
	 * @param type
	 * @param automatique
	 * @param liste des plages de fonctionnements
	 * @param heure de début
	 * @param heure de fin
	 * @throws Exception 
	 */
	public static void createIndicateur(Meteo_Service service, Meteo_Environnement envir, Meteo_TypeIndicateur type, int auto, List<Map<String,String>> listPlage, int heure_debut, int heure_fin)throws Exception{
		Session session = PilotageSession.getCurrentSession();
		try{
			Date date = new Date();
			Meteo_Indicateur indicateur = new Meteo_Indicateur();
			indicateur.setEnvironnement(envir);
			indicateur.setService(service);
			indicateur.setTypeIndic(type);
			indicateur.setAutomatique(auto);
			indicateur.setDateCreation(date);
			indicateur.setHeureDebut(heure_debut);
			indicateur.setHeureFin(heure_fin);
			session.save(indicateur);
			
			for(Map<String,String> plage: listPlage){
				Meteo_PlageFonctionnement p = new Meteo_PlageFonctionnement();
				Heures_Oceor zone = (Heures_Oceor) session.load(Heures_Oceor.class, Integer.parseInt(plage.get("zone")));
				Date heureDebut=DateService.getTimeFromString(plage.get("heureDebut"));
				Date heureFin=DateService.getTimeFromString(plage.get("heureFin"));
				p.setEtatDesire(plage.get("etatDesire"));
				
				Meteo_SeuilPlage seuil = null;
				if(type.getFormat().getFormat().equals("reel")){
					seuil = (Meteo_SeuilPlage)session.createCriteria(Meteo_SeuilPlage.class)
							.add(Restrictions.eq("pertu_max", plage.get("perturbe_max")))
							.add(Restrictions.eq("pertu_min", plage.get("perturbe_min")))
							.add(Restrictions.eq("ko_max", plage.get("ko_max")))
							.add(Restrictions.eq("ko_min", plage.get("ko_min")))
							.setMaxResults(1)
							.uniqueResult();
					
					if(seuil == null){
						seuil = new Meteo_SeuilPlage();
						seuil.setPertu_max(plage.get("perturbe_max"));
						seuil.setPertu_min(plage.get("perturbe_min"));
						seuil.setKo_max(plage.get("ko_max"));
						seuil.setKo_min(plage.get("ko_min"));
						session.save(seuil);
					}
				}
				
				p.setSeuil(seuil);
				
				p.setHeureDebut(heureDebut);
				p.setHeureFin(heureFin);
				p.setIndicateur(indicateur);
				p.setZone(zone);
				p.setDate_creation(date);
				session.save(p);
				
				List<Integer> jour = new ArrayList<Integer>();
				if(plage.get("lundi")!=null && plage.get("lundi").equals("on")){
					jour.add(1);
				}
				if(plage.get("mardi")!=null && plage.get("mardi").equals("on")){
					jour.add(2);
				}
				if(plage.get("mercredi")!=null && plage.get("mercredi").equals("on")){
					jour.add(3);
				}
				if(plage.get("jeudi")!=null && plage.get("jeudi").equals("on")){
					jour.add(4);
				}
				if(plage.get("vendredi")!=null && plage.get("vendredi").equals("on")){
					jour.add(5);
				}
				if(plage.get("samedi")!=null && plage.get("samedi").equals("on")){
					jour.add(6);
				}
				if(plage.get("dimanche")!=null && plage.get("dimanche").equals("on")){
					jour.add(0);
				}
				
				int ferie;
				if(plage.get("ferie")!=null && plage.get("ferie").equals("on")){
					ferie=1;
				}else{
					ferie=0;
				}
				
				for(int j : jour){
					Meteo_PlageFonct_Jour plageJour = new Meteo_PlageFonct_Jour();
					plageJour.setJour(j);
					plageJour.setPlageFonct(p);
					plageJour.setFerie(ferie);
					session.save(plageJour);
				}
			}
			
			session.getTransaction().commit();
			
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	/**
	 * SELECT de l'indicateur selon id
	 * @param idIndic
	 * @return
	 */
	public static Meteo_Indicateur get(Integer idIndic){
		Session session = PilotageSession.getCurrentSession();	
		Meteo_Indicateur indic = (Meteo_Indicateur)session.load(Meteo_Indicateur.class, idIndic);	
		return indic;
	}
	
	/**
	 * UPDATE d'un indicateur
	 * @param idService
	 * @param listIndicateur
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public static void modifyPlageFonctionnement(Integer idIndic, List<Map<String,String>> listPlage) throws Exception{
		Session session = PilotageSession.getCurrentSession();
		try{
			Meteo_Indicateur indicateur = (Meteo_Indicateur)session.load(Meteo_Indicateur.class, idIndic);	
			
			List<Meteo_PlageFonctionnement> plages = session.createCriteria(Meteo_PlageFonctionnement.class)
											.add(Restrictions.eq("indicateur", indicateur))
											.list();
			
			Date date = new Date();
			for(Meteo_PlageFonctionnement pl : plages){
				if(pl.getDate_suppression()==null){
					pl.setDate_suppression(date);
					session.update(pl);
				}
			}
			
			List<Meteo_PlageFonctionnement> plageToAdd = new ArrayList<Meteo_PlageFonctionnement>();

			for(Map<String,String> s : listPlage){
					Meteo_PlageFonctionnement p = new Meteo_PlageFonctionnement();
					if(indicateur.getTypeIndic().getId()==0){
						
						Meteo_SeuilPlage seuil_exist = (Meteo_SeuilPlage)session.createCriteria(Meteo_SeuilPlage.class)
														.add(Restrictions.eq("pertu_max", s.get("perturbe_max")))
														.add(Restrictions.eq("pertu_min", s.get("perturbe_min")))
														.add(Restrictions.eq("ko_max", s.get("ko_max")))
														.add(Restrictions.eq("ko_min", s.get("ko_min")))
														.setMaxResults(1)
														.uniqueResult();
						if(seuil_exist == null){
							Meteo_SeuilPlage seuil_new = new Meteo_SeuilPlage();
							seuil_new.setPertu_max(s.get("perturbe_max"));
							seuil_new.setPertu_min(s.get("perturbe_min"));
							seuil_new.setKo_max(s.get("ko_max"));
							seuil_new.setKo_min(s.get("ko_min"));
							session.save(seuil_new);
							p.setSeuil(seuil_new);
						}else{
							Meteo_SeuilPlage seuil = getSeuil(seuil_exist.getId(), session);
							p.setSeuil(seuil);
						}
					}
					p.setIndicateur(indicateur);
					p.setDate_creation(date);
					p.setEtatDesire(s.get("etatDesire"));
					p.setHeureDebut(DateService.getTimeFromString(s.get("heureDebut")));
					p.setHeureFin(DateService.getTimeFromString(s.get("heureFin")));
					Heures_Oceor zone = (Heures_Oceor) session.load(Heures_Oceor.class, Integer.parseInt(s.get("zone")));
					p.setZone(zone);
					plageToAdd.add(p);
					session.save(p);
					
					List<Integer> jour = new ArrayList<Integer>();
					if(s.get("lundi")!=null && s.get("lundi").equals("on")){
						jour.add(1);
					}
					if(s.get("mardi")!=null && s.get("mardi").equals("on")){
						jour.add(2);
					}
					if(s.get("mercredi")!=null && s.get("mercredi").equals("on")){
						jour.add(3);
					}
					if(s.get("jeudi")!=null && s.get("jeudi").equals("on")){
						jour.add(4);
					}
					if(s.get("vendredi")!=null && s.get("vendredi").equals("on")){
						jour.add(5);
					}
					if(s.get("samedi")!=null && s.get("samedi").equals("on")){
						jour.add(6);
					}
					if(s.get("dimanche")!=null && s.get("dimanche").equals("on")){
						jour.add(0);
					}
					
					int ferie;
					if(s.get("ferie")!=null && s.get("ferie").equals("on")){
						ferie=1;
					}else{
						ferie=0;
					}
					
					for(int j : jour){
						Meteo_PlageFonct_Jour plageJour = new Meteo_PlageFonct_Jour();
						plageJour.setJour(j);
						plageJour.setPlageFonct(p);
						plageJour.setFerie(ferie);
						session.save(plageJour);
					}
			}
			
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	/**
	 * SELECT de l'indicateur selon le service et l'environnement
	 * @param service
	 * @param environnement
	 * @return
	 */
	public static Meteo_Indicateur getIndicateurByServiceAndEnvir(Meteo_Service service, Meteo_Environnement environnement){
		Session session = PilotageSession.getCurrentSession();
		Meteo_Indicateur indicateur = (Meteo_Indicateur)session.createCriteria(Meteo_Indicateur.class)
										.add(Restrictions.eq("service", service))
										.add(Restrictions.eq("environnement", environnement))
										.setMaxResults(1)
										.uniqueResult();
		session.getTransaction().commit();
		return indicateur;
	}
	
	/**
	 * SELECT des jours de fonctionnement de la plage
	 * @param plage
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Meteo_PlageFonct_Jour> getListJoursByPlage(Meteo_PlageFonctionnement plage){
		Session session = PilotageSession.getCurrentSession();
				
		List<Meteo_PlageFonct_Jour> jours = session.createCriteria(Meteo_PlageFonct_Jour.class)
										.add(Restrictions.eq("plageFonct", plage))
										.list(); 
		session.getTransaction().commit();
		return jours;
	}
	
	/**
	 * SELECT du seuil selon id
	 * @param id
	 * @return
	 */
	public static Meteo_SeuilPlage getSeuil(Integer idSeuil, Session session){
		int ouvert = 1;
		if(session == null || !session.isOpen()){
			session = PilotageSession.getCurrentSession();
			ouvert = 0;
		}
		Meteo_SeuilPlage seuil =  (Meteo_SeuilPlage)session.get(Meteo_SeuilPlage.class, idSeuil);
		if(ouvert == 0) session.getTransaction().commit();
		return seuil;
	}

	@SuppressWarnings("unchecked")
	public static List<Meteo_Indicateur> getByService(Meteo_Service service){
		Session session = PilotageSession.getCurrentSession();
		List<Meteo_Indicateur> listIndic =  session.createCriteria(Meteo_Indicateur.class)
				.add(Restrictions.eq("service", service))
				.addOrder(Order.asc("environnement.id"))
				.list();
		return listIndic;
	}
}
