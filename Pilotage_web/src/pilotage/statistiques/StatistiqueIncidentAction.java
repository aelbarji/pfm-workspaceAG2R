package pilotage.statistiques;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;


import pilotage.database.astreintes.AstreinteAppelDatabaseService;
import pilotage.database.environnement.EnvironnementTypeDatabaseService;
import pilotage.database.statistiques.StatistiqueIncidentDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Applicatifs_Liste;
import pilotage.metier.Environnement;
import pilotage.metier.Machines_Liste;
import pilotage.metier.Services_Liste;
import pilotage.service.date.DateService;
import pilotage.service.statistique.StatistiquesService;

public class StatistiqueIncidentAction extends AbstractAction {

	private String INCIDENT_TITLE = "Incidents";
	private String INCIDENT_PRO_TITLE = "Incidents de l'environnement ";
	private String INCIDENT_AUTRE_TITLE = "Incidents Autre types";
	private String TOP_FIVE_SERVER_DE_MOIS_TITLE = "Top 5 Serveur de ce Mois";
	private String TOP_FIVE_SERVICE_DE_MOIS_TITLE = "Top 5 Service de ce Mois";
	private String TOP_FIVE_DOMAINE_DE_MOIS_TITLE = "Top 5 Domaine de ce Mois";
	private String TOP_FIVE_APP_DE_MOIS_TITLE = "Top 5 Application de ce Mois";
	private String TOP_FIVE_SERVER_DER30J_TITLE = "Top 5 Serveur les 30 Derniers Jours";
	private String TOP_FIVE_SERVICE_DER30J_TITLE = "Top 5 Service les 30 Derniers Jours";
	private String TOP_FIVE_DOMAINE_DER30J_TITLE = "Top 5 Domaine les 30 Derniers Jours";
	private String TOP_FIVE_APP_DE_DER30J_TITLE = "Top 5 Application les 30 Derniers Jours";
	
	private String URL_CHART = "drawBarChartAction.action?";

	private static final long serialVersionUID = -1492762476828824665L;
	private String selectedDateStr;
	private Date selectedDate;
	private Date monday;
	private Date sunday;
	private String enviroTypePrincipale;
	
	private String incidentURL;
	private String incidentProURL;
	private String incidentAutreURL;
	private String topFiveServerDer30JURL;
	private String topFiveAppDer30JURL;
	private String topFiveEnviroDer30JURL;
	private String topFiveServiceDer30JURL;

	private String topFiveServerDeMoisURL;
	private String topFiveAppDeMoisURL;
	private String topFiveEnviroDeMoisURL;
	private String topFiveServiceDeMoisURL;

	private Double moyenneSemain;
	private Double moyenneMois;
	
	private Long nbIncidentsMois;
	private Long nbIncidentsCritiquesMois;
	private Long nbIncidentsCoupuresMois;
	private Long nbAppelsAstreinteMois;
	private Long nbIncidentsCritiquesAvecCoupureMois;
	private Long nbIncidentsResoluPilotageMois;
	
	private Long incidentJour;
	private Long incidentSemaine;
	private Long incidentMois;
	private Long incident30jours;
	
	private Long incidentEnvirPrincJour;
	private Long incidentEnvirPrincSemaine;
	private Long incidentEnvirPrincMois;
	
	private Long incidentAutreJour;
	private Long incidentAutreSemaine;
	private Long incidentAutreMois;
	
	private String moisEnCourStr;
	private String anneeEnCourStr;
	private Date selectMois;
	
	private Map<Integer, String> mapSemaine;
	private String selectedAnneeS;
	private Integer selectedSemaine;
	private Long nbIncidentsSemaine;
	private Long nbIncidentsCritiquesSemaine;
	private Long nbIncidentsCoupuresSemaine;
	private Long nbAppelsAstreinteSemaine;
	private Long nbIncidentsCritiquesAvecCoupureSemaine;
	private Long nbIncidentsResoluPilotageSemaine;
	
	private LinkedHashMap<Environnement, Integer> domaineDeMoisList;
	private LinkedHashMap<Environnement, Integer> domaineDeSemaineList;

	public LinkedHashMap<Environnement, Integer> getDomaineDeMoisList() {
		return domaineDeMoisList;
	}

	public void setDomaineDeMoisList(LinkedHashMap<Environnement, Integer> domaineDeMoisList) {
		this.domaineDeMoisList = domaineDeMoisList;
	}

	public Long getIncidentJour() {
		return incidentJour;
	}
	
	
	public String getEnviroTypePrincipale() {
		enviroTypePrincipale = EnvironnementTypeDatabaseService.getPrincipal().getType();
		return enviroTypePrincipale;
	}


	public void setIncidentJour(Long incidentJour) {
		this.incidentJour = incidentJour;
	}

	public Long getIncidentSemaine() {
		return incidentSemaine;
	}

	public void setIncidentSemaine(Long incidentSemaine) {
		this.incidentSemaine = incidentSemaine;
	}

	public Long getIncidentMois() {
		return incidentMois;
	}

	public void setIncidentMois(Long incidentMois) {
		this.incidentMois = incidentMois;
	}

	public Long getIncident30jours() {
		return incident30jours;
	}

