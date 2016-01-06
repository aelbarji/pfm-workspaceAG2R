package pilotage.metier;

import java.io.Serializable;

public class Checklist_Parite implements Serializable {

	private static final long serialVersionUID = 4354042074381823800L;

	private Integer id;

	private Checklist_Base idChecklist;
	private Integer parite;
	
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
	 * Return the value associated with the column: parite
	 */
	public Integer getParite() {
		return parite;
	}
	
	/**
	 * Set the value related to the column: parite
	 * @param parite the parite value
	 */
	public void setParite(Integer parite) {
		this.parite = parite;
	}
	
}