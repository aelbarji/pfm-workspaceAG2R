package pilotage.database.gup;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import pilotage.admin.session.ParametreSession;
import pilotage.database.destinataires.DestinatairesDatabaseService;
import pilotage.metier.Com_Incident_Detection;
import pilotage.metier.Destinataires;
import pilotage.metier.Incidents_Gup;
import pilotage.session.PilotageSession;

public class ComIncidentDetectionDatabaseService {
	
	public static void create(Incidents_Gup incident, String[] detectionSelected) throws Exception{
		List<Destinataires> listdetect = new ArrayList<Destinataires>();
		for (int i=0; i<detectionSelected.length; i++) {
			Destinataires il = DestinatairesDatabaseService.get(Integer.parseInt(detectionSelected[i]));
			listdetect.add(il);	
		}
		Session session = PilotageSession.getCurrentSession();
		try{
			for (Destinataires il : listdetect) {
				Com_Incident_Detection cid = new Com_Incident_Detection();
				cid.setIdDetection(il);
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
	 * SELECT d'une detection
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public static List<Com_Incident_Detection> getDetection(Integer incident){
		Session session = PilotageSession.getCurrentSession();
		
		List<Com_Incident_Detection> s = (List<Com_Incident_Detection>)session.createCriteria(Com_Incident_Detection.class).add(Restrictions.eq("idIncident.id", incident)).list();
		session.getTransaction().commit();
		return s;
	}
	
	/**
	 * SELECT de la liste des domaines
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Com_Incident_Detection> getAll(){
		Session session = PilotageSession.getCurrentSession();
		List<Com_Incident_Detection> detection = session.createCriteria(Com_Incident_Detection.class).list();
		session.getTransaction().commit();
		return detection;
	}
	
	
	public static void modify(Incidents_Gup incident, String[] domaineSelected) throws Exception{
		
		List<Com_Incident_Detection> listd = getAll();
		for(Com_Incident_Detection d : listd){
			if(d.getIdIncident().getId().equals(incident.getId())){
				delete(d.getId());
			}
		}
		create(incident, domaineSelected);
	}
	
	/**
	 * DELETE d'un interlocuteur
	 * @param selectedID
	 * @throws Exception 
	 */
	public static void delete(Integer selectedID) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			Com_Incident_Detection det = (Com_Incident_Detection) session.load(Com_Incident_Detection.class, selectedID);
			
			session.delete(det);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}

}
