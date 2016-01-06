package pilotage.machines.actions.domaine;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import pilotage.database.historique.HistoriqueDatabaseService;
import pilotage.database.machine.MachineDomaineDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.machines.actions.liste.RedirectCreateMachineListAction;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;


public class CreateMachineDomaineAction extends AbstractAction {
	
	private static final long serialVersionUID = -1299589526541605339L;
	
	private String libelle;
	private List<Map<String, String>> listLogin;

	/**
	 * @return the libelle
	 */
	public String getLibelle() {
		return libelle;
	}

	/**
	 * @param libelle the libelle to set
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public List<Map<String, String>> getListLogin() {
		return listLogin;
	}

	public void setListLogin(List<Map<String, String>> listLogin) {
		this.listLogin = listLogin;
	}

	@Override
	protected boolean validateMetier() {
		try{
			if(MachineDomaineDatabaseService.exists(null, libelle)){
				error = MessageFormat.format(getText("machine.domaine.creation.existe.deja"), libelle);
				prepareRedirectToCreationPage();
				return false;
			}
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Création d'un domaine de machine - ", e);
			prepareRedirectToCreationPage();
			return false;
		}
		return true;
	}

	@Override
	protected String executeMetier() {
		try{
			HttpServletRequest request = ServletActionContext.getRequest();

			listLogin = new ArrayList<Map<String,String>>();
			RedirectCreateMachineDomaineAction.getListLoginDomaine(request, listLogin);
			
			MachineDomaineDatabaseService.create(libelle, listLogin);
			info = MessageFormat.format(getText("machine.domaine.creation.valide"), new Object[]{libelle});
			HistoriqueDatabaseService.create(null, MessageFormat.format(getText("historique.machine.domaine.creation"), new Object[]{libelle,MachineDomaineDatabaseService.getId(libelle)}), (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_MACHINES);
			libelle = null;
			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Création d'un domaine de machine - ", e);
			prepareRedirectToCreationPage();
			return ERROR;
		}
	}
	
	/**
	 * Action avant redirection vers la page de création
	 */
	private void prepareRedirectToCreationPage(){
		HttpServletRequest request = ServletActionContext.getRequest();
		
		listLogin = new ArrayList<Map<String,String>>();
		RedirectCreateMachineListAction.getListLoginAccesLocal(request, listLogin);
	}
}