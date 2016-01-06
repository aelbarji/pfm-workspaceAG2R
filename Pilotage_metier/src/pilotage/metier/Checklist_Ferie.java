package pilotage.metier;

import java.io.Serializable;

public class Checklist_Ferie implements Serializable {

	private static final long serialVersionUID = -3288155352315397659L;

	private Integer id;

	private Checklist_Base idChecklist;
	private Jour_Ferie idJourFerie;
	private Integer lendeveille;
	
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
	 * Return the value associated with the column: id_jour_ferie
	 */
	public Jour_Ferie getIdJourFerie() {
		return idJourFerie;
	}
	
	/**
	 * Set the value related to the column: id_jour_ferie
	 * @param idJourFerie the id_jour_ferie value
	 */
	public void setIdJourFerie(Jour_Ferie idJourFerie) {
		this.idJourFerie = idJourFerie;
	}
	
	/**
	 * Return the value associated with the column: lendeveille
	 */
	public Integer getLendeveille() {
		return lendeveille;
	}
	
	/**
	 * Set the value related to the column: lendeveille
	 * @param lendeveille the lendeveille value
	 */
	public void setLendeveille(Integer lendeveille) {
		this.lendeveille = lendeveille;
	}
	
}