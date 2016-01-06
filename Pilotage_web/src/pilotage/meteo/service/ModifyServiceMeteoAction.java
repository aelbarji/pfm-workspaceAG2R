package pilotage.meteo.service;

import java.text.MessageFormat;
import java.util.List;

import pilotage.database.meteo.MeteoServiceDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Meteo_Service;
import pilotage.utils.Pagination;

public class ModifyServiceMeteoAction extends AbstractAction{

	private static final long serialVersionUID = 4265081610298839001L;

	private Integer selectRow;
	private String  libelle;
	private Boolean  libelleChanged;
	private String consigne;
	private Boolean consigneChanged;

	private int page;
	private int nrPages;
	private int nrPerPage;
	
	private String filtreService;

	private Pagination<Meteo_Service> pagination;
	private List<Meteo_Service> services;
	private Meteo_Service service;
	
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

	public Boolean getLibelleChanged() {
		return libelleChanged;
	}

	public void setLibelleChanged(Boolean libelleChanged) {
		this.libelleChanged = libelleChanged;
	}

	public String getConsigne() {
		return consigne;
	}

	public void setConsigne(String consigne) {
		this.consigne = consigne;
	}

	public Boolean getConsigneChanged() {
		return consigneChanged;
	}

	public void setConsigneChanged(Boolean consigneChanged) {
		this.consigneChanged = consigneChanged;
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

	public String getFiltreService() {
		return filtreService;
	}

	public void setFiltreService(String filtreService) {
		this.filtreService = filtreService;
	}

	public Pagination<Meteo_Service> getPagination() {
		return pagination;
	}

	public void setPagination(Pagination<Meteo_Service> pagination) {
		this.pagination = pagination;
	}

	public List<Meteo_Service> getServices() {
		return services;
	}

	public void setServices(List<Meteo_Service> services) {
		this.services = services;
	}

	public Meteo_Service getService() {
		return service;
	}

	public void setService(Meteo_Service service) {
		this.service = service;
	}

	@Override
	protected boolean validateMetier() {
		try {
			//vérifie que le nouveau nom du service n'existe pas deja
			if (libelleChanged && MeteoServiceDatabaseService.exists(selectRow, libelle)) {
				error = MessageFormat.format(getText("meteo.service.creation.existe.deja"), libelle);
				return false;
			}
		} 
		catch (Exception e) {
			error = getText("error.message.generique")+ " : " + e.getMessage();
			erreurLogger.error("Modification d'un service météo", e );
			return false;
		}
		return true;
	}

	@Override
	protected String executeMetier() {
		try {
			//if(libelleChanged || consigneChanged)
				MeteoServiceDatabaseService.modifier(selectRow, libelle, consigne);
			info = MessageFormat.format(getText("meteo.service.modification.valide"), new Object[]{libelle});
			return OK;
		} 
		catch (Exception e) {
			error = getText("error.message.generique")+ " : " + e.getMessage();
			erreurLogger.error("Modification d'un service météo", e);
			return ERROR;
		}
	}

}
