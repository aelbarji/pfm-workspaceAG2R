package pilotage.applications;

import java.text.MessageFormat;

import pilotage.database.applicatif.ApplicatifDatabaseService;
import pilotage.database.historique.HistoriqueDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;
public class DeleteApplicationListeAction extends AbstractAction{

	private static final long serialVersionUID = -4247849760931789967L;

	private int selectRow;
	
	private String sort;
	private String sens;
	private int page;
	private int nrPages;
	private int nrPerPage;
	
	private String filtreNom;
	private String filtreDescription;
	private String filtreDocument;

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

	public String getFiltreNom() {
		return filtreNom;
	}

	public void setFiltreNom(String filtreNom) {
		this.filtreNom = filtreNom;
	}

	public String getFiltreDescription() {
		return filtreDescription;
	}

	public void setFiltreDescription(String filtreDescription) {
		this.filtreDescription = filtreDescription;
	}

	public String getFiltreDocument() {
		return filtreDocument;
	}

	public void setFiltreDocument(String filtreDocument) {
		this.filtreDocument = filtreDocument;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}
	
	@Override
	protected String executeMetier() {
		try {
			String nom = ApplicatifDatabaseService.get(selectRow).getApplicatif();
			
			//suppression en base
			ApplicatifDatabaseService.delete(selectRow);

			HistoriqueDatabaseService.create(null, MessageFormat.format(getText("historique.application.suppression"), new Object[]{selectRow, nom}), (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_APPLICATIONS);

			//message de validation
			info = MessageFormat.format(getText("application.suppression.valide"), nom);
			
			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Suppression d'une application - ", e);
			return ERROR;
		}

	}
	
}