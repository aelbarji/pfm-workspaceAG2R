package pilotage.meteo.indicateur;

import java.text.MessageFormat;

import java.util.List;
import java.util.Map;

import pilotage.database.incidents.HeuresOceorDatabaseService;
import pilotage.database.meteo.EtatPossibleDatabaseService;
import pilotage.database.meteo.MeteoEnvironnementDatabaseService;
import pilotage.database.meteo.MeteoIndicateurDatabaseService;
import pilotage.database.meteo.MeteoServiceDatabaseService;
import pilotage.database.meteo.TypeIndicateurDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Heures_Oceor;
import pilotage.metier.Meteo_Indicateur;
import pilotage.metier.Meteo_Service;
import pilotage.metier.Meteo_TypeIndic_EtatPoss;
import pilotage.metier.Meteo_TypeIndicateur;

public class CreateIndicateurServiceAction extends AbstractAction{

	private static final long serialVersionUID = -8707265469678060391L;

	private Integer selectRow;
	private Meteo_Service service;
	private String nomService;
	private Integer envir;
	private String type;
	private int auto;
	private int debut;
	private int fin;
	private List<Heures_Oceor> listZone;
	private String nomEnvir;
	private Integer idIndic;
	private List<Map<String,String>> listPlage;
	private List<Meteo_TypeIndic_EtatPoss> listEtatByType;
	private String etatDesire;
	private Integer deletePlage;
	private boolean etatdesire = true;
	private Meteo_TypeIndicateur typeIndic;
	
	public Integer getSelectRow() {
		return selectRow;
	}

	public void setSelectRow(Integer selectRow) {
		this.selectRow = selectRow;
	}

	public Meteo_Service getService() {
		return service;
	}

	public void setService(Meteo_Service service) {
		this.service = service;
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

	public List<Heures_Oceor> getListZone() {
		return listZone;
	}

	public void setListZone(List<Heures_Oceor> listZone) {
		this.listZone = listZone;
	}

	public String getNomEnvir() {
		return nomEnvir;
	}

	public void setNomEnvir(String nomEnvir) {
		this.nomEnvir = nomEnvir;
	}

	public Integer getIdIndic() {
		return idIndic;
	}

	public void setIdIndic(Integer idIndic) {
		this.idIndic = idIndic;
	}

	public List<Map<String, String>> getListPlage() {
		return listPlage;
	}

	public void setListPlage(List<Map<String, String>> listPlage) {
		this.listPlage = listPlage;
	}

	public List<Meteo_TypeIndic_EtatPoss> getListEtatByType() {
		return listEtatByType;
	}

	public void setListEtatByType(List<Meteo_TypeIndic_EtatPoss> listEtatByType) {
		this.listEtatByType = listEtatByType;
	}

	public String getEtatDesire() {
		return etatDesire;
	}

	public void setEtatDesire(String etatDesire) {
		this.etatDesire = etatDesire;
	}

	public Integer getDeletePlage() {
		return deletePlage;
	}

	public void setDeletePlage(Integer deletePlage) {
		this.deletePlage = deletePlage;
	}

	public boolean isEtatdesire() {
		return etatdesire;
	}

	public void setEtatdesire(boolean etatdesire) {
		this.etatdesire = etatdesire;
	}

	public Meteo_TypeIndicateur getTypeIndic() {
		return typeIndic;
	}

	public void setTypeIndic(Meteo_TypeIndicateur typeIndic) {
		this.typeIndic = typeIndic;
	}

	@Override
	protected boolean validateMetier() {
		try{
			service = MeteoServiceDatabaseService.get(selectRow);
			List<Meteo_Indicateur> listIndic = MeteoIndicateurDatabaseService.getListIndicByService(service);
			for(Meteo_Indicateur indic : listIndic){
				if(indic.getEnvironnement().getId()==envir){
					error = MessageFormat.format(getText("indicateur.creation.existe.deja"),"");
					return false;
				}
			}
			return true;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Création d'un indicateur - ", e);
			return false;
		}
	}

	@Override
	protected String executeMetier() {
		try {		
			service = MeteoServiceDatabaseService.get(selectRow);
			nomService = service.getService();
			listZone =  HeuresOceorDatabaseService.getAll();
			type = type.split("-")[1];
			Meteo_TypeIndicateur typeIndic = TypeIndicateurDatabaseService.get(Integer.parseInt(type));
			if(typeIndic.getFormat().getFormat().equals("datetime") || typeIndic.getFormat().getFormat().equals("varchar")) etatdesire = false;
			listEtatByType = EtatPossibleDatabaseService.getEtatsByType(typeIndic);
			nomEnvir = MeteoEnvironnementDatabaseService.get(envir).getNom_envir();
			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Création d'un indicateur - ", e);
			return ERROR;
		}
	}

}
