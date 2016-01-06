package pilotage.metier;

import java.io.Serializable;

public class Meteo_Environnement_Place implements Serializable{

	private static final long serialVersionUID = -5956359115657266557L;

	private Integer id;
	private Meteo_Meteo meteo;
	private Meteo_Environnement environnement;
	private int place;
	
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
	public Meteo_Environnement getEnvironnement() {
		return environnement;
	}
	public void setEnvironnement(Meteo_Environnement environnement) {
		this.environnement = environnement;
	}
	public int getPlace() {
		return place;
	}
	public void setPlace(int place) {
		this.place = place;
	}
}
