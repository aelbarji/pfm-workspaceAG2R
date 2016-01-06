package pilotage.astreinte.destinataires;

import java.util.List;

import pilotage.database.astreintes.AstreinteDestinatairesDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Astreinte_Destinataires;

public class ShowDestinatairesAction extends AbstractAction {

	private static final long serialVersionUID = 2847360271799323541L;
	private List<Astreinte_Destinataires> listDestina;
	private Integer selectedType;

	public List<Astreinte_Destinataires> getListDestina() {
		return listDestina;
	}

	public void setListDestina(List<Astreinte_Destinataires> listDestina) {
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
		listDestina = AstreinteDestinatairesDatabaseService.getAll();
		return OK;
	}

}
