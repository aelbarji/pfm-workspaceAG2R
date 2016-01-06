package pilotage.metier;

import java.io.Serializable;

public class Consignes_Pilotes_Validation implements Serializable {

	private static final long serialVersionUID = -7189053847348746191L;

	// primary key
	private Integer id;

	// fields
	private Users idPilote;
	private Consignes idConsigne;
	private Consignes_Validation valid;
	private String question;
	
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
	 * Return the value associated with the column: id_pilote
	 */
	public Users getIdPilote() {
		return idPilote;
	}
	
	/**
	 * Set the value related to the column: id_pilote
	 * @param idPilote the id_pilote value
	 */
	public void setIdPilote(Users idPilote) {
		this.idPilote = idPilote;
	}
	
	/**
	 * Return the value associated with the column: id_consigne
	 */
	public Consignes getIdConsigne() {
		return idConsigne;
	}
	
	/**
	 * Set the value related to the column: id_consigne
	 * @param idConsigne the id_consigne value
	 */
	public void setIdConsigne(Consignes idConsigne) {
		this.idConsigne = idConsigne;
	}
	
	/**
	 * Return the value associated with the column: valid
	 */
	public Consignes_Validation getValid() {
		return valid;
	}
	
	/**
	 * Set the value related to the column: valid
	 * @param valid the valid value
	 */
	public void setValid(Consignes_Validation valid) {
		this.valid = valid;
	}
	
	/**
	 * Return the value associated with the column: question
	 */
	public String getQuestion() {
		return question;
	}
	
	/**
	 * Set the value related to the column: question
	 * @param question the question value
	 */
	public void setQuestion(String question) {
		this.question = question;
	}

}