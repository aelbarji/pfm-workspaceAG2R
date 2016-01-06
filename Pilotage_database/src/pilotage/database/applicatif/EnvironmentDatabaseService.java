package pilotage.database.applicatif;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import pilotage.metier.Appli_Envir;
import pilotage.metier.Applicatifs_Liste;
import pilotage.metier.Environnement;
import pilotage.session.PilotageSession;

public class EnvironmentDatabaseService {

	/**
	 * SELECT de tous les environnements ordonnés par nom
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Environnement> getAll() {
		Session session = PilotageSession.getCurrentSession();
		List<Environnement> list = session.createCriteria(Environnement.class)
										.addOrder(Order.asc("environnement"))
										.list();
		session.getTransaction().commit();
		return list;
	}

	/**
	 * SELECT INTER de tous les services attachés à la liste des applicatifs en paramètre
	 * @param listAppID
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Environnement> getIntersectionByAppList(List<Integer> listAppID) {
		List<Applicatifs_Liste> apps = ApplicatifDatabaseService.getMultiple(listAppID);
		List<Environnement> listEnvi = new ArrayList<Environnement>();
		
		if(apps != null && !apps.isEmpty()){
			Session session = PilotageSession.getCurrentSession();
			for(Applicatifs_Liste al : apps){
				List<Appli_Envir> listApp_Envi = session.createCriteria(Appli_Envir.class)
													.add(Restrictions.eq("idApplication", al))
													.createAlias("idEnvironnement", "env", Criteria.LEFT_JOIN)
													.addOrder(Order.asc("env.environnement"))
													.list();
				//première appli
				if(listEnvi.isEmpty()){
					for(Appli_Envir ae : listApp_Envi){
						listEnvi.add(ae.getIdEnvironnement());
					}
				}
				//intersection des applis précédentes avec l'actuelle
				else{
					List<Environnement> envTemp = new ArrayList<Environnement>(); // va contenir l'inter entre les applis précédentes et l'appli actuelle
					for(Appli_Envir ae : listApp_Envi){
						if(listEnvi.contains(ae.getIdEnvironnement()))
							envTemp.add(ae.getIdEnvironnement());
					}
					listEnvi = envTemp;
				}
				
				//si l'intersection des appli jusqu'à maintenant est vide, pas la peine de continuer
				if(listEnvi.isEmpty()){
					break;
				}
			}
			session.getTransaction().commit();
		}
		return listEnvi;
	}

	/**
	 * SELECT UNION de tous les environnements attachées à la liste des applicatifs en paramètre
	 * @param listAppID
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Environnement> getUnionByAppList(List<Integer> listAppID) {
		List<Applicatifs_Liste> apps = ApplicatifDatabaseService.getMultiple(listAppID);
		List<Environnement> listEnvir = new ArrayList<Environnement>();
		if(apps != null && !apps.isEmpty()){
			Session session = PilotageSession.getCurrentSession();
			
			List<Appli_Envir> listAppliEnvir = session.createCriteria(Appli_Envir.class)
														.add(Restrictions.in("idApplication", apps))
														.createAlias("idEnvironnement", "env", Criteria.LEFT_JOIN)
														.addOrder(Order.asc("env.environnement"))
														.list();
			
			for(Appli_Envir ae : listAppliEnvir){
				if(!listEnvir.contains(ae.getIdEnvironnement())){
					listEnvir.add(ae.getIdEnvironnement());
				}
			}
			
			session.getTransaction().commit();
		}

		return listEnvir;
	}

	/**
	 * SELECT d'un environnement
	 * @param filtreEnvironnement
	 * @return
	 */
	public static Environnement get(Integer envID) {
		Session session = PilotageSession.getCurrentSession();
		Environnement env = (Environnement)session.createCriteria(Environnement.class)
										.add(Restrictions.eq("id", envID))
										.setMaxResults(1)
										.uniqueResult();
		session.getTransaction().commit();
		return env;
	}

}
