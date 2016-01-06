package pilotage.metier;

import java.io.Serializable;

public class Checklist_Consigne_Documents implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6118596932907403763L;

	private Integer id;

	private String document;
	private Checklist_Consignes idConsigne;
	
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

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	public Checklist_Consignes getIdConsigne() {
		return idConsigne;
	}

	public void setIdConsigne(Checklist_Consignes idConsigne) {
		this.idConsigne = idConsigne;
	}


}