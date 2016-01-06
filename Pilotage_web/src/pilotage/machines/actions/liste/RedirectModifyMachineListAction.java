package pilotage.machines.actions.liste;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import pilotage.database.admin.ParametreDatabaseService;
import pilotage.database.applicatif.ApplicatifDatabaseService;
import pilotage.database.machine.DomaineLoginDatabaseService;
import pilotage.database.machine.MachineApplicatifDatabaseService;
import pilotage.database.machine.MachineDomaineDatabaseService;
import pilotage.database.machine.MachineEnvironnementDatabaseService;
import pilotage.database.machine.MachineInterlocuteurDatabaseService;
import pilotage.database.machine.MachineIpDatabaseService;
import pilotage.database.machine.MachineLoginDatabaseService;
import pilotage.database.machine.MachineOSDatabaseService;
import pilotage.database.machine.MachineSiteDatabaseService;
import pilotage.database.machine.MachineTypeDatabaseService;
import pilotage.database.machine.MachinesListesDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Applicatifs_Liste;
import pilotage.metier.Domaine_Wind;
import pilotage.metier.Domaine_Wind_Login;
import pilotage.metier.Environnement;
import pilotage.metier.Interlocuteur;
import pilotage.metier.Machine_Login;
import pilotage.metier.Machine_Os;
import pilotage.metier.Machines_Applicatifs;
import pilotage.metier.Machines_Ip;
import pilotage.metier.Machines_Liste;
import pilotage.metier.Machines_Site;
import pilotage.metier.Machines_Type;
import pilotage.service.constants.PilotageConstants;
import pilotage.service.convertor.EncryptDecrypt;

public class RedirectModifyMachineListAction extends AbstractAction {

	private static final long serialVersionUID = 2800115204815595744L;
	
	private Integer selectRow;
	private String nomOrigine;
	
	private boolean detail;
	
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
	private String nomType;
	private String nomSite;
	private String nomDomaine;
	private String nomInterlocuteur;
	private String nomEnvironnement;
	private String nomOS;
	
	private Integer appToAdd;
	private Integer deleteAppli;
	private List<Map<String, String>> listApplications;
	private List<Map<String, String>> listIPs;
	private List<Map<String, String>> listAccesLocal;
	
	private List<Domaine_Wind_Login> listLoginDomaine;
	
	public Integer getSelectRow() {
		return selectRow;
	}

	public void setSelectRow(Integer selectRow) {
		this.selectRow = selectRow;
	}

	public String getNomOrigine() {
		return nomOrigine;
	}

	public void setNomOrigine(String nomOrigine) {
		this.nomOrigine = nomOrigine;
	}

	public boolean isDetail() {
		return detail;
	}

