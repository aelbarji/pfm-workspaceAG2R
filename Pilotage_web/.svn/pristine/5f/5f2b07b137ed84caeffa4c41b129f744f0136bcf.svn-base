package pilotage.bilan.flux.cft;

import java.text.MessageFormat;

import pilotage.database.bilan.FluxCFTDatabaseService;
import pilotage.database.historique.HistoriqueDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;

public class CreateFluxCFTAction extends AbstractAction {

	private static final long serialVersionUID = -7148872830874806510L;
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
			if(FluxCFTDatabaseService.exists(null, libelle)){
				error = MessageFormat.format(getText("flux.CFT.existe.deja"), libelle);
				return false;
			}
		}
		catch (Exception e){
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Création d'un flux - ", e);
			return false;
		}
		return true;
	}

	@Override
	protected String executeMetier() {
		try{
			FluxCFTDatabaseService.create(libelle);
			info = MessageFormat.format(getText("flux.CFT.creation.valide"), libelle);
			HistoriqueDatabaseService.create(null, MessageFormat.format(getText("historique.bilan.flux.creation"), new Object[]{libelle, FluxCFTDatabaseService.getId(libelle)}), (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_BILAN);

			return OK;
		}
		catch (Exception e){
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Création d'un flux - ", e);
			return ERROR;
		}
	}

}
