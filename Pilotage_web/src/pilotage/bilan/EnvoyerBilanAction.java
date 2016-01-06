package pilotage.bilan;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.naming.Context;
import javax.naming.InitialContext;

import pilotage.database.bilan.BilanColonnesDatabaseService;
import pilotage.database.bilan.BilanDatabaseService;
import pilotage.database.bilan.BilanDestinatairesDatabaseService;
import pilotage.database.environnement.EnvironnementTypeDatabaseService;
import pilotage.metier.Alertes_Disques;
import pilotage.metier.Applicatifs_Liste;
import pilotage.metier.Astreinte_Appel;
import pilotage.metier.Bilan_Colonnes;
import pilotage.metier.Bilan_Destinataires;
import pilotage.metier.Disques;
import pilotage.metier.Environnement_Type;
import pilotage.metier.Flux_CFT;
import pilotage.metier.Hardware_Software;
import pilotage.metier.Incidents;
import pilotage.service.constants.PilotageConstants;
import pilotage.service.date.DateService;
import pilotage.service.mail.MailService;

public class EnvoyerBilanAction extends ShowDetailBilanAction{

	private static final long serialVersionUID = 1L;

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		try{
			//Récupération de la liste de destinataires
			List<Bilan_Destinataires> listBilanDestinataire =  BilanDestinatairesDatabaseService.getAll(selectedType);
			
			if(listBilanDestinataire == null || listBilanDestinataire.isEmpty()){
				error = getText("bilan.envoi.aucun.destinataire");
				return ERROR;
			}

			//récupération des données
			if(ERROR.equals(super.executeMetier()))
				return ERROR;
			
			//constitution du bilan
			String body = getBody();
			
			//envoi
			String[] listDestinataireArray = new String[listBilanDestinataire.size()];
			for(int i = 0; i < listBilanDestinataire.size(); ++i){
				listDestinataireArray[i] = listBilanDestinataire.get(i).getMail();
			}
			Map<String, String> images = new HashMap<String, String>();
			Context ctx = new InitialContext();
			images.put("logo", ctx.lookup(PilotageConstants.BILAN_LOGO_CONFIG_PATH).toString());
			boolean checkSend = MailService.sendEmail(listDestinataireArray, typeDeBilan.getLibelle()+" "+ DateService.dateToStr(selectedDate, DateService.p1) + (dateFin != null ? " au " + DateService.dateToStr(dateFin, DateService.p1) : ""), body , null, images);
			if(!checkSend){
				error = getText("bilan.envoi.error");
				return ERROR;
			}
			
			//Mise à jour de bilan_stat
			if(DateService.isToday(selectedDate)){
				BilanDatabaseService.updateBilanStat(typeDeBilan, new Date());
			}

			info = MessageFormat.format(getText("bilan.envoi.valide"), DateService.dateToStr(selectedDate, DateService.p1) + (dateFin != null ? " au " + DateService.dateToStr(dateFin, DateService.p1) : ""));
			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Envoi du bilan du " + DateService.dateToStr(selectedDate, DateService.p1) + (dateFin != null ? " au " + DateService.dateToStr(dateFin, DateService.p1) : "") + " - ", e);
			return ERROR;
		}
	}
	
	private String getBody() throws Exception{
		
		//Récupération des colonnes du bilan
		List<Bilan_Colonnes> listBilanColonnes = BilanColonnesDatabaseService.getByBilan(selectedType);
		
		//Récupération du contenu de la CSS
		Context ctx = new InitialContext();
		String path = ctx.lookup(PilotageConstants.BILAN_CSS_CONFIG_PATH).toString();

		BufferedReader input =  new BufferedReader(new FileReader(path));
		String line;
		StringBuffer style = new StringBuffer();
		while (( line = input.readLine()) != null){
			style.append(line);
			style.append(System.getProperty("line.separator"));
		}
		input.close();
		
		//Constitution de la page HTML
		StringBuffer htmlPage = new StringBuffer();
		htmlPage.append("<html>");
		
		//Head
		htmlPage.append("<head>");
		htmlPage.append("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
		htmlPage.append("<style type='text/css'>");
		htmlPage.append(style.toString());
		htmlPage.append("</style>");
		htmlPage.append("<title>Bilan</title>");
		htmlPage.append("</head>");
		
		//Body
		htmlPage.append("<body>");
		htmlPage.append("<table width='200%' class='outTable'>");
		htmlPage.append("<tr><td>");
		//DEBUT Body --> En-tete
		htmlPage.append("<table width='100%' class='innerTable' cellpadding='0px' rules='all'>");
		htmlPage.append("<tr class='header'>");
		//Body --> En-tete --> Logo
		htmlPage.append("<td class='logo'>");
		htmlPage.append("<img alt='Logo' src='cid:logo' /><br/>");
		htmlPage.append("Exploitation et Datacenter<br/>");
		htmlPage.append("Pilotage 7/24 Paris");
		htmlPage.append("</td>");
		//Body --> En-tete --> Titre
		htmlPage.append("<td width='" + ((typeDeBilan.isVacation() && dateFin == null) ? "50" : "70") + "%' class='BigTitle'>");
		htmlPage.append(typeDeBilan.getLibelle() + " du " + DateService.dateToStr(selectedDate, DateService.p1));
		htmlPage.append(dateFin != null ? " au " + DateService.dateToStr(dateFin, DateService.p1) : "");
		htmlPage.append("</td>");
		//Body --> En-tete --> Vacations
		if(typeDeBilan.isVacation() && dateFin == null){
			htmlPage.append("<td align='center' width='20%'>");
			htmlPage.append("<table width='100%' border='0'>");
			htmlPage.append("<tr align='center' border='0'>");
			htmlPage.append("<td colspan='2' border='0' style='border: none;' class='title'>Responsables de vacations</td>");
			htmlPage.append("</tr>");
			htmlPage.append("<tr>");
			htmlPage.append("<td class='vacationHours' style='border: none;'>07h15/15h00 :</td>");
			htmlPage.append("<td class='vacationName' style='border: none;'>");			
			htmlPage.append(bilan == null || bilan.getVacationmatin() == null ? "Non v&eacute;rifi&eacute;" : (bilan.getVacationmatin().getNom() + " " + bilan.getVacationmatin().getPrenom()));
			htmlPage.append("</td>");
			htmlPage.append("</tr>");
			htmlPage.append("<tr>");
			htmlPage.append("<td class='vacationHours' style='border: none;'>14h45/22h30 :</td>");
			htmlPage.append("<td class='vacationName' style='border: none;'>");			
			htmlPage.append(bilan == null || bilan.getVacationaprem() == null ? "Non v&eacute;rifi&eacute;" : (bilan.getVacationaprem().getNom() + " " + bilan.getVacationaprem().getPrenom()));
			htmlPage.append("</td>");
			htmlPage.append("</tr>");
			htmlPage.append("<tr>");
			htmlPage.append("<td class='vacationHours' style='border: none;'>22h15/07h30 :</td>");
			htmlPage.append("<td class='vacationName' style='border: none;'>");			
			htmlPage.append(bilan == null || bilan.getVacationnuit() == null ? "Non v&eacute;rifi&eacute;" : (bilan.getVacationnuit().getNom() + " " + bilan.getVacationnuit().getPrenom()));
			htmlPage.append("</td>");
			htmlPage.append("</tr>");
			htmlPage.append("</table>");
			htmlPage.append("</td>");
		}
		htmlPage.append("</tr>");
		
		if(typeDeBilan.isActionEPI() && dateFin == null){
			//DEBUT Body --> En-tete --> Résumé
			htmlPage.append("<tr>");
			htmlPage.append("<td colspan='3' style='border: none;'>");
			htmlPage.append("<table width='100%' cellpadding='0px' cellspacing='0px' class='outTable'>");		
			//Body --> En-tete --> Résumé --> Titre
			htmlPage.append("<tr>");
			htmlPage.append("<td align='center' colspan='14' class='title'>Pilotage Opérationnel</td>");
			htmlPage.append("</tr>");
			//Body --> En-tete --> Résumé --> Titre colonnes
			htmlPage.append("<tr>");
			htmlPage.append("<td colspan='10' align='center' class='operationTitle'>Résumé du jour</td>");
			htmlPage.append("<td colspan='4' align='center' class='operationTitle'>Résumé du mois</td>");
			htmlPage.append("</tr>");
			htmlPage.append("<tr>");			
			htmlPage.append("<td width='6%' class='operationTitle'>Nombre d’incidents</td>");
			htmlPage.append("<td width='6%' class='operationTitle'>Incidents critiques</td>");
			htmlPage.append("<td width='6%' class='operationTitle'>Incidents résolus</td>");
			htmlPage.append("<td width='6%' class='operationTitle'>Incidents de "+EnvironnementTypeDatabaseService.getPrincipal().getType()+"</td>");
			htmlPage.append("<td width='6%' class='operationTitle'>Incidents « autres environnements »</td>");
			htmlPage.append("<td width='6%' class='operationTitle'>Nombre d’alertes</td>");
			htmlPage.append("<td width='6%' class='operationTitle'>Alertes résolus</td>");
			htmlPage.append("<td width='6%' class='operationTitle'>Alertes de "+EnvironnementTypeDatabaseService.getPrincipal().getType()+"</td>");
			htmlPage.append("<td width='6%' class='operationTitle'>Alertes « autres environnements »</td>");
			htmlPage.append("<td width='6%' class='operationTitle'>Appels astreintes</td>");
			htmlPage.append("<td width='5%' class='operationTitle'>Nombre d’incidents</td>");
			htmlPage.append("<td width='5%' class='operationTitle'>Nombre moyen d’incidents</td>");
			htmlPage.append("<td width='5%' class='operationTitle'>Nombre d’alertes</td>");
			htmlPage.append("<td width='5%' class='operationTitle'>Nombre moyen d’alertes</td>");
			htmlPage.append("</tr>");
			//Body --> En-tete --> Résumé --> valeurs
			htmlPage.append("<tr>");
			htmlPage.append("<td class='operationValue'>" + incidentsAuJour + "</td>");
			htmlPage.append("<td class='operationValue'>" + incidentsCritiques + "</td>");
			htmlPage.append("<td class='operationValue'>" + incidentsResolu + "</td>");
			htmlPage.append("<td class='operationValue'>" + incidentsDeProduction + "</td>");
			htmlPage.append("<td class='operationValue'>" + incidentsAutreEnvir + "</td>");
			htmlPage.append("<td class='operationValue'>" + alertesAuJour + "</td>");
			htmlPage.append("<td class='operationValue'>" + alertesResolu + "</td>");
			htmlPage.append("<td class='operationValue'>" + alertesDeProduction + "</td>");
			htmlPage.append("<td class='operationValue'>" + alertesAutreEnvir + "</td>");
			htmlPage.append("<td class='operationValue'>" + astreintesAppel + "</td>");
			htmlPage.append("<td class='operationValue'>" + incidentsAuMois + "</td>");
			htmlPage.append("<td class='operationValue'>" + incidentsMoyenAuMois + "</td>");
			htmlPage.append("<td class='operationValue'>" + alertesAuMois + "</td>");
			htmlPage.append("<td class='operationValue'>" + alertesMoyenAuMois + "</td>");
			htmlPage.append("</tr>");
			//FIN Body --> En-tete --> Résumé
			htmlPage.append("</table>");
			htmlPage.append("</td>");
			htmlPage.append("</tr>");
		}
		//FIN Body --> En-tete
		htmlPage.append("</table>");
		htmlPage.append("</td>");
		htmlPage.append("</tr>");
		htmlPage.append("<tr><td class='separation'></td></tr>");
		//DEBUT Incidents --> Production
		htmlPage.append("<tr>");
		htmlPage.append("<td class='title' align='center'>Incident de "+EnvironnementTypeDatabaseService.getPrincipal().getType()+"</td>");
		htmlPage.append("</tr>");
		//Incidents --> Production --> avec coupure
		htmlPage.append("<tr>");
		htmlPage.append("<td>");
		htmlPage.append("<table width='100%' align='center' class='innerTable'>");
		//Incidents --> Production --> avec coupure --> titre
		htmlPage.append("<tr>");
		htmlPage.append("<td colspan='"+listBilanColonnes.size()+"' align='center' class='incidentTableTitle'>Incidents avec coupure de service</td>");
		htmlPage.append("</tr>");
		//Incidents --> Production --> avec coupure --> titre des colonnes
		htmlPage.append("<tr>");

		for(Bilan_Colonnes col : listBilanColonnes){
			htmlPage.append("<td width='"+col.getTaille()+"%' class='incidentTableTH'>"+col.getNomDsBilan()+"</td>");
		}
		htmlPage.append("</tr>");
		//Incidents --> Production --> avec coupure --> liste des incidents
		if(incidentMap != null && incidentMap.get(incidentDeProduction) != null){
			for(Incidents incident : incidentMap.get(incidentDeProduction)){
				if(incident.getCoupure() != null && incident.getCoupure().equals(1)){
					htmlPage.append("<tr>");
					for(Bilan_Colonnes col : listBilanColonnes){
						htmlPage.append("<td class='incidentTableValue'>");
						if(col.getIncidentColonne().getNomColonne().equals("machine")){
							htmlPage.append(incident.getMachine().getNom() + "</td>");
						}
						if(col.getIncidentColonne().getNomColonne().equals("applicatif")){
							for(Applicatifs_Liste application : appMap.get(incident.getId())){
								htmlPage.append(application.getApplicatif());
								htmlPage.append("<br/>");
							}
						}
						if(col.getIncidentColonne().getNomColonne().equals("hard_software")){
							for(Hardware_Software hardsoft : hardMap.get(incident.getId())){
								htmlPage.append(hardsoft.getLibelle());
								htmlPage.append("<br/>");
							}
						}
						if(col.getIncidentColonne().getNomColonne().equals("idOutil")){
							htmlPage.append((incident.getIdOutil() == null ? "" : incident.getIdOutil().getNomOutils()));
						}
						if(col.getIncidentColonne().getNomColonne().equals("datedebut")){
							htmlPage.append(DateService.dateToStr(incident.getDateDebut(), DateService.p1) + " " + DateService.getTime(incident.getDateDebut(), DateService.pt1));
						}
						if(col.getIncidentColonne().getNomColonne().equals("datedebut_gmt")){
							if (incident.getOceor().getId() != 0) {
								TimeZone heureOceor = TimeZone.getTimeZone(incident.getOceor().getTimezone());
								Date DateHeureDebutOceor = DateService.convertFromTimeZoneToTimeZone(incident.getDateDebutGmt(),null,heureOceor);
								htmlPage.append(DateService.dateToStr(DateHeureDebutOceor, DateService.p1) + " " + DateService.getTime(DateHeureDebutOceor, DateService.pt1));
							} else {
								htmlPage.append("");				
							}
						}
						if(col.getIncidentColonne().getNomColonne().equals("observation")){
							htmlPage.append(incident.getObservation());
						}
						if(col.getIncidentColonne().getNomColonne().equals("action")){
							htmlPage.append(incident.getAction());
						}
						if(col.getIncidentColonne().getNomColonne().equals("ars")){
							htmlPage.append(incident.getArs());
						}
						if(col.getIncidentColonne().getNomColonne().equals("datefin")){
							htmlPage.append((incident.getDateFin() == null ? "En Cours" : DateService.getTime(incident.getDateFin(), DateService.pt1)));
						}
						if(col.getIncidentColonne().getNomColonne().equals("datefin_gmt")){
							if (incident.getOceor().getId() != 0) {
								TimeZone heureOceor = TimeZone.getTimeZone(incident.getOceor().getTimezone());
								Date DateHeureFinOceor = DateService.convertFromTimeZoneToTimeZone(incident.getDateFinGmt(),null,heureOceor);
								htmlPage.append((incident.getDateFinGmt() == null ? "En Cours" : DateService.getTime(DateHeureFinOceor, DateService.pt1)));
							} else {
								htmlPage.append("");				
							}
						}
						if(col.getIncidentColonne().getNomColonne().equals("aAstreinte")){
							for(Astreinte_Appel astreinte : astreinteMap.get(incident.getId())){
								htmlPage.append(astreinte.getAstreinte().getNom() + " " + astreinte.getAstreinte().getPrenom());
								htmlPage.append("<br/>");
							}
						}
						if(col.getIncidentColonne().getNomColonne().equals("appli_ordonnanceur")){
							htmlPage.append(incident.getAppli_ordonnanceur());
						}
						if(col.getIncidentColonne().getNomColonne().equals("job")){
							htmlPage.append(incident.getJob());
						}
						if(col.getIncidentColonne().getNomColonne().equals("reprise")){
							htmlPage.append((incident.getDateFin() == null ? "KO" : "OK"));
						}
						htmlPage.append("</td>");
					}
					htmlPage.append("</tr>");
				}
			}
		}
		htmlPage.append("</table>");
		htmlPage.append("</td>");
		htmlPage.append("</tr>");
		htmlPage.append("<tr><td class='separation'></td></tr>");
		//Incidents --> Production --> sans coupure
		htmlPage.append("<tr>");
		htmlPage.append("<td>");
		htmlPage.append("<table width='100%' align='center' class='innerTable'>");
		//Incidents --> Production --> sans coupure --> titre
		htmlPage.append("<tr>");
		htmlPage.append("<td colspan='"+listBilanColonnes.size()+"' align='center' class='incidentTableTitle'>Incidents</td>");
		htmlPage.append("</tr>");
		//Incidents --> Production --> sans coupure --> titre des colonnes
		htmlPage.append("<tr>");
		
		for(Bilan_Colonnes col : listBilanColonnes){
			htmlPage.append("<td width='"+col.getTaille()+"%' class='incidentTableTH'>"+col.getNomDsBilan()+"</td>");
		}
		htmlPage.append("</tr>");
		//Incidents --> Production --> sans coupure --> liste des incidents
		if(incidentMap != null && incidentMap.get(incidentDeProduction) != null){
			for(Incidents incident : incidentMap.get(incidentDeProduction)){
				if(incident.getType().getType().equals("Incident")){
					
					htmlPage.append("<tr>");
					for(Bilan_Colonnes col : listBilanColonnes){
						htmlPage.append("<td class='incidentTableValue'>");
						if(col.getIncidentColonne().getNomColonne().equals("machine")){
							htmlPage.append(incident.getMachine().getNom());
						}
						if(col.getIncidentColonne().getNomColonne().equals("applicatif")){
							for(Applicatifs_Liste application : appMap.get(incident.getId())){
								htmlPage.append(application.getApplicatif());
								htmlPage.append("<br/>");
							}
						}
						if(col.getIncidentColonne().getNomColonne().equals("hard_software")){
							for(Hardware_Software hardsoft : hardMap.get(incident.getId())){
								htmlPage.append(hardsoft.getLibelle());
								htmlPage.append("<br/>");
							}
						}
						if(col.getIncidentColonne().getNomColonne().equals("idOutil")){
							htmlPage.append((incident.getIdOutil() == null ? "" : incident.getIdOutil().getNomOutils()));
						}
						if(col.getIncidentColonne().getNomColonne().equals("datedebut")){
							htmlPage.append(DateService.dateToStr(incident.getDateDebut(), DateService.p1) + " " + DateService.getTime(incident.getDateDebut(), DateService.pt1));
						}
						if(col.getIncidentColonne().getNomColonne().equals("datedebut_gmt")){
							if (incident.getOceor().getId() != 0) {
								TimeZone heureOceor = TimeZone.getTimeZone(incident.getOceor().getTimezone());
								Date DateHeureDebutOceor = DateService.convertFromTimeZoneToTimeZone(incident.getDateDebutGmt(),null,heureOceor);
								htmlPage.append(DateService.dateToStr(DateHeureDebutOceor, DateService.p1) + " " + DateService.getTime(DateHeureDebutOceor, DateService.pt1));
							} else {
								htmlPage.append("");				
							}
						}
						if(col.getIncidentColonne().getNomColonne().equals("observation")){
							htmlPage.append(incident.getObservation());
						}
						if(col.getIncidentColonne().getNomColonne().equals("action")){
							htmlPage.append(incident.getAction());
						}
						if(col.getIncidentColonne().getNomColonne().equals("ars")){
							htmlPage.append(incident.getArs());
						}
						if(col.getIncidentColonne().getNomColonne().equals("datefin")){
							htmlPage.append((incident.getDateFin() == null ? "En Cours" : DateService.getTime(incident.getDateFin(), DateService.pt1)));
						}
						if(col.getIncidentColonne().getNomColonne().equals("datefin_gmt")){
							if (incident.getOceor().getId() != 0) {
								TimeZone heureOceor = TimeZone.getTimeZone(incident.getOceor().getTimezone());
								Date DateHeureFinOceor = DateService.convertFromTimeZoneToTimeZone(incident.getDateFinGmt(),null,heureOceor);
								htmlPage.append((incident.getDateFinGmt() == null ? "En Cours" : DateService.getTime(DateHeureFinOceor, DateService.pt1)));
							} else {
								htmlPage.append("");				
							}
						}
						if(col.getIncidentColonne().getNomColonne().equals("aAstreinte")){
							for(Astreinte_Appel astreinte : astreinteMap.get(incident.getId())){
								htmlPage.append(astreinte.getAstreinte().getNom() + " " + astreinte.getAstreinte().getPrenom());
								htmlPage.append("<br/>");
							}
						}
						if(col.getIncidentColonne().getNomColonne().equals("appli_ordonnanceur")){
							htmlPage.append(incident.getAppli_ordonnanceur());
						}
						if(col.getIncidentColonne().getNomColonne().equals("job")){
							htmlPage.append(incident.getJob());
						}
						if(col.getIncidentColonne().getNomColonne().equals("reprise")){
							htmlPage.append((incident.getDateFin() == null ? "KO" : "OK"));
						}
						htmlPage.append("</td>");
					}
					htmlPage.append("</tr>");
				}
			}
		}
		htmlPage.append("</table>");
		htmlPage.append("</td>");
		htmlPage.append("</tr>");
		
		htmlPage.append("<tr><td class='separation'></td></tr>");
		//Incidents --> Production --> sans coupure
		htmlPage.append("<tr>");
		htmlPage.append("<td>");
		htmlPage.append("<table width='100%' align='center' class='innerTable'>");
		//Incidents --> Production --> sans coupure --> titre
		htmlPage.append("<tr>");
		htmlPage.append("<td colspan='"+listBilanColonnes.size()+"' align='center' class='incidentTableTitle'>Alertes</td>");
		htmlPage.append("</tr>");
		//Incidents --> Production --> sans coupure --> titre des colonnes
		htmlPage.append("<tr>");
		
		for(Bilan_Colonnes col : listBilanColonnes){
			htmlPage.append("<td width='"+col.getTaille()+"%' class='incidentTableTH'>"+col.getNomDsBilan()+"</td>");
		}
		htmlPage.append("</tr>");
		//Incidents --> Production --> sans coupure --> liste des incidents
		if(incidentMap != null && incidentMap.get(incidentDeProduction) != null){
			for(Incidents incident : incidentMap.get(incidentDeProduction)){
				if(incident.getType().getType().equals("Alerte")){
					
					htmlPage.append("<tr>");
					for(Bilan_Colonnes col : listBilanColonnes){
						htmlPage.append("<td class='incidentTableValue'>");
						if(col.getIncidentColonne().getNomColonne().equals("machine")){
							htmlPage.append(incident.getMachine().getNom());
						}
						if(col.getIncidentColonne().getNomColonne().equals("applicatif")){
							for(Applicatifs_Liste application : appMap.get(incident.getId())){
								htmlPage.append(application.getApplicatif());
								htmlPage.append("<br/>");
							}
						}
						if(col.getIncidentColonne().getNomColonne().equals("hard_software")){
							for(Hardware_Software hardsoft : hardMap.get(incident.getId())){
								htmlPage.append(hardsoft.getLibelle());
								htmlPage.append("<br/>");
							}
						}
						if(col.getIncidentColonne().getNomColonne().equals("idOutil")){
							htmlPage.append((incident.getIdOutil() == null ? "" : incident.getIdOutil().getNomOutils()));
						}
						if(col.getIncidentColonne().getNomColonne().equals("datedebut")){
							htmlPage.append(DateService.dateToStr(incident.getDateDebut(), DateService.p1) + " " + DateService.getTime(incident.getDateDebut(), DateService.pt1));
						}
						if(col.getIncidentColonne().getNomColonne().equals("datedebut_gmt")){
							if (incident.getOceor().getId() != 0) {
								TimeZone heureOceor = TimeZone.getTimeZone(incident.getOceor().getTimezone());
								Date DateHeureDebutOceor = DateService.convertFromTimeZoneToTimeZone(incident.getDateDebutGmt(),null,heureOceor);
								htmlPage.append(DateService.dateToStr(DateHeureDebutOceor, DateService.p1) + " " + DateService.getTime(DateHeureDebutOceor, DateService.pt1));
							} else {
								htmlPage.append("");				
							}
						}
						if(col.getIncidentColonne().getNomColonne().equals("observation")){
							htmlPage.append(incident.getObservation());
						}
						if(col.getIncidentColonne().getNomColonne().equals("action")){
							htmlPage.append(incident.getAction());
						}
						if(col.getIncidentColonne().getNomColonne().equals("ars")){
							htmlPage.append(incident.getArs());
						}
						if(col.getIncidentColonne().getNomColonne().equals("datefin")){
							htmlPage.append((incident.getDateFin() == null ? "En Cours" : DateService.getTime(incident.getDateFin(), DateService.pt1)));
						}
						if(col.getIncidentColonne().getNomColonne().equals("datefin_gmt")){
							if (incident.getOceor().getId() != 0) {
								TimeZone heureOceor = TimeZone.getTimeZone(incident.getOceor().getTimezone());
								Date DateHeureFinOceor = DateService.convertFromTimeZoneToTimeZone(incident.getDateFinGmt(),null,heureOceor);
								htmlPage.append((incident.getDateFinGmt() == null ? "En Cours" : DateService.getTime(DateHeureFinOceor, DateService.pt1)));
							} else {
								htmlPage.append("");				
							}
						}
						if(col.getIncidentColonne().getNomColonne().equals("aAstreinte")){
							for(Astreinte_Appel astreinte : astreinteMap.get(incident.getId())){
								htmlPage.append(astreinte.getAstreinte().getNom() + " " + astreinte.getAstreinte().getPrenom());
								htmlPage.append("<br/>");
							}
						}
						if(col.getIncidentColonne().getNomColonne().equals("appli_ordonnanceur")){
							htmlPage.append(incident.getAppli_ordonnanceur());
						}
						if(col.getIncidentColonne().getNomColonne().equals("job")){
							htmlPage.append(incident.getJob());
						}
						if(col.getIncidentColonne().getNomColonne().equals("reprise")){
							htmlPage.append((incident.getDateFin() == null ? "KO" : "OK"));
						}
						htmlPage.append("</td>");
					}
					htmlPage.append("</tr>");
				}
			}
		}
		htmlPage.append("</table>");
		htmlPage.append("</td>");
		htmlPage.append("</tr>");
		
		htmlPage.append("<tr><td class='separation'></td></tr>");
		//Incidents --> Production --> sans coupure
		htmlPage.append("<tr>");
		htmlPage.append("<td>");
		htmlPage.append("<table width='100%' align='center' class='innerTable'>");
		//Incidents --> Production --> sans coupure --> titre
		htmlPage.append("<tr>");
		htmlPage.append("<td colspan='"+listBilanColonnes.size()+"' align='center' class='incidentTableTitle'>Informations</td>");
		htmlPage.append("</tr>");
		//Incidents --> Production --> sans coupure --> titre des colonnes
		htmlPage.append("<tr>");
		
		for(Bilan_Colonnes col : listBilanColonnes){
			htmlPage.append("<td width='"+col.getTaille()+"%' class='incidentTableTH'>"+col.getNomDsBilan()+"</td>");
		}
		htmlPage.append("</tr>");
		//Informations --> Production
		if(incidentMap != null && incidentMap.get(incidentDeProduction) != null){
			for(Incidents incident : incidentMap.get(incidentDeProduction)){
				if(incident.getType().getType().equals("Information")){
					
					htmlPage.append("<tr>");
					for(Bilan_Colonnes col : listBilanColonnes){
						htmlPage.append("<td class='incidentTableValue'>");
						if(col.getIncidentColonne().getNomColonne().equals("machine")){
							htmlPage.append(incident.getMachine().getNom());
						}
						if(col.getIncidentColonne().getNomColonne().equals("applicatif")){
							for(Applicatifs_Liste application : appMap.get(incident.getId())){
								htmlPage.append(application.getApplicatif());
								htmlPage.append("<br/>");
							}
						}
						if(col.getIncidentColonne().getNomColonne().equals("hard_software")){
							for(Hardware_Software hardsoft : hardMap.get(incident.getId())){
								htmlPage.append(hardsoft.getLibelle());
								htmlPage.append("<br/>");
							}
						}
						if(col.getIncidentColonne().getNomColonne().equals("idOutil")){
							htmlPage.append((incident.getIdOutil() == null ? "" : incident.getIdOutil().getNomOutils()));
						}
						if(col.getIncidentColonne().getNomColonne().equals("datedebut")){
							htmlPage.append(DateService.dateToStr(incident.getDateDebut(), DateService.p1) + " " + DateService.getTime(incident.getDateDebut(), DateService.pt1));
						}
						if(col.getIncidentColonne().getNomColonne().equals("datedebut_gmt")){
							if (incident.getOceor().getId() != 0) {
								TimeZone heureOceor = TimeZone.getTimeZone(incident.getOceor().getTimezone());
								Date DateHeureDebutOceor = DateService.convertFromTimeZoneToTimeZone(incident.getDateDebutGmt(),null,heureOceor);
								htmlPage.append(DateService.dateToStr(DateHeureDebutOceor, DateService.p1) + " " + DateService.getTime(DateHeureDebutOceor, DateService.pt1));
							} else {
								htmlPage.append("");				
							}
						}
						if(col.getIncidentColonne().getNomColonne().equals("observation")){
							htmlPage.append(incident.getObservation());
						}
						if(col.getIncidentColonne().getNomColonne().equals("action")){
							htmlPage.append(incident.getAction());
						}
						if(col.getIncidentColonne().getNomColonne().equals("ars")){
							htmlPage.append(incident.getArs());
						}
						if(col.getIncidentColonne().getNomColonne().equals("datefin")){
							htmlPage.append((incident.getDateFin() == null ? "En Cours" : DateService.getTime(incident.getDateFin(), DateService.pt1)));
						}
						if(col.getIncidentColonne().getNomColonne().equals("datefin_gmt")){
							if (incident.getOceor().getId() != 0) {
								TimeZone heureOceor = TimeZone.getTimeZone(incident.getOceor().getTimezone());
								Date DateHeureFinOceor = DateService.convertFromTimeZoneToTimeZone(incident.getDateFinGmt(),null,heureOceor);
								htmlPage.append((incident.getDateFinGmt() == null ? "En Cours" : DateService.getTime(DateHeureFinOceor, DateService.pt1)));
							} else {
								htmlPage.append("");				
							}
						}
						if(col.getIncidentColonne().getNomColonne().equals("aAstreinte")){
							for(Astreinte_Appel astreinte : astreinteMap.get(incident.getId())){
								htmlPage.append(astreinte.getAstreinte().getNom() + " " + astreinte.getAstreinte().getPrenom());
								htmlPage.append("<br/>");
							}
						}
						if(col.getIncidentColonne().getNomColonne().equals("appli_ordonnanceur")){
							htmlPage.append(incident.getAppli_ordonnanceur());
						}
						if(col.getIncidentColonne().getNomColonne().equals("job")){
							htmlPage.append(incident.getJob());
						}
						if(col.getIncidentColonne().getNomColonne().equals("reprise")){
							htmlPage.append((incident.getDateFin() == null ? "KO" : "OK"));
						}
						htmlPage.append("</td>");
					}
					htmlPage.append("</tr>");
				}
			}
		}
		htmlPage.append("</table>");
		htmlPage.append("</td>");
		htmlPage.append("</tr>");
		
		htmlPage.append("<tr><td class='separation'></td></tr>");
		//DEBUT Incidents --> Autres environnements
		for(Environnement_Type envType : listEnvironType){
			if(incidentMap.get(envType) != null && ! incidentMap.get(envType).isEmpty()){
				htmlPage.append("<tr><td class='separation'></td></tr>");
				//Incidents --> Autres environnements --> Titre
				htmlPage.append("<tr>");
				htmlPage.append("<td colspan='"+listBilanColonnes.size()+"' align='center' class='title'>Information de " + envType.getType() + "</td>");
				htmlPage.append("</tr>");
				//Incidents --> Autres environnements --> Critiques 
				htmlPage.append("<tr>");
				htmlPage.append("<td>");
				htmlPage.append("<table width='100%' align='center' class='innerTable'>");
				htmlPage.append("<tr>");
				htmlPage.append("<td colspan='"+listBilanColonnes.size()+"' align='center' class='incidentTableTitle'>Incidents avec coupure de service</td>");
				htmlPage.append("</tr>");
				//Incidents --> Autres environnements --> Critiques --> Titre des colonnes
				htmlPage.append("<tr>");
				
				for(Bilan_Colonnes col : listBilanColonnes){
					htmlPage.append("<td width='"+col.getTaille()+"%' class='incidentTableTH'>"+col.getNomDsBilan()+"</td>");
				}
				htmlPage.append("</tr>");
				//Incidents --> Autres environnements --> Critiques --> liste des incidents
				for(Incidents incident : incidentMap.get(envType)){
					if(incident.getCoupure()==1){
						htmlPage.append("<tr>");
												
						for(Bilan_Colonnes col : listBilanColonnes){
							htmlPage.append("<td class='incidentTableValue'>");
							if(col.getIncidentColonne().getNomColonne().equals("machine")){
								htmlPage.append(incident.getMachine().getNom());
							}
							if(col.getIncidentColonne().getNomColonne().equals("applicatif")){
								for(Applicatifs_Liste application : appMap.get(incident.getId())){
									htmlPage.append(application.getApplicatif());
									htmlPage.append("<br/>");
								}
							}
							if(col.getIncidentColonne().getNomColonne().equals("hard_software")){
								for(Hardware_Software hardsoft : hardMap.get(incident.getId())){
									htmlPage.append(hardsoft.getLibelle());
									htmlPage.append("<br/>");
								}
							}
							if(col.getIncidentColonne().getNomColonne().equals("idOutil")){
								htmlPage.append((incident.getIdOutil() == null ? "" : incident.getIdOutil().getNomOutils()));
							}
							if(col.getIncidentColonne().getNomColonne().equals("datedebut")){
								htmlPage.append(DateService.dateToStr(incident.getDateDebut(), DateService.p1) + " " + DateService.getTime(incident.getDateDebut(), DateService.pt1));
							}
							if(col.getIncidentColonne().getNomColonne().equals("datedebut_gmt")){
								if (incident.getOceor().getId() != 0) {
									TimeZone heureOceor = TimeZone.getTimeZone(incident.getOceor().getTimezone());
									Date DateHeureDebutOceor = DateService.convertFromTimeZoneToTimeZone(incident.getDateDebutGmt(),null,heureOceor);
									htmlPage.append(DateService.dateToStr(DateHeureDebutOceor, DateService.p1) + " " + DateService.getTime(DateHeureDebutOceor, DateService.pt1));
								} else {
									htmlPage.append("");				
								}
							}
							if(col.getIncidentColonne().getNomColonne().equals("observation")){
								htmlPage.append(incident.getObservation());
							}
							if(col.getIncidentColonne().getNomColonne().equals("action")){
								htmlPage.append(incident.getAction());
							}
							if(col.getIncidentColonne().getNomColonne().equals("ars")){
								htmlPage.append(incident.getArs());
							}
							if(col.getIncidentColonne().getNomColonne().equals("datefin")){
								htmlPage.append((incident.getDateFin() == null ? "En Cours" : DateService.getTime(incident.getDateFin(), DateService.pt1)));
							}
							if(col.getIncidentColonne().getNomColonne().equals("datefin_gmt")){
								if (incident.getOceor().getId() != 0) {
									TimeZone heureOceor = TimeZone.getTimeZone(incident.getOceor().getTimezone());
									Date DateHeureFinOceor = DateService.convertFromTimeZoneToTimeZone(incident.getDateFinGmt(),null,heureOceor);
									htmlPage.append((incident.getDateFinGmt() == null ? "En Cours" : DateService.getTime(DateHeureFinOceor, DateService.pt1)));
								} else {
									htmlPage.append("");				
								}
							}
							if(col.getIncidentColonne().getNomColonne().equals("aAstreinte")){
								for(Astreinte_Appel astreinte : astreinteMap.get(incident.getId())){
									htmlPage.append(astreinte.getAstreinte().getNom() + " " + astreinte.getAstreinte().getPrenom());
									htmlPage.append("<br/>");
								}
							}
							if(col.getIncidentColonne().getNomColonne().equals("appli_ordonnanceur")){
								htmlPage.append(incident.getAppli_ordonnanceur());
							}
							if(col.getIncidentColonne().getNomColonne().equals("job")){
								htmlPage.append(incident.getJob());
							}
							if(col.getIncidentColonne().getNomColonne().equals("reprise")){
								htmlPage.append((incident.getDateFin() == null ? "KO" : "OK"));
							}
							htmlPage.append("</td>");
						}
						htmlPage.append("</tr>");
					}
				}
				htmlPage.append("</table>");
				htmlPage.append("</td>");
				htmlPage.append("</tr>");
				//Incidents --> Autres environnements --> Non Critiques 
				htmlPage.append("<tr><td class='separation'></td></tr>");
				htmlPage.append("<tr>");
				htmlPage.append("<td>");
				htmlPage.append("<table width='100%' align='center' class='innerTable'>");
				htmlPage.append("<tr>");
				htmlPage.append("<td colspan='"+listBilanColonnes.size()+"' align='center' class='incidentTableTitle'>Incidents</td>");
				htmlPage.append("</tr>");
				//Incidents --> Autres environnements --> Non Critiques --> Titre des colonnes
				htmlPage.append("<tr>");
				
				for(Bilan_Colonnes col : listBilanColonnes){
					htmlPage.append("<td width='"+col.getTaille()+"%' class='incidentTableTH'>"+col.getNomDsBilan()+"</td>");
				}
				htmlPage.append("</tr>");
				//Incidents --> Autres environnements --> Non Critiques --> liste des incidents
				for(Incidents incident : incidentMap.get(envType)){
					if(incident.getType().getType().equals("Incident")){
						htmlPage.append("<tr>");
												
						for(Bilan_Colonnes col : listBilanColonnes){
							htmlPage.append("<td class='incidentTableValue'>");
							if(col.getIncidentColonne().getNomColonne().equals("machine")){
								htmlPage.append(incident.getMachine().getNom());
							}
							if(col.getIncidentColonne().getNomColonne().equals("applicatif")){
								for(Applicatifs_Liste application : appMap.get(incident.getId())){
									htmlPage.append(application.getApplicatif());
									htmlPage.append("<br/>");
								}
							}
							if(col.getIncidentColonne().getNomColonne().equals("hard_software")){
								for(Hardware_Software hardsoft : hardMap.get(incident.getId())){
									htmlPage.append(hardsoft.getLibelle());
									htmlPage.append("<br/>");
								}
							}
							if(col.getIncidentColonne().getNomColonne().equals("idOutil")){
								htmlPage.append((incident.getIdOutil() == null ? "" : incident.getIdOutil().getNomOutils()));
							}
							if(col.getIncidentColonne().getNomColonne().equals("datedebut")){
								htmlPage.append(DateService.dateToStr(incident.getDateDebut(), DateService.p1) + " " + DateService.getTime(incident.getDateDebut(), DateService.pt1));
							}
							if(col.getIncidentColonne().getNomColonne().equals("datedebut_gmt")){
								if (incident.getOceor().getId() != 0) {
									TimeZone heureOceor = TimeZone.getTimeZone(incident.getOceor().getTimezone());
									Date DateHeureDebutOceor = DateService.convertFromTimeZoneToTimeZone(incident.getDateDebutGmt(),null,heureOceor);
									htmlPage.append(DateService.dateToStr(DateHeureDebutOceor, DateService.p1) + " " + DateService.getTime(DateHeureDebutOceor, DateService.pt1));
								} else {
									htmlPage.append("");				
								}
							}
							if(col.getIncidentColonne().getNomColonne().equals("observation")){
								htmlPage.append(incident.getObservation());
							}
							if(col.getIncidentColonne().getNomColonne().equals("action")){
								htmlPage.append(incident.getAction());
							}
							if(col.getIncidentColonne().getNomColonne().equals("ars")){
								htmlPage.append(incident.getArs());
							}
							if(col.getIncidentColonne().getNomColonne().equals("datefin")){
								htmlPage.append((incident.getDateFin() == null ? "En Cours" : DateService.getTime(incident.getDateFin(), DateService.pt1)));
							}
							if(col.getIncidentColonne().getNomColonne().equals("datefin_gmt")){
								if (incident.getOceor().getId() != 0) {
									TimeZone heureOceor = TimeZone.getTimeZone(incident.getOceor().getTimezone());
									Date DateHeureFinOceor = DateService.convertFromTimeZoneToTimeZone(incident.getDateFinGmt(),null,heureOceor);
									htmlPage.append((incident.getDateFinGmt() == null ? "En Cours" : DateService.getTime(DateHeureFinOceor, DateService.pt1)));
								} else {
									htmlPage.append("");				
								}
							}
							if(col.getIncidentColonne().getNomColonne().equals("aAstreinte")){
								for(Astreinte_Appel astreinte : astreinteMap.get(incident.getId())){
									htmlPage.append(astreinte.getAstreinte().getNom() + " " + astreinte.getAstreinte().getPrenom());
									htmlPage.append("<br/>");
								}
							}
							if(col.getIncidentColonne().getNomColonne().equals("appli_ordonnanceur")){
								htmlPage.append(incident.getAppli_ordonnanceur());
							}
							if(col.getIncidentColonne().getNomColonne().equals("job")){
								htmlPage.append(incident.getJob());
							}
							if(col.getIncidentColonne().getNomColonne().equals("reprise")){
								htmlPage.append((incident.getDateFin() == null ? "KO" : "OK"));
							}
							htmlPage.append("</td>");
						}
						htmlPage.append("</tr>");
					}
				}
				htmlPage.append("</table>");
				htmlPage.append("</td>");
				htmlPage.append("</tr>");
				//Incidents --> Autres environnements --> Non Critiques 
				htmlPage.append("<tr><td class='separation'></td></tr>");
				
				//Incidents --> Autres environnements --> Non Critiques 
				htmlPage.append("<tr><td class='separation'></td></tr>");
				htmlPage.append("<tr>");
				htmlPage.append("<td>");
				htmlPage.append("<table width='100%' align='center' class='innerTable'>");
				htmlPage.append("<tr>");
				htmlPage.append("<td colspan='"+listBilanColonnes.size()+"' align='center' class='incidentTableTitle'>Alertes</td>");
				htmlPage.append("</tr>");
				//Alertes --> Autres environnements --> Titre des colonnes
				htmlPage.append("<tr>");
				
				for(Bilan_Colonnes col : listBilanColonnes){
					htmlPage.append("<td width='"+col.getTaille()+"%' class='incidentTableTH'>"+col.getNomDsBilan()+"</td>");
				}
				htmlPage.append("</tr>");
				
				//Alertes --> Autres environnements
				for(Incidents incident : incidentMap.get(envType)){
					if(incident.getType().getType().equals("Alerte")){
						htmlPage.append("<tr>");
												
						for(Bilan_Colonnes col : listBilanColonnes){
							htmlPage.append("<td class='incidentTableValue'>");
							if(col.getIncidentColonne().getNomColonne().equals("machine")){
								htmlPage.append(incident.getMachine().getNom());
							}
							if(col.getIncidentColonne().getNomColonne().equals("applicatif")){
								for(Applicatifs_Liste application : appMap.get(incident.getId())){
									htmlPage.append(application.getApplicatif());
									htmlPage.append("<br/>");
								}
							}
							if(col.getIncidentColonne().getNomColonne().equals("hard_software")){
								for(Hardware_Software hardsoft : hardMap.get(incident.getId())){
									htmlPage.append(hardsoft.getLibelle());
									htmlPage.append("<br/>");
								}
							}
							if(col.getIncidentColonne().getNomColonne().equals("idOutil")){
								htmlPage.append((incident.getIdOutil() == null ? "" : incident.getIdOutil().getNomOutils()));
							}
							if(col.getIncidentColonne().getNomColonne().equals("datedebut")){
								htmlPage.append(DateService.dateToStr(incident.getDateDebut(), DateService.p1) + " " + DateService.getTime(incident.getDateDebut(), DateService.pt1));
							}
							if(col.getIncidentColonne().getNomColonne().equals("datedebut_gmt")){
								if (incident.getOceor().getId() != 0) {
									TimeZone heureOceor = TimeZone.getTimeZone(incident.getOceor().getTimezone());
									Date DateHeureDebutOceor = DateService.convertFromTimeZoneToTimeZone(incident.getDateDebutGmt(),null,heureOceor);
									htmlPage.append(DateService.dateToStr(DateHeureDebutOceor, DateService.p1) + " " + DateService.getTime(DateHeureDebutOceor, DateService.pt1));
								} else {
									htmlPage.append("");				
								}
							}
							if(col.getIncidentColonne().getNomColonne().equals("observation")){
								htmlPage.append(incident.getObservation());
							}
							if(col.getIncidentColonne().getNomColonne().equals("action")){
								htmlPage.append(incident.getAction());
							}
							if(col.getIncidentColonne().getNomColonne().equals("ars")){
								htmlPage.append(incident.getArs());
							}
							if(col.getIncidentColonne().getNomColonne().equals("datefin")){
								htmlPage.append((incident.getDateFin() == null ? "En Cours" : DateService.getTime(incident.getDateFin(), DateService.pt1)));
							}
							if(col.getIncidentColonne().getNomColonne().equals("datefin_gmt")){
								if (incident.getOceor().getId() != 0) {
									TimeZone heureOceor = TimeZone.getTimeZone(incident.getOceor().getTimezone());
									Date DateHeureFinOceor = DateService.convertFromTimeZoneToTimeZone(incident.getDateFinGmt(),null,heureOceor);
									htmlPage.append((incident.getDateFinGmt() == null ? "En Cours" : DateService.getTime(DateHeureFinOceor, DateService.pt1)));
								} else {
									htmlPage.append("");				
								}
							}
							if(col.getIncidentColonne().getNomColonne().equals("aAstreinte")){
								for(Astreinte_Appel astreinte : astreinteMap.get(incident.getId())){
									htmlPage.append(astreinte.getAstreinte().getNom() + " " + astreinte.getAstreinte().getPrenom());
									htmlPage.append("<br/>");
								}
							}
							if(col.getIncidentColonne().getNomColonne().equals("appli_ordonnanceur")){
								htmlPage.append(incident.getAppli_ordonnanceur());
							}
							if(col.getIncidentColonne().getNomColonne().equals("job")){
								htmlPage.append(incident.getJob());
							}
							if(col.getIncidentColonne().getNomColonne().equals("reprise")){
								htmlPage.append((incident.getDateFin() == null ? "KO" : "OK"));
							}
							htmlPage.append("</td>");
						}
						htmlPage.append("</tr>");
					}
				}
				htmlPage.append("</table>");
				htmlPage.append("</td>");
				htmlPage.append("</tr>");
				htmlPage.append("<tr><td class='separation'></td></tr>");
				
				htmlPage.append("<tr>");
				htmlPage.append("<td>");
				htmlPage.append("<table width='100%' align='center' class='innerTable'>");
				htmlPage.append("<tr>");
				htmlPage.append("<td colspan='"+listBilanColonnes.size()+"' align='center' class='incidentTableTitle'>Informations</td>");
				htmlPage.append("</tr>");
				//Informations --> Autres environnements --> Titre des colonnes
				htmlPage.append("<tr>");
				
				for(Bilan_Colonnes col : listBilanColonnes){
					htmlPage.append("<td width='"+col.getTaille()+"%' class='incidentTableTH'>"+col.getNomDsBilan()+"</td>");
				}
				htmlPage.append("</tr>");
				//Informations --> Autres environnements --> liste des incidents
				for(Incidents incident : incidentMap.get(envType)){
					if(incident.getType().getType().equals("Information")){
						htmlPage.append("<tr>");
												
						for(Bilan_Colonnes col : listBilanColonnes){
							htmlPage.append("<td class='incidentTableValue'>");
							if(col.getIncidentColonne().getNomColonne().equals("machine")){
								htmlPage.append(incident.getMachine().getNom());
							}
							if(col.getIncidentColonne().getNomColonne().equals("applicatif")){
								for(Applicatifs_Liste application : appMap.get(incident.getId())){
									htmlPage.append(application.getApplicatif());
									htmlPage.append("<br/>");
								}
							}
							if(col.getIncidentColonne().getNomColonne().equals("hard_software")){
								for(Hardware_Software hardsoft : hardMap.get(incident.getId())){
									htmlPage.append(hardsoft.getLibelle());
									htmlPage.append("<br/>");
								}
							}
							if(col.getIncidentColonne().getNomColonne().equals("idOutil")){
								htmlPage.append((incident.getIdOutil() == null ? "" : incident.getIdOutil().getNomOutils()));
							}
							if(col.getIncidentColonne().getNomColonne().equals("datedebut")){
								htmlPage.append(DateService.dateToStr(incident.getDateDebut(), DateService.p1) + " " + DateService.getTime(incident.getDateDebut(), DateService.pt1));
							}
							if(col.getIncidentColonne().getNomColonne().equals("datedebut_gmt")){
								if (incident.getOceor().getId() != 0) {
									TimeZone heureOceor = TimeZone.getTimeZone(incident.getOceor().getTimezone());
									Date DateHeureDebutOceor = DateService.convertFromTimeZoneToTimeZone(incident.getDateDebutGmt(),null,heureOceor);
									htmlPage.append(DateService.dateToStr(DateHeureDebutOceor, DateService.p1) + " " + DateService.getTime(DateHeureDebutOceor, DateService.pt1));
								} else {
									htmlPage.append("");				
								}
							}
							if(col.getIncidentColonne().getNomColonne().equals("observation")){
								htmlPage.append(incident.getObservation());
							}
							if(col.getIncidentColonne().getNomColonne().equals("action")){
								htmlPage.append(incident.getAction());
							}
							if(col.getIncidentColonne().getNomColonne().equals("ars")){
								htmlPage.append(incident.getArs());
							}
							if(col.getIncidentColonne().getNomColonne().equals("datefin")){
								htmlPage.append((incident.getDateFin() == null ? "En Cours" : DateService.getTime(incident.getDateFin(), DateService.pt1)));
							}
							if(col.getIncidentColonne().getNomColonne().equals("datefin_gmt")){
								if (incident.getOceor().getId() != 0) {
									TimeZone heureOceor = TimeZone.getTimeZone(incident.getOceor().getTimezone());
									Date DateHeureFinOceor = DateService.convertFromTimeZoneToTimeZone(incident.getDateFinGmt(),null,heureOceor);
									htmlPage.append((incident.getDateFinGmt() == null ? "En Cours" : DateService.getTime(DateHeureFinOceor, DateService.pt1)));
								} else {
									htmlPage.append("");				
								}
							}
							if(col.getIncidentColonne().getNomColonne().equals("aAstreinte")){
								for(Astreinte_Appel astreinte : astreinteMap.get(incident.getId())){
									htmlPage.append(astreinte.getAstreinte().getNom() + " " + astreinte.getAstreinte().getPrenom());
									htmlPage.append("<br/>");
								}
							}
							if(col.getIncidentColonne().getNomColonne().equals("appli_ordonnanceur")){
								htmlPage.append(incident.getAppli_ordonnanceur());
							}
							if(col.getIncidentColonne().getNomColonne().equals("job")){
								htmlPage.append(incident.getJob());
							}
							if(col.getIncidentColonne().getNomColonne().equals("reprise")){
								htmlPage.append((incident.getDateFin() == null ? "KO" : "OK"));
							}
							htmlPage.append("</td>");
						}
						htmlPage.append("</tr>");
					}
				}
				htmlPage.append("</table>");
				htmlPage.append("</td>");
				htmlPage.append("</tr>");
			}
		}
		
		htmlPage.append("<tr><td class='separation'></td></tr>");
		//DEBUT Espaces Disques
		if(typeDeBilan.isEspaceDisk()){
			if(typeDeBilan.isDisknonOCEOR()){
				NumberFormat nf = NumberFormat.getInstance();
				nf.setMaximumFractionDigits(2);
				nf.setMinimumFractionDigits(2);
				
				htmlPage.append("<tr>");
				htmlPage.append("<td align='center' class='title'>Etat des espaces disques</td>");
				htmlPage.append("</tr>");
				//Espaces Disques --> Tableau
				if (listEspaceDate.size() == 0) {
					htmlPage.append("<tr>");
					htmlPage.append("<td>");
					htmlPage.append("<table width='100%' align='center' class='innerTable'>");
					//Espaces Disques --> Tableau --> Titre des colonnes
					htmlPage.append("<tr>");
					htmlPage.append("<td width='30%' class='espaceDisqueTH'>Serveur</td>");
					htmlPage.append("<td width='30%' class='espaceDisqueTH'>% d'occupation J-1</td>");
					htmlPage.append("<td width='30%' class='espaceDisqueTH'>% d'occupation J</td>");
					htmlPage.append("<td width='10%' class='espaceDisqueTH'>Seuil</td>");
					htmlPage.append("</tr>");
					//Espaces Disques --> Tableau --> liste des espaces disques
					for(Disques disque : listDisques){
						htmlPage.append("<tr>");
						htmlPage.append("<td class='espaceDisqueString'>" + disque.getLibelle() + "</td>");
						htmlPage.append("<td colspan='3' align='center'>Pas d'espace disque</td>");
						htmlPage.append("</tr>");
					}
					htmlPage.append("</table>");
					htmlPage.append("</td>");
					htmlPage.append("</tr>");
				} else {
					for (String date : listEspaceDate) {
						htmlPage.append("<tr>");
						htmlPage.append("<td align='center' class='title'>" + date + "</td>");
						htmlPage.append("</tr>");
						htmlPage.append("<tr>");
						htmlPage.append("<td>");
						htmlPage.append("<table width='100%' align='center' class='innerTable'>");
						//Espaces Disques --> Tableau --> Titre des colonnes
						htmlPage.append("<tr>");
						htmlPage.append("<td width='30%' class='espaceDisqueTH'>Serveur</td>");
						htmlPage.append("<td width='30%' class='espaceDisqueTH'>% d'occupation J-1</td>");
						htmlPage.append("<td width='30%' class='espaceDisqueTH'>% d'occupation J</td>");
						htmlPage.append("<td width='10%' class='espaceDisqueTH'>Seuil</td>");
						htmlPage.append("</tr>");
						//Espaces Disques --> Tableau --> liste des espaces disques
						for(Disques disque : listDisques){
							if(espaceMap.get(date).get(disque.getId()) != null && ((disque.isFiliale() && typeDeBilan.isDisknonOCEOR()) || (!disque.isFiliale() && typeDeBilan.isEspaceDisk()))){
								Float[] espacesValues = espaceMap.get(date).get(disque.getId());
								htmlPage.append("<tr>");
								htmlPage.append("<td class='espaceDisqueString'>" + disque.getLibelle() + "</td>");
								htmlPage.append("<td class='espaceDisqueNumber'>" + (espacesValues[1] != null ? nf.format(espacesValues[1]) + "%" : "") + "</td>");
								htmlPage.append("<td class='espaceDisqueNumber'>" + (espacesValues[2] != null ? nf.format(espacesValues[2]) + "%" : "") + "</td>");
								htmlPage.append("<td class='espaceDisqueNumber'>" + (espacesValues[0] != null ? nf.format(espacesValues[0]) + "%" : "") + "</td>");
								htmlPage.append("</tr>");
							}
						}
						htmlPage.append("</table>");
						htmlPage.append("</td>");
						htmlPage.append("</tr>");
					}
				}
			}
		
			htmlPage.append("<tr><td class='separation'></td></tr>");
			//DEBUT Alertes Disques
			htmlPage.append("<tr>");
			htmlPage.append("<td align='center' class='title'>Alertes FileSystem/TableSpace</td>");
			htmlPage.append("</tr>");
			//Alertes Disques --> tableau
			if (listAlertesDate.size() == 0) {
				//Alertes Disques --> vide
				htmlPage.append("<tr>");
				htmlPage.append("<td>");
				htmlPage.append("<table   width='100%' align='center' class='innerTable'>");
				//Alertes Disques --> Titre des colonnes
				htmlPage.append("<tr>");
				htmlPage.append("<td class='alerteDisqueTH' width='25%'>Heure</td>");
				htmlPage.append("<td class='alerteDisqueTH' width='5%'>Systeme</td>");
				htmlPage.append("<td class='alerteDisqueTH' width='15%'>FS</td>");
				htmlPage.append("<td class='alerteDisqueTH' width='15%'>Type</td>");
				htmlPage.append("<td class='alerteDisqueTH' width='15%'>Seuil</td>");
				htmlPage.append("<td class='alerteDisqueTH' width='15%'>Alerte</td>");
				htmlPage.append("</tr>");
				htmlPage.append("<tr><td colspan='6' align='center' class='noData'>Pas d’alertes saisies</td></tr>");
				htmlPage.append("</table>");
				htmlPage.append("</td>");
				htmlPage.append("</tr>");
			} else {
				for (String date : listAlertesDate) {
					htmlPage.append("<tr>");
					htmlPage.append("<td class='title'>" + date + "</td>");
					htmlPage.append("</tr>");
					htmlPage.append("<tr>");
					htmlPage.append("<td>");
					htmlPage.append("<table width='100%' align='center' class='innerTable'>");
					//Alertes Disques --> Titre des colonnes
					htmlPage.append("<tr>");
					htmlPage.append("<td class='alerteDisqueTH' width='25%'>Heure</td>");
					htmlPage.append("<td class='alerteDisqueTH' width='5%'>Systeme</td>");
					htmlPage.append("<td class='alerteDisqueTH' width='15%'>FS</td>");
					htmlPage.append("<td class='alerteDisqueTH' width='15%'>Type</td>");
					htmlPage.append("<td class='alerteDisqueTH' width='15%'>Seuil</td>");
					htmlPage.append("<td class='alerteDisqueTH' width='15%'>Alerte</td>");
					htmlPage.append("</tr>");
					//Alertes Disques --> liste des alertes
					for(Alertes_Disques alerte : alerteMap.get(date)){
						htmlPage.append("<tr>");
						htmlPage.append("<td class='alerteDisqueString'>" + DateService.dateToStr(alerte.getDate(), DateService.p1) + " " + DateService.getTime(alerte.getDate(), DateService.pt1) + "</td>");
						htmlPage.append("<td class='alerteDisqueString'>" + alerte.getSysteme().getNom() + "</td>");
						htmlPage.append("<td class='alerteDisqueString'>" + alerte.getFs() + "</td>");
						htmlPage.append("<td class='alerteDisqueString'>" + alerte.getType().getType() + "</td>");
						htmlPage.append("<td class='alerteDisqueNumber'>" + alerte.getSeuil() + "%</td>");
						htmlPage.append("<td class='alerteDisqueNumber'>" + alerte.getAlerte() + "%</td>");
						htmlPage.append("</tr>");
					}
					htmlPage.append("</table>");
					htmlPage.append("</td>");
					htmlPage.append("</tr>");
				}
			}
		}
		htmlPage.append("<tr><td class='separation'></td></tr>");
		//DEBUT Flux
		if(typeDeBilan.isEtatCFT()){
			htmlPage.append("<tr>");
			htmlPage.append("<td align='center' class='title'>Flux CFT en erreur</td>");
			htmlPage.append("</tr>");
			if (listFluxDate.size() == 0) {
				htmlPage.append("<tr>");
				htmlPage.append("<td>");
				htmlPage.append("<table width='100%' align='center' class='innerTable'>");
				for(Flux_CFT flux : listFlux){
					htmlPage.append("<tr><td class='fluxName' align='center'>" + flux.getLibelle() + "</td></tr>");
					htmlPage.append("<tr><td class='fluxValue' align='center'>Pas d’incident</td></tr>");
				}
				htmlPage.append("</table>");
				htmlPage.append("</td>");
				htmlPage.append("</tr>");
			} else {
				for (String date : listFluxDate) {
					htmlPage.append("<tr>");
					htmlPage.append("<td class='title'>" + date + "</td>");
					htmlPage.append("</tr>");
					htmlPage.append("<tr>");
					htmlPage.append("<td>");
					htmlPage.append("<table width='100%' align='center' class='innerTable'>");
					for(Flux_CFT flux : listFlux){
						htmlPage.append("<tr><td class='fluxName' align='center'>" + flux.getLibelle() + "</td></tr>");
						htmlPage.append("<tr>");
						htmlPage.append("<td class='fluxValue' align='center'>");
						if(fluxMap.get(date).get(flux.getId()) == null)
							htmlPage.append("Pas d’incident");
						else
							htmlPage.append(fluxMap.get(date).get(flux.getId()).replaceAll("\n", "<br/>"));
						htmlPage.append("</td>");
						htmlPage.append("</tr>");
					}
					htmlPage.append("</table>");
					htmlPage.append("</td>");
					htmlPage.append("</tr>");
				}
			}
		}
		htmlPage.append("</table>");
		htmlPage.append("</body>");
		htmlPage.append("</html>");

		return htmlPage.toString();
	}
}
