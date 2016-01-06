package pilotage.metier;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Checklist_Current implements Serializable {

	private static final long serialVersionUID = -4868941830446992890L;

	private Integer id;

	private Checklist_Base tache;
	private Checklist_Base_Soustache sousTache;
	private Date heure;
	private Long heureStamp;
	private Date heureGmt;
	private Date jour;
	private Long jourStamp;
	private Checklist_Status status;
	private String commentaire;
	private Users user;
	private Checklist_Horaire idHoraire;
	private Date time_used;
	private String color;
	private Date heureExecution;
	
	private String heureDateString;
	private String heureHeureString;
	
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
	 * Return the value associated with the column: tache
	 */
	public Checklist_Base getTache() {
		return tache;
	}
	
	/**
	 * Set the value related to the column: tache
	 * @param tache the tache value
	 */
	public void setTache(Checklist_Base tache) {
		this.tache = tache;
	}
	
	/**
	 * Return the value associated with the column: sous_tache
	 */
	public Checklist_Base_Soustache getSousTache() {
		return sousTache;
	}
	
	/**
	 * Set the value related to the column: sous_tache
	 * @param sousTache the sous_tache value
	 */
	public void setSousTache(Checklist_Base_Soustache sousTache) {
		this.sousTache = sousTache;
	}
	
	/**
	 * Return the value associated with the column: heure
	 */
	public Date getHeure() {
		return heure;
	}
	
	/**
	 * Set the value related to the column: heure
	 * @param heure the heure value
	 */
	public void setHeure(Date heure) {
		this.heure = heure;
		DateFormat dfHour = new SimpleDateFormat("HH:mm");
		DateFormat dfDate = new SimpleDateFormat("dd/MM/yyyy");
		
		heureDateString = dfDate.format(heure);
		
		if("01/01/1970".equals(heureDateString))
			heureDateString = null;
		
		else
			heureHeureString = dfHour.format(heure);
	}
	
	/**
	 * Return the value associated with the column: heure_stamp
	 */
	public Long getHeureStamp() {
		return heureStamp;
	}
	
	/**
	 * Set the value related to the column: heure_stamp
	 * @param heureStamp the heure_stamp value
	 */
	public void setHeureStamp(Long heureStamp) {
		this.heureStamp = heureStamp;
	}
	
	/**
	 * Return the value associated with the column: heure_gmt
	 */
	public Date getHeureGmt() {
		return heureGmt;
	}
	
	/**
	 * Set the value related to the column: heure_gmt
	 * @param heureGmt the heure_gmt value
	 */
	public void setHeureGmt(Date heureGmt) {
		this.heureGmt = heureGmt;
	}
	
	/**
	 * Return the value associated with the column: jour
	 */
	public Date getJour() {
		return jour;
	}
	
	/**
	 * Set the value related to the column: jour
	 * @param jour the jour value
	 */
	public void setJour(Date jour) {
		this.jour = jour;
	}
	
	/**
	 * Return the value associated with the column: jour_stamp
	 */
	public Long getJourStamp() {
		return jourStamp;
	}
	
	/**
	 * Set the value related to the column: jour_stamp
	 * @param jourStamp the jour_stamp value
	 */
	public void setJourStamp(Long jourStamp) {
		this.jourStamp = jourStamp;
	}
	
	/**
	 * Return the value associated with the column: status
	 */
	public Checklist_Status getStatus() {
		return status;
	}
	
	/**
	 * Set the value related to the column: status
	 * @param status the status value
	 */
	public void setStatus(Checklist_Status status) {
		this.status = status;
	}
	
	/**
	 * Return the value associated with the column: commentaire
	 */
	public String getCommentaire() {
		return commentaire;
	}
	
	/**
	 * Set the value related to the column: commentaire
	 * @param commentaire the commentaire value
	 */
	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}
	
	/**
	 * Return the value associated with the column: user
	 */
	public Users getUser() {
		return user;
	}
	
	/**
	 * Set the value related to the column: user
	 * @param user the user value
	 */
	public void setUser(Users user) {
		this.user = user;
	}
	
	/**
	 * Return the value associated with the column: id_horaire
	 */
	public Checklist_Horaire getIdHoraire() {
		return idHoraire;
	}
	
	/**
	 * Set the value related to the column: id_horaire
	 * @param idHoraire the id_horaire value
	 */
	public void setIdHoraire(Checklist_Horaire idHoraire) {
		this.idHoraire = idHoraire;
	}

	/**
	 * Return the value associated with the column: time_used
	 */
	public Date getTime_used() {
		return time_used;
	}

	/**
	 * Set the value related to the column: time_used
	 * @param time_used the time_used value
	 */
	public void setTime_used(Date time_used) {
		this.time_used = time_used;
	}

	/**
	 * Getter de la couleur
	 * @return
	 */
	public String getColor() {
		return color;
	}

	/**
	 * Setter de la couleur
	 * @param color
	 */
	public void setColor(String color) {
		this.color = color;
	}

	/**
	 * Getter heure où on doit exécuter la tache
	 * @return
	 */
	public Date getHeureExecution() {
		return heureExecution;
	}

	/**
	 * Setter heure où on doit exécuter la tache
	 * @param heureExecution
	 */
	public void setHeureExecution(Date heureExecution) {
		this.heureExecution = heureExecution;
	}

	public String getHeureDateString() {
		return heureDateString;
	}

	public void setHeureDateString(String heureDateString) {
		this.heureDateString = heureDateString;
	}

	public String getHeureHeureString() {
		return heureHeureString;
	}

	public void setHeureHeureString(String heureHeureString) {
		this.heureHeureString = heureHeureString;
	}
}