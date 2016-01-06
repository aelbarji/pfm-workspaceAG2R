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
import pilotage.metier.Hardware_Software;
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
public class CreateIncidentAction extends AbstractIncidentsAction {

	private static final long serialVersionUID = 5975387263087759338L;
	
	private Integer validAst;
	private Integer idInc;
	private Integer incident;
	private Integer incidentID;
	
	private String provenance;
	private String last_provenance;
	
	private int page = 0;
	private int nrPages = 0;
	private int nrPerPage = 0;
	
	public Integer getValidAst() {
		return validAst;
	}

	public void setValidAst(Integer validAst) {
		this.validAst = validAst;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see pilotage.incidents.outils.AbstractIncidentsAction#incidentExcute()
	 */

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

	public Integer getIncident() {
		return incident;
	}

	public void setIncident(Integer incident) {
		this.incident = incident;
	}

	public String getLast_provenance() {
		return last_provenance;
	}

	public void setLast_provenance(String last_provenance) {
		this.last_provenance = last_provenance;
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

	public Integer getIncidentID() {
		return incidentID;
	}

	public void setIncidentID(Integer incidentID) {
		this.incidentID = incidentID;
	}

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
				finGMT = gmtFormat.parse(gmtFormat.format(datefin));
			}
			observation.replaceAll("<br/>", "\n");
			actionInc.replaceAll("<br/>", "\n");	
					
			idInc = IncidentsDatabaseService.create(
					actionInc, 
					StringConverter.arrayToString(appliSelected, PilotageConstants.INCIDENT_SEPARATEUR),
					incidentARS.trim(),
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
			
			HistoriqueDatabaseService.create(null, MessageFormat.format(getText("historique.incidents.creation"),new Object[]{idInc}), (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_INCIDENTS);
			
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
			info = getText("incidents.creation.valide");
			listTypes = IncidentsTypesDatabaseService.getAll();
			if (typeSelected == null || typeSelected == -1) {
				listIncidents = IncidentsDatabaseService.getAllUnfinished(paginationIncident, filtreIncidents);
			}
			else {
				listIncidents = IncidentsDatabaseService.getSpecificTypeUnfinished(paginationIncident, filtreIncidents, typeSelected);
			}
			initApplicationMap(appMap, listIncidents);
			last_provenance = "redirectModifyIncident";
			//vidage de la session
			session.remove(PilotageConstants.LISTE_APPLICATIFS_ACTUELLE);
			session.remove(PilotageConstants.LISTE_ENVIRONNEMENTS_ACTUELLE);
			session.remove(PilotageConstants.LISTE_MACHINES_ACTUELLE);
			session.remove(PilotageConstants.LISTE_SERVICES_ACTUELLE);
			session.remove(PilotageConstants.LISTE_APPLICATIFS);
			session.remove(PilotageConstants.LISTE_HARDWARE);
			session.remove(PilotageConstants.LISTE_ENVIRONNEMENTS);
			session.remove(PilotageConstants.LISTE_MACHINES);
			session.remove(PilotageConstants.LISTE_SERVICES);
			
			incidentID = idInc;
			
			if(validAst == 0) // s'il n'y a pas de redirection
				return OK;
			else
				return AST;
				
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
			listHard 		= (List<Hardware_Software>) session.get(PilotageConstants.LISTE_HARDWARE);
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Création d'un incident - ", e);
			return ERROR;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pilotage.framework.AbstractAction#validateMetier()
	 */
	@Override
	protected boolean validateMetier() {		
		return true;
	}

}