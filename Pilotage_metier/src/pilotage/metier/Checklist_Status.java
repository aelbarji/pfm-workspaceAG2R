package pilotage.metier;

import java.io.Serializable;

public class Checklist_Status implements Serializable {

	private static final long serialVersionUID = 8755057479208105389L;

	private Integer id;
	private String status;

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
	 * Return the value associated with the column: status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Set the value related to the column: status
	 * @param status the status value
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
}