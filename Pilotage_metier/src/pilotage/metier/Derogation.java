package pilotage.metier;

import java.io.Serializable;
import java.util.Date;

public class Derogation implements Serializable{

	private static final long serialVersionUID = -7292097228708159419L;

	private Integer id;

	private Derogation_Type idType;
	private Users idNomDemandeur;
	private Users idNomCreateur;
	private String  telephone;
	private Applicatifs_Liste idAppli;
	private String  service;
	private String  realisateur;
	private String  description;
	private String  clientTouche;
	private String  serviceImpact;
	private String  numars;
	private Integer tp;
	private Date  timeDemande;
	private String  heureDemande;
	private Integer etude;
	private Integer retourArriere;
	private Derogation_Type_Changement  typeChangement;
	private Integer externe;
	private Integer recette;
	private String  justificatif;
	private Derogation_Etat etat;
	private String  valider;
	private String  message;
	private Date date_creation;
	
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
	 * Return the value associated with the column: idType
	 */
	public Derogation_Type getIdType() {
		return idType;
	}
	/**
	 * Set the value related to the column: idType
	 * @param idType the idType value
	 */
	public void setIdType(Derogation_Type idType) {
		this.idType = idType;
	}
	/**
	 * Return the value associated with the column: idNomDemandeur
	 */
	public Users getIdNomDemandeur() {
		return idNomDemandeur;
	}
	/**
	 * Set the value related to the column: idNomDemandeur
	 * @param idNomDemandeur the idNomDemandeur value
	 */
	public void setIdNomDemandeur(Users idNomDemandeur) {
		this.idNomDemandeur = idNomDemandeur;
	}
	/**
	 * Return the value associated with the column: idNomCreateur
	 */
	public Users getIdNomCreateur() {
		return idNomCreateur;
	}
	/**
	 * Set the value related to the column: idNomCreateur
	 * @param idNomCreateur the date idNomCreateur
	 */
	public void setIdNomCreateur(Users idNomCreateur) {
		this.idNomCreateur = idNomCreateur;
	}
	/**
	 * Return the value associated with the column: telephone
	 */
	public String getTelephone() {
		return telephone;
	}
	/**
	 * Set the value related to the column: telephone
	 * @param telephone the telephone value
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	/**
	 * Return the value associated with the column: idAppli
	 */
	public Applicatifs_Liste getIdAppli() {
		return idAppli;
	}
	/**
	 * Set the value related to the column: idAppli
	 * @param idAppli the idAppli value
	 */
	public void setIdAppli(Applicatifs_Liste idAppli) {
		this.idAppli = idAppli;
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
	 * Return the value associated with the column: realisateur
	 */
	public String getRealisateur() {
		return realisateur;
	}
	/**
	 * Set the value related to the column: realisateur
	 * @param realisateur the realisateur value
	 */
	public void setRealisateur(String realisateur) {
		this.realisateur = realisateur;
	}
	/**
	 * Return the value associated with the column: description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * Set the value related to the column: description
	 * @param description the description value
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * Return the value associated with the column: clientTouche
	 */
	public String getClientTouche() {
		return clientTouche;
	}
	/**
	 * Set the value related to the column: clientTouche
	 * @param clientTouche the clientTouche value
	 */
	public void setClientTouche(String clientTouche) {
		this.clientTouche = clientTouche;
	}
	/**
	 * Return the value associated with the column: serviceImpact
	 */
	public String getServiceImpact() {
		return serviceImpact;
	}
	/**
	 * Set the value related to the column: serviceImpact
	 * @param serviceImpact the serviceImpact value
	 */
	public void setServiceImpact(String serviceImpact) {
		this.serviceImpact = serviceImpact;
	}
	/**
	 * Return the value associated with the column: numars
	 */
	public String getNumars() {
		return numars;
	}
	/**
	 * Set the value related to the column: numars
	 * @param numars the numars value
	 */
	public void setNumars(String numars) {
		this.numars = numars;
	}
	/**
	 * Return the value associated with the column: tp
	 */
	public Integer getTp() {
		return tp;
	}
	/**
	 * Set the value related to the column: tp
	 * @param tp the tp value
	 */
	public void setTp(Integer tp) {
		this.tp = tp;
	}
	/**
	 * Return the value associated with the column: timeDemande
	 */
	public Date getTimeDemande() {
		return timeDemande;
	}
	/**
	 * Set the value related to the column: timeDemande
	 * @param timeDemande the timeDemande value
	 */
	public void setTimeDemande(Date timeDemande) {
		this.timeDemande = timeDemande;
	}
	/**
	 * Return the value associated with the column: heureDemande
	 */
	public String getHeureDemande() {
		return heureDemande;
	}
	/**
	 * Set the value related to the column: heureDemande
	 * @param heureDemande the heureDemande value
	 */
	public void setHeureDemande(String heureDemande) {
		this.heureDemande = heureDemande;
	}
	/**
	 * Return the value associated with the column: etude
	 */
	public Integer getEtude() {
		return etude;
	}
	/**
	 * Set the value related to the column: etude
	 * @param etude the etude value
	 */
	public void setEtude(Integer etude) {
		this.etude = etude;
	}
	/**
	 * Return the value associated with the column: retourArriere
	 */
	public Integer getRetourArriere() {
		return retourArriere;
	}
	/**
	 * Set the value related to the column: retourArriere
	 * @param retourArriere the retourArriere value
	 */
	public void setRetourArriere(Integer retourArriere) {
		this.retourArriere = retourArriere;
	}
	/**
	 * Return the value associated with the column: typeChangement
	 */
	public Derogation_Type_Changement getTypeChangement() {
		return typeChangement;
	}
	/**
	 * Set the value related to the column: typeChangement
	 * @param typeChangement the typeChangement value
	 */
	public void setTypeChangement(Derogation_Type_Changement typeChangement) {
		this.typeChangement = typeChangement;
	}
	/**
	 * Return the value associated with the column: externe
	 */
	public Integer getExterne() {
		return externe;
	}
	/**
	 * Set the value related to the column: externe
	 * @param externe the externe value
	 */
	public void setExterne(Integer externe) {
		this.externe = externe;
	}
	/**
	 * Return the value associated with the column: recette
	 */
	public Integer getRecette() {
		return recette;
	}
	/**
	 * Set the value related to the column: recette
	 * @param recette the recette value
	 */
	public void setRecette(Integer recette) {
		this.recette = recette;
	}
	/**
	 * Return the value associated with the column: justificatif
	 */
	public String getJustificatif() {
		return justificatif;
	}
	/**
	 * Set the value related to the column: justificatif
	 * @param justificatif the justificatif value
	 */
	public void setJustificatif(String justificatif) {
		this.justificatif = justificatif;
	}
	/**
	 * Return the value associated with the column: etat
	 */
	public Derogation_Etat getEtat() {
		return etat;
	}
	/**
	 * Set the value related to the column: etat
	 * @param etat the etat value
	 */
	public void setEtat(Derogation_Etat etat) {
		this.etat = etat;
	}
	/**
	 * Return the value associated with the column: valider
	 */
	public String getValider() {
		return valider;
	}
	/**
	 * Set the value related to the column: valider
	 * @param valider the valider value
	 */
	public void setValider(String valider) {
		this.valider = valider;
	}
	/**
	 * Return the value associated with the column: message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * Set the value related to the column: message
	 * @param message the message value
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * Return the value associated with the column: date_creation
	 */
	public Date getDate_creation() {
		return date_creation;
	}
	/**
	 * Set the value related to the column: date_creation
	 * @param message the message value
	 */
	public void setDate_creation(Date date_creation) {
		this.date_creation = date_creation;
	}
	
}
