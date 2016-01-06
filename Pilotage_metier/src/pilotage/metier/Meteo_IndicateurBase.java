package pilotage.metier;

import java.io.Serializable;

public class Meteo_IndicateurBase implements Serializable{

	private static final long serialVersionUID = 5449009658567914003L;
	
	private Integer id;
	private Meteo_Meteo meteo;
	private Meteo_Service service;
	private Meteo_Environnement environnement;
	private Meteo_TypeIndicateur typeIndicateur;
	private String format;
	private String etat_desire;
	private Meteo_PlageFonctionnement plage;
	private String heure_debut;
	private String heure_fin;
	private int indic_heure;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Meteo_Meteo getMeteo() {
		return meteo;
	}
	public void setMeteo(Meteo_Meteo meteo) {
		this.meteo = meteo;
	}
	public Meteo_Service getService() {
		return service;
	}
	public void setService(Meteo_Service service) {
		this.service = service;
	}
	public Meteo_Environnement getEnvironnement() {
		return environnement;
	}
	public void setEnvironnement(Meteo_Environnement environnement) {
		this.environnement = environnement;
	}
	public Meteo_TypeIndicateur getTypeIndicateur() {
		return typeIndicateur;
	}
	public void setTypeIndicateur(Meteo_TypeIndicateur typeIndicateur) {
		this.typeIndicateur = typeIndicateur;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public String getEtat_desire() {
		return etat_desire;
	}
	public void setEtat_desire(String etat_desire) {
		this.etat_desire = etat_desire;
	}
	public Meteo_PlageFonctionnement getPlage() {
		return plage;
	}
	public void setPlage(Meteo_PlageFonctionnement plage) {
		this.plage = plage;
	}
	public String getHeure_debut() {
		return heure_debut;
	}
	public void setHeure_debut(String heure_debut) {
		this.heure_debut = heure_debut;
	}
	public String getHeure_fin() {
		return heure_fin;
	}
	public void setHeure_fin(String heure_fin) {
		this.heure_fin = heure_fin;
	}
	public int getIndic_heure() {
		return indic_heure;
	}
	public void setIndic_heure(int indic_heure) {
		this.indic_heure = indic_heure;
	}
	
	
}
