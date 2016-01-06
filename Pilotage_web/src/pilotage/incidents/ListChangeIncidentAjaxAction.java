package pilotage.incidents;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import pilotage.database.applicatif.ApplicatifDatabaseService;
import pilotage.database.applicatif.EnvironmentDatabaseService;
import pilotage.database.applicatif.HardwareSoftwareDatabaseService;
import pilotage.database.applicatif.ServiceDatabaseService;
import pilotage.database.machine.MachinesListesDatabaseService;
import pilotage.metier.Applicatifs_Liste;
import pilotage.service.constants.PilotageConstants;

public class ListChangeIncidentAjaxAction extends AbstractIncidentsAction {

	private static final long serialVersionUID = -4808250234698118923L;

	@Override
	protected String incidentExecute() {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		
		Integer selectAction = Integer.parseInt(request.getParameter("selectAction"));
		Integer listMselect = 0;
		String[] listAselect = null;
		String[] listSselect = null;
		Integer listEselect = 0;
		
		if (request.getParameter("listM") != null && request.getParameter("listM").length() > 0) {
			listMselect = Integer.parseInt(request.getParameter("listM"));
		} else if (selectAction == 1 ){
			selectAction = 0;
		}
		if (request.getParameter("listA") != null && request.getParameter("listA").length() > 0) {
			listAselect = request.getParameter("listA").split(PilotageConstants.SEPARATEUR_4);
		} else if (selectAction == 2 ){
			selectAction = 0;
		}
		if (request.getParameter("listS") != null && request.getParameter("listS").length() > 0) {
			listSselect = request.getParameter("listS").split(PilotageConstants.SEPARATEUR_4);
		} else if (selectAction == 3 ){
			selectAction = 0;
		}
		if (request.getParameter("listE") != null && request.getParameter("listE").length() > 0) {
			listEselect = Integer.parseInt(request.getParameter("listE"));
		} else if (selectAction == 4 ){
			selectAction = 0;
		}
		
		machineSelected = listMselect;
		appliSelected = listAselect;
		serviceSelected = listSselect;
		enviroSelected = listEselect;
		
		switch (selectAction) {
		case 1:
			onMachineChange();
			break;
		case 2:
			onAppliChange();
			break;
		case 3:
			onServiceChange();
			break;
		case 4:
			onEnviroChange();
			break;
		case 5:
			reset();
			break;
		default:
			break;
		}
		
		// response Ajax JSON
		try {
			JSONArray mainArray = new JSONArray();
			JSONObject jsonsArray = new JSONObject();
			if (selectAction == 1) {	
				jsonsArray.put("listApp", listApp);
				jsonsArray.put("listEnviron", listEnviron);
				jsonsArray.put("listService", listService);	
				jsonsArray.put("listHard", listHard);
			}
			if (selectAction == 2) {	
				jsonsArray.put("listEnviron", listEnviron);
				jsonsArray.put("listMachine", listMachine);
				jsonsArray.put("listService", listService);	
				jsonsArray.put("listHard", listHard);
			}
			if (selectAction == 3) {	
				jsonsArray.put("listApp", listApp);
				jsonsArray.put("listEnviron", listEnviron);
				jsonsArray.put("listMachine", listMachine);	
				jsonsArray.put("listHard", listHard);
			}
			if (selectAction == 4) {	
				jsonsArray.put("listApp", listApp);
				jsonsArray.put("listMachine", listMachine);
				jsonsArray.put("listService", listService);	
				jsonsArray.put("listHard", listHard);
			}
			if (selectAction == 5) {
				jsonsArray.put("listMachine", listMachine);
				jsonsArray.put("listApp", listApp);
				jsonsArray.put("listService", listService);	
				jsonsArray.put("listEnviron", listEnviron);
				jsonsArray.put("listHard", listHard);
			}
			
			mainArray.add(jsonsArray);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("liste", mainArray);
			// chaque clé de notre map devient une clé dans l'objet JSON (utilisation de Json-lib)
			JSONObject json = JSONObject.fromObject(jsonObject);
			
			// façon d'envoyer l'objet JSON pour que Prototype puisse le récupérer
			response.setContentType("application/json");
			PrintWriter out = response.getWriter();
	        out.println(json.toString());
	        out.flush();
	        out.close();
	        selectAction = 0;
		}
		catch (Exception e) {
			Map<String, String> hm = new HashMap<String, String>();
			hm.put("error", "Une erreur est survenue lors du chargement des listes : " + e.getMessage());
			JSONObject json = JSONObject.fromObject(hm);
			response.setHeader("X-JSON", json.toString());
		}
		
		return null;
	}

