package pilotage.database.consigne;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import pilotage.metier.Consignes_Fichier;
import pilotage.metier.Consignes_Fichier_Mot;
import pilotage.metier.Consignes_Mots;
import pilotage.session.PilotageSession;
import pilotage.utils.Pagination;

public class ConsigneFichierDatabaseService {
	
	/**
	 * SELECT des fichiers
	 * @param pagination
	 * @param sort
	 * @param sens
	 * @param filtreNom
	 * @param listConsigneMot
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Consignes_Fichier> getAllConsigneFichier(Pagination<Consignes_Fichier> pagination, String sort, String sens, String filtreNom, List<Consignes_Mots> listConsigneMot){
        Session session = PilotageSession.getCurrentSession();
        //on récupère les id des fichiers correspondants à au moins un des mots

        Integer nombreMots = listConsigneMot.size();
        
        List<Consignes_Fichier_Mot> listFichierMot = session.createCriteria(Consignes_Fichier_Mot.class)
        											.add(Restrictions.in("idMot", listConsigneMot))
         											.list();

        List<Integer> listFichierID = new ArrayList<Integer>();
        
        Map<Integer, Integer> fichierID = new HashMap<Integer, Integer>();
        for (Consignes_Fichier_Mot consignefichiermot : listFichierMot ){
        	if (!fichierID.containsKey(consignefichiermot.getIdFichier().getId())) {
        		fichierID.put(consignefichiermot.getIdFichier().getId(),1);
        	} else {
	        	Integer nombreFichier = fichierID.get(consignefichiermot.getIdFichier().getId());
	        	fichierID.put(consignefichiermot.getIdFichier().getId(),nombreFichier + 1);
        	}
        }
        
        for (Entry<Integer, Integer> entry : fichierID.entrySet()) {
       	
        	if (entry.getValue() >= nombreMots) {
        		listFichierID.add(entry.getKey());
        	}
        }
        
        if(listFichierID.size() > 0){
	        //on récupère les fichiers avec le filtre et le tri
	        Criteria criteria = session.createCriteria(Consignes_Fichier.class)
	        				.add(Restrictions.in("id", listFichierID));
	        if(filtreNom != null && !"".equals(filtreNom)){
				criteria.add(Restrictions.like("nomFichier", "%" + filtreNom + "%"));
			}
	        criteria.addOrder("desc".equalsIgnoreCase(sens) ? Order.desc(sort) : Order.asc(sort));
	        
	        List<Consignes_Fichier> listCF = pagination.setCriteriaPagination(criteria);
	        session.getTransaction().commit();
			return listCF;
        }
        else{
        	return new ArrayList<Consignes_Fichier>();
        }
	}
	
	/**
	 * SELECT des Consignes_Mots associés à la liste des mots en paramètre
	 * @param listMots
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Consignes_Mots> getKeyWordsList(List<String> listMots) {
		if (listMots.isEmpty()) return new ArrayList<Consignes_Mots>();
		Session session = PilotageSession.getCurrentSession();
		
		List<Consignes_Mots> listConsigneMots = session.createCriteria(Consignes_Mots.class)
														.add(Restrictions.in("mot", listMots))
														.list();
		session.getTransaction().commit();
		return listConsigneMots;
	}

	/**
	 * SELECT des Consignes_Mots associés au debut du mot en paramêtre
	 * @param likeMots
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Consignes_Mots> getKeyWordsList(String likeMots) {
		Session session = PilotageSession.getCurrentSession();
		List<Consignes_Mots> listConsigneMots = session.createCriteria(Consignes_Mots.class)
														.add(Restrictions.like("mot", likeMots+"%"))
														.list();
		session.getTransaction().commit();
		return listConsigneMots;
	}

	/**
	 * UPDATE le nombre de recherche du mot clé
	 * @param motCle
	 * @throws Exception 
	 */
	public static void updateSearchNumber(String motCle) throws Exception {
		Session session = PilotageSession.getCurrentSession();
		
		try{
			Consignes_Mots mot = (Consignes_Mots) session.createCriteria(Consignes_Mots.class)
									.add(Restrictions.eq("mot", motCle))
									.setMaxResults(1)
									.uniqueResult();
			if(mot != null){
				mot.setRecherche(mot.getRecherche() + 1);
				session.update(mot);
			}
			session.getTransaction().commit();
		}
		catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}
	
	/**
	 * SELECT d'un fichier
	 * @param selectRow
	 * @return
	 */
	public static Consignes_Fichier get(Integer selectRow) {
		Session session = PilotageSession.getCurrentSession();
		Consignes_Fichier consignesFichier = (Consignes_Fichier)session.createCriteria(Consignes_Fichier.class)
								.add(Restrictions.eq("id", selectRow))
								.setMaxResults(1)
								.uniqueResult();
		session.getTransaction().commit();
		return consignesFichier;
	}
	
	@SuppressWarnings("unchecked")
	public static List<Consignes_Fichier> getAll(){
		Session session = PilotageSession.getCurrentSession();
		List<Consignes_Fichier> listFichier = session.createCriteria(Consignes_Fichier.class)
												.addOrder(Order.asc("localisation"))
												.list();
		session.getTransaction().commit();
		return listFichier;
	}
    
	/**
	 * ID d'un fichier
	 * @param localisation
	 * @retun id : identification du fichier
	 */
	public static Integer getId(String localisation) {
		
		Session session = PilotageSession.getCurrentSession();
		Consignes_Fichier consignesFichier = (Consignes_Fichier) session.createCriteria(Consignes_Fichier.class)
								.add(Restrictions.eq("localisation", localisation))
								.setMaxResults(1)
								.uniqueResult();
		if(consignesFichier == null){
			session.getTransaction().commit();
			return -1;
		}
		session.getTransaction().commit();
		return consignesFichier.getId();
		
	}
}