	public void setDetail(boolean detail) {
		this.detail = detail;
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

	public String getNomType() {
		return nomType;
	}

	public void setNomType(String nomType) {
		this.nomType = nomType;
	}

	public String getNomSite() {
		return nomSite;
	}

	public void setNomSite(String nomSite) {
		this.nomSite = nomSite;
	}

	public String getNomDomaine() {
		return nomDomaine;
	}

	public void setNomDomaine(String nomDomaine) {
		this.nomDomaine = nomDomaine;
	}

	public String getNomInterlocuteur() {
		return nomInterlocuteur;
	}

	public void setNomInterlocuteur(String nomInterlocuteur) {
		this.nomInterlocuteur = nomInterlocuteur;
	}

	public String getNomEnvironnement() {
		return nomEnvironnement;
	}

	public void setNomEnvironnement(String nomEnvironnement) {
		this.nomEnvironnement = nomEnvironnement;
	}

	public String getNomOS() {
		return nomOS;
	}

	public void setNomOS(String nomOS) {
		this.nomOS = nomOS;
	}

	public List<Domaine_Wind_Login> getListLoginDomaine() {
		return listLoginDomaine;
	}

	public void setListLoginDomaine(List<Domaine_Wind_Login> listLoginDomaine) {
		this.listLoginDomaine = listLoginDomaine;
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
		
		Machines_Liste machine = MachinesListesDatabaseService.get(selectRow);
		listLoginDomaine = DomaineLoginDatabaseService.getLoginsFromDomaine(machine.getId_domaine().getId());
		
		//Si on a cliqué sur suppr/ajour d'une appli
		if((deleteAppli != null && deleteAppli != -1) || (appToAdd != null && appToAdd != -1)){
			//recupération des appli à ajouter
			listApplications = new ArrayList<Map<String,String>>();
			RedirectCreateMachineListAction.getListApplicationsToAdd(request, listApplications);
			
			//Si on a cliqué sur la suppression d'une appli
			if(deleteAppli != null && deleteAppli != -1){
				Map<String, String> mapToRemove = null;
				for(Map<String, String> appli : listApplications){
					if(appli.get(RedirectCreateMachineListAction.APP_ID).equals(deleteAppli.toString())){
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
						appli.put(RedirectCreateMachineListAction.APP_ID, appToAdd.toString());
						listApplications.add(appli);
						break;
					}
				}
			}
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
		//sinon on entre pour la première fois pour aller sur la page de modification
		else{
			//init des infos de la machine
			nom = machine.getNom();
			nomOrigine = machine.getNom();
			type = machine.getId_type().getId();
			site = machine.getId_site().getId();
			domaine = machine.getId_domaine().getId();
			interlocuteur = machine.getId_interlocuteur() == null ? -1 : machine.getId_interlocuteur().getId();
			environnement = machine.getId_environnement() == null ? -1 : machine.getId_environnement().getId();
			os = machine.getId_os().getId();
			commentaire = machine.getCommentaire();
			
			//init des libellés des infos machine pour la page de détail
			nomType = machine.getId_type().getType();
			nomSite = machine.getId_site().getSite();
			nomDomaine = machine.getId_domaine().getNomDomaine();
			nomInterlocuteur = machine.getId_interlocuteur() == null ? null : machine.getId_interlocuteur().getNomService();
			nomEnvironnement = machine.getId_environnement() == null ? null : machine.getId_environnement().getEnvironnement();
			nomOS = machine.getId_os().getNom_OS();
			
			//init des applicatifs
			List<Machines_Applicatifs> listMachinesAppli = MachineApplicatifDatabaseService.getApplicatifsFromMachine(machine.getId());
			listApplications = new ArrayList<Map<String,String>>();
			for(Machines_Applicatifs machAppli : listMachinesAppli){
				Map<String, String> appli = new HashMap<String, String>();
				appli.put(RedirectCreateMachineListAction.APP_ID, machAppli.getApplicatif().getId().toString());
				listApplications.add(appli);
			}
			//suppression de la liste des applicatifs de tous les applicatifs à ajouter dans la machine
			//remplissage des noms des applis
			RedirectCreateMachineListAction.removeApplications(listApplicatif, listApplications);
			
			//init des ip
			List<Machines_Ip> listMachinesIp = MachineIpDatabaseService.getIPFromMachine(machine.getId());
			listIPs = new ArrayList<Map<String,String>>();
			for(Machines_Ip machIP : listMachinesIp){
				Map<String, String> ip = new HashMap<String, String>();
				ip.put(RedirectCreateMachineListAction.IP_IP, machIP.getIp());
				ip.put(RedirectCreateMachineListAction.IP_COMMENT, machIP.getCommentaire());
				listIPs.add(ip);
			}
			
			//init des logins
			List<Machine_Login> listMachineLogin = MachineLoginDatabaseService.getLoginsFromMachine(machine.getId());
			listAccesLocal = new ArrayList<Map<String,String>>();
			for(Machine_Login machLogin : listMachineLogin){
				Map<String, String> login = new HashMap<String, String>();
				login.put(RedirectCreateMachineListAction.AL_LOGIN, machLogin.getLogin());
				String key = ParametreDatabaseService.get(PilotageConstants.CLE_ENCODAGE).getValeur();
				String decrypt = EncryptDecrypt.decryptBlowfish(machLogin.getPassword(), key);
				
				if (decrypt != null)
					login.put(RedirectCreateMachineListAction.AL_PASSWORD, decrypt);
				else  // si le mot de passe n'était pas codé dans la bdd
					login.put(RedirectCreateMachineListAction.AL_PASSWORD, machLogin.getPassword());
				login.put(RedirectCreateMachineListAction.AL_DOMAINE, machLogin.getDomaine_wind().getId().toString());
				listAccesLocal.add(login);
			}
		}
		return OK;
	}

}
