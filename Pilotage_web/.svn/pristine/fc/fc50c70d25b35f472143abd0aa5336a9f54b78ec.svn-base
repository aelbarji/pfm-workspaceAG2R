package pilotage.incidents.qualite;

import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import pilotage.database.filtre.FiltreDatabaseService;
import pilotage.database.incidents.IncidentsQualiteDatabaseService;
import pilotage.database.incidents.IncidentsQualiteStatutDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Filtre;
import pilotage.metier.Incidents_Qualite;
import pilotage.metier.Incidents_Qualite_Statut;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;
import pilotage.service.date.DateService;
import pilotage.service.string.StringConverter;
import pilotage.utils.Pagination;

public class ShowFicheIncidentsQualiteAction extends AbstractAction {

	private static final long serialVersionUID = 8694865648435624466L;
	private Date dateEvenement;
	private Date dateResolution;
	private String description;
	private String cause_raison;
	private String incidence;
	private String echeance;
	private List<Incidents_Qualite_Statut>  statutList; 
	
	private Integer provenance;
	
	private String filtreDate;
	private String filtreDescription;
	private String filtreCause;
	private String filtreIncidence;
	private String filtreEcheance;
	private Integer filtreStatut;
	private String filtreDateRes;
	private String filtreString;
	private JSONObject filtreJson;
	
	private int validForm = 0;
	private String titrePage = "ICD_QLT";
	private Filtre filtre;
	private String filtreDateBase;
	private String filtreDescriptionBase;
	private String filtreCauseBase;
	private String filtreIncidenceBase;
	private String filtreEcheanceBase;
	private Integer filtreStatutBase;
	private String filtreDateResBase;
	
	private String sortFiche;
	private String sensFiche;
	private int pageFiche;
	private int nrPagesFiche;
	private int nrPerPageFiche;
	
	private String sort;
	private String sens;
	private int page;
	private int nrPages;
	private int nrPerPage;
	private Pagination<Incidents_Qualite> pagination;
	private List<Incidents_Qualite> listFicheIncidentsQualite;
	
	public Integer getProvenance() {
		return provenance;
	}

	public void setProvenance(Integer provenance) {
		this.provenance = provenance;
	}

	public String getSortFiche() {
		return sortFiche;
	}

	public void setSortFiche(String sortFiche) {
		this.sortFiche = sortFiche;
	}

	public String getSensFiche() {
		return sensFiche;
	}

	public void setSensFiche(String sensFiche) {
		this.sensFiche = sensFiche;
	}

	public int getPageFiche() {
		return pageFiche;
	}

	public void setPageFiche(int pageFiche) {
		this.pageFiche = pageFiche;
	}
	
	public JSONObject getFiltreJson() {
		return filtreJson;
	}

	public void setFiltreJson(JSONObject filtreJson) {
		this.filtreJson = filtreJson;
	}
	
	public int getNrPagesFiche() {
		return nrPagesFiche;
	}

	public void setNrPagesFiche(int nrPagesFiche) {
		this.nrPagesFiche = nrPagesFiche;
	}

	public int getNrPerPageFiche() {
		return nrPerPageFiche;
	}

	public void setNrPerPageFiche(int nrPerPageFiche) {
		this.nrPerPageFiche = nrPerPageFiche;
	}

	public String getFiltreDate() {
		return filtreDate;
	}

	public void setFiltreDate(String filtreDate) {
		if(! "".equals(filtreDate))
			this.filtreDate = filtreDate;
	}

	public String getFiltreDescription() {
		return filtreDescription;
	}

	public void setFiltreDescription(String filtreDescription) {
		this.filtreDescription = filtreDescription;
	}

	public String getFiltreCause() {
		return filtreCause;
	}

	public void setFiltreCause(String filtreCause) {
		this.filtreCause = filtreCause;
	}

	public String getFiltreIncidence() {
		return filtreIncidence;
	}

	public void setFiltreIncidence(String filtreIncidence) {
		this.filtreIncidence = filtreIncidence;
	}

	public String getFiltreEcheance() {
		return filtreEcheance;
	}

	public void setFiltreEcheance(String filtreEcheance) {
		this.filtreEcheance = filtreEcheance;
	}

	public Integer getFiltreStatut() {
		return filtreStatut;
	}

	public void setFiltreStatut(Integer filtreStatut) {
		this.filtreStatut = filtreStatut;
	}

	public String getFiltreDateRes() {
		return filtreDateRes;
	}

	public void setFiltreDateRes(String filtreDateRes) {
		if(! "".equals(filtreDateRes))
			this.filtreDateRes = filtreDateRes;
	}

	public String getFiltreString() {
		return filtreString;
	}

