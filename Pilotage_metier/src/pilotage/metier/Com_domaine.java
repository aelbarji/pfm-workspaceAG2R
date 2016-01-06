package pilotage.metier;

import java.io.Serializable;

public class Com_domaine implements Serializable{


	private static final long serialVersionUID = 5380112305597870585L;
	
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
