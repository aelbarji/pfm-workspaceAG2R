package pilotage.meteo.groupeMeteo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;

import pilotage.database.incidents.HeuresOceorDatabaseService;
import pilotage.database.meteo.GroupeMeteoDatabaseService;
import pilotage.database.meteo.GroupeMeteoHoraireDatabaseService;
import pilotage.database.meteo.MeteoDatabaseService;
import pilotage.database.meteo.MeteoDestinataireDatabaseService;
import pilotage.database.meteo.MeteoFormatDatabaseService;
import pilotage.database.meteo.MeteoGroupeMeteoDatabaseService;
import pilotage.database.users.management.UsersDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Heures_Oceor;
import pilotage.metier.Meteo_Format;
import pilotage.metier.Meteo_GroupeMeteo;
import pilotage.metier.Meteo_GroupeMeteo_Meteo;
import pilotage.metier.Meteo_Meteo;
import pilotage.metier.Users;

public class RedirectModifyGroupeMeteoAction extends AbstractAction{

	private static final long serialVersionUID = -4480008673482395823L;
	
	private int groupeMeteoID;
	private String nomGroupeMeteo;
	private Meteo_GroupeMeteo groupeMeteo;
	private List<Meteo_Meteo> meteoList;
	private List<Users> destinataireList;
	private List<Date> horaireList;
	private Integer meteoToAdd;
	private Integer deleteMeteo;
	private Integer destinataireToAdd;
	private Integer deleteDestinataire;
	private String horaireToAdd;
	private String deleteHoraire;
	private List<Map<String, String>> listMeteos;
	private List<Map<String, String>> listDestinataires;
	private List<Map<String, String>> listHoraires;
	private List<Meteo_Format> listFormats;
	private Integer idFormat;
	private String stringFormat;
	private List<Heures_Oceor> listFuseaux;
	private String fuseau;
	
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

	public List<Map<String, String>> getListMeteos() {
		return listMeteos;
	}

	public void setListMeteos(List<Map<String, String>> listMeteos) {
		this.listMeteos = listMeteos;
	}

	public Meteo_GroupeMeteo getGroupeMeteo() {
		return groupeMeteo;
	}

	public void setGroupeMeteo(Meteo_GroupeMeteo groupeMeteo) {
		this.groupeMeteo = groupeMeteo;
	}
	
	public int getGroupeMeteoID() {
		return groupeMeteoID;
	}

	public void setGroupeMeteoID(int groupeMeteoID) {
		this.groupeMeteoID = groupeMeteoID;
	}
	
	public String getNomGroupeMeteo() {
		return nomGroupeMeteo;
	}

	public void setNomGroupeMeteo(String nomGroupeMeteo) {
		this.nomGroupeMeteo = nomGroupeMeteo;
	}
	
	public List<Meteo_Meteo> getMeteoList() {
		return meteoList;
	}

	public void setMeteoList(List<Meteo_Meteo> meteoList) {
		this.meteoList = meteoList;
	}
	

	public List<Users> getDestinataireList() {
		return destinataireList;
	}

	public void setDestinataireList(List<Users> destinataireList) {
		this.destinataireList = destinataireList;
	}

	public List<Map<String, String>> getListDestinataires() {
		return listDestinataires;
	}

	public void setListDestinataires(List<Map<String, String>> listDestinataires) {
		this.listDestinataires = listDestinataires;
	}

	public List<Date> getHoraireList() {
		return horaireList;
	}

