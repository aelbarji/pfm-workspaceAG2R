package pilotage.metier;

import java.io.Serializable;

public class Hardware_Software implements Serializable {

	private static final long serialVersionUID = 175084942262002280L;
	private Integer id;
	private String libelle;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

}
