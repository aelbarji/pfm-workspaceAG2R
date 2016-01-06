package pilotage.metier;

import java.io.Serializable;

public class Machines_Liste implements Serializable{
	private static final long serialVersionUID = 6199096218095924292L;

	private Integer id;
	private String  nom;
	private String commentaire;
	
	private Machines_Type id_type;
	private Machines_Site id_site;
	private Domaine_Wind  id_domaine;
	private Interlocuteur id_interlocuteur;
	private Environnement  id_environnement;
	private Machine_Os    id_os;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public Machines_Type getId_type() {
		return id_type;
	}
	public void setId_type(Machines_Type id_type) {
		this.id_type = id_type;
	}
	public Machines_Site getId_site() {
		return id_site;
	}
	public void setId_site(Machines_Site id_site) {
		this.id_site = id_site;
	}
	public Domaine_Wind getId_domaine() {
		return id_domaine;
	}
	public void setId_domaine(Domaine_Wind id_domaine) {
		this.id_domaine = id_domaine;
	}
	public Interlocuteur getId_interlocuteur() {
		return id_interlocuteur;
	}
	public void setId_interlocuteur(Interlocuteur id_interlocuteur) {
		this.id_interlocuteur = id_interlocuteur;
	}
	public Environnement getId_environnement() {
		return id_environnement;
	}
	public void setId_environnement(Environnement id_environnement) {
		this.id_environnement = id_environnement;
	}
	public Machine_Os getId_os() {
		return id_os;
	}
	public void setId_os(Machine_Os id_os) {
		this.id_os = id_os;
	}
	public String getCommentaire() {
		return commentaire;
	}
	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}
}
