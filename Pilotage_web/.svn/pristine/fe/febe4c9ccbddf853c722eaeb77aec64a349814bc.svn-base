package pilotage.incidents.outils;

import pilotage.database.incidents.IncidentsOutilsDatabaseService;
import pilotage.framework.AbstractAction;

public class RedirectModifyOutilIncidentAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	private Integer outilID;
	private String nomOutils;

	public Integer getOutilID() {
		return outilID;
	}

	public void setOutilID(Integer outilID) {
		this.outilID = outilID;
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
		nomOutils = IncidentsOutilsDatabaseService.get(outilID).getNomOutils();
		return OK;
	}

}
