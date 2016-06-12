package pilotage.incidents.itsm;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import pilotage.database.incidents.itsm.IncidentsItsmDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Incidents_Itsm;
import pilotage.service.constants.PilotageConstants;

public class IncidentsItsmClient extends AbstractAction implements
ServletRequestAware {

	private static final long serialVersionUID = 1L;

	private List<Incidents_Itsm> list = new ArrayList<Incidents_Itsm>();

	private File myFile;
	private String myFileFileName;

	private HttpServletRequest servletRequest;

	@Override
	public void setServletRequest(HttpServletRequest servletRequest) {
		this.servletRequest = servletRequest;
	}

	public File getMyFile() {
		return myFile;
	}

	public void setMyFile(File myFile) {
		this.myFile = myFile;
	}

	public String getMyFileFileName() {
		return myFileFileName;
	}

	public void setMyFileFileName(String myFileFileName) {
		this.myFileFileName = myFileFileName;
	}

	@Override
	public String executeMetier() {
		try {

			if (PilotageConstants.isIncidentsItsmTimerStarted == false){
				//list = IncidentsItsmClient.fetchIncidents();
				Timer timer = new Timer();
				//execution du timer toute les 2min
				timer.schedule(new IncidentsItsmScheduledFetch(), 0, 20*6000);
				//timer.schedule(new IncidentsItsmScheduledFetch(), 0, 5000);
			}else{
				IncidentItsmFetcher.FetchData();
			}
			return OK;
		} catch (Exception e) {
			error = getText("error.message.export.excel");
			return ERROR;
		}
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

}
