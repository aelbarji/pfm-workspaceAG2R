package pilotage.database.consigne;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import pilotage.metier.Consignes;
import pilotage.metier.Consignes_Type;
import pilotage.metier.Users;
import pilotage.session.PilotageSession;
import pilotage.utils.Pagination;

public class ConsignesDatabaseService {
	
	/**
	 * SELECT de la liste des consignes courantes
	 * @param pagination
	 * @param sort
	 * @param sens
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Consignes> getAllCurrentConsignes(){
		Session session = PilotageSession.getCurrentSession();
		List<Consignes> listConsignes = session.createCriteria(Consignes.class)
								.add(Restrictions.eq("old", Boolean.FALSE))
								.list();
		session.getTransaction().commit();
		return listConsignes;
	}
	
	/**
	 * SELECT de la liste des consignes de utilisateur avec son id
	 * @param pagination
	 * @param sort
	 * @param sens
	 * @return
	 */
	public static List<Consignes> getAllCurrentConsignesByType(Integer typeID, Pagination<Consignes> pagination, String sort, String sens){
		Session session = PilotageSession.getCurrentSession();
		Consignes_Type type = (Consignes_Type)session.load(Consignes_Type.class, typeID);
		Criteria criteria = session.createCriteria(Consignes.class)
								.add(Restrictions.eq("type", type))
								.add(Restrictions.eq("old", Boolean.FALSE))
								.addOrder("desc".equalsIgnoreCase(sens) ? Order.desc(sort) : Order.asc(sort));
		List<Consignes> listConsignes = pagination.setCriteriaPagination(criteria);
		session.getTransaction().commit();
		return listConsignes;
	}
	
	/**
	 * UPDATE d'une consigne
	 * @param id
	 * @param date
	 * @param text
	 * @param couleur
	 * @param createur
	 * @param old
	 * @throws Exception 
	 */
	public static void modify(Integer id, Date date, String text, Boolean couleur,Users createur,Boolean old) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		
		try{
			Consignes consignes = (Consignes)session.load(Consignes.class, id);
			if(date != null)
				consignes.setDate(date);
			
			if(text != null)
				consignes.setText(text);
			
			if(couleur != null)
				consignes.setCouleur(couleur);
			
			if(createur != null)
				consignes.setCreateur(createur);
			
			if(old != null)
				consignes.setOld(old);
	
			session.update(consignes);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	/**
	 * UPDATE d'une consigne
	 * @param id
	 * @param date
	 * @param text
	 * @param couleur
	 * @param createur
	 * @param old
	 * @throws Exception 
	 */
	public static void modify(Integer id, Date date, String text, Boolean couleur,Users createur, String fichierconsigne, Boolean old) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		
		try{
			Consignes consignes = (Consignes)session.get(Consignes.class, id);
			if(date != null)
				consignes.setDate(date);
			
			if(text != null)
				consignes.setText(text);
			
			if(couleur != null)
				consignes.setCouleur(couleur);
			
			if(createur != null)
				consignes.setCreateur(createur);
			
			if(old != null)
				consignes.setOld(old);
			
			if(fichierconsigne != null)
				consignes.setFichierconsigne(fichierconsigne);
			
	
			session.update(consignes);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	/**
	 * INSERT d'une nouvelle consigne
	 * @param date
	 * @param text
	 * @param couleur
	 * @param createur
	 * @param old
	 * @throws Exception 
	 */
	public static void create(Date date, String text, Boolean couleur,Users createur,Boolean old) throws Exception {
		Consignes consignes = new Consignes();
		consignes.setDate(date);
		consignes.setText(text);
		consignes.setCouleur(couleur);
		consignes.setCreateur(createur);
		consignes.setOld(old);
		
		Session session = PilotageSession.getCurrentSession();
		try{
			session.save(consignes);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	
	/**
	 * INSERT d'une nouvelle consigne
	 * @param date
	 * @param text
	 * @param couleur
	 * @param createur
	 * @param old
	 * @throws Exception 
	 */
	public static void create(Date date, String text, Boolean couleur,Users createur, String fichierconsigne, Boolean old, Consignes_Type type) throws Exception {
		Consignes consignes = new Consignes();
		consignes.setDate(date);
		consignes.setText(text);
		consignes.setFichierconsigne(fichierconsigne);
		consignes.setCouleur(couleur);
		consignes.setCreateur(createur);
		consignes.setOld(old);
		consignes.setType(type);
		
		Session session = PilotageSession.getCurrentSession();
		try{
			session.save(consignes);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	/**
	 * SELECT de la consigne identifié par le paramètre
	 * @param selectRow
	 * @return
	 */
	public static Consignes get(Integer selectRow) {
		Session session = PilotageSession.getCurrentSession();
		Consignes consignes = (Consignes)session.createCriteria(Consignes.class)
								.add(Restrictions.eq("id", selectRow))
								.setMaxResults(1)
								.uniqueResult();
		session.getTransaction().commit();
		return consignes;
	}
	
	public static Integer getId(String text, Boolean couleur){
		Session session = PilotageSession.getCurrentSession();
		
		Consignes c = (Consignes)session.createCriteria(Consignes.class)
									.add(Restrictions.eq("text", text))
									.add(Restrictions.eq("couleur", couleur))									
									.setMaxResults(1)
									.uniqueResult();
		session.getTransaction().commit();
		return c.getId();
	}
	
	/**
	 * SELECT de la consigne identifié par le paramètre
	 * @param selectRow
	 * @return
	 */
	public static Consignes_Type getType(Integer id) {
		Session session = PilotageSession.getCurrentSession();
		Consignes_Type type = (Consignes_Type)session.createCriteria(Consignes_Type.class)
								.add(Restrictions.eq("id", id))
								.setMaxResults(1)
								.uniqueResult();
		session.getTransaction().commit();
		return type;
	}
}
