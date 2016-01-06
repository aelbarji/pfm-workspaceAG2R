package pilotage.meteo.service;

import java.text.MessageFormat;

import pilotage.database.meteo.MeteoIndicateurDatabaseService;
import pilotage.database.meteo.MeteoServiceDatabaseService;
import pilotage.framework.AbstractAction;

public class DeleteServiceMeteoAction extends AbstractAction{

	private static final long serialVersionUID = 5856824820826058035L;

	private Integer selectRow;
	private int page;
	private int nrPages;
	private int nrPerPage;
	
	public Integer getSelectRow() {
		return selectRow;
	}

	public void setSelectRow(Integer selectRow) {
		this.selectRow = selectRow;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getNrPages() {
		return nrPages;
	}

	public void setNrPages(int nrPages) {
		this.nrPages = nrPages;
	}

	public int getNrPerPage() {
		return nrPerPage;
	}

	public void setNrPerPage(int nrPerPage) {
		this.nrPerPage = nrPerPage;
	}

	@Override
	protected boolean validateMetier() {
		if(!MeteoIndicateurDatabaseService.getListIndicByService(MeteoServiceDatabaseService.get(selectRow)).isEmpty()){
			error = getText("service.meteo.suppression.failed");
			return false;
		}
		return true;
	}

	@Override
	protected String executeMetier() {
		try {
			String nomService = MeteoServiceDatabaseService.get(selectRow).getService();
			MeteoServiceDatabaseService.delete(selectRow);
			info = MessageFormat.format(getText("meteo.service.suppression.valide"), new Object[]{nomService});
			return OK;
		} 
		catch (Exception e) {
			error = getText("error.message.generique")+ ":" + e.getMessage();
			erreurLogger.error("Suppression d'un service météo", e );
			return ERROR;
		}
	}

}
