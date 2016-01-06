package pilotage.metier;

import java.io.Serializable;


public class Appli_Entite implements Serializable {

	private static final long serialVersionUID = -5595905906499403509L;

	// primary key
	private Integer id;

	// fields
	private Applicatifs_Liste idApplication;
	private Interlocuteur idEntite;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Applicatifs_Liste getIdApplication() {
		return idApplication;
	}
	public void setIdApplication(Applicatifs_Liste idApplication) {
		this.idApplication = idApplication;
	}
	public Interlocuteur getIdEntite() {
		return idEntite;
	}
	public void setIdEntite(Interlocuteur idEntite) {
		this.idEntite = idEntite;
	}
}