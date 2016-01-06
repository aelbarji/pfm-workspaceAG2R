package pilotage.metier;

import java.io.Serializable;
import java.util.Date;

public class Bilan_Test implements Serializable {

	private static final long serialVersionUID = 3948310684987186458L;

	// primary key
	private Integer id;

	// fields
	private String nomBilan;
	private Date dateHeure;
	
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
	 * Return the value associated with the column: nomBilan
	 */
	public String getNomBilan() {
		return nomBilan;
	}
	
	/**
	 * Set the value related to the column: nomBilan
	 * @param nomBilan the nomBilan value
	 */
	public void setNomBilan(String nomBilan) {
		this.nomBilan = nomBilan;
	}
	
	/**
	 * Return the value associated with the column: dateHeure
	 */
	public Date getDateHeure() {
		return dateHeure;
	}
	
	/**
	 * Set the value related to the column: dateHeure
	 * @param dateHeure the dateHeure value
	 */
	public void setDateHeure(Date dateHeure) {
		this.dateHeure = dateHeure;
	}

}