package pilotage.astreintes.actions.planning;

import java.util.Date;
import java.util.List;

import pilotage.database.astreintes.AstreinteDatabaseService;
import pilotage.database.astreintes.AstreinteDomaineDatabaseService;
import pilotage.database.astreintes.AstreintePlanningDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Astreinte;
import pilotage.metier.Astreinte_Domaine;
import pilotage.metier.Astreinte_Planning;

public class RedirectModifyAstreintePlanningAction extends AbstractAction{

	private static final long serialVersionUID = -8981730933065794489L;

	private Integer selectRow;
	private Astreinte_Planning aPlanning;
	
	private List<Astreinte> astreintes;
	private List<Astreinte_Domaine> aDomaines;
	
	private Integer domaine;
	private Integer astreinte;
	
	private String sort;
	private String sens;
	private int page;
	private int nrPages;
	private int nrPerPage;
	
	private Integer dateNB;

	private Date dateDeb;
	private Date dateFin;
	private String telephone;
	private String commentaire;
	private String infogene;

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public String getInfogene() {
		return infogene;
	}

	public void setInfogene(String infogene) {
		this.infogene = infogene;
	}

	public Date getDateDeb() {
		return dateDeb;
	}

	public void setDateDeb(Date dateDeb) {
		this.dateDeb = dateDeb;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public Integer getDateNB() {
		return dateNB;
	}

	public void setDateNB(Integer dateNB) {
		this.dateNB = dateNB;
	}

	public Integer getSelectRow() {
		return selectRow;
	}

	public void setSelectRow(Integer selectRow) {
		this.selectRow = selectRow;
	}

	public Astreinte_Planning getaPlanning() {
		return aPlanning;
	}

	public void setaPlanning(Astreinte_Planning aPlanning) {
		this.aPlanning = aPlanning;
	}

	public List<Astreinte> getAstreintes() {
		return astreintes;
	}

	public void setAstreintes(List<Astreinte> astreintes) {
		this.astreintes = astreintes;
	}

	public List<Astreinte_Domaine> getADomaines() {
		return aDomaines;
	}

	public void setADomaines(List<Astreinte_Domaine> aDomaines) {
		this.aDomaines = aDomaines;
	}

	public Integer getDomaine() {
		return domaine;
	}

	public void setDomaine(Integer domaine) {
		this.domaine = domaine;
	}

	public Integer getAstreinte() {
		return astreinte;
	}

	public void setAstreinte(Integer astreinte) {
		this.astreinte = astreinte;
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
		aPlanning = AstreintePlanningDatabaseService.get(selectRow);
		telephone = aPlanning.getTel();
		commentaire = aPlanning.getCommentaires();
		infogene = aPlanning.getInfogene();
		
		astreintes = AstreinteDatabaseService.getAll();
		aDomaines = AstreinteDomaineDatabaseService.getAll();
		
		domaine = aPlanning.getDomaine().getId();
		astreinte = aPlanning.getAstreinte().getId();
		
		dateDeb = aPlanning.getDatedeb();
		dateFin = aPlanning.getDatefin();
		
		return OK;
	}
}
