package pilotage.environnement.actions.environnement;

import java.util.List;

import pilotage.database.environnement.EnvironnementDatabaseService;
import pilotage.database.environnement.EnvironnementTypeDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Environnement;
import pilotage.metier.Environnement_Type;

public class RedirectCreateEnvironnementAction extends AbstractAction{

	private static final long serialVersionUID = 2834692890022736970L;

	private Integer selectRow;
	private String  libelle;
	private Integer type;
	
	private List<Environnement_Type> environnement_Types ;
	
	private String  sens;
	private String  sort;
	private int     page;
	private int     nrPages;
	private int     nrPerPage;
	
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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public List<Environnement_Type> getEnvironnement_Types() {
		return environnement_Types;
	}

	public void setEnvironnement_Types(List<Environnement_Type> environnement_Types) {
		this.environnement_Types = environnement_Types;
	}

	public String getSens() {
		return sens;
	}

	public void setSens(String sens) {
		this.sens = sens;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
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
		return true;
	}

	@Override
	protected String executeMetier() {
		if(selectRow != null) {
			Environnement environnement = EnvironnementDatabaseService.get(selectRow);
			libelle = environnement.getEnvironnement();
			type = environnement.getType().getId();
		}
		
		environnement_Types = EnvironnementTypeDatabaseService.getAll();
		return OK;
	}

}
