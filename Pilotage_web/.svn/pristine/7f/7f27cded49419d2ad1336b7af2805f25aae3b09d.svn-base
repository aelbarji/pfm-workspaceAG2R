package pilotage.derogation.action;

import java.util.List;

import pilotage.database.applicatif.ApplicatifDatabaseService;
import pilotage.database.derogation.DerogationDatabaseService;
import pilotage.database.derogation.DerogationTypeChangementDatabaseService;
import pilotage.database.derogation.DerogationTypeDatabaseService;
import pilotage.database.users.management.UsersDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Applicatifs_Liste;
import pilotage.metier.Derogation;
import pilotage.metier.Derogation_Type;
import pilotage.metier.Derogation_Type_Changement;
import pilotage.metier.Users;
import pilotage.service.date.DateService;

public class RedirectDerogationAction extends AbstractAction{
	
	
	private static final long serialVersionUID = -5607375861734886541L;
	
	private Boolean fromValidation = false;
	
	private Integer selectRow;
	
	private String sort;
	private String sens;
	private int page;
	private int nrPages;
	private int nrPerPage;
	
	private Integer dtID;
	private Integer demandeurID;
	private Integer appID;
	private String numTele;
	private String numARS;
	private String descriptionText;
	private String service;
	private String realisateur;
	private String clientT;
	private String serviceImp;
	private Integer rTP;
	private Integer etude;
	private Integer retourArriere;
	private Integer externe;
	private Integer recette;	
	private Integer dtcID;
	private String justificatif;
	private String dateDemandee;
	private String heureDemandee;

	private List<Derogation_Type> dtList;
	private List<Users> userList;
	private List<Applicatifs_Liste> appList;
	private List<Derogation_Type_Changement> dtcList;
	
	private Derogation derogation;

	public Boolean getFromValidation() {
		return fromValidation;
	}

	public void setFromValidation(Boolean fromValidation) {
		this.fromValidation = fromValidation;
	}

	public String getSort() {
		return sort;
	}

	public Integer getSelectRow() {
		return selectRow;
	}

	public void setSelectRow(Integer selectRow) {
		this.selectRow = selectRow;
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

	public List<Derogation_Type> getDtList() {
		return dtList;
	}

	public void setDtList(List<Derogation_Type> dtList) {
		this.dtList = dtList;
	}

	public List<Users> getUserList() {
		return userList;
	}

	public void setUserList(List<Users> userList) {
		this.userList = userList;
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

	public Derogation getDerogation() {
		return derogation;
	}

	public void setDerogation(Derogation derogation) {
		this.derogation = derogation;
	}

	public Integer getDtID() {
		return dtID;
	}

	public void setDtID(Integer dtID) {
		this.dtID = dtID;
	}

	public Integer getDemandeurID() {
		return demandeurID;
	}

	public void setDemandeurID(Integer demandeurID) {
		this.demandeurID = demandeurID;
	}

	public Integer getAppID() {
		return appID;
	}

	public void setAppID(Integer appID) {
		this.appID = appID;
	}

	public String getNumTele() {
		return numTele;
	}

	public void setNumTele(String numTele) {
		this.numTele = numTele;
	}

	public String getNumARS() {
		return numARS;
	}

	public void setNumARS(String numARS) {
		this.numARS = numARS;
	}

	public String getDescriptionText() {
		return descriptionText;
	}

	public void setDescriptionText(String descriptionText) {
		this.descriptionText = descriptionText;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getRealisateur() {
		return realisateur;
	}

	public void setRealisateur(String realisateur) {
		this.realisateur = realisateur;
	}

	public String getClientT() {
		return clientT;
	}

	public void setClientT(String clientT) {
		this.clientT = clientT;
	}

	public String getServiceImp() {
		return serviceImp;
	}

	public void setServiceImp(String serviceImp) {
		this.serviceImp = serviceImp;
	}


	public Integer getRTP() {
		return rTP;
	}

	public void setRTP(Integer rTP) {
		this.rTP = rTP;
	}

	public Integer getEtude() {
		return etude;
	}

	public void setEtude(Integer etude) {
		this.etude = etude;
	}

	public Integer getRetourArriere() {
		return retourArriere;
	}

	public void setRetourArriere(Integer retourArriere) {
		this.retourArriere = retourArriere;
	}

	public Integer getExterne() {
		return externe;
	}

	public void setExterne(Integer externe) {
		this.externe = externe;
	}

	public Integer getRecette() {
		return recette;
	}

	public void setRecette(Integer recette) {
		this.recette = recette;
	}

	public Integer getDtcID() {
		return dtcID;
	}

	public void setDtcID(Integer dtcID) {
		this.dtcID = dtcID;
	}

	public String getJustificatif() {
		return justificatif;
	}

	public void setJustificatif(String justificatif) {
		this.justificatif = justificatif;
	}

	public String getDateDemandee() {
		return dateDemandee;
	}

	public void setDateDemandee(String dateDemandee) {
		this.dateDemandee = dateDemandee;
	}

	public String getHeureDemandee() {
		return heureDemandee;
	}

	public void setHeureDemandee(String heureDemandee) {
		this.heureDemandee = heureDemandee;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		dtList = DerogationTypeDatabaseService.getAll();
		userList = UsersDatabaseService.getAll();
		appList = ApplicatifDatabaseService.getAll();
		dtcList = DerogationTypeChangementDatabaseService.getAll();
		
		if(selectRow != null){
			derogation = DerogationDatabaseService.get(selectRow);
			dtID = derogation.getIdType().getId();
			demandeurID = derogation.getIdNomDemandeur().getId();
			numTele = derogation.getTelephone();
			appID = derogation.getIdAppli().getId();
			service = derogation.getService();
			realisateur = derogation.getRealisateur();
			descriptionText = derogation.getDescription();
			clientT = derogation.getClientTouche();
			serviceImp = derogation.getServiceImpact();
			numARS = derogation.getNumars();
			rTP = derogation.getTp();
			etude = derogation.getEtude();
			retourArriere = derogation.getRetourArriere();
			dtcID = derogation.getTypeChangement().getId();
			externe = derogation.getExterne();
			recette = derogation.getRecette();
			justificatif = derogation.getJustificatif();
			dateDemandee = DateService.dateToStr(derogation.getTimeDemande(), DateService.p1);
			heureDemandee = derogation.getHeureDemande().substring(0, derogation.getHeureDemande().lastIndexOf(":"));
		}
		
		return OK;
	}

}