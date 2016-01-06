package pilotage.metier;

import java.io.Serializable;

public class Appli_Service implements Serializable {

	private static final long serialVersionUID = -7426598685825172274L;

	// primary key
	private Integer id;

	// fields
	private Applicatifs_Liste idApplication;
	private Services_Liste idServices;
	
	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="Assigned"
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
	 * Return the value associated with the column: id_application
	 */
	public Applicatifs_Liste getIdApplication() {
		return idApplication;
	}
	
	/**
	 * Set the value related to the column: id_application
	 * @param idApplication the id_application value
	 */
	public void setIdApplication(Applicatifs_Liste idApplication) {
		this.idApplication = idApplication;
	}
	
	/**
	 * Return the value associated with the column: id_services
	 */
	public Services_Liste getIdServices() {
		return idServices;
	}
	
	/**
	 * Set the value related to the column: id_services
	 * @param idServices the id_services value
	 */
	public void setIdServices(Services_Liste idServices) {
		this.idServices = idServices;
	}
}