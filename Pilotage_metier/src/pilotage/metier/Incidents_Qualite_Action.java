package pilotage.metier;

import java.io.Serializable;
import java.util.Date;

public class Incidents_Qualite_Action implements Serializable {

	private static final long serialVersionUID = -1906840881108452212L;

	private Integer id;

	private Incidents_Qualite idIncidentsQualite;
	private Date dateAction;
	private String action;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public Incidents_Qualite getIdIncidentsQualite() {
		return idIncidentsQualite;
	}
	public void setIdIncidentsQualite(Incidents_Qualite idIncidentsQualite) {
		this.idIncidentsQualite = idIncidentsQualite;
	}

	public Date getDateAction() {
		return dateAction;
	}
	public void setDateAction(Date dateAction) {
		this.dateAction = dateAction;
	}
	
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
}