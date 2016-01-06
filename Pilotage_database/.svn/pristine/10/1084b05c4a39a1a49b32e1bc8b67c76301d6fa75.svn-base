package pilotage.database.astreintes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import pilotage.metier.Astreinte;
import pilotage.metier.Astreinte_Domaine;
import pilotage.metier.Astreinte_Obligatoire;
import pilotage.metier.Astreinte_Planning;
import pilotage.metier.Astreinte_Type;
import pilotage.service.date.DateService;
import pilotage.session.PilotageSession;
import pilotage.utils.Pagination;

public class AstreintePlanningDatabaseService {

	/**
	 * DELETE d'un astreinte planning 
	 * @param selectRow
	 * @throws Exception 
	 */
	public static void delete(Integer selectRow) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			Astreinte_Planning astreintePlanning = (Astreinte_Planning)session.load(Astreinte_Planning.class, selectRow);
			
			session.delete(astreintePlanning);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	/**
	 * SELECT du planning identifié par le paramètre
	 * @param selectRow
	 * @return
	 */
	public static Astreinte_Planning get(Integer selectRow) {
		Session session = PilotageSession.getCurrentSession();
		Astreinte_Planning astreintePlanning = (Astreinte_Planning)session.createCriteria(Astreinte_Planning.class)
							.add(Restrictions.eq("id", selectRow))
							.setMaxResults(1)
							.uniqueResult();
		session.getTransaction().commit();
		return astreintePlanning;
	}
	
