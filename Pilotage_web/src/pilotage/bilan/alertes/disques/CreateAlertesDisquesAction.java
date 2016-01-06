package pilotage.bilan.alertes.disques;

import java.text.MessageFormat;
import java.util.Date;
import java.util.List;

import pilotage.database.bilan.AlertesDisquesDatabaseService;
import pilotage.database.bilan.AlertesTypeDatabaseService;
import pilotage.database.historique.HistoriqueDatabaseService;
import pilotage.database.machine.MachinesListesDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Alertes_Type;
import pilotage.metier.Machines_Liste;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;

public class CreateAlertesDisquesAction extends AbstractAction {

	private static final long serialVersionUID = -6671479412890018394L;
	
	private Date selectedDate;
	private List<Machines_Liste> listServer;
	private Integer systeme;
	private String fs;
	private List<Alertes_Type> listType;
	private Integer type;
	private Integer alerte;
	private Integer seuil;
	private String currentDate;
	
	/**
	 * @return the currentDate
	 */
	public String getCurrentDate() {
		return currentDate;
	}

	/**
	 * @param currentDate the currentDate to set
	 */
	public void setCurrentDate(String currentDate) {
		this.currentDate = currentDate;
	}

	/**
	 * @return the selectedDate
	 */
	public Date getSelectedDate() {
		return selectedDate;
	}

	/**
	 * @param selectedDate the selectedDate to set
	 */
	public void setSelectedDate(Date selectedDate) {
		this.selectedDate = selectedDate;
	}

	/**
	 * @return the listServer
	 */
	public List<Machines_Liste> getListServer() {
		return listServer;
	}

	/**
	 * @param listServer the listServer to set
	 */
	public void setListServer(List<Machines_Liste> listServer) {
		this.listServer = listServer;
	}

	/**
	 * @return the systeme
	 */
	public Integer getSysteme() {
		return systeme;
	}

	/**
	 * @param systeme the systeme to set
	 */
	public void setSysteme(Integer systeme) {
		this.systeme = systeme;
	}

	/**
	 * @return the fs
	 */
	public String getFs() {
		return fs;
	}

	/**
	 * @param fs the fs to set
	 */
	public void setFs(String fs) {
		this.fs = fs;
	}

	/**
	 * @return the listType
	 */
	public List<Alertes_Type> getListType() {
		return listType;
	}

	/**
	 * @param listType the listType to set
	 */
	public void setListType(List<Alertes_Type> listType) {
		this.listType = listType;
	}

	/**
	 * @return the type
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * @return the alerte
	 */
	public Integer getAlerte() {
		return alerte;
	}

	/**
	 * @param alerte the alerte to set
	 */
	public void setAlerte(Integer alerte) {
		this.alerte = alerte;
	}

	/**
	 * @return the seuil
	 */
	public Integer getSeuil() {
		return seuil;
	}

	/**
	 * @param seuil the seuil to set
	 */
	public void setSeuil(Integer seuil) {
		this.seuil = seuil;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		try{
			AlertesDisquesDatabaseService.create(selectedDate, systeme, fs, type, alerte, seuil, (Users)session.get(PilotageConstants.USER_LOGGED));
			Integer id = AlertesDisquesDatabaseService.getId(selectedDate, systeme, fs, type, alerte, seuil, (Users)session.get(PilotageConstants.USER_LOGGED));
			HistoriqueDatabaseService.create(null, MessageFormat.format(getText("historique.bilan.alerte.creation"), id), (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_BILAN);

			info = getText("alertes.disques.creation.valide");
			return OK;
		}
		catch(Exception e){
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Création d'une alerte disque - ", e);
			
			listServer = MachinesListesDatabaseService.getAll();
			listType = AlertesTypeDatabaseService.getAll();
			return ERROR;
		}
	}

}
