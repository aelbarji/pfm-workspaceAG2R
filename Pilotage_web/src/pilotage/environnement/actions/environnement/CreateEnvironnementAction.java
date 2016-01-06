package pilotage.environnement.actions.environnement;

import java.text.MessageFormat;

import pilotage.database.environnement.EnvironnementDatabaseService;
import pilotage.database.historique.HistoriqueDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;

public class CreateEnvironnementAction extends AbstractAction {

	private static final long serialVersionUID = -5715195244432667467L;

	private String  libelle;
	private Integer type;

	private String  sort;
	private String  sens;
	private int page;
	private int nrPages;
	private int nrPerPage;

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
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
		try {
			//vérifie que cet environnement n'existe pas deja
			if (EnvironnementDatabaseService.exists(null, libelle ,type)) {
				error = MessageFormat.format(getText("environnement.creation.existe.deja"), libelle);
				return false;
			}
		} catch (Exception e) {
			error  = getText("error.message.generique") + ":" + e.getMessage();
			erreurLogger.error("Creation d'un environnement - ",e);
			return false;
		}
		
		return true;
	}

	@Override
	protected String executeMetier() {
		try {
			//creation d'une nouvel environnement
			EnvironnementDatabaseService.create(libelle, type);
			
			HistoriqueDatabaseService.create(null, MessageFormat.format(getText("historique.environnement.creation"),new Object[]{libelle,EnvironnementDatabaseService.getId(libelle, type)}), (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_ENVIRONNEMENT);

			info = MessageFormat.format(getText("environnement.creation.valide"), new Object[]{libelle});

			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + ":" + e.getMessage();
			erreurLogger.error("Creation d'un environnement - ", e);
			return ERROR;
		}
	}
}
