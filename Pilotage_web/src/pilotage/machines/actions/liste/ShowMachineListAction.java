package pilotage.machines.actions.liste;

import java.util.List;

import net.sf.json.JSONObject;
import pilotage.database.filtre.FiltreDatabaseService;
import pilotage.database.machine.MachineDomaineDatabaseService;
import pilotage.database.machine.MachineEnvironnementDatabaseService;
import pilotage.database.machine.MachineInterlocuteurDatabaseService;
import pilotage.database.machine.MachineOSDatabaseService;
import pilotage.database.machine.MachineSiteDatabaseService;
import pilotage.database.machine.MachineTypeDatabaseService;
import pilotage.database.machine.MachinesListesDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Domaine_Wind;
import pilotage.metier.Environnement;
import pilotage.metier.Filtre;
import pilotage.metier.Interlocuteur;
import pilotage.metier.Machine_Os;
import pilotage.metier.Machines_Liste;
import pilotage.metier.Machines_Site;
import pilotage.metier.Machines_Type;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;
import pilotage.service.string.StringConverter;
import pilotage.utils.Pagination;

public class ShowMachineListAction extends AbstractAction {
	private static final long serialVersionUID = -4037713125016637085L;
	
	private String sort;
	private String sens;
	private int page;
	private int nrPages;
	private int nrPerPage;

	private Pagination<Machines_Liste> pagination;
	private List<Machines_Liste> listMachine;
	
	private List<Machines_Type> listMachineType;
	private List<Machines_Site> listMachineSite;
	private List<Machine_Os> listMachineOS;
	private List<Interlocuteur> listMachineInterlocuteur;
	private List<Domaine_Wind> listMachineDomaine;
	private List<Environnement> listMachineEnvironnement;
	
	private String filtreNom;
	private String filtreIP;
	private Integer filtreType;
	private Integer filtreSite;
	private Integer filtreOs;
	private Integer filtreInterlocuteur;
	private Integer filtreDomaine;
	private Integer filtreEnvironnement;
	private String filtreString;
	private JSONObject filtreJson;
	
	private int validForm = 0;
	private String titrePage = "MAC_LST";
	private Filtre filtre;
	private String filtreNomBase;
	private String filtreIPBase;
	private Integer filtreTypeBase;
	private Integer filtreSiteBase;
	private Integer filtreOsBase;
	private Integer filtreInterlocuteurBase;
	private Integer filtreDomaineBase;
	private Integer filtreEnvironnementBase;

	public JSONObject getFiltreJson() {
		return filtreJson;
	}

