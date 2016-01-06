package pilotage.incidents.base;

import pilotage.database.incidents.IncidentsDatabaseService;

public class ReinitialiserIncidentsAction extends ShowBaseIncidentsAction {

	private static final long serialVersionUID = -6339377183764359931L;

	private int selectedID;
	private String provenance;
	
	public int getSelectedID() {
		return selectedID;
	}

	public void setSelectedID(int selectedID) {
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
		try{
			IncidentsDatabaseService.reinitialiserIncident(selectedID);
			if(provenance != null && provenance.equals("showBaseIncidents"))
				return "base";
			return OK;
		}
		catch(Exception e){
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Reinitialisation d'un incident - ", e);
			return ERROR;
		}
		finally{
			//récupération des données
			if(ERROR.equals(super.executeMetier()))
				return ERROR;
			
		}
	}
}
