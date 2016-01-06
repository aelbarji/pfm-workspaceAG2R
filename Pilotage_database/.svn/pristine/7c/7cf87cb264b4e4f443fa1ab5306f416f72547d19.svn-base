package pilotage.database.planning;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import pilotage.metier.Planning_Cra_Partie_Jour;
import pilotage.metier.Planning_Vacation;
import pilotage.session.PilotageSession;

public class PlanningVacationsDatabaseService {
	
	/**
	 * SELECT de toutes les vacations dans l'ordre alphabetique
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Planning_Vacation> getAll(){
		Session session = PilotageSession.getCurrentSession();
		List<Planning_Vacation> listVacations = session.createCriteria(Planning_Vacation.class)
												.add(Restrictions.eq("active", Boolean.TRUE))
												.addOrder(Order.asc("libelle"))
												.list();
		session.getTransaction().commit();
		return listVacations;
	}
	
	/**
	 * SELECT de la vacation identifiée par selectRow
	 * @param selectRow
	 * @return
	 */
	public static Planning_Vacation get(Integer selectRow) {
		Session session = PilotageSession.getCurrentSession();
		Planning_Vacation pv = (Planning_Vacation)session.createCriteria(Planning_Vacation.class)
								.add(Restrictions.eq("id", selectRow))
								.setMaxResults(1)
								.uniqueResult();
		session.getTransaction().commit();
		return pv;
	}
	
	/**
	 * COUNT test si le libellé existe déjà
	 * @param id - à ne pas tester
	 * @param libelle
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static boolean exists(Integer id, String libelle){
		Session session = PilotageSession.getCurrentSession();
		
		Criteria criteria = session.createCriteria(Planning_Vacation.class);
		criteria.add(Restrictions.eq("libelle", libelle));
		if(id != null)
			criteria.add(Restrictions.ne("id", id));
		criteria.setProjection(Projections.rowCount());

		List<Long> results = criteria.list();
		session.getTransaction().commit();
		
		if (results != null && results.size() > 0 && results.get(0) > 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * INSERT d'une nouvelle vacation
	 * @param codeCouleur
	 * @param heureDebut
	 * @param heureFin
	 * @param libelle
	 * @return
	 * @throws Exception
	 */
	public static Integer create(String codeCouleur, Date heureDebut, Date heureFin, String libelle, Integer iPartiJour) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			Planning_Cra_Partie_Jour pcpj = (Planning_Cra_Partie_Jour)session.load(Planning_Cra_Partie_Jour.class, iPartiJour);
			Planning_Vacation pv = new Planning_Vacation();
			pv.setCodeCouleur(codeCouleur);
			pv.setHeureDebut(heureDebut);
			pv.setHeureFin(heureFin);
			pv.setLibelle(libelle);
			pv.setPartiJour(pcpj);
			pv.setActive(true);
			
			session.save(pv);
			session.getTransaction().commit();
			
			return pv.getId();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	/**
	 * UPDATE d'une vacation
	 * @param selectRow
	 * @param codeCouleur
	 * @param heureDebut
	 * @param heureFin
	 * @param libelle
	 * @throws Exception
	 */
	public static void modify(Integer selectRow, String codeCouleur, Date heureDebut, Date heureFin, String libelle, Integer iPartiJour) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			Planning_Cra_Partie_Jour pcpj = (Planning_Cra_Partie_Jour)session.load(Planning_Cra_Partie_Jour.class, iPartiJour);
			Planning_Vacation pv = (Planning_Vacation)session.load(Planning_Vacation.class, selectRow);
			pv.setCodeCouleur(codeCouleur);
			pv.setHeureDebut(heureDebut);
			pv.setHeureFin(heureFin);
			pv.setLibelle(libelle);
			pv.setPartiJour(pcpj);
			
			session.update(pv);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	/**
	 * DESACTIVE une vacation
	 * @param selectRow
	 * @throws Exception
	 */
	public static void desactiver(Integer selectRow) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			Planning_Vacation pv = (Planning_Vacation)session.load(Planning_Vacation.class, selectRow);
			pv.setActive(false);
			
			session.update(pv);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	/**
	 * DELETE d'une vacation
	 * @param selectRow
	 * @throws Exception
	 */
	public static void delete(Integer selectRow) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			Planning_Vacation pv = (Planning_Vacation)session.load(Planning_Vacation.class, selectRow);
			session.delete(pv);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}	
	}
	
	public static Planning_Vacation getByLibelle(String libelle){
		Session session = PilotageSession.getCurrentSession();
		Planning_Vacation pv = (Planning_Vacation)session.createCriteria(Planning_Vacation.class)
									.add(Restrictions.eq("libelle", libelle))
									.setMaxResults(1)
									.uniqueResult();
		session.getTransaction().commit();
		return pv;
	}
}
