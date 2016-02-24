package pilotage.metier;

import java.io.Serializable;
import java.util.Date;

public class Checklist_Base implements Serializable {

	private static final long serialVersionUID = 2112668460860624955L;

	private Integer id;

	private String tache;
	private Checklist_Etat etat;
	private Environnement environnement;
	private Date dateDebut;
	private Checklist_Criticite criticite;
	private Boolean actif;
	
	private String typeDemande;
	private Date heureReception;
	private String nomEmetteur;
	private String numeroObs;
	private String descriptionMail;
	private String descriptionObs;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTache() {
		return tache;
	}
	public void setTache(String tache) {
		this.tache = tache;
	}
	public Checklist_Etat getEtat() {
		return etat;
	}
	public void setEtat(Checklist_Etat etat) {
		this.etat = etat;
	}
	public Environnement getEnvironnement() {
		return environnement;
	}
	public void setEnvironnement(Environnement environnement) {
		this.environnement = environnement;
	}
	public Date getDateDebut() {
		return dateDebut;
	}
	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}
	public Checklist_Criticite getCriticite() {
		return criticite;
	}
	public void setCriticite(Checklist_Criticite criticite) {
		this.criticite = criticite;
	}
	public Boolean getActif() {
		return actif;
	}
	public void setActif(Boolean actif) {
		this.actif = actif;
	}
	public String getTypeDemande() {
		return typeDemande;
	}
	public void setTypeDemande(String typeDemande) {
		this.typeDemande = typeDemande;
	}
	public Date getHeureReception() {
		return heureReception;
	}
	public void setHeureReception(Date dateTime) {
		this.heureReception = dateTime;
	}
	public String getNomEmetteur() {
		return nomEmetteur;
	}
	public void setNomEmetteur(String nomEmetteur) {
		this.nomEmetteur = nomEmetteur;
	}
	public String getNumeroObs() {
		return numeroObs;
	}
	public void setNumeroObs(String numeroObs) {
		this.numeroObs = numeroObs;
	}
	public String getDescriptionMail() {
		return descriptionMail;
	}
	public void setDescriptionMail(String descriptionMail) {
		this.descriptionMail = descriptionMail;
	}
	public String getDescriptionObs() {
		return descriptionObs;
	}
	public void setDescriptionObs(String descriptionObs) {
		this.descriptionObs = descriptionObs;
	}
	
}