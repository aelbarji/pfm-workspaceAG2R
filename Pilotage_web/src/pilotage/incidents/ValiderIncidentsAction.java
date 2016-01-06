package pilotage.incidents;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import pilotage.database.incidents.IncidentsDatabaseService;
import pilotage.database.incidents.IncidentsTypesDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Applicatifs_Liste;
import pilotage.metier.Incidents;
import pilotage.metier.Incidents_Type;
import pilotage.service.constants.PilotageConstants;
import pilotage.utils.Pagination;

/**
 * @author xxu
 *
 */
public class ValiderIncidentsAction extends AbstractAction {

	private static final long serialVersionUID = -6339377183764359931L;

	private List<Incidents_Type> listTypes;
	private Integer typeSelected;
	private List<Incidents> listIncidents;
	private Map<Integer, List<Applicatifs_Liste>> appMap;

	private Integer selectedID;
	
	private int pageIncident;
	private int nrPagesIncident;
	private int nrPerPageIncident;

	private Pagination<Incidents> paginationIncident;
	
	private Integer  filtreIncidents;
	
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
	 * @return the selectedID
	 */
	public Integer getSelectedID() {
		return selectedID;
	}

	/**
	 * @param selectedID the selectedID to set
	 */
	public void setSelectedID(Integer selectedID) {
		this.selectedID = selectedID;
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
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			sdf.setTimeZone(TimeZone.getDefault());
			SimpleDateFormat sdfGMT = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			sdfGMT.setTimeZone(TimeZone.getTimeZone("GMT"));
			
			Date now = new Date();
			Date nowGMT = sdf.parse(sdfGMT.format(now));
			
			IncidentsDatabaseService.updateDateFin(selectedID, now, nowGMT);
			info = getText("incidents.validation.valide");
			
			return OK;
		}
		catch(Exception e){
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Validation d'un incident - ", e);
			return ERROR;
		}
		finally{
			try{
				if(pageIncident < 1)
					pageIncident = 1;
				else if(nrPagesIncident != 0 && pageIncident > nrPagesIncident)
					pageIncident = nrPagesIncident;		
				if(nrPerPageIncident == 0)
					nrPerPageIncident = PilotageConstants.NB_USERS_PER_PAGE;
				paginationIncident = new Pagination<Incidents>(pageIncident, nrPerPageIncident);
				paginationIncident.setNrPages(nrPagesIncident);
				if (filtreIncidents == null) {
					filtreIncidents = 1;
				}
				
				listTypes = IncidentsTypesDatabaseService.getAll();
				if (typeSelected == null || typeSelected == -1) {
					listIncidents = IncidentsDatabaseService.getAllUnfinished(paginationIncident, filtreIncidents);
				}
				else {
					listIncidents = IncidentsDatabaseService.getSpecificTypeUnfinished(paginationIncident, filtreIncidents, typeSelected);
				}
				appMap = new HashMap<Integer, List<Applicatifs_Liste>>();
				AbstractIncidentsAction.initApplicationMap(appMap, listIncidents);
			}
			catch (Exception e) {
				error = getText("error.message.generique") + " : " + e.getMessage();
				erreurLogger.error("Suppression d'un incident - ", e);
				return ERROR;
			}
		}
	}
}
