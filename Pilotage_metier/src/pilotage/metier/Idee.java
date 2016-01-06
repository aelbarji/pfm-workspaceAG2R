package pilotage.metier;
/*
 * author by WWei
 * @Groupe HN
 * @18/06/2011
 */
import java.io.Serializable;
import java.util.Date;

public class Idee implements Serializable{
	private static final long serialVersionUID = -1605897362860548412L;

	private Integer id;
	private String idee;
	private Date   date;
	private String etat;
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
	 * Return the value associated with the column: idee
	 */
	public String getIdee() {
		return idee;
	}
	/**
	 * Set the value related to the column: idee
	 * @param idee the idee value
	 */
	public void setIdee(String idee) {
		this.idee = idee;
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
	 * Return the value associated with the column: idUser
	 */
	public Integer getIdUser() {
		return idUser;
	}
	/**
	 * Set the value related to the column: idUser
	 * @param idUser the idUser value
	 */
	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}
}
