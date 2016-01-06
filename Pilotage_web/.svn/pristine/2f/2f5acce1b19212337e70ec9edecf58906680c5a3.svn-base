package pilotage.gup.admin.domaine;

import java.util.List;

import pilotage.database.gup.DomaineDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Com_domaine;
import pilotage.service.constants.PilotageConstants;
import pilotage.utils.Pagination;

public class ShowComDomaineAction extends AbstractAction{

	private static final long serialVersionUID = -6620754409795582544L;
	private List<Com_domaine> listDomaine;
	private Pagination<Com_domaine> pagination;
	
	private int page;
	private int nrPages;
	private int nrPerPage;
	
	
	public Pagination<Com_domaine> getPagination() {
		return pagination;
	}

	public void setPagination(Pagination<Com_domaine> pagination) {
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

	public List<Com_domaine> getListDomaine() {
		return listDomaine;
	}

	public void setListDomaine(List<Com_domaine> listDomaine) {
		this.listDomaine = listDomaine;
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
		pagination = new Pagination<Com_domaine>(page, nrPerPage);
		
		listDomaine = DomaineDatabaseService.getAll();
		return OK;
	}

}
