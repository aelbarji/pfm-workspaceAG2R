package pilotage.changements;

import java.util.List;

import pilotage.database.changements.ChangementsDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Changements;
import pilotage.service.constants.PilotageConstants;
import pilotage.utils.Pagination;

public class ShowChangements extends AbstractAction {

	private static final long serialVersionUID = 1L;

	private List<Changements> listeChangements;
	private String titrePage = "CHM_LST";

	// pagination
	private Pagination<Changements> pagination;
	private int page;
	private int nrPages;
	private int nrPerPage;

	public Pagination<Changements> getPagination() {
		return pagination;
	}

	public void setPagination(Pagination<Changements> pagination) {
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

	public List<Changements> getListeChangements() {
		return listeChangements;
	}

	public void setListeChangements(List<Changements> listeChangements) {
		this.listeChangements = listeChangements;
	}

	public String getTitrePage() {
		return titrePage;
	}

	public void setTitrePage(String titrePage) {
		this.titrePage = titrePage;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {

		try {
			if (page < 1)
				page = 1;
			else if (nrPages != 0 && page > nrPages)
				page = nrPages;
			if (nrPerPage == 0)
				nrPerPage = PilotageConstants.NB_INCIDENTS_PER_PAGE;
			pagination = new Pagination<Changements>(page, nrPerPage);

			listeChangements = ChangementsDatabaseService.getAll(pagination);
			return OK;
		} catch (Exception e) {
			error = getText("error.message.export.excel");
			return ERROR;
		}
	}
}
