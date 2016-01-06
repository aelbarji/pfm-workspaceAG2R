package pilotage.incidents.types;

import java.util.List;

import pilotage.database.incidents.IncidentsTypesDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Incidents_Type;

public class RedirectCreateTypesIncidentsAction extends AbstractAction {

		private static final long serialVersionUID = 2834692890022736970L;
		private String  libelle;
		
		private List<Incidents_Type> incident_Types ;
	
		public String getLibelle() {
			return libelle;
		}

		public void setLibelle(String libelle) {
			this.libelle = libelle;
		}

		public List<Incidents_Type> getIncident_Types() {
			return incident_Types;
		}

		public void setIncident_Types(List<Incidents_Type> incident_Types) {
			this.incident_Types = incident_Types;
		}

		@Override
		protected boolean validateMetier() {
			return true;
		}

		@Override
		protected String executeMetier() {
			if(error == null || error.equals(""))
				libelle = null;
			
			incident_Types = IncidentsTypesDatabaseService.getAll();
			return OK;
		}

	}


