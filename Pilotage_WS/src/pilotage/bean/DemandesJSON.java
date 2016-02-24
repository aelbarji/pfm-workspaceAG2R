package pilotage.bean;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DemandesJSON {

	private String type;
	private String nom;
	private String environnement;
	private String heure_reception;
	private String heure_realisation;
	private String description;
	private String id_demande;
	private String numero_obs;
	private String nom_emetteur;
	private String etat;
	private String criticite;

	public DemandesJSON() {
		super();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getEnvironnement() {
		return environnement;
	}

	public void setEnvironnement(String environnement) {
		this.environnement = environnement;
	}

	public String getHeure_reception() {
		return heure_reception;
	}

	public void setHeure_reception(String heure_reception) {
		this.heure_reception = heure_reception;
	}

	public String getHeure_realisation() {
		return heure_realisation;
	}

	public void setHeure_realisation(String heure_realisation) {
		this.heure_realisation = heure_realisation;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getId_demande() {
		return id_demande;
	}

	public void setId_demande(String id_demande) {
		this.id_demande = id_demande;
	}

	public String getNumero_obs() {
		return numero_obs;
	}

	public void setNumero_obs(String numero_obs) {
		this.numero_obs = numero_obs;
	}

	public String getNom_emetteur() {
		return nom_emetteur;
	}

	public void setNom_emetteur(String nom_emetteur) {
		this.nom_emetteur = nom_emetteur;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public String getCriticite() {
		return criticite;
	}

	public void setCriticite(String criticite) {
		this.criticite = criticite;
	}

}
