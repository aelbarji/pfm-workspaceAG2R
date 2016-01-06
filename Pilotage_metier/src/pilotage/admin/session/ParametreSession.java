/**
 * pilotage.admin.session
 * 10 juin 2011
 */
package pilotage.admin.session;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import pilotage.session.PilotageSession;

/**
 * @author xxu
 *
 */
public class ParametreSession {


	private static final String HIBERNATE_CFG_Pilotage = "/hibernate.parametre.cfg.xml";
	private static SessionFactory sessionFactoryParametre;

	static {
		sessionFactoryParametre = getNewSessionFactoryParametre();
	}

	private static SessionFactory getNewSessionFactoryParametre() {
		return new Configuration().configure(PilotageSession.class.getResource(HIBERNATE_CFG_Pilotage)).buildSessionFactory();
	}

	private static SessionFactory getSessionFactoryParametre() {
		return (sessionFactoryParametre != null) ? sessionFactoryParametre : getNewSessionFactoryParametre();
	}

	public static Session openNewSession() {
		Session session = getSessionFactoryParametre().openSession();
		session.beginTransaction();
		return session;
	}
	
	public static Session getCurrentSession() {
		Session session = getSessionFactoryParametre().getCurrentSession();
		session.beginTransaction();
		return session;
	}
	
	public static void closeSession(Session session) {
		if ((session != null) && session.isOpen()) {
			session.close();
		}
	}
	
	public static void rollbackTransaction(Session session) {
		if ((session != null) && session.isOpen()) {
			session.getTransaction().rollback();
		}
	}
}
