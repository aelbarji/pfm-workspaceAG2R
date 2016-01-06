package pilotage.metier;

import java.io.Serializable;

public class Checklist_Jour implements Serializable {

	private static final long serialVersionUID = 8032493115330793448L;

	private Integer id;

	private Checklist_Base idChecklist;
	private Integer jour;
	private Boolean ferie;
	
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
	 * Return the value associated with the column: jour
	 */
	public Integer getJour() {
		return jour;
	}
	
	/**
	 * Set the value related to the column: jour
	 * @param jour the jour value
	 */
	public void setJour(Integer jour) {
		this.jour = jour;
	}
	
	/**
	 * Return the value associated with the column: ferie
	 */
	public Boolean isFerie() {
		return ferie;
	}
	
	/**
	 * Set the value related to the column: ferie
	 * @param ferie the ferie value
	 */
	public void setFerie(Boolean ferie) {
		this.ferie = ferie;
	}
	
}