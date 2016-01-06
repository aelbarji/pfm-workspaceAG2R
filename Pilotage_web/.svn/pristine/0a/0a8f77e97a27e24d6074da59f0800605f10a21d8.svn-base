/**
 * pilotage.framework
 * 22 juin 2011
 */
package pilotage.login;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

/**
 * This class is used to logout and clear the session.
 * Logout success : EnterAction
 * Logout error : 
 *
 */
public class LogoutAction  extends ActionSupport implements SessionAware {

	private static final long serialVersionUID = 1L;
	@SuppressWarnings("rawtypes")
	protected Map session;

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

	@Override
	public String execute() throws Exception {
		session.clear();
		return "ok";
	}
}