	/**
	 * Remise à 0 des 4 listes
	 */
	private void reset() {
		
		listMachine 	= MachinesListesDatabaseService.getAll();
		listApp 		= ApplicatifDatabaseService.getAll();
		listService 	= ServiceDatabaseService.getAll();
		listEnviron 	= EnvironmentDatabaseService.getAll();
		listHard		=  HardwareSoftwareDatabaseService.getAll();
		
		machineSelected = null;
		appliSelected = null;
		serviceSelected = null;
		enviroSelected = null;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}
	
	/**
	 * When the selection of machines changes
	 */
	private void onMachineChange() {
	
		listApp = ApplicatifDatabaseService.getByMachine(machineSelected);
		listHard		=  HardwareSoftwareDatabaseService.getAll();
		
		if (listApp.isEmpty()){
			listApp 		= ApplicatifDatabaseService.getAll();
			listService 	= ServiceDatabaseService.getAll();
			listEnviron 	= EnvironmentDatabaseService.getAll();
		}
		else{
			//récupération de la liste des id des applicatifs et de la liste des environnements
			List<Integer> listAppID = new ArrayList<Integer>();
			if (appliSelected == null || appliSelected.equals("")) {
				for(Applicatifs_Liste al : listApp){
					listAppID.add(al.getId());
				}
				//si aucune appli n'est sélectionnée, on prend l'union des envir pour avoir toutes les possibilités
				listEnviron = EnvironmentDatabaseService.getUnionByAppList(listAppID);
				if (listEnviron.isEmpty()){
					listEnviron = EnvironmentDatabaseService.getAll();
				}
			}
			else{
				for(int i = 0; i < appliSelected.length; i++){
					listAppID.add(Integer.parseInt(appliSelected[i]));
				}
				//si des applis sont sélectionnées, on prend l'inter par rapports aux applis
				listEnviron = EnvironmentDatabaseService.getIntersectionByAppList(listAppID);
				if (listEnviron.isEmpty()){
					listEnviron = EnvironmentDatabaseService.getAll();
				}
			}
			//récupération de la liste des services suivant la liste des applicatifs
			listService = ServiceDatabaseService.getUnionByAppList(listAppID);
			if (listService.isEmpty()){
				listService = ServiceDatabaseService.getAll();
			}
		}
	}

	/**
	 * When the selection of application changes
	 */
	private void onAppliChange() {

		//récupération de la liste des id des applicatifs
		List<Integer> listAppID = new ArrayList<Integer>();
		for(int i = 0; i < appliSelected.length; i++){
			listAppID.add(Integer.parseInt(appliSelected[i]));
		}

		//récupération de la liste des machines, des services et des environnements suivant la liste des applicatifs
		listMachine = MachinesListesDatabaseService.getIntersectionByAppList(listAppID);
		if (listMachine.isEmpty()){
			listMachine = MachinesListesDatabaseService.getAll();
		}
		listService = ServiceDatabaseService.getUnionByAppList(listAppID);
		if (listService.isEmpty()){
			listService = ServiceDatabaseService.getAll();
		}
		listEnviron = EnvironmentDatabaseService.getIntersectionByAppList(listAppID);
		if (listEnviron.isEmpty()){
			listEnviron = EnvironmentDatabaseService.getAll();
		}
		listHard		=  HardwareSoftwareDatabaseService.getAll();
	}

