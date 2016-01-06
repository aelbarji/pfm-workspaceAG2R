package pilotage.metier;

import java.io.Serializable;
import java.util.Date;

public class Checklist_Annule implements Serializable {

	private static final long serialVersionUID = -836405551046857102L;

	private Integer id;

	private Checklist_Base_Soustache tache;
	private Date date;
	private Checklist_Horaire horaire;
	private Users user;
	
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
	 * Return the value associated with the column: tache
	 */
	public Checklist_Base_Soustache getTache() {
		return tache;
	}
	
	/**
	 * Set the value related to the column: tache
	 * @param tache the tache value
	 */
	public void setTache(Checklist_Base_Soustache tache) {
		this.tache = tache;
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
	 * Return the value associated with the column: horaire
	 */
	public Checklist_Horaire getHoraire() {
		return horaire;
	}
	
	/**
	 * Set the value related to the column: horaire
	 * @param horaire the horaire value
	 */
	public void setHoraire(Checklist_Horaire horaire) {
		this.horaire = horaire;
	}
	
	/**
	 * Return the value associated with the column: user
	 */
	public Users getUser() {
		return user;
	}
	
	/**
	 * Set the value related to the column: user
	 * @param user the user value
	 */
	public void setUser(Users user) {
		this.user = user;
	}

	
}