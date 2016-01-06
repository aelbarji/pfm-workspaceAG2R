package pilotage.incidents;

import pilotage.database.applicatif.ApplicatifDatabaseService;
import pilotage.database.applicatif.EnvironmentDatabaseService;
import pilotage.database.applicatif.HardwareSoftwareDatabaseService;
import pilotage.database.applicatif.ServiceDatabaseService;
import pilotage.database.astreintes.AstreinteDatabaseService;
import pilotage.database.incidents.HeuresOceorDatabaseService;
import pilotage.database.incidents.IncidentsOutilsDatabaseService;
import pilotage.database.incidents.IncidentsTypesDatabaseService;
import pilotage.database.machine.MachinesListesDatabaseService;
import pilotage.service.constants.PilotageConstants;

public class RedirectCreateIncidentAction extends AbstractIncidentsAction{

	private Integer validAst;
	private Integer nbAppelAstreinte;
	private String provenance;
	
	private static final long serialVersionUID = -3345898112752208959L;

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
			
			setNbAppelAstreinte(0);
			
			typeInciSelected = PilotageConstants.DEFAULT_INCIDENT_TYPE;
			
			//dateDebut	= DateService.dateToStr(new Date(), DateService.p1);
//			heureDebut 	= DateService.getTime(new Date(), DateService.pt1);
			return OK;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

}
