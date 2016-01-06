package pilotage.incidents.base;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;
import pilotage.database.applicatif.ApplicatifDatabaseService;
import pilotage.database.environnement.EnvironnementDatabaseService;
import pilotage.database.filtre.FiltreDatabaseService;
import pilotage.database.incidents.IncidentsDatabaseService;
import pilotage.database.incidents.IncidentsTypesDatabaseService;
import pilotage.database.machine.MachinesListesDatabaseService;
import pilotage.database.users.management.UsersDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.incidents.AbstractIncidentsAction;
import pilotage.metier.Applicatifs_Liste;
import pilotage.metier.Environnement;
import pilotage.metier.Filtre;
import pilotage.metier.Hardware_Software;
import pilotage.metier.Incidents;
import pilotage.metier.Incidents_Type;
import pilotage.metier.Machines_Liste;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;
import pilotage.service.date.DateService;
import pilotage.service.string.StringConverter;
import pilotage.utils.Pagination;

public class ShowBaseIncidentsAction extends AbstractAction {

	private static final long serialVersionUID = -6551867967856314345L;
	protected List<Incidents> listIncidents;
	protected Map<Integer, List<Applicatifs_Liste>> appMap;
	private Map<Integer, List<Hardware_Software>> hardMap;

	private int pageIncident;
	private int nrPagesIncident;
	private int nrPerPageIncident;

	private Pagination<Incidents> paginationIncident;
	
	protected Integer filtrePilote;
	protected Date filtreDate;
	protected Date filtreDateFin;
	protected Integer filtreServeur;
	protected Integer filtreApplicatif;
	protected Integer filtreEnvironnement;
	protected Integer filtreType;
	protected Integer filtreCoupureService;
	protected Integer filtreResoluPilotage;
	protected Integer filtreHasAstreinte;
	protected String filtreObservation;
	protected String filtreArs;
	
	private int validForm = 0;
	private boolean redir;
	private boolean stat;
	private Integer isredir;
	private String firstDayOfMonth;
	private String lastDayOfMonth;
	private Integer coupure;
	private Integer resoluPil;
	private Integer type;
	private Integer hasAstreinte;
	private String titrePage = "ICD_LST";
	private Filtre filtre;
	private Integer nbIncidents;
	protected Integer filtrePiloteBase;
	protected Date filtreDateBase;
	protected Date filtreDateFinBase;
	protected Integer filtreServeurBase;
	protected Integer filtreApplicatifBase;
	protected Integer filtreEnvironnementBase;
	protected Integer filtreCoupureServiceBase;
	protected Integer filtreHasAstreinteBase;
	protected Integer filtreResoluPilBase;
	protected String filtreObservationBase;
	protected String filtreArsBase;
	private JSONObject filtreJson;
	
	private String provenance;
	
	public String getFiltreArs() {
		return filtreArs;
	}

	public void setFiltreArs(String filtreArs) {
		this.filtreArs = filtreArs;
	}

	public String getFiltreArsBase() {
		return filtreArsBase;
	}

	public void setFiltreArsBase(String filtreArsBase) {
		this.filtreArsBase = filtreArsBase;
	}

	protected String filtreString;
	
	protected List<Users> listPilote;
	protected List<Applicatifs_Liste> listApplicatif;
	protected List<Environnement> listEnvironnement;
	protected List<Machines_Liste> listServeur;
	protected List<Incidents_Type> listType;
	protected String showResult;
	
	
	public List<Incidents> getListIncidents() {
		return listIncidents;
	}

	public void setListIncidents(List<Incidents> listIncidents) {
		this.listIncidents = listIncidents;
	}

	public Map<Integer, List<Applicatifs_Liste>> getAppMap() {
		return appMap;
	}

	public void setAppMap(Map<Integer, List<Applicatifs_Liste>> appMap) {
		this.appMap = appMap;
	}

	public int getPageIncident() {
		return pageIncident;
	}

	public void setPageIncident(int pageIncident) {
		this.pageIncident = pageIncident;
	}

