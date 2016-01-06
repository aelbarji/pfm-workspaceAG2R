package pilotage.incidents;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;

import pilotage.database.astreintes.AstreinteDatabaseService;
import pilotage.database.historique.HistoriqueDatabaseService;
import pilotage.database.incidents.HeuresOceorDatabaseService;
import pilotage.database.incidents.IncidentsDatabaseService;
import pilotage.database.incidents.IncidentsOutilsDatabaseService;
import pilotage.database.incidents.IncidentsTypesDatabaseService;
import pilotage.metier.Applicatifs_Liste;
import pilotage.metier.Environnement;
import pilotage.metier.Incidents;
import pilotage.metier.Machines_Liste;
import pilotage.metier.Services_Liste;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;
import pilotage.service.date.DateService;
import pilotage.service.string.StringConverter;
import pilotage.utils.Pagination;

/**
 * @author xxu
 *
 */
public class ModifyIncidentAction extends AbstractIncidentsAction {

	private static final long serialVersionUID = -7890900282932391908L;
	
	private Integer validAst;
	private Integer idInc;
	private String provenance;
	private Integer incFromAstreinte;
	protected static final String SHOW 	= "show";
	protected static final String NEW 	= "new";
	private boolean redir;
	private Integer filtrePilote;
	private Date filtreDate;
	private Date filtreDateFin;
	private Integer filtreServeur;
	private Integer filtreApplicatif;
	private Integer filtreEnvironnement;
	private Integer filtreType;
	private Integer filtreCoupureService;
	private Integer filtreResoluPilotage;
	private Integer filtreHasAstreinte;
	private String filtreObservation;
	private String filtreArs;
	
	
	public Integer getValidAst() {
		return validAst;
	}

	public void setValidAst(Integer validAst) {
		this.validAst = validAst;
	}

	public Integer getIdInc() {
		return idInc;
	}

	public void setIdInc(Integer idInc) {
		this.idInc = idInc;
	}

	public String getProvenance() {
		return provenance;
	}

