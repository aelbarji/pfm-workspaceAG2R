package pilotage.metier;

import java.io.Serializable;
import java.util.Date;

public class Bug implements Serializable {

	private static final long serialVersionUID = 8375186238535336114L;

	// primary key
	private Integer id;

	// fields
	private Date date;
	private String bug;
	private String etat;
	private Users idUser;
	
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
	
	/**
	 * Return the value associated with the column: bug
	 */
	public String getBug() {
		return bug;
	}
	
	/**
	 * Set the value related to the column: bug
	 * @param bug the bug value
	 */
	public void setBug(String bug) {
		this.bug = bug;
	}
	
	/**
	 * Return the value associated with the column: etat
	 */
	public String getEtat() {
		return etat;
	}
	
	/**
	 * Set the value related to the column: etat
	 * @param etat the etat value
	 */
	public void setEtat(String etat) {
		this.etat = etat;
	}
	
	/**
	 * Return the value associated with the column: id_user
	 */
	public Users getIdUser() {
		return idUser;
	}
	
	/**
	 * Set the value related to the column: id_user
	 * @param idUser the id_user value
	 */
	public void setIdUser(Users idUser) {
		this.idUser = idUser;
	}
	
	
}