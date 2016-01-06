/**
 * pilotage.incidents
 * 15 juil. 2011
 */
package pilotage.incidents;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;

import pilotage.database.environnement.EnvironnementDatabaseService;
import pilotage.database.incidents.IncidentsDatabaseService;
import pilotage.database.incidents.IncidentsTypesDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Applicatifs_Liste;
import pilotage.metier.Environnement;
import pilotage.metier.Hardware_Software;
import pilotage.metier.Incidents;
import pilotage.metier.Incidents_Type;
import pilotage.service.constants.PilotageConstants;
import pilotage.utils.Pagination;

/**
 * @author xxu
 *
 */
public class ShowIncidentsAction extends AbstractAction {

	private static final long serialVersionUID = -6551867967856314345L;
	private List<Incidents_Type> listTypes;
	private Integer typeSelected;
	private List<Incidents> listIncidents;
	private Map<Integer, List<Applicatifs_Liste>> appMap;
	private Map<Integer, List<Hardware_Software>> hardMap;
	private String provenance;
	
	//Pour Tableau de Bord
	private Integer envirSelected;
	private Integer clotureDemiJournee;
	private Environnement envir;

	private int pageIncident;
	private int nrPagesIncident;
	private int nrPerPageIncident;

	private Pagination<Incidents> paginationIncident;
	
	private Integer filtreIncidents;

	public Integer getFiltreIncidents() {
		return filtreIncidents;
	}

	public void setFiltreIncidents(Integer filtreIncidents) {
		this.filtreIncidents = filtreIncidents;
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

	/**
	 * @return the listTypes
	 */
	public List<Incidents_Type> getListTypes() {
		return listTypes;
	}

	/**
	 * @param listTypes the listTypes to set
	 */
	public void setListTypes(List<Incidents_Type> listTypes) {
		this.listTypes = listTypes;
	}

	/**
	 * @return the typeSelected
	 */
	public int getTypeSelected() {
		return typeSelected;
	}

	/**
	 * @param typeSelected the typeSelected to set
	 */
	public void setTypeSelected(int typeSelected) {
		this.typeSelected = typeSelected;
	}

	/**
	 * @return the listIncidents
	 */
	public List<Incidents> getListIncidents() {
		return listIncidents;
	}

	/**
	 * @param listIncidents the listIncidents to set
	 */
	public void setListIncidents(List<Incidents> listIncidents) {
		this.listIncidents = listIncidents;
	}

	/**
	 * @return the appMap
	 */
	public Map<Integer, List<Applicatifs_Liste>> getAppMap() {
		return appMap;
	}

	/**
	 * @param appMap the appMap to set
	 */
	public void setAppMap(Map<Integer, List<Applicatifs_Liste>> appMap) {
		this.appMap = appMap;
	}

	/**
	 * @param typeSelected the typeSelected to set
	 */
	public void setTypeSelected(Integer typeSelected) {
		this.typeSelected = typeSelected;
	}

	public String getProvenance() {
		return provenance;
	}

	public void setProvenance(String provenance) {
		this.provenance = provenance;
	}

	public Map<Integer, List<Hardware_Software>> getHardMap() {
		return hardMap;
	}

	public void setHardMap(Map<Integer, List<Hardware_Software>> hardMap) {
		this.hardMap = hardMap;
	}

	public Integer getEnvirSelected() {
		return envirSelected;
	}

	public void setEnvirSelected(Integer envirSelected) {
		this.envirSelected = envirSelected;
	}

	public Integer getClotureDemiJournee() {
		return clotureDemiJournee;
	}

	public void setClotureDemiJournee(Integer clotureDemiJournee) {
		this.clotureDemiJournee = clotureDemiJournee;
	}

	public Environnement getEnvir() {
		return envir;
	}

	public void setEnvir(Environnement envir) {
		this.envir = envir;
	}

	/* (non-Javadoc)
	 * @see pilotage.framework.AbstractAction#validateMetier()
	 */
	@Override
	protected boolean validateMetier() {
		return true;
	}

	/* (non-Javadoc)
	 * @see pilotage.framework.AbstractAction#executeMetier()
	 */
	@Override
	protected String executeMetier() {
		try{
			//vidage de la session pour les cas où on est en création/modification - bouton retour
			session.remove(PilotageConstants.LISTE_APPLICATIFS_ACTUELLE);
			session.remove(PilotageConstants.LISTE_ENVIRONNEMENTS_ACTUELLE);
			session.remove(PilotageConstants.LISTE_MACHINES_ACTUELLE);
			session.remove(PilotageConstants.LISTE_SERVICES_ACTUELLE);
			session.remove(PilotageConstants.LISTE_APPLICATIFS);
			session.remove(PilotageConstants.LISTE_HARDWARE);
			session.remove(PilotageConstants.LISTE_ENVIRONNEMENTS);
			session.remove(PilotageConstants.LISTE_MACHINES);
			session.remove(PilotageConstants.LISTE_SERVICES);
			
			provenance = "showIncidents";
			
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
			listTypes = IncidentsTypesDatabaseService.getAll();
			if (typeSelected == null) {
				typeSelected = -1;
			}
		
			if (typeSelected == -1) {
				if(envirSelected != null){
					envir = EnvironnementDatabaseService.get(envirSelected);
					if(clotureDemiJournee == null || clotureDemiJournee==0){ 
						listIncidents = IncidentsDatabaseService.getIncidentByEnvir(paginationIncident, envir, null);
						if(listIncidents==null || listIncidents.isEmpty()) info = MessageFormat.format(getText("tabDeBord.incidentEnCours.vide"), envir.getEnvironnement());
						else if(listIncidents!=null && !listIncidents.isEmpty()) info = MessageFormat.format(getText("tabDeBord.incidentEnCours.liste"), envir.getEnvironnement());
						filtreIncidents = null;
					}
					else if(clotureDemiJournee!= null && clotureDemiJournee==1){
						DateTime datetime = new DateTime().minusHours(12);
						listIncidents = IncidentsDatabaseService.getIncidentByEnvir(paginationIncident, envir, datetime.toDate());
						if(listIncidents==null || listIncidents.isEmpty()) info = MessageFormat.format(getText("tabDeBord.incidentDouzeHeures.vide"), envir.getEnvironnement());
						else if(listIncidents!=null && !listIncidents.isEmpty()) info = MessageFormat.format(getText("tabDeBord.incidentDouzeHeures.liste"), envir.getEnvironnement());
						filtreIncidents = null;
					}
				}
				else listIncidents = IncidentsDatabaseService.getAllUnfinished(paginationIncident, filtreIncidents);
			}
			else {
				listIncidents = IncidentsDatabaseService.getSpecificTypeUnfinished(paginationIncident, filtreIncidents, typeSelected);
			}
			appMap = new HashMap<Integer, List<Applicatifs_Liste>>();
			hardMap = new HashMap<Integer, List<Hardware_Software>>();
			AbstractIncidentsAction.initApplicationMap(appMap, listIncidents);
			AbstractIncidentsAction.initHardwareMap(hardMap, listIncidents);				
			
			return OK;
		} catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Affichage liste incidents - ", e);
			return ERROR;	
		}
	}


}
