package pilotage.derogation.action;

import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import pilotage.database.applicatif.ApplicatifDatabaseService;
import pilotage.database.derogation.DerogationDatabaseService;
import pilotage.database.derogation.DerogationEtatDatabaseService;
import pilotage.database.derogation.DerogationTypeChangementDatabaseService;
import pilotage.database.filtre.FiltreDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Applicatifs_Liste;
import pilotage.metier.Derogation;
import pilotage.metier.Derogation_Etat;
import pilotage.metier.Derogation_Type_Changement;
import pilotage.metier.Filtre;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;
import pilotage.service.date.DateService;
import pilotage.service.string.StringConverter;
import pilotage.utils.Pagination;

public class ShowDerogationAction extends AbstractAction{

	private static final long serialVersionUID = 4918087969870567460L;

	private String sort;
	private String sens;
	private int page;
	private int nrPages;
	private int nrPerPage;
	
	private String filtreNumero;
	private String filtreDate;
	private Integer filtreAppli;
	private Integer filtreTp;
	private Integer filtreDtc;
	private Integer filtreEtat;
	private JSONObject filtreJson;
	
	private int validForm = 0;
	private String titrePage = "DRG_LST";
	private Filtre filtre;
	private String filtreNumeroBase;
	private String filtreDateBase;
	private Integer filtreAppliBase;
	private Integer filtreTpBase;
	private Integer filtreDtcBase;
	private Integer filtreEtatBase;

	private Pagination<Derogation> pagination;
	
	private List<Derogation> derogationList;
	private List<Applicatifs_Liste> appList;
	private List<Derogation_Type_Changement> dtcList;
	private List<Derogation_Etat> etatList;
	
	
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

	public String getFiltreNumeroBase() {
		return filtreNumeroBase;
	}

	public void setFiltreNumeroBase(String filtreNumeroBase) {
		this.filtreNumeroBase = filtreNumeroBase;
	}

	public String getFiltreDateBase() {
		return filtreDateBase;
	}

	public void setFiltreDateBase(String filtreDateBase) {
		this.filtreDateBase = filtreDateBase;
	}

	public Integer getFiltreAppliBase() {
		return filtreAppliBase;
	}

	public void setFiltreAppliBase(Integer filtreAppliBase) {
		this.filtreAppliBase = filtreAppliBase;
	}

	public Integer getFiltreTpBase() {
		return filtreTpBase;
	}

	public void setFiltreTpBase(Integer filtreTpBase) {
		this.filtreTpBase = filtreTpBase;
	}

	public Integer getFiltreDtcBase() {
		return filtreDtcBase;
	}

	public void setFiltreDtcBase(Integer filtreDtcBase) {
		this.filtreDtcBase = filtreDtcBase;
	}

	public Integer getFiltreEtatBase() {
		return filtreEtatBase;
	}

	public void setFiltreEtatBase(Integer filtreEtatBase) {
		this.filtreEtatBase = filtreEtatBase;
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
		
		try {
			//récupération des filtres et paginations
			if(page < 1)
				page = 1;
			else if(nrPages != 0 && page > nrPages)
				page = nrPages;
			if(nrPerPage == 0)
				nrPerPage = PilotageConstants.NB_USERS_PER_PAGE;
			if(sens == null || "".equals(sens))
				sens = "desc";
			if(sort == null || "".equals(sort))
				sort = "timeDemande";
			pagination = new Pagination<Derogation>(page, nrPerPage);
			appList = ApplicatifDatabaseService.getAll();
			dtcList = DerogationTypeChangementDatabaseService.getAll();
			etatList = DerogationEtatDatabaseService.getAll();
			
			//gestion du filtre
			Users userLogged = (Users)session.get(PilotageConstants.USER_LOGGED);
			Integer userLoggedId = userLogged.getId();
			filtre = FiltreDatabaseService.getFiltre(userLoggedId,titrePage);
			
			if (filtre != null){
				try {
					Integer filtreId = filtre.getId();
					reloadFiltreBase(filtre.getFiltreString());
					//récupération de la liste des dérogations
					Date date = null;
					if(filtreDate != null && !"".equals(filtreDate))
						date = DateService.strToDate(filtreDate);
					
					Integer numero = null;			
					if(filtreNumero != null && !"".equals(filtreNumero)) 
						numero = Integer.parseInt(filtreNumero);
					
					derogationList = DerogationDatabaseService.getAllDerogation(pagination, sort, sens, numero, date, filtreAppli, filtreTp, filtreDtc, filtreEtat);
					//filtre
					filtreJson = ShowDerogationAction.filtreToString(filtreNumero, filtreDate, filtreAppli, filtreTp, filtreDtc, filtreEtat);
					
					if (validForm == 1){
						if (filtreNumeroBase != filtreNumero || filtreDateBase != filtreDate || filtreAppliBase != filtreAppli || filtreTpBase != filtreTp || filtreDtcBase != filtreDtc || filtreEtatBase != filtreEtat){
							filtreJson = ShowDerogationAction.filtreToString(filtreNumero, filtreDate, filtreAppli, filtreTp, filtreDtc, filtreEtat);
							if (filtreJson != null){
								FiltreDatabaseService.update(filtreId, filtreJson.toString());
							} else
								FiltreDatabaseService.update(filtreId, null);
						}
					}
				} catch (Exception e) {
					error = getText("error.message.generique") + " : " + e.getMessage();
					erreurLogger.error("Update de filtre liste des dérogations", e);
				}
			}
			else{
				try {
					//récupération de la liste des dérogations
					Date date = null;
					if(filtreDate != null && !"".equals(filtreDate))
						date = DateService.strToDate(filtreDate);
					
					Integer numero = null;			
					if(filtreNumero != null && !"".equals(filtreNumero)) 
						numero = Integer.parseInt(filtreNumero);
					
					derogationList = DerogationDatabaseService.getAllDerogation(pagination, sort, sens, numero, date, filtreAppli, filtreTp, filtreDtc, filtreEtat);
					//filtre
					filtreJson = ShowDerogationAction.filtreToString(filtreNumero, filtreDate, filtreAppli, filtreTp, filtreDtc, filtreEtat);
					if (validForm == 1){
						if (filtreNumero != null || filtreDate != null || filtreAppli != null || filtreTp != null || filtreDtc != null || filtreEtat != null){
							FiltreDatabaseService.create(userLoggedId, titrePage, filtreJson != null ? filtreJson.toString() : null);
						}
					}
				} catch (Exception e) {
					error = getText("error.message.generique") + " : " + e.getMessage();
					erreurLogger.error("Creation de filtre liste des dérogations", e);
				}
			}
			
			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Liste des dérogations - ", e);
			return ERROR;
		}		
	}

