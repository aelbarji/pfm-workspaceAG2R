package pilotage.metier;

import java.io.Serializable;
import java.util.Date;


public class Alertes_Disques implements Serializable {

	private static final long serialVersionUID = -3181662563096139674L;

	// primary key
	private Integer id;

	// fields
	private Date date;
	private Machines_Liste systeme;
	private String fs;
	private Integer seuil;
	private Alertes_Type type;
	private Integer alerte;
	private Users user;
	
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
	 * Return the value associated with the column: fs
	 */
	public String getFs() {
		return fs;
	}
	
	/**
	 * Set the value related to the column: fs
	 * @param fs the fs value
	 */
	public void setFs(String fs) {
		this.fs = fs;
	}
	
	/**
	 * Return the value associated with the column: seuil
	 */
	public Integer getSeuil() {
		return seuil;
	}
	
	/**
	 * Set the value related to the column: seuil
	 * @param seuil the seuil value
	 */
	public void setSeuil(Integer seuil) {
		this.seuil = seuil;
	}
	
	/**
	 * Return the value associated with the column: alerte
	 */
	public Integer getAlerte() {
		return alerte;
	}
	
	/**
	 * Set the value related to the column: alerte
	 * @param alerte the alerte value
	 */
	public void setAlerte(Integer alerte) {
		this.alerte = alerte;
	}

	/**
	 * @return the systeme
	 */
	public Machines_Liste getSysteme() {
		return systeme;
	}

	/**
	 * @param systeme the systeme to set
	 */
	public void setSysteme(Machines_Liste systeme) {
		this.systeme = systeme;
	}

	/**
	 * @return the type
	 */
	public Alertes_Type getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(Alertes_Type type) {
		this.type = type;
	}

	/**
	 * @return the user
	 */
	public Users getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(Users user) {
		this.user = user;
	}
	

}