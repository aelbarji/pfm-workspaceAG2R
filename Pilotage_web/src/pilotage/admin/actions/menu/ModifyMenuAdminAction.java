package pilotage.admin.actions.menu;

import java.io.File;
import java.text.MessageFormat;
import java.util.List;

import pilotage.database.admin.MenuDatabaseService;
import pilotage.database.images.ImagesDatabaseService;
import pilotage.framework.AbstractAdminAction;
import pilotage.service.menu.MenuGenerator;
import pilotage.service.menu.PilotageMenu;


public class ModifyMenuAdminAction extends AbstractAdminAction {
	private static final long serialVersionUID = 1L;
	private Integer selectRow;
	private String parent;
	private String place;
	private String libelle;
	private String lien;
	private Integer icone;
	private Boolean interop;
	private List<PilotageMenu> menuList;
	private File imageUpload;
	private String imageUploadFileName;
	private String imageUploadContentType;

	/**
	 * 
	 * @return
	 */
	public Boolean getInterop() {
		return interop;
	}

	/**
	 * 
	 * @param interop
	 */
	public void setInterop(Boolean interop) {
		this.interop = interop;
	}

	/**
	 * @return
	 */
	public Integer getSelectRow() {
		return selectRow;
	}

	/**
	 * @param selectRow
	 */
	public void setSelectRow(Integer selectRow) {
		this.selectRow = selectRow;
	}

	/**
	 * @return the parent
	 */
	public String getParent() {
		return parent;
	}

	/**
	 * @param parent the parent to set
	 */
	public void setParent(String parent) {
		this.parent = parent;
	}

	/**
	 * @return the place
	 */
	public String getPlace() {
		return place;
	}

	/**
	 * @param place the place to set
	 */
	public void setPlace(String place) {
		this.place = place;
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
	 * @return the lien
	 */
	public String getLien() {
		return lien;
	}

	/**
	 * @param lien the lien to set
	 */
	public void setLien(String lien) {
		this.lien = lien;
	}

	/**
	 * @return the icone
	 */
	public Integer getIcone() {
		return icone;
	}

	/**
	 * @param icone the icone to set
	 */
	public void setIcone(Integer icone) {
		this.icone = icone;
	}
	
	/**
	 * Getter liste des menus
	 * @return
	 */
	public List<PilotageMenu> getMenuList() {
		return menuList;
	}

	/**
	 * Setter liste des menus
	 * @param menuList
	 */
	public void setMenuList(List<PilotageMenu> menuList) {
		this.menuList = menuList;
	}

	public File getImageUpload() {
		return imageUpload;
	}

	public void setImageUpload(File imageUpload) {
		this.imageUpload = imageUpload;
	}

	public String getImageUploadFileName() {
		return imageUploadFileName;
	}

	public void setImageUploadFileName(String imageUploadFileName) {
		this.imageUploadFileName = imageUploadFileName;
	}

	public String getImageUploadContentType() {
		return imageUploadContentType;
	}

	public void setImageUploadContentType(String imageUploadContentType) {
		this.imageUploadContentType = imageUploadContentType;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		try {
			if((icone == null || icone == -1) && imageUpload != null){
				icone = ImagesDatabaseService.save(imageUploadFileName, imageUploadContentType, imageUpload);
			}
			
			MenuDatabaseService.modify(selectRow, parent, place, libelle, lien, icone, interop);
			info = MessageFormat.format(getText("menu.modification.valide"), libelle);
			
			//Récupération des menus avant affichage
			menuList = MenuGenerator.getListMenuForDisplay(MenuDatabaseService.getAllMenus());
			
			return OK;

		} catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Modification de menu - validation ", e);
			menuList = MenuGenerator.getListMenuForParentDisplay(selectRow, MenuDatabaseService.getAllMenus());
			return ERROR;
		}
	}

}
