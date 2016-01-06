package pilotage.astreintes.actions.planning;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import pilotage.database.astreintes.AstreinteDatabaseService;
import pilotage.framework.AbstractAction;

public class ShowAstreinteTelephone extends AbstractAction {

	private static final long serialVersionUID = -4949500484569095252L;

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		
		Integer idAstreinte = Integer.parseInt(request.getParameter("idAstreinte"));
		try {
			JSONArray mainArray = new JSONArray();
			
			String telephone = AstreinteDatabaseService.getAstreinteTelephone(idAstreinte); 
				JSONObject jsonsArray = new JSONObject();
				jsonsArray.put("astreinteTelephone", telephone);
				
				mainArray.add(jsonsArray);
			
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("telephone", mainArray);
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
			hm.put("error", "Une erreur est survenue lors de l'affichage du téléphone de l'astreinte : " + e.getMessage());
			JSONObject json = JSONObject.fromObject(hm);
			response.setHeader("X-JSON", json.toString());
		}
		return null;
	}
}