	public void setIncident30jours(Long incident30jours) {
		this.incident30jours = incident30jours;
	}

	public Long getIncidentProdJour() {
		return incidentEnvirPrincJour;
	}

	public void setIncidentProdJour(Long incidentProdJour) {
		this.incidentEnvirPrincJour = incidentProdJour;
	}

	public Long getIncidentProdSemaine() {
		return incidentEnvirPrincSemaine;
	}

	public void setIncidentProdSemaine(Long incidentProdSemaine) {
		this.incidentEnvirPrincSemaine = incidentProdSemaine;
	}

	public Long getNbIncidentsCritiquesAvecCoupureSemaine() {
		return nbIncidentsCritiquesAvecCoupureSemaine;
	}

	public void setNbIncidentsCritiquesAvecCoupureSemaine(
			Long nbIncidentsCritiquesAvecCoupureSemaine) {
		this.nbIncidentsCritiquesAvecCoupureSemaine = nbIncidentsCritiquesAvecCoupureSemaine;
	}

	public Long getNbIncidentsResoluPilotageSemaine() {
		return nbIncidentsResoluPilotageSemaine;
	}

	public void setNbIncidentsResoluPilotageSemaine(
			Long nbIncidentsResoluPilotageSemaine) {
		this.nbIncidentsResoluPilotageSemaine = nbIncidentsResoluPilotageSemaine;
	}

	public Long getIncidentProdMois() {
		return incidentEnvirPrincMois;
	}

	public void setIncidentProdMois(Long incidentProdMois) {
		this.incidentEnvirPrincMois = incidentProdMois;
	}

	public Long getIncidentAutreJour() {
		return incidentAutreJour;
	}

	public void setIncidentAutreJour(Long incidentAutreJour) {
		this.incidentAutreJour = incidentAutreJour;
	}

	public Long getIncidentAutreSemaine() {
		return incidentAutreSemaine;
	}

	public void setIncidentAutreSemaine(Long incidentAutreSemaine) {
		this.incidentAutreSemaine = incidentAutreSemaine;
	}

	public Long getIncidentAutreMois() {
		return incidentAutreMois;
	}

	public void setIncidentAutreMois(Long incidentAutreMois) {
		this.incidentAutreMois = incidentAutreMois;
	}

	/**
	 * @return the moyenneSemain
	 */
	public Double getMoyenneSemain() {
		return moyenneSemain;
	}

	/**
	 * @param moyenneSemain
	 *            the moyenneSemain to set
	 */
	public void setMoyenneSemain(Double moyenneSemain) {
		this.moyenneSemain = moyenneSemain;
	}

	/**
	 * @return the moyenneMois
	 */
	public Double getMoyenneMois() {
		return moyenneMois;
	}

	/**
	 * @param moyenneMois
	 *            the moyenneMois to set
	 */
	public void setMoyenneMois(Double moyenneMois) {
		this.moyenneMois = moyenneMois;
	}

	/**
	 * @return the topFiveServerDer30JURL
	 */
	public String getTopFiveServerDer30JURL() {
		return topFiveServerDer30JURL;
	}

	/**
	 * @return the topFiveAppDer30JURL
	 */
	public String getTopFiveAppDer30JURL() {
		return topFiveAppDer30JURL;
	}

	/**
	 * @return the topFiveEnviroDer30JURL
	 */
	public String getTopFiveEnviroDer30JURL() {
		return topFiveEnviroDer30JURL;
	}

	/**
	 * @return the topFiveServiceDer30JURL
	 */
	public String getTopFiveServiceDer30JURL() {
		return topFiveServiceDer30JURL;
	}

	/**
	 * @return the topFiveServerDeMoisURL
	 */
	public String getTopFiveServerDeMoisURL() {
		return topFiveServerDeMoisURL;
	}

	/**
	 * @return the topFiveAppDeMoisURL
	 */
	public String getTopFiveAppDeMoisURL() {
		return topFiveAppDeMoisURL;
	}

	/**
	 * @return the topFiveEnviroDeMoisURL
	 */
	public String getTopFiveEnviroDeMoisURL() {
		return topFiveEnviroDeMoisURL;
	}

	/**
	 * @return the topFiveServiceDeMoisURL
	 */
	public String getTopFiveServiceDeMoisURL() {
		return topFiveServiceDeMoisURL;
	}

	/**
	 * @return the selectedDate
	 */
	public Date getSelectedDate() {
		return selectedDate;
	}

	/**
	 * @param selectedDate
	 *            the selectedDate to set
	 */
	public void setSelectedDate(Date selectedDate) {
		this.selectedDate = selectedDate;
	}

	/**
	 * @return the selectedDateStr
	 */
	public String getSelectedDateStr() {
		return selectedDateStr;
	}

	/**
	 * @param selectedDateStr
	 *            the selectedDateStr to set
	 */
	public void setSelectedDateStr(String selectedDateStr) {
		this.selectedDateStr = selectedDateStr;
	}

	/**
	 * @return the incidentURL
	 */
	public String getIncidentURL() {
		return incidentURL;
	}

	/**
	 * @return the incidentProURL
	 */
	public String getIncidentProURL() {
		return incidentProURL;
	}

