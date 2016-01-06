package pilotage.admin.actions.profil;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import pilotage.admin.metier.Droits_Liste;
import pilotage.admin.metier.Menu;
import pilotage.admin.metier.Profil;
import pilotage.database.admin.DroitsListeDatabaseService;
import pilotage.database.admin.ProfilDatabaseService;
import pilotage.database.admin.ProfilDroitsDatabaseService;
import pilotage.framework.AbstractAdminAction;

public class ModifyProfilAdminAction extends AbstractAdminAction {

	private static final long serialVersionUID = 7013818341148152623L;
	private Integer selectRow;
	private String libelle;
	private String accueil;
	private Boolean clignConsigne;
	private Boolean admin;
	private Boolean pilote;
	private String[] droitSelected;
	private List<Menu> listMenu;
	private List<Profil> listProfil;
	
	/**
	 * @return the selectRow
	 */
	public Integer getSelectRow() {
		return selectRow;
	}

	/**
	 * @param selectRow the selectRow to set
	 */
	public void setSelectRow(Integer selectRow) {
		this.selectRow = selectRow;
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
	 * Getter accueil
	 * @return
	 */
	public String getAccueil() {
		return accueil;
	}

	/**
	 * setter accueil
	 * @param accueil
	 */
	public void setAccueil(String accueil) {
		this.accueil = accueil;
	}

	/**
	 * Getter consigne
	 * @return
	 */
	public Boolean getClignConsigne() {
		return clignConsigne;
	}

	/**
	 * Setter consigne
	 * @param clignConsigne
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

	/**
	 * Getter liste des menus
	 * @return
	 */
	public List<Menu> getListMenu() {
		return listMenu;
	}

	/**
	 * Setter liste des menus
	 * @param listMenu
	 */
	public void setListMenu(List<Menu> listMenu) {
		this.listMenu = listMenu;
	}

	/**
	 * Getter liste des profils
	 * @return
	 */
	public List<Profil> getListProfil() {
		return listProfil;
	}

	/**
	 * Setter liste des profils
	 * @param listProfil
	 */
	public void setListProfil(List<Profil> listProfil) {
		this.listProfil = listProfil;
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
			ProfilDatabaseService.modify(selectRow, libelle, clignConsigne, "".equals(accueil) ? null : Integer.parseInt(accueil), admin, pilote);
			List<String> ProfilDroitToDelete = new ArrayList<String>();
			List<String> ProfilDroitToAdd = new ArrayList<String>();
			List<Droits_Liste> listDroitsEnBase = DroitsListeDatabaseService.getDroitsByProfil(ProfilDatabaseService.get(selectRow));
			if (droitSelected != null && !(droitSelected.length==1 && droitSelected[0].equals("false"))) {
				for(String droit : droitSelected){
					boolean alreadyInBase = false;
					for(Droits_Liste dl : listDroitsEnBase){
						if(dl.getLibelle().equals(droit)){
							alreadyInBase = true;
							break;
						}
					}
					if(!alreadyInBase){
						ProfilDroitToAdd.add(droit);
					}
				}
			}
			for(Droits_Liste dl : listDroitsEnBase){
				boolean stillInList = false;
				for(String droit : droitSelected){
					if(dl.getLibelle().equals(droit)){
						stillInList = true;
						break;
					}
				}
				if(!stillInList){
					ProfilDroitToDelete.add(dl.getLibelle());
				}
			}
			ProfilDroitsDatabaseService.modify(selectRow, ProfilDroitToAdd, ProfilDroitToDelete);
			info = MessageFormat.format(getText("profil.modification.valide"), libelle);
			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Modification d'un profil - validation ", e);
			return ERROR;
		}
		finally{
			listProfil = ProfilDatabaseService.getAll();
		}
	}

}
