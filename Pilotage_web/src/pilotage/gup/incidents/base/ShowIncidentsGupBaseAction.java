package pilotage.gup.incidents.base;

import java.util.ArrayList;
import java.util.Calendar;
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
import pilotage.database.users.management.UsersDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Applicatifs_Liste;
import pilotage.metier.Com_Incident_Appli;
import pilotage.metier.Com_Incident_Detection;
import pilotage.metier.Com_Incident_Domaine;
import pilotage.metier.Com_Incident_Etat;
import pilotage.metier.Com_Incident_Interlocuteur;
import pilotage.metier.Com_Incident_Service;
import pilotage.metier.Com_Service;
import pilotage.metier.Com_domaine;
import pilotage.metier.Destinataires;
import pilotage.metier.Incidents_Gup;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;
import pilotage.service.date.DateService;
import pilotage.utils.Pagination;

public class ShowIncidentsGupBaseAction extends AbstractAction{

	private static final long serialVersionUID = -6519769719799973675L;
	
	private Pagination<Incidents_Gup> pagination;
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
	private Integer filtrePilote;
	
	private String provenance;
	
	private List<Users> listPilote;
	private List<Com_domaine> listDomaine;
	private List<Com_Service> listService;
	private List<Applicatifs_Liste> listApplicatif;
	private List<Destinataires> listInterlocuteur;
	private List<Destinataires> listDetection;
	private List<Com_Incident_Etat> listEtat;
	private List<Incidents_Gup> listIncident;
	
	private List<String> service;
	private List<String> appli;
	private List<String> domaine;
	private List<String> interloc;
	private List<String> detection;
	private List<String> date;
	
	private int page;
	private int nrPages;
	private int nrPerPage;

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

	public Integer getFiltreInterlocuteur() {
		return filtreInterlocuteur;
	}

