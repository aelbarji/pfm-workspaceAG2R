package pilotage.metier;
/*
 * author by WWei
 * @Groupe HN
 * @18/06/2011
 */
import java.io.Serializable;

public class Environnement_Entite implements Serializable{

	private static final long serialVersionUID = -3356186602875987445L;

	private Integer id;

	private Integer idEnvironnement;
	private Integer idEntite;
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
	 * Return the value associated with the column: idEnvironnement
	 */
	public Integer getIdEnvironnement() {
		return idEnvironnement;
	}
	/**
	 * Set the value related to the column: idEnvironnement
	 * @param idEnvironnement the idEnvironnement value
	 */
	public void setIdEnvironnement(Integer idEnvironnement) {
		this.idEnvironnement = idEnvironnement;
	}
	/**
	 * Return the value associated with the column: idEntite
	 */
	public Integer getIdEntite() {
		return idEntite;
	}
	/**
	 * Set the value related to the column: idEntite
	 * @param idEntite the idEntite value
	 */
	public void setIdEntite(Integer idEntite) {
		this.idEntite = idEntite;
	}
}
