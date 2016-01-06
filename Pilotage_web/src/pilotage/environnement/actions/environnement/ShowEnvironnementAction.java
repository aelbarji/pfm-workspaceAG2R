package pilotage.environnement.actions.environnement;

import java.util.HashMap;
import java.util.List;

import pilotage.database.environnement.EnvironnementDatabaseService;
import pilotage.database.environnement.EnvironnementTypeDatabaseService;
import pilotage.database.filtre.FiltreDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Environnement;
import pilotage.metier.Environnement_Type;
import pilotage.metier.Filtre;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;
import pilotage.utils.Pagination;

public class ShowEnvironnementAction extends AbstractAction{

	private static final long serialVersionUID = -5715195244432667467L;

	private String  sort;
	private String  sens;
	private int page;
	private int nrPages;
	private int nrPerPage;
	
	private String  filtreEnvironnement;
	private Integer filtreType;
	private String  filtreString;
	
	private int validForm = 0;
	private String titrePage = "ENV_LST";
	private Filtre filtre;
	private String  filtreEnvironnementBase;
	private Integer filtreTypeBase;
	
	private Pagination<Environnement> pagination;
	private List<Environnement> environnements;
	private List<Environnement_Type> environnement_Types;
	
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

	public String getFiltreEnvironnement() {
		return filtreEnvironnement;
	}

	public void setFiltreEnvironnement(String filtreEnvironnement) {
		this.filtreEnvironnement = filtreEnvironnement;
	}

	public Integer getFiltreType() {
		return filtreType;
	}

	public void setFiltreType(Integer filtreType) {
		this.filtreType = filtreType;
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

	public String getFiltreEnvironnementBase() {
		return filtreEnvironnementBase;
	}

	public void setFiltreEnvironnementBase(String filtreEnvironnementBase) {
		this.filtreEnvironnementBase = filtreEnvironnementBase;
	}

	public Integer getFiltreTypeBase() {
		return filtreTypeBase;
	}

	public void setFiltreTypeBase(Integer filtreTypeBase) {
		this.filtreTypeBase = filtreTypeBase;
	}

	public String getFiltreString() {
		return filtreString;
	}

	public void setFiltreString(String filtreString) {
		this.filtreString = filtreString;
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

	public List<Environnement> getEnvironnements() {
		return environnements;
	}

	public void setEnvironnements(List<Environnement> environnements) {
		this.environnements = environnements;
	}

	public List<Environnement_Type> getEnvironnement_Types() {
		return environnement_Types;
	}

	public void setEnvironnement_Types(List<Environnement_Type> environnement_Types) {
		this.environnement_Types = environnement_Types;
	}
	
	public Pagination<Environnement> getPagination() {
		return pagination;
	}

	public void setPagination(Pagination<Environnement> pagination) {
		this.pagination = pagination;
	}
	
	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		try {
			//recuperation des environnements suivant les filtres, le tri et la page
			if(page < 1)
				page = 1;
			else if(nrPages!= 0 && page > nrPages)
				page = nrPages;
			if(nrPerPage==0)
				nrPerPage = PilotageConstants.NB_ENVIRONNEMENT_PER_PAGE;
			if(sens==null || "".equals(sens)) 
				sens="asc";
			if(sort==null || "".equals(sort))
				sort="environnement";
			pagination = new Pagination<Environnement>(page, nrPerPage);
			//Récupération de la liste des types pour le filtre
			environnement_Types = EnvironnementTypeDatabaseService.getAll();
			
			//gestion du filtre
			Users userLogged = (Users)session.get(PilotageConstants.USER_LOGGED);
			Integer userLoggedId = userLogged.getId();
			filtre = FiltreDatabaseService.getFiltre(userLoggedId,titrePage);
			
			if (filtre != null){
				try {
					Integer filtreId = filtre.getId();
					reloadFiltreBase(filtre.getFiltreString());
					environnements=EnvironnementDatabaseService.getAll(pagination, sort, sens, filtreEnvironnement, filtreType);
					//Constitution du filtre sous forme de string
					filtreString = ShowEnvironnementAction.filtreToString(filtreEnvironnement, filtreType, environnement_Types );
					
					if (validForm == 1){
						if (filtreEnvironnementBase != filtreEnvironnement || filtreTypeBase != filtreType){
							//Constitution du filtre sous forme de string
							filtreString = ShowEnvironnementAction.filtreToString(filtreEnvironnement, filtreType, environnement_Types );
							FiltreDatabaseService.update(filtreId, filtreString);
						}
					}
				} catch (Exception e) {
					error = getText("error.message.generique") + " : " + e.getMessage();
					erreurLogger.error("Update de filtre environnement", e);
				}
			}
			else{
				try {
					environnements=EnvironnementDatabaseService.getAll(pagination, sort, sens, filtreEnvironnement, filtreType);
					//Constitution du filtre sous forme de string
					filtreString = ShowEnvironnementAction.filtreToString(filtreEnvironnement, filtreType, environnement_Types );
					if (validForm == 1){
						if (filtreEnvironnement != null || filtreType != null){
							FiltreDatabaseService.create(userLoggedId, titrePage, filtreString);
						}
					}
				} catch (Exception e) {
					error = getText("error.message.generique") + " : " + e.getMessage();
					erreurLogger.error("Creation de filtre environnement", e);
				}
			}

			return OK;
		} catch (Exception e) {
			error = getText("error.message.generique")+":"+e.getMessage();
			erreurLogger.error("Affichage de la liste des environnements - ",e);
		    return ERROR;
		}
	}
	
	public void reloadFiltreBase(String filtreString){
		HashMap<String, String> filtreMap = new HashMap<String, String>();
		if (filtreString != null){
			String filtre[] = filtreString.split(PilotageConstants.SEPARATEUR);
			for(int i=0; i<filtre.length; i++){
				String f = filtre[i];
				String f1[] = f.split(PilotageConstants.SEPARATEUR_2);
				filtreMap.put(f1[0], f1[1]);
			}
			if (filtreMap.containsKey("Environnement")){
				setFiltreEnvironnementBase(filtreMap.get("Environnement"));
			}
			if (filtreMap.containsKey("Type")){
				for(Environnement_Type envType : environnement_Types){
					if(envType.getType().equalsIgnoreCase(filtreMap.get("Type"))){
						setFiltreTypeBase(envType.getId());
					}
				}
			}
			
			if (validForm == 0){
				setFiltreEnvironnement(filtreEnvironnementBase);
				setFiltreType(filtreTypeBase);
			}
		}
	}
	
	/**
	 * Met les filtres sous forme string pour affichage
	 * @param filtreEnvironnement 
	 * @param filtreType
	 */
	public static String filtreToString(String filtreEnvironnement, Integer filtreType, List<Environnement_Type> environnement_Types ){
		StringBuffer buffer = new StringBuffer();
		if (filtreEnvironnement != null &&!"".equals(filtreEnvironnement)) {
			buffer.append("Environnement : ");
			buffer.append(filtreEnvironnement);
			buffer.append(PilotageConstants.SEPARATEUR);
		}
		if (filtreType != null && filtreType >= 0) {
			for(Environnement_Type environnement_Type : environnement_Types){
				if (environnement_Type.getId().equals(filtreType)) {
					buffer.append("Type : ");
					buffer.append(environnement_Type.getType());
					buffer.append(PilotageConstants.SEPARATEUR);
					break;
				}
			}
		}
		if (buffer.length() != 0) {
			return buffer.substring(0, buffer.lastIndexOf(PilotageConstants.SEPARATEUR));
		}
		else 
			return null;
	}
}
