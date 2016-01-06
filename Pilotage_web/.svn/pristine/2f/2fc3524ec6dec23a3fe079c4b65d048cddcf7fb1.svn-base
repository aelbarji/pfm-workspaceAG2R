package pilotage.machines.actions.site;

import java.text.MessageFormat;

import pilotage.database.historique.HistoriqueDatabaseService;
import pilotage.database.machine.MachineSiteDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Machines_Site;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;


public class ModifyMachineSiteAction extends AbstractAction{

	private static final long serialVersionUID = 7013818341148152623L;
	
	private String sort;
	private String sens;
	
	private Boolean libelleChanged;
	
	private Integer selectRow;
	private String libelle;
	private String adresse1;
	private String adresse2;
	private Integer cp;

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

	public Boolean getLibelleChanged() {
		return libelleChanged;
	}

	public void setLibelleChanged(Boolean libelleChanged) {
		this.libelleChanged = libelleChanged;
	}

	public Integer getSelectRow() {
		return selectRow;
	}

	public void setSelectRow(Integer selectRow) {
		this.selectRow = selectRow;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getAdresse1() {
		return adresse1;
	}

	public void setAdresse1(String adresse1) {
		this.adresse1 = adresse1;
	}

	public String getAdresse2() {
		return adresse2;
	}

	public void setAdresse2(String adresse2) {
		this.adresse2 = adresse2;
	}

	public Integer getCp() {
		return cp;
	}

	public void setCp(Integer cp) {
		this.cp = cp;
	}

	@Override
	protected boolean validateMetier() {
		try{
			if(libelleChanged && MachineSiteDatabaseService.exists(selectRow, libelle)){
				error = MessageFormat.format(getText("machine.site.creation.existe.deja"), libelle);
				libelle = MachineSiteDatabaseService.get(selectRow).getSite();
				return false;
			}
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Modification d'un site - ", e);
			return false;
		}
		return true;
	}

	@Override
	protected String executeMetier() {
		try{
			String historique = " ";
			Machines_Site ms = MachineSiteDatabaseService.get(selectRow);
			if (!libelle.equals(ms.getSite())) {
				historique += "libelle, ";
			}
			if (!adresse1.equals(ms.getAdresse1())) {
				historique += "adresse1, ";
			}
			if (!adresse2.equals(ms.getAdresse2())) {
				historique += "adresse2, ";
			}
			if (!cp.equals(ms.getCp())) {
				historique += "code postal, ";
			}
			MachineSiteDatabaseService.modify(selectRow, libelle, adresse1, adresse2, cp);
			info = MessageFormat.format(getText("machine.site.modification.valide"), new Object[]{libelle});
			HistoriqueDatabaseService.create(null, MessageFormat.format(getText("historique.machine.site.modification"), new Object[]{historique,selectRow}), (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_MACHINES);
			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Modification d'un site - ", e);
			return ERROR;
		}
	}
}
