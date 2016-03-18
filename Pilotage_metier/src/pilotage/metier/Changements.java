package pilotage.metier;

import java.io.Serializable;
import java.util.Date;

public class Changements implements Serializable {

	private static final long serialVersionUID = -4159603616029914648L;

	// primary key
	private Integer id;

	// fields
	private String resume;
	private String priorite;
	private String etat;
	private Date dateDebut;
	private Date dateFin;
	private String demandeur;
	private String valideur;
	private String idChangement;

	public Changements(String resume, String priorite, String etat,
			Date dateDebut, Date dateFin, String demandeur, String valideur, String idChangement) {
		this.resume = resume;
		this.priorite = priorite;
		this.etat = etat;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.demandeur = demandeur;
		this.valideur = valideur;
		this.idChangement = idChangement;
	}

	public Changements() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

	public String getPriorite() {
		return priorite;
	}

	public void setPriorite(String priorite) {
		this.priorite = priorite;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public String getDemandeur() {
		return demandeur;
	}

	public void setDemandeur(String demandeur) {
		this.demandeur = demandeur;
	}

	public String getValideur() {
		return valideur;
	}

	public void setValideur(String valideur) {
		this.valideur = valideur;
	}

	public String getIdChangement() {
		return idChangement;
	}

	public void setIdChangement(String idChangement) {
		this.idChangement = idChangement;
	}

	@Override
	public String toString() {
		return "Changements [id=" + id + ", resume=" + resume + ", priorite="
				+ priorite + ", etat=" + etat + ", dateDebut=" + dateDebut
				+ ", dateFin=" + dateFin + ", demandeur=" + demandeur
				+ ", valideur=" + valideur + ", idChangement=" + idChangement +"]";
	}
}
