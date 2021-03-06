package pilotage.database.planning;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.joda.time.DateTime;

import pilotage.metier.Planning_Cycle;
import pilotage.metier.Planning_Semaine;
import pilotage.metier.Planning_Vacation;
import pilotage.service.date.DateService;
import pilotage.session.PilotageSession;

public class PlanningCyclesDatabaseService {

	/**
	 * INSERT d'un nouveau cycle
	 * @param libelle
	 * @param listSemaine
	 * @return
	 * @throws Exception
	 */
	public static Integer create(String libelle, List<Map<String, String>> listSemaine) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			Planning_Cycle mt = new Planning_Cycle();
			mt.setNomCycle(libelle);
			
			List<Planning_Semaine> listPlanningSemaine = new ArrayList<Planning_Semaine>();
			for(Map<String, String> ligneSemaine : listSemaine){
				Planning_Vacation ligneLundi = null;
				Planning_Vacation ligneMardi = null;
				Planning_Vacation ligneMercredi = null;
				Planning_Vacation ligneJeudi = null;
				Planning_Vacation ligneVendredi = null;
				Planning_Vacation ligneSamedi = null;
				Planning_Vacation ligneDimanche = null;
				if (ligneSemaine.get("lundi") != null){
					ligneLundi = (Planning_Vacation) session.load(Planning_Vacation.class, Integer.parseInt(ligneSemaine.get("lundi")));
				} 
				if (ligneSemaine.get("mardi") != null){
					ligneMardi = (Planning_Vacation) session.load(Planning_Vacation.class, Integer.parseInt(ligneSemaine.get("mardi")));
				} 
				if (ligneSemaine.get("mercredi") != null){
					ligneMercredi = (Planning_Vacation) session.load(Planning_Vacation.class, Integer.parseInt(ligneSemaine.get("mercredi")));
				} 
				if (ligneSemaine.get("jeudi") != null){
					ligneJeudi = (Planning_Vacation) session.load(Planning_Vacation.class, Integer.parseInt(ligneSemaine.get("jeudi")));
				} 
				if (ligneSemaine.get("vendredi") != null){
					ligneVendredi = (Planning_Vacation) session.load(Planning_Vacation.class, Integer.parseInt(ligneSemaine.get("vendredi")));
				} 
				if (ligneSemaine.get("samedi") != null){
					ligneSamedi = (Planning_Vacation) session.load(Planning_Vacation.class, Integer.parseInt(ligneSemaine.get("samedi")));
				} 
				if (ligneSemaine.get("dimanche") != null){
					ligneDimanche = (Planning_Vacation) session.load(Planning_Vacation.class, Integer.parseInt(ligneSemaine.get("dimanche")));
				} 
				Planning_Semaine ps = new Planning_Semaine();
				ps.setIdNomCycle(mt);
				ps.setNomSemaine("Semaine "+ligneSemaine.get("numSemaine"));
				ps.setNumeroSemaine(Integer.parseInt(ligneSemaine.get("numSemaine")));
				ps.setLundi(ligneLundi);
				ps.setMardi(ligneMardi);
				ps.setMercredi(ligneMercredi);
				ps.setJeudi(ligneJeudi);
				ps.setVendredi(ligneVendredi);
				ps.setSamedi(ligneSamedi);
				ps.setDimanche(ligneDimanche);
				DateTime dateDeb = DateService.strToJodaDateTime("2013-01-01 00:00:00");
				ps.setDateDeb(dateDeb);
				
				listPlanningSemaine.add(ps);
			}
			session.save(mt);
			
			for(Planning_Semaine planningSemaine : listPlanningSemaine){
				session.save(planningSemaine);
			}
			session.getTransaction().commit();
			
			return mt.getId();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	/**
	 * SELECT de tous les cycles par ordre alphabetique
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Planning_Cycle> getAll(){
		Session session = PilotageSession.getCurrentSession();
		List<Planning_Cycle> cycleList = session.createCriteria(Planning_Cycle.class).addOrder(Order.asc("nomCycle")).list();
		session.getTransaction().commit();
		return cycleList;
	}

	/**
	 * DELETE d'un cycle
	 * @param selectRow
	 * @throws Exception
	 */
	public static void delete(Integer selectRow) throws Exception {
		List<Planning_Semaine> listPlanningSemaine = PlanningSemaineDatabaseService.getSemaineByCycle(selectRow);

		Session session = PilotageSession.getCurrentSession();
		try{
			Planning_Cycle pc = (Planning_Cycle)session.load(Planning_Cycle.class, selectRow);

			for(Planning_Semaine ps : listPlanningSemaine){
				session.delete(ps);
			}
			session.delete(pc);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}	
	}

