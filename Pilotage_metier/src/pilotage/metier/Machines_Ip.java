package pilotage.metier;

import java.io.Serializable;

public class Machines_Ip implements Serializable{
	private static final long serialVersionUID = 5121454793037878821L;

	private Integer id;
	private String  ip;
	private String  commentaire;
	
	private Machines_Liste machines_liste;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public Machines_Liste getMachines_liste() {
		return machines_liste;
	}

	public void setMachines_liste(Machines_Liste machines_liste) {
		this.machines_liste = machines_liste;
	}
}
