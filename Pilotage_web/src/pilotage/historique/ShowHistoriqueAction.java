package pilotage.historique;

import java.util.List;

import net.sf.json.JSONObject;

import pilotage.database.filtre.FiltreDatabaseService;
import pilotage.database.historique.HistoriqueDatabaseService;
import pilotage.database.users.management.UsersDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Filtre;
import pilotage.metier.Historique;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;
import pilotage.service.string.StringConverter;
import pilotage.utils.Pagination;

public class ShowHistoriqueAction extends AbstractAction {
	private static final long serialVersionUID = -4037713125016637085L;
	
	private String sort;
	private String sens;
	private int page;
	private int nrPages;
	private int nrPerPage;
	private String titrePage = "HIST";
	private int validForm = 0;

	private Pagination<Historique> pagination;
	private List<Historique> listHistorique;
	private Filtre filtre;
	private List<Users> listUsers;
	
	private String filtreAction;
	private Integer filtreUtilisateur;
	private String filtreModule;
	private String filtreString;
	private JSONObject filtreJson;
	
	private String filtreActionBase;
	private Integer filtreUtilisateurBase;
	private String filtreModuleBase;

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

	public Pagination<Historique> getPagination() {
		return pagination;
	}

	public void setPagination(Pagination<Historique> pagination) {
		this.pagination = pagination;
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
	
	public List<Historique> getListHistorique() {
		return listHistorique;
	}

	public void setListHistorique(List<Historique> listHistorique) {
		this.listHistorique = listHistorique;
	}

	public String getFiltreAction() {
		return filtreAction;
	}

	public void setFiltreAction(String filtreAction) {
		this.filtreAction = filtreAction;
	}

	public Integer getFiltreUtilisateur() {
		return filtreUtilisateur;
	}

	public void setFiltreUtilisateur(Integer filtreUtilisateur) {
		this.filtreUtilisateur = filtreUtilisateur;
	}

	public String getFiltreModule() {
		return filtreModule;
	}

	public void setFiltreModule(String filtreModule) {
		this.filtreModule = filtreModule;
	}

	public String getFiltreString() {
		return filtreString;
	}

	public void setFiltreString(String filtreString) {
		this.filtreString = filtreString;
	}

	public String getFiltreActionBase() {
		return filtreActionBase;
	}

	public void setFiltreActionBase(String filtreActionBase) {
		this.filtreActionBase = filtreActionBase;
	}

	public Integer getFiltreUtilisateurBase() {
		return filtreUtilisateurBase;
	}

	public void setFiltreUtilisateurBase(Integer filtreUtilisateurBase) {
		this.filtreUtilisateurBase = filtreUtilisateurBase;
	}

	public String getFiltreModuleBase() {
		return filtreModuleBase;
	}

	public void setFiltreModuleBase(String filtreModuleBase) {
		this.filtreModuleBase = filtreModuleBase;
	}
	
	public List<Users> getListUsers() {
		return listUsers;
	}

	public void setListUsers(List<Users> listUsers) {
		this.listUsers = listUsers;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {		
		//recuperation des users suivant les filtres, le tri et la page
		if(page < 1)
			page = 1;
		else if(nrPages != 0 && page > nrPages)
			page = nrPages;
		if(nrPerPage == 0)
			nrPerPage = 20;
		if(sens == null || "".equals(sens))
			sens = "desc";
		if(sort == null || "".equals(sort))
			sort = "dateAction";
		pagination = new Pagination<Historique>(page, nrPerPage);
		//listes utiles aux filtres
		listUsers = UsersDatabaseService.getAll();
		Users userLogged = (Users)session.get(PilotageConstants.USER_LOGGED);
		Integer userLoggedId = userLogged.getId();
		filtre = FiltreDatabaseService.getFiltre(userLoggedId,titrePage);
		if (filtre != null){
			try {
				Integer filtreId = filtre.getId();
				reloadFiltreBase(filtre.getFiltreString());
				listHistorique = HistoriqueDatabaseService.getAll(pagination, sort, sens, filtreAction, filtreUtilisateur, filtreModule);							
				filtreJson = filtreToString(filtreAction, filtreUtilisateur, filtreModule, listUsers);
				
				if (validForm == 1){
					if (filtreActionBase != filtreAction || filtreUtilisateurBase != filtreUtilisateur || filtreModuleBase != filtreModule){
						filtreJson = filtreToString(filtreAction, filtreUtilisateur, filtreModule, listUsers);	
						FiltreDatabaseService.update(filtreId, filtreJson != null ? filtreJson.toString() : null);
					}
				}				
			} catch (Exception e) {
				error = getText("error.message.generique") + " : " + e.getMessage();
				erreurLogger.error("Update de filtre historique", e);
			}
		}
		else{
			try {
				listHistorique = HistoriqueDatabaseService.getAll(pagination, sort, sens, filtreAction, filtreUtilisateur, filtreModule);
				filtreJson = filtreToString(filtreAction, filtreUtilisateur, filtreModule, listUsers);
				if (validForm == 1){
					if (filtreAction != null || filtreUtilisateur != null || filtreModule != null){
						FiltreDatabaseService.create(userLoggedId, titrePage, filtreJson != null ? filtreJson.toString() : null);
					}
				}	
			} catch (Exception e) {
				error = getText("error.message.generique") + " : " + e.getMessage();
				erreurLogger.error("Creation de filtre historique", e);
			}
			
		}
		if (filtreJson != null)
			filtreString = StringConverter.afficheFiltre(filtreJson.toString());
		
		return OK;
	}
	
	/**
	 * Met le filtre sous forme de string pour affichage
	 * @param filtreAction
	 * @param filtreUtilisateur
	 * @param filtreModule
	 * @param listUsers
	 * @return
	 */
	public static /*String*/ JSONObject filtreToString(String filtreAction, Integer filtreUtilisateur, String filtreModule, List<Users> listUsers) {
		StringBuffer buffer = new StringBuffer();
		if (filtreAction != null && !filtreAction.equals("")) {
			buffer.append(StringConverter.toJson("Action", filtreAction));
		}
		if (filtreUtilisateur != null && filtreUtilisateur != -1) {
			for(Users users : listUsers){
				if(users.getId().equals(filtreUtilisateur)){
					buffer.append(StringConverter.toJson("Utilisateur", users.getPrenom() + " " + users.getNom()));
					break;
				}
			}
		}
		if (filtreModule != null && !filtreModule.equals("")) {
			buffer.append(StringConverter.toJson("Module", filtreModule));
		}
		if(buffer.length() != 0) {
			String result = "{" + buffer.substring(0, buffer.lastIndexOf(PilotageConstants.SEPARATEUR_3)) + "}";
			return JSONObject.fromObject(result) ;
		}
		else {
			return null;
		}
	}
	
	public void reloadFiltreBase(String filtreString){
		if (filtreString != null){
			filtreJson = JSONObject.fromObject(filtreString);
			if (filtreJson.containsKey("Action")){
				setFiltreActionBase(filtreJson.getString("Action"));
			}
			if (filtreJson.containsKey("Utilisateur")){
				for(Users users : listUsers){
					String nom = users.getNom();
					String prenom = users.getPrenom();
					if (filtreJson.getString("Utilisateur").equalsIgnoreCase(prenom+" "+nom)){
						setFiltreUtilisateurBase(users.getId());
					}
				}
			}
			if (filtreJson.containsKey("Module")){
				setFiltreModuleBase(filtreJson.getString("Module"));
			}
			if (validForm == 0){
				setFiltreAction(filtreActionBase);
				setFiltreUtilisateur(filtreUtilisateurBase);
				setFiltreModule(filtreModuleBase);
			}
		}
	}
}
