
package pilotage.services.actions.liste;

import pilotage.database.services.ServicesDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Services_Liste;

public class RedirectModifierServicesListeAction extends AbstractAction{

	private static final long serialVersionUID = -322071102727759361L;
	private Integer selectRow;
	private String libelle;
	private String consigne_meteo;

	private int page;
	private int nrPages;
	private int nrPerPage;

	private String filtreNomService;

	public Integer getSelectRow() {
		return selectRow;
	}

	public void setSelectRow(Integer selectRow) {
		this.selectRow = selectRow;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getConsigne_meteo() {
		return consigne_meteo;
	}

	public void setConsigne_meteo(String consigne_meteo) {
		this.consigne_meteo = consigne_meteo;
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

	public String getFiltreNomService() {
		return filtreNomService;
	}

	public void setFiltreNomService(String filtreNomService) {
		this.filtreNomService = filtreNomService;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		Services_Liste service = ServicesDatabaseService.get(selectRow);
		libelle = service.getNomService();
		consigne_meteo = service.getConsigne_meteo();

		return OK;
	}
}
