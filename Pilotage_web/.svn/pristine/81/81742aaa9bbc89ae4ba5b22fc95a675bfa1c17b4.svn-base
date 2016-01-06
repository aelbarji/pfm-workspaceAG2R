package pilotage.gup.admin.service;

import java.util.List;

import pilotage.database.gup.ComServiceDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Com_Service;
import pilotage.service.constants.PilotageConstants;
import pilotage.utils.Pagination;

public class ShowComServiceAction extends AbstractAction{

	private static final long serialVersionUID = 5608628973521894895L;
	private Pagination<Com_Service> pagination;
	private List<Com_Service> listComServ;
	
	private int page;
	private int nrPages;
	private int nrPerPage;
	
	public Pagination<Com_Service> getPagination() {
		return pagination;
	}

	public void setPagination(Pagination<Com_Service> pagination) {
		this.pagination = pagination;
	}

	public List<Com_Service> getListComServ() {
		return listComServ;
	}

	public void setListComServ(List<Com_Service> listComServ) {
		this.listComServ = listComServ;
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
		
		if(page < 1)
			page = 1;
		else if(nrPages != 0 && page > nrPages)
			page = nrPages;		
		if(nrPerPage == 0)
			nrPerPage = PilotageConstants.NB_INCIDENTS_PER_PAGE;
		pagination = new Pagination<Com_Service>(page, nrPerPage);
		
		listComServ = ComServiceDatabaseService.getAllWithPagination(pagination);
		return OK;
	}

}
