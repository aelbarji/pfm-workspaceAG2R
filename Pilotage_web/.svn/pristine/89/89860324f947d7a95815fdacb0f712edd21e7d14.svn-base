package pilotage.meteo.groupeMeteo;

import java.text.MessageFormat;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import pilotage.database.incidents.HeuresOceorDatabaseService;
import pilotage.database.meteo.GroupeMeteoDatabaseService;
import pilotage.database.meteo.GroupeMeteoHoraireDatabaseService;
import pilotage.database.meteo.MeteoDestinataireDatabaseService;
import pilotage.database.meteo.MeteoFormatDatabaseService;
import pilotage.database.meteo.MeteoGroupeMeteoDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Meteo_Format;
import pilotage.metier.Meteo_GroupeMeteo;
import pilotage.metier.Meteo_GroupeMeteo_Meteo;
import pilotage.metier.Users;
import pilotage.service.date.DateService;

public class ModifyGroupeMeteoAction extends AbstractAction{

	private static final long serialVersionUID = 7819934438942596418L;
	private Integer groupeMeteoID;
	private String nomGroupeMeteo;
	private List<Map<String, String>> listMeteos;
	private List<Map<String,String>> listDestinataires;
	private List<Map<String,String>> listHoraires;
	private Integer idFormat;
	private Integer idFuseau;
	
	public String getNomGroupeMeteo() {
		return nomGroupeMeteo;
	}

	public void setNomGroupeMeteo(String nomGroupeMeteo) {
		this.nomGroupeMeteo = nomGroupeMeteo;
	}

	
	public Integer getGroupeMeteoID() {
		return groupeMeteoID;
	}

	public void setGroupeMeteoID(Integer groupeMeteoID) {
		this.groupeMeteoID = groupeMeteoID;
	}

	public List<Map<String, String>> getListMeteos() {
		return listMeteos;
	}

	public void setListMeteos(List<Map<String, String>> listMeteos) {
		this.listMeteos = listMeteos;
	}

	public List<Map<String, String>> getListDestinataires() {
		return listDestinataires;
	}

	public void setListDestinataires(List<Map<String, String>> listDestinataires) {
		this.listDestinataires = listDestinataires;
	}

	public List<Map<String, String>> getListHoraires() {
		return listHoraires;
	}

	public void setListHoraires(List<Map<String, String>> listHoraires) {
		this.listHoraires = listHoraires;
	}

	public Integer getIdFormat() {
		return idFormat;
	}

	public void setIdFormat(Integer idFormat) {
		this.idFormat = idFormat;
	}

	public Integer getIdFuseau() {
		return idFuseau;
	}

