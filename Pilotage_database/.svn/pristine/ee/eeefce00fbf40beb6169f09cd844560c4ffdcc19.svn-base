package pilotage.database.consigne;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import pilotage.metier.Consignes;
import pilotage.metier.Consignes_Pilotes_Validation;
import pilotage.metier.Consignes_Validation;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;
import pilotage.session.PilotageSession;

public class ConsignePiloteValidationDatabaseService {
	
	/**
	 * COUNT test si la ligne pour le pilote et la consigne existe
	 * @param consigne
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static boolean validationLineAlreadyExists(Users user, Consignes consigne){
		
		Session session = PilotageSession.getCurrentSession();
		
		List<Long> results = null;
		try{
			results = (List<Long>)session.createCriteria(Consignes_Pilotes_Validation.class)
							.add(Restrictions.eq("idConsigne", consigne))
							.add(Restrictions.eq("idPilote", user))
							.setProjection(Projections.rowCount())
							.list();
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			e.printStackTrace();
		}
		
		if (results != null && results.size() > 0 && results.get(0) > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * INSERT d'une nouvelle consigne validation
	 * @param consignes
	 * @param users
	 * @param coValidation
	 * @throws Exception 
	 */
    public static void create(Consignes consignes, Users users) throws Exception{
    	Session session = PilotageSession.getCurrentSession();
    	
    	try{
	    	Consignes_Validation consValid = (Consignes_Validation)session.load(Consignes_Validation.class, PilotageConstants.CONSIGNE_NON_LUE); 
	    	
			Consignes_Pilotes_Validation cpValidation = new Consignes_Pilotes_Validation();
			cpValidation.setIdPilote(users);
			cpValidation.setIdConsigne(consignes);
			cpValidation.setValid(consValid);
			cpValidation.setQuestion("");
			
			session.save(cpValidation);
			session.getTransaction().commit();
    	}
    	catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	/**
	 * SELECT du consigne validation avec son id
	 * @param selectRow
	 * @return
	 */
	public static Consignes_Pilotes_Validation get(Integer selectRow) {
		Session session = PilotageSession.getCurrentSession();
		Consignes_Pilotes_Validation cpValidation = (Consignes_Pilotes_Validation)session.createCriteria(Consignes_Pilotes_Validation.class)
																	.add(Restrictions.eq("id", selectRow))
																	.setMaxResults(1)
																	.uniqueResult();
		session.getTransaction().commit();
		return cpValidation;
	}
	
	/**
	 * SELECT consigne validation avec son id pilote et id consigne
	 * @param user
	 * @param consigne
	 * @return
	 */
	public static Consignes_Pilotes_Validation getValidationConsignePilote(Users user, Consignes consigneCurrent) {
		Session session = PilotageSession.getCurrentSession();
		Consignes_Pilotes_Validation cpLueComprise = (Consignes_Pilotes_Validation)session.load(Consignes_Pilotes_Validation.class, PilotageConstants.CONSIGNE_LUE_COMPRISE);
		Consignes_Pilotes_Validation cpLueNonComprise = (Consignes_Pilotes_Validation)session.load(Consignes_Pilotes_Validation.class, PilotageConstants.CONSIGNE_LUE_COMPRISE);
		Consignes_Pilotes_Validation cpValidation = (Consignes_Pilotes_Validation) session.createCriteria(Consignes_Pilotes_Validation.class)
																	.add(Restrictions.eq("idPilote", user))
																	.add(Restrictions.eq("idConsigne", consigneCurrent))
																	.add(Restrictions.or(Restrictions.eq("valid", cpLueComprise), Restrictions.eq("valid", cpLueNonComprise)))
																	.setMaxResults(1)
																	.uniqueResult();
		session.getTransaction().commit();
		return cpValidation;
	}
	
	/**
	 * UPDATE d'un validation
	 * @param id
	 * @param user
	 * @param consignes
	 * @param cValidation
	 * @param question
	 * @throws Exception 
	 */
	public static void modify(Integer id, Users user, Integer statut, String question) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		try{
			Consignes_Validation validation = (Consignes_Validation)session.load(Consignes_Validation.class, statut);
			Consignes consigne = (Consignes)session.load(Consignes.class, id);
			
			Consignes_Pilotes_Validation cpConsignes = (Consignes_Pilotes_Validation)session.createCriteria(Consignes_Pilotes_Validation.class)
																.add(Restrictions.eq("idConsigne", consigne))
																.add(Restrictions.eq("idPilote", user))
																.setMaxResults(1)
																.uniqueResult();
			if(cpConsignes == null){
				cpConsignes = new Consignes_Pilotes_Validation();
				cpConsignes.setIdConsigne(consigne);
				cpConsignes.setIdPilote(user);
				cpConsignes.setValid(validation);
				if(question != null)
					cpConsignes.setQuestion(question);
				else cpConsignes.setQuestion("");
				session.save(cpConsignes);
			}
			else{
				cpConsignes.setValid(validation);
				if(question != null)
					cpConsignes.setQuestion(question);
		
				session.update(cpConsignes);
			}
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}

	/**
	 * SELECT la liste des consignes_pilotes_validation associées à la consigne, Ordre de tri : nom prénom du pilote
	 * @param consigne
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Consignes_Pilotes_Validation> getByConsigne(Consignes consigne) {
		Session session = PilotageSession.getCurrentSession();
		List<Consignes_Pilotes_Validation> listCPV = session.createCriteria(Consignes_Pilotes_Validation.class)
														.add(Restrictions.eq("idConsigne", consigne))
														.createAlias("idPilote", "pilote", Criteria.LEFT_JOIN)
														.addOrder(Order.asc("pilote.nom"))
														.addOrder(Order.asc("pilote.prenom"))
														.list();
		session.getTransaction().commit();
		return listCPV;
	}

	/**
	 * SELECT la liste des consignes validées
	 * @param user
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Integer> getValidatedConsignesID(Users user) {
		Session session = PilotageSession.getCurrentSession();
		
		Consignes_Pilotes_Validation cpConsignes = (Consignes_Pilotes_Validation)session.load(Consignes_Pilotes_Validation.class, PilotageConstants.CONSIGNE_LUE_COMPRISE);
		List<Consignes_Pilotes_Validation> validatedConsignesList = session.createCriteria(Consignes_Pilotes_Validation.class)
									.add(Restrictions.eq("idPilote", user))
									.add(Restrictions.eq("valid", cpConsignes))
									.list();
		session.getTransaction().commit();
		List<Integer> result = new ArrayList<Integer>();
		for(Consignes_Pilotes_Validation conPilVal : validatedConsignesList){
			result.add(conPilVal.getIdConsigne().getId());
		}
		return result;
	}
	
}
