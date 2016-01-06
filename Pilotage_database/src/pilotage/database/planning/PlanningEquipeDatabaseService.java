package pilotage.database.planning;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import pilotage.metier.Planning_Cycle;
import pilotage.metier.Planning_Cycle_Equipe;
import pilotage.metier.Planning_Equipe_Pilote;
import pilotage.metier.Planning_Nom_Equipe;
import pilotage.metier.Users;
import pilotage.service.date.DateService;
import pilotage.session.PilotageSession;

public class PlanningEquipeDatabaseService {
	@SuppressWarnings("unchecked")
	public static List<Planning_Nom_Equipe> getAll(){
		Session session = PilotageSession.getCurrentSession();
		List<Planning_Nom_Equipe> equipeList = session.createCriteria(Planning_Nom_Equipe.class).addOrder(Order.asc("nomEquipe")).list();
		session.getTransaction().commit();
		return equipeList;
	}
	
	public static Planning_Nom_Equipe get(Integer selectRow){
		Session session = PilotageSession.getCurrentSession();
		Planning_Nom_Equipe pne = (Planning_Nom_Equipe)session.createCriteria(Planning_Nom_Equipe.class)
		                                .add(Restrictions.eq("id", selectRow))
		                                .setMaxResults(1)
		                                .uniqueResult();
		session.getTransaction().commit();
		return pne;
	}
	
