package pilotage.database.login;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import pilotage.admin.metier.Parametre;
import pilotage.database.admin.ParametreDatabaseService;
import pilotage.metier.Users;
import pilotage.service.convertor.MD5Convertor;
import pilotage.session.PilotageSession;

public class LoginDatabaseService {
	
	/**
	 * get the parameter's value
	 * @return
	 */
	public static List<Parametre> getParameterValue(){
		return ParametreDatabaseService.getAll();
	}
	
	/**
	 * Check if the login password and user name are correct
	 * @param username
	 * @param password
	 * @return  true     login or password correct
	 *          false    Invalid login or password
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public static boolean findUser(String username, String password, String encryptKey) throws Exception{
		String md5pwd = MD5Convertor.crypt(password, encryptKey);
		
		Session session = PilotageSession.getCurrentSession();
		
		List<Long> results = null;
		try{
			results= (List<Long>)session.createCriteria(Users.class)
				.add(Restrictions.eq("login", username))
				.add(Restrictions.eq("password", md5pwd))
				.add(Restrictions.eq("actif", true))
				.setProjection(Projections.rowCount())
				.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			PilotageSession.closeSession(session);
			throw e;
		}

		if (results != null && results.size() > 0 && results.get(0) > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * get all the user information
	 * @param username       login username
	 * @return
	 * @throws Exception 
	 */
	public static Users getUserByLogin(String username) throws Exception{
		
		Session session = PilotageSession.getCurrentSession();
		Users user = null;
		try {
			user = (Users)session.createCriteria(Users.class)
						.add(Restrictions.eq("login", username))
						.add(Restrictions.eq("actif", true))
						.setMaxResults(1)
						.uniqueResult();
			session.getTransaction().commit();
		} catch (Exception e) {
			PilotageSession.closeSession(session);
			throw e;
		}
		
		return user;
	}
	
	/**
	 * update password in database
	 * @param username
	 * @param password
	 * @throws Exception 
	 */
	public static void updatePwd(Users user, String password) throws Exception{
		
		Session session = PilotageSession.getCurrentSession();
		
		try {
			user.setPassword(password);
			session.update(user);
			session.getTransaction().commit();
		} catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
}
