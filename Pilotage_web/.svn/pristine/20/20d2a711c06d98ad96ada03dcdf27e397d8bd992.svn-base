package pilotage.destinataires.admin;


import java.util.List;

import pilotage.database.destinataires.DestinatairesDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Destinataires;
import pilotage.service.constants.PilotageConstants;
import pilotage.utils.Pagination;

public class ShowDestinatairesAdminAction extends AbstractAction{

	private static final long serialVersionUID = -2560113667746989783L;
	private Pagination<Destinataires> pagination;
	private List<Destinataires> listDest;
	
	private int page;
	private int nrPages;
	private int nrPerPage;
	
	
	public Pagination<Destinataires> getPagination() {
		return pagination;
	}

	public void setPagination(Pagination<Destinataires> pagination) {
		this.pagination = pagination;
	}

	public List<Destinataires> getListDest() {
		return listDest;
	}

	public void setListDest(List<Destinataires> listDest) {
		this.listDest = listDest;
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
			pagination = new Pagination<Destinataires>(page, nrPerPage);
			
			listDest = DestinatairesDatabaseService.getAll(pagination);
			return OK;
			
		} catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Affichage liste destinataires - ", e);
			return ERROR;	
		}
	}

}
