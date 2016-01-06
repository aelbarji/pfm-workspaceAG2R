package pilotage.login;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import pilotage.admin.metier.Affectation_menu;
import pilotage.admin.metier.Droits_Liste;
import pilotage.admin.metier.Parametre;
import pilotage.admin.metier.Profil;
import pilotage.database.admin.DroitsListeDatabaseService;
import pilotage.database.admin.MenuDatabaseService;
import pilotage.database.admin.ParametreDatabaseService;
import pilotage.database.admin.ProfilDatabaseService;
import pilotage.database.login.LoginDatabaseService;
import pilotage.framework.EnterAction;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;
import pilotage.service.convertor.Base64Convertor;
import pilotage.service.menu.MenuGenerator;
import pilotage.service.menu.PilotageMenu;

import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport implements SessionAware {

	private static final long serialVersionUID = -7809181731795142604L;
	protected static final Logger applicationLogger = Logger.getLogger("pilotage");
	protected static final Logger erreurLogger = Logger.getLogger("erreur");
	
	private String timezone;
	private String username;
	private String password;
	private String redirect;
	
	@SuppressWarnings("rawtypes")
	protected Map session;

	public String getRedirect() {
		return redirect;
	}

	public void setRedirect(String redirect) {
		this.redirect = redirect;
	}

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
	
	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	@Override
	public void validate() {
		//Si on n'a plus les parametres en session, on les remet
		if(!Boolean.TRUE.equals((Boolean)session.get(PilotageConstants.PARAM_IN_SESSION))){
			EnterAction.putParametersInSession(session);
		}
		
		//if the username or password are incorrect, give an error message
		try{
			if(!LoginDatabaseService.findUser(username, password, (String)session.get(PilotageConstants.CLE_ENCODAGE))){
				this.addActionError(getText("login.erreur.information.incorrect"));
			}
		}
		catch (Exception e) {
			this.addActionError(getText("error.message.generique") + " : " + e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	public String execute() {
		try{
			LoginAction.doLogin(session, username, timezone);
		}
		catch (Exception e) {
			this.addActionError(getText("error.message.generique") + " : " + e.getMessage());
			return INPUT;
		}

		Profil profil = (Profil)session.get(PilotageConstants.USER_PROFIL);
		if(profil.getAccueil() != null && MenuGenerator.contains((List<PilotageMenu>)session.get(PilotageConstants.MENUS), profil.getAccueil())){
			redirect = profil.getAccueil().getLien();
		
			// ajout de l'intéropérabilité lorsque la page d'accueil en contient un.
			if (profil.getAccueil().getInterop()){
				String encodedUsernameRedirect = Base64Convertor.encodeLoginParameter(username);
				if(profil.getAccueil().getLien().contains("?")){
					redirect += "&p=" + encodedUsernameRedirect;
				}
				else{
					redirect += "?p=" + encodedUsernameRedirect;				
				}
			}		
		}
		return SUCCESS;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void doLogin(Map session, String username, String timeZone) throws Exception{
		Date date = new Date();
		Users user = LoginDatabaseService.getUserByLogin(username);   //get user's information
		Profil profil = ProfilDatabaseService.get(user.getStatut());
		String encodedUsername = Base64Convertor.encodeLoginParameter(username);
		List<Droits_Liste> listDL = DroitsListeDatabaseService.getDroitsByProfil(profil);
		List<String> listDroits  = new ArrayList<String>();
		for (Droits_Liste dl : listDL) {
			listDroits.add(dl.getLibelle());
		}
		if (profil.getPilote()) {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession httpses = request.getSession();
			
			try {
				Parametre param = ParametreDatabaseService.get(PilotageConstants.TPS_INACTIVITE_SESSION);
				httpses.setMaxInactiveInterval(Integer.parseInt(param.getValeur()));
			} catch (Exception e){
				erreurLogger.error("Erreur lors du paramétrage du temps d'inactivité de la session");
			}
		}
		
		List<Affectation_menu> aMenus = null;
		try {
			aMenus = MenuDatabaseService.getALLMenusAffectedToProfil(user.getStatut());
		} catch (Exception e) {
			erreurLogger.error("Erreur lors de la récupération des menus du profil.", e);
		}
		
		List<PilotageMenu> treeMenu = MenuGenerator.getTreeMenu(aMenus);
		MenuGenerator.setInteroperabilite(treeMenu, encodedUsername);
		
		TimeZone tz = null;
		if(timeZone == null)
			tz = Calendar.getInstance().getTimeZone();
		else
			tz = TimeZone.getTimeZone(timeZone);

        session.put(PilotageConstants.DATE, date);           				//put date in session
		session.put(PilotageConstants.USER_LOGGED, user);  					//put user object into session
		session.put(PilotageConstants.USER_PROFIL, profil);					//put user profil into session
		session.put(PilotageConstants.MENUS, treeMenu);       				//put menus object into session
		session.put(PilotageConstants.TIMEZONE, tz);       					//put menus object into session
		session.put(PilotageConstants.USER_DROITS, listDroits);
		if(!profil.getPilote()) session.put(PilotageConstants.CONSIGNES_LUES, true);
		else session.put(PilotageConstants.CONSIGNES_LUES, false);
	}
}
