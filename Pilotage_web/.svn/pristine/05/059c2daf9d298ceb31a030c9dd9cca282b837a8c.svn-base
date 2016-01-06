/**
 * pilotage.incidents
 * 26 juil. 2011
 */
package pilotage.incidents;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import pilotage.database.applicatif.ApplicatifDatabaseService;
import pilotage.database.applicatif.HardwareSoftwareDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Applicatifs_Liste;
import pilotage.metier.Astreinte;
import pilotage.metier.Environnement;
import pilotage.metier.Hardware_Software;
import pilotage.metier.Heures_Oceor;
import pilotage.metier.Incidents;
import pilotage.metier.Incidents_Outils;
import pilotage.metier.Incidents_Type;
import pilotage.metier.Machines_Liste;
import pilotage.metier.Services_Liste;
import pilotage.utils.Pagination;

/**
 * @author xxu
 *
 */
public abstract class AbstractIncidentsAction extends AbstractAction {

	private static final long serialVersionUID = -2484564137747294196L;
	protected static final String AST 	= "ast";
	protected Integer selectedID;
	protected List<Incidents_Type> listTypes;
	protected List<Incidents> listIncidents;

	protected List<Machines_Liste> listMachine;
	protected List<Applicatifs_Liste> listApp;
	protected List<Services_Liste> listService;
	protected List<Environnement> listEnviron;
	protected List<Heures_Oceor> listOceor;
	protected List<Astreinte> listAstreintes;
	protected List<Incidents_Outils> listOutils;
	protected List<Hardware_Software> listHard;

	protected Integer machineSelected;
	// multi option
	protected String[] appliSelected;
	// multi option
	protected String[] serviceSelected;
	protected Integer enviroSelected;
	protected String[] hardsoftSelected; // multi option
	protected Integer coupureSelected;
	protected Integer resoluPilSelected;
	// multi option
	protected String[] astreintesSelected;
	protected Integer outilSelected;
	protected Integer typeInciSelected;
	protected Integer oceorSelected;

	protected String dateDebut;

	protected String dateFin;

	protected String observation;
	protected String actionInc;
	protected String incidentARS;
	protected String appli_ordonnanceur;
	protected String job;
	protected String reprise;

	protected Map<Integer, List<Applicatifs_Liste>> appMap;
	protected Map<Integer, List<Hardware_Software>> hardMap;
	//refers to the action of the select list changes
	//0: nothing. 
	//1: machine list changes  
	//2: applicatif list changes
	//3: service list changes  
	//4: environment list changes
	protected Integer selectAction;

	protected Integer typeSelected;
	
	protected int pageIncident;
	protected int nrPagesIncident;
	protected int nrPerPageIncident;
	
	protected Pagination<Incidents> paginationIncident;
	
	protected Integer filtreIncidents;
	
	public Integer getTypeSelected() {
		return typeSelected;
	}

	public void setTypeSelected(Integer typeSelected) {
		this.typeSelected = typeSelected;
	}

	public String[] getHardsoftSelected() {
		return hardsoftSelected;
	}

	public void setHardsoftSelected(String[] hardsoftSelected) {
		this.hardsoftSelected = hardsoftSelected;
	}

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
	 * @return the selectAction
	 */
	public Integer getSelectAction() {
		return selectAction;
	}

