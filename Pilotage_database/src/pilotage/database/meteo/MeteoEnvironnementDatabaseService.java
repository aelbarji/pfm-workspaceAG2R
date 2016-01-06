package pilotage.database.meteo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import pilotage.metier.Meteo_Caisse;
import pilotage.metier.Meteo_Environnement;
import pilotage.metier.Meteo_Environnement_Caisse;
import pilotage.metier.Meteo_Environnement_Place;
import pilotage.metier.Meteo_Indicateur;
import pilotage.metier.Meteo_Meteo;
import pilotage.metier.Meteo_Service;
import pilotage.session.PilotageSession;
import pilotage.utils.Pagination;

public class MeteoEnvironnementDatabaseService {

	/**
	 * SELECT de toutes les environnements, ordonnés par nom
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Meteo_Environnement> getAll() {
		Session session = PilotageSession.getCurrentSession();
		List<Meteo_Environnement> envirList = (List<Meteo_Environnement>)session.createCriteria(Meteo_Environnement.class)
													.addOrder(Order.asc("nom_envir"))
													.list();
		session.getTransaction().commit();
		return envirList;
	}
	
	/**
	 * SELECT de la liste des environnements
	 * @param pagination
	 * @return
	 */
	public static List<Meteo_Environnement> getAll(Pagination<Meteo_Environnement> pagination){
		Session session = PilotageSession.getCurrentSession();
		Criteria criteria = session.createCriteria(Meteo_Environnement.class)
											.addOrder(Order.asc("nom_envir"));
		List<Meteo_Environnement> envirList = pagination.setCriteriaPagination(criteria); 
		session.getTransaction().commit();
		return envirList;
	}
	
	/**
	 * INSERT d'un environnement
	 * @param nom
	 * @throws Exception 
	 */
	public static void create(String nom, String tech, List<Map<String,String>> listCaisse, Integer impact) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			Meteo_Environnement envir = new Meteo_Environnement();
			envir.setNom_envir(nom);
			int technique;
			technique=Integer.parseInt(tech);
			envir.setTechnique(technique);
			envir.setImpact(impact);
			session.save(envir);
			
			//Caisses
			List<Meteo_Environnement_Caisse> listEnvirCaisse = new ArrayList<Meteo_Environnement_Caisse>();
			for(Map<String, String> ligneCaisse : listCaisse){
				Meteo_Caisse caisse = (Meteo_Caisse) session.load(Meteo_Caisse.class, Integer.parseInt(ligneCaisse.get("caisseID")));
				Meteo_Environnement_Caisse ec = new Meteo_Environnement_Caisse();
				ec.setCaisse(caisse);
				ec.setEnvir(envir);
				
				listEnvirCaisse.add(ec);
			}
	
			for(Meteo_Environnement_Caisse envirCaisse : listEnvirCaisse){
				session.save(envirCaisse);
			}
	
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}	
	}
	
	/**
	 * SELECT d'un environnement avec son id
	 * @param selectRow
	 * @return
	 */
	public static Meteo_Environnement get(Integer selectRow) {
		Session session = PilotageSession.getCurrentSession();
		Meteo_Environnement envir = (Meteo_Environnement)session.createCriteria(Meteo_Environnement.class)
																	.add(Restrictions.eq("id", selectRow))
																	.setMaxResults(1)
																	.uniqueResult();
		session.getTransaction().commit();
		return envir;
	}
	
	/**
	 * SELECT d'un environnement avec son nom
	 * @param nom
	 * @return
	 */
	public static Meteo_Environnement getByName(String nom) {
		Session session = PilotageSession.getCurrentSession();
		Meteo_Environnement envir = (Meteo_Environnement)session.createCriteria(Meteo_Environnement.class)
																	.add(Restrictions.eq("nom_envir", nom))
																	.setMaxResults(1)
																	.uniqueResult();
		session.getTransaction().commit();
		return envir;
	}
	
	/**
	 * UPDATE d'un environnement
	 * @param id
	 * @param nom
	 * @throws Exception 
	 */
	public static void modify(Integer id, String nom_envir, int technique) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		
		try{
			Meteo_Environnement envir = (Meteo_Environnement)session.load(Meteo_Environnement.class, id);
			if(nom_envir != null)
				envir.setNom_envir(nom_envir);

			envir.setTechnique(technique);
			
			session.update(envir);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	/**
	 * DELETE d'un environnement
	 * @param selectRow
	 * @throws Exception 
	 */
	public static void delete(int selectRow) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			Meteo_Environnement envir = (Meteo_Environnement)session.load(Meteo_Environnement.class, selectRow);
			session.delete(envir);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	/**
	 * SELECT de la liste des environnements selon liste de services
	 * @param listServices
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Meteo_Environnement> getListEnvirByListService(List<Meteo_Service> listServices){
		Session session = PilotageSession.getCurrentSession();
		List<Meteo_Environnement> listEnvir = new ArrayList<Meteo_Environnement>();
		
		for(Meteo_Service service : listServices){
			List<Meteo_Indicateur> listIndic =  session.createCriteria(Meteo_Indicateur.class)
					.add(Restrictions.eq("service", service))
					.add(Restrictions.isNull("dateSuppression"))
					.list();
			
			for(Meteo_Indicateur indic : listIndic){
				if(!listEnvir.contains(indic.getEnvironnement())){
					listEnvir.add(indic.getEnvironnement());
				}
						
			}
		}
		session.getTransaction().commit();
		return listEnvir;
	}

	@SuppressWarnings("unchecked")
	public static List<Meteo_Environnement_Place> getPlaceEnvirMeteo(Meteo_Meteo meteo){
		Session session = PilotageSession.getCurrentSession();
		List<Meteo_Environnement_Place> listIndic =  session.createCriteria(Meteo_Environnement_Place.class)
				.add(Restrictions.eq("meteo", meteo))
				.addOrder(Order.asc("environnement.id"))
				.list();
		session.getTransaction().commit();
		return listIndic;
	}
	
}