	/**
	 * COUNT teste si le libell� existe deja
	 * @param id - id � ne pas tester
	 * @param libelle
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static boolean exists(Integer id, String libelle){
		Session session = PilotageSession.getCurrentSession();
		
		Criteria criteria = session.createCriteria(Planning_Cycle.class);
		criteria.add(Restrictions.eq("nomCycle", libelle));
		if(id != null)
			criteria.add(Restrictions.ne("id", id));
		criteria.setProjection(Projections.rowCount());

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
	 * SELECT d'un cycle identifi� par selectRow
	 * @param selectRow
	 * @return
	 */
	public static Planning_Cycle get(Integer selectRow) {
		Session session = PilotageSession.getCurrentSession();
		Planning_Cycle cycle = (Planning_Cycle)session.createCriteria(Planning_Cycle.class)
							.add(Restrictions.eq("id", selectRow))
							.setMaxResults(1)
							.uniqueResult();
		session.getTransaction().commit();
		return cycle;
	}

	/**
	 * UPDATE d'un cycle
	 * @param selectRow
	 * @param libelle
	 * @param listSemaineToAdd
	 * @param listSemaineToUpdate
	 * @param listSemaineToDelete
	 * @throws Exception
	 */
	public static void modify(Integer selectRow, String libelle, List<Map<String, String>> listSemaineToAdd, List<Integer> listSemaineToDelete) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			Planning_Cycle mt = (Planning_Cycle)session.load(Planning_Cycle.class, selectRow);
			mt.setNomCycle(libelle);
			session.update(mt);
			
			
			List<Planning_Semaine> semaineToAdd = new ArrayList<Planning_Semaine>();
			List<Planning_Semaine> semaineToUpdate = new ArrayList<Planning_Semaine>();
			List<Planning_Semaine> semaineToDelete = new ArrayList<Planning_Semaine>();
			
