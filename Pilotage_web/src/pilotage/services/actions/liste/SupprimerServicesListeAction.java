package pilotage.services.actions.liste;

import java.text.MessageFormat;

import pilotage.database.applicatif.ApplicatifDatabaseService;
import pilotage.database.derogation.DerogationDatabaseService;
import pilotage.database.historique.HistoriqueDatabaseService;
import pilotage.database.incidents.IncidentsDatabaseService;
import pilotage.database.services.ServicesDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;

public class SupprimerServicesListeAction extends AbstractAction {

	private static final long serialVersionUID = -8228658609814101673L;
	private Integer selectRow;
	private int page;
	private int nrPages;
	private int nrPerPage;
	
	private String filtreNomService;

	public Integer getSelectRow() {
		return selectRow;
	}

	public void setSelectRow(Integer selectRow) {
		this.selectRow = selectRow;
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

	public String getFiltreNomService() {
		return filtreNomService;
	}

	public void setFiltreNomService(String filtreNomService) {
		this.filtreNomService = filtreNomService;
	}

	@Override
	protected boolean validateMetier() {
		if(ApplicatifDatabaseService.hasService(selectRow)
				|| DerogationDatabaseService.hasService(selectRow)
				|| IncidentsDatabaseService.hasIncidentWithService(selectRow)){
			error = getText("services.liste.suppression.failed");
			
			return false;
		}
		return true;
	}

	@Override
	protected String executeMetier() {
		try {
			String servicesListeString = ServicesDatabaseService.get(selectRow).getNomService();
			ServicesDatabaseService.delete(selectRow);
			
			HistoriqueDatabaseService.create(null, MessageFormat.format(getText("historique.services.liste.suppression"),new Object[]{selectRow,servicesListeString}), (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_SERVICES);

			info = MessageFormat.format(getText("services.liste.suppression.valide"), new Object[]{servicesListeString});
			
			return OK;
		} 
		catch (Exception e) {
			error = getText("error.message.generique")+ ":" + e.getMessage();
			erreurLogger.error("Suppression de la liste de services", e );
			return ERROR;
		}
	}

}
