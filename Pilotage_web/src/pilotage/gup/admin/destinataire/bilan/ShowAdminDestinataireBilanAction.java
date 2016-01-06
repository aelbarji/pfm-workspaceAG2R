package pilotage.gup.admin.destinataire.bilan;

import java.util.List;

import pilotage.database.gup.ComBilanDestinataireDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Com_Bilan_Destinataire;
import pilotage.service.constants.PilotageConstants;
import pilotage.utils.Pagination;

public class ShowAdminDestinataireBilanAction extends AbstractAction {
	
private static final long serialVersionUID = -3214799583067725602L;
	
	private Pagination<Com_Bilan_Destinataire> pagination;
	private List<Com_Bilan_Destinataire> destBil;
	
	private int page;
	private int nrPages;
	private int nrPerPage;

	public List<Com_Bilan_Destinataire> getDestBil() {
		return destBil;
	}

	public void setDestBil(List<Com_Bilan_Destinataire> destBil) {
		this.destBil = destBil;
	}

	public Pagination<Com_Bilan_Destinataire> getPagination() {
		return pagination;
	}

	public void setPagination(Pagination<Com_Bilan_Destinataire> pagination) {
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
			pagination = new Pagination<Com_Bilan_Destinataire>(page, nrPerPage);
			
			destBil = ComBilanDestinataireDatabaseService.getAll(pagination);
			
			return OK;
		} catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Affichage liste destinataire bilan - ", e);
			return ERROR;	
		}
	}


}
