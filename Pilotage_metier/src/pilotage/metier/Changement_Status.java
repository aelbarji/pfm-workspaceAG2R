package pilotage.metier;

import java.io.Serializable;

public class Changement_Status implements Serializable {

	private static final long serialVersionUID = 6577267731699542917L;

	// primary key
	private Integer id;

	// fields
	private String statut;

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
	 * Return the value associated with the column: statut
	 */
	public String getStatut() {
		return statut;
	}

	/**
	 * Set the value related to the column: statut
	 * @param statut the statut value
	 */
	public void setStatut(String statut) {
		this.statut = statut;
	}
	
	
}