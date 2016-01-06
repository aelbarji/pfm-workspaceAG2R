package pilotage.astreintes.actions.astreinte;

import java.util.List;

import pilotage.database.astreintes.AstreinteDatabaseService;
import pilotage.database.astreintes.AstreinteTypeDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Astreinte;
import pilotage.metier.Astreinte_Type;

public class RedirectModifyAstreinteAction extends AbstractAction{

	private static final long serialVersionUID = -3021168839528082662L;
	
	private Integer selectRow;
	private String nom;
	private String prenom;
	private String tel1;
	private String tel2;
	private Integer type;
    private List<Astreinte_Type> aTypes;
    
	private String sort;
	private String sens;
	private int page;
	private int nrPages;
	private int nrPerPage;
	
	public List<Astreinte_Type> getATypes() {
		return aTypes;
	}

	public void setATypes(List<Astreinte_Type> aTypes) {
		this.aTypes = aTypes;
	}

	public Integer getselectRow() {
		return selectRow;
	}

	public void setselectRow(Integer selectRow) {
		this.selectRow = selectRow;
	}
	
	public Integer getSelectRow() {
		return selectRow;
	}

	public void setSelectRow(Integer selectRow) {
		this.selectRow = selectRow;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getTel1() {
		return tel1;
	}

	public void setTel1(String tel1) {
		this.tel1 = tel1;
	}

	public String getTel2() {
		return tel2;
	}

	public void setTel2(String tel2) {
		this.tel2 = tel2;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
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

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		Astreinte astreinte = AstreinteDatabaseService.get(selectRow);
		nom = astreinte.getNom();
		prenom = astreinte.getPrenom();
		tel1 = astreinte.getTel1();
		tel2 = astreinte.getTel2();
		type = astreinte.getType().getId();
		
		aTypes = AstreinteTypeDatabaseService.getAll();
		
		return OK;
	}
}
