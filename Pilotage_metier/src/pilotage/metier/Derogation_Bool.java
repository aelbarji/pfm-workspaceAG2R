package pilotage.metier;
/*
 * author by WWei
 * @Groupe HN
 * @18/06/2011
 */
import java.io.Serializable;

public class Derogation_Bool implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = 3268939942585926095L;
	//primary keys
	private Integer id;
	//fields
	private String bool;
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
	 * Return the value associated with the column: bool
	 */
	public String getBool() {
		return bool;
	}
	/**
	 * Set the value related to the column: bool
	 * @param bool the date bool
	 */
	public void setBool(String bool) {
		this.bool = bool;
	}
	
}