	@SuppressWarnings("unchecked")
	public static boolean exists(Integer id, String libelle){
		Session session = PilotageSession.getCurrentSession();
		
		Criteria criteria = session.createCriteria(Planning_Nom_Equipe.class);
		criteria.add(Restrictions.eq("nomEquipe", libelle));
		if(id != null)
			criteria.add(Restrictions.not(Restrictions.eq("id", id)));
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
	
	public static Integer getId(String libelle){
		Session session = PilotageSession.getCurrentSession();
		Planning_Nom_Equipe pne = (Planning_Nom_Equipe)session.createCriteria(Planning_Nom_Equipe.class)
									.add(Restrictions.eq("nomEquipe", libelle))
									.setMaxResults(1)
									.uniqueResult();
		session.getTransaction().commit();
		return pne.getId();
	}
	
	public static void create(String libelle, List<Map<String, String>> listPilote, List<Map<String, String>> listCycle) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			Planning_Nom_Equipe pne = new Planning_Nom_Equipe();
			pne.setNomEquipe(libelle);

			List<Planning_Equipe_Pilote> listEquipePilote = new ArrayList<Planning_Equipe_Pilote>();
			for(Map<String, String> Pilote : listPilote){
				Users lignePilote = null;
				Date ligneDateDebut = null;
				Date ligneDateFin = null;
				if (Pilote.get("pilote") != null){
					lignePilote = (Users) session.load(Users.class, Integer.parseInt(Pilote.get("pilote")));
				} 
				if (Pilote.get("dateDebut") != null){
					ligneDateDebut = DateService.strToDate(Pilote.get("dateDebut"));
				} 
				if (Pilote.get("dateFin") != null){
					ligneDateFin = DateService.strToDate(Pilote.get("dateFin"));
				} 
				Planning_Equipe_Pilote pep = new Planning_Equipe_Pilote();
				pep.setIdUser(lignePilote);
				pep.setIdNomEquipe(pne);
				pep.setDateDebut(ligneDateDebut);
				pep.setDateFin(ligneDateFin);
				
				listEquipePilote.add(pep);
			}
			
			List<Planning_Cycle_Equipe> listCycleEquipe = new ArrayList<Planning_Cycle_Equipe>();
			for(Map<String, String> Cycle : listCycle){
				Planning_Cycle ligneCycle = null;
				Date ligneDateDebut = null;
				Date ligneDateFin = null;
				if (Cycle.get("cycle") != null){
					ligneCycle = (Planning_Cycle) session.load(Planning_Cycle.class, Integer.parseInt(Cycle.get("cycle")));
				} 
				if (Cycle.get("dateDebut") != null){
					ligneDateDebut = DateService.strToDate(Cycle.get("dateDebut"));
				} 
				if (Cycle.get("dateFin") != null){
					ligneDateFin = DateService.strToDate(Cycle.get("dateFin"));
				} 
				Planning_Cycle_Equipe pce = new Planning_Cycle_Equipe();
				pce.setIdNomCycle(ligneCycle);
				pce.setIdNomEquipe(pne);
				pce.setDateDebut(ligneDateDebut);
				pce.setDateFin(ligneDateFin);
				
				listCycleEquipe.add(pce);
			}
			session.save(pne);
			
			for(Planning_Equipe_Pilote pep : listEquipePilote){
				session.save(pep);
			}
			
			for(Planning_Cycle_Equipe pce : listCycleEquipe){
				session.save(pce);
			}
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	public static void modify(Integer selectRow, String libelle, List<Map<String, String>> listPiloteToAdd, List<Map<String,String>> listPiloteToUpdate, List<Integer> listPiloteToDelete, List<Map<String, String>> listCycleToAdd, List<Map<String,String>> listCycleToUpdate, List<Integer> listCycleToDelete) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			Planning_Nom_Equipe pne = (Planning_Nom_Equipe)session.load(Planning_Nom_Equipe.class, selectRow);
			pne.setNomEquipe(libelle);
			session.update(pne);
			
			List<Planning_Equipe_Pilote> piloteToAdd = new ArrayList<Planning_Equipe_Pilote>();
			List<Planning_Equipe_Pilote> piloteToUpdate = new ArrayList<Planning_Equipe_Pilote>();
			List<Planning_Equipe_Pilote> piloteToDelete = new ArrayList<Planning_Equipe_Pilote>();
			for(Map<String, String> piloteMap : listPiloteToAdd){
				Users pilote = null;
				Date dateDebut = null;
				Date dateFin = null;
				if (piloteMap.get("pilote") != null){
					pilote = (Users) session.load(Users.class, Integer.parseInt(piloteMap.get("pilote")));
				} 
				if (piloteMap.get("dateDebut") != null){
					dateDebut = DateService.strToDate(piloteMap.get("dateDebut"));
				} 
				if (piloteMap.get("dateFin") != null){
					dateFin = DateService.strToDate(piloteMap.get("dateFin"));
				} 
				Planning_Equipe_Pilote pep = new Planning_Equipe_Pilote();
				pep.setIdUser(pilote);
				pep.setIdNomEquipe(pne);
				pep.setDateDebut(dateDebut);
				pep.setDateFin(dateFin);
				piloteToAdd.add(pep);
			}
			for(Map<String, String> piloteMap : listPiloteToUpdate){
				Users pilote = null;
				Date dateDebut = null;
				Date dateFin = null;
				if (piloteMap.get("pilote") != null){
					pilote = (Users) session.load(Users.class, Integer.parseInt(piloteMap.get("pilote")));
				} 
				if (piloteMap.get("dateDebut") != null){
					dateDebut = DateService.strToDate(piloteMap.get("dateDebut"));
				} 
				if (piloteMap.get("dateFin") != null){
					dateFin = DateService.strToDate(piloteMap.get("dateFin"));
				} 
				Planning_Equipe_Pilote pep = (Planning_Equipe_Pilote)session.load(Planning_Equipe_Pilote.class, Integer.parseInt(piloteMap.get("id")));
				pep.setIdUser(pilote);
				pep.setIdNomEquipe(pne);
				pep.setDateDebut(dateDebut);
				pep.setDateFin(dateFin);
				piloteToUpdate.add(pep);
			}
			for(Integer piloteID : listPiloteToDelete){
				Planning_Equipe_Pilote pep = (Planning_Equipe_Pilote)session.load(Planning_Equipe_Pilote.class, piloteID);
				piloteToDelete.add(pep);
			}
			
			for(Planning_Equipe_Pilote pep : piloteToAdd) {
				session.save(pep);
			}
			for(Planning_Equipe_Pilote pep : piloteToUpdate) {
				session.update(pep);
			}
			for(Planning_Equipe_Pilote pep : piloteToDelete) {
				session.delete(pep);
			}
			
			List<Planning_Cycle_Equipe> cycleToAdd = new ArrayList<Planning_Cycle_Equipe>();
			List<Planning_Cycle_Equipe> cycleToUpdate = new ArrayList<Planning_Cycle_Equipe>();
			List<Planning_Cycle_Equipe> cycleToDelete = new ArrayList<Planning_Cycle_Equipe>();
			for(Map<String, String> cycleMap : listCycleToAdd){
				Planning_Cycle cycle = null;
				Date dateDebut = null;
				Date dateFin = null;
				if (cycleMap.get("cycle") != null){
					cycle = (Planning_Cycle) session.load(Planning_Cycle.class, Integer.parseInt(cycleMap.get("cycle")));
				} 
				if (cycleMap.get("dateDebut") != null){
					dateDebut = DateService.strToDate(cycleMap.get("dateDebut"));
				} 
				if (cycleMap.get("dateFin") != null){
					dateFin = DateService.strToDate(cycleMap.get("dateFin"));
				} 
				Planning_Cycle_Equipe pce = new Planning_Cycle_Equipe();
				pce.setIdNomCycle(cycle);
				pce.setIdNomEquipe(pne);
				pce.setDateDebut(dateDebut);
				pce.setDateFin(dateFin);
				cycleToAdd.add(pce);
			}
			for(Map<String, String> cycleMap : listCycleToUpdate){
				Planning_Cycle cycle = null;
				Date dateDebut = null;
				Date dateFin = null;
				if (cycleMap.get("cycle") != null){
					cycle = (Planning_Cycle) session.load(Planning_Cycle.class, Integer.parseInt(cycleMap.get("cycle")));
				} 
				if (cycleMap.get("dateDebut") != null){
					dateDebut = DateService.strToDate(cycleMap.get("dateDebut"));
				} 
				if (cycleMap.get("dateFin") != null){
					dateFin = DateService.strToDate(cycleMap.get("dateFin"));
				} 
				Planning_Cycle_Equipe pce = (Planning_Cycle_Equipe)session.load(Planning_Cycle_Equipe.class, Integer.parseInt(cycleMap.get("id")));
				pce.setIdNomCycle(cycle);
				pce.setIdNomEquipe(pne);
				pce.setDateDebut(dateDebut);
				pce.setDateFin(dateFin);
				cycleToUpdate.add(pce);
			}
			for(Integer cycleID : listCycleToDelete){
				Planning_Cycle_Equipe pce = (Planning_Cycle_Equipe)session.load(Planning_Cycle_Equipe.class, cycleID);
				cycleToDelete.add(pce);
			}
			
			for(Planning_Cycle_Equipe pce : cycleToAdd) {
				session.save(pce);
			}
			for(Planning_Cycle_Equipe pce : cycleToUpdate) {
				session.update(pce);
			}
			for(Planning_Cycle_Equipe pce : cycleToDelete) {
				session.delete(pce);
			}
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	public static void delete(Integer selectRow) throws Exception {
		List<Planning_Equipe_Pilote> listPilote = PlanningEquipePiloteDatabaseService.getPilotesByEquipe(selectRow);
		List<Planning_Cycle_Equipe> listCycle = PlanningCycleEquipeDatabaseService.getCycleByEquipe(selectRow);

		Session session = PilotageSession.getCurrentSession();
		try{
			Planning_Nom_Equipe pne = (Planning_Nom_Equipe)session.load(Planning_Nom_Equipe.class, selectRow);

			for(Planning_Equipe_Pilote pep : listPilote){
				session.delete(pep);
			}
			for(Planning_Cycle_Equipe pce : listCycle){
				session.delete(pce);
			}
			session.delete(pne);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}	
	}
}
