package pilotage.metier;
/*
 * author by WWei
 * @Groupe HN
 * @18/06/2011
 */
import java.io.Serializable;
import java.util.Date;

public class Forum_Commentaire implements Serializable{
	private static final long serialVersionUID = 3915062918738045355L;

	private Integer id;
	private Integer sujet;
	private String  text;
	private Integer createur;
	private Date    dateCreation;
	private Integer numero;
	private Integer sousCommentaire;
	
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
	 * Return the value associated with the column: sujet
	 */
	public Integer getSujet() {
		return sujet;
	}
	/**
	 * Set the value related to the column: sujet
	 * @param sujet the sujet value
	 */
	public void setSujet(Integer sujet) {
		this.sujet = sujet;
	}
	/**
	 * Return the value associated with the column: text
	 */
	public String getText() {
		return text;
	}
	/**
	 * Set the value related to the column: text
	 * @param text the text value
	 */
	public void setText(String text) {
		this.text = text;
	}
	/**
	 * Return the value associated with the column: createur
	 */
	public Integer getCreateur() {
		return createur;
	}
	/**
	 * Set the value related to the column: createur
	 * @param createur the createur value
	 */
	public void setCreateur(Integer createur) {
		this.createur = createur;
	}
	/**
	 * Return the value associated with the column: dateCreation
	 */
	public Date getDateCreation() {
		return dateCreation;
	}
	/**
	 * Set the value related to the column: dateCreation
	 * @param dateCreation the dateCreation value
	 */
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	/**
	 * Return the value associated with the column: numero
	 */
	public Integer getNumero() {
		return numero;
	}
	/**
	 * Set the value related to the column: numero
	 * @param numero the numero value
	 */
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	/**
	 * Return the value associated with the column: sousCommentaire
	 */
	public Integer getSousCommentaire() {
		return sousCommentaire;
	}
	/**
	 * Set the value related to the column: sousCommentaire
	 * @param sousCommentaire the sousCommentaire value
	 */
	public void setSousCommentaire(Integer sousCommentaire) {
		this.sousCommentaire = sousCommentaire;
	}
}
