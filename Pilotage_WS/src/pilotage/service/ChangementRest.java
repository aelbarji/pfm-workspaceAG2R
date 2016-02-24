package pilotage.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import pilotage.bean.ChangementJSON;
import pilotage.database.changements.ChangementsDatabaseService;
import pilotage.metier.Changements;

@Path("/changements")
public class ChangementRest {

	@GET
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public List<ChangementJSON> getListeChangements() throws ParseException {
		List<Changements> listChangements = ChangementsDatabaseService.getAll();
		List<ChangementJSON> listJSON = new ArrayList<ChangementJSON>();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (Changements c : listChangements) {
			ChangementJSON changementJSON = new ChangementJSON();
			changementJSON.setId_changement(c.getId().toString());

			String dateDebut = simpleDateFormat.format(c.getDateDebut());
			changementJSON.setDebut(dateDebut);
			String dateFin = simpleDateFormat.format(c.getDateFin());
			changementJSON.setFin(dateFin);
			changementJSON.setDemandeur(c.getDemandeur());
			changementJSON.setResume(c.getResume());
			changementJSON.setPriorite(c.getPriorite());
			changementJSON.setEtat(c.getEtat());
			changementJSON.setValideur(c.getValideur());

			listJSON.add(changementJSON);
		}

		return listJSON;
	}
}