	public void reloadFiltreBase(String filtreString){
		if (filtreString != null){
			filtreJson = JSONObject.fromObject(filtreString);
			if (filtreJson.containsKey("Numero")){
				setFiltreNumeroBase(filtreJson.getString("Numero"));
			}
			if (filtreJson.containsKey("Date")){
				setFiltreDateBase(filtreJson.getString("Date"));
			}
			if (filtreJson.containsKey("Applicatif")){
				for(Applicatifs_Liste aListe : appList){
					if(aListe.getApplicatif().equalsIgnoreCase(filtreJson.getString("Applicatif"))){
						setFiltreAppliBase(aListe.getId());
					}
				}
			}
			if (filtreJson.containsKey("TP")){
				if (filtreJson.getString("TP").equalsIgnoreCase("Non")){
					setFiltreTpBase(0);
				}
				if (filtreJson.getString("TP").equalsIgnoreCase("Oui")){
					setFiltreTpBase(1);
				}
			}
			if (filtreJson.containsKey("Type de changement")){
				for(Derogation_Type_Changement dtc : dtcList){
					if(dtc.getTypeChangement().equalsIgnoreCase(filtreJson.getString("Type de changement"))){
						setFiltreDtcBase(dtc.getId());
					}
				}
			}
			if (filtreJson.containsKey("Etat")){
				for(Derogation_Etat deroEtat : etatList){
					if(deroEtat.getEtat().equalsIgnoreCase(filtreJson.getString("Etat"))){
						setFiltreEtatBase(deroEtat.getId());
					}
				}
			}
			
			if (validForm == 0){
				setFiltreNumero(filtreNumeroBase);
				setFiltreDate(filtreDateBase);
				setFiltreAppli(filtreAppliBase);
				setFiltreTp(filtreTpBase);
				setFiltreDtc(filtreDtcBase);
				setFiltreEtat(filtreEtatBase);
			}
		}
	}
	
	public static JSONObject filtreToString(String filtreNumero, String filtreDate, Integer filtreAppli, Integer filtreTp, Integer filtreDtc, Integer etat){
		StringBuffer buffer = new StringBuffer();
		if (filtreNumero != null && !filtreNumero.equals("")) {
			buffer.append(StringConverter.toJson("Numero", filtreNumero));
		}
		if (filtreDate != null && !filtreDate.equals("")) {
			buffer.append(StringConverter.toJson("Date", filtreDate));
		}
		if (filtreAppli != null && filtreAppli >= 0) {
			Applicatifs_Liste appli = ApplicatifDatabaseService.get(filtreAppli);
			buffer.append(StringConverter.toJson("Applicatif", appli.getApplicatif()));
		}
		if (filtreTp != null && filtreTp >= 0) {
			buffer.append(StringConverter.toJson("TP", filtreTp == 0 ? "Non" : "Oui"));
		}
		if (filtreDtc != null && filtreDtc >= 0) {
			Derogation_Type_Changement typeChangement = DerogationTypeChangementDatabaseService.get(filtreDtc);
			buffer.append(StringConverter.toJson("Type de changement", typeChangement.getTypeChangement()));
		}
		if (etat != null && etat >= 0) {
			Derogation_Etat derogEtat = DerogationEtatDatabaseService.get(etat);
			buffer.append(StringConverter.toJson("Etat", derogEtat.getEtat()));
		}
		if (buffer.length() != 0){
			String result = "{" + buffer.substring(0,buffer.lastIndexOf(PilotageConstants.SEPARATEUR_3)) + "}";
			JSONObject jsonFiltre = JSONObject.fromObject(result);
			return jsonFiltre;
			
		} else 
			return null;
	}
}
	
	
