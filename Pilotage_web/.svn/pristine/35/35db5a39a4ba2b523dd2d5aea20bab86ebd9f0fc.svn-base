package pilotage.meteo.etat;

import java.util.List;

import pilotage.database.meteo.EtatPossibleDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Meteo_EtatPossible;

public class ShowEtatAction extends AbstractAction{
	
	private static final long serialVersionUID = -594339074342554359L;

	private List<Meteo_EtatPossible> listEtat;
	
	public List<Meteo_EtatPossible> getListEtat() {
		return listEtat;
	}

	public void setListEtat(List<Meteo_EtatPossible> listEtat) {
		this.listEtat = listEtat;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		try{
			listEtat = EtatPossibleDatabaseService.getAll();
			return OK;
		}catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Affichage de la liste des états - ", e);
			return ERROR;	
		}
	}

}
