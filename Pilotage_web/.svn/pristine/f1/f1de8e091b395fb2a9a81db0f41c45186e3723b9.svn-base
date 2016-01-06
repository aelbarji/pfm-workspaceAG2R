package pilotage.meteo.environnement;

import java.util.List;
import pilotage.database.meteo.MeteoEnvironnementDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Meteo_Environnement;
import pilotage.service.constants.PilotageConstants;
import pilotage.utils.Pagination;

public class ShowEnvironnementMeteoAction extends AbstractAction{

	private static final long serialVersionUID = 6872881556294804783L;
	
	private int page;
	private int nrPages;
	private int nrPerPage;
	
	private Pagination<Meteo_Environnement> pagination;
	private List<Meteo_Environnement> environnements;
	
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

	public Pagination<Meteo_Environnement> getPagination() {
		return pagination;
	}

	public void setPagination(Pagination<Meteo_Environnement> pagination) {
		this.pagination = pagination;
	}

	public List<Meteo_Environnement> getEnvironnements() {
		return environnements;
	}

	public void setEnvironnements(List<Meteo_Environnement> environnements) {
		this.environnements = environnements;
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
			pagination = new Pagination<Meteo_Environnement>(page, nrPerPage);
			environnements = MeteoEnvironnementDatabaseService.getAll(pagination);
			return OK;
		}catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Affichage de la liste des environnements - ", e);
			return ERROR;	
		}
	}

}
