package pilotage.metier;
/*
 * author by WWei
 * @Groupe HN
 * @18/06/2011
 */
import java.io.Serializable;

public class Sieactiv implements Serializable{
	private static final long serialVersionUID = -1275590902770150872L;

	private Integer id;
	private Boolean active;
	
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
	 * Return the value associated with the column: active
	 */
	public Boolean getActive() {
		return active;
	}
	/**
	 * Set the value related to the column: active
	 * @param active the active value
	 */
	public void setActive(Boolean active) {
		this.active = active;
	}
	
}
