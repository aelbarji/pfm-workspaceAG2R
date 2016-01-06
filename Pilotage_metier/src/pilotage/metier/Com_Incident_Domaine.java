package pilotage.metier;

import java.io.Serializable;

public class Com_Incident_Domaine implements Serializable{

	private static final long serialVersionUID = 4140414220473096509L;
	
	private Integer id;
	private Incidents_Gup idIncident;
	private Com_domaine domaine;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public Com_domaine getDomaine() {
		return domaine;
	}

	public void setDomaine(Com_domaine domaine) {
		this.domaine = domaine;
	}

	public Incidents_Gup getIdIncident() {
		return idIncident;
	}

	public void setIdIncident(Incidents_Gup idIncident) {
		this.idIncident = idIncident;
	}
}
