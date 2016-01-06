package pilotage.metier;

import java.io.Serializable;
import java.util.Date;

public class Incidents implements Serializable{

	private static final long serialVersionUID = 8540656412750366276L;

	private Integer id;
	private Users user;
	private Heures_Oceor oceor;
	private Date    dateDebut;
	private Date    dateDebutGmt;
	private Date    dateFin;
	private Date    dateFinGmt;
	private String  applicatif;
	private String appli_ordonnanceur;
	private String job;
	private String  observation;
	private String  action;
	private String hard_software;
	private Environnement domaine;
	private String  ars;
	private Machines_Liste    machine;
	private String  astreinte;
	private Incidents_Type type;
	private Incidents_Outils idOutil;
	private Integer coupure;
	private Integer resoluPil;
	private Integer hasAstreinte;
	private String  service;
	
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
	 * Set the unique ideIntegerntifier of this class
	 * @param id the new ID
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Return the value associated with the column: heureDebut
	 */
	public Date getDateDebut() {
		return dateDebut;
	}
	/**
	 * Set the value related to the column: heureDebut
	 * @param heureDebut the heureDebut value
	 */
	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}
	/**
	 * Return the value associated with the column: heureDebutGmt
	 */
	public Date getDateDebutGmt() {
		return dateDebutGmt;
	}
	/**
	 * Set the value related to the column: heureDebutGmt
	 * @param heureDebutGmt the heureDebutGmt value
	 */
	public void setDateDebutGmt(Date dateDebutGmt) {
		this.dateDebutGmt = dateDebutGmt;
	}
	/**
	 * Return the value associated with the column: heureFin
	 */
	public Date getDateFin() {
		return dateFin;
	}
	/**
	 * Set the value related to the column: heureFin
	 * @param heureFin the heureFin value
	 */
	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}
	/**
	 * Return the value associated with the column: heureFinGmt
	 */
	public Date getDateFinGmt() {
		return dateFinGmt;
	}
	/**
	 * Set the value related to the column: heureFinGmt
	 * @param heureFinGmt the heureFinGmt value
	 */
	public void setDateFinGmt(Date dateFinGmt) {
		this.dateFinGmt = dateFinGmt;
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
	 * Return the value associated with the column: observation
	 */
	public String getObservation() {
		return observation;
	}
	/**
	 * Set the value related to the column: observation
	 * @param observation the observation value
	 */
	public void setObservation(String observation) {
		this.observation = observation;
	}
	/**
	 * Return the value associated with the column: action
	 */
	public String getAction() {
		return action;
	}
	/**
	 * Set the value related to the column: action
	 * @param action the action value
	 */
	public void setAction(String action) {
		this.action = action;
	}

	/**
	 * Return the value associated with the column: ars
	 */
	public String getArs() {
		return ars;
	}
	/**
	 * Set the value related to the column: ars
	 * @param ars the ars value
	 */
	public void setArs(String ars) {
		this.ars = ars;
	}

	/**
	 * Return the value associated with the column: astreinte
	 */
	public String getAstreinte() {
		return astreinte;
	}
	/**
	 * Set the value related to the column: astreinte
	 * @param astreinte the astreinte value
	 */
	public void setAstreinte(String astreinte) {
		this.astreinte = astreinte;
	}

	/**
	 * Return the value associated with the column: coupure
	 */
	public Integer getCoupure() {
		return coupure;
	}
	/**
	 * Set the value related to the column: coupure
	 * @param coupure the coupure value
	 */
	public void setCoupure(Integer coupure) {
		this.coupure = coupure;
	}
	public Integer getResoluPil() {
		return resoluPil;
	}
	public void setResoluPil(Integer resoluPil) {
		this.resoluPil = resoluPil;
	}
	/**
	 * Return the value associated with the column: service
	 */
	public String getService() {
		return service;
	}
	/**
	 * Set the value related to the column: service
	 * @param service the service value
	 */
	public void setService(String service) {
		this.service = service;
	}
	/**
	 * @return the user
	 */
	public Users getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(Users user) {
		this.user = user;
	}
	/**
	 * @return the oceor
	 */
	public Heures_Oceor getOceor() {
		return oceor;
	}
	/**
	 * @param oceor the oceor to set
	 */
	public void setOceor(Heures_Oceor oceor) {
		this.oceor = oceor;
	}
	/**
	 * @return the domaine
	 */
	public Environnement getDomaine() {
		return domaine;
	}
	/**
	 * @param domaine the domaine to set
	 */
	public void setDomaine(Environnement domaine) {
		this.domaine = domaine;
	}
	/**
	 * @return the machine
	 */
	public Machines_Liste getMachine() {
		return machine;
	}
	/**
	 * @param machine the machine to set
	 */
	public void setMachine(Machines_Liste machine) {
		this.machine = machine;
	}
	/**
	 * @return the type
	 */
	public Incidents_Type getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(Incidents_Type type) {
		this.type = type;
	}
	/**
	 * @return the idOutil
	 */
	public Incidents_Outils getIdOutil() {
		return idOutil;
	}
	/**
	 * @param idOutil the idOutil to set
	 */
	public void setIdOutil(Incidents_Outils idOutil) {
		this.idOutil = idOutil;
	}
	
	public String getHard_software() {
		return hard_software;
	}
	public void setHard_software(String hard_software) {
		this.hard_software = hard_software;
	}
	public Integer getHasAstreinte() {
		return hasAstreinte;
	}
	public void setHasAstreinte(Integer hasAstreinte) {
		this.hasAstreinte = hasAstreinte;
	}
	public String getAppli_ordonnanceur() {
		return appli_ordonnanceur;
	}
	public void setAppli_ordonnanceur(String appli_ordonnanceur) {
		this.appli_ordonnanceur = appli_ordonnanceur;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}


}
