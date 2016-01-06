package pilotage.metier;

public class Disques {
	private Integer id;
	private String libelle;
	private boolean filiale;
	private Float seuil;
	private boolean deleted;
	
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the libelle
	 */
	public String getLibelle() {
		return libelle;
	}
	/**
	 * @param libelle the libelle to set
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	/**
	 * @return the filiale
	 */
	public boolean isFiliale() {
		return filiale;
	}
	/**
	 * @param filiale the filiale to set
	 */
	public void setFiliale(boolean filiale) {
		this.filiale = filiale;
	}
	/**
	 * @return the seuil
	 */
	public Float getSeuil() {
		return seuil;
	}
	/**
	 * @param seuil the seuil to set
	 */
	public void setSeuil(Float seuil) {
		this.seuil = seuil;
	}
	/**
	 * @return the deleted
	 */
	public boolean isDeleted() {
		return deleted;
	}
	/**
	 * @param deleted the deleted to set
	 */
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (deleted ? 1231 : 1237);
		result = prime * result + (filiale ? 1231 : 1237);
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((libelle == null) ? 0 : libelle.hashCode());
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
		Disques other = (Disques) obj;
		if (deleted != other.deleted)
			return false;
		if (filiale != other.filiale)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (libelle == null) {
			if (other.libelle != null)
				return false;
		} else if (!libelle.equals(other.libelle))
			return false;
		return true;
	}

	
}
