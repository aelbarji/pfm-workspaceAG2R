package pilotage.bean;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ChangementJSON {

	private String id_changement;
	private String resume;
	private String priorite;
	private String etat;
	private String debut;
	private String fin;
	private String demandeur;
	private String valideur;

	public ChangementJSON() {
		super();
	}

	public String getId_changement() {
		return id_changement;
	}

	public void setId_changement(String id_changement) {
		this.id_changement = id_changement;
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

	public String getDebut() {
		return debut;
	}

	public void setDebut(String debut) {
		this.debut = debut;
	}

	public String getFin() {
		return fin;
	}

	public void setFin(String fin) {
		this.fin = fin;
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
}
