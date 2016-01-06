package pilotage.statistiques;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import pilotage.database.statistiques.StatistiqueIncidentDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Incidents;
import pilotage.service.constants.PilotageConstants;
import pilotage.service.date.DateService;

public class ShowDetailDomaineAction extends AbstractAction {

	private static final long serialVersionUID = 7288122669833126742L;
	private String selectMois;
	private String selectAnnee;
	private String selectSemaine;
	private Date dateSelectMois;
	private String environnement;
	private int environnementId;
	private Date monday;
	private Date sunday;

	private int nIncident = 0;
	private int nIncidentCritique = 0;
	private int nIncidentCoupureService = 0;
	private int nAppelAstreintes = 0;
	
	List<Incidents> listIncidentDomaine = new ArrayList<Incidents>();

	public String getSelectMois() {
		return selectMois;
	}

	public void setSelectMois(String selectMois) {
		this.selectMois = selectMois;
	}

	public String getSelectAnnee() {
		return selectAnnee;
	}

	public void setSelectAnnee(String selectAnnee) {
		this.selectAnnee = selectAnnee;
	}

	public Date getDateSelectMois() {
		return dateSelectMois;
	}

	public void setDateSelectMois(Date dateSelectMois) {
		this.dateSelectMois = dateSelectMois;
	}
	
	public String getEnvironnement() {
		return environnement;
	}

	public void setEnvironnement(String environnement) {
		this.environnement = environnement;
	}

	public int getEnvironnementId() {
		return environnementId;
	}

	public void setEnvironnementId(int environnementId) {
		this.environnementId = environnementId;
	}

	public int getnIncident() {
		return nIncident;
	}

	public void setnIncident(int nIncident) {
		this.nIncident = nIncident;
	}

	public int getnIncidentCritique() {
		return nIncidentCritique;
	}

	public void setnIncidentCritique(int nIncidentCritique) {
		this.nIncidentCritique = nIncidentCritique;
	}

	public int getnIncidentCoupureService() {
		return nIncidentCoupureService;
	}

	public void setnIncidentCoupureService(int nIncidentCoupureService) {
		this.nIncidentCoupureService = nIncidentCoupureService;
	}

	public int getnAppelAstreintes() {
		return nAppelAstreintes;
	}

	public void setnAppelAstreintes(int nAppelAstreintes) {
		this.nAppelAstreintes = nAppelAstreintes;
	}

	public String getSelectSemaine() {
		return selectSemaine;
	}

	public void setSelectSemaine(String selectSemaine) {
		this.selectSemaine = selectSemaine;
	}

	public Date getMonday() {
		return monday;
	}

	public void setMonday(Date monday) {
		this.monday = monday;
	}

	public Date getSunday() {
		return sunday;
	}

	public void setSunday(Date sunday) {
		this.sunday = sunday;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		try {
			
			if(selectSemaine == null || selectSemaine.equals("")){
				String numMois = StatistiqueIncidentDatabaseService.convMoisEnCour(selectMois, selectAnnee);
				try {
					dateSelectMois = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(numMois);
				} catch (ParseException e) {
					dateSelectMois = new Date();
				}
				
				listIncidentDomaine = StatistiqueIncidentDatabaseService.getIncidentDomaineDeMois(dateSelectMois, environnementId);
			}
			else if(selectMois == null || selectMois.equals("")){
				String debutAnneeStr = "01/01/" + selectAnnee;
				Date debutAnnee = DateService.strToDate(debutAnneeStr);
				
				monday = DateService.getMonday(Integer.parseInt(selectSemaine), debutAnnee);
				sunday = DateService.getWeekEnd(monday);
				
				listIncidentDomaine = StatistiqueIncidentDatabaseService.getIncidentDomaineDePeriode(monday, sunday, environnementId);
			}

			for (Incidents incident : listIncidentDomaine) {
				List<String> listAstreintes = Arrays.asList(incident.getAstreinte().split(PilotageConstants.INCIDENT_SEPARATEUR));
				Iterator<String> itAstreintes = listAstreintes.iterator();
				while(itAstreintes.hasNext()){
					String a = itAstreintes.next();
					if (a != ""){
						nAppelAstreintes++;
					}
				}
				nIncident++;
				if (incident.getCoupure() == 1){
					nIncidentCoupureService++;
				}
				if (PilotageConstants.BILAN_INCIDENT_CRITIQUE.equals(incident.getType().getId())){
					nIncidentCritique++;
				}
			}
		
			return SUCCESS;
		} 
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Statistiques incidents pour un mois et un domaine - ", e);
			return ERROR;
		}
	}

}
