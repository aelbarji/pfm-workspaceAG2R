package pilotage.machines.actions.os;

import java.text.MessageFormat;

import pilotage.database.historique.HistoriqueDatabaseService;
import pilotage.database.machine.MachineOSDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;


public class CreateMachineOSAction extends AbstractAction {
	
	private static final long serialVersionUID = -1299589526541605339L;
	
	private String libelle;

	/**
	 * @return the libelle
	 */
	public String getLibelle() {
		return libelle;
	}

	/**
	 * @param libelle the libelle to set
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	@Override
	protected boolean validateMetier() {
		try{
			if(MachineOSDatabaseService.exists(null, libelle)){
				error = MessageFormat.format(getText("machine.OS.creation.existe.deja"), libelle);
				return false;
			}
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Création d'un OS - ", e);
			return false;
		}
		return true;
	}

	@Override
	protected String executeMetier() {
		try{
			MachineOSDatabaseService.create(libelle);
			info = MessageFormat.format(getText("machine.OS.creation.valide"), new Object[]{libelle});
			HistoriqueDatabaseService.create(null, MessageFormat.format(getText("historique.machine.OS.creation"), new Object[]{libelle, MachineOSDatabaseService.getId(libelle)}), (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_MACHINES);

			libelle = null;
			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Création d'un OS - ", e);
			return ERROR;
		}
	}
}
