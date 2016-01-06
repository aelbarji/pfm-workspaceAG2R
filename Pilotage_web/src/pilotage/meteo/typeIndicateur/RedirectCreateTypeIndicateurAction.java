package pilotage.meteo.typeIndicateur;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import pilotage.database.meteo.EtatPossibleDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Meteo_EtatPossible;

public class RedirectCreateTypeIndicateurAction extends AbstractAction{

	private static final long serialVersionUID = -2278745152466624320L;

	public static final String ETAT_ID = "etatID";
	public static final String ETAT_NAME = "etatName";
	public static final String ETAT_COULEUR = "etatCouleur";
	public static final String ETAT_DEFINITION = "etatDefinition";
	private List<Meteo_EtatPossible> etatList;
	private Integer etatToAdd;
	private Integer deleteEtat;
	private List<Map<String,String>> listEtat;
	private String type;
	
	public List<Meteo_EtatPossible> getEtatList() {
		return etatList;
	}

	public void setEtatList(List<Meteo_EtatPossible> etatList) {
		this.etatList = etatList;
	}

	public Integer getEtatToAdd() {
		return etatToAdd;
	}

	public void setEtatToAdd(Integer etatToAdd) {
		this.etatToAdd = etatToAdd;
	}

	public Integer getDeleteEtat() {
		return deleteEtat;
	}

	public void setDeleteEtat(Integer deleteEtat) {
		this.deleteEtat = deleteEtat;
	}

	public List<Map<String, String>> getListEtat() {
		return listEtat;
	}

	public void setListEtat(List<Map<String, String>> listEtat) {
		this.listEtat = listEtat;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		HttpServletRequest request = ServletActionContext.getRequest();
		etatList = EtatPossibleDatabaseService.getAll();
		
		//recupération des états à ajouter
		listEtat= new ArrayList<Map<String,String>>();
		getListEtatToAdd(request, listEtat);
		
		if((deleteEtat != null && deleteEtat != -1) || (etatToAdd != null && etatToAdd != -1)){
		
			//type = request.getParameter("type");
			
			//Si on a cliqué sur la suppression d'un état
			if(deleteEtat != null && deleteEtat != -1){
				Map<String, String> mapToRemove = null;
				for(Map<String, String> etat : listEtat){
					if(etat.get(ETAT_ID).equals(deleteEtat.toString())){
						mapToRemove = etat;
						break;
					}
				}
				if(mapToRemove != null)
					listEtat.remove(mapToRemove);
			}
			//si on a cliqué sur l'ajout d'un état, on l'ajoute aux états à ajouter
			else if(etatToAdd != null && etatToAdd != -1){
				for(Meteo_EtatPossible etat : etatList){
					if(etat.getId().equals(etatToAdd)){
						Map<String, String> etatMap = new HashMap<String, String>();
						etatMap.put(ETAT_ID, etatToAdd.toString());
						listEtat.add(etatMap);
						break;
					}
				}
			}
		}
		
		//suppression de la liste des état déjà affectés au type indicateur
		removeEtat(etatList, listEtat);
		
		return OK;
	}
	
	/**
	 * Suppression des états de arg1 présents dans arg2
	 * @param etatList
	 * @param listEtat
	 */
	public static void removeEtat(List<Meteo_EtatPossible> etatList, List<Map<String, String>> listEtat) {
		List<Meteo_EtatPossible> etatToRemove = new ArrayList<Meteo_EtatPossible>();
		for(Meteo_EtatPossible etat : etatList){
			for(Map<String, String> etatToAdd : listEtat){
				if(etat.getId().toString().equals(etatToAdd.get(ETAT_ID))){
					etatToAdd.put(ETAT_NAME, etat.getLibelle_etat());
					etatToAdd.put(ETAT_COULEUR, etat.getCouleur());
					etatToAdd.put(ETAT_DEFINITION, etat.getDefinition());
					etatToRemove.add(etat);
				}
			}
		}
		etatList.removeAll(etatToRemove);
	}

	/**
	 * Récupération des états que l'utilisateur veut ajouter
	 * @param request
	 * @param listEtat
	 */
	public static void getListEtatToAdd(HttpServletRequest request, List<Map<String, String>> listEtat) {
		int i = 0;
		while(request.getParameter("etat" + i) != null){
			Map<String, String> etat = new HashMap<String, String>();
			etat.put(ETAT_ID, request.getParameter("etat" + i));
			listEtat.add(etat);
			++i;
		}

	}

}
