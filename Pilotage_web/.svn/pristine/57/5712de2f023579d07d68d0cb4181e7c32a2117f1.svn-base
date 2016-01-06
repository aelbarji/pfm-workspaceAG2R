package pilotage.url;

import java.util.ArrayList;
import java.util.List;

import pilotage.database.url.ListeURLDatabaseService;
import pilotage.database.url.ListeURLfavorisDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Liste_URL;
import pilotage.metier.Liste_URL_favoris;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;
import pilotage.utils.Pagination;

public class ShowURLAction extends AbstractAction{

	private static final long serialVersionUID = -2869950336232685648L;

	private String sort;
	private String sens;
	private int page;
	private int nrPages;
	private int nrPerPage;

	private Pagination<Liste_URL> pagination;
	private List<Liste_URL> listURLpagination;
	private List<Liste_URL> listURLfavoris;
	private List<Liste_URL_favoris> listFavoris;
	
	private String filtreAppli;
	private String filtreAdresse;
	
	private Integer urlID;
	private int addFavori;
	private int delFavori;
	
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

	public Pagination<Liste_URL> getPagination() {
		return pagination;
	}

	public void setPagination(Pagination<Liste_URL> pagination) {
		this.pagination = pagination;
	}

	public List<Liste_URL> getListURLpagination() {
		return listURLpagination;
	}

	public void setListURLpagination(List<Liste_URL> listURLpagination) {
		this.listURLpagination = listURLpagination;
	}

	public List<Liste_URL> getListURLfavoris() {
		return listURLfavoris;
	}

	public void setListURLfavoris(List<Liste_URL> listURLfavoris) {
		this.listURLfavoris = listURLfavoris;
	}

	public List<Liste_URL_favoris> getListFavoris() {
		return listFavoris;
	}

	public void setListFavoris(List<Liste_URL_favoris> listFavoris) {
		this.listFavoris = listFavoris;
	}

	public String getFiltreAppli() {
		return filtreAppli;
	}

	public void setFiltreAppli(String filtreAppli) {
		this.filtreAppli = filtreAppli;
	}

	public String getFiltreAdresse() {
		return filtreAdresse;
	}

	public void setFiltreAdresse(String filtreAdresse) {
		this.filtreAdresse = filtreAdresse;
	}

	public Integer getUrlID() {
		return urlID;
	}

	public void setUrlID(Integer urlID) {
		this.urlID = urlID;
	}

	public int getAddFavori() {
		return addFavori;
	}

	public void setAddFavori(int addFavori) {
		this.addFavori = addFavori;
	}

	public int getDelFavori() {
		return delFavori;
	}

	public void setDelFavori(int delFavori) {
		this.delFavori = delFavori;
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
				nrPerPage = PilotageConstants.NB_MACHINES_PER_PAGE;
			if(sens == null || "".equals(sens))
				sens = "asc";
			if(sort == null || "".equals(sort))
				sort = "nom";
			pagination = new Pagination<Liste_URL>(page, nrPerPage);
			listURLpagination = ListeURLDatabaseService.getByFilter(pagination, filtreAppli, filtreAdresse);
			List<Liste_URL> listURL = ListeURLDatabaseService.getAll();
			
			Users userLogged = (Users)session.get(PilotageConstants.USER_LOGGED);
			
			if(addFavori == 1 && urlID != null){
				if(ListeURLfavorisDatabaseService.get(userLogged, ListeURLDatabaseService.get(urlID)) == null)
				ListeURLfavorisDatabaseService.create(userLogged, ListeURLDatabaseService.get(urlID));
			}
			if(delFavori == 1 && urlID != null){
				ListeURLfavorisDatabaseService.delete(userLogged, ListeURLDatabaseService.get(urlID));
			}
			
			listFavoris = ListeURLfavorisDatabaseService.getByUser(userLogged);
			listURLfavoris = new ArrayList<Liste_URL>();
			for(Liste_URL l : listURL){
				for(Liste_URL_favoris favori : listFavoris){
					if(favori.getUrl().getId()==l.getId()){
					listURLfavoris.add(l);
					}
				}
			}
			
			return OK;
		}catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Affichage de la liste des appels astreinte", e);
			return ERROR;
		}
	}

}
