package pilotage.machines.actions.service;

import java.util.List;

import net.sf.json.JSONObject;

import pilotage.database.filtre.FiltreDatabaseService;
import pilotage.database.machine.MachineInterlocuteurDatabaseService;
import pilotage.database.users.management.UsersDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Filtre;
import pilotage.metier.Interlocuteur;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;
import pilotage.service.string.StringConverter;
import pilotage.utils.Pagination;

public class ShowMachineInterlocuteurAction extends AbstractAction {
	private static final long serialVersionUID = -4037713125016637085L;
	
	private String sort;
	private String sens;
	private int page;
	private int nrPages;
	private int nrPerPage;
	
	private String filtreNom;
	private String filtreNomComplet;
	private Integer filtreResponsable;
	private String filtreBAI;
	private String filtreString;
	private JSONObject filtreJson;
	
	private int validForm = 0;
	private String titrePage = "MAC_SGE";
	private Filtre filtre;
	private String filtreNomBase;
	private String filtreNomCompletBase;
	private Integer filtreResponsableBase;
	private String filtreBAIBase;

	private Pagination<Interlocuteur> pagination;
	private List<Interlocuteur> listInterlocuteur;
	private List<Users> listUsers;

	public List<Interlocuteur> getListInterlocuteur() {
		return listInterlocuteur;
	}

	public void setListInterlocuteur(List<Interlocuteur> listInterlocuteur) {
		this.listInterlocuteur = listInterlocuteur;
	}

	public List<Users> getListUsers() {
		return listUsers;
	}

