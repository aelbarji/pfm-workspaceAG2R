package pilotage.meteo.etat;

import pilotage.database.meteo.EtatPossibleDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Meteo_EtatPossible;

public class RedirectModifyEtatMeteoAction extends AbstractAction{

	private static final long serialVersionUID = 3005193920978692358L;

	private Integer selectRow;
	private Meteo_EtatPossible etat;
	
	public Integer getSelectRow() {
		return selectRow;
	}

	public void setSelectRow(Integer selectRow) {
		this.selectRow = selectRow;
	}

	public Meteo_EtatPossible getEtat() {
		return etat;
	}

	public void setEtat(Meteo_EtatPossible etat) {
		this.etat = etat;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		try{
			etat = EtatPossibleDatabaseService.get(selectRow);
			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Modification d'un état Météo - ", e);
			return ERROR;
		}
	}

}
