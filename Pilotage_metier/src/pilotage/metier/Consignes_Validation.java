package pilotage.metier;

import java.io.Serializable;

public class Consignes_Validation implements Serializable {

	private static final long serialVersionUID = 4812962039488368398L;

	// primary key
	private Integer id;

	// fields
	private String libelleStatut;

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
	 * Return the value associated with the column: libelle_statut
	 */
	public String getLibelleStatut() {
		return libelleStatut;
	}

	/**
	 * Set the value related to the column: libelle_statut
	 * @param libelleStatut the libelle_statut value
	 */
	public void setLibelleStatut(String libelleStatut) {
		this.libelleStatut = libelleStatut;
	}
}