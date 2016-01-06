package pilotage.metier;

import java.io.Serializable;

public class Checklist_Consignes implements Serializable {

	private static final long serialVersionUID = 2973222813316694073L;

	private Integer id;

	private String consigne;

	
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
	 * Return the value associated with the column: consigne
	 */
	public String getConsigne() {
		return consigne;
	}
	
	/**
	 * Set the value related to the column: consigne
	 * @param consigne the consigne value
	 */
	public void setConsigne(String consigne) {
		this.consigne = consigne;
	}
	

}