package pilotage.consignes;

import pilotage.database.consigne.ConsignesDatabaseService;
import pilotage.framework.AbstractActionConsigne;
import pilotage.metier.Consignes;

public class RedirectModifyConsigneAction extends AbstractActionConsigne{

	private static final long serialVersionUID = 5140692618251976611L;
	
	private int page;
	private int nrPages;
	private int nrPerPage;
	private int consigneID;
	private Consignes consigne;
	
	public Consignes getConsigne() {
		return consigne;
	}

	public void setConsigne(Consignes consigne) {
		this.consigne = consigne;
	}

	public int getConsigneID() {
		return consigneID;
	}

	public void setConsigneID(int consigneID) {
		this.consigneID = consigneID;
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
		consigne = ConsignesDatabaseService.get(consigneID);
		return OK;
	}

}
