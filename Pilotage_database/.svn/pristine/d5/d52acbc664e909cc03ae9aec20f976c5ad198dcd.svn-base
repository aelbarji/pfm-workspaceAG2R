package pilotage.database.gup;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import pilotage.admin.session.ParametreSession;
import pilotage.metier.Com_Incident_Service;
import pilotage.metier.Com_Service;
import pilotage.metier.Incidents_Gup;
import pilotage.session.PilotageSession;

public class ComIncidentServiceDatabaseService {
	
	public static void create(Incidents_Gup incident, String[] service) throws Exception{
		List<Com_Service> listS = new ArrayList<Com_Service>();
		for (int i=0; i<service.length; i++) {
			Com_Service il = ComServiceDatabaseService.get(Integer.parseInt(service[i]));
			listS.add(il);	
		}
		Session session = PilotageSession.getCurrentSession();
		try{
			for (Com_Service s : listS) {
				Com_Incident_Service cis = new Com_Incident_Service();
				cis.setIdService(s);
				cis.setIdIncident(incident);
				session.save(cis);
			}
			session.getTransaction().commit();
		}
		catch (Exception e) {
			ParametreSession.rollbackTransaction(session);
			throw e;
		}
		
	}
	
	/**
	 * SELECT d'un service
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public static List<Com_Incident_Service> getServices(Integer incident){
		Session session = PilotageSession.getCurrentSession();
		
		List<Com_Incident_Service> s = (List<Com_Incident_Service>)session.createCriteria(Com_Incident_Service.class).add(Restrictions.eq("idIncident.id", incident)).list();
		session.getTransaction().commit();
		return s;
	}
	
	
	/**
	 * SELECT de la liste des services
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Com_Incident_Service> getAll(){
		Session session = PilotageSession.getCurrentSession();
		List<Com_Incident_Service> service = session.createCriteria(Com_Incident_Service.class).list();
		session.getTransaction().commit();
		return service;
	}
	
	
	public static void modify(Incidents_Gup incident, String[] domaineSelected) throws Exception{
		
		List<Com_Incident_Service> lists = getAll();
		for(Com_Incident_Service s : lists){
			if(s.getIdIncident().getId().equals(incident.getId())){
				delete(s.getId());
			}
		}
		create(incident, domaineSelected);
	}
	
	/**
	 * DELETE d'un service
	 * @param selectedID
	 * @throws Exception 
	 */
	public static void delete(Integer selectedID) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			Com_Incident_Service dom = (Com_Incident_Service) session.load(Com_Incident_Service.class, selectedID);
			
			session.delete(dom);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	

}
