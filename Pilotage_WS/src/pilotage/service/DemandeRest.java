package pilotage.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import pilotage.bean.DemandesJSON;
import pilotage.database.checklist.ChecklistBaseDatabaseService;
import pilotage.database.checklist.ChecklistHoraireDatabaseService;
import pilotage.metier.Checklist_Base;

@Path("/demandes")
public class DemandeRest {

	@GET
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public List<DemandesJSON> getListeDemandes() {
		List<DemandesJSON> listJSON = new ArrayList<DemandesJSON>();
		List<Checklist_Base> list = ChecklistBaseDatabaseService.getDemandes();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat simpleDateFormat3 = new SimpleDateFormat("HH:mm:ss");
		for (Checklist_Base c : list) {
			DemandesJSON demande = new DemandesJSON();
			demande.setId_demande(c.getId().toString());
			if (Integer.parseInt(c.getTypeDemande())==1) {
				demande.setType("mail");
				demande.setDescription(c.getDescriptionMail());
			}
			else {
				demande.setType("obs");
				demande.setDescription(c.getDescriptionObs());
			}
			demande.setNom(c.getTache());
			demande.setEnvironnement(c.getEnvironnement().getEnvironnement());
			demande.setNumero_obs(c.getNumeroObs());
			demande.setNom_emetteur(c.getNomEmetteur());
			demande.setEtat(c.getEtat().getEtat());
			demande.setCriticite(c.getCriticite().getLibelle());
			if (c.getHeureReception() != null) {
				String heureReception = simpleDateFormat.format(c.getHeureReception());
				demande.setHeure_reception(heureReception);
			}
			else {
				demande.setHeure_reception("");
			}
			
			String date_debut = simpleDateFormat2.format(c.getDateDebut());
			Date horaire = ChecklistHoraireDatabaseService.getHoraire(c);
			String heure_reception = simpleDateFormat3.format(horaire);
			demande.setHeure_realisation(date_debut + " " + heure_reception);
			listJSON.add(demande);
		}
		
		return listJSON;
	}
}