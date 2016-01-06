package pilotage.derogation.action;

import java.text.MessageFormat;

import pilotage.database.derogation.DerogationDatabaseService;
import pilotage.database.historique.HistoriqueDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Derogation;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;
import pilotage.service.date.DateService;
import pilotage.service.mail.MailService;

public class RetournerDerogationAction extends AbstractAction{

	private static final long serialVersionUID = 5531274587121263615L;

	private int selectRow;
	
	private String sort;
	private String sens;
	private int page;
	private int nrPages;
	private int nrPerPage;

	public int getSelectRow() {
		return selectRow;
	}

	public void setSelectRow(int selectRow) {
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
			DerogationDatabaseService.changeStatut(selectRow, PilotageConstants.DEROGATION_ETAT_RETOURNEE);
			
			Derogation derogation = DerogationDatabaseService.get(selectRow);
			
			//adresses mails
			String[] emails = new String[2];
			emails[0] = derogation.getIdNomCreateur().getEmail();
			emails[1] = derogation.getIdNomDemandeur().getEmail();
			
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
			MailService.sendEmail(emails, PilotageConstants.MAILSERVER_DEROGATION_RETOURNER_SUBJECT, PilotageConstants.MAILSERVER_DEROGATION_RETOURNER_TEXT, content, null);
			
			info = MessageFormat.format(getText("derogation.valideur.retournee"), new Object[]{selectRow});
			HistoriqueDatabaseService.create(null, MessageFormat.format(getText("historique.derogation.valideur.retour"), new Object[]{selectRow}), (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_DEROGATION);
			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Retour d'une dérogation - ", e);
			return ERROR;
		}
	}
}
	
	
