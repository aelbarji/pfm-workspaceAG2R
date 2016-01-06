package pilotage.admin.metier;

import java.io.Serializable;

public class Profil_Droits implements Serializable{

	private static final long serialVersionUID = 4265898070517692617L;
	private Integer id;
	private Profil id_profil;
	private Droits_Liste id_droits;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Profil getId_profil() {
		return id_profil;
	}
	public void setId_profil(Profil id_profil) {
		this.id_profil = id_profil;
	}
	public Droits_Liste getId_droits() {
		return id_droits;
	}
	public void setId_droits(Droits_Liste id_droits) {
		this.id_droits = id_droits;
	}
}
