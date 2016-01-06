package pilotage.meteo.groupeMeteo;

import java.util.ArrayList;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;

import pilotage.database.incidents.HeuresOceorDatabaseService;
import pilotage.database.meteo.MeteoDatabaseService;
import pilotage.database.meteo.MeteoFormatDatabaseService;
import pilotage.database.users.management.UsersDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Heures_Oceor;
import pilotage.metier.Meteo_Format;
import pilotage.metier.Meteo_Meteo;
import pilotage.metier.Users;

public class RedirectCreateGroupeMeteoAction extends AbstractAction {

	private static final long serialVersionUID = 1687866108288927421L;
	public static final String METEO_ID = "meteoID";
	public static final String METEO_NAME = "meteoName";
	public static final String DEST_ID = "destId";
	public static final String DEST_NOM = "destNom";
	public static final String DEST_PRENOM = "destPrenom";
	public static final String HORAIRE = "horaire";
	private List<Meteo_Meteo> meteoList;
	private String nom_groupeMeteo;
	private Integer meteoToAdd;
	private Integer deleteMeteo;
	private List<Map<String, String>> listMeteos;
	private Integer destinataireToAdd;
	private Integer deleteDestinataire;
	private List<Users> destinataireList;
	private List<Map<String, String>> listDestinataires;
	private String horaireToAdd;
	private String deleteHoraire;
	private List<Date> horaireList;
	private List<Map<String, String>> listHoraires;
	private List<Meteo_Format> listFormats;
	private Integer idFormat;
	private List<Heures_Oceor> listFuseaux;

	public List<Meteo_Meteo> getMeteoList() {
		return meteoList;
	}

	public void setMeteoList(List<Meteo_Meteo> meteoList) {
		this.meteoList = meteoList;
	}

	public String getNom_groupeMeteo() {
		return nom_groupeMeteo;
	}

	public void setNom_groupeMeteo(String nom_groupeMeteo) {
		this.nom_groupeMeteo = nom_groupeMeteo;
	}

	public Integer getMeteoToAdd() {
		return meteoToAdd;
	}

	public void setMeteoToAdd(Integer meteoToAdd) {
		this.meteoToAdd = meteoToAdd;
	}

	public Integer getDeleteMeteo() {
		return deleteMeteo;
	}

	public void setDeleteMeteo(Integer deleteMeteo) {
		this.deleteMeteo = deleteMeteo;
	}

	public List<Map<String, String>> getListMeteos() {
		return listMeteos;
	}

