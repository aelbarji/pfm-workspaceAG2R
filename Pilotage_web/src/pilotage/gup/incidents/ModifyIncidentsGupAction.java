package pilotage.gup.incidents;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;

import pilotage.database.applicatif.ApplicatifDatabaseService;
import pilotage.database.destinataires.DestinatairesDatabaseService;
import pilotage.database.gup.ComIncidentAppliDatabaseService;
import pilotage.database.gup.ComIncidentDetectionDatabaseService;
import pilotage.database.gup.ComIncidentDomaineDatabaseService;
import pilotage.database.gup.ComIncidentEtatDatabaseService;
import pilotage.database.gup.ComIncidentInterlocuteurDatabaseService;
import pilotage.database.gup.ComIncidentServiceDatabaseService;
import pilotage.database.gup.ComServiceDatabaseService;
import pilotage.database.gup.DomaineDatabaseService;
import pilotage.database.gup.IncidentsGupDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Destinataires;
import pilotage.metier.Incidents_Gup;
import pilotage.metier.Users;
import pilotage.service.date.DateService;
import pilotage.service.string.StringConverter;

public class ModifyIncidentsGupAction extends AbstractAction{

	private static final long serialVersionUID = 6173794671258209664L;
	private Integer selectedID;
	private String appliSelected;
	private String domaineSelected;
	private String serviceSelected;
	private String detectionSelected;
	private String interlocSelected;
	private Integer etat;
	private String dateFin;
	private String heureFin;
	private Integer impactSelected;
	private String prob;
	private DateTime date_deb;
	private DateTime date_fin;
	private final String SEPARATEUR = ", ";
	private String dateDebut;
	private String heureDebut;
	private String ars;
	private String com;
	
	private String provenance;
	
	private String nomInterlocuteur;
	private String nomDetections;
	private String nomServices;
	private String nomApplis;
	private String nomDomaines;
	private String nomEtat;
	
	private Integer filtreDomaine;
	private Integer filtreService;
	private Integer filtreApplicatif;
	private Integer filtreDetection;
	private Integer filtreInterlocuteur;
	private Integer filtreEtat;
	private String filtreArs;
	private String filtreDate;
	private String filtreDateFin;
	private Integer filtreImpact;
	private String filtreDescription;
	private String filtreResolution;
	private Integer filtrePilote;
	
	private List<Destinataires> listInt;
	private List<Destinataires> listDes;

	public String getAppliSelected() {
		return appliSelected;
	}

	public void setAppliSelected(String appliSelected) {
		this.appliSelected = appliSelected;
	}

	public String getDomaineSelected() {
		return domaineSelected;
	}

	public void setDomaineSelected(String domaineSelected) {
		this.domaineSelected = domaineSelected;
	}

	public String getServiceSelected() {
		return serviceSelected;
	}

	public void setServiceSelected(String serviceSelected) {
		this.serviceSelected = serviceSelected;
	}

	public String getDetectionSelected() {
		return detectionSelected;
	}

	public void setDetectionSelected(String detection) {
		this.detectionSelected = detection;
	}

	public String getInterlocSelected() {
		return interlocSelected;
	}

	public void setInterlocSelected(String interlocuteur) {
		this.interlocSelected = interlocuteur;
	}

	public Integer getEtat() {
		return etat;
	}

	public void setEtat(Integer etat) {
		this.etat = etat;
	}

	public String getDateFin() {
		return dateFin;
	}

	public void setDateFin(String dateFin) {
		this.dateFin = dateFin;
	}

	public Integer getFiltreDomaine() {
		return filtreDomaine;
	}

	public void setFiltreDomaine(Integer filtreDomaine) {
		this.filtreDomaine = filtreDomaine;
	}

	public Integer getFiltreService() {
		return filtreService;
	}

	public void setFiltreService(Integer filtreService) {
		this.filtreService = filtreService;
	}

	public Integer getFiltreApplicatif() {
		return filtreApplicatif;
	}

	public void setFiltreApplicatif(Integer filtreApplicatif) {
		this.filtreApplicatif = filtreApplicatif;
	}

	public Integer getFiltreDetection() {
		return filtreDetection;
	}

	public void setFiltreDetection(Integer filtreDetection) {
		this.filtreDetection = filtreDetection;
	}

	public Integer getFiltreInterlocuteur() {
		return filtreInterlocuteur;
	}

