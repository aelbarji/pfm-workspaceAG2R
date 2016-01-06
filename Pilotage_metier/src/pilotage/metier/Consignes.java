package pilotage.metier;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Consignes implements Serializable {

	private static final long serialVersionUID = 371261310053558899L;

	private static DateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
	private static DateFormat heureFormatter = new SimpleDateFormat("HH:mm");
	
	// primary key
	private Integer id;


	// fields
	private Date date;
	private String text;
	private Boolean couleur;
	private Users createur;
	private String fichierconsigne;
	private Boolean old;
	private Consignes_Type type;
	
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
	 * Return the value associated with the column: couleur
	 */
	public Boolean getCouleur() {
		return couleur;
	}
	
	/**
	 * Set the value related to the column: couleur
	 * @param couleur the couleur value
	 */
	public void setCouleur(Boolean couleur) {
		this.couleur = couleur;
	}
	
	/**
	 * Return the value associated with the column: createur
	 */
	public Users getCreateur() {
		return createur;
	}
	
	/**
	 * Set the value related to the column: createur
	 * @param createur the createur value
	 */
	public void setCreateur(Users createur) {
		this.createur = createur;
	}
	
	public String getFichierconsigne() {
		return fichierconsigne;
	}

	public void setFichierconsigne(String fichierconsigne) {
		this.fichierconsigne = fichierconsigne;
	}

	/**
	 * Return the value associated with the column: old
	 */
	public Boolean getOld() {
		return old;
	}
	
	/**
	 * Set the value related to the column: old
	 * @param old the old value
	 */
	public void setOld(Boolean old) {
		this.old = old;
	}
	
	public Consignes_Type getType() {
		return type;
	}

	public void setType(Consignes_Type type) {
		this.type = type;
	}

	/**
	 * Formatte la date dans le format dd/MM/yyyy
	 * @return
	 */
	public String getDateFormattee(){
		if(date == null)
			return null;
		else
			return dateFormatter.format(date);
	}
	
	/**
	 * Formatte l'heure associée à la date au format HH:mm
	 * @return
	 */
	public String getHeureFormattee(){
		if(date == null)
			return null;
		else
			return heureFormatter.format(date);
	}

}