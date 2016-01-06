package pilotage.machines.actions.site;

import java.util.List;

import net.sf.json.JSONObject;
import pilotage.database.filtre.FiltreDatabaseService;
import pilotage.database.machine.MachineSiteDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Filtre;
import pilotage.metier.Machines_Site;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;
import pilotage.service.string.StringConverter;

public class ShowMachineSiteAction extends AbstractAction {
	
	private static final long serialVersionUID = -4037713125016637085L;
	
	private String sort;
	private String sens;
	
	private String filtreSite;
	private String filtreAdresse;
	private Integer filtreCP;
	private String filtreString;
	private JSONObject filtreJson;
	
	private int validForm = 0;
	private String titrePage = "MAC_SITE";
	private Filtre filtre;
	private String filtreSiteBase;
	private String filtreAdresseBase;
	private Integer filtreCPBase;
	
	private List<Machines_Site> listMachineSite;

	public JSONObject getFiltreJson() {
		return filtreJson;
	}

	public void setFiltreJson(JSONObject filtreJson) {
		this.filtreJson = filtreJson;
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

	public String getFiltreSite() {
		return filtreSite;
	}

	public void setFiltreSite(String filtreSite) {
		this.filtreSite = filtreSite;
	}

	public String getFiltreAdresse() {
		return filtreAdresse;
	}

	public void setFiltreAdresse(String filtreAdresse) {
		this.filtreAdresse = filtreAdresse;
	}

	public Integer getFiltreCP() {
		return filtreCP;
	}

	public void setFiltreCP(Integer filtreCP) {
		this.filtreCP = filtreCP;
	}

	public String getFiltreString() {
		return filtreString;
	}

	public void setFiltreString(String filtreString) {
		this.filtreString = filtreString;
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

	public String getFiltreSiteBase() {
		return filtreSiteBase;
	}

	public void setFiltreSiteBase(String filtreSiteBase) {
		this.filtreSiteBase = filtreSiteBase;
	}

	public String getFiltreAdresseBase() {
		return filtreAdresseBase;
	}

	public void setFiltreAdresseBase(String filtreAdresseBase) {
		this.filtreAdresseBase = filtreAdresseBase;
	}

	public Integer getFiltreCPBase() {
		return filtreCPBase;
	}

	public void setFiltreCPBase(Integer filtreCPBase) {
		this.filtreCPBase = filtreCPBase;
	}

	public List<Machines_Site> getListMachineSite() {
		return listMachineSite;
	}

	public void setListMachineSite(List<Machines_Site> listMachineSite) {
		this.listMachineSite = listMachineSite;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		if(sort == null || "".equals(sort))
			sort = "site";
		if(sens == null || "".equals(sens))
			sens = "asc";
		
		//gestion du filtre
		Users userLogged = (Users)session.get(PilotageConstants.USER_LOGGED);
		Integer userLoggedId = userLogged.getId();
		filtre = FiltreDatabaseService.getFiltre(userLoggedId,titrePage);
		
		if (filtre != null){
			try {
				Integer filtreId = filtre.getId();
				reloadFiltreBase(filtre.getFiltreString());
				listMachineSite = MachineSiteDatabaseService.getAll(sort, sens, filtreSite, filtreAdresse, filtreCP);
				filtreJson = filtreToString(filtreSite, filtreAdresse, filtreCP);
				
				if (validForm == 1){
					if (filtreSiteBase != filtreSite || filtreAdresseBase != filtreAdresse || filtreCPBase != filtreCP){
						filtreJson = filtreToString(filtreSite, filtreAdresse, filtreCP);
						FiltreDatabaseService.update(filtreId, filtreJson != null ? filtreJson.toString() : null);
					}
				}
			} catch (Exception e) {
				error = getText("error.message.generique") + " : " + e.getMessage();
				erreurLogger.error("Update de filtre machine site", e);
			}
		}
		else{
			try {
				listMachineSite = MachineSiteDatabaseService.getAll(sort, sens, filtreSite, filtreAdresse, filtreCP);
				filtreJson = filtreToString(filtreSite, filtreAdresse, filtreCP);
				if (validForm == 1){
					if (filtreSite != null || filtreAdresse != null || filtreCP != null){
						FiltreDatabaseService.create(userLoggedId, titrePage, filtreJson != null ? filtreJson.toString() : null);
					}
				}
			} catch (Exception e) {
				error = getText("error.message.generique") + " : " + e.getMessage();
				erreurLogger.error("Creation de filtre machine site", e);
			}
		}

		if(filtreSite != null)
			filtreSite = filtreSite.replaceAll("\\\\", "\\\\\\\\").replaceAll("'", "\\\\'");
		
		if(filtreAdresse != null)
			filtreAdresse = filtreAdresse.replaceAll("\\\\", "\\\\\\\\").replaceAll("'", "\\\\'");
		
		if (filtreJson != null)
			filtreString = StringConverter.afficheFiltre(filtreJson.toString());
		
		return OK;
	}
	
	public void reloadFiltreBase(String filtreString){
		if (filtreString != null){
			filtreJson = JSONObject.fromObject(filtreString);
			if (filtreJson.containsKey("Site")){
				setFiltreSiteBase(filtreJson.getString("Site"));
			}
			if (filtreJson.containsKey("Adresse")){
				setFiltreAdresseBase(filtreJson.getString("Adresse"));
			}
			if (filtreJson.containsKey("Code Postal")){
				setFiltreCPBase(Integer.parseInt(filtreJson.getString("Code Postal")));
			}
			
			if (validForm == 0){
				setFiltreSite(filtreSiteBase);
				setFiltreAdresse(filtreAdresseBase);
				setFiltreCP(filtreCPBase);
			}
		}
	}
	
	/**
	 * Met les filtres sous forme string pour affichage
	 * @param filtreSite
	 * @param filtreAdresse
	 * @param filtreCP
	 * @return
	 */
	public static JSONObject filtreToString(String filtreSite, String filtreAdresse, Integer filtreCP) {
		StringBuffer buffer = new StringBuffer();
		if (filtreSite != null && !filtreSite.equals("")) {
			buffer.append(StringConverter.toJson("Site", filtreSite));
		}
		if (filtreAdresse != null && !filtreAdresse.equals("")) {
			buffer.append(StringConverter.toJson("Adresse", filtreAdresse));
		}
		if (filtreCP != null && !filtreCP.equals("")) {
			buffer.append(StringConverter.toJson("Code Postal", filtreCP.toString()));
		}
		
		if(buffer.length() != 0){
			String result = "{" + buffer.substring(0, buffer.lastIndexOf(PilotageConstants.SEPARATEUR_3)) + "}";
			return JSONObject.fromObject(result);
		}
		else
			return null;
	}
}
