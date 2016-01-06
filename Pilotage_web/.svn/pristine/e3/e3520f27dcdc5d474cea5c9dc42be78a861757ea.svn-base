package pilotage.astreintes.actions.obligatoire;

import java.text.MessageFormat;

import pilotage.database.astreintes.AstreinteObligatoireDatabaseService;
import pilotage.database.historique.HistoriqueDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Astreinte_Domaine;
import pilotage.metier.Astreinte_Obligatoire;
import pilotage.metier.Astreinte_Type;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;

public class SupprimerAstreinteObligatoireAction extends AbstractAction{

	private static final long serialVersionUID = 3279698261363530133L;

	private Integer selectRow;
	private String sort;
	private String sens;

	public Integer getSelectRow() {
		return selectRow;
	}

	public void setSelectRow(Integer selectRow) {
		this.selectRow = selectRow;
	}

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

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		
		try {
			
			Astreinte_Obligatoire ao = AstreinteObligatoireDatabaseService.get(selectRow);
			Astreinte_Domaine adomaine = ao.getDomaine();
			Astreinte_Type aType = ao.getType();
			String domaine = adomaine.getDomaine();
			String type = aType.getType();
			
			AstreinteObligatoireDatabaseService.delete(selectRow);
			HistoriqueDatabaseService.create(null, MessageFormat.format(getText("historique.astreinte.obligatoire.suppression"), new Object[]{selectRow, domaine, type}), (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_ASTREINTES);

			info = MessageFormat.format(getText("astreinte.obligatoire.suppression.valide"), new Object[]{adomaine.getDomaine(), aType.getType()});
			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Suppression d'une astreinte obligatoire - ", e);
			return ERROR;
		}

	}

}
