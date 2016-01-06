package pilotage.service.menu;

import java.util.ArrayList;
import java.util.List;

import pilotage.admin.metier.Menu;

public class PilotageMenu {
	private Integer level;
	private String parents;
	private Menu menu;
	private String lien;
	private List<PilotageMenu> children;
	
	public PilotageMenu() {
		children = new ArrayList<PilotageMenu>();
	}
	
	public PilotageMenu(Menu menu2) {
		children = new ArrayList<PilotageMenu>();
		setMenu(menu2);
		lien = menu.getLien() == null || menu.getLien().length() == 0 ? "#" : menu.getLien();
	}

	/**
	 * Getter parents
	 * @return
	 */
	public String getParents() {
		return parents;
	}

	/**
	 * Setter parents
	 */
	public void setParents(String parents) {
		if(parents == null)
			this.parents = menu.getLibelle();
		else{
			this.parents = parents + " - " + menu.getLibelle();
		}
		for(PilotageMenu child : children){
			child.setParents(this.parents);
		}
	}

	/**
	 * Getter level
	 * @return
	 */
	public Integer getLevel() {
		return level;
	}

	/**
	 * Setter level
	 * @param level
	 */
	public void setLevel(Integer level) {
		this.level = level;
		for(PilotageMenu child : children){
			child.setLevel(level + 1);
		}
	}

	/**
	 * Getter lien
	 * @return
	 */
	public String getLien() {
		return lien;
	}

	/**
	 * Setter lien
	 * @param lien
	 */
	public void setLien(String lien) {
		this.lien = lien;
	}

	
	/**
	 * Getter menu
	 * @return
	 */
	public Menu getMenu() {
		return menu;
	}
	
	/**
	 * Setter menu
	 * @param menu
	 */
	public void setMenu(Menu menu) {
		this.menu = menu;
		lien = menu.getLien() == null || menu.getLien().length() == 0 ? "#" : menu.getLien();
	}
	
	/**
	 * Getter des sous menus
	 * @return
	 */
	public List<PilotageMenu> getChildren() {
		return children;
	}
	
	/**
	 * Setter des sous menus
	 * @param children
	 */
	public void setChildren(List<PilotageMenu> children) {
		this.children = children;
	}
}
