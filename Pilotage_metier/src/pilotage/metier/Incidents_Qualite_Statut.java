package pilotage.metier;

import java.io.Serializable;

public class Incidents_Qualite_Statut implements Serializable{
 
	private static final long serialVersionUID = 1379986945185454696L;
	private Integer id;
    private String statut;
     
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

	public String getStatut() {
		return statut;
	}
	public void setStatut(String statut) {
		this.statut = statut;
	}
}
