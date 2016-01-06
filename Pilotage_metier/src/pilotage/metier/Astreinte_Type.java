package pilotage.metier;

import java.io.Serializable;

public class Astreinte_Type implements Serializable {

	private static final long serialVersionUID = -7197367736631647867L;

	// primary key
	private Integer id;

	// fields
	private String type;

	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="increment"
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
	 * Return the value associated with the column: type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Set the value related to the column: type
	 * @param type the type value
	 */
	public void setType(String type) {
		this.type = type;
	}
	
}