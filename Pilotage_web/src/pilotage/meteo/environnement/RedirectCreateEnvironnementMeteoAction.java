package pilotage.meteo.environnement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import pilotage.database.meteo.MeteoCaisseDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Meteo_Caisse;

public class RedirectCreateEnvironnementMeteoAction extends AbstractAction{

	private static final long serialVersionUID = 3864838087210305322L;
	
	public static final String CAISSE_ID = "caisseID";
	public static final String CAISSE_NAME = "caisseName";
	private List<Meteo_Caisse> caisseList;
	private String nom_caisse;
	private Integer caisseToAdd;
	private Integer deleteCaisse;
	private String nom_envir;
	private String technique;
	private List<Map<String, String>> listCaisse;
	
	private int page;
	private int nrPages;
	private int nrPerPage;

	public List<Meteo_Caisse> getCaisseList() {
		return caisseList;
	}

	public void setCaisseList(List<Meteo_Caisse> caisseList) {
		this.caisseList = caisseList;
	}

	public String getNom_caisse() {
		return nom_caisse;
	}

	public void setNom_caisse(String nom_caisse) {
		this.nom_caisse = nom_caisse;
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

	public String getNom_envir() {
		return nom_envir;
	}

	public void setNom_envir(String nom_envir) {
		this.nom_envir = nom_envir;
	}

	public String getTechnique() {
		return technique;
	}

	public void setTechnique(String technique) {
		this.technique = technique;
	}

	public List<Map<String, String>> getListCaisse() {
		return listCaisse;
	}

	public void setListCaisse(List<Map<String, String>> listCaisse) {
		this.listCaisse = listCaisse;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getNrPages() {
		return nrPages;
	}

	public void setNrPages(int nrPages) {
		this.nrPages = nrPages;
	}

	public int getNrPerPage() {
		return nrPerPage;
	}

	public void setNrPerPage(int nrPerPage) {
		this.nrPerPage = nrPerPage;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		HttpServletRequest request = ServletActionContext.getRequest();
		caisseList = MeteoCaisseDatabaseService.getAll();
		technique = String.valueOf(0);
		
		//recupération des caisses à ajouter
		listCaisse= new ArrayList<Map<String,String>>();
		getListCaisseToAdd(request, listCaisse);
		
		if((deleteCaisse != null && deleteCaisse != -1) || (caisseToAdd != null && caisseToAdd != -1)){
		
			nom_envir = request.getParameter("nom_envir");
			technique = request.getParameter("technique");
			
			//Si on a cliqué sur la suppression d'une caisse
			if(deleteCaisse != null && deleteCaisse != -1){
				Map<String, String> mapToRemove = null;
				for(Map<String, String> caisse : listCaisse){
					if(caisse.get(CAISSE_ID).equals(deleteCaisse.toString())){
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
						caisseMap.put(CAISSE_ID, caisseToAdd.toString());
						listCaisse.add(caisseMap);
						break;
					}
				}
		}
		}
		
		//suppression de la liste des caisses déjà affecté à l'environnement
		removeCaisse(caisseList, listCaisse);
		
		return OK;
	}
	
	/**
	 * Suppression des caisses de arg1 présents dans arg2
	 * @param caisseList
	 * @param listCaisse
	 */
	public static void removeCaisse(List<Meteo_Caisse> caisseList, List<Map<String, String>> listCaisse) {
		List<Meteo_Caisse> caisseToRemove = new ArrayList<Meteo_Caisse>();
		for(Meteo_Caisse caisse : caisseList){
			for(Map<String, String> caisseToAdd : listCaisse){
				if(caisse.getId().toString().equals(caisseToAdd.get(CAISSE_ID))){
					caisseToAdd.put(CAISSE_NAME, caisse.getNomCaisse());
					caisseToRemove.add(caisse);
				}
			}
		}
		caisseList.removeAll(caisseToRemove);
	}

	/**
	 * Récupération des caisses que l'utilisateur veut ajouter
	 * @param request
	 * @param listCaisse
	 */
	public static void getListCaisseToAdd(HttpServletRequest request, List<Map<String, String>> listCaisse) {
		int i = 0;
		while(request.getParameter("caisse" + i) != null){
			Map<String, String> caisse = new HashMap<String, String>();
			caisse.put(CAISSE_ID, request.getParameter("caisse" + i));
			listCaisse.add(caisse);		
			++i;
		}
	}

}