	public void setHoraireList(List<Date> horaireList) {
		this.horaireList = horaireList;
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

	public List<Map<String, String>> getListHoraires() {
		return listHoraires;
	}

	public void setListHoraires(List<Map<String, String>> listHoraires) {
		this.listHoraires = listHoraires;
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

	public String getStringFormat() {
		return stringFormat;
	}

	public void setStringFormat(String stringFormat) {
		this.stringFormat = stringFormat;
	}

	public List<Heures_Oceor> getListFuseaux() {
		return listFuseaux;
	}

	public void setListFuseaux(List<Heures_Oceor> listFuseaux) {
		this.listFuseaux = listFuseaux;
	}

	public String getFuseau() {
		return fuseau;
	}

	public void setFuseau(String fuseau) {
		this.fuseau = fuseau;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		HttpServletRequest request = ServletActionContext.getRequest();
		groupeMeteo = GroupeMeteoDatabaseService.get(groupeMeteoID);
		meteoList = MeteoDatabaseService.getAll();
		destinataireList = UsersDatabaseService.getAll();
		listFormats = MeteoFormatDatabaseService.getAll();
		idFormat = groupeMeteo.getFormat().getId();
		stringFormat = groupeMeteo.getFormat().getFormat();
		listFuseaux = HeuresOceorDatabaseService.getAll(); 
		fuseau = groupeMeteo.getTimezone(); 
		
		
		//Si on a cliqué sur suppr/ajour d'une météo
		if((deleteMeteo != null && deleteMeteo != -1) || (meteoToAdd != null && meteoToAdd != -1)
				|| (deleteDestinataire != null && deleteDestinataire != -1)
				|| (destinataireToAdd != null && destinataireToAdd != -1) 
				|| (deleteHoraire != null && !deleteHoraire.equals("-1"))
				|| (horaireToAdd != null && !horaireToAdd.equals(""))){
			
			//init des infos du groupe Météo
			nomGroupeMeteo = groupeMeteo.getNom_groupeMeteo();
			
			//recupération des météos à ajouter
			listMeteos= new ArrayList<Map<String,String>>();
			RedirectCreateGroupeMeteoAction.getListMeteosToAdd(request, listMeteos);
			
			//recupération des destinataires à ajouter
			listDestinataires = new ArrayList<Map<String,String>>();
			RedirectCreateGroupeMeteoAction.getListDestinatairesToAdd(request, listDestinataires);
			
			//récupération des horaires à ajouter
			listHoraires = new ArrayList<Map<String,String>>();
			RedirectCreateGroupeMeteoAction.getListHorairesToAdd(request, listHoraires);
			
			//Si on a cliqué sur la suppression d'une météo
			if(deleteMeteo != null && deleteMeteo != -1){
				Map<String, String> mapToRemove = null;
				for(Map<String, String> meteo : listMeteos){
					if(meteo.get(RedirectCreateGroupeMeteoAction.METEO_ID).equals(deleteMeteo.toString())){
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
						meteo.put(RedirectCreateGroupeMeteoAction.METEO_ID, meteoToAdd.toString());
						listMeteos.add(meteo);
						break;
					}
				}
			}
			
			//si on a cliqué sur la suppression d'un destinataire
			else if(deleteDestinataire != null && deleteDestinataire != -1){
				Map<String, String> destToRemove = null;
				for(Map<String, String> dest : listDestinataires){
					if(dest.get(RedirectCreateGroupeMeteoAction.DEST_ID).equals(deleteDestinataire.toString())){
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
						u.put(RedirectCreateGroupeMeteoAction.DEST_ID, destinataireToAdd.toString());
						listDestinataires.add(u);
						break;
					}
				}
			}
			
			//si on a cliqué sur l'ajout d'un horaire, on l'ajoute aux horaires à ajouter
			else if(horaireToAdd != null && !horaireToAdd.equals("")){
				int present = 0;
				for(Map<String, String> horaire : listHoraires){
					if(horaire.get(RedirectCreateGroupeMeteoAction.HORAIRE).equals(horaireToAdd.toString())){
						present = 1;
						break;
					}	
				}
				if(present==0){
					Map<String, String> map = new HashMap<String, String>();
					map.put(RedirectCreateGroupeMeteoAction.HORAIRE, horaireToAdd.toString());
					listHoraires.add(map);
				}
			}
			
			//si on a cliqué sur la suppression d'un horaire
			else if(deleteHoraire != null && !deleteHoraire.equals("-1")){
				Map<String, String> horaireToRemove = null;
				for(Map<String, String> horaire : listHoraires){
					if(horaire.get(RedirectCreateGroupeMeteoAction.HORAIRE).equals(deleteHoraire.toString())){
						horaireToRemove = horaire;
						break;
					}
				}
				if(horaireToRemove != null){
					listHoraires.remove(horaireToRemove);
				}
			}
			
			//suppression de la liste des applicatifs de tous les applicatifs à ajouter dans la machine
			//remplissage des noms des applis
			RedirectCreateGroupeMeteoAction.removeMeteos(meteoList, listMeteos);
			RedirectCreateGroupeMeteoAction.removeDestinataires(destinataireList, listDestinataires);
		}else{
			//init des infos du groupe Météo
			nomGroupeMeteo = groupeMeteo.getNom_groupeMeteo();
			
			//resuperation des destinataires du groupe
			List<Users> users = MeteoDestinataireDatabaseService.getDestinataires(groupeMeteoID);
			listDestinataires = new ArrayList<Map<String,String>>();
			for(Users u : users){
				Map<String, String> destinataire = new HashMap<String, String>();
				destinataire.put(RedirectCreateGroupeMeteoAction.DEST_ID, u.getId().toString());
				listDestinataires.add(destinataire);
			}
			RedirectCreateGroupeMeteoAction.removeDestinataires(destinataireList, listDestinataires);
			
			//init des météos
			List<Meteo_GroupeMeteo_Meteo> listMeteoGroupeMeteo = MeteoGroupeMeteoDatabaseService.getMeteosFromGroupeMeteo(groupeMeteo.getId());
			listMeteos = new ArrayList<Map<String,String>>();
			for(Meteo_GroupeMeteo_Meteo meteoGroupe : listMeteoGroupeMeteo){
				if(meteoGroupe.getDateSuppression() == null){
					Map<String, String> meteo = new HashMap<String, String>();
					meteo.put(RedirectCreateGroupeMeteoAction.METEO_ID, meteoGroupe.getIdMeteos().getId().toString());
					listMeteos.add(meteo);
				}
			}
			RedirectCreateGroupeMeteoAction.removeMeteos(meteoList, listMeteos);
			
			//récupération des horaires du groupe
			List<Date> horaires = GroupeMeteoHoraireDatabaseService.getHoraires(groupeMeteoID, null);
			listHoraires = new ArrayList<Map<String,String>>();
			for(Date horaire : horaires){
				Map<String, String> horaireGroupe = new HashMap<String, String>();
				horaireGroupe.put(RedirectCreateGroupeMeteoAction.HORAIRE, horaire.toString());
				listHoraires.add(horaireGroupe);
			}
		}
			return OK;
	}


}
