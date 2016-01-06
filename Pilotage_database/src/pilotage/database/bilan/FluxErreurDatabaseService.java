package pilotage.database.bilan;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import pilotage.metier.Flux_CFT;
import pilotage.metier.Flux_Erreur;
import pilotage.service.date.DateService;
import pilotage.session.PilotageSession;

public class FluxErreurDatabaseService {

	/**
	 * INSERT/UPDATE d'un flux en erreur
	 * @param cftId
	 * @param date
	 * @param libelle
	 * @throws Exception
	 */
	public static void save(int cftId, Date date, String libelle) throws Exception {
		if(exists(date, cftId)){
			Flux_Erreur fe = get(date, cftId);
			Session session = PilotageSession.getCurrentSession();
			try{
				fe.setErreur(libelle);
				session.update(fe);
				session.getTransaction().commit();
			}
			catch (Exception e) {
				PilotageSession.rollbackTransaction(session);
				throw e;
			}
		}
		else{
			Session session = PilotageSession.getCurrentSession();
			try{
				Flux_CFT fc = (Flux_CFT)session.load(Flux_CFT.class, cftId);
				Flux_Erreur fe = new Flux_Erreur();
				fe.setDate(date);
				fe.setErreur(libelle);
				fe.setFlux(fc);
				
				session.save(fe);
				session.getTransaction().commit();
			}
			catch (Exception e) {
				PilotageSession.rollbackTransaction(session);
				throw e;
			}
		}
	}

	/**
	 * @param id
	 * @return
	 */
	public static Flux_Erreur get(int id) {
		// TODO Auto-generated method stub
		Session session = PilotageSession.getCurrentSession();
		Flux_Erreur fe = (Flux_Erreur) session.createCriteria(Flux_Erreur.class)
					.add(Restrictions.eq("id", id))
					.uniqueResult();
		session.getTransaction().commit();
		return fe;
	}
	
	/**
	 * SELECT d'un flux erreur
	 * @param date
	 * @param cftID
	 * @return
	 */
	public static Flux_Erreur get(Date date, Integer cftID) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		
		Flux_CFT cft = (Flux_CFT)session.load(Flux_CFT.class, cftID);
		Flux_Erreur fe = (Flux_Erreur) session.createCriteria(Flux_Erreur.class)
									.add(Restrictions.eq("flux", cft))
									.add(Restrictions.between("date", DateService.getDateEarlier(date), DateService.getDateLater(date)))
									.uniqueResult();
		session.getTransaction().commit();
		return fe;
	}
	
	/**
	 * SELECT des flux en erreur pour la date précisée
	 * @param date
	 * @return
	 * @throws  
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public static List<Flux_Erreur> getAllByDate(Date date) throws Exception{
		Session session = PilotageSession.getCurrentSession();
		List<Flux_Erreur> listError = session.createCriteria(Flux_Erreur.class)
										.add(Restrictions.between("date", DateService.getDateEarlier(date), DateService.getDateLater(date)))
										.addOrder(Order.asc("date"))
										.list();
		session.getTransaction().commit();
		return listError;
	}
	
	/**
	 * SELECT des flux en erreur pour la date periode
	 * @param dateDebut
	 * @param dateFin
	 * @return
	 * @throws  
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public static List<Flux_Erreur> getAllByDatePeriode(Date dateDebut, Date dateFin) throws Exception{
		Session session = PilotageSession.getCurrentSession();
		List<Flux_Erreur> listError = session.createCriteria(Flux_Erreur.class)
										.add(Restrictions.between("date", DateService.getDateEarlier(dateDebut), DateService.getDateLater(DateService.addDays(dateFin, 1))))
										.addOrder(Order.asc("date"))
										.list();
		session.getTransaction().commit();
		return listError;
	}
	
	/**
	 * COUNT test si le flux existe pour le jour indiqué
	 * @param date
	 * @param idcft
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public static boolean exists(Date date, Integer idcft) throws Exception{
		Session session = PilotageSession.getCurrentSession();
		
		Flux_CFT cft = (Flux_CFT)session.load(Flux_CFT.class, idcft);
		List<Long> results = session.createCriteria(Flux_Erreur.class)
									.add(Restrictions.between("date", DateService.getDateEarlier(date), DateService.getDateLater(date)))
									.add(Restrictions.eq("flux", cft))
									.setProjection(Projections.rowCount())
									.list();
		session.getTransaction().commit();
		
		if (results != null && results.size() > 0 && results.get(0) > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * DELETE d'un flux en erreur
	 * @param idCFT
	 * @param selectedDate
	 * @throws Exception 
	 */
	public static void delete(int idCFT, Date date) throws Exception {
		Flux_Erreur fe = get(date, idCFT);
		Session session = PilotageSession.getCurrentSession();
		try{
			session.delete(fe);
			session.getTransaction().commit();
		}
		catch(Exception e){
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
}