	public void setFiltreString(String filtreString) {
		this.filtreString = filtreString;
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

	public String getFiltreDateBase() {
		return filtreDateBase;
	}

	public void setFiltreDateBase(String filtreDateBase) {
		this.filtreDateBase = filtreDateBase;
	}

	public String getFiltreDescriptionBase() {
		return filtreDescriptionBase;
	}

	public void setFiltreDescriptionBase(String filtreDescriptionBase) {
		this.filtreDescriptionBase = filtreDescriptionBase;
	}

	public String getFiltreCauseBase() {
		return filtreCauseBase;
	}

	public void setFiltreCauseBase(String filtreCauseBase) {
		this.filtreCauseBase = filtreCauseBase;
	}

	public String getFiltreIncidenceBase() {
		return filtreIncidenceBase;
	}

	public void setFiltreIncidenceBase(String filtreIncidenceBase) {
		this.filtreIncidenceBase = filtreIncidenceBase;
	}

	public String getFiltreEcheanceBase() {
		return filtreEcheanceBase;
	}

	public void setFiltreEcheanceBase(String filtreEcheanceBase) {
		this.filtreEcheanceBase = filtreEcheanceBase;
	}

	public Integer getFiltreStatutBase() {
		return filtreStatutBase;
	}

	public void setFiltreStatutBase(Integer filtreStatutBase) {
		this.filtreStatutBase = filtreStatutBase;
	}

	public String getFiltreDateResBase() {
		return filtreDateResBase;
	}

	public void setFiltreDateResBase(String filtreDateResBase) {
		this.filtreDateResBase = filtreDateResBase;
	}

	public List<Incidents_Qualite> getListFicheIncidentsQualite() {
		return listFicheIncidentsQualite;
	}

	public void setListFicheIncidentsQualite(List<Incidents_Qualite> listFicheIncidentsQualite) {
		this.listFicheIncidentsQualite = listFicheIncidentsQualite;
	}

	public Date getDateEvenement() {
		return dateEvenement;
	}

	public void setDateEvenement(Date dateEvenement) {
		this.dateEvenement = dateEvenement;
	}

	public Date getDateResolution() {
		return dateResolution;
	}

	public void setDateResolution(Date dateResolution) {
		this.dateResolution = dateResolution;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCause_raison() {
		return cause_raison;
	}

	public void setCause_raison(String cause_raison) {
		this.cause_raison = cause_raison;
	}

	public String getIncidence() {
		return incidence;
	}

	public void setIncidence(String incidence) {
		this.incidence = incidence;
	}

	public String getEcheance() {
		return echeance;
	}

	public void setEcheance(String echeance) {
		this.echeance = echeance;
	}

	public List<Incidents_Qualite_Statut> getStatutList() {
		return statutList;
	}

	public void setStatutList(List<Incidents_Qualite_Statut> statutList) {
		this.statutList = statutList;
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

	public Pagination<Incidents_Qualite> getPagination() {
		return pagination;
	}

	public void setPagination(Pagination<Incidents_Qualite> pagination) {
		this.pagination = pagination;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	protected String executeMetier() {
		try{
			if(provenance != null && provenance == 1){
				sort = sortFiche;
				sens = sensFiche;
				page = pageFiche;
				nrPages = nrPagesFiche;
				nrPerPage = nrPerPageFiche;
			}

			if(page < 1)
				page = 1;
			else if(nrPages != 0 && page > nrPages)
				page = nrPages;		
			if(nrPerPage == 0)
				nrPerPage = PilotageConstants.NB_FICHE_INCIDENTS_QUALITE_PER_PAGE;
			if(sens == null || "".equals(sens))
				sens = "desc";
			if(sort == null || "".equals(sort))
				sort = "dateEvenement";
			
			Date date = null;
			if(filtreDate != null && !"".equals(filtreDate))
				date = DateService.strToDate(filtreDate);
			
			Date dateRes = null;
			if(filtreDateRes != null && !"".equals(filtreDateRes))
				dateRes = DateService.strToDate(filtreDateRes);

			pagination = new Pagination<Incidents_Qualite>(page, nrPerPage);
			statutList = IncidentsQualiteStatutDatabaseService.getAll();
			
			//gestion du filtre
			Users userLogged = (Users)session.get(PilotageConstants.USER_LOGGED);
			Integer userLoggedId = userLogged.getId();
			filtre = FiltreDatabaseService.getFiltre(userLoggedId,titrePage);
			
			if (filtre != null){
				try {
					Integer filtreId = filtre.getId();
					reloadFiltreBase(filtre.getFiltreString());
					listFicheIncidentsQualite = IncidentsQualiteDatabaseService.getAllFicheIncidentQualite(pagination, sort, sens, date, filtreDescription, filtreCause, filtreIncidence, filtreEcheance, filtreStatut, dateRes);	
					filtreJson = ShowFicheIncidentsQualiteAction.filtreToString(filtreDate, filtreDescription, filtreCause, filtreIncidence, filtreEcheance, filtreStatut, filtreDateRes);
					
					if (validForm == 1){
						if (filtreDateBase != filtreDate || filtreDescriptionBase != filtreDescription || filtreCauseBase != filtreCause || filtreIncidenceBase != filtreIncidence || filtreEcheanceBase != filtreEcheance || filtreStatutBase != filtreStatut || filtreDateResBase != filtreDateRes){
							filtreJson = ShowFicheIncidentsQualiteAction.filtreToString(filtreDate, filtreDescription, filtreCause, filtreIncidence, filtreEcheance, filtreStatut, filtreDateRes);
							if (filtreJson != null){
								FiltreDatabaseService.update(filtreId, filtreJson.toString());
							} else
								FiltreDatabaseService.update(filtreId, null);
						}
					}
				} catch (Exception e) {
					error = getText("error.message.generique") + " : " + e.getMessage();
					erreurLogger.error("Update de filtre fiche incidents qualité", e);
				}
				if (filtreJson != null)
					filtreString = StringConverter.afficheFiltre(filtreJson.toString());
			}
			else{
				try {
					listFicheIncidentsQualite = IncidentsQualiteDatabaseService.getAllFicheIncidentQualite(pagination, sort, sens, date, filtreDescription, filtreCause, filtreIncidence, filtreEcheance, filtreStatut, dateRes);	
					filtreJson = ShowFicheIncidentsQualiteAction.filtreToString(filtreDate, filtreDescription, filtreCause, filtreIncidence, filtreEcheance, filtreStatut, filtreDateRes);
					if (validForm == 1){
						if (filtreDate != null || filtreDescription != null || filtreCause != null || filtreIncidence != null || filtreEcheance != null || filtreStatut!= null || filtreDateRes != null){
							FiltreDatabaseService.create(userLoggedId, titrePage, filtreJson != null ? filtreJson.toString() : null);
						}
					}
				} catch (Exception e) {
					error = getText("error.message.generique") + " : " + e.getMessage();
					erreurLogger.error("Creation de filtre fiche incidents qualité", e);
				}
			}

			return OK;
			
		} catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Affichage liste fiche incidents qualité - ", e);
			return ERROR;	
		}
	}
	
	public void reloadFiltreBase(String filtreString){
		filtreJson = JSONObject.fromObject(filtreString);
		if (filtreJson != null){
			if (filtreJson.containsKey("Date")){
				setFiltreDateBase(filtreJson.getString("Date"));
			}
			if (filtreJson.containsKey("Description")){
				setFiltreDescriptionBase(filtreJson.getString("Description"));
			}
			if (filtreJson.containsKey("Cause")){
				setFiltreDescriptionBase(filtreJson.getString("Cause"));
			}
			if (filtreJson.containsKey("Incidence")){
				setFiltreDescriptionBase(filtreJson.getString("Incidence"));
			}
			if (filtreJson.containsKey("Echéance")){
				setFiltreDescriptionBase(filtreJson.getString("Echéance"));
			}
			if (filtreJson.containsKey("Statut")){
				for(Incidents_Qualite_Statut statut : statutList){
					if(statut.getStatut().equalsIgnoreCase(filtreJson.getString("Statut"))){
						setFiltreStatutBase(statut.getId());
					}
				}
			}
			if (filtreJson.containsKey("Date résolution")){
				setFiltreDateResBase(filtreJson.getString("Date résolution"));
			}
			
			if (validForm == 0){
				setFiltreDate(filtreDateBase);
				setFiltreDescription(filtreDescriptionBase);
				setFiltreCause(filtreCauseBase);
				setFiltreIncidence(filtreIncidenceBase);
				setFiltreEcheance(filtreEcheanceBase);
				setFiltreStatut(filtreStatutBase);
				setFiltreDateRes(filtreDateResBase);
			}
		}
	}
	
	
	public static JSONObject filtreToString(String filtreDate, String filtreDescription, String filtreCause, String filtreIncidence, String filtreEcheance, Integer filtreStatut, String filtreDateRes){
		StringBuffer buffer = new StringBuffer();
		if (filtreDate != null && !filtreDate.equals("")) {
			buffer.append(StringConverter.toJson("Date", filtreDate));
		}
		if (filtreDescription != null && !filtreDescription.equals("")) {
			buffer.append(StringConverter.toJson("Description", filtreDescription));
		}
		if (filtreCause != null && !filtreCause.equals("")) {
			buffer.append(StringConverter.toJson("Cause", filtreCause));
		}
		if (filtreIncidence != null && !filtreIncidence.equals("")) {
			buffer.append(StringConverter.toJson("Incidence", filtreIncidence));
		}
		if (filtreEcheance != null && !filtreEcheance.equals("")) {
			buffer.append(StringConverter.toJson("Echeance", filtreEcheance));
		}
		if (filtreStatut != null && filtreStatut >= 0) {
			Incidents_Qualite_Statut statut = IncidentsQualiteStatutDatabaseService.get(filtreStatut);
			buffer.append(StringConverter.toJson("Statut", statut.getStatut()));
		}
		if (filtreDateRes != null && !filtreDateRes.equals("")) {
			buffer.append(StringConverter.toJson("Date résolution", filtreDateRes));
		}
		if (buffer.length() != 0){
			String result = "{" + buffer.substring(0, buffer.lastIndexOf(PilotageConstants.SEPARATEUR_3)) + "}";
			JSONObject jsonFiltre = JSONObject.fromObject(result);
			return jsonFiltre;
		}
		return null;
	}
}
