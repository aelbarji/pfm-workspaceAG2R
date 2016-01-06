package pilotage.metier;

import java.io.Serializable;
import java.util.Date;

public class Checklist_Horaire implements Serializable {

	private static final long serialVersionUID = 449104192732704930L;

	private Integer id;

	private Checklist_Base idChecklist;
	private Date horaire;
	private Long horaireStamp;
	private Boolean actif;
	
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
	 * Return the value associated with the column: id_checklist
	 */
	public Checklist_Base getIdChecklist() {
		return idChecklist;
	}
	
	/**
	 * Set the value related to the column: id_checklist
	 * @param idChecklist the id_checklist value
	 */
	public void setIdChecklist(Checklist_Base idChecklist) {
		this.idChecklist = idChecklist;
	}
	
	/**
	 * Return the value associated with the column: horaire
	 */
	public Date getHoraire() {
		return horaire;
	}
	
	/**
	 * Set the value related to the column: horaire
	 * @param horaire the horaire value
	 */
	public void setHoraire(Date horaire) {
		this.horaire = horaire;
	}
	
	/**
	 * Return the value associated with the column: horaire_stamp
	 */
	public Long getHoraireStamp() {
		return horaireStamp;
	}
	
	/**
	 * Set the value related to the column: horaire_stamp
	 * @param horaireStamp the horaire_stamp value
	 */
	public void setHoraireStamp(Long horaireStamp) {
		this.horaireStamp = horaireStamp;
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
	
	
}