package pilotage.environnement.actions.environnement;

import java.text.MessageFormat;

import pilotage.database.environnement.EnvironnementDatabaseService;
import pilotage.database.environnement.EnvironnementTypeDatabaseService;
import pilotage.database.historique.HistoriqueDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Environnement;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;

public class ModifierEnvironnementAction extends AbstractAction{

	private static final long serialVersionUID = -5715195244432667467L;

	private Integer selectRow;
	private String  libelle;
	private Integer type;
	private Boolean libelleChanged;
	
	private String  sort;
	private String  sens;
	private int page;
	private int nrPages;
	private int nrPerPage;
	
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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Boolean getLibelleChanged() {
		return libelleChanged;
	}

	public void setLibelleChanged(Boolean libelleChanged) {
		this.libelleChanged = libelleChanged;
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
			//si un element a changé, on verifie si la combinaison n'existe pas déjà
			if (libelleChanged && EnvironnementDatabaseService.exists(selectRow, libelle, type)) {
				error = MessageFormat.format(getText("environnement.creation.existe.deja"), libelle + "/" + EnvironnementTypeDatabaseService.get(type).getType());
				
				Environnement environnement = EnvironnementDatabaseService.get(selectRow);
				libelle = environnement.getEnvironnement();
				type = environnement.getType().getId();
				
				return false;
			}
		}
		catch (Exception e) {
			error = getText("error.message.generique")+":"+e.getMessage();
			erreurLogger.error("Modification d'environnement - ",e);
			return false;
		}
		
		return true;
	}

	@Override
	protected String executeMetier() {
		try {
			String historique = " ";
			Environnement e = EnvironnementDatabaseService.get(selectRow);
			if (!libelle.equals(e.getEnvironnement())) {
				historique += "libelle, ";
			}
			if (!type.equals(e.getType().getId())) {
				historique += "type, ";
			}
			EnvironnementDatabaseService.modifier(selectRow, libelle, type);
			
			HistoriqueDatabaseService.create(null, MessageFormat.format(getText("historique.environnement.modification"),new Object[]{historique,selectRow}), (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_ENVIRONNEMENT);

			info = MessageFormat.format(getText("environnement.modification.valide"), new Object[]{libelle});
			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + ":" + e.getMessage();
			erreurLogger.error("Modification d'un environnement - ",e);
		    return ERROR;
		}
	}
}
