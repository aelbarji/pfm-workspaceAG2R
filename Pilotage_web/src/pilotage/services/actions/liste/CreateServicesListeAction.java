package pilotage.services.actions.liste;

import java.text.MessageFormat;

import pilotage.database.historique.HistoriqueDatabaseService;
import pilotage.database.services.ServicesDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;

public class CreateServicesListeAction extends AbstractAction{

	private static final long serialVersionUID = 3025879305459270312L;

	private String libelle;
	
	private int page;
	private int nrPages;
	private int nrPerPage;

	private String filtreNomService;

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

	public String getFiltreNomService() {
		return filtreNomService;
	}

	public void setFiltreNomService(String filtreNomService) {
		this.filtreNomService = filtreNomService;
	}

	@Override
	protected boolean validateMetier() {
		//vérifie que ce service n'existe pas deja
		if (ServicesDatabaseService.exists(null, libelle)) {
			error = MessageFormat.format(getText("services.liste.creation.existe.deja"), libelle);
			return false;
		}
		return true;
	}
	
	@Override
	protected String executeMetier() {
		try {
			ServicesDatabaseService.create(libelle);
			
			HistoriqueDatabaseService.create(null, MessageFormat.format(getText("historique.services.liste.creation"),new Object[]{libelle,ServicesDatabaseService.getId(libelle)}), (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_SERVICES);

			info = MessageFormat.format(getText("services.liste.creation.valide"), new Object[]{libelle});
			libelle = null;
			
			return OK;
		} 
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Creation d'un service - ", e);
			return ERROR;
		}
	}

}
