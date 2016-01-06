package pilotage.machines.actions.liste;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import pilotage.database.applicatif.ApplicatifDatabaseService;
import pilotage.database.machine.MachineDomaineDatabaseService;
import pilotage.database.machine.MachineEnvironnementDatabaseService;
import pilotage.database.machine.MachineInterlocuteurDatabaseService;
import pilotage.database.machine.MachineOSDatabaseService;
import pilotage.database.machine.MachineSiteDatabaseService;
import pilotage.database.machine.MachineTypeDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Applicatifs_Liste;
import pilotage.metier.Domaine_Wind;
import pilotage.metier.Environnement;
import pilotage.metier.Interlocuteur;
import pilotage.metier.Machine_Os;
import pilotage.metier.Machines_Site;
import pilotage.metier.Machines_Type;

public class RedirectCreateMachineListAction extends AbstractAction {

	private static final long serialVersionUID = 2800115204815595744L;
	public static final String APP_NAME = "appName";
	public static final String APP_ID = "appID";
	
	public static final String IP_IP = "IP";
	public static final String IP_COMMENT = "Commentaire";
	
	public static final String AL_LOGIN = "login";
	public static final String AL_PASSWORD = "password";
	public static final String AL_DOMAINE = "domaine";
	
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
	
	private String nom;
	private Integer type;
	private Integer site;
	private Integer domaine;
	private Integer interlocuteur;
	private Integer environnement;
	private Integer os;
	private String commentaire;
	
	private Integer appToAdd;
	private Integer deleteAppli;
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

	public Integer getAppToAdd() {
		return appToAdd;
	}

	public void setAppToAdd(Integer appToAdd) {
		this.appToAdd = appToAdd;
	}

	public Integer getDeleteAppli() {
		return deleteAppli;
	}

	public void setDeleteAppli(Integer deleteAppli) {
		this.deleteAppli = deleteAppli;
	}

	public List<Map<String, String>> getListApplications() {
		return listApplications;
	}

	public void setListApplications(List<Map<String, String>> listApplications) {
		this.listApplications = listApplications;
	}

	public List<Map<String, String>> getListAccesLocal() {
		return listAccesLocal;
	}

	public void setListAccesLocal(List<Map<String, String>> listAccesLocal) {
		this.listAccesLocal = listAccesLocal;
	}

	public List<Map<String, String>> getListIPs() {
		return listIPs;
	}

	public void setListIPs(List<Map<String, String>> listIPs) {
		this.listIPs = listIPs;
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

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
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
		getListApplicationsToAdd(request, listApplications);
		
		//Si on a cliqué sur la suppression d'une appli
		if(deleteAppli != null && deleteAppli != -1){
			Map<String, String> mapToRemove = null;
			for(Map<String, String> appli : listApplications){
				if(appli.get(APP_ID).equals(deleteAppli.toString())){
					mapToRemove = appli;
					break;
				}
			}
			if(mapToRemove != null)
				listApplications.remove(mapToRemove);
		}
		//si on a cliqué sur l'ajout d'une appli, on l'ajoute aux appli à ajouter
		else if(appToAdd != null && appToAdd != -1){
			for(Applicatifs_Liste applicatif : listApplicatif){
				if(applicatif.getId().equals(appToAdd)){
					Map<String, String> appli = new HashMap<String, String>();
					appli.put(APP_ID, appToAdd.toString());
					listApplications.add(appli);
					break;
				}
			}
		}
		//suppression de la liste des applicatifs de tous les applicatifs à ajouter dans la machine
		//remplissage des noms des applis
		removeApplications(listApplicatif, listApplications);
		
		//récupération des ip à ajouter
		listIPs = new ArrayList<Map<String,String>>();
		getListIPToAdd(request, listIPs);
		
		//récupération des accès local à ajouter
		listAccesLocal = new ArrayList<Map<String,String>>();
		getListLoginAccesLocal(request, listAccesLocal);
		
		return OK;
	}

	/**
	 * Récupération de la liste des login pour les accès locals de la machine
	 * @param request
	 * @param listAccesLocal
	 */
	public static void getListLoginAccesLocal(HttpServletRequest request, List<Map<String, String>> listAccesLocal) {
		int i = 0;
		while(request.getParameter("login" + i) != null){
			Map<String, String> al = new HashMap<String, String>();
			al.put(AL_LOGIN, request.getParameter("login" + i));
			al.put(AL_PASSWORD, request.getParameter("password" + i));
			al.put(AL_DOMAINE, request.getParameter("domaine" + i));
			listAccesLocal.add(al);
			
			++i;
		}

	}

	/**
	 * Récupération de la liste des IP que l'utilisateur veut ajouter
	 * @param request
	 * @param listIPs
	 */
	public static void getListIPToAdd(HttpServletRequest request, List<Map<String, String>> listIPs) {
		int i = 0;
		while(request.getParameter("ip" + i) != null){
			Map<String, String> ip = new HashMap<String, String>();
			ip.put(IP_IP, request.getParameter("ip" + i));
			ip.put(IP_COMMENT, request.getParameter("commentaire" + i));
			listIPs.add(ip);
			
			++i;
		}
	}

	/**
	 * Suppression des applications de arg1 présents dans arg2
	 * @param listApplicatif
	 * @param listApplications
	 */
	public static void removeApplications(List<Applicatifs_Liste> listApplicatif, List<Map<String, String>> listApplications) {
		List<Applicatifs_Liste> appliToRemove = new ArrayList<Applicatifs_Liste>();
		for(Applicatifs_Liste applicatif : listApplicatif){
			for(Map<String, String> appliToAdd : listApplications){
				if(applicatif.getId().toString().equals(appliToAdd.get(APP_ID))){
					appliToAdd.put(APP_NAME, applicatif.getApplicatif());
					appliToRemove.add(applicatif);
				}
			}
		}
		listApplicatif.removeAll(appliToRemove);
	}

	/**
	 * Récupération des applications que l'utilisateur veut ajouter
	 * @param request
	 * @param listApplications
	 */
	public static void getListApplicationsToAdd(HttpServletRequest request, List<Map<String, String>> listApplications) {
		int i = 0;
		while(request.getParameter("application" + i) != null){
			Map<String, String> appli = new HashMap<String, String>();
			appli.put(APP_ID, request.getParameter("application" + i));
			listApplications.add(appli);
			
			++i;
		}
	}


}