	/**
	 * INSERT d'un nouvel astreinte_planning
	 * @param datedeb
	 * @param datefin
	 * @param astreinte
	 * @param domaine
	 * @param commentaires
	 * @param tel
	 * @throws Exception 
	 */
	public static void create(Date datedeb, Date datefin, Integer astreinte, Integer domaine, String commentaires, String tel ) throws Exception {
		Session session = PilotageSession.getCurrentSession();
	
		try{
			Astreinte ast = (Astreinte)session.load(Astreinte.class, astreinte);
			Astreinte_Domaine dom = (Astreinte_Domaine)session.load(Astreinte_Domaine.class, domaine);
			
			Astreinte_Planning astreintePlanning = new Astreinte_Planning();
			astreintePlanning.setDatedeb(datedeb);
			astreintePlanning.setDatefin(datefin);
			astreintePlanning.setAstreinte(ast);
			astreintePlanning.setDomaine(dom);
			astreintePlanning.setCommentaires(commentaires);
			astreintePlanning.setTel(tel);
		
			session.save(astreintePlanning);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	/**
	 * INSERT d'un nouvel astreinte_planning
	 * @param datedeb
	 * @param datefin
	 * @param astreinte
	 * @param domaine
	 * @param commentaires
	 * @param tel
	 * @param infogene
	 * @throws Exception 
	 */
	public static void create(Date datedeb, Date datefin, Integer astreinte, Integer domaine, String commentaires, String tel, String infogene ) throws Exception {
		Session session = PilotageSession.getCurrentSession();
	
		try{
			Astreinte ast = (Astreinte)session.load(Astreinte.class, astreinte);
			Astreinte_Domaine dom = (Astreinte_Domaine)session.load(Astreinte_Domaine.class, domaine);
			
			Astreinte_Planning astreintePlanning = new Astreinte_Planning();
			astreintePlanning.setDatedeb(datedeb);
			astreintePlanning.setDatefin(datefin);
			astreintePlanning.setAstreinte(ast);
			astreintePlanning.setDomaine(dom);
			astreintePlanning.setCommentaires(commentaires);
			astreintePlanning.setTel(tel);
			astreintePlanning.setInfogene(infogene);
		
			session.save(astreintePlanning);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	/**
	 * UPDATE d'un astreinte planning
	 * @param id
	 * @param datedeb
	 * @param datefin
	 * @param astreinte
	 * @param domaine
	 * @param commentaires
	 * @param tel
	 * @throws Exception 
	 */
	public static void modify(Integer id, Date datedeb, Date datefin, Integer astreinte, Integer domaine, String commentaires, String tel) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			Astreinte ast = (Astreinte)session.load(Astreinte.class, astreinte);
			Astreinte_Domaine dom = (Astreinte_Domaine)session.load(Astreinte_Domaine.class, domaine);
			
			Astreinte_Planning astreintePlanning = (Astreinte_Planning)session.load(Astreinte_Planning.class, id);
			astreintePlanning.setDatedeb(datedeb);
			astreintePlanning.setDatefin(datefin);
			astreintePlanning.setAstreinte(ast);
			astreintePlanning.setDomaine(dom);
			astreintePlanning.setCommentaires(commentaires);
			astreintePlanning.setTel(tel);
			
			session.update(astreintePlanning);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	/**
	 * UPDATE d'un astreinte planning
	 * @param id
	 * @param datedeb
	 * @param datefin
	 * @param astreinte
	 * @param domaine
	 * @param commentaires
	 * @param tel
	 * @param infogene
	 * @throws Exception 
	 */
	public static void modify(Integer id, Date datedeb, Date datefin, Integer astreinte, Integer domaine, String commentaires, String tel, String infogene) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			Astreinte ast = (Astreinte)session.load(Astreinte.class, astreinte);
			Astreinte_Domaine dom = (Astreinte_Domaine)session.load(Astreinte_Domaine.class, domaine);
			
			Astreinte_Planning astreintePlanning = (Astreinte_Planning)session.load(Astreinte_Planning.class, id);
			astreintePlanning.setDatedeb(datedeb);
			astreintePlanning.setDatefin(datefin);
			astreintePlanning.setAstreinte(ast);
			astreintePlanning.setDomaine(dom);
			astreintePlanning.setCommentaires(commentaires);
			astreintePlanning.setTel(tel);
			astreintePlanning.setInfogene(infogene);
			
			session.update(astreintePlanning);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	/**
	 * SELECT de toutes les astreintes obligatoires non couvertes pour les dateNB jours à venir 
	 * @param aObligatoires
	 * @param dateNB
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Astreinte_Obligatoire> getAONotCovered(List<Astreinte_Obligatoire> aObligatoires, Integer dateNB){

		//dates concernées
		Date start = DateService.addDays(DateService.getTodayWithoutHour(), 1);
		Date end = DateService.addDays(start, dateNB); //date inclus dans la zone à couvrir
		
		//ajout de toutes les astreintes obligatoires dans la liste
		List<Astreinte_Obligatoire> aObligatoiresNotCovered = new ArrayList<Astreinte_Obligatoire>();
		aObligatoiresNotCovered.addAll(aObligatoires);
		
		
		List<Astreinte_Obligatoire> aObligatoiresCovered = new ArrayList<Astreinte_Obligatoire>();
		
		Session session = PilotageSession.getCurrentSession();
		for (Astreinte_Obligatoire aObligatoire : aObligatoires) {
			List<Astreinte_Planning> apList = session.createCriteria(Astreinte_Planning.class)
													.add(Restrictions.eq("domaine", aObligatoire.getDomaine()))
													.createAlias("astreinte", "ast", Criteria.LEFT_JOIN)
													.add(Restrictions.eq("ast.type", aObligatoire.getType()))
													.add(Restrictions.le("datedeb", end))
													.add(Restrictions.ge("datefin", start))
													.addOrder(Order.asc("datedeb"))
													.list();
			
			if (apList.size() > 0) {
				// si l'astreinte obligatoire est couverte, ajout à la liste des couverts
				if (isCovered(apList, start, end)) {
					aObligatoiresCovered.add(aObligatoire);
				}
			}
		}
		
		session.getTransaction().commit();
		
		//suppr des astreintes obligatoires couvertes
		aObligatoires.removeAll(aObligatoiresCovered);
		
		return aObligatoires;
	}
	
	/**
	 * TEST si les astreintes en paramètres couvrent les dates en paramètres
	 * @param aList - classé par ordre de date de début croissants
	 * @param datedeb
	 * @param datefin - inclus dans la zone à couvrir
	 * @return
	 */
	public static boolean isCovered(List<Astreinte_Planning> aList, Date datedeb, Date datefin){
		//aucun planning dans ces dates --> non couvert
		if(aList == null || aList.isEmpty())
			return false;
		
		//un seul planning : on teste les dates
		if(aList.size() == 1)
			return (aList.get(0).getDatedeb().equals(datedeb) || aList.get(0).getDatedeb().before(datedeb))
					&& (aList.get(0).getDatefin().equals(datefin) || aList.get(0).getDatefin().after(datefin));
		
		//Plusieurs plannings : on fusionne les dates pour avoir un seul créneau de couverture et on teste les dates
		Date debut = null;
		Date fin = null;
		for(Astreinte_Planning aPlanning : aList){
			//pas de fusion ou premières dates
			if(debut == null){
				debut = aPlanning.getDatedeb();
				fin = aPlanning.getDatefin();
			}
			//si |___creneau__|
			//						|___aPlanning___|
			//pas de fusion, on a un trou
			else if(aPlanning.getDatedeb().after(fin)){
				return false;
			}
			
			//fusion : 		|___creneau__|		
			//				|___aPlanning___|	
			else if(debut.equals(aPlanning.getDatedeb()) && fin.before(aPlanning.getDatefin())) {
				fin = aPlanning.getDatefin();
			}
			//fusion : 		|___creneau__|					ou			|___creneau__|					
			//						|_____aPlanning___|						 		 |___aPlanning___|
			else if((fin.after(aPlanning.getDatedeb()) || fin.equals(aPlanning.getDatedeb())) && fin.before(aPlanning.getDatefin())){
				fin = aPlanning.getDatefin();
			}
		}
		
		return (debut.getTime() == datedeb.getTime() || debut.before(datedeb))
				&& (fin.getTime() == datefin.getTime() || fin.after(datefin));
	}
	
	/**
	 * SELECT de la liste des plannings
	 * @param enCours_aVenir - true : recup des astreintes en cours et a venir. false : historique   
	 * @param pagination
	 * @param sort
	 * @param sens
	 * @param filtreType
	 * @param filtreDomaine
	 * @param filtreAstreinte
	 * @param filtreDateDeb
	 * @param filtreDateFin
	 * @param filtreTelephone
	 * @param filtreCommentaire
	 * @return
	 */
	public static List<Astreinte_Planning> getAll(Boolean enCours_aVenir, Pagination<Astreinte_Planning> pagination, String sort, String sens, String filtreType, String filtreDomaine, String filtreAstreinte, String filtreDateDeb, String filtreDateFin, String filtreTelephone, String filtreCommentaire){
		
		Session session = PilotageSession.getCurrentSession();

		Criteria criteria = session.createCriteria(Astreinte_Planning.class)
								.createAlias("astreinte", "ast")
								.createAlias("ast.type", "astType")
								.createAlias("domaine", "dom");
		if(enCours_aVenir)
			criteria.add(Restrictions.ge("datefin", DateService.getTodayWithoutHour()));
		else
			criteria.add(Restrictions.lt("datefin", DateService.getTodayWithoutHour()));
		
		if(filtreType != null && !"".equals(filtreType)){
			Astreinte_Type type = (Astreinte_Type) session.load(Astreinte_Type.class, Integer.parseInt(filtreType));
			criteria.add(Restrictions.eq("ast.type", type));
		}
		if(filtreDomaine != null && !"".equals(filtreDomaine)){
			Astreinte_Domaine domaine = (Astreinte_Domaine) session.load(Astreinte_Domaine.class, Integer.parseInt(filtreDomaine));
			criteria.add(Restrictions.eq("domaine", domaine));
		}
		if(filtreAstreinte != null && !"".equals(filtreAstreinte)){
			Astreinte astreinte = (Astreinte) session.load(Astreinte.class, Integer.parseInt(filtreAstreinte));
			criteria.add(Restrictions.eq("astreinte", astreinte));
		}
		if(filtreDateDeb != null && !"".equals(filtreDateDeb) && filtreDateFin != null && !"".equals(filtreDateFin)){
			Date debut = DateService.strToDate(filtreDateDeb);
			criteria.add(Restrictions.ge("datefin", debut));
			Date fin = DateService.strToDate(filtreDateFin);
			criteria.add(Restrictions.le("datedeb", fin));
		} else if (filtreDateDeb != null && !"".equals(filtreDateDeb)) {
			Date debut = DateService.strToDate(filtreDateDeb);
			criteria.add(Restrictions.ge("datefin", debut));
			criteria.add(Restrictions.le("datedeb", debut));
		} else if (filtreDateFin != null && !"".equals(filtreDateFin)) {
			Date fin = DateService.strToDate(filtreDateFin);
			criteria.add(Restrictions.ge("datefin", fin));
			criteria.add(Restrictions.le("datedeb", fin));
		}
		if(filtreTelephone != null && !"".equals(filtreTelephone)){
			criteria.add(Restrictions.like("tel", "%" + filtreTelephone + "%"));
		}
		if(filtreCommentaire != null && !"".equals(filtreCommentaire)){
			criteria.add(Restrictions.disjunction().add(Restrictions.like("commentaires", "%" + filtreCommentaire + "%")).add(Restrictions.like("infogene", "%" + filtreCommentaire + "%")));
		}
		if(sort.equals("type")){
			criteria.addOrder("desc".equalsIgnoreCase(sens) ? Order.desc("astType.type") : Order.asc("astType.type"));
		}
		else if(sort.equals("astreinte")){
			criteria.addOrder("desc".equalsIgnoreCase(sens) ? Order.desc("ast.nom") : Order.asc("ast.nom"));
			criteria.addOrder("desc".equalsIgnoreCase(sens) ? Order.desc("ast.prenom") : Order.asc("ast.prenom"));
		}
		else if(sort.equals("domaine")){
			criteria.addOrder("desc".equalsIgnoreCase(sens) ? Order.desc("dom.domaine") : Order.asc("dom.domaine"));
		}
		else 
			criteria.addOrder("desc".equalsIgnoreCase(sens) ? Order.desc(sort) : Order.asc(sort));
		
		List<Astreinte_Planning> listAP = pagination.setCriteriaPagination(criteria);
		session.getTransaction().commit();
		return listAP;
	}
	
	/**
	 * SELECT de tous les astreinte planning pour la weekNB-ième semaine qui suit, ordonnés par domaine puis date de début
	 * @param debut
	 * @param fin
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Astreinte_Planning> getWeekPlanning(Date debut, Date fin){
		Session session = PilotageSession.getCurrentSession();
		List<Astreinte_Planning> aPlannings = session.createCriteria(Astreinte_Planning.class)
		                            .add(Restrictions.lt("datedeb", fin))
		                            .add(Restrictions.gt("datefin", debut))
		                            .createAlias("domaine", "dom", Criteria.LEFT_JOIN)
		                            .createAlias("astreinte", "ast", Criteria.LEFT_JOIN)
		                            .createAlias("ast.type","type",Criteria.LEFT_JOIN)
		                            .addOrder(Order.asc("dom.domaine"))
		                            .addOrder(Order.asc("type.type"))
		                            .addOrder(Order.asc("datedeb"))
		                            .list();
		session.getTransaction().commit();
		return aPlannings;
	}

	/**
	 * COUNT test si une astreinte_planning a l'astreinte en paramètre associé
	 * @param selectRow
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public static Boolean hasPlanningWithAstreinte(int selectRow) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		
		List<Long> results = null;
		try{
			Astreinte astreinte = (Astreinte)session.load(Astreinte.class, selectRow);
			results = session.createCriteria(Astreinte_Planning.class)
				.add(Restrictions.eq("astreinte", astreinte))
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
	 * COUNT test si un planning a le domaine passé en paramètre
	 * @param domaineID
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static boolean hasAstreinteDomaine(Integer domaineID) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		
		List<Long> results = null;
		try{
			Astreinte_Domaine domaine = (Astreinte_Domaine)session.load(Astreinte_Domaine.class, domaineID);
			results = session.createCriteria(Astreinte_Planning.class)
							.add(Restrictions.eq("domaine", domaine))
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
	
	public static Integer getId(Date datedeb, Date datefin, Integer astreinte, Integer domaine, String commentaires, String tel){
		Session session = PilotageSession.getCurrentSession();
		Astreinte ast = (Astreinte)session.load(Astreinte.class, astreinte);
		Astreinte_Domaine dom = (Astreinte_Domaine)session.load(Astreinte_Domaine.class, domaine);
		
		Astreinte_Planning ap = (Astreinte_Planning)session.createCriteria(Astreinte_Planning.class)
									.add(Restrictions.eq("datedeb", datedeb))
									.add(Restrictions.eq("datefin", datefin))
									.add(Restrictions.eq("astreinte", ast))
									.add(Restrictions.eq("domaine", dom))
									.add(Restrictions.eq("commentaires", commentaires))
									.add(Restrictions.eq("tel", tel))
									.setMaxResults(1)
									.uniqueResult();	
		session.getTransaction().commit();
		return ap.getId();
	}
	
	public static Integer getId(Date datedeb, Date datefin, Integer astreinte, Integer domaine, String commentaires, String tel, String infogene){
		Session session = PilotageSession.getCurrentSession();
		Astreinte ast = (Astreinte)session.load(Astreinte.class, astreinte);
		Astreinte_Domaine dom = (Astreinte_Domaine)session.load(Astreinte_Domaine.class, domaine);
		
		Astreinte_Planning ap = (Astreinte_Planning)session.createCriteria(Astreinte_Planning.class)
									.add(Restrictions.eq("datedeb", datedeb))
									.add(Restrictions.eq("datefin", datefin))
									.add(Restrictions.eq("astreinte", ast))
									.add(Restrictions.eq("domaine", dom))
									.add(Restrictions.eq("commentaires", commentaires))
									.add(Restrictions.eq("tel", tel))
									.add(Restrictions.eq("infogene", infogene))
									.setMaxResults(1)
									.uniqueResult();	
		session.getTransaction().commit();
		return ap.getId();
	}
}