	public void setIdFuseau(Integer idFuseau) {
		this.idFuseau = idFuseau;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		try{
			HttpServletRequest request = ServletActionContext.getRequest();
			
			//recupération des météos
			listMeteos = new ArrayList<Map<String,String>>();
			RedirectCreateGroupeMeteoAction.getListMeteosToAdd(request, listMeteos);
			
			//récupération des infos en base
			List<Meteo_GroupeMeteo_Meteo> listMeteosEnBase = MeteoGroupeMeteoDatabaseService.getMeteosFromGroupeMeteo(groupeMeteoID);
			
			//Isolation des modifications des météos
			List<Integer> meteoToDelete = new ArrayList<Integer>();
			List<Integer> meteoToAdd = new ArrayList<Integer>();
			for(Map<String,String> meteo : listMeteos){
				boolean alreadyInBase = false;
				Integer id = Integer.parseInt(meteo.get(RedirectCreateGroupeMeteoAction.METEO_ID));
				for(Meteo_GroupeMeteo_Meteo meteoGroupe : listMeteosEnBase){
					if(meteoGroupe.getIdMeteos().getId().equals(id)){
						alreadyInBase = true;
						break;
					}
				}
				if(!alreadyInBase){
					meteoToAdd.add(id);
				}
			}
			for(Meteo_GroupeMeteo_Meteo meteoGroupe : listMeteosEnBase){
				boolean stillInList = false;
				String idString = meteoGroupe.getIdMeteos().getId().toString();
				for(Map<String,String> meteo : listMeteos){
					if(meteo.get(RedirectCreateGroupeMeteoAction.METEO_ID).equals(idString)){
						stillInList = true;
						break;
					}
				}
				if(!stillInList){
					meteoToDelete.add(meteoGroupe.getIdMeteos().getId());
				}
			}
			
			//recupération des destinataires
			listDestinataires = new ArrayList<Map<String,String>>();
			RedirectCreateGroupeMeteoAction.getListDestinatairesToAdd(request, listDestinataires);
			
			//récupération des infos en base
			List<Users> usersEnBase = MeteoDestinataireDatabaseService.getDestinataires(groupeMeteoID);
			
			//Isolation des modifications des destinataires
			List<Integer> destinataireToDelete = new ArrayList<Integer>();
			List<Integer> destinataireToAdd = new ArrayList<Integer>();
			for(Map<String,String> dest : listDestinataires){
				boolean alreadyInBase = false;
				int id = Integer.parseInt(dest.get(RedirectCreateGroupeMeteoAction.DEST_ID));
				for(Users user : usersEnBase){
					if(user.getId() == id){
						alreadyInBase = true;
						break;
					}
				}

				if(!alreadyInBase){
					destinataireToAdd.add(id);
				}
			}
			for(Users user : usersEnBase){
				boolean stillInList = false;
				String idString = user.getId().toString();
				for(Map<String,String> dest : listDestinataires){
					if(dest.get(RedirectCreateGroupeMeteoAction.DEST_ID).equals(idString)){
						stillInList = true;
						break;
					}
				}
				if(!stillInList){
					destinataireToDelete.add(user.getId());
				}
			}
			
			//recupération des destinataires
			listHoraires = new ArrayList<Map<String,String>>();
			RedirectCreateGroupeMeteoAction.getListHorairesToAdd(request, listHoraires);
			
			//récupération des infos en base
			List<Date> horaireEnBase = GroupeMeteoHoraireDatabaseService.getHoraires(groupeMeteoID, null);
			
			//Isolation des modifications des horaires
			List<Date> horaireToDelete = new ArrayList<Date>();
			List<Date> horaireToAdd = new ArrayList<Date>();
			for(Map<String,String> horaire : listHoraires){
				boolean alreadyInBase = false;
				for(Date heure : horaireEnBase){
					if(heure.toString().equals(horaire.get(RedirectCreateGroupeMeteoAction.HORAIRE))){
						alreadyInBase = true;
						break;
					}
				}
				if(!alreadyInBase){
					Date horaireGroupe = DateService.getTimeFromString(horaire.get(RedirectCreateGroupeMeteoAction.HORAIRE));
					horaireToAdd.add(horaireGroupe);
				}
			}
			for(Date heure : horaireEnBase){
				boolean stillInList = false;
				String ho = heure.toString();
				for(Map<String,String> horaire : listHoraires){
					if(horaire.get(RedirectCreateGroupeMeteoAction.HORAIRE).equals(ho)){
						stillInList = true;
						break;
					}
				}
				if(!stillInList){
					horaireToDelete.add(heure);
				}
			}
			
			Meteo_Format format = MeteoFormatDatabaseService.get(idFormat);
			MeteoGroupeMeteoDatabaseService.modify(groupeMeteoID, nomGroupeMeteo, meteoToAdd, meteoToDelete, format, HeuresOceorDatabaseService.getTimezone(idFuseau));
			MeteoDestinataireDatabaseService.modify(groupeMeteoID, destinataireToAdd, destinataireToDelete);
			Meteo_GroupeMeteo groupe = GroupeMeteoDatabaseService.get(groupeMeteoID);
			GroupeMeteoHoraireDatabaseService.modify(groupe, horaireToAdd, horaireToDelete);
			
			info = MessageFormat.format(getText("groupeMeteo.modification.valide"), nomGroupeMeteo);
			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Modification d'un groupe Météo - ", e);
			return ERROR;
		}
	}

}
