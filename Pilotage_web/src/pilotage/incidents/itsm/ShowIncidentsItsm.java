package pilotage.incidents.itsm;

import java.util.List;

import pilotage.database.incidents.itsm.IncidentsItsmDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Incidents_Itsm;
import pilotage.service.constants.PilotageConstants;
import pilotage.utils.Pagination;

public class ShowIncidentsItsm extends AbstractAction {

	private static final long serialVersionUID = 1L;

	private List<Incidents_Itsm> listeIncidents;
	private String titrePage = "ICD_LST";

	// pagination
	private Pagination<Incidents_Itsm> pagination;
	private int page;
	private int nrPages;
	private int nrPerPage;

	public Pagination<Incidents_Itsm> getPagination() {
		return pagination;
	}

	public void setPagination(Pagination<Incidents_Itsm> pagination) {
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

	public List<Incidents_Itsm> getListeIncidents() {
		return listeIncidents;
	}

	public void setListeIncidents(List<Incidents_Itsm> listeIncidents) {
		this.listeIncidents = listeIncidents;
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
			pagination = new Pagination<Incidents_Itsm>(page, nrPerPage);

			listeIncidents = IncidentsItsmDatabaseService.getAll(pagination);
			return OK;
		} catch (Exception e) {
			error = getText("error.message.export.excel");
			return ERROR;
		}
	}
}
