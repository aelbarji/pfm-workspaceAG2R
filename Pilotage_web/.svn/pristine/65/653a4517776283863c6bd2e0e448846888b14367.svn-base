package pilotage.machines.actions.domaine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import pilotage.framework.AbstractAction;

public class RedirectCreateMachineDomaineAction extends AbstractAction {
	
	private static final long serialVersionUID = -2136820498896120263L;

	public static final String LOGIN = "login";
	public static final String PASSWORD = "password";

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		return OK;
	}
	
	/**
	 * Récupération de la liste des login pour le domaine
	 * @param request
	 * @param listLogin
	 */
	public static void getListLoginDomaine(HttpServletRequest request, List<Map<String, String>> listLogin) {
		int i = 0;
		while(request.getParameter("login" + i) != null){
			Map<String, String> al = new HashMap<String, String>();
			al.put(LOGIN, request.getParameter("login" + i));
			al.put(PASSWORD, request.getParameter("password" + i));
			listLogin.add(al);
			
			++i;
		}
	}
}
