package pilotage.bean;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class IncidentsJSON {

	private String id_requete;
	private String date_modification;
	private String date_creation;
	private String resume;
	private String etat;
	private String priorite;
	private String urgence;
	private String impact;
	private String nb_relance;
	
	public IncidentsJSON() {
		super();
	}

	public String getId_requete() {
		return id_requete;
	}

	public void setId_requete(String id_requete) {
		this.id_requete = id_requete;
	}

	public String getDate_modification() {
		return date_modification;
	}

	public void setDate_modification(String date_modification) {
		this.date_modification = date_modification;
	}

	public String getDate_creation() {
		return date_creation;
	}

	public void setDate_creation(String date_creation) {
		this.date_creation = date_creation;
	}

	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public String getPriorite() {
		return priorite;
	}

	public void setPriorite(String priorite) {
		this.priorite = priorite;
	}

	public String getUrgence() {
		return urgence;
	}

	public void setUrgence(String urgence) {
		this.urgence = urgence;
	}

	public String getImpact() {
		return impact;
	}

	public void setImpact(String impact) {
		this.impact = impact;
	}

	public String getNb_relance() {
		return nb_relance;
	}

	public void setNb_relance(String nb_relance) {
		this.nb_relance = nb_relance;
	}
}
