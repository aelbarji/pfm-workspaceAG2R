package pilotage.metier;

import java.io.Serializable;

public class Meteo_EtatPossible implements Serializable{
	
	private static final long serialVersionUID = 1003732635321385048L;
	
	private Integer id;
	private String libelle_etat;
	private String couleur;
	private String definition;
	private Integer impact;
	
	public String getLibelle_etat() {
		return libelle_etat;
	}
	public void setLibelle_etat(String libelle_etat) {
		this.libelle_etat = libelle_etat;
	}
	public String getCouleur() {
		return couleur;
	}
	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}
	public String getDefinition() {
		return definition;
	}
	public void setDefinition(String definition) {
		this.definition = definition;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getImpact() {
		return impact;
	}
	public void setImpact(Integer impact) {
		this.impact = impact;
	}
}
