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

public class CreateIncidentsGupAction extends AbstractAction {

	private static final long serialVersionUID = 2489368823337147666L;
	private Integer user;
	private Integer appli;
	private String domaineSelected;
	private String serviceSelected;
	private String appliSelected;
	private	String detectionSelected;
	private String nomInterlocuteur;
	private String nomDetections;
	private String nomServices;
	private String nomApplis;
	private String nomDomaines;
	private String prob; 
	private String interlocSelected;
	private Integer impactSelected;
	private Integer etatSelected;
	private String dateDebut;
	private String heureDebut;
	private String dateFin;
	private String heureFin;
	private String ars;
	private Integer selectedID;
	private String provenance;
	private String com;
	
	private final String SEPARATEUR = ", ";
	private String nomEtat;
	private List<Destinataires> listDes;
	private List<Destinataires> listInt;
	private List<String> listCom;
	
	public Integer getUser() {
		return user;
	}

	public void setUser(Integer user) {
		this.user = user;
	}

	public String getDomaineSelected() {
		return domaineSelected;
	}

	public void setDomaineSelected(String domaineSelected) {
		this.domaineSelected = domaineSelected;
	}

	public Integer getAppli() {
		return appli;
	}

	public void setAppli(Integer appli) {
		this.appli = appli;
	}

	public String getServiceSelected() {
		return serviceSelected;
	}

	public void setServiceSelected(String serviceSelected) {
		this.serviceSelected = serviceSelected;
	}

	public String getProb() {
		return prob;
	}

	public void setProb(String prob) {
		this.prob = prob;
	}

	public String getInterlocSelected() {
		return interlocSelected;
	}

	public void setInterlocSelected(String interlocSelected) {
		this.interlocSelected = interlocSelected;
	}

	public List<Destinataires> getListInt() {
		return listInt;
	}

	public void setListInt(List<Destinataires> listInt) {
		this.listInt = listInt;
	}

	public String getNomApplis() {
		return nomApplis;
	}

	public void setNomApplis(String nomApplis) {
		this.nomApplis = nomApplis;
	}

	public Integer getImpactSelected() {
		return impactSelected;
	}

	public void setImpactSelected(Integer impactSelected) {
		this.impactSelected = impactSelected;
	}

	public String getDetectionSelected() {
		return detectionSelected;
	}

	public void setDetectionSelected(String detectionSelected) {
		this.detectionSelected = detectionSelected;
	}

	public String getNomDomaines() {
		return nomDomaines;
	}

	public void setNomDomaines(String nomDomaines) {
		this.nomDomaines = nomDomaines;
	}

	public Integer getEtatSelected() {
		return etatSelected;
	}

	public void setEtatSelected(Integer etatSelected) {
		this.etatSelected = etatSelected;
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

	public String getDateFin() {
		return dateFin;
	}

	public void setDateFin(String dateFin) {
		this.dateFin = dateFin;
	}

	public String getHeureFin() {
		return heureFin;
	}

	public void setHeureFin(String heureFin) {
		this.heureFin = heureFin;
	}

	public String getAppliSelected() {
		return appliSelected;
	}

	public void setAppliSelected(String appliSelected) {
		this.appliSelected = appliSelected;
	}

	public String getNomServices() {
		return nomServices;
	}

	public void setNomServices(String nomServices) {
		this.nomServices = nomServices;
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

	public String getNomEtat() {
		return nomEtat;
	}

	public void setNomEtat(String nomEtat) {
		this.nomEtat = nomEtat;
	}

	public Integer getSelectedID() {
		return selectedID;
	}

	public void setSelectedID(Integer selectedID) {
		this.selectedID = selectedID;
	}

	public String getArs() {
		return ars;
	}

	public void setArs(String ars) {
		this.ars = ars;
	}

	public List<Destinataires> getListDes() {
		return listDes;
	}

	public void setListDes(List<Destinataires> listDes) {
		this.listDes = listDes;
	}

	public String getCom() {
		return com;
	}

	public void setCom(String com) {
		this.com = com;
	}

	public String getProvenance() {
		return provenance;
	}

	public void setProvenance(String provenance) {
		this.provenance = provenance;
	}

	public List<String> getListCom() {
		return listCom;
	}

	public void setListCom(List<String> listCom) {
		this.listCom = listCom;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		try {
			Date dateD = new Date();
			dateDebut = dateDebut.trim();
			heureDebut = heureDebut.trim();
			String d = dateDebut + " " + heureDebut;
			dateD = DateService.strToDate(d);
			DateTime dateTimeD = new DateTime(dateD);
			DateTime dateTimeF = null;
			Date dateF = new Date();
			if(dateFin != null && !dateFin.equals("")){
				dateFin = dateFin.trim();
				heureFin = heureFin.trim();
				String d2 = dateFin + " " + heureFin;
				dateF = DateService.strToDate(d2);
				dateTimeF = new DateTime(dateF);
			}
			Incidents_Gup inc = IncidentsGupDatabaseService.create((Users) session.get("USER_LOGGED"), dateTimeD, dateTimeF, prob, impactSelected, etatSelected, ars);
			selectedID = inc.getId();
			String[] service = StringConverter.stringToArray(serviceSelected, SEPARATEUR);
			ComIncidentServiceDatabaseService.create(inc, service);
			String[] appli = StringConverter.stringToArray(appliSelected, SEPARATEUR);
			ComIncidentAppliDatabaseService.create(inc, appli);
			String[] dom = StringConverter.stringToArray(domaineSelected, SEPARATEUR);
			ComIncidentDomaineDatabaseService.create(inc, dom);
			String[] inter = StringConverter.stringToArray(interlocSelected, SEPARATEUR);
			ComIncidentInterlocuteurDatabaseService.create(inc, inter);
			
			if(detectionSelected != null && !detectionSelected.equals("")){
				String[] detec = StringConverter.stringToArray(detectionSelected, SEPARATEUR);
				ComIncidentDetectionDatabaseService.create(inc, detec);
				
				nomDetections = "";
				int k=0;
				for(int i=0; i<detec.length; i++){
					if(k==0)
						nomDetections = DestinatairesDatabaseService.get(Integer.parseInt(detec[i])).getNom();
					else
						nomDetections += " - " + DestinatairesDatabaseService.get(Integer.parseInt(detec[i])).getNom();
					k++;
				}
			} else nomDetections = "Non renseigné";
			
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
			ars=inc.getArs();
			nomDomaines = "";
			j=0;
			for(int i=0; i<dom.length; i++){
				if(j==0)
					nomDomaines = DomaineDatabaseService.get(Integer.parseInt(dom[i])).getNom();
				else
					nomDomaines += " - " + DomaineDatabaseService.get(Integer.parseInt(dom[i])).getNom();
				j++;
			}
			if(!etatSelected.equals(null) && !etatSelected.equals(-1))
				setNomEtat(ComIncidentEtatDatabaseService.get(etatSelected).getLibelle());
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
