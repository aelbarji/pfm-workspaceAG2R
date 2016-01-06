package pilotage.metier;

import java.io.Serializable;

public class Checklist_Mensuel implements Serializable {

	private static final long serialVersionUID = -8392680920918886858L;

	private Integer id;

	private Checklist_Base idChecklist;
	private Integer mensuel;
	
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
	 * Return the value associated with the column: mensuel
	 */
	public Integer getMensuel() {
		return mensuel;
	}
	
	/**
	 * Set the value related to the column: mensuel
	 * @param mensuel the mensuel value
	 */
	public void setMensuel(Integer mensuel) {
		this.mensuel = mensuel;
	}	
}