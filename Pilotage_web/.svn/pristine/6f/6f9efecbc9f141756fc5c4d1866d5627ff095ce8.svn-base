package pilotage.meteo.service;

import java.util.List;

import net.sf.json.JSONObject;
import pilotage.database.filtre.FiltreDatabaseService;
import pilotage.database.meteo.MeteoServiceDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Filtre;
import pilotage.metier.Meteo_Service;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;
import pilotage.service.string.StringConverter;
import pilotage.utils.Pagination;

public class ShowServiceMeteoAction extends AbstractAction{

	private static final long serialVersionUID = -6233312579826477437L;

	private int page;
	private int nrPages;
	private int nrPerPage;

	private String filtreService;
	private String filtreString;
	
	private int validForm = 0;
	private String titrePage = "MTG_SRV";
	private Filtre filtre;
	private String filtreServiceBase;
	private JSONObject filtreJson;
	
	private String libelle;

	private Pagination<Meteo_Service> pagination;
	private List<Meteo_Service> services;
	
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

	public String getFiltreService() {
		return filtreService;
	}

	public void setFiltreService(String filtreService) {
		this.filtreService = filtreService;
	}

	public String getFiltreString() {
		return filtreString;
	}

	public void setFiltreString(String filtreString) {
		this.filtreString = filtreString;
	}

	public String getTitrePage() {
		return titrePage;
	}

	public void setTitrePage(String titrePage) {
		this.titrePage = titrePage;
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

	public Filtre getFiltre() {
		return filtre;
	}

	public void setFiltre(Filtre filtre) {
		this.filtre = filtre;
	}

	public String getFiltreServiceBase() {
		return filtreServiceBase;
	}

	public void setFiltreServiceBase(String filtreServiceBase) {
		this.filtreServiceBase = filtreServiceBase;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public Pagination<Meteo_Service> getPagination() {
		return pagination;
	}

	public void setPagination(Pagination<Meteo_Service> pagination) {
		this.pagination = pagination;
	}

	public List<Meteo_Service> getServices() {
		return services;
	}

	public void setServices(List<Meteo_Service> services) {
		this.services = services;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		try {
			if(error == null || error.length() == 0)
				libelle = null;
			
			//recuperation des services listes suivant les filtres
			if (page < 1)
				page = 1;
			else if (nrPages != 0 && page > nrPages)
				page = nrPages;
			if (nrPerPage==0)
				nrPerPage = PilotageConstants.NB_SERVICE_METEO_PER_PAGE;
			pagination = new Pagination<Meteo_Service>(page, nrPerPage);
			
			//gestion du filtre
			Users userLogged = (Users)session.get(PilotageConstants.USER_LOGGED);
			Integer userLoggedId = userLogged.getId();
			filtre = FiltreDatabaseService.getFiltre(userLoggedId,titrePage);
			
			if (filtre != null){
				try {
					Integer filtreId = filtre.getId();
					reloadFiltreBase(filtre.getFiltreString());
					services = MeteoServiceDatabaseService.getAll(pagination, filtreService);
					
					//Constitution du filtre sous forme de string
					filtreJson = ShowServiceMeteoAction.filtreToString(filtreService);
					
					if (validForm == 1){
						if (filtreServiceBase != filtreService){
							//Constitution du filtre sous forme de string
							filtreJson = ShowServiceMeteoAction.filtreToString(filtreService);
							if (filtreJson != null){
								FiltreDatabaseService.update(filtreId, filtreJson.toString());
								filtreString = StringConverter.afficheFiltre(filtreJson.toString());
							} else
								FiltreDatabaseService.update(filtreId, null);
						}
					}
				} catch (Exception e) {
					error = getText("error.message.generique") + " : " + e.getMessage();
					erreurLogger.error("Update de filtre services liste", e);
				}
			}
			else{
				try {
					services = MeteoServiceDatabaseService.getAll(pagination, filtreService);
					//Constitution du filtre sous forme de string
					filtreJson = ShowServiceMeteoAction.filtreToString(filtreService);
					if (validForm == 1){
						if (filtreService != null){
							FiltreDatabaseService.create(userLoggedId, titrePage, filtreJson != null ? filtreJson.toString() : null);
							filtreString = StringConverter.afficheFiltre(filtreJson.toString());
						}
					}
				} catch (Exception e) {
					error = getText("error.message.generique") + " : " + e.getMessage();
					erreurLogger.error("Creation de filtre services liste", e);
				}
			}
			return OK;

		}
		catch (Exception e) {
			error = getText("error.message.generique") + ":" + e.getMessage();
			erreurLogger.error("Affichage de la liste de services météo - " , e );
			return ERROR;
		}
	}
	
	public void reloadFiltreBase(String filtreString){
		if (filtreString != null){
			JSONObject jsonFiltre = JSONObject.fromObject(filtreString);
			if (jsonFiltre.containsKey("Nom")){
				setFiltreServiceBase(jsonFiltre.getString("Nom"));
			}
			
			if (validForm == 0){
				setFiltreService(filtreServiceBase);
			}
		}
	}
	
	/**
	 * Met les filtres sous forme string pour affichage
	 * @param filtreService 
	 */
	public static JSONObject filtreToString (String filtreService){
		StringBuffer buffer = new StringBuffer();
		if (filtreService!=null && !"".equals(filtreService)) {
			buffer.append(StringConverter.toJson("Nom", filtreService));
		}
		if (buffer.length() != 0 ) {
			String result = "{" + buffer.substring(0, buffer.lastIndexOf(PilotageConstants.SEPARATEUR_3)) + "}";
			JSONObject jsonFiltre = JSONObject.fromObject(result);
			return jsonFiltre;
		}
		else 
			return null;
	}

}
