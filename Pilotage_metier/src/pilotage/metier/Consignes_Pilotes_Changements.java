package pilotage.metier;

import java.io.Serializable;
import java.util.Date;

public class Consignes_Pilotes_Changements implements Serializable {

	private static final long serialVersionUID = 7643385219240073649L;

	// primary key
	private Integer id;

	// fields
	private Users idUser;
	private Consignes idConsigne;
	private Consignes_Type_Changement typeChangement;
	private Date dateChangement;
	private String avant;
	private String apres;
	
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
	
	/**
	 * Return the value associated with the column: id_consigne
	 */
	public Consignes getIdConsigne() {
		return idConsigne;
	}
	
	/**
	 * Set the value related to the column: id_consigne
	 * @param idConsigne the id_consigne value
	 */
	public void setIdConsigne(Consignes idConsigne) {
		this.idConsigne = idConsigne;
	}
	
	/**
	 * Return the value associated with the column: type_changement
	 */
	public Consignes_Type_Changement getTypeChangement() {
		return typeChangement;
	}
	
	/**
	 * Set the value related to the column: type_changement
	 * @param typeChangement the type_changement value
	 */
	public void setTypeChangement(Consignes_Type_Changement typeChangement) {
		this.typeChangement = typeChangement;
	}
	
	/**
	 * Return the value associated with the column: date_changement
	 */
	public Date getDateChangement() {
		return dateChangement;
	}
	
	/**
	 * Set the value related to the column: date_changement
	 * @param dateChangement the date_changement value
	 */
	public void setDateChangement(Date dateChangement) {
		this.dateChangement = dateChangement;
	}
	
	/**
	 * Return the value associated with the column: avant
	 */
	public String getAvant() {
		return avant;
	}
	
	/**
	 * Set the value related to the column: avant
	 * @param avant the avant value
	 */
	public void setAvant(String avant) {
		this.avant = avant;
	}
	
	/**
	 * Return the value associated with the column: apres
	 */
	public String getApres() {
		return apres;
	}
	
	/**
	 * Set the value related to the column: apres
	 * @param apres the apres value
	 */
	public void setApres(String apres) {
		this.apres = apres;
	}
}