	public void setFiltreJson(JSONObject filtreJson) {
		this.filtreJson = filtreJson;
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

	public Pagination<Machines_Liste> getPagination() {
		return pagination;
	}

	public void setPagination(Pagination<Machines_Liste> pagination) {
		this.pagination = pagination;
	}

	public List<Machines_Liste> getListMachine() {
		return listMachine;
	}

	public void setListMachine(List<Machines_Liste> listMachine) {
		this.listMachine = listMachine;
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

	public String getFiltreNom() {
		return filtreNom;
	}

	public void setFiltreNom(String filtreNom) {
		this.filtreNom = filtreNom;
	}

	public String getFiltreIP() {
		return filtreIP;
	}

	public void setFiltreIP(String filtreIP) {
		this.filtreIP = filtreIP;
	}

	public Integer getFiltreType() {
		return filtreType;
	}

	public void setFiltreType(Integer filtreType) {
		this.filtreType = filtreType;
	}

	public Integer getFiltreSite() {
		return filtreSite;
	}

	public void setFiltreSite(Integer filtreSite) {
		this.filtreSite = filtreSite;
	}

	public Integer getFiltreOs() {
		return filtreOs;
	}

	public void setFiltreOs(Integer filtreOs) {
		this.filtreOs = filtreOs;
	}

	public Integer getFiltreInterlocuteur() {
		return filtreInterlocuteur;
	}

	public void setFiltreInterlocuteur(Integer filtreInterlocuteur) {
		this.filtreInterlocuteur = filtreInterlocuteur;
	}

	public Integer getFiltreDomaine() {
		return filtreDomaine;
	}

	public void setFiltreDomaine(Integer filtreDomaine) {
		this.filtreDomaine = filtreDomaine;
	}

	public Integer getFiltreEnvironnement() {
		return filtreEnvironnement;
	}

	public void setFiltreEnvironnement(Integer filtreEnvironnement) {
		this.filtreEnvironnement = filtreEnvironnement;
	}

	public int getValidForm() {
		return validForm;
	}

	public void setValidForm(int validForm) {
		this.validForm = validForm;
	}

	public String getTitrePage() {
		return titrePage;
	}

	public void setTitrePage(String titrePage) {
		this.titrePage = titrePage;
	}

	public Filtre getFiltre() {
		return filtre;
	}

	public void setFiltre(Filtre filtre) {
		this.filtre = filtre;
	}

	public String getFiltreNomBase() {
		return filtreNomBase;
	}

	public void setFiltreNomBase(String filtreNomBase) {
		this.filtreNomBase = filtreNomBase;
	}

	public String getFiltreIPBase() {
		return filtreIPBase;
	}

	public void setFiltreIPBase(String filtreIPBase) {
		this.filtreIPBase = filtreIPBase;
	}

	public Integer getFiltreTypeBase() {
		return filtreTypeBase;
	}

	public void setFiltreTypeBase(Integer filtreTypeBase) {
		this.filtreTypeBase = filtreTypeBase;
	}

	public Integer getFiltreSiteBase() {
		return filtreSiteBase;
	}

	public void setFiltreSiteBase(Integer filtreSiteBase) {
		this.filtreSiteBase = filtreSiteBase;
	}

	public Integer getFiltreOsBase() {
		return filtreOsBase;
	}

	public void setFiltreOsBase(Integer filtreOsBase) {
		this.filtreOsBase = filtreOsBase;
	}

	public Integer getFiltreInterlocuteurBase() {
		return filtreInterlocuteurBase;
	}

	public void setFiltreInterlocuteurBase(Integer filtreInterlocuteurBase) {
		this.filtreInterlocuteurBase = filtreInterlocuteurBase;
	}

	public Integer getFiltreDomaineBase() {
		return filtreDomaineBase;
	}

	public void setFiltreDomaineBase(Integer filtreDomaineBase) {
		this.filtreDomaineBase = filtreDomaineBase;
	}

	public Integer getFiltreEnvironnementBase() {
		return filtreEnvironnementBase;
	}

	public void setFiltreEnvironnementBase(Integer filtreEnvironnementBase) {
		this.filtreEnvironnementBase = filtreEnvironnementBase;
	}

	public String getFiltreString() {
		return filtreString;
	}

	public void setFiltreString(String filtreString) {
		this.filtreString = filtreString;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		
		//recuperation des users suivant les filtres, le tri et la page
		if(page < 1)
			page = 1;
		else if(nrPages != 0 && page > nrPages)
			page = nrPages;
		if(nrPerPage == 0)
			nrPerPage = PilotageConstants.NB_MACHINES_PER_PAGE;
		if(sens == null || "".equals(sens))
			sens = "asc";
		if(sort == null || "".equals(sort))
			sort = "nom";
		pagination = new Pagination<Machines_Liste>(page, nrPerPage);
		//listes utiles aux filtres
		listMachineType = MachineTypeDatabaseService.getAll();
		listMachineSite = MachineSiteDatabaseService.getAll("site", "asc", null, null, null);
		listMachineOS = MachineOSDatabaseService.getAll();
		listMachineInterlocuteur = MachineInterlocuteurDatabaseService.getAll();
		listMachineDomaine = MachineDomaineDatabaseService.getAll();
		listMachineEnvironnement = MachineEnvironnementDatabaseService.getAll();
		
		//gestion du filtre
		Users userLogged = (Users)session.get(PilotageConstants.USER_LOGGED);
		Integer userLoggedId = userLogged.getId();
		filtre = FiltreDatabaseService.getFiltre(userLoggedId,titrePage);
		
		if (filtre != null){
			try {
				Integer filtreId = filtre.getId();
				reloadFiltreBase(filtre.getFiltreString());
				listMachine = MachinesListesDatabaseService.getAll(pagination, sort, sens, filtreNom, filtreType, filtreSite, filtreOs, filtreDomaine, filtreInterlocuteur, filtreEnvironnement, filtreIP);
				//filtre
				filtreJson = ShowMachineListAction.filtreToString(filtreNom, filtreType, filtreSite, filtreOs, filtreDomaine, filtreInterlocuteur, filtreEnvironnement, filtreIP, listMachineType, listMachineSite, listMachineOS, listMachineDomaine, listMachineInterlocuteur, listMachineEnvironnement);
				
				if (validForm == 1){
					if (filtreNomBase != filtreNom || filtreTypeBase != filtreType || filtreSiteBase != filtreSite || filtreOsBase != filtreOs || filtreDomaineBase != filtreDomaine || filtreInterlocuteurBase != filtreInterlocuteur || filtreEnvironnementBase != filtreEnvironnement || filtreIPBase != filtreIP){
						filtreJson = ShowMachineListAction.filtreToString(filtreNom, filtreType, filtreSite, filtreOs, filtreDomaine, filtreInterlocuteur, filtreEnvironnement, filtreIP, listMachineType, listMachineSite, listMachineOS, listMachineDomaine, listMachineInterlocuteur, listMachineEnvironnement);
						FiltreDatabaseService.update(filtreId, filtreJson != null ? filtreJson.toString() : null);
					}
				}
			} catch (Exception e) {
				error = getText("error.message.generique") + " : " + e.getMessage();
				erreurLogger.error("Update de filtre machine liste", e);
			}
		}
		else{
			try {
				listMachine = MachinesListesDatabaseService.getAll(pagination, sort, sens, filtreNom, filtreType, filtreSite, filtreOs, filtreDomaine, filtreInterlocuteur, filtreEnvironnement, filtreIP);
				//filtre
				filtreJson = ShowMachineListAction.filtreToString(filtreNom, filtreType, filtreSite, filtreOs, filtreDomaine, filtreInterlocuteur, filtreEnvironnement, filtreIP, listMachineType, listMachineSite, listMachineOS, listMachineDomaine, listMachineInterlocuteur, listMachineEnvironnement);
				if (validForm == 1){
					if (filtreNom != null || filtreType != null || filtreSite != null || filtreOs != null || filtreDomaine != null || filtreInterlocuteur != null || filtreEnvironnement != null || filtreIP != null){
						FiltreDatabaseService.create(userLoggedId, titrePage, filtreJson != null ? filtreJson.toString() : null);
					}
				}
			} catch (Exception e) {
				error = getText("error.message.generique") + " : " + e.getMessage();
				erreurLogger.error("Creation de filtre machine liste", e);
			}
		}

		if(filtreNom != null)
			filtreNom = filtreNom.replaceAll("\\\\", "\\\\\\\\").replaceAll("'", "\\\\'");
		if (filtreJson != null)
			filtreString = StringConverter.afficheFiltre(filtreJson.toString());
		
		return OK;
	}
	
	public void reloadFiltreBase(String filtreString){
		if (filtreString != null){
			filtreJson = JSONObject.fromObject(filtreString);
			if (filtreJson.containsKey("Nom")){
				setFiltreNomBase(filtreJson.getString("Nom"));
			}
			if (filtreJson.containsKey("IP")){
				setFiltreIPBase(filtreJson.getString("IP"));
			}
			if (filtreJson.containsKey("Type")){
				for(Machines_Type mType : listMachineType){
					if(mType.getType().equalsIgnoreCase(filtreJson.getString("Type"))){
						setFiltreTypeBase(mType.getId());
					}
				}
			}
			if (filtreJson.containsKey("Site")){
				for(Machines_Site mSite : listMachineSite){
					if(mSite.getSite().equalsIgnoreCase(filtreJson.getString("Site"))){
						setFiltreSiteBase(mSite.getId());
					}
				}
			}
			if (filtreJson.containsKey("Os")){
				for(Machine_Os mOs : listMachineOS){
					if(mOs.getNom_OS().equalsIgnoreCase(filtreJson.getString("Os"))){
						setFiltreOsBase(mOs.getId());
					}
				}
			}
			if (filtreJson.containsKey("Domaine")){
				for(Domaine_Wind mDomaine : listMachineDomaine){
					if(mDomaine.getNomDomaine().equalsIgnoreCase(filtreJson.getString("Domaine"))){
						setFiltreDomaineBase(mDomaine.getId());
					}
				}
			}
			if (filtreJson.containsKey("Interlocuteur")){
				for(Interlocuteur inter : listMachineInterlocuteur){
					if(inter.getNomService().equalsIgnoreCase(filtreJson.getString("Interlocuteur"))){
						setFiltreInterlocuteurBase(inter.getId());
					}
				}
			}
			if (filtreJson.containsKey("Environnement")){
				for(Environnement env : listMachineEnvironnement){
					if(env.getEnvironnement().equalsIgnoreCase(filtreJson.getString("Environnement"))){
						setFiltreEnvironnementBase(env.getId());
					}
				}
			}
			if (validForm == 0){
				setFiltreNom(filtreNomBase);
				setFiltreIP(filtreIPBase);
				setFiltreType(filtreTypeBase);
				setFiltreSite(filtreSiteBase);
				setFiltreOs(filtreOsBase);
				setFiltreDomaine(filtreDomaineBase);
				setFiltreInterlocuteur(filtreInterlocuteurBase);
				setFiltreEnvironnement(filtreEnvironnementBase);
			}
		}
	}
	
	/**
	 * Met le filtre sous forme de string pour affichage
	 * @param filtreNom
	 * @param filtreType
	 * @param filtreSite
	 * @param filtreOs
	 * @param filtreDomaine
	 * @param filtreInterlocuteur
	 * @param filtreEnvironnement
	 * @param listMachineType
	 * @param listMachineSite
	 * @param listMachineOS
	 * @param listMachineDomaine
	 * @param listMachineInterlocuteur
	 * @param listMachineEnvironnement
	 * @param filtreIP2 
	 * @return
	 */
	public static /*String*/ JSONObject filtreToString(String filtreNom, Integer filtreType, Integer filtreSite, Integer filtreOs, Integer filtreDomaine, Integer filtreInterlocuteur, Integer filtreEnvironnement, String filtreIP, List<Machines_Type> listMachineType, List<Machines_Site> listMachineSite, List<Machine_Os> listMachineOS, List<Domaine_Wind> listMachineDomaine, List<Interlocuteur> listMachineInterlocuteur, List<Environnement>listMachineEnvironnement) {
		StringBuffer buffer = new StringBuffer();
		if (filtreNom != null && !filtreNom.equals("")) {
			buffer.append(StringConverter.toJson("Nom", filtreNom));
		}
		if (filtreIP != null && !filtreIP.equals("")) {
			buffer.append(StringConverter.toJson("IP", filtreIP));
		}
		if (filtreType != null && filtreType != -1) {
			for(Machines_Type type : listMachineType){
				if(type.getId().equals(filtreType)){
					buffer.append(StringConverter.toJson("Type", type.getType()));
					break;
				}
			}
		}
		if (filtreSite != null && filtreSite != -1) {
			for(Machines_Site site : listMachineSite){
				if(site.getId().equals(filtreSite)){
					buffer.append(StringConverter.toJson("Site", site.getSite()));
					break;
				}
			}
		}
		if (filtreOs != null && filtreOs != -1) {
			for(Machine_Os machineos : listMachineOS){
				if(machineos.getId().equals(filtreOs)){
					buffer.append(StringConverter.toJson("Os", machineos.getNom_OS()));
					break;
				}
			}
		}
		if (filtreDomaine != null && filtreDomaine != -1) {
			for(Domaine_Wind domaine : listMachineDomaine){
				if(domaine.getId().equals(filtreDomaine)){
					buffer.append(StringConverter.toJson("Domaine", domaine.getNomDomaine()));
					break;
				}
			}
		}
		if (filtreInterlocuteur != null && filtreInterlocuteur != -1) {
			for(Interlocuteur interl : listMachineInterlocuteur){
				if(interl.getId().equals(filtreInterlocuteur)){
					buffer.append(StringConverter.toJson("Interlocuteur", interl.getNomService()));
					break;
				}
			}
		}
		if (filtreEnvironnement != null && filtreEnvironnement != -1) {
			for(Environnement envir : listMachineEnvironnement){
				if(envir.getId().equals(filtreEnvironnement)){
					buffer.append(StringConverter.toJson("Environnement", envir.getEnvironnement()));
					break;
				}
			}
		}
		if(buffer.length() != 0) {
			String result = "{" + buffer.substring(0, buffer.lastIndexOf(PilotageConstants.SEPARATEUR_3)) + "}";
			return JSONObject.fromObject(result);
		}
		else {
			return null;
		}
	}
}
