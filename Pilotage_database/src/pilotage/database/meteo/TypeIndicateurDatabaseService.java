package pilotage.database.meteo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import pilotage.metier.Meteo_EtatPossible;
import pilotage.metier.Meteo_Indicateur_Format;
import pilotage.metier.Meteo_TypeIndic_EtatPoss;
import pilotage.metier.Meteo_TypeIndicateur;
import pilotage.session.PilotageSession;

public class TypeIndicateurDatabaseService {
	
	/**
	 * SELECT de toutes les types d'indicateur 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Meteo_TypeIndicateur> getAll() {
		Session session = PilotageSession.getCurrentSession();
		List<Meteo_TypeIndicateur> typeIndicList = (List<Meteo_TypeIndicateur>)session.createCriteria(Meteo_TypeIndicateur.class)
													.addOrder(Order.asc("type"))
													.list();
		session.getTransaction().commit();
		return typeIndicList;
	}

	/**
	 * SELECT de toutes les types d'indicateur selon id
	 * @param id
	 * @return
	 */
	public static Meteo_TypeIndicateur get(Integer id){
		Session session = PilotageSession.getCurrentSession();
		Meteo_TypeIndicateur indic = (Meteo_TypeIndicateur)session.createCriteria(Meteo_TypeIndicateur.class)
																	.add(Restrictions.eq("id", id))
																	.setMaxResults(1)
																	.uniqueResult();
		session.getTransaction().commit();
		return indic;
	}
	
