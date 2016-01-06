package pilotage.metier;

import java.io.Serializable;

public class Machine_Os implements Serializable{
	private static final long serialVersionUID = 3426626656300350283L;

	private Integer id;
	private String Nom_OS;

	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="org.hibernate.id.Assigned"
     *  column="id"
     */
	public Integer getId() {
		return id;
	}
	/**
	 * Set the unique identifier of this class
	 * @param id the new ID
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * Return the value associated with the column: NomOS
	 */
	public String getNom_OS() {
		return Nom_OS;
	}
	
	/**
	 * Set the value related to the column: NomOS
	 * @param NomOS the NomOS value
	 */
	public void setNom_OS(String nom_OS) {
		Nom_OS = nom_OS;
	}
}
