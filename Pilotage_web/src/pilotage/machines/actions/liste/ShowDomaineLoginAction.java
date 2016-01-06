package pilotage.machines.actions.liste;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import pilotage.database.machine.DomaineLoginDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Domaine_Wind_Login;

public class ShowDomaineLoginAction extends AbstractAction {

	private static final long serialVersionUID = -822559184215295838L;

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		
		Integer idDomaine = Integer.parseInt(request.getParameter("idDomaine"));
		try {
			JSONArray mainArray = new JSONArray();
			
			List<Domaine_Wind_Login> listLogin = DomaineLoginDatabaseService.getLoginsFromDomaine(idDomaine); 
			for (Domaine_Wind_Login dwl : listLogin) {
				JSONObject jsonsArray = new JSONObject();
				jsonsArray.put("login", dwl.getLogin());
				jsonsArray.put("password", dwl.getPassword());
				jsonsArray.put("nomDomaine", dwl.getDomaine().getNomDomaine());
				
				mainArray.add(jsonsArray);
			}
			
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("lignes", mainArray);
			// chaque clé de notre map devient une clé dans l'objet JSON (utilisation de Json-lib)
			JSONObject json = JSONObject.fromObject(jsonObject);
			
			// façon d'envoyer l'objet JSON pour que Prototype puisse le récupérer
			response.setContentType("application/json");
			PrintWriter out = response.getWriter();
	        out.println(json.toString());
	        out.flush();
	        out.close();
		}
		catch (Exception e) {
			Map<String, String> hm = new HashMap<String, String>();
			hm.put("error", "Une erreur est survenue lors de l'affichage des logins de domaine : " + e.getMessage());
			JSONObject json = JSONObject.fromObject(hm);
			response.setHeader("X-JSON", json.toString());
		}
		return null;
	}
}
