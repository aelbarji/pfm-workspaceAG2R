package pilotage.planning.equipes;

import java.text.MessageFormat;
import java.util.List;

import pilotage.database.historique.HistoriqueDatabaseService;
import pilotage.database.planning.PlanningEquipeDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Planning_Nom_Equipe;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;

public class SupprimerPlanningEquipesAction extends AbstractAction{

	private static final long serialVersionUID = 5215317819756935397L;
	
	private int selectRow;
	private List<Planning_Nom_Equipe> listPlanningEquipe;

	public int getSelectRow() {
		return selectRow;
	}

	public void setSelectRow(int selectRow) {
		this.selectRow = selectRow;
	}

	public List<Planning_Nom_Equipe> getListPlanningEquipe() {
		return listPlanningEquipe;
	}

	public void setListPlanningEquipe(List<Planning_Nom_Equipe> listPlanningEquipe) {
		this.listPlanningEquipe = listPlanningEquipe;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		try{
			String libelle = PlanningEquipeDatabaseService.get(selectRow).getNomEquipe();
			PlanningEquipeDatabaseService.delete(selectRow);
			info = MessageFormat.format(getText("planning.equipe.suppression.valide"), new Object[]{libelle});
			HistoriqueDatabaseService.create(null, MessageFormat.format(getText("historique.planning.equipe.suppression"), new Object[]{selectRow,libelle}), (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_PLANNING);
			listPlanningEquipe = PlanningEquipeDatabaseService.getAll();
			return OK;
		}
		catch (Exception e){
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Suppression d'une équipe de planning - ", e);
			listPlanningEquipe = PlanningEquipeDatabaseService.getAll();
			return ERROR;
		}
	}
}
