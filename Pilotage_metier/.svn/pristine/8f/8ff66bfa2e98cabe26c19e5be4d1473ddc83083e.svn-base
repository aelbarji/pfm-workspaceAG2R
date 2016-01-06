package pilotage.metier;

import java.io.Serializable;

public class Consignes_Fichier_Mot implements Serializable {

	private static final long serialVersionUID = -4640051049216788914L;

	// primary key
	private Integer id;

	// fields
	private Consignes_Fichier idFichier;
	private Consignes_Mots idMot;
	
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
	 * Return the value associated with the column: id_fichier
	 */
	public Consignes_Fichier getIdFichier() {
		return idFichier;
	}
	
	/**
	 * Set the value related to the column: id_fichier
	 * @param idFichier the id_fichier value
	 */
	public void setIdFichier(Consignes_Fichier idFichier) {
		this.idFichier = idFichier;
	}
	
	/**
	 * Return the value associated with the column: id_mot
	 */
	public Consignes_Mots getIdMot() {
		return idMot;
	}
	
	/**
	 * Set the value related to the column: id_mot
	 * @param idMot the id_mot value
	 */
	public void setIdMot(Consignes_Mots idMot) {
		this.idMot = idMot;
	}
}