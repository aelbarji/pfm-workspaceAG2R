package pilotage.derogation.action;

import java.text.MessageFormat;
import java.util.Date;
import java.util.List;

import pilotage.database.applicatif.ApplicatifDatabaseService;
import pilotage.database.derogation.DerogationDatabaseService;
import pilotage.database.derogation.DerogationTypeChangementDatabaseService;
import pilotage.database.derogation.DerogationTypeDatabaseService;
import pilotage.database.historique.HistoriqueDatabaseService;
import pilotage.database.users.management.UsersDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Applicatifs_Liste;
import pilotage.metier.Derogation_Type;
import pilotage.metier.Derogation_Type_Changement;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;
import pilotage.service.date.DateService;

public class CreateDerogationAction extends AbstractAction{
	
	private static final long serialVersionUID = -2047643053278301312L;
	
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
	private Integer etatID;
	private String valider;
	private String message;
	private String dateDemandee;
	private String heureDemandee;
	private int validerEtEnvoyer = 0;
	
	private String sort;
	private String sens;
	private int page;
	private int nrPages;
	private int nrPerPage;
	
	private List<Derogation_Type> dtList;
	private List<Users> userList;
	private List<Applicatifs_Liste> appList;
	private List<Derogation_Type_Changement> dtcList;

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

	public Integer getEtatID() {
		return etatID;
	}

	public void setEtatID(Integer etatID) {
		this.etatID = etatID;
	}

	public String getValider() {
		return valider;
	}

	public void setValider(String valider) {
		this.valider = valider;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
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

	public int getValiderEtEnvoyer() {
		return validerEtEnvoyer;
	}

	public void setValiderEtEnvoyer(int validerEtEnvoyer) {
		this.validerEtEnvoyer = validerEtEnvoyer;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		try{			
			Users createur = (Users)session.get(PilotageConstants.USER_LOGGED);
			
			Date date = DateService.strToDate(dateDemandee);
			String time = heureDemandee + ":00";
			
			if (validerEtEnvoyer == 0){
				DerogationDatabaseService.create(dtID, demandeurID, createur, numTele, appID, service, realisateur, descriptionText, clientT, serviceImp, numARS, rTP, date, time, etude, retourArriere, dtcID, externe, recette, justificatif, message);
				HistoriqueDatabaseService.create(null, MessageFormat.format(getText("historique.derogation.creation"), new Object[]{DerogationDatabaseService.getId(appID, rTP, date, dtcID)}), (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_DEROGATION);
				info = MessageFormat.format(getText("derogation.creation.valide"), new Object[]{dtID});
			}
			else{
				DerogationDatabaseService.create(dtID, demandeurID, createur, numTele, appID, service, realisateur, descriptionText, clientT, serviceImp, numARS, rTP, date, time, etude, retourArriere, dtcID, externe, recette, justificatif, message);
				HistoriqueDatabaseService.create(null, MessageFormat.format(getText("historique.derogation.creation"), new Object[]{DerogationDatabaseService.getId(appID, rTP, date, dtcID)}), (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_DEROGATION);
				info = MessageFormat.format(getText("derogation.creation.valide"), new Object[]{dtID});
				EnvoyerEmailAction envoieMailAction = new EnvoyerEmailAction();
				Integer derogId = DerogationDatabaseService.getId(appID, rTP, date, dtcID);
				envoieMailAction.setSelectRow(derogId);
				envoieMailAction.setSession(session);
				envoieMailAction.executeMetier();
				info = MessageFormat.format(getText("derogation.mail.sent"), new Object[]{derogId});
			}
			return OK;
		}
		catch(Exception e){
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Creation d'une dérogation", e);
			
			dtList = DerogationTypeDatabaseService.getAll();
			userList = UsersDatabaseService.getAll();
			appList = ApplicatifDatabaseService.getAll();
			dtcList = DerogationTypeChangementDatabaseService.getAll();
			
			return ERROR;
		}
	}
}
