package pilotage.applications;

import java.util.ArrayList;
import java.util.List;

import pilotage.database.applicatif.ApplicatifDatabaseService;
import pilotage.database.applicatif.ServiceDatabaseService;
import pilotage.database.environnement.EnvironnementDatabaseService;
import pilotage.database.machine.MachineInterlocuteurDatabaseService;
import pilotage.database.machine.MachinesListesDatabaseService;
import pilotage.database.services.ServicesDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Applicatifs_Liste;
import pilotage.metier.Environnement;
import pilotage.metier.Interlocuteur;
import pilotage.metier.Machines_Liste;
import pilotage.metier.Services_Liste;

public class RedirectApplicationListeAction extends AbstractAction{

	private static final long serialVersionUID = -4247849760931789967L;

	private Integer selectRow;
	
	private String sort;
	private String sens;
	private int page;
	private int nrPages;
	private int nrPerPage;
	
	private String filtreNom;
	private String filtreDescription;
	private String filtreDocument;
	
	private String nom;
	private String description;
	private String documentation;
	
	private List<Machines_Liste> listeMachine;
	private List<Environnement> listeEnvironnement;
	private List<Interlocuteur> listeInterlocuteur;
	private List<Services_Liste> listeService;
	
	protected String[] machineSelected;
	protected String[] environnementSelected;
	protected String[] interlocuteurSelected;
	protected String[] serviceSelected;

	public Integer getSelectRow() {
		return selectRow;
	}

	public void setSelectRow(Integer selectRow) {
		this.selectRow = selectRow;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getSens() {
		return sens;
	}

	public void setSens(String sens) {
		this.sens = sens;
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

	public String getFiltreNom() {
		return filtreNom;
	}

	public void setFiltreNom(String filtreNom) {
		this.filtreNom = filtreNom;
	}

	public String getFiltreDescription() {
		return filtreDescription;
	}

	public void setFiltreDescription(String filtreDescription) {
		this.filtreDescription = filtreDescription;
	}

	public String getFiltreDocument() {
		return filtreDocument;
	}

	public void setFiltreDocument(String filtreDocument) {
		this.filtreDocument = filtreDocument;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDocumentation() {
		return documentation;
	}

	public void setDocumentation(String document) {
		this.documentation = document;
	}

	public List<Machines_Liste> getListeMachine() {
		return listeMachine;
	}

	public void setListeMachine(List<Machines_Liste> listeMachine) {
		this.listeMachine = listeMachine;
	}

	public List<Environnement> getListeEnvironnement() {
		return listeEnvironnement;
	}

	public void setListeEnvironnement(List<Environnement> listeEnvironnement) {
		this.listeEnvironnement = listeEnvironnement;
	}

	public List<Interlocuteur> getListeInterlocuteur() {
		return listeInterlocuteur;
	}

	public void setListeInterlocuteur(List<Interlocuteur> listeInterlocuteur) {
		this.listeInterlocuteur = listeInterlocuteur;
	}

	public List<Services_Liste> getListeService() {
		return listeService;
	}

	public void setListeService(List<Services_Liste> listeService) {
		this.listeService = listeService;
	}

	public String[] getMachineSelected() {
		return machineSelected;
	}

	public void setMachineSelected(String[] machineSelected) {
		this.machineSelected = machineSelected;
	}

	public String[] getEnvironnementSelected() {
		return environnementSelected;
	}

	public void setEnvironnementSelected(String[] environnementSelected) {
		this.environnementSelected = environnementSelected;
	}

	public String[] getInterlocuteurSelected() {
		return interlocuteurSelected;
	}

	public void setInterlocuteurSelected(String[] interlocuteurSelected) {
		this.interlocuteurSelected = interlocuteurSelected;
	}

	public String[] getServiceSelected() {
		return serviceSelected;
	}

	public void setServiceSelected(String[] serviceSelected) {
		this.serviceSelected = serviceSelected;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}
	
	@Override
	protected String executeMetier() {
		try {
			if(selectRow != null){
				Applicatifs_Liste app = ApplicatifDatabaseService.get(selectRow);
				
				nom = app.getApplicatif();
				description = app.getDescription();
				documentation = app.getDocumentation();
				
				List<Integer> listAppID = new ArrayList<Integer>();
				listAppID.add(app.getId());
				List<Machines_Liste> listMachineByApp = MachinesListesDatabaseService.getUnionByAppList(listAppID);
				if(listMachineByApp != null){
					machineSelected = new String[listMachineByApp.size()];
					for(int i = 0; i < listMachineByApp.size(); ++i){
						machineSelected[i] = listMachineByApp.get(i).getId().toString();
					}
				}
				
				List<Environnement> listEnvByApp = EnvironnementDatabaseService.getAllByApp(app.getId());
				if(listEnvByApp != null){
					environnementSelected = new String[listEnvByApp.size()];
					for(int i = 0; i < listEnvByApp.size(); ++i){
						environnementSelected[i] = listEnvByApp.get(i).getId().toString();
					}
				}
				
				List<Interlocuteur> listInterlocByApp = MachineInterlocuteurDatabaseService.getAllByApp(app.getId());
				if(listInterlocByApp != null){
					interlocuteurSelected = new String[listInterlocByApp.size()];
					for(int i = 0; i < listInterlocByApp.size(); ++i){
						interlocuteurSelected[i] = listInterlocByApp.get(i).getId().toString();
					}
				}
				
				List<Services_Liste> listServiceByApp = ServiceDatabaseService.getUnionByAppList(listAppID);
				if(listServiceByApp != null){
					serviceSelected = new String[listServiceByApp.size()];
					for(int i = 0; i < listServiceByApp.size(); ++i){
						serviceSelected[i] = listServiceByApp.get(i).getId().toString();
					}
				}
			}
			
			listeMachine = MachinesListesDatabaseService.getAll();
			listeEnvironnement = EnvironnementDatabaseService.getAll();
			listeInterlocuteur = MachineInterlocuteurDatabaseService.getAll();
			listeService = ServicesDatabaseService.getAll();
			
			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Redirection application - ", e);
			return ERROR;
		}

	}
	
}