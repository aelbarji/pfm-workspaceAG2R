package pilotage.checklist.admin;

import java.util.List;
import net.sf.json.JSONObject;

import pilotage.database.applicatif.EnvironmentDatabaseService;
import pilotage.database.checklist.ChecklistBaseDatabaseService;
import pilotage.database.checklist.ChecklistCriticiteDatabaseService;
import pilotage.database.checklist.ChecklistEtatDatabaseService;
import pilotage.database.filtre.FiltreDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Checklist_Base;
import pilotage.metier.Checklist_Criticite;
import pilotage.metier.Checklist_Etat;
import pilotage.metier.Environnement;
import pilotage.metier.Filtre;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;
import pilotage.service.string.StringConverter;
import pilotage.utils.Pagination;

public class ShowChecklistBaseAction extends AbstractAction {

	private static final long serialVersionUID = 8601252711002185215L;
	
	private String sort;
	private String sens;
	private int page;
	private int nrPages;
	private int nrPerPage;
	
	private String filtreNom;
	private Integer filtreEnvironnement;
	private String filtreDateDebut;
	private Integer filtreEtat;
	private Integer filtreCriticite;
	private JSONObject filtreJson;
	
	private int validForm = 0;
	private String titrePage = "CKL_LST";
	private Filtre filtre;
	private String filtreNomBase;
	private Integer filtreEnvironnementBase;
	private String filtreDateDebutBase;
	private Integer filtreEtatBase;
	private Integer filtreCriticiteBase;
	
	private Pagination<Checklist_Base> pagination;
	private List<Checklist_Base> listeTaches;
	
	private List<Environnement> listEnvironnement;
	private List<Checklist_Etat> listEtat;
	private List<Checklist_Criticite> listCriticite;
	
	public JSONObject getFiltreJson() {
		return filtreJson;
	}

	public void setFiltreJson(JSONObject filtreJson) {
		this.filtreJson = filtreJson;
	}

	public List<Checklist_Base> getListeTaches() {
		return listeTaches;
	}

