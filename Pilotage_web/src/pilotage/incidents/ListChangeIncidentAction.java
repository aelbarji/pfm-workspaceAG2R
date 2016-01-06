package pilotage.incidents;

import java.util.ArrayList;
import java.util.List;

import pilotage.database.applicatif.ApplicatifDatabaseService;
import pilotage.database.applicatif.EnvironmentDatabaseService;
import pilotage.database.applicatif.ServiceDatabaseService;
import pilotage.database.astreintes.AstreinteDatabaseService;
import pilotage.database.incidents.HeuresOceorDatabaseService;
import pilotage.database.incidents.IncidentsOutilsDatabaseService;
import pilotage.database.incidents.IncidentsTypesDatabaseService;
import pilotage.database.machine.MachinesListesDatabaseService;
import pilotage.metier.Applicatifs_Liste;
import pilotage.metier.Environnement;
import pilotage.metier.Machines_Liste;
import pilotage.metier.Services_Liste;
import pilotage.service.constants.PilotageConstants;

public class ListChangeIncidentAction extends AbstractIncidentsAction {

	private static final long serialVersionUID = 1247117701142903358L;

	@Override
	protected String incidentExecute() {
		listTypes = IncidentsTypesDatabaseService.getAll();
		listOceor = HeuresOceorDatabaseService.getAll();
		listAstreintes = AstreinteDatabaseService.getAll();
		listOutils = IncidentsOutilsDatabaseService.getAll();
		
		switch (selectAction) {
		case 1:
			onMachineChange();
			selectAction = 0;
			break;
		case 2:
			onAppliChange();
			selectAction = 0;
			break;
		case 3:
			onServiceChange();
			selectAction = 0;
			break;
		case 4:
			onEnviroChange();
			selectAction = 0;
			break;
		case 5:
			reset();
			selectAction = 0;
			break;
		}
		return OK;
	}

