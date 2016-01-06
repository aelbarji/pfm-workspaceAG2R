package pilotage.gup.admin.debordementnoc;

import java.util.List;

import pilotage.database.gup.DebordementNocDestinataireDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Debordement_Noc_Destinataire;
import pilotage.service.constants.PilotageConstants;
import pilotage.utils.Pagination;

public class ShowAdminDestinataireNocAction extends AbstractAction{

	private static final long serialVersionUID = -3214799583067725602L;
	
	private Pagination<Debordement_Noc_Destinataire> pagination;
	private List<Debordement_Noc_Destinataire> destDebNoc;
	
	private int page;
	private int nrPages;
	private int nrPerPage;

	public List<Debordement_Noc_Destinataire> getDestDebNoc() {
		return destDebNoc;
	}

	public void setDestDebNoc(List<Debordement_Noc_Destinataire> destDebNoc) {
		this.destDebNoc = destDebNoc;
	}

	public Pagination<Debordement_Noc_Destinataire> getPagination() {
		return pagination;
	}

	public void setPagination(Pagination<Debordement_Noc_Destinataire> pagination) {
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
			if(page < 1)
				page = 1;
			else if(nrPages != 0 && page > nrPages)
				page = nrPages;		
			if(nrPerPage == 0)
				nrPerPage = PilotageConstants.NB_INCIDENTS_PER_PAGE;
			pagination = new Pagination<Debordement_Noc_Destinataire>(page, nrPerPage);
			
			destDebNoc = DebordementNocDestinataireDatabaseService.getAll(pagination);
			return OK;
		} catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Affichage liste destinataire debordement NOC - ", e);
			return ERROR;	
		}
	}

}