	public void setProvenance(String provenance) {
		this.provenance = provenance;
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

	public boolean isRedir() {
		return redir;
	}

	public void setRedir(boolean redir) {
		this.redir = redir;
	}

	/* (non-Javadoc)
	 * @see pilotage.incidents.outils.AbstractIncidentsAction#incidentExcute()
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected String incidentExecute() {
		
		try {
			//Récupération des dates sous format Date
			Date datedeb = DateService.strToDate(dateDebut);
			Date datefin = DateService.strToDate(dateFin);
			
			//Initialisation du format GMT
			SimpleDateFormat gmtFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			TimeZone gmtTime = TimeZone.getTimeZone("GMT");
			
			gmtFormat.setTimeZone(gmtTime);
			
			//Conversion de format de datetime
			Date debutGMT = gmtFormat.parse(gmtFormat.format(datedeb));
			
			
			Date finGMT = null;
			if(datefin != null && !datefin.equals("")){
				//finGMT = sdf_date_d.parse(sdfGMT.format(dateFin));
				finGMT = gmtFormat.parse(gmtFormat.format(datefin));
			}
			
			String historique = " ";
			Incidents i = IncidentsDatabaseService.get(selectedID);
			if (!datedeb.equals(i.getDateDebut())){
				historique += "dateDebut, ";
			}
			if (datefin != null) {
				if (!datefin.equals(i.getDateFin())) {
					historique += "dateFin, ";
				}
			}
			if (!machineSelected.equals(i.getMachine().getId())) {
				historique += "server, ";
			}
			if (!(StringConverter.arrayToString(appliSelected, PilotageConstants.INCIDENT_SEPARATEUR)).equals(i.getApplicatif())) {
				historique += "applicatif, ";
			}
			if (!(StringConverter.arrayToString(serviceSelected, PilotageConstants.INCIDENT_SEPARATEUR)).equals(i.getService())) {
				historique += "service, ";
			}
			if (!(StringConverter.arrayToString(hardsoftSelected, PilotageConstants.INCIDENT_SEPARATEUR)).equals(i.getHard_software())) {
				historique += "hardware, ";
			}
			if (!enviroSelected.equals(i.getDomaine().getId())) {
				historique += "environnement, ";
			}
			if (!coupureSelected.equals(i.getCoupure())) {
				historique += "coupure, ";
			}
			if (!typeInciSelected.equals(i.getType().getId())) {
				historique += "type, ";
			}
			if (i.getIdOutil() != null) {
				if (!outilSelected.equals(i.getIdOutil().getId())) {
					historique += "outil, ";
				}
			}
			if (i.getOceor() != null) {
				if (!oceorSelected.equals(i.getOceor().getId())) {
					historique += "oceor, ";
				}
			}
			if (!observation.equals(i.getObservation())) {
				historique += "observation, ";
			}
			if (!actionInc.equals(i.getAction())) {
				historique += "action, ";
			}
			if (incidentARS != null) {
				if (!incidentARS.trim().equals(i.getArs())) {
					historique += "ars, ";
				}
			}
			if (!(astreintesSelected != null ? StringConverter.arrayToString(astreintesSelected, PilotageConstants.INCIDENT_SEPARATEUR) : "").equals(i.getAstreinte())) {
				historique += "astreintes, ";
			}
			if(!appli_ordonnanceur.equals(i.getAppli_ordonnanceur())){
				historique += "appli_ordonnanceur, ";
			}
			if(!job.equals(i.getJob())){
				historique += "job, ";
			}
			idInc = selectedID;
			IncidentsDatabaseService.modify(
					selectedID,
					actionInc, 
					StringConverter.arrayToString(appliSelected, PilotageConstants.INCIDENT_SEPARATEUR),
					incidentARS.trim(),
					astreintesSelected != null ? StringConverter.arrayToString(astreintesSelected, PilotageConstants.INCIDENT_SEPARATEUR) : "",
					coupureSelected,
					resoluPilSelected,
					enviroSelected,
					StringConverter.arrayToString(hardsoftSelected, PilotageConstants.INCIDENT_SEPARATEUR),
					datedeb,
					debutGMT,
					datefin,
					finGMT,
					appli_ordonnanceur,
					job,
					reprise,
					outilSelected,
					machineSelected,
					observation,
					oceorSelected,
					StringConverter.arrayToString(serviceSelected, PilotageConstants.INCIDENT_SEPARATEUR),
					typeInciSelected,
					(Users) session.get("USER_LOGGED")
			);

			HistoriqueDatabaseService.create(null, MessageFormat.format(getText("historique.incidents.modification"),new Object[]{historique, selectedID}), (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_INCIDENTS);
			
			if(pageIncident < 1)
				pageIncident = 1;
			else if(nrPagesIncident != 0 && pageIncident > nrPagesIncident)
				pageIncident = nrPagesIncident;		
			if(nrPerPageIncident == 0)
				nrPerPageIncident = PilotageConstants.NB_MACHINES_PER_PAGE;
			paginationIncident = new Pagination<Incidents>(pageIncident, nrPerPageIncident);
			paginationIncident.setNrPages(nrPagesIncident);
			if (filtreIncidents == null) {
				filtreIncidents = 1;
			}
			
			appMap = new HashMap<Integer, List<Applicatifs_Liste>>();
			info = getText("incidents.modification.valide");
			listTypes = IncidentsTypesDatabaseService.getAll();
			if (typeSelected == null || typeSelected == -1) {
				listIncidents = IncidentsDatabaseService.getAllUnfinished(paginationIncident, filtreIncidents);
			}
			else {
				listIncidents = IncidentsDatabaseService.getSpecificTypeUnfinished(paginationIncident, filtreIncidents, typeSelected);
			}
			initApplicationMap(appMap, listIncidents);
			
			//vidage de la session
			session.remove(PilotageConstants.LISTE_APPLICATIFS_ACTUELLE);
			session.remove(PilotageConstants.LISTE_ENVIRONNEMENTS_ACTUELLE);
			session.remove(PilotageConstants.LISTE_MACHINES_ACTUELLE);
			session.remove(PilotageConstants.LISTE_SERVICES_ACTUELLE);
			session.remove(PilotageConstants.LISTE_APPLICATIFS);
			session.remove(PilotageConstants.LISTE_ENVIRONNEMENTS);
			session.remove(PilotageConstants.LISTE_MACHINES);
			session.remove(PilotageConstants.LISTE_SERVICES);
			
			if(validAst != null && validAst == 1) // s'il n'y a pas d'astreintes
				return NEW;
			else if(validAst != null && validAst == 2) // s'il y a des astreintes
				return SHOW;
			else if(provenance != null && !provenance.equals("")){
				redir = true;
				return provenance;
			}
			return "show"; // sinon on retourne dans showAppelAstreinte si on venait de la consultation à partir de cette page
		}
		catch (Exception e) {
			listOceor = HeuresOceorDatabaseService.getAll();
			listAstreintes = AstreinteDatabaseService.getAll();
			listOutils = IncidentsOutilsDatabaseService.getAll();
			listTypes 		= IncidentsTypesDatabaseService.getAll();
			
			listMachine 	= (List<Machines_Liste>) session.get(PilotageConstants.LISTE_MACHINES_ACTUELLE);
			listApp 		= (List<Applicatifs_Liste>) session.get(PilotageConstants.LISTE_APPLICATIFS_ACTUELLE);
			listService 	= (List<Services_Liste>) session.get(PilotageConstants.LISTE_SERVICES_ACTUELLE);
			listEnviron 	= (List<Environnement>) session.get(PilotageConstants.LISTE_ENVIRONNEMENTS_ACTUELLE);
			
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Modification d'un incident - ", e);
			return ERROR;
		}
	}

	/* (non-Javadoc)
	 * @see pilotage.framework.AbstractAction#validateMetier()
	 */
	@Override
	protected boolean validateMetier() {
		return true;
	}

}