	/**
	 * SELECT de toutes les listes types d'indicateur 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Meteo_TypeIndicateur> getAllListe() {
		Session session = PilotageSession.getCurrentSession();
		List<Meteo_TypeIndicateur> typeIndicList = (List<Meteo_TypeIndicateur>)session.createCriteria(Meteo_TypeIndicateur.class)
													.createAlias("format", "form", Criteria.LEFT_JOIN)
													.addOrder(Order.asc("type"))
													.add(Restrictions.eq("form.format", "liste"))
													.list();
		session.getTransaction().commit();
		return typeIndicList;
	}
	/**
	 * CREATE type indicateur
	 * @param type
	 * @param listEtat
	 * @throws Exception 
	 */
	public static void create(String type, List<Map<String, String>> listEtat) throws Exception{
		Session session = PilotageSession.getCurrentSession();
		try{
			Meteo_Indicateur_Format format = (Meteo_Indicateur_Format)session.createCriteria(Meteo_Indicateur_Format.class)
											.add(Restrictions.like("format", "liste"))
											.setMaxResults(1)
											.uniqueResult();
			
			Meteo_TypeIndicateur typeIndic = new Meteo_TypeIndicateur();
			typeIndic.setFormat(format);
			typeIndic.setType(type);
			session.save(typeIndic);
			
			if(listEtat != null){
				//Etats
				List<Meteo_TypeIndic_EtatPoss> listTypeEtat = new ArrayList<Meteo_TypeIndic_EtatPoss>();
				for(Map<String, String> ligneEtat : listEtat){
					Meteo_EtatPossible etat = (Meteo_EtatPossible) session.load(Meteo_EtatPossible.class, Integer.parseInt(ligneEtat.get("etatID")));
					Meteo_TypeIndic_EtatPoss te = new Meteo_TypeIndic_EtatPoss();
					te.setTypeIndic(typeIndic);
					te.setEtatPoss(etat);
					
					listTypeEtat.add(te);
				}
		
				for(Meteo_TypeIndic_EtatPoss typeEtat : listTypeEtat){
					session.save(typeEtat);
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
	 * COUNT teste si le typeIndicateur passé en paramètre existe déjà
	 * @param id
	 * @param type
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public static Boolean exists(Integer id, String type) throws Exception{
		Session session = PilotageSession.getCurrentSession();
		
		Meteo_Indicateur_Format format = (Meteo_Indicateur_Format)session.createCriteria(Meteo_Indicateur_Format.class)
				.add(Restrictions.like("format", "liste"))
				.setMaxResults(1)
				.uniqueResult();
		
		List<Long> results = null;
		try{
			Criteria criteria = session.createCriteria(Meteo_TypeIndicateur.class);
			criteria.add(Restrictions.eq("type", type));
			criteria.add(Restrictions.eq("format", format));
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
	
	/**
	 * SELECT de la liste des typeIndic etats possible 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Meteo_TypeIndic_EtatPoss> getEtatsTypeIndic() {
		Session session = PilotageSession.getCurrentSession();
		List<Meteo_TypeIndic_EtatPoss> typeIndicList = (List<Meteo_TypeIndic_EtatPoss>)session.createCriteria(Meteo_TypeIndic_EtatPoss.class)
													.list();
		session.getTransaction().commit();
		return typeIndicList;
	}
	
	
	/**
	 * SELECT de la liste des etats possible selon type indicateur
	 * @param typeIndic
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Meteo_TypeIndic_EtatPoss> getEtats(Meteo_TypeIndicateur typeIndic) {
		Session session = PilotageSession.getCurrentSession();
		List<Meteo_TypeIndic_EtatPoss> typeIndicList = (List<Meteo_TypeIndic_EtatPoss>)session.createCriteria(Meteo_TypeIndic_EtatPoss.class)
														.add(Restrictions.eq("typeIndic", typeIndic))
														.list();
		session.getTransaction().commit();
		return typeIndicList;
	}
	
	/**
	 * MODIFY de la liste des etats possible d'un type indicateur
	 * @param idType
	 * @param type
	 * @param etatToAdd
	 * @param etatToDelete
	 * @throws Exception
	 */
	public static void modify(Integer idType, String type, List<Integer> etatToAdd, List<Integer> etatToDelete) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			Meteo_TypeIndicateur  typeIndic = (Meteo_TypeIndicateur)session.load(Meteo_TypeIndicateur.class, idType);
			typeIndic.setType(type);
			
			List<Meteo_TypeIndic_EtatPoss> listTypeEtatToAdd = new ArrayList<Meteo_TypeIndic_EtatPoss>();
			List<Meteo_TypeIndic_EtatPoss> listTypeEtatToDelete = new ArrayList<Meteo_TypeIndic_EtatPoss>();
			for(Integer etatID : etatToAdd){
				Meteo_EtatPossible etat = (Meteo_EtatPossible)session.load(Meteo_EtatPossible.class, etatID);
				Meteo_TypeIndic_EtatPoss typeEtat = new Meteo_TypeIndic_EtatPoss();
				typeEtat.setTypeIndic(typeIndic);
				typeEtat.setEtatPoss(etat);
				
				listTypeEtatToAdd.add(typeEtat);
			}
			
			for(Integer etatID : etatToDelete){
				Meteo_EtatPossible etat = (Meteo_EtatPossible)session.load(Meteo_EtatPossible.class, etatID);
				Meteo_TypeIndic_EtatPoss typeEtat = (Meteo_TypeIndic_EtatPoss)session.createCriteria(Meteo_TypeIndic_EtatPoss.class)
																	.add(Restrictions.eq("typeIndic", typeIndic))
																	.add(Restrictions.eq("etatPoss", etat))
																	.setMaxResults(1)
																	.uniqueResult();
				listTypeEtatToDelete.add(typeEtat);
			}
			
			for(Meteo_TypeIndic_EtatPoss typeEtat : listTypeEtatToAdd) {
				session.save(typeEtat);
			}
			for(Meteo_TypeIndic_EtatPoss typeEtat : listTypeEtatToDelete) {
				session.delete(typeEtat);
			}
			
			session.save(typeIndic);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	/**
	 * DELETE d'un type indicateur Météo
	 * @param id
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public static void delete(Integer id) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			Meteo_TypeIndicateur typeIndic = (Meteo_TypeIndicateur)session.load(Meteo_TypeIndicateur.class, id);
			List<Meteo_TypeIndic_EtatPoss> listTypeEtat = (List<Meteo_TypeIndic_EtatPoss>)session.createCriteria(Meteo_TypeIndic_EtatPoss.class)
															.add(Restrictions.eq("typeIndic", typeIndic))
															.list();
			if(listTypeEtat != null){
				for(Meteo_TypeIndic_EtatPoss typeEtat : listTypeEtat) {
					session.delete(typeEtat);
				}
			}
			session.delete(typeIndic);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
}
