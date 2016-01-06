package pilotage.consignes;

import java.text.MessageFormat;

import pilotage.database.consigne.ConsignesDatabaseService;
import pilotage.database.historique.HistoriqueDatabaseService;
import pilotage.framework.AbstractActionConsigne;
import pilotage.metier.Consignes;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;
import pilotage.utils.Pagination;

public class SupprimerConsigneAction extends AbstractActionConsigne{

	private static final long serialVersionUID = 5345694849676608914L;
	
	private int consigneID;

	private int page;
	private int nrPages;
	private int nrPerPage;
	
	private Pagination<Consignes> pagination;

	public int getConsigneID() {
		return consigneID;
	}

	public void setConsigneID(int consigneID) {
		this.consigneID = consigneID;
	}

	public Pagination<Consignes> getPagination() {
		return pagination;
	}

	public void setPagination(Pagination<Consignes> pagination) {
		this.pagination = pagination;
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
		try{
			//supprimer une consigne 
			ConsignesDatabaseService.modify(consigneID, null, null, null, null, true);
			
			HistoriqueDatabaseService.create(null, MessageFormat.format(getText("historique.communication.consigne.suppression"), new Object[]{consigneID}), (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_CONSIGNES);

			info = getText("consigne.supprimer.valide");
			
			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Suppression d'une consigne - ", e);
			return ERROR;
		}

	}
	
}
