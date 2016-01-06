package pilotage.bilan;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pilotage.database.bilan.DisqueDatabaseService;
import pilotage.database.bilan.EspacesDisquesDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.service.date.DateService;

public class SaveEspaceDisquesAction extends AbstractAction {

	private static final long serialVersionUID = -2553320083230052851L;
	
	private Date selectedDate;
	private String espaceArray;
	private String seuilArray;
	
	public Date getSelectedDate() {
		return selectedDate;
	}

	public void setSelectedDate(Date selectedDate) {
		this.selectedDate = selectedDate;
	}

	public String getEspaceArray() {
		return espaceArray;
	}

	public void setEspaceArray(String espaceArray) {
		this.espaceArray = espaceArray;
	}

	public String getSeuilArray() {
		return seuilArray;
	}

	public void setSeuilArray(String seuilArray) {
		this.seuilArray = seuilArray;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		try{
			List<String> listEspaceOK = new ArrayList<String>();
			List<String> listEspaceKO = new ArrayList<String>();
			
			Map<Integer, Float> seuilMap = new HashMap<Integer, Float>();
			String[] seuilStringArray = seuilArray.split(",");
			for (String str : seuilStringArray) {
				if (!str.equals("")) {
					String[] dataArray = str.split(":");
					Integer key = Integer.parseInt(dataArray[0].split("_")[1]);
					Float value = null;
					if (dataArray.length > 1) {
						value = Float.parseFloat(dataArray[1]);
					}
					seuilMap.put(key, value);
				}
			}
			
			String[] strArray = espaceArray.split(",");
			for (String str : strArray) {
				if (!str.equals("")) {
					String[] dataArray = str.split(":");
					int idDisque = Integer.parseInt(dataArray[0].split("_")[1]);
					String nomDisque = DisqueDatabaseService.get(idDisque).getLibelle();
					if (dataArray.length == 1) {
						try {
							if (EspacesDisquesDatabaseService.exists(selectedDate, idDisque)) {
								EspacesDisquesDatabaseService.delete(idDisque, selectedDate);
								listEspaceOK.add(nomDisque);
							}
						} 
						catch (Exception e) {
							listEspaceKO.add(nomDisque);
						}
					}
					else {
						try {
							Float espace = Float.parseFloat(dataArray[1]);
							EspacesDisquesDatabaseService.save(idDisque, DateService.isToday(selectedDate) ? new Date() : selectedDate, espace, seuilMap.get(idDisque));
							listEspaceOK.add(nomDisque);
						}
						catch (Exception e) {
							listEspaceKO.add(nomDisque);
						}
					}
				}
			}
			
			if(!listEspaceOK.isEmpty()){
				String listEspace = "";
				for(String espaceOK : listEspaceOK)
					listEspace += espaceOK + ",";
				info = MessageFormat.format(getText("espaces.disques.modification.valide"), listEspace.substring(0, listEspace.lastIndexOf(",")));
			}
			if(!listEspaceKO.isEmpty()){
				String listEspace = "";
				for(String espaceKO : listEspaceKO)
					listEspace += espaceKO + ",";
				error = MessageFormat.format(getText("espaces.disques.modification.failed"), listEspace.substring(0, listEspace.lastIndexOf(",")));
			}
			
			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Bilan - Sauvegarde des espaces disques - ", e);
			return ERROR;
		}
	}

}
