package pilotage.metier;
/*
 * author by WWei
 * @Groupe HN
 * @18/06/2011
 */
import java.io.Serializable;
import java.util.Date;

public class Forum_Sujet implements Serializable{

	private static final long serialVersionUID = -4412933288792121608L;

	private Integer id;
	private Integer forum;
	private String  sujet;
	private Integer createur;
	private Date    dateCreation;
	private Integer importance;
	private Integer lastPost;
	private Integer estVerrouille;
	
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
	 * Return the value associated with the column: forum
	 */
	public Integer getForum() {
		return forum;
	}
	/**
	 * Set the value related to the column: forum
	 * @param forum the forum value
	 */
	public void setForum(Integer forum) {
		this.forum = forum;
	}
	/**
	 * Return the value associated with the column: sujet
	 */
	public String getSujet() {
		return sujet;
	}
	/**
	 * Set the value related to the column: sujet
	 * @param sujet the sujet value
	 */
	public void setSujet(String sujet) {
		this.sujet = sujet;
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
	 * Return the value associated with the column: importance
	 */
	public Integer getImportance() {
		return importance;
	}
	/**
	 * Set the value related to the column: importance
	 * @param importance the importance value
	 */
	public void setImportance(Integer importance) {
		this.importance = importance;
	}
	/**
	 * Return the value associated with the column: lastPost
	 */
	public Integer getLastPost() {
		return lastPost;
	}
	/**
	 * Set the value related to the column: lastPost
	 * @param lastPost the lastPost value
	 */
	public void setLastPost(Integer lastPost) {
		this.lastPost = lastPost;
	}
	/**
	 * Return the value associated with the column: estVerrouille
	 */
	public Integer getEstVerrouille() {
		return estVerrouille;
	}
	/**
	 * Set the value related to the column: estVerrouille
	 * @param estVerrouille the estVerrouille value
	 */
	public void setEstVerrouille(Integer estVerrouille) {
		this.estVerrouille = estVerrouille;
	}
	
}
