package pilotage.metier;

import java.io.Serializable;
import java.util.Date;

public class Incidents_Itsm implements Serializable {

	private static final long serialVersionUID = 7056043055945366591L;

	// primary key
	private Integer id;

	// fields
	private String idRequete;
	private Date dateModification;
	private Date dateCreation;
	private String resume;
	private String etat;
	private String priorite;
	private String urgence;
	private String impact;
	private Integer nbRelance;

	public Incidents_Itsm() {
		super();
	}

	public Incidents_Itsm(String idRequete, Date dateModification,
			Date dateCreation, String resume, String etat, String priorite,
			String urgence, String impact, Integer nbRelance) {
		super();
		this.idRequete = idRequete;
		this.dateModification = dateModification;
		this.dateCreation = dateCreation;
		this.resume = resume;
		this.etat = etat;
		this.priorite = priorite;
		this.urgence = urgence;
		this.impact = impact;
		this.nbRelance = nbRelance;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIdRequete() {
		return idRequete;
	}

	public void setIdRequete(String idRequete) {
		this.idRequete = idRequete;
	}

	public Date getDateModification() {
		return dateModification;
	}

	public void setDateModification(Date dateModification) {
		this.dateModification = dateModification;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
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

	public Integer getNbRelance() {
		return nbRelance;
	}

	public void setNbRelance(Integer nbRelance) {
		this.nbRelance = nbRelance;
	}

	@Override
	public String toString() {
		return "Incidents_Itsm [id=" + id + ", idRequete=" + idRequete
				+ ", dateModification=" + dateModification + ", dateCreation="
				+ dateCreation + ", resume=" + resume + ", etat=" + etat
				+ ", priorite=" + priorite + ", urgence=" + urgence
				+ ", impact=" + impact + ", nbRelance=" + nbRelance + "]";
	}

}