	/**
	 * @return the incidentAutreURL
	 */
	public String getIncidentAutreURL() {
		return incidentAutreURL;
	}

	/**
	 * @return the moisDomaine
	 */
	public String getMoisEnCourStr() {
		return moisEnCourStr;
	}

	public void setMoisEnCourStr(String moisEnCourStr) {
		this.moisEnCourStr = moisEnCourStr;
	}

	public String getAnneeEnCourStr() {
		return anneeEnCourStr;
	}

	public void setAnneeEnCourStr(String anneeEnCourStr) {
		this.anneeEnCourStr = anneeEnCourStr;
	}

	public Date getSelectMois() {
		return selectMois;
	}

	public void setSelectMois(Date selectMois) {
		this.selectMois = selectMois;
	}

	public Long getNbIncidentsMois() {
		return nbIncidentsMois;
	}

	public void setNbIncidentsMois(Long nbIncidentsMois) {
		this.nbIncidentsMois = nbIncidentsMois;
	}

	public Long getNbIncidentsCritiquesMois() {
		return nbIncidentsCritiquesMois;
	}

	public void setNbIncidentsCritiquesMois(Long nbIncidentsCritiquesMois) {
		this.nbIncidentsCritiquesMois = nbIncidentsCritiquesMois;
	}

	public Long getNbIncidentsCoupuresMois() {
		return nbIncidentsCoupuresMois;
	}

	public void setNbIncidentsCoupuresMois(Long nbIncidentsCoupuresMois) {
		this.nbIncidentsCoupuresMois = nbIncidentsCoupuresMois;
	}

	public Long getNbAppelsAstreinteMois() {
		return nbAppelsAstreinteMois;
	}

	public void setNbAppelsAstreinteMois(Long nbAppelsAstreinteMois) {
		this.nbAppelsAstreinteMois = nbAppelsAstreinteMois;
	}

	public Map<Integer, String> getMapSemaine() {
		return mapSemaine;
	}

	public void setMapSemaine(Map<Integer, String> mapSemaine) {
		this.mapSemaine = mapSemaine;
	}

	public String getSelectedAnneeS() {
		return selectedAnneeS;
	}

	public void setSelectedAnneeS(String selectedAnneeS) {
		this.selectedAnneeS = selectedAnneeS;
	}

	public Integer getSelectedSemaine() {
		return selectedSemaine;
	}

	public void setSelectedSemaine(Integer selectedSemaine) {
		this.selectedSemaine = selectedSemaine;
	}

	public Long getNbIncidentsSemaine() {
		return nbIncidentsSemaine;
	}

	public void setNbIncidentsSemaine(Long nbIncidentsSemaine) {
		this.nbIncidentsSemaine = nbIncidentsSemaine;
	}

	public Long getNbIncidentsCritiquesSemaine() {
		return nbIncidentsCritiquesSemaine;
	}

	public void setNbIncidentsCritiquesSemaine(Long nbIncidentsCritiquesSemaine) {
		this.nbIncidentsCritiquesSemaine = nbIncidentsCritiquesSemaine;
	}

	public Long getNbIncidentsCoupuresSemaine() {
		return nbIncidentsCoupuresSemaine;
	}

	public void setNbIncidentsCoupuresSemaine(Long nbIncidentsCoupuresSemaine) {
		this.nbIncidentsCoupuresSemaine = nbIncidentsCoupuresSemaine;
	}

	public Long getNbAppelsAstreinteSemaine() {
		return nbAppelsAstreinteSemaine;
	}

	public void setNbAppelsAstreinteSemaine(Long nbAppelsAstreinteSemaine) {
		this.nbAppelsAstreinteSemaine = nbAppelsAstreinteSemaine;
	}

	public Long getNbIncidentsCritiquesAvecCoupureMois() {
		return nbIncidentsCritiquesAvecCoupureMois;
	}

	public void setNbIncidentsCritiquesAvecCoupureMois(
			Long nbIncidentsCritiquesAvecCoupureMois) {
		this.nbIncidentsCritiquesAvecCoupureMois = nbIncidentsCritiquesAvecCoupureMois;
	}

	public Long getNbIncidentsResoluPilotageMois() {
		return nbIncidentsResoluPilotageMois;
	}

