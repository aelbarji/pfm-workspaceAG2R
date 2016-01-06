package pilotage.metier;

import java.io.Serializable;

public class Applicatifs_Liste implements Serializable {

	private static final long serialVersionUID = 2452691698954754890L;

	// primary key
	private Integer id;

	// fields
	private String applicatif;
	private String description;
	private String documentation;
	private Boolean actif;
	
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
	 * Return the value associated with the column: applicatif
	 */
	public String getApplicatif() {
		return applicatif;
	}
	
	/**
	 * Set the value related to the column: applicatif
	 * @param applicatif the applicatif value
	 */
	public void setApplicatif(String applicatif) {
		this.applicatif = applicatif;
	}
	
	/**
	 * Return the value associated with the column: Description
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Set the value related to the column: Description
	 * @param description the Description value
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Return the value associated with the column: Documentation
	 */
	public String getDocumentation() {
		return documentation;
	}
	
	/**
	 * Set the value related to the column: Documentation
	 * @param documentation the Documentation value
	 */
	public void setDocumentation(String documentation) {
		this.documentation = documentation;
	}

	public Boolean getActif() {
		return actif;
	}

	public void setActif(Boolean actif) {
		this.actif = actif;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((applicatif == null) ? 0 : applicatif.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((documentation == null) ? 0 : documentation.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((actif == null) ? 0 : actif.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Applicatifs_Liste other = (Applicatifs_Liste) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}