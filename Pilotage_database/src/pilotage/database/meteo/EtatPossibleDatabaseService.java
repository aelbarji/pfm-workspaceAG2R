package pilotage.database.meteo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import pilotage.metier.Meteo_EtatPossible;
import pilotage.metier.Meteo_TypeIndic_EtatPoss;
import pilotage.metier.Meteo_TypeIndicateur;
import pilotage.session.PilotageSession;


public class EtatPossibleDatabaseService{
	
	/**
	 * SELECT etat possible selon id
	 * @param id
	 */
	public static Meteo_EtatPossible get(Integer id){
		Session session = PilotageSession.getCurrentSession();
		Meteo_EtatPossible etat = (Meteo_EtatPossible)session.createCriteria(Meteo_EtatPossible.class)
									.add(Restrictions.eq("id", id))
									.setMaxResults(1)
									.uniqueResult();
		session.getTransaction().commit();
		return etat;
	}
	
	/**
	 * SELECT de la liste des etats possible selon 
	 * @param id
	 */
	@SuppressWarnings("unchecked")
	public static List<Meteo_TypeIndic_EtatPoss> getEtatsByType(Meteo_TypeIndicateur type) {
		Session session = PilotageSession.getCurrentSession();
		List<Meteo_TypeIndic_EtatPoss> typeIndicList = (List<Meteo_TypeIndic_EtatPoss>)session.createCriteria(Meteo_TypeIndic_EtatPoss.class)
														.add(Restrictions.eq("typeIndic", type))
														.list();
		session.getTransaction().commit();
		return typeIndicList;
	}
	
	/**
	 * SELECT etat selon libelle
	 * @param libelle
	 */
	public static Meteo_EtatPossible getByName(String etat){
		Session session = PilotageSession.getCurrentSession();
		Meteo_EtatPossible e = (Meteo_EtatPossible)session.createCriteria(Meteo_EtatPossible.class)
									.add(Restrictions.like("libelle_etat", etat))
									.setMaxResults(1)
									.uniqueResult();
		session.getTransaction().commit();
		return e;
	}
	
	/**
	 * SELECT de tous les etats
	 */
	@SuppressWarnings("unchecked")
	public static List<Meteo_EtatPossible> getAll(){
		Session session = PilotageSession.getCurrentSession();
		List<Meteo_EtatPossible> caisseList = (List<Meteo_EtatPossible>)session.createCriteria(Meteo_EtatPossible.class)
													.addOrder(Order.asc("libelle_etat"))
													.list();
		session.getTransaction().commit();
		return caisseList;
	}
	
	/**
	 * INSERT d'un nouvel état M2T2O
	 * @param libelle
	 * @param codeCouleur
	 * @param definition
	 * @throws Exception 
	 */
	public static void create(String libelle, String codeCouleur, String definition, Integer impact) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			Meteo_EtatPossible etat = new Meteo_EtatPossible();
			etat.setLibelle_etat(libelle);
			etat.setCouleur(codeCouleur);
			etat.setDefinition(definition);
			etat.setImpact(impact);
			session.save(etat);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}	
	}
	
	/**
	 * MODIFY un nouvel état M2T2O
	 * @param id
	 * @param libelle
	 * @param codeCouleur
	 * @param definition
	 * @throws Exception 
	 */
	public static void modify(Integer id, String libelle, String codeCouleur, String definition, Integer impact) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			Meteo_EtatPossible etat = (Meteo_EtatPossible)session.load(Meteo_EtatPossible.class, id);
			etat.setLibelle_etat(libelle);
			etat.setCouleur(codeCouleur);
			etat.setDefinition(definition);
			etat.setImpact(impact);
			session.save(etat);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}	
	}
	
	/**
	 * DELETE un état
	 * @param id
	 * @throws Exception 
	 */
	public static void delete(Integer id) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			Meteo_EtatPossible etat = (Meteo_EtatPossible)session.load(Meteo_EtatPossible.class, id);
			session.delete(etat);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
}
