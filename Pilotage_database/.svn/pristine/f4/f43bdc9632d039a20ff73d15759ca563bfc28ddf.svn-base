package pilotage.database.meteo;

import java.util.ArrayList;


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import pilotage.metier.Meteo_Caisse;
import pilotage.metier.Meteo_Environnement;
import pilotage.metier.Meteo_Environnement_Caisse;
import pilotage.session.PilotageSession;

public class MeteoEnvironnementCaisseDatabaseService {

	/**
	 * SELECT caisse lié à l'environnement
	 * @param idEnvir
	 */
	@SuppressWarnings("unchecked")
	public static List<Meteo_Environnement_Caisse> getListCaisseByEnvir(Integer idEnvir){

		Session session = PilotageSession.getCurrentSession();

		Meteo_Environnement envir  = (Meteo_Environnement)session.load(Meteo_Environnement.class, idEnvir);
		
		List<Meteo_Environnement_Caisse> listEnvirCaisse = session.createCriteria(Meteo_Environnement_Caisse.class)
													.add(Restrictions.eq("envir", envir))
													.createAlias("caisse", "caisse", Criteria.LEFT_JOIN)
													.addOrder(Order.asc("caisse.nomCaisse"))
													.list();
		
		session.getTransaction().commit();
		
		return listEnvirCaisse;
		
	}
	
	/**
	 * MODIFY caisse lié à l'environnement
	 * @param idEnvir
	 * @param nom
	 * @param caisseToAdd
	 * @param caisseToDelete
	 * @throws Exception
	 */
	public static void modify(Integer idEnvir, String nom, List<Integer> caisseToAdd, List<Integer> caisseToDelete, Integer impact) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			Meteo_Environnement envir = (Meteo_Environnement)session.load(Meteo_Environnement.class, idEnvir);
			envir.setNom_envir(nom);
			envir.setImpact(impact);
			
			List<Meteo_Environnement_Caisse> listEnvirCaisseToAdd = new ArrayList<Meteo_Environnement_Caisse>();
			List<Meteo_Environnement_Caisse> listEnvirCaisseToDelete = new ArrayList<Meteo_Environnement_Caisse>();
			for(Integer caisseID : caisseToAdd){
				Meteo_Caisse caisse = (Meteo_Caisse)session.load(Meteo_Caisse.class, caisseID);
				Meteo_Environnement_Caisse envirCaisse = new Meteo_Environnement_Caisse();
				envirCaisse.setCaisse(caisse);
				envirCaisse.setEnvir(envir);
				
				listEnvirCaisseToAdd.add(envirCaisse);
			}
			
			for(Integer caisseID : caisseToDelete){
				Meteo_Caisse caisse = (Meteo_Caisse)session.load(Meteo_Caisse.class, caisseID);
				Meteo_Environnement_Caisse envirCaisse = (Meteo_Environnement_Caisse)session.createCriteria(Meteo_Environnement_Caisse.class)
																	.add(Restrictions.eq("caisse", caisse))
																	.add(Restrictions.eq("envir", envir))
																	.setMaxResults(1)
																	.uniqueResult();
				listEnvirCaisseToDelete.add(envirCaisse);
			}
			
			for(Meteo_Environnement_Caisse envirCaisse : listEnvirCaisseToAdd) {
				session.save(envirCaisse);
			}
			for(Meteo_Environnement_Caisse envirCaisse : listEnvirCaisseToDelete) {
				session.delete(envirCaisse);
			}
			
			session.save(envir);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
}
