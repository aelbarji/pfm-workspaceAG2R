package pilotage.incidents.qualite;

import pilotage.database.incidents.IncidentsQualiteActionDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Incidents_Qualite_Action;
import pilotage.service.date.DateService;

public class RedirectActionIncidentsQualiteAction extends AbstractAction {

	private static final long serialVersionUID = -1244408947796266773L;
	private String sort;
	private String sens;
	private int page;
	private int nrPages;
	private int nrPerPage;
	private Integer selectRow;
	
	private String sortFiche;
	private String sensFiche;
	private int pageFiche;
	private int nrPagesFiche;
	private int nrPerPageFiche;
	
	private String dateAction;
	private String action;
	private Integer ficheId;
	
	private Incidents_Qualite_Action incidentsQualiteAction;
	
	public String getSortFiche() {
		return sortFiche;
	}

	public void setSortFiche(String sortFiche) {
		this.sortFiche = sortFiche;
	}

	public String getSensFiche() {
		return sensFiche;
	}

	public void setSensFiche(String sensFiche) {
		this.sensFiche = sensFiche;
	}

	public int getPageFiche() {
		return pageFiche;
	}

	public void setPageFiche(int pageFiche) {
		this.pageFiche = pageFiche;
	}

	public int getNrPagesFiche() {
		return nrPagesFiche;
	}

	public void setNrPagesFiche(int nrPagesFiche) {
		this.nrPagesFiche = nrPagesFiche;
	}

	public int getNrPerPageFiche() {
		return nrPerPageFiche;
	}

	public void setNrPerPageFiche(int nrPerPageFiche) {
		this.nrPerPageFiche = nrPerPageFiche;
	}

	public Integer getFicheId() {
		return ficheId;
	}

	public void setFicheId(Integer ficheId) {
		this.ficheId = ficheId;
	}
	
	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getSens() {
		return sens;
	}

	public void setSens(String sens) {
		this.sens = sens;
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

	public Integer getSelectRow() {
		return selectRow;
	}

	public void setSelectRow(Integer selectRow) {
		this.selectRow = selectRow;
	}

	public String getDateAction() {
		return dateAction;
	}

	public void setDateAction(String dateAction) {
		this.dateAction = dateAction;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Incidents_Qualite_Action getIncidentsQualiteAction() {
		return incidentsQualiteAction;
	}

	public void setIncidentsQualiteAction(
			Incidents_Qualite_Action incidentsQualiteAction) {
		this.incidentsQualiteAction = incidentsQualiteAction;
	}
	
	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		
		if(selectRow != null){
			incidentsQualiteAction = IncidentsQualiteActionDatabaseService.get(selectRow);
			dateAction = DateService.dateToStr(incidentsQualiteAction.getDateAction(), DateService.p1);
			action = incidentsQualiteAction.getAction();
			ficheId = incidentsQualiteAction.getIdIncidentsQualite().getId();
		}
		return OK;
	}
}
