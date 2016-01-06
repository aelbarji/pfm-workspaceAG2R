package pilotage.metier;
/*
 * author by WWei
 * @Groupe HN
 * @18/06/2011
 */
import java.io.Serializable;

public class Services_Liste implements Serializable{
	private static final long serialVersionUID = -5928496144974657029L;

	private Integer id;
	private String nomService;
	private String consigne_meteo;
	private Integer actif;
	
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
	 * Return the value associated with the column: nomService
	 */
	public String getNomService() {
		return nomService;
	}
	/**
	 * Set the value related to the column: nomService
	 * @param nomService the nomService value
	 */
	public void setNomService(String nomService) {
		this.nomService = nomService;
	}
	
	public String getConsigne_meteo() {
		return consigne_meteo;
	}
	public void setConsigne_meteo(String consigne_meteo) {
		this.consigne_meteo = consigne_meteo;
	}
	public Integer getActif() {
		return actif;
	}
	public void setActif(Integer actif) {
		this.actif = actif;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((nomService == null) ? 0 : nomService.hashCode());
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
		Services_Liste other = (Services_Liste) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nomService == null) {
			if (other.nomService != null)
				return false;
		} else if (!nomService.equals(other.nomService))
			return false;
		return true;
	}
	
	
}
