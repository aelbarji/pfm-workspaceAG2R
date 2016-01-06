package pilotage.machines.actions.type;

import java.text.MessageFormat;

import pilotage.database.historique.HistoriqueDatabaseService;
import pilotage.database.machine.MachineTypeDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;

public class CreateMachineTypeAction extends AbstractAction {
	
	private static final long serialVersionUID = -1299589526541605339L;

	private String libelle;

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}


	@Override
	protected boolean validateMetier() {
		try{
			if(MachineTypeDatabaseService.exists(null, libelle)){
				error = MessageFormat.format(getText("machine.type.creation.existe.deja"), libelle);
				return false;
			}
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Création d'un type de machine - ", e);
			return false;
		}
		return true;
	}

	@Override
	protected String executeMetier() {
		try{
			MachineTypeDatabaseService.create(libelle);
			info = MessageFormat.format(getText("machine.type.creation.valide"), new Object[]{libelle});
			HistoriqueDatabaseService.create(null, MessageFormat.format(getText("historique.machine.type.creation"),new Object[]{libelle, MachineTypeDatabaseService.getId(libelle)}), (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_MACHINES);
			libelle = null;

			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Création d'un type de machine - ", e);
			return ERROR;
		}
	}
}
