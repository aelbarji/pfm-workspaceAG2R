package pilotage.framework;

import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import pilotage.admin.metier.Profil;
import pilotage.database.consigne.ConsignePiloteValidationDatabaseService;
import pilotage.database.consigne.ConsignesDatabaseService;
import pilotage.database.login.LoginDatabaseService;
import pilotage.login.LoginAction;
import pilotage.metier.Users;
import pilotage.metier.Consignes;
import pilotage.service.constants.PilotageConstants;
import pilotage.service.convertor.Base64Convertor;

import com.opensymphony.xwork2.ActionSupport;

public abstract class AbstractAction extends ActionSupport implements SessionAware {

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
	protected static final String SUITE 	= "suite";
	protected static final String EXIST     = "exist";
	protected static final String CONSIGNE     = "consigne";
		
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
    @SuppressWarnings("unchecked")
	public String execute() {
    	try{
	    	if(!isLogged()){
	    		return LOGIN;
	    	}
	    	if(!(Boolean) session.get(PilotageConstants.CONSIGNES_LUES)){
		    	Profil profil = (Profil)session.get(PilotageConstants.USER_PROFIL);
		    	if(profil.getPilote()){
		    		Users user = (Users)session.get(PilotageConstants.USER_LOGGED);
		    		for(Consignes consigne : ConsignesDatabaseService.getAllCurrentConsignes()){
		    			if(ConsignePiloteValidationDatabaseService.getValidationConsignePilote(user, consigne) == null){
		    				error = getText("consigne.non.lue");
		    				return CONSIGNE;
		    			}
		    		}
		    		session.put(PilotageConstants.CONSIGNES_LUES, true);
		    	}
	    	}
	    	if(!validateMetier()){
	    		return ERROR;
	    	}
	    	else{
	    		return executeMetier();
	    	}
    	}
    	catch (Exception e) {
    		e.printStackTrace();
    		erreurLogger.error("Exception récupérée : ", e);
    		this.addActionError(e.getMessage());
    		error = getText("error.message.generique") + " : " + e.getMessage();
    		return ERROR;
		}
    }
    
    /**
     * Méthode vérifiant si l'utilisateur est logué
     * @return
     */
    protected boolean isLogged(){
    	
    	if(session!=null){
    		//on recupere le nom de l'action en cours , si c'est une action différente d'un des noms de classes indiqués on supprime de la session tous les objets
    		//potentiellement mis en session par des exports
    		//c'est pour éviter de suppression de la session des objest dans le cas de deux exports conécutifs
    		EnterAction.gereSessionExportEnCours(session,this.getClass().getCanonicalName());
    		
    	}
    	
    	if(session != null && session.get(PilotageConstants.USER_LOGGED) != null){
    		return true;
    	}
    	//interopérabilité : si on a le parametre 'p' on fait les etapes préliminaires
    	//-------------------------------------------------------------------------------
    	else if(ServletActionContext.getRequest().getParameter("p") != null){
    		try{
	        	String encodedParameter = ServletActionContext.getRequest().getParameter("p");
	        	String username = Base64Convertor.decodeLoginParameter(encodedParameter);
	        	Users user = LoginDatabaseService.getUserByLogin(username);
	        	
	        	if(user != null){
	        		//EnterAction
	        		if(!Boolean.TRUE.equals((Boolean)session.get(PilotageConstants.PARAM_IN_SESSION))){
	    	           	EnterAction.putParametersInSession(session);
	        		}
	    	       		
	            	//LoginAction
	        		LoginAction.doLogin(session, username, null);
	        	}
	        	return true;
    		}
    		catch (Exception e) {
	    		erreurLogger.error("Login par GET : ", e);
				return false;
			}
        }
        
    	else
    		return false;
    }
    
    /**
     * Méthode vérifiant si le pilote a validé toutes les consignes
     * @return
     */
    protected boolean consigneValidee(){
    	return true;
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
