package pilotage.metier;

import java.io.Serializable;
import java.math.BigInteger;

public class Consignes_Mots implements Serializable {

	private static final long serialVersionUID = 611236649733424160L;

	// primary key
	private java.lang.Integer id;

	// fields
	private String mot;
	private BigInteger hashcode;
	private Integer recherche;
	
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
	 * Return the value associated with the column: mot
	 */
	public String getMot() {
		return mot;
	}
	
	/**
	 * Set the value related to the column: mot
	 * @param mot the mot value
	 */
	public void setMot(String mot) {
		this.mot = mot;
	}
	
	/**
	 * Return the value associated with the column: Hashcode
	 */
	public BigInteger getHashcode() {
		return hashcode;
	}
	
	/**
	 * Set the value related to the column: Hashcode
	 * @param hashcode the Hashcode value
	 */
	public void setHashcode(BigInteger hashcode) {
		this.hashcode = hashcode;
	}
	
	/**
	 * Return the value associated with the column: recherche
	 */
	public Integer getRecherche() {
		return recherche;
	}
	
	/**
	 * Set the value related to the column: recherche
	 * @param recherche the recherche value
	 */
	public void setRecherche(Integer recherche) {
		this.recherche = recherche;
	}

}