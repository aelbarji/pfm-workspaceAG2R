package pilotage.machines.actions.site;

import java.text.MessageFormat;

import pilotage.database.historique.HistoriqueDatabaseService;
import pilotage.database.machine.MachineSiteDatabaseService;
import pilotage.database.machine.MachinesListesDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;

public class SupprimerMachineSiteAction extends AbstractAction{

	private static final long serialVersionUID = 5215317819756935397L;
	
	private String sort;
	private String sens;
	
	private Integer selectRow;
	
	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getSens() {
		return sens;
	}

	public void setSens(String sens) {
		this.sens = sens;
	}

	public Integer getSelectRow() {
		return selectRow;
	}

	public void setSelectRow(Integer selectRow) {
		this.selectRow = selectRow;
	}

	@Override
	protected boolean validateMetier() {
		try{
			if(MachinesListesDatabaseService.hasMachineWithSite(selectRow)){
				error = getText("machine.site.suppression.failed");
				return false;
			}
			return true;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Suppression d'un site - ", e);
			return false;	
		}
	}

	@Override
	protected String executeMetier() {
		try{
			String libelle = MachineSiteDatabaseService.get(selectRow).getSite();
			MachineSiteDatabaseService.delete(selectRow);
			info = MessageFormat.format(getText("machine.site.suppression.valide"), new Object[]{libelle});
			HistoriqueDatabaseService.create(null, MessageFormat.format(getText("historique.machine.site.suppression"), new Object[]{selectRow,libelle}), (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_MACHINES);
			
			return OK;
		}
		catch (Exception e){
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Suppression d'un site - ", e);
			return ERROR;
		}
	}
}