	public void setFiltreInterlocuteur(Integer filtreInterlocuteur) {
		this.filtreInterlocuteur = filtreInterlocuteur;
	}

	public Integer getFiltreEtat() {
		return filtreEtat;
	}

	public void setFiltreEtat(Integer filtreEtat) {
		this.filtreEtat = filtreEtat;
	}

	public String getFiltreArs() {
		return filtreArs;
	}

	public void setFiltreArs(String filtreArs) {
		this.filtreArs = filtreArs;
	}

	public String getFiltreDate() {
		return filtreDate;
	}

	public void setFiltreDate(String filtreDate) {
		this.filtreDate = filtreDate;
	}

	public String getFiltreDateFin() {
		return filtreDateFin;
	}

	public void setFiltreDateFin(String filtreDateFin) {
		this.filtreDateFin = filtreDateFin;
	}

	public Integer getFiltreImpact() {
		return filtreImpact;
	}

	public void setFiltreImpact(Integer filtreImpact) {
		this.filtreImpact = filtreImpact;
	}

	public String getFiltreDescription() {
		return filtreDescription;
	}

	public void setFiltreDescription(String filtreDescription) {
		this.filtreDescription = filtreDescription;
	}

	public String getFiltreResolution() {
		return filtreResolution;
	}

	public void setFiltreResolution(String filtreResolution) {
		this.filtreResolution = filtreResolution;
	}

	public Integer getFiltrePilote() {
		return filtrePilote;
	}

	public void setFiltrePilote(Integer filtrePilote) {
		this.filtrePilote = filtrePilote;
	}

	public String getHeureFin() {
		return heureFin;
	}

	public void setHeureFin(String heureFin) {
		this.heureFin = heureFin;
	}

	public String getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(String dateDebut) {
		this.dateDebut = dateDebut;
	}

	public String getHeureDebut() {
		return heureDebut;
	}

	public void setHeureDebut(String heureDebut) {
		this.heureDebut = heureDebut;
	}

	public Integer getImpactSelected() {
		return impactSelected;
	}

	public void setImpactSelected(Integer impactSelected) {
		this.impactSelected = impactSelected;
	}

	public String getNomInterlocuteur() {
		return nomInterlocuteur;
	}

	public void setNomInterlocuteur(String nomInterlocuteur) {
		this.nomInterlocuteur = nomInterlocuteur;
	}

	public String getNomDetections() {
		return nomDetections;
	}

	public void setNomDetections(String nomDetections) {
		this.nomDetections = nomDetections;
	}

	public String getNomServices() {
		return nomServices;
	}

	public void setNomServices(String nomServices) {
		this.nomServices = nomServices;
	}

	public List<Destinataires> getListInt() {
		return listInt;
	}

	public void setListInt(List<Destinataires> listInt) {
		this.listInt = listInt;
	}

	public String getArs() {
		return ars;
	}

	public void setArs(String ars) {
		this.ars = ars;
	}

	public String getNomApplis() {
		return nomApplis;
	}

	public void setNomApplis(String nomApplis) {
		this.nomApplis = nomApplis;
	}

	public String getNomDomaines() {
		return nomDomaines;
	}

	public void setNomDomaines(String nomDomaines) {
		this.nomDomaines = nomDomaines;
	}

	public String getSEPARATEUR() {
		return SEPARATEUR;
	}

	public String getProb() {
		return prob;
	}

	public void setProb(String prob) {
		this.prob = prob;
	}

	public DateTime getDate_deb() {
		return date_deb;
	}

	public void setDate_deb(DateTime date_deb) {
		this.date_deb = date_deb;
	}

	public String getCom() {
		return com;
	}

	public void setCom(String com) {
		this.com = com;
	}

	public DateTime getDate_fin() {
		return date_fin;
	}

	public void setDate_fin(DateTime date_fin) {
		this.date_fin = date_fin;
	}

	public Integer getSelectedID() {
		return selectedID;
	}

	public void setSelectedID(Integer selectedID) {
		this.selectedID = selectedID;
	}

	public String getNomEtat() {
		return nomEtat;
	}

	public void setNomEtat(String nomEtat) {
		this.nomEtat = nomEtat;
	}

	public List<Destinataires> getListDes() {
		return listDes;
	}

	public void setListDes(List<Destinataires> listDes) {
		this.listDes = listDes;
	}

	public String getProvenance() {
		return provenance;
	}

