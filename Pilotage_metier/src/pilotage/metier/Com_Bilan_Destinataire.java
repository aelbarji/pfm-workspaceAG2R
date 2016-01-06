package pilotage.metier;

import java.io.Serializable;

public class Com_Bilan_Destinataire implements Serializable {

	private static final long serialVersionUID = -2805365215307044368L;
	private Integer id;
	private Destinataires destinataire;
	private Integer cc;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public Destinataires getDestinataire() {
		return destinataire;
	}

	public void setDestinataire(Destinataires destinataire) {
		this.destinataire = destinataire;
	}

	public Integer getCc() {
		return cc;
	}

	public void setCc(Integer cc) {
		this.cc = cc;
	}

}
