package pilotage.gup.debordement_noc;

import org.joda.time.DateTime;

import pilotage.database.gup.DebordementNOCDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Debordement_NOC;

public class RedirectModifyDebordementNOCAction extends AbstractAction{

	private static final long serialVersionUID = -2935287696922304720L;
	private Integer selectedID;
	private String operateur;
	private String inc_op;
	private String inc_noc;
	private String info_act;
	private String dateAppel;
	private String heureAppel;
	
	public Integer getSelectedID() {
		return selectedID;
	}

	public void setSelectedID(Integer selectedID) {
		this.selectedID = selectedID;
	}

	public String getOperateur() {
		return operateur;
	}

	public void setOperateur(String operateur) {
		this.operateur = operateur;
	}

	public String getInc_op() {
		return inc_op;
	}

	public void setInc_op(String inc_op) {
		this.inc_op = inc_op;
	}

	public String getInc_noc() {
		return inc_noc;
	}

	public void setInc_noc(String inc_noc) {
		this.inc_noc = inc_noc;
	}

	public String getInfo_act() {
		return info_act;
	}

	public void setInfo_act(String info_act) {
		this.info_act = info_act;
	}

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
		Debordement_NOC debNoc = DebordementNOCDatabaseService.get(selectedID);
		operateur = debNoc.getOperateur();
		inc_op = debNoc.getInc_op();
		info_act = debNoc.getInfo_act();
		inc_noc = debNoc.getInc_noc();
		
		DateTime d = new DateTime();
		d = debNoc.getDatetime();
		dateAppel = d.getDayOfMonth() + "/" +  d.getMonthOfYear() + "/" + d.getYear();
		Integer heure = d.getHourOfDay();
		String hh = heure.toString();
		if(hh.length() == 1)
			hh = "0" + hh;
		Integer minutes = d.getMinuteOfHour();
		String mm = minutes.toString();
		if(mm.length() == 1)
			mm = "0" + mm;
		heureAppel = hh + ":" + mm;
		return OK;
		}catch(Exception e){
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Redirection modification débordement NOC - ", e);
			return ERROR;
		}
	}

}
