package pilotage.metier;

import java.io.Serializable;
import java.util.Date;

public class Astreinte_Nombre_Appel_View implements Serializable{

	private static final long serialVersionUID = 2845237421181507464L;
	private Integer id;
	private Astreinte astreinte;
	private Incidents incident;
	private Date mindate;
	private Integer nbAppel;
	private Astreinte_Appel_Statut statut;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Astreinte getAstreinte() {
		return astreinte;
	}
	public void setAstreinte(Astreinte astreinte) {
		this.astreinte = astreinte;
	}
	public Incidents getIncident() {
		return incident;
	}
	public void setIncident(Incidents incident) {
		this.incident = incident;
	}
	public Date getMindate() {
		return mindate;
	}
	public void setMindate(Date mindate) {
		this.mindate = mindate;
	}
	public Integer getNbAppel() {
		return nbAppel;
	}
	public void setNbAppel(Integer nbAppel) {
		this.nbAppel = nbAppel;
	}
	public Astreinte_Appel_Statut getStatut() {
		return statut;
	}
	public void setStatut(Astreinte_Appel_Statut statut) {
		this.statut = statut;
	}

}