	public void setListeTaches(List<Checklist_Base> listeTaches) {
		this.listeTaches = listeTaches;
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

	public Integer getFiltreEnvironnement() {
		return filtreEnvironnement;
	}

	public void setFiltreEnvironnement(Integer filtreEnvironnement) {
		this.filtreEnvironnement = filtreEnvironnement;
	}

	public String getFiltreDateDebut() {
		return filtreDateDebut;
	}

	public void setFiltreDateDebut(String filtreDateDebut) {
		if(!"".equals(filtreDateDebut))
			this.filtreDateDebut = filtreDateDebut;
	}
	
	public Integer getFiltreEtat() {
		return filtreEtat;
	}

	public void setFiltreEtat(Integer filtreEtat) {
		this.filtreEtat = filtreEtat;
	}

	public Integer getFiltreCriticite() {
		return filtreCriticite;
	}

	public void setFiltreCriticite(Integer filtreCriticite) {
		this.filtreCriticite = filtreCriticite;
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

	public Integer getFiltreEnvironnementBase() {
		return filtreEnvironnementBase;
	}

	public void setFiltreEnvironnementBase(Integer filtreEnvironnementBase) {
		this.filtreEnvironnementBase = filtreEnvironnementBase;
	}

	public String getFiltreDateDebutBase() {
		return filtreDateDebutBase;
	}

	public void setFiltreDateDebutBase(String filtreDateDebutBase) {
		this.filtreDateDebutBase = filtreDateDebutBase;
	}

	public Integer getFiltreEtatBase() {
		return filtreEtatBase;
	}

	public void setFiltreEtatBase(Integer filtreEtatBase) {
		this.filtreEtatBase = filtreEtatBase;
	}

	public Integer getFiltreCriticiteBase() {
		return filtreCriticiteBase;
	}

	public void setFiltreCriticiteBase(Integer filtreCriticiteBase) {
		this.filtreCriticiteBase = filtreCriticiteBase;
	}

	public Pagination<Checklist_Base> getPagination() {
		return pagination;
	}

	public void setPagination(Pagination<Checklist_Base> pagination) {
		this.pagination = pagination;
	}

	public List<Environnement> getListEnvironnement() {
		return listEnvironnement;
	}

	public void setListEnvironnement(List<Environnement> listEnvironnement) {
		this.listEnvironnement = listEnvironnement;
	}

	public List<Checklist_Etat> getListEtat() {
		return listEtat;
	}

	public void setListEtat(List<Checklist_Etat> listEtat) {
		this.listEtat = listEtat;
	}

	public List<Checklist_Criticite> getListCriticite() {
		return listCriticite;
	}

	public void setListCriticite(List<Checklist_Criticite> listCriticite) {
		this.listCriticite = listCriticite;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		//recuperation des taches suivant les filtres, le tri et la page
		if(page < 1)
			page = 1;
		else if(nrPages != 0 && page > nrPages)
			page = nrPages;
		if(nrPerPage == 0)
			nrPerPage = PilotageConstants.NB_TACHES_PER_PAGE;
		if(sens == null || "".equals(sens))
			sens = "asc";
		if(sort == null || "".equals(sort))
			sort = "tache";
		pagination = new Pagination<Checklist_Base>(page, nrPerPage);
		listCriticite = ChecklistCriticiteDatabaseService.getAll();
		listEnvironnement = EnvironmentDatabaseService.getAll();
		listEtat = ChecklistEtatDatabaseService.getAll();
		
		//gestion du filtre
		Users userLogged = (Users)session.get(PilotageConstants.USER_LOGGED);
		Integer userLoggedId = userLogged.getId();
		filtre = FiltreDatabaseService.getFiltre(userLoggedId,titrePage);
		
		if (filtre != null){
			try {
				Integer filtreId = filtre.getId();
				reloadFiltreBase(filtre.getFiltreString());
				listeTaches = ChecklistBaseDatabaseService.getAll(pagination, sort, sens, filtreNom, filtreEnvironnement, filtreDateDebut, filtreEtat, filtreCriticite);		
				filtreJson = ShowChecklistBaseAction.filtreToString(filtreNom, filtreEnvironnement, filtreDateDebut, filtreEtat, filtreCriticite);

				if (validForm == 1){
					if (filtreNomBase != filtreNom || filtreEnvironnementBase != filtreEnvironnement || filtreDateDebutBase != filtreDateDebut || filtreEtatBase != filtreEtat || filtreCriticiteBase != filtreCriticite){
						filtreJson = ShowChecklistBaseAction.filtreToString(filtreNom, filtreEnvironnement, filtreDateDebut, filtreEtat, filtreCriticite);
						FiltreDatabaseService.update(filtreId, filtreJson != null ? filtreJson.toString() : null);
					}
				}
			} catch (Exception e) {
				error = getText("error.message.generique") + " : " + e.getMessage();
				erreurLogger.error("Update de filtre administration checklist", e);
			}
		}
		else{
			try {
				listeTaches = ChecklistBaseDatabaseService.getAll(pagination, sort, sens, filtreNom, filtreEnvironnement, filtreDateDebut, filtreEtat, filtreCriticite);		
				filtreJson = ShowChecklistBaseAction.filtreToString(filtreNom, filtreEnvironnement, filtreDateDebut, filtreEtat, filtreCriticite);
				if (validForm == 1){
					if (filtreNom != null || filtreEnvironnement != null || filtreDateDebut != null || filtreEtat != null || filtreCriticite != null){
						FiltreDatabaseService.create(userLoggedId, titrePage, filtreJson != null ? filtreJson.toString() : null);
					}
				}
			} catch (Exception e) {
				error = getText("error.message.generique") + " : " + e.getMessage();
				erreurLogger.error("Creation de filtre administration checklist", e);
			}
		}
		return OK;
	}
	
	public void reloadFiltreBase(String filtreString){
		if (filtreString != null){
			filtreJson = JSONObject.fromObject(filtreString);
			if (filtreJson.containsKey("Nom")){
				setFiltreNomBase(filtreJson.getString("Nom"));
			}
			if (filtreJson.containsKey("Environnement")){
				for(Environnement env : listEnvironnement){
					if(env.getEnvironnement().equalsIgnoreCase(filtreJson.getString("Environnement"))){
						setFiltreEnvironnementBase(env.getId());
					}
				}
			}
			if (filtreJson.containsKey("Date de début")){
				setFiltreDateDebutBase(filtreJson.getString("Date de début"));
			}
			if (filtreJson.containsKey("Criticité")){
				for(Checklist_Criticite crit : listCriticite){
					if(crit.getLibelle().equalsIgnoreCase(filtreJson.getString("Criticité"))){
						setFiltreCriticiteBase(crit.getId());
					}
				}
			}
			if (validForm == 0){
				setFiltreNom(filtreNomBase);
				setFiltreEnvironnement(filtreEnvironnementBase);
				setFiltreDateDebut(filtreDateDebutBase);
				setFiltreCriticite(filtreCriticiteBase);
			}
		}
	}
	
	
	/**
	 * Met les filtres sous forme string pour affichage
	 * @param filtreCriticite 
	 * @param filtreEtat 
	 * @param filtreDateDebut 
	 * @param filtreEnvironnement 
	 * @param filtreNom 
	 * @param listProfil 
	 * @param filtreNom2
	 * @param filtrePrenom2
	 * @param filtreLogin2
	 * @param filtreEmail2
	 * @param filtreProfil2
	 */
	public static JSONObject filtreToString(String filtreNom, Integer filtreEnvironnement, String filtreDateDebut, Integer filtreEtat, Integer filtreCriticite) {
		StringBuffer buffer = new StringBuffer();
		if (filtreNom != null && !filtreNom.equals("")) {
			buffer.append(StringConverter.toJson("Nom", filtreNom));
		}
		if (filtreEnvironnement!= null && filtreEnvironnement != -1) {
			Environnement env = EnvironmentDatabaseService.get(filtreEnvironnement);
			buffer.append(StringConverter.toJson("Environnement", env.getEnvironnement()));
		}
		if (filtreDateDebut != null && !filtreDateDebut.equals("")) {
			buffer.append(StringConverter.toJson("Date de début", filtreDateDebut));
		}
		if (filtreEtat!= null && filtreEtat != -1) {
			Checklist_Etat etat = ChecklistEtatDatabaseService.get(filtreEtat);
			buffer.append(StringConverter.toJson("Etat", etat.getEtat()));
		}
		if (filtreCriticite!= null && filtreCriticite != -1) {
			Checklist_Criticite criticite = ChecklistCriticiteDatabaseService.get(filtreCriticite);
			buffer.append(StringConverter.toJson("Criticité", criticite.getLibelle()));
		}
		if(buffer.length() != 0){
			String result = "{" + buffer.substring(0, buffer.lastIndexOf(PilotageConstants.SEPARATEUR_3)) + "}";
			return JSONObject.fromObject(result) ;
		}
		else
			return null;
	}

}
