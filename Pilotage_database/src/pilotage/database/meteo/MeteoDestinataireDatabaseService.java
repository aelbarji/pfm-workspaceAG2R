package pilotage.database.meteo;

import java.util.ArrayList;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import pilotage.metier.Meteo_Destinataire;
import pilotage.metier.Meteo_GroupeMeteo;
import pilotage.metier.Users;
import pilotage.session.PilotageSession;

public class MeteoDestinataireDatabaseService {

	/**
	 * SELECT des destinataires d'un groupe météo
	 * @param idGroupe
	 */
	@SuppressWarnings("unchecked")
	public static List<Users> getDestinataires(Integer idGroupe){
		List<Users> destinataires = new ArrayList<Users>();
		if(idGroupe != null){
			Session session = PilotageSession.getCurrentSession();

			Meteo_GroupeMeteo groupe = (Meteo_GroupeMeteo)session.load(Meteo_GroupeMeteo.class, idGroupe);
		
			List<Meteo_Destinataire> groupeDestinataire = session.createCriteria(Meteo_Destinataire.class)
														.add(Restrictions.eq("groupeMeteo", groupe))
														.createAlias("destinataire", "users", Criteria.LEFT_JOIN)
														.addOrder(Order.asc("users.nom"))
														.list();
			
			for(Meteo_Destinataire as : groupeDestinataire){
				if(!destinataires.contains(as.getDestinataire())){
					destinataires.add(as.getDestinataire());
				}
			}
			
			session.getTransaction().commit();
		}

		return destinataires;
	}
	
	/**
	 * INSERT des destinataires d'un groupe météo
	 * @param nomGroupe
	 * @param liste destinataires
	 * @throws Exception 
	 */
	public static void create(Meteo_GroupeMeteo groupe, List<Users> listDestinataires) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			for(Users user : listDestinataires){
				Meteo_Destinataire meteoDest = new Meteo_Destinataire();
				meteoDest.setDestinataire(user);
				meteoDest.setGroupeMeteo(groupe);
				session.save(meteoDest);
			}
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}	
	}
	
	/**
	 * MODIFY des destinataires d'un groupe météo
	 * @param idGroupe
	 * @param destinataireToAdd
	 * @param destinataireToDelete
	 * @throws Exception 
	 */
	public static void modify(Integer idGroupe, List<Integer> destinataireToAdd, List<Integer> destinataireToDelete) throws Exception{
		Session session = PilotageSession.getCurrentSession();
		try{
			Meteo_GroupeMeteo groupe = (Meteo_GroupeMeteo)session.load(Meteo_GroupeMeteo.class, idGroupe);
			
			List<Meteo_Destinataire> listDestinataireToAdd = new ArrayList<Meteo_Destinataire>();
			List<Meteo_Destinataire> listDestinataireToDelete = new ArrayList<Meteo_Destinataire>();
			
			for(Integer destID : destinataireToAdd){
				Users user = (Users)session.load(Users.class, destID);
				Meteo_Destinataire meteoDest = new Meteo_Destinataire();
				meteoDest.setDestinataire(user);
				meteoDest.setGroupeMeteo(groupe);
				
				listDestinataireToAdd.add(meteoDest);
			}
			
			for(Integer destID : destinataireToDelete){
				Users user = (Users)session.load(Users.class, destID);
				Meteo_Destinataire meteoDest = (Meteo_Destinataire)session.createCriteria(Meteo_Destinataire.class)
																	.add(Restrictions.eq("destinataire", user))
																	.add(Restrictions.eq("groupeMeteo", groupe))
																	.setMaxResults(1)
																	.uniqueResult();
				listDestinataireToDelete.add(meteoDest);
			}
			
			for(Meteo_Destinataire groupeDest : listDestinataireToAdd) {
				session.save(groupeDest);
			}
			for(Meteo_Destinataire groupeDest : listDestinataireToDelete) {
				session.delete(groupeDest);
			}
			
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
}
