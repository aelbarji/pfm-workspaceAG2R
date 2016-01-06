package pilotage.environnement.actions.type;

import java.text.MessageFormat;

import pilotage.database.environnement.EnvironnementTypeDatabaseService;
import pilotage.database.historique.HistoriqueDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;

public class ModifierEnvironnementTypeAction extends AbstractAction{

	private static final long serialVersionUID = 1440408612673689847L;
	
	private Integer selectRow;
	private String  libelle;
	private Boolean libelleChanged;

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

	public Boolean getLibelleChanged() {
		return libelleChanged;
	}

	public void setLibelleChanged(Boolean libelleChanged) {
		this.libelleChanged = libelleChanged;
	}

	@Override
	protected boolean validateMetier() {
		try {
			//vérifie que le libellé n'existe pas deja
			if (libelleChanged && EnvironnementTypeDatabaseService.exists(selectRow, libelle)) {
				error = MessageFormat.format(getText("environnement.type.creation.existe.deja"), libelle);
				libelle = EnvironnementTypeDatabaseService.get(selectRow).getType();
				return false;
			}
			if(EnvironnementTypeDatabaseService.isPrincipal(selectRow)){
				error = MessageFormat.format(getText("environnement.type.principal.modification.failed"), libelle);
				libelle = EnvironnementTypeDatabaseService.get(selectRow).getType();
				return false;
			}
		} catch (Exception e) {
			error = getText("error.message.generique")+ ":"+ e.getMessage();
			erreurLogger.error("Modification d'un type d'environnement",e);
			return false;
		}
		return true;
	}

	@Override
	protected String executeMetier() {
		try {
			if (libelleChanged)
				EnvironnementTypeDatabaseService.modifier(selectRow, libelle);

			HistoriqueDatabaseService.create(null, MessageFormat.format(getText("historique.environnement.type.modification"),new Object[]{selectRow}), (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_ENVIRONNEMENT);

			info = MessageFormat.format(getText("environnement.type.modification.valide"), new Object[]{libelle});
			libelle = "";
			
			return OK;

		} catch (Exception e) {
			error = getText("error.message.generique") + ":"+ e.getMessage();
			erreurLogger.error("Modification d'un type d'environnement",e);
			return ERROR;
		}
	}

}
