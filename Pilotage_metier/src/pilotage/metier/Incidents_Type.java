package pilotage.metier;

import java.io.Serializable;

public class Incidents_Type implements Serializable{
	private static final long serialVersionUID = -7378520093183223372L;

	private Integer id;
	private String type;
	private String description;
	private int impact;
	private String titre_bilan;
	
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getImpact() {
		return impact;
	}
	public void setImpact(int impact) {
		this.impact = impact;
	}
	public String getTitre_bilan() {
		return titre_bilan;
	}
	public void setTitre_bilan(String titre_bilan) {
		this.titre_bilan = titre_bilan;
	}
	
}
