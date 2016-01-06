package pilotage.metier;

import java.io.Serializable;

public class Com_Incident_Detection implements Serializable{

	private static final long serialVersionUID = -4925259735324064343L;

	private Integer id;
	private Incidents_Gup idIncident;
	private Destinataires idDetection;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Incidents_Gup getIdIncident() {
		return idIncident;
	}
	
	public void setIdIncident(Incidents_Gup idIncident) {
		this.idIncident = idIncident;
	}
	
	public Destinataires getIdDetection() {
		return idDetection;
	}
	
	public void setIdDetection(Destinataires idDetection) {
		this.idDetection = idDetection;
	}
}
