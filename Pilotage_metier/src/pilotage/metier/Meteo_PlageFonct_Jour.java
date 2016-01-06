package pilotage.metier;

import java.io.Serializable;

public class Meteo_PlageFonct_Jour implements Serializable{

	private static final long serialVersionUID = -4965947369618386654L;
	
	private Integer id;
	private Meteo_PlageFonctionnement plageFonct;
	private int jour;
	private int ferie;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Meteo_PlageFonctionnement getPlageFonct() {
		return plageFonct;
	}
	public void setPlageFonct(Meteo_PlageFonctionnement plageFonct) {
		this.plageFonct = plageFonct;
	}
	public int getJour() {
		return jour;
	}
	public void setJour(int jour) {
		this.jour = jour;
	}
	public int getFerie() {
		return ferie;
	}
	public void setFerie(int ferie) {
		this.ferie = ferie;
	}

	
}
