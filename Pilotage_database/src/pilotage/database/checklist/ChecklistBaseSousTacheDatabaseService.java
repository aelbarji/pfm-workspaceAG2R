package pilotage.database.checklist;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import pilotage.metier.Checklist_Base;
import pilotage.metier.Checklist_Base_Soustache;
import pilotage.metier.Checklist_Consigne_Documents;
import pilotage.metier.Checklist_Consignes;
import pilotage.session.PilotageSession;


public class ChecklistBaseSousTacheDatabaseService {
	
	/**
	 * SELECT de la liste suivant la base
	 * @param baseID
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Checklist_Base_Soustache> getListFromBase(Integer baseID){
		Session session = PilotageSession.getCurrentSession();
		
		Checklist_Base base = (Checklist_Base)session.load(Checklist_Base.class, baseID);
		List<Checklist_Base_Soustache> sousTaches = session.createCriteria(Checklist_Base_Soustache.class)
															.add(Restrictions.eq("idBase", base))
															.add(Restrictions.eq("actif", Boolean.TRUE))
															.addOrder(Order.asc("decalageStamp"))
															.list();
		session.getTransaction().commit();
		return sousTaches;
	}
	
	/**
	 * Récupère l'id de la sous tache correspondant aux paramètres et étant le dernier en date (max id) 
	 * @param listSousTache
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Integer getId(List<String> listSousTache) {
		Session session = PilotageSession.getCurrentSession();
		List<Checklist_Consignes> consignes = session.createCriteria(Checklist_Consignes.class)
													.add(Restrictions.eq("consigne", listSousTache.get(3)))
													.list();
		
		Checklist_Base_Soustache cbs = (Checklist_Base_Soustache)session.createCriteria(Checklist_Base_Soustache.class)
										.add(Restrictions.eq("decalageString",listSousTache.get(1) + ":" + listSousTache.get(2) + ":00"))
										.add(Restrictions.eq("nomSousTache",listSousTache.get(0)))
										.add(Restrictions.in("idConsigne",consignes))
										.addOrder(Order.desc("id"))
										.setMaxResults(1)
										.uniqueResult();
		session.getTransaction().commit();
		return cbs.getId();
	}
	
	public static Checklist_Base_Soustache get(Integer ID){
		Session session = PilotageSession.getCurrentSession();
		Checklist_Base_Soustache sousTaches = (Checklist_Base_Soustache)session.createCriteria(Checklist_Base_Soustache.class)
												.add(Restrictions.eq("id", ID))
												.setMaxResults(1)
												.uniqueResult();
		session.getTransaction().commit();
		return sousTaches;
	}
	
	/**
	 * Récupère la liste des documents de la sous tache correspondant aux paramètres et étant le
	 * dernier en date (max id)
	 * 
	 * @param soustache id
	 * @return liste checklist_consignes
	 */
	@SuppressWarnings("unchecked")
	public static List<Checklist_Consigne_Documents> getAllDocumentsForSousTache(Integer consigneID){ 
		
		Session session = PilotageSession.getCurrentSession();
		Checklist_Consignes consigne = (Checklist_Consignes) session.load(Checklist_Consignes.class, consigneID);
		
		List<Checklist_Consigne_Documents> listDocumentsSousTaches = session.createCriteria(Checklist_Consigne_Documents.class).add(Restrictions.eq("idConsigne", consigne)).list();
		
		
		session.getTransaction().commit();
		
		return listDocumentsSousTaches;
	}
}
