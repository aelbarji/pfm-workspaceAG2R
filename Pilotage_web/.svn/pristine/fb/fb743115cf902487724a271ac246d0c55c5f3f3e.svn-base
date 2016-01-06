package pilotage.incidents.types;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import pilotage.database.incidents.IncidentsTypesDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Incidents_Type;

public class SaveImpactTypesIncidentAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String map;

	
	public String getMap() {
		return map;
	}

	public void setMap(String map) {
		this.map = map;
	}

	@Override
	protected boolean validateMetier() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	protected String executeMetier() {
		
		HttpServletResponse response = ServletActionContext.getResponse();
		Map<String,String> newmap = new HashMap<String,String>();
		ObjectMapper mapper = new ObjectMapper();
	 
		try {
			//convert JSON string to Map
			newmap = mapper.readValue(map,
			    new TypeReference<HashMap<String,String>>(){});
			System.out.println(newmap);
			Set<String> keys = newmap.keySet();
			Iterator<String> it = keys.iterator();
			while (it.hasNext()){
			   Object key = it.next();
			   Object valeur = newmap.get(key); 
			   Integer rowID = IncidentsTypesDatabaseService.getId((String)valeur);
			   Incidents_Type incident = IncidentsTypesDatabaseService.get(rowID);
			   IncidentsTypesDatabaseService.update(rowID, (String)valeur, incident.getDescription(), Integer.parseInt((String)key), incident.getTitre_bilan());
			}
			try{
				response.setContentType("text/text;charset=utf-8");
				PrintWriter out = response.getWriter();
		        out.println("Hiérarchie des types a été mise à jour!");
		        out.flush();
		        out.close();
			}catch (Exception e) {
				Map<String, String> hm = new HashMap<String, String>();
				hm.put("error", "Une erreur est survenue lors de la sauvegarde de la hierachie des types : " + e.getMessage());
				JSONObject json = JSONObject.fromObject(hm);
				response.setHeader("X-JSON", json.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		
		return null;
	}

}
