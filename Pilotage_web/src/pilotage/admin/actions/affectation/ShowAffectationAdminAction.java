package pilotage.admin.actions.affectation;

import java.util.List;

import pilotage.admin.metier.Profil;
import pilotage.database.admin.ProfilDatabaseService;
import pilotage.framework.AbstractAdminAction;
import pilotage.service.constants.PilotageConstants;

/**
 * This class is used to get the data for the affectMenu page
 * Success : listAffectMenu.jsp
 * Failed :
 * Error :
 * 
 * @author Xxu
 *
 */

public class ShowAffectationAdminAction extends AbstractAdminAction {

	private static final long serialVersionUID = -5489498252261751065L;
	
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
		session.remove(PilotageConstants.AFFECTATION_LISTE_MENU);
		session.remove(PilotageConstants.AFFECTATION_LISTE_ID_CHECKBOX);
		return OK;
	}

}
