package pilotage.metier;
/*
 * author by WWei
 * @Groupe HN
 * @18/06/2011
 */
import java.io.Serializable;

public class Users implements Serializable {
	private static final long serialVersionUID = -936725572729285422L;

	private Integer id;

	private String login;
	private String password;
	private String nom;
	private String prenom;
	private Integer statut;
	private String email;
	private Integer myChecklist;
	private Integer refresshChecklist;
	private String filtreEnv;
	private Integer autoXguard;
	private Boolean actif;
	private Heures_Oceor timezone;
	private Integer nrPerpage;
	
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
	 * Return the value associated with the column: login
	 */
	public String getLogin() {
		return login;
	}
	/**
	 * Set the value related to the column: login
	 * @param login the login value
	 */
	public void setLogin(String login) {
		this.login = login;
	}
	/**
	 * Return the value associated with the column: password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * Set the value related to the column: password
	 * @param password the password value
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * Return the value associated with the column: nom
	 */
	public String getNom() {
		return nom;
	}
	/**
	 * Set the value related to the column: nom
	 * @param nom the nom value
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	/**
	 * Return the value associated with the column: prenom
	 */
	public String getPrenom() {
		return prenom;
	}
	/**
	 * Set the value related to the column: prenom
	 * @param prenom the prenom value
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	/**
	 * Return the value associated with the column: statut
	 */
	public Integer getStatut() {
		return statut;
	}
	/**
	 * Set the value related to the column: statut
	 * @param statut the statut value
	 */
	public void setStatut(Integer statut) {
		this.statut = statut;
	}
	/**
	 * Return the value associated with the column: email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * Set the value related to the column: email
	 * @param email the email value
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * Return the value associated with the column: myChecklist
	 */
	public Integer getMyChecklist() {
		return myChecklist;
	}
	/**
	 * Set the value related to the column: myChecklist
	 * @param myChecklist the myChecklist value
	 */
	public void setMyChecklist(Integer myChecklist) {
		this.myChecklist = myChecklist;
	}
	/**
	 * Return the value associated with the column: refresshChecklist
	 */
	public Integer getRefresshChecklist() {
		return refresshChecklist;
	}
	/**
	 * Set the value related to the column: refresshChecklist
	 * @param refresshChecklist the refresshChecklist value
	 */
	public void setRefresshChecklist(Integer refresshChecklist) {
		this.refresshChecklist = refresshChecklist;
	}
	/**
	 * Return the value associated with the column: filtreEnv
	 */
	public String getFiltreEnv() {
		return filtreEnv;
	}
	/**
	 * Set the value related to the column: filtreEnv
	 * @param filtreEnv the filtreEnv value
	 */
	public void setFiltreEnv(String filtreEnv) {
		this.filtreEnv = filtreEnv;
	}
	/**
	 * Return the value associated with the column: AutoXguard
	 */
	public Integer getAutoXguard() {
		return autoXguard;
	}
	/**
	 * Set the value related to the column: AutoXguard
	 * @param AutoXguard the AutoXguard value
	 */
	public void setAutoXguard(Integer autoXguard) {
		this.autoXguard = autoXguard;
	}
	public Boolean getActif() {
		return actif;
	}
	public void setActif(Boolean actif) {
		this.actif = actif;
	}
	public Heures_Oceor getTimezone() {
		return timezone;
	}
	public void setTimezone(Heures_Oceor timezone) {
		this.timezone = timezone;
	}
	public Integer getNrPerpage() {
		return nrPerpage;
	}
	public void setNrPerpage(Integer nrPerpage) {
		this.nrPerpage = nrPerpage;
	}
	

}
