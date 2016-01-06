package pilotage.meteo.environnement;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import pilotage.database.meteo.MeteoCaisseDatabaseService;
import pilotage.database.meteo.MeteoEnvironnementCaisseDatabaseService;
import pilotage.database.meteo.MeteoEnvironnementDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Meteo_Caisse;
import pilotage.metier.Meteo_Environnement;
import pilotage.metier.Meteo_Environnement_Caisse;

public class RedirectModifyEnvironnementMeteoAction extends AbstractAction{

	private static final long serialVersionUID = 6340712738315142963L;
	private Integer envirID;
	private String envirNOM;
	private String technique;
	private Meteo_Environnement envirMeteo;
	private List<Meteo_Caisse> caisseList;
	private Integer caisseToAdd;
	private Integer deleteCaisse;
	private Integer changeTechnique;
	private List<Map<String, String>> listCaisse;
	private String techniqueOui;
	private String techniqueNon;

	public Integer getEnvirID() {
		return envirID;
	}

	public void setEnvirID(Integer envirID) {
		this.envirID = envirID;
	}

	public String getEnvirNOM() {
		return envirNOM;
	}

	public void setEnvirNOM(String envirNOM) {
		this.envirNOM = envirNOM;
	}

	public String getTechnique() {
		return technique;
	}

	public void setTechnique(String technique) {
		this.technique = technique;
	}

	public Meteo_Environnement getEnvirMeteo() {
		return envirMeteo;
	}

	public void setEnvirMeteo(Meteo_Environnement envirMeteo) {
		this.envirMeteo = envirMeteo;
	}

	public List<Meteo_Caisse> getCaisseList() {
		return caisseList;
	}

	public void setCaisseList(List<Meteo_Caisse> caisseList) {
		this.caisseList = caisseList;
	}

	public Integer getCaisseToAdd() {
		return caisseToAdd;
	}

	public void setCaisseToAdd(Integer caisseToAdd) {
		this.caisseToAdd = caisseToAdd;
	}

	public Integer getDeleteCaisse() {
		return deleteCaisse;
	}

	public void setDeleteCaisse(Integer deleteCaisse) {
		this.deleteCaisse = deleteCaisse;
	}

	public Integer getChangeTechnique() {
		return changeTechnique;
	}

	public void setChangeTechnique(Integer changeTechnique) {
		this.changeTechnique = changeTechnique;
	}

	public List<Map<String, String>> getListCaisse() {
		return listCaisse;
	}

	public void setListCaisse(List<Map<String, String>> listCaisse) {
		this.listCaisse = listCaisse;
	}

	public String getTechniqueOui() {
		return techniqueOui;
	}

	public void setTechniqueOui(String techniqueOui) {
		this.techniqueOui = techniqueOui;
	}

	public String getTechniqueNon() {
		return techniqueNon;
	}

	public void setTechniqueNon(String techniqueNon) {
		this.techniqueNon = techniqueNon;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		HttpServletRequest request = ServletActionContext.getRequest();
		envirMeteo = MeteoEnvironnementDatabaseService.get(envirID);
		caisseList = MeteoCaisseDatabaseService.getAll();
		
		//Si on a cliqué sur suppr/ajour d'une caisse
		if((deleteCaisse != null && deleteCaisse != -1) || (caisseToAdd != null && caisseToAdd != -1)
				|| (changeTechnique != null && changeTechnique != -1))
		{
			if(changeTechnique != -1){
				technique = request.getParameter("technique");
			}
			
			//recupération des caisses à ajouter
			listCaisse= new ArrayList<Map<String,String>>();
			RedirectCreateEnvironnementMeteoAction.getListCaisseToAdd(request, listCaisse);
			
			//Si on a cliqué sur la suppression d'une caisse
			if(deleteCaisse != null && deleteCaisse != -1){
				Map<String, String> mapToRemove = null;
				for(Map<String, String> caisse : listCaisse){
					if(caisse.get(RedirectCreateEnvironnementMeteoAction.CAISSE_ID).equals(deleteCaisse.toString())){
						mapToRemove = caisse;
						break;
					}
				}
				if(mapToRemove != null)
					listCaisse.remove(mapToRemove);
			}
			//si on a cliqué sur l'ajout d'une caisse, on l'ajoute aux caisses à ajouter
			else if(caisseToAdd != null && caisseToAdd != -1){
				for(Meteo_Caisse caisse : caisseList){
					if(caisse.getId().equals(caisseToAdd)){
						Map<String, String> caisseMap = new HashMap<String, String>();
						caisseMap.put(RedirectCreateEnvironnementMeteoAction.CAISSE_ID, caisseToAdd.toString());
						listCaisse.add(caisseMap);
						break;
					}
				}
			}
			
			
			//suppression de la liste des applicatifs de tous les applicatifs à ajouter dans la machine
			//remplissage des noms des applis
			RedirectCreateEnvironnementMeteoAction.removeCaisse(caisseList, listCaisse);
		}else{
			//init des infos de l'environnement
			envirNOM = envirMeteo.getNom_envir();
			technique = String.valueOf(envirMeteo.getTechnique());
			
			//info pour page détail environnement 
			if(envirMeteo.getTechnique()==0){
				techniqueOui = "BoutonRadio0";
				techniqueNon ="BoutonRadio1";
			}
			else{
				techniqueOui = "BoutonRadio1";
				techniqueNon ="BoutonRadio0";
			}
			
			//init des caisses
			List<Meteo_Environnement_Caisse> listEnvirCaisse = MeteoEnvironnementCaisseDatabaseService.getListCaisseByEnvir(envirID);
			listCaisse = new ArrayList<Map<String,String>>();
			for(Meteo_Environnement_Caisse envirCaisse : listEnvirCaisse){
				Map<String, String> caisse = new HashMap<String, String>();
				caisse.put(RedirectCreateEnvironnementMeteoAction.CAISSE_ID, envirCaisse.getCaisse().getId().toString());
				listCaisse.add(caisse);
			}
			RedirectCreateEnvironnementMeteoAction.removeCaisse(caisseList, listCaisse);
		}
		return OK;
	}

}
