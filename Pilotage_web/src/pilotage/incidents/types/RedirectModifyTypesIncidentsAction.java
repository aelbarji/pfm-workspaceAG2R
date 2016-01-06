package pilotage.incidents.types;

import java.util.List;

import pilotage.database.incidents.IncidentsTypesDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Incidents_Type;

public class RedirectModifyTypesIncidentsAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	private List<Incidents_Type> listType;
	private String libelle;
	private String description;
	private Integer impact;
	private String titre_bilan;
	private Integer typeID;
	
	
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

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		libelle = IncidentsTypesDatabaseService.get(typeID).getType();
		description = IncidentsTypesDatabaseService.get(typeID).getDescription();
		impact = IncidentsTypesDatabaseService.get(typeID).getImpact();
		titre_bilan = IncidentsTypesDatabaseService.get(typeID).getTitre_bilan();
		return OK;
	}

}
