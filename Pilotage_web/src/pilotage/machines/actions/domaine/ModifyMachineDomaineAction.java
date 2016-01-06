package pilotage.machines.actions.domaine;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import pilotage.database.historique.HistoriqueDatabaseService;
import pilotage.database.machine.DomaineLoginDatabaseService;
import pilotage.database.machine.MachineDomaineDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Domaine_Wind_Login;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;

public class ModifyMachineDomaineAction extends AbstractAction{

	private static final long serialVersionUID = 7013818341148152623L;
	
	private Boolean libelleChanged;
	private Integer selectRow;
	private String libelle;
	private List<Map<String, String>> listLogin;
	
	/**
	 * @return
	 */
	public Boolean getLibelleChanged() {
		return libelleChanged;
	}

	/**
	 * @param libelleChanged
	 */
	public void setLibelleChanged(Boolean libelleChanged) {
		this.libelleChanged = libelleChanged;
	}

	/**
	 * @return the selectRow
	 */
	public Integer getSelectRow() {
		return selectRow;
	}

	/**
	 * @param selectRow the selectRow to set
	 */
	public void setSelectRow(Integer selectRow) {
		this.selectRow = selectRow;
	}
	
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
			if(libelleChanged && MachineDomaineDatabaseService.exists(selectRow, libelle)){
				error = MessageFormat.format(getText("machine.domaine.creation.existe.deja"), libelle);
				libelle = MachineDomaineDatabaseService.get(selectRow).getNomDomaine();
				prepareRedirectToModificationPage();
				return false;
			}
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Modification d'un domaine de machine - ", e);
			prepareRedirectToModificationPage();
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
			
			List<Domaine_Wind_Login> listLoginsEnBase = DomaineLoginDatabaseService.getLoginsFromDomaine(selectRow);
			
			List<Integer> loginToDelete = new ArrayList<Integer>();
			List<Map<String, String>> loginToUpdate = new ArrayList<Map<String,String>>();
			List<Map<String, String>> loginToAdd = new ArrayList<Map<String,String>>();
			for(Map<String,String> loginMap : listLogin){
				boolean alreadyInBase = false;
				boolean inBaseButUpdated = false;
				String login = loginMap.get(RedirectCreateMachineDomaineAction.LOGIN);
				String password = loginMap.get(RedirectCreateMachineDomaineAction.PASSWORD);
				for(Domaine_Wind_Login dwl : listLoginsEnBase){
					if(dwl.getLogin().equals(login) && dwl.getPassword().equals(password)){
						alreadyInBase = true;
						break;
					}
					else if(dwl.getLogin().equals(login)){
						inBaseButUpdated = true;
						loginMap.put("id", dwl.getId().toString());
						break;
					}
				}
				if(inBaseButUpdated){
					loginToUpdate.add(loginMap);
				}
				else if(!alreadyInBase){
					loginToAdd.add(loginMap);
				}
			}
			for(Domaine_Wind_Login dwl : listLoginsEnBase){
				boolean stillInList = false;
				String login = dwl.getLogin();
				for(Map<String,String> loginMap : listLogin){
					if(loginMap.get(RedirectCreateMachineDomaineAction.LOGIN).equals(login)){
						stillInList = true;
						break;
					}
				}
				if(!stillInList){
					loginToDelete.add(dwl.getId());
				}
			}
			
			MachineDomaineDatabaseService.modify(selectRow, libelle, loginToAdd, loginToUpdate, loginToDelete);
			
			info = MessageFormat.format(getText("machine.domaine.modification.valide"), new Object[]{libelle});
			HistoriqueDatabaseService.create(null, MessageFormat.format(getText("historique.machine.domaine.modification"), new Object[]{selectRow}), (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_MACHINES);
			libelle = null;
			
			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Modification d'un domaine de machine - ", e);
			prepareRedirectToModificationPage();
			return ERROR;
		}
	}
	
	/**
	 * Action avant redirection vers la page de modification
	 */
	private void prepareRedirectToModificationPage(){
		HttpServletRequest request = ServletActionContext.getRequest();
		
		listLogin = new ArrayList<Map<String,String>>();
		RedirectCreateMachineDomaineAction.getListLoginDomaine(request, listLogin);
	}
}