package pilotage.astreintes.actions.planning;

import java.util.List;

import net.sf.json.JSONObject;

import pilotage.database.astreintes.AstreinteDatabaseService;
import pilotage.database.astreintes.AstreinteDomaineDatabaseService;
import pilotage.database.astreintes.AstreintePlanningDatabaseService;
import pilotage.database.astreintes.AstreinteTypeDatabaseService;
import pilotage.database.filtre.FiltreDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Astreinte;
import pilotage.metier.Astreinte_Domaine;
import pilotage.metier.Astreinte_Planning;
import pilotage.metier.Astreinte_Type;
import pilotage.metier.Filtre;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;
import pilotage.service.string.StringConverter;
import pilotage.utils.Pagination;

public class ShowHistoricAstreintePlanning extends AbstractAction{

	private static final long serialVersionUID = -8526407777753806090L;
	
	private List<Astreinte_Planning> aPlannings;
	private List<Astreinte> astreintes;
	private List<Astreinte_Domaine> aDomaines;
	private List<Astreinte_Type> aTypes;
	
	private String sort;
	private String sens;
	private int page;
	private int nrPages;
	private int nrPerPage;
	
	private String filtreType;
	private String filtreDomaine;
	private String filtreAstreinte;
	private String filtreDebDate;
	private String filtreFinDate;
	private String filtreTelephone;
	private String filtreCommentaire;
	private String filtreString;
	private JSONObject filtreJson;
	
	private int validForm = 0;
	private String titrePage = "AST_HISTO";
	private Filtre filtre;
	private String filtreTypeBase;
	private String filtreDomaineBase;
	private String filtreAstreinteBase;
	private String filtreDebDateBase;
	private String filtreFinDateBase;
	private String filtreTelephoneBase;
	private String filtreCommentaireBase;

	private Pagination<Astreinte_Planning> pagination;

	public String getFiltreString() {
		return filtreString;
	}

	public void setFiltreString(String filtreString) {
		this.filtreString = filtreString;
	}

	public List<Astreinte_Planning> getAPlannings() {
		return aPlannings;
	}

	public JSONObject getFiltreJson() {
		return filtreJson;
	}

	public void setFiltreJson(JSONObject filtreJson) {
		this.filtreJson = filtreJson;
	}

	public void setAPlannings(List<Astreinte_Planning> aPlannings) {
		this.aPlannings = aPlannings;
	}

	public List<Astreinte> getAstreintes() {
		return astreintes;
	}

	public void setAstreintes(List<Astreinte> astreintes) {
		this.astreintes = astreintes;
	}

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

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getFiltreType() {
		return filtreType;
	}

	public void setFiltreType(String filtreType) {
		this.filtreType = filtreType;
	}

	public String getFiltreDomaine() {
		return filtreDomaine;
	}

	public void setFiltreDomaine(String filtreDomaine) {
		this.filtreDomaine = filtreDomaine;
	}

	public String getFiltreAstreinte() {
		return filtreAstreinte;
	}

	public void setFiltreAstreinte(String filtreAstreinte) {
		this.filtreAstreinte = filtreAstreinte;
	}

	public String getFiltreDebDate() {
		return filtreDebDate;
	}

	public void setFiltreDebDate(String filtreDebDate) {
		if(!"".equals(filtreDebDate))
		this.filtreDebDate = filtreDebDate;
	}

	public String getFiltreFinDate() {
		return filtreFinDate;
	}

	public void setFiltreFinDate(String filtreFinDate) {
		if(!"".equals(filtreFinDate))
		this.filtreFinDate = filtreFinDate;
	}

	public String getFiltreTelephone() {
		return filtreTelephone;
	}

	public void setFiltreTelephone(String filtreTelephone) {
		this.filtreTelephone = filtreTelephone;
	}

	public String getFiltreCommentaire() {
		return filtreCommentaire;
	}