	public void setListUsers(List<Users> listUsers) {
		this.listUsers = listUsers;
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

	public String getFiltreNom() {
		return filtreNom;
	}

	public void setFiltreNom(String filtreNom) {
		this.filtreNom = filtreNom;
	}

	public String getFiltreNomComplet() {
		return filtreNomComplet;
	}

	public void setFiltreNomComplet(String filtreNomComplet) {
		this.filtreNomComplet = filtreNomComplet;
	}
	
	public Integer getFiltreResponsable() {
		return filtreResponsable;
	}

	public void setFiltreResponsable(Integer filtreResponsable) {
		this.filtreResponsable = filtreResponsable;
	}

	public String getFiltreBAI() {
		return filtreBAI;
	}

	public void setFiltreBAI(String filtreBAI) {
		this.filtreBAI = filtreBAI;
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

	public String getFiltreNomBase() {
		return filtreNomBase;
	}

	public void setFiltreNomBase(String filtreNomBase) {
		this.filtreNomBase = filtreNomBase;
	}

	public String getFiltreNomCompletBase() {
		return filtreNomCompletBase;
	}

	public void setFiltreNomCompletBase(String filtreNomCompletBase) {
		this.filtreNomCompletBase = filtreNomCompletBase;
	}

	public Integer getFiltreResponsableBase() {
		return filtreResponsableBase;
	}

	public void setFiltreResponsableBase(Integer filtreResponsableBase) {
		this.filtreResponsableBase = filtreResponsableBase;
	}

	public String getFiltreBAIBase() {
		return filtreBAIBase;
	}

	public void setFiltreBAIBase(String filtreBAIBase) {
		this.filtreBAIBase = filtreBAIBase;
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

	public Pagination<Interlocuteur> getPagination() {
		return pagination;
	}

	public void setPagination(Pagination<Interlocuteur> pagination) {
		this.pagination = pagination;
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
			nrPerPage = PilotageConstants.NB_INTERLOCUTEURS_PER_PAGE;
		if(sens == null || "".equals(sens))
			sens = "asc";
		if(sort == null || "".equals(sort))
			sort = "nomService";
		if(sort == null || "".equals(sort))
			sort = "nomComplet";
		pagination = new Pagination<Interlocuteur>(page, nrPerPage);
		listUsers = UsersDatabaseService.getAll();
		
		//gestion du filtre
		Users userLogged = (Users)session.get(PilotageConstants.USER_LOGGED);
		Integer userLoggedId = userLogged.getId();
		filtre = FiltreDatabaseService.getFiltre(userLoggedId,titrePage);
		
		if (filtre != null){
			try {
				Integer filtreId = filtre.getId();
				reloadFiltreBase(filtre.getFiltreString());
				listInterlocuteur = MachineInterlocuteurDatabaseService.getAll(pagination, sort, sens, filtreNom, filtreNomComplet, filtreResponsable, filtreBAI);
				//Constitution du filtre sous forme de string
				filtreJson = ShowMachineInterlocuteurAction.filtreToString(filtreNom, filtreNomComplet, filtreResponsable, filtreBAI);
				
				if (validForm == 1){
					if (filtreNomBase != filtreNom || filtreNomCompletBase != filtreNomComplet || filtreResponsableBase != filtreResponsable || filtreBAIBase != filtreBAI){
						//Constitution du filtre sous forme de string
						filtreJson = ShowMachineInterlocuteurAction.filtreToString(filtreNom, filtreNomComplet, filtreResponsable, filtreBAI);
						FiltreDatabaseService.update(filtreId, filtreJson != null ? filtreJson.toString() : null);
					}
				}
			} catch (Exception e) {
				error = getText("error.message.generique") + " : " + e.getMessage();
				erreurLogger.error("Update de filtre machine interlocuteur", e);
			}
		}
		else{
			try {
				listInterlocuteur = MachineInterlocuteurDatabaseService.getAll(pagination, sort, sens, filtreNom, filtreNomComplet, filtreResponsable, filtreBAI);
				//Constitution du filtre sous forme de string
				filtreJson = ShowMachineInterlocuteurAction.filtreToString(filtreNom, filtreNomComplet, filtreResponsable, filtreBAI);
				if (validForm == 1){
					if (filtreNom != null || filtreNomComplet != null || filtreResponsable != null || filtreBAI != null){
						FiltreDatabaseService.create(userLoggedId, titrePage, filtreJson != null ? filtreJson.toString() : null);
					}
				}
			} catch (Exception e) {
				error = getText("error.message.generique") + " : " + e.getMessage();
				erreurLogger.error("Creation de filtre machine interlocuteur", e);
			}
		}
		if(filtreNom != null)
			filtreNom = filtreNom.replaceAll("\\\\", "\\\\\\\\").replaceAll("'", "\\\\'");
		if(filtreNomComplet != null)
			filtreNomComplet = filtreNomComplet.replaceAll("\\\\", "\\\\\\\\").replaceAll("'", "\\\\'");
		if(filtreBAI != null)
			filtreBAI = filtreBAI.replaceAll("\\\\", "\\\\\\\\").replaceAll("'", "\\\\'");
		if (filtreJson != null)
			filtreString = StringConverter.afficheFiltre(filtreJson.toString());
		
		return OK;
	}

	public void reloadFiltreBase(String filtreString){
		if (filtreString != null){
			filtreJson = JSONObject.fromObject(filtreString);
			if (filtreJson.containsKey("Nom")){
				setFiltreNomBase(filtreJson.getString("Nom"));
			}
			if (filtreJson.containsKey("Nom complet")){
				setFiltreNomCompletBase(filtreJson.getString("Nom complet"));
			}
			if (filtreJson.containsKey("Responsable")){
				for(Users user : listUsers){
					if(filtreJson.getString("Responsable").equalsIgnoreCase(user.getNom()+" "+user.getPrenom())){
						setFiltreResponsableBase(user.getId());
					}
				}
			}
			if (filtreJson.containsKey("BAI")){
				setFiltreBAIBase(filtreJson.getString("BAI"));
			}
			if (validForm == 0){
				setFiltreNom(filtreNomBase);
				setFiltreNomComplet(filtreNomCompletBase);
				setFiltreResponsable(filtreResponsableBase);
				setFiltreBAI(filtreBAIBase);
			}
		}
	}
	
	/**
	 * Met les filtres sous forme string pour affichage
	 * @param filtreNom
	 * @param filtreNomComplet
	 * @param filtreResponsable
	 * @param filtreBAI
	 * @return
	 */
	public static JSONObject filtreToString(String filtreNom, String filtreNomComplet, Integer filtreResponsable, String filtreBAI) {
		StringBuffer buffer = new StringBuffer();
		if (filtreNom != null && !filtreNom.equals("")) {
			buffer.append(StringConverter.toJson("Nom", filtreNom));
		}
		if (filtreNomComplet != null && !filtreNomComplet.equals("")) {
			buffer.append(StringConverter.toJson("Nom complet", filtreNomComplet));
		}
		if (filtreResponsable != null && filtreResponsable != -1) {
			Users user = UsersDatabaseService.get(filtreResponsable);
			buffer.append(StringConverter.toJson("Responsable", user.getNom() + " " + user.getPrenom()));
		}
		if (filtreBAI != null && !filtreBAI.equals("")) {
			buffer.append(StringConverter.toJson("BAI", filtreBAI));
		}
		
		if(buffer.length() != 0){
			String result = "{" + buffer.substring(0, buffer.lastIndexOf(PilotageConstants.SEPARATEUR_3)) + "}";
			return JSONObject.fromObject(result);
			
		} else
			return null;
	}
}
