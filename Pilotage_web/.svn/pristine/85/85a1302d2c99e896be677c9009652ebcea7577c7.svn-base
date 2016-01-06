package pilotage.bilan.destinataires;

import java.util.List;

import pilotage.database.bilan.BilanDestinatairesDatabaseService;
import pilotage.database.bilan.BilanEnvoieDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Bilan_Destinataires;
import pilotage.metier.Bilan_Envoie;

public class ShowDestinatairesAction extends AbstractAction {

	private static final long serialVersionUID = 2847360271799323541L;
	private List<Bilan_Envoie> listEnvoie;
	private List<Bilan_Destinataires> listDestina;
	private Integer selectedType;

	public List<Bilan_Envoie> getListEnvoie() {
		return listEnvoie;
	}

	public void setListEnvoie(List<Bilan_Envoie> listEnvoie) {
		this.listEnvoie = listEnvoie;
	}

	public List<Bilan_Destinataires> getListDestina() {
		return listDestina;
	}

	public void setListDestina(List<Bilan_Destinataires> listDestina) {
		this.listDestina = listDestina;
	}

	public Integer getSelectedType() {
		return selectedType;
	}

	public void setSelectedType(Integer selectedType) {
		this.selectedType = selectedType;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		listEnvoie = BilanEnvoieDatabaseService.getAll();
		listDestina = BilanDestinatairesDatabaseService.getAll(selectedType);
		return OK;
	}

}