	public void setFiltreCommentaire(String filtreCommentaire) {
		this.filtreCommentaire = filtreCommentaire;
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

	public String getFiltreTypeBase() {
		return filtreTypeBase;
	}

	public void setFiltreTypeBase(String filtreTypeBase) {
		this.filtreTypeBase = filtreTypeBase;
	}

	public String getFiltreDomaineBase() {
		return filtreDomaineBase;
	}

	public void setFiltreDomaineBase(String filtreDomaineBase) {
		this.filtreDomaineBase = filtreDomaineBase;
	}

	public String getFiltreAstreinteBase() {
		return filtreAstreinteBase;
	}

	public void setFiltreAstreinteBase(String filtreAstreinteBase) {
		this.filtreAstreinteBase = filtreAstreinteBase;
	}

	public String getFiltreDebDateBase() {
		return filtreDebDateBase;
	}

	public void setFiltreDebDateBase(String filtreDebDateBase) {
		this.filtreDebDateBase = filtreDebDateBase;
	}

	public String getFiltreFinDateBase() {
		return filtreFinDateBase;
	}

	public void setFiltreFinDateBase(String filtreFinDateBase) {
		this.filtreFinDateBase = filtreFinDateBase;
	}

	public String getFiltreTelephoneBase() {
		return filtreTelephoneBase;
	}

	public void setFiltreTelephoneBase(String filtreTelephoneBase) {
		this.filtreTelephoneBase = filtreTelephoneBase;
	}

	public String getFiltreCommentaireBase() {
		return filtreCommentaireBase;
	}

	public void setFiltreCommentaireBase(String filtreCommentaireBase) {
		this.filtreCommentaireBase = filtreCommentaireBase;
	}

	public Pagination<Astreinte_Planning> getPagination() {
		return pagination;
	}

	public void setPagination(Pagination<Astreinte_Planning> pagination) {
		this.pagination = pagination;
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
		
		//recuperation des astreintes plannings suivant les filtres, le tri et la page
		if(page < 1)
			page = 1;
		else if(nrPages != 0 && page > nrPages)
			page = nrPages;
		if(nrPerPage == 0)
			nrPerPage = PilotageConstants.NB_ASTREINTES_PER_PAGE;
		if(sens == null || "".equals(sens))
			sens = "asc";
		if(sort == null || "".equals(sort))
			sort = "datedeb";

		pagination = new Pagination<Astreinte_Planning>(page, nrPerPage);
		//récupération des types, des astreintes et des domaines pour les filtres
		astreintes = AstreinteDatabaseService.getAll();
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
				//obtenir les plannings en cours et à venir.
				aPlannings = AstreintePlanningDatabaseService.getAll(false, pagination, sort, sens, filtreType, filtreDomaine, filtreAstreinte, filtreDebDate, filtreFinDate, filtreTelephone, filtreCommentaire);
				//Constitution du filtre sous forme de string
				filtreJson = ShowAstreintePlanningAction.filtreToString(filtreType, filtreDomaine, filtreAstreinte, filtreDebDate, filtreFinDate, filtreTelephone, filtreCommentaire, aTypes, aDomaines, astreintes);
				
				if (validForm == 1){
					if (filtreTypeBase != filtreType || filtreDomaineBase != filtreDomaine || filtreAstreinteBase != filtreAstreinte || filtreDebDateBase != filtreDebDate || filtreFinDateBase != filtreFinDate || filtreTelephoneBase != filtreTelephone || filtreCommentaireBase != filtreCommentaire){
						//Constitution du filtre sous forme de string
						filtreJson = ShowAstreintePlanningAction.filtreToString(filtreType, filtreDomaine, filtreAstreinte, filtreDebDate, filtreFinDate, filtreTelephone, filtreCommentaire, aTypes, aDomaines, astreintes);
						if (filtreJson != null)
							FiltreDatabaseService.update(filtreId, filtreJson.toString());
						else
							FiltreDatabaseService.update(filtreId, null);
					}
				}
			} catch (Exception e) {
				error = getText("error.message.generique") + " : " + e.getMessage();
				erreurLogger.error("Update de filtre astreinte historique", e);
			}
		}
		else{
			try {
				//obtenir les plannings en cours et à venir.
				aPlannings = AstreintePlanningDatabaseService.getAll(false, pagination, sort, sens, filtreType, filtreDomaine, filtreAstreinte, filtreDebDate, filtreFinDate, filtreTelephone, filtreCommentaire);
				//Constitution du filtre sous forme de string
				filtreJson = ShowAstreintePlanningAction.filtreToString(filtreType, filtreDomaine, filtreAstreinte, filtreDebDate, filtreFinDate, filtreTelephone, filtreCommentaire, aTypes, aDomaines, astreintes);
				if (validForm == 1){
					if (filtreType != null || filtreDomaine != null || filtreAstreinte != null || filtreDebDate != null || filtreFinDate != null || filtreTelephone != null || filtreCommentaire!= null){
						FiltreDatabaseService.create(userLoggedId, titrePage, filtreJson != null ? filtreJson.toString() : null );
					}
				}
			} catch (Exception e) {
				error = getText("error.message.generique") + " : " + e.getMessage();
				erreurLogger.error("Creation de filtre astreinte historique", e);
			}
		}

