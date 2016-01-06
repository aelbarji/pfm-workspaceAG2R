package pilotage.incidents;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import pilotage.database.applicatif.ApplicatifDatabaseService;
import pilotage.database.applicatif.EnvironmentDatabaseService;
import pilotage.database.applicatif.HardwareSoftwareDatabaseService;
import pilotage.database.applicatif.ServiceDatabaseService;
import pilotage.database.astreintes.AstreinteAppelDatabaseService;
import pilotage.database.astreintes.AstreinteDatabaseService;
import pilotage.database.incidents.HeuresOceorDatabaseService;
import pilotage.database.incidents.IncidentsDatabaseService;
import pilotage.database.incidents.IncidentsOutilsDatabaseService;
import pilotage.database.incidents.IncidentsTypesDatabaseService;
import pilotage.database.machine.MachinesListesDatabaseService;
import pilotage.metier.Incidents;
import pilotage.service.constants.PilotageConstants;
import pilotage.service.string.StringConverter;
import pilotage.utils.Pagination;

public class RedirectIncidentsAction extends AbstractIncidentsAction {

	private static final long serialVersionUID = -4016495913116185474L;

	private Integer idInc;
	private Integer validAst;
	private Integer nbAppelAstreinte;
	private Integer incFromAstreinte;
	private String provenance;
	private String last_provenance;
	
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
	
	private int pageIncident;
	private int nrPagesIncident;
	private int nrPerPageIncident;

	private Pagination<Incidents> paginationIncident;
	
	public Integer getIdInc() {
		return idInc;
	}

	public void setIdInc(Integer idInc) {
		this.idInc = idInc;
	}

	public Integer getValidAst() {
		return validAst;
	}

	public void setValidAst(Integer validAst) {
		this.validAst = validAst;
	}

	public Integer getNbAppelAstreinte() {
		return nbAppelAstreinte;
	}

	public void setNbAppelAstreinte(Integer nbAppelAstreinte) {
		this.nbAppelAstreinte = nbAppelAstreinte;
	}

	public String getProvenance() {
		return provenance;
	}

	public void setProvenance(String provenance) {
		this.provenance = provenance;
	}

	public String getLast_provenance() {
		return last_provenance;
	}

	public void setLast_provenance(String last_provenance) {
		this.last_provenance = last_provenance;
	}

	public Integer getIncFromAstreinte() {
		return incFromAstreinte;
	}

