package pilotage.session;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class PilotageSession {

	private static final String HIBERNATE_CFG_Pilotage = "/hibernate.pilotage.cfg.xml";
	private static SessionFactory sessionFactoryPilotage;

	static {
		sessionFactoryPilotage = getNewSessionFactoryPilotage();
	}

	private static SessionFactory getNewSessionFactoryPilotage() {
		return new Configuration().configure(PilotageSession.class.getResource(HIBERNATE_CFG_Pilotage)).buildSessionFactory();
	}

	private static SessionFactory getSessionFactoryPilotage() {
		return (sessionFactoryPilotage != null) ? sessionFactoryPilotage : getNewSessionFactoryPilotage();
	}

	public static Session openNewSession() {
		Session session = getSessionFactoryPilotage().openSession();
		session.beginTransaction();
		return session;
	}
	
	public static Session getCurrentSession() {
		Session session = getSessionFactoryPilotage().getCurrentSession();
		session.beginTransaction();
		try{
			session.createSQLQuery("SELECT 1").list();
		}
		catch (Exception e) {
			closeSession(session);
			session = getSessionFactoryPilotage().getCurrentSession();
			session.beginTransaction();
		}
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
