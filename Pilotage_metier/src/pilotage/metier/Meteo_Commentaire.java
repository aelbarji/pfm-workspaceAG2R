package pilotage.metier;

import java.io.Serializable;
import java.util.Date;

public class Meteo_Commentaire implements Serializable{

	private static final long serialVersionUID = -3801885612790376211L;

	private Integer id;
	private Meteo_Meteo meteo;
	private String commentaire;
	private Date date;
	private Date horaire;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Meteo_Meteo getMeteo() {
		return meteo;
	}
	public void setMeteo(Meteo_Meteo meteo) {
		this.meteo = meteo;
	}
	public String getCommentaire() {
		return commentaire;
	}
	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Date getHoraire() {
		return horaire;
	}
	public void setHoraire(Date horaire) {
		this.horaire = horaire;
	}
}