	/**
	 * Remise à 0 des 4 listes
	 */
	@SuppressWarnings("unchecked")
	private void reset() {
		listMachine 	= (List<Machines_Liste>) session.get(PilotageConstants.LISTE_MACHINES);
		listApp 		= (List<Applicatifs_Liste>) session.get(PilotageConstants.LISTE_APPLICATIFS);
		listService 	= (List<Services_Liste>) session.get(PilotageConstants.LISTE_SERVICES);
		listEnviron 	= (List<Environnement>) session.get(PilotageConstants.LISTE_ENVIRONNEMENTS);
		
		session.remove(PilotageConstants.LISTE_APPLICATIFS_ACTUELLE);
		session.remove(PilotageConstants.LISTE_ENVIRONNEMENTS_ACTUELLE);
		session.remove(PilotageConstants.LISTE_MACHINES_ACTUELLE);
		session.remove(PilotageConstants.LISTE_SERVICES_ACTUELLE);
		
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
	@SuppressWarnings("unchecked")
	private void onMachineChange() {
		//récupération de la liste des machines affichée
		listMachine = (List<Machines_Liste>) session.get(PilotageConstants.LISTE_MACHINES_ACTUELLE);
		if(listMachine == null)
			listMachine = (List<Machines_Liste>) session.get(PilotageConstants.LISTE_MACHINES);
		
		//récupération de la liste des applicatifs reliées à la machine
		if(machineSelected == null)
			listApp = (List<Applicatifs_Liste>) session.get(PilotageConstants.LISTE_APPLICATIFS);
		else
			listApp = ApplicatifDatabaseService.getByMachine(machineSelected);
		
		//récupération de la liste des id des applicatifs et de la liste des environnements
		List<Integer> listAppID = new ArrayList<Integer>();
		if (appliSelected == null || appliSelected.equals("")) {
			for(Applicatifs_Liste al : listApp){
				listAppID.add(al.getId());
			}
			//si aucune appli n'est sélectionnée, on prend l'union des envir pour avoir toutes les possibilités
			listEnviron = EnvironmentDatabaseService.getUnionByAppList(listAppID);
		}
		else{
			for(int i = 0; i < appliSelected.length; i++){
				listAppID.add(Integer.parseInt(appliSelected[i]));
			}
			//si des applis sont sélectionnées, on prend l'inter par rapports aux applis
			listEnviron = EnvironmentDatabaseService.getIntersectionByAppList(listAppID);
		}
		
		//récupération de la liste des services suivant la liste des applicatifs
		listService = ServiceDatabaseService.getUnionByAppList(listAppID);
		
		if(listApp == null || listApp.size() == 0)
			listApp = (List<Applicatifs_Liste>) session.get(PilotageConstants.LISTE_APPLICATIFS);
		if(listEnviron == null || listEnviron.size() == 0)
			listEnviron = (List<Environnement>) session.get(PilotageConstants.LISTE_ENVIRONNEMENTS);
		if(listService == null || listService.size() == 0)
			listService = (List<Services_Liste>) session.get(PilotageConstants.LISTE_SERVICES);
		
		session.put(PilotageConstants.LISTE_APPLICATIFS_ACTUELLE, listApp);
		session.put(PilotageConstants.LISTE_ENVIRONNEMENTS_ACTUELLE, listEnviron);
		session.put(PilotageConstants.LISTE_SERVICES_ACTUELLE, listService);
	}

	/**
	 * When the selection of application changes
	 */
	@SuppressWarnings("unchecked")
	private void onAppliChange() {
		//récupération de la liste des appli affichée
		listApp = (List<Applicatifs_Liste>) session.get(PilotageConstants.LISTE_APPLICATIFS_ACTUELLE);
		if(listApp == null)
			listApp = (List<Applicatifs_Liste>) session.get(PilotageConstants.LISTE_APPLICATIFS);

		if(appliSelected != null && appliSelected.length > 0){
			//récupération de la liste des id des applicatifs
			List<Integer> listAppID = new ArrayList<Integer>();
			for(int i = 0; i < appliSelected.length; i++){
				listAppID.add(Integer.parseInt(appliSelected[i]));
			}
			
			//récupération de la liste des machines, des services et des environnements suivant la liste des applicatifs
			listMachine = MachinesListesDatabaseService.getIntersectionByAppList(listAppID);
			listService = ServiceDatabaseService.getUnionByAppList(listAppID);
			listEnviron = EnvironmentDatabaseService.getIntersectionByAppList(listAppID);
			
			if(listMachine == null || listMachine.size() == 0)
				listMachine = (List<Machines_Liste>) session.get(PilotageConstants.LISTE_MACHINES);
			if(listEnviron == null || listEnviron.size() == 0)
				listEnviron = (List<Environnement>) session.get(PilotageConstants.LISTE_ENVIRONNEMENTS);
			if(listService == null || listService.size() == 0)
				listService = (List<Services_Liste>) session.get(PilotageConstants.LISTE_SERVICES);
			
			session.put(PilotageConstants.LISTE_ENVIRONNEMENTS_ACTUELLE, listEnviron);
			session.put(PilotageConstants.LISTE_MACHINES_ACTUELLE, listMachine);
			session.put(PilotageConstants.LISTE_SERVICES_ACTUELLE, listService);
		}
		else{
			listEnviron = (List<Environnement>) session.get(PilotageConstants.LISTE_ENVIRONNEMENTS_ACTUELLE);
			listMachine = (List<Machines_Liste>) session.get(PilotageConstants.LISTE_MACHINES_ACTUELLE);
			listService = (List<Services_Liste>) session.get(PilotageConstants.LISTE_SERVICES_ACTUELLE);
		}
	}

	/**
	 * When the selection of service changes
	 */
	@SuppressWarnings("unchecked")
	private void onServiceChange() {
		//récupération de la liste des services affichée
		listService = (List<Services_Liste>) session.get(PilotageConstants.LISTE_SERVICES_ACTUELLE);
		if(listService == null)
			listService = (List<Services_Liste>) session.get(PilotageConstants.LISTE_SERVICES);

		if (serviceSelected != null && !serviceSelected.equals("")) {
			//récupération de la liste des services sélectionnés
			List<Integer> serviceSelectedID = new ArrayList<Integer>();
			for (int i = 0; i < serviceSelected.length; i++) {
				serviceSelectedID.add(Integer.parseInt(serviceSelected[i]));
			}
			listApp = ApplicatifDatabaseService.getUnionByServiceList(serviceSelectedID);
			
			//récupération des id des applis sélectionnées ou si aucun sélectionnée les id de la liste des applis affichées
			List<Integer> listAppID = new ArrayList<Integer>();
			if (appliSelected == null || appliSelected.equals("")) {
				for (Applicatifs_Liste al : listApp) {
					listAppID.add(al.getId());
				}
				//Si aucune appli n'est sélectionnée, on prend l'union pour avoir toutes les machines et environnements possibles
				listMachine = MachinesListesDatabaseService.getUnionByAppList(listAppID);
				listEnviron = EnvironmentDatabaseService.getUnionByAppList(listAppID);
			}
			else{
				for(int i = 0; i < appliSelected.length; i++){
					listAppID.add(Integer.parseInt(appliSelected[i]));
				}
				//Si des applis sont sélectionnées, on prend l'inter pour les machines et les environnements
				listMachine = MachinesListesDatabaseService.getIntersectionByAppList(listAppID);
				listEnviron = EnvironmentDatabaseService.getIntersectionByAppList(listAppID);
			}

			if(listMachine == null || listMachine.size() == 0)
				listMachine = (List<Machines_Liste>) session.get(PilotageConstants.LISTE_MACHINES);
			if(listEnviron == null || listEnviron.size() == 0)
				listEnviron = (List<Environnement>) session.get(PilotageConstants.LISTE_ENVIRONNEMENTS);
			if(listApp == null || listApp.size() == 0)
				listApp = (List<Applicatifs_Liste>) session.get(PilotageConstants.LISTE_APPLICATIFS);
			
			session.put(PilotageConstants.LISTE_APPLICATIFS_ACTUELLE, listApp);
			session.put(PilotageConstants.LISTE_ENVIRONNEMENTS_ACTUELLE, listEnviron);
			session.put(PilotageConstants.LISTE_MACHINES_ACTUELLE, listMachine);
		}
		else{
			listEnviron = (List<Environnement>) session.get(PilotageConstants.LISTE_ENVIRONNEMENTS_ACTUELLE);
			listMachine = (List<Machines_Liste>) session.get(PilotageConstants.LISTE_MACHINES_ACTUELLE);
			listApp = (List<Applicatifs_Liste>) session.get(PilotageConstants.LISTE_APPLICATIFS_ACTUELLE);
		}
	}

	/**
	 * When the selection of environment changes
	 */
	@SuppressWarnings("unchecked")
	private void onEnviroChange() {
		//récupération de la liste des environnements affichée
		listEnviron = (List<Environnement>) session.get(PilotageConstants.LISTE_ENVIRONNEMENTS_ACTUELLE);
		if(listEnviron == null)
			listEnviron = (List<Environnement>) session.get(PilotageConstants.LISTE_ENVIRONNEMENTS);
		
		//récupération des applis
		if(enviroSelected == null)
			listApp = (List<Applicatifs_Liste>) session.get(PilotageConstants.LISTE_APPLICATIFS);
		else
			listApp = ApplicatifDatabaseService.getByEnvironnement(enviroSelected);
		
		//récupération de la liste des id des applicatifs et de la liste des machines
		List<Integer> listAppID = new ArrayList<Integer>();
		if(appliSelected == null || appliSelected.equals("")){
			for(Applicatifs_Liste al : listApp){
				listAppID.add(al.getId());
			}
			//si aucune appli n'est sélectionnée, on prend l'union par rapports aux applis
			listMachine = MachinesListesDatabaseService.getUnionByAppList(listAppID);
		}
		else{
			for(int i = 0; i < appliSelected.length; i++){
				listAppID.add(Integer.parseInt(appliSelected[i]));
			}
			//si des applis sont sélectionnées, on prend l'inter par rapports aux applis
			listMachine = MachinesListesDatabaseService.getIntersectionByAppList(listAppID);
		}

		listService = ServiceDatabaseService.getUnionByAppList(listAppID);

		if(listMachine == null || listMachine.size() == 0)
			listMachine = (List<Machines_Liste>) session.get(PilotageConstants.LISTE_MACHINES);
		if(listService == null || listService.size() == 0)
			listService = (List<Services_Liste>) session.get(PilotageConstants.LISTE_SERVICES);
		if(listApp == null || listApp.size() == 0)
			listApp = (List<Applicatifs_Liste>) session.get(PilotageConstants.LISTE_APPLICATIFS);
			
		session.put(PilotageConstants.LISTE_APPLICATIFS_ACTUELLE, listApp);
		session.put(PilotageConstants.LISTE_MACHINES_ACTUELLE, listMachine);
		session.put(PilotageConstants.LISTE_SERVICES_ACTUELLE, listService);		
	}
}
