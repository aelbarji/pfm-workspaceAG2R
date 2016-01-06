package pilotage.machines.actions.liste;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import pilotage.database.applicatif.ApplicatifDatabaseService;
import pilotage.database.historique.HistoriqueDatabaseService;
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
import pilotage.metier.Environnement;
import pilotage.metier.Interlocuteur;
import pilotage.metier.Machine_Login;
import pilotage.metier.Machine_Os;
import pilotage.metier.Machines_Applicatifs;
import pilotage.metier.Machines_Ip;
import pilotage.metier.Machines_Liste;
import pilotage.metier.Machines_Site;
import pilotage.metier.Machines_Type;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;
import pilotage.utils.Pagination;

public class ModifyMachineListAction extends AbstractAction{

	private static final long serialVersionUID = 7013818341148152623L;

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

	private Pagination<Machines_Liste> pagination;

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

	private List<Map<String, String>> listApplications;
	private List<Map<String, String>> listIPs;
	private List<Map<String, String>> listAccesLocal;
	
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
			if(!nomOrigine.trim().equals(nom.trim()) && MachinesListesDatabaseService.exists(selectRow, nom)){
				error = MessageFormat.format(getText("machine.creation.existe.deja"), nom );
				prepareRedirectToModificationPage();
				
				return false;
			}
			return true;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Modification d'une machine - ", e);
			prepareRedirectToModificationPage();
			return false;
		}
	}

	@Override
	protected String executeMetier() {
		try{
			HttpServletRequest request = ServletActionContext.getRequest();
			
			//recupération des appli
			listApplications = new ArrayList<Map<String,String>>();
			RedirectCreateMachineListAction.getListApplicationsToAdd(request, listApplications);

			//récupération des ip
			listIPs = new ArrayList<Map<String,String>>();
			RedirectCreateMachineListAction.getListIPToAdd(request, listIPs);
			
			//récupération des accès local
			listAccesLocal = new ArrayList<Map<String,String>>();
			RedirectCreateMachineListAction.getListLoginAccesLocal(request, listAccesLocal);
			
			//récupération des infos en base
			List<Machines_Applicatifs> listApplisEnBase = MachineApplicatifDatabaseService.getApplicatifsFromMachine(selectRow);
			List<Machines_Ip> listIPsEnBase = MachineIpDatabaseService.getIPFromMachine(selectRow);
			List<Machine_Login> listLoginsEnBase = MachineLoginDatabaseService.getLoginsFromMachine(selectRow);
			
			//Isolation des modifications des applis
			List<Integer> appliToDelete = new ArrayList<Integer>();
			List<Integer> appliToAdd = new ArrayList<Integer>();
			for(Map<String,String> appli : listApplications){
				boolean alreadyInBase = false;
				Integer id = Integer.parseInt(appli.get(RedirectCreateMachineListAction.APP_ID));
				for(Machines_Applicatifs machAppli : listApplisEnBase){
					if(machAppli.getApplicatif().getId().equals(id)){
						alreadyInBase = true;
						break;
					}
				}
				if(!alreadyInBase){
					appliToAdd.add(id);
				}
			}
			for(Machines_Applicatifs machAppli : listApplisEnBase){
				boolean stillInList = false;
				String idString = machAppli.getApplicatif().getId().toString();
				for(Map<String,String> appli : listApplications){
					if(appli.get(RedirectCreateMachineListAction.APP_ID).equals(idString)){
						stillInList = true;
						break;
					}
				}
				if(!stillInList){
					appliToDelete.add(machAppli.getApplicatif().getId());
				}
			}
			
			//Isolation des modifications des IPs
			List<Integer> ipToDelete = new ArrayList<Integer>();
			List<Map<String, String>> ipToUpdate = new ArrayList<Map<String,String>>();
			List<Map<String, String>> ipToAdd = new ArrayList<Map<String,String>>();
			for(Map<String,String> ip : listIPs){
				boolean alreadyInBase = false;
				boolean inBaseButUpdated = false;
				String ipAddress = ip.get(RedirectCreateMachineListAction.IP_IP);
				String ipComment = ip.get(RedirectCreateMachineListAction.IP_COMMENT);
				for(Machines_Ip machIP : listIPsEnBase){
					if(machIP.getIp().equals(ipAddress) && machIP.getCommentaire().equals(ipComment)){
						alreadyInBase = true;
						break;
					}
					else if(machIP.getIp().equals(ipAddress)){
						inBaseButUpdated = true;
						ip.put("id", machIP.getId().toString());
						break;
					}
				}
				if(inBaseButUpdated){
					ipToUpdate.add(ip);
				}
				else if(!alreadyInBase){
					ipToAdd.add(ip);
				}
			}
			for(Machines_Ip machIP : listIPsEnBase){
				boolean stillInList = false;
				String ipAddress = machIP.getIp();
				for(Map<String,String> ip : listIPs){
					if(ip.get(RedirectCreateMachineListAction.IP_IP).equals(ipAddress)){
						stillInList = true;
						break;
					}
				}
				if(!stillInList){
					ipToDelete.add(machIP.getId());
				}
			}
			
			//Isolation des modifications des Logins
			List<Integer> loginToDelete = new ArrayList<Integer>();
			List<Map<String, String>> loginToUpdate = new ArrayList<Map<String,String>>();
			List<Map<String, String>> loginToAdd = new ArrayList<Map<String,String>>();
			for(Map<String,String> acces : listAccesLocal){
				boolean alreadyInBase = false;
				boolean inBaseButUpdated = false;
				String login = acces.get(RedirectCreateMachineListAction.AL_LOGIN);
				String password = acces.get(RedirectCreateMachineListAction.AL_PASSWORD);
				Integer domaine = Integer.parseInt(acces.get(RedirectCreateMachineListAction.AL_DOMAINE));
				for(Machine_Login machLogin : listLoginsEnBase){
					if(machLogin.getLogin().equals(login) && machLogin.getPassword().equals(password) && machLogin.getDomaine_wind().getId().equals(domaine)){
						alreadyInBase = true;
						break;
					}
					else if(machLogin.getLogin().equals(login)){
						inBaseButUpdated = true;
						acces.put("id", machLogin.getId().toString());
						break;
					}
				}
				if(inBaseButUpdated){
					loginToUpdate.add(acces);
				}
				else if(!alreadyInBase){
					loginToAdd.add(acces);
				}
			}
			for(Machine_Login machLogin : listLoginsEnBase){
				boolean stillInList = false;
				String login = machLogin.getLogin();
				for(Map<String,String> acces : listAccesLocal){
					if(acces.get(RedirectCreateMachineListAction.AL_LOGIN).equals(login)){
						stillInList = true;
						break;
					}
				}
				if(!stillInList){
					loginToDelete.add(machLogin.getId());
				}
			}
			
			String historique = " ";
			Machines_Liste ml = MachinesListesDatabaseService.get(selectRow);
			if (!nom.equals(ml.getNom())) {
				historique += "nom, ";
			}
			if (!type.equals(ml.getId_type().getId())) {
				historique += "type, ";
			}
			if (!site.equals(ml.getId_site().getId())) {
				historique += "site, ";
			}
			if (!domaine.equals(ml.getId_domaine().getId())) {
				historique += "domaine, ";
			}
			if (ml.getId_interlocuteur() != null) {
				if (!interlocuteur.equals(ml.getId_interlocuteur().getId())) {
					historique += "d'interlocuteur, ";
				}
			}
			if (ml.getId_environnement() != null) {
				if (!environnement.equals(ml.getId_environnement().getId())) {
					historique += "d'environnement, ";
				}
			}
			if (!os.equals(ml.getId_os().getId())) {
				historique += "d'os, ";
			}
			
			if (!commentaire.equals(ml.getCommentaire())) {
				historique += "commentaire, ";
			}
			
			for (Integer i : appliToDelete) {
				historique += "suppression de l'application " + i + ", ";
			}
			for (Integer i : appliToAdd) {
				historique += "ajout de l'application " + i + ", ";
			}
			for (Integer i : ipToDelete) {
				historique += "suppression de l'ip " + i + ", ";
			}
			for (Map<String, String> i : ipToUpdate) {
				historique += "modification de l'ip " + i.get("id") + ", ";
			}
			for (Integer i : loginToDelete) {
				historique += "suppression du login " + i + ", ";
			}
			for (Map<String, String> i : loginToUpdate) {
				historique += "modification du login " + i.get("id") + ", ";
			}
			
			MachinesListesDatabaseService.modify(selectRow, nom, type, site, domaine, interlocuteur, environnement, os, commentaire,
					appliToAdd, appliToDelete,
					ipToAdd, ipToUpdate, ipToDelete,
					loginToAdd, loginToUpdate, loginToDelete);
			
			for (Map<String, String> i : ipToAdd) {
				historique += "ajout de l'ip " + MachineIpDatabaseService.getId(i.get("IP")) + ", ";
			}
			for (Map<String, String> i : loginToAdd) {
				historique += "ajout du login " + MachineLoginDatabaseService.getId(i.get("login")) + ", ";
			}
			HistoriqueDatabaseService.create(null, MessageFormat.format(getText("historique.machine.modification") ,new Object[]{historique, selectRow}), (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_MACHINES);
			
			info = MessageFormat.format(getText("machine.modification.valide"), new Object[]{nom});
			
			
			if(detail){
				//prepareRedirectToDetailPage();
				return "detail";
			}
			else{
				return OK;
			}
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Modification d'une machine - ", e);
			prepareRedirectToModificationPage();
			return ERROR;
		}
	}
	
	/**
	 * Action avant redirection vers la page de détails
	 */
	/*private void prepareRedirectToDetailPage() {
		//init de la liste des domaines
		listMachineDomaine = MachineDomaineDatabaseService.getAll();
		
		//init des noms des applicatifs
		listApplicatif = ApplicatifDatabaseService.getAll();
		RedirectCreateMachineListAction.removeApplications(listApplicatif, listApplications);
		
		//init des infos de la machine
		Machines_Liste machine = MachinesListesDatabaseService.get(selectRow);
		
		//init des libellés des infos machine pour la page de détail
		nomType = machine.getId_type().getType();
		nomSite = machine.getId_site().getSite();
		nomDomaine = machine.getId_domaine().getNomDomaine();
		nomInterlocuteur = machine.getId_interlocuteur() == null ? null : machine.getId_interlocuteur().getNomService();
		nomEnvironnement = machine.getId_environnement() == null ? null : machine.getId_environnement().getEnvironnement();
		nomOS = machine.getId_os().getNom_OS();
	}*/

	/**
	 * Action avant redirection vers la page de création
	 */
	private void prepareRedirectToModificationPage(){
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
