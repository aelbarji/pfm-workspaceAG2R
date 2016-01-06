package pilotage.database.gup;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import pilotage.admin.session.ParametreSession;
import pilotage.database.destinataires.DestinatairesDatabaseService;
import pilotage.metier.Com_Incident_Interlocuteur;
import pilotage.metier.Destinataires;
import pilotage.metier.Incidents_Gup;
import pilotage.session.PilotageSession;

public class ComIncidentInterlocuteurDatabaseService {
	
	public static void create(Incidents_Gup incident, String[] interlocuteurSelected) throws Exception{
		List<Destinataires> listInter = new ArrayList<Destinataires>();
		for (int i=0; i<interlocuteurSelected.length; i++) {
			Destinataires il = DestinatairesDatabaseService.get(Integer.parseInt(interlocuteurSelected[i]));
			listInter.add(il);	
		}
		Session session = PilotageSession.getCurrentSession();
		try{
			for (Destinataires il : listInter) {
				Com_Incident_Interlocuteur cid = new Com_Incident_Interlocuteur();
				cid.setIdInterlocuteur(il);
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
	 * SELECT d'un interlocuteur
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public static List<Com_Incident_Interlocuteur> getInterlocuteurs(Integer incident){
		Session session = PilotageSession.getCurrentSession();
		
		List<Com_Incident_Interlocuteur> i = (List<Com_Incident_Interlocuteur>)session.createCriteria(Com_Incident_Interlocuteur.class).add(Restrictions.eq("idIncident.id", incident)).list();
		session.getTransaction().commit();
		return i;
	}
	

	/**
	 * SELECT de la liste des domaines
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Com_Incident_Interlocuteur> getAll(){
		Session session = PilotageSession.getCurrentSession();
		List<Com_Incident_Interlocuteur> domaine = session.createCriteria(Com_Incident_Interlocuteur.class).list();
		session.getTransaction().commit();
		return domaine;
	}
	
	
	public static void modify(Incidents_Gup incident, String[] domaineSelected) throws Exception{
		
		List<Com_Incident_Interlocuteur> listi = getAll();
		for(Com_Incident_Interlocuteur i : listi){
			if(i.getIdIncident().getId().equals(incident.getId())){
				delete(i.getId());
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
			Com_Incident_Interlocuteur inte = (Com_Incident_Interlocuteur) session.load(Com_Incident_Interlocuteur.class, selectedID);
			
			session.delete(inte);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	

}
