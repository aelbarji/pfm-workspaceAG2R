package pilotage.bilan.alertes.destinataires;

import java.util.List;

import pilotage.database.bilan.AlertesDisquesDestinatairesDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Alertes_Disques_Destinataires;

public class ShowDestinatairesAction extends AbstractAction {

	private static final long serialVersionUID = -8885247425484484989L;
	private List<Alertes_Disques_Destinataires> listDestina;

	public List<Alertes_Disques_Destinataires> getListDestina() {
		return listDestina;
	}

	public void setListDestina(List<Alertes_Disques_Destinataires> listDestina) {
		this.listDestina = listDestina;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		listDestina = AlertesDisquesDestinatairesDatabaseService.getAll();
		return OK;
	}

}
