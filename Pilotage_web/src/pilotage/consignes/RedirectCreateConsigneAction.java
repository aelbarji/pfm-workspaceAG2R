package pilotage.consignes;

import pilotage.database.consigne.ConsignesDatabaseService;
import pilotage.framework.AbstractActionConsigne;
import pilotage.metier.Consignes;
import pilotage.utils.Pagination;

public class RedirectCreateConsigneAction extends AbstractActionConsigne {

	private static final long serialVersionUID = 1L;
	
	private int page;
	private int nrPages;
	private int nrPerPage;
	private int pageQuotidienne;
	private int nrPagesQuotidienne;
	private int nrPerPageQuotidienne;
	private int pageInterEquipe;
	private int nrPagesInterEquipe;
	private int nrPerPageInterEquipe;
	
	private Pagination<Consignes> pagination;
	private Pagination<Consignes> paginationQuotidienne;
	private Pagination<Consignes> paginationInterEquipe;
	
	private Integer typeConsigne;
	private String libelle_type;

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

	public int getPageQuotidienne() {
		return pageQuotidienne;
	}

	public void setPageQuotidienne(int pageQuotidienne) {
		this.pageQuotidienne = pageQuotidienne;
	}

	public int getNrPagesQuotidienne() {
		return nrPagesQuotidienne;
	}

	public void setNrPagesQuotidienne(int nrPagesQuotidienne) {
		this.nrPagesQuotidienne = nrPagesQuotidienne;
	}

	public int getNrPerPageQuotidienne() {
		return nrPerPageQuotidienne;
	}

	public void setNrPerPageQuotidienne(int nrPerPageQuotidienne) {
		this.nrPerPageQuotidienne = nrPerPageQuotidienne;
	}

	public int getPageInterEquipe() {
		return pageInterEquipe;
	}

	public void setPageInterEquipe(int pageInterEquipe) {
		this.pageInterEquipe = pageInterEquipe;
	}

	public int getNrPagesInterEquipe() {
		return nrPagesInterEquipe;
	}

	public void setNrPagesInterEquipe(int nrPagesInterEquipe) {
		this.nrPagesInterEquipe = nrPagesInterEquipe;
	}

	public int getNrPerPageInterEquipe() {
		return nrPerPageInterEquipe;
	}

	public void setNrPerPageInterEquipe(int nrPerPageInterEquipe) {
		this.nrPerPageInterEquipe = nrPerPageInterEquipe;
	}

	public Pagination<Consignes> getPagination() {
		return pagination;
	}

	public void setPagination(Pagination<Consignes> pagination) {
		this.pagination = pagination;
	}

	public Pagination<Consignes> getPaginationQuotidienne() {
		return paginationQuotidienne;
	}

	public void setPaginationQuotidienne(Pagination<Consignes> paginationQuotidienne) {
		this.paginationQuotidienne = paginationQuotidienne;
	}

	public Pagination<Consignes> getPaginationInterEquipe() {
		return paginationInterEquipe;
	}

	public void setPaginationInterEquipe(Pagination<Consignes> paginationInterEquipe) {
		this.paginationInterEquipe = paginationInterEquipe;
	}

	public Integer getTypeConsigne() {
		return typeConsigne;
	}

	public void setTypeConsigne(Integer typeConsigne) {
		this.typeConsigne = typeConsigne;
	}

	public String getLibelle_type() {
		return libelle_type;
	}

	public void setLibelle_type(String libelle_type) {
		this.libelle_type = libelle_type;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		libelle_type = ConsignesDatabaseService.getType(typeConsigne).getType();
		return OK;
	}
}