		if(filtreCommentaire != null)
			filtreCommentaire = filtreCommentaire.replaceAll("\\\\", "\\\\\\\\").replaceAll("'", "\\\\'");
		if(filtreTelephone != null)
			filtreTelephone = filtreTelephone.replaceAll("\\\\", "\\\\\\\\").replaceAll("'", "\\\\'");
		
		if(filtreJson != null)
			filtreString = StringConverter.afficheFiltre(filtreJson.toString());
		return OK;
	}

	public void reloadFiltreBase(String filtreString){
		if (filtreString != null){
			JSONObject jsonFiltre = JSONObject.fromObject(filtreString);
			
			if (jsonFiltre.containsKey("Type")){
				for(Astreinte_Type aType : aTypes){
					if(aType.getType().equalsIgnoreCase(jsonFiltre.getString("Type"))){
						setFiltreTypeBase(aType.getId().toString());
					}
				}
			}
			if (jsonFiltre.containsKey("Domaine")){
				for(Astreinte_Domaine aDomaine : aDomaines){
					if(aDomaine.getDomaine().equalsIgnoreCase(jsonFiltre.getString("Domaine"))){
						setFiltreDomaineBase(aDomaine.getId().toString());
					}
				}
			}
			if (jsonFiltre.containsKey("Date Debut")){
				setFiltreDebDateBase(jsonFiltre.getString("Date Debut"));
			}
			if (jsonFiltre.containsKey("Date Fin")){
				setFiltreFinDateBase(jsonFiltre.getString("Date Fin"));
			}
			if (jsonFiltre.containsKey("Astreinte")){
				for(Astreinte ast : astreintes){
					if(jsonFiltre.getString("Astreinte").equalsIgnoreCase(ast.getPrenom()+" "+ast.getNom())){
						setFiltreAstreinteBase(ast.getId().toString());
					}
				}
			}
			if (jsonFiltre.containsKey("Téléphone")){
				setFiltreTelephoneBase(jsonFiltre.getString("Téléphone"));
			}
			if (jsonFiltre.containsKey("Commentaire")){
				setFiltreCommentaireBase(jsonFiltre.getString("Commentaire"));
			}
			
			if (validForm == 0){
				setFiltreType(filtreTypeBase);
				setFiltreDomaine(filtreDomaineBase);
				setFiltreDebDate(filtreDebDateBase);
				setFiltreFinDate(filtreFinDateBase);
				setFiltreAstreinte(filtreAstreinteBase);
				setFiltreTelephone(filtreTelephoneBase);
				setFiltreCommentaire(filtreCommentaireBase);
			}
		}
	}
}
