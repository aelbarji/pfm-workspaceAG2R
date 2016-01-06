package pilotage.bilan;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pilotage.database.bilan.FluxCFTDatabaseService;
import pilotage.database.bilan.FluxErreurDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.service.date.DateService;

public class SaveFluxErreurAction extends AbstractAction {

	private static final long serialVersionUID = 7821972890207082383L;

	private Date selectedDate;
	private String fluxArray;

	public Date getSelectedDate() {
		return selectedDate;
	}

	public void setSelectedDate(Date selectedDate) {
		this.selectedDate = selectedDate;
	}

	public String getFluxArray() {
		return fluxArray;
	}

	public void setFluxArray(String fluxArray) {
		this.fluxArray = fluxArray;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		try {
			List<String> listFluxOK = new ArrayList<String>();
			List<String> listFluxKO = new ArrayList<String>();
			String[] strArray = fluxArray.split(",");
			for (String str : strArray) {
				if (!str.equals("")) {
					String[] dataArray = str.split(":");
					int idCFT = Integer.parseInt(dataArray[0].split("_")[1]);
					String nomFlux = FluxCFTDatabaseService.get(idCFT).getLibelle();
					if (dataArray.length == 1) {
						try {
							if (FluxErreurDatabaseService.exists(selectedDate, idCFT)) {
								FluxErreurDatabaseService.delete(idCFT, selectedDate);
								listFluxOK.add(nomFlux);
							}
						} 
						catch (Exception e) {
							listFluxKO.add(nomFlux);
						}
					}
					else {
						String libelle = dataArray[1];
						try {
							FluxErreurDatabaseService.save(idCFT, DateService.isToday(selectedDate) ? new Date() : selectedDate, libelle);
							listFluxOK.add(nomFlux);
						}
						catch (Exception e) {
							listFluxKO.add(nomFlux);
						}
					}
				}
			}
			
			if(!listFluxOK.isEmpty()){
				String listFlux = "";
				for(String fluxOK : listFluxOK)
					listFlux += fluxOK + ",";
				info = MessageFormat.format(getText("flux.erreur.modification.valide"), listFlux.substring(0, listFlux.lastIndexOf(",")));
			}
			if(!listFluxKO.isEmpty()){
				String listFlux = "";
				for(String fluxKO : listFluxKO)
					listFlux += fluxKO + ",";
				error = MessageFormat.format(getText("flux.erreur.modification.failed"), listFlux.substring(0, listFlux.lastIndexOf(",")));
			}
			
			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Bilan - Sauvegarde des flux en erreur - ", e);
			return ERROR;
		}
	}

}
