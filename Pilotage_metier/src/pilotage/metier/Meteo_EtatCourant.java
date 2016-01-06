package pilotage.metier;

import java.io.Serializable;
import java.util.Date;

public class Meteo_EtatCourant implements Serializable{

	private static final long serialVersionUID = -725092272956483801L;

	private Integer id;
	private Meteo_Indicateur indicateur;
	private String etat;
	private String commentaire;
	private Date date;
	private Date horaire;
	private Date heure_saisie;
	private Date heure_debut;
	private Date heure_fin;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Meteo_Indicateur getIndicateur() {
		return indicateur;
	}
	public void setIndicateur(Meteo_Indicateur indicateur) {
		this.indicateur = indicateur;
	}
	public String getEtat() {
		return etat;
	}
	public void setEtat(String etat) {
		this.etat = etat;
	}
	public String getCommentaire() {
		return commentaire;
	}
	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Date getHoraire() {
		return horaire;
	}
	public void setHoraire(Date horaire) {
		this.horaire = horaire;
	}
	public Date getHeure_saisie() {
		return heure_saisie;
	}
	public void setHeure_saisie(Date heure_saisie) {
		this.heure_saisie = heure_saisie;
	}
	public Date getHeure_debut() {
		return heure_debut;
	}
	public void setHeure_debut(Date heure_debut) {
		this.heure_debut = heure_debut;
	}
	public Date getHeure_fin() {
		return heure_fin;
	}
	public void setHeure_fin(Date heure_fin) {
		this.heure_fin = heure_fin;
	}
	
}
