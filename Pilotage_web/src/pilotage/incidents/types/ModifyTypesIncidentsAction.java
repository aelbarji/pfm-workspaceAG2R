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
public class ModifyTypesIncidentsAction extends AbstractAction {

	private static final long serialVersionUID = 7629990744840560385L;
	private List<Incidents_Type> listType;
	private Integer typeID;
	private String libelle;
	private String description;
	private Integer impact;
	private String titre_bilan;
	private Boolean libelleChanged;


	public List<Incidents_Type> getListType() {
		return listType;
	}

	public void setListType(List<Incidents_Type> listType) {
		this.listType = listType;
	}

	public Integer getTypeID() {
		return typeID;
	}

	public void setTypeID(Integer typeID) {
		this.typeID = typeID;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getImpact() {
		return impact;
	}

	public void setImpact(Integer impact) {
		this.impact = impact;
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
			if(libelleChanged && IncidentsTypesDatabaseService.exists(typeID, libelle)){
				error = MessageFormat.format(getText("incidents.type.creation.existe.deja"), new Object[]{libelle});
				libelle = IncidentsTypesDatabaseService.get(typeID).getType();
				return false;
			}
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Modification d'un type d'incident - ", e);
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
			
			IncidentsTypesDatabaseService.update(typeID, libelle, description, impact, titre_bilan);
			HistoriqueDatabaseService.create(null, MessageFormat.format(getText("historique.incident.type.modification"), typeID), (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_INCIDENTS);

			info = MessageFormat.format(getText("incidents.type.modification.valide"), libelle);
			libelle = null;
			listType = IncidentsTypesDatabaseService.getAll();
			return OK;
		} catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Modification d'un type d'incident - ", e);
			return ERROR;	
		}
	}

}
