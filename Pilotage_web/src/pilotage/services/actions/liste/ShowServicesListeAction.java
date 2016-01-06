package pilotage.services.actions.liste;

import java.util.List;

import net.sf.json.JSONObject;

import pilotage.database.filtre.FiltreDatabaseService;
import pilotage.database.services.ServicesDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Filtre;
import pilotage.metier.Services_Liste;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;
import pilotage.service.string.StringConverter;
import pilotage.utils.Pagination;

public class ShowServicesListeAction extends AbstractAction {

	private static final long serialVersionUID = -2459840438540126688L;

	private int page;
	private int nrPages;
	private int nrPerPage;

	private String filtreNomService;
	private String filtreString;
	private JSONObject filtreJson;
	
	private int validForm = 0;
	private String titrePage = "SRV_LST";
	private Filtre filtre;
	private String filtreNomServiceBase;
	
	private String libelle;

	private Pagination<Services_Liste> pagination;
	private List<Services_Liste> services_Listes;

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

	public String getFiltreNomService() {
		return filtreNomService;
	}

	public void setFiltreNomService(String filtreNomService) {
		this.filtreNomService = filtreNomService;
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

	public String getFiltreNomServiceBase() {
		return filtreNomServiceBase;
	}

	public void setFiltreNomServiceBase(String filtreNomServiceBase) {
		this.filtreNomServiceBase = filtreNomServiceBase;
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

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public Pagination<Services_Liste> getPagination() {
		return pagination;
	}

	public void setPagination(Pagination<Services_Liste> pagination) {
		this.pagination = pagination;
	}

	public List<Services_Liste> getServices_Listes() {
		return services_Listes;
	}

	public void setServices_Listes(List<Services_Liste> services_Listes) {
		this.services_Listes = services_Listes;
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
				nrPerPage = PilotageConstants.NB_SERVICES_LISTE_PER_PAGE;
			pagination = new Pagination<Services_Liste>(page, nrPerPage);
			
			//gestion du filtre
			Users userLogged = (Users)session.get(PilotageConstants.USER_LOGGED);
			Integer userLoggedId = userLogged.getId();
			filtre = FiltreDatabaseService.getFiltre(userLoggedId,titrePage);
			
			if (filtre != null){
				try {
					Integer filtreId = filtre.getId();
					reloadFiltreBase(filtre.getFiltreString());
					services_Listes = ServicesDatabaseService.getAll(pagination, filtreNomService);
					//Constitution du filtre sous forme de string
					filtreJson = ShowServicesListeAction.filtreToString(filtreNomService);
					
					if (validForm == 1){
						if (filtreNomServiceBase != filtreNomService){
							//Constitution du filtre sous forme de string
							filtreJson = ShowServicesListeAction.filtreToString(filtreNomService);
							FiltreDatabaseService.update(filtreId, filtreJson != null ? filtreJson.toString() : null);
						}
					}
				} catch (Exception e) {
					error = getText("error.message.generique") + " : " + e.getMessage();
					erreurLogger.error("Update de filtre services liste", e);
				}
			}
			else{
				try {
					services_Listes = ServicesDatabaseService.getAll(pagination, filtreNomService);
					//Constitution du filtre sous forme de string
					filtreJson = ShowServicesListeAction.filtreToString(filtreNomService);
					if (validForm == 1){
						if (filtreNomService != null){
							FiltreDatabaseService.create(userLoggedId, titrePage, filtreJson != null ? filtreJson.toString() : null);
						}
					}
				} catch (Exception e) {
					error = getText("error.message.generique") + " : " + e.getMessage();
					erreurLogger.error("Creation de filtre services liste", e);
				}
			}
			
			if (filtreJson != null)
				filtreString = StringConverter.afficheFiltre(filtreJson.toString());
			
			return OK;

		}
		catch (Exception e) {
			error = getText("error.message.generique") + ":" + e.getMessage();
			erreurLogger.error("Affichage de la liste de services - " , e );
			return ERROR;
		}
	}

	public void reloadFiltreBase(String filtreString){
		if (filtreString != null){
			filtreJson = JSONObject.fromObject(filtreString);
			if (filtreJson.containsKey("Nom")){
				setFiltreNomServiceBase(filtreJson.getString("Nom"));
			}
			
			if (validForm == 0){
				setFiltreNomService(filtreNomServiceBase);
			}
		}
	}
	
	/**
	 * Met les filtres sous forme string pour affichage
	 * @param filtreNomService 
	 */
	public static JSONObject filtreToString (String filtreNomService){
		StringBuffer buffer = new StringBuffer();
		if (filtreNomService!=null && !"".equals(filtreNomService)) {
			buffer.append(StringConverter.toJson("Nom", filtreNomService));
		}
		if (buffer.length() != 0 ) {
			String result = "{" + buffer.substring(0, buffer.lastIndexOf(PilotageConstants.SEPARATEUR_3)) + "}";
			return JSONObject.fromObject(result);
		}
		else 
			return null;
	}
}
