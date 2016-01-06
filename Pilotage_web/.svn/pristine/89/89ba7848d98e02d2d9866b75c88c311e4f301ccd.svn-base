package pilotage.bilan.flux.cft;

import java.text.MessageFormat;

import pilotage.database.bilan.FluxCFTDatabaseService;
import pilotage.database.historique.HistoriqueDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;

public class ModifyFluxCFTAction extends AbstractAction {

	private static final long serialVersionUID = 6796336274935922988L;
	private Integer selectedID;
	private String libelle;
	private Boolean libelleChanged;

	/**
	 * @return the selectedID
	 */
	public Integer getSelectedID() {
		return selectedID;
	}

	/**
	 * @param selectedID the selectedID to set
	 */
	public void setSelectedID(Integer selectedID) {
		this.selectedID = selectedID;
	}

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
	
	/**
	 * @return the libelleChanged
	 */
	public Boolean getLibelleChanged() {
		return libelleChanged;
	}
	
	/**
	 * @param libelleChanged the libelleChanged to set
	 */
	public void setLibelleChanged(Boolean libelleChanged) {
		this.libelleChanged = libelleChanged;
	}
	
	@Override
	protected boolean validateMetier() {
		try{
			if(libelleChanged && FluxCFTDatabaseService.exists(selectedID, libelle)){
				error = MessageFormat.format(getText("flux.CFT.existe.deja"), libelle);
				libelle = FluxCFTDatabaseService.get(selectedID).getLibelle();
				return false;
			}
		} 
		catch (Exception e){
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Modification d'un flux - ", e);
			return false;
		}
		return true;
	}

	@Override
	protected String executeMetier() {
		try{
			FluxCFTDatabaseService.update(selectedID , libelle);
			info = MessageFormat.format(getText("flux.CFT.modification.valide"), libelle);
			HistoriqueDatabaseService.create(null, MessageFormat.format(getText("historique.bilan.flux.modification"), selectedID), (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_BILAN);

			return OK;
		}
		catch (Exception e){
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Modification d'un flux - ", e);
			return ERROR;
		}
	}

}
