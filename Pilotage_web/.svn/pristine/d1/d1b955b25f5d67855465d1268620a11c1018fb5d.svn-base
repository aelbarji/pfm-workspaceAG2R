package pilotage.meteo.service;

import pilotage.database.meteo.MeteoServiceDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Meteo_Service;

public class RedirectModifyServiceMeteoAction extends AbstractAction{

	private static final long serialVersionUID = 3871645883339408022L;
	
	private Integer selectRow;
	private String libelle;
	private String consigne;

	public Integer getSelectRow() {
		return selectRow;
	}

	public void setSelectRow(Integer selectRow) {
		this.selectRow = selectRow;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getConsigne() {
		return consigne;
	}

	public void setConsigne(String consigne) {
		this.consigne = consigne;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		Meteo_Service service = MeteoServiceDatabaseService.get(selectRow);
		libelle = service.getService();
		consigne = service.getConsigne();
		return OK;
	}

}
