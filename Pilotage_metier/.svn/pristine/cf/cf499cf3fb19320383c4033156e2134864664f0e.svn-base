package pilotage.metier;

import java.io.Serializable;
import java.util.Date;

public class Chat implements Serializable {

	private static final long serialVersionUID = 5239840476168927450L;

	// primary key
	private Integer id;

	// fields
	private Date date;
	private Integer datestamp;
	private String text;
	private Integer idUser;
	
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
	 * Return the value associated with the column: datestamp
	 */
	public Integer getDatestamp() {
		return datestamp;
	}
	
	/**
	 * Set the value related to the column: datestamp
	 * @param datestamp the datestamp value
	 */
	public void setDatestamp(Integer datestamp) {
		this.datestamp = datestamp;
	}
	
	/**
	 * Return the value associated with the column: text
	 */
	public String getText() {
		return text;
	}
	
	/**
	 * Set the value related to the column: text
	 * @param text the text value
	 */
	public void setText(String text) {
		this.text = text;
	}
	
	/**
	 * Return the value associated with the column: id_user
	 */
	public Integer getIdUser() {
		return idUser;
	}
	
	/**
	 * Set the value related to the column: id_user
	 * @param idUser the id_user value
	 */
	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

}