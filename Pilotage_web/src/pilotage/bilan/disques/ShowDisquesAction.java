package pilotage.bilan.disques;

import java.util.List;

import pilotage.database.bilan.DisqueDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Disques;

public class ShowDisquesAction extends AbstractAction {

	private static final long serialVersionUID = -9041201672071758915L;
	private List<Disques> listDisques;

	/**
	 * @return the listDisques
	 */
	public List<Disques> getListDisques() {
		return listDisques;
	}

	/**
	 * @param listDisques the listDisques to set
	 */
	public void setListDisques(List<Disques> listDisques) {
		this.listDisques = listDisques;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		listDisques = DisqueDatabaseService.getAll();
		return OK;
	}

}
