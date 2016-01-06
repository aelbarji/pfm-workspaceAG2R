package pilotage.bilan;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import pilotage.database.admin.ParametreDatabaseService;
import pilotage.database.applicatif.ApplicatifDatabaseService;
import pilotage.database.applicatif.HardwareSoftwareDatabaseService;
import pilotage.database.astreintes.AstreinteAppelDatabaseService;
import pilotage.database.bilan.AlertesDisquesDatabaseService;
import pilotage.database.bilan.BilanColonnesDatabaseService;
import pilotage.database.bilan.BilanDatabaseService;
import pilotage.database.bilan.BilanEnvoieDatabaseService;
import pilotage.database.bilan.DisqueDatabaseService;
import pilotage.database.bilan.EspacesDisquesDatabaseService;
import pilotage.database.bilan.FluxCFTDatabaseService;
import pilotage.database.bilan.FluxErreurDatabaseService;
import pilotage.database.environnement.EnvironnementTypeDatabaseService;
import pilotage.database.incidents.IncidentsDatabaseService;
import pilotage.database.incidents.IncidentsTypesDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Alertes_Disques;
import pilotage.metier.Applicatifs_Liste;
import pilotage.metier.Astreinte_Appel;
import pilotage.metier.Bilan;
import pilotage.metier.Bilan_Colonnes;
import pilotage.metier.Bilan_Envoie;
import pilotage.metier.Disques;
import pilotage.metier.Environnement_Type;
import pilotage.metier.Espaces_Disques;
import pilotage.metier.Flux_CFT;
import pilotage.metier.Flux_Erreur;
import pilotage.metier.Hardware_Software;
import pilotage.metier.Incidents;
import pilotage.metier.Incidents_Type;
import pilotage.service.constants.PilotageConstants;
import pilotage.service.date.DateService;

public class ShowDetailBilanAction extends AbstractAction {

	private static final long serialVersionUID = -113862836719882036L;
	protected Bilan bilan;
	protected Date selectedDate;
	protected Date dateFin;
	protected Integer selectedType;
	
	protected List<Flux_CFT> listFlux;
	protected List<Disques> listDisques;
	
	protected Integer incidentsAuJour;
	protected Integer incidentsCritiques;
	protected Integer incidentsResolu;
	protected Integer alertesAuJour;
	protected Integer alertesResolu;
	protected Integer incidentsDeProduction = 0;
	protected Integer incidentsAutreEnvir = 0;
	protected Integer alertesDeProduction = 0;
	protected Integer alertesAutreEnvir = 0;
	protected Integer astreintesAppel;
	protected Integer incidentsAuMois=0;
	protected Double incidentsMoyenAuMois=0.0;
	protected Integer alertesAuMois=0;
	protected Double alertesMoyenAuMois=0.0;

	protected Bilan_Envoie typeDeBilan;
	protected List<Environnement_Type> listEnvironType;
	protected List<Alertes_Disques> listAlertes;
	protected Map<Integer, Float[]> espaceDisqueMap;
	protected Map<Integer, String> fluxErreurMap;
	protected Map<Environnement_Type, List<Incidents>> incidentMap;
	protected Environnement_Type incidentDeProduction;
	protected Map<Integer, List<Applicatifs_Liste>> appMap;
	protected Map<Integer, List<Hardware_Software>> hardMap;
	protected Map<Integer, List<Astreinte_Appel>> astreinteMap;
	protected Map<Integer, Date> heureDebutOceorMap;
	protected Map<Integer, Date> heureFinOceorMap;
	protected List<String> listAlertesDate;
	protected List<String> listEspaceDate;
	protected List<String> listFluxDate;
	protected Map<String, List<Alertes_Disques>> alerteMap;
	protected Map<String, Map<Integer, Float[]>> espaceMap;
	protected Map<String, Map<Integer, String>> fluxMap;
	protected List<Bilan_Colonnes> listBilanColonnes;
	protected List<Incidents_Type> listTypeIncident;

	//This part is to make sure the page can receive the constants
	protected String incident_separateur = PilotageConstants.INCIDENT_SEPARATEUR;
	protected Integer bilan_incident_critique = PilotageConstants.BILAN_INCIDENT_CRITIQUE;	