	public void setIncFromAstreinte(Integer incFromAstreinte) {
		this.incFromAstreinte = incFromAstreinte;
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

	public Integer getFiltreType() {
		return filtreType;
	}

	public void setFiltreType(Integer filtreType) {
		this.filtreType = filtreType;
	}

	public Integer getFiltreCoupureService() {
		return filtreCoupureService;
	}

	public void setFiltreCoupureService(Integer filtreCoupureService) {
		this.filtreCoupureService = filtreCoupureService;
	}

	public Integer getFiltreResoluPilotage() {
		return filtreResoluPilotage;
	}

	public void setFiltreResoluPilotage(Integer filtreResoluPilotage) {
		this.filtreResoluPilotage = filtreResoluPilotage;
	}

	public Integer getFiltreHasAstreinte() {
		return filtreHasAstreinte;
	}

	public void setFiltreHasAstreinte(Integer filtreHasAstreinte) {
		this.filtreHasAstreinte = filtreHasAstreinte;
	}

	public String getFiltreObservation() {
		return filtreObservation;
	}

	public void setFiltreObservation(String filtreObservation) {
		this.filtreObservation = filtreObservation;
	}

	public String getFiltreArs() {
		return filtreArs;
	}

	public void setFiltreArs(String filtreArs) {
		this.filtreArs = filtreArs;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see pilotage.incidents.AbstractIncidentsAction#validateMetier()
	 */
	@Override
	protected boolean validateMetier() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pilotage.incidents.AbstractIncidentsAction#incidentExcute()
	 */
	@Override
	protected String incidentExecute() {
		//vidage de la session pour actualiser les 4 listes
		session.remove(PilotageConstants.LISTE_APPLICATIFS_ACTUELLE);
		session.remove(PilotageConstants.LISTE_ENVIRONNEMENTS_ACTUELLE);
		session.remove(PilotageConstants.LISTE_MACHINES_ACTUELLE);
		session.remove(PilotageConstants.LISTE_SERVICES_ACTUELLE);
		session.remove(PilotageConstants.LISTE_APPLICATIFS);
		session.remove(PilotageConstants.LISTE_ENVIRONNEMENTS);
		session.remove(PilotageConstants.LISTE_MACHINES);
		session.remove(PilotageConstants.LISTE_SERVICES);
		
		//initialisation des listes
		listTypes 		= IncidentsTypesDatabaseService.getAll();
		listMachine 	= MachinesListesDatabaseService.getAll();
		listApp 		= ApplicatifDatabaseService.getAll();
		listService 	= ServiceDatabaseService.getAll();
		listEnviron 	= EnvironmentDatabaseService.getAll();
		listOceor 		= HeuresOceorDatabaseService.getAll();
		listAstreintes 	= AstreinteDatabaseService.getAll();
		listOutils		= IncidentsOutilsDatabaseService.getAll();
		listHard		= HardwareSoftwareDatabaseService.getAll();
		if(idInc != null && idInc != 0 && last_provenance != null && last_provenance.equals("redirectModifyIncident")){
			selectedID = idInc;
		}
		if(selectedID != null)
			nbAppelAstreinte = AstreinteAppelDatabaseService.getByIncident(selectedID).size();
		last_provenance = "redirectModifyIncident";
		//incidents existant
		if (selectedID != null) {
			//Date formatters
			SimpleDateFormat sdf_Date = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			sdf_Date.setTimeZone(TimeZone.getDefault());
			
			//récupération des infos
			Incidents i = IncidentsDatabaseService.get(selectedID);

			machineSelected 		= i.getMachine().getId();
			appliSelected 			= StringConverter.stringToArray(i.getApplicatif(), PilotageConstants.INCIDENT_SEPARATEUR);
			serviceSelected 		= StringConverter.stringToArray(i.getService(), PilotageConstants.INCIDENT_SEPARATEUR);
			hardsoftSelected		= StringConverter.stringToArray(i.getHard_software(), PilotageConstants.INCIDENT_SEPARATEUR);
			enviroSelected 			= i.getDomaine().getId();
			coupureSelected 		= i.getCoupure();
			resoluPilSelected 		= i.getResoluPil();
			if (i.getAstreinte() != null)
				astreintesSelected 	= StringConverter.stringToArray(i.getAstreinte(), PilotageConstants.INCIDENT_SEPARATEUR);
			if (i.getIdOutil() != null)
				outilSelected 		= i.getIdOutil().getId();
			observation 			= i.getObservation();
			actionInc 				= i.getAction();
			incidentARS 			= i.getArs();
			typeInciSelected 		= i.getType().getId();
			oceorSelected 			= i.getOceor().getId();
			dateDebut 				= sdf_Date.format(i.getDateDebut());
		
			appli_ordonnanceur		= i.getAppli_ordonnanceur();
			job						= i.getJob();
			try {
				if (i.getDateFin() != null) {
					if (!sdf_Date.parse("01/01/1970 00:00").equals(sdf_Date.format(i.getDateFin()))) {
						dateFin = sdf_Date.format(i.getDateFin());
						
					}
				}
			} catch (ParseException e) {
				applicationLogger.warn("Redirection vers la création d'incident : ", e);
			}
		}
		else{
			typeInciSelected = PilotageConstants.DEFAULT_INCIDENT_TYPE;
			
//			dateDebut	= DateService.dateToStr(new Date(), DateService.p1);
//			heureDebut 	= DateService.getTime(new Date(), DateService.pt1);
		}
		
		return OK;
	}

}
