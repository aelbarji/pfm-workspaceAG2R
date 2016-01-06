package pilotage.meteo.caisse;

import pilotage.database.meteo.MeteoCaisseDatabaseService;
import pilotage.framework.AbstractAction;

public class RedirectModifyCaisseAction extends AbstractAction{

	private static final long serialVersionUID = -4745027373628783344L;
	private Integer caisseID;
	private String libelleCaisse;
	private String nomCaisseComplet;

	private int page;
	private int nrPages;
	private int nrPerPage;

	public Integer getCaisseID() {
		return caisseID;
	}

	public void setCaisseID(Integer caisseID) {
		this.caisseID = caisseID;
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
		libelleCaisse = MeteoCaisseDatabaseService.get(caisseID).getNomCaisse();
		nomCaisseComplet = MeteoCaisseDatabaseService.get(caisseID).getNomCaisseComplet();

		return OK;
	}

}
