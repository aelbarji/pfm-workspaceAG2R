package pilotage.admin.metier;

import pilotage.metier.Checklist_Status;

public class Checklist_color {
	private Integer id;
	private Integer checklist_status;
	private String couleur;
	private String retard1;
	private String retard2;
	private Checklist_Status statut;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getChecklist_status() {
		return checklist_status;
	}
	public void setChecklist_status(Integer checklist_status) {
		this.checklist_status = checklist_status;
	}
	public String getCouleur() {
		return couleur;
	}
	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}
	public String getRetard1() {
		return retard1;
	}
	public void setRetard1(String retard1) {
		this.retard1 = retard1;
	}
	public String getRetard2() {
		return retard2;
	}
	public void setRetard2(String retard2) {
		this.retard2 = retard2;
	}
	public Checklist_Status getStatut() {
		return statut;
	}
	public void setStatut(Checklist_Status statut) {
		this.statut = statut;
	}
}
