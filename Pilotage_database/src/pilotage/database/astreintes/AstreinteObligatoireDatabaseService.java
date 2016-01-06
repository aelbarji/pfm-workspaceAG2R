package pilotage.database.astreintes;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import pilotage.metier.Astreinte_Domaine;
import pilotage.metier.Astreinte_Obligatoire;
import pilotage.metier.Astreinte_Type;
import pilotage.session.PilotageSession;

public class AstreinteObligatoireDatabaseService {

	/**
	 * DELETE d'une astreinte obligatoire
	 * @param selectRow
	 * @throws Exception 
	 */
	public static void delete(Integer selectRow) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			Astreinte_Obligatoire astreinteObligatoire = (Astreinte_Obligatoire)session.load(Astreinte_Obligatoire.class, selectRow);
			session.delete(astreinteObligatoire);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	/**
	 * SELECT d'une astreinte obligatoire
	 * @param selectRow
	 * @return
	 */
	public static Astreinte_Obligatoire get(Integer selectRow) {
		Session session = PilotageSession.getCurrentSession();
		Astreinte_Obligatoire astreinteObligatoire = (Astreinte_Obligatoire)session.createCriteria(Astreinte_Obligatoire.class)
							.add(Restrictions.eq("id", selectRow))
							.setMaxResults(1)
							.uniqueResult();
		session.getTransaction().commit();
		return astreinteObligatoire;
	}
	
	/**
	 * INSERT d'une nouvelle astreinte obligatoire 
	 * @param domaine
	 * @param type
	 * @param indicEnvoi
	 * @throws Exception 
	 */
	public static void create(Integer domaine, Integer type, Boolean indicEnvoi) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			Astreinte_Domaine aDomaine = (Astreinte_Domaine)session.load(Astreinte_Domaine.class, domaine);
			Astreinte_Type aType = (Astreinte_Type)session.load(Astreinte_Type.class, type);
			
			Astreinte_Obligatoire astreinteObligatoire = new Astreinte_Obligatoire();
			astreinteObligatoire.setDomaine(aDomaine);
			astreinteObligatoire.setType(aType);
			astreinteObligatoire.setIndicEnvoi(indicEnvoi);
		
			session.save(astreinteObligatoire);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
		
	}
	
	/**
	 * UPDATE d'une astreinte obligatoire
	 * @param id
	 * @param domaine
	 * @param type
	 * @param indicEnvoi
	 * @throws Exception 
	 */
	public static void modify(Integer id, Integer domaine, Integer type, Boolean indicEnvoi) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try {
			Astreinte_Domaine aDomaine = (Astreinte_Domaine) session.load(Astreinte_Domaine.class, domaine);
			Astreinte_Type aType = (Astreinte_Type)session.load(Astreinte_Type.class, type);
			
			Astreinte_Obligatoire astreinteObligatoire = (Astreinte_Obligatoire)session.load(Astreinte_Obligatoire.class, id);
			astreinteObligatoire.setDomaine(aDomaine);
			astreinteObligatoire.setType(aType);
			astreinteObligatoire.setIndicEnvoi(indicEnvoi);
	
			session.update(astreinteObligatoire);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session); 
			throw e;
		}
	}
	
	/**
	 * SELECT de la liste des astreintes obligatoires
	 * @param sort
	 * @param sens
	 * @param filtreDomaine
	 * @param filtreType
	 * @param filtreIndicEnvoi
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Astreinte_Obligatoire> getAll(String sort, String sens, Integer filtreDomaine, Integer filtreType, String filtreIndicEnvoi) {
		Session session = PilotageSession.getCurrentSession();
		
		Criteria criteria = session.createCriteria(Astreinte_Obligatoire.class);
		if(filtreDomaine != null && filtreDomaine > 0){
			Astreinte_Domaine aDomaine = (Astreinte_Domaine) session.load(Astreinte_Domaine.class, filtreDomaine);
			criteria.add(Restrictions.eq("domaine", aDomaine));
		}
		if(filtreType != null && filtreType > 0){
			Astreinte_Type aType = (Astreinte_Type)session.load(Astreinte_Type.class, filtreType);
			criteria.add(Restrictions.eq("type", aType));
		}
		if(filtreIndicEnvoi != null && !"".equals(filtreIndicEnvoi)){
			if(filtreIndicEnvoi.equals("oui"))
				criteria.add(Restrictions.eq("indicEnvoi", true));
			else
				criteria.add(Restrictions.eq("indicEnvoi", false));
			
		}
		
		if("domaine".equals(sort)){
			criteria.createAlias("domaine", "domaine", Criteria.LEFT_JOIN).addOrder("desc".equalsIgnoreCase(sens) ? Order.desc("domaine.domaine") : Order.asc("domaine.domaine"));
		}
		else if("type".equals(sort)){
			criteria.createAlias("type", "type", Criteria.LEFT_JOIN).addOrder("desc".equalsIgnoreCase(sens) ? Order.desc("type.type") : Order.asc("type.type"));
		}
		criteria.addOrder("desc".equalsIgnoreCase(sens) ? Order.desc(sort) : Order.asc(sort));
		
		List<Astreinte_Obligatoire> listAO = criteria.list();
		session.getTransaction().commit();
		return listAO;
	}
	
	/**
	 * COUNT teste si la combinaison domaine/type existe déjà
	 * @param id
	 * @param domaine
	 * @param type
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static boolean exists(Integer id, Integer domaine, Integer type) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		
		List<Long> results = null;
		try{
			Astreinte_Domaine aDomaine = (Astreinte_Domaine) session.load(Astreinte_Domaine.class, domaine);
			Astreinte_Type aType = (Astreinte_Type)session.load(Astreinte_Type.class, type);
			
			Criteria criteria = session.createCriteria(Astreinte_Obligatoire.class)
										.add(Restrictions.eq("type", aType))
										.add(Restrictions.eq("domaine", aDomaine));
			if(id != null)
				criteria.add(Restrictions.not(Restrictions.eq("id", id)));
			criteria.setProjection(Projections.rowCount());
							
			results = criteria.list();
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
	
	public static Integer getId(Integer domaine, Integer type, Boolean indicEnvoi){
		Session session = PilotageSession.getCurrentSession();
		Astreinte_Domaine aDomaine = (Astreinte_Domaine)session.load(Astreinte_Domaine.class, domaine);
		Astreinte_Type aType = (Astreinte_Type)session.load(Astreinte_Type.class, type);
		
		Astreinte_Obligatoire ao = (Astreinte_Obligatoire)session.createCriteria(Astreinte_Obligatoire.class)
									.add(Restrictions.eq("domaine", aDomaine))
									.add(Restrictions.eq("type", aType))
									.add(Restrictions.eq("indicEnvoi", indicEnvoi))
									.setMaxResults(1)
									.uniqueResult();
		session.getTransaction().commit();
		return ao.getId();
	}
}
