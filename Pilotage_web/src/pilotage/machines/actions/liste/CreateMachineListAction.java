package pilotage.machines.actions.liste;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import pilotage.database.applicatif.ApplicatifDatabaseService;
import pilotage.database.historique.HistoriqueDatabaseService;
import pilotage.database.machine.MachineDomaineDatabaseService;
import pilotage.database.machine.MachineEnvironnementDatabaseService;
import pilotage.database.machine.MachineInterlocuteurDatabaseService;
import pilotage.database.machine.MachineOSDatabaseService;
import pilotage.database.machine.MachineSiteDatabaseService;
import pilotage.database.machine.MachineTypeDatabaseService;
import pilotage.database.machine.MachinesListesDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Applicatifs_Liste;
import pilotage.metier.Domaine_Wind;
import pilotage.metier.Environnement;
import pilotage.metier.Interlocuteur;
import pilotage.metier.Machine_Os;
import pilotage.metier.Machines_Liste;
import pilotage.metier.Machines_Site;
import pilotage.metier.Machines_Type;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;
import pilotage.utils.Pagination;

public class CreateMachineListAction extends AbstractAction {
	private static final long serialVersionUID = -1299589526541605339L;
	private String sort;
	private String sens;
	private int page;
	private int nrPages;
	private int nrPerPage;
	
	private List<Machines_Type> listMachineType;
	private List<Machines_Site> listMachineSite;
	private List<Machine_Os> listMachineOS;
	private List<Interlocuteur> listMachineInterlocuteur;
	private List<Domaine_Wind> listMachineDomaine;
	private List<Environnement> listMachineEnvironnement;	
	private List<Applicatifs_Liste> listApplicatif;
	
	private Pagination<Machines_Liste> pagination;
	
	private String nom;
	private Integer type;
	private Integer site;
	private Integer domaine;
	private Integer interlocuteur;
	private Integer environnement;
	private Integer os;
	private String commentaire;
	
	private List<Map<String, String>> listApplications;
	private List<Map<String, String>> listIPs;
	private List<Map<String, String>> listAccesLocal;
	
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

	public List<Machines_Type> getListMachineType() {
		return listMachineType;
	}

	public void setListMachineType(List<Machines_Type> listMachineType) {
		this.listMachineType = listMachineType;
	}

	public List<Machines_Site> getListMachineSite() {
		return listMachineSite;
	}

	public void setListMachineSite(List<Machines_Site> listMachineSite) {
		this.listMachineSite = listMachineSite;
	}

	public List<Machine_Os> getListMachineOS() {
		return listMachineOS;
	}

	public void setListMachineOS(List<Machine_Os> listMachineOS) {
		this.listMachineOS = listMachineOS;
	}

	public List<Interlocuteur> getListMachineInterlocuteur() {
		return listMachineInterlocuteur;
	}

	public void setListMachineInterlocuteur(
			List<Interlocuteur> listMachineInterlocuteur) {
		this.listMachineInterlocuteur = listMachineInterlocuteur;
	}

	public List<Domaine_Wind> getListMachineDomaine() {
		return listMachineDomaine;
	}

	public void setListMachineDomaine(List<Domaine_Wind> listMachineDomaine) {
		this.listMachineDomaine = listMachineDomaine;
	}

	public List<Environnement> getListMachineEnvironnement() {
		return listMachineEnvironnement;
	}

	public void setListMachineEnvironnement(
			List<Environnement> listMachineEnvironnement) {
		this.listMachineEnvironnement = listMachineEnvironnement;
	}

	public List<Applicatifs_Liste> getListApplicatif() {
		return listApplicatif;
	}

	public void setListApplicatif(List<Applicatifs_Liste> listApplicatif) {
		this.listApplicatif = listApplicatif;
	}

	public Pagination<Machines_Liste> getPagination() {
		return pagination;
	}

