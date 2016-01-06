package pilotage.database.astreintes;

import java.util.Calendar;
import java.util.Date;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import pilotage.metier.Astreinte;
import pilotage.metier.Astreinte_Appel;
import pilotage.metier.Astreinte_Appel_Statut;
import pilotage.metier.Astreinte_Nombre_Appel_View;
import pilotage.metier.Incidents;
import pilotage.service.date.DateService;
import pilotage.session.PilotageSession;
import pilotage.utils.Pagination;
public class AstreinteAppelDatabaseService {

	/**
	 * SELECT de la liste des appels Astreinte
	 * @return
	 */
	public static List<Astreinte_Appel> getAll(Pagination<Astreinte_Appel> pagination, String filtreIncident, String filtreNbAppel, String filtreDate, String filtreAstreinte, String filtreStatut){
		Session session = PilotageSession.getCurrentSession();
		Criteria criteria = session.createCriteria(Astreinte_Appel.class);

		try{
			Integer filtreInc = Integer.parseInt(filtreIncident);
			if(filtreIncident != null && !filtreIncident.equals("")){
				Incidents incidents = (Incidents)session.load(Incidents.class, filtreInc);
				criteria.add(Restrictions.eq("incident", incidents));
			}
		} catch(NumberFormatException e){
			e.printStackTrace();
		}
		if(filtreNbAppel != null && !filtreNbAppel.equals("")){
			try{
				/*Integer filtreApp = Integer.parseInt(filtreNbAppel);
				Astreinte_Nombre_Appel_View anav = (Astreinte_Nombre_Appel_View)session.load(Astreinte_Nombre_Appel_View.class);
				criteria.add(Restrictions.eq("anav.nbAppel", filtreApp));
				/*String groupBy = "id_astreinte, id_incident" + " having " + "count('datetime') = " + filtreApp;
				String[] alias = new String[2]; 
				 alias[0] = "ast"; 
				 alias[1] = "incid";
				 Type[] types = new Type[2]; 
				 types[0] = StandardBasicTypes.INTEGER;
				 types[1] = StandardBasicTypes.INTEGER;
				 criteria.setProjection(Projections.projectionList()
						//.add(Projections.groupProperty("astreinte"))
						//.add(Projections.groupProperty("incident"))
						.add(Projections.sqlGroupProjection("id_astreinte as ast, id_incident as incid", groupBy , alias, types))
						.add(Projections.count("date"))
						.add(Projections.max("date"))
						);*/
			} catch(NumberFormatException e){
				e.printStackTrace();
			}
		}
		if(filtreDate != null && !filtreDate.equals("")){
			Date filtre = DateService.strToDate(filtreDate);
			Date filtreFin = DateService.strToDate(filtreDate);
			Calendar calendar = Calendar.getInstance();
	        calendar.setTime(filtreFin);
	        calendar.set(Calendar.HOUR_OF_DAY, 23);
	        calendar.set(Calendar.MINUTE, 59);
	        calendar.set(Calendar.SECOND, 59);
	        filtreFin = calendar.getTime();
			criteria.add(Restrictions.between("date", filtre, filtreFin));
			criteria.addOrder(Order.asc("date"));
		}
		if(filtreAstreinte != null && !filtreAstreinte.equals("")){
			Astreinte as = (Astreinte) session.load(Astreinte.class, Integer.parseInt(filtreAstreinte));
			criteria.add(Restrictions.eq("astreinte", as));
		}
		
		if(filtreStatut != null && !filtreStatut.equals("")){
			Astreinte_Appel_Statut aas = (Astreinte_Appel_Statut) session.load(Astreinte_Appel_Statut.class, Integer.parseInt(filtreStatut));
			criteria.add(Restrictions.eq("statut", aas));
		}
		
		criteria.addOrder(Order.asc("astreinte"));
		criteria.addOrder(Order.asc("incident"));
		//criteria.addOrder(Order.asc("date"));
		List<Astreinte_Appel> astAppelList = pagination.setCriteriaPagination(criteria);
		session.getTransaction().commit();
		return astAppelList;
		
	}
	
