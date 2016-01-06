package pilotage.incidents.types;

import java.text.MessageFormat;
import java.util.List;

import pilotage.database.historique.HistoriqueDatabaseService;
import pilotage.database.incidents.IncidentsTypesDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Incidents_Type;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;

/**
 * @author xxu
 *
 */
public class CreateTypesIncidentsAction extends AbstractAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1648576541502846958L;
	private List<Incidents_Type> listType;
	private String libelle;
	private String description;
	private String titre_bilan;
	
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTitre_bilan() {
		return titre_bilan;
	}

	public void setTitre_bilan(String titre_bilan) {
		this.titre_bilan = titre_bilan;
	}

	/* (non-Javadoc)
	 * @see pilotage.framework.AbstractAction#validateMetier()
	 */
	@Override
	protected boolean validateMetier() {
		try{
			if(IncidentsTypesDatabaseService.exists(null, libelle)){
				error = MessageFormat.format(getText("incidents.type.creation.existe.deja"), new Object[]{libelle});
				listType = IncidentsTypesDatabaseService.getAll();
				return false;
			}
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Création d'un type d'incident - ", e);
			listType = IncidentsTypesDatabaseService.getAll();
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see pilotage.framework.AbstractAction#executeMetier()
	 */
	@Override
	protected String executeMetier() {	
		try {
			Integer incidentsTypeSize = IncidentsTypesDatabaseService.getAll().size();
		
			//Incrémentation de l'impact
			Integer impact = ++incidentsTypeSize;
			IncidentsTypesDatabaseService.create(libelle,description, impact, titre_bilan);
			
			info = MessageFormat.format(getText("incidents.type.creation.valide"), new Object[]{libelle});
			HistoriqueDatabaseService.create(null, MessageFormat.format(getText("historique.incident.type.creation"), new Object[]{libelle, IncidentsTypesDatabaseService.getId(libelle)}), (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_INCIDENTS);

			libelle = null;
			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Création d'un type d'incident - ", e);
			return ERROR;	
		}
		finally{
			listType = IncidentsTypesDatabaseService.getAll();
		}
	}

}