	public void setFiltreInterlocuteur(Integer filtreInterlocuteur) {
		this.filtreInterlocuteur = filtreInterlocuteur;
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

	public List<String> getService() {
		return service;
	}

	public void setService(List<String> service) {
		this.service = service;
	}

	public List<String> getAppli() {
		return appli;
	}

	public void setAppli(List<String> appli) {
		this.appli = appli;
	}

	public List<String> getDomaine() {
		return domaine;
	}

	public void setDomaine(List<String> domaine) {
		this.domaine = domaine;
	}

	public List<String> getInterloc() {
		return interloc;
	}

	public void setInterloc(List<String> interloc) {
		this.interloc = interloc;
	}

	public List<String> getDetection() {
		return detection;
	}

	public void setDetection(List<String> detection) {
		this.detection = detection;
	}

	public List<String> getDate() {
		return date;
	}

	public void setDate(List<String> date) {
		this.date = date;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getNrPages() {
		return nrPages;
	}

	public void setNrPages(int nrPages) {
		this.nrPages = nrPages;
	}

	public int getNrPerPage() {
		return nrPerPage;
	}

	public void setNrPerPage(int nrPerPage) {
		this.nrPerPage = nrPerPage;
	}

	public Integer getFiltrePilote() {
		return filtrePilote;
	}

	public void setFiltrePilote(Integer filtrePilote) {
		this.filtrePilote = filtrePilote;
	}

	public Pagination<Incidents_Gup> getPagination() {
		return pagination;
	}

	public void setPagination(Pagination<Incidents_Gup> pagination) {
		this.pagination = pagination;
	}

	public Integer getFiltreDetection() {
		return filtreDetection;
	}

	public void setFiltreDetection(Integer filtreDetection) {
		this.filtreDetection = filtreDetection;
	}

	public List<Users> getListPilote() {
		return listPilote;
	}

	public void setListPilote(List<Users> listPilote) {
		this.listPilote = listPilote;
	}

	public List<Com_domaine> getListDomaine() {
		return listDomaine;
	}

	public void setListDomaine(List<Com_domaine> listDomaine) {
		this.listDomaine = listDomaine;
	}

	public List<Com_Service> getListService() {
		return listService;
	}

	public void setListService(List<Com_Service> listService) {
		this.listService = listService;
	}

	public List<Applicatifs_Liste> getListApplicatif() {
		return listApplicatif;
	}

	public void setListApplicatif(List<Applicatifs_Liste> listApplicatif) {
		this.listApplicatif = listApplicatif;
	}

	public List<Destinataires> getListInterlocuteur() {
		return listInterlocuteur;
	}

	public void setListInterlocuteur(List<Destinataires> listInterlocuteur) {
		this.listInterlocuteur = listInterlocuteur;
	}

	public List<Com_Incident_Etat> getListEtat() {
		return listEtat;
	}

	public void setListEtat(List<Com_Incident_Etat> listEtat) {
		this.listEtat = listEtat;
	}

	public List<Destinataires> getListDetection() {
		return listDetection;
	}

	public void setListDetection(List<Destinataires> listDetection) {
		this.listDetection = listDetection;
	}

	public List<Incidents_Gup> getListIncident() {
		return listIncident;
	}

	public void setListIncident(List<Incidents_Gup> listIncident) {
		this.listIncident = listIncident;
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
		try{
			if(page < 1)
				page = 1;
			else if(nrPages != 0 && page > nrPages)
				page = nrPages;		
			if(nrPerPage == 0)
				nrPerPage = PilotageConstants.NB_INCIDENTS_PER_PAGE;
			pagination = new Pagination<Incidents_Gup>(page, nrPerPage);
			
			Date dateD = new Date();
			DateTime filtreDateDT = null;
			if(filtreDate != null && !filtreDate.equals("")){
				filtreDate = filtreDate.trim();
				String d = filtreDate;
				dateD = DateService.strToDate(d);
				filtreDateDT = new DateTime(dateD);
			}
			DateTime filtreDateFinDT = null;
			Date dateF = new Date();
			if(filtreDateFin != null && !filtreDateFin.equals("")){
				filtreDateFin = filtreDateFin.trim();
				String d2 = filtreDateFin;
				dateF = DateService.strToDate(d2);
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(dateF);
				calendar.set(Calendar.HOUR_OF_DAY, 23);
				calendar.set(Calendar.MINUTE, 59);
				calendar.set(Calendar.SECOND, 59);
				dateF = calendar.getTime();
				filtreDateFinDT = new DateTime(dateF);
			}
			
			date = new ArrayList<String>();
			service = new ArrayList<String>();
			appli = new ArrayList<String>();
			domaine = new ArrayList<String>();
			interloc = new ArrayList<String>();
			detection = new ArrayList<String>();
			listDetection = new ArrayList<Destinataires>();
			
			//listIncident = IncidentsGupDatabaseService.getAll(pagination, filtreDomaine, filtreService, filtreDetection, filtreApplicatif, filtreArs, filtreDateDT, filtreDateFinDT,  filtreInterlocuteur, filtreEtat, filtreImpact, filtreDescription, filtreResolution, filtrePilote);
					
			List<Incidents_Gup> listIncident1 = IncidentsGupDatabaseService.getAllWithoutPagination(filtreArs, filtreDateDT, filtreDateFinDT, filtreEtat, filtreImpact, filtreDescription, filtrePilote);
			listIncident = new ArrayList<Incidents_Gup>();
			List<Incidents_Gup> listeFiltree = new ArrayList<Incidents_Gup>();
			List<Incidents_Gup> listeIncS = new ArrayList<Incidents_Gup>();
			List<Incidents_Gup> listeIncA = new ArrayList<Incidents_Gup>();
			List<Incidents_Gup> listeIncD = new ArrayList<Incidents_Gup>();
			List<Incidents_Gup> listeIncI = new ArrayList<Incidents_Gup>();
			
			List<Com_Incident_Domaine> comid = ComIncidentDomaineDatabaseService.getAll();
			List<Com_Incident_Service> comis = ComIncidentServiceDatabaseService.getAll();
			List<Com_Incident_Appli> comia = ComIncidentAppliDatabaseService.getAll();
			List<Com_Incident_Detection> comide = ComIncidentDetectionDatabaseService.getAll();
			List<Com_Incident_Interlocuteur> comii = ComIncidentInterlocuteurDatabaseService.getAll();
			
			if(filtreDetection != null && !filtreDetection.equals(-1)){
				for(Incidents_Gup i : listIncident1)
					for(Com_Incident_Detection cid : comide)
						if(cid.getIdDetection().getId().equals(filtreDetection) && cid.getIdIncident().getId().equals(i.getId()))
							listeIncD.add(i);
			}
			else listeIncD = listIncident1; 
			
			if(filtreInterlocuteur != null && !filtreInterlocuteur.equals(-1)){
				for(Incidents_Gup i : listeIncD)
					for(Com_Incident_Interlocuteur cii : comii)
						if(cii.getIdInterlocuteur().getId().equals(filtreInterlocuteur) && cii.getIdIncident().getId().equals(i.getId()))
							listeIncA.add(i);
			}
			else listeIncA = listeIncD; 
			
			if(filtreApplicatif != null && !filtreApplicatif.equals(-1)){
				for(Incidents_Gup i : listeIncA)
					for(Com_Incident_Appli cia : comia)
						if(cia.getIdAppli().getId().equals(filtreApplicatif) && cia.getIdIncident().getId().equals(i.getId()))
							listeIncI.add(i);
			}
			else listeIncI = listeIncA; 
			
			if(filtreService != null && !filtreService.equals(-1)){
				for(Incidents_Gup i : listeIncI)
					for(Com_Incident_Service cis : comis)
						if(cis.getIdService().getId().equals(filtreService) && cis.getIdIncident().getId().equals(i.getId()))
							listeIncS.add(i);
			}
			else listeIncS = listeIncI;
			
			
			if(filtreDomaine != null && !filtreDomaine.equals(-1)){
				for(Incidents_Gup i : listeIncS)
					for(Com_Incident_Domaine cid : comid)
						if(cid.getDomaine().getId().equals(filtreDomaine) && cid.getIdIncident().getId().equals(i.getId()))
							listeFiltree.add(i);
			}
			
			else listeFiltree = listeIncS;
			
			for(int i = (page - 1) * nrPerPage; i < page * nrPerPage && i < listeFiltree.size(); ++i){
				listIncident.add(listeFiltree.get(i));
			}
			pagination.setNrPages(listeFiltree.size() / nrPerPage + (listeFiltree.size() % nrPerPage == 0 ? 0 : 1));
			
			/*if(listIncident.isEmpty())
				listIncident = listIncident1;*/
			
			for(Incidents_Gup i : listIncident){
				
				// mise de l'heure à un format affichable
				DateTime deb = new DateTime();
				deb = i.getDate_deb();
					Integer dd1 = deb.getDayOfMonth();
					String dd = dd1.toString();
					if(dd.length() == 1)
						dd = "0" + dd;
					Integer mon = deb.getMonthOfYear();
					String mo = mon.toString();
					if(mo.length() == 1)
						mo = "0" + mo;
				String dateDebut = dd + "/" +  mo + "/" + deb.getYear();
				Integer heure = deb.getHourOfDay();
				String hh = heure.toString();
				if(hh.length() == 1)
					hh = "0" + hh;
				Integer minutes = deb.getMinuteOfHour();
				String mm = minutes.toString();
				if(mm.length() == 1)
					mm = "0" + mm;
				String heureDebut = hh + ":" + mm;
				
				date.add(dateDebut + " " + heureDebut); 
				
				List<Com_Incident_Service> ls = ComIncidentServiceDatabaseService.getServices(i.getId());
				List<Com_Incident_Appli> la = ComIncidentAppliDatabaseService.getAppli(i.getId());
				List<Com_Incident_Domaine> ld = ComIncidentDomaineDatabaseService.getDomaine(i.getId());
				List<Com_Incident_Interlocuteur> li = ComIncidentInterlocuteurDatabaseService.getInterlocuteurs(i.getId());
				List<Com_Incident_Detection> lde = ComIncidentDetectionDatabaseService.getDetection(i.getId());
				String s = "";
				int j = 0;
				for(Com_Incident_Service cis : ls){
						if(j==0)
							s = cis.getIdService().getNom();
						else
							s += "; " + cis.getIdService().getNom();
						j++;
				}
				service.add(s);
				
				j=0;
				String a = "";
				for(Com_Incident_Appli cia : la){
					if(j==0)
						a = cia.getIdAppli().getApplicatif();
					else
						a += "; " + cia.getIdAppli().getApplicatif() ;
					j++;
				}
				appli.add(a);
				j=0;
				String d = "";
				for(Com_Incident_Domaine cid : ld){
					if(j==0)
						d = cid.getDomaine().getNom();
					else
						d += "; " + cid.getDomaine().getNom();
					j++;
				}
				domaine.add(d);
				j=0;
				String i1 = "";
				for(Com_Incident_Interlocuteur cii : li){
					if(j==0)
						i1 = cii.getIdInterlocuteur().getNom();
					else
						i1 += "; " + cii.getIdInterlocuteur().getNom();
					j++;
				}
				interloc.add(i1);
				j=0;
				String de = "";
				for(Com_Incident_Detection cide : lde){
					if(j==0)
						de = cide.getIdDetection().getNom();
					else
						de +=  "; " + cide.getIdDetection().getNom();
					j++;
				}
				detection.add(de);
			}
			
			listPilote = UsersDatabaseService.getAll();
			listDomaine = DomaineDatabaseService.getAll();
			listService = ComServiceDatabaseService.getAll();
			listEtat = ComIncidentEtatDatabaseService.getAll();
			listInterlocuteur = DestinatairesDatabaseService.getAll();
			listApplicatif = ApplicatifDatabaseService.getAll();
			listDetection = DestinatairesDatabaseService.getAll();
			provenance = "ShowIncidentsGupBase";
			return OK;
		} catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Affichage liste incidents Base GUP - ", e);
			return ERROR;	
		}
	}

}