	public int getNrPagesIncident() {
		return nrPagesIncident;
	}

	public void setNrPagesIncident(int nrPagesIncident) {
		this.nrPagesIncident = nrPagesIncident;
	}

	public int getNrPerPageIncident() {
		return nrPerPageIncident;
	}

	public void setNrPerPageIncident(int nrPerPageIncident) {
		this.nrPerPageIncident = nrPerPageIncident;
	}

	public Pagination<Incidents> getPaginationIncident() {
		return paginationIncident;
	}

	public void setPaginationIncident(Pagination<Incidents> paginationIncident) {
		this.paginationIncident = paginationIncident;
	}

	public Integer getFiltrePilote() {
		return filtrePilote;
	}
	
	public void setFiltrePilote(Integer filtrePilote) {
		this.filtrePilote = filtrePilote;
	}

	public Date getFiltreDate() {
		return filtreDate;
	}

	public void setFiltreDate(Date filtreDate) {
		this.filtreDate = filtreDate;
	}
	
	public Date getFiltreDateFin() {
		return filtreDateFin;
	}

	public void setFiltreDateFin(Date filtreDateFin) {
		this.filtreDateFin = filtreDateFin;
	}

	public Integer getFiltreServeur() {
		return filtreServeur;
	}

	public void setFiltreServeur(Integer filtreServeur) {
		this.filtreServeur = filtreServeur;
	}

	public Integer getFiltreApplicatif() {
		return filtreApplicatif;
	}

	public void setFiltreApplicatif(Integer filtreApplicatif) {
		this.filtreApplicatif = filtreApplicatif;
	}

	public Integer getFiltreEnvironnement() {
		return filtreEnvironnement;
	}

	public void setFiltreEnvironnement(Integer filtreEnvironnement) {
		this.filtreEnvironnement = filtreEnvironnement;
	}

	public Integer getFiltreCoupureService() {
		return filtreCoupureService;
	}

	public void setFiltreCoupureService(Integer filtreCoupureService) {
		this.filtreCoupureService = filtreCoupureService;
	}

	public String getFiltreObservation() {
		return filtreObservation;
	}

