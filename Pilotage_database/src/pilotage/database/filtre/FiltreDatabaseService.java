package pilotage.database.filtre;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import pilotage.metier.Filtre;
import pilotage.metier.Users;
import pilotage.session.PilotageSession;

public class FiltreDatabaseService {

	/**
	 * SELECT de toutes les filtres suivant le userLogged et titrePage
	 * @param userLogged
	 * @param titrePage
	 * @return
	 */
	public static Filtre getFiltre(Integer userLoggedId, String titrePage){
		Session session = PilotageSession.getCurrentSession();
		Users user = (Users) session.get(Users.class, userLoggedId);
		Filtre filtre = (Filtre) session.createCriteria(Filtre.class)
							.add(Restrictions.eq("userId", user))
							.add(Restrictions.eq("titrePageId", titrePage))
							.uniqueResult();
		session.getTransaction().commit();
		return filtre;

	}
	
	/**
	 * INSERT d'un nouveau filtre
	 * @param userLoggedId
	 * @param titrePageId
	 * @param filtreString
	 * @throws Exception
	 */
	public static void create(Integer userLoggedId, String titrePageId, String filtreString) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		Users userLogged = (Users)session.get(Users.class, userLoggedId);
		
		try{
			Filtre filtre = new Filtre();			
			filtre.setUserId(userLogged);
			filtre.setTitrePageId(titrePageId);
			filtre.setFiltreString(filtreString);			
			session.save(filtre);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	/**
	 * UPDATE d'un filtre
	 * @param filtreId
	 * @param filtreString
	 * @throws Exception
	 */
	public static void update(Integer filtreId, String filtreString) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		Filtre f = (Filtre)session.get(Filtre.class, filtreId);
		try{
			f.setFiltreString(filtreString);			
			session.update(f);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
}
