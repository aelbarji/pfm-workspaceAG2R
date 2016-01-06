package pilotage.meteo.indicateur;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import pilotage.database.meteo.MeteoEnvironnementDatabaseService;
import pilotage.database.meteo.MeteoIndicateurDatabaseService;
import pilotage.database.meteo.MeteoServiceDatabaseService;
import pilotage.database.meteo.TypeIndicateurDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Meteo_Environnement;
import pilotage.metier.Meteo_Service;
import pilotage.metier.Meteo_TypeIndicateur;

public class CreatePlageFonctIndicServiceAction extends AbstractAction{

	private static final long serialVersionUID = -8841364939383004943L;
	private Meteo_Service service;
	private Integer selectRow;
	private String nomService;
	private Integer envir;	
	private String type;
	private int auto;
	private int debut;
	private int fin;
	private Meteo_Environnement environnement;
	private Meteo_TypeIndicateur typeIndic;
	private List<Map<String,String>> listPlage;

	public Meteo_Service getService() {
		return service;
	}

	public void setService(Meteo_Service service) {
		this.service = service;
	}

	public Integer getSelectRow() {
		return selectRow;
	}

	public void setSelectRow(Integer selectRow) {
		this.selectRow = selectRow;
	}

	public String getNomService() {
		return nomService;
	}

	public void setNomService(String nomService) {
		this.nomService = nomService;
	}

	public Integer getEnvir() {
		return envir;
	}

	public void setEnvir(Integer envir) {
		this.envir = envir;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getAuto() {
		return auto;
	}

	public void setAuto(int auto) {
		this.auto = auto;
	}

	public int getDebut() {
		return debut;
	}

	public void setDebut(int debut) {
		this.debut = debut;
	}

	public int getFin() {
		return fin;
	}

	public void setFin(int fin) {
		this.fin = fin;
	}

	public Meteo_Environnement getEnvironnement() {
		return environnement;
	}

	public void setEnvironnement(Meteo_Environnement environnement) {
		this.environnement = environnement;
	}

	public Meteo_TypeIndicateur getTypeIndic() {
		return typeIndic;
	}

	public void setTypeIndic(Meteo_TypeIndicateur typeIndic) {
		this.typeIndic = typeIndic;
	}

	public List<Map<String, String>> getListPlage() {
		return listPlage;
	}

	public void setListPlage(List<Map<String, String>> listPlage) {
		this.listPlage = listPlage;
	}

	@Override
	protected boolean validateMetier() {
		environnement = MeteoEnvironnementDatabaseService.get(envir);
		if(MeteoIndicateurDatabaseService.getIndicateurByServiceAndEnvir(service, environnement)!=null){
			error = MessageFormat.format(getText("indicateur.creation.existe.deja"),"");
			return false;
		}
		return true;
	}

	@Override
	protected String executeMetier() {
		try{
			HttpServletRequest request = ServletActionContext.getRequest();
			listPlage = new ArrayList<Map<String,String>>();
			getListPlageToAdd(request, listPlage);
			service = MeteoServiceDatabaseService.get(selectRow);
			//typeIndic = TypeIndicateurDatabaseService.get(Integer.parseInt(type));
			MeteoIndicateurDatabaseService.createIndicateur(service, environnement, typeIndic, auto, listPlage, debut, fin);
			info = MessageFormat.format(getText("indicateurService.creation.valide"),nomService);
			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Création d'un indicateur - ", e);
			return ERROR;
		}
	}
	
	public static void getListPlageToAdd(HttpServletRequest request, List<Map<String, String>> listPlage) {
		int i = 0;
		while(request.getParameter("heureDebut" + i) != null){
			Map<String, String> al = new HashMap<String, String>();
			if( request.getParameter("idPlage" +i)!=null){
			al.put("idPlage", request.getParameter("idPlage" +i));
			}
			al.put("heureDebut", request.getParameter("heureDebut" + i));
			al.put("heureFin", request.getParameter("heureFin"+i));
			al.put("zone",request.getParameter("zone"+i));
			al.put("etatDesire", request.getParameter("desire"+i));
			al.put("perturbe_max", request.getParameter("perturbe_max"+i));
			al.put("perturbe_min", request.getParameter("perturbe_min"+i));
			al.put("ko_max", request.getParameter("ko_max"+i));
			al.put("ko_min", request.getParameter("ko_min"+i));
			al.put("lundi", request.getParameter("lundi"+i));
			al.put("mardi", request.getParameter("mardi"+i));
			al.put("mercredi", request.getParameter("mercredi"+i));
			al.put("jeudi", request.getParameter("jeudi"+i));
			al.put("vendredi", request.getParameter("vendredi"+i));
			al.put("samedi", request.getParameter("samedi"+i));
			al.put("dimanche", request.getParameter("dimanche"+i));
			al.put("ferie", request.getParameter("ferie"+i));
			al.put("seuil", request.getParameter("seuil"+i));
			listPlage.add(al);
			++i;
		}
		
	}

	

}
