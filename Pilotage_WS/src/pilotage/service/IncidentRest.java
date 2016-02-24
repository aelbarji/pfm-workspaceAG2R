package pilotage.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import pilotage.bean.IncidentsJSON;
import pilotage.database.incidents.itsm.IncidentsItsmDatabaseService;
import pilotage.metier.Incidents_Itsm;

@Path("/incidents")
public class IncidentRest {

	@GET
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public List<IncidentsJSON> getListeIncidents() {
		List<Incidents_Itsm> listIncidents = IncidentsItsmDatabaseService
				.getAll();
		List<IncidentsJSON> listJSON = new ArrayList<IncidentsJSON>();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		for (Incidents_Itsm incident : listIncidents) {
			IncidentsJSON incidentJson = new IncidentsJSON();
			incidentJson.setId_requete(incident.getIdRequete());
			incidentJson.setEtat(incident.getEtat());
			incidentJson.setImpact(incident.getImpact());
			incidentJson.setPriorite(incident.getPriorite());
			incidentJson.setResume(incident.getResume());
			incidentJson.setUrgence(incident.getUrgence());
			incidentJson.setNb_relance(incident.getNbRelance().toString());
			String dateCreation = simpleDateFormat.format(incident
					.getDateCreation());
			incidentJson.setDate_creation(dateCreation);
			String dateModification = simpleDateFormat.format(incident
					.getDateModification());
			incidentJson.setDate_modification(dateModification);

			listJSON.add(incidentJson);
		}

		return listJSON;
	}
}
