package pilotage.metier;

import java.io.Serializable;

public class Environnement implements Serializable{

	private static final long serialVersionUID = 7361384829093876643L;

	private Integer id;

	private String environnement;
	private Environnement_Type type;
	
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
	 * Return the value associated with the column: environnement
	 */
	public String getEnvironnement() {
		return environnement;
	}
	/**
	 * Set the value related to the column: environnement
	 * @param environnement the environnement value
	 */
	public void setEnvironnement(String environnement) {
		this.environnement = environnement;
	}
	/**
	 * Return the value associated with the column: Type
	 */
	public Environnement_Type getType() {
		return type;
	}
	/**
	 * Set the value related to the column: Type
	 * @param Type the Type value
	 */
	public void setType(Environnement_Type type) {
		this.type = type;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((environnement == null) ? 0 : environnement.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Environnement other = (Environnement) obj;
		if (environnement == null) {
			if (other.environnement != null)
				return false;
		} else if (!environnement.equals(other.environnement))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
	
	
}
