package pilotage.database.gup;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import pilotage.admin.session.ParametreSession;
import pilotage.database.applicatif.ApplicatifDatabaseService;
import pilotage.metier.Applicatifs_Liste;
import pilotage.metier.Com_Incident_Appli;
import pilotage.metier.Incidents_Gup;
import pilotage.session.PilotageSession;

public class ComIncidentAppliDatabaseService {

	public static void create(Incidents_Gup incident, String[] appliSelected) throws Exception{
		List<Applicatifs_Liste> listAppli = new ArrayList<Applicatifs_Liste>();
		for (int i=0; i < appliSelected.length; i++) {
			Applicatifs_Liste dl = ApplicatifDatabaseService.get(Integer.parseInt(appliSelected[i]));
			listAppli.add(dl);	
		}
		Session session = PilotageSession.getCurrentSession();
		try{
			for (Applicatifs_Liste al : listAppli) {
				Com_Incident_Appli cia = new Com_Incident_Appli();
				cia.setIdAppli(al);
				cia.setIdIncident(incident);
				session.save(cia);
			}
			session.getTransaction().commit();
		}
		catch (Exception e) {
			ParametreSession.rollbackTransaction(session);
			throw e;
		}
		
	}
	
	/**
	 * SELECT d'un applicatif
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public static List<Com_Incident_Appli> getAppli(Integer incident){
		Session session = PilotageSession.getCurrentSession();
		
		List<Com_Incident_Appli> a = (List<Com_Incident_Appli>)session.createCriteria(Com_Incident_Appli.class).add(Restrictions.eq("idIncident.id", incident)).list();
		session.getTransaction().commit();
		return a;
	}
	
	
	/**
	 * SELECT de la liste des applis
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Com_Incident_Appli> getAll(){
		Session session = PilotageSession.getCurrentSession();
		List<Com_Incident_Appli> appli = session.createCriteria(Com_Incident_Appli.class).list();
		session.getTransaction().commit();
		return appli;
	}
	
	
	public static void modify(Incidents_Gup incident, String[] domaineSelected) throws Exception{
		
		List<Com_Incident_Appli> lista = getAll();
		for(Com_Incident_Appli appli : lista){
			if(appli.getIdIncident().getId().equals(incident.getId())){
				delete(appli.getId());
			}
		}
		create(incident, domaineSelected);
	}
	
	/**
	 * DELETE d'una appli
	 * @param selectedID
	 * @throws Exception 
	 */
	public static void delete(Integer selectedID) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			Com_Incident_Appli appli = (Com_Incident_Appli) session.load(Com_Incident_Appli.class, selectedID);
			
			session.delete(appli);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
} 
