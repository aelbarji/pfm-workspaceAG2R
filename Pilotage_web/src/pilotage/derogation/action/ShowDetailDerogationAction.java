package pilotage.derogation.action;

import pilotage.database.derogation.DerogationDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Derogation;

public class ShowDetailDerogationAction extends AbstractAction{

	private static final long serialVersionUID = 4918087969870567460L;

	private Boolean fromValidation = false;
	
	private Integer selectRow;	
	private Derogation derogation;
	
	private String sort;
	private String sens;
	private int page;
	private int nrPages;
	private int nrPerPage;
	
	private String filtreNumero;
	private String filtreDate;
	private Integer filtreAppli;
	private Integer filtreTp;
	private Integer filtreDtc ;
	private Integer filtreEtat;

	public Boolean getFromValidation() {
		return fromValidation;
	}

	public void setFromValidation(Boolean fromValidation) {
		this.fromValidation = fromValidation;
	}

	public Integer getSelectRow() {
		return selectRow;
	}

	public void setSelectRow(Integer selectRow) {
		this.selectRow = selectRow;
	}

	public Derogation getDerogation() {
		return derogation;
	}

	public void setDerogation(Derogation derogation) {
		this.derogation = derogation;
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

	public String getFiltreNumero() {
		return filtreNumero;
	}

	public void setFiltreNumero(String filtreNumero) {
		this.filtreNumero = filtreNumero;
	}

	public String getFiltreDate() {
		return filtreDate;
	}

	public void setFiltreDate(String filtreDate) {
		this.filtreDate = filtreDate;
	}

	public Integer getFiltreAppli() {
		return filtreAppli;
	}

	public void setFiltreAppli(Integer filtreAppli) {
		this.filtreAppli = filtreAppli;
	}

	public Integer getFiltreTp() {
		return filtreTp;
	}

	public void setFiltreTp(Integer filtreTp) {
		this.filtreTp = filtreTp;
	}

	public Integer getFiltreDtc() {
		return filtreDtc;
	}

	public void setFiltreDtc(Integer filtreDtc) {
		this.filtreDtc = filtreDtc;
	}

	public Integer getFiltreEtat() {
		return filtreEtat;
	}

	public void setFiltreEtat(Integer filtreEtat) {
		this.filtreEtat = filtreEtat;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		
		try {
			derogation = DerogationDatabaseService.get(selectRow);
			
			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Affichage du détail d'une dérogation - ", e);
			return ERROR;
		}
		
	}
}
	
	
