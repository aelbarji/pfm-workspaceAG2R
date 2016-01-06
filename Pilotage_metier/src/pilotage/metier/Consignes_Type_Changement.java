package pilotage.metier;

import java.io.Serializable;

public class Consignes_Type_Changement implements Serializable {

	private static final long serialVersionUID = 2794149231428843793L;

	// primary key
	private Integer id;

	// fields
	private String libelleChangement;

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
	 * Return the value associated with the column: libelle_changement
	 */
	public String getLibelleChangement() {
		return libelleChangement;
	}

	/**
	 * Set the value related to the column: libelle_changement
	 * @param libelleChangement the libelle_changement value
	 */
	public void setLibelleChangement(String libelleChangement) {
		this.libelleChangement = libelleChangement;
	}

}