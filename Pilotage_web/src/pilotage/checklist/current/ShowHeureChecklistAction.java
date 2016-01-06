package pilotage.checklist.current;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import pilotage.database.incidents.HeuresOceorDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Heures_Oceor;
import pilotage.service.date.DateService;

public class ShowHeureChecklistAction extends AbstractAction {
	private static final long serialVersionUID = -1461207418051508668L;	

	private List<String[]> paysHeure = new ArrayList<String[]>();
	
	public List<String[]> getPaysHeure() {
		return paysHeure;
	}
	public void setPaysHeure(List<String[]> paysHeure) {
		this.paysHeure = paysHeure;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		HttpServletResponse response = ServletActionContext.getResponse();
		JSONArray mainArray = new JSONArray();
		
		mainArray = calculHeuresLocales(paysHeure);
		
		// façon d'envoyer l'objet JSON pour que Prototype puisse le récupérer
		response.setContentType("application/json");
		PrintWriter out;
		
		try {
			out = response.getWriter();
	        out.println(mainArray.toString());
	        out.flush();
	        out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return OK;
	}
	
	public static JSONArray calculHeuresLocales(List<String[]> paysHeure){
		List<Heures_Oceor> listHeure = HeuresOceorDatabaseService.getAllByHourAsc();
		Calendar c = Calendar.getInstance();
		TimeZone z = c.getTimeZone();
		int offsetHrs = 0;
		if(!z.inDaylightTime(new Date())){
			offsetHrs = 1;
		}
		
		JSONArray array = new JSONArray();
		
		for (int i = 0; i < listHeure.size(); i++){
			JSONObject lieuHeure = new JSONObject();
			Heures_Oceor heure = (Heures_Oceor) listHeure.get(i);
			int decalage = Integer.parseInt(heure.getHeure()) + offsetHrs;
			Date DateheureDomTom = DateService.addHours( new Date(), decalage);
			String heureDomTom = DateService.getTime(DateheureDomTom, DateService.pt1);
			String ville = heure.getTimezone().split("/")[1];
			lieuHeure.put("pays", ville);
			lieuHeure.put("heure", heureDomTom);
			if (!ville.equals("Paris"))
				array.add(lieuHeure);
		}
		return array;
	}
}
