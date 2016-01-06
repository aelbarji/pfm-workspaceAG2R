package pilotage.framework;

import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;

import pilotage.service.constants.PilotageConstants;

import com.opensymphony.xwork2.ActionSupport;

public abstract class AbstractAdminAction extends ActionSupport implements SessionAware {

	private static final long serialVersionUID = 1L;
	
	protected static final Logger applicationLogger = Logger.getLogger("pilotage");
	protected static final Logger erreurLogger = Logger.getLogger("erreur");
	
	@SuppressWarnings("rawtypes")
	protected Map session;
	protected String error;
	protected String info;

	//codes retour
	protected static final String ERROR 	= "error";
	protected static final String OK 		= "ok";
	protected static final String LOGIN 	= "login";
	
	/**
	 * Getter info
	 * @return
	 */
	public String getInfo() {
		return info;
	}

	/**
	 * Setter info
	 * @param info
	 */
	public void setInfo(String info) {
		this.info = info;
	}
	
	/**
	 * Getter error
	 * @return
	 */
	public String getError() {
		return error;
	}

	/**
	 * Setter error
	 * @param error
	 */
	public void setError(String error) {
		this.error = error;
	}
	
	/**
	 * Getter session
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Map getSession() {
        return session;
    }
 
	/**
	 * Setter session
	 */
	@SuppressWarnings("rawtypes")
    public void setSession(Map sess) {
        this.session = sess;
    }

    /**
     * Méthode éxécutée à l'appel de l'action
     */
    public String execute() {
    	if(!isLogged()){
    		return LOGIN;
    	}
    	if(!validateMetier()){
    		return ERROR;
    	}
    	else{
    		try{
    			return executeMetier();
    		}
    		catch (Exception e) {
    			error = getText("error.message.generique") + " : " + e.getMessage();
    			return ERROR;
			}
    	}
    }
    
    /**
     * Méthode vérifiant si l'utilisateur est logué
     * @return
     */
    protected boolean isLogged(){
    	if(session != null && session.get(PilotageConstants.ADMIN_LOGGED) != null)
    		return true;
    	else
    		return false;
    }
    
    /**
     * Méthode abstraite vérifiant si les valeurs du formulaire sont conformes
     * @return
     */
    protected abstract boolean validateMetier();
    
    /**
     * Méthode éxécutée à l'appel de l'action spécifique à chaque action
     * @return
     */
    protected abstract String executeMetier();
}
