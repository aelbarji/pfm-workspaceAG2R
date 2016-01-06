/**
 * pilotage.framework
 * 20 juin 2011
 */
package pilotage.admin.actions.login;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import pilotage.admin.metier.Profil;
import pilotage.database.admin.ProfilDatabaseService;
import pilotage.database.login.LoginDatabaseService;
import pilotage.framework.EnterAction;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;

import com.opensymphony.xwork2.ActionSupport;

/**
 * This class is to check the login information and turn to the corresponding pages.
 * Login success: admin_pages/accueil.jsp
 * Login fail(incorrect username or password: 
 * Login error(the server side error)
 */
public class LoginAdminAction extends ActionSupport implements SessionAware {

	private static final long serialVersionUID = 7782750837309116731L;
	
	private String username;
	private String password;
	
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
    /**
     * Méthode exécutée à l'appel de l'action
     */
	@SuppressWarnings("unchecked")
    public String execute() {
    	try{
			Users user = LoginDatabaseService.getUserByLogin(username);
	    	Profil userProfil = ProfilDatabaseService.get(user.getStatut());
	    	
	    	if(userProfil.getAdmin()){
		    	session.put(PilotageConstants.ADMIN_LOGGED, user);
		    	return SUCCESS;
	    	}
	    	else{
	    		this.addActionError(getText("login.erreur.information.incorrect"));
	    		return INPUT;
	    	}
    	}
    	catch (Exception e) {
    		this.addActionError(getText("error.message.generique") + " : " + e.getMessage());
			return INPUT;
		}
    }
    
    /**
     * Contrôle sur l'existence du couple user/mdp
     */
    @Override
	public void validate() {
    	//Si on n'a plus les parametres en session, on les remet
		if(!Boolean.TRUE.equals((Boolean)session.get(PilotageConstants.PARAM_IN_SESSION))){
			EnterAction.putParametersInSession(session);
		}
		
		try{
			if(!LoginDatabaseService.findUser(username, password, (String)session.get(PilotageConstants.CLE_ENCODAGE))){
				this.addActionError(getText("login.erreur.information.incorrect"));
			}
		}
		catch (Exception e) {
			this.addActionError(getText("error.message.generique") + " : " + e.getMessage());
		}
	}
}
