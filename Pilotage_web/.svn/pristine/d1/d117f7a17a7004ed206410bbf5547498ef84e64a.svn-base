package pilotage.meteo.caisse;

import java.text.MessageFormat;

import pilotage.database.meteo.MeteoCaisseDatabaseService;
import pilotage.framework.AbstractAction;


public class DeleteCaisseAction extends AbstractAction{

	private static final long serialVersionUID = -848801504332775496L;

	private Integer caisseID;
	private int page;
	private int nrPages;
	private int nrPerPage;
	
	public Integer getCaisseID() {
		return caisseID;
	}

	public void setCaisseID(Integer caisseID) {
		this.caisseID = caisseID;
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
		try {
			String caisseString = MeteoCaisseDatabaseService.get(caisseID).getNomCaisse();
			MeteoCaisseDatabaseService.delete(caisseID);

			info = MessageFormat.format(getText("caisse.suppression.valide"), new Object[]{caisseString});
			
			return OK;
		} 
		catch (Exception e) {
			error = getText("error.message.generique")+ ":" + e.getMessage();
			erreurLogger.error("Suppression de la liste de services", e );
			return ERROR;
		}
	}

}
