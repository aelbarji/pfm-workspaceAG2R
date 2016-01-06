package pilotage.metier;

import java.io.Serializable;
import java.math.BigInteger;

public class Consignes_Fichier implements Serializable {

	private static final long serialVersionUID = 4339166266216032647L;

	// primary key
	private Integer id;

	// fields
	private String nomFichier;
	private String localisation;
	private Long lastmodif;
	private Integer hashcode;
	private BigInteger taille;
	private boolean m_New;
	private Byte indexe;
	
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
	 * Return the value associated with the column: Nom_fichier
	 */
	public String getNomFichier() {
		return nomFichier;
	}
	
	/**
	 * Set the value related to the column: Nom_fichier
	 * @param nomFichier the Nom_fichier value
	 */
	public void setNomFichier(String nomFichier) {
		this.nomFichier = nomFichier;
	}
	
	/**
	 * Return the value associated with the column: Localisation
	 */
	public String getLocalisation() {
		return localisation;
	}
	
	/**
	 * Set the value related to the column: Localisation
	 * @param localisation the Localisation value
	 */
	public void setLocalisation(String localisation) {
		this.localisation = localisation;
	}
	
	/**
	 * Return the value associated with the column: Lastmodif
	 */
	public Long getLastmodif() {
		return lastmodif;
	}
	
	/**
	 * Set the value related to the column: Lastmodif
	 * @param lastmodif the Lastmodif value
	 */
	public void setLastmodif(Long lastmodif) {
		this.lastmodif = lastmodif;
	}
	
	/**
	 * Return the value associated with the column: Hashcode
	 */
	public Integer getHashcode() {
		return hashcode;
	}
	
	/**
	 * Set the value related to the column: Hashcode
	 * @param hashcode the Hashcode value
	 */
	public void setHashcode(Integer hashcode) {
		this.hashcode = hashcode;
	}
	
	/**
	 * Return the value associated with the column: Taille
	 */
	public BigInteger getTaille() {
		return taille;
	}
	
	/**
	 * Set the value related to the column: Taille
	 * @param taille the Taille value
	 */
	public void setTaille(BigInteger taille) {
		this.taille = taille;
	}
	
	/**
	 * Return the value associated with the column: new
	 */
	public boolean isM_New() {
		return m_New;
	}

	/**
	 * Set the value related to the column: new
	 * @param m_new the new value
	 */
	public void setM_New(boolean m_New) {
		this.m_New = m_New;
	}
	
	/**
	 * Return the value associated with the column: indexe
	 */
	public Byte getIndexe() {
		return indexe;
	}
	
	/**
	 * Set the value related to the column: indexe
	 * @param indexe the indexe value
	 */
	public void setIndexe(Byte indexe) {
		this.indexe = indexe;
	}
}