	/**
	 * When the selection of service changes
	 */
	private void onServiceChange() {
		listHard		=  HardwareSoftwareDatabaseService.getAll();
		
		//récupération de la liste des services sélectionnés
		List<Integer> serviceSelectedID = new ArrayList<Integer>();
		for (int i = 0; i < serviceSelected.length; i++) {
			serviceSelectedID.add(Integer.parseInt(serviceSelected[i]));
		}
		listApp = ApplicatifDatabaseService.getUnionByServiceList(serviceSelectedID);

		if (listApp.isEmpty()){
			listMachine 	= MachinesListesDatabaseService.getAll();
			listApp 		= ApplicatifDatabaseService.getAll();
			listEnviron 	= EnvironmentDatabaseService.getAll();	
		}
		else{
			//récupération des id des applis sélectionnées ou si aucun sélectionnée les id de la liste des applis affichées
			List<Integer> listAppID = new ArrayList<Integer>();
			if (appliSelected == null || appliSelected.equals("")) {
				for (Applicatifs_Liste al : listApp) {
					listAppID.add(al.getId());
				}
				//Si aucune appli n'est sélectionnée, on prend l'union pour avoir toutes les machines et environnements possibles
				listMachine = MachinesListesDatabaseService.getUnionByAppList(listAppID);
				if (listMachine.isEmpty()){
					listMachine = MachinesListesDatabaseService.getAll();
				}
				listEnviron = EnvironmentDatabaseService.getUnionByAppList(listAppID);
				if (listEnviron.isEmpty()){
					listEnviron = EnvironmentDatabaseService.getAll();
				}
			}
			else{
				for(int i = 0; i < appliSelected.length; i++){
					listAppID.add(Integer.parseInt(appliSelected[i]));
				}
				//Si des applis sont sélectionnées, on prend l'inter pour les machines et les environnements
				listMachine = MachinesListesDatabaseService.getIntersectionByAppList(listAppID);
				if (listMachine.isEmpty()){
					listMachine = MachinesListesDatabaseService.getAll();
				}
				listEnviron = EnvironmentDatabaseService.getIntersectionByAppList(listAppID);
				if (listEnviron.isEmpty()){
					listEnviron = EnvironmentDatabaseService.getAll();
				}
			}
		}
	}

	/**
	 * When the selection of environment changes
	 */
	@SuppressWarnings("unchecked")
	private void onEnviroChange() {
		listHard		=  HardwareSoftwareDatabaseService.getAll();
		//récupération des applis
		if(enviroSelected == null){
			listApp = (List<Applicatifs_Liste>) session.get(PilotageConstants.LISTE_APPLICATIFS);
		}
		else{
			listApp = ApplicatifDatabaseService.getByEnvironnement(enviroSelected);
		}
		if (listApp.isEmpty()){
			listMachine 	= MachinesListesDatabaseService.getAll();
			listApp 		= ApplicatifDatabaseService.getAll();
			listService 	= ServiceDatabaseService.getAll();
		}
		else{
			//récupération de la liste des id des applicatifs et de la liste des machines
			List<Integer> listAppID = new ArrayList<Integer>();
			if(appliSelected == null || appliSelected.equals("")){
				for(Applicatifs_Liste al : listApp){
					listAppID.add(al.getId());
				}
				//si aucune appli n'est sélectionnée, on prend l'union par rapports aux applis
				listMachine = MachinesListesDatabaseService.getUnionByAppList(listAppID);
				if (listMachine.isEmpty()){
					listMachine = MachinesListesDatabaseService.getAll();
				}
			}
			else{
				for(int i = 0; i < appliSelected.length; i++){
					listAppID.add(Integer.parseInt(appliSelected[i]));
				}
				//si des applis sont sélectionnées, on prend l'inter par rapports aux applis
				listMachine = MachinesListesDatabaseService.getIntersectionByAppList(listAppID);
				if (listMachine.isEmpty()){
					listMachine = MachinesListesDatabaseService.getAll();
				}
			}
			listService = ServiceDatabaseService.getUnionByAppList(listAppID);
			if (listService.isEmpty()){
				listService = ServiceDatabaseService.getAll();
			}
		}
	}
}
