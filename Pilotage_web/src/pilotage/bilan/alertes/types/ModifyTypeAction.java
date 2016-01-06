package pilotage.bilan.alertes.types;

import java.text.MessageFormat;
import java.util.List;

import pilotage.database.bilan.AlertesTypeDatabaseService;
import pilotage.database.historique.HistoriqueDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Alertes_Type;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;

public class ModifyTypeAction extends AbstractAction {

	private static final long serialVersionUID = 1186460177212489370L;
	private int selectedID;
	private List<Alertes_Type> listType;
	private String libelle;
	private Boolean libelleChanged;

	public int getSelectedID() {
		return selectedID;
	}

	public void setSelectedID(int selectedID) {
		this.selectedID = selectedID;
	}

	public List<Alertes_Type> getListType() {
		return listType;
	}

	public void setListType(List<Alertes_Type> listType) {
		this.listType = listType;
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
			if(libelleChanged && AlertesTypeDatabaseService.exists(selectedID, libelle)){
				error = MessageFormat.format(getText("alertes.type.existe.deja"), libelle);
				libelle = AlertesTypeDatabaseService.get(selectedID).getType();
				libelleChanged = false;
				return false;
			}
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Modification d'un type d'alerte disque - ", e);
			return false;
		}
		return true;
	}

	@Override
	protected String executeMetier() {
		try {
			AlertesTypeDatabaseService.update(selectedID, libelle);
			info = MessageFormat.format(getText("alertes.type.modification.valide"), libelle);
			HistoriqueDatabaseService.create(null, MessageFormat.format(getText("historique.bilan.alerte.type.modification"), selectedID), (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_BILAN);
			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Modification d'un type d'alerte disque - ", e);
			return ERROR;
		}
	}

}
