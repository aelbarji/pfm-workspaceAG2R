package pilotage.metier;

import java.io.Serializable;

public class Astreinte_Appel_Statut implements Serializable{

	private static final long serialVersionUID = -1071897195324906921L;

	private Integer id;
	private String statut;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getStatut() {
		return statut;
	}
	public void setStatut(String statut) {
		this.statut = statut;
	}
	
}
