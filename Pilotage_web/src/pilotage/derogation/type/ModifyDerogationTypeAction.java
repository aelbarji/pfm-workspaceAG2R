package pilotage.derogation.type;

import java.text.MessageFormat;

import pilotage.database.derogation.DerogationTypeDatabaseService;
import pilotage.database.historique.HistoriqueDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;


public class ModifyDerogationTypeAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String libelle;
	private Boolean libelleChanged;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public Boolean getLibelleChanged() {
		return libelleChanged;
	}

	public void setLibelleChanged(Boolean libelleChanged) {
		this.libelleChanged = libelleChanged;
	}

	@Override
	protected boolean validateMetier() {
		try{
			if(libelleChanged && DerogationTypeDatabaseService.exists(id, libelle)){
				error = MessageFormat.format(getText("derogation.type.creation.existe.deja"), new Object[]{libelle});
				libelle = DerogationTypeDatabaseService.get(id).getType();
				return false;
			}
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Modification d'un type de dérogation - ", e);
			return false;
		}
		return true;
	}

	@Override
	protected String executeMetier() {
		try{
			if(libelleChanged)
				DerogationTypeDatabaseService.modify(id, libelle);
			
			info = MessageFormat.format(getText("derogation.type.modification.valide"), new Object[]{libelle});
			HistoriqueDatabaseService.create(null, MessageFormat.format(getText("historique.derogation.type.modification"), new Object[]{id}), (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_DEROGATION);
			return OK;
		}
		catch(Exception e){
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Modification d'un type de dérogation - ", e);
			return ERROR;
		}
	}
}
