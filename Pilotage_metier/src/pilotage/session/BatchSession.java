package pilotage.session;

import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class BatchSession {

	private static final String HIBERNATE_CFG_BATCH = "/hibernate.batch.cfg.xml";
	private static SessionFactory sessionFactoryBatch;
	private static Properties prop;

	public static Properties getProp() {
		return prop;
	}

	public static void setProp(Properties prop) {
		BatchSession.prop = prop;
		sessionFactoryBatch = getNewSessionFactoryBatch();
	}

	private static SessionFactory getNewSessionFactoryBatch() {
		return new Configuration()
					.configure(PilotageSession.class.getResource(HIBERNATE_CFG_BATCH))
					.setProperty("hibernate.connection.url", prop.getProperty("hibernate.connection.url"))
					.setProperty("hibernate.connection.username", prop.getProperty("hibernate.connection.username"))
					.setProperty("hibernate.connection.password", prop.getProperty("hibernate.connection.password"))
					.buildSessionFactory();
	}

	private static SessionFactory getSessionFactoryBatch() {
		return (sessionFactoryBatch != null) ? sessionFactoryBatch : getNewSessionFactoryBatch();
	}

	public static Session openNewSession() {
		Session session = getSessionFactoryBatch().openSession();
		session.beginTransaction();
		return session;
	}
	
	public static Session getCurrentSession() {
		Session session = getSessionFactoryBatch().getCurrentSession();
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
