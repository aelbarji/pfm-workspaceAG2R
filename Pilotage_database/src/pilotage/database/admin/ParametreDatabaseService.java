package pilotage.database.admin;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import pilotage.admin.metier.Parametre;
import pilotage.admin.session.ParametreSession;

public class ParametreDatabaseService {
	/**
	 * SELECT tous les paramètres
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Parametre> getAll(){
		Session session = ParametreSession.getCurrentSession();
		List<Parametre> paramList = session.createCriteria(Parametre.class).list();
		session.getTransaction().commit();
		return paramList;
	}
	
	/**
	 * SELECT le parametre dont l'id est le libellé
	 * @param libelle
	 * @return
	 */
	public static Parametre get(String libelle){
		Session session = ParametreSession.getCurrentSession();
		Parametre param = (Parametre)session.createCriteria(Parametre.class)
							.add(Restrictions.eq("libelle", libelle))
							.setMaxResults(1)
							.uniqueResult();
		session.getTransaction().commit();
		return  param;
	}
	
	/**
	 * DELETE le parametre identifié par le libellé
	 * @param libelle
	 * @throws Exception 
	 */
	public static void delete(String libelle) throws Exception {
		Parametre param = ParametreDatabaseService.get(libelle);
		Session session = ParametreSession.getCurrentSession();
		try{
			session.delete(param);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			ParametreSession.rollbackTransaction(session);
			throw e;
		} 
	}

	/**
	 * INSERT le parametre
	 * @param libelle
	 * @param valeur
	 * @throws Exception 
	 */
	public static void save(String libelle, String valeur) throws Exception {
		Parametre param = ParametreDatabaseService.get(libelle);
		if(param == null){
			param = new Parametre();
			param.setLibelle(libelle);
		}
		param.setValeur(valeur);
		ParametreDatabaseService.checkSizes(param);
		
		Session session = ParametreSession.getCurrentSession();
		try{
			session.saveOrUpdate(param);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			ParametreSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	/**
	 * CHECK si les tailles des champs sont conformes à la base de données. Si non il y a troncature
	 * @param tp
	 */
	private static void checkSizes(Parametre param){
		if(param.getLibelle() != null && param.getLibelle().length() > 127){
			param.setLibelle(param.getLibelle().substring(0, 127));
		}
		if(param.getValeur() != null && param.getValeur().length() > 127){
			param.setValeur(param.getValeur().substring(0, 127));
		}
	}
}
