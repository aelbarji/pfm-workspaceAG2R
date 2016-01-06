package pilotage.astreintes.actions.astreinte;

import java.util.List;

import net.sf.json.JSONObject;
import pilotage.database.astreintes.AstreinteDatabaseService;
import pilotage.database.astreintes.AstreinteTypeDatabaseService;
import pilotage.database.filtre.FiltreDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Astreinte;
import pilotage.metier.Astreinte_Type;
import pilotage.metier.Filtre;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;
import pilotage.service.string.StringConverter;
import pilotage.utils.Pagination;

public class ShowAstreintesAction extends AbstractAction{

	private static final long serialVersionUID = 4918087969870567460L;

	private String sort;
	private String sens;
	private int page;
	private int nrPages;
	private int nrPerPage;
	
	private String filtreNom;
	private String filtrePrenom;
	private String filtreTel1;
	private String filtreTel2;
	private Integer filtreType;
	private String filtreString;
	private JSONObject filtreJson;
	private String filtreNomBase;
	private String filtrePrenomBase;
	private String filtreTel1Base;
	private String filtreTel2Base;
	private Integer filtreTypeBase;
	private int validForm = 0;
	private String titrePage = "AST_LST";
	private Filtre filtre;
	
	private Pagination<Astreinte> pagination;
	private List<Astreinte> astreintes;
    private List<Astreinte_Type> aTypes;
	
	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getSens() {
		return sens;
	}

	public void setSens(String sens) {
		this.sens = sens;
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

	public String getFiltreNom() {
		return filtreNom;
	}

	public void setFiltreNom(String filtreNom) {
		this.filtreNom = filtreNom;
	}

	public String getFiltrePrenom() {
		return filtrePrenom;
	}

	public void setFiltrePrenom(String filtrePrenom) {
		this.filtrePrenom = filtrePrenom;
	}

	public String getFiltreTel1() {
		return filtreTel1;
	}

	public void setFiltreTel1(String filtreTel1) {
		this.filtreTel1 = filtreTel1;
	}

	public String getFiltreTel2() {
		return filtreTel2;
	}

	public void setFiltreTel2(String filtreTel2) {
		this.filtreTel2 = filtreTel2;
	}

	public Integer getFiltreType() {
		return filtreType;
	}

	public void setFiltreType(Integer filtreType) {
		this.filtreType = filtreType;
	}
	
	public String getFiltreString() {
		return filtreString;
	}

	public void setFiltreString(String filtreString) {
		this.filtreString = filtreString;
	}

	public String getFiltreNomBase() {
		return filtreNomBase;
	}

	public void setFiltreNomBase(String filtreNomBase) {
		this.filtreNomBase = filtreNomBase;
	}

	public String getFiltrePrenomBase() {
		return filtrePrenomBase;
	}

	public void setFiltrePrenomBase(String filtrePrenomBase) {
		this.filtrePrenomBase = filtrePrenomBase;
	}

	public String getFiltreTel1Base() {
		return filtreTel1Base;
	}

	public void setFiltreTel1Base(String filtreTel1Base) {
		this.filtreTel1Base = filtreTel1Base;
	}

	public String getFiltreTel2Base() {
		return filtreTel2Base;
	}

	public void setFiltreTel2Base(String filtreTel2Base) {
		this.filtreTel2Base = filtreTel2Base;
	}

	public Integer getFiltreTypeBase() {
		return filtreTypeBase;
	}

	public void setFiltreTypeBase(Integer filtreTypeBase) {
		this.filtreTypeBase = filtreTypeBase;
	}

	public int getValidForm() {
		return validForm;
	}

	public void setValidForm(int validForm) {
		this.validForm = validForm;
	}

	public String getTitrePage() {
		return titrePage;
	}

	public void setTitrePage(String titrePage) {
		this.titrePage = titrePage;
	}

	public Filtre getFiltre() {
		return filtre;
	}

	public void setFiltre(Filtre filtre) {
		this.filtre = filtre;
	}

	public JSONObject getFiltreJson() {
		return filtreJson;
	}

	public void setFiltreJson(JSONObject filtreJson) {
		this.filtreJson = filtreJson;
	}

	public Pagination<Astreinte> getPagination() {
		return pagination;
	}

	public void setPagination(Pagination<Astreinte> pagination) {
		this.pagination = pagination;
	}

	public List<Astreinte> getAstreintes() {
		return astreintes;
	}

	public void setAstreintes(List<Astreinte> astreintes) {
		this.astreintes = astreintes;
	}

	public List<Astreinte_Type> getATypes() {
		return aTypes;
	}

	public void setATypes(List<Astreinte_Type> aTypes) {
		this.aTypes = aTypes;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		
		try {
			//recuperation des astreintes suivant les filtres, le tri et la page
			if(page < 1)
				page = 1;
			else if(nrPages != 0 && page > nrPages)
				page = nrPages;
			if(nrPerPage == 0)
				nrPerPage = PilotageConstants.NB_ASTREINTES_PER_PAGE;
			if(sens == null || "".equals(sens))
				sens = "asc";
			if(sort == null || "".equals(sort))
				sort = "nom";
			pagination = new Pagination<Astreinte>(page, nrPerPage);
			//récupération des types pour les filtres
			aTypes = AstreinteTypeDatabaseService.getAll();
			//gestion du filtre
			Users userLogged = (Users)session.get(PilotageConstants.USER_LOGGED);
			Integer userLoggedId = userLogged.getId();
			filtre = FiltreDatabaseService.getFiltre(userLoggedId,titrePage);
			
			if (filtre != null){
				try {
					Integer filtreId = filtre.getId();
					reloadFiltreBase(filtre.getFiltreString());
					astreintes = AstreinteDatabaseService.getAllActifs(pagination, sort, sens, filtreNom, filtrePrenom, filtreTel1, filtreTel2, filtreType);
					
					//Constitution du filtre sous forme de string
					filtreJson = ShowAstreintesAction.filtreToString(filtreNom, filtrePrenom, filtreTel1, filtreTel2, filtreType);
					
					if (validForm == 1){
						if (filtreNomBase != filtreNom || filtrePrenomBase != filtrePrenom || filtreTel1Base != filtreTel1 || filtreTel2Base != filtreTel2 || filtreTypeBase != filtreType){
							//Constitution du filtre sous forme de string
							filtreJson = ShowAstreintesAction.filtreToString(filtreNom, filtrePrenom, filtreTel1, filtreTel2, filtreType);
							if (filtreJson != null){
								FiltreDatabaseService.update(filtreId, filtreJson.toString());
								filtreString = StringConverter.afficheFiltre(filtreJson.toString());
							} else
								FiltreDatabaseService.update(filtreId, null);
						}
					}
				} catch (Exception e) {
					error = getText("error.message.generique") + " : " + e.getMessage();
					erreurLogger.error("Update de filtre astreinte", e);
				}
			}
			else{
				try {
					astreintes = AstreinteDatabaseService.getAllActifs(pagination, sort, sens, filtreNom, filtrePrenom, filtreTel1, filtreTel2, filtreType);
					
					//Constitution du filtre sous forme de string
					filtreJson = ShowAstreintesAction.filtreToString(filtreNom, filtrePrenom, filtreTel1, filtreTel2, filtreType);
					if (validForm == 1){
						if (filtreNom != null || filtrePrenom != null || filtreTel1 != null || filtreTel2 != null || filtreType != null){
							FiltreDatabaseService.create(userLoggedId, titrePage, filtreJson != null ? filtreJson.toString() : null);
							filtreString = StringConverter.afficheFiltre(filtreJson.toString());
						}
					}
				} catch (Exception e) {
					error = getText("error.message.generique") + " : " + e.getMessage();
					erreurLogger.error("Creation de filtre astreinte", e);
				}
			}
			return OK;
		} catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Affichage de la liste des astreintes - ", e);
			return ERROR;	
		}
	}
	
