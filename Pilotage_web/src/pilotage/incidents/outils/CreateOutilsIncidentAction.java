package pilotage.incidents.outils;

import java.text.MessageFormat;
import java.util.List;

import pilotage.database.historique.HistoriqueDatabaseService;
import pilotage.database.incidents.IncidentsOutilsDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Incidents_Outils;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;

/**
 * @author xxu
 *
 */
public class CreateOutilsIncidentAction extends AbstractAction {

	private static final long serialVersionUID = 5663158054496448188L;
	private List<Incidents_Outils> listOutils;
	private String nomOutils;
	
	
	/**
	 * @return the listOutils
	 */
	public List<Incidents_Outils> getListOutils() {
		return listOutils;
	}

	/**
	 * @param listOutils the listOutils to set
	 */
	public void setListOutils(List<Incidents_Outils> listOutils) {
		this.listOutils = listOutils;
	}

	/**
	 * @return the nomOutils
	 */
	public String getNomOutils() {
		return nomOutils;
	}

	/**
	 * @param nomOutils the nomOutils to set
	 */
	public void setNomOutils(String nomOutils) {
		this.nomOutils = nomOutils;
	}

	/* (non-Javadoc)
	 * @see pilotage.framework.AbstractAction#validateMetier()
	 */
	@Override
	protected boolean validateMetier() {
		try{
			if(IncidentsOutilsDatabaseService.exists(null, nomOutils)){
				error = MessageFormat.format(getText("incidents.outil.creation.existe.deja"), new Object[]{nomOutils});
				listOutils = IncidentsOutilsDatabaseService.getAll();
				return false;
			}
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Création d'un outil - ", e);
			listOutils = IncidentsOutilsDatabaseService.getAll();
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see pilotage.framework.AbstractAction#executeMetier()
	 */
	@Override
	protected String executeMetier() {
		try {
			IncidentsOutilsDatabaseService.create(nomOutils);
		
			info = MessageFormat.format(getText("incidents.outil.creation.valide"), nomOutils);
			HistoriqueDatabaseService.create(null, MessageFormat.format(getText("historique.incident.outil.creation"), new Object[]{nomOutils, IncidentsOutilsDatabaseService.getId(nomOutils)}), (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_INCIDENTS);

			nomOutils = null;
			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Création d'un type outil - ", e);
			return ERROR;	
		}
		finally{
			listOutils = IncidentsOutilsDatabaseService.getAll();
		}
	}

}
