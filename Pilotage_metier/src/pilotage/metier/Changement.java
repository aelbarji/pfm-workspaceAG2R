package pilotage.metier;

import java.io.Serializable;
import java.util.Date;

public class Changement implements Serializable {

	private static final long serialVersionUID = 7190528686709022814L;

	// primary key
	private Integer id;

	// fields
	private String libelle;
	private Integer environnement;
	private Date datedebut;
	private Date datefin;
	private String intervenant;
	private Integer statut;
	
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
	 * Return the value associated with the column: libelle
	 */
	public String getLibelle() {
		return libelle;
	}
	
	/**
	 * Set the value related to the column: libelle
	 * @param libelle the libelle value
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
	/**
	 * Return the value associated with the column: environnement
	 */
	public Integer getEnvironnement() {
		return environnement;
	}
	
	/**
	 * Set the value related to the column: environnement
	 * @param environnement the environnement value
	 */
	public void setEnvironnement(Integer environnement) {
		this.environnement = environnement;
	}
	
	/**
	 * Return the value associated with the column: datedebut
	 */
	public Date getDatedebut() {
		return datedebut;
	}
	
	/**
	 * Set the value related to the column: datedebut
	 * @param datedebut the datedebut value
	 */
	public void setDatedebut(Date datedebut) {
		this.datedebut = datedebut;
	}
	
	/**
	 * Return the value associated with the column: datefin
	 */
	public Date getDatefin() {
		return datefin;
	}
	
	/**
	 * Set the value related to the column: datefin
	 * @param datefin the datefin value
	 */
	public void setDatefin(Date datefin) {
		this.datefin = datefin;
	}
	
	/**
	 * Return the value associated with the column: intervenant
	 */
	public String getIntervenant() {
		return intervenant;
	}
	
	/**
	 * Set the value related to the column: intervenant
	 * @param intervenant the intervenant value
	 */
	public void setIntervenant(String intervenant) {
		this.intervenant = intervenant;
	}
	
	/**
	 * Return the value associated with the column: statut
	 */
	public Integer getStatut() {
		return statut;
	}
	
	/**
	 * Set the value related to the column: statut
	 * @param statut the statut value
	 */
	public void setStatut(Integer statut) {
		this.statut = statut;
	}
	
}