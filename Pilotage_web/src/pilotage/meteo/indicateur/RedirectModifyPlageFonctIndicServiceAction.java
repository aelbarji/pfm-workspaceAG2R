package pilotage.meteo.indicateur;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;

import java.util.Map;

import pilotage.database.incidents.HeuresOceorDatabaseService;
import pilotage.database.meteo.EtatPossibleDatabaseService;
import pilotage.database.meteo.MeteoIndicateurDatabaseService;
import pilotage.database.meteo.MeteoServiceDatabaseService;
import pilotage.database.meteo.TypeIndicateurDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Heures_Oceor;
import pilotage.metier.Meteo_Indicateur;
import pilotage.metier.Meteo_PlageFonct_Jour;
import pilotage.metier.Meteo_PlageFonctionnement;
import pilotage.metier.Meteo_Service;
import pilotage.metier.Meteo_SeuilPlage;
import pilotage.metier.Meteo_TypeIndic_EtatPoss;
import pilotage.metier.Meteo_TypeIndicateur;
import pilotage.service.date.DateService;

public class RedirectModifyPlageFonctIndicServiceAction extends AbstractAction{

	private static final long serialVersionUID = -6364202471879380281L;

	private Integer idIndic;
	private Integer selectRow;
	private Meteo_Service service;
	private String nomService;
	private String nomEnvir;
	private Meteo_TypeIndicateur type;
	private boolean boolType;
	private Integer deletePlage;
	private Integer envir;
	private List<Meteo_PlageFonctionnement> plageList;
	private List<Map<String,String>> listPlage;
	private List<Heures_Oceor> listZone;
	private List<Meteo_TypeIndic_EtatPoss> listEtatByType;
	private int nbPlages;
	
	public Meteo_TypeIndicateur getType() {
		return type;
	}

	public void setType(Meteo_TypeIndicateur type) {
		this.type = type;
	}

	public Integer getEnvir() {
		return envir;
	}

	public void setEnvir(Integer envir) {
		this.envir = envir;
	}

	public List<Heures_Oceor> getListZone() {
		return listZone;
	}

	public void setListZone(List<Heures_Oceor> listZone) {
		this.listZone = listZone;
	}

	public List<Meteo_TypeIndic_EtatPoss> getListEtatByType() {
		return listEtatByType;
	}

	public void setListEtatByType(List<Meteo_TypeIndic_EtatPoss> listEtatByType) {
		this.listEtatByType = listEtatByType;
	}

	public Integer getIdIndic() {
		return idIndic;
	}

	public void setIdIndic(Integer idIndic) {
		this.idIndic = idIndic;
	}

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

	public String getNomEnvir() {
		return nomEnvir;
	}

	public void setNomEnvir(String nomEnvir) {
		this.nomEnvir = nomEnvir;
	}

	public Integer getDeletePlage() {
		return deletePlage;
	}

	public void setDeletePlage(Integer deletePlage) {
		this.deletePlage = deletePlage;
	}

	public List<Meteo_PlageFonctionnement> getPlageList() {
		return plageList;
	}

	public void setPlageList(List<Meteo_PlageFonctionnement> plageList) {
		this.plageList = plageList;
	}

	public List<Map<String, String>> getListPlage() {
		return listPlage;
	}

	public void setListPlage(List<Map<String, String>> listPlage) {
		this.listPlage = listPlage;
	}

	public boolean isBoolType() {
		return boolType;
	}

	public void setBoolType(boolean boolType) {
		this.boolType = boolType;
	}

	public int getNbPlages() {
		return nbPlages;
	}

	public void setNbPlages(int nbPlages) {
		this.nbPlages = nbPlages;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		service = MeteoServiceDatabaseService.get(selectRow);
		nomService = service.getService();
		listZone =  HeuresOceorDatabaseService.getAll();
		Meteo_Indicateur indicateur = MeteoIndicateurDatabaseService.get(idIndic);
		nomEnvir = indicateur.getEnvironnement().getNom_envir();
		listPlage = getPlagesFonctionnement(MeteoIndicateurDatabaseService.getListPlageActive(indicateur));
		nbPlages = listPlage.size();
		type = indicateur.getTypeIndic();
		listEtatByType = EtatPossibleDatabaseService.getEtatsByType(TypeIndicateurDatabaseService.get(type.getId()));
		if(type.getFormat().getFormat().equals("liste") || type.getFormat().getFormat().equals("reel")){
			boolType=true;
		}else{
			boolType=false;
		}
		return OK;
	}
	
	private List<Map<String,String>> getPlagesFonctionnement(List<Meteo_PlageFonctionnement> plages){
		listPlage = new ArrayList<Map<String,String>>();
		for(Meteo_PlageFonctionnement plage : plages){
			Map<String, String> al = new HashMap<String, String>();
			List<Meteo_PlageFonct_Jour> jours = MeteoIndicateurDatabaseService.getListJoursByPlage(plage);
			al.put("idPlage", Integer.toString(plage.getId()));
			
			al.put("ferie", "");
			al.put("dimanche", "");
			al.put("lundi", "");
			al.put("mardi", "");
			al.put("mercredi", "");
			al.put("jeudi", "");
			al.put("vendredi", "");
			al.put("samedi", "");
			for(Meteo_PlageFonct_Jour j: jours){
				if(j.getFerie()==1){
					al.put("ferie", "checked");
				}
				if(j.getJour()==0){
					al.put("dimanche", "checked");
				}
				if(j.getJour()==1){
					al.put("lundi", "checked");
				}
				if(j.getJour()==2){
					al.put("mardi", "checked");
				}
				if(j.getJour()==3){
					al.put("mercredi", "checked");
				}
				if(j.getJour()==4){
					al.put("jeudi", "checked");
				}
				if(j.getJour()==5){
					al.put("vendredi", "checked");
				}
				if(j.getJour()==6){
					al.put("samedi", "checked");
				}
			}
			
			al.put("heureDebut", DateService.getTime(plage.getHeureDebut(), null));
			al.put("heureFin", DateService.getTime(plage.getHeureFin(), null));
			al.put("idZone", Integer.toString(plage.getZone().getId()));
			al.put("etatDesire", plage.getEtatDesire());
			
			if(plage.getIndicateur().getTypeIndic().getId() == 0){
				Meteo_SeuilPlage seuil = plage.getSeuil();
				al.put("seuil", Integer.toString(seuil.getId()));
				al.put("perturbe_max", seuil.getPertu_max());
				al.put("perturbe_min", seuil.getPertu_min());
				al.put("ko_max", seuil.getKo_max());
				al.put("ko_min", seuil.getKo_min());
			}
			
			listPlage.add(al);
		}
		return listPlage;
	}
}
