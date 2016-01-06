package pilotage.metier;

import java.io.Serializable;

public class Domaine_Wind_Login implements Serializable{

	private static final long serialVersionUID = 8833994600519236991L;

	private Integer id;
	private Domaine_Wind domaine;
	private String login;
	private String password;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Domaine_Wind getDomaine() {
		return domaine;
	}
	public void setDomaine(Domaine_Wind domaine) {
		this.domaine = domaine;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
