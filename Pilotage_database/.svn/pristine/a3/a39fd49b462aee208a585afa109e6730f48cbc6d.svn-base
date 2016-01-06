package pilotage.database.astreintes;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import pilotage.database.incidents.IncidentsDatabaseService;
import pilotage.metier.Astreinte;
import pilotage.metier.Astreinte_Type;
import pilotage.session.PilotageSession;
import pilotage.utils.Pagination;

public class AstreinteDatabaseService {

	/**
	 * SELECT de la liste des Astreinte
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Astreinte> getAll(){
		Session session = PilotageSession.getCurrentSession();
		List<Astreinte> astreinteList = session.createCriteria(Astreinte.class)
											.add(Restrictions.eq("actif", true))
											.addOrder(Order.asc("nom"))
											.addOrder(Order.asc("prenom"))
											.list();
		session.getTransaction().commit();
		return astreinteList;
	}
	
	/**
	 * DELETE d'une astreinte s'il n'est pas référencé en astreinte_planning ou en incident
	 * UPDATE actif = false sinon
	 * @param selectRow
	 * @throws Exception 
	 */
	public static void delete(int selectRow) throws Exception {
		Boolean hasIncidentWithThisAstreinte = IncidentsDatabaseService.hasIncidentWithAstreinte(selectRow);
		Boolean hasPlanningWithThisAstreinte = AstreintePlanningDatabaseService.hasPlanningWithAstreinte(selectRow);
		Session session = PilotageSession.getCurrentSession();
		try{
			Astreinte astreinte = (Astreinte)session.load(Astreinte.class, selectRow);
			
			if(hasIncidentWithThisAstreinte || hasPlanningWithThisAstreinte){
				astreinte.setActif(false);
				session.update(astreinte);
			}
			else{
				session.delete(astreinte);
			}
			
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	/**
	 * SELECT de l'astreinte identifié par le paramètre
	 * @param selectRow
	 * @return
	 */
	public static Astreinte get(Integer selectRow) {
		Session session = PilotageSession.getCurrentSession();
		Astreinte astreinte = (Astreinte)session.createCriteria(Astreinte.class)
							.add(Restrictions.eq("id", selectRow))
							.setMaxResults(1)
							.uniqueResult();
		session.getTransaction().commit();
		return astreinte;
	}
	
	/**
	 * INSERT d'une nouvelle astreinte
	 * @param nom
	 * @param prenom
	 * @param tel1
	 * @param tel2
	 * @param type
	 * @throws Exception 
	 */
	public static void create(String nom, String prenom, String tel1,String tel2, Integer type) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			Astreinte_Type astreinte_Type = (Astreinte_Type) session.load(Astreinte_Type.class, type);
			Astreinte astreinte = new Astreinte();
			astreinte.setNom(nom);
			astreinte.setPrenom(prenom);
			astreinte.setTel1(tel1);
			astreinte.setTel2(tel2);
			astreinte.setActif(true);
			astreinte.setType(astreinte_Type);
		
			session.save(astreinte);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
		
	}
	
	/**
	 * UPDATE d'une astreinte
	 * @param id
	 * @param nom
	 * @param prenom
	 * @param tel1
	 * @param tel2
	 * @param astreinte_Type
	 * @throws Exception 
	 */
	public static void modify(Integer id, String nom, String prenom, String tel1,String tel2, Integer type) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			Astreinte_Type astreinte_Type = (Astreinte_Type) session.load(Astreinte_Type.class, type);
			
			Astreinte astreinte = (Astreinte)session.load(Astreinte.class, id);
			astreinte.setNom(nom);
			astreinte.setPrenom(prenom);
			astreinte.setTel1(tel1);
			astreinte.setTel2(tel2);
			astreinte.setType(astreinte_Type);
	
			session.update(astreinte);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	/**
	 * SELECT de tous les astreintes actifs
	 * @param pagination
	 * @param sort
	 * @param sens
	 * @param filtreNom
	 * @param filtrePrenom
	 * @param filtreTel1
	 * @param filtreTel2
	 * @param filtreType
	 * @return
	 */
	public static List<Astreinte> getAllActifs(Pagination<Astreinte> pagination, String sort, String sens, String filtreNom, String filtrePrenom, String filtreTel1, String filtreTel2, Integer filtreType) {
		Session session = PilotageSession.getCurrentSession();
		
		Criteria criteria = session.createCriteria(Astreinte.class).add(Restrictions.eq("actif", true));
		if(filtreNom != null && !"".equals(filtreNom)){
			criteria.add(Restrictions.like("nom", "%" + filtreNom + "%"));
		}
		if(filtrePrenom != null && !"".equals(filtrePrenom)){
			criteria.add(Restrictions.like("prenom", "%" + filtrePrenom + "%"));
		}
		if(filtreTel1 != null && !"".equals(filtreTel1)){
			criteria.add(Restrictions.like("tel1", "%" + filtreTel1 + "%"));
		}
		if(filtreTel2 != null && !"".equals(filtreTel2)){
			criteria.add(Restrictions.like("tel2", "%" + filtreTel2 + "%"));
		}
		if(filtreType != null && filtreType >= 0){
			Astreinte_Type aTypes = (Astreinte_Type) session.load(Astreinte_Type.class, filtreType);
			criteria.add(Restrictions.eq("type", aTypes));
		}
		
		if("type".equals(sort)){
			criteria.createAlias("type", "type_object", Criteria.LEFT_JOIN);
			criteria.addOrder("desc".equalsIgnoreCase(sens) ? Order.desc("type_object.type") : Order.asc("type_object.type"));
		}
		criteria.addOrder("desc".equalsIgnoreCase(sens) ? Order.desc(sort) : Order.asc(sort));
		List<Astreinte> listAstreinte = pagination.setCriteriaPagination(criteria);
		session.getTransaction().commit();
		return listAstreinte;
	}
	
	/**
	 * SELECT des astreintes du type passé en paramètre
	 * @param astreinte_Type
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Astreinte> getAstreintesByType(Astreinte_Type astreinte_Type){
		Session session = PilotageSession.getCurrentSession();
		
		List<Astreinte> astreintes = session.createCriteria(Astreinte.class)
							.add(Restrictions.eq("type", astreinte_Type))
							.list();
		session.getTransaction().commit();
		return astreintes;
	}
	
	/**
	 * COUNT test si une astreintea le type passé en paramètre
	 * @param typeID
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static Boolean hasAstreinteType(Integer typeID) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		
		List<Long> results = null;
		try{
			Astreinte_Type type = (Astreinte_Type)session.load(Astreinte_Type.class, typeID);
			results = session.createCriteria(Astreinte.class)
							.add(Restrictions.eq("type", type))
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
	 * SELECT de la liste des astreintes dans la liste des id passée 
	 * @param listAstreinteID
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Astreinte> getMultiple(List<Integer> listAstreinteID) {
		List<Astreinte> asts;
		if(listAstreinteID != null && !listAstreinteID.isEmpty()){
			Session session = PilotageSession.getCurrentSession();
			asts = (List<Astreinte>)session.createCriteria(Astreinte.class)
												.add(Restrictions.in("id", listAstreinteID))
												.list();
			session.getTransaction().commit();
		}
		else{
			asts = new ArrayList<Astreinte>();
		}
		return asts;
	}
	
	public static Integer getId(String nom, String prenom, String tel1,String tel2, Integer type){
		Session session = PilotageSession.getCurrentSession();
		Astreinte_Type astreinte_Type = (Astreinte_Type) session.load(Astreinte_Type.class, type);

		Astreinte a = (Astreinte)session.createCriteria(Astreinte.class)
							.add(Restrictions.eq("nom", nom))
							.add(Restrictions.eq("prenom", prenom))
							.add(Restrictions.eq("tel1", tel1))
							.add(Restrictions.eq("tel2", tel2))
							.add(Restrictions.eq("type", astreinte_Type))
							.setMaxResults(1)
							.uniqueResult();
		session.getTransaction().commit();
		return a.getId();
	}
	
	public static String getAstreinteTelephone(Integer astreinteId){
		Session session = PilotageSession.getCurrentSession();
		Astreinte astreinte = (Astreinte)session.createCriteria(Astreinte.class)
									.add(Restrictions.eq("id", astreinteId))
									.setMaxResults(1)
									.uniqueResult();
		session.getTransaction().commit();
		return astreinte.getTel1();
	}
}