	public void setNbIncidentsResoluPilotageMois(Long nbIncidentsResoluPilotageMois) {
		this.nbIncidentsResoluPilotageMois = nbIncidentsResoluPilotageMois;
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

	public LinkedHashMap<Environnement, Integer> getDomaineDeSemaineList() {
		return domaineDeSemaineList;
	}

	public void setDomaineDeSemaineList(
			LinkedHashMap<Environnement, Integer> domaineDeSemaineList) {
		this.domaineDeSemaineList = domaineDeSemaineList;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		try{
			if (selectedDateStr == null) {
				selectedDate = new Date();
				selectedDateStr = DateService.dateToStr(selectedDate, DateService.p1 + " " + DateService.pt2);
			}
			else {
				selectedDate = DateService.strToDate(selectedDateStr);
				if(selectedDate == null){
					selectedDate = new Date();
					selectedDateStr = DateService.dateToStr(selectedDate, DateService.p1 + " " + DateService.pt2);
				}
			}
			if(moisEnCourStr == null){
				selectMois = new Date();
				anneeEnCourStr = new SimpleDateFormat("yyyy").format(selectMois);
				moisEnCourStr = DateService.getMonthString(selectMois);
			}
			else{
				String numMois;
				if(anneeEnCourStr == null || anneeEnCourStr.length() == 0){
					numMois = StatistiqueIncidentDatabaseService.convMoisEnCour(moisEnCourStr, null);
				}
				else{
					numMois = StatistiqueIncidentDatabaseService.convMoisEnCour(moisEnCourStr, anneeEnCourStr);
				}
				try {
					selectMois = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(numMois); 
				}
				catch (ParseException e) {
					selectMois = new Date();
					moisEnCourStr = DateService.getMonthString(selectMois);
				}
			}			
			//Liste Domaine et statistiques du mois
			domaineDeMoisList = StatistiquesService.getTopFive(StatistiqueIncidentDatabaseService.getDomaineDeMois(selectMois));
			nbIncidentsMois = StatistiqueIncidentDatabaseService.getIncidentDeMois(selectMois, null, null,EnvironnementTypeDatabaseService.getPrincipal().getId());
			nbIncidentsCritiquesMois = StatistiqueIncidentDatabaseService.getIncidentDeMois(selectMois, Boolean.TRUE, null,EnvironnementTypeDatabaseService.getPrincipal().getId());
			nbIncidentsCoupuresMois = StatistiqueIncidentDatabaseService.getIncidentDeMois(selectMois, null, Boolean.TRUE,EnvironnementTypeDatabaseService.getPrincipal().getId());
			nbIncidentsResoluPilotageMois = StatistiqueIncidentDatabaseService.getIncidentDeMoisResolu(selectMois, Boolean.TRUE);
			nbIncidentsCritiquesAvecCoupureMois = StatistiqueIncidentDatabaseService.getIncidentDeMois(selectMois, Boolean.TRUE, Boolean.TRUE,EnvironnementTypeDatabaseService.getPrincipal().getId());
			
			nbAppelsAstreinteMois = 0L;
			
			nbAppelsAstreinteMois = AstreinteAppelDatabaseService.getNbAstreinteAppelMois(selectMois);
			
			//statistiques hebdos
			if(selectedAnneeS == null || selectedAnneeS.equals("")){
				selectedAnneeS = new SimpleDateFormat("yyyy").format(new Date());
			}
			if (selectedSemaine == null || "".equals(selectedSemaine)) {
				selectedSemaine = DateService.getWeekOfYear(new Date());
			}
			mapSemaine = new LinkedHashMap<Integer, String>();
			String debutAnneeStr = "01/01/" + selectedAnneeS;
			Date debutAnnee = DateService.strToDate(debutAnneeStr);
			for (int i=1; i<53; i++) {
				Date lundi = DateService.getMonday(i, debutAnnee);
				Date dimanche = DateService.getWeekEnd(lundi);
				mapSemaine.put(i, "Semaine " + i + " - " + DateService.dateToStr(lundi, DateService.p1) + " au " + DateService.dateToStr(dimanche, DateService.p1));
			}
			
			monday = DateService.getMonday(selectedSemaine, debutAnnee);
			sunday = DateService.getWeekEnd(monday);
			domaineDeSemaineList = StatistiquesService.getTopFive(StatistiqueIncidentDatabaseService.getDomaineDePeriode(monday, sunday));
			nbIncidentsSemaine = StatistiqueIncidentDatabaseService.getIncidentDeSemaine(monday, sunday, null, null,EnvironnementTypeDatabaseService.getPrincipal().getId());
			nbIncidentsCritiquesSemaine = StatistiqueIncidentDatabaseService.getIncidentDeSemaine(monday, sunday, Boolean.TRUE, null,EnvironnementTypeDatabaseService.getPrincipal().getId());
			nbIncidentsCoupuresSemaine = StatistiqueIncidentDatabaseService.getIncidentDeSemaine(monday, sunday, null, Boolean.TRUE,EnvironnementTypeDatabaseService.getPrincipal().getId());
			nbIncidentsResoluPilotageSemaine = StatistiqueIncidentDatabaseService.getIncidentDeSemaineResolu(monday, sunday, Boolean.TRUE);
			nbIncidentsCritiquesAvecCoupureSemaine = StatistiqueIncidentDatabaseService.getIncidentDeSemaine(monday, sunday, Boolean.TRUE, Boolean.TRUE,EnvironnementTypeDatabaseService.getPrincipal().getId());
			
			nbAppelsAstreinteSemaine = 0L;
			nbAppelsAstreinteSemaine = AstreinteAppelDatabaseService.getNbAstreinteAppelPeriode(monday, sunday);

			//statistiques journalieres
			incidentJour 		= StatistiqueIncidentDatabaseService.getIncidentDeJour(selectedDate);
			incidentSemaine 	= StatistiqueIncidentDatabaseService.getIncidentDeSemaine(selectedDate);
			incidentMois 		= StatistiqueIncidentDatabaseService.getIncidentDeMois(selectedDate, null, null,EnvironnementTypeDatabaseService.getPrincipal().getId());
			incident30jours 	= StatistiqueIncidentDatabaseService.getIncidentDeDernier30Jour(selectedDate);
	
			incidentEnvirPrincJour		= StatistiqueIncidentDatabaseService.getIncidentDeJourPro(selectedDate);
			incidentEnvirPrincSemaine 	= StatistiqueIncidentDatabaseService.getIncidentDeSemainePro(selectedDate);
			incidentEnvirPrincMois 		= StatistiqueIncidentDatabaseService.getIncidentDeMoisPro(selectedDate);
	
			incidentAutreJour 		= StatistiqueIncidentDatabaseService.getIncidentDeJourAutre(selectedDate);
			incidentAutreSemaine 	= StatistiqueIncidentDatabaseService.getIncidentDeSemaineAutre(selectedDate);
			incidentAutreMois 		= StatistiqueIncidentDatabaseService.getIncidentDeMoisAutre(selectedDate);
			
			//top 5
			LinkedHashMap<Machines_Liste, Integer> topFiveServerDer30J 	= StatistiquesService.getTopFive(StatistiqueIncidentDatabaseService.getTopFiveServerDer30J(selectedDate));
			LinkedHashMap<Applicatifs_Liste, Integer> topFiveAppDer30J 	= StatistiquesService.getTopFive(StatistiqueIncidentDatabaseService.getTopFiveAppDer30J(selectedDate));
			LinkedHashMap<Services_Liste, Integer> topFiveServiceDer30J = StatistiquesService.getTopFive(StatistiqueIncidentDatabaseService.getTopFiveServiceDer30J(selectedDate));
			LinkedHashMap<Environnement, Integer> topFiveDomaineDer30J 	= StatistiquesService.getTopFive(StatistiqueIncidentDatabaseService.getTopFiveDomaineDer30J(selectedDate));
	
			LinkedHashMap<Machines_Liste, Integer> topFiveServerDeMois	= StatistiquesService.getTopFive(StatistiqueIncidentDatabaseService.getTopFiveServerDeMois(selectedDate));
			LinkedHashMap<Applicatifs_Liste, Integer> topFiveAppDeMois	= StatistiquesService.getTopFive(StatistiqueIncidentDatabaseService.getTopFiveAppDeMois(selectedDate));
			LinkedHashMap<Services_Liste, Integer> topFiveServiceDeMois = StatistiquesService.getTopFive(StatistiqueIncidentDatabaseService.getTopFiveServiceDeMois(selectedDate));
			LinkedHashMap<Environnement, Integer> topFiveDomaineDeMois 	= StatistiquesService.getTopFive(StatistiqueIncidentDatabaseService.getTopFiveDomaineDeMois(selectedDate));
	
			StringBuilder sbIncidentURL = new StringBuilder();
			sbIncidentURL.append(URL_CHART);
			sbIncidentURL.append("type=bar&titre=");
			sbIncidentURL.append(INCIDENT_TITLE);
			sbIncidentURL
					.append("&legende=true&tips=true&colorString=55FE75&vertialLabel=Nombre&horizontalLabel=Periode&width=350&height=300&is3D=false&");
	
			sbIncidentURL.append("n0=Jour&c0=Jour&v0=");
			sbIncidentURL.append(incidentJour);
			sbIncidentURL.append("&n1=Semaine&c1=Semaine&v1=");
			sbIncidentURL.append(incidentSemaine);
			sbIncidentURL.append("&n2=Mois&c2=Mois&v2=");
			sbIncidentURL.append(incidentMois);
			sbIncidentURL.append("&n3=Dernier30Jour&c3=Dernier30Jour&v3=");
			sbIncidentURL.append(incident30jours);
	
			StringBuilder sbIncidentProURL = new StringBuilder();
	
			sbIncidentProURL.append(URL_CHART);
			sbIncidentProURL.append("type=bar&titre=");
			sbIncidentProURL.append(INCIDENT_PRO_TITLE+getEnviroTypePrincipale());//+typeName
			sbIncidentProURL.append("&legende=true&tips=true&colorString=55FE75&vertialLabel=Nombre&horizontalLabel=Periode&width=350&height=300&is3D=false&");
	
			sbIncidentProURL.append("n0=Jour&c0=Jour&v0=");
			sbIncidentProURL.append(incidentEnvirPrincJour);
			sbIncidentProURL.append("&n1=Semaine&c1=Semaine&v1=");
			sbIncidentProURL.append(incidentEnvirPrincSemaine);
			sbIncidentProURL.append("&n2=Mois&c2=Mois&v2=");
			sbIncidentProURL.append(incidentEnvirPrincMois);
	
			StringBuilder sbIncidentAutreURL = new StringBuilder();
	
			sbIncidentAutreURL.append(URL_CHART);
			sbIncidentAutreURL.append("type=bar&titre=");
			sbIncidentAutreURL.append(INCIDENT_AUTRE_TITLE);
			sbIncidentAutreURL.append("&legende=true&tips=true&colorString=55FE75&vertialLabel=Nombre&horizontalLabel=Periode&width=350&height=300&is3D=false&");
	
			sbIncidentAutreURL.append("n0=Jour&c0=Jour&v0=");
			sbIncidentAutreURL.append(incidentAutreJour);
			sbIncidentAutreURL.append("&n1=Semaine&c1=Semaine&v1=");
			sbIncidentAutreURL.append(incidentAutreSemaine);
			sbIncidentAutreURL.append("&n2=Mois&c2=Mois&v2=");
			sbIncidentAutreURL.append(incidentAutreMois);
	
			StringBuilder sbTopFiveServerDer30JURL = new StringBuilder();
			sbTopFiveServerDer30JURL.append(URL_CHART);
			sbTopFiveServerDer30JURL.append("type=bar&titre=");
			sbTopFiveServerDer30JURL.append(TOP_FIVE_SERVER_DER30J_TITLE);
			sbTopFiveServerDer30JURL.append("&legende=true&tips=true&colorString=55FE75&vertialLabel=Nombre&horizontalLabel=Serveurs&width=500&height=300&is3D=false");
	
			Set<Entry<Machines_Liste, Integer>> serverDer30JSet = topFiveServerDer30J.entrySet();
			Iterator<Entry<Machines_Liste, Integer>> serverDer30JIterator = serverDer30JSet.iterator();
			int i = 0;
			while (serverDer30JIterator.hasNext() && i < 5) {
				Entry<Machines_Liste, Integer> e = serverDer30JIterator.next();
				sbTopFiveServerDer30JURL.append("&n");
				sbTopFiveServerDer30JURL.append(i);
				sbTopFiveServerDer30JURL.append("=");
				sbTopFiveServerDer30JURL.append(e.getKey().getNom());
				sbTopFiveServerDer30JURL.append("&c");
				sbTopFiveServerDer30JURL.append(i);
				sbTopFiveServerDer30JURL.append("=");
				sbTopFiveServerDer30JURL.append(e.getKey().getNom());
				sbTopFiveServerDer30JURL.append("&v");
				sbTopFiveServerDer30JURL.append(i);
				sbTopFiveServerDer30JURL.append("=");
				sbTopFiveServerDer30JURL.append(e.getValue());
				i++;
			}
	
			StringBuilder sbTopFiveAppDer30JURL = new StringBuilder();
	
			sbTopFiveAppDer30JURL.append(URL_CHART);
			sbTopFiveAppDer30JURL.append("type=bar&titre=");
			sbTopFiveAppDer30JURL.append(TOP_FIVE_APP_DE_DER30J_TITLE);
			sbTopFiveAppDer30JURL.append("&legende=true&tips=true&colorString=55FE75&vertialLabel=Nombre&horizontalLabel=Applications&width=500&height=300&is3D=false");
	
			Set<Entry<Applicatifs_Liste, Integer>> appDer30JSet = topFiveAppDer30J.entrySet();
			Iterator<Entry<Applicatifs_Liste, Integer>> appDer30JIterator = appDer30JSet.iterator();
			i = 0;
			while (appDer30JIterator.hasNext() && i < 5) {
				Entry<Applicatifs_Liste, Integer> e = appDer30JIterator.next();
				sbTopFiveAppDer30JURL.append("&n");
				sbTopFiveAppDer30JURL.append(i);
				sbTopFiveAppDer30JURL.append("=");
				sbTopFiveAppDer30JURL.append(e.getKey().getApplicatif());
				sbTopFiveAppDer30JURL.append("&c");
				sbTopFiveAppDer30JURL.append(i);
				sbTopFiveAppDer30JURL.append("=");
				sbTopFiveAppDer30JURL.append(e.getKey().getApplicatif());
				sbTopFiveAppDer30JURL.append("&v");
				sbTopFiveAppDer30JURL.append(i);
				sbTopFiveAppDer30JURL.append("=");
				sbTopFiveAppDer30JURL.append(e.getValue());
				i++;
			}
	
			StringBuilder sbTopFiveServiceDer30JURL = new StringBuilder();
	
			sbTopFiveServiceDer30JURL.append(URL_CHART);
			sbTopFiveServiceDer30JURL.append("type=bar&titre=");
			sbTopFiveServiceDer30JURL.append(TOP_FIVE_SERVICE_DER30J_TITLE);
			sbTopFiveServiceDer30JURL.append("&legende=true&tips=true&colorString=55FE75&vertialLabel=Nombre&horizontalLabel=Services&width=500&height=300&is3D=false");
	
			Set<Entry<Services_Liste, Integer>> serviceDer30JSet = topFiveServiceDer30J.entrySet();
			Iterator<Entry<Services_Liste, Integer>> serviceDer30JIterator = serviceDer30JSet.iterator();
			i = 0;
			while (serviceDer30JIterator.hasNext() && i < 5) {
				Entry<Services_Liste, Integer> e = serviceDer30JIterator.next();
				sbTopFiveServiceDer30JURL.append("&n");
				sbTopFiveServiceDer30JURL.append(i);
				sbTopFiveServiceDer30JURL.append("=");
				sbTopFiveServiceDer30JURL.append(e.getKey().getNomService());
				sbTopFiveServiceDer30JURL.append("&c");
				sbTopFiveServiceDer30JURL.append(i);
				sbTopFiveServiceDer30JURL.append("=");
				sbTopFiveServiceDer30JURL.append(e.getKey().getNomService());
				sbTopFiveServiceDer30JURL.append("&v");
				sbTopFiveServiceDer30JURL.append(i);
				sbTopFiveServiceDer30JURL.append("=");
				sbTopFiveServiceDer30JURL.append(e.getValue());
				i++;
			}
	
			StringBuilder sbTopFiveDomaineDer30JURL = new StringBuilder();
	
			sbTopFiveDomaineDer30JURL.append(URL_CHART);
			sbTopFiveDomaineDer30JURL.append("type=bar&titre=");
			sbTopFiveDomaineDer30JURL.append(TOP_FIVE_DOMAINE_DER30J_TITLE);
			sbTopFiveDomaineDer30JURL.append("&legende=true&tips=true&colorString=55FE75&vertialLabel=Nombre&horizontalLabel=Domaines&width=500&height=300&is3D=false");
	
			Set<Entry<Environnement, Integer>> domainDer30JSet = topFiveDomaineDer30J.entrySet();
			Iterator<Entry<Environnement, Integer>> domainDer30JIterator = domainDer30JSet.iterator();
			i = 0;
			while (domainDer30JIterator.hasNext() && i < 5) {
				Entry<Environnement, Integer> e = domainDer30JIterator.next();
				sbTopFiveDomaineDer30JURL.append("&n");
				sbTopFiveDomaineDer30JURL.append(i);
				sbTopFiveDomaineDer30JURL.append("=");
				sbTopFiveDomaineDer30JURL.append(e.getKey().getEnvironnement());
				sbTopFiveDomaineDer30JURL.append("&c");
				sbTopFiveDomaineDer30JURL.append(i);
				sbTopFiveDomaineDer30JURL.append("=");
				sbTopFiveDomaineDer30JURL.append(e.getKey().getEnvironnement());
				sbTopFiveDomaineDer30JURL.append("&v");
				sbTopFiveDomaineDer30JURL.append(i);
				sbTopFiveDomaineDer30JURL.append("=");
				sbTopFiveDomaineDer30JURL.append(e.getValue());
				i++;
			}
	
			StringBuilder sbTopFiveServerDeMoisURL = new StringBuilder();
			sbTopFiveServerDeMoisURL.append(URL_CHART);
			sbTopFiveServerDeMoisURL.append("type=bar&titre=");
			sbTopFiveServerDeMoisURL.append(TOP_FIVE_SERVER_DE_MOIS_TITLE);
			sbTopFiveServerDeMoisURL.append("&legende=true&tips=true&colorString=55FE75&vertialLabel=Nombre&horizontalLabel=Serveurs&width=500&height=300&is3D=false");
			i = 0;
			Set<Entry<Machines_Liste, Integer>> serverDeMoisSet = topFiveServerDeMois.entrySet();
			Iterator<Entry<Machines_Liste, Integer>> serverDeMoisIterator = serverDeMoisSet.iterator();
			while (serverDeMoisIterator.hasNext() && i < 5) {
				Entry<Machines_Liste, Integer> e = serverDeMoisIterator.next();
				sbTopFiveServerDeMoisURL.append("&n");
				sbTopFiveServerDeMoisURL.append(i);
				sbTopFiveServerDeMoisURL.append("=");
				sbTopFiveServerDeMoisURL.append(e.getKey().getNom());
				sbTopFiveServerDeMoisURL.append("&c");
				sbTopFiveServerDeMoisURL.append(i);
				sbTopFiveServerDeMoisURL.append("=");
				sbTopFiveServerDeMoisURL.append(e.getKey().getNom());
				sbTopFiveServerDeMoisURL.append("&v");
				sbTopFiveServerDeMoisURL.append(i);
				sbTopFiveServerDeMoisURL.append("=");
				sbTopFiveServerDeMoisURL.append(e.getValue());
				i++;
			}
	
			StringBuilder sbTopFiveAppDeMoisURL = new StringBuilder();
	
			sbTopFiveAppDeMoisURL.append(URL_CHART);
			sbTopFiveAppDeMoisURL.append("type=bar&titre=");
			sbTopFiveAppDeMoisURL.append(TOP_FIVE_APP_DE_MOIS_TITLE);
			sbTopFiveAppDeMoisURL.append("&legende=true&tips=true&colorString=55FE75&vertialLabel=Nombre&horizontalLabel=Applications&width=500&height=300&is3D=false");
	
			Set<Entry<Applicatifs_Liste, Integer>> appDeMoisSet = topFiveAppDeMois.entrySet();
			Iterator<Entry<Applicatifs_Liste, Integer>> appDeMoisIterator = appDeMoisSet.iterator();
			i = 0;
			while (appDeMoisIterator.hasNext() && i < 5) {
				Entry<Applicatifs_Liste, Integer> e = appDeMoisIterator.next();
				sbTopFiveAppDeMoisURL.append("&n");
				sbTopFiveAppDeMoisURL.append(i);
				sbTopFiveAppDeMoisURL.append("=");
				sbTopFiveAppDeMoisURL.append(e.getKey().getApplicatif());
				sbTopFiveAppDeMoisURL.append("&c");
				sbTopFiveAppDeMoisURL.append(i);
				sbTopFiveAppDeMoisURL.append("=");
				sbTopFiveAppDeMoisURL.append(e.getKey().getApplicatif());
				sbTopFiveAppDeMoisURL.append("&v");
				sbTopFiveAppDeMoisURL.append(i);
				sbTopFiveAppDeMoisURL.append("=");
				sbTopFiveAppDeMoisURL.append(e.getValue());
				i++;
			}
	
			StringBuilder sbTopFiveServiceDeMoisURL = new StringBuilder();
	
			sbTopFiveServiceDeMoisURL.append(URL_CHART);
			sbTopFiveServiceDeMoisURL.append("type=bar&titre=");
			sbTopFiveServiceDeMoisURL.append(TOP_FIVE_SERVICE_DE_MOIS_TITLE);
			sbTopFiveServiceDeMoisURL.append("&legende=true&tips=true&colorString=55FE75&vertialLabel=Nombre&horizontalLabel=Services&width=500&height=300&is3D=false");
	
			Set<Entry<Services_Liste, Integer>> serviceDeMoisSet = topFiveServiceDeMois.entrySet();
			Iterator<Entry<Services_Liste, Integer>> serviceDeMoisIterator = serviceDeMoisSet.iterator();
			i = 0;
			while (serviceDeMoisIterator.hasNext() && i < 5) {
				Entry<Services_Liste, Integer> e = serviceDeMoisIterator.next();
				sbTopFiveServiceDeMoisURL.append("&n");
				sbTopFiveServiceDeMoisURL.append(i);
				sbTopFiveServiceDeMoisURL.append("=");
				sbTopFiveServiceDeMoisURL.append(e.getKey().getNomService());
				sbTopFiveServiceDeMoisURL.append("&c");
				sbTopFiveServiceDeMoisURL.append(i);
				sbTopFiveServiceDeMoisURL.append("=");
				sbTopFiveServiceDeMoisURL.append(e.getKey().getNomService());
				sbTopFiveServiceDeMoisURL.append("&v");
				sbTopFiveServiceDeMoisURL.append(i);
				sbTopFiveServiceDeMoisURL.append("=");
				sbTopFiveServiceDeMoisURL.append(e.getValue());
				i++;
			}
	
			StringBuilder sbTopFiveDomaineDeMoisURL = new StringBuilder();
	
			sbTopFiveDomaineDeMoisURL.append(URL_CHART);
			sbTopFiveDomaineDeMoisURL.append("type=bar&titre=");
			sbTopFiveDomaineDeMoisURL.append(TOP_FIVE_DOMAINE_DE_MOIS_TITLE);
			sbTopFiveDomaineDeMoisURL.append("&legende=true&tips=true&colorString=55FE75&vertialLabel=Nombre&horizontalLabel=Domaines&width=500&height=300&is3D=false");
	
			Set<Entry<Environnement, Integer>> domainDeMoisSet = topFiveDomaineDeMois.entrySet();
			Iterator<Entry<Environnement, Integer>> domainDeMoisIterator = domainDeMoisSet.iterator();
			i = 0;
			while (domainDeMoisIterator.hasNext() && i < 5) {
				Entry<Environnement, Integer> e = domainDeMoisIterator.next();
				sbTopFiveDomaineDeMoisURL.append("&n");
				sbTopFiveDomaineDeMoisURL.append(i);
				sbTopFiveDomaineDeMoisURL.append("=");
				sbTopFiveDomaineDeMoisURL.append(e.getKey().getEnvironnement());
				sbTopFiveDomaineDeMoisURL.append("&c");
				sbTopFiveDomaineDeMoisURL.append(i);
				sbTopFiveDomaineDeMoisURL.append("=");
				sbTopFiveDomaineDeMoisURL.append(e.getKey().getEnvironnement());
				sbTopFiveDomaineDeMoisURL.append("&v");
				sbTopFiveDomaineDeMoisURL.append(i);
				sbTopFiveDomaineDeMoisURL.append("=");
				sbTopFiveDomaineDeMoisURL.append(e.getValue());
				i++;
			}
	
			incidentURL = sbIncidentURL.toString();
			incidentProURL = sbIncidentProURL.toString();
			incidentAutreURL = sbIncidentAutreURL.toString();
	
			topFiveServerDeMoisURL = sbTopFiveServerDeMoisURL.toString();
			topFiveAppDeMoisURL = sbTopFiveAppDeMoisURL.toString();
			topFiveEnviroDeMoisURL = sbTopFiveDomaineDeMoisURL.toString();
			topFiveServiceDeMoisURL = sbTopFiveServiceDeMoisURL.toString();
	
			topFiveServerDer30JURL = sbTopFiveServerDer30JURL.toString();
			topFiveAppDer30JURL = sbTopFiveAppDer30JURL.toString();
			topFiveEnviroDer30JURL = sbTopFiveDomaineDer30JURL.toString();
			topFiveServiceDer30JURL = sbTopFiveServiceDer30JURL.toString();
	
			moyenneSemain = ((double) incidentSemaine / 7);
			moyenneMois = ((double) incidentMois / DateService.getNbDaysInMonth(selectedDate));
			
			return SUCCESS;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Statistiques incident - ", e);
			return ERROR;
		}
	}
	
}
