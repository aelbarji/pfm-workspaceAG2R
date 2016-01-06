package pilotage.database.destinataires;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import pilotage.metier.Destinataires;
import pilotage.session.PilotageSession;
import pilotage.utils.Pagination;

public class DestinatairesDatabaseService {
	
	/**
	 * SELECT de la liste des destinataires
	 * @return
	 */
	public static List<Destinataires> getAll(Pagination<Destinataires> pagination){
		Session session = PilotageSession.getCurrentSession();
		Criteria criteria = session.createCriteria(Destinataires.class);
		
		criteria.addOrder(Order.desc("nom"));
		
		List<Destinataires> dest = pagination.setCriteriaPagination(criteria);
		session.getTransaction().commit();
		return dest;
	}
	
	/**
	 * SELECT de la liste des destinataires
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Destinataires> getAll(){
		Session session = PilotageSession.getCurrentSession();
		List<Destinataires> dest = session.createCriteria(Destinataires.class).list();
		
		session.getTransaction().commit();
		return dest;
	}
	
	
	/**
	 * SELECT destinataire
	 * @param selectRow
	 * @return
	 */
	public static Destinataires get(Integer selectRow){
		Session session = PilotageSession.getCurrentSession();
		Destinataires dest = (Destinataires)session.createCriteria(Destinataires.class)
								.add(Restrictions.eq("id", selectRow))
								.setMaxResults(1)
								.uniqueResult();
		session.getTransaction().commit();
		return dest;
	}
	
	
	/**
	 * INSERT d'un nouveau destinataire
	 * @param datetime
	 * @param operateur
	 * @param inc_noc
	 * @param inc_op
	 * @param info_act
	 */
	public static Integer create(String nom, String mail, String prenom) throws Exception{
		Session session = PilotageSession.getCurrentSession();
		
		try{
			Destinataires dest = new Destinataires();
			dest.setMail(mail);
			dest.setNom(nom);
			dest.setPrenom(prenom);
			
			session.save(dest);
			session.getTransaction().commit();
			
			return dest.getId();
		}catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	/**
	 * UPDATE d'un destinataire
	 * @param id
	 * @param datetime
	 * @param operateur
	 * @param inc_noc
	 * @param inc_op
	 * @param info_act
	 * @throws Exception 
	 */
	public static void modify(Integer id, String nom, String mail, String prenom) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			Destinataires dest = (Destinataires)session.load(Destinataires.class, id);
			dest.setMail(mail);
			dest.setNom(nom);
			dest.setPrenom(prenom);
		
			session.update(dest);
			session.getTransaction().commit();
			
		}catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	/**
	 * DELETE d'un destinataire
	 * @param selectedID
	 * @throws Exception 
	 */
	public static void delete(Integer selectedID) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			Destinataires dest = (Destinataires) session.load(Destinataires.class, selectedID);
			
			session.delete(dest);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	
}

