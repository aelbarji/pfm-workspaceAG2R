package pilotage.metier;
/*
 * author by WWei
 * @Groupe HN
 * @18/06/2011
 */
import java.io.Serializable;

public class Forum_Type_Sujet implements Serializable{
	private static final long serialVersionUID = -7836110539657850573L;

	private Integer id;
	private String type;
	
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
