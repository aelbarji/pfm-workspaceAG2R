package pilotage.login;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import pilotage.database.login.LoginDatabaseService;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;
import pilotage.service.convertor.MD5Convertor;
import pilotage.service.mail.MailService;
import pilotage.service.random.RandomGenerator;

import com.opensymphony.xwork2.ActionSupport;

public class PasswordReinitAction extends ActionSupport implements SessionAware {

	private static final long serialVersionUID = -1520629237811325578L;
	private static final String OK = "ok";
	private static final String ERROR = "error";	
	
	private String username;
	
	@SuppressWarnings("rawtypes")
	protected Map session;

	@SuppressWarnings("rawtypes")
	public Map getSession() {
		return session;
	}

	@SuppressWarnings("rawtypes")
	public void setSession(Map session) {
		this.session = session;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	
	public String execute() throws Exception {
		//test si l'utilisateur existe
		Users user = LoginDatabaseService.getUserByLogin(username);
		if(user == null){
			this.addActionError(getText("initialize.user.inexistant"));
			return ERROR;
		}
		
		//modification du mot de passe
		String password = RandomGenerator.getRandomString();
		String md5pwd = MD5Convertor.crypt(password, (String)session.get(PilotageConstants.CLE_ENCODAGE));
		LoginDatabaseService.updatePwd(user, md5pwd);
		
		//envoi du mail
		String email = user.getEmail();
		try{
			MailService.sendEmail(email, PilotageConstants.MAILSERVER_INIT_PWD_SUBJECT, PilotageConstants.MAILSERVER_INIT_PWD_TEXTE, new String[]{password}, null);
		}
		catch (Exception e) {
			this.addActionError(getText("initialize.sendEmail.failed"));
			return ERROR;
		}
		
		return OK;
	}
}
