package pilotage.user.profil;

import pilotage.database.historique.HistoriqueDatabaseService;
import pilotage.database.user.profil.management.UserProfilDatabaseService;
import pilotage.database.users.management.UsersDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;
import pilotage.service.convertor.MD5Convertor;

public class ModifyUserProfilAction extends AbstractAction {

	private static final long serialVersionUID = -685066947627256421L;
	
	private Integer userId;
	private String nom;
	private String prenom;
	private String email;
	private String oldPassword;
	private String newPassword;
	private String confirmPassword;

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
	
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}
	
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	@Override
	protected boolean validateMetier() {
		if (!oldPassword.isEmpty()){
			Users userprofil = (Users)session.get(PilotageConstants.USER_LOGGED);
			String pwd = userprofil.getPassword();
			
			String md5pwd = MD5Convertor.crypt(oldPassword, (String)session.get(PilotageConstants.CLE_ENCODAGE));
			if (! md5pwd.equals(pwd)){
				error = getText("user.profil.error.oldpassword");
				return false;
			}
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected String executeMetier() {
		try{
			String historique = "modification de ";
			Users u = UsersDatabaseService.get(userId);
			if (!nom.equals(u.getNom())) {
				historique += "nom, ";
			}
			if (!prenom.equals(u.getPrenom())) {
				historique += "prenom, ";
			}
			if (!email.equals(u.getEmail())) {
				historique += "email, ";
			}
			if (!oldPassword.isEmpty()){
				String md5NewPassword = MD5Convertor.crypt(newPassword, (String)session.get(PilotageConstants.CLE_ENCODAGE));
				UserProfilDatabaseService.modifyWithPwd(userId, nom, prenom, email, md5NewPassword);
				historique += "password, ";
			}
			else{
				UserProfilDatabaseService.modifyWithoutPwd(userId, nom, prenom, email);
			}
			
			HistoriqueDatabaseService.create(null, historique + "dans le profil utilisateur " + userId, (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_USERS);

			Users userModify = UserProfilDatabaseService.getUserById(userId);
			session.put(PilotageConstants.USER_LOGGED, userModify); 
			info = getText("user.profil.modification.valide");
			
			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Modification du profil utilisateur - ", e);
			return ERROR;
		}
	}
}
