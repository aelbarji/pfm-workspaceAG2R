package pilotage.database.gup;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import pilotage.metier.Debordement_Noc_Destinataire;
import pilotage.metier.Destinataires;
import pilotage.session.PilotageSession;
import pilotage.utils.Pagination;

public class DebordementNocDestinataireDatabaseService {

	
	/**
	 * SELECT de la liste des debordements NOC Destinataire
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Debordement_Noc_Destinataire> getAll(){
		Session session = PilotageSession.getCurrentSession();
		List<Debordement_Noc_Destinataire> debNOC = session.createCriteria(Debordement_Noc_Destinataire.class).list();
		session.getTransaction().commit();
		return debNOC;
	}
	
	/**
	 * SELECT de la liste des debordements NOC Destinataire
	 * @return
	 */
	public static List<Debordement_Noc_Destinataire> getAll(Pagination<Debordement_Noc_Destinataire> pagination){
		
		Session session = PilotageSession.getCurrentSession();
		Criteria criteria = session.createCriteria(Debordement_Noc_Destinataire.class);
			
		List<Debordement_Noc_Destinataire> debNOC = pagination.setCriteriaPagination(criteria);
		session.getTransaction().commit();
		return debNOC;
	}
	
	/**
	 * INSERT d'un nouveau debordement_noc
	 * @param datetime
	 * @param operateur
	 * @param inc_noc
	 * @param inc_op
	 * @param info_act
	*/
	
	public static void create(Destinataires d, Integer cc) throws Exception{
		Session session = PilotageSession.getCurrentSession();
		
		try{
			Debordement_Noc_Destinataire destNoc = new Debordement_Noc_Destinataire();
			destNoc.setDestinataire(d);
			destNoc.setCc(cc);
			
			session.save(destNoc);
			session.getTransaction().commit();
		}catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	
	/**
	 * SELECT debordement NOC Destinataire
	 * @param selectRow
	 * @return
	 */
	public static Debordement_Noc_Destinataire get(Integer selectRow){
		Session session = PilotageSession.getCurrentSession();
		Debordement_Noc_Destinataire debNOC = (Debordement_Noc_Destinataire)session.createCriteria(Debordement_Noc_Destinataire.class)
								.add(Restrictions.eq("id", selectRow))
								.setMaxResults(1)
								.uniqueResult();
		session.getTransaction().commit();
		return debNOC;
	}
	
	/**
	 * DELETE d'un destinataire debordement NOC
	 * @param selectedID
	 * @throws Exception 
	 */
	public static void delete(Integer selectedID) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			Debordement_Noc_Destinataire debNoc = (Debordement_Noc_Destinataire) session.load(Debordement_Noc_Destinataire.class, selectedID);
			
			session.delete(debNoc);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
}
