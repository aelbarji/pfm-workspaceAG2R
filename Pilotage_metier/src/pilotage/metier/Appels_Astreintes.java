package pilotage.metier;

import java.io.Serializable;
import java.util.Date;


public class Appels_Astreintes implements Serializable {
	
	private static final long serialVersionUID = 5917075938644553444L;

	// primary key
	private Integer id;

	// fields
	private Long incident;
	private Integer astreinte;
	private Date date;
	
	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="Assigned"
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
	 * Return the value associated with the column: incident
	 */
	public Long getIncident() {
		return incident;
	}
	
	/**
	 * Set the value related to the column: incident
	 * @param incident the incident value
	 */
	public void setIncident(Long incident) {
		this.incident = incident;
	}
	
	/**
	 * Return the value associated with the column: astreinte
	 */
	public Integer getAstreinte() {
		return astreinte;
	}
	
	/**
	 * Set the value related to the column: astreinte
	 * @param astreinte the astreinte value
	 */
	public void setAstreinte(Integer astreinte) {
		this.astreinte = astreinte;
	}
	
	/**
	 * Return the value associated with the column: date
	 */
	public Date getDate() {
		return date;
	}
	
	/**
	 * Set the value related to the column: date
	 * @param date the date value
	 */
	public void setDate(Date date) {
		this.date = date;
	}

}