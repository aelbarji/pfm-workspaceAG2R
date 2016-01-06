package pilotage.incidents.outils;

import java.util.List;

import pilotage.database.incidents.IncidentsOutilsDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Incidents_Outils;

public class ShowOutilsIncidentAction extends AbstractAction {

	private static final long serialVersionUID = 4599001528849364705L;
	private List<Incidents_Outils> listOutils;
	private String nomOutils;

	public List<Incidents_Outils> getListOutils() {
		return listOutils;
	}

	public void setListOutils(List<Incidents_Outils> listOutils) {
		this.listOutils = listOutils;
	}

	public String getNomOutils() {
		return nomOutils;
	}

	public void setNomOutils(String nomOutils) {
		this.nomOutils = nomOutils;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		if(error == null || error.equals("")){
			nomOutils = null;
		}
		
		listOutils = IncidentsOutilsDatabaseService.getAll();
		return OK;
	}

}
