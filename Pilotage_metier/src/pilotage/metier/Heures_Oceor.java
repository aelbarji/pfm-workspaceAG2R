package pilotage.metier;
/*
 * author by WWei
 * @Groupe HN
 * @18/06/2011
 */
import java.io.Serializable;

public class Heures_Oceor implements Serializable{
	private static final long serialVersionUID = 1530774337489936648L;

	private Integer id;
	private String nom;
	private String heure;
	private String timezone;
	
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
	 * Return the value associated with the column: heure
	 */
	public String getHeure() {
		return heure;
	}
	/**
	 * Set the value related to the column: heure
	 * @param heure the heure value
	 */
	public void setHeure(String heure) {
		this.heure = heure;
	}
	/**
	 * Return the value associated with the column: timezone
	 */
	public String getTimezone() {
		return timezone;
	}
	/**
	 * Set the value related to the column: timezone
	 * @param timezone the timezone value
	 */
	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}
}
