package pilotage.database.bilan;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import pilotage.admin.session.ParametreSession;
import pilotage.metier.Bilan_Colonnes;
import pilotage.metier.Bilan_Envoie;
import pilotage.metier.Incident_Colonnes;
import pilotage.session.PilotageSession;
import pilotage.utils.Pagination;

public class BilanColonnesDatabaseService {
	
	@SuppressWarnings("unchecked")
	public static List<Bilan_Colonnes> getAll() {
		Session session = PilotageSession.getCurrentSession();
		List<Bilan_Colonnes> bcList = session.createCriteria(Bilan_Colonnes.class).list();		
		session.getTransaction().commit();
		return bcList;
	}
	
	public static List<Bilan_Colonnes> getAll(Pagination<Bilan_Colonnes> pagination, Integer bilan) {
		Session session = PilotageSession.getCurrentSession();
		Criteria criteria = session.createCriteria(Bilan_Colonnes.class);
		criteria.addOrder(Order.asc("position"));
		criteria.add(Restrictions.eq("bilan.id", bilan));
		
		List<Bilan_Colonnes> bcList = pagination.setCriteriaPagination(criteria);
		session.getTransaction().commit();
		return bcList;
	}
	
	public static List<Bilan_Colonnes> getAll(Pagination<Bilan_Colonnes> pagination) {
		Session session = PilotageSession.getCurrentSession();
		Criteria criteria = session.createCriteria(Bilan_Colonnes.class);
		List<Bilan_Colonnes> bcList = pagination.setCriteriaPagination(criteria);
		session.getTransaction().commit();
		return bcList;
	}
	
	public static Bilan_Colonnes get(Integer selectRow){
		Session session = ParametreSession.getCurrentSession();
		Bilan_Colonnes bc = (Bilan_Colonnes)session.createCriteria(Bilan_Colonnes.class)
								.add(Restrictions.eq("id", selectRow)) 
								.setMaxResults(1)
								.uniqueResult();
		session.getTransaction().commit();
		return bc;
	}
	
	/**
	 * SELECT selon le bilan choisi
	 * @param bilan
	*/
	
	@SuppressWarnings("unchecked")
	public static List<Bilan_Colonnes> getByBilan(Integer idbilan){
		Session session = PilotageSession.getCurrentSession();
		List<Bilan_Colonnes> bc = (List<Bilan_Colonnes>)session.createCriteria(Bilan_Colonnes.class)
								.add(Restrictions.eq("bilan.id", idbilan)) 
								.addOrder(Order.asc("position"))
								.list();
		session.getTransaction().commit();
		return bc;
	}
	
	/**
	 * INSERT d'un nouveau bilan colonne
	 * @param nom
	*/
	
	public static void create(String nomDsBilan, Integer position, Integer taille, Incident_Colonnes ic, Bilan_Envoie b) throws Exception{
		Session session = PilotageSession.getCurrentSession();
		
		try{
			Bilan_Colonnes bc = new Bilan_Colonnes();
			bc.setNomDsBilan(nomDsBilan);
			bc.setPosition(position);
			bc.setTaille(taille);
			bc.setIncidentColonne(ic);
			bc.setBilan(b);	
			session.save(bc);
			session.getTransaction().commit();
		}catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	/**
	 * UPDATE d'un bilan colonne
	 * @param id
	 * @param nom
	 */
	
	public static void modify(Integer id, String nomDsBilan, Integer position, Integer taille, Incident_Colonnes ic, Bilan_Envoie b) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			Bilan_Colonnes bc = (Bilan_Colonnes)session.load(Bilan_Colonnes.class, id);
			bc.setNomDsBilan(nomDsBilan);
			bc.setPosition(position);
			bc.setTaille(taille);
			bc.setIncidentColonne(ic);
			bc.setBilan(b);
			session.update(bc);
			session.getTransaction().commit();		
		}catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	/**
	 * UPDATE de la taille d'un bilan colonne
	 * @param id
	 * @param nom
	 */
	
	public static void modifyTaille(Integer id, Integer taille) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			Bilan_Colonnes bc = (Bilan_Colonnes)session.load(Bilan_Colonnes.class, id);
			bc.setTaille(taille);
			session.update(bc);
			session.getTransaction().commit();
		}catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	/**
	 * DELETE d'un bilan colonne
	 * @param selectedID
	 * @throws Exception 
	 */
	public static void delete(Integer selectedID) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			Bilan_Colonnes bc = (Bilan_Colonnes) session.load(Bilan_Colonnes.class, selectedID);
			session.delete(bc);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}


}
