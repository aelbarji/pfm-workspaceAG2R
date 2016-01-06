package pilotage.admin.actions.profil;

import java.util.ArrayList;
import java.util.List;

import pilotage.admin.metier.Droits_Liste;
import pilotage.admin.metier.Menu;
import pilotage.admin.metier.Module;
import pilotage.admin.metier.Profil;
import pilotage.admin.metier.Sous_Module;
import pilotage.database.admin.DroitsListeDatabaseService;
import pilotage.database.admin.MenuDatabaseService;
import pilotage.database.admin.ModuleDatabaseService;
import pilotage.database.admin.ProfilDatabaseService;
import pilotage.database.admin.SousModuleDatabaseService;
import pilotage.framework.AbstractAdminAction;

public class RedirectModifyProfilAdminAction extends AbstractAdminAction {
	
	private static final long serialVersionUID = -2136820498896120263L;
	private int selectRow;
	private Profil selectProfil;
	private String libelle;
	private String accueil;
	private Boolean clignConsigne;
	private Boolean admin;
	private Boolean pilote;
	private List<Menu> listMenu;
	private List<Profil> listProfil;
	private List<Droits_Liste> listDroits;
	private List<String> listDroitSelected;
	private List<Module> listModule;
	private List<List<Sous_Module>> listSousModule;
	private List<Sous_Module> listSousMod;
	
	/**
	 * @return the listProfil
	 */
	public List<Profil> getListProfil() {
		return listProfil;
	}

	/**
	 * @param listProfil the listProfil to set
	 */
	public void setListProfil(List<Profil> listProfil) {
		this.listProfil = listProfil;
	}

	public int getSelectRow() {
		return selectRow;
	}

	public void setSelectRow(int selectRow) {
		this.selectRow = selectRow;
	}

	public Profil getSelectProfil() {
		return selectProfil;
	}

	public void setSelectProfil(Profil selectProfil) {
		this.selectProfil = selectProfil;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getAccueil() {
		return accueil;
	}

	public void setAccueil(String accueil) {
		this.accueil = accueil;
	}

	public Boolean getClignConsigne() {
		return clignConsigne;
	}

	public void setClignConsigne(Boolean clignConsigne) {
		this.clignConsigne = clignConsigne;
	}

	public Boolean getAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}

	public Boolean getPilote() {
		return pilote;
	}

	public void setPilote(Boolean pilote) {
		this.pilote = pilote;
	}

	public List<Menu> getListMenu() {
		return listMenu;
	}

	public void setListMenu(List<Menu> listMenu) {
		this.listMenu = listMenu;
	}

	public List<Droits_Liste> getListDroits() {
		return listDroits;
	}

	public void setListDroits(List<Droits_Liste> listDroits) {
		this.listDroits = listDroits;
	}

	public List<String> getListDroitSelected() {
		return listDroitSelected;
	}

	public void setListDroitSelected(List<String> listDroitSelected) {
		this.listDroitSelected = listDroitSelected;
	}

	public List<Module> getListModule() {
		return listModule;
	}

	public void setListModule(List<Module> listModule) {
		this.listModule = listModule;
	}

	public List<List<Sous_Module>> getListSousModule() {
		return listSousModule;
	}

	public void setListSousModule(List<List<Sous_Module>> listSousModule) {
		this.listSousModule = listSousModule;
	}

	public List<Sous_Module> getListSousMod() {
		return listSousMod;
	}

	public void setListSousMod(List<Sous_Module> listSousMod) {
		this.listSousMod = listSousMod;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		try {
			selectProfil = ProfilDatabaseService.get(selectRow);
			libelle = selectProfil.getLibelle();
			accueil = selectProfil.getAccueil() != null ? selectProfil.getAccueil().getId().toString() : null;
			clignConsigne = "1".equals(selectProfil.getClign_consigne());
			admin = selectProfil.getAdmin();
			pilote = selectProfil.getPilote();
			List<Droits_Liste> listDL = DroitsListeDatabaseService.getDroitsByProfil(ProfilDatabaseService.get(selectRow));
			listDroitSelected = new ArrayList<String>();
			for (Droits_Liste dl : listDL) {
				listDroitSelected.add(dl.getLibelle());
			}
			listDroits = DroitsListeDatabaseService.getAll();
			listMenu = MenuDatabaseService.getAllFinalMenus();
			listModule = ModuleDatabaseService.getAll();
			listSousMod = new ArrayList<Sous_Module>();
			listSousModule = new ArrayList<List<Sous_Module>>();
			for(Module m : listModule){
				listSousMod = SousModuleDatabaseService.getAllFromModule(m.getId());
				listSousModule.add(listSousMod);
			}
			return OK;
		} catch (Exception e) {
			listProfil = ProfilDatabaseService.getAll();
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Redirection vers la modification de profil ", e);
			return ERROR;
		}

	}

}
