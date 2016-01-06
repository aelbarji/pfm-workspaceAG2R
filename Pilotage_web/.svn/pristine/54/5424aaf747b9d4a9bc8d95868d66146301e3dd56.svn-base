package pilotage.meteo.caisse;

import java.util.List;

import pilotage.database.meteo.MeteoCaisseDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Meteo_Caisse;
import pilotage.service.constants.PilotageConstants;
import pilotage.utils.Pagination;

public class ShowCaisseAction extends AbstractAction{

	private static final long serialVersionUID = 8094635654706576344L;

	private int page;
	private int nrPages;
	private int nrPerPage;
	
	private String libelleCaisse;
	private String nomCaisseComplet;
	
	private Pagination<Meteo_Caisse> pagination;
	private List<Meteo_Caisse> listCaisses;
	
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

	public Pagination<Meteo_Caisse> getPagination() {
		return pagination;
	}

	public void setPagination(Pagination<Meteo_Caisse> pagination) {
		this.pagination = pagination;
	}

	public String getLibelleCaisse() {
		return libelleCaisse;
	}

	public void setLibelleCaisse(String libelleCaisse) {
		this.libelleCaisse = libelleCaisse;
	}

	public String getNomCaisseComplet() {
		return nomCaisseComplet;
	}

	public void setNomCaisseComplet(String nomCaisseComplet) {
		this.nomCaisseComplet = nomCaisseComplet;
	}

	public List<Meteo_Caisse> getListCaisses() {
		return listCaisses;
	}

	public void setListCaisses(List<Meteo_Caisse> listCaisses) {
		this.listCaisses = listCaisses;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		try {
			if(error == null || error.length() == 0)
				libelleCaisse = null;
			
			if(page < 1)
				page = 1;
			else if(nrPages != 0 && page > nrPages)
				page = nrPages;
			if(nrPerPage == 0)
				nrPerPage = PilotageConstants.NB_CAISSE_PER_PAGE;
			pagination = new Pagination<Meteo_Caisse>(page, nrPerPage);
			listCaisses = MeteoCaisseDatabaseService.getAll(pagination);
			return OK;
		}catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Affichage de la liste des caisses - ", e);
			return ERROR;	
		}
	}

}
