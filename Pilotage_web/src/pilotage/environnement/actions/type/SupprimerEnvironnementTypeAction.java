package pilotage.environnement.actions.type;

import java.text.MessageFormat;

import pilotage.database.environnement.EnvironnementDatabaseService;
import pilotage.database.environnement.EnvironnementTypeDatabaseService;
import pilotage.database.historique.HistoriqueDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;

public class SupprimerEnvironnementTypeAction extends AbstractAction {

	private static final long serialVersionUID = 3081434809195809630L;

	private Integer selectRow;

	public void setSelectRow(Integer selectRow) {
		this.selectRow = selectRow;
	}

	public Integer getSelectRow() {
		return selectRow;
	}

	@Override
	protected boolean validateMetier() {
		try {
			// vérifie que cet environnement type existe ou modifié ou non
			if (EnvironnementDatabaseService.hasEnvironnementType(selectRow)) {
				error = getText("environnement.type.suppression.failed");
				return false;
			}
			if (EnvironnementTypeDatabaseService.isPrincipal(selectRow)) {
				error = getText("environnement.type.principal.suppression.failed");
				return false;
			}
			return true;
		} catch (Exception e) {
			error = getText("error.message.generique") + ":" + e.getMessage();
			erreurLogger.error("Suppression d'un type d'environnement - ", e);

			return false;
		}
	}

	@Override
	protected String executeMetier() {
		try {
			// suppression du environnement type
			String environnement_Type = EnvironnementTypeDatabaseService.get(
					selectRow).getType();

			EnvironnementTypeDatabaseService.delete(selectRow);

			HistoriqueDatabaseService.create(null, MessageFormat.format(
					getText("historique.environnement.type.suppression"),
					new Object[] { selectRow, environnement_Type }),
					(Users) session.get(PilotageConstants.USER_LOGGED),
					PilotageConstants.HISTORIQUE_MODULE_ENVIRONNEMENT);

			// message de validation
			info = MessageFormat.format(
					getText("environnement.type.suppression.valide"),
					new Object[] { environnement_Type });

			return OK;
		} catch (Exception e) {
			error = getText("error.message.generique") + ":" + e.getMessage();
			erreurLogger.error("Suppression d'un type d'environnement - ", e);
			return ERROR;
		}
	}
}
