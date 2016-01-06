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
import pilotage.database.meteo.MeteoDatabaseService;
import pilotage.database.meteo.MeteoDestinataireDatabaseService;
import pilotage.database.meteo.MeteoFormatDatabaseService;
import pilotage.database.meteo.MeteoGroupeMeteoDatabaseService;
import pilotage.database.users.management.UsersDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Meteo_Format;
import pilotage.metier.Meteo_GroupeMeteo;
import pilotage.metier.Meteo_Meteo;
import pilotage.metier.Users;
import pilotage.service.date.DateService;

public class CreateGroupeMeteoAction extends AbstractAction{

	private static final long serialVersionUID = -8293020081867760629L;
	private String nom_groupeMeteo;
	private List<Map<String, String>> listMeteos;
	private List<Map<String, String>> listDestinataires;
	private List<Map<String, String>> listHoraires;
	private Integer idFormat;
	private Integer idFuseau;

	public String getNom_groupeMeteo() {
		return nom_groupeMeteo;
	}

	public void setNom_groupeMeteo(String nom_groupeMeteo) {
		this.nom_groupeMeteo = nom_groupeMeteo;
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
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			
			//recupération des météos à ajouter
			listMeteos = new ArrayList<Map<String,String>>();
			List<Meteo_Meteo> meteoList = new ArrayList<Meteo_Meteo>();
			RedirectCreateGroupeMeteoAction.getListMeteosToAdd(request, listMeteos);
			for(Map<String, String> ligneMeteo : listMeteos){
				meteoList.add(MeteoDatabaseService.get(Integer.parseInt(ligneMeteo.get("meteoID"))));
			}
			
			//récupération des destinataires à ajouter
			listDestinataires = new ArrayList<Map<String,String>>();
			RedirectCreateGroupeMeteoAction.getListDestinatairesToAdd(request, listDestinataires);
			List<Users> destinataireList = new ArrayList<Users>();
			for(Map<String, String> ligneDest : listDestinataires){
				destinataireList.add(UsersDatabaseService.get(Integer.parseInt(ligneDest.get("destId"))));
			}
			
			//récupération des horaires à ajouter
			listHoraires = new ArrayList<Map<String,String>>();
			RedirectCreateGroupeMeteoAction.getListHorairesToAdd(request, listHoraires);
			List<Date> horaireList = new ArrayList<Date>();
			for(Map<String, String> ligneHoraire : listHoraires){
				Date horaire = DateService.getTimeFromString(ligneHoraire.get("horaire"));
				horaireList.add(horaire);
			}
			
			Meteo_Format format = MeteoFormatDatabaseService.get(idFormat);
			String fuseau = HeuresOceorDatabaseService.getTimezone(idFuseau);
			
			//creation d'un nouveau groupe Météo
			GroupeMeteoDatabaseService.create(nom_groupeMeteo, format, fuseau);
			Meteo_GroupeMeteo groupe = GroupeMeteoDatabaseService.getByName(nom_groupeMeteo);
			
			MeteoGroupeMeteoDatabaseService.create(groupe, meteoList);
			MeteoDestinataireDatabaseService.create(groupe, destinataireList);
			GroupeMeteoHoraireDatabaseService.create(groupe, horaireList);
			info = MessageFormat.format(getText("groupeMeteo.creation.valide"),nom_groupeMeteo);
			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Création d'un groupe Météo - ", e);
			return ERROR;
		}
	}

	
}
