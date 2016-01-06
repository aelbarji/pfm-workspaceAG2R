package pilotage.metier;

import java.io.Serializable;

public class Consignes_Type implements Serializable{

	private static final long serialVersionUID = -4644913549679534433L;

	private Integer id;
	private String type;
	private String dossier;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDossier() {
		return dossier;
	}
	public void setDossier(String dossier) {
		this.dossier = dossier;
	}
}
