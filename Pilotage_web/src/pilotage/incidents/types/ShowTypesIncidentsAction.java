package pilotage.incidents.types;

import java.util.List;

import pilotage.database.incidents.IncidentsTypesDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Incidents_Type;

public class ShowTypesIncidentsAction extends AbstractAction {

	private static final long serialVersionUID = 158510374602104754L;
	private List<Incidents_Type> listType;

	private String libelle;

	public List<Incidents_Type> getListType() {
		return listType;
	}

	public void setListType(List<Incidents_Type> listType) {
		this.listType = listType;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		if (error == null || error.equals(""))
			libelle = null;

		listType = IncidentsTypesDatabaseService.getAll();

		return OK;
	}

	public String getJSON() {
		return executeMetier();
	}

}
