package pilotage.gup.debordement_noc;

import java.util.Date;

import org.joda.time.DateTime;

import pilotage.database.gup.DebordementNOCDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.service.date.DateService;

public class CreateDebordementNOCAction extends AbstractAction{

	private static final long serialVersionUID = 2247375548437028157L;
	
	private String dateAppel;
	private String heureAppel;
	private DateTime dateheure;
	private String operateur;
	private String inc_noc;
	private String inc_op;
	private String info_act;
	private Integer idDebNoc;
	
	public DateTime getDateheure() {
		return dateheure;
	}

	public void setDateheure(DateTime dateheure) {
		this.dateheure = dateheure;
	}

	public String getOperateur() {
		return operateur;
	}

	public void setOperateur(String operateur) {
		this.operateur = operateur;
	}

	public String getInc_noc() {
		return inc_noc;
	}

	public void setInc_noc(String inc_noc) {
		this.inc_noc = inc_noc;
	}

	public String getInc_op() {
		return inc_op;
	}

	public void setInc_op(String inc_op) {
		this.inc_op = inc_op;
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

	public Integer getIdDebNoc() {
		return idDebNoc;
	}

	public void setIdDebNoc(Integer idDebNoc) {
		this.idDebNoc = idDebNoc;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		try{
			Date date = new Date();
			dateAppel = dateAppel.trim();
			heureAppel = heureAppel.trim();
			String d = dateAppel + " " + heureAppel;
			date = DateService.strToDate(d);
			DateTime dateTime = new DateTime(date);
			idDebNoc = DebordementNOCDatabaseService.create(dateTime, operateur, inc_noc, inc_op, info_act);
			return OK;
		} catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Création débordement NOC - ", e);
			return ERROR;	
		}
	}

}
