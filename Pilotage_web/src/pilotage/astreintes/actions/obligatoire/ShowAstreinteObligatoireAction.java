package pilotage.astreintes.actions.obligatoire;

import java.util.List;

import net.sf.json.JSONObject;
import pilotage.database.astreintes.AstreinteDomaineDatabaseService;
import pilotage.database.astreintes.AstreinteObligatoireDatabaseService;
import pilotage.database.astreintes.AstreinteTypeDatabaseService;
import pilotage.database.filtre.FiltreDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Astreinte_Domaine;
import pilotage.metier.Astreinte_Obligatoire;
import pilotage.metier.Astreinte_Type;
import pilotage.metier.Filtre;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;
import pilotage.service.string.StringConverter;

public class ShowAstreinteObligatoireAction extends AbstractAction{

	private static final long serialVersionUID = -3133755362464233898L;

	private String sort;
	private String sens;
	
	private Integer filtreDomaine;
	private Integer filtreType;
	private String filtreIndicEnvoi;
	private String filtreString;
	private JSONObject filtreJson;
	private int validForm = 0;
	private String titrePage = "AST_O_LST";
	private Filtre filtre;
	private Integer filtreDomaineBase;
	private Integer filtreTypeBase;
	private String filtreIndicEnvoiBase;

	private List<Astreinte_Obligatoire> aObligatoires;
	private List<Astreinte_Domaine> aDomaines;
	private List<Astreinte_Type> aTypes;
	
	public List<Astreinte_Domaine> getADomaines() {
		return aDomaines;
	}

	public void setADomaines(List<Astreinte_Domaine> aDomaines) {
		this.aDomaines = aDomaines;
	}

	public List<Astreinte_Type> getATypes() {
		return aTypes;
	}

	public void setATypes(List<Astreinte_Type> aTypes) {
		this.aTypes = aTypes;
	}

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

	public Integer getFiltreDomaine() {
		return filtreDomaine;
	}

	public void setFiltreDomaine(Integer filtreDomaine) {
		this.filtreDomaine = filtreDomaine;
	}

	public Integer getFiltreType() {
		return filtreType;
	}

	public void setFiltreType(Integer filtreType) {
		this.filtreType = filtreType;
	}

	public String getFiltreIndicEnvoi() {
		return filtreIndicEnvoi;
	}

	public void setFiltreIndicEnvoi(String filtreIndicEnvoi) {
		this.filtreIndicEnvoi = filtreIndicEnvoi;
	}

	public String getFiltreString() {
		return filtreString;
	}

	public void setFiltreString(String filtreString) {
		this.filtreString = filtreString;
	}
	
	public JSONObject getFiltreJson() {
		return filtreJson;
	}