	/**
	 * SELECT de la liste des appels Astreinte
	 * @return
	 */
	public static List<Astreinte_Nombre_Appel_View> getAllView(Pagination<Astreinte_Nombre_Appel_View> pagination, String filtreIncident, String filtreNbAppel, String filtreDate, String filtreAstreinte, String filtreStatut){
		Session session = PilotageSession.getCurrentSession();
		Criteria criteria = session.createCriteria(Astreinte_Nombre_Appel_View.class);

		try{
			if(filtreIncident != null && !filtreIncident.equals("")){
				Integer filtreInc = Integer.parseInt(filtreIncident);
				Incidents incidents = (Incidents)session.load(Incidents.class, filtreInc);
				criteria.add(Restrictions.eq("incident", incidents));
			}
		} catch(NumberFormatException e){
			e.printStackTrace();
		}
		
		if(filtreNbAppel != null && !filtreNbAppel.equals("")){
			try{
				Integer filtreApp = Integer.parseInt(filtreNbAppel);
				criteria.add(Restrictions.eq("nbAppel", filtreApp));
			} catch(NumberFormatException e){
				e.printStackTrace();
			}
		}
		try{
			if(filtreDate != null && !filtreDate.trim().equals("")){
				Date filtre = DateService.strToDate(filtreDate);
				Date filtreFin = DateService.strToDate(filtreDate);
				Calendar calendar = Calendar.getInstance();
		        calendar.setTime(filtreFin);
		        calendar.set(Calendar.HOUR_OF_DAY, 23);
		        calendar.set(Calendar.MINUTE, 59);
		        calendar.set(Calendar.SECOND, 59);
		        filtreFin = calendar.getTime();
				criteria.add(Restrictions.between("mindate", filtre, filtreFin));
				criteria.addOrder(Order.asc("mindate"));
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		
		if(filtreAstreinte != null && !filtreAstreinte.equals("")){
			Astreinte as = (Astreinte) session.load(Astreinte.class, Integer.parseInt(filtreAstreinte));
			criteria.add(Restrictions.eq("astreinte", as));
		}
		
		if(filtreStatut != null && !filtreStatut.equals("")){
			Astreinte_Appel_Statut aas = (Astreinte_Appel_Statut) session.load(Astreinte_Appel_Statut.class, Integer.parseInt(filtreStatut));
			criteria.add(Restrictions.eq("statut", aas));
		}
		
		criteria.addOrder(Order.desc("mindate"));
		criteria.addOrder(Order.asc("incident"));
		criteria.addOrder(Order.asc("astreinte"));
		
		List<Astreinte_Nombre_Appel_View> astAppelList = pagination.setCriteriaPagination(criteria);
		session.getTransaction().commit();
		return astAppelList;
		
	}
	
	/**
	 * SELECT de la liste des appels Astreinte
	 * @return
	 */
	public static List<Astreinte_Appel> getAll(){
		Session session = PilotageSession.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Astreinte_Appel> astAppelList = (List<Astreinte_Appel>)session.createCriteria(Astreinte_Appel.class)
											.addOrder(Order.asc("astreinte"))
											.list();
		session.getTransaction().commit();
		return astAppelList;
		
	}
	
	/**
	 * SELECT de tous les astreintes appels du mois
	 * @param selectMois
	 * @return
	 * @throws Exception 
	 */
	public static Long getNbAstreinteAppelMois(Date selectMois) throws Exception {
		return getNbAstreinteAppelPeriode(DateService.getMonthEarlier(selectMois), DateService.getMonthLater(selectMois));
	}
	
	/**
	 * SELECT de tous les appels astreintes de la période
	 * @param debut
	 * @param fin
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static Long getNbAstreinteAppelPeriode(Date ddebut, Date dfin) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			java.sql.Date debut = new java.sql.Date(ddebut.getTime());
			java.sql.Date fin = new java.sql.Date(dfin.getTime());
			Query query = session.createQuery("select distinct a.incident.id, a.astreinte.id from Astreinte_Appel a where a.date >= :debut and a.date <= :fin")
								.setDate("debut", debut)
								.setDate("fin", fin);
			List<Astreinte_Appel> result = query.list();
			session.getTransaction().commit();
			return (long) result.size();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	/**
	 * INSERT d'un nouvel appel astreinte
	 * @param datetime
	 * @param astreinte
	 * @param incident
	 * @param statut
	 * @param commentaire
	 */
	public static void create(Date date, Integer astreinte, Integer incident, Integer statut, String commentaire) throws Exception {
		Session session = PilotageSession.getCurrentSession();
	
		try{
			Astreinte ast = (Astreinte)session.load(Astreinte.class, astreinte);
			Incidents inc = (Incidents)session.load(Incidents.class, incident);
			Astreinte_Appel_Statut sta = (Astreinte_Appel_Statut)session.load(Astreinte_Appel_Statut.class, statut);
			
			Astreinte_Appel appel = new Astreinte_Appel();
			appel.setDate(date);
			appel.setAstreinte(ast);
			appel.setIncident(inc);
			appel.setStatut(sta);
			appel.setCommentaire(commentaire);
		
			session.save(appel);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	/**
	 * SELECT appel astreinte
	 * @param selectRow
	 * @return
	 */
	public static Astreinte_Appel get(Integer selectRow) {
		Session session = PilotageSession.getCurrentSession();
		Astreinte_Appel appel = (Astreinte_Appel)session.createCriteria(Astreinte_Appel.class)
							.add(Restrictions.eq("id", selectRow))
							.setMaxResults(1)
							.uniqueResult();
		session.getTransaction().commit();
		return appel;
	}
	
	/**
	 * SELECT des appels astreinte selon astreinte et incident
	 * @param astreinte
	 * @param incident
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Astreinte_Appel> getByAstreinteIncident(Integer astreinte, Integer incident) {
		Session session = PilotageSession.getCurrentSession();
		
		Astreinte ast = (Astreinte)session.load(Astreinte.class, astreinte);
		Incidents inc = (Incidents)session.load(Incidents.class, incident);
		
		List<Astreinte_Appel> appels = session.createCriteria(Astreinte_Appel.class)
										.addOrder(Order.desc("date"))
										.add(Restrictions.eq("astreinte", ast))
										.add(Restrictions.eq("incident", inc))
										.list();
		session.getTransaction().commit();
		return appels;
	}
	
	/**
	 * SELECT des appels astreinte selon incident
	 * @param astreinte
	 * @param incident
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Astreinte_Appel> getByIncident(Integer incident) {
		Session session = PilotageSession.getCurrentSession();
		
		Incidents inc = (Incidents)session.load(Incidents.class, incident);
		
		List<Astreinte_Appel> appels = session.createCriteria(Astreinte_Appel.class)
										.addOrder(Order.desc("date"))
										.add(Restrictions.eq("incident", inc))
										.list();
		session.getTransaction().commit();
		return appels;
	}
	
	/**
	 * UPDATE d'un appel astreinte 
	 * @param id
	 * @param date
	 * @param statut
	 * @param commentaire
	 * @throws Exception 
	 */
	public static void modify(Integer id, Date date, Integer idStatut, String commentaire) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			Astreinte_Appel_Statut statut = (Astreinte_Appel_Statut)session.load(Astreinte_Appel_Statut.class, idStatut);
			
			Astreinte_Appel appel = (Astreinte_Appel)session.load(Astreinte_Appel.class, id);
			appel.setDate(date);
			appel.setStatut(statut);
			appel.setCommentaire(commentaire);
			
			session.update(appel);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	/**
	 * DELETE d'un appel astreinte  
	 * @param selectRow
	 * @throws Exception 
	 */
	public static void delete(Integer selectRow) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			Astreinte_Appel appel = (Astreinte_Appel)session.load(Astreinte_Appel.class, selectRow);
			
			session.delete(appel);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	/**
	 * DELETE d'un appel astreinte 
	 * @param astreinte
	 * @param incident
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public static void deleteAll(Integer astreinte, Integer incident) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			Astreinte ast = (Astreinte)session.load(Astreinte.class, astreinte);
			Incidents inc = (Incidents)session.load(Incidents.class, incident);
			
			List<Astreinte_Appel> appels = session.createCriteria(Astreinte_Appel.class)
											.addOrder(Order.desc("date"))
											.add(Restrictions.eq("astreinte", ast))
											.add(Restrictions.eq("incident", inc))
											.list();
			for(Astreinte_Appel a: appels){
				session.delete(a);
			}
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
}