	public void reloadFiltreBase(String filtreString){
		if (filtreString != null){
			JSONObject jsonFiltre = JSONObject.fromObject(filtreString);
			if (jsonFiltre.containsKey("Nom")){
				setFiltreNomBase(jsonFiltre.getString("Nom"));
			}
			if (jsonFiltre.containsKey("Prénom")){
				setFiltrePrenomBase(jsonFiltre.getString("Prénom"));
			}
			if (jsonFiltre.containsKey("Tel1")){  // filtreTel1 changé
				setFiltreTel1Base(jsonFiltre.getString("Tel1"));
			}
			if (jsonFiltre.containsKey("Tel2")){  // filtreTel2 changé
				setFiltreTel2Base(jsonFiltre.getString("Tel2"));
			}
			if (jsonFiltre.containsKey("Type")){  // filtreType changé
				for(Astreinte_Type aType : aTypes){
					if(aType.getType().equalsIgnoreCase(jsonFiltre.getString("Type"))){	// filtreType changé
						setFiltreTypeBase(aType.getId());
					}
				}
			}
			
			if (validForm == 0){
				setFiltreNom(filtreNomBase);
				setFiltrePrenom(filtrePrenomBase);
				setFiltreTel1(filtreTel1Base);
				setFiltreTel2(filtreTel2Base);
				setFiltreType(filtreTypeBase);
			}
		}
	}
	
	/**
	 * Met les filtres sous forme string pour affichage
	 * @param filtreNom
	 * @param filtrePrenom
	 * @param filtreTel1
	 * @param filtreTel2
	 * @param filtreType
	 * @return
	 */
	public static /*String*/ JSONObject filtreToString (String filtreNom, String filtrePrenom, String filtreTel1, String filtreTel2, Integer filtreType) {
		StringBuffer buffer = new StringBuffer();
		if (filtreNom != null && !filtreNom.equals("")) {
			buffer.append(StringConverter.toJson("Nom", filtreNom));
		}
		if (filtrePrenom!= null && !filtrePrenom.equals("")) {
			buffer.append(StringConverter.toJson("Prénom", filtrePrenom));
		}
		if (filtreTel1 != null && !filtreTel1.equals("")) {
			buffer.append(StringConverter.toJson("Tel1", filtreTel1));
		}
		if (filtreTel2 != null && !filtreTel2.equals("")) {
			buffer.append(StringConverter.toJson("Tel2", filtreTel2));
		}
		if (filtreType != null && filtreType >= 0) {
			buffer.append(StringConverter.toJson("Type", AstreinteTypeDatabaseService.get(filtreType).getType()));
		}
		
		if(buffer.length() != 0){
			String result = "{" + buffer.substring(0, buffer.lastIndexOf(PilotageConstants.SEPARATEUR_3)) + "}";
			JSONObject jsonFiltre = JSONObject.fromObject(result);
			return jsonFiltre;
		} else
			return null;
	}
}
