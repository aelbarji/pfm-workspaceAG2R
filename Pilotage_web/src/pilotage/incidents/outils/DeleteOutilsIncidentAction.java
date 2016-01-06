package pilotage.incidents.outils;

import java.text.MessageFormat;
import java.util.List;

import pilotage.database.historique.HistoriqueDatabaseService;
import pilotage.database.incidents.IncidentsDatabaseService;
import pilotage.database.incidents.IncidentsOutilsDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Incidents_Outils;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;

/**
 * @author xxu
 *
 */
public class DeleteOutilsIncidentAction extends AbstractAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6435880591110753510L;
	private List<Incidents_Outils> listOutils;
	private Integer outilID;
	
	
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

	/* (non-Javadoc)
	 * @see pilotage.framework.AbstractAction#validateMetier()
	 */
	@Override
	protected boolean validateMetier() {
		try{
			if(IncidentsDatabaseService.hasIncidentOutil(outilID)){
				error = MessageFormat.format(getText("incidents.outil.supprimer.failed"),new Object[]{});
				listOutils = IncidentsOutilsDatabaseService.getAll();
				return false;
			}
			return true;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Suppression d'un outil - ", e);
			return false;	
		}
	}

	/* (non-Javadoc)
	 * @see pilotage.framework.AbstractAction#executeMetier()
	 */
	@Override
	protected String executeMetier() {

		try {
			String nom = IncidentsOutilsDatabaseService.get(outilID).getNomOutils();
		
			IncidentsOutilsDatabaseService.delete(outilID);
			HistoriqueDatabaseService.create(null, MessageFormat.format(getText("historique.incident.outil.suppression"), new Object[]{nom, outilID}), (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_INCIDENTS);

			info = MessageFormat.format(getText("incidents.outil.supprimer.valide"), nom);
			listOutils = IncidentsOutilsDatabaseService.getAll();
			return OK;
		
		} catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Suppression d'un outil - ", e);
			return ERROR;	
		}
	}

}
