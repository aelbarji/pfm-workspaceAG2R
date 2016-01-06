package pilotage.database.gup;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import pilotage.admin.session.ParametreSession;
import pilotage.metier.Com_Incident_Domaine;
import pilotage.metier.Com_domaine;
import pilotage.metier.Incidents_Gup;
import pilotage.session.PilotageSession;

public class ComIncidentDomaineDatabaseService {
	
	public static void create(Incidents_Gup incident, String[] domaineSelected) throws Exception{
		List<Com_domaine> listdomaine = new ArrayList<Com_domaine>();
		for (int i=0; i<domaineSelected.length; i++) {
			Com_domaine cd = DomaineDatabaseService.get(Integer.parseInt(domaineSelected[i]));
			listdomaine.add(cd);	
		}
		Session session = PilotageSession.getCurrentSession();
		try{
			for (Com_domaine cd : listdomaine) {
				Com_Incident_Domaine cid = new Com_Incident_Domaine();
				cid.setDomaine(cd);
				cid.setIdIncident(incident);
				session.save(cid);
			}
			session.getTransaction().commit();
		}
		catch (Exception e) {
			ParametreSession.rollbackTransaction(session);
			throw e;
		}
		
	}
	
	/**
	 * SELECT de la liste des domaines
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Com_Incident_Domaine> getAll(){
		Session session = PilotageSession.getCurrentSession();
		List<Com_Incident_Domaine> domaine = session.createCriteria(Com_Incident_Domaine.class).list();
		session.getTransaction().commit();
		return domaine;
	}
	
	
	public static void modify(Incidents_Gup incident, String[] domaineSelected) throws Exception{
		
		List<Com_Incident_Domaine> listd = getAll();
		for(Com_Incident_Domaine d : listd){
			if(d.getIdIncident().getId().equals(incident.getId())){
				delete(d.getId());
			}
		}
		create(incident, domaineSelected);
	}
	
	/**
	 * DELETE d'un debordement NOC
	 * @param selectedID
	 * @throws Exception 
	 */
	public static void delete(Integer selectedID) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			Com_Incident_Domaine dom = (Com_Incident_Domaine) session.load(Com_Incident_Domaine.class, selectedID);
			
			session.delete(dom);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	/**
	 * SELECT d'un domaine
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public static List<Com_Incident_Domaine> getDomaine(Integer incident1){
		Session session = PilotageSession.getCurrentSession();
	
		List<Com_Incident_Domaine> d = (List<Com_Incident_Domaine>)session.createCriteria(Com_Incident_Domaine.class).add(Restrictions.eq("idIncident.id", incident1)).list();
		session.getTransaction().commit();
		return d;
	}

}
