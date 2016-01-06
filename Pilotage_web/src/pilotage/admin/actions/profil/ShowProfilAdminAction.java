package pilotage.admin.actions.profil;

import java.util.List;

import pilotage.admin.metier.Profil;
import pilotage.database.admin.ProfilDatabaseService;
import pilotage.framework.AbstractAdminAction;

public class ShowProfilAdminAction extends AbstractAdminAction {
	
	private static final long serialVersionUID = -4037713125016637085L;
	private List<Profil> listProfil;
	
	/**
	 * @return the listProfil
	 */
	public List<Profil> getListProfil() {
		return listProfil;
	}

	/**
	 * @param listProfil the listProfil to set
	 */
	public void setListProfil(List<Profil> listProfil) {
		this.listProfil = listProfil;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		listProfil = ProfilDatabaseService.getAll();
		return OK;
	}
}
