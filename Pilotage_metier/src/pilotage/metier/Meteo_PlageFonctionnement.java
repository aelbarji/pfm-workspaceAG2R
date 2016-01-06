package pilotage.metier;

import java.io.Serializable;
import java.util.Date;

public class Meteo_PlageFonctionnement implements Serializable{

	private static final long serialVersionUID = 4273376087182123208L;

	private Integer id;
	private Date heureDebut;
	private Date heureFin;
	private Heures_Oceor zone;
	private String etatDesire;
	private Meteo_Indicateur indicateur;
	private Meteo_SeuilPlage seuil;
	private int heure_debut;
	private int heure_fin;
	private Date date_creation;
	private Date date_suppression;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getHeureDebut() {
		return heureDebut;
	}
	public void setHeureDebut(Date heureDebut) {
		this.heureDebut = heureDebut;
	}
	public Date getHeureFin() {
		return heureFin;
	}
	public void setHeureFin(Date heureFin) {
		this.heureFin = heureFin;
	}
	public Heures_Oceor getZone() {
		return zone;
	}
	public void setZone(Heures_Oceor zone) {
		this.zone = zone;
	}
	public String getEtatDesire() {
		return etatDesire;
	}
	public void setEtatDesire(String etatDesire) {
		this.etatDesire = etatDesire;
	}
	public Meteo_Indicateur getIndicateur() {
		return indicateur;
	}
	public void setIndicateur(Meteo_Indicateur indicateur) {
		this.indicateur = indicateur;
	}
	public Meteo_SeuilPlage getSeuil() {
		return seuil;
	}
	public void setSeuil(Meteo_SeuilPlage seuil) {
		this.seuil = seuil;
	}
	public int getHeure_debut() {
		return heure_debut;
	}
	public void setHeure_debut(int heure_debut) {
		this.heure_debut = heure_debut;
	}
	public int getHeure_fin() {
		return heure_fin;
	}
	public void setHeure_fin(int heure_fin) {
		this.heure_fin = heure_fin;
	}
	public Date getDate_creation() {
		return date_creation;
	}
	public void setDate_creation(Date date_creation) {
		this.date_creation = date_creation;
	}
	public Date getDate_suppression() {
		return date_suppression;
	}
	public void setDate_suppression(Date date_suppression) {
		this.date_suppression = date_suppression;
	}
}
