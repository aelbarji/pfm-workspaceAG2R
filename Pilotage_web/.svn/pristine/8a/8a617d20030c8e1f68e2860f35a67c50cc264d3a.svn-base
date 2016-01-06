package pilotage.astreintes.actions.appel;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import pilotage.database.incidents.IncidentsDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Incidents;

public class VerifExistIncidentAction extends AbstractAction{

	private static final long serialVersionUID = 7157178097100606295L;

	private String incident;
	
	public String getIncident() {
		return incident;
	}

	public void setIncident(String incident) {
		this.incident = incident;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		HttpServletResponse response = ServletActionContext.getResponse();
		String idIncident = incident.replace(" ", "");
		Incidents inc = IncidentsDatabaseService.get(Integer.parseInt(idIncident));
		int exist = 0;
		if(inc != null) exist = 1;
		try{
			response.setContentType("text/text;charset=utf-8");
			PrintWriter out = response.getWriter();
	        out.println(exist);
	        out.flush();
	        out.close();
		}
        catch (Exception e) {
			Map<String, String> hm = new HashMap<String, String>();
			hm.put("error", "Une erreur est survenue lors de la recherche incident : " + e.getMessage());
			JSONObject json = JSONObject.fromObject(hm);
			response.setHeader("X-JSON", json.toString());
		}
		return null;
	}

}
