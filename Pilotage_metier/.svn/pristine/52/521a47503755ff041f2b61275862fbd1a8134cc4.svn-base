package pilotage.metier;

import java.io.Serializable;

public class Astreinte implements Serializable {

	private static final long serialVersionUID = 6659130581278514973L;

	// primary key
	private Integer id;

	// fields
	private String nom;
	private String prenom;
	private String tel1;
	private String tel2;
	private Boolean actif;
	private Astreinte_Type type;
	
	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="increment"
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
	 * Return the value associated with the column: tel1
	 */
	public String getTel1() {
		return tel1;
	}
	
	/**
	 * Set the value related to the column: tel1
	 * @param tel1 the tel1 value
	 */
	public void setTel1(String tel1) {
		this.tel1 = tel1;
	}
	
	/**
	 * Return the value associated with the column: tel2
	 */
	public String getTel2() {
		return tel2;
	}
	
	/**
	 * Set the value related to the column: tel2
	 * @param tel2 the tel2 value
	 */
	public void setTel2(String tel2) {
		this.tel2 = tel2;
	}
	
	/**
	 * Return the value associated with the column: actif
	 */
	public Boolean getActif() {
		return actif;
	}
	
	/**
	 * Set the value related to the column: actif
	 * @param actif the actif value
	 */
	public void setActif(Boolean actif) {
		this.actif = actif;
	}
	
	/**
	 * Return the value associated with the column: type
	 */
	public Astreinte_Type getType() {
		return type;
	}
	
	/**
	 * Set the value related to the column: type
	 * @param type the type value
	 */
	public void setType(Astreinte_Type type) {
		this.type = type;
	}
	
	/**
	 * Récupère la concaténation du nom et du prénom
	 * @return
	 */
	public String getNomPrenom(){
		return nom + " " + prenom;
	}

}