	public void setFiltreObservation(String filtreObservation) {
		this.filtreObservation = filtreObservation;
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

	public Integer getNbIncidents() {
		return nbIncidents;
	}

	public void setNbIncidents(Integer nbIncidents) {
		this.nbIncidents = nbIncidents;
	}

	public Integer getFiltrePiloteBase() {
		return filtrePiloteBase;
	}

	public void setFiltrePiloteBase(Integer filtrePiloteBase) {
		this.filtrePiloteBase = filtrePiloteBase;
	}

	public Date getFiltreDateBase() {
		return filtreDateBase;
	}

	public void setFiltreDateBase(Date filtreDateBase) {
		this.filtreDateBase = filtreDateBase;
	}

	public Date getFiltreDateFinBase() {
		return filtreDateFinBase;
	}

	public void setFiltreDateFinBase(Date filtreDateFinBase) {
		this.filtreDateFinBase = filtreDateFinBase;
	}

	public Integer getFiltreResoluPilBase() {
		return filtreResoluPilBase;
	}

	public void setFiltreResoluPilBase(Integer filtreResoluPilBase) {
		this.filtreResoluPilBase = filtreResoluPilBase;
	}

	public Integer getFiltreServeurBase() {
		return filtreServeurBase;
	}

	public void setFiltreServeurBase(Integer filtreServeurBase) {
		this.filtreServeurBase = filtreServeurBase;
	}

	public Integer getFiltreApplicatifBase() {
		return filtreApplicatifBase;
	}

	public void setFiltreApplicatifBase(Integer filtreApplicatifBase) {
		this.filtreApplicatifBase = filtreApplicatifBase;
	}

	public Integer getFiltreEnvironnementBase() {
		return filtreEnvironnementBase;
	}

	public void setFiltreEnvironnementBase(Integer filtreEnvironnementBase) {
		this.filtreEnvironnementBase = filtreEnvironnementBase;
	}

	public Integer getFiltreCoupureServiceBase() {
		return filtreCoupureServiceBase;
	}

	public void setFiltreCoupureServiceBase(Integer filtreCoupureServiceBase) {
		this.filtreCoupureServiceBase = filtreCoupureServiceBase;
	}

	public String getFiltreObservationBase() {
		return filtreObservationBase;
	}

	public void setFiltreObservationBase(String filtreObservationBase) {
		this.filtreObservationBase = filtreObservationBase;
	}

	public String getFiltreString() {
		return filtreString;
	}

	public void setFiltreString(String filtreString) {
		this.filtreString = filtreString;
	}

	public List<Users> getListPilote() {
		return listPilote;
	}

	public void setListPilote(List<Users> listPilote) {
		this.listPilote = listPilote;
	}

	public List<Applicatifs_Liste> getListApplicatif() {
		return listApplicatif;
	}

	public void setListApplicatif(List<Applicatifs_Liste> listApplicatif) {
		this.listApplicatif = listApplicatif;
	}

	public List<Environnement> getListEnvironnement() {
		return listEnvironnement;
	}

	public void setListEnvironnement(List<Environnement> listEnvironnement) {
		this.listEnvironnement = listEnvironnement;
	}

	public List<Machines_Liste> getListServeur() {
		return listServeur;
	}

	public void setListServeur(List<Machines_Liste> listServeur) {
		this.listServeur = listServeur;
	}

	public String getShowResult() {
		return showResult;
	}

	public void setShowResult(String showResult) {
		this.showResult = showResult;
	}

	public Integer getFiltreResoluPilotage() {
		return filtreResoluPilotage;
	}

	public void setFiltreResoluPilotage(Integer filtreResoluPilotage) {
		this.filtreResoluPilotage = filtreResoluPilotage;
	}

	public boolean isRedir() {
		return redir;
	}

	public void setRedir(boolean redir) {
		this.redir = redir;
	}

	public String getFirstDayOfMonth() {
		return firstDayOfMonth;
	}

	public void setFirstDayOfMonth(String firstDayOfMonth) {
		this.firstDayOfMonth = firstDayOfMonth;
	}

	public String getLastDayOfMonth() {
		return lastDayOfMonth;
	}

	public void setLastDayOfMonth(String lastDayOfMonth) {
		this.lastDayOfMonth = lastDayOfMonth;
	}

	public Integer getCoupure() {
		return coupure;
	}

	public void setCoupure(Integer coupure) {
		this.coupure = coupure;
	}

	public Integer getResoluPil() {
		return resoluPil;
	}

	public void setResoluPil(Integer resoluPil) {
		this.resoluPil = resoluPil;
	}

	public List<Incidents_Type> getListType() {
		return listType;
	}

	public void setListType(List<Incidents_Type> listType) {
		this.listType = listType;
	}

	public Integer getFiltreType() {
		return filtreType;
	}

	public void setFiltreType(Integer filtreType) {
		this.filtreType = filtreType;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getHasAstreinte() {
		return hasAstreinte;
	}

	public void setHasAstreinte(Integer hasAstreinte) {
		this.hasAstreinte = hasAstreinte;
	}

	public Integer getFiltreHasAstreinte() {
		return filtreHasAstreinte;
	}

	public void setFiltreHasAstreinte(Integer filtreHasAstreinte) {
		this.filtreHasAstreinte = filtreHasAstreinte;
	}

	public Integer getIsredir() {
		return isredir;
	}

	public void setIsredir(Integer isredir) {
		this.isredir = isredir;
	}

	public String getProvenance() {
		return provenance;
	}

	public void setProvenance(String provenance) {
		this.provenance = provenance;
	}

	public boolean isStat() {
		return stat;
	}

	public void setStat(boolean stat) {
		this.stat = stat;
	}

	public JSONObject getFiltreJson() {
		return filtreJson;
	}

	public void setFiltreJson(JSONObject filtreJson) {
		this.filtreJson = filtreJson;
	}

	public Map<Integer, List<Hardware_Software>> getHardMap() {
		return hardMap;
	}

	public void setHardMap(Map<Integer, List<Hardware_Software>> hardMap) {
		this.hardMap = hardMap;
	}

	public Integer getFiltreHasAstreinteBase() {
		return filtreHasAstreinteBase;
	}

	public void setFiltreHasAstreinteBase(Integer filtreHasAstreinteBase) {
		this.filtreHasAstreinteBase = filtreHasAstreinteBase;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		try{
			if(pageIncident < 1)
				pageIncident = 1;
			else if(nrPagesIncident != 0 && pageIncident > nrPagesIncident)
				pageIncident = nrPagesIncident;		
			if(nrPerPageIncident == 0)
				nrPerPageIncident = PilotageConstants.NB_MACHINES_PER_PAGE;
			paginationIncident = new Pagination<Incidents>(pageIncident, nrPerPageIncident);
			paginationIncident.setNrPages(nrPagesIncident);
			listPilote = UsersDatabaseService.getAll();
			listApplicatif = ApplicatifDatabaseService.getAll();
			listEnvironnement = EnvironnementDatabaseService.getAll();
			listServeur = MachinesListesDatabaseService.getAll();
			listType = IncidentsTypesDatabaseService.getAll();
			//gestion du filtre
			Users userLogged = (Users)session.get(PilotageConstants.USER_LOGGED);
			Integer userLoggedId = userLogged.getId();
			filtre = FiltreDatabaseService.getFiltre(userLoggedId,titrePage);
			provenance = "showBaseIncidents";
			if(redir){
				showResult = "1";
				validForm = 1;
				if(stat)
					isredir = 1;
				redir = false;
				if(firstDayOfMonth != null && lastDayOfMonth != null && !firstDayOfMonth.equals("") && !lastDayOfMonth.equals("")){
					filtreDate = DateService.strToDate(firstDayOfMonth);
					filtreDateFin = DateService.strToDate(lastDayOfMonth);
					Calendar cfiltreDate = Calendar.getInstance();
					cfiltreDate.setTime(filtreDate);
					cfiltreDate.set(Calendar.HOUR_OF_DAY, 7);
					cfiltreDate.set(Calendar.MINUTE, 30);
					filtreDate = cfiltreDate.getTime();
					
					Calendar cfiltreDateFin = Calendar.getInstance();
					cfiltreDateFin.setTime(filtreDateFin);
					cfiltreDateFin.set(Calendar.HOUR_OF_DAY, 7);
					cfiltreDateFin.set(Calendar.MINUTE, 29);
					cfiltreDateFin.set(Calendar.SECOND, 59);
					filtreDateFin = cfiltreDateFin.getTime();
				}
				if(coupure != null && coupure == 1)
					filtreCoupureService = coupure;
				if(resoluPil != null && resoluPil == 1)
					filtreResoluPilotage = resoluPil;
				if(type != null && type == 3)
					filtreType = type;
				if(hasAstreinte != null && hasAstreinte == 1)
					filtreHasAstreinte = hasAstreinte;
			}
			if (filtre != null){
				try {
					Integer filtreId = filtre.getId();
					reloadFiltreBase(filtre.getFiltreString());
					listIncidents = IncidentsDatabaseService.getAllByFiltre(paginationIncident, filtrePilote, filtreDate, filtreDateFin, filtreServeur, filtreApplicatif, filtreEnvironnement, filtreType,filtreCoupureService, filtreResoluPilotage, filtreHasAstreinte, filtreObservation,filtreArs);
					nbIncidents = paginationIncident.getTotalElements();
					appMap = new HashMap<Integer, List<Applicatifs_Liste>>();
					hardMap = new HashMap<Integer, List<Hardware_Software>>();
					AbstractIncidentsAction.initApplicationMap(appMap, listIncidents);
					AbstractIncidentsAction.initHardwareMap(hardMap, listIncidents);
					filtreJson = filtreToString(filtrePilote, filtreDate, filtreDateFin, filtreServeur, filtreApplicatif, filtreEnvironnement, filtreType, filtreCoupureService, filtreResoluPilotage, filtreHasAstreinte,filtreObservation,filtreArs, listPilote, listServeur, listApplicatif, listEnvironnement, listType);

					if (validForm == 1){
						if (filtrePiloteBase != filtrePilote || filtreDateBase != filtreDate || filtreDateFinBase != filtreDateFin || filtreServeurBase != filtreServeur || filtreApplicatifBase != filtreApplicatif || 
								filtreEnvironnementBase != filtreEnvironnement || filtreCoupureServiceBase != filtreCoupureService || filtreObservationBase != filtreObservation || filtreArsBase != filtreArs || filtreHasAstreinteBase != filtreHasAstreinte || filtreResoluPilBase != filtreResoluPilotage){
							filtreJson = filtreToString(filtrePilote, filtreDate, filtreDateFin, filtreServeur, filtreApplicatif, filtreEnvironnement, filtreType, filtreCoupureService, filtreResoluPilotage, filtreHasAstreinte, filtreObservation,filtreArs, listPilote, listServeur, listApplicatif, listEnvironnement, listType);
							if (filtreJson != null){
								FiltreDatabaseService.update(filtreId, filtreJson.toString());
							} else
								FiltreDatabaseService.update(filtreId, null);
						}
					}
				} catch (Exception e) {
					error = getText("error.message.generique") + " : " + e.getMessage();
					erreurLogger.error("Update de filtre checklist", e);
				}
			}
			else{
				try {
					listIncidents = IncidentsDatabaseService.getAllByFiltre(paginationIncident, filtrePilote, filtreDate, filtreDateFin, filtreServeur, filtreApplicatif, filtreEnvironnement, filtreType, filtreCoupureService, filtreResoluPilotage, filtreHasAstreinte, filtreObservation,filtreArs);
					nbIncidents = paginationIncident.getTotalElements();
					appMap = new HashMap<Integer, List<Applicatifs_Liste>>();
					AbstractIncidentsAction.initApplicationMap(appMap, listIncidents);
					filtreJson = filtreToString(filtrePilote, filtreDate, filtreDateFin, filtreServeur, filtreApplicatif, filtreEnvironnement, filtreType, filtreCoupureService, filtreResoluPilotage, filtreHasAstreinte, filtreObservation,filtreArs, listPilote, listServeur, listApplicatif, listEnvironnement, listType);
					if (validForm == 1){
						if (filtrePilote != null || filtreDate != null || filtreDateFinBase != filtreDateFin || filtreServeur != null || filtreApplicatif != null || filtreEnvironnement != null || filtreCoupureService != null || filtreObservation != null || filtreArs != null){
							FiltreDatabaseService.create(userLoggedId, titrePage, filtreJson != null ? filtreJson.toString() : null);
						}
					}
				} catch (Exception e) {
					error = getText("error.message.generique") + " : " + e.getMessage();
					erreurLogger.error("Creation de filtre checklist", e);
				}
			}
			if (filtreJson != null)
				filtreString = StringConverter.afficheFiltre(filtreJson.toString());
				
			return OK;
		} catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Affichage liste base incidents - ", e);
			return ERROR;	
		}
	}
	
	public void reloadFiltreBase(String filtreString){
		filtreJson = JSONObject.fromObject(filtreString);
		if (filtreJson != null){
			if (filtreJson.containsKey("Pilote")){
				for(Users user : listPilote){
					if(filtreJson.getString("Pilote").equalsIgnoreCase(user.getNom()+" "+user.getPrenom())){
						setFiltrePiloteBase(user.getId());
					}
				}
			}
			if (filtreJson.containsKey("Date debut")){
				Date dateDeb = DateService.strToDate(filtreJson.getString("Date debut"));
				setFiltreDateBase(dateDeb);
			}
			if (filtreJson.containsKey("Date fin")){
				Date dateFin = DateService.strToDate(filtreJson.getString("Date fin"));
				setFiltreDateFinBase(dateFin);  // Fin ajouté 14.08.13
			}
			if (filtreJson.containsKey("Date")){
				Date date = DateService.strToDate(filtreJson.getString("Date"));
				setFiltreDateBase(date);
			}
				
			if (filtreJson.containsKey("Serveur")){
				for(Machines_Liste ml : listServeur){
					if(filtreJson.getString("Serveur").equalsIgnoreCase(ml.getNom())){
						setFiltreServeurBase(ml.getId());
					}
				}
			}
			if (filtreJson.containsKey("Application")){
				for(Applicatifs_Liste al : listApplicatif){
					if(filtreJson.getString("Application").equalsIgnoreCase(al.getApplicatif())){
						setFiltreApplicatifBase(al.getId());
					}
				}
			}
			if (filtreJson.containsKey("Type")){
				for(Incidents_Type it : listType){
					if(filtreJson.getString("Type").equalsIgnoreCase(it.getType())){
						setFiltreApplicatifBase(it.getId());
					}
				}
			}
			if (filtreJson.containsKey("Environnement")){
				for(Environnement env : listEnvironnement){
					if(env.getEnvironnement().equalsIgnoreCase(filtreJson.getString("Environnement"))){
						setFiltreEnvironnementBase(env.getId());
					}
				}
			}
			if (filtreJson.containsKey("Coupure de service")){
				if (filtreJson.getString("Coupure de service").equalsIgnoreCase("Oui")){
					setFiltreCoupureServiceBase(1);
				}
				if (filtreJson.getString("Coupure de service").equalsIgnoreCase("Non")){
					setFiltreCoupureServiceBase(0);
				}
			}

			if (filtreJson.containsKey("Astreinte")){
				if (filtreJson.getString("Astreinte").equalsIgnoreCase("Oui")){
					setFiltreHasAstreinteBase(1);
				}
				if (filtreJson.getString("Astreinte").equalsIgnoreCase("Non")){
					setFiltreHasAstreinteBase(0);
				}
			}
			
			if (filtreJson.containsKey("Resolu par le pilotage")){
				if (filtreJson.getString("Resolu par le pilotage").equalsIgnoreCase("Oui")){
					setFiltreResoluPilBase(1);
				}
				if (filtreJson.getString("Resolu par le pilotage").equalsIgnoreCase("Non")){
					setFiltreResoluPilBase(0);
				}
			}

			if (filtreJson.containsKey("Observation")){
				setFiltreObservationBase(filtreJson.getString("Observation"));
			}
			
			if (filtreJson.containsKey("Ars")){
				setFiltreArsBase(filtreJson.getString("Ars"));
			}
			
			if (validForm == 0){
				setFiltrePilote(filtrePiloteBase);
				setFiltreDate(filtreDateBase);
				setFiltreDateFin(filtreDateFinBase);
				setFiltreServeur(filtreServeurBase);
				setFiltreApplicatif(filtreApplicatifBase);
				setFiltreEnvironnement(filtreEnvironnementBase);
				setFiltreType(filtreType);
				setFiltreCoupureService(filtreCoupureServiceBase);
				setFiltreResoluPilotage(filtreResoluPilBase);
				setFiltreHasAstreinte(filtreHasAstreinteBase);
				setFiltreObservation(filtreObservationBase);
				setFiltreArs(filtreArsBase);
			}
		}
	}
	
	public static JSONObject filtreToString(Integer filtrePilote, Date filtreDate, Date filtreDateFin, Integer filtreServeur, Integer filtreApplicatif, Integer filtreEnvironnement,Integer filtreType, Integer filtreCoupureService, Integer filtreResoluPilotage, Integer filtreHasAstreinte, String filtreObservation,String filtreArs, List<Users> listPilote, List<Machines_Liste> listServeur, List<Applicatifs_Liste> listApplicatif, List<Environnement> listEnvironnement, List<Incidents_Type> listType) {
		StringBuffer buffer = new StringBuffer();
		if (filtrePilote != null && filtrePilote != -1) {
			for(Users u : listPilote){
				if(u.getId().equals(filtrePilote)){
					buffer.append(StringConverter.toJson("Pilote", u.getNom() + " " + u.getPrenom()) );
					break;
				}
			}
		}
		if (filtreDate != null && !filtreDate.equals("")) {
			if(filtreDateFin != null && !filtreDateFin.equals("")){
			    buffer.append(StringConverter.toJson("Date debut", new SimpleDateFormat("dd/MM/yyyy").format(filtreDate)) );
			    buffer.append(StringConverter.toJson("Date fin", new SimpleDateFormat("dd/MM/yyyy").format(filtreDateFin)) );
			}
		}
		if (filtreDate != null && !filtreDate.equals("")) {
			if(filtreDateFin == null){
				buffer.append(StringConverter.toJson("Date", new SimpleDateFormat("dd/MM/yyyy").format(filtreDate)));
			}
		}
		if (filtreServeur != null && filtreServeur != -1) {
			for(Machines_Liste ml : listServeur){
				if(ml.getId().equals(filtreServeur)){
					buffer.append(StringConverter.toJson("Serveur", ml.getNom()));
					break;
				}
			}
		}
		if (filtreApplicatif != null && filtreApplicatif != -1) {
			for(Applicatifs_Liste al : listApplicatif){
				if(al.getId().equals(filtreApplicatif)){
					buffer.append(StringConverter.toJson("Application",al.getApplicatif())); 
					break;
				}
			}
		}
		if (filtreEnvironnement != null && filtreEnvironnement != -1) {
			for(Environnement e : listEnvironnement){
				if(e.getId().equals(filtreEnvironnement)){
					buffer.append(StringConverter.toJson("Environnement", e.getEnvironnement()));
					break;
				}
			}
		}
		if (filtreType != null && filtreType != -1) {
			for(Incidents_Type t : listType){
				if(t.getId().equals(filtreType)){
					buffer.append(StringConverter.toJson("Type", t.getType()));
					break;
				}
			}
		}
		if (filtreResoluPilotage != null && !filtreResoluPilotage.equals(-1)) {
			if (filtreResoluPilotage.equals(1)) {
				buffer.append(StringConverter.toJson("Resolu par le pilotage", "Oui"));
			} else if (filtreResoluPilotage.equals(0)) {
				buffer.append(StringConverter.toJson("Resolu par le pilotage", "Non"));
			} else {
				buffer.append(StringConverter.toJson("Resolu par le pilotage", "Aucun"));
			}
		}
		if (filtreCoupureService != null && !filtreCoupureService.equals(-1)) {
			if (filtreCoupureService.equals(1)) {
				buffer.append(StringConverter.toJson("Coupure de service", "Oui"));
			} else if (filtreCoupureService.equals(0)) {
				buffer.append(StringConverter.toJson("Coupure de service", "Non"));
			} else {
				buffer.append(StringConverter.toJson("Coupure de service", "Aucun"));
			}
		}
		if (filtreHasAstreinte != null && !filtreHasAstreinte.equals(-1)) {
			if (filtreHasAstreinte.equals(1)) {
				buffer.append(StringConverter.toJson("Astreinte","Oui"));
			} else if (filtreHasAstreinte.equals(0)) {
				buffer.append(StringConverter.toJson("Astreinte","Non"));
			} else {
				buffer.append(StringConverter.toJson("Astreinte","Aucun"));
			}
		}
		if (filtreObservation != null && !filtreObservation.equals("")) {
			buffer.append(StringConverter.toJson("Observation", filtreObservation));
		}
		
		if (filtreArs != null && !filtreArs.equals("")) {
			buffer.append(StringConverter.toJson("Ars", filtreArs));
		}
		
		if(buffer.length() != 0) {
			String result = "{" + buffer.substring(0, buffer.lastIndexOf(PilotageConstants.SEPARATEUR_3)) + "}";
			JSONObject jsonFiltre = JSONObject.fromObject(result);
			return jsonFiltre;
		}
		else {
			return null;
		}
	}
}
