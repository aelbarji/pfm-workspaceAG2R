package pilotage.metier;

import java.io.Serializable;

import org.joda.time.DateTime;

public class TdB_Commentaire implements Serializable{

	private static final long serialVersionUID = 4089659777926937809L;

	private Integer id;
	
	private DateTime date;
	private Environnement environnement;
	private String commentaire;
	private Users pilote;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public DateTime getDate() {
		return date;
	}
	public void setDate(DateTime date) {
		this.date = date;
	}
	public Environnement getEnvironnement() {
		return environnement;
	}
	public void setEnvironnement(Environnement environnement) {
		this.environnement = environnement;
	}
	public String getCommentaire() {
		return commentaire;
	}
	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}
	public Users getPilote() {
		return pilote;
	}
	public void setPilote(Users pilote) {
		this.pilote = pilote;
	}
}
