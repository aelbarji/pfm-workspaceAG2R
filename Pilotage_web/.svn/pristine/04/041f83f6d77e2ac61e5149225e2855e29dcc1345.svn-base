package pilotage.applications;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import pilotage.database.applicatif.ApplicatifDatabaseService;
import pilotage.database.applicatif.ServiceDatabaseService;
import pilotage.database.environnement.EnvironnementDatabaseService;
import pilotage.database.historique.HistoriqueDatabaseService;
import pilotage.database.machine.MachineInterlocuteurDatabaseService;
import pilotage.database.machine.MachinesListesDatabaseService;
import pilotage.database.services.ServicesDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Applicatifs_Liste;
import pilotage.metier.Environnement;
import pilotage.metier.Interlocuteur;
import pilotage.metier.Machines_Liste;
import pilotage.metier.Services_Liste;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;
public class ModifyApplicationListeAction extends AbstractAction{

	private static final long serialVersionUID = -4247849760931789967L;

	private String sort;
	private String sens;
	private int page;
	private int nrPages;
	private int nrPerPage;
	private Integer selectRow;
	
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

	public String[] getServiceSelected() {
		return serviceSelected;
	}

	public void setServiceSelected(String[] serviceSelected) {
		this.serviceSelected = serviceSelected;
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

	@Override
	protected boolean validateMetier() {
		try{
			if(ApplicatifDatabaseService.exists(selectRow, nom)){
				error = MessageFormat.format(getText("application.creation.existe.deja"), nom );
				prepareRedirectToModificationPage();
				
				return false;
			}
			return true;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Modification d'une application - ", e);
			prepareRedirectToModificationPage();
			return false;
		}
	}
	
	@Override
	protected String executeMetier() {
		try {
			List<Integer> listAppId = new ArrayList<Integer>();
			listAppId.add(selectRow);
			Applicatifs_Liste app = ApplicatifDatabaseService.get(selectRow);
			List<Interlocuteur> listInterloc = MachineInterlocuteurDatabaseService.getAllByApp(selectRow);
			List<Machines_Liste> listMac = MachinesListesDatabaseService.getUnionByAppList(listAppId);
			List<Environnement> listEnv = EnvironnementDatabaseService.getAllByApp(selectRow);
			List<Services_Liste> listServ = ServiceDatabaseService.getUnionByAppList(listAppId);
			
			StringBuffer buff = new StringBuffer();
			if(!app.getApplicatif().equals(nom)){
				buff.append("nom, ");
			}
			if((app.getDescription() == null && (description != null && !description.equals("")))
				|| (app.getDescription() != null 
				&& !app.getDescription().equals(description))){
					buff.append("description, ");
			}
			if((app.getDocumentation() == null && (documentation != null && !documentation.equals("")))
				|| (app.getDocumentation() != null 
				&& !app.getDocumentation().equals(documentation))){
					buff.append("documentation, ");
			}
			if(((listInterloc == null || listInterloc.size() == 0) && (interlocuteurSelected != null && interlocuteurSelected.length > 0)) || (listInterloc != null && listInterloc.size() > 0 && (interlocuteurSelected == null || listInterloc.size() != interlocuteurSelected.length))){
				buff.append("gérants, ");
			}
			else if(listInterloc != null){
				for(Interlocuteur interloc : listInterloc){
					boolean inList = false;
					for(String idString : interlocuteurSelected){
						if(interloc.getId().toString().equals(idString)){
							inList = true;
							break;
						}
					}
					if(!inList){
						buff.append("gérants, ");
						break;
					}
				}
			}

			if(((listMac == null || listMac.size() == 0) && (machineSelected != null && machineSelected.length > 0)) || (listMac != null && listMac.size() > 0 && (machineSelected == null || listMac.size() != machineSelected.length))){
				buff.append("machines, ");
			}
			else if(listMac != null){
				for(Machines_Liste mac : listMac){
					boolean inList = false;
					for(String idString : machineSelected){
						if(mac.getId().toString().equals(idString)){
							inList = true;
							break;
						}
					}
					if(!inList){
						buff.append("machines, ");
						break;
					}
				}
			}

			if(((listEnv == null || listEnv.size() == 0) && (environnementSelected != null && environnementSelected.length > 0)) || (listEnv != null && listEnv.size() > 0 && (environnementSelected == null || listEnv.size() != environnementSelected.length))){
				buff.append("environnements, ");
			}
			else if(listEnv != null){
				for(Environnement env : listEnv){
					boolean inList = false;
					for(String idString : environnementSelected){
						if(env.getId().toString().equals(idString)){
							inList = true;
							break;
						}
					}
					if(!inList){
						buff.append("environnements, ");
						break;
					}
				}
			}

			if(((listServ == null || listServ.size() == 0) && (serviceSelected != null && serviceSelected.length > 0)) || (listServ != null && listServ.size() > 0 && (serviceSelected == null || listServ.size() != serviceSelected.length))){
				buff.append("services, ");
			}
			else if(listServ != null){
				for(Services_Liste serv : listServ){
					boolean inList = false;
					for(String idString : serviceSelected){
						if(serv.getId().toString().equals(idString)){
							inList = true;
							break;
						}
					}
					if(!inList){
						buff.append("services");
						break;
					}
				}
			}
			
			ApplicatifDatabaseService.modify(selectRow, nom, description, documentation, machineSelected, environnementSelected, interlocuteurSelected, serviceSelected);
			
			info = MessageFormat.format(getText("application.modification.valide"), nom);
			HistoriqueDatabaseService.create(null, MessageFormat.format(getText("historique.application.modification") ,new Object[]{nom, selectRow, buff.toString()}), (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_APPLICATIONS);

			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Modification d'une application - ", e);
			
			prepareRedirectToModificationPage();
			
			return ERROR;
		}
	}
	
	private void prepareRedirectToModificationPage() {
		listeMachine = MachinesListesDatabaseService.getAll();
		listeEnvironnement = EnvironnementDatabaseService.getAll();
		listeInterlocuteur = MachineInterlocuteurDatabaseService.getAll();
		listeService = ServicesDatabaseService.getAll();
	}

	
}