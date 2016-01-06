package pilotage.statistiques;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import pilotage.database.statistiques.StatistiqueIncidentDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.service.date.DateService;

public class ShowDetailIncidentStatAction extends AbstractAction{

	private static final long serialVersionUID = 6029673443082530496L;
	private boolean redir;
	private Date selectedMois;
	private String moisEnCourStr;
	private String anneeEnCourStr;
	private Date monday;
	private Date sunday;
	private String firstDayOfMonth;
	private String lastDayOfMonth;
	private Integer redirMois;
	private Integer coupure;
	private Integer type;
	private Integer resoluPil;
	private Integer hasAstreinte;
	private boolean stat;

	public Date getSelectedMois() {
		return selectedMois;
	}

	public void setSelectedMois(Date selectedMois) {
		this.selectedMois = selectedMois;
	}


	public String getMoisEnCourStr() {
		return moisEnCourStr;
	}

	public void setMoisEnCourStr(String moisEnCourStr) {
		this.moisEnCourStr = moisEnCourStr;
	}

	public String getAnneeEnCourStr() {
		return anneeEnCourStr;
	}

	public void setAnneeEnCourStr(String anneeEnCourStr) {
		this.anneeEnCourStr = anneeEnCourStr;
	}

	public String getFirstDayOfMonth() {
		return firstDayOfMonth;
	}

	public void setFirstDayOfMonth(String firstDayOfMonth) {
		this.firstDayOfMonth = firstDayOfMonth;
	}

	public String getLastDayOfMonth() {
		return lastDayOfMonth;
	}

	public void setLastDayOfMonth(String lastDayOfMonth) {
		this.lastDayOfMonth = lastDayOfMonth;
	}

	public boolean isRedir() {
		return redir;
	}

	public void setRedir(boolean redir) {
		this.redir = redir;
	}

	public Integer getCoupure() {
		return coupure;
	}

	public void setCoupure(Integer coupure) {
		this.coupure = coupure;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getResoluPil() {
		return resoluPil;
	}

	public void setResoluPil(Integer resoluPil) {
		this.resoluPil = resoluPil;
	}

	public Integer getHasAstreinte() {
		return hasAstreinte;
	}

	public void setHasAstreinte(Integer hasAstreinte) {
		this.hasAstreinte = hasAstreinte;
	}

	public Date getMonday() {
		return monday;
	}

	public void setMonday(Date monday) {
		this.monday = monday;
	}

	public Date getSunday() {
		return sunday;
	}

	public void setSunday(Date sunday) {
		this.sunday = sunday;
	}

	public Integer getRedirMois() {
		return redirMois;
	}

	public void setRedirMois(Integer redirMois) {
		this.redirMois = redirMois;
	}

	public boolean isStat() {
		return stat;
	}

	public void setStat(boolean stat) {
		this.stat = stat;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		int nbJMax = 0;
		if(moisEnCourStr == null){
			selectedMois = new Date();
			anneeEnCourStr = new SimpleDateFormat("yyyy").format(selectedMois);
			moisEnCourStr = DateService.getMonthString(selectedMois);
		}
		else{
			String numMois;
			if(anneeEnCourStr == null || anneeEnCourStr.length() == 0){
				numMois = StatistiqueIncidentDatabaseService.convMoisEnCour(moisEnCourStr, null);
			}
			else{
				numMois = StatistiqueIncidentDatabaseService.convMoisEnCour(moisEnCourStr, anneeEnCourStr);
			}
			
			try {
				selectedMois = new Date();
				selectedMois = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(numMois);
				nbJMax = DateService.getNbDaysInMonth(selectedMois);
			}
			catch (ParseException e) {
				selectedMois = new Date();
				moisEnCourStr = DateService.getMonthString(selectedMois);
			}
		}
		String sm = null, ss = null , jours1=null, jourm1=null;
		if(monday != null && sunday != null && redirMois.equals(0)){
			Calendar Cmonday = Calendar.getInstance();
			Calendar Csunday = Calendar.getInstance();
			Cmonday.setTime(monday);
			Csunday.setTime(sunday);
			
			Integer m = (Cmonday.get(Calendar.MONTH)+1);
			if(m.toString().trim().length() == 1)
				 sm = "0" + m;
			else sm = m.toString();
			
			Integer s = (Csunday.get(Calendar.MONTH)+1);
			if(s.toString().trim().length() == 1)
				 ss = "0" + s;
			else ss = s.toString();
			
			Integer s1 = Csunday.get(Calendar.DAY_OF_MONTH);
			if(s1.toString().trim().length() == 1)
				jours1 = "0" + s1;
			else jours1 = s1.toString();
			
			Integer m1 = Cmonday.get(Calendar.DAY_OF_MONTH);
			if(m1.toString().trim().length() == 1)
				jourm1 = "0" + m1;
			else jourm1 = m1.toString();
			
			
			firstDayOfMonth = jourm1 +"/" + sm + "/" + Cmonday.get(Calendar.YEAR);
			lastDayOfMonth = jours1 + "/" + ss + "/" + Csunday.get(Calendar.YEAR);
		}
		else{
			Calendar CselectedMois = Calendar.getInstance();
			CselectedMois.setTime(selectedMois);
			Integer j = (CselectedMois.get(Calendar.MONTH)+1);;
			if(j.toString().trim().length() == 1)
				 sm = "0" + j;
			else sm = j.toString();
			nbJMax = DateService.getNbDaysInMonth(selectedMois);
			firstDayOfMonth = "01/" + sm + "/" + anneeEnCourStr;
			lastDayOfMonth = nbJMax + "/" + sm + "/" + anneeEnCourStr;
		}
		redir = true;
		setStat(true);
		return OK;
	}

}
