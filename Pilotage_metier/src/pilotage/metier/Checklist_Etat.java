package pilotage.metier;

import java.io.Serializable;

public class Checklist_Etat implements Serializable {

	private static final long serialVersionUID = 1800568343863617500L;

	private Integer id;

	private String etat;

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
	 * Return the value associated with the column: etat
	 */
	public String getEtat() {
		return etat;
	}

	/**
	 * Set the value related to the column: etat
	 * @param etat the etat value
	 */
	public void setEtat(String etat) {
		this.etat = etat;
	}
	
}