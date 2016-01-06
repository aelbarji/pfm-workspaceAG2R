package pilotage.gup.bilan;

import java.util.Date;

import pilotage.framework.AbstractAction;

public class ShowAccueilBilanAction extends AbstractAction {

	
	private static final long serialVersionUID = -1982679129147048662L;
	private Date selectedDate;
	
	public Date getSelectedDate() {
		return selectedDate;
	}

	public void setSelectedDate(Date selectedDate) {
		this.selectedDate = selectedDate;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		if(selectedDate == null)
			selectedDate = new Date();
		return OK;
	}

}
