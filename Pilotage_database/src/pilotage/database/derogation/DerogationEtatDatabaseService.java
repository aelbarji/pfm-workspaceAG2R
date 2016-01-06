package pilotage.database.derogation;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;


import pilotage.metier.Derogation_Etat;
import pilotage.service.constants.PilotageConstants;
import pilotage.session.PilotageSession;

public class DerogationEtatDatabaseService {
	
	/**
	 * SELECT de tous les états
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Derogation_Etat> getAll(){
		Session session = PilotageSession.getCurrentSession();
		List<Derogation_Etat> derogationEtatList = session.createCriteria(Derogation_Etat.class).list();
		session.getTransaction().commit();
		return derogationEtatList;
	}

	/**
	 * SELECT d'un etat
	 * @param selectRow
	 * @return
	 */
	public static Derogation_Etat get(Integer selectRow) {
		Session session = PilotageSession.getCurrentSession();
		Derogation_Etat derogationEtat = (Derogation_Etat)session.createCriteria(Derogation_Etat.class)
							.add(Restrictions.eq("id", selectRow))
							.setMaxResults(1)
							.uniqueResult();
		session.getTransaction().commit();
		return derogationEtat;
	}

	/**
	 * SELECT de tous les états disponibles pour la validation
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Derogation_Etat> getAllForValidation() {
		Session session = PilotageSession.getCurrentSession();
		List<Derogation_Etat> derogationEtatList = session.createCriteria(Derogation_Etat.class)
															.add(Restrictions.or(Restrictions.eq("id", PilotageConstants.DEROGATION_ETAT_SUSPENDUE), Restrictions.eq("id", PilotageConstants.DEROGATION_ETAT_ENVOYEE)))
															.list();
		session.getTransaction().commit();
		return derogationEtatList;
	}
}
