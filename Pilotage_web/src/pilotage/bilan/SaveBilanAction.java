package pilotage.bilan;

import java.util.Date;

import pilotage.database.bilan.BilanDatabaseService;
import pilotage.framework.AbstractAction;

public class SaveBilanAction extends AbstractAction {

	private static final long serialVersionUID = -7170733852944204810L;
	
	private Date selectedDate;
	private Integer vacMatin;
	private Integer vacJournee;
	private Integer vacSoir;

	public Date getSelectedDate() {
		return selectedDate;
	}

	public void setSelectedDate(Date selectedDate) {
		this.selectedDate = selectedDate;
	}

	public Integer getVacMatin() {
		return vacMatin;
	}

	public void setVacMatin(Integer vacMatin) {
		this.vacMatin = vacMatin;
	}

	public Integer getVacJournee() {
		return vacJournee;
	}

	public void setVacJournee(Integer vacJournee) {
		this.vacJournee = vacJournee;
	}

	public Integer getVacSoir() {
		return vacSoir;
	}

	public void setVacSoir(Integer vacSoir) {
		this.vacSoir = vacSoir;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		try {
			BilanDatabaseService.save(selectedDate, vacMatin, vacJournee, vacSoir);
			info = getText("vacation.modification.valide");
			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Bilan - Sauvegarde des vacations - ", e);
			return ERROR;
		}
	}

}