	public void setPagination(Pagination<Machines_Liste> pagination) {
		this.pagination = pagination;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getSite() {
		return site;
	}

	public void setSite(Integer site) {
		this.site = site;
	}

	public Integer getDomaine() {
		return domaine;
	}

	public void setDomaine(Integer domaine) {
		this.domaine = domaine;
	}

	public Integer getInterlocuteur() {
		return interlocuteur;
	}

	public void setInterlocuteur(Integer interlocuteur) {
		this.interlocuteur = interlocuteur;
	}

	public Integer getEnvironnement() {
		return environnement;
	}

	public void setEnvironnement(Integer environnement) {
		this.environnement = environnement;
	}

	public Integer getOs() {
		return os;
	}

	public void setOs(Integer os) {
		this.os = os;
	}
	
	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}


	public List<Map<String, String>> getListApplications() {
		return listApplications;
	}

	public void setListApplications(List<Map<String, String>> listApplications) {
		this.listApplications = listApplications;
	}

	public List<Map<String, String>> getListIPs() {
		return listIPs;
	}

	public void setListIPs(List<Map<String, String>> listIPs) {
		this.listIPs = listIPs;
	}

	public List<Map<String, String>> getListAccesLocal() {
		return listAccesLocal;
	}

	public void setListAccesLocal(List<Map<String, String>> listAccesLocal) {
		this.listAccesLocal = listAccesLocal;
	}

	@Override
	protected boolean validateMetier() {
		try{
			if(MachinesListesDatabaseService.exists(null, nom)){
				error = MessageFormat.format(getText("machine.creation.existe.deja"), nom );
				prepareRedirectToCreationPage();
				
				return false;
			}
			return true;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Création d'une machine - ", e);
			prepareRedirectToCreationPage();
			return false;
		}
	}

	@Override
	protected String executeMetier() {
		try{
			HttpServletRequest request = ServletActionContext.getRequest();
			
			//recupération des appli à ajouter
			listApplications = new ArrayList<Map<String,String>>();
			RedirectCreateMachineListAction.getListApplicationsToAdd(request, listApplications);

			//récupération des ip à ajouter
			listIPs = new ArrayList<Map<String,String>>();
			RedirectCreateMachineListAction.getListIPToAdd(request, listIPs);
			
			//récupération des accès local à ajouter
			listAccesLocal = new ArrayList<Map<String,String>>();
			RedirectCreateMachineListAction.getListLoginAccesLocal(request, listAccesLocal);
			
			MachinesListesDatabaseService.create(nom, type, site, domaine, interlocuteur, environnement, os, commentaire, listApplications, listIPs, listAccesLocal);
			info = MessageFormat.format(getText("machine.creation.valide"), new Object[]{nom});
			HistoriqueDatabaseService.create(null, MessageFormat.format(getText("historique.machine.creation") ,new Object[]{MachinesListesDatabaseService.getId(nom)}), (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_MACHINES);
			
			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Création d'une machine - ", e);
			prepareRedirectToCreationPage();
			return ERROR;
		}
	}

	/**
	 * Action avant redirection vers la page de création
	 */
	private void prepareRedirectToCreationPage(){
		HttpServletRequest request = ServletActionContext.getRequest();
		
		listMachineType = MachineTypeDatabaseService.getAll();
		listMachineSite = MachineSiteDatabaseService.getAll("site", "asc", null, null, null);
		listMachineOS = MachineOSDatabaseService.getAll();
		listMachineInterlocuteur = MachineInterlocuteurDatabaseService.getAll();
		listMachineDomaine = MachineDomaineDatabaseService.getAll();
		listMachineEnvironnement = MachineEnvironnementDatabaseService.getAll();	
		listApplicatif = ApplicatifDatabaseService.getAll();
		
		//recupération des appli à ajouter
		listApplications = new ArrayList<Map<String,String>>();
		RedirectCreateMachineListAction.getListApplicationsToAdd(request, listApplications);
		
		//suppression de la liste des applicatifs de tous les applicatifs à ajouter dans la machine
		//remplissage des noms des applis
		RedirectCreateMachineListAction.removeApplications(listApplicatif, listApplications);
		
		//récupération des ip à ajouter
		listIPs = new ArrayList<Map<String,String>>();
		RedirectCreateMachineListAction.getListIPToAdd(request, listIPs);
		
		//récupération des accès local à ajouter
		listAccesLocal = new ArrayList<Map<String,String>>();
		RedirectCreateMachineListAction.getListLoginAccesLocal(request, listAccesLocal);
	}
}
