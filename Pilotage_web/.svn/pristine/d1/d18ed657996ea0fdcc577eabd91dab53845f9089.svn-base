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
import pilotage.metier.Derogation;
import pilotage.metier.Derogation_Type;
import pilotage.metier.Derogation_Type_Changement;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;
import pilotage.service.date.DateService;
import pilotage.service.mail.MailService;

public class ModifyDerogationAction extends AbstractAction {
	
	private static final long serialVersionUID = -1926111995966254729L;
	
	protected static final String VALIDATION 	= "validation";
	private Boolean fromValidation = false;;
	
	private Integer selectRow;
	
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
	
	private String sort;
	private String sens;
	private int page;
	private int nrPages;
	private int nrPerPage;
	
	private List<Derogation_Type> dtList;
	private List<Users> userList;
	private List<Applicatifs_Liste> appList;
	private List<Derogation_Type_Changement> dtcList;
	
	public Boolean getFromValidation() {
		return fromValidation;
	}

	public void setFromValidation(Boolean fromValidation) {
		this.fromValidation = fromValidation;
	}

	public Integer getSelectRow() {
		return selectRow;
	}

	public void setSelectRow(Integer selectRow) {
		this.selectRow = selectRow;
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

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		try{
			Date date = DateService.strToDate(dateDemandee);
			String time = heureDemandee + ":00";
			
			String historique = " ";
			Derogation d = DerogationDatabaseService.get(selectRow);
			if (!dtID.equals(d.getIdType().getId())) {
				historique += "type de dérogation, ";
			}
			if (!demandeurID.equals(d.getIdNomDemandeur().getId())) {
				historique += "demandeur, ";
			}
			if (numTele != null) {
				if (!numTele.equals(d.getTelephone())) {
					historique += "telephone, ";
				}
			}
			if (!numARS.equals(d.getNumars())) {
				historique += "maintenance, ";
			}
			if (!appID.equals(d.getIdAppli().getId())) {
				historique += "application, ";
			}
			if (!descriptionText.equals(d.getDescription())) {
				historique += "description, ";
			}
			if (service != null) {
				if (!service.equals(d.getService())) {
					historique += "equipe, ";
				}
			}
			if (realisateur != null) {
				if (!realisateur.equals(d.getRealisateur())) {
					historique += "realisateur, ";
				}
			}
			if (!clientT.equals(d.getClientTouche())) {
				historique += "client touché, ";
			}
			if (!serviceImp.equals(d.getServiceImpact())) {
				historique += "service impact, ";
			}
			if (!rTP.equals(d.getTp())) {
				historique += "TP, ";
			}
			if (!etude.equals(d.getEtude())) {
				historique += "etude, ";
			}
			if (!retourArriere.equals(d.getRetourArriere())) {
				historique += "retour arrière, ";
			}
			if (!externe.equals(d.getExterne())) {
				historique += "externe, ";
			}
			if (!recette.equals(d.getRecette())) {
				historique += "recette, ";
			}
			if (!date.equals(d.getTimeDemande())) {
				historique += "date, ";
			}
			if (!time.equals(d.getHeureDemande())) {
				historique += "heure, ";
			}
			if (!dtcID.equals(d.getTypeChangement().getId())) {
				historique += "type de changement, ";
			}
			if (!justificatif.equals(d.getJustificatif())) {
				historique += "justificatif, ";
			}
			DerogationDatabaseService.modify(selectRow, dtID, demandeurID, numTele, appID, service, realisateur, descriptionText, clientT, serviceImp, numARS, rTP, etude, retourArriere, dtcID, externe, recette, justificatif, date, time);
			
			HistoriqueDatabaseService.create(null, MessageFormat.format(getText("historique.derogation.modification"), new Object[]{historique, selectRow}), (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_DEROGATION);
			info = MessageFormat.format(getText("derogation.modification.valide"), new Object[]{selectRow});
			
			if(fromValidation){
				Derogation derogation = DerogationDatabaseService.get(selectRow);
				
				//adresses mails
				String[] emails = new String[2];
				emails[0] = derogation.getIdNomCreateur().getEmail();
				emails[1] = derogation.getIdNomDemandeur().getEmail();
				
				//constitution des paramètres pour le mail
				String[] content = new String [20];
				content[0] = derogation.getId().toString();
				content[1] = derogation.getNumars();
				content[2] = derogation.getIdType().getType();
				content[3] = derogation.getIdNomDemandeur().getNom() + " " + derogation.getIdNomDemandeur().getPrenom();
				content[4] = derogation.getIdNomCreateur().getNom() + " " + derogation.getIdNomCreateur().getPrenom();
				content[5] = derogation.getTelephone() == null ? "" : derogation.getTelephone();
				content[6] = derogation.getIdAppli().getApplicatif();
				content[7] = derogation.getDescription();
				content[8] = derogation.getService()  == null ? "" : derogation.getService();
				content[9] = derogation.getRealisateur()  == null ? "" : derogation.getRealisateur();
				content[10] = derogation.getClientTouche();
				content[11] = derogation.getServiceImpact();
				content[12] = derogation.getTp() == 0 ? "Non" : "Oui";
				content[13] = derogation.getEtude() == 0 ? "Non" : "Oui";
				content[14] = derogation.getRetourArriere() == 0 ? "Non" : "Oui";
				content[15] = derogation.getExterne() == 0 ? "Non" : "Oui";
				content[16] = derogation.getRecette() == 0 ? "Non" : "Oui";
				content[17] = derogation.getTypeChangement().getTypeChangement();
				content[18] = derogation.getJustificatif();
				content[19] = DateService.dateToStr(derogation.getTimeDemande(), DateService.p1) + " " + derogation.getHeureDemande();
				
				//envoi du mail
				MailService.sendEmail(emails, PilotageConstants.MAILSERVER_DEROGATION_MODIFIER_SUBJECT, PilotageConstants.MAILSERVER_DEROGATION_MODIFIER_TEXT, content, null);
			}
			
			if(fromValidation){
				return VALIDATION;
			}
			else{
				return OK;			
			}
		}
		catch(Exception e){
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Modification d'une dérogation - ", e);
			
			dtList = DerogationTypeDatabaseService.getAll();
			userList = UsersDatabaseService.getAll();
			appList = ApplicatifDatabaseService.getAll();
			dtcList = DerogationTypeChangementDatabaseService.getAll();
			
			return ERROR;
		}
	}

}