	public void setListMeteos(List<Map<String, String>> listMeteos) {
		this.listMeteos = listMeteos;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getDestinataireToAdd() {
		return destinataireToAdd;
	}

	public void setDestinataireToAdd(Integer destinataireToAdd) {
		this.destinataireToAdd = destinataireToAdd;
	}

	public Integer getDeleteDestinataire() {
		return deleteDestinataire;
	}

	public void setDeleteDestinataire(Integer deleteDestinataire) {
		this.deleteDestinataire = deleteDestinataire;
	}

	public List<Map<String, String>> getListDestinataires() {
		return listDestinataires;
	}

	public void setListDestinataires(List<Map<String, String>> listDestinataires) {
		this.listDestinataires = listDestinataires;
	}

	public String getHoraireToAdd() {
		return horaireToAdd;
	}

	public void setHoraireToAdd(String horaireToAdd) {
		this.horaireToAdd = horaireToAdd;
	}

	public String getDeleteHoraire() {
		return deleteHoraire;
	}

	public void setDeleteHoraire(String deleteHoraire) {
		this.deleteHoraire = deleteHoraire;
	}

	public List<Date> getHoraireList() {
		return horaireList;
	}

	public void setHoraireList(List<Date> horaireList) {
		this.horaireList = horaireList;
	}

	public List<Map<String, String>> getListHoraires() {
		return listHoraires;
	}

	public void setListHoraires(List<Map<String, String>> listHoraires) {
		this.listHoraires = listHoraires;
	}

	public List<Users> getDestinataireList() {
		return destinataireList;
	}

	public void setDestinataireList(List<Users> destinataireList) {
		this.destinataireList = destinataireList;
	}
	
	public List<Meteo_Format> getListFormats() {
		return listFormats;
	}

	public void setListFormats(List<Meteo_Format> listFormats) {
		this.listFormats = listFormats;
	}

	public Integer getIdFormat() {
		return idFormat;
	}

	public void setIdFormat(Integer idFormat) {
		this.idFormat = idFormat;
	}

	public List<Heures_Oceor> getListFuseaux() {
		return listFuseaux;
	}

	public void setListFuseaux(List<Heures_Oceor> listFuseaux) {
		this.listFuseaux = listFuseaux;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		meteoList = MeteoDatabaseService.getAll();
		destinataireList = UsersDatabaseService.getAll();
		listFormats = MeteoFormatDatabaseService.getAll();
		listFuseaux = HeuresOceorDatabaseService.getAll(); 
		
		//recupération des météos à ajouter
		listMeteos= new ArrayList<Map<String,String>>();
		getListMeteosToAdd(request, listMeteos);
		
		//recupération des destinataires à ajouter
		listDestinataires = new ArrayList<Map<String,String>>();
		getListDestinatairesToAdd(request, listDestinataires);
		
		//récupération des horaires à ajouter
		listHoraires = new ArrayList<Map<String,String>>();
		getListHorairesToAdd(request, listHoraires);
		
		//Si on a cliqué sur la suppression d'une météo
		if(deleteMeteo != null && deleteMeteo != -1){
			Map<String, String> mapToRemove = null;
			for(Map<String, String> meteo : listMeteos){
				if(meteo.get(METEO_ID).equals(deleteMeteo.toString())){
					mapToRemove = meteo;
					break;
				}
			}
			if(mapToRemove != null)
				listMeteos.remove(mapToRemove);
		}
		
		//si on a cliqué sur l'ajout d'une météo, on l'ajoute aux météos à ajouter
		else if(meteoToAdd != null && meteoToAdd != -1){
			for(Meteo_Meteo meteos : meteoList){
				if(meteos.getId().equals(meteoToAdd)){
					Map<String, String> meteo = new HashMap<String, String>();
					meteo.put(METEO_ID, meteoToAdd.toString());
					listMeteos.add(meteo);
					break;
				}
			}
		}
		
		//si on a cliqué sur la suppression d'un destinataire
		else if(deleteDestinataire != null && deleteDestinataire != -1){
			Map<String, String> destToRemove = null;
			for(Map<String, String> dest : listDestinataires){
				if(dest.get(DEST_ID).equals(deleteDestinataire.toString())){
					destToRemove = dest;
					break;
				}
			}
			if(destToRemove != null){
				listDestinataires.remove(destToRemove);
			}
		}		
		
		//si on a cliqué sur l'ajout d'un destinataire, on l'ajoute aux destinataires à ajouter
		else if(destinataireToAdd != null && destinataireToAdd != -1){
			for(Users user : destinataireList){
				if(user.getId().equals(destinataireToAdd)){
					Map<String, String> u = new HashMap<String, String>();
					u.put(DEST_ID, destinataireToAdd.toString());
					listDestinataires.add(u);
					break;
				}
			}
		}
			
		
		//si on a cliqué sur l'ajout d'un horaire, on l'ajoute aux horaires à ajouter
		else if(horaireToAdd != null && !horaireToAdd.equals("")){
			int present = 0;
			for(Map<String, String> horaire : listHoraires){
				if(horaire.get(HORAIRE).equals(horaireToAdd.toString())){
					present = 1;
					break;
				}	
			}
			if(present==0){
				Map<String, String> map = new HashMap<String, String>();
				map.put(HORAIRE, horaireToAdd.toString());
				listHoraires.add(map);
			}
		}
		
		//si on a cliqué sur la suppression d'un horaire
		else if(deleteHoraire != null && !deleteHoraire.equals("-1")){
			Map<String, String> horaireToRemove = null;
			for(Map<String, String> horaire : listHoraires){
				if(horaire.get(HORAIRE).equals(deleteHoraire.toString())){
					horaireToRemove = horaire;
					break;
				}
			}
			if(horaireToRemove != null){
				listHoraires.remove(horaireToRemove);
			}
		}
		
		//suppression de la liste des météos déjà affecté au groupe météo
		//remplissage des noms des météos
		removeMeteos(meteoList, listMeteos);
		removeDestinataires(destinataireList, listDestinataires);
		
		return OK;
	}
	
	/**
	 * Suppression des météos de arg1 présents dans arg2
	 * @param meteoList
	 * @param listMeteos
	 */
	public static void removeMeteos(List<Meteo_Meteo> meteoList, List<Map<String, String>> listMeteos) {
		List<Meteo_Meteo> meteoToRemove = new ArrayList<Meteo_Meteo>();
		for(Meteo_Meteo meteo : meteoList){
			for(Map<String, String> meteoToAdd : listMeteos){
				if(meteo.getId().toString().equals(meteoToAdd.get(METEO_ID))){
					meteoToAdd.put(METEO_NAME, meteo.getNom_meteo());
					meteoToRemove.add(meteo);
				}
			}
		}
		meteoList.removeAll(meteoToRemove);
	}

	/**
	 * Récupération des météos que l'utilisateur veut ajouter
	 * @param request
	 * @param listMeteos
	 */
	public static void getListMeteosToAdd(HttpServletRequest request, List<Map<String, String>> listMeteos) {
		int i = 0;
		while(request.getParameter("meteo" + i) != null){
			Map<String, String> meteo = new HashMap<String, String>();
			meteo.put(METEO_ID, request.getParameter("meteo" + i));
			listMeteos.add(meteo);		
			++i;
		}
	}

	/**
	 * Suppression des destinataires de arg1 présents dans arg2
	 * @param meteoList
	 * @param listMeteos
	 */
	public static void removeDestinataires(List<Users> destinataireList, List<Map<String, String>> listDestinataires) {
		List<Users> userToRemove = new ArrayList<Users>();
		for(Users user : destinataireList){
			for(Map<String, String> destinataireToAdd : listDestinataires){
				if(user.getId().toString().equals(destinataireToAdd.get(DEST_ID))){
					destinataireToAdd.put(DEST_NOM, user.getNom());
					destinataireToAdd.put(DEST_PRENOM, user.getPrenom());
					userToRemove.add(user);
				}
			}
		}
		destinataireList.removeAll(userToRemove);
	}

	/**
	 * Récupération des destinataires que l'utilisateur veut ajouter
	 * @param request
	 * @param listMeteos
	 */
	public static void getListDestinatairesToAdd(HttpServletRequest request, List<Map<String, String>> listDestinatairess) {
		int i = 0;
		while(request.getParameter("destinataire" + i) != null){
			Map<String, String> user = new HashMap<String, String>();
			user.put(DEST_ID, request.getParameter("destinataire" + i));
			listDestinatairess.add(user);		
			++i;
		}
	}
	
	/**
	 * Récupération des horaires que l'utilisateur veut ajouter
	 * @param request
	 * @param listHoraires
	 */
	public static void getListHorairesToAdd(HttpServletRequest request, List<Map<String, String>> listHoraires) {
		int i = 0;
		while(request.getParameter("horaire" + i) != null){
			Map<String, String> horaire = new HashMap<String, String>();
			horaire.put(HORAIRE, request.getParameter("horaire" + i));
			listHoraires.add(horaire);		
			++i;
		}
	}
	
}