	/**
	 * @param selectAction the selectAction to set
	 */
	public void setSelectAction(Integer selectAction) {
		this.selectAction = selectAction;
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
	 * @param listTypes
	 *            the listTypes to set
	 */
	public void setListTypes(List<Incidents_Type> listTypes) {
		this.listTypes = listTypes;
	}

	/**
	 * @return the listIncidents
	 */
	public List<Incidents> getListIncidents() {
		return listIncidents;
	}

	/**
	 * @param listIncidents
	 *            the listIncidents to set
	 */
	public void setListIncidents(List<Incidents> listIncidents) {
		this.listIncidents = listIncidents;
	}

	/**
	 * @return the listMachine
	 */
	public List<Machines_Liste> getListMachine() {
		return listMachine;
	}

	/**
	 * @param listMachine
	 *            the listMachine to set
	 */
	public void setListMachine(List<Machines_Liste> listMachine) {
		this.listMachine = listMachine;
	}

	/**
	 * @return the listApp
	 */
	public List<Applicatifs_Liste> getListApp() {
		return listApp;
	}

	/**
	 * @param listApp
	 *            the listApp to set
	 */
	public void setListApp(List<Applicatifs_Liste> listApp) {
		this.listApp = listApp;
	}

	/**
	 * @return the listService
	 */
	public List<Services_Liste> getListService() {
		return listService;
	}

	/**
	 * @param listService
	 *            the listService to set
	 */
	public void setListService(List<Services_Liste> listService) {
		this.listService = listService;
	}

	/**
	 * @return the listEnviron
	 */
	public List<Environnement> getListEnviron() {
		return listEnviron;
	}

	/**
	 * @param listEnviron
	 *            the listEnviron to set
	 */
	public void setListEnviron(List<Environnement> listEnviron) {
		this.listEnviron = listEnviron;
	}

	/**
	 * @return the listOceor
	 */
	public List<Heures_Oceor> getListOceor() {
		return listOceor;
	}

	/**
	 * @param listOceor
	 *            the listOceor to set
	 */
	public void setListOceor(List<Heures_Oceor> listOceor) {
		this.listOceor = listOceor;
	}

	/**
	 * @return the listAstreintes
	 */
	public List<Astreinte> getListAstreintes() {
		return listAstreintes;
	}

	/**
	 * @param listAstreintes
	 *            the listAstreintes to set
	 */
	public void setListAstreintes(List<Astreinte> listAstreintes) {
		this.listAstreintes = listAstreintes;
	}

	/**
	 * @return the machineSelected
	 */
	public Integer getMachineSelected() {
		return machineSelected;
	}

	/**
	 * @param machineSelected
	 *            the machineSelected to set
	 */
	public void setMachineSelected(Integer machineSelected) {
		this.machineSelected = machineSelected;
	}

	/**
	 * @return the serviceSelected
	 */
	public String[] getServiceSelected() {
		return serviceSelected;
	}

	/**
	 * @param serviceSelected
	 *            the serviceSelected to set
	 */
	public void setServiceSelected(String[] serviceSelected) {
		this.serviceSelected = serviceSelected;
	}

	/**
	 * @return the astreintesSelected
	 */
	public String[] getAstreintesSelected() {
		return astreintesSelected;
	}

	/**
	 * @param astreintesSelected
	 *            the astreintesSelected to set
	 */
	public void setAstreintesSelected(String[] astreintesSelected) {
		this.astreintesSelected = astreintesSelected;
	}

	/**
	 * @return the outilSelected
	 */
	public Integer getOutilSelected() {
		return outilSelected;
	}

	/**
	 * @param outilSelected
	 *            the outilSelected to set
	 */
	public void setOutilSelected(Integer outilSelected) {
		this.outilSelected = outilSelected;
	}

	/**
	 * @return the oceorSelected
	 */
	public Integer getOceorSelected() {
		return oceorSelected;
	}

	/**
	 * @param oceorSelected
	 *            the oceorSelected to set
	 */
	public void setOceorSelected(Integer oceorSelected) {
		this.oceorSelected = oceorSelected;
	}

	/**
	 * @return the enviroSelected
	 */
	public Integer getEnviroSelected() {
		return enviroSelected;
	}

	/**
	 * @param enviroSelected
	 *            the enviroSelected to set
	 */
	public void setEnviroSelected(Integer enviroSelected) {
		this.enviroSelected = enviroSelected;
	}

	/**
	 * @return the coupureSelected
	 */
	public Integer getCoupureSelected() {
		return coupureSelected;
	}

	/**
	 * @param coupureSelected
	 *            the coupureSelected to set
	 */
	public void setCoupureSelected(Integer coupureSelected) {
		this.coupureSelected = coupureSelected;
	}


	public Integer getResoluPilSelected() {
		return resoluPilSelected;
	}

	public void setResoluPilSelected(Integer resoluPilSelected) {
		this.resoluPilSelected = resoluPilSelected;
	}

	/**
	 * @return the typeInciSelected
	 */
	public Integer getTypeInciSelected() {
		return typeInciSelected;
	}

	/**
	 * @param typeInciSelected the typeInciSelected to set
	 */
	public void setTypeInciSelected(Integer typeInciSelected) {
		this.typeInciSelected = typeInciSelected;
	}

	/**
	 * @return the listOutils
	 */
	public List<Incidents_Outils> getListOutils() {
		return listOutils;
	}

	/**
	 * @param listOutils
	 *            the listOutils to set
	 */
	public void setListOutils(List<Incidents_Outils> listOutils) {
		this.listOutils = listOutils;
	}

	/**
	 * Return the String convert to HTML
	 * 
	 * @return the appliSelected
	 */
	public String[] getAppliSelected() {
		return appliSelected;
	}

	/**
	 * @param appliSelected
	 *            the appliSelected to set
	 */
	public void setAppliSelected(String[] appliSelected) {
		this.appliSelected = appliSelected;
	}

	/**
	 * @return the dateDebut
	 */
	public String getDateDebut() {
		return dateDebut;
	}

	/**
	 * @param dateDebut
	 *            the dateDebut to set
	 */
	public void setDateDebut(String dateDebut) {
		if(!"".equals(dateDebut))
			this.dateDebut = dateDebut;
	}

	

	/**
	 * @return the dateFin
	 */
	public String getDateFin() {
		return dateFin;
	}

	/**
	 * @param dateFin
	 *            the dateFin to set
	 */
	public void setDateFin(String dateFin) {
		if(!"".equals(dateFin))
			this.dateFin = dateFin;
	}

	public List<Hardware_Software> getListHard() {
		return listHard;
	}

	public void setListHard(List<Hardware_Software> listHard) {
		this.listHard = listHard;
	}

	public Map<Integer, List<Hardware_Software>> getHardMap() {
		return hardMap;
	}

	public void setHardMap(Map<Integer, List<Hardware_Software>> hardMap) {
		this.hardMap = hardMap;
	}

	/**
	 * @return the observation
	 */
	public String getObservation() {
		return observation;
	}

	/**
	 * @param observation
	 *            the observation to set
	 */
	public void setObservation(String observation) {
		this.observation = observation;
	}

	/**
	 * @return the actionInc
	 */
	public String getActionInc() {
		return actionInc;
	}

	/**
	 * @param actionInc the actionInc to set
	 */
	public void setActionInc(String actionInc) {
		this.actionInc = actionInc;
	}

	/**
	 * @return the incidentARS
	 */
	public String getIncidentARS() {
		return incidentARS;
	}

	/**
	 * @param incidentARS
	 *            the incidentARS to set
	 */
	public void setIncidentARS(String incidentARS) {
		this.incidentARS = incidentARS;
	}

	@Override
	protected String executeMetier(){
		if("".equals(dateDebut)){
			dateDebut = null;
		}
		if("".equals(dateFin)){
			dateFin = null;
		}
		return incidentExecute();
	}
	
	public String getAppli_ordonnanceur() {
		return appli_ordonnanceur;
	}

	public void setAppli_ordonnanceur(String appli_ordonnanceur) {
		this.appli_ordonnanceur = appli_ordonnanceur;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getReprise() {
		return reprise;
	}

	public void setReprise(String reprise) {
		this.reprise = reprise;
	}

	/**
	 * This function do the real work and return the results
	 * @return
	 */
	protected abstract String incidentExecute();

	/**
	 * Transforme la liste des ids applicatif en liste d'applicatif
	 * @param mapApplication
	 * @param incidentList
	 */
	public static void initApplicationMap(Map<Integer, List<Applicatifs_Liste>> mapApplication, List<Incidents> incidentList) {
		for(Incidents inc : incidentList){
			String str = inc.getApplicatif();
			String[] applist = str.split(";");
			List<Integer> appIDList = new ArrayList<Integer>();
			 
			for(String s : applist){
				try{
					appIDList.add(Integer.parseInt(s));
				}catch(Exception e){
				}
			}
			List<Applicatifs_Liste> appArray;
			if(appIDList.isEmpty())
				appArray = new ArrayList<Applicatifs_Liste>();
			else
				appArray = ApplicatifDatabaseService.getMultiple(appIDList);

			mapApplication.put(inc.getId(), appArray);
		}
	}

	public static void initHardwareMap(Map<Integer, List<Hardware_Software>> hardMap, List<Incidents> listIncidents2) {
			for(Incidents inc : listIncidents2){
					String str = inc.getHard_software();
					if(str != null){
						String[] hardlist = str.split(";");
						List<Integer> hardIDList = new ArrayList<Integer>();
					 
						for(String s : hardlist){
							try{
								hardIDList.add(Integer.parseInt(s));
							}catch(Exception e){
							}
						}

						List<Hardware_Software> hardArray;
						if(hardIDList.isEmpty())
							hardArray = new ArrayList<Hardware_Software>();
						else
							hardArray = HardwareSoftwareDatabaseService.getMultiple(hardIDList);
			
						hardMap.put(inc.getId(), hardArray);
					}
			}
		
	}
	
}
