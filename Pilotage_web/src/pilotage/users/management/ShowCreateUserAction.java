package pilotage.users.management;

import java.util.List;
import pilotage.admin.metier.Profil;
import pilotage.database.admin.ProfilDatabaseService;
import pilotage.framework.AbstractAction;


public class ShowCreateUserAction extends AbstractAction{

	private static final long serialVersionUID = 1L;
	private List<Profil> listProfil;
	private String sort;
	private String sens;
	private int page;
	private int nrPages;
	private int nrPerPage;
	
	public void setListProfil(List<Profil> listProfil) {
		this.listProfil = listProfil;
	}

	public List<Profil> getListProfil() {
		return listProfil;
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
		listProfil = ProfilDatabaseService.getAll();
		return OK;
	}
}
