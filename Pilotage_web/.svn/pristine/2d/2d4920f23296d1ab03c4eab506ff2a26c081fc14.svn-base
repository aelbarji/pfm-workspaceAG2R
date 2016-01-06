package pilotage.derogation.action;



import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import pilotage.database.applicatif.ApplicatifDatabaseService;
import pilotage.database.derogation.DerogationDatabaseService;
import pilotage.database.derogation.DerogationEtatDatabaseService;
import pilotage.database.derogation.DerogationTypeChangementDatabaseService;
import pilotage.database.derogation.DerogationValideurDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Applicatifs_Liste;
import pilotage.metier.Derogation;
import pilotage.metier.Derogation_Etat;
import pilotage.metier.Derogation_Type_Changement;
import pilotage.metier.Derogation_Valideur;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;
import pilotage.service.date.DateService;
import pilotage.utils.Pagination;

public class ShowValiderDerogationAction extends AbstractAction{

	private static final long serialVersionUID = -1408140974286036712L;
	
	private Boolean isValideur = false;
	
	private String sort;
	private String sens;
	private int page;
	private int nrPages;
	private int nrPerPage;
	
	private String filtreNumero;
	private String filtreDate;
	private Integer filtreAppli;
	private Integer filtreTp;
	private Integer filtreDtc ;
	private Integer filtreEtat;
	private JSONObject filtreJson;

	private Pagination<Derogation> pagination;
	
	private List<Derogation> derogationList;
	private List<Applicatifs_Liste> appList;
	private List<Derogation_Type_Changement> dtcList;
	private List<Derogation_Etat> etatList;
	
	public Boolean getIsValideur() {
		return isValideur;
	}

	public void setIsValideur(Boolean isValideur) {
		this.isValideur = isValideur;
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

	public String getFiltreNumero() {
		return filtreNumero;
	}

	public void setFiltreNumero(String filtreNumero) {
		this.filtreNumero = filtreNumero;
	}

	public String getFiltreDate() {
		return filtreDate;
	}

	public void setFiltreDate(String filtreDate) {
		if(filtreDate != null && !"".equals(filtreDate))
			this.filtreDate = filtreDate;
	}

	public Integer getFiltreAppli() {
		return filtreAppli;
	}

	public void setFiltreAppli(Integer filtreAppli) {
		this.filtreAppli = filtreAppli;
	}

	public Integer getFiltreTp() {
		return filtreTp;
	}

	public void setFiltreTp(Integer filtreTp) {
		this.filtreTp = filtreTp;
	}

	public Integer getFiltreDtc() {
		return filtreDtc;
	}

	public void setFiltreDtc(Integer filtreDtc) {
		this.filtreDtc = filtreDtc;
	}

	public Integer getFiltreEtat() {
		return filtreEtat;
	}

	public void setFiltreEtat(Integer filtreEtat) {
		this.filtreEtat = filtreEtat;
	}
	
	public JSONObject getFiltreJson() {
		return filtreJson;
	}
	
	public void setFiltreJson(JSONObject filtreJson) {
		this.filtreJson = filtreJson;
	}

	public Pagination<Derogation> getPagination() {
		return pagination;
	}

	public void setPagination(Pagination<Derogation> pagination) {
		this.pagination = pagination;
	}

	public List<Derogation> getDerogationList() {
		return derogationList;
	}

	public void setDerogationList(List<Derogation> derogationList) {
		this.derogationList = derogationList;
	}

	public List<Applicatifs_Liste> getAppList() {
		return appList;
	}

	public void setAppList(List<Applicatifs_Liste> appList) {
		this.appList = appList;
	}

	public List<Derogation_Type_Changement> getDtcList() {
		return dtcList;
	}

	public void setDtcList(List<Derogation_Type_Changement> dtcList) {
		this.dtcList = dtcList;
	}

	public List<Derogation_Etat> getEtatList() {
		return etatList;
	}

	public void setEtatList(List<Derogation_Etat> etatList) {
		this.etatList = etatList;
	}

	@Override
	protected boolean validateMetier() {
		return true;
		
	}

	@Override
	protected String executeMetier() {
		//vérification que la personne est valideur
		Users user = (Users)session.get(PilotageConstants.USER_LOGGED);
		List<Derogation_Valideur> listValideurs = DerogationValideurDatabaseService.getAll();
		for(Derogation_Valideur valideur : listValideurs){
			if(valideur.getValideur().getId().equals(user.getId())){
				isValideur = true;
				break;
			}
		}
		
		if(!isValideur){
			error = getText("derogation.valideur.failed");
			return OK;
		}
		else{
		
			//récupération des filtres et paginations
			if(page < 1)
				page = 1;
			else if(nrPages != 0 && page > nrPages)
				page = nrPages;
			if(nrPerPage == 0)
				nrPerPage = PilotageConstants.NB_DEROGATIONS_PER_PAGE;
			if(sens == null || "".equals(sens))
				sens = "asc";
			if(sort == null || "".equals(sort))
				sort = "timeDemande";
			pagination = new Pagination<Derogation>(page, nrPerPage);
			
			//récupération de la liste des dérogations
			Date date = null;
			if(filtreDate != null && !"".equals(filtreDate))
				date = DateService.strToDate(filtreDate);
			
			Integer numero = null;			
			if(filtreNumero != null && !"".equals(filtreNumero)) 
				numero = Integer.parseInt(filtreNumero);
			
			derogationList = DerogationDatabaseService.getAllDerogationToValidate(pagination, sort, sens, numero, date, filtreAppli, filtreTp, filtreDtc, filtreEtat);
		
			//filtre
			filtreJson = ShowDerogationAction.filtreToString(filtreNumero, filtreDate, filtreAppli, filtreTp, filtreDtc, filtreEtat);
			appList = ApplicatifDatabaseService.getAll();
			dtcList = DerogationTypeChangementDatabaseService.getAll();
			etatList = DerogationEtatDatabaseService.getAllForValidation();
			
			return OK;
		}
	}
}
	
	
