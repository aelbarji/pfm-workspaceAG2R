package pilotage.metier;

import java.io.Serializable;

public class Interlocuteur implements Serializable{

	private static final long serialVersionUID = 4881039419742007929L;

	private Integer id;
	private String  nomService;
	private String  nomComplet;
	private Users idResponsable;
	private String  balService;

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
	 * Return the value associated with the column: NomService
	 */
	public String getNomService() {
		return nomService;
	}
	/**
	 * Set the value related to the column: NomService
	 * @param NomService the NomService value
	 */
	public void setNomService(String nomService) {
		this.nomService = nomService;
	}
	/**
	 * Return the value associated with the column: NomComplet
	 */
	public String getNomComplet() {
		return nomComplet;
	}
	/**
	 * Set the value related to the column: NomComplet
	 * @param NomComplet the NomComplet value
	 */
	public void setNomComplet(String nomComplet) {
		this.nomComplet = nomComplet;
	}
	/**
	 * Return the value associated with the column: IdResponsable
	 */
	public Users getIdResponsable() {
		return idResponsable;
	}
	/**
	 * Set the value related to the column: IdResponsable
	 * @param IdResponsable the IdResponsable value
	 */
	public void setIdResponsable(Users idResponsable) {
		this.idResponsable = idResponsable;
	}
	/**
	 * Return the value associated with the column: BALService
	 */
	public String getBalService() {
		return balService;
	}
	/**
	 * Set the value related to the column: BALService
	 * @param BALService the BALService value
	 */
	public void setBalService(String balService) {
		this.balService = balService;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		if(obj instanceof Interlocuteur)
			return ((Interlocuteur)obj).getId().equals(this.getId());
		else
			return false;
	}
}
