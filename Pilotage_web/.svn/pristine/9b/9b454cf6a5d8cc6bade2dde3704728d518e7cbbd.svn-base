package pilotage.meteo.service;

import java.text.MessageFormat;
import pilotage.database.meteo.MeteoServiceDatabaseService;
import pilotage.framework.AbstractAction;

public class CreateServiceMeteoAction extends AbstractAction{

	private static final long serialVersionUID = 4245212070384744536L;

	private String libelle;
	
	private int page;
	private int nrPages;
	private int nrPerPage;

	private String filtreService;
	
	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
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

	public String getFiltreService() {
		return filtreService;
	}

	public void setFiltreService(String filtreService) {
		this.filtreService = filtreService;
	}

	@Override
	protected boolean validateMetier() {
		//vérifie que ce service météo n'existe pas deja
		if (MeteoServiceDatabaseService.exists(null, libelle)) {
			error = MessageFormat.format(getText("meteo.service.creation.existe.deja"), libelle);
			return false;
		}
		return true;
	}

	@Override
	protected String executeMetier() {
		try {
			MeteoServiceDatabaseService.create(libelle);
			info = MessageFormat.format(getText("meteo.service.creation.valide"), new Object[]{libelle});
			libelle = null;
			return OK;
		} 
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Creation d'un service météo - ", e);
			return ERROR;
		}
	}

}
