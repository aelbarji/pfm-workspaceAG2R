package pilotage.bilan.disques;

import java.text.MessageFormat;

import pilotage.database.bilan.DisqueDatabaseService;
import pilotage.database.historique.HistoriqueDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Disques;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;

public class ModifyDisquesAction extends AbstractAction {

	private static final long serialVersionUID = -5102282578912816137L;
	private Integer selectedID;
	private String libelle;
	private Integer filiale;
	private String seuil;
	private Boolean filialeChanged;
	private Boolean libelleChanged;
	
	public Integer getSelectedID() {
		return selectedID;
	}

	public void setSelectedID(Integer selectedID) {
		this.selectedID = selectedID;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public Integer getFiliale() {
		return filiale;
	}

	public void setFiliale(Integer filiale) {
		this.filiale = filiale;
	}

	public String getSeuil() {
		return seuil;
	}

	public void setSeuil(String seuil) {
		this.seuil = seuil;
	}

	public Boolean getFilialeChanged() {
		return filialeChanged;
	}

	public void setFilialeChanged(Boolean filialeChanged) {
		this.filialeChanged = filialeChanged;
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
			if (libelleChanged && DisqueDatabaseService.exists(selectedID, libelle)) {
				error = MessageFormat.format(getText("bilan.disques.existe.deja"), libelle);
				Disques disque = DisqueDatabaseService.get(selectedID);
				libelle = disque.getLibelle();
				filiale = disque.isFiliale() ? 1 : 0;
				return false;
			}
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Modification d'un disque - ", e);
			return false;
		}
		return true;
	}

	@Override
	protected String executeMetier() {
		try{
			String historique = "";
			Disques d = DisqueDatabaseService.get(selectedID);
			if (!libelle.equals(d.getLibelle())) {
				historique += "libelle, ";
			}
			if (filiale == 1 ? true : false != d.isFiliale()) {
				historique += "filiale, ";
			}
			if (!seuil.equals(d.getSeuil())) {
				historique += "seuil, ";
			}
			DisqueDatabaseService.update(selectedID, libelle, filiale == 1 ? true : false, Float.parseFloat(seuil.replaceAll(",", ".")));
			HistoriqueDatabaseService.create(null, MessageFormat.format(getText("historique.bilan.disque.modification"), new Object[]{selectedID, historique}), (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_BILAN);

			info = MessageFormat.format(getText("bilan.disques.modification.valide"), libelle);
			return OK;
		}
		catch (Exception e){
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Modification d'un disque - ", e);
			return ERROR;
		}
	}
}
