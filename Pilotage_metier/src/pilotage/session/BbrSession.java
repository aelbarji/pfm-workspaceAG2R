package pilotage.session;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class BbrSession {


	private static final String HIBERNATE_CFG_Bbr = "/hibernate.bbracnsg.cfg.xml";
	private static SessionFactory sessionFactoryBbr;

	static {
		sessionFactoryBbr = getNewSessionFactoryBbr();
	}

	private static SessionFactory getNewSessionFactoryBbr() {
		return new Configuration().configure(BbrSession.class.getResource(HIBERNATE_CFG_Bbr)).buildSessionFactory();
	}

	private static SessionFactory getSessionFactoryBbr() {
		return (sessionFactoryBbr != null) ? sessionFactoryBbr : getNewSessionFactoryBbr();
	}

	public static Session openNewSession() {
		Session sessionBBr = getSessionFactoryBbr().openSession();
		sessionBBr.beginTransaction();
		return sessionBBr;
	}
	
	public static Session getCurrentSession() {
		Session sessionBBr = getSessionFactoryBbr().getCurrentSession();
		sessionBBr.beginTransaction();
		try{
			sessionBBr.createSQLQuery("SELECT 1 FROM dual").list();
		}
		catch (Exception e) {
			closeSession(sessionBBr);
			sessionBBr = getSessionFactoryBbr().getCurrentSession();
			sessionBBr.beginTransaction();
		}
		return sessionBBr;
	}
	
	public static void closeSession(Session sessionBBr) {
		if ((sessionBBr != null) && sessionBBr.isOpen()) {
			sessionBBr.close();
		}
	}
	
	public static void rollbackTransaction(Session sessionBBr) {
		if ((sessionBBr != null) && sessionBBr.isOpen()) {
			sessionBBr.getTransaction().rollback();
		}
	}
}
