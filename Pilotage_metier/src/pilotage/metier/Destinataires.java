package pilotage.metier;

import java.io.Serializable;

public class Destinataires implements Serializable{

	private static final long serialVersionUID = -3010569881720958756L;
	
	private Integer id;
	private String nom;
	private String prenom;
	private String mail;
	
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
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
}
