/**
 * pilotage.admin.metier
 * 10 juin 2011
 */
package pilotage.admin.metier;

import java.io.Serializable;


/**
 * @author xxu
 *
 */

public class Affectation_menu implements Serializable{

	private static final long serialVersionUID = 1L;
	private Menu id_menu;
	private Profil id_profil;
	/**
	 * @return the id_menu
	 */
	public Menu getId_menu() {
		return id_menu;
	}
	/**
	 * @param id_menu the id_menu to set
	 */
	public void setId_menu(Menu id_menu) {
		this.id_menu = id_menu;
	}
	/**
	 * @return the id_profil
	 */
	public Profil getId_profil() {
		return id_profil;
	}
	/**
	 * @param id_profil the id_profil to set
	 */
	public void setId_profil(Profil id_profil) {
		this.id_profil = id_profil;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id_menu == null) ? 0 : id_menu.hashCode());
		result = prime * result + ((id_profil == null) ? 0 : id_profil.hashCode());
		return result;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		Affectation_menu other = (Affectation_menu) obj;
		
		if (id_menu == null) {
			if (other.id_menu != null)
				return false;
		} 
		else if (!id_menu.equals(other.id_menu)){
			return false;
		}
		
		if (id_profil == null) {
			if (other.id_profil != null)
				return false;
		} 
		else if (!id_profil.equals(other.id_profil)){
			return false;
		}
		
		return true;
	}

	
}
