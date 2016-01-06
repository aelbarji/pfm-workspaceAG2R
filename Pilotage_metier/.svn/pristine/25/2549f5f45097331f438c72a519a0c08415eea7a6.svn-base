package pilotage.metier;

import java.io.Serializable;

public class Astreinte_Obligatoire implements Serializable {

	private static final long serialVersionUID = 7456018014955787190L;

	// primary key
	private Integer id;

	// fields
	private Astreinte_Domaine domaine;
	private Astreinte_Type type;
	private Boolean indicEnvoi;
	
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
	 * Return the value associated with the column: domaine
	 */
	public Astreinte_Domaine getDomaine() {
		return domaine;
	}
	
	/**
	 * Set the value related to the column: domaine
	 * @param domaine the domaine value
	 */
	public void setDomaine(Astreinte_Domaine domaine) {
		this.domaine = domaine;
	}
	
	/**
	 * Return the value associated with the column: type
	 */
	public Astreinte_Type getType() {
		return type;
	}
	
	/**
	 * Set the value related to the column: type
	 * @param type the type value
	 */
	public void setType(Astreinte_Type type) {
		this.type = type;
	}
	
	/**
	 * Return the value associated with the column: indic_envoi
	 */
	public Boolean getIndicEnvoi() {
		return indicEnvoi;
	}
	
	/**
	 * Set the value related to the column: indic_envoi
	 * @param indicEnvoi the indic_envoi value
	 */
	public void setIndicEnvoi(Boolean indicEnvoi) {
		this.indicEnvoi = indicEnvoi;
	}
	
	
}