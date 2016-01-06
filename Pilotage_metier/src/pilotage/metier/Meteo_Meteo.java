package pilotage.metier;

import java.io.Serializable;

public class Meteo_Meteo implements Serializable{

	private static final long serialVersionUID = 2978698117191807872L;
	// primary key
	private Integer id;
	// fields
	private String nom_meteo;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNom_meteo() {
		return nom_meteo;
	}

	public void setNom_meteo(String nom_meteo) {
		this.nom_meteo = nom_meteo;
	}

}
