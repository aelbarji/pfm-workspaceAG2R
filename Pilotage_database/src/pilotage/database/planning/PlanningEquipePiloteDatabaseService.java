package pilotage.database.planning;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import pilotage.admin.metier.Profil;
import pilotage.database.admin.ProfilDatabaseService;
import pilotage.metier.Planning_Equipe_Pilote;
import pilotage.metier.Planning_Nom_Equipe;
import pilotage.metier.Users;
import pilotage.service.date.DateService;
import pilotage.session.PilotageSession;

public class PlanningEquipePiloteDatabaseService {
	@SuppressWarnings("unchecked")
	public static List<Planning_Equipe_Pilote> getPilotesByEquipe(int idEquipe){
		Session session = PilotageSession.getCurrentSession();
		Planning_Nom_Equipe pne = (Planning_Nom_Equipe)session.load(Planning_Nom_Equipe.class, idEquipe);
		List<Planning_Equipe_Pilote> listPilote = (List<Planning_Equipe_Pilote>)session.createCriteria(Planning_Equipe_Pilote.class)
													.add(Restrictions.eq("idNomEquipe", pne))
													.createAlias("idUser", "user", Criteria.LEFT_JOIN)
													.add(Restrictions.eq("user.actif", Boolean.TRUE))
													.addOrder(Order.asc("idUser"))
													.list();
		session.getTransaction().commit();

		return listPilote;
	}
	
	@SuppressWarnings("unchecked")
	public static List<Planning_Equipe_Pilote> getPilotesByEquipeNomAsc (int idEquipe){
		
		List<Profil> piloteProfil = ProfilDatabaseService.getPilotes();
		Session session = PilotageSession.getCurrentSession();
		Planning_Nom_Equipe pne = (Planning_Nom_Equipe)session.load(Planning_Nom_Equipe.class, idEquipe);
		Integer[] piloteID = new Integer[piloteProfil.size()];
		int i = 0;
		for(Profil p : piloteProfil){
			piloteID[i] = p.getId();
			i++;
		}
		
		List<Planning_Equipe_Pilote> listPilote = (List<Planning_Equipe_Pilote>)session.createCriteria(Planning_Equipe_Pilote.class)
									.add(Restrictions.eq("idNomEquipe", pne))
									.createAlias("idUser", "user", Criteria.LEFT_JOIN)
									.add(Restrictions.eq("user.actif", Boolean.TRUE))
									.add(Restrictions.in("user.statut", piloteID))
									.addOrder(Order.asc("user.nom"))
									.addOrder(Order.asc("user.prenom"))
									.list();
		return listPilote;
	}
	
	public static Planning_Equipe_Pilote getEquipeByPilote(Users pilote){
		Session session = PilotageSession.getCurrentSession();
		Planning_Equipe_Pilote pep = (Planning_Equipe_Pilote)session.createCriteria(Planning_Equipe_Pilote.class)
										.add(Restrictions.eq("idUser", pilote))
										.setMaxResults(1)
										.uniqueResult();
		session.getTransaction().commit();
		
		return pep;
	}
	
	/**
	 * SELECT de toutes les équipes du pilote
	 * @param pilote
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Planning_Equipe_Pilote> getListEquipeByPilote(Users pilote){
		Session session = PilotageSession.getCurrentSession();
		List<Planning_Equipe_Pilote> pep = (List<Planning_Equipe_Pilote>)session.createCriteria(Planning_Equipe_Pilote.class)
										.add(Restrictions.eq("idUser", pilote))
										.addOrder(Order.asc("dateDebut"))
										.list();
		session.getTransaction().commit();
		
		return pep;
	}
	
	
	/**
	 * SELECT de toutes les équipes du pilote
	 * @param pilote
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Planning_Equipe_Pilote> getListEquipeByPiloteWithDate(Users pilote, Date dateDeb, Date dateFin){
		Session session = PilotageSession.getCurrentSession();
		List<Planning_Equipe_Pilote> pep = (List<Planning_Equipe_Pilote>)session.createCriteria(Planning_Equipe_Pilote.class)
										.add(Restrictions.eq("idUser", pilote))
										.add(Restrictions.or(
												Restrictions.isNull("dateFin"),
												Restrictions.ge("dateFin", dateDeb)))
										.add(Restrictions.le("dateDebut", dateFin))
										.addOrder(Order.asc("dateDebut"))
										.list();
		session.getTransaction().commit();
		
		return pep;
	}
	
	public static Integer getId(Integer idEquipe, Integer idPilote, String dateDebut){
		Session session = PilotageSession.getCurrentSession();
		Users user = (Users)session.load(Users.class, idPilote);
		Planning_Nom_Equipe pne = (Planning_Nom_Equipe)session.load(Planning_Nom_Equipe.class, idEquipe);
		Planning_Equipe_Pilote pep = (Planning_Equipe_Pilote)session.createCriteria(Planning_Equipe_Pilote.class)
									.add(Restrictions.eq("idUser", user))
									.add(Restrictions.eq("idNomEquipe", pne))
									.add(Restrictions.eq("dateDebut", DateService.strToDate(dateDebut)))
									.setMaxResults(1)
									.uniqueResult();
		session.getTransaction().commit();
		return pep.getId();
	}
	
	@SuppressWarnings("unchecked")
	public static List<Planning_Equipe_Pilote> getAll() {
		Session session = PilotageSession.getCurrentSession();
		List<Planning_Equipe_Pilote> listPilote = (List<Planning_Equipe_Pilote>)session.createCriteria(Planning_Equipe_Pilote.class)
													.add(Restrictions.isNull("dateFin"))
													.list();
		session.getTransaction().commit();
		return listPilote;
	}
	
	@SuppressWarnings("unchecked")
	public static List<Planning_Equipe_Pilote> getAllCRA() {
		Session session = PilotageSession.getCurrentSession();
		List<Planning_Equipe_Pilote> listPilote = (List<Planning_Equipe_Pilote>)session.createCriteria(Planning_Equipe_Pilote.class)
													.list();
		session.getTransaction().commit();
		return listPilote;
	}
	
	@SuppressWarnings("unchecked")
	public static List<Planning_Equipe_Pilote> getAllWithoutEquipePiloteSelect(Integer selectRow) {
		Session session = PilotageSession.getCurrentSession();
		
		Planning_Nom_Equipe pne = (Planning_Nom_Equipe)session.load(Planning_Nom_Equipe.class, selectRow);
		List<Planning_Equipe_Pilote> listPilote = (List<Planning_Equipe_Pilote>)session.createCriteria(Planning_Equipe_Pilote.class)
													.add(Restrictions.ne("idNomEquipe", pne))
													.add(Restrictions.isNull("dateFin"))
													.list();
		session.getTransaction().commit();
		return listPilote;
	}
}
