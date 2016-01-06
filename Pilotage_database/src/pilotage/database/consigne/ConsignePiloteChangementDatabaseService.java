package pilotage.database.consigne;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import pilotage.metier.Consignes_Pilotes_Changements;
import pilotage.session.PilotageSession;

public class ConsignePiloteChangementDatabaseService {
	
	/**
	 * SELECT de la liste des consignes changement
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Consignes_Pilotes_Changements> getAll(){
		Session session = PilotageSession.getCurrentSession();
		List<Consignes_Pilotes_Changements> cpChangementList = session.createCriteria(Consignes_Pilotes_Changements.class).list();
		session.getTransaction().commit();
		return cpChangementList;
	}
	
	/**
	 * DELETE d'un consigne changement à l'aide de son id
	 * @param selectRow
	 */
	public static void delete(int selectRow) {
		Session session = PilotageSession.getCurrentSession();
		Consignes_Pilotes_Changements cpChangement = (Consignes_Pilotes_Changements)session.load(Consignes_Pilotes_Changements.class, selectRow);
		session.delete(cpChangement);
		session.getTransaction().commit();
	}

	/**
	 * SELECT du consigne validation avec son id
	 * @param selectRow
	 * @return
	 */
	public static Consignes_Pilotes_Changements get(Integer selectRow) {
		Session session = PilotageSession.getCurrentSession();
		Consignes_Pilotes_Changements cpChangements = (Consignes_Pilotes_Changements)session.createCriteria(Consignes_Pilotes_Changements.class)
							.add(Restrictions.eq("id", selectRow))
							.setMaxResults(1)
							.uniqueResult();
		session.getTransaction().commit();
		return cpChangements;
	}
}