	public void setFiltreJson(JSONObject filtreJson) {
		this.filtreJson = filtreJson;
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

	public Integer getFiltreDomaineBase() {
		return filtreDomaineBase;
	}

	public void setFiltreDomaineBase(Integer filtreDomaineBase) {
		this.filtreDomaineBase = filtreDomaineBase;
	}

	public Integer getFiltreTypeBase() {
		return filtreTypeBase;
	}

	public void setFiltreTypeBase(Integer filtreTypeBase) {
		this.filtreTypeBase = filtreTypeBase;
	}

	public String getFiltreIndicEnvoiBase() {
		return filtreIndicEnvoiBase;
	}

	public void setFiltreIndicEnvoiBase(String filtreIndicEnvoiBase) {
		this.filtreIndicEnvoiBase = filtreIndicEnvoiBase;
	}
	
	public List<Astreinte_Obligatoire> getAObligatoires() {
		return aObligatoires;
	}

	public void setAObligatoires(List<Astreinte_Obligatoire> aObligatoires) {
		this.aObligatoires = aObligatoires;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		
		try {
			if(sens == null)
				sens = "asc";
			if(sort == null)
				sort = "domaine";
			
			//récupération des types et des domaines pour les filtres
			aDomaines = AstreinteDomaineDatabaseService.getAll();
			aTypes = AstreinteTypeDatabaseService.getAll();
			
			//gestion du filtre
			Users userLogged = (Users)session.get(PilotageConstants.USER_LOGGED);
			Integer userLoggedId = userLogged.getId();
			filtre = FiltreDatabaseService.getFiltre(userLoggedId,titrePage);
			
			if (filtre != null){
				try {
					Integer filtreId = filtre.getId();
					reloadFiltreBase(filtre.getFiltreString());
					aObligatoires = AstreinteObligatoireDatabaseService.getAll(sort, sens, filtreDomaine, filtreType, filtreIndicEnvoi);
					//Constitution du filtre sous forme de string
					filtreJson = ShowAstreinteObligatoireAction.filtreToString(filtreDomaine, filtreType, filtreIndicEnvoi);
					
					if (validForm == 1){
						if (filtreDomaineBase != filtreDomaine || filtreTypeBase != filtreType || filtreIndicEnvoiBase != filtreIndicEnvoi){
							//Constitution du filtre sous forme de string
							filtreJson = ShowAstreinteObligatoireAction.filtreToString(filtreDomaine, filtreType, filtreIndicEnvoi);
							FiltreDatabaseService.update(filtreId, filtreJson != null ? filtreJson.toString() : null);
						}
					}
				} catch (Exception e) {
					error = getText("error.message.generique") + " : " + e.getMessage();
					erreurLogger.error("Update de filtre astreinte obligatoire", e);
				}
			}
			else{
				try {
					aObligatoires = AstreinteObligatoireDatabaseService.getAll(sort, sens, filtreDomaine, filtreType, filtreIndicEnvoi);
					//Constitution du filtre sous forme de string
					filtreJson = ShowAstreinteObligatoireAction.filtreToString(filtreDomaine, filtreType, filtreIndicEnvoi);
					if (validForm == 1){
						if (filtreDomaine != null || filtreType != null || filtreIndicEnvoi != null){
							FiltreDatabaseService.create(userLoggedId, titrePage, filtreJson != null ? filtreJson.toString() : null);
						}
					}
				} catch (Exception e) {
					error = getText("error.message.generique") + " : " + e.getMessage();
					erreurLogger.error("Creation de filtre astreinte obligatoire", e);
				}
			}
			
			return OK;
			
		} catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Affichage de la liste des astreintes obligatoires - ", e);
			return ERROR;
		}
		
	}
	
	public void reloadFiltreBase(String filtreString){
		if (filtreString != null){
			filtreJson = JSONObject.fromObject(filtreString);
			if (filtreJson.containsKey("Domaine")){
				for(Astreinte_Domaine aDomaine : aDomaines){
					if(aDomaine.getDomaine().equalsIgnoreCase(filtreJson.getString("Domaine"))){
						setFiltreDomaineBase(aDomaine.getId());
					}
				}
			}
			if (filtreJson.containsKey("filtreType")){
				for(Astreinte_Type aType : aTypes){
					if(aType.getType().equalsIgnoreCase(filtreJson.getString("filtreType"))){
						setFiltreTypeBase(aType.getId());
					}
				}
			}
			if (filtreJson.containsKey("IndicEnvoi")){
				setFiltreIndicEnvoiBase(filtreJson.getString("IndicEnvoi"));
			}
			if (validForm == 0){
				setFiltreDomaine(filtreDomaineBase);
				setFiltreType(filtreTypeBase);
				setFiltreIndicEnvoi(filtreIndicEnvoiBase);
			}
		}
	}
	
	/**
	 * Met les filtres sous forme string pour affichage
	 * @param filtreDomaine
	 * @param filtreType
	 * @param filtreIndicEnvoi
	 * @return
	 */
	public static JSONObject filtreToString(Integer filtreDomaine, Integer filtreType, String filtreIndicEnvoi) {
		StringBuffer buffer = new StringBuffer();
		if (filtreDomaine != null && filtreDomaine > 0) {
			buffer.append(StringConverter.toJson("Domaine", AstreinteDomaineDatabaseService.get(filtreDomaine).getDomaine()));
		}
		if (filtreType!= null && filtreType > 0) {
			buffer.append(StringConverter.toJson("Type", AstreinteTypeDatabaseService.get(filtreType).getType()));
		}
		if (filtreIndicEnvoi != null && !filtreIndicEnvoi.equals("")) {
			buffer.append(StringConverter.toJson("IndicEnvoi", filtreIndicEnvoi));
		}
		if(buffer.length() != 0){
			String result = "{" + buffer.substring(0, buffer.lastIndexOf(PilotageConstants.SEPARATEUR_3)) + "}";
			return JSONObject.fromObject(result);
		} 
		 else
			return null;
	}
}
