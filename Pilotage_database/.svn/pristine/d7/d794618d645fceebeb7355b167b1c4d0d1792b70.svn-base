package pilotage.database.applicatif;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import pilotage.metier.Appli_Service;
import pilotage.metier.Applicatifs_Liste;
import pilotage.metier.Services_Liste;
import pilotage.session.PilotageSession;

public class ServiceDatabaseService {

	/**
	 * SELECT de tous les services ordonnés par nom
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Services_Liste> getAll() {
		Session session = PilotageSession.getCurrentSession();
		List<Services_Liste> listSL = session.createCriteria(Services_Liste.class).add(Restrictions.eq("actif", 1))
											.addOrder(Order.asc("nomService"))
											.list();
		session.getTransaction().commit();
		return listSL;
	}

	/**
	 * SELECT UNION de tous les services attachés à la liste des applicatifs en paramètre
	 * @param listAppID
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Services_Liste> getUnionByAppList(List<Integer> listAppID) {
		List<Applicatifs_Liste> apps = ApplicatifDatabaseService.getMultiple(listAppID);
		List<Services_Liste> listService = new ArrayList<Services_Liste>();
		if(apps != null && !apps.isEmpty()){
			Session session = PilotageSession.getCurrentSession();
			
			List<Appli_Service> listApp_Service = session.createCriteria(Appli_Service.class)
														.add(Restrictions.in("idApplication", apps))
														.createAlias("idServices", "service", Criteria.LEFT_JOIN)
														.addOrder(Order.asc("service.nomService"))
														.add(Restrictions.eq("service.actif", 1))
														.list();
			for(Appli_Service as : listApp_Service){
				if(!listService.contains(as.getIdServices())){
					listService.add(as.getIdServices());
				}
			}
			
			session.getTransaction().commit();
		}

		return listService;
	}
	
	/**
	 * SELECT de la liste des service ayant l'id dans le tableau passé en paramètre
	 * @param appIDList
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Services_Liste> getMultiple(List<Integer> serviceIDList) {
		List<Services_Liste> services;
		if(serviceIDList != null && !serviceIDList.isEmpty()){
			Session session = PilotageSession.getCurrentSession();
			services = (List<Services_Liste>)session.createCriteria(Services_Liste.class)
												.add(Restrictions.in("id", serviceIDList))
												.list();
			session.getTransaction().commit();
		}
		else{
			services = new ArrayList<Services_Liste>();
		}
		return services;
	}
}
