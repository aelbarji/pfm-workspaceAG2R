package pilotage.machines.actions.domaine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import pilotage.database.machine.DomaineLoginDatabaseService;
import pilotage.database.machine.MachineDomaineDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Domaine_Wind_Login;

public class RedirectModifyMachineDomaineAction extends AbstractAction {
	
	private static final long serialVersionUID = -2136820498896120263L;
	
	private int selectRow;
	private String libelle;
	private List<Map<String, String>> listLogin;
	
	public int getSelectRow() {
		return selectRow;
	}

	public void setSelectRow(int selectRow) {
		this.selectRow = selectRow;
	}

	public String getLibelle() {
		return libelle;
	}

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
		return true;
	}

	@Override
	protected String executeMetier() {
		try {
			libelle = MachineDomaineDatabaseService.get(selectRow).getNomDomaine();
			
			List<Domaine_Wind_Login> listLoginDomaine = DomaineLoginDatabaseService.getLoginsFromDomaine(selectRow);
			listLogin = new ArrayList<Map<String,String>>();
			for(Domaine_Wind_Login dwl : listLoginDomaine){
				Map<String, String> login = new HashMap<String, String>();
				login.put(RedirectCreateMachineDomaineAction.LOGIN, dwl.getLogin());
				login.put(RedirectCreateMachineDomaineAction.PASSWORD, dwl.getPassword());
				listLogin.add(login);
			}
			return OK;
		}
		catch (Exception e) {
			erreurLogger.error("Redirection vers la modification d'un domaine de machine : ", e);
			error = getText("error.message.generique") + " : " + e.getMessage();
			return ERROR;
		}
	}
}