	protected String service;
	
	
	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public Bilan getBilan() {
		return bilan;
	}

	public void setBilan(Bilan bilan) {
		this.bilan = bilan;
	}

	public Map<Integer, List<Hardware_Software>> getHardMap() {
		return hardMap;
	}

	public void setHardMap(Map<Integer, List<Hardware_Software>> hardMap) {
		this.hardMap = hardMap;
	}

	public Date getSelectedDate() {
		return selectedDate;
	}

	public void setSelectedDate(Date selectedDate) {
		this.selectedDate = selectedDate;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public Integer getSelectedType() {
		return selectedType;
	}

	public void setSelectedType(Integer selectedType) {
		this.selectedType = selectedType;
	}

	public List<Flux_CFT> getListFlux() {
		return listFlux;
	}

	public void setListFlux(List<Flux_CFT> listFlux) {
		this.listFlux = listFlux;
	}

	public List<Disques> getListDisques() {
		return listDisques;
	}

	public void setListDisques(List<Disques> listDisques) {
		this.listDisques = listDisques;
	}

	public Integer getIncidentsAuJour() {
		return incidentsAuJour;
	}

	public void setIncidentsAuJour(Integer incidentsAuJour) {
		this.incidentsAuJour = incidentsAuJour;
	}

	public Integer getIncidentsCritiques() {
		return incidentsCritiques;
	}

	public void setIncidentsCritiques(Integer incidentsCritiques) {
		this.incidentsCritiques = incidentsCritiques;
	}

	public Integer getIncidentsResolu() {
		return incidentsResolu;
	}

	public void setIncidentsResolu(Integer incidentsResolu) {
		this.incidentsResolu = incidentsResolu;
	}

	public Integer getAstreintesAppel() {
		return astreintesAppel;
	}

	public void setAstreintesAppel(Integer astreintesAppel) {
		this.astreintesAppel = astreintesAppel;
	}

	public Integer getIncidentsAuMois() {
		return incidentsAuMois;
	}

	public void setIncidentsAuMois(Integer incidentsAuMois) {
		this.incidentsAuMois = incidentsAuMois;
	}

	public Double getIncidentsMoyenAuMois() {
		return incidentsMoyenAuMois;
	}

	public List<Incidents_Type> getListTypeIncident() {
		return listTypeIncident;
	}

	public void setListTypeIncident(List<Incidents_Type> listTypeIncident) {
		this.listTypeIncident = listTypeIncident;
	}

	public void setIncidentsMoyenAuMois(Double incidentsMoyenAuMois) {
		this.incidentsMoyenAuMois = incidentsMoyenAuMois;
	}

	public Bilan_Envoie getTypeDeBilan() {
		return typeDeBilan;
	}

	public void setTypeDeBilan(Bilan_Envoie typeDeBilan) {
		this.typeDeBilan = typeDeBilan;
	}

	public List<Environnement_Type> getListEnvironType() {
		return listEnvironType;
	}

	public void setListEnvironType(List<Environnement_Type> listEnvironType) {
		this.listEnvironType = listEnvironType;
	}

	public List<Alertes_Disques> getListAlertes() {
		return listAlertes;
	}

	public void setListAlertes(List<Alertes_Disques> listAlertes) {
		this.listAlertes = listAlertes;
	}

	public Map<Integer, Float[]> getEspaceDisqueMap() {
		return espaceDisqueMap;
	}

	public void setEspaceDisqueMap(Map<Integer, Float[]> espaceDisqueMap) {
		this.espaceDisqueMap = espaceDisqueMap;
	}

	public Map<Integer, String> getFluxErreurMap() {
		return fluxErreurMap;
	}

	public void setFluxErreurMap(Map<Integer, String> fluxErreurMap) {
		this.fluxErreurMap = fluxErreurMap;
	}

	public Map<Environnement_Type, List<Incidents>> getIncidentMap() {
		return incidentMap;
	}

	public void setIncidentMap(Map<Environnement_Type, List<Incidents>> incidentMap) {
		this.incidentMap = incidentMap;
	}

	public Environnement_Type getIncidentDeProduction() {
		return incidentDeProduction;
	}

	public void setIncidentDeProduction(Environnement_Type incidentDeProduction) {
		this.incidentDeProduction = incidentDeProduction;
	}

	public Map<Integer, List<Applicatifs_Liste>> getAppMap() {
		return appMap;
	}

	public void setAppMap(Map<Integer, List<Applicatifs_Liste>> appMap) {
		this.appMap = appMap;
	}

	public Map<Integer, List<Astreinte_Appel>> getAstreinteMap() {
		return astreinteMap;
	}

	public void setAstreinteMap(Map<Integer, List<Astreinte_Appel>> astreinteMap) {
		this.astreinteMap = astreinteMap;
	}
	
	public Map<Integer, Date> getHeureDebutOceorMap() {
		return heureDebutOceorMap;
	}

	public void setHeureDebutOceorMap(Map<Integer, Date> heureDebutOceorMap) {
		this.heureDebutOceorMap = heureDebutOceorMap;
	}
	
	public Map<Integer, Date> getHeureFinOceorMap() {
		return heureFinOceorMap;
	}

	public void setHeureFinOceorMap(Map<Integer, Date> heureFinOceorMap) {
		this.heureFinOceorMap = heureFinOceorMap;
	}
	
	public String getIncident_separateur() {
		return incident_separateur;
	}

	public void setIncident_separateur(String incident_separateur) {
		this.incident_separateur = incident_separateur;
	}

	public Integer getBilan_incident_critique() {
		return bilan_incident_critique;
	}

	public void setBilan_incident_critique(Integer bilan_incident_critique) {
		this.bilan_incident_critique = bilan_incident_critique;
	}

	public List<String> getListAlertesDate() {
		return listAlertesDate;
	}

	public void setListAlertesDate(List<String> listAlertesDate) {
		this.listAlertesDate = listAlertesDate;
	}

	public List<String> getListEspaceDate() {
		return listEspaceDate;
	}

	public void setListEspaceDate(List<String> listEspaceDate) {
		this.listEspaceDate = listEspaceDate;
	}

	public List<String> getListFluxDate() {
		return listFluxDate;
	}

	public void setListFluxDate(List<String> listFluxDate) {
		this.listFluxDate = listFluxDate;
	}

	public Map<String, List<Alertes_Disques>> getAlerteMap() {
		return alerteMap;
	}

	public void setAlerteMap(Map<String, List<Alertes_Disques>> alerteMap) {
		this.alerteMap = alerteMap;
	}

	public Map<String, Map<Integer, Float[]>> getEspaceMap() {
		return espaceMap;
	}

	public void setEspaceMap(Map<String, Map<Integer, Float[]>> espaceMap) {
		this.espaceMap = espaceMap;
	}

	public Map<String, Map<Integer, String>> getFluxMap() {
		return fluxMap;
	}

	public void setFluxMap(Map<String, Map<Integer, String>> fluxMap) {
		this.fluxMap = fluxMap;
	}

	public List<Bilan_Colonnes> getListBilanColonnes() {
		return listBilanColonnes;
	}

	public void setListBilanColonnes(List<Bilan_Colonnes> listBilanColonnes) {
		this.listBilanColonnes = listBilanColonnes;
	}

	public Integer getAlertesAuJour() {
		return alertesAuJour;
	}

	public void setAlertesAuJour(Integer alertesAuJour) {
		this.alertesAuJour = alertesAuJour;
	}

	public Integer getAlertesResolu() {
		return alertesResolu;
	}

	public void setAlertesResolu(Integer alertesResolu) {
		this.alertesResolu = alertesResolu;
	}

	public Integer getAlertesDeProduction() {
		return alertesDeProduction;
	}

	public void setAlertesDeProduction(Integer alertesDeProduction) {
		this.alertesDeProduction = alertesDeProduction;
	}

	public Integer getAlertesAutreEnvir() {
		return alertesAutreEnvir;
	}

	public void setAlertesAutreEnvir(Integer alertesAutreEnvir) {
		this.alertesAutreEnvir = alertesAutreEnvir;
	}

	public Integer getAlertesAuMois() {
		return alertesAuMois;
	}

	public void setAlertesAuMois(Integer alertesAuMois) {
		this.alertesAuMois = alertesAuMois;
	}

	public Double getAlertesMoyenAuMois() {
		return alertesMoyenAuMois;
	}

	public void setAlertesMoyenAuMois(Double alertesMoyenAuMois) {
		this.alertesMoyenAuMois = alertesMoyenAuMois;
	}

	public Integer getIncidentsDeProduction() {
		return incidentsDeProduction;
	}

	public void setIncidentsDeProduction(Integer incidentsDeProduction) {
		this.incidentsDeProduction = incidentsDeProduction;
	}

	public Integer getIncidentsAutreEnvir() {
		return incidentsAutreEnvir;
	}

	public void setIncidentsAutreEnvir(Integer incidentsAutreEnvir) {
		this.incidentsAutreEnvir = incidentsAutreEnvir;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		try{
			bilan = BilanDatabaseService.get(selectedDate);
			typeDeBilan = BilanEnvoieDatabaseService.get(selectedType);			
			service = ParametreDatabaseService.get(PilotageConstants.BILAN_SERVICE).getValeur();
			listBilanColonnes = BilanColonnesDatabaseService.getByBilan(selectedType);
			listTypeIncident = IncidentsTypesDatabaseService.getAll();
			astreintesAppel = 0;
			
			Integer[] listEnvironnementID = null; 
			String[] listEnvironnementStringID = typeDeBilan.getClauseSelect() == null || typeDeBilan.getClauseSelect().equals("") ? null : typeDeBilan.getClauseSelect().split(PilotageConstants.INCIDENT_SEPARATEUR);
			if(listEnvironnementStringID != null){
				listEnvironnementID = new Integer[listEnvironnementStringID.length];
				for(int i = 0; i < listEnvironnementID.length; ++i){
						listEnvironnementID[i] = Integer.parseInt(listEnvironnementStringID[i]);
				}
			}
			
			if(typeDeBilan.isActionEPI()){
				//Informations résumé
				incidentsAuJour 		= IncidentsDatabaseService.getNbNew(selectedDate, listEnvironnementID, new Integer[]{PilotageConstants.CRITIQUE, PilotageConstants.INCIDENT}, null);
				incidentsCritiques		= IncidentsDatabaseService.getNbNew(selectedDate, listEnvironnementID, new Integer[]{PilotageConstants.CRITIQUE}, null);
				incidentsResolu 		= IncidentsDatabaseService.getNumResoluByDate(selectedDate, listEnvironnementID, new Integer[]{PilotageConstants.CRITIQUE, PilotageConstants.INCIDENT});
				alertesAuJour 			= IncidentsDatabaseService.getNbNew(selectedDate, listEnvironnementID, new Integer[]{PilotageConstants.ALERTE}, null);
				alertesResolu			= IncidentsDatabaseService.getNumResoluByDate(selectedDate, listEnvironnementID, new Integer[]{PilotageConstants.ALERTE});
				//astreintesAppel 		= IncidentsDatabaseService.getNumAstreintesAppel(selectedDate, listEnvironnementID);
				incidentsAuMois 		= IncidentsDatabaseService.getNbSinceFirstOfMonth(selectedDate, listEnvironnementID, new Integer[]{PilotageConstants.CRITIQUE, PilotageConstants.INCIDENT});
				incidentsMoyenAuMois 	= Math.round(incidentsAuMois*100/DateService.getDayOfMonth(selectedDate))/100d;
				incidentsDeProduction	= IncidentsDatabaseService.getNbNew(selectedDate, listEnvironnementID, new Integer[]{PilotageConstants.CRITIQUE, PilotageConstants.INCIDENT}, new Integer[]{EnvironnementTypeDatabaseService.getPrincipal().getId()});
				incidentsAutreEnvir		= incidentsAuJour - incidentsDeProduction;
				alertesDeProduction		= IncidentsDatabaseService.getNbNew(selectedDate, listEnvironnementID, new Integer[]{PilotageConstants.ALERTE}, new Integer[]{EnvironnementTypeDatabaseService.getPrincipal().getId()});
				alertesAutreEnvir		= alertesAuJour - alertesDeProduction;
				alertesAuMois			= IncidentsDatabaseService.getNbSinceFirstOfMonth(selectedDate, listEnvironnementID, new Integer[]{PilotageConstants.ALERTE});
				alertesMoyenAuMois		= Math.round(alertesAuMois*100/DateService.getDayOfMonth(selectedDate))/100d;
			}

			
			//type d'environnement
			listEnvironType = EnvironnementTypeDatabaseService.getAll();
			for(Environnement_Type typeEnv : listEnvironType){
				if(typeEnv.getId().equals(EnvironnementTypeDatabaseService.getPrincipal().getId())){
					incidentDeProduction = typeEnv;
					break;
				}
			}
			listEnvironType.remove(incidentDeProduction);
			
			
			//liste des incidents
			List<Incidents> listInci = new ArrayList<Incidents>();
			if (dateFin == null) {
				listInci = IncidentsDatabaseService.getByDate(selectedDate, listEnvironnementID);
			} else {
				listInci = IncidentsDatabaseService.getByDatePeriode(selectedDate, dateFin, listEnvironnementID);
			}
			incidentMap = new HashMap<Environnement_Type, List<Incidents>>();
			for(Incidents incident : listInci){
				List<Incidents> listIncidentDuType = incidentMap.get(incident.getDomaine().getType());
				if(listIncidentDuType == null)
					listIncidentDuType = new ArrayList<Incidents>();
				listIncidentDuType.add(incident);
				
				incidentMap.put(incident.getDomaine().getType(), listIncidentDuType);
			}
			
			//liste des applications par incident et liste des astreintes par incident
			appMap = new HashMap<Integer, List<Applicatifs_Liste>>();
			hardMap = new HashMap<Integer, List<Hardware_Software>>();
			
			astreinteMap = new HashMap<Integer, List<Astreinte_Appel>>();
			heureDebutOceorMap = new HashMap<Integer, Date>();
			heureFinOceorMap = new HashMap<Integer, Date>();
			for(Incidents incident : listInci){
				String[] listAppID = incident.getApplicatif().split(PilotageConstants.INCIDENT_SEPARATEUR);
				List<Integer> listAppliID = new ArrayList<Integer>();
				for(String appID : listAppID){
					try{
						listAppliID.add(Integer.parseInt(appID));
					}
					catch (Exception e) {
					}
				}
				List<Applicatifs_Liste> listAppliParIncident = ApplicatifDatabaseService.getMultiple(listAppliID);
				appMap.put(incident.getId(), listAppliParIncident);
				
				if(incident.getHard_software() != null){
					String[] listHardID = incident.getHard_software().split(PilotageConstants.INCIDENT_SEPARATEUR);
					List<Integer> listHardSoftID = new ArrayList<Integer>();
					for(String hardID : listHardID){
						try{
							listHardSoftID.add(Integer.parseInt(hardID));
						}
						catch (Exception e) {
						}
					}
					List<Hardware_Software> listHardParIncident = HardwareSoftwareDatabaseService.getMultiple(listHardSoftID);
					hardMap.put(incident.getId(), listHardParIncident);
				}
				
				List<Astreinte_Appel> listAstreinteParIncident = AstreinteAppelDatabaseService.getByIncident(incident.getId());
				astreinteMap.put(incident.getId(), listAstreinteParIncident);
				astreintesAppel = astreintesAppel + listAstreinteParIncident.size();
				
				if (incident.getOceor().getId() != 0) {
					TimeZone heureOceor = TimeZone.getTimeZone(incident.getOceor().getTimezone());
					Date DateHeureDebutOceor = DateService.convertFromTimeZoneToTimeZone(incident.getDateDebutGmt(),null,heureOceor);
					heureDebutOceorMap.put(incident.getId(),DateHeureDebutOceor);
					Date DateHeureFinOceor = DateService.convertFromTimeZoneToTimeZone(incident.getDateFinGmt(),null,heureOceor);
					heureFinOceorMap.put(incident.getId(),DateHeureFinOceor);
				} else {
					heureDebutOceorMap.put(incident.getId(),null);
					heureFinOceorMap.put(incident.getId(),null);					
				}
				
			}
			
			if(typeDeBilan.isDisknonOCEOR() || typeDeBilan.isEspaceDisk()){
				//liste des disques
				listDisques = DisqueDatabaseService.getAll();
				
				//liste des espaces disques
				List<Espaces_Disques> listEspaceDisque = new ArrayList<Espaces_Disques>();
				if (dateFin == null) {
					listEspaceDisque = EspacesDisquesDatabaseService.getAllByDate(selectedDate);
				} else {
					listEspaceDisque = EspacesDisquesDatabaseService.getAllByDatePeriode(selectedDate, dateFin);
				}
				espaceMap = new HashMap<String, Map<Integer, Float[]>>();
				listEspaceDate = new ArrayList<String>();
				for (Espaces_Disques ed : listEspaceDisque) {
					if (!listEspaceDate.contains(DateService.dateToStr(ed.getDate(), DateService.p1))) {
						listEspaceDate.add(DateService.dateToStr(ed.getDate(), DateService.p1));
						List<Espaces_Disques> listEspaceDisqueHier = EspacesDisquesDatabaseService.getAllByDate(DateService.addDays(ed.getDate(), -1));
						List<Espaces_Disques> listEspaceDisqueAjd = EspacesDisquesDatabaseService.getAllByDate(ed.getDate());
						espaceDisqueMap = new HashMap<Integer, Float[]>();
						for (Espaces_Disques edH : listEspaceDisqueHier) {
							Float[] values = espaceDisqueMap.get(edH.getDisque().getId());
							if(values == null){
								values = new Float[3];
							}
							values[1] = edH.getEspace();
			
							espaceDisqueMap.put(edH.getDisque().getId(), values);
						}
						for (Espaces_Disques edA : listEspaceDisqueAjd) {
							Float[] values = espaceDisqueMap.get(edA.getDisque().getId());
							if(values == null){
								values = new Float[3];
							}
							values[0] = edA.getSeuil();
							values[2] = edA.getEspace();
			
							espaceDisqueMap.put(edA.getDisque().getId(), values);
						}
						espaceMap.put(DateService.dateToStr(ed.getDate(), DateService.p1), espaceDisqueMap);
					}
				}
			}
			
			//liste des alertes disques
			if (dateFin == null) {
				listAlertes = AlertesDisquesDatabaseService.getByDate(selectedDate);
			} else {
				listAlertes = AlertesDisquesDatabaseService.getByDatePeriode(selectedDate, dateFin);
			}
			alerteMap = new HashMap<String, List<Alertes_Disques>>();
			listAlertesDate = new ArrayList<String>();
			for (Alertes_Disques ad : listAlertes) {
				alerteMap.put(DateService.dateToStr(ad.getDate(), DateService.p1), AlertesDisquesDatabaseService.getByDate(ad.getDate()));
				if (!listAlertesDate.contains(DateService.dateToStr(ad.getDate(), DateService.p1))) {
					listAlertesDate.add(DateService.dateToStr(ad.getDate(), DateService.p1));
				}
			}
			
			if(typeDeBilan.isEtatCFT()){
				//liste des flux CFT
				listFlux = FluxCFTDatabaseService.getAll();
				
				//liste des flux en erreur
				List<Flux_Erreur> listeFluxErreur = new ArrayList<Flux_Erreur>();
				if (dateFin == null) {
					listeFluxErreur = FluxErreurDatabaseService.getAllByDate(selectedDate);
				} else {
					listeFluxErreur = FluxErreurDatabaseService.getAllByDatePeriode(selectedDate, dateFin);
				}
				fluxMap = new HashMap<String, Map<Integer, String>>();
				listFluxDate = new ArrayList<String>();
				for (Flux_Erreur fe : listeFluxErreur) {
					if (!listFluxDate.contains(DateService.dateToStr(fe.getDate(), DateService.p1))) {
						listFluxDate.add(DateService.dateToStr(fe.getDate(), DateService.p1));
						fluxErreurMap = new HashMap<Integer, String>();
						for (Flux_Erreur fe2 : FluxErreurDatabaseService.getAllByDate(fe.getDate())) {
							fluxErreurMap.put(fe2.getFlux().getId(), fe2.getErreur());
						}
						fluxMap.put(DateService.dateToStr(fe.getDate(), DateService.p1), fluxErreurMap);
					}
				}
			}
	
			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Affichage du détail d'un bilan - ", e);
			return ERROR;
		}
	}

}
