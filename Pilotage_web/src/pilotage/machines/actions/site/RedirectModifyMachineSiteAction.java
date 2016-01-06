package pilotage.machines.actions.site;

import pilotage.database.machine.MachineSiteDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Machines_Site;

public class RedirectModifyMachineSiteAction extends AbstractAction {
	private static final long serialVersionUID = -2136820498896120263L;
	
	private String sort;
	private String sens;
	
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
		return true;
	}

	@Override
	protected String executeMetier() {
		try {
			Machines_Site selectMachineSite = MachineSiteDatabaseService.get(selectRow);
			libelle = selectMachineSite.getSite();
			adresse1 = selectMachineSite.getAdresse1();
			adresse2 = selectMachineSite.getAdresse2();
			cp = selectMachineSite.getCp();
			
			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Redirection vers la modification d'un site - ", e);
			return ERROR;
		}
	}
}
