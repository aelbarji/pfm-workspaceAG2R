package pilotage.user.profil;

import java.util.List;

import pilotage.admin.metier.Profil;
import pilotage.framework.AbstractAction;
import pilotage.service.constants.PilotageConstants;
import pilotage.service.menu.MenuGenerator;
import pilotage.service.menu.PilotageMenu;

public class RedirectUserProfilAction extends AbstractAction {
	
	private static final long serialVersionUID = 7802877358655502887L;
	private String redirect;

	public String getRedirect() {
		return redirect;
	}

	public void setRedirect(String redirect) {
		this.redirect = redirect;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected String executeMetier() {
		Profil profil = (Profil)session.get(PilotageConstants.USER_PROFIL);
		if(profil.getAccueil() != null && MenuGenerator.contains((List<PilotageMenu>)session.get(PilotageConstants.MENUS), profil.getAccueil()))
			redirect = profil.getAccueil().getLien();
		
		return OK;
	}
}
