package pilotage.database.user.profil.management;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import pilotage.metier.Users;
import pilotage.session.PilotageSession;

public class UserProfilDatabaseService {

	/**
	 * UPDATE du profil user avec mot de passe
	 * @param nom
	 * @param prenom
	 * @param email
	 * @param md5NewPassword
	 * @throws Exception 
	 */
	public static void modifyWithPwd(Integer userId, String nom, String prenom, String email, String md5NewPassword) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		
		try{
			Users user = (Users)session.load(Users.class, userId);
			user.setNom(nom);
			user.setPrenom(prenom);
			user.setEmail(email);
			user.setPassword(md5NewPassword);
			session.update(user);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	/**
	 * UPDATE du profil user sans mot de passe 
	 * @param nom
	 * @param prenom
	 * @param email
	 * @throws Exception 
	 */
	public static void modifyWithoutPwd(Integer userId, String nom, String prenom, String email) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		
		try{
			Users user = (Users)session.load(Users.class, userId);
			user.setNom(nom);
			user.setPrenom(prenom);
			user.setEmail(email);
			session.update(user);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	/**
	 * get user information
	 * @param userId
	 * @return user
	 * @throws Exception 
	 */
	public static Users getUserById(Integer userId) throws Exception{
		
		Session session = PilotageSession.getCurrentSession();
		Users user = null;
		try {
			user = (Users)session.createCriteria(Users.class)
						.add(Restrictions.eq("id", userId))
						.setMaxResults(1)
						.uniqueResult();
			session.getTransaction().commit();
		} catch (Exception e) {
			PilotageSession.closeSession(session);
			throw e;
		}
		
		return user;
	}
}
