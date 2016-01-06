package pilotage.astreintes.actions.appel;

import java.text.MessageFormat;
import java.util.List;

import pilotage.database.astreintes.AstreinteAppelDatabaseService;
import pilotage.database.astreintes.AstreinteDatabaseService;
import pilotage.database.incidents.IncidentsDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Astreinte;
import pilotage.metier.Astreinte_Appel;
import pilotage.metier.Astreinte_Nombre_Appel_View;
import pilotage.metier.Incidents;
import pilotage.utils.Pagination;

public class DeleteAllAppelAstreinteAction extends AbstractAction{

	private static final long serialVersionUID = -2159756647950567049L;

	private Integer astreinte;
	private Integer incident;
	private String last_provenance;
	private String provenance;
	
	protected Integer typeSelected;
	
	protected int page;
	protected int nrPages;
	protected int nrPerPage;
	
	protected Pagination<Astreinte_Nombre_Appel_View> pagination;
	
	protected int pageIncident;
	protected int nrPagesIncident;
	protected int nrPerPageIncident;
	
	protected Pagination<Incidents> paginationIncident;
	
	public Integer getAstreinte() {
		return astreinte;
	}

	public void setAstreinte(Integer astreinte) {
		this.astreinte = astreinte;
	}

	public Integer getIncident() {
		return incident;
	}

	public void setIncident(Integer incident) {
		this.incident = incident;
	}

	public String getLast_provenance() {
		return last_provenance;
	}

	public void setLast_provenance(String last_provenance) {
		this.last_provenance = last_provenance;
	}

	public String getProvenance() {
		return provenance;
	}

	public void setProvenance(String provenance) {
		this.provenance = provenance;
	}

	public Integer getTypeSelected() {
		return typeSelected;
	}

	public void setTypeSelected(Integer typeSelected) {
		this.typeSelected = typeSelected;
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

	public Pagination<Astreinte_Nombre_Appel_View> getPagination() {
		return pagination;
	}

	public void setPagination(Pagination<Astreinte_Nombre_Appel_View> pagination) {
		this.pagination = pagination;
	}

	public int getPageIncident() {
		return pageIncident;
	}

	public void setPageIncident(int pageIncident) {
		this.pageIncident = pageIncident;
	}

	public int getNrPagesIncident() {
		return nrPagesIncident;
	}

	public void setNrPagesIncident(int nrPagesIncident) {
		this.nrPagesIncident = nrPagesIncident;
	}

	public int getNrPerPageIncident() {
		return nrPerPageIncident;
	}

	public void setNrPerPageIncident(int nrPerPageIncident) {
		this.nrPerPageIncident = nrPerPageIncident;
	}

	public Pagination<Incidents> getPaginationIncident() {
		return paginationIncident;
	}

	public void setPaginationIncident(Pagination<Incidents> paginationIncident) {
		this.paginationIncident = paginationIncident;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		try{
			Astreinte ast = AstreinteDatabaseService.get(astreinte);
			AstreinteAppelDatabaseService.deleteAll(astreinte, incident);
			List<Astreinte_Appel> astapp = AstreinteAppelDatabaseService.getAll();
			boolean b = false;
			for(Astreinte_Appel a : astapp){
				if(a.getIncident().getId().equals(incident)){
					b=true;
					break;
				}
			}
			if(b==false)
				IncidentsDatabaseService.updateAstreinte(incident, 0);
			info = MessageFormat.format(getText("astreinte.allappel.suppression.valide"),ast.getNomPrenom(), incident);
			return OK;
		}catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Suppression d'un appel astreinte", e);
			return ERROR;
		}
	}

}
