package pilotage.admin.actions.profil;

import java.text.MessageFormat;
import java.util.List;

import pilotage.admin.metier.Menu;
import pilotage.admin.metier.Profil;
import pilotage.database.admin.MenuDatabaseService;
import pilotage.database.admin.ProfilDatabaseService;
import pilotage.database.admin.ProfilDroitsDatabaseService;
import pilotage.framework.AbstractAdminAction;

public class CreateProfilAdminAction extends AbstractAdminAction {

	private static final long serialVersionUID = -1299589526541605339L;
	private String libelle;
	private String accueil;
	private Boolean clignConsigne;
	private Boolean admin;
	private Boolean pilote;
	private String[] droitSelected;
	private List<Menu> listMenu;
	private List<Profil> listProfil;
	
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

	/**
	 * @return the listMenu
	 */
	public List<Menu> getListMenu() {
		return listMenu;
	}

	/**
	 * @param listMenu the listMenu to set
	 */
	public void setListMenu(List<Menu> listMenu) {
		this.listMenu = listMenu;
	}
	
	/**
	 * @return the libelle
	 */
	public String getLibelle() {
		return libelle;
	}

	/**
	 * @param libelle the libelle to set
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	/**
	 * @return the accueil
	 */
	public String getAccueil() {
		return accueil;
	}

	/**
	 * @param accueil the accueil to set
	 */
	public void setAccueil(String accueil) {
		this.accueil = accueil;
	}

	/**
	 * @return the check
	 */
	public Boolean getClignConsigne() {
		return clignConsigne;
	}

	/**
	 * @param check the check to set
	 */
	public void setClignConsigne(Boolean clignConsigne) {
		this.clignConsigne = clignConsigne;
	}
	
	/**
	 * Getter admin
	 * @return
	 */
	public Boolean getAdmin() {
		return admin;
	}

	/**
	 * Setter admin
	 * @param admin
	 */
	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}

	/**
	 * Getter pilote
	 * @return
	 */
	public Boolean getPilote() {
		return pilote;
	}

	/**
	 * Setter pilote
	 * @param pilote
	 */
	public void setPilote(Boolean pilote) {
		this.pilote = pilote;
	}

	public String[] getDroitSelected() {
		return droitSelected;
	}

	public void setDroitSelected(String[] droitSelected) {
		this.droitSelected = droitSelected;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		try{
			ProfilDatabaseService.create(libelle, clignConsigne, "".equals(accueil) ? null : Integer.parseInt(accueil), admin, pilote);
			if (droitSelected != null){
				for(int i=0; i < droitSelected.length; ++i){
					System.out.println(droitSelected[i] + " ");
				}
				if (!(droitSelected.length==1 && droitSelected[0].equals("false"))) {
					ProfilDroitsDatabaseService.create(ProfilDatabaseService.getByLibelle(libelle), droitSelected);
				}
			}
			info = MessageFormat.format(getText("profil.creation.valide"), libelle);
			return OK;
		}
		catch (Exception e) {
			listMenu = MenuDatabaseService.getAllFinalMenus();
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Création d'un profil - validation ", e);
			return ERROR;
		}
		finally{
			listProfil = ProfilDatabaseService.getAll();
		}
	}

}
