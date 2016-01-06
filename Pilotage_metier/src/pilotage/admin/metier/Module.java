package pilotage.admin.metier;

import java.io.Serializable;

public class Module implements Serializable{

	private static final long serialVersionUID = 2255714152257221694L;

	private Integer id;
	private String nom;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
}
