package pilotage.astreintes.actions.astreinte;

import java.util.List;

import pilotage.database.astreintes.AstreinteTypeDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Astreinte_Type;

public class RedirectCreateAstreinteAction extends AbstractAction{

	private static final long serialVersionUID = 5102061869738582630L;
    private List<Astreinte_Type> aTypes;
    
    private String sort;
	private String sens;
	private int page;
	private int nrPages;
	private int nrPerPage;

	public List<Astreinte_Type> getATypes() {
		return aTypes;
	}

	public void setATypes(List<Astreinte_Type> aTypes) {
		this.aTypes = aTypes;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getSens() {
		return sens;
	}

	public void setSens(String sens) {
		this.sens = sens;
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
		aTypes = AstreinteTypeDatabaseService.getAll();
		return OK;
	}

}
