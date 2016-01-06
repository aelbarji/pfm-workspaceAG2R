package pilotage.meteo.groupeMeteo;

import java.util.List;
import pilotage.database.meteo.GroupeMeteoDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Meteo_GroupeMeteo;
import pilotage.service.constants.PilotageConstants;
import pilotage.utils.Pagination;

public class ShowGroupeMeteoAction extends AbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -635035604755082115L;
	
	private int page;
	private int nrPages;
	private int nrPerPage;
	
	private Pagination<Meteo_GroupeMeteo> pagination;
	private List<Meteo_GroupeMeteo> groupesMeteo;
	
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

	public Pagination<Meteo_GroupeMeteo> getPagination() {
		return pagination;
	}

	public void setPagination(Pagination<Meteo_GroupeMeteo> pagination) {
		this.pagination = pagination;
	}
	
	public List<Meteo_GroupeMeteo> getGroupesMeteo() {
		return groupesMeteo;
	}

	public void setGroupesMeteo(List<Meteo_GroupeMeteo> groupesMeteo) {
		this.groupesMeteo = groupesMeteo;
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
				nrPerPage = PilotageConstants.NB_GROUPE_METEO_PER_PAGE;
			pagination = new Pagination<Meteo_GroupeMeteo>(page, nrPerPage);
			groupesMeteo = GroupeMeteoDatabaseService.getAll(pagination);
			return OK;
		}catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Affichage de la liste des groupes Météos - ", e);
			return ERROR;	
		}
	}

}
