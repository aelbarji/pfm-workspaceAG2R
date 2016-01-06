package pilotage.derogation.action;

import java.text.MessageFormat;
import java.util.List;

import pilotage.database.derogation.DerogationDatabaseService;
import pilotage.database.derogation.DerogationValideurDatabaseService;
import pilotage.database.historique.HistoriqueDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Derogation;
import pilotage.metier.Derogation_Valideur;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;
import pilotage.service.date.DateService;
import pilotage.service.mail.MailService;

public class EnvoyerEmailAction extends AbstractAction{
	
	private static final long serialVersionUID = -6444579561176134214L;

	private Integer selectRow;
	
	private String sort;
	private String sens;
	private int page;
	private int nrPages;
	private int nrPerPage;

	public Integer getSelectRow() {
		return selectRow;
	}

	public void setSelectRow(Integer selectRow) {
		this.selectRow = selectRow;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getSens() {
		return sens;
	}

	public void setSens(String sens) {
		this.sens = sens;
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
	
	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		try {
			Derogation derogation = DerogationDatabaseService.get(selectRow);					

			//récupération de la liste des valideurs
			List<Derogation_Valideur> listValideurs = DerogationValideurDatabaseService.getAll();
			if(listValideurs == null || listValideurs.isEmpty()){
				error = getText("derogation.no.valideur");
				return ERROR;
			}
				
			String[] emails = new String[listValideurs.size()];
			for(int i = 0; i < listValideurs.size(); ++i)
				emails[i] = listValideurs.get(i).getValideur().getEmail();
			
			//constitution des paramètres pour le mail
			String[] content = new String [20];
			content[0] = derogation.getId().toString();
			content[1] = derogation.getNumars();
			content[2] = derogation.getIdType().getType();
			content[3] = derogation.getIdNomDemandeur().getNom() + " " + derogation.getIdNomDemandeur().getPrenom();
			content[4] = derogation.getIdNomCreateur().getNom() + " " + derogation.getIdNomCreateur().getPrenom();
			content[5] = derogation.getTelephone() == null ? "" : derogation.getTelephone();
			content[6] = derogation.getIdAppli().getApplicatif();
			content[7] = derogation.getDescription();
			content[8] = derogation.getService()  == null ? "" : derogation.getService();
			content[9] = derogation.getRealisateur()  == null ? "" : derogation.getRealisateur();
			content[10] = derogation.getClientTouche();
			content[11] = derogation.getServiceImpact();
			content[12] = derogation.getTp() == 0 ? "Non" : "Oui";
			content[13] = derogation.getEtude() == 0 ? "Non" : "Oui";
			content[14] = derogation.getRetourArriere() == 0 ? "Non" : "Oui";
			content[15] = derogation.getExterne() == 0 ? "Non" : "Oui";
			content[16] = derogation.getRecette() == 0 ? "Non" : "Oui";
			content[17] = derogation.getTypeChangement().getTypeChangement();
			content[18] = derogation.getJustificatif();
			content[19] = DateService.dateToStr(derogation.getTimeDemande(), DateService.p1) + " " + derogation.getHeureDemande();
			
			//envoi du mail
			MailService.sendEmail(emails, PilotageConstants.MAILSERVER_DEROGATION_ENVOYER_SUBJECT, PilotageConstants.MAILSERVER_DEROGATION_ENVOYER_TEXT, content, null);

			DerogationDatabaseService.changeStatut(selectRow, PilotageConstants.DEROGATION_ETAT_ENVOYEE);
			HistoriqueDatabaseService.create(null, MessageFormat.format(getText("historique.derogation.envoyer.mail"), new Object[]{selectRow}), (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_DEROGATION);
			info = MessageFormat.format(getText("derogation.mail.sent"), new Object[]{selectRow});
			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Envoi d'une dérogation - ", e);
			return ERROR;
		}
	}
}
	
	
