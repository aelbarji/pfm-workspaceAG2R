package pilotage.meteo.caisse;

import java.text.MessageFormat;

import pilotage.database.meteo.MeteoCaisseDatabaseService;
import pilotage.framework.AbstractAction;


public class CreateCaisseAction extends AbstractAction{

	private static final long serialVersionUID = -4709131560749479718L;
	
	private String libelleCaisse;
	private String nomCaisseComplet;
	
	private int page;
	private int nrPages;
	private int nrPerPage;

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
		//vérifie que cette caisse n'existe pas deja
		if (MeteoCaisseDatabaseService.exists(null, libelleCaisse)) {
			error = MessageFormat.format(getText("caisse.creation.existe.deja"), libelleCaisse);
			return false;
		}
		return true;
	}

	@Override
	protected String executeMetier() {
		try {
			MeteoCaisseDatabaseService.create(libelleCaisse,nomCaisseComplet);

			info = MessageFormat.format(getText("caisse.creation.valide"), new Object[]{libelleCaisse});
			libelleCaisse = null;
			
			return OK;
		} 
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Creation d'une caisse - ", e);
			return ERROR;
		}
	}

}
