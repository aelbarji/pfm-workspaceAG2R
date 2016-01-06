package pilotage.derogation.valideur;

import java.util.ArrayList;
import java.util.List;

import pilotage.database.derogation.DerogationValideurDatabaseService;
import pilotage.database.users.management.UsersDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Derogation_Valideur;
import pilotage.metier.Users;

public class ShowDerogationValideurAction extends AbstractAction{

	private static final long serialVersionUID = 3434875538278099536L;

	private List<Users> listUserNonValideurs;
	private List<Derogation_Valideur> listUserValideurs;

	public List<Users> getListUserNonValideurs() {
		return listUserNonValideurs;
	}

	public void setListUserNonValideurs(List<Users> listUserNonValideurs) {
		this.listUserNonValideurs = listUserNonValideurs;
	}

	public List<Derogation_Valideur> getListUserValideurs() {
		return listUserValideurs;
	}

	public void setListUserValideurs(List<Derogation_Valideur> listUserValideurs) {
		this.listUserValideurs = listUserValideurs;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		listUserValideurs = DerogationValideurDatabaseService.getAll();
		List<Integer> valideursID = new ArrayList<Integer>();
		for(Derogation_Valideur dv : listUserValideurs){
			valideursID.add(dv.getValideur().getId());
		}
		listUserNonValideurs = UsersDatabaseService.getAllExcept(valideursID, null, null);
		return OK;
	}
}
