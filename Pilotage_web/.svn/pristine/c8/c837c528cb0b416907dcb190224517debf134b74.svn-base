package pilotage.incidents.types;

import java.text.MessageFormat;
import java.util.List;

import pilotage.database.historique.HistoriqueDatabaseService;
import pilotage.database.incidents.IncidentsDatabaseService;
import pilotage.database.incidents.IncidentsTypesDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Incidents_Type;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;

/**
 * @author xxu
 *
 */
public class DeleteTypesIncidentsAction extends AbstractAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4685803812313387129L;
	private List<Incidents_Type> listType;
	private int typeID;

	/**
	 * @return the typeID
	 */
	public int getTypeID() {
		return typeID;
	}

	/**
	 * @param typeID the typeID to set
	 */
	public void setTypeID(int typeID) {
		this.typeID = typeID;
	}

	/**
	 * @return the listType
	 */
	public List<Incidents_Type> getListType() {
		return listType;
	}

	/**
	 * @param listType the listType to set
	 */
	public void setListType(List<Incidents_Type> listType) {
		this.listType = listType;
	}

	/* (non-Javadoc)
	 * @see pilotage.framework.AbstractAction#validateMetier()
	 */
	@Override
	protected boolean validateMetier() {
		try{
			if(IncidentsDatabaseService.hasIncidentType(typeID)){
				error = getText("incidents.type.supprimer.failed");
				listType = IncidentsTypesDatabaseService.getAll();
				return false;
			}
			return true;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Suppression d'un type d'incident - ", e);
			return false;	
		}
	}

	/* (non-Javadoc)
	 * @see pilotage.framework.AbstractAction#executeMetier()
	 */
	@Override
	protected String executeMetier() {
		try {
			String libelle = IncidentsTypesDatabaseService.get(typeID).getType();
		
			IncidentsTypesDatabaseService.delete(typeID);
			HistoriqueDatabaseService.create(null, MessageFormat.format(getText("historique.incident.type.suppression"), new Object[]{libelle, typeID}), (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_INCIDENTS);

			info = MessageFormat.format(getText("incidents.type.supprimer.valide"), new Object[]{libelle});
			listType = IncidentsTypesDatabaseService.getAll();
			return OK;
		
		} catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Suppression d'un type d'incident - ", e);
			return ERROR;	
		}
	}

}
