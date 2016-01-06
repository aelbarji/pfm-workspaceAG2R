package pilotage.metier;

import java.io.Serializable;
import java.util.Date;

public class Connexion_Pilote implements Serializable {

	private static final long serialVersionUID = 2346164810975343353L;

	// primary key
	private Integer id;

	// fields
	private Integer idPilote;
	private String nom;
	private String prenom;
	private Date mois;
	private Date date;
	private Float nbConnexion;
	
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
	 * Return the value associated with the column: idPilote
	 */
	public Integer getIdPilote() {
		return idPilote;
	}
	
	/**
	 * Set the value related to the column: idPilote
	 * @param idPilote the idPilote value
	 */
	public void setIdPilote(Integer idPilote) {
		this.idPilote = idPilote;
	}
	
	/**
	 * Return the value associated with the column: nom
	 */
	public String getNom() {
		return nom;
	}
	
	/**
	 * Set the value related to the column: nom
	 * @param nom the nom value
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	/**
	 * Return the value associated with the column: prenom
	 */
	public String getPrenom() {
		return prenom;
	}
	
	/**
	 * Set the value related to the column: prenom
	 * @param prenom the prenom value
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	/**
	 * Return the value associated with the column: mois
	 */
	public Date getMois() {
		return mois;
	}
	
	/**
	 * Set the value related to the column: mois
	 * @param mois the mois value
	 */
	public void setMois(Date mois) {
		this.mois = mois;
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
	 * Return the value associated with the column: nbConnexion
	 */
	public Float getNbConnexion() {
		return nbConnexion;
	}
	
	/**
	 * Set the value related to the column: nbConnexion
	 * @param nbConnexion the nbConnexion value
	 */
	public void setNbConnexion(Float nbConnexion) {
		this.nbConnexion = nbConnexion;
	}
	
}