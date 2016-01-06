package pilotage.meteo.meteo;

import java.util.List;

import pilotage.database.meteo.MeteoDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Meteo_Meteo;
import pilotage.service.constants.PilotageConstants;
import pilotage.utils.Pagination;

public class ShowMeteoAction extends AbstractAction{

	private static final long serialVersionUID = 1581554558912601127L;
	
	private int page;
	private int nrPages;
	private int nrPerPage;
	
	private Pagination<Meteo_Meteo> pagination;
	private List<Meteo_Meteo> meteos;

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

	public Pagination<Meteo_Meteo> getPagination() {
		return pagination;
	}

	public void setPagination(Pagination<Meteo_Meteo> pagination) {
		this.pagination = pagination;
	}

	public List<Meteo_Meteo> getMeteos() {
		return meteos;
	}

	public void setMeteo(List<Meteo_Meteo> meteos) {
		this.meteos = meteos;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		try {
			if(page < 1)
				page = 1;
			else if(nrPages != 0 && page > nrPages)
				page = nrPages;
			if(nrPerPage == 0)
				nrPerPage = PilotageConstants.NB_METEO_PER_PAGE;
			pagination = new Pagination<Meteo_Meteo>(page, nrPerPage);
			meteos = MeteoDatabaseService.getAll(pagination);
			return OK;
		}catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Affichage de la liste des Météos - ", e);
			return ERROR;	
		}
	}

}
