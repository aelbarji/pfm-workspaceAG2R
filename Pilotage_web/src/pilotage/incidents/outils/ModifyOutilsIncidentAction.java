package pilotage.incidents.outils;

import java.text.MessageFormat;
import java.util.List;

import pilotage.database.historique.HistoriqueDatabaseService;
import pilotage.database.incidents.IncidentsOutilsDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Incidents_Outils;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;

public class ModifyOutilsIncidentAction extends AbstractAction {

	private static final long serialVersionUID = -3272190295481138937L;
	private List<Incidents_Outils> listOutils;
	private Integer outilID;
	private String nomOutils;
	private Boolean libelleChanged;

	public Boolean getLibelleChanged() {
		return libelleChanged;
	}

	public void setLibelleChanged(Boolean libelleChanged) {
		this.libelleChanged = libelleChanged;
	}

	/**
	 * @return the listOutils
	 */
	public List<Incidents_Outils> getListOutils() {
		return listOutils;
	}

	/**
	 * @param listOutils the listOutils to set
	 */
	public void setListOutils(List<Incidents_Outils> listOutils) {
		this.listOutils = listOutils;
	}

	/**
	 * @return the outilID
	 */
	public Integer getOutilID() {
		return outilID;
	}

	/**
	 * @param outilID the outilID to set
	 */
	public void setOutilID(Integer outilID) {
		this.outilID = outilID;
	}

	/**
	 * @return the nomOutils
	 */
	public String getNomOutils() {
		return nomOutils;
	}

	/**
	 * @param nomOutils the nomOutils to set
	 */
	public void setNomOutils(String nomOutils) {
		this.nomOutils = nomOutils;
	}

	@Override
	protected boolean validateMetier() {
		try{
			if(libelleChanged && IncidentsOutilsDatabaseService.exists(outilID, nomOutils)){
				error = MessageFormat.format(getText("incidents.outil.creation.existe.deja"), new Object[]{nomOutils});
				nomOutils = IncidentsOutilsDatabaseService.get(outilID).getNomOutils();
				return false;
			}
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Modification d'un outil - ", e);
			return false;
		}
		return true;
	}

	@Override
	protected String executeMetier() {
		
		try {
			if(libelleChanged){
				IncidentsOutilsDatabaseService.update(outilID, nomOutils);
				HistoriqueDatabaseService.create(null, MessageFormat.format(getText("historique.incident.outil.modification"), outilID), (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_INCIDENTS);
			}

			listOutils = IncidentsOutilsDatabaseService.getAll();
			info = MessageFormat.format(getText("incidents.outil.modification.valide"), nomOutils);
			nomOutils = null;
			return OK;
		} catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Modification d'un outil - ", e);
			return ERROR;	
		}
	}

}
