package pilotage.database.consigne;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import pilotage.admin.session.ParametreSession;
import pilotage.metier.Bbr_Consignes;
import pilotage.session.BbrSession;
import pilotage.utils.Pagination;

public class ConsigneBbrDatabaseService {
	/**
	 * SELECT d'une consigne bbr
	 * @param idConsigneBbr
	 * @return
	 */
	public static Bbr_Consignes get(long id) {
		Session sessionBbr = BbrSession.getCurrentSession();
		Bbr_Consignes bbr = (Bbr_Consignes) sessionBbr.createCriteria(Bbr_Consignes.class)
								.add(Restrictions.eq("id", id))
								.setMaxResults(1)
								.uniqueResult();
		sessionBbr.getTransaction().commit();
		return bbr;
	}
	
	/**
	 * SELECT de toutes les consignes bbr suivant les filtres
	 * @param pagination
	 * @param sort
	 * @param sens
	 * @param filtreOrigine
	 * @param filtreComposant
	 * @param filtreType
	 * @param filtreLocalisation 
	 * @return
	 */
	public static List<Bbr_Consignes> getAll(Pagination<Bbr_Consignes> pagination, String sort, String sens, String filtreOrigine, String filtreComposant, String filtreType, String filtreLocalisation) {
		Session sessionBbr = BbrSession.getCurrentSession();
		Criteria criteria = sessionBbr.createCriteria(Bbr_Consignes.class);
		
		if (filtreOrigine != null && !"".equals(filtreOrigine)) {
			criteria.add(Restrictions.ilike("bbrorigine", "%" + filtreOrigine + "%"));
		}
		if (filtreComposant != null && !"".equals(filtreComposant)) {
			criteria.add(Restrictions.ilike("bbrcomposant", "%" + filtreComposant + "%"));
		}
		if (filtreType != null && !"".equals(filtreType)) {
			criteria.add(Restrictions.ilike("bbrtype", "%" + filtreType + "%"));
		}
		if (filtreLocalisation != null && !"".equals(filtreLocalisation)) {
//			Consignes_Fichier fichier = (Consignes_Fichier)session.load(Consignes_Fichier.class, filtreLocalisation);
			criteria.add(Restrictions.ilike("localisation", "%"+filtreLocalisation+"%"));
		}
		
		criteria.addOrder("desc".equals(sens) ? Order.desc(sort) : Order.asc(sort));

		List<Bbr_Consignes> listBBR = pagination.setCriteriaPagination(criteria);
		sessionBbr.getTransaction().commit();
		return listBBR;
	}
	
	/**
	 * INSERT d'une consigne bbr
	 * @param origine
	 * @param composant
	 * @param type
	 * @param localisation
	 * @param priority
	 * @throws Exception
	 */
	public static long create(String origine, String composant, String type, String localisation, BigDecimal priority) throws Exception {
		
		Session sessionBbr = BbrSession.getCurrentSession();
		try{		
		
			Bbr_Consignes bbr = new Bbr_Consignes(origine,composant,type,priority,localisation);

			sessionBbr.save(bbr);
			sessionBbr.getTransaction().commit();
			
			return bbr.getId();
		}
		catch (Exception e) {
			ParametreSession.rollbackTransaction(sessionBbr);
			throw e;
		}
	}

	/**
	 * UPDATE d'une consigne bbr
	 * @param selectRow
	 * @param origine
	 * @param composant
	 * @param type
	 * @param localisation
	 * @param priority
	 * @throws Exception 
	 */
	public static void modify(long selectRow, String origine, String composant, String type, String localisation, BigDecimal priority) throws Exception {

		Session sessionBbr = BbrSession.getCurrentSession();
		try{
			Bbr_Consignes bbr = (Bbr_Consignes)sessionBbr.load(Bbr_Consignes.class, selectRow);
			
			bbr.setBbrorigine(origine);
			bbr.setBbrcomposant(composant);
			bbr.setBbrtype(type);
			bbr.setLocalisation(localisation);
			bbr.setBbrpriority(priority);
			
			sessionBbr.save(bbr);
			
			sessionBbr.getTransaction().commit();
		}
		catch (Exception e) {
			BbrSession.rollbackTransaction(sessionBbr);
			throw e;
		}
	}
	
	/**
	 * DELETE d'une consigne bbr
	 * @param selectRow
	 * @throws Exception 
	 */
	public static void delete(long selectRow) throws Exception {
		Session session = BbrSession.getCurrentSession();
		try{
			Bbr_Consignes bbr = (Bbr_Consignes)session.load(Bbr_Consignes.class, selectRow);
			session.delete(bbr);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			BbrSession.rollbackTransaction(session);
			throw e;
		}
	}
}
