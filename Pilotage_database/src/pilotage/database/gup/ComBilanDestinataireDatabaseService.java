package pilotage.database.gup;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import pilotage.metier.Com_Bilan_Destinataire;
import pilotage.metier.Destinataires;
import pilotage.session.PilotageSession;
import pilotage.utils.Pagination;

public class ComBilanDestinataireDatabaseService {
	
	/**
	 * SELECT de la liste des bilan Destinataire
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Com_Bilan_Destinataire> getAll(){
		Session session = PilotageSession.getCurrentSession();
		List<Com_Bilan_Destinataire> bdes = session.createCriteria(Com_Bilan_Destinataire.class).list();
		session.getTransaction().commit();
		return bdes;
	}
	
	/**
	 * SELECT de la liste des bilan Destinataire
	 * @return
	 */
	public static List<Com_Bilan_Destinataire> getAll(Pagination<Com_Bilan_Destinataire> pagination){
		
		Session session = PilotageSession.getCurrentSession();
		Criteria criteria = session.createCriteria(Com_Bilan_Destinataire.class);
			
		List<Com_Bilan_Destinataire> bdes = pagination.setCriteriaPagination(criteria);
		session.getTransaction().commit();
		return bdes;
	}
	
	/**
	 * INSERT d'un nouveau bilan destinataire
	 * @param datetime
	 * @param operateur
	 * @param inc_noc
	 * @param inc_op
	 * @param info_act
	*/
	
	public static void create(Destinataires d, Integer cc) throws Exception{
		Session session = PilotageSession.getCurrentSession();
		
		try{
			Com_Bilan_Destinataire bdes = new Com_Bilan_Destinataire();
			bdes.setDestinataire(d);
			bdes.setCc(cc);
			
			session.save(bdes);
			session.getTransaction().commit();
		}catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	
	/**
	 * SELECT bilan Destinataire
	 * @param selectRow
	 * @return
	 */
	public static Com_Bilan_Destinataire get(Integer selectRow){
		Session session = PilotageSession.getCurrentSession();
		Com_Bilan_Destinataire bdes = (Com_Bilan_Destinataire)session.createCriteria(Com_Bilan_Destinataire.class)
								.add(Restrictions.eq("id", selectRow))
								.setMaxResults(1)
								.uniqueResult();
		session.getTransaction().commit();
		return bdes;
	}
	
	/**
	 * DELETE d'un destinataire bilan
	 * @param selectedID
	 * @throws Exception 
	 */
	public static void delete(Integer selectedID) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			Com_Bilan_Destinataire bdes = (Com_Bilan_Destinataire) session.load(Com_Bilan_Destinataire.class, selectedID);
			
			session.delete(bdes);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}

}
