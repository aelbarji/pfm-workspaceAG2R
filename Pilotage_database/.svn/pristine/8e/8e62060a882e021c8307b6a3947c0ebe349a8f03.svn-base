package pilotage.database.meteo;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import pilotage.metier.Meteo_Format;
import pilotage.metier.Meteo_GroupeMeteo;
import pilotage.metier.Meteo_GroupeMeteo_Meteo;
import pilotage.metier.Meteo_IndicateurBase;
import pilotage.session.PilotageSession;
import pilotage.utils.Pagination;

public class GroupeMeteoDatabaseService {

	/**
	 * SELECT de tous les groupes météo, ordonnés par nom
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Meteo_GroupeMeteo> getAll() {
		Session session = PilotageSession.getCurrentSession();
		List<Meteo_GroupeMeteo> groupeList = (List<Meteo_GroupeMeteo>)session.createCriteria(Meteo_GroupeMeteo.class)
													.addOrder(Order.asc("nom_groupeMeteo"))
													.list();
		session.getTransaction().commit();
		return groupeList;
	}
	
	/**
	 * SELECT de la liste des groupes Météo
	 * @param pagination
	 * @return
	 */
	
	public static List<Meteo_GroupeMeteo> getAll(Pagination<Meteo_GroupeMeteo> pagination){
		Session session = PilotageSession.getCurrentSession();
		Criteria criteria = session.createCriteria(Meteo_GroupeMeteo.class)
											.addOrder(Order.asc("nom_groupeMeteo"));
		List<Meteo_GroupeMeteo> groupes = pagination.setCriteriaPagination(criteria);
		session.getTransaction().commit();
		return groupes;
	}
	
	/**
	 * INSERT d'un nouveau Groupe Météo
	 * @param nom
	 * @throws Exception 
	 */
	public static void create(String nomGroupe, Meteo_Format format, String timezone) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			Meteo_GroupeMeteo groupeMeteo = new Meteo_GroupeMeteo();
			groupeMeteo.setNom_groupeMeteo(nomGroupe);
			groupeMeteo.setFormat(format);
			groupeMeteo.setTimezone(timezone);
			session.save(groupeMeteo);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}	
	}
	
	/**
	 * SELECT d'un Groupe Météo avec son id
	 * @param selectRow
	 * @return
	 */
	public static Meteo_GroupeMeteo get(Integer selectRow) {
		Session session = PilotageSession.getCurrentSession();
		Meteo_GroupeMeteo groupeMeteo = (Meteo_GroupeMeteo)session.createCriteria(Meteo_GroupeMeteo.class)
																	.add(Restrictions.eq("id", selectRow))
																	.setMaxResults(1)
																	.uniqueResult();
		session.getTransaction().commit();
		return groupeMeteo;
	}
	
	/**
	 * SELECT d'un Groupe Météo avec son nom
	 * @param selectRow
	 * @return
	 */
	public static Meteo_GroupeMeteo getByName(String nom) {
		Session session = PilotageSession.getCurrentSession();
		Meteo_GroupeMeteo groupeMeteo = (Meteo_GroupeMeteo)session.createCriteria(Meteo_GroupeMeteo.class)
																	.add(Restrictions.eq("nom_groupeMeteo", nom))
																	.setMaxResults(1)
																	.uniqueResult();
		session.getTransaction().commit();
		return groupeMeteo;
	}
	
	/**
	 * UPDATE d'un groupe Météo
	 * @param id
	 * @param nom
	 * @throws Exception 
	 */
	public static void modify(Integer id, String nom_groupeMeteo) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		
		try{
			Meteo_GroupeMeteo groupeMeteo = (Meteo_GroupeMeteo)session.load(Meteo_GroupeMeteo.class, id);
			if(nom_groupeMeteo != null)
				groupeMeteo.setNom_groupeMeteo(nom_groupeMeteo);
	
			session.update(groupeMeteo);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	/**
	 * DELETE d'un groupe Météo avec ces météos
	 * @param selectRow
	 * @throws Exception 
	 */
	public static void delete(Integer idGroupe) throws Exception{
		Session session = PilotageSession.getCurrentSession();
		try{
			Meteo_GroupeMeteo groupe = (Meteo_GroupeMeteo)session.load(Meteo_GroupeMeteo.class, idGroupe);
			
			List<Meteo_GroupeMeteo_Meteo> listMeteosGroupe=MeteoGroupeMeteoDatabaseService.getMeteosFromGroupeMeteo(idGroupe);
			for(Meteo_GroupeMeteo_Meteo meteoGroupe : listMeteosGroupe) {
				session.delete(meteoGroupe);
			}
			session.delete(groupe);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	@SuppressWarnings({ "unchecked" })
	public static List<Meteo_IndicateurBase> createGroupeMeteo(Integer groupe, String date, String datetime, String heure) {
		Session session = PilotageSession.getCurrentSession();
		
		//MeteoIndicateurBase
		List<Meteo_IndicateurBase> result = new ArrayList<Meteo_IndicateurBase>();
		//Construction de la requete HQL pour les procédures stockés
		Query query = session.createSQLQuery(
				"CALL epi.selectIndicateurMeteo(:groupe,:date,:datetime,:heure)").addEntity(Meteo_IndicateurBase.class)
				.setParameter("groupe", groupe)
				.setParameter("date", date)
				.setParameter("datetime", datetime)
				.setParameter("heure", heure);
		result = query.list();
		session.getTransaction().commit();
		return result;
	}
	
}
