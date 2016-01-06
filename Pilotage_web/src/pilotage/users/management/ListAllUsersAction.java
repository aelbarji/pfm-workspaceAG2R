package pilotage.users.management;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;
import pilotage.admin.metier.Profil;
import pilotage.database.admin.ProfilDatabaseService;
import pilotage.database.filtre.FiltreDatabaseService;
import pilotage.database.users.management.UsersDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Filtre;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;
import pilotage.service.string.StringConverter;
import pilotage.utils.Pagination;

public class ListAllUsersAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	
	private String sort;
	private String sens;
	private int page;
	private int nrPages;
	private int nrPerPage;
	
	private String filtreNom;
	private String filtreLogin;
	private String filtreProfil;
	private String filtrePrenom;
	private String filtreEmail;
	private String filtreString;
	private JSONObject filtreJson;
	private String filtreNomBase;
	private String filtreLoginBase;
	private String filtreProfilBase;
	private String filtrePrenomBase;
	private String filtreEmailBase;
	private int validForm = 0;
	private String titrePage = "USR_LST";
	private Filtre filtre;
	
	private Pagination<Users> pagination;
	private List<Users> users;
	private Map<Users,Profil> upMap;
	private List<Profil> listProfil;

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
	
	public String getFiltreNom() {
		return filtreNom;
	}

	public void setFiltreNom(String filtreNom) {
		this.filtreNom = filtreNom;
	}

	public String getFiltreLogin() {
		return filtreLogin;
	}

	public void setFiltreLogin(String filtreLogin) {
		this.filtreLogin = filtreLogin;
	}

	public String getFiltreProfil() {
		return filtreProfil;
	}

	public void setFiltreProfil(String filtreProfil) {
		this.filtreProfil = filtreProfil;
	}

	public String getFiltrePrenom() {
		return filtrePrenom;
	}

	public void setFiltrePrenom(String filtrePrenom) {
		this.filtrePrenom = filtrePrenom;
	}

	public String getFiltreEmail() {
		return filtreEmail;
	}

	public void setFiltreEmail(String filtreEmail) {
		this.filtreEmail = filtreEmail;
	}
	
	public String getFiltreNomBase() {
		return filtreNomBase;
	}

	public void setFiltreNomBase(String filtreNomBase) {
		this.filtreNomBase = filtreNomBase;
	}

	public String getFiltreLoginBase() {
		return filtreLoginBase;
	}

	public void setFiltreLoginBase(String filtreLoginBase) {
		this.filtreLoginBase = filtreLoginBase;
	}

	public String getFiltreProfilBase() {
		return filtreProfilBase;
	}

	public void setFiltreProfilBase(String filtreProfilBase) {
		this.filtreProfilBase = filtreProfilBase;
	}

	public String getFiltrePrenomBase() {
		return filtrePrenomBase;
	}

	public void setFiltrePrenomBase(String filtrePrenomBase) {
		this.filtrePrenomBase = filtrePrenomBase;
	}

	public String getFiltreEmailBase() {
		return filtreEmailBase;
	}

	public void setFiltreEmailBase(String filtreEmailBase) {
		this.filtreEmailBase = filtreEmailBase;
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

	public Pagination<Users> getPagination() {
		return pagination;
	}

	public void setPagination(Pagination<Users> pagination) {
		this.pagination = pagination;
	}
	
	public Map<Users, Profil> getUpMap() {
		return upMap;
	}

	public void setUpMap(Map<Users, Profil> upMap) {
		this.upMap = upMap;
	}
	
	public List<Users> getUsers() {
		return users;
	}

	public void setUsers(List<Users> users) {
		this.users = users;
	}
	
	public List<Profil> getListProfil() {
		return listProfil;
	}

	public void setListProfil(List<Profil> listProfil) {
		this.listProfil = listProfil;
	}
	
	public String getFiltreString() {
		return filtreString;
	}

	public void setFiltreString(String filtreString) {
		this.filtreString = filtreString;
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
		try{
			//recuperation des users suivant les filtres, le tri et la page
			if(page < 1)
				page = 1;
			else if(nrPages != 0 && page > nrPages)
				page = nrPages;
			if(nrPerPage == 0)
				nrPerPage = PilotageConstants.NB_USERS_PER_PAGE;
			if(sens == null || "".equals(sens))
				sens = "asc";
			if(sort == null || "".equals(sort))
				sort = "nom";
			pagination = new Pagination<Users>(page, nrPerPage);
			
			//gestion du filtre
			Users userLogged = (Users)session.get(PilotageConstants.USER_LOGGED);
			Integer userLoggedId = userLogged.getId();
			filtre = FiltreDatabaseService.getFiltre(userLoggedId,titrePage);
			//Récupération de la liste des profils pour le filtre
			listProfil = ProfilDatabaseService.getAll();
			
			if (filtre != null){
				try {
					Integer filtreId = filtre.getId();
					reloadFiltreBase(filtre.getFiltreString());
					users = UsersDatabaseService.getAllUsers(pagination, sort, sens, filtreNom, filtrePrenom, filtreLogin, filtreEmail, filtreProfil);	
					//attribution du profil pour chaque user
					upMap = new LinkedHashMap<Users, Profil>();
					for(Users user : users){
						Profil profil = ProfilDatabaseService.get(user.getStatut());
						upMap.put(user, profil);
					}
					//Constitution du filtre sous forme de string
					filtreJson = ListAllUsersAction.filtreToString(filtreNom, filtrePrenom, filtreLogin, filtreEmail, filtreProfil, listProfil);
					
					if (validForm == 1){
						if (filtreNomBase != filtreNom || filtrePrenomBase != filtrePrenom || filtreLoginBase != filtreLogin || filtreEmailBase != filtreEmail || filtreProfilBase != filtreProfil){
							//Constitution du filtre sous forme de string
							filtreJson = ListAllUsersAction.filtreToString(filtreNom, filtrePrenom, filtreLogin, filtreEmail, filtreProfil, listProfil);	
							FiltreDatabaseService.update(filtreId, filtreJson != null ? filtreJson.toString() : null);
						}
					}
				} catch (Exception e) {
					error = getText("error.message.generique") + " : " + e.getMessage();
					erreurLogger.error("Update de filtre users", e);
				}
			}
			else{
				try {
					users = UsersDatabaseService.getAllUsers(pagination, sort, sens, filtreNom, filtrePrenom, filtreLogin, filtreEmail, filtreProfil);	
					//attribution du profil pour chaque user
					upMap = new LinkedHashMap<Users, Profil>();
					for(Users user : users){
						Profil profil = ProfilDatabaseService.get(user.getStatut());
						upMap.put(user, profil);
					}
					//Constitution du filtre sous forme de string
					filtreJson = ListAllUsersAction.filtreToString(filtreNom, filtrePrenom, filtreLogin, filtreEmail, filtreProfil, listProfil);
					if (validForm == 1){
						if (filtreNom != null || filtrePrenom != null || filtreLogin != null || filtreEmail != null || filtreProfil != null){
							FiltreDatabaseService.create(userLoggedId, titrePage, filtreJson != null ? filtreJson.toString() : null);
						}
					}
				} catch (Exception e) {
					error = getText("error.message.generique") + " : " + e.getMessage();
					erreurLogger.error("Creation de filtre users", e);
				}
				
			}

			if(filtreNom != null)
				filtreNom = filtreNom.replaceAll("\\\\", "\\\\\\\\").replaceAll("'", "\\\\'");
			if(filtrePrenom != null)
				filtrePrenom = filtrePrenom.replaceAll("\\\\", "\\\\\\\\").replaceAll("'", "\\\\'");
			if(filtreLogin != null)
				filtreLogin = filtreLogin.replaceAll("\\\\", "\\\\\\\\").replaceAll("'", "\\\\'");
			if(filtreEmail != null)
				filtreEmail = filtreEmail.replaceAll("\\\\", "\\\\\\\\").replaceAll("'", "\\\\'");
			
			if (filtreJson != null)
				filtreString = StringConverter.afficheFiltre(filtreJson.toString());
			
			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Liste des utilisateurs - ", e);
			return ERROR;
		}
	}

	public void reloadFiltreBase(String filtreString){
		if (filtreString != null){
			filtreJson = JSONObject.fromObject(filtreString);
			if (filtreJson.containsKey("Nom")){
				setFiltreNomBase(filtreJson.getString("Nom"));
			}
			if (filtreJson.containsKey("Prénom")){
				setFiltrePrenomBase(filtreJson.getString("Prénom"));
			}
			if (filtreJson.containsKey("Login")){
				setFiltreLoginBase(filtreJson.getString("Login"));
			}
			if (filtreJson.containsKey("Profil")){
				for(Profil profil : listProfil){
					if(profil.getLibelle().equalsIgnoreCase(filtreJson.getString("Profil"))){
						setFiltreProfilBase(profil.getId().toString());
					}
				}
			}
			if (filtreJson.containsKey("E-mail")){
				setFiltreEmailBase(filtreJson.getString("E-mail"));
			}
			if (validForm == 0){
				setFiltreNom(filtreNomBase);
				setFiltrePrenom(filtrePrenomBase);
				setFiltreLogin(filtreLoginBase);
				setFiltreProfil(filtreProfilBase);
				setFiltreEmail(filtreEmailBase);
			}
		}
	}
	
	
	/**
	 * Met les filtres sous forme string pour affichage
	 * @param listProfil 
	 * @param filtreNom2
	 * @param filtrePrenom2
	 * @param filtreLogin2
	 * @param filtreEmail2
	 * @param filtreProfil2
	 */
	public static JSONObject filtreToString(String filtreNom, String filtrePrenom, String filtreLogin, String filtreEmail, String filtreProfil, List<Profil> listProfil) {
		StringBuffer buffer = new StringBuffer();
		if (filtreNom != null && !filtreNom.equals("")) {
			buffer.append(StringConverter.toJson("Nom", filtreNom));
		}
		if (filtrePrenom!= null && !filtrePrenom.equals("")) {
			buffer.append(StringConverter.toJson("Prénom", filtrePrenom));
		}
		if (filtreLogin != null && !filtreLogin.equals("")) {
			buffer.append(StringConverter.toJson("Login", filtreLogin));
		}
		if (filtreProfil!= null && !filtreProfil.equals("")) {
			Integer idProfil = Integer.parseInt(filtreProfil);
			for(Profil profil : listProfil){
				if(profil.getId().equals(idProfil)){
					buffer.append(StringConverter.toJson("Profil", profil.getLibelle()));
					break;
				}
			}
		}
		if (filtreEmail!= null && !filtreEmail.equals("")) {
			buffer.append(StringConverter.toJson("E-mail", filtreEmail));
		}
		if(buffer.length() != 0){
			String result = "{" + buffer.substring(0, buffer.lastIndexOf(PilotageConstants.SEPARATEUR_3)) + "}";
			JSONObject jsonFiltre = JSONObject.fromObject(result);
			return jsonFiltre;
			
		} else
			return null;
	}
}