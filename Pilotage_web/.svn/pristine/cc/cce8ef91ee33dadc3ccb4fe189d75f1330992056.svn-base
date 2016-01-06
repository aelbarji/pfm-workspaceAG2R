package pilotage.gup.incidents;

import java.util.Date;
import java.util.List;

import pilotage.database.applicatif.ApplicatifDatabaseService;
import pilotage.database.destinataires.DestinatairesDatabaseService;
import pilotage.database.gup.ComIncidentEtatDatabaseService;
import pilotage.database.gup.ComServiceDatabaseService;
import pilotage.database.gup.DomaineDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Applicatifs_Liste;
import pilotage.metier.Com_Incident_Etat;
import pilotage.metier.Com_Service;
import pilotage.metier.Com_domaine;
import pilotage.metier.Destinataires;
import pilotage.service.date.DateService;

public class RedirectCreateIncidentsGupAction extends AbstractAction {


	private static final long serialVersionUID = -4375417208607797045L;
	private List<Com_Incident_Etat> listEtat;
	private List<Com_domaine> listDomaine;
	private List<Com_Service> listService;
	private List<Applicatifs_Liste> listAppli;
	private List<Destinataires> listInterloc;
	private String dateDebut;
	private String heureDebut;
	private String provenance;

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
	
	public List<Com_domaine> getListDomaine() {
		return listDomaine;
	}

	public void setListDomaine(List<Com_domaine> listDomaine) {
		this.listDomaine = listDomaine;
	}

	public List<Com_Incident_Etat> getListEtat() {
		return listEtat;
	}

	public void setListEtat(List<Com_Incident_Etat> listEtat) {
		this.listEtat = listEtat;
	}

	public List<Com_Service> getListService() {
		return listService;
	}

	public void setListService(List<Com_Service> listService) {
		this.listService = listService;
	}

	public List<Applicatifs_Liste> getListAppli() {
		return listAppli;
	}

	public void setListAppli(List<Applicatifs_Liste> listAppli) {
		this.listAppli = listAppli;
	}

	public List<Destinataires> getListInterloc() {
		return listInterloc;
	}

	public void setListInterloc(List<Destinataires> listInterloc) {
		this.listInterloc = listInterloc;
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
			listEtat = ComIncidentEtatDatabaseService.getAll();
			listDomaine = DomaineDatabaseService.getAll();
			listAppli = ApplicatifDatabaseService.getAll();
			listService = ComServiceDatabaseService.getAll();
			listInterloc = DestinatairesDatabaseService.getAll();
			dateDebut = DateService.dateToStr(new Date(), DateService.p1);
			heureDebut = DateService.getTime(new Date(), DateService.pt1);
			return OK;
		}catch(Exception e){
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Redirection création débordement NOC - ", e);
			return ERROR;
		}
	}

}
