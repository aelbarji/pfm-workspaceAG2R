package pilotage.database.meteo;

import java.util.ArrayList;

import java.util.Date;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import pilotage.metier.Meteo_Format;
import pilotage.metier.Meteo_GroupeMeteo;
import pilotage.metier.Meteo_GroupeMeteo_Meteo;
import pilotage.metier.Meteo_Meteo;
import pilotage.session.PilotageSession;

public class MeteoGroupeMeteoDatabaseService {
	
	/**
	 * INSERT d'une nouvelle affiliation d'une météo à un groupe météo
	 * @param groupe
	 * @param listMeteo
	 * @throws Exception 
	 */
	public static void create(Meteo_GroupeMeteo groupe, List<Meteo_Meteo> listMeteos) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			//Météos
			List<Meteo_GroupeMeteo_Meteo> listMeteosGroupeMeteo = new ArrayList<Meteo_GroupeMeteo_Meteo>();
			for(Meteo_Meteo meteo : listMeteos){
				Meteo_GroupeMeteo_Meteo mg = new Meteo_GroupeMeteo_Meteo();
				mg.setIdMeteos(meteo);
				mg.setIdGroupeMeteo(groupe);
				mg.setDateCreation(new Date());
				
				listMeteosGroupeMeteo.add(mg);
			}
	
			for(Meteo_GroupeMeteo_Meteo meteo : listMeteosGroupeMeteo){
				session.save(meteo);
			}
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}	
	}
	
	/**
	 * SELECT de tous les meteo_groupeMeteo_meteo attachés au groupe 
	 * @param selectGroupe
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Meteo_GroupeMeteo_Meteo> getMeteosFromGroupeMeteo(Integer selectGroupe) {
		Session session = PilotageSession.getCurrentSession();
	
		Meteo_GroupeMeteo groupeMeteo = (Meteo_GroupeMeteo)session.load(Meteo_GroupeMeteo.class, selectGroupe);
		List<Meteo_GroupeMeteo_Meteo> meteoGroupeMeteo = session.createCriteria(Meteo_GroupeMeteo_Meteo.class)
														.add(Restrictions.eq("idGroupeMeteo", groupeMeteo))
														.createAlias("idMeteos", "meteo", Criteria.LEFT_JOIN)
														.addOrder(Order.asc("meteo.nom_meteo"))
														.list(); 
		
		return meteoGroupeMeteo;
	}
	
	/**
	 * MODIFY des meteo attachés au groupe 
	 * @param idGroupe
	 * @param nom
	 * @param meteoToAdd
	 * @param meteoToDelete
	 * @param format
	 * @throws Exception
	 */
	public static void modify(Integer idGroupe, String nom, List<Integer> meteoToAdd, List<Integer> meteoToDelete, Meteo_Format format, String timezone) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			Meteo_GroupeMeteo groupe = (Meteo_GroupeMeteo)session.load(Meteo_GroupeMeteo.class, idGroupe);
			groupe.setNom_groupeMeteo(nom);
			groupe.setFormat(format);
			groupe.setTimezone(timezone);
			
			List<Meteo_GroupeMeteo_Meteo> listMeteosGroupeToAdd = new ArrayList<Meteo_GroupeMeteo_Meteo>();
			List<Meteo_GroupeMeteo_Meteo> listMeteosGroupeToDelete = new ArrayList<Meteo_GroupeMeteo_Meteo>();
			for(Integer meteoID : meteoToAdd){
				Meteo_Meteo meteo = (Meteo_Meteo)session.load(Meteo_Meteo.class, meteoID);
				Meteo_GroupeMeteo_Meteo meteoGroupe = new Meteo_GroupeMeteo_Meteo();
				meteoGroupe.setIdMeteos(meteo);
				meteoGroupe.setIdGroupeMeteo(groupe);
				meteoGroupe.setDateCreation(new Date());
				
				listMeteosGroupeToAdd.add(meteoGroupe);
			}
			
			for(Integer meteoID : meteoToDelete){
				Meteo_Meteo meteo = (Meteo_Meteo)session.load(Meteo_Meteo.class, meteoID);
				Meteo_GroupeMeteo_Meteo meteoGroupe = (Meteo_GroupeMeteo_Meteo)session.createCriteria(Meteo_GroupeMeteo_Meteo.class)
																	.add(Restrictions.eq("idMeteos", meteo))
																	.add(Restrictions.eq("idGroupeMeteo", groupe))
																	.setMaxResults(1)
																	.uniqueResult();
				listMeteosGroupeToDelete.add(meteoGroupe);
			}
			
			for(Meteo_GroupeMeteo_Meteo meteoGroupe : listMeteosGroupeToAdd) {
				session.save(meteoGroupe);
			}
			for(Meteo_GroupeMeteo_Meteo meteoGroupe : listMeteosGroupeToDelete) {
				meteoGroupe.setDateSuppression(new Date());
			}
			
			session.save(groupe);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	/**
	 * SELECT UNION de toutes les météos attachés à la liste des groupes Météo en paramètre
	 * @param listGroupeMeteo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Meteo_GroupeMeteo_Meteo> getUnionByGroupeMeteoList(Integer groupeMeteo) {
		List<Meteo_GroupeMeteo_Meteo> listGroupeMeteo_Meteo = null;
		if(groupeMeteo != null){
			Session session = PilotageSession.getCurrentSession();

			Meteo_GroupeMeteo groupe = (Meteo_GroupeMeteo)session.load(Meteo_GroupeMeteo.class, groupeMeteo);
			listGroupeMeteo_Meteo = session.createCriteria(Meteo_GroupeMeteo_Meteo.class)
														.add(Restrictions.eq("idGroupeMeteo", groupe))
														.createAlias("idMeteos", "meteo", Criteria.LEFT_JOIN)
														.addOrder(Order.asc("meteo.nom_meteo"))
														.list();
			session.getTransaction().commit();
		}

		return listGroupeMeteo_Meteo;
	}
}
