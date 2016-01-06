package pilotage.gup.incidents;

import pilotage.database.gup.IncidentsGupDatabaseService;
import pilotage.framework.AbstractAction;

public class ReinitialiserIncidentGupAction extends AbstractAction {

	private static final long serialVersionUID = -1952210288732796552L;
	private Integer selectedID;
	private String provenance;
	
	public Integer getSelectedID() {
		return selectedID;
	}

	public void setSelectedID(Integer selectedID) {
		this.selectedID = selectedID;
	}

	public String getProvenance() {
		return provenance;
	}

	public void setProvenance(String provenance) {
		this.provenance = provenance;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		try {
			IncidentsGupDatabaseService.reinitialiserIncident(selectedID);
			return provenance;
		} catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Reinitialisation d'un incident GUP - ", e);
			return ERROR;
		}
		
	}

}
