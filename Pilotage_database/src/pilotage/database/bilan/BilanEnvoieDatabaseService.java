package pilotage.database.bilan;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import pilotage.metier.Bilan_Destinataires;
import pilotage.metier.Bilan_Envoie;
import pilotage.session.PilotageSession;

public class BilanEnvoieDatabaseService {

	/**
	 * SELECT de tous les types de bilan
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Bilan_Envoie> getAll() {
		Session session = PilotageSession.getCurrentSession();
		List<Bilan_Envoie> listEnvoie = session.createCriteria(Bilan_Envoie.class)
										.addOrder(Order.asc("nom"))
										.list();
		session.getTransaction().commit();
		return listEnvoie;
	}


	/**
	 * COUNT test si le nom du type de bilan existe déjà
	 * @param id - id à ne pas tester
	 * @param nom
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static boolean exists(Integer id, String nom) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		
		Criteria criteria = session.createCriteria(Bilan_Envoie.class)
									.add(Restrictions.eq("nom", nom))
									.setProjection(Projections.rowCount());
		if(id != null)
			criteria.add(Restrictions.ne("id", id));
		
		List<Long> results = criteria.list();
		
		session.getTransaction().commit();
		
		if (results != null && results.size() > 0 && results.get(0) > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * INSERT d'un nouveau type de bilan
	 * @param nom
	 * @param libelle
	 * @param calauseSelect
	 * @param vacation
	 * @param actionEPI
	 * @param espaceDisk
	 * @param disknonOCEOR
	 * @param etatCFT
	 * @param nbDailySent 
	 * @throws HibernateException
	 * @throws Exception
	 */
	
	public static void create(String nom, String libelle, String clauseSelect,
			boolean vacation, boolean actionEPI, boolean espaceDisk,
			boolean disknonOCEOR, boolean etatCFT, Integer nbDailySent) throws HibernateException, Exception {
		Session session = PilotageSession.getCurrentSession();
		
		try{
			Bilan_Envoie be = new Bilan_Envoie();
			be.setNom(nom);
			be.setLibelle(libelle);
			be.setClauseSelect(clauseSelect);
			be.setVacation(vacation);
			be.setActionEPI(actionEPI);
			be.setEspaceDisk(espaceDisk);
			be.setDisknonOCEOR(disknonOCEOR);
			be.setEtatCFT(etatCFT);
			be.setNbDailySent(nbDailySent);
			setPara(be);
			
			session.save(be);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	/**
	 * This is for setting the values for old table
	 * @param be
	 */
	private static void setPara(Bilan_Envoie be){
		be.setLogo(""); 
		be.setStatEPI (false);
		be.setLundi(new Byte("0"));
		be.setMardi(new Byte("0"));
		be.setMercredi(new Byte("0"));
		be.setJeudi(new Byte("0"));
		be.setVendredi(new Byte("0"));
		be.setSamedi(new Byte("0"));
		be.setDimanche(new Byte("0"));
		be.setFerie(new Byte("0"));
	}

	/**
	 * SELECT d'un type de bilan
	 * @param selectedID
	 * @return
	 */
	public static Bilan_Envoie get(Integer selectedID) {
		Session session = PilotageSession.getCurrentSession();
		Bilan_Envoie result = (Bilan_Envoie) session.createCriteria(Bilan_Envoie.class)
							.add(Restrictions.eq("id", selectedID))
							.uniqueResult();
		session.getTransaction().commit();
		return result;
	}

	/**
	 * DELETE d'un type de bilan
	 * @param selectedID
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public static void delete(Integer selectedID) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			Bilan_Envoie typeBilan = (Bilan_Envoie)session.load(Bilan_Envoie.class, selectedID);
			List<Bilan_Destinataires> listDestina = session.createCriteria(Bilan_Destinataires.class)
														.add(Restrictions.eq("type_bilan", typeBilan))
														.list();
			for(Bilan_Destinataires bd : listDestina){
				session.delete(bd);
			}
			
			session.delete(typeBilan);
			session.getTransaction().commit();
		}
		catch (Exception e){
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}

	/**
	 * MODIFY d'un type de bilan
	 * @param selectedID
	 * @param nom
	 * @param libelle
	 * @param clauseSelect
	 * @param vacation
	 * @param actionEPI
	 * @param espaceDisk
	 * @param disknonOCEOR
	 * @param etatCFT
	 * @param nbDailySent 
	 * @throws Exception 
	 */
	public static void update(Integer selectedID, String nom, String libelle,
			String clauseSelect, boolean vacation, boolean actionEPI,
			boolean espaceDisk, boolean disknonOCEOR, boolean etatCFT, Integer nbDailySent) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			Bilan_Envoie be = (Bilan_Envoie) session.load(Bilan_Envoie.class, selectedID);
			be.setNom(nom);
			be.setLibelle(libelle);
			be.setClauseSelect(clauseSelect);
			be.setVacation(vacation);
			be.setActionEPI(actionEPI);
			be.setEspaceDisk(espaceDisk);
			be.setDisknonOCEOR(disknonOCEOR);
			be.setEtatCFT(etatCFT);
			be.setNbDailySent(nbDailySent);
			
			session.update(be);
			session.getTransaction().commit();
		}
		catch (Exception e){
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}

	public static Integer getId(String libelle){
		Session session = PilotageSession.getCurrentSession();
		Bilan_Envoie be = (Bilan_Envoie)session.createCriteria(Bilan_Envoie.class)
									.add(Restrictions.eq("nom", libelle))
									.setMaxResults(1)
									.uniqueResult();
		session.getTransaction().commit();
		return be.getId();
	}
}
