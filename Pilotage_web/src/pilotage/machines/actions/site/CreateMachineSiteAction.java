package pilotage.machines.actions.site;

import java.text.MessageFormat;

import pilotage.database.historique.HistoriqueDatabaseService;
import pilotage.database.machine.MachineSiteDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;


public class CreateMachineSiteAction extends AbstractAction {
	private static final long serialVersionUID = -1299589526541605339L;
	
	private String sort;
	private String sens;
	
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
			if(MachineSiteDatabaseService.exists(null, libelle)){
				error = MessageFormat.format(getText("machine.site.creation.existe.deja"), libelle);
				return false;
			}
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Création d'un site - ", e);
			return false;
		}
		return true;
	}

	@Override
	protected String executeMetier() {
		try{
			MachineSiteDatabaseService.create(libelle,adresse1,adresse2,cp);
			info = MessageFormat.format(getText("machine.site.creation.valide"), new Object[]{libelle});
			HistoriqueDatabaseService.create(null, MessageFormat.format(getText("historique.machine.site.creation"), new Object[]{libelle, MachineSiteDatabaseService.getId(libelle)}), (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_MACHINES);

			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Création d'un site - ", e);
			return ERROR;
		}
	}
}
