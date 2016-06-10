package pilotage.incidents.itsm;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;

import org.apache.struts2.interceptor.ServletRequestAware;

import pilotage.client.CUS_HPD_InterfaceQuery_WSServiceStub;
import pilotage.client.CUS_HPD_InterfaceQuery_WSServiceStub.AuthenticationInfo;
import pilotage.client.CUS_HPD_InterfaceQuery_WSServiceStub.AuthenticationInfoE;
import pilotage.client.CUS_HPD_InterfaceQuery_WSServiceStub.GetListValues_type0;
import pilotage.client.CUS_HPD_InterfaceQuery_WSServiceStub.Incident_QueryList_Light_Service;
import pilotage.client.CUS_HPD_InterfaceQuery_WSServiceStub.Incident_QueryList_Light_ServiceResponse;
import pilotage.client.CUS_HPD_InterfaceQuery_WSServiceStub.InputMapping10;
import pilotage.client.CUS_HPD_InterfaceQuery_WSServiceStub.OutputMapping10;
import pilotage.database.incidents.itsm.IncidentsItsmDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Incidents_Itsm;

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
			try {

				// Create SOAP Connection
				SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory
						.newInstance();
				SOAPConnection soapConnection = soapConnectionFactory
						.createConnection();

				//Authentification Info
				AuthenticationInfo authenticationInfo = new AuthenticationInfo();
				authenticationInfo.setLocale("fr");
				authenticationInfo.setUserName("WSUSER");
				authenticationInfo.setPassword("almuserws");

				AuthenticationInfoE authenticationInfoE = new AuthenticationInfoE();
				authenticationInfoE.setAuthenticationInfo(authenticationInfo);

				//Incident_QueryList_Light_Service
				InputMapping10 input = new InputMapping10();
				input.setQualification("'Assigned Group'=\"DIS SUPERVISION\" AND 'Reported Date' &gt; \"20/04/2016\"");
				input.setMaxLimit("");
				input.setStartRecord("");
				Incident_QueryList_Light_Service incident_QueryList_Light_Service = new Incident_QueryList_Light_Service();
				incident_QueryList_Light_Service.setIncident_QueryList_Light_Service(input);
				// Send SOAP Message to SOAP Server
				String url = "http://incidents.getsandbox.com/arsys/services/ARService";
				CUS_HPD_InterfaceQuery_WSServiceStub hpd_InterfaceQuery_WSPortTypePortTypeProxy = new CUS_HPD_InterfaceQuery_WSServiceStub(url);
	
				Incident_QueryList_Light_ServiceResponse output = hpd_InterfaceQuery_WSPortTypePortTypeProxy.incident_QueryList_Light_Service(incident_QueryList_Light_Service, authenticationInfoE);
				// mapping output donn�e
				OutputMapping10 outputMapping = output.getIncident_QueryList_Light_ServiceResponse();
				GetListValues_type0[] listOutput = outputMapping.getGetListValues();
				getListIncidentsItsm(listOutput, list);

				//IncidentsItsmDatabaseService.saveIncidents(list);

				soapConnection.close();
			} catch (Exception e) {

				System.err.println("Error occurred while sending SOAP Request to Server");
				e.printStackTrace();
			} catch (Throwable e) {
				e.printStackTrace();
			}
			//list = IncidentsItsmClient.fetchIncidents();
			IncidentsItsmDatabaseService.saveIncidents(list);

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
	
	@SuppressWarnings("unused")

	private static List<Incidents_Itsm> getListIncidentsItsm(
			GetListValues_type0[] output, List<Incidents_Itsm> list) {

		for (GetListValues_type0 value : output) {
			Incidents_Itsm incidents_Itsm = new Incidents_Itsm();
			incidents_Itsm.setIdRequete(value.getNumero_Incident());
			incidents_Itsm.setDateCreation(value.getDate_creation().getTime());
			incidents_Itsm.setDateModification(value.getDate_modification()
					.getTime());
			incidents_Itsm.setEtat(value.getEtat());
			incidents_Itsm.setImpact(value.getImpact());
			incidents_Itsm.setNbRelance(value.getNb_Relance());
			incidents_Itsm.setPriorite(value.getPriorite());
			incidents_Itsm.setUrgence(value.getUrgence());
			incidents_Itsm.setResume(value.getType_incident());
			list.add(incidents_Itsm);
		}

		return list;

	}
}