	public void setProvenance(String provenance) {
		this.provenance = provenance;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		try {
			//Conversion de la date récuperer en String vers DateTime
			Date dateD = new Date();
			dateDebut = dateDebut.trim();
			heureDebut = heureDebut.trim();
			String d = dateDebut + " " + heureDebut;
			dateD = DateService.strToDate(d);
			DateTime dateTimeD = new DateTime(dateD);
			
			DateTime dateTimeF = null;
			if(dateFin != null && !dateFin.equals("")){
				Date dateF = new Date();
				dateFin = dateFin.trim();
				heureFin = heureFin.trim();
				String d2 = dateFin + " " + heureFin;
				dateF = DateService.strToDate(d2);
				dateTimeF = new DateTime(dateF);
			}
			//modification de l'incident, puis des tables de jonction 
			Incidents_Gup inc = IncidentsGupDatabaseService.modify(selectedID, (Users) session.get("USER_LOGGED"), dateTimeD, dateTimeF, prob, impactSelected, etat, ars);
			String[] dom = StringConverter.stringToArray(domaineSelected, SEPARATEUR);
			ComIncidentDomaineDatabaseService.modify(inc, dom);
			String[] service = StringConverter.stringToArray(serviceSelected, SEPARATEUR);
			ComIncidentServiceDatabaseService.modify(inc, service);
			String[] appli = StringConverter.stringToArray(appliSelected, SEPARATEUR);
			ComIncidentAppliDatabaseService.modify(inc, appli);
			String[] inter = StringConverter.stringToArray(interlocSelected, SEPARATEUR);
			ComIncidentInterlocuteurDatabaseService.modify(inc, inter);
			
			//Mettre les infos des tables de jonctions en String afin de les intégrer au mail
			if(detectionSelected != null && !detectionSelected.equals("")){
				String[] detec = StringConverter.stringToArray(detectionSelected, SEPARATEUR);
				ComIncidentDetectionDatabaseService.modify(inc, detec);
				nomDetections = "";
				int k=0;
				for(int i=0; i<detec.length; i++){
					if(k==0)
						nomDetections = DestinatairesDatabaseService.get(Integer.parseInt(detec[i])).getNom();
					else nomDetections += " - " + DestinatairesDatabaseService.get(Integer.parseInt(detec[i])).getNom();
						k++;
					}
				}
			else nomDetections = "Non renseigné";
			nomServices = "";
			int j=0;
			for(int i=0; i<service.length; i++){
				if(j==0)
					nomServices = ComServiceDatabaseService.get(Integer.parseInt(service[i])).getNom();
				else
					nomServices += " - " + ComServiceDatabaseService.get(Integer.parseInt(service[i])).getNom();
				j++;
			}
			
			nomApplis = "";
			j=0;
			for(int i=0; i<appli.length; i++){
				if(j==0)
					nomApplis = ApplicatifDatabaseService.get(Integer.parseInt(appli[i])).getApplicatif();
				else
					nomApplis += " - " + ApplicatifDatabaseService.get(Integer.parseInt(appli[i])).getApplicatif();
				j++;
			}
			
			nomDomaines = "";
			j=0;
			for(int i=0; i<dom.length; i++){
				if(j==0)
					nomDomaines = DomaineDatabaseService.get(Integer.parseInt(dom[i])).getNom();
				else
					nomDomaines += " - " + DomaineDatabaseService.get(Integer.parseInt(dom[i])).getNom();
				j++;
			}
			if(etat != null && !etat.equals(-1))
				setNomEtat(ComIncidentEtatDatabaseService.get(etat).getLibelle());
			else nomEtat = "Non renseigné";
			listInt = new ArrayList<Destinataires>();
			nomInterlocuteur = "";
			j=0;
			for(int i=0; i<inter.length; i++){
				if(j==0)
					nomInterlocuteur = DestinatairesDatabaseService.get(Integer.parseInt(inter[i])).getNom();
				else
					nomInterlocuteur += " - " + DestinatairesDatabaseService.get(Integer.parseInt(inter[i])).getNom();
				
				listInt.add(DestinatairesDatabaseService.get(Integer.parseInt(inter[i])));
				j++;
			}
			com = inc.getDescription_prob();
			com = com.replace("\r\n", "<br/>");
			listDes = new ArrayList<Destinataires>();
			listDes = DestinatairesDatabaseService.getAll();
			return OK;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		
	}

}