			for(Map<String, String> semaineMap : listSemaineToAdd){
				Planning_Vacation ligneLundi = null;
				Planning_Vacation ligneMardi = null;
				Planning_Vacation ligneMercredi = null;
				Planning_Vacation ligneJeudi = null; 
				Planning_Vacation ligneVendredi = null;
				Planning_Vacation ligneSamedi = null;
				Planning_Vacation ligneDimanche = null;
				if (semaineMap.get("lundi") != null){
					ligneLundi = (Planning_Vacation) session.load(Planning_Vacation.class, Integer.parseInt(semaineMap.get("lundi")));
				} 
				if (semaineMap.get("mardi") != null){
					ligneMardi = (Planning_Vacation) session.load(Planning_Vacation.class, Integer.parseInt(semaineMap.get("mardi")));
				} 
				if (semaineMap.get("mercredi") != null){
					ligneMercredi = (Planning_Vacation) session.load(Planning_Vacation.class, Integer.parseInt(semaineMap.get("mercredi")));
				} 
				if (semaineMap.get("jeudi") != null){
					ligneJeudi = (Planning_Vacation) session.load(Planning_Vacation.class, Integer.parseInt(semaineMap.get("jeudi")));
				} 
				if (semaineMap.get("vendredi") != null){
					ligneVendredi = (Planning_Vacation) session.load(Planning_Vacation.class, Integer.parseInt(semaineMap.get("vendredi")));
				} 
				if (semaineMap.get("samedi") != null){
					ligneSamedi = (Planning_Vacation) session.load(Planning_Vacation.class, Integer.parseInt(semaineMap.get("samedi")));
				} 
				if (semaineMap.get("dimanche") != null){
					ligneDimanche = (Planning_Vacation) session.load(Planning_Vacation.class, Integer.parseInt(semaineMap.get("dimanche")));
				}  
				Calendar c = Calendar.getInstance();
				c.set(Calendar.HOUR_OF_DAY, 0);
				c.set(Calendar.MINUTE, 0);
				c.set(Calendar.SECOND, 0);
				DateTime d = new DateTime(c.getTime());
				
				Planning_Semaine ps = new Planning_Semaine();
				ps.setIdNomCycle(mt);
				ps.setNomSemaine("Semaine "+semaineMap.get("numSemaine"));
				ps.setNumeroSemaine(Integer.parseInt(semaineMap.get("numSemaine")));
				ps.setLundi(ligneLundi);
				ps.setMardi(ligneMardi);
				ps.setMercredi(ligneMercredi);
				ps.setJeudi(ligneJeudi);
				ps.setVendredi(ligneVendredi);
				ps.setSamedi(ligneSamedi);
				ps.setDimanche(ligneDimanche);
				ps.setDateDeb(d);
				ps.setDateFin(null);

				semaineToAdd.add(ps);
				List<Planning_Semaine> lps = PlanningSemaineDatabaseService.getBySemAndCycle(mt.getId(), ps.getNumeroSemaine());
				if(lps.size() > 0){
					Integer select = lps.get(lps.size()-1).getId();
					Planning_Semaine psOld = (Planning_Semaine)session.load(Planning_Semaine.class, select);
					psOld.setDateFin(d);
					semaineToUpdate.add(psOld);
				}
			}
			/*for(Map<String, String> semaineMap : listSemaineToUpdate){
				Planning_Vacation ligneLundi = null;
				Planning_Vacation ligneMardi = null;
				Planning_Vacation ligneMercredi = null;
				Planning_Vacation ligneJeudi = null;
				Planning_Vacation ligneVendredi = null;
				Planning_Vacation ligneSamedi = null;
				Planning_Vacation ligneDimanche = null;
				if (semaineMap.get("lundi") != null){
					ligneLundi = (Planning_Vacation) session.load(Planning_Vacation.class, Integer.parseInt(semaineMap.get("lundi")));
				} 
				if (semaineMap.get("mardi") != null){
					ligneMardi = (Planning_Vacation) session.load(Planning_Vacation.class, Integer.parseInt(semaineMap.get("mardi")));
				} 
				if (semaineMap.get("mercredi") != null){
					ligneMercredi = (Planning_Vacation) session.load(Planning_Vacation.class, Integer.parseInt(semaineMap.get("mercredi")));
				} 
				if (semaineMap.get("jeudi") != null){
					ligneJeudi = (Planning_Vacation) session.load(Planning_Vacation.class, Integer.parseInt(semaineMap.get("jeudi")));
				} 
				if (semaineMap.get("vendredi") != null){
					ligneVendredi = (Planning_Vacation) session.load(Planning_Vacation.class, Integer.parseInt(semaineMap.get("vendredi")));
				} 
				if (semaineMap.get("samedi") != null){
					ligneSamedi = (Planning_Vacation) session.load(Planning_Vacation.class, Integer.parseInt(semaineMap.get("samedi")));
				} 
				if (semaineMap.get("dimanche") != null){
					ligneDimanche = (Planning_Vacation) session.load(Planning_Vacation.class, Integer.parseInt(semaineMap.get("dimanche")));
				}  
				Planning_Semaine ps = (Planning_Semaine)session.load(Planning_Semaine.class, Integer.parseInt(semaineMap.get("id")));
				ps.setIdNomCycle(mt);
				ps.setNomSemaine("Semaine "+semaineMap.get("numSemaine"));
				ps.setNumeroSemaine(Integer.parseInt(semaineMap.get("numSemaine")));
				ps.setLundi(ligneLundi);
				ps.setMardi(ligneMardi);
				ps.setMercredi(ligneMercredi);
				ps.setJeudi(ligneJeudi);
				ps.setVendredi(ligneVendredi);
				ps.setSamedi(ligneSamedi);
				ps.setDimanche(ligneDimanche);
				
				semaineToUpdate.add(ps);
			}*/
			for(Integer semaineID : listSemaineToDelete){
				Planning_Semaine ps = (Planning_Semaine)session.load(Planning_Semaine.class, semaineID);
				
				semaineToDelete.add(ps);
			}
			
			for(Planning_Semaine ps : semaineToAdd) {
				session.save(ps);
			}
			for(Planning_Semaine ps : semaineToUpdate) {
				session.update(ps);
			}
			for(Planning_Semaine ps : semaineToDelete) {
				session.delete(ps);
			}
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
}