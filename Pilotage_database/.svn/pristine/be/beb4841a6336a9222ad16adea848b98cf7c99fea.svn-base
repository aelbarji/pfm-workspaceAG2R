package pilotage.database.planning;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import pilotage.metier.Planning_Modif_Ponctuelle;
import pilotage.metier.Planning_Nom_Equipe;
import pilotage.metier.Planning_Vacation;
import pilotage.metier.Users;
import pilotage.service.date.DateService;
import pilotage.session.PilotageSession;

public class PlanningModifPonctuelleDatabaseService {
	@SuppressWarnings("unchecked")
	public static boolean hasModifPonctuelleWithVacation(int id){
		Session session = PilotageSession.getCurrentSession();
		
		Planning_Vacation pv = (Planning_Vacation)session.load(Planning_Vacation.class, id);
		List<Long> results= (List<Long>)session.createCriteria(Planning_Modif_Ponctuelle.class)
									.add(Restrictions.eq("idVacation", pv))
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
	 * INSERT d'une nouvelle modification ponctuelle d'un pilote ou d'une equipe
	 * @param pilote
	 * @param equipe
	 * @param dateDebut
	 * @param dateFin
	 * @param vacation
	 * @param user
	 * @param textCell 
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static void create(Users pilote, Planning_Nom_Equipe equipe, Date dateDebut, Date dateFin, String vacation, Users user, String textCell) throws Exception {
		Planning_Vacation pv = PlanningVacationsDatabaseService.getByLibelle(vacation);
		if (pv == null) {
			PlanningVacationsDatabaseService.create("FFFFFF", DateService.getTimeFromString("00:01"), DateService.getTimeFromString("23:59"), vacation, 5);
			pv = PlanningVacationsDatabaseService.getByLibelle(vacation);
		}
		Session session = PilotageSession.getCurrentSession();
		try{
			//entrées en base englobant la nouvelle modif --> decoupage résultant 2 créneaux
			Criteria critpmtEnglobant = session.createCriteria(Planning_Modif_Ponctuelle.class)
																	.add(Restrictions.lt("dateDebut", dateDebut))
																	.add(Restrictions.gt("dateFin", dateFin));
			//entrées en base contenues dans la nouvelle modif --> suppr car les dates sont écrasées par la nouvelle modif
			Criteria critpmtContenu = session.createCriteria(Planning_Modif_Ponctuelle.class)
																	.add(Restrictions.ge("dateDebut", dateDebut))
																	.add(Restrictions.le("dateFin", dateFin));
			//entrées en base chevauchant au debut de la nouvelle modif --> troncature
			Criteria critpmtChevalAvant = session.createCriteria(Planning_Modif_Ponctuelle.class)
																	.add(Restrictions.lt("dateDebut", dateDebut))
																	.add(Restrictions.lt("dateFin", dateFin))
																	.add(Restrictions.ge("dateFin", dateDebut));
			//entrées en base chevauchant a la fin de la nouvelle modif --> troncature
			Criteria critpmtChevalApres = session.createCriteria(Planning_Modif_Ponctuelle.class)
																	.add(Restrictions.gt("dateDebut", dateDebut))
																	.add(Restrictions.le("dateDebut", dateFin))
																	.add(Restrictions.gt("dateFin", dateFin));
			//entrées en base contenant au debut la nouvelle modif --> troncature
			Criteria critpmtContenantDebut = session.createCriteria(Planning_Modif_Ponctuelle.class)
																	.add(Restrictions.eq("dateDebut", dateDebut))
																	.add(Restrictions.gt("dateFin", dateFin));
			//entrées en base contenant a la fin la nouvelle modif --> troncature
			Criteria critpmtContenantFin = session.createCriteria(Planning_Modif_Ponctuelle.class)
																	.add(Restrictions.lt("dateDebut", dateDebut))
																	.add(Restrictions.eq("dateFin", dateFin));
			
			// si la modification concerne un pilote
			if (pilote != null){
				critpmtEnglobant.add(Restrictions.eq("idUser", pilote));
				critpmtContenu.add(Restrictions.eq("idUser", pilote));
				critpmtChevalAvant.add(Restrictions.eq("idUser", pilote));
				critpmtChevalApres.add(Restrictions.eq("idUser", pilote));
				critpmtContenantDebut.add(Restrictions.eq("idUser", pilote));
				critpmtContenantFin.add(Restrictions.eq("idUser", pilote));
				
			} else if (equipe != null){    // si la modification concerne une équipe
				critpmtEnglobant.add(Restrictions.eq("idEquipe", equipe));
				critpmtContenu.add(Restrictions.eq("idEquipe", equipe));
				critpmtChevalAvant.add(Restrictions.eq("idEquipe", equipe));
				critpmtChevalApres.add(Restrictions.eq("idEquipe", equipe));
				critpmtContenantDebut.add(Restrictions.eq("idEquipe", equipe));
				critpmtContenantFin.add(Restrictions.eq("idEquipe", equipe));
			}
			
			List<Planning_Modif_Ponctuelle> pmtEnglobant = critpmtEnglobant.list();
			List<Planning_Modif_Ponctuelle> pmtContenu = critpmtContenu.list();
			List<Planning_Modif_Ponctuelle> pmtChevalAvant = critpmtChevalAvant.list();
			List<Planning_Modif_Ponctuelle> pmtChevalApres = critpmtChevalApres.list();
			List<Planning_Modif_Ponctuelle> pmtContenantDebut = critpmtContenantDebut.list();
			List<Planning_Modif_Ponctuelle> pmtContenantFin = critpmtContenantFin.list();
			
			// cas particulier des congés, ne pas compter les WE
			if (pilote != null && pv.getLibelle().equals("Vacances") && dateDebut.compareTo(dateFin) != 0 ){
				List<Date[]> listeSem = getSemainesSansWE(dateDebut, dateFin);
				for (Date[] sem : listeSem){
					Planning_Modif_Ponctuelle pmt = new Planning_Modif_Ponctuelle();
					pmt.setIdUser(pilote);
					pmt.setDateDebut(sem[0]);
					pmt.setDateFin(sem[1]);
					pmt.setIdVacation(pv);
					pmt.setDateModif(new Date());
					pmt.setIdUserModif(user);
					pmt.setTextCell(textCell);
					
					session.save(pmt);
				}
			} else {
				Planning_Modif_Ponctuelle pmt = new Planning_Modif_Ponctuelle();
				if (pilote != null){
					pmt.setIdUser(pilote);
				} 
				else if (equipe != null){
					pmt.setIdEquipe(equipe);
				}
				
				pmt.setDateDebut(dateDebut);
				pmt.setDateFin(dateFin);
				pmt.setIdVacation(pv);
				pmt.setDateModif(new Date());
				pmt.setIdUserModif(user);
				pmt.setTextCell(textCell);
				
				session.save(pmt);
			}
			
			if(pmtEnglobant != null && pmtEnglobant.size() > 0){
				Planning_Modif_Ponctuelle pmtEng = pmtEnglobant.get(0);
				
				Planning_Modif_Ponctuelle pmt1 = new Planning_Modif_Ponctuelle();
				if (pilote != null)
					pmt1.setIdUser(pmtEng.getIdUser());
				else if (equipe != null)
					pmt1.setIdEquipe(equipe);
				
				pmt1.setDateDebut(pmtEng.getDateDebut());
				pmt1.setDateFin(DateService.addDays(dateDebut, -1));
				pmt1.setIdVacation(pmtEng.getIdVacation());
				pmt1.setDateModif(pmtEng.getDateModif());
				pmt1.setIdUserModif(pmtEng.getIdUserModif());
				pmt1.setTextCell(textCell);
				
				Planning_Modif_Ponctuelle pmt2 = new Planning_Modif_Ponctuelle();
				if (pilote != null)
					pmt2.setIdUser(pmtEng.getIdUser());
				else if (equipe != null)
					pmt2.setIdEquipe(equipe);
				
				pmt2.setDateDebut(DateService.addDays(dateDebut, 1));
				pmt2.setDateFin(pmtEng.getDateFin());
				pmt2.setIdVacation(pmtEng.getIdVacation());
				pmt2.setDateModif(pmtEng.getDateModif());
				pmt2.setIdUserModif(pmtEng.getIdUserModif());
				pmt2.setTextCell(textCell);
				
				session.delete(pmtEng);
				session.save(pmt1);
				session.save(pmt2);
			}
			
			if(pmtContenu != null && pmtContenu.size() > 0){
				for(Planning_Modif_Ponctuelle pmtTmp : pmtContenu)
					session.delete(pmtTmp);
			}
			
			if(pmtChevalAvant != null && pmtChevalAvant.size() > 0){
				Planning_Modif_Ponctuelle pmtChAv = pmtChevalAvant.get(0);
				pmtChAv.setDateFin(DateService.addDays(dateDebut, -1));
				session.update(pmtChAv);
			}
			
			if(pmtChevalApres != null && pmtChevalApres.size() > 0){
				Planning_Modif_Ponctuelle pmtChAp = pmtChevalApres.get(0);
				pmtChAp.setDateDebut(DateService.addDays(dateFin, 1));
				session.update(pmtChAp);
			}
			
			if(pmtContenantDebut != null && pmtContenantDebut.size() > 0){
				Planning_Modif_Ponctuelle pmtCoDe = pmtContenantDebut.get(0);
				pmtCoDe.setDateDebut(DateService.addDays(dateFin, 1));
				session.update(pmtCoDe);
			}
			
			if(pmtContenantFin != null && pmtContenantFin.size() > 0){
				Planning_Modif_Ponctuelle pmtCoFi = pmtContenantFin.get(0);
				pmtCoFi.setDateFin(DateService.addDays(dateDebut, -1));
				session.update(pmtCoFi);
			}
			
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	@SuppressWarnings("unchecked")
	public static boolean userExists(Users user) {
		Session session = PilotageSession.getCurrentSession();
		
		List<Long> results = null;
		try{
			results= (List<Long>)session.createCriteria(Planning_Modif_Ponctuelle.class)
				.add(Restrictions.eq("idUser", user))
				.setProjection(Projections.rowCount())
				.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			e.printStackTrace();
		}

		if (results != null && results.size() > 0 && results.get(0) > 0)
			return true;
		else
			return false;
	}
	
	@SuppressWarnings("unchecked")
	public static List<Planning_Modif_Ponctuelle> getByUser(Users user) {
		Session session = PilotageSession.getCurrentSession();
		List<Planning_Modif_Ponctuelle> pmpList = session.createCriteria(Planning_Modif_Ponctuelle.class)
													.add(Restrictions.eq("idUser", user))
													.addOrder(Order.desc("dateModif"))
													.list();
		session.getTransaction().commit();
		return pmpList;
	}
	
	public static Planning_Modif_Ponctuelle getByUserAndDate(Users user, Date date ) {
		Session session = PilotageSession.getCurrentSession();
		Planning_Modif_Ponctuelle pmpList = (Planning_Modif_Ponctuelle) session.createCriteria(Planning_Modif_Ponctuelle.class)
													.add(Restrictions.eq("idUser", user))
													.add(Restrictions.le("dateFin", date))
													.add(Restrictions.ge("dateDebut", date))
													.setMaxResults(1)
													.uniqueResult();
		session.getTransaction().commit();
		return pmpList;
	}


	/**
	 * SELECT 
	 * @param u
	 * @param dateDebutSemaine
	 * @param dateFinSemaine
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Planning_Modif_Ponctuelle> getByUserAndDate(Users u, Date dateDebutSemaine, Date dateFinSemaine) {
		Session session = PilotageSession.getCurrentSession();
		List<Planning_Modif_Ponctuelle> pmpList = session.createCriteria(Planning_Modif_Ponctuelle.class)
													.add(Restrictions.eq("idUser", u))
													.add(Restrictions.ge("dateFin", dateDebutSemaine))
													.add(Restrictions.le("dateDebut", dateFinSemaine))
													.addOrder(Order.desc("dateModif"))
													.list();
		session.getTransaction().commit();
		return pmpList;
	}
	
	/**
	 * SELECT 
	 * @param equipe
	 * @param dateDebutSemaine
	 * @param dateFinSemaine
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Planning_Modif_Ponctuelle> getByEquipeAndDate(Planning_Nom_Equipe equipe, Date dateDebutSemaine, Date dateFinSemaine) {
		Session session = PilotageSession.getCurrentSession();
		List<Planning_Modif_Ponctuelle> pmpList = session.createCriteria(Planning_Modif_Ponctuelle.class)
													.add(Restrictions.eq("idEquipe", equipe))
													.add(Restrictions.ge("dateFin", dateDebutSemaine))
													.add(Restrictions.le("dateDebut", dateFinSemaine))
													.addOrder(Order.desc("dateModif"))
													.list();
		session.getTransaction().commit();
		return pmpList;
	}
	
	public static List<Date[]> getSemainesSansWE(Date debutConges, Date finConges){
		List<Date[]> listSem = new ArrayList<Date[]>();
		Date jourCourant = debutConges;
		Date debutSem = debutConges;
		Date finSem = null;
		while(jourCourant.compareTo(finConges) <= 0){ // si le jour en cours est < à la fin des congés
			switch (DateService.getJour(jourCourant)){
			case 5: // vendredi
				finSem = jourCourant;
				Date[] sem = {debutSem, finSem};
				listSem.add(sem);
				jourCourant = DateService.addDays(jourCourant, 3);
				break;
				
			case 1: // lundi
				debutSem = jourCourant;
				jourCourant = DateService.addDays(jourCourant, 1);
				break;
			
			default:
				jourCourant = DateService.addDays(jourCourant, 1);
			}
		}
		if (finSem == null || finSem.compareTo(finConges) != 0){
			finSem = finConges;
			Date[] sem = {debutSem, finSem};
			listSem.add(sem);
		}
	return listSem;
	
	}
}
