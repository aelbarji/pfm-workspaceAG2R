package pilotage.user.profil;

import pilotage.framework.AbstractAction;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;

public class ShowModifyUserProfilAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	private int userId;
	
	private String nom;
	private String prenom;
	private String email;
	private String password;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}
	
	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		
		try {
			Users userprofil = new Users();	
			userprofil = (Users)session.get(PilotageConstants.USER_LOGGED);
		
			userId = userprofil.getId();
			nom = userprofil.getNom();
			prenom = userprofil.getPrenom();
			email = userprofil.getEmail();
			password = userprofil.getPassword();
			
			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Redirection vers la modification de profil - ", e);
			return ERROR;
		}	
	}
}
