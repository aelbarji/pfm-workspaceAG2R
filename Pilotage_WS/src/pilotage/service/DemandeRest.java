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
import pilotage.database.checklist.ChecklistCurrentDatabaseService;
import pilotage.database.checklist.ChecklistHoraireDatabaseService;
import pilotage.metier.Checklist_Base;
import pilotage.metier.Checklist_Horaire;

@Path("/demandes")
public class DemandeRest {

	@GET
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public List<DemandesJSON> getListeDemandes() {
		List<DemandesJSON> listJSON = new ArrayList<DemandesJSON>();
		List<Checklist_Horaire> listDemandesHoraire = ChecklistHoraireDatabaseService.getHorairesDemandes();
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat simpleDateFormat3 = new SimpleDateFormat("HH:mm:ss");
		for (Checklist_Horaire c : listDemandesHoraire) {
			final Checklist_Base tache = c.getIdChecklist();
			DemandesJSON demande = new DemandesJSON();
			demande.setId_demande(c.getId().toString());
			if (Integer.parseInt(tache.getTypeDemande()) == 1) {
				demande.setType("mail");
				demande.setDescription(tache.getDescriptionMail());
			}
			else {
				demande.setType("obs");
				demande.setDescription(tache.getDescriptionObs());
			}
			demande.setNom(tache.getTache());
			demande.setEnvironnement(tache.getEnvironnement().getEnvironnement());
			demande.setNumero_obs(tache.getNumeroObs());
			demande.setNom_emetteur(tache.getNomEmetteur());
			
			demande.setCriticite(tache.getCriticite().getLibelle());
			if (tache.getHeureReception() != null) {
				String heureReception = simpleDateFormat.format(tache.getHeureReception());
				demande.setHeure_reception(heureReception);
			}
			else {
				demande.setHeure_reception("");
			}
			
			String date_debut = simpleDateFormat2.format(tache.getDateDebut());
			Date horaire = c.getHoraire();
			String heure_reception = simpleDateFormat3.format(horaire);
			demande.setHeure_realisation(date_debut + " " + heure_reception);
			
			String etat = ChecklistCurrentDatabaseService.getStatus(c);
			if (etat.equals("Pris en charge par")) {
				etat = "Affecté";
			}
			demande.setEtat(etat);
			
			listJSON.add(demande);
		}
		
		return listJSON;
	}
}