package pilotage.gup.debordement_noc;

import java.util.Date;

import pilotage.framework.AbstractAction;
import pilotage.service.date.DateService;

public class RedirectCreateDebordementNOCAction extends AbstractAction{

	private static final long serialVersionUID = -2012589944921341046L;
	private String dateAppel;
	private String heureAppel;

	public String getDateAppel() {
		return dateAppel;
	}

	public void setDateAppel(String dateAppel) {
		this.dateAppel = dateAppel;
	}

	public String getHeureAppel() {
		return heureAppel;
	}

	public void setHeureAppel(String heureAppel) {
		this.heureAppel = heureAppel;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		try{
		dateAppel = DateService.dateToStr(new Date(), DateService.p1);
		heureAppel = DateService.getTime(new Date(), DateService.pt1);
		return OK;
		}catch(Exception e){
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Redirection création débordement NOC - ", e);
			return ERROR;
		}
		
	}

}
